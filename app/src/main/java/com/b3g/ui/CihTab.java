package com.b3g.ui;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Tabs;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CihTab implements ITab {
    public void DrawTabs(Container container, Object obj, int i, String str, String str2, Object obj2, int i2) {
    }

    public Tabs GetTabsNoHeader(Object obj, boolean z, String str) {
        ArrayList arrayList = (ArrayList) obj;
        Tabs tabs = new Tabs();
        tabs.hideTabs();
        for (int i = 0; i < arrayList.size(); i++) {
            tabs.addTab("Tab title", (Component) arrayList.get(i));
        }
        tabs.hideTabs();
        tabs.setUIID(str);
        tabs.setSwipeActivated(z);
        return tabs;
    }
}
