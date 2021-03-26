package com.gdtidm.bacaonline.Artikel.Health;

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
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.gdtidm.bacaonline.Artikel.Health.ComHealth.AdapterHealth;
import com.gdtidm.bacaonline.Artikel.Health.ComHealth.ResponseHealth;
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

public class Health extends AppCompatActivity {

    private SwipeRefreshLayout swipe_health;
    private RecyclerView recycler_health;
    private ImageView ic_back, ic_rating, ic_about;

    ArrayList<HashMap<String, String>> listHealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_health);

        initWidget();
        fungsionClick();
        getArtikelHealth();
    }

    private void initWidget() {
        swipe_health        =   findViewById(R.id.swipe_health);
        recycler_health     =   findViewById(R.id.recycler_health);
        ic_back             =   findViewById(R.id.imageBack);
        ic_rating           =   findViewById(R.id.imageRating);
        ic_about            =   findViewById(R.id.imageAbout);
    }

    private void fungsionClick() {

        swipe_health.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_health.setRefreshing(false);
                        listHealth.clear();
                        getArtikelHealth();
                    }
                }, 3500);
            }
        });

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Health.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ic_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Health.this);
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

        ic_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder     =   new AlertDialog.Builder(Health.this);
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
                        ProgressDialog pDialog = new ProgressDialog(Health.this);
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
                            Toast.makeText(Health.this, "Anda tidak bisa menambahkan rating kosong", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(Health.this, "Anda berhasil menambahkan sebuah ulasan!\nTerima kasih.", Toast.LENGTH_SHORT).show();
                                        } else if (response.body().getSuccess().equals("2")) {
                                            Log.d("response failed ", response.body().getMessage().toString());
                                            pDialog.dismiss();
                                            Toast.makeText(Health.this, "Anda gagal menambahkan sebuah ulasan", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Health.this, "Anda bisa balik kapan saja untuk menambahkan ulasan.", Toast.LENGTH_SHORT).show();
                    }
                });

                aldial.show();

            }
        });
    }

    private void getArtikelHealth() {
        ProgressDialog dialog = new ProgressDialog(Health.this);
        dialog.setIcon(R.drawable.logo_apk);
        dialog.setTitle("Education");
        dialog.setMessage("Sedang mengambil data artikel, harap tunggu . . .");
        dialog.setCancelable(false);
        dialog.show();

        listHealth  = new ArrayList<HashMap<String, String>>();
        recycler_health.setLayoutManager(new LinearLayoutManager(Health.this));

        ApiServices api = InitRetrofit.getInstance();
        Call<ResponseHealth> healthCall = api.getHealth();

        healthCall.enqueue(new Callback<ResponseHealth>() {
            @Override
            public void onResponse(Call<ResponseHealth> call, Response<ResponseHealth> response) {
                if (response.isSuccessful()) {
                    Log.d("response ", response.body().toString());
                    List<ModelArtikel> data_health = response.body().getList_health();
                    boolean status = response.body().isStatus();

                    if (status) {
                        swipe_health.setRefreshing(false);
                        dialog.dismiss();
                        AdapterHealth adapter = new AdapterHealth(Health.this, data_health);
                        recycler_health.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseHealth> call, Throwable t) {

            }
        });
    }

    private void ViewRating() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Health.this);
        builder.setTitle("E-Baca")
                .setIcon(R.drawable.logo_apk)
                .setMessage("Apakah anda ingin melihat semua ulasan untuk E-Baca?")
                .setCancelable(false);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent rating = new Intent(Health.this, Rating.class);
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