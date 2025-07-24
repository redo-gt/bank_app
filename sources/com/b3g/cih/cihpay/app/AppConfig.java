package com.b3g.cih.cihpay.app;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AppConfig {
    public static String a = "https://www.cihnetbis.co.ma/CihMobileV401/";

    public enum SERVICE_ID {
        SEND_OTP("900142"),
        CHECK_OTP("10715"),
        GET_CARDS("10716"),
        TOKENIZATION("10717");

        public String id;

        SERVICE_ID(String str) {
            this.id = str;
        }

        public String toString() {
            return this.id;
        }
    }
}
