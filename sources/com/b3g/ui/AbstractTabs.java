package com.b3g.ui;

import com.codename1.ui.Container;
import com.codename1.ui.Tabs;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public abstract class AbstractTabs extends Container {
    public Container TabItemsCnt;
    public Container TabsCnt;
    public Container header;
    public Tabs t;
    public ArrayList tabItemList;

    public abstract Container drawHeader();

    public abstract Container drawTabs(int i);
}
