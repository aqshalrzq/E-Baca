package com.gdtidm.bacaonline.Team.TeamComponents;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponseTeam {

    @SerializedName("data_team")
    private List<ItemTeam> dataTeam;

    @SerializedName("status")
    private boolean status;

    public List<ItemTeam> getDataTeam() {
        return dataTeam;
    }

    public void setDataTeam(List<ItemTeam> dataTeam) {
        this.dataTeam = dataTeam;
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
        return "ResponseTeam { " +
                "status = '" + status + '\'' +
                ", data_team = '" + dataTeam + '\'' +
                "}";
    }
}
