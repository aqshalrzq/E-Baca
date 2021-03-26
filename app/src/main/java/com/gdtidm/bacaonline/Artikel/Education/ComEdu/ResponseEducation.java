package com.gdtidm.bacaonline.Artikel.Education.ComEdu;

import androidx.annotation.NonNull;

import com.gdtidm.bacaonline.Artikel.ModelArtikel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseEducation {

    @SerializedName("artikel_pendidikan")
    private List<ModelArtikel> listEdu;

    @SerializedName("status")
    private boolean status;

    public List<ModelArtikel> getListEdu() {
        return listEdu;
    }

    public void setListEdu(List<ModelArtikel> listEdu) {
        this.listEdu = listEdu;
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
                "ResponseEducation { " +
                        "status = '" + status + '\'' +
                        ", artikel_pendidikan = '" + listEdu + '\'' +
                        "}";
    }
}
