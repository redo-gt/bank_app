package com.b3g.ui.menu;

import com.codename1.ui.Image;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class NodeB3G {
    public Image Icon;
    public int ParentId;
    public String Type;
    public String Uiid;
    public NodeB3G[] children;
    public int id;
    public int isLeaf;
    public int pageId;
    public int serviceId;
    public String value;

    public NodeB3G(String str, int i, Image image, String str2, int i2, String str3, NodeB3G[] nodeB3GArr, int i3, int i4, int i5) {
        this.children = nodeB3GArr;
        this.value = str;
        this.id = i;
        this.Icon = image;
        this.Type = str2;
        this.ParentId = i2;
        this.Uiid = str3;
        this.serviceId = i4;
        this.pageId = i5;
    }

    public NodeB3G[] GetChildren() {
        return this.children;
    }

    public String GetLabel() {
        return this.value;
    }

    public int GetId() {
        return this.id;
    }

    public Image GetIcon() {
        return this.Icon;
    }

    public String GetType() {
        return this.Type;
    }

    public int GetParentId() {
        return this.ParentId;
    }

    public String GetUiid() {
        return this.Uiid;
    }

    public int GetIsLeaf() {
        return this.isLeaf;
    }

    public int GetServiceId() {
        return this.serviceId;
    }
}
