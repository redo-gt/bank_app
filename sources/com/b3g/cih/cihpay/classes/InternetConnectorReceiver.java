package com.b3g.cih.cihpay.classes;

import a.a.a.a.c.b;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.b3g.cih.cihpay.app.AppController;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class InternetConnectorReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            if (!AppController.c || b.b(context)) {
                return;
            }
            Intent intent2 = new Intent("CONNECTION_ACTIONS");
            intent2.putExtra("CONNECTED", false);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
