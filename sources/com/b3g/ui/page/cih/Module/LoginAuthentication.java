package com.b3g.ui.page.cih.Module;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.tools.DataTools;
import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.B3GTextField;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gLoginTextBox;
import com.codename1.fingerprint.Fingerprint;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class LoginAuthentication extends Container {
    public static Container Container7;
    static Container LoginAuthentificationCnt;
    public static Container LogingArea;
    public static Button LostPasswdBtn;
    public static TextField PassTx;
    public static Container RememberMe;
    public static Container TitleCnt;
    public static Container cntKybrd;
    static Button delUserBtn;
    public static Button fakeBtn;
    public static Button fakeBtn1;
    public static Button fakeBtn2;
    public static Button fakeBtn3;
    static Label fullnameLbl;
    static int incr;
    public static Container infoUserCnt;
    public static Container keyBoard;
    static int keyboardHeight;
    public static Container loginFields;
    public static B3GTextField loginTxt;
    public static Container loginTxtFieldCnt;
    static TouchIDNativeCall pTouchIDNativeCall;
    public static B3GTextField passTxt;
    static Label radicalLbl;
    static Button sendBtn;
    static Container sendCnt;
    static Button touchIDBtn;
    TableLayout Layouttbl;
    public TextField LoginField;
    public String NameSvd;
    String RadicalSvd;
    public String RadicalSvdL;
    Button RememberBtn;
    AccountLastConnection acc;
    public Button connetBtn;
    public String firstRadical;
    String radicalClient;
    public final B3gLoginTextBox userNameCnt = new B3gLoginTextBox(null);
    final B3gLoginTextBox passCnt = new B3gLoginTextBox("Password");
    int UserNameTextBoxIndex = 0;
    int PasswordTextBoxIndex = 0;
    private boolean iskeyBoardVisible = false;
    Vector vecStoragePic = new Vector();
    Vector vecStorageClientSex = new Vector();
    boolean isSwitchToAnotherRdc = false;
    UIBuilder uib = new UIBuilder();

    public LoginAuthentication() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v147, types: [com.codename1.ui.Button] */
    /* JADX WARN: Type inference failed for: r0v185, types: [com.b3g.ui.B3GTextField] */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r28v0, types: [com.b3g.ui.page.cih.Module.LoginAuthentication] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v8 */
    public LoginAuthentication(B3GUiManager b3GUiManager, Button button) throws IOException {
        Label label;
        ?? r6;
        boolean z;
        Image image;
        ?? r12;
        setLayout(new GridLayout(1, 1));
        this.Layouttbl = new TableLayout(1, 2);
        Image image2 = b3GUiManager.ressource.getImage("round-mask.png");
        Image createImage = Image.createImage(image2.getWidth(), image2.getHeight(), 12569042);
        EncodedImage.createFromImage(createImage, false);
        Image createImage2 = Image.createImage(createImage.getWidth(), createImage.getHeight(), -16777216);
        Graphics graphics = createImage2.getGraphics();
        graphics.setColor(16777215);
        graphics.fillArc(0, 0, createImage.getWidth(), createImage.getHeight(), 0, 360);
        URLImage.createMaskAdapter(createImage2);
        Container createContainer = this.uib.createContainer("/cihv3", "LoginAuthentification");
        LoginAuthentificationCnt = createContainer;
        TitleCnt = (Container) this.uib.findByName("TitleCnt", createContainer);
        Label label2 = new Label("Authentification");
        label2.setUIID("Lbl_exo_semiBold_18");
        TitleCnt.add(label2);
        Container container = (Container) this.uib.findByName("Container7", LoginAuthentificationCnt);
        Container7 = container;
        container.setUIID("ContainerTrspBottom1");
        keyBoard = (Container) this.uib.findByName("keybordCnt", LoginAuthentificationCnt);
        cntKybrd = (Container) this.uib.findByName("Container2", LoginAuthentificationCnt);
        fakeBtn3 = (Button) this.uib.findByName("Button", LoginAuthentificationCnt);
        sendCnt = (Container) this.uib.findByName("sendCnt", LoginAuthentificationCnt);
        loginFields = (Container) this.uib.findByName("loginFields", LoginAuthentificationCnt);
        fakeBtn1 = (Button) this.uib.findByName("fakeBtn1", LoginAuthentificationCnt);
        LogingArea = (Container) this.uib.findByName("loginCnt", LoginAuthentificationCnt);
        PassTx = (TextField) this.uib.findByName("PassTxt", LoginAuthentificationCnt);
        LostPasswdBtn = (Button) this.uib.findByName("mdpBtn", LoginAuthentificationCnt);
        passTxt = new B3GTextField(PassTx, (Container) this.uib.findByName("passTxtField", LoginAuthentificationCnt));
        PassTx.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        PassTx.setHint("Mot de passe");
        PassTx.setCursorBlinkTimeOff(999999);
        if (CihMBanking.RTL) {
            PassTx.setRTL(true);
        } else {
            PassTx.setRTL(false);
        }
        LoginAuthentificationCnt.setScrollVisible(false);
        Button button2 = new Button("Connexion");
        sendBtn = button2;
        button2.setUIID("sendBtnNewOffLine");
        sendBtn.getAllStyles().setPaddingUnit(1);
        sendBtn.getAllStyles().setPadding(1.5f, 1.5f, 2.0f, 2.0f);
        sendBtn.getAllStyles().setMarginUnit(1);
        sendBtn.getAllStyles().setMargin(0, 2, 5, 5);
        Button button3 = new Button();
        touchIDBtn = button3;
        button3.getAllStyles().setMarginUnit(1);
        touchIDBtn.setUIID("VKBBtnBiom");
        touchIDBtn.setVerticalAlignment(4);
        touchIDBtn.setAlignment(4);
        touchIDBtn.getAllStyles().setBorder(RoundBorder.create().rectangle(false).shadowSpread(40).color(15818018).strokeColor(15818018).stroke(new Stroke(3.0f, 1, 1, 3.0f)));
        Label label3 = new Label();
        label3.setVerticalAlignment(4);
        if (!Display.getInstance().isSimulator()) {
            pTouchIDNativeCall = (TouchIDNativeCall) NativeLookup.create(TouchIDNativeCall.class);
        }
        Vector vector = new Vector();
        this.RadicalSvd = "";
        vector = Storage.getInstance().exists("RadicalSvd") ? (Vector) Storage.getInstance().readObject("RadicalSvd") : vector;
        if (Storage.getInstance().exists("RadicalSvd") && vector.size() > 0) {
            if (vector.size() > 0) {
                ArrayList arrayList = new ArrayList();
                int i = 0;
                for (int i2 = 0; i2 < vector.size(); i2 = i2 + 1 + 1) {
                    LoginAuthentication loginAuthentication = new LoginAuthentication();
                    loginAuthentication.RadicalSvdL = (String) vector.get(i2);
                    int i3 = i + 1;
                    loginAuthentication.NameSvd = (String) vector.get(i3);
                    i = i3 + 1;
                    arrayList.add(loginAuthentication);
                }
                Collections.reverse(arrayList);
                ArrayList arrayList2 = arrayList;
                label = label3;
                z = false;
                Container DrawListContainer = DrawListContainer(b3GUiManager, "GloabalContainerLogin", "", Boolean.TRUE, arrayList2, 1, 89, "", null, null, LoginAuthentificationCnt, null);
                infoUserCnt = DrawListContainer;
                DrawListContainer.getAllStyles().setBgTransparency(0);
                LogingArea.addComponent("Center", infoUserCnt);
                Button button4 = new Button();
                button4.setUIID("");
                button4.setIcon(CihMBanking.theme.getImage(".png"));
                Container cntLoginFromTab = getCntLoginFromTab(infoUserCnt, 0);
                loginTxtFieldCnt = getTextFieldLoginCnt(cntLoginFromTab);
                this.LoginField = getTextFieldLogin(cntLoginFromTab);
                fakeBtn = getfakeBtn(cntLoginFromTab);
                Button button5 = getfakeBtn2(cntLoginFromTab);
                fakeBtn2 = button5;
                button5.setName("fakeBtn2");
                delUserBtn = getDelUserBtn(cntLoginFromTab);
                radicalLbl = getRadicalLbl(cntLoginFromTab);
                fullnameLbl = getFullnameLbl(cntLoginFromTab);
                loginTxt = new B3GTextField(this.LoginField, loginTxtFieldCnt);
                this.LoginField.getHintLabel().setUIID("Lbl_lato_Meduim_18");
                loginTxt.getTextField().getHintLabel().setUIID("Lbl_lato_Meduim_18");
                r6 = 1;
                passTxt.setIsTextFieldFocused(true);
                loginTxt.setIsTextFieldFocused(false);
                if (CihMBanking.RTL) {
                    this.LoginField.setRTL(true);
                } else {
                    this.LoginField.setRTL(false);
                }
                fakeBtn.addActionListener(new 1());
                int i4 = 0;
                while (i4 < arrayList2.size()) {
                    ArrayList arrayList3 = arrayList2;
                    if (((LoginAuthentication) arrayList3.get(i4)).NameSvd.equals(fullnameLbl.getText())) {
                        String str = ((LoginAuthentication) arrayList3.get(i4)).RadicalSvdL;
                        this.RadicalSvd = str;
                        this.firstRadical = str;
                    }
                    i4++;
                    arrayList2 = arrayList3;
                }
                LoginAuthentificationCnt.forceRevalidate();
            } else {
                label = label3;
                r6 = 1;
                z = false;
            }
        } else {
            label = label3;
            r6 = 1;
            z = false;
            Container createContainer2 = this.uib.createContainer("/cihv3", "loginCntNew");
            infoUserCnt = createContainer2;
            radicalLbl = (Label) this.uib.findByName("radicalLbl", createContainer2);
            fullnameLbl = (Label) this.uib.findByName("fullnameLbl", infoUserCnt);
            fakeBtn2 = (Button) this.uib.findByName("fakeBtn2", infoUserCnt);
            fakeBtn = (Button) this.uib.findByName("fakeBtn", infoUserCnt);
            loginTxtFieldCnt = (Container) this.uib.findByName("loginTxtField", infoUserCnt);
            this.LoginField = (TextField) this.uib.findByName("LoginTxt", infoUserCnt);
            Button button6 = (Button) this.uib.findByName("delUserBtn", infoUserCnt);
            delUserBtn = button6;
            button6.setIcon(CihMBanking.theme.getImage("help.png"));
            this.LoginField.setCursorBlinkTimeOff(999999);
            if (CihMBanking.RTL) {
                this.LoginField.setRTL(true);
            } else {
                this.LoginField.setRTL(false);
            }
            loginTxt = new B3GTextField(this.LoginField, loginTxtFieldCnt);
            fakeBtn2.setIcon(CihMBanking.theme.getImage("User3.png"));
            this.LoginField.getHintLabel().setUIID("Lbl_lato_Meduim_18");
            loginTxt.getTextField().getHintLabel().setUIID("Lbl_lato_Meduim_18");
            this.LoginField.setHint("Utilisateur");
            fakeBtn3.setHidden(true);
            fullnameLbl.setHidden(true);
            radicalLbl.setHidden(true);
            LogingArea.addComponent("Center", infoUserCnt);
            delUserBtn.setVisible(false);
            LoginAuthentificationCnt.forceRevalidate();
            delUserBtn.addActionListener(new 2());
        }
        boolean z2 = z;
        DrawLoginKeyBoard(keyBoard, loginTxt, passTxt, b3GUiManager);
        keyboardHeight = keyBoard.getPreferredH();
        if (!passTxt.isIsTextFieldFocused()) {
            loginTxt.setIsTextFieldFocused(r6);
        }
        fakeBtn.addActionListener(new 3());
        fakeBtn1.addActionListener(new 4());
        CihMBanking.sesPMTR.setStoredRadical(this.RadicalSvd);
        if (!Display.getInstance().isSimulator()) {
            if (pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                Label label4 = label;
                label4.setIcon(b3GUiManager.ressource.getImage("Biometrie_ID.png"));
                touchIDBtn.setIcon(label4.getIcon());
            } else {
                Label label5 = label;
                if (pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
                    label5.setIcon(b3GUiManager.ressource.getImage("Empreinte_ID.png"));
                    touchIDBtn.setIcon(label5.getIcon());
                } else if (pTouchIDNativeCall.getBiometryType().equals("FACEID")) {
                    touchIDBtn.setEnabled(z2);
                    label5.setIcon(b3GUiManager.ressource.getImage("face_id.png"));
                    touchIDBtn.setIcon(label5.getIcon());
                }
            }
        }
        Vector vector2 = new Vector();
        if (Storage.getInstance().exists("veclistCnxStorage")) {
            Vector vector3 = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
            int i5 = z2 ? 1 : 0;
            while (true) {
                if (i5 >= vector3.size()) {
                    r12 = z2 ? 1 : 0;
                    break;
                } else {
                    if (CihMBanking.sesPMTR.getStoredRadical().equals(((AccountLastConnection) vector3.elementAt(i5)).radical)) {
                        r12 = r6;
                        break;
                    }
                    i5++;
                }
            }
            if (r12 != false && pTouchIDNativeCall.isSupported() && Fingerprint.isAvailable()) {
                sendCnt.setLayout(this.Layouttbl);
                sendBtn.getAllStyles().setMargin(0.8f, 2.7f, 0.5f, 1.3f);
                sendCnt.add(this.Layouttbl.createConstraint().widthPercentage(80), sendBtn).add(this.Layouttbl.createConstraint().widthPercentage(20), touchIDBtn);
            } else {
                sendCnt.setLayout(new GridLayout(r6, r6));
                sendBtn.getAllStyles().setMargin(z2 ? 1 : 0, 2, 5, 5);
                sendCnt.add(sendBtn);
            }
        } else {
            Storage.getInstance().writeObject("veclistCnxStorage", vector2);
            sendCnt.setLayout(new GridLayout(r6, r6));
            sendBtn.getAllStyles().setMargin(z2 ? 1 : 0, 2, 5, 5);
            sendCnt.add(sendBtn);
        }
        sendBtn.addActionListener(new 5(b3GUiManager));
        touchIDBtn.addActionListener(new 6(b3GUiManager));
        try {
            image = Image.createImage(FileSystemStorage.getInstance().openInputStream(FileSystemStorage.getInstance().getAppHomePath() + this.RadicalSvd + ".jpg"));
        } catch (Exception unused) {
            image = null;
        }
        setProfilPict(b3GUiManager, image, fakeBtn3);
        if (!DataTools.checkConnection()) {
            new PopUpsManager(b3GUiManager).showNoConnectoinMessg();
        } else {
            new Vector();
            if (Storage.getInstance().exists("veclistCnxStorage")) {
                Vector vector4 = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                int i6 = z2 ? 1 : 0;
                while (true) {
                    if (i6 >= vector4.size()) {
                        break;
                    }
                    if (CihMBanking.sesPMTR.getStoredRadical().equals(((AccountLastConnection) vector4.elementAt(i6)).radical)) {
                        this.acc = new AccountLastConnection(CihMBanking.sesPMTR.getStoredRadical(), simpleDateFormat.format(date), z2 ? 1 : 0);
                        break;
                    }
                    i6++;
                }
            }
        }
        addComponent(LoginAuthentificationCnt);
        delUserBtn.setVisible(r6);
        new UITimer(new LoginAuthentication$$ExternalSyntheticLambda1()).schedule(2000, z2, b3GUiManager.currentForm);
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LoginAuthentication.fakeBtn2.setIcon(CihMBanking.theme.getImage("UserFocus.png"));
            LoginAuthentication.fakeBtn3.setHidden(true);
            LoginAuthentication.fakeBtn2.setHidden(false);
            LoginAuthentication.fakeBtn1.setIcon(CihMBanking.theme.getImage("PasswordV1.png"));
            LoginAuthentication.delUserBtn.setIcon(CihMBanking.theme.getImage("help.png"));
            LoginAuthentication.this.LoginField.setText("");
            LoginAuthentication.this.LoginField.setHint("");
            LoginAuthentication.passTxt.setIsTextFieldFocused(false);
            LoginAuthentication.loginTxt.setIsTextFieldFocused(true);
            LoginAuthentication.fullnameLbl.setHidden(true);
            LoginAuthentication.radicalLbl.setHidden(true);
            LoginAuthentication.sendCnt.removeAll();
            LoginAuthentication.sendCnt.setLayout(new GridLayout(1, 1));
            LoginAuthentication.sendBtn.getAllStyles().setMargin(0, 2, 5, 5);
            LoginAuthentication.sendCnt.add(LoginAuthentication.sendBtn);
            if (LoginAuthentication.PassTx.getText().length() <= 0) {
                LoginAuthentication.PassTx.setHint("Mot de passe");
                actionEvent.consume();
            }
            LoginAuthentication.infoUserCnt.revalidate();
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LoginAuthentication.this.ShowHelpDialog();
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LoginAuthentication.fakeBtn2.setIcon(CihMBanking.theme.getImage("UserFocus.png"));
            LoginAuthentication.fakeBtn3.setHidden(true);
            LoginAuthentication.fakeBtn2.setHidden(false);
            LoginAuthentication.fakeBtn1.setIcon(CihMBanking.theme.getImage("PasswordV1.png"));
            LoginAuthentication.delUserBtn.setIcon(CihMBanking.theme.getImage("help.png"));
            LoginAuthentication.this.LoginField.setText("");
            LoginAuthentication.this.LoginField.setHint("");
            LoginAuthentication.passTxt.setIsTextFieldFocused(false);
            LoginAuthentication.loginTxt.setIsTextFieldFocused(true);
            LoginAuthentication.fullnameLbl.setHidden(true);
            LoginAuthentication.radicalLbl.setHidden(true);
            LoginAuthentication.sendCnt.removeAll();
            LoginAuthentication.sendCnt.setLayout(new GridLayout(1, 1));
            LoginAuthentication.sendBtn.getAllStyles().setMargin(0, 2, 5, 5);
            LoginAuthentication.sendCnt.add(LoginAuthentication.sendBtn);
            if (LoginAuthentication.PassTx.getText().length() <= 0) {
                LoginAuthentication.PassTx.setHint("Mot de passe");
                actionEvent.consume();
            }
            LoginAuthentication.infoUserCnt.revalidate();
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LoginAuthentication.fakeBtn2.setIcon(CihMBanking.theme.getImage("User3.png"));
            LoginAuthentication.fakeBtn1.setIcon(CihMBanking.theme.getImage("PasswordFocus.png"));
            LoginAuthentication.loginTxt.setIsTextFieldFocused(false);
            LoginAuthentication.passTxt.setIsTextFieldFocused(true);
            LoginAuthentication.PassTx.setText("");
            LoginAuthentication.PassTx.setHint("");
            if (LoginAuthentication.this.LoginField.getText().length() <= 0 && LoginAuthentication.radicalLbl.isHidden()) {
                LoginAuthentication.this.LoginField.getHintLabel().setUIID("Lbl_lato_Meduim_18");
                LoginAuthentication.loginTxt.getTextField().getHintLabel().setUIID("Lbl_lato_Meduim_18");
                LoginAuthentication.this.LoginField.setHint("Utilisateur");
                actionEvent.consume();
            }
            LoginAuthentication.infoUserCnt.revalidate();
        }
    }

    class 5 implements ActionListener {
        final /* synthetic */ B3GUiManager val$uIManager;

        5(B3GUiManager b3GUiManager) {
            this.val$uIManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (LoginAuthentication.loginTxt.getTextField().getText().length() > 0) {
                LoginAuthentication.this.RadicalSvd = LoginAuthentication.loginTxt.getTextField().getText();
            }
            this.val$uIManager.stateMachine.authentificationNew(LoginAuthentication.this.RadicalSvd, LoginAuthentication.passTxt.getTextField().getText(), false, LoginAuthentication.this.RememberBtn);
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ B3GUiManager val$uIManager;

        6(B3GUiManager b3GUiManager) {
            this.val$uIManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String msgTouchID;
            if (Display.getInstance().isSimulator()) {
                return;
            }
            TouchIDNativeCall touchIDNativeCall = (TouchIDNativeCall) NativeLookup.create(TouchIDNativeCall.class);
            if (touchIDNativeCall.isSupported()) {
                String storedRadical = CihMBanking.sesPMTR.getStoredRadical();
                if (storedRadical.length() == 0 || !touchIDNativeCall.isExistPasswordForRadical(storedRadical) || !Fingerprint.isAvailable() || CihMBanking.isConnecting.equals("03")) {
                    return;
                }
                if (touchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                    msgTouchID = DataTools.getMsgAndroiBiometric();
                } else {
                    msgTouchID = touchIDNativeCall.getBiometryType().equals("TOUCHID") ? DataTools.getMsgTouchID() : "";
                }
                Fingerprint.scanFingerprint(DataTools.getAvailabeBiometric(touchIDNativeCall.getBiometryType().equals("TOUCHID"), touchIDNativeCall.getBiometryType().equals("FACEID"), touchIDNativeCall.getBiometryType().equals("FINGERPRINT")), msgTouchID, DataTools.getNamCancelBtn(), new LoginAuthentication$6$$ExternalSyntheticLambda0(this, this.val$uIManager, storedRadical, touchIDNativeCall), new LoginAuthentication$6$$ExternalSyntheticLambda1(touchIDNativeCall), false);
            }
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-cih-Module-LoginAuthentication$6(B3GUiManager b3GUiManager, String str, TouchIDNativeCall touchIDNativeCall, Object obj) {
            b3GUiManager.stateMachine.authentificationNew(str, touchIDNativeCall.getPasswordForRadical(str), true, LoginAuthentication.this.RememberBtn);
        }

        static /* synthetic */ void lambda$actionPerformed$1(TouchIDNativeCall touchIDNativeCall, Object obj, Throwable th, int i, String str) {
            if (Display.getInstance().isSimulator()) {
                return;
            }
            touchIDNativeCall.getBiometryType().equals("FINGERPRINT");
        }
    }

    static /* synthetic */ void lambda$new$0() {
        touchIDBtn.setEnabled(true);
    }

    public void DrawLoginKeyBoard(Container container, B3GTextField b3GTextField, B3GTextField b3GTextField2, B3GUiManager b3GUiManager) {
        Container container2 = new Container(BoxLayout.y());
        ArrayList genrateRandomListInt = genrateRandomListInt();
        Container container3 = new Container(new FlowLayout(1, 4));
        Label label = new Label("Se souvenir de moi");
        label.setUIID("Lbl_lato_bold_15");
        label.getAllStyles().setMarginUnit(1);
        label.getAllStyles().setMargin(0.3f, 0.0f, 1.6f, 0.0f);
        Button button = new Button();
        this.RememberBtn = button;
        button.setIcon(CihMBanking.theme.getImage("RememberOn.png"));
        this.RememberBtn.addActionListener(new 7());
        this.RememberBtn.setUIID("Container");
        container3.add(this.RememberBtn);
        container3.add(label);
        container3.setLeadComponent(this.RememberBtn);
        container3.getAllStyles().setMarginUnit(1);
        container3.getAllStyles().setMargin(1, 1, 0, 0);
        Component container4 = new Container(new BorderLayout());
        container4.getAllStyles().setMarginUnit(1);
        container4.getAllStyles().setMargin(0, 2, 0, 0);
        LostPasswdBtn.addActionListener(new 8(b3GUiManager));
        container2.add(container3);
        container2.add(container4);
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

    class 7 implements ActionListener {
        7() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (LoginAuthentication.this.RememberBtn.getIcon().equals(CihMBanking.theme.getImage("RememberOn.png"))) {
                LoginAuthentication.this.RememberBtn.setIcon(CihMBanking.theme.getImage("RememberOff.png"));
            } else {
                LoginAuthentication.this.RememberBtn.setIcon(CihMBanking.theme.getImage("RememberOn.png"));
            }
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ B3GUiManager val$uIManager;

        8(B3GUiManager b3GUiManager) {
            this.val$uIManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$uIManager.btnBackOffline.getListeners() != null) {
                this.val$uIManager.btnBackOffline.getListeners().clear();
            }
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
            button.addActionListener(new 9(b3GTextField2, str2, b3GTextField));
            container.addComponent(button);
        }
        return container;
    }

    class 9 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ B3GTextField val$PassField;
        final /* synthetic */ String val$number;

        9(B3GTextField b3GTextField, String str, B3GTextField b3GTextField2) {
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
        Stroke stroke = new Stroke(3.0f, 1, 1, 3.0f);
        Button button = new Button();
        button.setUIID("EmptyContainer");
        Container container = new Container();
        TableLayout tableLayout = new TableLayout(1, 3);
        tableLayout.setGrowHorizontally(true);
        int i = 4;
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
            button2.setVerticalAlignment(i);
            button2.setAlignment(i);
            button2.setFocusable(false);
            label.setVerticalAlignment(i);
            button2.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(0).shadowSpread(40).color(3947067).strokeColor(16777215).stroke(stroke));
            button2.getPressedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(255).shadowSpread(40).color(15818018).strokeColor(15818018).stroke(stroke));
            button2.getAllStyles().setMargin(0, 0, 2, 2);
            label.getAllStyles().setMargin(2, 2, 2, 2);
            button2.getAllStyles().setPadding(2, 2, 2, 2);
            button2.getAllStyles().setMarginUnit(1);
            button2.getAllStyles().setPaddingUnit(2);
            button2.setVerticalAlignment(4);
            button2.setFocusable(false);
            button2.addActionListener(new 10(b3GTextField2, str2, b3GTextField));
            arrayList.add(button2);
            i = 4;
        }
        tableLayout.createConstraint().setWidthPercentage(25);
        Button button3 = new Button("");
        button3.setIcon(CihMBanking.theme.getImage("Delett.png"));
        button3.setUIID("VKBBtnNewVs");
        button3.setVerticalAlignment(4);
        button3.setAlignment(4);
        button3.setFocusable(false);
        button3.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(0).shadowSpread(40).color(3947067).strokeColor(16777215));
        button3.getPressedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(255).shadowSpread(40).color(15818018).strokeColor(15818018));
        button3.getAllStyles().setMargin(0.3f, 0.3f, 2.0f, 2.0f);
        button3.getAllStyles().setPadding(2.2f, 2.0f, 2.0f, 2.0f);
        button3.getAllStyles().setMarginUnit(1);
        button3.getAllStyles().setPaddingUnit(1);
        button3.setVerticalAlignment(4);
        button3.setAlignment(4);
        button3.setFocusable(false);
        Button button4 = new Button("");
        button3.addActionListener(new 11(b3GTextField, b3GTextField2));
        button4.addActionListener(new 12(b3GTextField, b3GTextField2));
        button4.setIcon(CihMBanking.theme.getImage("NewDelete3.png"));
        button4.setUIID("VKBBtnNewVs");
        button4.setVerticalAlignment(4);
        button4.setAlignment(4);
        button4.setFocusable(false);
        button4.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(0).shadowSpread(40).color(3947067).strokeColor(16777215));
        button4.getPressedStyle().setBorder(RoundBorder.create().rectangle(false).opacity(255).shadowSpread(40).color(15818018).strokeColor(15818018));
        button4.getAllStyles().setMargin(0.4f, 0.4f, 2.0f, 2.0f);
        button4.getAllStyles().setPadding(2.2f, 1.0f, 2.0f, 2.0f);
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

    class 10 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ B3GTextField val$PassField;
        final /* synthetic */ String val$number;

        10(B3GTextField b3GTextField, String str, B3GTextField b3GTextField2) {
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

    class 11 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ B3GTextField val$PassField;

        11(B3GTextField b3GTextField, B3GTextField b3GTextField2) {
            this.val$PassField = b3GTextField;
            this.val$LoginField = b3GTextField2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$PassField.isIsTextFieldFocused()) {
                if (this.val$PassField.getTextField().getText().length() > 0) {
                    this.val$PassField.getTextField().setText("");
                    actionEvent.consume();
                    return;
                }
                return;
            }
            if (!this.val$LoginField.isIsTextFieldFocused() || this.val$LoginField.getTextField().getText().length() <= 0) {
                return;
            }
            this.val$LoginField.getTextField().setText("");
            actionEvent.consume();
        }
    }

    class 12 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ B3GTextField val$PassField;

        12(B3GTextField b3GTextField, B3GTextField b3GTextField2) {
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

    public static void openKeyboard(B3GUiManager b3GUiManager) {
        keyBoard.setPreferredH(keyboardHeight);
        loginFields.setScrollVisible(false);
        cntKybrd.setScrollVisible(false);
        keyBoard.getParent().animateLayout(300);
        new UITimer(new LoginAuthentication$$ExternalSyntheticLambda0()).schedule(500, false, b3GUiManager.currentForm);
        cntKybrd.addScrollListener(new 13());
    }

    static /* synthetic */ void lambda$openKeyboard$1() {
        loginFields.setScrollVisible(false);
    }

    class 13 implements ScrollListener {
        13() {
        }

        public void scrollChanged(int i, int i2, int i3, int i4) {
            LoginAuthentication.cntKybrd.setScrollVisible(true);
        }
    }

    static Container getCntLoginFromTab(Container container, int i) {
        Tabs tabs = (Tabs) container.getComponentAt(2);
        System.err.println("///" + tabs.getComponentCount() + " " + tabs.getSelectedIndex());
        return (Container) ((Container) tabs.getTabComponentAt(i)).getComponentAt(0);
    }

    static TextField getTextFieldLogin(Container container) {
        return (TextField) ((Container) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(0)).getComponentAt(1)).getComponentAt(1);
    }

    static Container getTextFieldLoginCnt(Container container) {
        return (Container) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(0)).getComponentAt(1);
    }

    static Button getfakeBtn(Container container) {
        return (Button) ((Container) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(0)).getComponentAt(1)).getComponentAt(0);
    }

    static Button getfakeBtn2(Container container) {
        return (Button) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
    }

    static Button getDelUserBtn(Container container) {
        Button button = (Button) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(1)).getComponentAt(0);
        button.setIcon(CihMBanking.theme.getImage("DeleteTheUser.png"));
        return button;
    }

    static Container getInfoUserCnt(Container container) {
        return (Container) ((Container) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(0)).getComponentAt(1)).getComponentAt(1);
    }

    static Label getFullnameLbl(Container container) {
        return (Label) ((Container) ((Container) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(0)).getComponentAt(1)).getComponentAt(2)).getComponentAt(0);
    }

    static Label getRadicalLbl(Container container) {
        return (Label) ((Container) ((Container) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(0)).getComponentAt(1)).getComponentAt(2)).getComponentAt(1);
    }

    Container DrawListContainer(B3GUiManager b3GUiManager, String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2, Component component3) {
        Container GetContainer = GetContainer(b3GUiManager, str, str2);
        Image image = b3GUiManager.ressource.getImage("round-mask.png");
        Image createImage = Image.createImage(image.getWidth(), image.getHeight(), 12569042);
        boolean z = false;
        EncodedImage.createFromImage(createImage, false);
        Image createImage2 = Image.createImage(createImage.getWidth(), createImage.getHeight(), -16777216);
        Graphics graphics = createImage2.getGraphics();
        graphics.setColor(16777215);
        graphics.fillArc(0, 0, createImage.getWidth(), createImage.getHeight(), 0, 360);
        URLImage.createMaskAdapter(createImage2);
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList.size() > 0) {
            Tabs tabs = new Tabs();
            int size = arrayList.size();
            int i3 = (size / i) + (size % i);
            int i4 = 0;
            while (i4 < i3) {
                Container container = new Container(new BoxLayout(2));
                int i5 = i4 * i;
                int i6 = i5;
                while (i6 < Math.min(size - i5, i) + i5) {
                    container.setScrollableY(z);
                    container.setTensileDragEnabled(z);
                    if (i6 != i5) {
                        container.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                    }
                    Container container2 = container;
                    Container DrawItem = ((LoginAuthentication) arrayList.get(i6)).DrawItem(b3GUiManager, b3GUiManager.stateMachine, b3GUiManager.ressource, i2, null, null, null);
                    container2.addComponent(DrawItem);
                    container2.setName(DrawItem.getName());
                    container2.revalidate();
                    i6++;
                    container = container2;
                    i4 = i4;
                    i5 = i5;
                    z = false;
                }
                tabs.addTab(str2, container);
                i4++;
                z = false;
            }
            tabs.hideTabs();
            tabs.setUIID("g_TabsV11");
            if (arrayList.size() == i) {
                tabs.setSwipeActivated(false);
            } else {
                tabs.setSwipeActivated(true);
            }
            GetContainer.addComponent(tabs);
            if (i3 > 1) {
                b3GUiManager.stateMachine.findCntHeaderPagerV2(GetContainer).removeAll();
                for (int i7 = 0; i7 < i3; i7++) {
                    Button button = new Button();
                    button.setIcon(b3GUiManager.ressource.getImage("slider_arrowRed_w.png"));
                    button.setDisabledIcon(b3GUiManager.ressource.getImage("slider_arrowRed.png"));
                    button.setUIID("gb_btnDot");
                    if (i7 == 0) {
                        button.setEnabled(true);
                    } else {
                        button.setEnabled(false);
                    }
                    b3GUiManager.stateMachine.findCntHeaderPagerV2(GetContainer).addComponent(button);
                }
                tabs.addSelectionListener(new 14(i3, b3GUiManager, GetContainer, arrayList, tabs));
            }
        } else {
            GetContainer.addComponent(DrawItem(b3GUiManager, b3GUiManager.stateMachine, b3GUiManager.ressource, i2, null, null, null));
        }
        return GetContainer;
    }

    class 14 implements SelectionListener {
        final /* synthetic */ Container val$cntGloableHeader;
        final /* synthetic */ ArrayList val$dataList;
        final /* synthetic */ Tabs val$tab;
        final /* synthetic */ int val$totalContainers;
        final /* synthetic */ B3GUiManager val$uiManager;

        14(int i, B3GUiManager b3GUiManager, Container container, ArrayList arrayList, Tabs tabs) {
            this.val$totalContainers = i;
            this.val$uiManager = b3GUiManager;
            this.val$cntGloableHeader = container;
            this.val$dataList = arrayList;
            this.val$tab = tabs;
        }

        public void selectionChanged(int i, int i2) {
            Image image;
            boolean z;
            if (this.val$totalContainers == 1) {
                this.val$uiManager.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).removeAll();
            } else {
                for (int i3 = 0; i3 < this.val$uiManager.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentCount(); i3++) {
                    if (i == i3) {
                        this.val$uiManager.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    } else if (i2 == i3) {
                        this.val$uiManager.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(true);
                    } else {
                        this.val$uiManager.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    }
                }
            }
            Container cntLoginFromTab = LoginAuthentication.getCntLoginFromTab(LoginAuthentication.infoUserCnt, i2);
            LoginAuthentication.loginTxtFieldCnt = LoginAuthentication.getTextFieldLoginCnt(cntLoginFromTab);
            LoginAuthentication.this.LoginField = LoginAuthentication.getTextFieldLogin(cntLoginFromTab);
            if (CihMBanking.RTL) {
                LoginAuthentication.this.LoginField.setRTL(true);
            } else {
                LoginAuthentication.this.LoginField.setRTL(false);
            }
            LoginAuthentication.fakeBtn2 = LoginAuthentication.getfakeBtn2(cntLoginFromTab);
            LoginAuthentication.delUserBtn = LoginAuthentication.getDelUserBtn(cntLoginFromTab);
            LoginAuthentication.radicalLbl = LoginAuthentication.getRadicalLbl(cntLoginFromTab);
            LoginAuthentication.fullnameLbl = LoginAuthentication.getFullnameLbl(cntLoginFromTab);
            LoginAuthentication.loginTxt = new B3GTextField(LoginAuthentication.this.LoginField, LoginAuthentication.loginTxtFieldCnt);
            LoginAuthentication.passTxt.getTextField().clear();
            LoginAuthentication.fakeBtn1.setIcon(CihMBanking.theme.getImage("PasswordV1.png"));
            LoginAuthentication.PassTx.setHint("Mot de passe");
            LoginAuthentication.PassTx.setText("");
            LoginAuthentication.PassTx.getHintLabel().setUIID("Lbl_lato_Meduim_18");
            LoginAuthentication.passTxt.setIsTextFieldFocused(false);
            if (LoginAuthentication.this.LoginField.getText().length() <= 0 && LoginAuthentication.radicalLbl.isHidden()) {
                LoginAuthentication.this.LoginField.getHintLabel().setUIID("Lbl_lato_Meduim_18");
                LoginAuthentication.loginTxt.getTextField().getHintLabel().setUIID("Lbl_lato_Meduim_18");
                LoginAuthentication.this.LoginField.setHint("Utilisateur");
                LoginAuthentication.delUserBtn.setIcon(CihMBanking.theme.getImage("help.png"));
            } else {
                LoginAuthentication.delUserBtn.setIcon(CihMBanking.theme.getImage("DeleteTheUser.png"));
            }
            for (int i4 = 0; i4 < this.val$dataList.size(); i4++) {
                if (LoginAuthentication.fullnameLbl.getText().equals(((LoginAuthentication) this.val$dataList.get(i4)).NameSvd)) {
                    LoginAuthentication.this.RadicalSvd = ((LoginAuthentication) this.val$dataList.get(i4)).RadicalSvdL;
                    CihMBanking.sesPMTR.setStoredRadical(LoginAuthentication.this.RadicalSvd);
                }
            }
            try {
                image = Image.createImage(FileSystemStorage.getInstance().openInputStream(FileSystemStorage.getInstance().getAppHomePath() + LoginAuthentication.this.RadicalSvd + ".jpg"));
            } catch (Exception unused) {
                image = null;
            }
            LoginAuthentication.this.setProfilPict(this.val$uiManager, image, LoginAuthentication.fakeBtn3);
            Vector vector = new Vector();
            if (Storage.getInstance().exists("veclistCnxStorage")) {
                Vector vector2 = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
                int i5 = 0;
                while (true) {
                    if (i5 >= vector2.size()) {
                        z = false;
                        break;
                    }
                    if (CihMBanking.sesPMTR.getStoredRadical().equals(((AccountLastConnection) vector2.elementAt(i5)).radical)) {
                        z = true;
                        break;
                    }
                    i5++;
                }
                if (z && LoginAuthentication.pTouchIDNativeCall.isSupported() && Fingerprint.isAvailable()) {
                    if (!LoginAuthentication.fullnameLbl.isHidden() && !LoginAuthentication.radicalLbl.isHidden()) {
                        LoginAuthentication.sendCnt.removeAll();
                        LoginAuthentication.sendCnt.setLayout(LoginAuthentication.this.Layouttbl);
                        LoginAuthentication.sendBtn.getAllStyles().setMargin(0.8f, 2.7f, 0.5f, 1.3f);
                        LoginAuthentication.sendCnt.add(LoginAuthentication.this.Layouttbl.createConstraint().widthPercentage(80), LoginAuthentication.sendBtn).add(LoginAuthentication.this.Layouttbl.createConstraint().widthPercentage(20), LoginAuthentication.touchIDBtn);
                        LoginAuthentication.sendCnt.revalidate();
                    } else {
                        LoginAuthentication.sendCnt.removeAll();
                        LoginAuthentication.sendCnt.setLayout(new GridLayout(1, 1));
                        LoginAuthentication.sendBtn.getAllStyles().setMargin(0, 2, 5, 5);
                        LoginAuthentication.sendCnt.add(LoginAuthentication.sendBtn);
                        LoginAuthentication.sendCnt.revalidate();
                    }
                } else {
                    LoginAuthentication.sendCnt.removeAll();
                    LoginAuthentication.sendCnt.setLayout(new GridLayout(1, 1));
                    LoginAuthentication.sendBtn.getAllStyles().setMargin(0, 2, 5, 5);
                    LoginAuthentication.sendCnt.add(LoginAuthentication.sendBtn);
                    LoginAuthentication.sendCnt.revalidate();
                }
            } else {
                Storage.getInstance().writeObject("veclistCnxStorage", vector);
                LoginAuthentication.sendCnt.removeAll();
                LoginAuthentication.sendCnt.setLayout(new GridLayout(1, 1));
                LoginAuthentication.sendBtn.getAllStyles().setMargin(0, 2, 5, 5);
                LoginAuthentication.sendCnt.add(LoginAuthentication.sendBtn);
                LoginAuthentication.touchIDBtn.setUIID("sendBtnNew");
                LoginAuthentication.sendCnt.revalidate();
            }
            if (LoginAuthentication.fakeBtn2.isHidden()) {
                LoginAuthentication.fakeBtn3.setHidden(false);
            } else {
                LoginAuthentication.fakeBtn3.setHidden(true);
                LoginAuthentication.fakeBtn2.setIcon(CihMBanking.theme.getImage("User3.png"));
            }
            LoginAuthentication.fakeBtn1.setIcon(CihMBanking.theme.getImage("PasswordV1.png"));
            this.val$tab.getComponentAt(0).getAllStyles().setBgTransparency(0);
            this.val$tab.getParent().getParent().revalidate();
        }
    }

    Container GetContainer(B3GUiManager b3GUiManager, String str, String str2) {
        Container createContainer = b3GUiManager.stateMachine.createContainer(b3GUiManager.ressource, str);
        if (str.equals("GloabalContainerLogin")) {
            b3GUiManager.stateMachine.findLblGlobalHeaderTitle(createContainer).setText(str2);
        }
        return createContainer;
    }

    public Container GetCntBorderSeparator(String str) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID(str);
        return container;
    }

    public Container DrawItem(B3GUiManager b3GUiManager, StateMachine stateMachine, Resources resources, int i, B3gService b3gService, Component component, Component component2) {
        Image image;
        UIBuilder uIBuilder = new UIBuilder();
        if (i == 89) {
            try {
                Image image2 = b3GUiManager.ressource.getImage("round-mask.png");
                Image createImage = Image.createImage(image2.getWidth(), image2.getHeight(), 12569042);
                EncodedImage.createFromImage(createImage, false);
                Image createImage2 = Image.createImage(createImage.getWidth(), createImage.getHeight(), -16777216);
                Graphics graphics = createImage2.getGraphics();
                graphics.setColor(16777215);
                graphics.fillArc(0, 0, createImage.getWidth(), createImage.getHeight(), 0, 360);
                URLImage.createMaskAdapter(createImage2);
                LoginAuthentication loginAuthentication = this;
                Container createContainer = stateMachine.createContainer(resources, "loginCntNew");
                Label label = (Label) uIBuilder.findByName("radicalLbl", createContainer);
                Label label2 = (Label) uIBuilder.findByName("fullnameLbl", createContainer);
                fakeBtn2 = (Button) uIBuilder.findByName("fakeBtn2", createContainer);
                fakeBtn = (Button) uIBuilder.findByName("fakeBtn", createContainer);
                fakeBtn2.setIcon(CihMBanking.theme.getImage("User3.png"));
                loginTxtFieldCnt = (Container) uIBuilder.findByName("loginTxtField", createContainer);
                this.LoginField = (TextField) uIBuilder.findByName("LoginTxt", createContainer);
                if (CihMBanking.RTL) {
                    this.LoginField.setRTL(true);
                } else {
                    this.LoginField.setRTL(false);
                }
                Button button = (Button) uIBuilder.findByName("delUserBtn", createContainer);
                fakeBtn2.setHidden(true);
                this.LoginField.setCursorBlinkTimeOff(999999);
                loginTxt = new B3GTextField(this.LoginField, loginTxtFieldCnt);
                new Vector();
                if (Storage.getInstance().exists("RadicalSvd")) {
                    Vector vector = (Vector) Storage.getInstance().readObject("RadicalSvd");
                    this.RadicalSvdL = this.RadicalSvdL;
                    String str = "******" + this.RadicalSvdL.charAt(6);
                    label2.setText("");
                    label.setText(str);
                    if (vector.size() == 2) {
                        label2.setText((String) vector.elementAt(1));
                    } else if (vector.size() > 2) {
                        for (int i2 = 0; i2 < vector.size(); i2++) {
                            if (((String) vector.get(i2)).equals(this.RadicalSvdL)) {
                                label2.setText(this.NameSvd);
                            }
                        }
                    }
                    createContainer.setName(this.NameSvd);
                    button.addActionListener(new 15(button, this, b3GUiManager));
                } else {
                    createContainer.setHidden(true);
                    label2.setHidden(true);
                    label.setHidden(true);
                }
                if (Storage.getInstance().exists("RadicalSvd")) {
                    try {
                        image = Image.createImage(FileSystemStorage.getInstance().openInputStream(FileSystemStorage.getInstance().getAppHomePath() + this.RadicalSvdL + ".jpg"));
                    } catch (Exception unused) {
                        image = null;
                    }
                    setProfilPict(b3GUiManager, image, fakeBtn3);
                }
                return createContainer;
            } catch (Exception unused2) {
                return new Container();
            }
        }
        return new Container();
    }

    class 15 implements ActionListener {
        final /* synthetic */ LoginAuthentication val$auth;
        final /* synthetic */ Button val$delUserBtn;
        final /* synthetic */ B3GUiManager val$uIManager;

        15(Button button, LoginAuthentication loginAuthentication, B3GUiManager b3GUiManager) {
            this.val$delUserBtn = button;
            this.val$auth = loginAuthentication;
            this.val$uIManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$delUserBtn.getIcon().getImageName().equals("DeleteTheUser.png")) {
                LoginAuthentication loginAuthentication = LoginAuthentication.this;
                loginAuthentication.DeleteFromStorage(loginAuthentication.RadicalSvdL, this.val$auth.NameSvd);
                this.val$uIManager.stateMachine.showForm("LoginV2New", (Command) null);
                LoginAuthentication.sendCnt.removeAll();
                LoginAuthentication.sendCnt.setLayout(new GridLayout(1, 1));
                LoginAuthentication.sendBtn.getAllStyles().setMargin(0, 2, 5, 5);
                LoginAuthentication.sendCnt.add(LoginAuthentication.sendBtn);
                return;
            }
            LoginAuthentication.this.ShowHelpDialog();
        }
    }

    void setProfilPict(B3GUiManager b3GUiManager, Image image, Button button) {
        b3GUiManager.ressource.getImage("round-mask.png");
        fakeBtn2.setIcon(CihMBanking.theme.getImage("User3.png"));
        fakeBtn3.setHidden(true);
        fakeBtn2.setHidden(false);
    }

    public boolean isPremier(int i) {
        if (i < 0) {
            return false;
        }
        if (i == 2) {
            return false;
        }
        if (i != 0 && i != 1) {
            for (int i2 = 2; i2 <= i / 2; i2++) {
                if (i != i2 && i % i2 == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void DeleteFromStorage(String str, String str2) {
        new Vector();
        Vector vector = (Vector) Storage.getInstance().readObject("RadicalSvd");
        int i = 0;
        for (int i2 = 0; i2 < vector.size(); i2 = i2 + 1 + 1) {
            int i3 = i + 1;
            if (((String) vector.get(i2)).equals(str) && ((String) vector.get(i3)).equals(str2)) {
                vector.remove(vector.get(i2));
                vector.remove(vector.get(i2));
                Storage.getInstance().writeObject("RadicalSvd", vector);
                if (vector.size() == 0) {
                    Storage.getInstance().deleteStorageFile("RadicalSvd");
                    return;
                }
                return;
            }
            i = i3 + 1;
        }
    }

    public void ShowHelpDialog() {
        Dialog dialog = new Dialog();
        Container createContainer = this.uib.createContainer("/cihv3", "3DInfosCnt");
        dialog.setLayout(new BorderLayout());
        dialog.getAllStyles().setBgTransparency(0);
        dialog.add("Center", createContainer);
        dialog.setDialogUIID("Container");
        dialog.setDisposeWhenPointerOutOfBounds(true);
        dialog.setBackCommand(new 16("Back", dialog));
        dialog.show(fullnameLbl.getAbsoluteX(), Display.getInstance().getDisplayHeight() - ((createContainer.getPreferredH() + infoUserCnt.getPreferredH()) + 200), 20, 20);
    }

    class 16 extends Command {
        final /* synthetic */ Dialog val$dlg;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        16(String str, Dialog dialog) {
            super(str);
            this.val$dlg = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$dlg.dispose();
        }
    }
}
