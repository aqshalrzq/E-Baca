package com.gdtidm.bacaonline.Artikel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gdtidm.bacaonline.R;
import com.squareup.picasso.Picasso;

public class DetailArtikel extends AppCompatActivity {

    private ImageView imgArt;
    private TextView tv_judul, tv_penulis, tv_tanggal, tv_kategori;
    private WebView webdeskrip;

    String idArtikel, judul, penulis, tanggal, kategori, gambar, deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_artikel);

        inialisasiWidget();
        tampungDariContext();
        detailArt();
    }

    private void inialisasiWidget() {
        tv_judul        =   findViewById(R.id.judul);
        tv_penulis      =   findViewById(R.id.penulis);
        tv_tanggal      =   findViewById(R.id.tanggal);
        tv_kategori     =   findViewById(R.id.kategori);
        webdeskrip      =   findViewById(R.id.deskripsi);
        imgArt          =   findViewById(R.id.gambar_artikel);
    }

    private void tampungDariContext() {
        idArtikel       =   getIntent().getStringExtra("id_artikel");
        judul           =   getIntent().getStringExtra("judul");
        penulis         =   getIntent().getStringExtra("penulis");
        tanggal         =   getIntent().getStringExtra("tanggal");
        kategori        =   getIntent().getStringExtra("kategori");
        deskripsi       =   getIntent().getStringExtra("deskripsi");
        gambar          =   getIntent().getStringExtra("gambar");
    }

    private void detailArt() {
        tv_judul.setText(judul);
        tv_penulis.setText(penulis);
        tv_tanggal.setText(tanggal);
        tv_kategori.setText(kategori);
        Picasso.get().load(gambar).into(imgArt);
        webdeskrip.loadData("<p style =\"text-align: justify\">" + deskripsi + "</p>", "text/html", "UTF-8");
        webdeskrip.getSettings();
        WebSettings settings = webdeskrip.getSettings();
        settings.setDefaultFontSize(13);
    }
}