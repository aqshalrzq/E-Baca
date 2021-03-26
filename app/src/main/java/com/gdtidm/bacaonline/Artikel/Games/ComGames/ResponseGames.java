package com.gdtidm.bacaonline.Artikel.Games.ComGames;

import androidx.annotation.NonNull;

import com.gdtidm.bacaonline.Artikel.ModelArtikel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGames {

    @SerializedName("artikel_games")
    private List<ModelArtikel> listGames;

    @SerializedName("status")
    private boolean status;

    public List<ModelArtikel> getListGames() {
        return listGames;
    }

    public void setListGames(List<ModelArtikel> listGames) {
        this.listGames = listGames;
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
                "ResponseGames { " +
                        "status = '" + status + '\'' +
                        ", artikel_games = '" + listGames + '\'' +
                        "}";
    }
}
