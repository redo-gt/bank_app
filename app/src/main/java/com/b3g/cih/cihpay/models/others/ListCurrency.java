package com.b3g.cih.cihpay.models.others;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ListCurrency implements Serializable {

    @SerializedName("currencies")
    @Expose
    private ArrayList currencies;

    public ArrayList getCurrencies() {
        return this.currencies;
    }

    public void setCurrencies(ArrayList arrayList) {
        this.currencies = arrayList;
    }
}
