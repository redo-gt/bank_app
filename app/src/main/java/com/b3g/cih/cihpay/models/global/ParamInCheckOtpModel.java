package com.b3g.cih.cihpay.models.global;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ParamInCheckOtpModel implements Serializable {

    @SerializedName("CLIENT_ID")
    private String CLIENT_ID;

    @SerializedName("PASSID")
    private String PASSID;

    @SerializedName("SESSIONID")
    private String SESSIONID;

    public ParamInCheckOtpModel(String str, String str2) {
        this.CLIENT_ID = str;
        this.SESSIONID = str2;
    }

    public String getCLIENT_ID() {
        return this.CLIENT_ID;
    }

    public String getPASSID() {
        return this.PASSID;
    }

    public String getSESSIONID() {
        return this.SESSIONID;
    }

    public void setCLIENT_ID(String str) {
        this.CLIENT_ID = str;
    }

    public void setPASSID(String str) {
        this.PASSID = str;
    }

    public void setSESSIONID(String str) {
        this.SESSIONID = str;
    }
}
