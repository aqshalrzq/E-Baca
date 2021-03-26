package com.gdtidm.bacaonline.Rating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.gdtidm.bacaonline.Connection.ApiServices;
import com.gdtidm.bacaonline.Connection.InitRetrofit;
import com.gdtidm.bacaonline.MainActivity;
import com.gdtidm.bacaonline.R;
import com.gdtidm.bacaonline.Rating.CompRead.AdapterRating;
import com.gdtidm.bacaonline.Rating.CompRead.ModelRating;
import com.gdtidm.bacaonline.Rating.CompRead.ResponseRating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Rating extends AppCompatActivity {

    private RecyclerView recycler_rating;
    private SwipeRefreshLayout swipe;
    private ImageView ic_back;

    ArrayList<HashMap <String, String>> list_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rating);

        initWidget();
        functionOnClick();
        getRating();
    }

    private void initWidget() {
        recycler_rating     =   findViewById(R.id.recycler_rating);
        swipe               =   findViewById(R.id.swipe_rating);
        ic_back             =   findViewById(R.id.imageBack);
    }

    private void functionOnClick() {

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       list_rating.clear();
                       getRating();
                        Toast.makeText(Rating.this, "Data Rating sedang diperbarui, harap tunggu . . .", Toast.LENGTH_SHORT).show();
                    }
                }, 3500);
            }
        });

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rating.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getRating() {
        ProgressDialog dialog = new ProgressDialog(Rating.this);
        dialog.setTitle("E-Baca");
        dialog.setIcon(R.drawable.logo_apk);
        dialog.setMessage("Sedang menampilkan data ulasan, harap tunggu . . .");
        dialog.setCancelable(false);
        dialog.show();

        list_rating = new ArrayList<HashMap<String, String>>();
        recycler_rating.setLayoutManager(new LinearLayoutManager(Rating.this));

        ApiServices api = InitRetrofit.getInstance();
        Call<ResponseRating> ratingCall = api.getRating();

        ratingCall.enqueue(new Callback<ResponseRating>() {
            @Override
            public void onResponse(Call<ResponseRating> call, Response<ResponseRating> response) {
                Log.d("response body ", response.body().toString());
                if (response.isSuccessful()) {
                    List<ModelRating> list = response.body().getListRating();
                    boolean status = response.body().isStatus();
                    if (status) {
                        swipe.setRefreshing(false);
                        dialog.dismiss();
                        AdapterRating adapter = new AdapterRating(Rating.this, list);
                        recycler_rating.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRating> call, Throwable t) {
                t.printStackTrace();
                swipe.setRefreshing(false);
                dialog.dismiss();
                Log.d("TAG ", "fatal error : " + t.getMessage().toString());
            }
        });
    }
}