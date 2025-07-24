package com.b3g.ui;

import com.b3g.services.B3gService;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3gContainer {
    public Boolean IsToBeSelected = false;
    public String Title;
    public B3gService b3gService;
    public Container cnt;

    public B3gContainer(Container container, String str) {
        this.Title = "";
        this.Title = str;
        this.cnt = container;
        Settings.setNightMode(container, 0);
    }
}
