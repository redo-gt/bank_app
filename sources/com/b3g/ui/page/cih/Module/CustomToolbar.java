package com.b3g.ui.page.cih.Module;

import com.codename1.ui.Container;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CustomToolbar extends Toolbar {
    public CustomToolbar(boolean z) {
        super(z);
    }

    protected Container constructSideNavigationComponent() {
        return constructSideNavigationPanel();
    }

    Container constructSideNavigationPanel() {
        Container container = new Container(new BoxLayout(2));
        if (Settings.isNightMode()) {
            container.setUIID("SideNavigationPanelDark");
        } else {
            container.setUIID("SideNavigationPanel");
        }
        container.setScrollableY(true);
        container.setScrollVisible(getUIManager().isThemeConstant("sideMenuScrollVisibleBool", false));
        return container;
    }
}
