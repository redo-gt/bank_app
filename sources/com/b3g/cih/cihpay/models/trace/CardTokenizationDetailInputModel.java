package com.b3g.cih.cihpay.models.trace;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CardTokenizationDetailInputModel implements Serializable {

    @SerializedName("issuerAccountReferenceId")
    private String issuerAccountReferenceId;

    public CardTokenizationDetailInputModel() {
    }

    public CardTokenizationDetailInputModel(String str) {
        this.issuerAccountReferenceId = str;
    }

    public String getIssuerAccountReferenceId() {
        return this.issuerAccountReferenceId;
    }

    public void setIssuerAccountReferenceId(String str) {
        this.issuerAccountReferenceId = str;
    }
}
