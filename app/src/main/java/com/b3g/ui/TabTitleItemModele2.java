package com.b3g.ui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TabTitleItemModele2 extends AbstractTabItem {
    Button tabItemTitle;

    public Container drawTabHeaderCnt() {
        Container container = new Container(new BorderLayout());
        container.setUIID("TabsHeaderLogin");
        Container container2 = new Container(new FlowLayout(4, 4));
        Button button = new Button();
        this.tabItemTitle = button;
        button.setUIID("tabItemTitleSelectd");
        new Container().setUIID("bottomBorder");
        container2.addComponent(this.tabItemTitle);
        this.fackBtn = new Button();
        this.fackBtn.setPreferredSize(new Dimension(0, 0));
        this.fackBtn.setUIID("BlankCnt");
        container2.addComponent(this.fackBtn);
        if (this.isSelected) {
            this.tabItemTitle.setUIID("tabItemTitleSelectd");
            container.setUIID("TabsHeaderLogin");
        } else {
            this.tabItemTitle.setUIID("tabItemTitleNew");
            container.setUIID("TabsHeaderLoginNotSelectd");
        }
        this.tabItemTitle.setText(this.title);
        container.addComponent("Center", container2);
        container.setLeadComponent(this.fackBtn);
        container.revalidate();
        return container;
    }
}
