package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Walet;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class WaletListPage extends B3GPage {
    public WaletListPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setUIID("mn_cntMenuItem");
        Container container = new Container(new BoxLayout(2));
        container.setUIID("mn_cntMenuItem");
        try {
            Walet walet = new Walet();
            ArrayList WaletProcess = walet.WaletProcess();
            ArrayList arrayList = new ArrayList();
            ArrayList GetWaletListResume = walet.GetWaletListResume(WaletProcess);
            container.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Mon Portefeuille Titre", Boolean.TRUE, GetWaletListResume, 1, 8, CihMBanking.sesPMTR.getSessionClient().getClient_WaletList_StatusLabel(), null, null, null, null));
            if (GetWaletListResume.size() > 0) {
                for (int i = 0; i < WaletProcess.size(); i++) {
                    ((Walet) WaletProcess.get(i)).groupKey = ((Walet) WaletProcess.get(i)).Title;
                    arrayList.add((B3gService) WaletProcess.get(i));
                }
                Container container2 = new Container(new BoxLayout(2));
                this.uiManager.Draw_GroupBy(container2, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 6, null);
                container.addComponent(container2);
            }
        } catch (Exception unused) {
            container.addComponent(this.uiManager.GetGoBackHome());
        }
        this.thisContainer = container;
        Settings.setNightMode(this.thisContainer, 0);
        return container;
    }
}
