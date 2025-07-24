package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.AgregationHistorique;
import com.b3g.services.B3gService;
import com.b3g.services.MDMAccountOperation;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AgregationHistoriquePage extends B3GPage {
    EncodedImage encImage;
    int iconWidth;
    Image placeholder;

    public AgregationHistoriquePage(Object obj, Object obj2, int i) {
        int displayWidth = (Display.getInstance().getDisplayWidth() * 10) / 100;
        this.iconWidth = displayWidth;
        Image createImage = Image.createImage(displayWidth, displayWidth, 12569042);
        this.placeholder = createImage;
        this.encImage = EncodedImage.createFromImage(createImage, false);
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        Container container;
        CihMBanking.exitApplication = true;
        UIBuilder uIBuilder = new UIBuilder();
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("mn_cntMenuItem");
        try {
            AgregationHistorique agregationHistorique = (AgregationHistorique) this.service;
            ArrayList arrayList = new ArrayList();
            ArrayList AccountOperationProcess = agregationHistorique.AccountOperationProcess(agregationHistorique.getAccountId());
            for (int i = 0; i < AccountOperationProcess.size(); i++) {
                ((MDMAccountOperation) AccountOperationProcess.get(i)).groupKey = ((MDMAccountOperation) AccountOperationProcess.get(i)).date;
                arrayList.add((B3gService) AccountOperationProcess.get(i));
            }
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AccountDetailV2");
            Container createContainer2 = uIBuilder.createContainer("/cihv3", "MDMAcountDtails");
            Label label = (Label) uIBuilder.findByName("lblBankName", createContainer2);
            Label label2 = (Label) uIBuilder.findByName("lblAccountNumber", createContainer2);
            Label label3 = (Label) uIBuilder.findByName("lblBalanceeValue", createContainer2);
            Label label4 = (Label) uIBuilder.findByName("lblBalanceeMADValue", createContainer2);
            Label label5 = (Label) uIBuilder.findByName("lblAccountType", createContainer2);
            Label label6 = (Label) uIBuilder.findByName("lblStatus", createContainer2);
            Label label7 = (Label) uIBuilder.findByName("lblLogobank", createContainer2);
            Button button = (Button) uIBuilder.findByName("btnAccountDetail", createContainer2);
            Button button2 = (Button) uIBuilder.findByName("SupprimerBtn", createContainer2);
            try {
                Container container3 = (Container) uIBuilder.findByName("CntAccountInfo", createContainer);
                container3.removeAll();
                label4.setHidden(true);
                button.setHidden(true);
                button2.setHidden(true);
                label6.setHidden(true);
                label3.setText(agregationHistorique.getAmount());
                label7.setIcon(agregationHistorique.getIconBank());
                label.setText(agregationHistorique.getBankName());
                label2.setText(agregationHistorique.getAccountNumber());
                label5.setText(agregationHistorique.getAccountType());
                container3.add(createContainer2);
                Container container4 = new Container(new BoxLayout(2));
                if (arrayList.size() > 0) {
                    this.uiManager.Draw_GroupBy(container4, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 41, this.service);
                    container4.setScrollableX(false);
                } else {
                    container4.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune opération"));
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new B3gContainer(container4, "OPERATIONS"));
                this.uiManager.DrawTabsWithNavigation(this.uiManager.stateMachine.findCntAccountDetailsMain(createContainer), arrayList2);
                container = container2;
                try {
                    container.addComponent(createContainer);
                    this.thisContainer = container;
                } catch (Exception unused) {
                    container.addComponent(this.uiManager.GetGoBackHome());
                    this.thisContainer = container;
                    return container;
                }
            } catch (Exception unused2) {
                container = container2;
            }
        } catch (Exception unused3) {
            container = container2;
        }
        return container;
    }
}
