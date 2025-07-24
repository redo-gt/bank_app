package com.b3g.cih.cihpay.models.tokenization;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DigitizeRequest implements Serializable {

    @SerializedName("externalUserId")
    private String externalUserId;

    @SerializedName("hceTechnicalId")
    private String hceTechnicalId;

    public String getExternalUserId() {
        return this.externalUserId;
    }

    public String getHceTechnicalId() {
        return this.hceTechnicalId;
    }

    public void setExternalUserId(String str) {
        this.externalUserId = str;
    }

    public void setHceTechnicalId(String str) {
        this.hceTechnicalId = str;
    }
}
