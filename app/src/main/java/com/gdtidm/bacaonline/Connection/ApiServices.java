package com.gdtidm.bacaonline.Connection;

import com.gdtidm.bacaonline.Artikel.Education.ComEdu.ResponseEducation;
import com.gdtidm.bacaonline.Artikel.Games.ComGames.ResponseGames;
import com.gdtidm.bacaonline.Artikel.Health.ComHealth.ResponseHealth;
import com.gdtidm.bacaonline.Artikel.Kinship.ComKinship.ResponseKinship;
import com.gdtidm.bacaonline.Artikel.Religion.ComReg.ResponseReligion;
import com.gdtidm.bacaonline.Artikel.Technology.ComTech.ResponseTechnology;
import com.gdtidm.bacaonline.Rating.CompCreate.CreateModelRating;
import com.gdtidm.bacaonline.Rating.CompRead.ResponseRating;
import com.gdtidm.bacaonline.Team.TeamComponents.ResponseTeam;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @GET("team/viewTeam.php")
    Call<ResponseTeam> getTeam();

    @GET("artikel/artikelPendidikan.php")
    Call<ResponseEducation> getEdu();

    @GET("artikel/artikelTechnology.php")
    Call<ResponseTechnology> getTech();

    @GET("artikel/artikelReligion.php")
    Call<ResponseReligion> getReligion();

    @GET("artikel/artikelGames.php")
    Call<ResponseGames> getGames();

    @GET("artikel/artikelHealth.php")
    Call<ResponseHealth> getHealth();

    @GET("artikel/artikelKinship.php")
    Call<ResponseKinship> getKinship();

    @FormUrlEncoded
    @POST("rating/tambahRating.php")
    Call<CreateModelRating> createRating(@Field("nama") String nama,
                                         @Field("deskripsi") String deskripsi,
                                         @Field("rating") String rating);

    @GET("rating/lihatRating.php")
    Call<ResponseRating> getRating();

}
