package com.gdtidm.bacaonline.Connection;

import com.gdtidm.bacaonline.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitRetrofit {

    public static String API_URL = "https://onportofol.000webhostapp.com/baca_online/";

    //Indonesia
    //Silahkan ganti link url sesuai dengan localhost atau hosting yang anda miliki.

    //English
    //Please change the url link according to the localhost or hosting you have.

    public static Retrofit setInit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static ApiServices getInstance() {
        return setInit().create(ApiServices.class);
    }

}
