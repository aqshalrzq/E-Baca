package com.gdtidm.bacaonline.Artikel.Health.ComHealth;

import androidx.annotation.NonNull;

import com.gdtidm.bacaonline.Artikel.ModelArtikel;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponseHealth {

    @SerializedName("artikel_health")
    private List<ModelArtikel> list_health;

    @SerializedName("status")
    private boolean status;

    public List<ModelArtikel> getList_health() {
        return list_health;
    }

    public void setList_health(List<ModelArtikel> list_health) {
        this.list_health = list_health;
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
                "ResponseHealth { " +
                        "status = '" + status + '\'' +
                        ", artikel_health = '" + list_health + '\'' +
                        "}";
    }
}
