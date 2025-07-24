package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.AccountOperation;
import com.b3g.services.B3gService;
import com.b3g.services.SavingAccount;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SavingAccountDetailPage extends B3GPage {
    public SavingAccountDetailPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add((SavingAccount) CihMBanking.sesPMTR.getSessionClient().getClient_SavingAccountList().get(0));
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "SavingAccountV2");
            Container container = new Container(new BoxLayout(2));
            if (arrayList.size() > 0) {
                this.uiManager.stateMachine.findLblSavingAccountNumberValue(createContainer).setText(((SavingAccount) arrayList.get(0)).accountNumber);
                this.uiManager.stateMachine.findLblSavingAccountBalanceValue(createContainer).setText(((SavingAccount) arrayList.get(0)).balance);
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = ((SavingAccount) arrayList.get(0)).AccountOperationList;
                for (int i = 0; i < arrayList3.size(); i++) {
                    ((AccountOperation) arrayList3.get(i)).groupKey = ((AccountOperation) arrayList3.get(i)).date;
                    arrayList2.add((B3gService) arrayList3.get(i));
                }
                if (arrayList2.size() > 0) {
                    this.uiManager.Draw_GroupBy(container, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 12, (B3gService) arrayList.get(0));
                    container.setScrollableX(false);
                } else {
                    container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune opération"));
                }
                ArrayList arrayList4 = new ArrayList();
                arrayList4.add(new B3gContainer(container, "OPERATIONS"));
                this.uiManager.DrawTabsWithNavigation(this.uiManager.stateMachine.findCntSavingAccountDetailsMain(createContainer), arrayList4);
                this.thisContainer.addComponent(createContainer);
            } else {
                this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Compte sur Carnet", Boolean.TRUE, arrayList, arrayList.size(), 2, CihMBanking.sesPMTR.getSessionClient().getClient_SavingAccountList_StatusLabel(), null, null, null, null));
            }
            new Container().setUIID("g_CntTranspMsg");
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }
}
