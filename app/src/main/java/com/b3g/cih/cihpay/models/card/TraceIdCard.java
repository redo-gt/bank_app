package com.b3g.cih.cihpay.models.card;

import a.a.a.a.c.b;
import android.content.Context;
import com.b3g.cih.cihpay.models.global.ResponseModel;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TraceIdCard implements Serializable {

    @SerializedName("TRACE_ID")
    private String TRACE_ID;

    public class 1 extends TypeToken {
    }

    public static TraceIdCard defaultData(Context context) {
        try {
            return (TraceIdCard) new Gson().fromJson(((ResponseModel) ((ArrayList) new Gson().fromJson(b.b(context, "Jsons/GetTraceId.json"), new 1().getType())).get(0)).getParamOut(), TraceIdCard.class);
        } catch (Exception unused) {
            return null;
        }
    }

    public String getTRACE_ID() {
        return this.TRACE_ID;
    }

    public void setTRACE_ID(String str) {
        this.TRACE_ID = str;
    }
}
