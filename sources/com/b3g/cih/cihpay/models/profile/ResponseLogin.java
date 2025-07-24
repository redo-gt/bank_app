package com.b3g.cih.cihpay.models.profile;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ResponseLogin implements Serializable {

    @SerializedName("SessionId")
    private String SessionId;

    public String getSessionId() {
        return this.SessionId;
    }

    public void setSessionId(String str) {
        this.SessionId = str;
    }
}
