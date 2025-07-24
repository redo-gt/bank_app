package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.BlankService;
import com.b3g.services.CheckPassword;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GTextField;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizard;
import com.b3g.ui.B3gLoginTextBox;
import com.b3g.ui.Step;
import com.b3g.ui.page.cih.Module.AccountLastConnection;
import com.codename1.components.SpanLabel;
import com.codename1.fingerprint.Fingerprint;
import com.codename1.io.Storage;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ChangePasswordPage extends B3GPage {
    static int counter;
    B3GWizard ChangePasswordWizard;
    Container ChangePaswordStep1;
    Container ChangePaswordStep2;
    public Container LogingArea;
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    public Button connetBtn;
    Button delOldPassBtn;
    Button delPassBtn;
    Button delnewPassBtn;
    B3GTextField newPassTxt;
    public Button nextBtn;
    public B3GTextField passTxt;
    Step s1;
    Step s2;
    Step s3;
    public Button validBtn;
    public final B3gLoginTextBox userNameCnt = new B3gLoginTextBox(null);
    final B3gLoginTextBox passCnt = new B3gLoginTextBox("Password");
    int UserNameTextBoxIndex = 0;
    int PasswordTextBoxIndex = 0;

    static /* synthetic */ String access$000(ChangePasswordPage changePasswordPage) {
        return changePasswordPage.VnewaliasText;
    }

    static /* synthetic */ String access$002(ChangePasswordPage changePasswordPage, String str) {
        changePasswordPage.VnewaliasText = str;
        return str;
    }

    public ChangePasswordPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        Container container = new Container(new BoxLayout(2));
        container.setUIID("");
        container.setScrollVisible(false);
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            this.thisContainer = new Container(BoxLayout.y());
            Label label = new Label("Changement mot de passe");
            label.setUIID("gb_lblGlobalHeaderTitleV2");
            label.setTextPosition(1);
            label.setVerticalAlignment(4);
            label.setScrollVisible(false);
            label.setEnabled(false);
            this.thisContainer.addComponent(label);
            SpanLabel spanLabel = new SpanLabel("Ce service n'est pas disponible en mode démonstration.");
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container.addComponent(spanLabel);
            this.thisContainer.add(container);
        } else {
            Step step = new Step();
            this.s1 = step;
            step.icon = "1inactif.png";
            this.s1.selectedIcon = "1actif.png";
            this.s1.stepCnt = managePasswordsCnt();
            Step step2 = new Step();
            this.s2 = step2;
            step2.icon = "2inactif.png";
            this.s2.selectedIcon = "2actif.png";
            this.s2.stepCnt = changePasswordCnt();
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.s1);
            arrayList.add(this.s2);
            B3GWizard b3GWizard = new B3GWizard(arrayList);
            this.ChangePasswordWizard = b3GWizard;
            this.thisContainer = b3GWizard.drawWizard("", 16777215, 0);
        }
        this.thisContainer.getAllStyles().setBgColor(16777215);
        this.thisContainer.getAllStyles().setBgTransparency(255);
        this.thisContainer.getAllStyles().setPadding(0, 2, 0, 0);
        this.thisContainer.getAllStyles().setPaddingUnit(2, 2, 2, 2);
        return this.thisContainer;
    }

    private Container managePasswordsCnt() {
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "ChangePaswordStep1");
        this.ChangePaswordStep1 = createContainer;
        this.LogingArea = (Container) uIBuilder.findByName("loginCnt", createContainer);
        TextField textField = (TextField) uIBuilder.findByName("LoginTxt", this.ChangePaswordStep1);
        textField.setCursorBlinkTimeOff(999999);
        TextField textField2 = (TextField) uIBuilder.findByName("PassTxt", this.ChangePaswordStep1);
        textField2.setCursorBlinkTimeOff(999999);
        TextField textField3 = (TextField) uIBuilder.findByName("newPassTxt", this.ChangePaswordStep1);
        textField3.setCursorBlinkTimeOff(999999);
        this.delOldPassBtn = (Button) uIBuilder.findByName("delOldPassBtn", this.ChangePaswordStep1);
        this.delPassBtn = (Button) uIBuilder.findByName("delPassBtn", this.ChangePaswordStep1);
        this.delnewPassBtn = (Button) uIBuilder.findByName("delnewPassBtn", this.ChangePaswordStep1);
        this.delPassBtn.setHidden(true);
        this.delOldPassBtn.setHidden(true);
        this.delnewPassBtn.setHidden(true);
        this.delPassBtn.setIcon(CihMBanking.delUserIcon);
        this.delOldPassBtn.setIcon(CihMBanking.delUserIcon);
        this.delnewPassBtn.setIcon(CihMBanking.delUserIcon);
        B3GTextField b3GTextField = new B3GTextField(textField, (Container) uIBuilder.findByName("loginTxtField", this.ChangePaswordStep1));
        this.newPassTxt = new B3GTextField(textField3, (Container) uIBuilder.findByName("newPassTxtField", this.ChangePaswordStep1));
        this.passTxt = new B3GTextField(textField2, (Container) uIBuilder.findByName("passTxtField", this.ChangePaswordStep1));
        Container container = (Container) uIBuilder.findByName("keybordCnt", this.ChangePaswordStep1);
        this.nextBtn = (Button) uIBuilder.findByName("nextBtn", this.ChangePaswordStep1);
        DrawLoginKeyBoard(container, b3GTextField, this.passTxt, this.newPassTxt);
        Button button = (Button) uIBuilder.findByName("fakeBtn1", this.ChangePaswordStep1);
        Button button2 = (Button) uIBuilder.findByName("fakeBtn", this.ChangePaswordStep1);
        Button button3 = (Button) uIBuilder.findByName("fakeBtn2", this.ChangePaswordStep1);
        button2.addActionListener(new 1(b3GTextField, button2, button, button3, textField, textField2, textField3));
        button.addActionListener(new 2(b3GTextField, button2, button, button3, textField, textField2, textField3));
        button3.addActionListener(new 3(b3GTextField, button2, button, button3, textField, textField2, textField3));
        this.passTxt.getTextField().addDataChangedListener(new 4());
        b3GTextField.getTextField().addDataChangedListener(new 5(b3GTextField));
        this.newPassTxt.getTextField().addDataChangedListener(new 6());
        this.nextBtn.setPreferredH(Display.getInstance().getDisplayWidth() / 7);
        this.nextBtn.addActionListener(new 7(b3GTextField, textField2, textField3, button2, button, button3, textField));
        button2.setIcon(CihMBanking.theme.getImage("ancienMDPactif.png"));
        b3GTextField.setIsTextFieldFocused(true);
        this.passTxt.setIsTextFieldFocused(false);
        this.newPassTxt.setIsTextFieldFocused(false);
        this.ChangePaswordStep1.revalidate();
        return this.ChangePaswordStep1;
    }

    class 1 implements ActionListener {
        final /* synthetic */ TextField val$LoginField;
        final /* synthetic */ TextField val$PassTx;
        final /* synthetic */ Button val$fakeBtn;
        final /* synthetic */ Button val$fakeBtn1;
        final /* synthetic */ Button val$fakeBtn2;
        final /* synthetic */ TextField val$newPassTxtF;
        final /* synthetic */ B3GTextField val$oldPassword;

        1(B3GTextField b3GTextField, Button button, Button button2, Button button3, TextField textField, TextField textField2, TextField textField3) {
            this.val$oldPassword = b3GTextField;
            this.val$fakeBtn = button;
            this.val$fakeBtn1 = button2;
            this.val$fakeBtn2 = button3;
            this.val$LoginField = textField;
            this.val$PassTx = textField2;
            this.val$newPassTxtF = textField3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$oldPassword.setIsTextFieldFocused(true);
            ChangePasswordPage.this.passTxt.setIsTextFieldFocused(false);
            ChangePasswordPage.this.newPassTxt.setIsTextFieldFocused(false);
            this.val$fakeBtn.setIcon(CihMBanking.theme.getImage("ancienMDPactif.png"));
            this.val$fakeBtn1.setIcon(CihMBanking.theme.getImage("nouveauMDP.png"));
            this.val$fakeBtn2.setIcon(CihMBanking.theme.getImage("confirmerMDP.png"));
            this.val$LoginField.setHint("");
            this.val$PassTx.setHint("Nouveau mot de passe");
            this.val$newPassTxtF.setHint("Confirmer le mot de passe");
            ChangePasswordPage.this.delPassBtn.setHidden(true);
            ChangePasswordPage.this.delnewPassBtn.setHidden(true);
            ChangePasswordPage.this.ChangePaswordStep1.revalidate();
            ChangePasswordPage.this.thisContainer.revalidate();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ TextField val$LoginField;
        final /* synthetic */ TextField val$PassTx;
        final /* synthetic */ Button val$fakeBtn;
        final /* synthetic */ Button val$fakeBtn1;
        final /* synthetic */ Button val$fakeBtn2;
        final /* synthetic */ TextField val$newPassTxtF;
        final /* synthetic */ B3GTextField val$oldPassword;

        2(B3GTextField b3GTextField, Button button, Button button2, Button button3, TextField textField, TextField textField2, TextField textField3) {
            this.val$oldPassword = b3GTextField;
            this.val$fakeBtn = button;
            this.val$fakeBtn1 = button2;
            this.val$fakeBtn2 = button3;
            this.val$LoginField = textField;
            this.val$PassTx = textField2;
            this.val$newPassTxtF = textField3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$oldPassword.setIsTextFieldFocused(false);
            ChangePasswordPage.this.passTxt.setIsTextFieldFocused(true);
            ChangePasswordPage.this.newPassTxt.setIsTextFieldFocused(false);
            this.val$fakeBtn.setIcon(CihMBanking.theme.getImage("ancienMDP.png 1"));
            this.val$fakeBtn1.setIcon(CihMBanking.theme.getImage("nouveauMDPactif.png"));
            this.val$fakeBtn2.setIcon(CihMBanking.theme.getImage("confirmerMDP.png"));
            this.val$LoginField.setHint("Ancien mot de passe");
            this.val$PassTx.setHint("");
            this.val$newPassTxtF.setHint("Confirmer le mot de passe");
            ChangePasswordPage.this.delOldPassBtn.setHidden(true);
            ChangePasswordPage.this.delnewPassBtn.setHidden(true);
            ChangePasswordPage.this.ChangePaswordStep1.revalidate();
            ChangePasswordPage.this.thisContainer.revalidate();
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ TextField val$LoginField;
        final /* synthetic */ TextField val$PassTx;
        final /* synthetic */ Button val$fakeBtn;
        final /* synthetic */ Button val$fakeBtn1;
        final /* synthetic */ Button val$fakeBtn2;
        final /* synthetic */ TextField val$newPassTxtF;
        final /* synthetic */ B3GTextField val$oldPassword;

        3(B3GTextField b3GTextField, Button button, Button button2, Button button3, TextField textField, TextField textField2, TextField textField3) {
            this.val$oldPassword = b3GTextField;
            this.val$fakeBtn = button;
            this.val$fakeBtn1 = button2;
            this.val$fakeBtn2 = button3;
            this.val$LoginField = textField;
            this.val$PassTx = textField2;
            this.val$newPassTxtF = textField3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$oldPassword.setIsTextFieldFocused(false);
            ChangePasswordPage.this.passTxt.setIsTextFieldFocused(false);
            ChangePasswordPage.this.newPassTxt.setIsTextFieldFocused(true);
            this.val$fakeBtn.setIcon(CihMBanking.theme.getImage("ancienMDP.png 1"));
            this.val$fakeBtn1.setIcon(CihMBanking.theme.getImage("nouveauMDP.png"));
            this.val$fakeBtn2.setIcon(CihMBanking.theme.getImage("confirmerMDPactif.png"));
            this.val$LoginField.setHint("Ancien mot de passe");
            this.val$PassTx.setHint("Nouveau mot de passe");
            this.val$newPassTxtF.setHint("");
            ChangePasswordPage.this.delOldPassBtn.setHidden(true);
            ChangePasswordPage.this.delPassBtn.setHidden(true);
            ChangePasswordPage.this.ChangePaswordStep1.revalidate();
            ChangePasswordPage.this.thisContainer.revalidate();
        }
    }

    class 4 implements DataChangedListener {
        4() {
        }

        public void dataChanged(int i, int i2) {
            if (ChangePasswordPage.this.passTxt.getTextField().getText().length() > 0) {
                ChangePasswordPage.this.delPassBtn.setHidden(false);
                ChangePasswordPage.this.ChangePaswordStep1.revalidate();
                ChangePasswordPage.this.thisContainer.revalidate();
            } else {
                ChangePasswordPage.this.delPassBtn.setHidden(true);
                ChangePasswordPage.this.ChangePaswordStep1.revalidate();
                ChangePasswordPage.this.thisContainer.revalidate();
            }
        }
    }

    class 5 implements DataChangedListener {
        final /* synthetic */ B3GTextField val$oldPassword;

        5(B3GTextField b3GTextField) {
            this.val$oldPassword = b3GTextField;
        }

        public void dataChanged(int i, int i2) {
            if (this.val$oldPassword.getTextField().getText().length() > 0) {
                ChangePasswordPage.this.delOldPassBtn.setHidden(false);
                ChangePasswordPage.this.ChangePaswordStep1.revalidate();
                ChangePasswordPage.this.thisContainer.revalidate();
            } else {
                ChangePasswordPage.this.delOldPassBtn.setHidden(true);
                ChangePasswordPage.this.ChangePaswordStep1.revalidate();
                ChangePasswordPage.this.thisContainer.revalidate();
            }
        }
    }

    class 6 implements DataChangedListener {
        6() {
        }

        public void dataChanged(int i, int i2) {
            if (ChangePasswordPage.this.newPassTxt.getTextField().getText().length() > 0) {
                ChangePasswordPage.this.delnewPassBtn.setHidden(false);
                ChangePasswordPage.this.thisContainer.revalidate();
            } else {
                ChangePasswordPage.this.delnewPassBtn.setHidden(true);
                ChangePasswordPage.this.thisContainer.revalidate();
            }
        }
    }

    class 7 implements ActionListener {
        final /* synthetic */ TextField val$LoginField;
        final /* synthetic */ TextField val$PassTx;
        final /* synthetic */ Button val$fakeBtn;
        final /* synthetic */ Button val$fakeBtn1;
        final /* synthetic */ Button val$fakeBtn2;
        final /* synthetic */ TextField val$newPassTxtF;
        final /* synthetic */ B3GTextField val$oldPassword;

        7(B3GTextField b3GTextField, TextField textField, TextField textField2, Button button, Button button2, Button button3, TextField textField3) {
            this.val$oldPassword = b3GTextField;
            this.val$PassTx = textField;
            this.val$newPassTxtF = textField2;
            this.val$fakeBtn = button;
            this.val$fakeBtn1 = button2;
            this.val$fakeBtn2 = button3;
            this.val$LoginField = textField3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$oldPassword.getTextField().getText().length() == 0 || ChangePasswordPage.this.newPassTxt.getTextField().getText().length() == 0 || ChangePasswordPage.this.passTxt.getTextField().getText().length() == 0) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ChangePasswordPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            if (!ChangePasswordPage.this.newPassTxt.getTextField().getText().equals(ChangePasswordPage.this.passTxt.getTextField().getText())) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ChangePasswordPage.this.uiManager.stateMachine, "Les nouveaux mots de passe ne sont pas identiques", null);
                this.val$PassTx.setText("");
                this.val$newPassTxtF.setText("");
                this.val$oldPassword.setIsTextFieldFocused(false);
                ChangePasswordPage.this.passTxt.setIsTextFieldFocused(true);
                ChangePasswordPage.this.newPassTxt.setIsTextFieldFocused(false);
                this.val$fakeBtn.setIcon(CihMBanking.theme.getImage("ancienMDP.png 1"));
                this.val$fakeBtn1.setIcon(CihMBanking.theme.getImage("nouveauMDPactif.png"));
                this.val$fakeBtn2.setIcon(CihMBanking.theme.getImage("confirmerMDP.png"));
                this.val$LoginField.setHint("Ancien mot de passe");
                this.val$PassTx.setHint("Nouveau mot de passe");
                this.val$newPassTxtF.setHint("Confirmer le mot de passe");
                ChangePasswordPage.this.delOldPassBtn.setHidden(true);
                ChangePasswordPage.this.delPassBtn.setHidden(true);
                ChangePasswordPage.this.delnewPassBtn.setHidden(true);
                return;
            }
            ServiceResponse checkPasswordProcess = ChangePasswordPage.this.checkPasswordProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, this.val$oldPassword.getTextField().getText(), ChangePasswordPage.this.newPassTxt.getTextField().getText(), CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm);
            if (!checkPasswordProcess.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ChangePasswordPage.this.uiManager.stateMachine, DataTools.FormatUnicode(checkPasswordProcess.getStatusLabel()), null);
                this.val$PassTx.setText("");
                this.val$newPassTxtF.setText("");
                this.val$oldPassword.setIsTextFieldFocused(false);
                ChangePasswordPage.this.passTxt.setIsTextFieldFocused(true);
                ChangePasswordPage.this.newPassTxt.setIsTextFieldFocused(false);
                this.val$fakeBtn.setIcon(CihMBanking.theme.getImage("ancienMDP.png 1"));
                this.val$fakeBtn1.setIcon(CihMBanking.theme.getImage("nouveauMDPactif.png"));
                this.val$fakeBtn2.setIcon(CihMBanking.theme.getImage("confirmerMDP.png"));
                this.val$LoginField.setHint("Ancien mot de passe");
                this.val$PassTx.setHint("Nouveau mot de passe");
                this.val$newPassTxtF.setHint("Confirmer le mot de passe");
                ChangePasswordPage.this.delOldPassBtn.setHidden(true);
                ChangePasswordPage.this.delPassBtn.setHidden(true);
                ChangePasswordPage.this.delnewPassBtn.setHidden(true);
                return;
            }
            ChangePasswordPage.this.ChangePasswordWizard.goToNextStep(ChangePasswordPage.this.s1, "", 16777215, 0);
        }
    }

    private Container changePasswordCnt() {
        UIBuilder uIBuilder = new UIBuilder();
        Container container = new Container(BoxLayout.y());
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container2 = (Container) uIBuilder.findByName("Container17", createContainer);
        container2.removeAll();
        container2.setLayout(BoxLayout.y());
        container2.add(b3GCIHEcode.getComponent());
        container.add(createContainer);
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 8());
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 9(b3GCIHEcode));
        return container;
    }

    class 8 implements ActionListener {
        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ChangePasswordPage.this.ChangePasswordWizard.goToPreviousStep(ChangePasswordPage.this.s2, "", 16777215, 0);
        }
    }

    class 9 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        9(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            boolean z = false;
            if (ChangePasswordPage.counter < 3) {
                ChangePasswordPage.access$002(ChangePasswordPage.this, this.val$eCode1.getValue());
                ServiceResponse changePasswordProcess = ChangePasswordPage.this.changePasswordProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, ChangePasswordPage.this.newPassTxt.getTextField().getText(), ChangePasswordPage.access$000(ChangePasswordPage.this));
                if (changePasswordProcess.getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus = "1";
                    if (SessionParameter.currentTransactionnalPage == 0) {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ChangePasswordPage.this.uiManager.stateMachine, DataTools.FormatUnicode(changePasswordProcess.getStatusLabel()), null);
                        ChangePasswordPage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
                    } else {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ChangePasswordPage.this.uiManager.stateMachine, "Changement de mot de passe effectué avec succès, merci de ressaisir votre opération", null);
                        ChangePasswordPage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
                        ChangePasswordPage.this.uiManager.NavigateToPageById(SessionParameter.currentTransactionnalPage);
                    }
                    Vector vector = new Vector();
                    if (Storage.getInstance().exists("veclistCnxStorage")) {
                        vector = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
                    }
                    int i = 0;
                    while (true) {
                        if (i >= vector.size()) {
                            break;
                        }
                        if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical.equals(((AccountLastConnection) vector.elementAt(i)).radical)) {
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
                        touchIDNativeCall.savePasswordForRadical(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, ChangePasswordPage.this.newPassTxt.getTextField().getText());
                        return;
                    }
                    return;
                }
                if (changePasswordProcess.getStatusCode().equals("001")) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ChangePasswordPage.this.uiManager.stateMachine, "CIH E-Code est incorrect", null);
                    ChangePasswordPage.counter++;
                    return;
                } else {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ChangePasswordPage.this.uiManager.stateMachine, DataTools.FormatUnicode(changePasswordProcess.getStatusLabel()), null);
                    return;
                }
            }
            ChangePasswordPage.counter = 0;
            ChangePasswordPage.this.uiManager.stateMachine.showForm("LoginV2New", (Command) null);
        }
    }

    public void DrawLoginKeyBoard(Container container, B3GTextField b3GTextField, B3GTextField b3GTextField2, B3GTextField b3GTextField3) {
        ArrayList GetRandomIntArray = DataTools.GetRandomIntArray(10);
        container.setLayout(new BoxLayout(2));
        container.removeAll();
        Container DrawKeyBoardRow = DrawKeyBoardRow(b3GTextField2, b3GTextField, b3GTextField3, GetRandomIntArray, 0, 0, 1, 5);
        Container DrawKeyBoardRow2 = DrawKeyBoardRow(b3GTextField2, b3GTextField, b3GTextField3, GetRandomIntArray, 5, 1, 1, 5);
        container.addComponent(DrawKeyBoardRow);
        container.revalidate();
        container.addComponent(DrawKeyBoardRow2);
        container.revalidate();
    }

    public Container DrawKeyBoardRow(B3GTextField b3GTextField, B3GTextField b3GTextField2, B3GTextField b3GTextField3, Object obj, int i, int i2, int i3, int i4) {
        ArrayList arrayList = (ArrayList) obj;
        Container container = new Container();
        TableLayout tableLayout = new TableLayout(i3, i4);
        container.setLayout(tableLayout);
        container.removeAll();
        container.setUIID("Container");
        for (int i5 = i; i5 <= i + 4; i5++) {
            String str = "" + arrayList.get(i5);
            Button button = new Button("" + arrayList.get(i5));
            button.setPreferredH(Display.getInstance().getDisplayWidth() / 7);
            button.setUIID("VKBBtn");
            button.setVerticalAlignment(4);
            button.setFocusable(false);
            button.addActionListener(new 10(b3GTextField2, str, b3GTextField3, b3GTextField));
            TableLayout.Constraint createConstraint = tableLayout.createConstraint();
            createConstraint.setWidthPercentage(20);
            container.addComponent(createConstraint, button);
        }
        this.delPassBtn.setFocusable(false);
        this.delPassBtn.addActionListener(new 11(b3GTextField));
        this.delnewPassBtn.setFocusable(false);
        this.delnewPassBtn.addActionListener(new 12(b3GTextField3));
        this.delOldPassBtn.setFocusable(false);
        this.delOldPassBtn.addActionListener(new 13(b3GTextField2));
        return container;
    }

    class 10 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ B3GTextField val$PassField;
        final /* synthetic */ B3GTextField val$newPassTxt;
        final /* synthetic */ String val$number;

        10(B3GTextField b3GTextField, String str, B3GTextField b3GTextField2, B3GTextField b3GTextField3) {
            this.val$LoginField = b3GTextField;
            this.val$number = str;
            this.val$newPassTxt = b3GTextField2;
            this.val$PassField = b3GTextField3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$LoginField.isIsTextFieldFocused()) {
                this.val$LoginField.getTextField().setConstraint(65536);
                this.val$LoginField.getTextField().setText(this.val$LoginField.getTextField().getText() + this.val$number);
            } else if (this.val$newPassTxt.isIsTextFieldFocused()) {
                this.val$newPassTxt.getTextField().setConstraint(65536);
                this.val$newPassTxt.getTextField().setText(this.val$newPassTxt.getTextField().getText() + this.val$number);
            } else if (this.val$PassField.isIsTextFieldFocused()) {
                this.val$PassField.getTextField().setConstraint(65536);
                this.val$PassField.getTextField().setText(this.val$PassField.getTextField().getText() + this.val$number);
            }
        }
    }

    class 11 implements ActionListener {
        final /* synthetic */ B3GTextField val$PassField;

        11(B3GTextField b3GTextField) {
            this.val$PassField = b3GTextField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!this.val$PassField.isIsTextFieldFocused() || this.val$PassField.getTextField().getText().length() <= 0) {
                return;
            }
            this.val$PassField.getTextField().setText(this.val$PassField.getTextField().getText().substring(0, this.val$PassField.getTextField().getText().length() - 1));
            actionEvent.consume();
        }
    }

    class 12 implements ActionListener {
        final /* synthetic */ B3GTextField val$newPassTxt;

        12(B3GTextField b3GTextField) {
            this.val$newPassTxt = b3GTextField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!this.val$newPassTxt.isIsTextFieldFocused() || this.val$newPassTxt.getTextField().getText().length() <= 0) {
                return;
            }
            this.val$newPassTxt.getTextField().setText(this.val$newPassTxt.getTextField().getText().substring(0, this.val$newPassTxt.getTextField().getText().length() - 1));
            actionEvent.consume();
        }
    }

    class 13 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;

        13(B3GTextField b3GTextField) {
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

    public void DrawLoginKeyBoard(Container container, B3GTextField b3GTextField) {
        ArrayList GetRandomIntArray = DataTools.GetRandomIntArray(10);
        container.setLayout(new BoxLayout(2));
        container.removeAll();
        Container DrawKeyBoardRow = DrawKeyBoardRow(b3GTextField, GetRandomIntArray, 0, 0, 1, 5);
        Container DrawKeyBoardRow2 = DrawKeyBoardRow(b3GTextField, GetRandomIntArray, 5, 1, 1, 5);
        container.addComponent(DrawKeyBoardRow);
        container.revalidate();
        container.addComponent(DrawKeyBoardRow2);
        container.revalidate();
    }

    public Container DrawKeyBoardRow(B3GTextField b3GTextField, Object obj, int i, int i2, int i3, int i4) {
        ArrayList arrayList = (ArrayList) obj;
        Container container = new Container();
        TableLayout tableLayout = new TableLayout(i3, i4);
        container.setLayout(tableLayout);
        container.removeAll();
        container.setUIID("Container");
        for (int i5 = i; i5 <= i + 4; i5++) {
            String str = "" + arrayList.get(i5);
            Button button = new Button("" + arrayList.get(i5));
            button.setPreferredH(Display.getInstance().getDisplayWidth() / 7);
            button.setUIID("VKBBtn");
            button.setVerticalAlignment(4);
            button.setFocusable(false);
            button.addActionListener(new 14(b3GTextField, str));
            TableLayout.Constraint createConstraint = tableLayout.createConstraint();
            createConstraint.setWidthPercentage(20);
            container.addComponent(createConstraint, button);
        }
        return container;
    }

    class 14 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ String val$number;

        14(B3GTextField b3GTextField, String str) {
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

    public ServiceResponse checkPasswordProcess(String str, String str2, String str3, String str4) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("OLD_PASSWORD", str2);
        hashtable.put("NEW_PASSWORD", str3);
        hashtable.put("PHONE_NUMBER", str4);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setServiceId(57);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    CheckPassword checkPassword(ServiceResponse serviceResponse) {
        return new CheckPassword();
    }

    public ServiceResponse changePasswordProcess(String str, String str2, String str3) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("NEW_PASSWORD", str2);
        hashtable.put("CODE_OTP", str3);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setServiceId(58);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    BlankService changePassword(ServiceResponse serviceResponse) {
        return new BlankService();
    }
}
