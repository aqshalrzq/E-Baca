package com.gdtidm.bacaonline.Artikel.Technology.ComTech;

import androidx.annotation.NonNull;

import com.gdtidm.bacaonline.Artikel.ModelArtikel;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponseTechnology {

    @SerializedName("artikel_technology")
    private List<ModelArtikel> listTech;

    @SerializedName("status")
    private boolean status;

    public List<ModelArtikel> getListTech() {
        return listTech;
    }

    public void setListTech(List<ModelArtikel> listTech) {
        this.listTech = listTech;
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
                "ResponseTechnology { " +
                        "status = '" + status + '\'' +
                        ", artikel_technology = '" + listTech + '\'' +
                        "}";
    }
}
