package com.gdtidm.bacaonline.Rating.CompRead;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponseRating {

    @SerializedName("rating")
    private List<ModelRating> listRating;

    @SerializedName("status")
    private boolean status;

    public List<ModelRating> getListRating() {
        return listRating;
    }

    public void setListRating(List<ModelRating> listRating) {
        this.listRating = listRating;
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
                "ResponseRating { " +
                        "status = '" + status + '\'' +
                        ", rating = '" + listRating + '\'' +
                        "}";
    }
}
