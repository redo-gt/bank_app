package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.animations.Transition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AddBenfWallet extends B3GPage {
    private String VnewaliasText;
    TextField txtBenfName;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();

    static /* synthetic */ Container access$000(AddBenfWallet addBenfWallet, Container container, Beneficiary beneficiary, String str) {
        return addBenfWallet.GetTransferSecurityForm(container, beneficiary, str);
    }

    static /* synthetic */ String access$100(AddBenfWallet addBenfWallet) {
        return addBenfWallet.VnewaliasText;
    }

    static /* synthetic */ String access$102(AddBenfWallet addBenfWallet, String str) {
        addBenfWallet.VnewaliasText = str;
        return str;
    }

    static /* synthetic */ ServiceResponse access$200(AddBenfWallet addBenfWallet, Hashtable hashtable, String str) {
        return addBenfWallet.addBenefProcess(hashtable, str);
    }

    public AddBenfWallet(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = false;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setScrollableY(false);
        this.thisContainer.setUIID("mn_cntMenuItem");
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AddNewBenfCompteCont");
        Container container = (Container) this.uib.findByName("CntGlobal", createContainer);
        Container container2 = (Container) this.uib.findByName("CntMotifRadio21", createContainer);
        Button button = (Button) this.uib.findByName("BtnAddBenfValidation", createContainer);
        new Container().setUIID("EmptyContainer");
        container2.setHidden(true);
        try {
            RadioButton radioButton = (RadioButton) this.uib.findByName("rdMotifValue1", createContainer);
            Label label = (Label) this.uib.findByName("lblMotifValue1", createContainer);
            radioButton.setEnabled(true);
            radioButton.setHidden(true);
            label.setText("WE PAY");
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(radioButton);
            buttonGroup.getRadioButton(0).addActionListener(new 1(container));
            button.addActionListener(new 2(buttonGroup, container, createContainer));
            container.add(GetCihContainer());
            this.thisContainer.addComponent(createContainer);
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$cntGlobal;

        1(Container container) {
            this.val$cntGlobal = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$cntGlobal;
            container.replace((Container) container.getComponentAt(0), AddBenfWallet.this.GetCihContainer(), (Transition) null);
            this.val$cntGlobal.revalidate();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$cntGlobal;
        final /* synthetic */ Container val$cont;
        final /* synthetic */ ButtonGroup val$group;

        2(ButtonGroup buttonGroup, Container container, Container container2) {
            this.val$group = buttonGroup;
            this.val$cntGlobal = container;
            this.val$cont = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            boolean z;
            try {
                if (this.val$group.getRadioButton(0).isSelected()) {
                    String trim = ((TextField) AddBenfWallet.this.uib.findByName("txtCardNumber", this.val$cntGlobal.getComponentAt(0))).getText().trim();
                    String escapeHtml = DataTools.escapeHtml(AddBenfWallet.this.txtBenfName.getText().trim());
                    if (escapeHtml.trim().length() != 0 && trim.trim().length() != 0) {
                        boolean z2 = true;
                        if (escapeHtml.trim().length() > 30) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Le nom du bénéficiaire ne doit pas dépasser 35 caractères"), null);
                            z = true;
                        } else {
                            z = false;
                        }
                        if (escapeHtml.trim().length() < 3) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Le nom du bénéficiaire doit être composé au minimum de 3 caractères"), null);
                            z = true;
                        }
                        if (trim.trim().length() < 10) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Numéro invalide"), null);
                        } else {
                            z2 = z;
                        }
                        if (z2) {
                            return;
                        }
                        AddBenfWallet addBenfWallet = AddBenfWallet.this;
                        ServiceResponse VerfProcess = addBenfWallet.VerfProcess(addBenfWallet.FillHashMapFromParams(escapeHtml, trim, "", "", "WALLET", ""));
                        if (VerfProcess.getStatusCode().equals("000")) {
                            Beneficiary beneficiary = new Beneficiary();
                            beneficiary.phoneNumber = trim;
                            beneficiary.alias = escapeHtml;
                            beneficiary.serviceId = "300013";
                            beneficiary.typeBenef = "300033";
                            AddBenfWallet addBenfWallet2 = AddBenfWallet.this;
                            Container container = addBenfWallet2.thisContainer;
                            Container container2 = this.val$cont;
                            addBenfWallet2.SwitchTransfertForms(container, container2, AddBenfWallet.access$000(AddBenfWallet.this, container2, beneficiary, "WALLET"), false);
                            return;
                        }
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, VerfProcess.getStatusLabel(), null);
                        return;
                    }
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez compléter le formulaire"), null);
                }
            } catch (Exception e) {
                System.err.println(" ERROR ===> " + e.getMessage());
            }
        }
    }

    public Container GetCihContainer() {
        Container createContainer = this.uib.createContainer(this.uiManager.ressource, "CntAddBenfTypeAutreCnt");
        this.txtBenfName = (TextField) this.uib.findByName("txtBenefName", createContainer);
        ((Label) this.uib.findByName("lblCardNumber", createContainer)).setText("Numéro du bénéficiaire (*)");
        this.txtBenfName.addDataChangedListener(new AddBenfWallet$$ExternalSyntheticLambda0(this, createContainer));
        return createContainer;
    }

    /* synthetic */ void lambda$GetCihContainer$0$com-b3g-ui-page-AddBenfWallet(Container container, int i, int i2) {
        if (DataTools.isValidInput(this.txtBenfName.getText())) {
            TextField textField = this.txtBenfName;
            textField.putClientProperty("LastValidAddBenWallet", textField.getText());
        } else {
            this.txtBenfName.stopEditing();
            TextField textField2 = this.txtBenfName;
            textField2.setText((String) textField2.getClientProperty("LastValidAddBenWallet"));
            this.txtBenfName.startEditingAsync();
        }
        container.revalidate();
    }

    private Container GetTransferSecurityForm(Container container, Beneficiary beneficiary, String str) {
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container2 = (Container) this.uib.findByName("Container17", createContainer);
        container2.removeAll();
        container2.setLayout(BoxLayout.y());
        container2.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 3());
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 4(b3GCIHEcode, beneficiary, str));
        Settings.setNightMode(createContainer, 0);
        return createContainer;
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AddBenfWallet.this.uiManager.GoBack();
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ String val$CheckType;
        final /* synthetic */ Beneficiary val$benf;
        final /* synthetic */ B3GCIHEcode val$eCode1;

        4(B3GCIHEcode b3GCIHEcode, Beneficiary beneficiary, String str) {
            this.val$eCode1 = b3GCIHEcode;
            this.val$benf = beneficiary;
            this.val$CheckType = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AddBenfWallet.access$102(AddBenfWallet.this, this.val$eCode1.getValue());
            try {
                if (AddBenfWallet.access$100(AddBenfWallet.this).length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AddBenfWallet.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                } else {
                    AddBenfWallet.this.ShowDialogTransfertService(this.val$benf, this.val$CheckType, AddBenfWallet.access$100(AddBenfWallet.this), CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 111);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }

    public ServiceResponse VerfProcess(Hashtable hashtable) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(110);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private ServiceResponse addBenefProcess(Hashtable hashtable, String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("PASSID", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(111);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public final Hashtable FillHashMapFromParams(String str, String str2, String str3, String str4, String str5, String str6) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        if (!str5.equals("WALLET")) {
            hashtable.put("ACCOUNT_NUMBER", str2);
        } else {
            hashtable.put("PHONE_NUMBER", str2);
        }
        hashtable.put("FIRST_NAME", str3);
        hashtable.put("LAST_NAME", str4);
        hashtable.put("ALIAS", str);
        hashtable.put("CHECK_TYPE", str5);
        hashtable.put("ADD_TYPE", str6);
        return hashtable;
    }

    public void ShowDialogTransfertService(Beneficiary beneficiary, String str, String str2, StateMachine stateMachine, B3gService b3gService, int i) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(beneficiary, str, stateMachine, open, createContainer, null);
            for (int i2 = 0; i2 < GetPopupItemDetail.size(); i2++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i2));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupTransferBtn(dialog, beneficiary, str, str2, i));
            stateMachine.findLblpopupItemTitleV2(createContainer).setText("Ajouter Bénéficiaire");
            stateMachine.findLblpopupItemValueV2(createContainer).setText("");
            createContainer.setPreferredH(CihMBanking.sesPMTR.displayHeight);
            createContainer.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
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
            Settings.setNightMode(createContainer, 0);
            dialog.addComponent("Center", createContainer);
            dialog.show(0, 0, 0, 0);
        } catch (IOException unused) {
        }
        Settings.setNightMode(this.thisContainer, 0);
    }

    public ArrayList GetPopupItemDetail(Beneficiary beneficiary, String str, StateMachine stateMachine, Resources resources, Container container, B3gService b3gService) {
        ArrayList arrayList = new ArrayList();
        new DataTools();
        if (str.equals("COMPTE_CIH")) {
            Container createContainer = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer).setText("N°Compte : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer).setText(beneficiary.cardNumber);
            arrayList.add(createContainer);
            Container createContainer2 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer2).setText("Nom : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer2).setText(beneficiary.alias);
            arrayList.add(createContainer2);
        } else if (str.equals("AUTRE_COMPTE")) {
            Container createContainer3 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer3).setText("Le RIB : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer3).setText(beneficiary.cardNumber);
            arrayList.add(createContainer3);
            Container createContainer4 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer4).setText("Nom : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer4).setText(beneficiary.lastName);
            arrayList.add(createContainer4);
            if (beneficiary.firstName != null) {
                Container createContainer5 = stateMachine.createContainer(resources, "PopupItem2");
                stateMachine.findLblPopupDetailItemTitle(createContainer5).setText("Prénom : ");
                stateMachine.findLblPopupDetailItemValueSP(createContainer5).setText(beneficiary.firstName);
                arrayList.add(createContainer5);
            }
        } else if (str.equals("WALLET")) {
            Container createContainer6 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer6).setText("Numéro du Tél : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer6).setText(beneficiary.phoneNumber);
            arrayList.add(createContainer6);
            Container createContainer7 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer7).setText("Nom : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer7).setText(beneficiary.alias);
            arrayList.add(createContainer7);
        }
        return arrayList;
    }

    public Container GetPopupTransferBtn(Dialog dialog, Beneficiary beneficiary, String str, String str2, int i) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 5(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 6(dialog, str, beneficiary, str2));
        container.addComponent(button2);
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
        ServiceResponse sr;
        final /* synthetic */ String val$HBpass;
        final /* synthetic */ Beneficiary val$benf;
        final /* synthetic */ String val$chekType;
        final /* synthetic */ Dialog val$d;

        6(Dialog dialog, String str, Beneficiary beneficiary, String str2) {
            this.val$d = dialog;
            this.val$chekType = str;
            this.val$benf = beneficiary;
            this.val$HBpass = str2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            if (this.val$chekType.equals("COMPTE_CIH")) {
                AddBenfWallet addBenfWallet = AddBenfWallet.this;
                String str = this.val$benf.alias;
                String str2 = this.val$benf.cardNumber;
                String str3 = this.val$benf.alias;
                String str4 = this.val$chekType;
                this.sr = AddBenfWallet.access$200(addBenfWallet, addBenfWallet.FillHashMapFromParams(str, str2, "", str3, str4, str4), this.val$HBpass);
            } else if (this.val$chekType.equals("AUTRE_COMPTE")) {
                AddBenfWallet addBenfWallet2 = AddBenfWallet.this;
                String str5 = this.val$benf.alias;
                String str6 = this.val$benf.cardNumber;
                String str7 = this.val$benf.firstName;
                String str8 = this.val$benf.lastName;
                String str9 = this.val$chekType;
                this.sr = AddBenfWallet.access$200(addBenfWallet2, addBenfWallet2.FillHashMapFromParams(str5, str6, str7, str8, str9, str9), this.val$HBpass);
            } else if (this.val$chekType.equals("WALLET")) {
                AddBenfWallet addBenfWallet3 = AddBenfWallet.this;
                String str10 = this.val$benf.alias;
                String str11 = this.val$benf.phoneNumber;
                String str12 = this.val$benf.alias;
                String str13 = this.val$chekType;
                this.sr = AddBenfWallet.access$200(addBenfWallet3, addBenfWallet3.FillHashMapFromParams(str10, str11, "", str12, str13, str13), this.val$HBpass);
            }
            if (this.sr.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(this.sr.getStatusLabel()), null);
                AddBenfWallet.this.AccountProcess();
                if (CihMBanking.sesPMTR.actionAddBenfFromTrsfr) {
                    AddBenfWallet.this.uiManager.NavigateToPageById(81);
                    return;
                } else {
                    AddBenfWallet.this.uiManager.NavigateToPageById(51);
                    return;
                }
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(this.sr.getStatusLabel()), null);
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
        CihMBanking.sesPMTR.getSessionClient().setClient_AccountList(FillAccountArrayDataFromServiceResponse);
        if (FillAccountArrayDataFromServiceResponse.size() == 1) {
            CihMBanking.sesPMTR.setCurrentAccount((Account) FillAccountArrayDataFromServiceResponse.get(0));
        }
    }
}
