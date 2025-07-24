package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.MTCCreancier;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MTCCreancierTopPage extends B3GPage {
    public MTCCreancierTopPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            CihMBanking.sesPMTR.getSessionClient().setClient_MTCCreancierList(null);
            CihMBanking.sesPMTR.setBillerType(1);
            ArrayList MTCCreancierTopProcess = new MTCCreancier().MTCCreancierTopProcess("T");
            this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Liste des Opérateurs", Boolean.TRUE, MTCCreancierTopProcess, MTCCreancierTopProcess.size(), 900060, "Aucun Créancier n'est disponible pour le moment", null, null, null, null));
            Container container = new Container();
            container.setUIID("g_CntTranspMsg");
            this.thisContainer.addComponent(container);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }
}
