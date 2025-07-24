package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.B3GTextField;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizard1;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.Step;
import com.b3g.ui.page.cih.Module.AccountLastConnection;
import com.b3g.ui.page.cih.Module.LoginAuthentication;
import com.b3g.ui.page.cih.Module.PopUpsManager;
import com.codename1.components.SpanLabel;
import com.codename1.fingerprint.Fingerprint;
import com.codename1.io.Storage;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ForgotPasswdPage extends B3GPage {
    static int counter;
    String Answer;
    TextField PassTxt;
    B3GWizard1 VirementWizard;
    String clientId;
    int cntActivateAccountStp1Height;
    int cntActivateAccountStp2Height;
    int cntActivateAccountStp4Height;
    public Container keyBoard;
    String phoneNumber;
    Step s1;
    Step s2;
    Step s3;
    Step s4;
    B3GUiManager uiManager;
    UIBuilder uib;
    String otp = "";
    String QuestionReponse = "";

    static /* synthetic */ String access$000(ForgotPasswdPage forgotPasswdPage, String str) {
        return forgotPasswdPage.phoneNumberFormatted(str);
    }

    static /* synthetic */ ServiceResponse access$100(ForgotPasswdPage forgotPasswdPage, String str, String str2) {
        return forgotPasswdPage.ForgotPasswordstep1(str, str2);
    }

    static /* synthetic */ ServiceResponse access$200(ForgotPasswdPage forgotPasswdPage, String str, String str2) {
        return forgotPasswdPage.ForgotPasswordstep4(str, str2);
    }

    static /* synthetic */ ServiceResponse access$300(ForgotPasswdPage forgotPasswdPage, String str) {
        return forgotPasswdPage.ForgotPasswordstep3(str);
    }

    static /* synthetic */ ServiceResponse access$400(ForgotPasswdPage forgotPasswdPage, String str) {
        return forgotPasswdPage.ForgotPasswordstep2(str);
    }

    public ForgotPasswdPage(Object obj, B3gService b3gService, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = b3gService;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.uiManager.btnBackOffline.setVisible(true);
        this.uiManager = this.uiManager;
        UIBuilder uIBuilder = new UIBuilder();
        this.uib = uIBuilder;
        this.uiManager.btnBackOffline.addActionListener(new 1());
        Container container = new Container(new BoxLayout(2));
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(0, 0, 4, 4);
        container.getAllStyles().setOpacity(230);
        CihMBanking.exitApplication = false;
        Step step = new Step();
        this.s1 = step;
        step.icon = "1inactif.png 1";
        this.s1.selectedIcon = "1actif.png 1";
        this.s1.stepCnt = LoginActivateAccountStep1();
        this.cntActivateAccountStp1Height = this.s1.stepCnt.getHeight();
        Step step2 = new Step();
        this.s2 = step2;
        step2.icon = "2inactif.png 1";
        this.s2.selectedIcon = "2actif.png 1";
        this.s2.stepCnt = LoginActivateAccountStep3();
        this.cntActivateAccountStp2Height = this.s2.stepCnt.getHeight();
        Step step3 = new Step();
        this.s3 = step3;
        step3.icon = "3inactif1.png";
        this.s3.selectedIcon = "3actif1.png";
        this.s3.stepCnt = LoginActivateAccountStep4();
        Step step4 = new Step();
        this.s4 = step4;
        step4.icon = "4inactif1.png";
        this.s4.selectedIcon = "4actif1.png";
        this.s4.stepCnt = LoginActivateAccountStep2();
        this.cntActivateAccountStp4Height = this.s4.stepCnt.getHeight();
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.s1);
        arrayList.add(this.s2);
        arrayList.add(this.s3);
        arrayList.add(this.s4);
        B3GWizard1 b3GWizard1 = new B3GWizard1(arrayList);
        this.VirementWizard = b3GWizard1;
        Container drawWizard = b3GWizard1.drawWizard("", 16777215, 0);
        drawWizard.setUIID("ContainerTrspBottom1");
        drawWizard.setScrollableY(false);
        container.addComponent(drawWizard);
        this.thisContainer = container;
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ForgotPasswdPage.this.Previous();
        }
    }

    private Container LoginActivateAccountStep1() {
        Container createContainer = this.uib.createContainer("/cihv3", "ForgotPasswd");
        Button button = (Button) this.uib.findByName("suivantBtn", createContainer);
        TextField textField = (TextField) this.uib.findByName("accountNum", createContainer);
        TextField textField2 = (TextField) this.uib.findByName("phoneNum", createContainer);
        textField2.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        textField.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        this.keyBoard = (Container) this.uib.findByName("keybordCnt", createContainer);
        createContainer.getAllStyles().setPaddingUnit(1);
        createContainer.getAllStyles().setPadding(0, 0, 0, 0);
        createContainer.getStyle().setBgTransparency(0);
        textField.getAllStyles().setMarginUnit(CIHStyles.b);
        textField2.getAllStyles().setMarginUnit(CIHStyles.b);
        textField.getAllStyles().setMargin(0.0f, 0.0f, 2.0f, 2.0f);
        textField2.getAllStyles().setMargin(0.0f, 0.0f, 2.0f, 2.0f);
        if (CihMBanking.RTL) {
            textField.setRTL(true);
        } else {
            textField.setRTL(false);
        }
        if (CihMBanking.RTL) {
            textField2.setRTL(true);
        } else {
            textField2.setRTL(false);
        }
        textField.addPointerReleasedListener(new 2(createContainer));
        textField.addActionListener(new 3(createContainer));
        textField2.addPointerReleasedListener(new 4(createContainer));
        textField2.addActionListener(new 5(createContainer));
        button.addActionListener(new 6(textField, textField2));
        createContainer.setScrollableY(true);
        createContainer.setScrollVisible(false);
        return createContainer;
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$LoginActivateAccountStp1;

        2(Container container) {
            this.val$LoginActivateAccountStp1 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                return;
            }
            this.val$LoginActivateAccountStp1.setScrollableY(false);
            this.val$LoginActivateAccountStp1.setScrollVisible(false);
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Container val$LoginActivateAccountStp1;

        3(Container container) {
            this.val$LoginActivateAccountStp1 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                return;
            }
            this.val$LoginActivateAccountStp1.setScrollableY(true);
            this.val$LoginActivateAccountStp1.setScrollVisible(false);
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Container val$LoginActivateAccountStp1;

        4(Container container) {
            this.val$LoginActivateAccountStp1 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                return;
            }
            this.val$LoginActivateAccountStp1.setScrollableY(false);
            this.val$LoginActivateAccountStp1.setScrollVisible(false);
        }
    }

    class 5 implements ActionListener {
        final /* synthetic */ Container val$LoginActivateAccountStp1;

        5(Container container) {
            this.val$LoginActivateAccountStp1 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                return;
            }
            this.val$LoginActivateAccountStp1.setScrollableY(true);
            this.val$LoginActivateAccountStp1.setScrollVisible(false);
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ TextField val$accountNum;
        final /* synthetic */ TextField val$phoneNum;

        6(TextField textField, TextField textField2) {
            this.val$accountNum = textField;
            this.val$phoneNum = textField2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PopUpsManager popUpsManager = new PopUpsManager();
            ForgotPasswdPage.this.clientId = this.val$accountNum.getText();
            ForgotPasswdPage.this.phoneNumber = this.val$phoneNum.getText();
            if ((ForgotPasswdPage.this.clientId.length() == 16 || ForgotPasswdPage.this.clientId.length() == 7) && ForgotPasswdPage.this.phoneNumber.length() >= 10) {
                if (DataTools.checkConnection()) {
                    ForgotPasswdPage forgotPasswdPage = ForgotPasswdPage.this;
                    String substring = forgotPasswdPage.clientId.substring(0, 7);
                    ForgotPasswdPage forgotPasswdPage2 = ForgotPasswdPage.this;
                    ServiceResponse access$100 = ForgotPasswdPage.access$100(forgotPasswdPage, substring, ForgotPasswdPage.access$000(forgotPasswdPage2, forgotPasswdPage2.phoneNumber));
                    if (access$100.getStatusCode().equals("000")) {
                        ForgotPasswdPage.this.VirementWizard.goToNextStep(ForgotPasswdPage.this.s1, "", 16777215, 16777215);
                        return;
                    } else {
                        ForgotPasswdPage.this.uiManager.ShowMessageTransaction(ForgotPasswdPage.this.uiManager.stateMachine, DataTools.FormatUnicode(access$100.getStatusLabel()), null);
                        return;
                    }
                }
                popUpsManager.showNoConnectoinMessg();
                return;
            }
            ForgotPasswdPage.this.uiManager.ShowMessageTransaction(ForgotPasswdPage.this.uiManager.stateMachine, DataTools.FormatUnicode("veuillez remplir tous les champs requis."), null);
        }
    }

    private String phoneNumberFormatted(String str) {
        if (str.substring(0, 2).equals("06")) {
            return "212" + str.substring(1);
        }
        return str.substring(0, 2).equals("07") ? "212" + str.substring(1) : str;
    }

    private Container LoginActivateAccountStep2() {
        Container createContainer = this.uib.createContainer("/cihv3", "ForgotPsswdStep2");
        createContainer.getAllStyles().setPaddingUnit(1);
        createContainer.getAllStyles().setMargin(0, 2, 0, 0);
        TextField textField = (TextField) this.uib.findByName("codeAccesTxtF", createContainer);
        textField.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        textField.setCursorBlinkTimeOff(999999);
        SessionParameter.setOtpTextField(textField);
        if (CihMBanking.RTL) {
            textField.setRTL(true);
        } else {
            textField.setRTL(false);
        }
        Button button = (Button) this.uib.findByName("delCodeAccesBtn", createContainer);
        button.setIcon(CihMBanking.delUserIcon);
        button.setHidden(true);
        B3GTextField b3GTextField = new B3GTextField(textField, (Container) this.uib.findByName("loginTxtField", createContainer));
        Button button2 = (Button) this.uib.findByName("fakeBtn", createContainer);
        createContainer.getStyle().setBgTransparency(0);
        createContainer.setScrollVisible(false);
        textField.addPointerReleasedListener(new 7(createContainer));
        textField.addActionListener(new 8(createContainer));
        button2.addActionListener(new 9(button2, textField, createContainer));
        b3GTextField.getTextField().addDataChangedListener(new 10(b3GTextField, button, createContainer));
        ((Button) this.uib.findByName("ivrBtn", createContainer)).addActionListener(new 11());
        ((Button) this.uib.findByName("suivantBtn", createContainer)).addActionListener(new 12(textField, createContainer));
        b3GTextField.setIsTextFieldFocused(false);
        createContainer.revalidate();
        return createContainer;
    }

    class 7 implements ActionListener {
        final /* synthetic */ Container val$LoginActivationAccountStep2;

        7(Container container) {
            this.val$LoginActivationAccountStep2 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                return;
            }
            this.val$LoginActivationAccountStep2.setScrollableY(false);
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ Container val$LoginActivationAccountStep2;

        8(Container container) {
            this.val$LoginActivationAccountStep2 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                return;
            }
            this.val$LoginActivationAccountStep2.setScrollableY(true);
            this.val$LoginActivationAccountStep2.setScrollVisible(false);
        }
    }

    class 9 implements ActionListener {
        final /* synthetic */ Container val$LoginActivationAccountStep2;
        final /* synthetic */ TextField val$codeAccesTxtF;
        final /* synthetic */ Button val$fakeBtn;

        9(Button button, TextField textField, Container container) {
            this.val$fakeBtn = button;
            this.val$codeAccesTxtF = textField;
            this.val$LoginActivationAccountStep2 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$fakeBtn.setIcon(CihMBanking.theme.getImage("PasswordFocus.png"));
            this.val$codeAccesTxtF.setHint("");
            this.val$LoginActivationAccountStep2.revalidate();
        }
    }

    class 10 implements DataChangedListener {
        final /* synthetic */ Container val$LoginActivationAccountStep2;
        final /* synthetic */ Button val$delCodeAccesBtn;
        final /* synthetic */ B3GTextField val$otpB3gTexF;

        10(B3GTextField b3GTextField, Button button, Container container) {
            this.val$otpB3gTexF = b3GTextField;
            this.val$delCodeAccesBtn = button;
            this.val$LoginActivationAccountStep2 = container;
        }

        public void dataChanged(int i, int i2) {
            if (this.val$otpB3gTexF.getTextField().getText().length() > 0) {
                this.val$delCodeAccesBtn.setHidden(true);
                this.val$LoginActivationAccountStep2.revalidate();
            } else {
                this.val$delCodeAccesBtn.setHidden(true);
                this.val$LoginActivationAccountStep2.revalidate();
            }
        }
    }

    class 11 implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
        }

        11() {
        }
    }

    class 12 implements ActionListener {
        final /* synthetic */ Container val$LoginActivationAccountStep2;
        final /* synthetic */ TextField val$codeAccesTxtF;

        12(TextField textField, Container container) {
            this.val$codeAccesTxtF = textField;
            this.val$LoginActivationAccountStep2 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$codeAccesTxtF.getText().length() != 0) {
                boolean z = false;
                if (ForgotPasswdPage.counter < 3) {
                    ForgotPasswdPage forgotPasswdPage = ForgotPasswdPage.this;
                    ServiceResponse access$200 = ForgotPasswdPage.access$200(forgotPasswdPage, forgotPasswdPage.PassTxt.getText(), this.val$codeAccesTxtF.getText());
                    if (access$200.getStatusCode().equals("000")) {
                        Container container = this.val$LoginActivationAccountStep2;
                        container.replace(container.getComponentAt(0), ForgotPasswdPage.this.ConfirmPasswordRecup(), CommonTransitions.createSlide(0, true, 0));
                        this.val$LoginActivationAccountStep2.revalidate();
                        Vector vector = new Vector();
                        if (Storage.getInstance().exists("veclistCnxStorage")) {
                            vector = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
                        }
                        int i = 0;
                        while (true) {
                            if (i >= vector.size()) {
                                break;
                            }
                            if (ForgotPasswdPage.this.clientId.equals(((AccountLastConnection) vector.elementAt(i)).radical)) {
                                z = true;
                                break;
                            }
                            i++;
                        }
                        if (Display.getInstance().isSimulator()) {
                            return;
                        }
                        TouchIDNativeCall touchIDNativeCall = (TouchIDNativeCall) NativeLookup.create(TouchIDNativeCall.class);
                        if (Fingerprint.isAvailable() && z && touchIDNativeCall.isSupported()) {
                            touchIDNativeCall.savePasswordForRadical(ForgotPasswdPage.this.clientId, ForgotPasswdPage.this.PassTxt.getText());
                            return;
                        }
                        return;
                    }
                    ForgotPasswdPage.counter++;
                    ForgotPasswdPage.this.uiManager.ShowMessageTransaction(ForgotPasswdPage.this.uiManager.stateMachine, DataTools.FormatUnicode(access$200.getStatusLabel()), null);
                    return;
                }
                ForgotPasswdPage.counter = 0;
                ForgotPasswdPage.this.uiManager.stateMachine.showForm("LoginV2New", (Command) null);
                return;
            }
            ForgotPasswdPage.this.uiManager.ShowMessageTransaction(ForgotPasswdPage.this.uiManager.stateMachine, DataTools.FormatUnicode("Veuillez saisir le code reçu par SMS"), null);
        }
    }

    private Container LoginActivateAccountStep3() {
        Container createContainer = this.uib.createContainer("/cihv3", "ForgotPasswdStep3");
        ComboBox comboBox = (ComboBox) this.uib.findByName("CbxQuestion", createContainer);
        TextField textField = (TextField) this.uib.findByName("responsetxtF", createContainer);
        textField.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        Button button = (Button) this.uib.findByName("validerBtn", createContainer);
        if (CihMBanking.RTL) {
            textField.setRTL(true);
        } else {
            textField.setRTL(false);
        }
        createContainer.getStyle().setBgTransparency(0);
        createContainer.setScrollVisible(false);
        textField.addPointerReleasedListener(new 13(createContainer));
        textField.addActionListener(new 14(createContainer));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Lieu de naissance de ma mère", "32");
        linkedHashMap.put("Meilleur ami d’enfance", "33");
        linkedHashMap.put("Professeur préféré", "34");
        linkedHashMap.put("Personnage Historique préféré", "35");
        linkedHashMap.put("Sport préféré", "36");
        linkedHashMap.put("Livre préféré", "37");
        comboBox.setUIID("Lbl_lato_bold_13");
        ((DefaultListModel) comboBox.getModel()).removeAll();
        for (int i = 0; i < linkedHashMap.size(); i++) {
            comboBox.addItem(linkedHashMap.keySet().toArray()[i]);
        }
        button.addActionListener(new 15(textField));
        return createContainer;
    }

    class 13 implements ActionListener {
        final /* synthetic */ Container val$LoginActivationAccountStep4;

        13(Container container) {
            this.val$LoginActivationAccountStep4 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                return;
            }
            this.val$LoginActivationAccountStep4.setScrollableY(false);
        }
    }

    class 14 implements ActionListener {
        final /* synthetic */ Container val$LoginActivationAccountStep4;

        14(Container container) {
            this.val$LoginActivationAccountStep4 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                return;
            }
            this.val$LoginActivationAccountStep4.setScrollableY(true);
            this.val$LoginActivationAccountStep4.setScrollVisible(false);
        }
    }

    class 15 implements ActionListener {
        final /* synthetic */ TextField val$responsetxtF;

        15(TextField textField) {
            this.val$responsetxtF = textField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!this.val$responsetxtF.getText().equals("")) {
                ForgotPasswdPage.this.QuestionReponse = this.val$responsetxtF.getText();
                ServiceResponse access$300 = ForgotPasswdPage.access$300(ForgotPasswdPage.this, this.val$responsetxtF.getText());
                if (access$300.getStatusCode().equals("000")) {
                    ForgotPasswdPage.this.VirementWizard.goToNextStep(ForgotPasswdPage.this.s2, "", 16777215, 16777215);
                    return;
                } else {
                    ForgotPasswdPage.this.uiManager.ShowMessageTransaction(ForgotPasswdPage.this.uiManager.stateMachine, DataTools.FormatUnicode(access$300.getStatusLabel()), null);
                    return;
                }
            }
            ForgotPasswdPage.this.uiManager.ShowMessageTransaction(ForgotPasswdPage.this.uiManager.stateMachine, DataTools.FormatUnicode("veuillez remplir tous les champs requis."), null);
        }
    }

    private Container LoginActivateAccountStep4() {
        Container createContainer = this.uib.createContainer("/cihv3", "ForgotPasswdStep4");
        TextField textField = (TextField) this.uib.findByName("PassTxt", createContainer);
        this.PassTxt = textField;
        textField.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        this.PassTxt.setCursorBlinkTimeOff(999999);
        TextField textField2 = (TextField) this.uib.findByName("newPassTxt", createContainer);
        textField2.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        textField2.setCursorBlinkTimeOff(999999);
        if (CihMBanking.RTL) {
            this.PassTxt.setRTL(true);
        } else {
            this.PassTxt.setRTL(false);
        }
        if (CihMBanking.RTL) {
            textField2.setRTL(true);
        } else {
            textField2.setRTL(false);
        }
        Button button = (Button) this.uib.findByName("delPassBtn", createContainer);
        Button button2 = (Button) this.uib.findByName("delnewPassBtn", createContainer);
        button.setIcon(CihMBanking.delUserIcon);
        button2.setIcon(CihMBanking.delUserIcon);
        button.setVisible(false);
        button2.setVisible(false);
        button.setHidden(true);
        button2.setHidden(true);
        B3GTextField b3GTextField = new B3GTextField(this.PassTxt, (Container) this.uib.findByName("passTxtField", createContainer));
        B3GTextField b3GTextField2 = new B3GTextField(textField2, (Container) this.uib.findByName("newPassTxtField", createContainer));
        Container container = (Container) this.uib.findByName("keybordCnt", createContainer);
        Button button3 = (Button) this.uib.findByName("fakeBtn", createContainer);
        Button button4 = (Button) this.uib.findByName("fakeBtn1", createContainer);
        createContainer.getStyle().setBgTransparency(0);
        createContainer.setScrollVisible(false);
        button3.addActionListener(new 16(button3, button4, textField2, button2, createContainer));
        button4.addActionListener(new 17(button3, button4, textField2, button, createContainer));
        b3GTextField.getTextField().addDataChangedListener(new 18(b3GTextField, button, createContainer));
        ((Button) this.uib.findByName("suivantBtn", createContainer)).addActionListener(new 19(textField2));
        b3GTextField.setIsTextFieldFocused(false);
        DrawLoginKeyBoard(container, b3GTextField, b3GTextField2, this.uiManager);
        b3GTextField.getTextField().addDataChangedListener(new 20(createContainer));
        return createContainer;
    }

    class 16 implements ActionListener {
        final /* synthetic */ Container val$LoginActivateAccountStep3;
        final /* synthetic */ Button val$delnewPassBtn;
        final /* synthetic */ Button val$fakeBtn;
        final /* synthetic */ Button val$fakeBtn1;
        final /* synthetic */ TextField val$newPassTxt;

        16(Button button, Button button2, TextField textField, Button button3, Container container) {
            this.val$fakeBtn = button;
            this.val$fakeBtn1 = button2;
            this.val$newPassTxt = textField;
            this.val$delnewPassBtn = button3;
            this.val$LoginActivateAccountStep3 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$fakeBtn.setIcon(CihMBanking.theme.getImage("PasswordFocus.png"));
            this.val$fakeBtn1.setIcon(CihMBanking.theme.getImage("PasswordV1.png"));
            ForgotPasswdPage.this.PassTxt.setHint("");
            this.val$newPassTxt.setHint("Confirmer le mot de passe");
            this.val$delnewPassBtn.setVisible(false);
            this.val$LoginActivateAccountStep3.forceRevalidate();
        }
    }

    class 17 implements ActionListener {
        final /* synthetic */ Container val$LoginActivateAccountStep3;
        final /* synthetic */ Button val$delPassBtn;
        final /* synthetic */ Button val$fakeBtn;
        final /* synthetic */ Button val$fakeBtn1;
        final /* synthetic */ TextField val$newPassTxt;

        17(Button button, Button button2, TextField textField, Button button3, Container container) {
            this.val$fakeBtn = button;
            this.val$fakeBtn1 = button2;
            this.val$newPassTxt = textField;
            this.val$delPassBtn = button3;
            this.val$LoginActivateAccountStep3 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$fakeBtn.setIcon(CihMBanking.theme.getImage("PasswordV1.png"));
            this.val$fakeBtn1.setIcon(CihMBanking.theme.getImage("PasswordFocus.png"));
            this.val$newPassTxt.setHint("");
            ForgotPasswdPage.this.PassTxt.setHint("Mot de passe");
            this.val$delPassBtn.setVisible(false);
            this.val$LoginActivateAccountStep3.forceRevalidate();
        }
    }

    class 18 implements DataChangedListener {
        final /* synthetic */ Container val$LoginActivateAccountStep3;
        final /* synthetic */ Button val$delPassBtn;
        final /* synthetic */ B3GTextField val$passTxt;

        18(B3GTextField b3GTextField, Button button, Container container) {
            this.val$passTxt = b3GTextField;
            this.val$delPassBtn = button;
            this.val$LoginActivateAccountStep3 = container;
        }

        public void dataChanged(int i, int i2) {
            if (this.val$passTxt.getTextField().getText().length() > 0) {
                this.val$delPassBtn.setVisible(true);
                this.val$LoginActivateAccountStep3.forceRevalidate();
            } else {
                this.val$delPassBtn.setVisible(false);
                this.val$LoginActivateAccountStep3.forceRevalidate();
            }
        }
    }

    class 19 implements ActionListener {
        final /* synthetic */ TextField val$newPassTxt;

        19(TextField textField) {
            this.val$newPassTxt = textField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!ForgotPasswdPage.this.PassTxt.getText().equals("") && !this.val$newPassTxt.getText().equals("")) {
                if (ForgotPasswdPage.this.PassTxt.getText().length() < 17 && ForgotPasswdPage.this.PassTxt.getText().length() > 5) {
                    if (ForgotPasswdPage.this.PassTxt.getText().equals(this.val$newPassTxt.getText())) {
                        ForgotPasswdPage forgotPasswdPage = ForgotPasswdPage.this;
                        ServiceResponse access$400 = ForgotPasswdPage.access$400(forgotPasswdPage, forgotPasswdPage.QuestionReponse);
                        if (access$400.getStatusCode().equals("000")) {
                            ForgotPasswdPage.this.VirementWizard.goToNextStep(ForgotPasswdPage.this.s3, "", 16777215, 16777215);
                            return;
                        } else {
                            ForgotPasswdPage.this.uiManager.ShowMessageTransaction(ForgotPasswdPage.this.uiManager.stateMachine, access$400.getStatusLabel(), null);
                            return;
                        }
                    }
                    ForgotPasswdPage.this.uiManager.ShowMessageTransaction(ForgotPasswdPage.this.uiManager.stateMachine, DataTools.FormatUnicode("Les mots de passe ne sont pas identiques"), null);
                    return;
                }
                ForgotPasswdPage.this.uiManager.ShowMessageTransaction(ForgotPasswdPage.this.uiManager.stateMachine, DataTools.FormatUnicode("Le mot de passe doit avoir une longueur minimale de 6 chiffres et une longueur maximale de 16 chiffres"), null);
                return;
            }
            ForgotPasswdPage.this.uiManager.ShowMessageTransaction(ForgotPasswdPage.this.uiManager.stateMachine, DataTools.FormatUnicode("veuillez remplir tous les champs requis."), null);
        }
    }

    class 20 implements DataChangedListener {
        final /* synthetic */ Container val$LoginActivateAccountStep3;

        20(Container container) {
            this.val$LoginActivateAccountStep3 = container;
        }

        public void dataChanged(int i, int i2) {
            if (LoginAuthentication.loginTxt.getTextField().getText().length() > 0) {
                this.val$LoginActivateAccountStep3.forceRevalidate();
            } else {
                this.val$LoginActivateAccountStep3.forceRevalidate();
            }
        }
    }

    public void DrawLoginKeyBoard(Container container, B3GTextField b3GTextField, B3GTextField b3GTextField2, B3GUiManager b3GUiManager) {
        Container container2 = new Container(BoxLayout.y());
        ArrayList genrateRandomListInt = genrateRandomListInt();
        Container container3 = new Container(new BorderLayout());
        Label label = new Label("Se souvenir de moi");
        label.setUIID("lbl_medium_black");
        Button button = new Button();
        button.setIcon(CihMBanking.theme.getImage("RememberOff.png"));
        button.addActionListener(new 21(button));
        button.setUIID("Container");
        container3.add("West", label);
        container3.add("East", button);
        container3.getAllStyles().setMarginUnit(1);
        container3.getAllStyles().setMargin(1, 1, 0, 0);
        Container container4 = new Container(new BorderLayout());
        Button button2 = new Button("Mot de passe oublié?");
        button2.addActionListener(new 22(b3GUiManager));
        button2.setUIID("SV_lbl_medium_blan");
        container4.add("East", button2);
        if (Display.getInstance().getDisplayHeight() < 1334) {
            container2.addComponent(DrawKeyBoardRow(b3GTextField2, b3GTextField, (List) genrateRandomListInt.get(0)));
            container2.addComponent(DrawKeyBoardRow(b3GTextField2, b3GTextField, (List) genrateRandomListInt.get(1)));
        } else {
            container2.addComponent(DrawKeyBoardRow(b3GTextField2, b3GTextField, (List) genrateRandomListInt.get(0)));
            container2.addComponent(DrawKeyBoardRow(b3GTextField2, b3GTextField, (List) genrateRandomListInt.get(1)));
            container2.addComponent(DrawFinalKeyBoardRow(b3GTextField2, b3GTextField, (List) genrateRandomListInt.get(2)));
        }
        container.add(container2);
    }

    class 21 implements ActionListener {
        final /* synthetic */ Button val$RememberBtn;

        21(Button button) {
            this.val$RememberBtn = button;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$RememberBtn.getIcon().equals(CihMBanking.theme.getImage("RememberOn.png"))) {
                this.val$RememberBtn.setIcon(CihMBanking.theme.getImage("RememberOff.png"));
            } else {
                this.val$RememberBtn.setIcon(CihMBanking.theme.getImage("RememberOn.png"));
            }
        }
    }

    class 22 implements ActionListener {
        final /* synthetic */ B3GUiManager val$uIManager;

        22(B3GUiManager b3GUiManager) {
            this.val$uIManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$uIManager.NavigateToPageByIdOffligne(121);
        }
    }

    public Container DrawKeyBoardRow(B3GTextField b3GTextField, B3GTextField b3GTextField2, List list) {
        Container container = new Container();
        new GridLayout(1, list.size());
        container.setLayout(new FlowLayout(4, 4));
        container.removeAll();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            String str2 = "" + str;
            Label label = new Label(str);
            Button button = new Button(label.getText());
            button.setUIID("VKBBtnNewVs");
            button.setVerticalAlignment(4);
            button.setAlignment(4);
            button.setFocusable(false);
            label.setVerticalAlignment(4);
            Stroke stroke = new Stroke(3.0f, 1, 1, 3.0f);
            button.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(0).shadowSpread(40).color(3947067).strokeColor(16777215).stroke(stroke));
            button.getPressedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(255).shadowSpread(40).color(15818018).strokeColor(15818018).stroke(stroke));
            button.getAllStyles().setMargin(0, 0, 2, 2);
            label.getAllStyles().setMargin(2, 2, 2, 2);
            button.getAllStyles().setPadding(2, 2, 2, 2);
            button.getAllStyles().setMarginUnit(1);
            button.getAllStyles().setPaddingUnit(2);
            button.setFocusable(false);
            button.addActionListener(new 23(b3GTextField2, str2, b3GTextField));
            container.addComponent(button);
        }
        return container;
    }

    class 23 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ B3GTextField val$PassField;
        final /* synthetic */ String val$number;

        23(B3GTextField b3GTextField, String str, B3GTextField b3GTextField2) {
            this.val$LoginField = b3GTextField;
            this.val$number = str;
            this.val$PassField = b3GTextField2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$LoginField.isIsTextFieldFocused()) {
                this.val$LoginField.getTextField().setText(this.val$LoginField.getTextField().getText() + this.val$number);
            } else if (this.val$PassField.isIsTextFieldFocused()) {
                this.val$PassField.getTextField().setConstraint(65536);
                this.val$PassField.getTextField().setText(this.val$PassField.getTextField().getText() + this.val$number);
            }
        }
    }

    public Container DrawFinalKeyBoardRow(B3GTextField b3GTextField, B3GTextField b3GTextField2, List list) {
        ArrayList arrayList = new ArrayList();
        Button button = new Button();
        button.setUIID("EmptyContainer");
        Container container = new Container();
        int i = 1;
        TableLayout tableLayout = new TableLayout(1, 3);
        tableLayout.setGrowHorizontally(true);
        int i2 = 4;
        container.setLayout(new FlowLayout(4, 4));
        container.removeAll();
        arrayList.add(button);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            String str2 = "" + str;
            Label label = new Label(str);
            Button button2 = new Button(label.getText());
            button2.setUIID("VKBBtnNewVs");
            button2.setVerticalAlignment(i2);
            button2.setAlignment(i2);
            button2.setFocusable(false);
            label.setVerticalAlignment(i2);
            Stroke stroke = new Stroke(3.0f, i, i, 3.0f);
            button2.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(0).shadowSpread(40).color(3947067).strokeColor(16777215).stroke(stroke));
            button2.getPressedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(255).shadowSpread(40).color(15818018).strokeColor(15818018).stroke(stroke));
            button2.getAllStyles().setMargin(0, 0, 2, 2);
            label.getAllStyles().setMargin(2, 2, 2, 2);
            button2.getAllStyles().setPadding(2, 2, 2, 2);
            button2.getAllStyles().setMarginUnit(1);
            button2.getAllStyles().setPaddingUnit(2);
            button2.setVerticalAlignment(4);
            button2.setFocusable(false);
            button2.addActionListener(new 24(b3GTextField2, str2, b3GTextField));
            arrayList.add(button2);
            i = 1;
            i2 = 4;
        }
        tableLayout.createConstraint().setWidthPercentage(25);
        Button button3 = new Button("");
        button3.addActionListener(new 25(b3GTextField, b3GTextField2));
        button3.setIcon(CihMBanking.theme.getImage("Delett.png"));
        button3.setUIID("VKBBtnNewVs");
        button3.setVerticalAlignment(4);
        button3.setAlignment(4);
        button3.setFocusable(false);
        new Stroke(3.0f, 1, 1, 3.0f);
        button3.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(0).shadowSpread(40).color(3947067).strokeColor(16777215));
        button3.getPressedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(255).shadowSpread(40).color(15818018).strokeColor(15818018));
        button3.getAllStyles().setMargin(1, 1, 2, 2);
        button3.getAllStyles().setPadding(1, 1, 2, 2);
        button3.getAllStyles().setMarginUnit(1);
        button3.getAllStyles().setPaddingUnit(1);
        button3.setVerticalAlignment(4);
        button3.setAlignment(4);
        button3.setFocusable(false);
        Button button4 = new Button("");
        button4.addActionListener(new 26(b3GTextField2, b3GTextField));
        button4.setIcon(CihMBanking.theme.getImage("NewDelete3.png"));
        button4.setUIID("VKBBtnNewVs");
        button4.setVerticalAlignment(4);
        button4.setAlignment(4);
        button4.setFocusable(false);
        new Stroke(3.0f, 1, 1, 3.0f);
        button4.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(0).shadowSpread(40).color(3947067).strokeColor(16777215));
        button4.getPressedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(255).shadowSpread(40).color(15818018).strokeColor(15818018));
        button4.getAllStyles().setMargin(1, 1, 2, 2);
        button4.getAllStyles().setPadding(1, 1, 2, 2);
        button4.getAllStyles().setMarginUnit(1);
        button4.getAllStyles().setPaddingUnit(1);
        button4.setVerticalAlignment(4);
        button4.setAlignment(4);
        button4.setFocusable(false);
        button4.setPreferredW(((Button) arrayList.get(2)).getPreferredW());
        button3.setPreferredW(((Button) arrayList.get(2)).getPreferredW());
        container.addComponent(button3);
        container.addComponent((Component) arrayList.get(0));
        container.addComponent((Component) arrayList.get(1));
        container.addComponent((Component) arrayList.get(2));
        container.addComponent(button4);
        return container;
    }

    class 24 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ B3GTextField val$PassField;
        final /* synthetic */ String val$number;

        24(B3GTextField b3GTextField, String str, B3GTextField b3GTextField2) {
            this.val$LoginField = b3GTextField;
            this.val$number = str;
            this.val$PassField = b3GTextField2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$LoginField.isIsTextFieldFocused()) {
                this.val$LoginField.getTextField().setText(this.val$LoginField.getTextField().getText() + this.val$number);
            } else if (this.val$PassField.isIsTextFieldFocused()) {
                this.val$PassField.getTextField().setConstraint(65536);
                this.val$PassField.getTextField().setText(this.val$PassField.getTextField().getText() + this.val$number);
            }
        }
    }

    class 25 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ B3GTextField val$PassField;

        25(B3GTextField b3GTextField, B3GTextField b3GTextField2) {
            this.val$PassField = b3GTextField;
            this.val$LoginField = b3GTextField2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$PassField.isIsTextFieldFocused()) {
                this.val$PassField.getTextField().setText(this.val$PassField.getTextField().getText().substring(0, this.val$PassField.getTextField().getText().length() - 1));
                actionEvent.consume();
            } else {
                if (!this.val$LoginField.isIsTextFieldFocused() || this.val$LoginField.getTextField().getText().length() <= 0) {
                    return;
                }
                this.val$LoginField.getTextField().setText(this.val$LoginField.getTextField().getText().substring(0, this.val$LoginField.getTextField().getText().length() - 1));
                actionEvent.consume();
            }
        }
    }

    class 26 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ B3GTextField val$PassField;

        26(B3GTextField b3GTextField, B3GTextField b3GTextField2) {
            this.val$LoginField = b3GTextField;
            this.val$PassField = b3GTextField2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$LoginField.getTextField().setText("");
            this.val$PassField.getTextField().setText("");
        }
    }

    private ArrayList genrateRandomListInt() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i <= 9; i++) {
            arrayList2.add(String.valueOf(i));
        }
        Collections.shuffle(arrayList2);
        if (Display.getInstance().getDisplayHeight() < 1334) {
            arrayList.add(arrayList2.subList(0, 5));
            arrayList.add(arrayList2.subList(5, 10));
        } else {
            arrayList.add(arrayList2.subList(0, 4));
            arrayList.add(arrayList2.subList(4, 8));
            arrayList.add(arrayList2.subList(8, 10));
        }
        return arrayList;
    }

    public Container ConfirmPasswordRecup() {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("margin_2_2_2_2");
        Container container2 = new Container();
        Label label = new Label("Récupération de mot de passe");
        label.setUIID("Lbl_exo_semiBold_18");
        container2.add(label);
        container2.setUIID("margin_2_2_0_0");
        container.add(container2);
        Container container3 = new Container(new BoxLayout(2));
        Label label2 = new Label();
        label2.setIcon(CihMBanking.theme.getImage("MotdePasse.png"));
        label2.setUIID("dg_splblMsgCenter");
        container3.add(label2);
        container3.setUIID("margin_2_2_0_0");
        container.add(container3);
        Container container4 = new Container(new BoxLayout(2));
        SpanLabel spanLabel = new SpanLabel("Votre mot de passe a été modifié avec succès");
        spanLabel.setTextUIID("Lbl_lato_bold_13");
        container4.add(spanLabel);
        container4.setUIID("margin_2_2_0_0");
        container3.setUIID("margin_2_2_0_0");
        container.add(container4);
        Container container5 = new Container(new BoxLayout(2));
        Button button = new Button("Continuer");
        button.addActionListener(new 27());
        button.setUIID("sendBtnNewOffLine");
        container5.add(button);
        container.add(container5);
        return container;
    }

    class 27 implements ActionListener {
        27() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ForgotPasswdPage.this.uiManager.NavigateToPageByIdOffligne(190);
        }
    }

    private ServiceResponse ForgotPasswordstep1(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("PHONE_NUMBER", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(10711);
        serviceRequest.setSessionId(str);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private ServiceResponse ForgotPasswordstep2(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", this.clientId.substring(0, 7));
        this.Answer = str;
        hashtable.put("ANSWER", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(10712);
        serviceRequest.setSessionId(this.clientId.substring(0, 7));
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private ServiceResponse ForgotPasswordstep3(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", this.clientId.substring(0, 7));
        hashtable.put("ANSWER", str);
        hashtable.put("OTP", "1111");
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(10713);
        serviceRequest.setSessionId(this.clientId.substring(0, 7));
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private ServiceResponse ForgotPasswordstep4(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", this.clientId.substring(0, 7));
        hashtable.put("Password", str);
        hashtable.put("ConfirmPassword", str);
        hashtable.put("OTP", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(10714);
        serviceRequest.setSessionId(this.clientId.substring(0, 7));
        serviceRequest.setIsDemo(0);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public void Previous() {
        if (this.VirementWizard.currentStepId == 0) {
            this.uiManager.GoBackOffligne();
        }
        if (this.VirementWizard.currentStepId == 1) {
            this.VirementWizard.goToPreviousStep(this.s2, "", 16777215, 16777215);
        } else if (this.VirementWizard.currentStepId == 2) {
            this.VirementWizard.goToPreviousStep(this.s3, "", 16777215, 16777215);
        } else if (this.VirementWizard.currentStepId == 3) {
            this.VirementWizard.goToPreviousStep(this.s4, "", 16777215, 16777215);
        }
    }
}
