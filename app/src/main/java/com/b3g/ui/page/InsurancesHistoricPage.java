package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.InsuranceHistoric;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class InsurancesHistoricPage extends B3GPage {
    private String codeProduit;
    private String referenceContract;
    private final UIBuilder uib;

    public void setCodeProduit(String str) {
        this.codeProduit = str;
    }

    public void setReferenceContract(String str) {
        this.referenceContract = str;
    }

    public InsurancesHistoricPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
        this.uib = new UIBuilder();
    }

    public Container GetPageContainer() {
        try {
            this.thisContainer = this.uib.createContainer("/cihv3", "InsurancesHistoric");
            ArrayList allHistoric = new InsuranceHistoric().getAllHistoric(this.referenceContract, this.codeProduit);
            Container container = (Container) this.uib.findByName("CenterCnt", this.thisContainer);
            container.setScrollableY(true);
            container.setScrollVisible(false);
            for (int i = 0; i < allHistoric.size(); i++) {
                container.add(((InsuranceHistoric) allHistoric.get(i)).DrawItem(this.uiManager.stateMachine, this.uiManager.ressource, this.PageId, this.service, null, null));
            }
        } catch (Exception unused) {
            this.thisContainer = new Container(BoxLayout.y());
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }
}
