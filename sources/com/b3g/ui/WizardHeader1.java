package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class WizardHeader1 extends AbstractWizardHeader {
    public Container drawWizardHeader(String str, String str2, int i, int i2) {
        Container container = new Container(new BorderLayout());
        container.getAllStyles().setBgColor(i);
        container.getAllStyles().setBgTransparency(0);
        container.getAllStyles().setPaddingUnit(2, 2, 2, 2);
        container.getAllStyles().setPadding(1, 1, 1, 1);
        Button button = new Button(CihMBanking.theme.getImage(str2));
        button.setUIID("Container");
        Button button2 = new Button(str);
        button2.setUIID("lbl_regular_bold_center");
        Container container2 = new Container(new BorderLayout());
        container2.addComponent("Center", button2);
        container2.addComponent("East", button);
        container.addComponent("North", container2);
        return container;
    }
}
