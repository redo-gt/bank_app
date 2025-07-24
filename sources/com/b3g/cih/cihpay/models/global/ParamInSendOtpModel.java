package com.b3g.cih.cihpay.models.global;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ParamInSendOtpModel implements Serializable {

    @SerializedName("CLIENT_ID")
    private String CLIENT_ID;

    @SerializedName("CODE_TEMPLATE")
    private String CODE_TEMPLATE = "1035";

    @SerializedName("IS_IVR")
    private String IS_IVR = "1";

    @SerializedName("NEED_CHECK_TRANSACTION")
    private String NEED_CHECK_TRANSACTION = "1";

    @SerializedName("SERVICEID")
    private String SERVICEID;

    @SerializedName("SESSIONID")
    private String SESSIONID;

    public ParamInSendOtpModel(String str, String str2) {
        this.CLIENT_ID = str;
        this.SESSIONID = str2;
    }

    public String getCLIENT_ID() {
        return this.CLIENT_ID;
    }

    public String getCODE_TEMPLATE() {
        return this.CODE_TEMPLATE;
    }

    public String getIS_IVR() {
        return this.IS_IVR;
    }

    public String getNEED_CHECK_TRANSACTION() {
        return this.NEED_CHECK_TRANSACTION;
    }

    public String getSERVICEID() {
        return this.SERVICEID;
    }

    public String getSESSIONID() {
        return this.SESSIONID;
    }

    public void setCLIENT_ID(String str) {
        this.CLIENT_ID = str;
    }

    public void setCODE_TEMPLATE(String str) {
        this.CODE_TEMPLATE = str;
    }

    public void setIS_IVR(String str) {
        this.IS_IVR = str;
    }

    public void setNEED_CHECK_TRANSACTION(String str) {
        this.NEED_CHECK_TRANSACTION = str;
    }

    public void setSERVICEID(String str) {
        this.SERVICEID = str;
    }

    public void setSESSIONID(String str) {
        this.SESSIONID = str;
    }
}
