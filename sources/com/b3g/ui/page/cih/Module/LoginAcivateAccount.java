package com.b3g.ui.page.cih.Module;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GTextField;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizard1;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.Step;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
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
import java.util.Map;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class LoginAcivateAccount extends Container {
    static int counter;
    TextField PassTxt;
    B3GWizard1 VirementWizard;
    String clientId;
    int cntActivateAccountStp1Height;
    int cntActivateAccountStp2Height;
    int cntActivateAccountStp4Height;
    String phoneNumber;
    Step s1;
    Step s2;
    Step s3;
    Step s4;
    B3GUiManager uiManager;
    UIBuilder uib;

    static /* synthetic */ String access$000(LoginAcivateAccount loginAcivateAccount, String str) {
        return loginAcivateAccount.phoneNumberFormatted(str);
    }

    public LoginAcivateAccount(B3GUiManager b3GUiManager) {
        CihMBanking.exitApplication = false;
        this.uiManager = b3GUiManager;
        setLayout(BoxLayout.y());
        this.uib = new UIBuilder();
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
        this.s2.stepCnt = LoginActivateAccountStep2();
        this.cntActivateAccountStp2Height = this.s2.stepCnt.getHeight();
        Step step3 = new Step();
        this.s3 = step3;
        step3.icon = "3inactif1.png";
        this.s3.selectedIcon = "3actif1.png";
        this.s3.stepCnt = LoginActivateAccountStep3();
        Step step4 = new Step();
        this.s4 = step4;
        step4.icon = "4inactif1.png";
        this.s4.selectedIcon = "4actif1.png";
        this.s4.stepCnt = LoginActivateAccountStep4();
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
        setScrollableY(false);
        addComponent(drawWizard);
    }

    private Container LoginActivateAccountStep1() {
        Container createContainer = this.uib.createContainer("/cihv3", "LoginActivateAccount");
        Button button = (Button) this.uib.findByName("suivantBtn", createContainer);
        TextField textField = (TextField) this.uib.findByName("accountNum", createContainer);
        TextField textField2 = (TextField) this.uib.findByName("phoneNum", createContainer);
        textField.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        textField2.getHintLabel().setUIID("Lbl_lato_Meduim_18");
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
        createContainer.getStyle().setBgTransparency(0);
        System.err.println("LoginActivateAccount Scrollable : " + createContainer.isScrollVisible());
        textField.addPointerReleasedListener(new 1(createContainer));
        textField.addActionListener(new 2(createContainer));
        textField2.addPointerReleasedListener(new 3(createContainer));
        textField2.addActionListener(new 4(createContainer));
        button.addActionListener(new 5(textField, textField2));
        createContainer.setScrollableY(true);
        createContainer.setScrollVisible(false);
        return createContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$LoginActivateAccountStp1;

        1(Container container) {
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

    class 2 implements ActionListener {
        final /* synthetic */ Container val$LoginActivateAccountStp1;

        2(Container container) {
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

    class 3 implements ActionListener {
        final /* synthetic */ Container val$LoginActivateAccountStp1;

        3(Container container) {
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

    class 4 implements ActionListener {
        final /* synthetic */ Container val$LoginActivateAccountStp1;

        4(Container container) {
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

    class 5 implements ActionListener {
        final /* synthetic */ TextField val$accountNum;
        final /* synthetic */ TextField val$phoneNum;

        5(TextField textField, TextField textField2) {
            this.val$accountNum = textField;
            this.val$phoneNum = textField2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PopUpsManager popUpsManager = new PopUpsManager();
            LoginAcivateAccount.this.clientId = this.val$accountNum.getText();
            LoginAcivateAccount.this.phoneNumber = this.val$phoneNum.getText();
            if ((LoginAcivateAccount.this.clientId.length() == 16 || LoginAcivateAccount.this.clientId.length() == 7) && LoginAcivateAccount.this.phoneNumber.length() >= 10) {
                if (DataTools.checkConnection()) {
                    LoginAcivateAccount loginAcivateAccount = LoginAcivateAccount.this;
                    String substring = loginAcivateAccount.clientId.substring(0, 7);
                    LoginAcivateAccount loginAcivateAccount2 = LoginAcivateAccount.this;
                    ServiceResponse sendClientIdPhoneProcess = loginAcivateAccount.sendClientIdPhoneProcess(substring, LoginAcivateAccount.access$000(loginAcivateAccount2, loginAcivateAccount2.phoneNumber));
                    if (sendClientIdPhoneProcess.getStatusCode().equals("000")) {
                        LoginAcivateAccount.this.VirementWizard.goToNextStep(LoginAcivateAccount.this.s1, "", 16777215, 16777215);
                        return;
                    } else {
                        LoginAcivateAccount.this.uiManager.ShowMessageTransaction(LoginAcivateAccount.this.uiManager.stateMachine, DataTools.FormatUnicode(sendClientIdPhoneProcess.getStatusLabel()), null);
                        return;
                    }
                }
                popUpsManager.showNoConnectoinMessg();
                return;
            }
            LoginAcivateAccount.this.uiManager.ShowMessageTransaction(LoginAcivateAccount.this.uiManager.stateMachine, DataTools.FormatUnicode("veuillez remplir tous les champs requis."), null);
        }
    }

    private Container LoginActivateAccountStep2() {
        Container createContainer = this.uib.createContainer("/cihv3", "LoginActivationAccountStep2");
        createContainer.getAllStyles().setPaddingUnit(1);
        createContainer.getAllStyles().setMargin(0, 2, 0, 0);
        TextField textField = (TextField) this.uib.findByName("codeAccesTxtF", createContainer);
        textField.setCursorBlinkTimeOff(999999);
        SessionParameter.setOtpTextField(textField);
        textField.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        textField.setUIID("LoginTextF_Blan");
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
        textField.addPointerReleasedListener(new 6(createContainer));
        textField.addActionListener(new 7(createContainer));
        button2.addActionListener(new 8(button2, textField, createContainer));
        b3GTextField.getTextField().addDataChangedListener(new 9(b3GTextField, createContainer));
        ((Button) this.uib.findByName("ivrBtn", createContainer)).addActionListener(new 10());
        ((Button) this.uib.findByName("suivantBtn", createContainer)).addActionListener(new 11(textField));
        b3GTextField.setIsTextFieldFocused(false);
        createContainer.revalidate();
        return createContainer;
    }

    class 6 implements ActionListener {
        final /* synthetic */ Container val$LoginActivationAccountStep2;

        6(Container container) {
            this.val$LoginActivationAccountStep2 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                return;
            }
            this.val$LoginActivationAccountStep2.setScrollableY(false);
        }
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
            this.val$LoginActivationAccountStep2.setScrollableY(true);
            this.val$LoginActivationAccountStep2.setScrollVisible(false);
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ Container val$LoginActivationAccountStep2;
        final /* synthetic */ TextField val$codeAccesTxtF;
        final /* synthetic */ Button val$fakeBtn;

        8(Button button, TextField textField, Container container) {
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

    class 9 implements DataChangedListener {
        final /* synthetic */ Container val$LoginActivationAccountStep2;
        final /* synthetic */ B3GTextField val$otpB3gTexF;

        9(B3GTextField b3GTextField, Container container) {
            this.val$otpB3gTexF = b3GTextField;
            this.val$LoginActivationAccountStep2 = container;
        }

        public void dataChanged(int i, int i2) {
            if (this.val$otpB3gTexF.getTextField().getText().length() > 0) {
                this.val$LoginActivationAccountStep2.revalidate();
            } else {
                this.val$LoginActivationAccountStep2.revalidate();
            }
        }
    }

    class 10 implements ActionListener {
        10() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String substring = LoginAcivateAccount.this.clientId.substring(0, 7);
            LoginAcivateAccount loginAcivateAccount = LoginAcivateAccount.this;
            ServiceResponse sendOtpProcess = ServiceManager.sendOtpProcess(substring, LoginAcivateAccount.access$000(loginAcivateAccount, loginAcivateAccount.phoneNumber), "1023", 0, "1", "1", "", "", "", "");
            if (sendOtpProcess.getStatusCode().equals("000")) {
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(LoginAcivateAccount.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcess.getStatusLabel()), null);
        }
    }

    class 11 implements ActionListener {
        final /* synthetic */ TextField val$codeAccesTxtF;

        11(TextField textField) {
            this.val$codeAccesTxtF = textField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$codeAccesTxtF.getText().length() != 0) {
                if (LoginAcivateAccount.counter < 3) {
                    if (LoginAcivateAccount.this.sheckOtp(this.val$codeAccesTxtF.getText(), LoginAcivateAccount.this.clientId.substring(0, 7)).getStatusCode().equals("000")) {
                        LoginAcivateAccount.this.VirementWizard.goToNextStep(LoginAcivateAccount.this.s2, "", 16777215, 16777215);
                        return;
                    } else {
                        LoginAcivateAccount.this.uiManager.ShowMessageTransaction(LoginAcivateAccount.this.uiManager.stateMachine, "Code confidentiel incorrect", null);
                        LoginAcivateAccount.counter++;
                        return;
                    }
                }
                LoginAcivateAccount.counter = 0;
                LoginAcivateAccount.this.uiManager.stateMachine.showForm("LoginV2New", (Command) null);
                return;
            }
            LoginAcivateAccount.this.uiManager.ShowMessageTransaction(LoginAcivateAccount.this.uiManager.stateMachine, DataTools.FormatUnicode("Veuillez saisir le code reçu par SMS"), null);
        }
    }

    private Container LoginActivateAccountStep3() {
        Container createContainer = this.uib.createContainer("/cihv3", "LoginActivateAccountStep3");
        TextField textField = (TextField) this.uib.findByName("PassTxt", createContainer);
        this.PassTxt = textField;
        textField.setCursorBlinkTimeOff(999999);
        this.PassTxt.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        TextField textField2 = (TextField) this.uib.findByName("newPassTxt", createContainer);
        textField2.setCursorBlinkTimeOff(999999);
        textField2.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        textField2.setUIID("LoginTextF_Blan");
        this.PassTxt.setUIID("LoginTextF_Blan");
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
        DrawLoginKeyBoard((Container) this.uib.findByName("keybordCnt", createContainer), b3GTextField, b3GTextField2, button, button2);
        Button button3 = (Button) this.uib.findByName("fakeBtn", createContainer);
        Button button4 = (Button) this.uib.findByName("fakeBtn1", createContainer);
        createContainer.getStyle().setBgTransparency(0);
        createContainer.setScrollVisible(false);
        button3.addActionListener(new 12(button3, button4, textField2, button2, createContainer));
        button4.addActionListener(new 13(button3, button4, textField2, button, createContainer));
        b3GTextField.getTextField().addDataChangedListener(new 14(b3GTextField, button, createContainer));
        b3GTextField2.getTextField().addDataChangedListener(new 15(b3GTextField2, button2, createContainer));
        ((Button) this.uib.findByName("suivantBtn", createContainer)).addActionListener(new 16(textField2, button3, button4, b3GTextField));
        b3GTextField2.setIsTextFieldFocused(false);
        b3GTextField.setIsTextFieldFocused(false);
        return createContainer;
    }

    class 12 implements ActionListener {
        final /* synthetic */ Container val$LoginActivateAccountStep3;
        final /* synthetic */ Button val$delnewPassBtn;
        final /* synthetic */ Button val$fakeBtn;
        final /* synthetic */ Button val$fakeBtn1;
        final /* synthetic */ TextField val$newPassTxt;

        12(Button button, Button button2, TextField textField, Button button3, Container container) {
            this.val$fakeBtn = button;
            this.val$fakeBtn1 = button2;
            this.val$newPassTxt = textField;
            this.val$delnewPassBtn = button3;
            this.val$LoginActivateAccountStep3 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$fakeBtn.setIcon(CihMBanking.theme.getImage("PasswordFocus.png"));
            this.val$fakeBtn1.setIcon(CihMBanking.theme.getImage("PasswordV1.png"));
            LoginAcivateAccount.this.PassTxt.setHint("");
            this.val$newPassTxt.setHint("Confirmer le mot de passe");
            this.val$delnewPassBtn.setVisible(false);
            this.val$LoginActivateAccountStep3.forceRevalidate();
        }
    }

    class 13 implements ActionListener {
        final /* synthetic */ Container val$LoginActivateAccountStep3;
        final /* synthetic */ Button val$delPassBtn;
        final /* synthetic */ Button val$fakeBtn;
        final /* synthetic */ Button val$fakeBtn1;
        final /* synthetic */ TextField val$newPassTxt;

        13(Button button, Button button2, TextField textField, Button button3, Container container) {
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
            LoginAcivateAccount.this.PassTxt.setHint("Mot de passe");
            this.val$delPassBtn.setVisible(false);
            this.val$LoginActivateAccountStep3.forceRevalidate();
        }
    }

    class 14 implements DataChangedListener {
        final /* synthetic */ Container val$LoginActivateAccountStep3;
        final /* synthetic */ Button val$delPassBtn;
        final /* synthetic */ B3GTextField val$passTxt;

        14(B3GTextField b3GTextField, Button button, Container container) {
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

    class 15 implements DataChangedListener {
        final /* synthetic */ Container val$LoginActivateAccountStep3;
        final /* synthetic */ Button val$delnewPassBtn;
        final /* synthetic */ B3GTextField val$newPassTxtField;

        15(B3GTextField b3GTextField, Button button, Container container) {
            this.val$newPassTxtField = b3GTextField;
            this.val$delnewPassBtn = button;
            this.val$LoginActivateAccountStep3 = container;
        }

        public void dataChanged(int i, int i2) {
            if (this.val$newPassTxtField.getTextField().getText().length() > 0) {
                this.val$delnewPassBtn.setVisible(true);
                this.val$LoginActivateAccountStep3.forceRevalidate();
            } else {
                this.val$delnewPassBtn.setVisible(false);
                this.val$LoginActivateAccountStep3.forceRevalidate();
            }
        }
    }

    class 16 implements ActionListener {
        final /* synthetic */ Button val$fakeBtn;
        final /* synthetic */ Button val$fakeBtn1;
        final /* synthetic */ TextField val$newPassTxt;
        final /* synthetic */ B3GTextField val$passTxt;

        16(TextField textField, Button button, Button button2, B3GTextField b3GTextField) {
            this.val$newPassTxt = textField;
            this.val$fakeBtn = button;
            this.val$fakeBtn1 = button2;
            this.val$passTxt = b3GTextField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!LoginAcivateAccount.this.PassTxt.getText().equals("") && !this.val$newPassTxt.getText().equals("")) {
                if (LoginAcivateAccount.this.PassTxt.getText().length() < 17 && LoginAcivateAccount.this.PassTxt.getText().length() > 5) {
                    if (LoginAcivateAccount.this.PassTxt.getText().equals(this.val$newPassTxt.getText())) {
                        LoginAcivateAccount loginAcivateAccount = LoginAcivateAccount.this;
                        ServiceResponse checkPasswordComplexityProcess = loginAcivateAccount.checkPasswordComplexityProcess(loginAcivateAccount.clientId.substring(0, 7), this.val$newPassTxt.getText());
                        if (checkPasswordComplexityProcess.getStatusCode().equals("000")) {
                            LoginAcivateAccount.this.VirementWizard.goToNextStep(LoginAcivateAccount.this.s3, "", 16777215, 16777215);
                            return;
                        }
                        LoginAcivateAccount.this.uiManager.ShowMessageTransaction(LoginAcivateAccount.this.uiManager.stateMachine, checkPasswordComplexityProcess.getStatusLabel(), null);
                        LoginAcivateAccount.this.PassTxt.setText("");
                        this.val$newPassTxt.setText("");
                        this.val$fakeBtn.setIcon(CihMBanking.theme.getImage("PasswordFocus.png"));
                        this.val$fakeBtn1.setIcon(CihMBanking.theme.getImage("Password.png"));
                        LoginAcivateAccount.this.PassTxt.setHint("Mot de passe");
                        this.val$newPassTxt.setHint("Confirmer le mot de passe");
                        this.val$passTxt.setIsTextFieldFocused(true);
                        return;
                    }
                    LoginAcivateAccount.this.uiManager.ShowMessageTransaction(LoginAcivateAccount.this.uiManager.stateMachine, DataTools.FormatUnicode("Les mots de passe ne sont pas identiques"), null);
                    return;
                }
                LoginAcivateAccount.this.uiManager.ShowMessageTransaction(LoginAcivateAccount.this.uiManager.stateMachine, DataTools.FormatUnicode("Le mot de passe doit avoir une longueur minimale de 6 chiffres et une longueur maximale de 16 chiffres"), null);
                return;
            }
            LoginAcivateAccount.this.uiManager.ShowMessageTransaction(LoginAcivateAccount.this.uiManager.stateMachine, DataTools.FormatUnicode("Veuillez remplir tous les champs requis."), null);
        }
    }

    private Container LoginActivateAccountStep4() {
        Container createContainer = this.uib.createContainer("/cihv3", "LoginActivationAccountStep4");
        ComboBox comboBox = (ComboBox) this.uib.findByName("CbxQuestion", createContainer);
        TextField textField = (TextField) this.uib.findByName("responsetxtF", createContainer);
        textField.setUIID("Lbl_lato_Meduim_18");
        textField.setUIID("LoginTextF_Blan");
        if (CihMBanking.RTL) {
            textField.setRTL(true);
        } else {
            textField.setRTL(false);
        }
        Button button = (Button) this.uib.findByName("validerBtn", createContainer);
        createContainer.getStyle().setBgTransparency(0);
        createContainer.setScrollVisible(false);
        textField.addPointerReleasedListener(new 17(createContainer));
        textField.addActionListener(new 18(createContainer));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Lieu de naissance de ma mère", "32");
        linkedHashMap.put("Meilleur ami d’enfance", "33");
        linkedHashMap.put("Professeur préféré", "34");
        linkedHashMap.put("Personnage Historique préféré", "35");
        linkedHashMap.put("Sport préféré", "36");
        linkedHashMap.put("Livre préféré", "37");
        ((DefaultListModel) comboBox.getModel()).removeAll();
        for (int i = 0; i < linkedHashMap.size(); i++) {
            comboBox.addItem(linkedHashMap.keySet().toArray()[i]);
        }
        button.addActionListener(new 19(textField, linkedHashMap, comboBox));
        return createContainer;
    }

    class 17 implements ActionListener {
        final /* synthetic */ Container val$LoginActivationAccountStep4;

        17(Container container) {
            this.val$LoginActivationAccountStep4 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                return;
            }
            this.val$LoginActivationAccountStep4.setScrollableY(false);
        }
    }

    class 18 implements ActionListener {
        final /* synthetic */ Container val$LoginActivationAccountStep4;

        18(Container container) {
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

    class 19 implements ActionListener {
        final /* synthetic */ ComboBox val$CbxQuestion;
        final /* synthetic */ Map val$questionMap;
        final /* synthetic */ TextField val$responsetxtF;

        19(TextField textField, Map map, ComboBox comboBox) {
            this.val$responsetxtF = textField;
            this.val$questionMap = map;
            this.val$CbxQuestion = comboBox;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!this.val$responsetxtF.getText().equals("")) {
                LoginAcivateAccount loginAcivateAccount = LoginAcivateAccount.this;
                ServiceResponse sendNewData = loginAcivateAccount.sendNewData(loginAcivateAccount.clientId.substring(0, 7), LoginAcivateAccount.this.PassTxt.getText(), this.val$questionMap.get(this.val$CbxQuestion.getSelectedItem()).toString(), this.val$responsetxtF.getText());
                if (sendNewData.getStatusCode().equals("000")) {
                    LoginAcivateAccount.this.uiManager.ShowMessageTransaction(LoginAcivateAccount.this.uiManager.stateMachine, DataTools.FormatUnicode(sendNewData.getStatusLabel()), null);
                    Storage.getInstance().deleteStorageFile("clientId");
                    new CihMBanking().showSplashScreen();
                    return;
                } else {
                    LoginAcivateAccount.this.uiManager.ShowMessageTransaction(LoginAcivateAccount.this.uiManager.stateMachine, DataTools.FormatUnicode(sendNewData.getStatusLabel()), null);
                    new CihMBanking().showSplashScreen();
                    return;
                }
            }
            LoginAcivateAccount.this.uiManager.ShowMessageTransaction(LoginAcivateAccount.this.uiManager.stateMachine, DataTools.FormatUnicode("veuillez remplir tous les champs requis."), null);
        }
    }

    public ServiceResponse sendClientIdPhoneProcess(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("PHONE_NUMBER", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setSessionId(str);
        serviceRequest.setServiceId(900093);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse sheckOtp(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str2);
        hashtable.put("CODE_OTP", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900102);
        serviceRequest.setSessionId(str2);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse sendNewData(String str, String str2, String str3, String str4) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("PASSWORD", str2);
        hashtable.put("SECRET_QUESTION", str3);
        hashtable.put("SECRET_RESPONSE", str4);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900094);
        serviceRequest.setSessionId(str);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private String phoneNumberFormatted(String str) {
        if (str.substring(0, 2).equals("06")) {
            return "212" + str.substring(1);
        }
        return str.substring(0, 2).equals("07") ? "212" + str.substring(1) : str;
    }

    public void DrawLoginKeyBoard(Container container, B3GTextField b3GTextField, Button button) {
        ArrayList GetRandomIntArray = DataTools.GetRandomIntArray(10);
        container.setLayout(new BoxLayout(2));
        container.removeAll();
        Container DrawKeyBoardRow = DrawKeyBoardRow(b3GTextField, GetRandomIntArray, 0, 0, 1, 5, button);
        Container DrawKeyBoardRow2 = DrawKeyBoardRow(b3GTextField, GetRandomIntArray, 5, 1, 1, 5, button);
        container.addComponent(DrawKeyBoardRow);
        container.revalidate();
        container.addComponent(DrawKeyBoardRow2);
        container.revalidate();
    }

    public Container DrawKeyBoardRow(B3GTextField b3GTextField, Object obj, int i, int i2, int i3, int i4, Button button) {
        ArrayList arrayList = (ArrayList) obj;
        Container container = new Container();
        TableLayout tableLayout = new TableLayout(i3, i4);
        container.setLayout(tableLayout);
        container.removeAll();
        container.setUIID("Container");
        for (int i5 = i; i5 <= i + 4; i5++) {
            String str = "" + arrayList.get(i5);
            Button button2 = new Button("" + arrayList.get(i5));
            button2.setPreferredH(Display.getInstance().getDisplayWidth() / 7);
            button2.setUIID("VKBBtn");
            button2.setVerticalAlignment(4);
            button2.setFocusable(false);
            button2.addActionListener(new 20(b3GTextField, str));
            TableLayout.Constraint createConstraint = tableLayout.createConstraint();
            createConstraint.setWidthPercentage(20);
            container.addComponent(createConstraint, button2);
        }
        button.setFocusable(false);
        button.addActionListener(new 21(b3GTextField));
        return container;
    }

    class 20 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ String val$number;

        20(B3GTextField b3GTextField, String str) {
            this.val$LoginField = b3GTextField;
            this.val$number = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$LoginField.isIsTextFieldFocused()) {
                this.val$LoginField.getTextField().setConstraint(2);
                this.val$LoginField.getTextField().setText(this.val$LoginField.getTextField().getText() + this.val$number);
                actionEvent.consume();
            }
        }
    }

    class 21 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;

        21(B3GTextField b3GTextField) {
            this.val$LoginField = b3GTextField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!this.val$LoginField.isIsTextFieldFocused() || this.val$LoginField.getTextField().getText().length() <= 0) {
                return;
            }
            this.val$LoginField.getTextField().setText(this.val$LoginField.getTextField().getText().substring(0, this.val$LoginField.getTextField().getText().length() - 1));
            actionEvent.consume();
        }
    }

    public void DrawLoginKeyBoard(Container container, B3GTextField b3GTextField, B3GTextField b3GTextField2, Button button, Button button2) {
        Container container2 = new Container(BoxLayout.y());
        ArrayList genrateRandomListInt = genrateRandomListInt();
        container2.addComponent(DrawKeyBoardRow(b3GTextField2, b3GTextField, (List) genrateRandomListInt.get(0), button, button2));
        container2.addComponent(DrawKeyBoardRow(b3GTextField2, b3GTextField, (List) genrateRandomListInt.get(1), button, button2));
        container2.addComponent(DrawFinalKeyBoardRow(b3GTextField2, b3GTextField, (List) genrateRandomListInt.get(2), button, button2));
        container.add(container2);
    }

    public Container DrawKeyBoardRow(B3GTextField b3GTextField, B3GTextField b3GTextField2, List list, Button button, Button button2) {
        Container container = new Container();
        int i = 1;
        new GridLayout(1, list.size());
        int i2 = 4;
        container.setLayout(new FlowLayout(4, 4));
        container.removeAll();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            String str2 = "" + str;
            Label label = new Label(str);
            Button button3 = new Button(label.getText());
            button3.setUIID("VKBBtnNewVs");
            button3.setVerticalAlignment(i2);
            button3.setAlignment(i2);
            button3.setFocusable(false);
            label.setVerticalAlignment(i2);
            Stroke stroke = new Stroke(3.0f, i, i, 3.0f);
            button3.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(0).shadowSpread(40).color(3947067).strokeColor(16777215).stroke(stroke));
            button3.getPressedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(255).shadowSpread(40).color(15818018).strokeColor(15818018).stroke(stroke));
            button3.getAllStyles().setMargin(0, 0, 2, 2);
            label.getAllStyles().setMargin(2, 2, 2, 2);
            button3.getAllStyles().setPadding(2, 2, 2, 2);
            button3.getAllStyles().setMarginUnit(1);
            button3.getAllStyles().setPaddingUnit(2);
            i2 = 4;
            button3.setVerticalAlignment(4);
            button3.setFocusable(false);
            button3.addActionListener(new 22(b3GTextField2, str2, b3GTextField));
            container.addComponent(button3);
            i = 1;
        }
        button2.setFocusable(false);
        button2.addActionListener(new 23(b3GTextField));
        button.setFocusable(false);
        button.addActionListener(new 24(b3GTextField2));
        return container;
    }

    class 22 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ B3GTextField val$PassField;
        final /* synthetic */ String val$number;

        22(B3GTextField b3GTextField, String str, B3GTextField b3GTextField2) {
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

    class 23 implements ActionListener {
        final /* synthetic */ B3GTextField val$PassField;

        23(B3GTextField b3GTextField) {
            this.val$PassField = b3GTextField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$PassField.getTextField().getText().length() > 0) {
                this.val$PassField.getTextField().setText(this.val$PassField.getTextField().getText().substring(0, this.val$PassField.getTextField().getText().length() - 1));
                actionEvent.consume();
            }
        }
    }

    class 24 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;

        24(B3GTextField b3GTextField) {
            this.val$LoginField = b3GTextField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$LoginField.getTextField().getText().length() > 0) {
                this.val$LoginField.getTextField().setText(this.val$LoginField.getTextField().getText().substring(0, this.val$LoginField.getTextField().getText().length() - 1));
                actionEvent.consume();
            }
        }
    }

    public Container DrawFinalKeyBoardRow(B3GTextField b3GTextField, B3GTextField b3GTextField2, List list, Button button, Button button2) {
        ArrayList arrayList = new ArrayList();
        Button button3 = new Button();
        button3.setUIID("EmptyContainer");
        Container container = new Container();
        int i = 1;
        TableLayout tableLayout = new TableLayout(1, 3);
        tableLayout.setGrowHorizontally(true);
        int i2 = 4;
        container.setLayout(new FlowLayout(4, 4));
        container.removeAll();
        arrayList.add(button3);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            String str2 = "" + str;
            Label label = new Label(str);
            Button button4 = new Button(label.getText());
            button4.setUIID("VKBBtnNewVs");
            button4.setVerticalAlignment(i2);
            button4.setAlignment(i2);
            button4.setFocusable(false);
            label.setVerticalAlignment(i2);
            Stroke stroke = new Stroke(3.0f, i, i, 3.0f);
            button4.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(0).shadowSpread(40).color(3947067).strokeColor(16777215).stroke(stroke));
            button4.getPressedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(255).shadowSpread(40).color(15818018).strokeColor(15818018).stroke(stroke));
            button4.getAllStyles().setMargin(0, 0, 2, 2);
            label.getAllStyles().setMargin(2, 2, 2, 2);
            button4.getAllStyles().setPadding(2, 2, 2, 2);
            button4.getAllStyles().setMarginUnit(1);
            button4.getAllStyles().setPaddingUnit(2);
            button4.setVerticalAlignment(4);
            button4.setFocusable(false);
            button4.addActionListener(new 25(b3GTextField2, str2, b3GTextField));
            arrayList.add(button4);
            i = 1;
            i2 = 4;
        }
        tableLayout.createConstraint().setWidthPercentage(25);
        Button button5 = new Button("");
        button5.addActionListener(new 26(b3GTextField, b3GTextField2));
        button5.setIcon(CihMBanking.theme.getImage("Delett.png"));
        button5.setUIID("VKBBtnNewVs");
        button5.setVerticalAlignment(4);
        button5.setAlignment(4);
        button5.setFocusable(false);
        new Stroke(3.0f, 1, 1, 3.0f);
        button5.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(0).shadowSpread(40).color(3947067).strokeColor(16777215));
        button5.getPressedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(255).shadowSpread(40).color(15818018).strokeColor(15818018));
        button5.getAllStyles().setMargin(1, 1, 2, 2);
        button5.getAllStyles().setPadding(1.0f, 1.0f, 2.0f, 2.0f);
        button5.getAllStyles().setMarginUnit(1);
        button5.getAllStyles().setPaddingUnit(1);
        button5.setVerticalAlignment(4);
        button5.setAlignment(4);
        button5.setFocusable(false);
        Button button6 = new Button("");
        button6.addActionListener(new 27(b3GTextField2, b3GTextField));
        button6.setIcon(CihMBanking.theme.getImage("NewDelete3.png"));
        button6.setUIID("VKBBtnNewVs");
        button6.setVerticalAlignment(4);
        button6.setAlignment(4);
        button6.setFocusable(false);
        new Stroke(3.0f, 1, 1, 3.0f);
        button6.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(0).shadowSpread(40).color(3947067).strokeColor(16777215));
        button6.getPressedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(255).shadowSpread(40).color(15818018).strokeColor(15818018));
        button6.getAllStyles().setMargin(1, 1, 2, 2);
        button6.getAllStyles().setPadding(1, 1, 2, 2);
        button6.getAllStyles().setMarginUnit(1);
        button6.getAllStyles().setPaddingUnit(1);
        button6.setVerticalAlignment(4);
        button6.setAlignment(4);
        button6.setFocusable(false);
        button6.setPreferredW(((Button) arrayList.get(2)).getPreferredW());
        button5.setPreferredW(((Button) arrayList.get(2)).getPreferredW());
        container.addComponent(button5);
        container.addComponent((Component) arrayList.get(0));
        container.addComponent((Component) arrayList.get(1));
        container.addComponent((Component) arrayList.get(2));
        container.addComponent(button6);
        return container;
    }

    class 25 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ B3GTextField val$PassField;
        final /* synthetic */ String val$number;

        25(B3GTextField b3GTextField, String str, B3GTextField b3GTextField2) {
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

    class 26 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ B3GTextField val$PassField;

        26(B3GTextField b3GTextField, B3GTextField b3GTextField2) {
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

    class 27 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ B3GTextField val$PassField;

        27(B3GTextField b3GTextField, B3GTextField b3GTextField2) {
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
        arrayList.add(arrayList2.subList(0, 4));
        arrayList.add(arrayList2.subList(4, 8));
        arrayList.add(arrayList2.subList(8, 10));
        return arrayList;
    }

    public ServiceResponse checkPasswordComplexityProcess(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("NEW_PASSWORD", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setSessionId(str);
        serviceRequest.setServiceId(59);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public void showKeyboard(boolean z) {
        Component focused;
        Form current = Display.getInstance().getCurrent();
        if (current == null || (focused = current.getFocused()) == null || !(focused instanceof TextArea)) {
            return;
        }
        TextArea textArea = (TextArea) focused;
        if (z) {
            Display.getInstance().editString(textArea, textArea.getMaxSize(), textArea.getConstraint(), textArea.getText(), 0);
        }
    }
}
