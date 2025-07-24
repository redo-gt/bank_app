package com.b3g.ui.menu;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Node {
    public Object[] children;
    public String value;

    public Node(String str, Object[] objArr) {
        this.children = objArr;
        this.value = str;
    }

    public String toString() {
        return this.value;
    }
}
