package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
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
public class AddBenfPage extends B3GPage {
    private String VnewaliasText;
    TextField txtGSMNumber;
    TextField txtHBPassWord;
    private UIBuilder uib = new UIBuilder();
    boolean isPushNotification = false;

    static /* synthetic */ UIBuilder access$000(AddBenfPage addBenfPage) {
        return addBenfPage.uib;
    }

    static /* synthetic */ Container access$100(AddBenfPage addBenfPage, Container container, Beneficiary beneficiary, String str, String str2) {
        return addBenfPage.GetTransferSecurityForm(container, beneficiary, str, str2);
    }

    static /* synthetic */ String access$200(AddBenfPage addBenfPage) {
        return addBenfPage.VnewaliasText;
    }

    static /* synthetic */ String access$202(AddBenfPage addBenfPage, String str) {
        addBenfPage.VnewaliasText = str;
        return str;
    }

    static /* synthetic */ ServiceResponse access$300(AddBenfPage addBenfPage, Hashtable hashtable, String str) {
        return addBenfPage.addBenefProcess(hashtable, str);
    }

    public AddBenfPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = false;
    }

    public Container GetPageContainer() {
        AddBenfPage addBenfPage;
        CihMBanking.exitApplication = false;
        Container GetContainer = GetContainer();
        Container notificationContainer = notificationContainer();
        RadioButton radioButton = (RadioButton) this.uib.findByName("rdMotifValue2", GetContainer);
        RadioButton radioButton2 = (RadioButton) this.uib.findByName("rdMotifValue1", GetContainer);
        Container container = (Container) this.uib.findByName("CntGlobalTransfer", GetContainer);
        Container container2 = (Container) this.uib.findByName("emptyContainer", GetContainer);
        Button button = (Button) this.uib.findByName("BtnAddBenfValidation", GetContainer);
        TextField textField = (TextField) this.uib.findByName("txtCardNumber", GetContainer);
        TextField textField2 = (TextField) this.uib.findByName("txtBenfName", GetContainer);
        Container createContainer = this.uib.createContainer(this.uiManager.ressource, "AddGSMCnt");
        Container container3 = new Container();
        container3.setUIID("EmptyContainer");
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.setScrollableY(false);
        try {
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(radioButton2);
            buttonGroup.add(radioButton);
            try {
                buttonGroup.getRadioButton(0).addActionListener(new 1(textField, textField2, container, notificationContainer, createContainer));
                buttonGroup.getRadioButton(1).addActionListener(new 2(textField, textField2, container, container3));
                textField2.addDataChangedListener(new AddBenfPage$$ExternalSyntheticLambda0(textField2, container));
                button.addActionListener(new 3(buttonGroup, textField, textField2, notificationContainer, GetContainer));
                addBenfPage = this;
            } catch (Exception unused) {
                addBenfPage = this;
            }
        } catch (Exception unused2) {
            addBenfPage = this;
        }
        try {
            container2.addComponent(addBenfPage.DrawNotificationCheck(notificationContainer, createContainer, notificationContainer));
            addBenfPage.thisContainer.addComponent(GetContainer);
            return addBenfPage.thisContainer;
        } catch (Exception unused3) {
            addBenfPage.thisContainer = new Container(new BoxLayout(2));
            addBenfPage.thisContainer.addComponent(addBenfPage.uiManager.GetGoBackHome());
            return addBenfPage.thisContainer;
        }
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$CntGlobalTransfer;
        final /* synthetic */ Container val$GSMCont;
        final /* synthetic */ Container val$notifContainer;
        final /* synthetic */ TextField val$txtBenfName;
        final /* synthetic */ TextField val$txtCardNumber;

        1(TextField textField, TextField textField2, Container container, Container container2, Container container3) {
            this.val$txtCardNumber = textField;
            this.val$txtBenfName = textField2;
            this.val$CntGlobalTransfer = container;
            this.val$notifContainer = container2;
            this.val$GSMCont = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$txtCardNumber.clear();
            this.val$txtBenfName.clear();
            Container container = this.val$CntGlobalTransfer;
            Container container2 = (Container) container.getComponentAt(6);
            AddBenfPage addBenfPage = AddBenfPage.this;
            Container container3 = this.val$notifContainer;
            container.replace(container2, addBenfPage.DrawNotificationCheck(container3, this.val$GSMCont, container3), (Transition) null);
            this.val$CntGlobalTransfer.revalidate();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$CntGlobalTransfer;
        final /* synthetic */ Container val$cntEmpty;
        final /* synthetic */ TextField val$txtBenfName;
        final /* synthetic */ TextField val$txtCardNumber;

        2(TextField textField, TextField textField2, Container container, Container container2) {
            this.val$txtCardNumber = textField;
            this.val$txtBenfName = textField2;
            this.val$CntGlobalTransfer = container;
            this.val$cntEmpty = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$txtCardNumber.clear();
            this.val$txtBenfName.clear();
            Container container = this.val$CntGlobalTransfer;
            container.replace((Container) container.getComponentAt(6), this.val$cntEmpty, (Transition) null);
            this.val$CntGlobalTransfer.revalidate();
        }
    }

    static /* synthetic */ void lambda$GetPageContainer$0(TextField textField, Container container, int i, int i2) {
        if (DataTools.isValidInput(textField.getText())) {
            textField.putClientProperty("LastValidAddBenfPage", textField.getText());
        } else {
            textField.stopEditing();
            textField.setText((String) textField.getClientProperty("LastValidAddBenfPage"));
            textField.startEditingAsync();
        }
        container.revalidate();
    }

    class 3 implements ActionListener {
        final /* synthetic */ Container val$cont;
        final /* synthetic */ ButtonGroup val$group;
        final /* synthetic */ Container val$notifContainer;
        final /* synthetic */ TextField val$txtBenfName;
        final /* synthetic */ TextField val$txtCardNumber;

        3(ButtonGroup buttonGroup, TextField textField, TextField textField2, Container container, Container container2) {
            this.val$group = buttonGroup;
            this.val$txtCardNumber = textField;
            this.val$txtBenfName = textField2;
            this.val$notifContainer = container;
            this.val$cont = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            boolean z;
            boolean z2;
            try {
                if (this.val$group.getRadioButton(0).isSelected()) {
                    Beneficiary beneficiary = new Beneficiary();
                    String text = this.val$txtCardNumber.getText();
                    String escapeHtml = DataTools.escapeHtml(this.val$txtBenfName.getText().trim());
                    if (escapeHtml.trim().length() != 0 && text.trim().length() != 0) {
                        if (text.trim().length() < 16) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Le numéro de la carte doit être composé de 16 chiffres"), null);
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (escapeHtml.trim().length() > 30) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Le nom du bénéficiaire ne doit pas dépasser 30 caractères"), null);
                            z2 = true;
                        }
                        if (escapeHtml.trim().length() < 3) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Le nom du bénéficiaire doit être composé au minimum de 3 caractères"), null);
                            z2 = true;
                        }
                        if (AddBenfPage.this.isPushNotification) {
                            if (AddBenfPage.this.txtGSMNumber == null) {
                                AddBenfPage addBenfPage = AddBenfPage.this;
                                addBenfPage.txtGSMNumber = (TextField) AddBenfPage.access$000(addBenfPage).findByName("TxtBeneGSM", this.val$notifContainer.getComponentAt(2));
                            }
                            if (!z2) {
                                if (AddBenfPage.this.txtGSMNumber.getText().trim().length() != 0) {
                                    if (!StringTools.phoneNumberChecker(AddBenfPage.this.txtGSMNumber.getText().trim())) {
                                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AddBenfPage.this.uiManager.stateMachine, DataTools.FormatUnicode("Merci d'entrer un numéro GSM valide"), null);
                                    } else {
                                        beneficiary.isNotif = "1";
                                        beneficiary.phoneNumber = AddBenfPage.this.txtGSMNumber.getText().trim();
                                    }
                                } else {
                                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez compléter le formulaire"), null);
                                }
                                z2 = true;
                            }
                        } else {
                            beneficiary.isNotif = "0";
                            beneficiary.phoneNumber = null;
                        }
                        if (z2) {
                            return;
                        }
                        AddBenfPage addBenfPage2 = AddBenfPage.this;
                        ServiceResponse VerfProcess = addBenfPage2.VerfProcess(addBenfPage2.FillHashMapFromParams(escapeHtml, text, "CARTE_B", "", "", false, ""));
                        if (VerfProcess.getStatusCode().equals("000")) {
                            beneficiary.cardNumber = text;
                            beneficiary.alias = escapeHtml;
                            beneficiary.typeBenef = "300014";
                            beneficiary.serviceId = "300014";
                            AddBenfPage addBenfPage3 = AddBenfPage.this;
                            Container container = addBenfPage3.thisContainer;
                            Container container2 = this.val$cont;
                            addBenfPage3.SwitchTransfertForms(container, container2, AddBenfPage.access$100(AddBenfPage.this, container2, beneficiary, "CARTE_B", "CB"), false);
                            return;
                        }
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, VerfProcess.getStatusLabel(), null);
                        return;
                    }
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez compléter le formulaire"), null);
                    return;
                }
                boolean z3 = true;
                if (this.val$group.getRadioButton(1).isSelected()) {
                    Beneficiary beneficiary2 = new Beneficiary();
                    String text2 = this.val$txtCardNumber.getText();
                    String escapeHtml2 = DataTools.escapeHtml(this.val$txtBenfName.getText().trim());
                    if (escapeHtml2.trim().length() != 0 && text2.trim().length() != 0) {
                        if (text2.trim().length() < 16) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Le numero de la carte doit être composé de 16 chiffres"), null);
                            z = true;
                        } else {
                            z = false;
                        }
                        if (escapeHtml2.trim().length() > 30) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Le nom du bénéficiaire ne doit pas dépasser 30 caractères"), null);
                            z = true;
                        }
                        if (escapeHtml2.trim().length() < 3) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Le nom du bénéficiaire doit être composé au minimum de 3 caractères"), null);
                        } else {
                            z3 = z;
                        }
                        if (z3) {
                            return;
                        }
                        AddBenfPage addBenfPage4 = AddBenfPage.this;
                        ServiceResponse VerfProcess2 = addBenfPage4.VerfProcess(addBenfPage4.FillHashMapFromParams(escapeHtml2, text2, "CARTE_E", "", "", false, ""));
                        if (VerfProcess2.getStatusCode().equals("000")) {
                            beneficiary2.cardNumber = text2;
                            beneficiary2.alias = escapeHtml2;
                            beneficiary2.typeBenef = "300018";
                            beneficiary2.serviceId = "300014";
                            AddBenfPage addBenfPage5 = AddBenfPage.this;
                            Container container3 = addBenfPage5.thisContainer;
                            Container container4 = this.val$cont;
                            addBenfPage5.SwitchTransfertForms(container3, container4, AddBenfPage.access$100(AddBenfPage.this, container4, beneficiary2, "CARTE_E", "CE"), false);
                            return;
                        }
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, VerfProcess2.getStatusLabel(), null);
                        return;
                    }
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez compléter le formulaire"), null);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public Container DrawNotificationCheck(Container container, Container container2, Container container3) {
        Container container4 = new Container();
        container.replace(container.getComponentAt(2), container4, (Transition) null);
        container4.setUIID("EmptyContainer");
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container5 = new Container();
        container5.setLayout(flowLayout);
        container5.setUIID("ContainerCheckFavorite");
        CheckBox checkBox = new CheckBox();
        checkBox.setUIID("mtc_checkBox");
        checkBox.setIcon(this.uiManager.ressource.getImage("radio_off.PNG"));
        checkBox.setPressedIcon(this.uiManager.ressource.getImage("radio_on.png"));
        checkBox.setToggle(true);
        checkBox.setTactileTouch(true);
        checkBox.setVerticalAlignment(4);
        checkBox.setTextPosition(1);
        checkBox.setText("Notifier le bénéficiaire");
        checkBox.setSelected(false);
        checkBox.addActionListener(new 4(checkBox, container, container2, container4));
        container5.addComponent(checkBox);
        container.replace(container.getComponentAt(0), container5, (Transition) null);
        container.revalidate();
        return container;
    }

    class 4 implements ActionListener {
        final /* synthetic */ CheckBox val$chk;
        final /* synthetic */ Container val$cntAlias;
        final /* synthetic */ Container val$cntEmpty;
        final /* synthetic */ Container val$cntGlobal;

        4(CheckBox checkBox, Container container, Container container2, Container container3) {
            this.val$chk = checkBox;
            this.val$cntGlobal = container;
            this.val$cntAlias = container2;
            this.val$cntEmpty = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$chk.isSelected()) {
                AddBenfPage.this.isPushNotification = true;
                Container container = this.val$cntGlobal;
                container.replace(container.getComponentAt(2), this.val$cntAlias, (Transition) null);
                this.val$cntGlobal.revalidate();
                return;
            }
            AddBenfPage.this.isPushNotification = false;
            Container container2 = this.val$cntGlobal;
            container2.replace(container2.getComponentAt(2), this.val$cntEmpty, (Transition) null);
            this.val$cntGlobal.revalidate();
        }
    }

    public Container GetContainer() {
        return this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AddNewBenfCont");
    }

    public final Hashtable FillHashMapFromParams(String str, String str2, String str3, String str4, String str5, boolean z, String str6) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CARD_NUMBER", str2);
        hashtable.put("ALIAS", str);
        hashtable.put("CHECK_TYPE", str3);
        hashtable.put("ADD_TYPE", "CARTE");
        hashtable.put("CARD_TYPE", str5);
        if (this.isPushNotification) {
            hashtable.put("ISPUSHNOTIFICATION", "1");
            hashtable.put("PHONE_NUMBER", str6);
        } else {
            hashtable.put("ISPUSHNOTIFICATION", "0");
        }
        return hashtable;
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

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }

    public Container notificationContainer() {
        return this.uib.createContainer(this.uiManager.ressource, "BeneficiaryNotificationCont");
    }

    private Container GetTransferSecurityForm(Container container, Beneficiary beneficiary, String str, String str2) {
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container2 = (Container) this.uib.findByName("Container17", createContainer);
        container2.removeAll();
        container2.setLayout(BoxLayout.y());
        container2.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 5(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 6(b3GCIHEcode, beneficiary, str, str2));
        return createContainer;
    }

    class 5 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        5(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            this.val$Parent.revalidate();
            AddBenfPage.this.uiManager.btnBack.getListeners().clear();
            AddBenfPage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                AddBenfPage.this.uiManager.GoBack();
            }
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ String val$CheckType;
        final /* synthetic */ Beneficiary val$benf;
        final /* synthetic */ String val$cardType;
        final /* synthetic */ B3GCIHEcode val$eCode1;

        6(B3GCIHEcode b3GCIHEcode, Beneficiary beneficiary, String str, String str2) {
            this.val$eCode1 = b3GCIHEcode;
            this.val$benf = beneficiary;
            this.val$CheckType = str;
            this.val$cardType = str2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AddBenfPage.access$202(AddBenfPage.this, this.val$eCode1.getValue());
            try {
                if (AddBenfPage.access$200(AddBenfPage.this).length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AddBenfPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                } else {
                    AddBenfPage.this.ShowDialogTransfertService(this.val$benf, this.val$CheckType, this.val$cardType, AddBenfPage.access$200(AddBenfPage.this), CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 111);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void ShowDialogTransfertService(Beneficiary beneficiary, String str, String str2, String str3, StateMachine stateMachine, B3gService b3gService, int i) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(beneficiary, str, str2, stateMachine, open, createContainer, null);
            for (int i2 = 0; i2 < GetPopupItemDetail.size(); i2++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i2));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupTransferBtn(dialog, beneficiary, str, str2, str3, i));
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
            dialog.addComponent("Center", createContainer);
            dialog.show(0, 0, 0, 0);
        } catch (IOException unused) {
        }
    }

    public ArrayList GetPopupItemDetail(Beneficiary beneficiary, String str, String str2, StateMachine stateMachine, Resources resources, Container container, B3gService b3gService) {
        ArrayList arrayList = new ArrayList();
        if (str.equals("CARTE_B")) {
            Container createContainer = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer).setText("N° Carte : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer).setText(beneficiary.cardNumber);
            arrayList.add(createContainer);
            Container createContainer2 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer2).setText("Nom : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer2).setText(beneficiary.alias);
            arrayList.add(createContainer2);
            if (beneficiary.isNotif.equals("1")) {
                Container createContainer3 = stateMachine.createContainer(resources, "PopupItem2");
                stateMachine.findLblPopupDetailItemTitle(createContainer3).setText("GSM : ");
                stateMachine.findLblPopupDetailItemValueSP(createContainer3).setText(beneficiary.phoneNumber);
                arrayList.add(createContainer3);
            }
        } else if (str.equals("CARTE_E")) {
            Container createContainer4 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer4).setText("N° Carte : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer4).setText(beneficiary.cardNumber);
            arrayList.add(createContainer4);
            Container createContainer5 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer5).setText("Nom : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer5).setText(beneficiary.alias);
            arrayList.add(createContainer5);
        }
        return arrayList;
    }

    public Container GetPopupTransferBtn(Dialog dialog, Beneficiary beneficiary, String str, String str2, String str3, int i) {
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
        button.addActionListener(new 7(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 8(dialog, beneficiary, str, str2, str3));
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

    class 8 implements ActionListener {
        ServiceResponse sr;
        final /* synthetic */ String val$CardType;
        final /* synthetic */ String val$HBpass;
        final /* synthetic */ Beneficiary val$benf;
        final /* synthetic */ String val$chekType;
        final /* synthetic */ Dialog val$d;

        8(Dialog dialog, Beneficiary beneficiary, String str, String str2, String str3) {
            this.val$d = dialog;
            this.val$benf = beneficiary;
            this.val$chekType = str;
            this.val$CardType = str2;
            this.val$HBpass = str3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            AddBenfPage addBenfPage = AddBenfPage.this;
            String str = this.val$benf.alias;
            String str2 = this.val$benf.cardNumber;
            String str3 = this.val$chekType;
            ServiceResponse access$300 = AddBenfPage.access$300(addBenfPage, addBenfPage.FillHashMapFromParams(str, str2, str3, str3, this.val$CardType, AddBenfPage.this.isPushNotification, this.val$benf.phoneNumber), this.val$HBpass);
            this.sr = access$300;
            if (access$300.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(this.sr.getStatusLabel()), null);
                AddBenfPage.this.AccountProcess();
                if (CihMBanking.sesPMTR.actionAddBenfFromTrsfr) {
                    AddBenfPage.this.uiManager.NavigateToPageById(9);
                    return;
                } else {
                    AddBenfPage.this.uiManager.NavigateToPageById(51);
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
        CihMBanking.sesPMTR.getSessionClient().setClient_AccountListVirm(FillAccountArrayDataFromServiceResponse);
        if (FillAccountArrayDataFromServiceResponse.size() == 1) {
            CihMBanking.sesPMTR.setCurrentAccount((Account) FillAccountArrayDataFromServiceResponse.get(0));
        }
    }
}
