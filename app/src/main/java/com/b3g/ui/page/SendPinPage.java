package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.PinCard;
import com.b3g.services.ServiceManager;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SendPinPage extends B3GPage {
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();

    static /* synthetic */ String access$000(SendPinPage sendPinPage) {
        return sendPinPage.VnewaliasText;
    }

    static /* synthetic */ String access$002(SendPinPage sendPinPage, String str) {
        sendPinPage.VnewaliasText = str;
        return str;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        new ArrayList();
        ArrayList GetListCardProcess = GetListCardProcess();
        Container container = new Container(new BoxLayout(2));
        if (GetListCardProcess.size() > 0) {
            container.setScrollableY(false);
            container.getStyle().setPaddingUnit(1, 1, 1, 1);
            container.getStyle().setPadding(0, 2, 1, 1);
            container.setUIID("ad_CntAccountDetailsMain");
            Label label = new Label("Numéro de téléphone");
            label.setUIID("g_lblNotif");
            Label label2 = new Label(((PinCard) getCardList().get(0)).GSM2);
            label2.setUIID("g_lblDashBoardValueBlue");
            Label label3 = new Label("Opérateur");
            label3.setUIID("g_lblNotif");
            Container DrawListContainerCommands = this.uiManager.DrawListContainerCommands("GloabalContainerV2", "Cartes", Boolean.TRUE, GetListCardProcess, 1, 74, null, null, null, label2);
            ComboBox comboBox = new ComboBox();
            comboBox.setUIID("listBox");
            comboBox.addItem("MAROC TELECOM");
            comboBox.addItem("INWI");
            comboBox.addItem("MEDITEL");
            comboBox.addItem("Autre");
            Button button = new Button("Valider");
            button.setUIID("op_BtnOppositionValidation");
            button.addActionListener(new 1(DrawListContainerCommands, comboBox, container));
            container.addComponent(DrawListContainerCommands);
            Container container2 = new Container();
            container2.setUIID("EmptyContainer");
            container.addComponent(container2);
            container.addComponent(label);
            container.addComponent(label2);
            Container container3 = new Container();
            container3.setUIID("EmptyContainer");
            container.addComponent(container3);
            container.addComponent(label3);
            container.addComponent(comboBox);
            Container container4 = new Container();
            container4.setUIID("EmptyContainer");
            container.addComponent(container4);
            container.addComponent(button);
            this.thisContainer.add(container);
        } else {
            container.add(this.uiManager.GetCntMessage("Vous n'avez aucune catre"));
            container.setLayout(new BoxLayout(2));
            this.thisContainer.add(container);
        }
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$cardsContainer;
        final /* synthetic */ Container val$cntGlobal;
        final /* synthetic */ ComboBox val$operators;

        1(Container container, ComboBox comboBox, Container container2) {
            this.val$cardsContainer = container;
            this.val$operators = comboBox;
            this.val$cntGlobal = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PinCard selectedCard = SendPinPage.this.getSelectedCard(this.val$cardsContainer);
            selectedCard.Operator = this.val$operators.getSelectedItem().toString();
            SendPinPage sendPinPage = SendPinPage.this;
            Container container = sendPinPage.thisContainer;
            Container container2 = this.val$cntGlobal;
            SendPinPage sendPinPage2 = SendPinPage.this;
            sendPinPage.SwitchTransfertForms(container, container2, sendPinPage2.GetTransferSecurityForm(sendPinPage2.thisContainer, this.val$cntGlobal, selectedCard), false);
        }
    }

    public SendPinPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public ArrayList FillTransferArrayFromServiceResponse(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        if (serviceResponse.getParamsOut().size() > 1) {
            for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
                PinCard pinCard = new PinCard();
                pinCard.FillDataFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i)));
                arrayList.add(pinCard);
            }
        }
        return arrayList;
    }

    public ArrayList GetListCardProcess() {
        new ArrayList();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("SERVICE_ID_PARENT", Integer.toString(900129));
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900129);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return FillTransferArrayFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public ServiceResponse SendPinProcess(PinCard pinCard, String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CardNumber", pinCard.CardNumber);
        hashtable.put("GSM1", pinCard.GSM);
        hashtable.put("GSM2", pinCard.GSM2);
        hashtable.put("Operator", pinCard.Operator);
        hashtable.put("Pass", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(182);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    ArrayList getCardList() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 4; i++) {
            PinCard pinCard = new PinCard();
            pinCard.CardNumber = "123456789009876534";
            pinCard.Code = "45677656";
            pinCard.GSM = "0678354565";
            pinCard.GSM2 = "0678354565";
            pinCard.Status = "Active";
            arrayList.add(pinCard);
        }
        return arrayList;
    }

    public Container GetTransferSecurityForm(Container container, Container container2, PinCard pinCard) {
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container3 = (Container) this.uib.findByName("Container17", createContainer);
        container3.removeAll();
        container3.setLayout(BoxLayout.y());
        container3.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 2(container, createContainer, container2));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).setText("Envoyer");
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 3(b3GCIHEcode, pinCard));
        return createContainer;
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$Parent;
        final /* synthetic */ Container val$cntTransfertSecurityForm;
        final /* synthetic */ Container val$firstCnt;

        2(Container container, Container container2, Container container3) {
            this.val$Parent = container;
            this.val$cntTransfertSecurityForm = container2;
            this.val$firstCnt = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            SendPinPage.this.SwitchTransfertForms(this.val$Parent, this.val$cntTransfertSecurityForm, this.val$firstCnt, false);
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ PinCard val$card;
        final /* synthetic */ B3GCIHEcode val$eCode1;

        3(B3GCIHEcode b3GCIHEcode, PinCard pinCard) {
            this.val$eCode1 = b3GCIHEcode;
            this.val$card = pinCard;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            SendPinPage.access$002(SendPinPage.this, this.val$eCode1.getValue());
            String access$000 = SendPinPage.access$000(SendPinPage.this);
            if (SendPinPage.access$000(SendPinPage.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(SendPinPage.this.uiManager.stateMachine, "Veuillez saisir le mot de passe", null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(SendPinPage.this.uiManager.stateMachine, SendPinPage.this.SendPinProcess(this.val$card, access$000).getStatusLabel(), null);
            CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(56);
        }
    }

    public PinCard getSelectedCard(Container container) {
        return (PinCard) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getClientProperty("PinCard");
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }
}
