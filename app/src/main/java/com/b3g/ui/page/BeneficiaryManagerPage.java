package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.tools.SortBenifByAlias;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GCheckBox;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordion;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.menu.TreeMenuHardData;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.animations.Transition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class BeneficiaryManagerPage extends B3GPage {
    static int count;
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    private TextField benifPhoneNumber;
    Container cnt1;
    Container cnt2;
    public Button flottButton;
    private TextField newaliasText;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();
    private Boolean isNotif = false;
    ArrayList listAcc = new ArrayList();

    static /* synthetic */ Boolean access$000(BeneficiaryManagerPage beneficiaryManagerPage) {
        return beneficiaryManagerPage.isNotif;
    }

    static /* synthetic */ Boolean access$002(BeneficiaryManagerPage beneficiaryManagerPage, Boolean bool) {
        beneficiaryManagerPage.isNotif = bool;
        return bool;
    }

    static /* synthetic */ TextField access$100(BeneficiaryManagerPage beneficiaryManagerPage) {
        return beneficiaryManagerPage.newaliasText;
    }

    static /* synthetic */ TextField access$200(BeneficiaryManagerPage beneficiaryManagerPage) {
        return beneficiaryManagerPage.benifPhoneNumber;
    }

    static /* synthetic */ ServiceResponse access$300(BeneficiaryManagerPage beneficiaryManagerPage, Hashtable hashtable, String str) {
        return beneficiaryManagerPage.renameBenefProcess(hashtable, str);
    }

    static /* synthetic */ ServiceResponse access$400(BeneficiaryManagerPage beneficiaryManagerPage, Hashtable hashtable, String str) {
        return beneficiaryManagerPage.activateBenefProcess(hashtable, str);
    }

    static /* synthetic */ ServiceResponse access$500(BeneficiaryManagerPage beneficiaryManagerPage, Hashtable hashtable) {
        return beneficiaryManagerPage.deleteBenefProcess(hashtable);
    }

    static /* synthetic */ String access$600(BeneficiaryManagerPage beneficiaryManagerPage) {
        return beneficiaryManagerPage.VnewaliasText;
    }

    static /* synthetic */ String access$602(BeneficiaryManagerPage beneficiaryManagerPage, String str) {
        beneficiaryManagerPage.VnewaliasText = str;
        return str;
    }

    public BeneficiaryManagerPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = false;
    }

    public Container GetPageContainer() {
        count = 0;
        CihMBanking.exitApplication = false;
        CihMBanking.sesPMTR.actionAddBenfFromTrsfr = false;
        TableLayout tableLayout = new TableLayout(1, 1);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setHeightPercentage(100);
        createConstraint.setWidthPercentage(100);
        Container container = new Container(new BoxLayout(2));
        this.thisContainer = new Container(tableLayout);
        this.thisContainer.setUIID("mn_cntMenuItem");
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            this.thisContainer.setLayout(new BoxLayout(2));
            Container container2 = new Container(new FlowLayout(4, 4));
            SpanLabel spanLabel = new SpanLabel("Ce service n'est pas disponible en mode démonstration.");
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container2.add(spanLabel);
            this.thisContainer.addComponent(container2);
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
        try {
            CihMBanking.sesPMTR.setTitle("COMPTES");
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            arrayList.addAll(getBenifProcess());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Beneficiary beneficiary = (Beneficiary) it.next();
                if (beneficiary.typeBenef.equals("300013")) {
                    arrayList3.add(beneficiary);
                } else if (beneficiary.typeBenef.equals("300033")) {
                    arrayList5.add(beneficiary);
                } else {
                    arrayList4.add(beneficiary);
                }
            }
            Collections.sort(arrayList5, new SortBenifByAlias());
            Container transferBenefForm = getTransferBenefForm(arrayList3, "300013", this.thisContainer, this.flottButton, "Vous n'avez aucun Bénéficiaire");
            Container transferBenefForm2 = getTransferBenefForm(arrayList4, "300014", this.thisContainer, this.flottButton, "Vous n'avez aucun Bénéficiaire");
            Container transferBenefForm3 = getTransferBenefForm(arrayList5, "300033", this.thisContainer, this.flottButton, "Vous n'avez aucun Bénéficiaire");
            arrayList2.add(new B3gContainer(transferBenefForm, "COMPTES"));
            arrayList2.add(new B3gContainer(transferBenefForm2, "CARTES"));
            arrayList2.add(new B3gContainer(transferBenefForm3, "WE PAY"));
            this.uiManager.DrawTabsWithNavigation(container, arrayList2);
            this.thisContainer.addComponent(createConstraint, container);
            Settings.setNightMode(this.thisContainer, 0);
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
    }

    public Container getTransferBenefForm(ArrayList arrayList, String str, Container container, Button button, String str2) {
        Container container2 = new Container(new LayeredLayout());
        if (Settings.isNightMode()) {
            container2.setUIID("darkCnt");
        } else {
            container2.setUIID("whiteCnt");
        }
        Container container3 = new Container(new BorderLayout());
        this.cnt1 = container3;
        container3.setUIID("Container");
        Container container4 = new Container(new BorderLayout());
        this.cnt2 = container4;
        container4.setUIID("Container");
        str.hashCode();
        switch (str) {
            case "300013":
                button = new Button(this.uiManager.ressource.getImage("AddAccount.png"));
                break;
            case "300014":
                button = new Button(this.uiManager.ressource.getImage("AddCard (1).png"));
                break;
            case "300033":
                button = new Button(this.uiManager.ressource.getImage("AddWallet.png"));
                break;
        }
        button.addActionListener(new 1(str));
        button.setTextPosition(2);
        button.setUIID("margin_3_3_3_3");
        this.cnt1.addComponent("East", button);
        this.cnt2.addComponent("South", this.cnt1);
        Container container5 = new Container(new BoxLayout(2));
        if (Settings.isNightMode()) {
            container2.setUIID("darkCnt");
        } else {
            container2.setUIID("whiteCnt");
        }
        if (arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                container5.addComponent(drawBenfItem((Beneficiary) it.next(), this.listAcc, container2, container, arrayList));
                drawQRAccordionCnt(this.listAcc, container5);
            }
        } else {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlign(4);
            flowLayout.setValign(4);
            flowLayout.setFillRows(true);
            Container container6 = new Container();
            container6.setLayout(flowLayout);
            container6.setUIID("g_CntTranspMsg");
            SpanLabel spanLabel = new SpanLabel();
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setText(str2);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container6.addComponent(spanLabel);
            container5.addComponent(container6);
        }
        container2.addComponent(container5);
        container2.add(this.cnt2);
        container5.setScrollableY(true);
        container5.setScrollVisible(false);
        return container2;
    }

    class 1 implements ActionListener {
        final /* synthetic */ String val$serviceId;

        1(String str) {
            this.val$serviceId = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (this.val$serviceId.equals("300013")) {
                    BeneficiaryManagerPage.this.uiManager.NavigateToPageById(72);
                } else if (this.val$serviceId.equals("300014")) {
                    BeneficiaryManagerPage.this.uiManager.NavigateToPageById(73);
                } else if (this.val$serviceId.equals("300033")) {
                    BeneficiaryManagerPage.this.uiManager.NavigateToPageById(80);
                }
            }
        }
    }

    private void drawQRAccordionCnt(ArrayList arrayList, Container container) {
        Container container2 = new Container(new FlowLayout(4, 4));
        Container container3 = new Container();
        container3.setUIID("greyCnt");
        container.addComponent((Component) arrayList.get(count));
        container.add(container2);
        container.add(container3);
        if (Settings.isNightMode()) {
            container.setUIID("darkCnt2");
        }
        count++;
    }

    public Container drawBenfItem(Beneficiary beneficiary, ArrayList arrayList, Container container, Container container2, ArrayList arrayList2) {
        Image image;
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AccountBenefiaciaryItemManagV2");
        beneficiary.cntManagementForm = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "BeneficiaryManagementV3Cnt");
        Label label = new Label(beneficiary.balance + " MAD");
        label.setUIID("g_lblDashBoardbalance");
        Image image2 = this.uiManager.ressource.getImage("ArrowOpen (1).png");
        Image image3 = this.uiManager.ressource.getImage("ArrowClose (1).png");
        beneficiary.accr = new B3gAccordion();
        Button button = (Button) this.uib.findByName("btnUpdate", beneficiary.cntManagementForm);
        Button button2 = (Button) this.uib.findByName("btnDelete", beneficiary.cntManagementForm);
        Button button3 = (Button) this.uib.findByName("btnActDsct", beneficiary.cntManagementForm);
        arrayList.add(beneficiary.accr);
        Label label2 = new Label("  Notification SMS :");
        label2.setUIID("ac_lblitemDetailR");
        Label label3 = new Label("Non");
        label3.setUIID("ac_lblitemDetailValueFormDSC");
        if (beneficiary.typeBenef.equals("300033")) {
            this.uiManager.stateMachine.findLblAccountOwner(createContainer).setText(beneficiary.alias);
            this.uiManager.stateMachine.findLblAccountRibValue(createContainer).setText(phoneNumberFormatted(beneficiary.phoneNumber));
        } else {
            this.uiManager.stateMachine.findLblAccountOwner(createContainer).setText(beneficiary.alias);
            this.uiManager.stateMachine.findLblAccountRibValue(createContainer).setText(StringTools.RibFormatWithSP(beneficiary.cardNumber));
        }
        if (beneficiary.typeBenef.equals("300014")) {
            if (beneficiary.alias.equals("Carte Binatna")) {
                this.uiManager.stateMachine.findLblAccountOwner(createContainer).setText(beneficiary.alias);
                image = image2;
            } else {
                image = image2;
                this.uiManager.stateMachine.findLblAccountOwner(createContainer).setText(getBenefTitle(beneficiary.alias) + " Binatna");
            }
            createContainer.setName("300014");
            this.uiManager.stateMachine.findNotifCnt(createContainer).addComponent(label2);
            this.uiManager.stateMachine.findNotifCnt(createContainer).addComponent(label3);
            if (this.isNotif.equals("1")) {
                label3.setText("Oui");
                label3.setUIID("ac_lblitemDetailValueFormACT");
            }
        } else {
            image = image2;
        }
        if (beneficiary.typeBenef.equals("300018")) {
            if (beneficiary.alias.equals("Carte Eshopping")) {
                this.uiManager.stateMachine.findLblAccountOwner(createContainer).setText(beneficiary.alias);
            } else {
                this.uiManager.stateMachine.findLblAccountOwner(createContainer).setText(getBenefTitle(beneficiary.alias) + " E-shopping");
            }
            createContainer.setName("300018");
        }
        if (beneficiary.typeBenef.equals("300014") || beneficiary.typeBenef.equals("300018")) {
            if (beneficiary.balance.length() > 0) {
                this.uiManager.stateMachine.findCntV2inGlobalRight(createContainer).addComponent(label);
            }
            this.uiManager.stateMachine.findLblAccountRibValue(createContainer).setText(StringTools.HideCardNumber(beneficiary.cardNumber));
        }
        if (beneficiary.statusActivation.equals("0")) {
            this.uiManager.stateMachine.findLblCardStatusValueV2(createContainer).setUIID("ac_lblitemDetailValueFormACT");
        } else {
            this.uiManager.stateMachine.findLblCardStatusValueV2(createContainer).setText("Désactivé");
            this.uiManager.stateMachine.findLblCardStatusValueV2(createContainer).setUIID("ac_lblitemDetailValueFormDSC");
        }
        if (beneficiary.statusActivation.equals("0")) {
            button3.setText("Désactiver");
            button3.setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "desactiver (1).png"));
        } else {
            button3.setText("Activer");
            button3.setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "activer (1).png"));
        }
        if (!beneficiary.isSystem.equals("0")) {
            button2.setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "Opposer (1).png"));
        }
        button.addActionListener(new 2(beneficiary));
        button3.addActionListener(new 3(beneficiary, container, container2));
        button2.addActionListener(new 4(beneficiary));
        beneficiary.accr.setCloseIcon(image3);
        Image image4 = image;
        beneficiary.accr.setOpenIcon(image4);
        beneficiary.accr.setLayoutChangGlb(true);
        beneficiary.accr.setScrollableY(false);
        beneficiary.accr.setScrollVisible(false);
        createContainer.setScrollableY(false);
        beneficiary.cntManagementForm.setScrollableY(false);
        beneficiary.accr.addContent(new Container(new FlowLayout(4, 4)), beneficiary.cntManagementForm);
        createContainer.putClientProperty("beneficiary", this);
        beneficiary.accr.addOnClickItemListener(new 5(arrayList2, beneficiary, image3, image4));
        if (Settings.isNightMode()) {
            createContainer.setUIID("darkCnt");
        } else {
            createContainer.setUIID("whiteCnt");
        }
        return createContainer;
    }

    class 2 implements ActionListener {
        final /* synthetic */ Beneficiary val$benf;

        2(Beneficiary beneficiary) {
            this.val$benf = beneficiary;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                BeneficiaryManagerPage beneficiaryManagerPage = BeneficiaryManagerPage.this;
                StateMachine stateMachine = CihMBanking.sesPMTR.getCurrentUiManager().stateMachine;
                Beneficiary beneficiary = this.val$benf;
                beneficiaryManagerPage.ShowDialog(67, stateMachine, null, 11, beneficiary, "U", beneficiary.typeBenef, "");
            }
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Beneficiary val$benf;
        final /* synthetic */ Container val$pCnt2;
        final /* synthetic */ Container val$pComponent;

        3(Beneficiary beneficiary, Container container, Container container2) {
            this.val$benf = beneficiary;
            this.val$pComponent = container;
            this.val$pCnt2 = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (this.val$benf.statusActivation.equals("0")) {
                    BeneficiaryManagerPage beneficiaryManagerPage = BeneficiaryManagerPage.this;
                    StateMachine stateMachine = CihMBanking.sesPMTR.getCurrentUiManager().stateMachine;
                    Beneficiary beneficiary = this.val$benf;
                    beneficiaryManagerPage.ShowDialog(68, stateMachine, null, 11, beneficiary, "A", beneficiary.typeBenef, "");
                    return;
                }
                CihMBanking.sesPMTR.setSessionSavedContainer(this.val$pComponent);
                BeneficiaryManagerPage.this.uiManager.btnBack.getListeners().clear();
                BeneficiaryManagerPage.this.uiManager.btnBack.addActionListener(new 1());
                BeneficiaryManagerPage beneficiaryManagerPage2 = BeneficiaryManagerPage.this;
                Container container = this.val$pCnt2;
                Container container2 = (Container) container.getComponentAt(0);
                BeneficiaryManagerPage beneficiaryManagerPage3 = BeneficiaryManagerPage.this;
                Container container3 = this.val$pCnt2;
                Beneficiary beneficiary2 = this.val$benf;
                beneficiaryManagerPage2.SwitchTransfertForms(container, container2, beneficiaryManagerPage3.GetTransferSecurityForm(container3, beneficiary2, beneficiary2.typeBenef), false);
            }
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                BeneficiaryManagerPage.this.uiManager.NavigateToPageById(51);
                BeneficiaryManagerPage.this.uiManager.btnBack.getListeners().clear();
                BeneficiaryManagerPage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    BeneficiaryManagerPage.this.uiManager.GoBack();
                }
            }
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Beneficiary val$benf;

        4(Beneficiary beneficiary) {
            this.val$benf = beneficiary;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (this.val$benf.isSystem.equals("0")) {
                    BeneficiaryManagerPage beneficiaryManagerPage = BeneficiaryManagerPage.this;
                    StateMachine stateMachine = CihMBanking.sesPMTR.getCurrentUiManager().stateMachine;
                    Beneficiary beneficiary = this.val$benf;
                    beneficiaryManagerPage.ShowDialog(69, stateMachine, null, 11, beneficiary, "D", beneficiary.typeBenef, "");
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Ce compte (ou carte) vous appartient, vous ne pouvez que le désactiver "), null);
            }
        }
    }

    class 5 implements ActionListener {
        final /* synthetic */ ArrayList val$beneficiaryB3gServiceList;
        final /* synthetic */ Beneficiary val$benf;
        final /* synthetic */ Image val$closeIcon;
        final /* synthetic */ Image val$openIcon;

        5(ArrayList arrayList, Beneficiary beneficiary, Image image, Image image2) {
            this.val$beneficiaryB3gServiceList = arrayList;
            this.val$benf = beneficiary;
            this.val$closeIcon = image;
            this.val$openIcon = image2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Iterator it = this.val$beneficiaryB3gServiceList.iterator();
            while (it.hasNext()) {
                Beneficiary beneficiary = (Beneficiary) it.next();
                if (!beneficiary.alias.equals(this.val$benf.alias)) {
                    B3gAccordion.AccordionContent accordionContent = (B3gAccordion.AccordionContent) beneficiary.accr.getComponentAt(0);
                    this.val$benf.accr.setAutoClose(true);
                    accordionContent.setArrowIcon(this.val$closeIcon);
                    beneficiary.cntManagementForm.setHidden(true);
                    beneficiary.accr.setOpenIcon(this.val$openIcon);
                } else if (!this.val$benf.isOpen) {
                    this.val$benf.isOpen = true;
                    this.val$benf.accr.setAutoClose(false);
                    this.val$benf.cntManagementForm.setHidden(false);
                } else {
                    this.val$benf.isOpen = false;
                    this.val$benf.accr.setAutoClose(true);
                    beneficiary.cntManagementForm.setHidden(true);
                }
            }
        }
    }

    private String getBenefTitle(String str) {
        return str.trim().length() > 0 ? str + "--" : "Carte";
    }

    public Container getTopUpBenefForm(ArrayList arrayList) {
        Container container = new Container(new BoxLayout(2));
        container.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Mes bénéficiaires", Boolean.TRUE, arrayList, 2, 17, "", null, null, null, null));
        Settings.setNightMode(container, 0);
        return container;
    }

    public ArrayList getBenifProcess() {
        new ArrayList();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900131);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return FillDcsDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public ArrayList FillDcsDataFromServiceResponse(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
            arrayList.add(new Beneficiary().FillBeneficiaryFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i))));
        }
        return arrayList;
    }

    private ServiceResponse deleteBenefProcess(Hashtable hashtable) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900130);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private ServiceResponse renameBenefProcess(Hashtable hashtable, String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("SERVICEID", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900130);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private ServiceResponse activateBenefProcess(Hashtable hashtable, String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("PASSID", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900130);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public final Hashtable FillHashMapFromParams(Beneficiary beneficiary, String str, String str2, boolean z, String str3, String str4) {
        Hashtable hashtable = new Hashtable();
        if (str.equals("U")) {
            hashtable.put("BENEF_ACCOUNT", beneficiary.cardNumber);
            hashtable.put("NEW_ALIAS", str2);
            hashtable.put("ACTION", str);
            if (z) {
                hashtable.put("ISPUSHNOTIFICATION", "1");
            } else {
                hashtable.put("ISPUSHNOTIFICATION", "0");
            }
            hashtable.put("BENEFICIARY_MOBILE_PHONE", str3);
            if (beneficiary.phoneNumber == str3) {
                hashtable.put("WITH_PHONE_NUMBER", "false");
            } else {
                hashtable.put("WITH_PHONE_NUMBER", "true");
            }
        }
        if (str.equals("D")) {
            hashtable.put("BENEF_ACCOUNT", beneficiary.cardNumber);
            hashtable.put("ACTION", str);
            hashtable.put("SERVICEID", str4);
        }
        if (str.equals("A")) {
            hashtable.put("BENEF_ACCOUNT", beneficiary.cardNumber);
            hashtable.put("STATUS", beneficiary.statusActivation);
            hashtable.put("ACTION", str);
            hashtable.put("SERVICEID", "9000130");
        }
        return hashtable;
    }

    public void ShowDialog(int i, StateMachine stateMachine, B3gService b3gService, int i2, Beneficiary beneficiary, String str, String str2, String str3) {
        Object obj;
        Dialog dialog;
        Object obj2;
        String str4;
        String str5;
        Container container;
        Dialog dialog2 = new Dialog();
        dialog2.setLayout(new BorderLayout());
        this.newaliasText = new TextField();
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            if (str.equals("A") || str.equals("D")) {
                obj = "D";
                Container container2 = createContainer;
                dialog = dialog2;
                obj2 = "A";
                str4 = "Notifier le bénéficiaire";
                str5 = "EmptyContainer";
                ArrayList GetPopupItemDetail = stateMachine.uiManager.GetPopupItemDetail(i, this.service, stateMachine, open, container2, beneficiary, null);
                int i3 = 0;
                while (i3 < GetPopupItemDetail.size()) {
                    Container container3 = container2;
                    stateMachine.findCntPopupBody(container3).addComponent((Component) GetPopupItemDetail.get(i3));
                    i3++;
                    container2 = container3;
                }
                container = container2;
            } else {
                obj = "D";
                dialog = dialog2;
                str4 = "Notifier le bénéficiaire";
                obj2 = "A";
                str5 = "EmptyContainer";
                container = createContainer;
            }
            if (str.equals("U")) {
                Container container4 = new Container(new BoxLayout(2));
                Container container5 = new Container();
                Container container6 = new Container(new BoxLayout(2));
                Component container7 = new Container();
                container7.setUIID(str5);
                new Container().setUIID(str5);
                Component container8 = new Container();
                container8.setUIID(str5);
                Label label = new Label("Merci d'entrer le nouvel alias :");
                label.setUIID("SimLabelOrange");
                Label label2 = new Label("Merci d'entrer le numéro du bénéficiaire  :");
                label2.setUIID("SimLabelOrange");
                TextField textField = new TextField();
                this.benifPhoneNumber = textField;
                textField.setMaxSize(16);
                this.benifPhoneNumber.setUIID("textFieldPopUpStyle");
                this.benifPhoneNumber.setConstraint(2);
                this.newaliasText.setUIID("textFieldPopUpStyle");
                this.newaliasText.setMaxSize(24);
                this.newaliasText.setText(beneficiary.alias);
                B3GCheckBox b3GCheckBox = new B3GCheckBox("Notification");
                b3GCheckBox.setRadioUIID("g_CntTranspMsg");
                String str6 = str4;
                b3GCheckBox.addItem(str6);
                b3GCheckBox.getItem(str6).addActionListener(new 6(container4, container5, container6));
                container4.addComponent(label);
                container4.addComponent(this.newaliasText);
                container4.addComponent(container7);
                if (beneficiary.typeBenef.equals("300014")) {
                    container4.addComponent(b3GCheckBox.GetContainer());
                    container4.addComponent(container8);
                    container6.addComponent(label2);
                    container6.addComponent(this.benifPhoneNumber);
                    if (beneficiary.isNotif.equals("1")) {
                        b3GCheckBox.getItem(str6).setSelected(true);
                        this.benifPhoneNumber.setText(beneficiary.phoneNumber);
                        container4.addComponent(container6);
                        this.isNotif = true;
                        stateMachine.findCntPopupBody(container).addComponent(container4);
                        Label label3 = new Label(" ");
                        label3.setUIID("dg_lblPopUpDemo");
                        label3.setVerticalAlignment(4);
                        stateMachine.findCntPopupBody(container).addComponent(label3);
                    } else {
                        b3GCheckBox.getItem(str6).setSelected(false);
                        container4.addComponent(container5);
                        this.benifPhoneNumber.setText(beneficiary.phoneNumber);
                        this.isNotif = false;
                        stateMachine.findCntPopupBody(container).addComponent(container4);
                        Label label32 = new Label(" ");
                        label32.setUIID("dg_lblPopUpDemo");
                        label32.setVerticalAlignment(4);
                        stateMachine.findCntPopupBody(container).addComponent(label32);
                    }
                } else {
                    stateMachine.findCntPopupBody(container).addComponent(container4);
                    Label label322 = new Label(" ");
                    label322.setUIID("dg_lblPopUpDemo");
                    label322.setVerticalAlignment(4);
                    stateMachine.findCntPopupBody(container).addComponent(label322);
                }
            }
            Container container9 = container;
            stateMachine.findCntPopupBody(container).addComponent(GetPopupBtn(dialog, i, b3gService, i2, stateMachine, beneficiary, str, str2, str3));
            if (str.equals("U")) {
                stateMachine.findLblpopupItemTitleV2(container9).setText("Modifier le bénéficiaire :");
            }
            if (str.equals(obj2)) {
                if (beneficiary.statusActivation.equals("0")) {
                    stateMachine.findLblpopupItemTitleV2(container9).setText("Désactiver le bénéficiaire :");
                } else {
                    stateMachine.findLblpopupItemTitleV2(container9).setText("Activer le bénéficiaire :");
                }
            }
            if (str.equals(obj)) {
                stateMachine.findLblpopupItemTitleV2(container9).setText("Supprimer le bénéficiaire :");
            }
            if (beneficiary.typeBenef.equals("300033")) {
                stateMachine.findLblpopupItemValueV2(container9).setText(beneficiary.phoneNumber);
            } else {
                stateMachine.findLblpopupItemValueV2(container9).setText(beneficiary.cardNumber);
            }
            container9.setPreferredH(CihMBanking.sesPMTR.displayHeight);
            container9.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
            Dialog dialog3 = dialog;
            dialog3.setAlwaysTensile(false);
            dialog3.setDraggable(false);
            dialog3.setScrollable(false);
            dialog3.setTactileTouch(false);
            dialog3.setTensileDragEnabled(false);
            dialog3.getDialogStyle().setBgTransparency(255);
            dialog3.getDialogStyle().setBgColor(1118481);
            dialog3.getStyle().setPadding(0, 0, 0, 0);
            dialog3.getStyle().setMargin(0, 0, 0, 0);
            dialog3.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog3.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog3.revalidate();
            dialog3.addComponent("Center", container9);
            dialog3.show(0, 0, 0, 0);
        } catch (IOException unused) {
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ Container val$RenameCnt;
        final /* synthetic */ Container val$notifContainer;
        final /* synthetic */ Container val$notificationContainerEmpty;

        6(Container container, Container container2, Container container3) {
            this.val$RenameCnt = container;
            this.val$notificationContainerEmpty = container2;
            this.val$notifContainer = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!BeneficiaryManagerPage.access$000(BeneficiaryManagerPage.this).booleanValue()) {
                this.val$RenameCnt.replace(this.val$notificationContainerEmpty, this.val$notifContainer, (Transition) null);
                this.val$RenameCnt.revalidate();
                BeneficiaryManagerPage.access$002(BeneficiaryManagerPage.this, true);
            } else {
                this.val$RenameCnt.replace(this.val$notifContainer, this.val$notificationContainerEmpty, (Transition) null);
                this.val$RenameCnt.revalidate();
                BeneficiaryManagerPage.access$002(BeneficiaryManagerPage.this, false);
            }
        }
    }

    public Container GetPopupBtn(Dialog dialog, int i, B3gService b3gService, int i2, StateMachine stateMachine, Beneficiary beneficiary, String str, String str2, String str3) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("Annuler");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(stateMachine.uiManager.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 7(dialog));
        container.addComponent(button);
        Button button2 = new Button("Valider");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(stateMachine.uiManager.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        this.newaliasText.addDataChangedListener(new BeneficiaryManagerPage$$ExternalSyntheticLambda0(this));
        button2.addActionListener(new 8(str, beneficiary, dialog, str2, str3));
        container.addComponent(button2);
        return container;
    }

    class 7 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        7(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    /* synthetic */ void lambda$GetPopupBtn$0$com-b3g-ui-page-BeneficiaryManagerPage(int i, int i2) {
        if (DataTools.ignoreCaracSpc(this.newaliasText.getText())) {
            TextField textField = this.newaliasText;
            textField.putClientProperty("LastValidnewaliasText", textField.getText());
        } else {
            this.newaliasText.stopEditing();
            TextField textField2 = this.newaliasText;
            textField2.setText((String) textField2.getClientProperty("LastValidnewaliasText"));
            this.newaliasText.startEditingAsync();
        }
        this.newaliasText.getParent().revalidate();
    }

    class 8 implements ActionListener {
        final /* synthetic */ String val$HBPass;
        final /* synthetic */ String val$action;
        final /* synthetic */ Beneficiary val$benifCnt;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ String val$serviceID;

        8(String str, Beneficiary beneficiary, Dialog dialog, String str2, String str3) {
            this.val$action = str;
            this.val$benifCnt = beneficiary;
            this.val$d = dialog;
            this.val$serviceID = str2;
            this.val$HBPass = str3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$action.equals("U")) {
                if (BeneficiaryManagerPage.access$100(BeneficiaryManagerPage.this).getText().length() != 0) {
                    if (!BeneficiaryManagerPage.access$100(BeneficiaryManagerPage.this).getText().equals(this.val$benifCnt.alias) || ((this.val$benifCnt.phoneNumber != BeneficiaryManagerPage.access$200(BeneficiaryManagerPage.this).getText() && BeneficiaryManagerPage.access$000(BeneficiaryManagerPage.this).booleanValue() && BeneficiaryManagerPage.access$200(BeneficiaryManagerPage.this).getText().length() >= 10) || ((BeneficiaryManagerPage.access$100(BeneficiaryManagerPage.this).getText().equals(this.val$benifCnt.alias) && !BeneficiaryManagerPage.access$000(BeneficiaryManagerPage.this).booleanValue() && this.val$benifCnt.isNotif.equals("1")) || (BeneficiaryManagerPage.access$100(BeneficiaryManagerPage.this).getText().equals(this.val$benifCnt.alias) && BeneficiaryManagerPage.access$000(BeneficiaryManagerPage.this).booleanValue() && this.val$benifCnt.isNotif.equals("0") && BeneficiaryManagerPage.access$200(BeneficiaryManagerPage.this).getText().length() >= 10)))) {
                        this.val$d.dispose();
                        BeneficiaryManagerPage beneficiaryManagerPage = BeneficiaryManagerPage.this;
                        ServiceResponse access$300 = BeneficiaryManagerPage.access$300(beneficiaryManagerPage, beneficiaryManagerPage.FillHashMapFromParams(this.val$benifCnt, "U", DataTools.escapeHtml(BeneficiaryManagerPage.access$100(beneficiaryManagerPage).getText()), BeneficiaryManagerPage.access$000(BeneficiaryManagerPage.this).booleanValue(), BeneficiaryManagerPage.access$200(BeneficiaryManagerPage.this).getText(), ""), this.val$serviceID);
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(access$300.getStatusLabel()), null);
                        if (access$300.getStatusCode().equals("000")) {
                            BeneficiaryManagerPage.this.UpdateBeneficiary(this.val$benifCnt.cardNumber, BeneficiaryManagerPage.access$000(BeneficiaryManagerPage.this), BeneficiaryManagerPage.access$200(BeneficiaryManagerPage.this).getText());
                            BeneficiaryManagerPage.count = 0;
                            BeneficiaryManagerPage.this.AccountProcess();
                            BeneficiaryManagerPage.this.uiManager.NavigateToPageById(51);
                            return;
                        }
                        return;
                    }
                    if (BeneficiaryManagerPage.access$100(BeneficiaryManagerPage.this).getText().length() > 24) {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Cet alias est trop long ,veuillez entrer un nouveau alias pour ce bénéficiaire "), null);
                        return;
                    }
                    if (BeneficiaryManagerPage.access$100(BeneficiaryManagerPage.this).getText().equals(this.val$benifCnt.alias) && BeneficiaryManagerPage.access$000(BeneficiaryManagerPage.this).booleanValue() && this.val$benifCnt.phoneNumber.equals(BeneficiaryManagerPage.access$200(BeneficiaryManagerPage.this).getText())) {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Ces informations du bénéficiaire sont déjà enregistrées "), null);
                        return;
                    } else if (BeneficiaryManagerPage.access$200(BeneficiaryManagerPage.this).getText().length() < 10 && BeneficiaryManagerPage.access$000(BeneficiaryManagerPage.this).booleanValue()) {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez entrer un numéro valide de ce bénéficiaire "), null);
                        return;
                    } else {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Ces informations du bénéficiaire sont déjà enregistrées "), null);
                        return;
                    }
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez entrer un nouveau alias pour ce bénéficiaire "), null);
                return;
            }
            if (this.val$action.equals("A")) {
                this.val$d.dispose();
                BeneficiaryManagerPage beneficiaryManagerPage2 = BeneficiaryManagerPage.this;
                ServiceResponse access$400 = BeneficiaryManagerPage.access$400(beneficiaryManagerPage2, beneficiaryManagerPage2.FillHashMapFromParams(this.val$benifCnt, "A", "", false, "", ""), this.val$HBPass);
                if (access$400.getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(access$400.getStatusLabel()), null);
                    BeneficiaryManagerPage.count = 0;
                    BeneficiaryManagerPage.this.AccountProcess();
                    BeneficiaryManagerPage.this.uiManager.NavigateToPageById(51);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(access$400.getStatusLabel()), null);
                return;
            }
            this.val$d.dispose();
            BeneficiaryManagerPage beneficiaryManagerPage3 = BeneficiaryManagerPage.this;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(BeneficiaryManagerPage.access$500(beneficiaryManagerPage3, beneficiaryManagerPage3.FillHashMapFromParams(this.val$benifCnt, "D", "", false, "", this.val$serviceID)).getStatusLabel()), null);
            BeneficiaryManagerPage.count = 0;
            BeneficiaryManagerPage.this.AccountProcess();
            BeneficiaryManagerPage.this.uiManager.NavigateToPageById(51);
        }
    }

    Beneficiary getBenifFromTab(Container container) {
        return (Beneficiary) ((B3gService) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getClientProperty("beneficiary"));
    }

    public void UpdateBeneficiary(String str, Boolean bool, String str2) {
        ArrayList client_AccountList = CihMBanking.sesPMTR.getSessionClient().getClient_AccountList();
        for (int i = 0; i < client_AccountList.size(); i++) {
            Iterator it = ((Account) client_AccountList.get(i)).accountBeneficiaries.iterator();
            while (it.hasNext()) {
                Beneficiary beneficiary = (Beneficiary) it.next();
                if (beneficiary.cardNumber.equals(str)) {
                    beneficiary.isNotif = bool.booleanValue() ? "1" : "0";
                    beneficiary.phoneNumber = str2;
                }
            }
        }
    }

    private String phoneNumberFormatted(String str) {
        return str.substring(0, 2).equals("06") ? "212" + str.substring(1) : str;
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
            container.replace(container2, new PinNotActivatedPage(this.uiManager, null, this.PageId).GetPageContainer(), CommonTransitions.createSlide(0, z, 300));
        } else {
            container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        }
        container.revalidate();
    }

    public Container GetTransferSecurityForm(Container container, Beneficiary beneficiary, String str) {
        Container container2 = new Container(BoxLayout.y());
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container3 = (Container) this.uib.findByName("Container17", createContainer);
        container3.removeAll();
        container3.setLayout(BoxLayout.y());
        container3.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 9(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 10(b3GCIHEcode, beneficiary, str));
        container2.add(createContainer);
        return container2;
    }

    class 9 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        9(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            this.val$Parent.revalidate();
        }
    }

    class 10 implements ActionListener {
        final /* synthetic */ Beneficiary val$benf;
        final /* synthetic */ B3GCIHEcode val$eCode1;
        final /* synthetic */ String val$serviceId;

        10(B3GCIHEcode b3GCIHEcode, Beneficiary beneficiary, String str) {
            this.val$eCode1 = b3GCIHEcode;
            this.val$benf = beneficiary;
            this.val$serviceId = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            BeneficiaryManagerPage.access$602(BeneficiaryManagerPage.this, this.val$eCode1.getValue());
            if (BeneficiaryManagerPage.access$600(BeneficiaryManagerPage.this).length() == 0) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(BeneficiaryManagerPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
            } else {
                BeneficiaryManagerPage.this.ShowDialog(68, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 11, this.val$benf, "A", this.val$serviceId, BeneficiaryManagerPage.access$600(BeneficiaryManagerPage.this));
            }
        }
    }

    public void AccountProcess() {
        new ArrayList();
        Account account = new Account();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setServiceId(11);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setParamsIn(hashtable);
        ArrayList FillAccountArrayDataFromServiceResponse = account.FillAccountArrayDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
        CihMBanking.sesPMTR.getSessionClient().setClient_AccountListVirm(FillAccountArrayDataFromServiceResponse);
        if (FillAccountArrayDataFromServiceResponse.size() == 1) {
            CihMBanking.sesPMTR.setCurrentAccount((Account) FillAccountArrayDataFromServiceResponse.get(0));
        }
    }
}
