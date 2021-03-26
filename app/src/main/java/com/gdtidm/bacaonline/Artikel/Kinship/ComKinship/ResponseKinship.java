package com.gdtidm.bacaonline.Artikel.Kinship.ComKinship;

import androidx.annotation.NonNull;

import com.gdtidm.bacaonline.Artikel.ModelArtikel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKinship {

    @SerializedName("artikel_kinship")
    private List<ModelArtikel> listKinship;

    @SerializedName("status")
    private boolean status;

    public List<ModelArtikel> getListKinship() {
        return listKinship;
    }

    public void setListKinship(List<ModelArtikel> listKinship) {
        this.listKinship = listKinship;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "ResponseKinship { " +
                        "status = '" + status + '\'' +
                        ", artikel_kinship = '" + listKinship + '\'' +
                        "}";
    }
}
