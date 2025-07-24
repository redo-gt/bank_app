package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.codescanner.CodeScanner;
import com.b3g.codescanner.ScanResult;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.QRModel;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.tools.NativeCall;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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
import com.codename1.ui.util.UITimer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AddBenFromCompte extends B3GPage {
    private String VnewaliasText;
    Container c;
    TextField txtBenfName;
    TextField txtCardNumber;
    TextField txtFirstNameOther;
    TextField txtHBPassWord;
    TextField txtLastNameOthe;
    UIBuilder uib = new UIBuilder();

    static /* synthetic */ ServiceResponse access$000(AddBenFromCompte addBenFromCompte, Hashtable hashtable, String str) {
        return addBenFromCompte.addBenefProcess(hashtable, str);
    }

    public AddBenFromCompte(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = false;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.setScrollableY(false);
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AddNewBenfCompteCont");
        Container container = (Container) this.uib.findByName("CntGlobal", createContainer);
        Button button = (Button) this.uib.findByName("BtnAddBenfValidation", createContainer);
        new Container().setUIID("EmptyContainer");
        try {
            RadioButton radioButton = (RadioButton) this.uib.findByName("rdMotifValue1", createContainer);
            RadioButton radioButton2 = (RadioButton) this.uib.findByName("rdMotifValue2", createContainer);
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(radioButton);
            buttonGroup.add(radioButton2);
            buttonGroup.getRadioButton(0).addActionListener(new 1(container));
            buttonGroup.getRadioButton(1).addActionListener(new 2(container));
            button.addActionListener(new 3(buttonGroup, createContainer, container));
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
            container.replace((Container) container.getComponentAt(0), AddBenFromCompte.this.GetCihContainer(), (Transition) null);
            this.val$cntGlobal.revalidate();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$cntGlobal;

        2(Container container) {
            this.val$cntGlobal = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$cntGlobal;
            container.replace(container.getComponentAt(0), AddBenFromCompte.this.GetAutreContainer(), (Transition) null);
            this.val$cntGlobal.revalidate();
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Container val$cntGlobal;
        final /* synthetic */ Container val$cont;
        final /* synthetic */ ButtonGroup val$group;

        3(ButtonGroup buttonGroup, Container container, Container container2) {
            this.val$group = buttonGroup;
            this.val$cont = container;
            this.val$cntGlobal = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            boolean z;
            boolean z2;
            try {
                boolean z3 = true;
                if (this.val$group.getRadioButton(0).isSelected()) {
                    String trim = AddBenFromCompte.this.txtCardNumber.getText().trim();
                    String escapeHtml = DataTools.escapeHtml(AddBenFromCompte.this.txtBenfName.getText().trim());
                    if (escapeHtml.trim().length() != 0 && trim.trim().length() != 0) {
                        if (escapeHtml.trim().length() > 30) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Le nom du bénéficiaire ne doit pas dépasser 30 caractères"), null);
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (escapeHtml.trim().length() < 3) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Le nom du bénéficiaire doit être composé au minimum de 3 caractères"), null);
                            z2 = true;
                        }
                        if (trim.trim().length() < 16) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Le numero de compte doit être composé de 16 chiffres"), null);
                        } else {
                            z3 = z2;
                        }
                        if (z3) {
                            return;
                        }
                        if (!trim.substring(0, 7).equals(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical)) {
                            if (!trim.substring(7, 11).equals("2310")) {
                                AddBenFromCompte addBenFromCompte = AddBenFromCompte.this;
                                ServiceResponse VerfProcess = addBenFromCompte.VerfProcess(addBenFromCompte.FillHashMapFromParams(escapeHtml, trim, "", "", "COMPTE_CIH", ""));
                                if (VerfProcess.getStatusCode().equals("000")) {
                                    Beneficiary beneficiary = new Beneficiary();
                                    beneficiary.cardNumber = trim;
                                    beneficiary.alias = escapeHtml;
                                    beneficiary.serviceId = "300013";
                                    beneficiary.typeBenef = "300013";
                                    ServiceResponse sendOtpProcess = ServiceManager.sendOtpProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1021", 1012, "1", "1", "", "", "", "");
                                    if (sendOtpProcess.getStatusCode().equals("000")) {
                                        AddBenFromCompte addBenFromCompte2 = AddBenFromCompte.this;
                                        Container container = addBenFromCompte2.thisContainer;
                                        Container container2 = this.val$cont;
                                        addBenFromCompte2.SwitchTransfertForms(container, container2, AddBenFromCompte.this.GetTransferSecurityForm(container2, beneficiary, "COMPTE_CIH"), false);
                                        return;
                                    }
                                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AddBenFromCompte.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcess.getStatusLabel()), null);
                                    return;
                                }
                                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, VerfProcess.getStatusLabel(), null);
                                return;
                            }
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "bénéficiaire doit être différent de compte sur carnet", null);
                            return;
                        }
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Le compte bénéficiaire doit être différent de votre compte", null);
                        return;
                    }
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez compléter le formulaire"), null);
                    return;
                }
                if (this.val$group.getRadioButton(1).isSelected()) {
                    String trim2 = ((TextField) AddBenFromCompte.this.uib.findByName("txtRib", this.val$cntGlobal.getComponentAt(0))).getText().trim();
                    String escapeHtml2 = DataTools.escapeHtml(AddBenFromCompte.this.txtLastNameOthe.getText().trim());
                    String escapeHtml3 = DataTools.escapeHtml(AddBenFromCompte.this.txtFirstNameOther.getText().trim());
                    String str = escapeHtml3 + " " + escapeHtml2;
                    if (trim2.trim().length() != 0 && str.trim().length() != 0) {
                        if (trim2.trim().length() < 24) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Le RIB doit être composé de 24 chiffres"), null);
                            z = true;
                        } else {
                            z = false;
                        }
                        if (AddBenFromCompte.this.txtLastNameOthe.getText().trim().length() > 15 || AddBenFromCompte.this.txtFirstNameOther.getText().trim().length() > 15) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Le nom & prénom du bénéficiaire ne doivent pas dépasser 15 caractères "), null);
                        } else {
                            z3 = z;
                        }
                        if (z3) {
                            return;
                        }
                        AddBenFromCompte addBenFromCompte3 = AddBenFromCompte.this;
                        ServiceResponse VerfProcess2 = addBenFromCompte3.VerfProcess(addBenFromCompte3.FillHashMapFromParams(str, trim2, "", "", "AUTRE_COMPTE", ""));
                        if (VerfProcess2.getStatusCode().equals("000")) {
                            Beneficiary beneficiary2 = new Beneficiary();
                            beneficiary2.cardNumber = trim2;
                            beneficiary2.firstName = escapeHtml3;
                            beneficiary2.lastName = escapeHtml2;
                            beneficiary2.alias = str;
                            beneficiary2.serviceId = "300013";
                            beneficiary2.typeBenef = "300013";
                            ServiceResponse sendOtpProcess2 = ServiceManager.sendOtpProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1021", 1012, "1", "1", beneficiary2.cardNumber, "", "10", "");
                            if (sendOtpProcess2.getStatusCode().equals("000")) {
                                AddBenFromCompte addBenFromCompte4 = AddBenFromCompte.this;
                                Container container3 = addBenFromCompte4.thisContainer;
                                Container container4 = this.val$cont;
                                addBenFromCompte4.SwitchTransfertForms(container3, container4, AddBenFromCompte.this.GetTransferSecurityForm(container4, beneficiary2, "AUTRE_COMPTE"), false);
                                return;
                            }
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AddBenFromCompte.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcess2.getStatusLabel()), null);
                            return;
                        }
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, VerfProcess2.getStatusLabel(), null);
                        return;
                    }
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez compléter le formulaire"), null);
                }
            } catch (Exception e) {
                System.err.println(" ERROR ===> " + e.getMessage());
            }
        }
    }

    public Container GetAutreContainer() {
        Container createContainer = this.uib.createContainer(this.uiManager.ressource, "CntAddBenfTransCnt");
        this.txtFirstNameOther = (TextField) this.uib.findByName("txtFirstNameOther", createContainer);
        this.txtLastNameOthe = (TextField) this.uib.findByName("txtLastNameOthe", createContainer);
        this.txtFirstNameOther.addDataChangedListener(new AddBenFromCompte$$ExternalSyntheticLambda0(this, createContainer));
        this.txtLastNameOthe.addDataChangedListener(new AddBenFromCompte$$ExternalSyntheticLambda1(this, createContainer));
        return createContainer;
    }

    /* synthetic */ void lambda$GetAutreContainer$0$com-b3g-ui-page-AddBenFromCompte(Container container, int i, int i2) {
        if (DataTools.isValidInput(this.txtFirstNameOther.getText())) {
            TextField textField = this.txtFirstNameOther;
            textField.putClientProperty("LastValidFirstNameOther", textField.getText());
        } else {
            this.txtFirstNameOther.stopEditing();
            TextField textField2 = this.txtFirstNameOther;
            textField2.setText((String) textField2.getClientProperty("LastValidFirstNameOther"));
            this.txtFirstNameOther.startEditingAsync();
        }
        container.revalidate();
    }

    /* synthetic */ void lambda$GetAutreContainer$1$com-b3g-ui-page-AddBenFromCompte(Container container, int i, int i2) {
        if (DataTools.isValidInput(this.txtLastNameOthe.getText())) {
            TextField textField = this.txtLastNameOthe;
            textField.putClientProperty("LastValidtxtLastNameOthe", textField.getText());
        } else {
            this.txtLastNameOthe.stopEditing();
            TextField textField2 = this.txtLastNameOthe;
            textField2.setText((String) textField2.getClientProperty("LastValidtxtLastNameOthe"));
            this.txtLastNameOthe.startEditingAsync();
        }
        container.revalidate();
    }

    public Container GetCihContainer() {
        Container createContainer = this.uib.createContainer(this.uiManager.ressource, "CntAddBenfTypeAutreCnt");
        this.txtBenfName = (TextField) this.uib.findByName("txtBenefName", createContainer);
        this.txtCardNumber = (TextField) this.uib.findByName("txtCardNumber", createContainer);
        Button button = (Button) this.uib.findByName("BtnScan", createContainer);
        Button button2 = (Button) this.uib.findByName("BtnImport", createContainer);
        SessionParameter.setAddBenfFromQrCode(this.txtBenfName, this.txtCardNumber);
        this.txtBenfName.addDataChangedListener(new AddBenFromCompte$$ExternalSyntheticLambda2(this, createContainer));
        button.addActionListener(new 4(createContainer));
        button2.addActionListener(new 5(createContainer));
        return createContainer;
    }

    /* synthetic */ void lambda$GetCihContainer$2$com-b3g-ui-page-AddBenFromCompte(Container container, int i, int i2) {
        this.txtBenfName.setText(removeSpecialCharacters(this.txtBenfName.getText()));
        container.revalidate();
    }

    class 4 implements ActionListener {
        final /* synthetic */ Container val$contAddBenfTypeAutre;

        4(Container container) {
            this.val$contAddBenfTypeAutre = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            NativeCall nativeCall = (NativeCall) NativeLookup.create(NativeCall.class);
            if (nativeCall.isSupported() && !Display.getInstance().isSimulator() && !Display.getInstance().getPlatformName().equals("ios")) {
                nativeCall.scanQrCode();
                new UITimer(new 1()).schedule(1200, false, AddBenFromCompte.this.uiManager.currentForm);
            } else {
                CodeScanner.getInstance().scan(new 2());
            }
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                SessionParameter sessionParameter = CihMBanking.sesPMTR;
                if (SessionParameter.qrInfos != null) {
                    try {
                        QRModel qRModel = new QRModel();
                        SessionParameter sessionParameter2 = CihMBanking.sesPMTR;
                        QRModel ExtractData = qRModel.ExtractData(SessionParameter.qrInfos);
                        AddBenFromCompte.this.txtBenfName.setText(ExtractData.getBenfName());
                        AddBenFromCompte.this.txtCardNumber.setText(ExtractData.getBenfRib());
                        4.this.val$contAddBenfTypeAutre.revalidate();
                        4.this.val$contAddBenfTypeAutre.getParent().revalidate();
                    } catch (Exception unused) {
                    }
                }
            }
        }

        class 2 implements ScanResult {
            public void scanCanceled() {
            }

            public void scanError(int i, String str) {
            }

            2() {
            }

            public void scanCompleted(String str) {
                SessionParameter sessionParameter = CihMBanking.sesPMTR;
                SessionParameter.qrInfos = str;
                System.out.print("QR CODE RESULT : " + str);
                new UITimer(new 1()).schedule(800, false, AddBenFromCompte.this.uiManager.currentForm);
            }

            class 1 implements Runnable {
                1() {
                }

                public void run() {
                    QRModel qRModel = new QRModel();
                    try {
                        SessionParameter sessionParameter = CihMBanking.sesPMTR;
                        qRModel = qRModel.ExtractData(SessionParameter.qrInfos);
                    } catch (Exception unused) {
                    }
                    System.out.print("QR CODE RESULT MODEL NAME  : " + qRModel.getBenfName());
                    AddBenFromCompte.this.txtBenfName.setText(qRModel.getBenfName());
                    AddBenFromCompte.this.txtCardNumber.setText(qRModel.getBenfRib());
                    4.this.val$contAddBenfTypeAutre.revalidate();
                    4.this.val$contAddBenfTypeAutre.getParent().revalidate();
                }
            }
        }
    }

    class 5 implements ActionListener {
        NativeCall pNativeCall = (NativeCall) NativeLookup.create(NativeCall.class);
        final /* synthetic */ Container val$contAddBenfTypeAutre;

        5(Container container) {
            this.val$contAddBenfTypeAutre = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().isSimulator() || !this.pNativeCall.isSupported()) {
                return;
            }
            this.pNativeCall.importQrCode();
            new UITimer(new 1()).schedule(1200, false, AddBenFromCompte.this.uiManager.currentForm);
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                SessionParameter sessionParameter = CihMBanking.sesPMTR;
                if (SessionParameter.qrInfos != null) {
                    try {
                        QRModel qRModel = new QRModel();
                        SessionParameter sessionParameter2 = CihMBanking.sesPMTR;
                        QRModel ExtractData = qRModel.ExtractData(SessionParameter.qrInfos);
                        AddBenFromCompte.this.txtBenfName.setText(ExtractData.getBenfName());
                        AddBenFromCompte.this.txtCardNumber.setText(ExtractData.getBenfRib());
                        5.this.val$contAddBenfTypeAutre.revalidate();
                        5.this.val$contAddBenfTypeAutre.getParent().revalidate();
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    public void updateLabels(TextField... textFieldArr) {
        for (TextField textField : textFieldArr) {
            SessionParameter sessionParameter = CihMBanking.sesPMTR;
            textField.setText(SessionParameter.qrInfos);
        }
    }

    public Container GetTransferSecurityForm(Container container, Beneficiary beneficiary, String str) {
        this.c = new Container(BoxLayout.y());
        Container container2 = new Container(BoxLayout.y());
        Label label = new Label("Service sélectionné : ");
        label.setUIID("g_lblDashBoardTitleOrange");
        Label label2 = new Label("Ajouter Bénéficiaire");
        label2.setUIID("ad_lblValueBlue");
        Container container3 = new Container(BoxLayout.x());
        container3.add(label);
        container3.add(label2);
        Container container4 = new Container(BoxLayout.y());
        container4.setUIID("g_cntBorderV2");
        container2.add(container3);
        container2.add(container4);
        this.c.add(container2);
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransferFormTab3");
        Container container5 = (Container) this.uib.findByName("CntPassValue", createContainer);
        Container container6 = (Container) this.uib.findByName("CntPassTransparent", createContainer);
        this.txtHBPassWord = (TextField) this.uib.findByName("txtHBPassWord", createContainer);
        container5.setEnabled(true);
        container6.setEnabled(true);
        this.txtHBPassWord.setEnabled(true);
        container5.setFocusable(false);
        container6.setFocusable(false);
        this.txtHBPassWord.setFocusable(true);
        this.txtHBPassWord.setCursorBlinkTimeOff(999999);
        SessionParameter.setOtpTextField(this.txtHBPassWord);
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 6());
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 7(createContainer, beneficiary, str));
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).getParent().setEnabled(true);
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).getParent().setFocusable(false);
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).setFocusable(true);
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).setFocusable(true);
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).setEnabled(true);
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).setEnabled(true);
        this.uiManager.stateMachine.findCntKeyBoard(createContainer).revalidate();
        this.uiManager.stateMachine.findLblMotifTitle1(createContainer).setUIID("g_lblNotifOrange");
        this.uiManager.stateMachine.findIvrBtn(createContainer).addActionListener(new 8());
        this.c.add(createContainer);
        this.uiManager.stateMachine.findGsmMsgSP(createContainer).setText(DataTools.getEncryptedPhone(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm));
        return this.c;
    }

    class 6 implements ActionListener {
        6() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AddBenFromCompte.this.uiManager.GoBack();
        }
    }

    class 7 implements ActionListener {
        final /* synthetic */ String val$CheckType;
        final /* synthetic */ Beneficiary val$benf;
        final /* synthetic */ Container val$cntTransfertSecurityForm;

        7(Container container, Beneficiary beneficiary, String str) {
            this.val$cntTransfertSecurityForm = container;
            this.val$benf = beneficiary;
            this.val$CheckType = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if (AddBenFromCompte.this.uiManager.stateMachine.findTxtHBPassWord(this.val$cntTransfertSecurityForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AddBenFromCompte.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                } else {
                    AddBenFromCompte.this.ShowDialogTransfertService(this.val$benf, this.val$CheckType, AddBenFromCompte.this.uiManager.stateMachine.findTxtHBPassWord(this.val$cntTransfertSecurityForm).getText(), CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 111);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    class 8 implements ActionListener {
        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ServiceResponse sendOtpProcess = ServiceManager.sendOtpProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1021", 1012, "1", "1", "", "", "", "");
            if (!sendOtpProcess.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AddBenFromCompte.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcess.getStatusLabel()), null);
            }
            SessionParameter.setOtpTextField(AddBenFromCompte.this.txtHBPassWord);
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
        hashtable.put("ACCOUNT_NUMBER", str2);
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
            dialog.addComponent("Center", createContainer);
            dialog.show(0, 0, 0, 0);
        } catch (IOException unused) {
        }
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
        button.addActionListener(new 9(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 10(dialog, str, beneficiary, str2));
        container.addComponent(button2);
        return container;
    }

    class 9 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        9(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 10 implements ActionListener {
        ServiceResponse sr;
        final /* synthetic */ String val$HBpass;
        final /* synthetic */ Beneficiary val$benf;
        final /* synthetic */ String val$chekType;
        final /* synthetic */ Dialog val$d;

        10(Dialog dialog, String str, Beneficiary beneficiary, String str2) {
            this.val$d = dialog;
            this.val$chekType = str;
            this.val$benf = beneficiary;
            this.val$HBpass = str2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            if (this.val$chekType.equals("COMPTE_CIH")) {
                AddBenFromCompte addBenFromCompte = AddBenFromCompte.this;
                String str = this.val$benf.alias;
                String str2 = this.val$benf.cardNumber;
                String str3 = this.val$benf.alias;
                String str4 = this.val$chekType;
                this.sr = AddBenFromCompte.access$000(addBenFromCompte, addBenFromCompte.FillHashMapFromParams(str, str2, "", str3, str4, str4), this.val$HBpass);
            } else if (this.val$chekType.equals("AUTRE_COMPTE")) {
                AddBenFromCompte addBenFromCompte2 = AddBenFromCompte.this;
                String str5 = this.val$benf.alias;
                String str6 = this.val$benf.cardNumber;
                String str7 = this.val$benf.firstName;
                String str8 = this.val$benf.lastName;
                String str9 = this.val$chekType;
                this.sr = AddBenFromCompte.access$000(addBenFromCompte2, addBenFromCompte2.FillHashMapFromParams(str5, str6, str7, str8, str9, str9), this.val$HBpass);
            }
            if (this.sr.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(this.sr.getStatusLabel()), null);
                AddBenFromCompte.this.AccountProcess();
                if (CihMBanking.sesPMTR.actionAddBenfFromTrsfr) {
                    AddBenFromCompte.this.uiManager.NavigateToPageById(8);
                    return;
                } else {
                    AddBenFromCompte.this.uiManager.NavigateToPageById(51);
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

    public static void showScanResult(String str) {
        SessionParameter sessionParameter = CihMBanking.sesPMTR;
        SessionParameter.qrInfos = str;
    }

    public String removeSpecialCharacters(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if ((charAt >= 'A' && charAt <= 'Z') || ((charAt >= 'a' && charAt <= 'z') || ((charAt >= '0' && charAt <= '9') || charAt == ' ' || charAt == '.' || charAt == ','))) {
                sb.append(charAt);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Le nom du bénéficiaire ne doit pas contenir de caractères spéciaux (@#&%!=?+-*/\\)", null);
            }
        }
        return sb.toString();
    }
}
