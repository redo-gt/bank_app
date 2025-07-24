package com.b3g.cih.cihpay.models.card;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class GetCardResponse implements Serializable {

    @SerializedName("Label")
    private String Label;

    @SerializedName("Result")
    private ArrayList Result;

    @SerializedName("StatusCode")
    private String StatusCode;

    public String getLabel() {
        return this.Label;
    }

    public ArrayList getResult() {
        return this.Result;
    }

    public String getStatusCode() {
        return this.StatusCode;
    }

    public void setLabel(String str) {
        this.Label = str;
    }

    public void setResult(ArrayList arrayList) {
        this.Result = arrayList;
    }

    public void setStatusCode(String str) {
        this.StatusCode = str;
    }
}
