package com.b3g.cih.cihpay.models.global;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ParamInTokenizationModel implements Serializable {

    @SerializedName("CLIENT_ID")
    private String CLIENT_ID;

    @SerializedName("SESSIONID")
    private String SESSIONID;

    @SerializedName("authenticationToken")
    private String authenticationToken;

    @SerializedName("cardType")
    private Character cardType;

    @SerializedName("hceTechnicalId")
    private String hceTechnicalId;

    @SerializedName("issuerAccountReferenceId")
    private String issuerAccountReferenceId;

    public ParamInTokenizationModel(Character ch, String str, String str2, String str3, String str4, String str5) {
        this.cardType = ch;
        this.issuerAccountReferenceId = str;
        this.authenticationToken = str2;
        this.hceTechnicalId = str3;
        this.CLIENT_ID = str4;
        this.SESSIONID = str5;
    }

    public ParamInTokenizationModel(String str, String str2, String str3, String str4, String str5) {
        this.issuerAccountReferenceId = str;
        this.authenticationToken = str2;
        this.hceTechnicalId = str3;
        this.CLIENT_ID = str4;
        this.SESSIONID = str5;
    }

    public String getAuthenticationToken() {
        return this.authenticationToken;
    }

    public String getCLIENT_ID() {
        return this.CLIENT_ID;
    }

    public Character getCardType() {
        return this.cardType;
    }

    public String getHceTechnicalId() {
        return this.hceTechnicalId;
    }

    public String getIssuerAccountReferenceId() {
        return this.issuerAccountReferenceId;
    }

    public String getSESSIONID() {
        return this.SESSIONID;
    }

    public void setAuthenticationToken(String str) {
        this.authenticationToken = str;
    }

    public void setCLIENT_ID(String str) {
        this.CLIENT_ID = str;
    }

    public void setCardType(Character ch) {
        this.cardType = ch;
    }

    public void setHceTechnicalId(String str) {
        this.hceTechnicalId = str;
    }

    public void setIssuerAccountReferenceId(String str) {
        this.issuerAccountReferenceId = str;
    }

    public void setSESSIONID(String str) {
        this.SESSIONID = str;
    }
}
