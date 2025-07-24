package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.About;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AboutPage extends B3GPage {
    public AboutPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.addComponent(GetTitle("A PROPOS"));
        this.thisContainer.addComponent(GetAboutBlocs());
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public Container GetAboutBlocs() {
        Container container = new Container(new BoxLayout(2));
        try {
            container.setUIID("mn_cntMenuItem");
            ArrayList AboutProcess = new About().AboutProcess();
            for (int i = 0; i < AboutProcess.size(); i++) {
                if (i == 0) {
                    container.addComponent(((About) AboutProcess.get(i)).DrawItem(null, null, 0, null, null, null));
                } else {
                    container.addComponent(((About) AboutProcess.get(i)).DrawItem(null, null, 1, null, null, null));
                }
            }
            container.setScrollVisible(false);
            container.setScrollableY(true);
        } catch (Exception unused) {
            container.addComponent(this.uiManager.GetGoBackHome());
        }
        return container;
    }

    public Label GetTitle(String str) {
        Label label = new Label(str);
        label.setUIID("ad_lblValueOrgMedium");
        return label;
    }
}
