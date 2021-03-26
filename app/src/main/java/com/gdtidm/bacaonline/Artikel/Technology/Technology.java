package com.gdtidm.bacaonline.Artikel.Technology;

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

import com.gdtidm.bacaonline.Artikel.ModelArtikel;
import com.gdtidm.bacaonline.Artikel.Technology.ComTech.AdapterTechnology;
import com.gdtidm.bacaonline.Artikel.Technology.ComTech.ResponseTechnology;
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

public class Technology extends AppCompatActivity {

    private ImageView icback, icrating, icabout;
    private RecyclerView recycler_tech;
    private SwipeRefreshLayout swipe_tech;

    private ArrayList<HashMap<String, String>> list_tech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_technology);

        initWidget();
        setOnClickWidget();
        getArtikelTech();
    }

    private void initWidget() {
        icback          =   findViewById(R.id.imageBack);
        icrating        =   findViewById(R.id.imageRating);
        icabout         =   findViewById(R.id.imageAbout);
        recycler_tech   =   findViewById(R.id.recycler_tech);
        swipe_tech      =   findViewById(R.id.swipe_tech);
    }

    private void setOnClickWidget() {
        swipe_tech.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_tech.setRefreshing(false);
                        list_tech.clear();
                        getArtikelTech();
                    }
                }, 3500);
            }
        });

        icback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Technology.this, MainActivity.class);
                startActivity(intent);
            }
        });

        icabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Technology.this);
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

        icrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder     =   new AlertDialog.Builder(Technology.this);
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
                        ProgressDialog pDialog = new ProgressDialog(Technology.this);
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
                            Toast.makeText(Technology.this, "Anda tidak bisa menambahkan rating kosong", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(Technology.this, "Anda berhasil menambahkan sebuah ulasan!\nTerima kasih.", Toast.LENGTH_SHORT).show();
                                        } else if (response.body().getSuccess().equals("2")) {
                                            Log.d("response failed ", response.body().getMessage().toString());
                                            pDialog.dismiss();
                                            Toast.makeText(Technology.this, "Anda gagal menambahkan sebuah ulasan", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Technology.this, "Anda bisa balik kapan saja untuk menambahkan ulasan.", Toast.LENGTH_SHORT).show();
                    }
                });

                aldial.show();

            }
        });
    }

    private void getArtikelTech() {
        ProgressDialog pDialog = new ProgressDialog(Technology.this);
        pDialog.setTitle("E-Baca");
        pDialog.setMessage("Sedang mengambil data artikel, harap tunggu . . .");
        pDialog.setIcon(R.drawable.logo_apk);
        pDialog.setCancelable(false);
        pDialog.show();

        list_tech = new ArrayList<HashMap<String, String>>();
        recycler_tech.setLayoutManager(new LinearLayoutManager(Technology.this));

        ApiServices apiTech = InitRetrofit.getInstance();
        Call<ResponseTechnology> techCall = apiTech.getTech();

        techCall.enqueue(new Callback<ResponseTechnology>() {
            @Override
            public void onResponse(Call<ResponseTechnology> call, Response<ResponseTechnology> response) {
                if (response.isSuccessful()) {
                    Log.d("response ", response.body().toString());
                    List<ModelArtikel> data_tech = response.body().getListTech();
                    boolean status = response.body().isStatus();
                    if (status) {
                        swipe_tech.setRefreshing(false);
                        pDialog.dismiss();
                        AdapterTechnology adapter = new AdapterTechnology(Technology.this, data_tech);
                        recycler_tech.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTechnology> call, Throwable t) {
                t.printStackTrace();
                Log.d("TAG ", "fatal error : " + t.getMessage().toString());
                pDialog.dismiss();
                swipe_tech.setRefreshing(false);
            }
        });
    }

    private void ViewRating() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Technology.this);
        builder.setTitle("E-Baca")
                .setIcon(R.drawable.logo_apk)
                .setMessage("Apakah anda ingin melihat semua ulasan untuk E-Baca?")
                .setCancelable(false);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent rating = new Intent(Technology.this, Rating.class);
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