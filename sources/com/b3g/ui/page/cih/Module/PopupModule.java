package com.b3g.ui.page.cih.Module;

import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.GridLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class PopupModule {
    public void ShowMessageTransaction(Container container) {
        Form form = new Form();
        form.addComponent(container);
        form.setLayout(new GridLayout(1, 1));
        form.setPreferredH(Display.getInstance().getDisplayHeight());
        form.setPreferredW(Display.getInstance().getDisplayWidth());
        form.setUIID("Container");
        form.show();
    }
}
