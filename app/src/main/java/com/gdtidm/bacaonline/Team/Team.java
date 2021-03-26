package com.gdtidm.bacaonline.Team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.gdtidm.bacaonline.Connection.ApiServices;
import com.gdtidm.bacaonline.Connection.InitRetrofit;
import com.gdtidm.bacaonline.R;
import com.gdtidm.bacaonline.Team.TeamComponents.AdapterTeam;
import com.gdtidm.bacaonline.Team.TeamComponents.ItemTeam;
import com.gdtidm.bacaonline.Team.TeamComponents.ResponseTeam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Team extends AppCompatActivity {

    private WebView webView;
    private SwipeRefreshLayout swipe;
    private RecyclerView recylerTeam;

    ArrayList<HashMap<String, String>> listTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_team);

        inialisasiWidget();
        explainTeam();
        getDataTeam();
    }

    private void inialisasiWidget() {
        //WebView
        webView         =   findViewById(R.id.webview);
        //RecyclerView
        recylerTeam     =   findViewById(R.id.recycler_team);
        //SwipeRefreshLayout
        swipe           =   findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(true);
                        listTeam.clear();
                        getDataTeam();
                    }
                }, 3500);
            }
        });
    }

    private void explainTeam() {

        String teks = "Halo, salam kenal. Perkenalkan kami dari team 4 yang berasal dari kelas XII RPL A, dan kami terdiri dari 6 orang. Saat ini, kami bekerja dalam satu team untuk mengerjakan tugas Bahasa Indonesia yang bertema PBL (Project Base Learning)."
                    + "Untuk materi kali ini, kita sepakat untuk mengangkat sebuah tema yang berjudul 'Media Artikel di Android'. Mengapa kita mengangkat tema ini? Karena kita ketahui, dengan adanya perkembangan teknologi yang semakin cepat, tak dapat dipungkiri "
                    + "jika kini semua kegiatan dapat kita akses melalui teknologi, salah satunya adalah kegiatan membaca artikel pada sebuah buku. Tapi, dengan perkembangan teknologi saat ini, sebuah bacaan artikel pada buku semakin berkurang peminatnya dan semakin ditinggalkan."
                    + "Hal ini pula, yang membuat minat baca masyarakat terhadap sebuah bacaan semakin menurun. Oleh karena itu, kami dari team 4 ingin meluncurkan sebuah produk yang akan meningkatkan minat masyarakat untuk membaca sebuah artikel kembali."
                    + "Produk ini merupakan sebuah ide dan inovasi dari kami untuk memudahkan masyarakat yang ingin membaca sebuah artikel tanpa harus di suatu tempat dan tentunya bisa diakses saat pengguna berada di tempat manapun."
                    + "Produk ini, kami namakan dengan E-Baca. Dapat diakses oleh pengguna yang memiliki android, kemudian tampilan yang lebih friendly, dan tentunya artikel yang dihadirkan akan semakin menarik minat baca masyarakat. Kami berharap, E-Baca ini selain"
                    + "meningkatkan minat baca pada masyarakat, kami juga berharap produk ini juga dapat membawa manfaat yang tak terduga.";

        webView.loadData("<p style =\"text-align: justify\">" + teks + "</p>", "text/html", "UTF-8");
        webView.getSettings();
        WebSettings settings = webView.getSettings();
        settings.setDefaultFontSize(13);

    }

    private void getDataTeam() {

        ProgressDialog pDialog = new ProgressDialog(Team.this);
        pDialog.setTitle("E-Baca");
        pDialog.setMessage("Sedang mengambil data, harap tunggu sebentar...");
        pDialog.setIcon(R.drawable.logo_apk);
        pDialog.setCancelable(false);
        pDialog.show();

        listTeam = new ArrayList<HashMap<String, String>>();
        recylerTeam.setLayoutManager(new LinearLayoutManager(Team.this));

        ApiServices api = InitRetrofit.getInstance();
        Call<ResponseTeam> teamCall = api.getTeam();

        teamCall.enqueue(new Callback<ResponseTeam>() {
            @Override
            public void onResponse(Call<ResponseTeam> call, Response<ResponseTeam> response) {
                if (response.isSuccessful()) {
                    Log.d("response body ", response.body().toString());
                    List<ItemTeam> team = response.body().getDataTeam();
                    boolean status = response.body().isStatus();

                    if (status) {
                        Log.d("response success ", response.body().toString());
                        pDialog.dismiss();
                        swipe.setRefreshing(false);
                        AdapterTeam adapter = new AdapterTeam(Team.this, team);
                        recylerTeam.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTeam> call, Throwable t) {
                Log.d("TAG ", "FATAL ERROR : " + t.getMessage().toString());
                pDialog.dismiss();
            }
        });
    }

}