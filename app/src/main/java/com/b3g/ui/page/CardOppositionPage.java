package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.services.CardOpperationStatus;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CardOppositionPage extends B3GPage {
    public CardOppositionPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            new Container(new BoxLayout(2)).setUIID("ad_CntAccountDetailsMain");
            this.thisContainer.addComponent(GetCardOppositionContainer());
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    public Container GetCardOppositionContainer() {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "CardOpposition");
        Card card = new Card();
        card.uiManager = this.uiManager;
        ArrayList PurgeOppositeCardList = PurgeOppositeCardList(card.CardProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, false));
        Container DrawListContainerCardOpperation = this.uiManager.DrawListContainerCardOpperation("GloabalContainerV2", "Mes Cartes", Boolean.TRUE, PurgeOppositeCardList, 1, 25, CihMBanking.sesPMTR.getSessionClient().getClient_CardList_StatusLabel(), null, this.uiManager.stateMachine.findRdActivateCard(createContainer), this.uiManager.stateMachine.findRdOpposite(createContainer), this.uiManager.stateMachine.findBtnCardActivationTitleV2(createContainer));
        this.uiManager.stateMachine.findCntCardList(createContainer).addComponent(DrawListContainerCardOpperation);
        if (PurgeOppositeCardList.size() > 0) {
            if (((Card) PurgeOppositeCardList.get(0)).statusCard.equals("Y")) {
                this.uiManager.stateMachine.findRdActivateCard(createContainer).setSelected(true);
                this.uiManager.stateMachine.findBtnCardActivationTitleV2(createContainer).setText("Blocage");
            } else if (((Card) PurgeOppositeCardList.get(0)).statusCard.equals("O")) {
                this.uiManager.stateMachine.findRdOpposite(createContainer).setSelected(true);
            } else {
                this.uiManager.stateMachine.findRdActivateCard(createContainer).setSelected(true);
                this.uiManager.stateMachine.findBtnCardActivationTitleV2(createContainer).setText("Déblocage");
            }
        }
        this.uiManager.stateMachine.findBtnOppositionValidation(createContainer).addActionListener(new 1(createContainer, DrawListContainerCardOpperation));
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            this.uiManager.stateMachine.findBtnOppositionValidation(createContainer).setEnabled(false);
        }
        createContainer.setScrollableY(true);
        return createContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$CardContainerList;
        final /* synthetic */ Container val$cntCardOppositionForm;

        1(Container container, Container container2) {
            this.val$cntCardOppositionForm = container;
            this.val$CardContainerList = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            int i;
            CardOppositionPage cardOppositionPage = CardOppositionPage.this;
            CardOpperationStatus GetCardOperationResume = cardOppositionPage.GetCardOperationResume(cardOppositionPage.uiManager.stateMachine.findRdActivateCard(this.val$cntCardOppositionForm), CardOppositionPage.this.uiManager.stateMachine.findRdOpposite(this.val$cntCardOppositionForm), CardOppositionPage.this.uiManager.stateMachine.findRdMotifValue1(this.val$cntCardOppositionForm), CardOppositionPage.this.uiManager.stateMachine.findRdMotifValue2(this.val$cntCardOppositionForm), CardOppositionPage.this.uiManager.stateMachine.findRdMotifValue3(this.val$cntCardOppositionForm), this.val$CardContainerList, CardOppositionPage.this.uiManager.stateMachine.findBtnCardActivationTitleV2(this.val$cntCardOppositionForm));
            if (CardOppositionPage.this.uiManager.stateMachine.findRdActivateCard(this.val$cntCardOppositionForm).isSelected() && GetCardOperationResume.newCardStatus.equals("Y")) {
                i = 300037;
            } else {
                i = CardOppositionPage.this.uiManager.stateMachine.findRdOpposite(this.val$cntCardOppositionForm).isSelected() ? 300039 : 300038;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationService(25, GetCardOperationResume, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, i);
        }
    }

    public CardOpperationStatus GetCardOperationResume(Component component, Component component2, Component component3, Component component4, Component component5, Container container, Component component6) {
        CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
        String text = ((Label) component6).getText();
        if (((RadioButton) component).isSelected() && text.equals("Blocage")) {
            cardOpperationStatus.newCardStatus = "D";
        } else if (((RadioButton) component2).isSelected()) {
            cardOpperationStatus.newCardStatus = "O";
        } else {
            cardOpperationStatus.newCardStatus = "Y";
        }
        if (((RadioButton) component3).isSelected()) {
            cardOpperationStatus.motif = "Perte";
        } else if (((RadioButton) component4).isSelected()) {
            cardOpperationStatus.motif = "Vol";
        } else {
            cardOpperationStatus.motif = "Autre";
        }
        Card card = (Card) ((B3gService) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getClientProperty("CardOpposition"));
        cardOpperationStatus.plainCardNumber = card.plainCardNumber;
        cardOpperationStatus.cardNumber = card.cardNumber;
        cardOpperationStatus.cardOpperationStatus = card.statusCard;
        return cardOpperationStatus;
    }

    public ArrayList PurgeOppositeCardList(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (!((Card) arrayList.get(i)).statusCard.equals("O")) {
                    arrayList2.add((Card) arrayList.get(i));
                }
            }
        }
        return arrayList2;
    }
}
