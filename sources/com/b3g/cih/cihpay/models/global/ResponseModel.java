package com.b3g.cih.cihpay.models.global;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ResponseModel implements Serializable {

    @SerializedName("ParamOut")
    private String ParamOut;

    @SerializedName("StatusCode")
    private String StatusCode;

    @SerializedName("StatusLabel")
    private String StatusLabel;

    public String getParamOut() {
        return this.ParamOut;
    }

    public String getStatusCode() {
        return this.StatusCode;
    }

    public String getStatusLabel() {
        return this.StatusLabel;
    }

    public void setParamOut(String str) {
        this.ParamOut = str;
    }

    public void setStatusCode(String str) {
        this.StatusCode = str;
    }

    public void setStatusLabel(String str) {
        this.StatusLabel = str;
    }
}
