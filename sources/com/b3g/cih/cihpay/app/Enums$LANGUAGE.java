package com.b3g.cih.cihpay.app;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public enum Enums$LANGUAGE {
    ARABIC("ar"),
    FRANCE("fr"),
    ENGLISH("en"),
    NONE(null);

    private String lang;

    Enums$LANGUAGE(String str) {
        this.lang = str;
    }

    public static Enums$LANGUAGE getLanguage(String str) {
        if (str == null) {
            return NONE;
        }
        str.hashCode();
        switch (str) {
        }
        return NONE;
    }

    public String toString() {
        return this.lang;
    }
}
