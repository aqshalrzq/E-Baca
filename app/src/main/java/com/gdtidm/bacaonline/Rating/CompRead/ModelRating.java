package com.gdtidm.bacaonline.Rating.CompRead;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

public class ModelRating {

    @SerializedName("id_rating")
    private String id_rating;

    @SerializedName("nama")
    private String nama;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("rating")
    private String rating;

    public String getId_rating() {
        return id_rating;
    }

    public void setId_rating(String id_rating) {
        this.id_rating = id_rating;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "ModelRating { " +
                        "id_rating = '" + id_rating + '\'' +
                        ", nama = '" + nama + '\'' +
                        ", deskripsi = '" + deskripsi + '\'' +
                        ", rating = '" + rating + '\'' +
                        "}";
    }
}
