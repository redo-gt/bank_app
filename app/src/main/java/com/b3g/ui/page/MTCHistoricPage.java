package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.MTCHistoric;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MTCHistoricPage extends B3GPage {
    public MTCHistoricPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            String str = CihMBanking.sesPMTR.getBillerType() != 0 ? "HISTORIQUE DE RECHARGES" : "HISTORIQUE DE PAIEMENTS";
            new Container(new BoxLayout(2)).setUIID("ad_CntAccountDetailsMain");
            Container container = new Container(new GridLayout(1, 1));
            container.setUIID("ad_CntBtnTab");
            Button button = new Button(str);
            button.setUIID("ad_BtnTab");
            button.setEnabled(false);
            container.addComponent(button);
            this.thisContainer.addComponent(container);
            this.thisContainer.addComponent(GetHistoricContainer());
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    public Container GetHistoricContainer() {
        return GetMTCHistoricListContainer(new MTCHistoric().MTCHistoricProcess(CihMBanking.sesPMTR.getBillerType()));
    }

    public Container GetMTCHistoricListContainer(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        MTCHistoric mTCHistoric = new MTCHistoric();
        for (int i = 0; i < arrayList.size(); i++) {
            ((MTCHistoric) arrayList.get(i)).groupKey = ((MTCHistoric) arrayList.get(i)).BillerId;
            arrayList2.add((B3gService) arrayList.get(i));
        }
        Container container = new Container(new BoxLayout(2));
        if (arrayList2.size() > 0) {
            this.uiManager.Draw_GroupBy(container, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 29, mTCHistoric);
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique ."));
        }
        return container;
    }
}
