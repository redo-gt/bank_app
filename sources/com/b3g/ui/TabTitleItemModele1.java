package com.b3g.ui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TabTitleItemModele1 extends AbstractTabItem {
    Button tabItemTitle;

    public Container drawTabHeaderCnt() {
        Container container = new Container(new BorderLayout());
        container.setUIID("HeaderTabItem");
        Container container2 = new Container(new BoxLayout(2));
        Button button = new Button();
        this.tabItemTitle = button;
        button.setUIID("tabItemTitle");
        Container container3 = new Container();
        container3.setUIID("bottomBorder");
        container2.addComponent(this.tabItemTitle);
        container2.addComponent(container3);
        this.fackBtn = new Button();
        this.fackBtn.setPreferredSize(new Dimension(0, 0));
        this.fackBtn.setUIID("BlankCnt");
        container2.addComponent(this.fackBtn);
        if (this.isSelected) {
            container3.getAllStyles().setBgColor(15225615);
        } else {
            container3.getAllStyles().setBgColor(15461096);
        }
        this.tabItemTitle.setText(this.title);
        container.addComponent("North", container2);
        container.setLeadComponent(this.fackBtn);
        container.revalidate();
        return container;
    }
}
