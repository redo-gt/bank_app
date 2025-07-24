package com.b3g.cih.cihpay.models.login;

import android.os.Parcel;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class LoginResult implements Serializable {

    @SerializedName("StatusCode")
    private String SessionId;

    public LoginResult() {
    }

    public LoginResult(Parcel parcel) {
        this.SessionId = parcel.readString();
    }

    public String getSessionId() {
        return this.SessionId;
    }

    public void setSessionId(String str) {
        this.SessionId = str;
    }
}
