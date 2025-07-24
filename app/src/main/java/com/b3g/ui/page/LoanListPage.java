package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Loan;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class LoanListPage extends B3GPage {
    public LoanListPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(GetLoanContainer("IMMOA"), "Prêt Immobilier"));
            arrayList.add(new B3gContainer(GetLoanContainer("CONSO"), "Prêt Consommation"));
            this.uiManager.DrawTabsWithNavigation(container, arrayList);
            this.thisContainer.add(container);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public Container GetLoanContainer(String str) {
        ArrayList GetConsoLoans;
        String str2;
        String str3;
        ArrayList LoanProcess = new Loan().LoanProcess();
        if (str.equals("IMMOA")) {
            GetConsoLoans = Loan.GetImmoaLoans(LoanProcess);
            str2 = "Prêts Immobiliers";
            str3 = "Vous n'avez pas de prêt immobilier";
        } else {
            GetConsoLoans = Loan.GetConsoLoans(LoanProcess);
            str2 = "Prêts Consommations";
            str3 = "Vous n'avez pas de prêt à la consommation";
        }
        ArrayList arrayList = GetConsoLoans;
        Container DrawListContainer = this.uiManager.DrawListContainer("GloabalContainerV2", str2, Boolean.TRUE, arrayList, 1, this.PageId, arrayList.size() > 0 ? CihMBanking.sesPMTR.getSessionClient().getClient_LoanList_StatusLabel() : str3, null, null, null, null);
        DrawListContainer.setUIID("lsd_cntTableLayoutLoanImmoBY1");
        return DrawListContainer;
    }
}
