package com.gdtidm.bacaonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.gdtidm.bacaonline.Artikel.Education.Education;
import com.gdtidm.bacaonline.Artikel.Games.Games;
import com.gdtidm.bacaonline.Artikel.Health.Health;
import com.gdtidm.bacaonline.Artikel.Kinship.Kinship;
import com.gdtidm.bacaonline.Artikel.Religion.Religion;
import com.gdtidm.bacaonline.Artikel.Technology.Technology;
import com.gdtidm.bacaonline.Rating.Rating;
import com.gdtidm.bacaonline.Team.Team;

public class MainActivity extends AppCompatActivity {

    private CardView cdEducation, cdTech, cdReligion, cdGames, cdHealth, cdKinship, cdTeam, cdRating, cardRead;
    private ImageView facebook, instagram, youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        inialisasiWidget();
        fungsiSetOnClickWidget();
        linkToMediaSocial();
    }

    private void inialisasiWidget() {
        cdEducation     =   findViewById(R.id.card_education);
        cdTech          =   findViewById(R.id.card_technology);
        cdReligion      =   findViewById(R.id.card03);
        cdGames         =   findViewById(R.id.card04);
        cdHealth        =   findViewById(R.id.card05);
        cdKinship       =   findViewById(R.id.card06);
        cdTeam          =   findViewById(R.id.cd_team);
        cdRating        =   findViewById(R.id.cd_to_rating);
        cardRead        =   findViewById(R.id.card_team);
        facebook        =   findViewById(R.id.gambar02);
        youtube         =   findViewById(R.id.gambar01);
        instagram       =   findViewById(R.id.gambar03);
    }

    private void fungsiSetOnClickWidget() {

        cdEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edu = new Intent(MainActivity.this, Education.class);
                startActivity(edu);
            }
        });

        cdTech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tech = new Intent(MainActivity.this, Technology.class);
                startActivity(tech);
            }
        });

        cdReligion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent religion = new Intent(MainActivity.this, Religion.class);
                startActivity(religion);
            }
        });

        cdGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent games = new Intent(MainActivity.this, Games.class);
                startActivity(games);
            }
        });

        cdHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent health = new Intent(MainActivity.this, Health.class);
                startActivity(health);
            }
        });

        cdKinship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kinship = new Intent(MainActivity.this, Kinship.class);
                startActivity(kinship);
            }
        });

        cdRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ulasan = new Intent(MainActivity.this, Rating.class);
                startActivity(ulasan);
            }
        });

        cdTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent team = new Intent(MainActivity.this, Team.class);
                startActivity(team);
            }
        });

        cardRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent readthis = new Intent(MainActivity.this, Team.class);
                startActivity(readthis);
            }
        });
    }

    private void linkToMediaSocial() {

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.youtube.com/channel/UCGGVdb_Wh1lvn8HIoMKdiLA");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.facebook.com/SKARIGA/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.instagram.com/skariga_official/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.logo_apk)
                .setTitle("E-Baca")
                .setMessage("Apakah anda ingin keluar dari aplikasi?")
                .setCancelable(false);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.onBackPressed();
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}