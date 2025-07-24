package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CardsListPage extends B3GPage {
    public CardsListPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BorderLayout());
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.setScrollableY(false);
        new Container();
        try {
            Card card = new Card();
            String str = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical;
            if (CihMBanking.sesPMTR.getSessionClient().getListRelationAccount() != null && CihMBanking.sesPMTR.getSessionClient().getListRelationAccount().size() > 0) {
                for (int i = 0; i < CihMBanking.sesPMTR.getSessionClient().getListRelationAccount().size(); i++) {
                    str = str + "+" + ((Account) CihMBanking.sesPMTR.getSessionClient().getListRelationAccount().get(i)).accountNumber.substring(0, 7);
                }
            }
            ArrayList CardProcess = card.CardProcess(str, false);
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < CardProcess.size(); i2++) {
                Card card2 = (Card) CardProcess.get(i2);
                if (card2.radicalClient.equals(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical)) {
                    arrayList.add(card2);
                }
            }
            if (!arrayList.isEmpty()) {
                new ArrayList();
                if (CihMBanking.sesPMTR.cardfromHom) {
                    arrayList = getCardToShowFirstTime(((Card) CihMBanking.sesPMTR.objctCrdCurrent).cardNumber, arrayList);
                }
                this.thisContainer.setLayout(new BorderLayout());
                CihMBanking.sesPMTR.pagecardLoad = true;
                Container DrawListContainer = this.uiManager.DrawListContainer("GloabalContainerV2", "Mes Cartes", Boolean.TRUE, arrayList, 1, 5, CihMBanking.sesPMTR.getSessionClient().getClient_CardList_StatusLabel(), null, null, this.thisContainer, null);
                Container container = new Container(new BoxLayout(1));
                Container container2 = new Container(new BoxLayout(1));
                Label label = new Label("Dotation Touristique :");
                label.setUIID("ac_lblitemDetailValue");
                Label label2 = new Label("522542 Dh");
                label2.setUIID("g_lblBalanceValueF");
                container.addComponent(label);
                container.addComponent(label2);
                Label label3 = new Label("Dotation E-commerce :");
                label3.setUIID("ac_lblitemDetailValue");
                Label label4 = new Label("6325635 dh");
                label4.setUIID("g_lblBalanceValueF");
                container2.addComponent(label3);
                container2.addComponent(label4);
                DrawListContainer.setScrollableY(false);
                this.thisContainer.addComponent("Center", DrawListContainer);
                Settings.setNightMode(this.thisContainer, 0);
                return this.thisContainer;
            }
            this.thisContainer.setLayout(new BoxLayout(2));
            SpanLabel spanLabel = new SpanLabel("Vous ne pouvez pas utiliser ce service car vous n'avez aucune carte.");
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            this.thisContainer.removeAll();
            this.thisContainer.addComponent(spanLabel);
            this.thisContainer.revalidate();
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer.setLayout(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
    }

    ArrayList getCardToShowFirstTime(String str, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Card card = (Card) it.next();
            if (card.cardNumber.equals(str)) {
                arrayList2.add(card);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Card card2 = (Card) it2.next();
            if (!card2.cardNumber.equals(str)) {
                arrayList2.add(card2);
            }
        }
        return arrayList2;
    }
}
