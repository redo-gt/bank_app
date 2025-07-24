package com.b3g.ui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public abstract class AbstractTabItem extends Container {
    public Button fackBtn;
    public String icon;
    public int id;
    public boolean isSelected;
    public String selectedIcon;
    public Container tabCnt;
    public Container tabItemBorder;
    public Button tabItemTitle;
    public String title;
    public int titleColor;

    public abstract Container drawTabHeaderCnt();
}
