package com.b3g.cih.cihpay.models.others;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Currency implements Serializable {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("decimals")
    @Expose
    private Integer decimals;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("number")
    @Expose
    private String number;

    public String getCode() {
        return this.code;
    }

    public Integer getDecimals() {
        return this.decimals;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setDecimals(Integer num) {
        this.decimals = num;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNumber(String str) {
        this.number = str;
    }
}
