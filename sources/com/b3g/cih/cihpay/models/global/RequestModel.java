package com.b3g.cih.cihpay.models.global;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class RequestModel implements Serializable {

    @SerializedName("ServiceId")
    private String ServiceId;

    @SerializedName("SessionId")
    private String SessionId;

    @SerializedName("isDemo")
    private int isDemo = 0;

    @SerializedName("paramIn")
    private String paramIn;

    public int getIsDemo() {
        return this.isDemo;
    }

    public String getParamIn() {
        return this.paramIn;
    }

    public String getServiceId() {
        return this.ServiceId;
    }

    public String getSessionId() {
        return this.SessionId;
    }

    public void setIsDemo(int i) {
        this.isDemo = i;
    }

    public void setParamIn(String str) {
        this.paramIn = str;
    }

    public void setServiceId(String str) {
        this.ServiceId = str;
    }

    public void setSessionId(String str) {
        this.SessionId = str;
    }
}
