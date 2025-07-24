package com.b3g.cih.cihpay.app;

import a.a.a.a.c.a;
import android.app.Application;
import android.content.Context;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AppController extends Application {
    public static String a = "com.b3g.cih.cihpay";
    public static Context b;
    public static boolean c;

    public static boolean a(boolean z) {
        c = z;
        return z;
    }

    public static boolean hasTokenizedCards() {
        return (a.d(b) == null || a.d(b).isEmpty()) ? false : true;
    }

    public void onCreate() {
        super.onCreate();
        a = getPackageName().replace(".App", "");
        b = this;
    }
}
