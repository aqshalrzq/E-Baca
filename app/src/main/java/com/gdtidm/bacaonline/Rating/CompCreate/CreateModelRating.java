package com.gdtidm.bacaonline.Rating.CompCreate;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

public class CreateModelRating {

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "CreateModelRating { " +
                        "success = '" + success + '\'' +
                        ", message = '" + message + '\'' +
                        "}";
    }
}
