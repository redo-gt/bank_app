package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.services.CardOperation;
import com.b3g.services.CardPlafond;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CardsDetailPage extends B3GPage {
    public CardsDetailPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        Container container = new Container(new BoxLayout(2));
        container.setUIID("mn_cntMenuItem");
        try {
            Card card = (Card) this.service;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = card.CardOperationList;
            for (int i = 0; i < arrayList2.size(); i++) {
                ((CardOperation) arrayList2.get(i)).groupKey = ((CardOperation) arrayList2.get(i)).transactionDate;
                arrayList.add((B3gService) arrayList2.get(i));
            }
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "CardDetailV2");
            this.uiManager.stateMachine.findLblCardNumberValue(createContainer).setText(card.cardNumber);
            this.uiManager.stateMachine.findLblCardEntitiledValue(createContainer).setText(card.entitled);
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new B3gContainer(GetOperationContainer(), "OPERATIONS"));
            if (card.cardType.equals("N")) {
                arrayList3.add(new B3gContainer(GetAvailableBalanceContainer(), "DISPONIBLE"));
                ((Container) createContainer.getComponentAt(0)).removeComponent(((Container) createContainer.getComponentAt(0)).getComponentAt(2));
                createContainer.revalidate();
            } else {
                this.uiManager.stateMachine.findLblCardBalanceValue(createContainer).setText(card.BalanceCard);
                this.uiManager.stateMachine.findLblCardBalanceValue(createContainer).setUIID("ad_lblValueGreen");
            }
            arrayList3.add(new B3gContainer(GetCeillingContainer(), "PLAFONDS"));
            this.uiManager.DrawTabsWithNavigation(this.uiManager.stateMachine.findCntCardDetailsMain(createContainer), arrayList3);
            container.addComponent(createContainer);
        } catch (Exception unused) {
            container.addComponent(this.uiManager.GetGoBackHome());
        }
        this.thisContainer = container;
        return container;
    }

    public Container GetOperationContainer() {
        Card card = (Card) this.service;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = card.CardOperationList;
        for (int i = 0; i < arrayList2.size(); i++) {
            ((CardOperation) arrayList2.get(i)).groupKey = ((CardOperation) arrayList2.get(i)).transactionDate;
            arrayList.add((B3gService) arrayList2.get(i));
        }
        Container container = new Container(new BoxLayout(2));
        if (arrayList.size() > 0) {
            this.uiManager.Draw_GroupBy(container, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 4, card);
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune opération"));
        }
        return container;
    }

    public Container GetCeillingContainer() {
        Card card = (Card) this.service;
        if (CihMBanking.sesPMTR.isPlafondsChanged) {
            CihMBanking.sesPMTR.listAncPlafonds = new CardNationalPlafond(this.uiManager, card, 0, null).CardPlafondProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, "false");
            for (int i = 0; i < CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.size(); i++) {
                CardPlafond cardPlafond = (CardPlafond) CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.get(i);
                if (cardPlafond.PlafondType.equals("RetraitNat")) {
                    card.ceilingDailyRemoving = cardPlafond.Plafond.toString();
                } else if (cardPlafond.PlafondType.equals("PaiementNat")) {
                    card.ceilingDailyPayment = cardPlafond.Plafond.toString();
                } else if (cardPlafond.PlafondType.equals("EComNat")) {
                    card.ceilingDailyECom = cardPlafond.Plafond.toString();
                }
            }
        }
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "CardCeillingItem");
        createContainer.getStyle().setMarginUnit(0, 0, 0, 0);
        createContainer.getStyle().setMargin(3, 0, 0, 0);
        createContainer.revalidate();
        this.uiManager.stateMachine.findBtnRetriveCeillingValueV2(createContainer).setText(card.ceilingDailyRemoving);
        this.uiManager.stateMachine.findBtnRetriveCeillingCPValueV2(createContainer).setText(card.ceilingDailyPayment);
        this.uiManager.stateMachine.findBtnRetriveCeillingECValueV2(createContainer).setText(card.ceilingDailyECom);
        return createContainer;
    }

    public Container GetAvailableBalanceContainer() {
        Card card = (Card) this.service;
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AvailableBalanceItem");
        createContainer.getStyle().setMarginUnit(0, 0, 0, 0);
        createContainer.getStyle().setMargin(3, 0, 0, 0);
        createContainer.revalidate();
        this.uiManager.stateMachine.findBtnNationalECOMBalanceAvailableValueV2(createContainer).setText(card.nationalEComBalanceAvailable);
        this.uiManager.stateMachine.findBtnNationalPaimentBalanceAvailableValueV2(createContainer).setText(card.nationalPaymentBalanceAvailable);
        this.uiManager.stateMachine.findBtnNationalRetriveBalanceAvailableValueV2(createContainer).setText(card.nationalRetriveBalanceAvailable);
        this.uiManager.stateMachine.findBtnInternationalECOMBalanceAvailableValueV2(createContainer).setText(card.internationalEComBalanceAvailable);
        this.uiManager.stateMachine.findBtnInternationalPaimentBalanceAvailableValueV2(createContainer).setText(card.internationalPaymentBalanceAvailable);
        this.uiManager.stateMachine.findBtnInternationalRetriveBalanceAvailableValueV2(createContainer).setText(card.internationalRetriveBalanceAvailable);
        return createContainer;
    }
}
