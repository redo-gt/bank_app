package com.b3g.ui;

import com.codename1.ui.Image;
import com.codename1.ui.List;
import com.codename1.ui.Sheet;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3GKeyboard extends Sheet {
    private Boolean isRandom;
    private Layout layout;
    private Image left;
    private List numbers;
    private Image right;

    public B3GKeyboard() {
        super(null, "", "B3G");
        getAllStyles().setBgTransparency(0);
        hideBackButton();
        this.layout = new BorderLayout();
        getContentPane().setLayout(this.layout);
    }
}
