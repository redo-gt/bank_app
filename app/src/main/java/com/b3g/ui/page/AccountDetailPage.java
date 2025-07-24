package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.Account;
import com.b3g.services.AccountAutorisation;
import com.b3g.services.AccountOperation;
import com.b3g.services.B3gService;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AccountDetailPage extends B3GPage {
    public AccountDetailPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        Container container = new Container(new BoxLayout(2));
        container.setUIID("mn_cntMenuItem");
        try {
            Account account = (Account) this.service;
            CihMBanking.sesPMTR.setCurrentAccount(account);
            ArrayList arrayList = new ArrayList();
            ArrayList AccountOperationProcess = account.AccountOperationProcess(300021);
            for (int i = 0; i < AccountOperationProcess.size(); i++) {
                ((AccountOperation) AccountOperationProcess.get(i)).groupKey = ((AccountOperation) AccountOperationProcess.get(i)).accountedDate;
                arrayList.add((B3gService) AccountOperationProcess.get(i));
            }
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AccountDetailV2");
            this.uiManager.stateMachine.findLblAccountNumberValue(createContainer).setText(account.accountNumber);
            this.uiManager.stateMachine.findLblBalanceActValue(createContainer).setText(account.balance);
            if (DataTools.PurgeBlankFromStringAmount(account.balanceBrut).doubleValue() > 0.0d) {
                this.uiManager.stateMachine.findLblBalanceActValue(createContainer).setUIID("ad_lblValueGreen");
            } else {
                this.uiManager.stateMachine.findLblBalanceActValue(createContainer).setUIID("ad_lblValueRed");
            }
            if (account.AccountAutorisationList.size() > 0) {
                this.uiManager.stateMachine.findLblAutorisationAmountValue(createContainer).setText(((AccountAutorisation) account.AccountAutorisationList.get(0)).montant);
                if (DataTools.PurgeBlankFromStringAmount(((AccountAutorisation) account.AccountAutorisationList.get(0)).montant).doubleValue() > 0.0d) {
                    this.uiManager.stateMachine.findLblAutorisationAmountValue(createContainer).setUIID("ad_lblValueGreen");
                } else {
                    this.uiManager.stateMachine.findLblAutorisationAmountValue(createContainer).setUIID("ad_lblValueRed");
                }
                this.uiManager.stateMachine.findLblTermDateValue(createContainer).setText(((AccountAutorisation) account.AccountAutorisationList.get(0)).dateEchenance);
            } else {
                Component componentAt = ((Container) createContainer.getComponentAt(0)).getComponentAt(2);
                Component componentAt2 = ((Container) createContainer.getComponentAt(0)).getComponentAt(3);
                ((Container) createContainer.getComponentAt(0)).removeComponent(componentAt);
                ((Container) createContainer.getComponentAt(0)).removeComponent(componentAt2);
                createContainer.revalidate();
            }
            Container container2 = new Container(new BoxLayout(2));
            if (arrayList.size() > 0) {
                this.uiManager.Draw_GroupBy(container2, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 1, this.service);
                container2.setScrollableX(false);
            } else {
                container2.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune opération"));
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new B3gContainer(container2, "OPERATIONS"));
            this.uiManager.DrawTabsWithNavigation(this.uiManager.stateMachine.findCntAccountDetailsMain(createContainer), arrayList2);
            container.addComponent(createContainer);
            this.thisContainer = container;
        } catch (Exception unused) {
            container.addComponent(this.uiManager.GetGoBackHome());
        }
        Settings.setNightMode(this.thisContainer, 0);
        return container;
    }
}
