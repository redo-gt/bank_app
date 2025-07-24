package com.b3g.cih.cihpay.models.trace;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DigitizeStatutOutputModel implements Serializable {

    @SerializedName("issuerAccountReferenceId")
    private String issuerAccountReferenceId;

    @SerializedName("traceId")
    private String traceId;

    public String getIssuerAccountReferenceId() {
        return this.issuerAccountReferenceId;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setIssuerAccountReferenceId(String str) {
        this.issuerAccountReferenceId = str;
    }

    public void setTraceId(String str) {
        this.traceId = str;
    }
}
