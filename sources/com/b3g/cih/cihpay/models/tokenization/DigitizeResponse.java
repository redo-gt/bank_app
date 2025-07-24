package com.b3g.cih.cihpay.models.tokenization;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DigitizeResponse implements Serializable {

    @SerializedName("authenticationToken")
    private String traceId;

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(String str) {
        this.traceId = str;
    }
}
