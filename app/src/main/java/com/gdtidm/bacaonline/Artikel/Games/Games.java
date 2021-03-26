package com.gdtidm.bacaonline.Artikel.Games;

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

import com.gdtidm.bacaonline.Artikel.Games.ComGames.AdapterGames;
import com.gdtidm.bacaonline.Artikel.Games.ComGames.ResponseGames;
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

public class Games extends AppCompatActivity {

    private SwipeRefreshLayout swipe_games;
    private RecyclerView recycler_games;
    private ImageView icon_back, icon_rating, icon_about;

    ArrayList<HashMap<String, String>> list_games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_games);

        initWidget();
        fungsiOnClickWidget();
        getArtikelGames();
    }

    private void initWidget() {
        swipe_games     =   findViewById(R.id.swipe_games);
        recycler_games  =   findViewById(R.id.recycler_games);
        icon_back       =   findViewById(R.id.imageBack);
        icon_rating     =   findViewById(R.id.imageRating);
        icon_about      =   findViewById(R.id.imageAbout);
    }

    private void fungsiOnClickWidget() {

        swipe_games.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_games.setRefreshing(false);
                        list_games.clear();
                        getArtikelGames();
                    }
                }, 3500);
            }
        });

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Games.this, MainActivity.class);
                startActivity(intent);
            }
        });

        icon_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Games.this);
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

        icon_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder     =   new AlertDialog.Builder(Games.this);
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
                        ProgressDialog pDialog = new ProgressDialog(Games.this);
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
                            Toast.makeText(Games.this, "Anda tidak bisa menambahkan rating kosong", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(Games.this, "Anda berhasil menambahkan sebuah ulasan!\nTerima kasih.", Toast.LENGTH_SHORT).show();
                                        } else if (response.body().getSuccess().equals("2")) {
                                            Log.d("response failed ", response.body().getMessage().toString());
                                            pDialog.dismiss();
                                            Toast.makeText(Games.this, "Anda gagal menambahkan sebuah ulasan", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Games.this, "Anda bisa balik kapan saja untuk menambahkan ulasan.", Toast.LENGTH_SHORT).show();
                    }
                });

                aldial.show();

            }
        });
    }

    private void getArtikelGames() {
        ProgressDialog dialog = new ProgressDialog(Games.this);
        dialog.setIcon(R.drawable.logo_apk);
        dialog.setMessage("Sedang mengambil data artikel, harap tunggu . . .");
        dialog.setTitle("E-Baca");
        dialog.setCancelable(false);
        dialog.show();

        list_games = new ArrayList<HashMap<String, String>>();
        recycler_games.setLayoutManager(new LinearLayoutManager(Games.this));

        ApiServices api = InitRetrofit.getInstance();
        Call<ResponseGames> gamesCall = api.getGames();

        gamesCall.enqueue(new Callback<ResponseGames>() {
            @Override
            public void onResponse(Call<ResponseGames> call, Response<ResponseGames> response) {
                if (response.isSuccessful()) {
                    Log.d("response ", response.body().toString());
                    List<ModelArtikel> data_games = response.body().getListGames();
                    boolean status = response.body().isStatus();

                    if (status) {
                        swipe_games.setRefreshing(false);
                        dialog.dismiss();
                        AdapterGames adapter = new AdapterGames(Games.this, data_games);
                        recycler_games.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGames> call, Throwable t) {
                t.printStackTrace();
                Log.d("TAG ", "fatal error : " + t.getMessage().toString());
                swipe_games.setRefreshing(false);
                dialog.dismiss();
            }
        });
    }

    private void ViewRating() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Games.this);
        builder.setTitle("E-Baca")
                .setIcon(R.drawable.logo_apk)
                .setMessage("Apakah anda ingin melihat semua ulasan untuk E-Baca?")
                .setCancelable(false);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent rating = new Intent(Games.this, Rating.class);
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