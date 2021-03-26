package com.gdtidm.bacaonline.Artikel.Religion.ComReg;

import androidx.annotation.NonNull;

import com.gdtidm.bacaonline.Artikel.ModelArtikel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseReligion {

    @SerializedName("artikel_religion")
    private List<ModelArtikel> listReligion;

    @SerializedName("status")
    private boolean status;

    public List<ModelArtikel> getListReligion() {
        return listReligion;
    }

    public void setListReligion(List<ModelArtikel> listReligion) {
        this.listReligion = listReligion;
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
                "ResponseReligion { " +
                        "status = '" + status + '\'' +
                        ", artikel_religion = '" + '\'' +
                        "}";
    }
}
