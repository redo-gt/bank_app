package com.b3g.cih.cihpay.models.tokenization;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SubscribeModel implements Serializable {

    @SerializedName("authenticationToken")
    private String authenticationToken;

    @SerializedName("hceTechnicalId")
    private String hceTechnicalId;

    @SerializedName("issuerAccountReferenceId")
    private String issuerAccountReferenceId;

    public String getAuthenticationToken() {
        return this.authenticationToken;
    }

    public String getHceTechnicalId() {
        return this.hceTechnicalId;
    }

    public String getIssuerAccountReferenceId() {
        return this.issuerAccountReferenceId;
    }

    public void setAuthenticationToken(String str) {
        this.authenticationToken = str;
    }

    public void setHceTechnicalId(String str) {
        this.hceTechnicalId = str;
    }

    public void setIssuerAccountReferenceId(String str) {
        this.issuerAccountReferenceId = str;
    }
}
