package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TabTitleItemModeleViewPager extends AbstractTabItem {
    public TabTitleItemModeleViewPager() {
        this.tabCnt = new Container(new GridLayout(1, 1));
        this.tabCnt.setScrollableY(true);
        this.tabCnt.setScrollVisible(false);
    }

    public Container drawTabHeaderCnt() {
        Container container = new Container(new FlowLayout(4, 4));
        container.setUIID("margin_0_0_0_1");
        Button button = new Button();
        button.setUIID("Container");
        if (this.isSelected) {
            button.setIcon(CihMBanking.theme.getImage(this.selectedIcon));
        } else {
            button.setIcon(CihMBanking.theme.getImage(this.icon));
        }
        container.addComponent(button);
        container.revalidate();
        return container;
    }
}
