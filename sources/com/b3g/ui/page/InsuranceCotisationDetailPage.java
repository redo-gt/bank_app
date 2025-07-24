package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.services.B3gService;
import com.b3g.services.InssuranceCotisation;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class InsuranceCotisationDetailPage extends B3GPage {
    public InsuranceCotisationDetailPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            InssuranceCotisation inssuranceCotisation = new InssuranceCotisation();
            SessionParameter sessionParameter = CihMBanking.sesPMTR;
            ArrayList InssuranceCotisationProcess = inssuranceCotisation.InssuranceCotisationProcess(SessionParameter.currentInssuranceContract);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < InssuranceCotisationProcess.size() - 1; i++) {
                arrayList.add((InssuranceCotisation) InssuranceCotisationProcess.get(i));
            }
            Collections.reverse(arrayList);
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            Container container2 = new Container(new FlowLayout(4, 4));
            Container container3 = new Container(BoxLayout.x());
            container3.setUIID("margin_5_5_0_0");
            Label label = new Label("Montant épargne valorisé : ");
            label.setUIID("ac_lblitemDetail");
            Label label2 = new Label(((InssuranceCotisation) InssuranceCotisationProcess.get(InssuranceCotisationProcess.size() - 1)).Montant + " DHS");
            label2.setUIID("g_lblBalanceValueFLFT");
            container3.add(label);
            container3.add(label2);
            container2.add(container3);
            container.add(container2);
            ArrayList arrayList2 = new ArrayList();
            B3GUiManager b3GUiManager = this.uiManager;
            SessionParameter sessionParameter2 = CihMBanking.sesPMTR;
            arrayList2.add(new B3gContainer(b3GUiManager.DrawListContainer("GloabalContainerV2", SessionParameter.currentInssuranceName, Boolean.TRUE, arrayList, arrayList.size(), 101, "", inssuranceCotisation, null, null, null), "Historique des cotisations"));
            this.uiManager.DrawTabsWithNavigation(container, arrayList2);
            this.thisContainer.addComponent(container);
            Container container4 = new Container();
            container4.setUIID("g_CntTranspMsg");
            this.thisContainer.addComponent(container4);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }
}
