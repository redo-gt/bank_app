package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Recurring;
import com.b3g.services.RecurringDetail;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ReccuringDetailPage extends B3GPage {
    public ReccuringDetailPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        Container container = new Container(new BoxLayout(2));
        container.setUIID("mn_cntMenuItem");
        try {
            Recurring recurring = (Recurring) this.service;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = recurring.RecurringDetailList;
            for (int i = 0; i < arrayList2.size(); i++) {
                ((RecurringDetail) arrayList2.get(i)).groupKey = ((RecurringDetail) arrayList2.get(i)).Status;
                arrayList.add((B3gService) arrayList2.get(i));
            }
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "ReccuringDetailV2");
            this.uiManager.stateMachine.findLblOrganisationValue(createContainer).setText(recurring.OrganismName);
            this.uiManager.stateMachine.findLblRefValue(createContainer).setText(((RecurringDetail) recurring.RecurringDetailList.get(0)).ContractRef);
            this.uiManager.stateMachine.findLblAccountNumberValue(createContainer).setHidden(true);
            Container container2 = new Container(new BoxLayout(2));
            if (arrayList.size() > 0) {
                this.uiManager.Draw_GroupBy(container2, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 5, (B3gService) recurring.RecurringDetailList.get(0));
            } else {
                container2.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun prélèvement"));
            }
            this.uiManager.stateMachine.findCntReccuringDetailsMain(createContainer).addComponent(container2);
            container.addComponent(createContainer);
        } catch (Exception unused) {
            container.addComponent(this.uiManager.GetGoBackHome());
        }
        this.thisContainer = container;
        return container;
    }
}
