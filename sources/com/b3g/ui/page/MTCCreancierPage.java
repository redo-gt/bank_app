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
public class MTCCreancierPage extends B3GPage {
    public MTCCreancierPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            CihMBanking.sesPMTR.getSessionClient().setClient_MTCCPmFactreancierList(null);
            CihMBanking.sesPMTR.setBillerType(0);
            ArrayList MTCCreancierProcess = new MTCCreancier().MTCCreancierProcess("B");
            this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Liste des Facturiers", Boolean.TRUE, MTCCreancierProcess, MTCCreancierProcess.size(), 900060, "Aucun Créancier n'est disponible pour le moment", null, null, null, null));
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

    public static String getPathUrlFromBinnerId(String str) {
        ArrayList MTCCreancierProcess;
        MTCCreancier mTCCreancier = new MTCCreancier();
        if (CihMBanking.sesPMTR.MTCCreancierList != null) {
            MTCCreancierProcess = CihMBanking.sesPMTR.MTCCreancierList;
        } else {
            MTCCreancierProcess = mTCCreancier.MTCCreancierProcess("B");
        }
        String str2 = "";
        for (int i = 0; i < MTCCreancierProcess.size(); i++) {
            if (((MTCCreancier) MTCCreancierProcess.get(i)).CodeCreancier.equals(str)) {
                str2 = ((MTCCreancier) MTCCreancierProcess.get(i)).LogoPath;
            }
        }
        return str2;
    }
}
