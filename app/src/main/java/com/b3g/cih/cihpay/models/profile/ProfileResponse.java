package com.b3g.cih.cihpay.models.profile;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ProfileResponse implements Serializable {

    @SerializedName("Label")
    private String Label;

    @SerializedName("Result")
    private Profile Result;

    @SerializedName("StatusCode")
    private String StatusCode;

    public String getLabel() {
        return this.Label;
    }

    public Profile getResult() {
        return this.Result;
    }

    public String getStatusCode() {
        return this.StatusCode;
    }

    public void setLabel(String str) {
        this.Label = str;
    }

    public void setResult(Profile profile) {
        this.Result = profile;
    }

    public void setStatusCode(String str) {
        this.StatusCode = str;
    }
}
