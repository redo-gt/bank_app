package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.services.CardOpperationStatus;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CardADOppositionPage extends B3GPage {
    private ArrayList CardList;
    private ArrayList CardMultiServiceList;
    ArrayList msCards;

    static /* synthetic */ void access$000(CardADOppositionPage cardADOppositionPage, String str, String str2, String str3) {
        cardADOppositionPage.changeStatusDotation(str, str2, str3);
    }

    public CardADOppositionPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.msCards = new ArrayList();
        CihMBanking.exitApplication = true;
        Card card = (Card) CihMBanking.sesPMTR.objctCrdCurrent;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        String str = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical;
        Card card2 = new Card();
        if (CihMBanking.sesPMTR.getSessionClient().getListRelationAccount() != null && CihMBanking.sesPMTR.getSessionClient().getListRelationAccount().size() > 0) {
            for (int i = 0; i < CihMBanking.sesPMTR.getSessionClient().getListRelationAccount().size(); i++) {
                str = str + "+" + ((Account) CihMBanking.sesPMTR.getSessionClient().getListRelationAccount().get(i)).accountNumber.substring(0, 7);
            }
        }
        this.CardList = PurgeOppositeCardList(card2.CardProcess(str, false));
        for (int i2 = 0; i2 < this.CardList.size(); i2++) {
            if (card.plainCardNumber.equals(((Card) this.CardList.get(i2)).plainCardNumber)) {
                this.msCards.add((Card) this.CardList.get(i2));
            }
        }
        this.CardMultiServiceList = getMultiServiceCard(this.msCards);
        try {
            if (!this.msCards.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                card2.uiManager = this.uiManager;
                arrayList.add(new B3gContainer(GetCardOppositionContainer(), "GERER LA CARTE"));
                if (!this.CardMultiServiceList.isEmpty()) {
                    arrayList.clear();
                    if (CihMBanking.sesPMTR.IsMsDotationClick) {
                        arrayList.add(new B3gContainer(getCardADDotationCnt(), "GERER LA DOTATION"));
                    } else if (CihMBanking.sesPMTR.IsOperationCarteClick) {
                        arrayList.add(new B3gContainer(GetCardOppositionContainer(), "GERER LA CARTE"));
                    }
                }
                this.uiManager.DrawTabsWithNavigation(this.thisContainer, arrayList);
                new Container(new BoxLayout(2)).setUIID("ad_CntAccountDetailsMain");
            } else {
                SpanLabel spanLabel = new SpanLabel("Vous ne pouvez pas utiliser ce service car vous n'avez aucune carte.");
                spanLabel.setUIID("dg_splblMsgCenter");
                spanLabel.setScrollVisible(false);
                spanLabel.setIconPosition("West");
                spanLabel.setTextUIID("dg_lblSPError");
                spanLabel.setIconUIID("g_cntEmpty");
                this.thisContainer.removeAll();
                this.thisContainer.addComponent(spanLabel);
                this.thisContainer.revalidate();
            }
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    public Container GetCardOppositionContainer() {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "CardADOpposition");
        Container DrawListContainerCardOpperation = this.uiManager.DrawListContainerCardOpperation("GloabalContainerV2", "Mes Cartes", Boolean.TRUE, this.msCards, 1, 25, CihMBanking.sesPMTR.getSessionClient().getClient_CardList_StatusLabel(), null, this.uiManager.stateMachine.findRdActivateCard(createContainer), this.uiManager.stateMachine.findRdOpposite(createContainer), this.uiManager.stateMachine.findBtnCardActivationTitleV2(createContainer));
        this.uiManager.stateMachine.findCntCardList(createContainer).addComponent(DrawListContainerCardOpperation);
        if (this.msCards.size() > 0) {
            if (((Card) this.msCards.get(0)).statusCard.equals("Y")) {
                this.uiManager.stateMachine.findBtnCardActivationTitleV2(createContainer).setText("Blocage");
            } else if (!((Card) this.msCards.get(0)).statusCard.equals("O")) {
                this.uiManager.stateMachine.findBtnCardActivationTitleV2(createContainer).setText("Déblocage");
            }
        }
        this.uiManager.stateMachine.findBtnOppCard(createContainer).addActionListener(new 1(DrawListContainerCardOpperation, createContainer));
        this.uiManager.stateMachine.findBtnADCard(createContainer).addActionListener(new 2(DrawListContainerCardOpperation, createContainer));
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            this.uiManager.stateMachine.findBtnADCard(createContainer).setEnabled(false);
            this.uiManager.stateMachine.findBtnOppCard(createContainer).setEnabled(false);
        }
        createContainer.setScrollableY(true);
        return createContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$CardContainerList;
        final /* synthetic */ Container val$cntCardOppositionForm;

        1(Container container, Container container2) {
            this.val$CardContainerList = container;
            this.val$cntCardOppositionForm = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardADOppositionPage cardADOppositionPage = CardADOppositionPage.this;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationADOService(25, cardADOppositionPage.GetCardOperationADOResume("OP", this.val$CardContainerList, cardADOppositionPage.uiManager.stateMachine.findBtnCardActivationTitleV2(this.val$cntCardOppositionForm)), CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 300039);
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$CardContainerList;
        final /* synthetic */ Container val$cntCardOppositionForm;

        2(Container container, Container container2) {
            this.val$CardContainerList = container;
            this.val$cntCardOppositionForm = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardADOppositionPage cardADOppositionPage = CardADOppositionPage.this;
            CardOpperationStatus GetCardOperationADOResume = cardADOppositionPage.GetCardOperationADOResume("AD", this.val$CardContainerList, cardADOppositionPage.uiManager.stateMachine.findBtnCardActivationTitleV2(this.val$cntCardOppositionForm));
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationADOService(25, GetCardOperationADOResume, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, GetCardOperationADOResume.newCardStatus.equals("D") ? 300038 : 300037);
        }
    }

    public Container getCardADDotationCnt() {
        if (!this.CardMultiServiceList.isEmpty()) {
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "CardADOpposition");
            this.uiManager.stateMachine.findBtnCardActivationTitleV2(createContainer).setText("Dotation Touristique");
            this.uiManager.stateMachine.findBtnCardOppositionTitleV2(createContainer).setText("Dotation E-commerce");
            Container DrawListContainerCardMultiSrvOpperation = this.uiManager.DrawListContainerCardMultiSrvOpperation("GloabalContainerV2", "Mes Cartes multiservices", Boolean.TRUE, this.CardMultiServiceList, 1, 25, CihMBanking.sesPMTR.getSessionClient().getClient_CardList_StatusLabel(), null, this.uiManager.stateMachine.findBtnOppCard(createContainer), this.uiManager.stateMachine.findBtnADCard(createContainer));
            this.uiManager.stateMachine.findCntCardList(createContainer).addComponent(DrawListContainerCardMultiSrvOpperation);
            if (this.CardMultiServiceList.size() > 0) {
                if (((Card) this.CardMultiServiceList.get(0)).dotationTouristSattus.equals("A")) {
                    this.uiManager.stateMachine.findBtnADCard(createContainer).setText("Bloquer");
                } else {
                    this.uiManager.stateMachine.findBtnADCard(createContainer).setText("Débloquer");
                }
                if (((Card) this.CardMultiServiceList.get(0)).dotationEcomStatus.equals("A")) {
                    this.uiManager.stateMachine.findBtnOppCard(createContainer).setText("Bloquer");
                } else {
                    this.uiManager.stateMachine.findBtnOppCard(createContainer).setText("Débloquer");
                }
            }
            this.uiManager.stateMachine.findBtnADCard(createContainer).addActionListener(new 3(DrawListContainerCardMultiSrvOpperation, createContainer));
            this.uiManager.stateMachine.findBtnOppCard(createContainer).addActionListener(new 4(DrawListContainerCardMultiSrvOpperation, createContainer));
            return createContainer;
        }
        Container GetCntMessage = this.uiManager.GetCntMessage("Vous n'avez aucune catre multiservice");
        GetCntMessage.setLayout(new BoxLayout(2));
        return GetCntMessage;
    }

    class 3 implements ActionListener {
        final /* synthetic */ Container val$CardContainerList;
        final /* synthetic */ Container val$cntCardDotForm;

        3(Container container, Container container2) {
            this.val$CardContainerList = container;
            this.val$cntCardDotForm = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardADOppositionPage cardADOppositionPage = CardADOppositionPage.this;
            CardOpperationStatus GetCardOperationADOResume = cardADOppositionPage.GetCardOperationADOResume("AD", this.val$CardContainerList, cardADOppositionPage.uiManager.stateMachine.findBtnCardActivationTitleV2(this.val$cntCardDotForm));
            if (CardADOppositionPage.this.uiManager.stateMachine.findBtnADCard(this.val$cntCardDotForm).getText().equals("Bloquer") || CardADOppositionPage.this.uiManager.stateMachine.findBtnADCard(this.val$cntCardDotForm).getText().equals("تجميد") || CardADOppositionPage.this.uiManager.stateMachine.findBtnADCard(this.val$cntCardDotForm).getText().equals("Turn off") || CardADOppositionPage.this.uiManager.stateMachine.findBtnADCard(this.val$cntCardDotForm).getText().equals("Bloquer") || CardADOppositionPage.this.uiManager.stateMachine.findBtnADCard(this.val$cntCardDotForm).getText().equals("Apaga")) {
                GetCardOperationADOResume.action = "N";
                GetCardOperationADOResume.serviceDotId = "01";
                GetCardOperationADOResume.newDotStatus = "Bloquée";
                GetCardOperationADOResume.oldDotStatus = "Débloquée";
                CardADOppositionPage cardADOppositionPage2 = CardADOppositionPage.this;
                cardADOppositionPage2.ShowDialog(61, cardADOppositionPage2.uiManager.stateMachine, GetCardOperationADOResume, CardADOppositionPage.this.PageId, CardADOppositionPage.this.uiManager.stateMachine.findBtnADCard(this.val$cntCardDotForm));
                this.val$cntCardDotForm.revalidate();
                return;
            }
            GetCardOperationADOResume.action = "A";
            GetCardOperationADOResume.serviceDotId = "01";
            GetCardOperationADOResume.newDotStatus = "Débloquée";
            GetCardOperationADOResume.oldDotStatus = "Bloquée";
            CardADOppositionPage cardADOppositionPage3 = CardADOppositionPage.this;
            cardADOppositionPage3.ShowDialog(61, cardADOppositionPage3.uiManager.stateMachine, GetCardOperationADOResume, CardADOppositionPage.this.PageId, CardADOppositionPage.this.uiManager.stateMachine.findBtnADCard(this.val$cntCardDotForm));
            this.val$cntCardDotForm.revalidate();
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Container val$CardContainerList;
        final /* synthetic */ Container val$cntCardDotForm;

        4(Container container, Container container2) {
            this.val$CardContainerList = container;
            this.val$cntCardDotForm = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardADOppositionPage cardADOppositionPage = CardADOppositionPage.this;
            CardOpperationStatus GetCardOperationADOResume = cardADOppositionPage.GetCardOperationADOResume("AD", this.val$CardContainerList, cardADOppositionPage.uiManager.stateMachine.findBtnCardActivationTitleV2(this.val$cntCardDotForm));
            if (CardADOppositionPage.this.uiManager.stateMachine.findBtnOppCard(this.val$cntCardDotForm).getText().equals("Bloquer") || CardADOppositionPage.this.uiManager.stateMachine.findBtnOppCard(this.val$cntCardDotForm).getText().equals("تجميد") || CardADOppositionPage.this.uiManager.stateMachine.findBtnOppCard(this.val$cntCardDotForm).getText().equals("Turn off") || CardADOppositionPage.this.uiManager.stateMachine.findBtnOppCard(this.val$cntCardDotForm).getText().equals("Bloquer") || CardADOppositionPage.this.uiManager.stateMachine.findBtnOppCard(this.val$cntCardDotForm).getText().equals("Apaga")) {
                GetCardOperationADOResume.action = "N";
                GetCardOperationADOResume.serviceDotId = "02";
                GetCardOperationADOResume.newDotStatus = "Bloquée";
                GetCardOperationADOResume.oldDotStatus = "Débloquée";
                CardADOppositionPage cardADOppositionPage2 = CardADOppositionPage.this;
                cardADOppositionPage2.ShowDialog(61, cardADOppositionPage2.uiManager.stateMachine, GetCardOperationADOResume, CardADOppositionPage.this.PageId, CardADOppositionPage.this.uiManager.stateMachine.findBtnOppCard(this.val$cntCardDotForm));
                this.val$cntCardDotForm.revalidate();
                return;
            }
            GetCardOperationADOResume.action = "A";
            GetCardOperationADOResume.serviceDotId = "02";
            GetCardOperationADOResume.newDotStatus = "Débloquée";
            GetCardOperationADOResume.oldDotStatus = "Bloquée";
            CardADOppositionPage cardADOppositionPage3 = CardADOppositionPage.this;
            cardADOppositionPage3.ShowDialog(61, cardADOppositionPage3.uiManager.stateMachine, GetCardOperationADOResume, CardADOppositionPage.this.PageId, CardADOppositionPage.this.uiManager.stateMachine.findBtnOppCard(this.val$cntCardDotForm));
            this.val$cntCardDotForm.revalidate();
        }
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

    public CardOpperationStatus GetCardOperationResume(Component component, Component component2, Component component3, Component component4, Component component5, Container container, Component component6) {
        CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
        String text = ((Label) component6).getText();
        if (((RadioButton) component).isSelected() && (text.equals("Blocage") || text.equals("تجميد") || text.equals("Turn off") || text.equals("Apagar"))) {
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

    public CardOpperationStatus GetCardOperationADOResume(String str, Container container, Component component) {
        CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
        String text = ((Label) component).getText();
        if (str.equals("AD")) {
            if (text.equals("Blocage") || text.equals("تجميد") || text.equals("Turn off") || text.equals("Apagar")) {
                cardOpperationStatus.newCardStatus = "D";
            } else {
                cardOpperationStatus.newCardStatus = "Y";
            }
        } else {
            cardOpperationStatus.newCardStatus = "O";
        }
        cardOpperationStatus.motif = " ";
        Card card = (Card) ((B3gService) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getClientProperty("CardOpposition"));
        cardOpperationStatus.plainCardNumber = card.plainCardNumber;
        cardOpperationStatus.cardNumber = card.cardNumber;
        cardOpperationStatus.cardOpperationStatus = card.statusCard;
        cardOpperationStatus.pClientCardHolder = card.radicalClient;
        return cardOpperationStatus;
    }

    public ServiceResponse dotationOpProcess(String str, String str2, String str3) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CARD_NUMBER", str);
        hashtable.put("SERVICEDOT_ID", str2);
        hashtable.put("ACTION", str3);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900075);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    ArrayList getMultiServiceCard(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Card card = (Card) it.next();
            if (card.cardCategory.equals("MS")) {
                arrayList2.add(card);
            }
        }
        return arrayList2;
    }

    public void ShowDialog(int i, StateMachine stateMachine, B3gService b3gService, int i2, Button button) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        CardOpperationStatus cardOpperationStatus = (CardOpperationStatus) b3gService;
        String str = cardOpperationStatus.action.equals("A") ? "Déblocage" : "Blocage";
        String str2 = cardOpperationStatus.serviceDotId.equals("01") ? "Dotation Touristique" : "Dotation E-commerce";
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = stateMachine.uiManager.GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, null);
            for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupBtn(dialog, i, b3gService, i2, stateMachine, button));
            stateMachine.findLblpopupItemTitleV2(createContainer).setText(str);
            stateMachine.findLblpopupItemValueV2(createContainer).setText(str2);
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(false);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(255);
            dialog.getDialogStyle().setBgColor(1118481);
            dialog.getStyle().setPadding(0, 0, 0, 0);
            dialog.getStyle().setMargin(0, 0, 0, 0);
            dialog.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog.revalidate();
            dialog.addComponent("Center", createContainer);
            dialog.show(0, 0, 0, 0);
        } catch (IOException unused) {
        }
    }

    public Container GetPopupBtn(Dialog dialog, int i, B3gService b3gService, int i2, StateMachine stateMachine, Button button) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button2 = new Button("ANNULER");
        button2.setTextPosition(3);
        button2.setUIID("dg_BtnCancelIcon");
        button2.setIcon(stateMachine.uiManager.ressource.getImage("ico_close_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 5(dialog));
        container.addComponent(button2);
        Button button3 = new Button("VALIDER");
        button3.setUIID("dg_BtnYestIconTR");
        button3.setTextPosition(3);
        button3.setIcon(stateMachine.uiManager.ressource.getImage("ico_check_popup_NW.png"));
        button3.setVerticalAlignment(4);
        button3.addActionListener(new 6(b3gService, dialog, button));
        container.addComponent(button3);
        return container;
    }

    class 5 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        5(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 6 implements ActionListener {
        CardOpperationStatus datas;
        final /* synthetic */ Button val$actionButton;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ B3gService val$data;

        6(B3gService b3gService, Dialog dialog, Button button) {
            this.val$data = b3gService;
            this.val$d = dialog;
            this.val$actionButton = button;
            this.datas = (CardOpperationStatus) b3gService;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            CihMBanking.sesPMTR.setTabPosition(0);
            ServiceResponse dotationOpProcess = CardADOppositionPage.this.dotationOpProcess(this.datas.plainCardNumber, this.datas.serviceDotId, this.datas.action);
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(dotationOpProcess.getStatusLabel()), null);
            if (dotationOpProcess.getStatusCode().equals("000")) {
                if (this.datas.action.equals("A")) {
                    this.val$actionButton.setText("Bloquer");
                } else {
                    this.val$actionButton.setText("Débloquer");
                }
                CardADOppositionPage.access$000(CardADOppositionPage.this, this.datas.plainCardNumber, this.datas.serviceDotId, this.datas.action);
            }
        }
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }

    private void changeStatusDotation(String str, String str2, String str3) {
        Iterator it = this.CardMultiServiceList.iterator();
        while (it.hasNext()) {
            Card card = (Card) it.next();
            if (card.plainCardNumber.equals(str)) {
                if (str2.equals("01")) {
                    card.dotationTouristSattus = str3;
                    return;
                } else {
                    card.dotationEcomStatus = str3;
                    return;
                }
            }
        }
    }

    private String getAccountNameFromCardNumber(Card card, ArrayList arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            Account account = (Account) arrayList.get(i);
            if (account.accountNumber.substring(0, 7).equals(card.radicalClient)) {
                return account.type;
            }
        }
        return "";
    }
}
