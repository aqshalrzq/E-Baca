package com.gdtidm.bacaonline.Artikel.Education;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.gdtidm.bacaonline.Artikel.Education.ComEdu.AdapterEducation;
import com.gdtidm.bacaonline.Artikel.Education.ComEdu.ResponseEducation;
import com.gdtidm.bacaonline.Artikel.ModelArtikel;
import com.gdtidm.bacaonline.Connection.ApiServices;
import com.gdtidm.bacaonline.Connection.InitRetrofit;
import com.gdtidm.bacaonline.MainActivity;
import com.gdtidm.bacaonline.R;
import com.gdtidm.bacaonline.Rating.CompCreate.CreateModelRating;
import com.gdtidm.bacaonline.Rating.Rating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Education extends AppCompatActivity {

    private RecyclerView recycler_edu;
    private ImageView ic_rating, ic_back, ic_about;
    private SwipeRefreshLayout swipe_edu;

    private ArrayList<HashMap <String, String>> list_eduu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_education);

        inialisasiWidget();
        fungsiWidget();
        getDataArtikelEdu();
    }

    private void inialisasiWidget() {
        recycler_edu    =   findViewById(R.id.recycler_edu);
        ic_back         =   findViewById(R.id.imageBack);
        ic_rating       =   findViewById(R.id.imageRating);
        ic_about        =   findViewById(R.id.imageAbout);
        swipe_edu       =   findViewById(R.id.swipe_edu);
    }

    private void fungsiWidget() {
        //swipe
        swipe_edu.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_edu.setRefreshing(true);
                        list_eduu.clear();
                        getDataArtikelEdu();
                    }
                }, 3500);
            }
        });

        ic_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Education.this);
                builder.setIcon(R.drawable.logo_apk)
                        .setTitle("E-Baca")
                        .setMessage("Halaman ini adalah list artikel. Anda bisa memilih satu artikel untuk dibaca. Klik icon arah kanan panah di setiap list untuk masuk ke halaman detail artikel.")
                        .setCancelable(false);
                builder.setPositiveButton("Oke, Saya Mengerti", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Education.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        ic_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder     =   new AlertDialog.Builder(Education.this);
                LayoutInflater inflater         =   getLayoutInflater();
                View popup                      =   inflater.inflate(R.layout.dialog_rating, null);

                builder.setCancelable(false);
                builder.setView(popup);
                AlertDialog aldial              =   builder.create();
                RatingBar rating_ulasan         =   popup.findViewById(R.id.rating);
                EditText nama_ulasan            =   popup.findViewById(R.id.field_nama);
                EditText desc_ulasan            =   popup.findViewById(R.id.field_deskripsi);
                CardView cd_posting             =   popup.findViewById(R.id.cd_posting);
                ImageView ic_balik              =   popup.findViewById(R.id.ic_back);

                cd_posting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ProgressDialog pDialog = new ProgressDialog(Education.this);
                        pDialog.setIcon(R.drawable.logo_apk);
                        pDialog.setTitle("E-Baca");
                        pDialog.setMessage("Harap tunggu, ulasan anda sedang ditambahkan . . .");
                        pDialog.setCancelable(false);
                        pDialog.show();

                        String nama     =   nama_ulasan.getText().toString();
                        String desc     =   desc_ulasan.getText().toString();
                        String rati     =   String.valueOf(rating_ulasan.getRating());

                        if (nama.isEmpty()) {
                            pDialog.dismiss();
                            nama_ulasan.setError("Form nama kosong, harap diisi terlebih dahulu");
                        } else if (desc.isEmpty()) {
                            pDialog.dismiss();
                            desc_ulasan.setError("Form deskripsi kosong, harap diisi terlebih dahulu");
                        } else if (rating_ulasan.getRating() == 0) {
                            pDialog.dismiss();
                            Toast.makeText(Education.this, "Anda tidak bisa menambahkan rating kosong", Toast.LENGTH_SHORT).show();
                        } else {
                            ApiServices api = InitRetrofit.getInstance();
                            Call<CreateModelRating> createRating = api.createRating(nama, desc, rati);

                            createRating.enqueue(new Callback<CreateModelRating>() {
                                @Override
                                public void onResponse(Call<CreateModelRating> call, Response<CreateModelRating> response) {
                                    if (response.isSuccessful()) {
                                        Log.d("response body", response.body().getMessage().toString());
                                        if (response.body().getSuccess().equals("1")) {
                                            Log.d("response success ", response.body().getMessage().toString());
                                            pDialog.dismiss();
                                            ViewRating();
                                            Toast.makeText(Education.this, "Anda berhasil menambahkan sebuah ulasan!\nTerima kasih.", Toast.LENGTH_SHORT).show();
                                        } else if (response.body().getSuccess().equals("2")) {
                                            Log.d("response failed ", response.body().getMessage().toString());
                                            pDialog.dismiss();
                                            Toast.makeText(Education.this, "Anda gagal menambahkan sebuah ulasan", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<CreateModelRating> call, Throwable t) {
                                    t.printStackTrace();
                                    Log.d("TAG , ", "fatal error : " + t.getMessage().toString());
                                    pDialog.dismiss();
                                }
                            });
                        }
                    }
                });

                ic_balik.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aldial.dismiss();
                        Toast.makeText(Education.this, "Anda bisa balik kapan saja untuk menambahkan ulasan.", Toast.LENGTH_SHORT).show();
                    }
                });

                aldial.show();

            }
        });
    }

    private void getDataArtikelEdu() {
        ProgressDialog dialog = new ProgressDialog(Education.this);
        dialog.setIcon(R.drawable.logo_apk);
        dialog.setTitle("Education");
        dialog.setMessage("Sedang mengambil data artikel, harap tunggu . . .");
        dialog.setCancelable(false);
        dialog.show();

        list_eduu = new ArrayList<HashMap<String, String>>();
        recycler_edu.setLayoutManager(new LinearLayoutManager(Education.this));

        ApiServices api = InitRetrofit.getInstance();
        Call<ResponseEducation> eduCall = api.getEdu();

        eduCall.enqueue(new Callback<ResponseEducation>() {
            @Override
            public void onResponse(Call<ResponseEducation> call, Response<ResponseEducation> response) {
                if (response.isSuccessful()) {
                    Log.d("response : ", response.body().toString());
                    List<ModelArtikel> listArt = response.body().getListEdu();
                    boolean status = response.body().isStatus();

                    if (status) {
                        swipe_edu.setRefreshing(false);
                        AdapterEducation adapter = new AdapterEducation(Education.this, listArt);
                        recycler_edu.setAdapter(adapter);
                        dialog.dismiss();
                    }
                } else {
                    swipe_edu.setRefreshing(false);
                    Log.d("response error : ", response.body().toString());
                    dialog.dismiss();
                    Toast.makeText(Education.this, "Gagal mengambil data!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEducation> call, Throwable t) {
                t.printStackTrace();
                swipe_edu.setRefreshing(false);
                Log.d("response error : ", t.getMessage().toString());
                dialog.dismiss();
            }
        });
    }

    private void ViewRating() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Education.this);
        builder.setTitle("E-Baca")
                .setIcon(R.drawable.logo_apk)
                .setMessage("Apakah anda ingin melihat semua ulasan untuk E-Baca?")
                .setCancelable(false);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent rating = new Intent(Education.this, Rating.class);
                startActivity(rating);
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}