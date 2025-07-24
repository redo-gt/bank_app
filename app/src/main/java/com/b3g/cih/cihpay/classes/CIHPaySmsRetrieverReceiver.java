package com.b3g.cih.cihpay.classes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CIHPaySmsRetrieverReceiver extends BroadcastReceiver {
    public a a;

    public interface a {
    }

    public void onReceive(Context context, Intent intent) {
        Matcher matcher;
        try {
            if ("com.google.android.gms.auth.api.phone.SMS_RETRIEVED".equals(intent.getAction())) {
                Bundle extras = intent.getExtras();
                if (((Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).getStatusCode() != 0) {
                    return;
                }
                try {
                    matcher = Pattern.compile("(|^)\\d{6}").matcher((String) extras.get("com.google.android.gms.auth.api.phone.EXTRA_SMS_MESSAGE"));
                } catch (Exception e) {
                    String str = "getOtpFromMessage ERROR :: " + e.getMessage();
                }
                String group = matcher.find() ? matcher.group(0) : "";
                if (group != null) {
                    a.a.a.a.a.h.a aVar = this.a;
                    aVar.getClass();
                    aVar.a.j.setText(group);
                }
            }
        } catch (Exception unused) {
        }
    }
}
