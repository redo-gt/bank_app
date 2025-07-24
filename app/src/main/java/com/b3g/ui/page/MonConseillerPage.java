package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.MonConseiller;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MonConseillerPage extends B3GPage {
    public MonConseillerPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            new MonConseiller();
            new MonConseiller();
            MonConseiller MonConseillerProcess = MonConseiller.MonConseillerProcess();
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "GloabalContainerV2");
            this.uiManager.stateMachine.findLblGlobalHeaderTitle(createContainer).setText("Mon Conseiller");
            createContainer.addComponent(MonConseillerProcess.DrawItem(this.uiManager.stateMachine, this.uiManager.ressource, this.PageId, this.service, null, null));
            this.thisContainer.addComponent(createContainer);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }
}
