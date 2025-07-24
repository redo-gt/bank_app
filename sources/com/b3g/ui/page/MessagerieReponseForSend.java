package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MessagerieReponseForSend extends B3GPage {
    UIBuilder uib = new UIBuilder();

    public MessagerieReponseForSend(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setScrollVisible(false);
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "MessagerieReponseForSend");
        Label label = (Label) uIBuilder.findByName("ObjetLbl", createContainer);
        Container container = (Container) uIBuilder.findByName("MessageCnt", createContainer);
        BrowserComponent browserComponent = new BrowserComponent();
        browserComponent.setPage(CihMBanking.sesPMTR.MessageBody.Body, "");
        container.add(browserComponent);
        container.setScrollVisible(false);
        browserComponent.setScrollVisible(false);
        label.setText(CihMBanking.sesPMTR.MessageBody.subject);
        ((Label) uIBuilder.findByName("MessageDate", createContainer)).setText(CihMBanking.sesPMTR.MessageBody.date);
        this.thisContainer.setPreferredH(createContainer.getPreferredH());
        this.thisContainer.setPreferredW(createContainer.getPreferredW());
        this.thisContainer.add(createContainer);
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }
}
