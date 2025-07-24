package com.b3g.cih.cihpay.models.profile;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class LoginInputToeknization implements Serializable {

    @SerializedName("ClientId")
    private String ClientId;

    @SerializedName("ClientSecret")
    private String ClientSecret;

    @SerializedName("Password")
    private String Password;

    @SerializedName("UserName")
    private String UserName;

    public LoginInputToeknization() {
        this.Password = "";
        this.ClientId = "";
        this.ClientSecret = "";
    }

    public LoginInputToeknization(String str, String str2) {
        this.Password = "";
        this.ClientSecret = "";
        this.UserName = str;
        this.ClientId = str2;
    }

    public String getClientId() {
        return this.ClientId;
    }

    public String getClientSecret() {
        return this.ClientSecret;
    }

    public String getPassword() {
        return this.Password;
    }

    public String getUserName() {
        return this.UserName;
    }

    public void setClientId(String str) {
        this.ClientId = str;
    }

    public void setClientSecret(String str) {
        this.ClientSecret = str;
    }

    public void setPassword(String str) {
        this.Password = str;
    }

    public void setUserName(String str) {
        this.UserName = str;
    }
}
