package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.interfaces.TextFieldListener;
import com.b3g.services.B3gService;
import com.b3g.tools.DataTools;
import com.b3g.tools.NativeCall;
import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizard;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.Step;
import com.b3g.ui.page.cih.Module.AccountLastConnection;
import com.b3g.ui.page.cih.Module.LoginAuthentication;
import com.b3g.ui.page.cih.Module.PopUpsManager;
import com.codename1.fingerprint.Fingerprint;
import com.codename1.io.Storage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Sheet;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AuthenticationPage extends B3GPage implements TextFieldListener {
    static int incr = 0;
    public static Container infoUserCnt = null;
    public static boolean is_open = false;
    public static Sheet keyboardDialog;
    static int keyboardHeight;
    static TouchIDNativeCall pTouchIDNativeCall;
    TableLayout Layouttbl;
    public String NameSvd;
    public String RadicalSvdL;
    Button RememberBtn;
    AccountLastConnection acc;
    NativeCall cihPayNativeCall;
    private Container cntGloableHeader;
    private Button forgot_password;
    private Label header;
    private Button help;
    private ActionListener key_listener;
    private Container keyboardContainer;
    private TableLayout keyboard_layout;
    private ActionListener listener;
    private Container login_content;
    private Container login_wrapper;
    private Container main_login_wrapper;
    B3GWizard menuWizard;
    private TextField password_field;
    String radicalClient;
    private Button remember;
    private Step s1;
    private Step s2;
    private Step s3;
    private Container services_container;
    private Container sessCnt;
    private int step1_height;
    private TableLayout tl_login;
    private Button touch;
    private Container touch_cnt;
    private Container touch_container;
    private TextField username;
    private TextField username_empty_field;
    private TextField username_field;
    private Container wrapper_login;
    Vector vecStoragePic = new Vector();
    Vector vecStorageClientSex = new Vector();
    boolean isSwitchToAnotherRdc = false;
    UIBuilder uib = new UIBuilder();
    private Integer active_container_index = 0;
    private List removed_container_indexes = new ArrayList();
    private boolean is_services_container = true;
    private Tabs tab = new Tabs();
    ArrayList radicaux = new ArrayList();
    ArrayList has_touch_id = new ArrayList();
    private boolean show_touch = false;
    private boolean isRadicalActive = false;
    private boolean isPasswordActive = false;
    private boolean isContainer1Visible = true;
    private boolean done_connect = false;
    private Vector list_cnx = new Vector();

    public void handleTextFieldFocus(String str) {
    }

    static /* synthetic */ Integer access$000(AuthenticationPage authenticationPage) {
        return authenticationPage.active_container_index;
    }

    static /* synthetic */ Integer access$002(AuthenticationPage authenticationPage, Integer num) {
        authenticationPage.active_container_index = num;
        return num;
    }

    static /* synthetic */ ActionListener access$100(AuthenticationPage authenticationPage) {
        return authenticationPage.listener;
    }

    static /* synthetic */ TextField access$1000(AuthenticationPage authenticationPage) {
        return authenticationPage.password_field;
    }

    static /* synthetic */ void access$1100(AuthenticationPage authenticationPage) {
        authenticationPage.checkTouchIdIsAvailable();
    }

    static /* synthetic */ Container access$1200(AuthenticationPage authenticationPage) {
        return authenticationPage.cntGloableHeader;
    }

    static /* synthetic */ Container access$1300(AuthenticationPage authenticationPage) {
        return authenticationPage.login_content;
    }

    static /* synthetic */ TextField access$1400(AuthenticationPage authenticationPage) {
        return authenticationPage.username_field;
    }

    static /* synthetic */ TextField access$1402(AuthenticationPage authenticationPage, TextField textField) {
        authenticationPage.username_field = textField;
        return textField;
    }

    static /* synthetic */ void access$1500(AuthenticationPage authenticationPage, Container container, Container container2, Container container3) {
        authenticationPage.switchContainersWithAnimation(container, container2, container3);
    }

    static /* synthetic */ boolean access$1600(AuthenticationPage authenticationPage) {
        return authenticationPage.isContainer1Visible;
    }

    static /* synthetic */ Container access$1700(AuthenticationPage authenticationPage) {
        return authenticationPage.main_login_wrapper;
    }

    static /* synthetic */ void access$1800(AuthenticationPage authenticationPage) {
        authenticationPage.slideIn();
    }

    static /* synthetic */ void access$1900(AuthenticationPage authenticationPage, String str, TextField textField, boolean z) {
        authenticationPage.write_to_textfield(str, textField, z);
    }

    static /* synthetic */ Step access$200(AuthenticationPage authenticationPage) {
        return authenticationPage.s1;
    }

    static /* synthetic */ void access$2000(AuthenticationPage authenticationPage, boolean z) {
        authenticationPage.handleLogin(z);
    }

    static /* synthetic */ Container access$300(AuthenticationPage authenticationPage) {
        return authenticationPage.sessCnt;
    }

    static /* synthetic */ boolean access$402(AuthenticationPage authenticationPage, boolean z) {
        authenticationPage.isRadicalActive = z;
        return z;
    }

    static /* synthetic */ boolean access$502(AuthenticationPage authenticationPage, boolean z) {
        authenticationPage.isPasswordActive = z;
        return z;
    }

    static /* synthetic */ void access$600(AuthenticationPage authenticationPage) {
        authenticationPage.createCustomKeyboardDialog();
    }

    static /* synthetic */ boolean access$700(AuthenticationPage authenticationPage) {
        return authenticationPage.done_connect;
    }

    static /* synthetic */ boolean access$702(AuthenticationPage authenticationPage, boolean z) {
        authenticationPage.done_connect = z;
        return z;
    }

    static /* synthetic */ TextField access$800(AuthenticationPage authenticationPage) {
        return authenticationPage.username_empty_field;
    }

    static /* synthetic */ void access$900(AuthenticationPage authenticationPage, boolean z, TextField textField) {
        authenticationPage.openKeyboard(z, textField);
    }

    public AuthenticationPage(Object obj, B3gService b3gService, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = b3gService;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        CihMBanking.sesPMTR.getSessionSavedContainer();
        this.uiManager.CurrentPageId = 190;
        this.thisContainer = this.uiManager.stateMachine.findTabsCnt();
        if (this.thisContainer != null) {
            this.thisContainer.removeAll();
            this.uiManager.stateMachine.findTabsCnt().revalidate();
        } else {
            this.thisContainer = new Container(new GridLayout(1, 1));
        }
        this.username_field = new TextField();
        this.username_empty_field = new TextField();
        this.password_field = new TextField();
        this.touch = new Button();
        Container container = new Container();
        this.touch_cnt = container;
        container.setHidden(true);
        this.login_content = new Container();
        Step step = new Step();
        this.s1 = step;
        step.icon = "1inactif.png 1";
        this.s1.selectedIcon = "1actif.png 1";
        this.s1.stepCnt = getStep1();
        Step step2 = new Step();
        this.s2 = step2;
        step2.icon = "2inactif.png 1";
        this.s2.selectedIcon = "2actif.png 1";
        this.s2.stepCnt = getStep2();
        if (!Display.getInstance().isSimulator()) {
            pTouchIDNativeCall = (TouchIDNativeCall) NativeLookup.create(TouchIDNativeCall.class);
        }
        this.login_wrapper = new Container();
        if (!Display.getInstance().isSimulator()) {
            pTouchIDNativeCall = (TouchIDNativeCall) NativeLookup.create(TouchIDNativeCall.class);
        }
        if (!Display.getInstance().isSimulator()) {
            if (pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                this.touch.setIcon(this.uiManager.ressource.getImage("Biometrie_ID.png"));
            } else if (pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
                this.touch.setIcon(this.uiManager.ressource.getImage("Empreinte_ID.png"));
            } else if (pTouchIDNativeCall.getBiometryType().equals("FACEID")) {
                this.touch.setIcon(this.uiManager.ressource.getImage("face_id.png"));
            }
        } else {
            this.touch.setIcon(this.uiManager.ressource.getImage("Biometrie_ID.png"));
        }
        Vector vector = new Vector();
        if (Storage.getInstance().exists("RadicalSvd")) {
            vector = (Vector) Storage.getInstance().readObject("RadicalSvd");
        }
        if (Storage.getInstance().exists("RadicalSvd") && vector.size() > 0) {
            this.radicaux = new ArrayList();
            int i = 0;
            for (int i2 = 0; i2 < vector.size(); i2 = i2 + 1 + 1) {
                LoginAuthentication loginAuthentication = new LoginAuthentication();
                loginAuthentication.RadicalSvdL = (String) vector.get(i2);
                int i3 = i + 1;
                loginAuthentication.NameSvd = (String) vector.get(i3);
                i = i3 + 1;
                this.radicaux.add(loginAuthentication);
            }
            Collections.reverse(this.radicaux);
        }
        checkTouchIdIsAvailable();
        this.cihPayNativeCall = (NativeCall) NativeLookup.create(NativeCall.class);
        boolean z = !Display.getInstance().isSimulator() && !Display.getInstance().getPlatformName().equals("ios") && this.cihPayNativeCall.isSupported() && this.cihPayNativeCall.hasTokenizedCards();
        TableLayout tableLayout = new TableLayout(6, 1);
        Container container2 = new Container(tableLayout);
        container2.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(8), new Container(new FlowLayout())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(50), drawLoginSection()).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(6), z ? drawCihPayButton() : new Container(new FlowLayout())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(3), new Container(new FlowLayout())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(30), drawServicesSection());
        createCustomKeyboardDialog();
        if (!DataTools.checkConnection()) {
            new PopUpsManager(this.uiManager).showNoConnectoinMessg();
        } else {
            new Vector();
            if (Storage.getInstance().exists("veclistCnxStorage")) {
                Vector vector2 = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                int i4 = 0;
                while (true) {
                    if (i4 >= vector2.size()) {
                        break;
                    }
                    if (((LoginAuthentication) this.radicaux.get(this.active_container_index.intValue())).RadicalSvdL.equals(((AccountLastConnection) vector2.elementAt(i4)).radical)) {
                        this.acc = new AccountLastConnection(((LoginAuthentication) this.radicaux.get(this.active_container_index.intValue())).RadicalSvdL, simpleDateFormat.format(date), 0);
                        break;
                    }
                    i4++;
                }
            }
        }
        this.listener = new 1();
        this.uiManager.currentForm.setBackCommand(new 2("Back"));
        this.sessCnt = container2;
        this.thisContainer.add(container2);
        CihMBanking.sesPMTR.setSessionSavedContainer(container2);
        CihMBanking.sesPMTR.setPreloginStep(1);
        CihMBanking.sesPMTR.setBtnListener(this.listener);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.this.Previous();
        }
    }

    class 2 extends Command {
        2(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.this.Previous();
        }
    }

    private Container drawLoginButtonWithTouch() {
        this.main_login_wrapper = new Container(new BorderLayout());
        Button button = new Button("Connexion");
        button.addActionListener(new AuthenticationPage$$ExternalSyntheticLambda0(this));
        this.touch.addActionListener(new 3());
        Container roundedFilledButtonStyle = setRoundedFilledButtonStyle(button, null, 15752481);
        roundedFilledButtonStyle.getAllStyles().setMarginUnit(PreLoginPage.b);
        roundedFilledButtonStyle.getAllStyles().setMargin(0.0f, 0.0f, 4.0f, 4.0f);
        this.main_login_wrapper.add("Center", roundedFilledButtonStyle);
        Container drawFingerprintIcon = drawFingerprintIcon(this.touch);
        this.touch_cnt = drawFingerprintIcon;
        drawFingerprintIcon.setHidden(!this.show_touch);
        this.main_login_wrapper.add("East", this.touch_cnt);
        return this.main_login_wrapper;
    }

    /* synthetic */ void lambda$drawLoginButtonWithTouch$0$com-b3g-ui-page-AuthenticationPage(ActionEvent actionEvent) {
        handleLogin(false);
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String msgTouchID;
            if (Display.getInstance().isSimulator()) {
                return;
            }
            TouchIDNativeCall touchIDNativeCall = (TouchIDNativeCall) NativeLookup.create(TouchIDNativeCall.class);
            if (touchIDNativeCall.isSupported()) {
                String str = ((LoginAuthentication) AuthenticationPage.this.radicaux.get(AuthenticationPage.access$000(AuthenticationPage.this).intValue())).RadicalSvdL;
                if (str.length() == 0 || !touchIDNativeCall.isExistPasswordForRadical(str) || !Fingerprint.isAvailable() || CihMBanking.isConnecting.equals("03")) {
                    return;
                }
                if (touchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                    msgTouchID = DataTools.getMsgAndroiBiometric();
                } else {
                    msgTouchID = touchIDNativeCall.getBiometryType().equals("TOUCHID") ? DataTools.getMsgTouchID() : "";
                }
                Fingerprint.scanFingerprint(DataTools.getAvailabeBiometric(touchIDNativeCall.getBiometryType().equals("TOUCHID"), touchIDNativeCall.getBiometryType().equals("FACEID"), touchIDNativeCall.getBiometryType().equals("FINGERPRINT")), msgTouchID, DataTools.getNamCancelBtn(), new AuthenticationPage$3$$ExternalSyntheticLambda0(this, str, touchIDNativeCall), new AuthenticationPage$3$$ExternalSyntheticLambda1(touchIDNativeCall), false);
            }
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-AuthenticationPage$3(String str, TouchIDNativeCall touchIDNativeCall, Object obj) {
            AuthenticationPage.this.uiManager.stateMachine.authentificationNew(str, touchIDNativeCall.getPasswordForRadical(str), true, AuthenticationPage.this.RememberBtn);
        }

        static /* synthetic */ void lambda$actionPerformed$1(TouchIDNativeCall touchIDNativeCall, Object obj, Throwable th, int i, String str) {
            if (Display.getInstance().isSimulator()) {
                return;
            }
            touchIDNativeCall.getBiometryType().equals("FINGERPRINT");
        }
    }

    private Container drawCihPayButton() {
        Container container = new Container(new GridLayout(1, 1));
        Button button = new Button(CihMBanking.theme.getImage("CiyPay_icn.png"));
        Label label = new Label("Paiement CIH PAY");
        label.getAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(2.5f), 1));
        label.getAllStyles().setBgTransparency(0);
        button.addActionListener(new 4());
        button.setUIID("Container");
        button.getAllStyles().setBgColor(CIHStyles.colorWhite);
        button.getAllStyles().setBgTransparency(0);
        button.getAllStyles().setMarginUnit(PreLoginPage.b);
        button.getAllStyles().setMargin(0.0f, 0.0f, 0.0f, 12.0f);
        RoundRectBorder cornerRadius = RoundRectBorder.create().cornerRadius(2.0f);
        Container container2 = new Container(new BorderLayout());
        container2.add("West", button);
        container2.add("Center", label);
        container2.getAllStyles().setPaddingUnit(PreLoginPage.b);
        container2.getAllStyles().setPadding(0.0f, 0.0f, 6.0f, 6.0f);
        container.getAllStyles().setBorder(cornerRadius);
        container.getAllStyles().setBgColor(CIHStyles.colorWhite);
        container.getAllStyles().setBgTransparency(255);
        container.getAllStyles().setMarginUnit(PreLoginPage.b);
        container.getAllStyles().setPaddingUnit(PreLoginPage.b);
        container.getAllStyles().setMargin(0.0f, 0.0f, 6.0f, 6.0f);
        container.getAllStyles().setPadding(1.0f, 1.0f, 2.0f, 2.0f);
        container.add(container2);
        container.setLeadComponent(button);
        return container;
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (AuthenticationPage.this.cihPayNativeCall.isSupported()) {
                AuthenticationPage.this.cihPayNativeCall.startCihPay();
            }
        }
    }

    private Container setRoundedFilledButtonStyle(Button button, Image image, int i) {
        Container container = new Container(new GridLayout(1, 1));
        button.setUIID("Container");
        Style allStyles = button.getAllStyles();
        allStyles.setPaddingUnit(PreLoginPage.b);
        allStyles.setPadding(1.0f, 1.0f, 1.0f, 1.0f);
        allStyles.setFont(CIHStyles.create_font("Poppins-Regular", "native:MainBold", Float.valueOf(3.0f), 0));
        allStyles.setBorder(RoundRectBorder.create().cornerRadius(1.2f));
        allStyles.setAlignment(4);
        allStyles.setBgColor(i);
        allStyles.setFgColor(16777215);
        allStyles.setBgTransparency(255);
        if (image != null) {
            button.setIcon(image);
            button.getAllStyles().setMarginUnit(PreLoginPage.b);
            button.getAllStyles().setMargin(0.0f, 0.0f, 1.0f, 0.0f);
        }
        container.add(button);
        container.setLeadComponent(button);
        return container;
    }

    public void Previous() {
        if (this.menuWizard.currentStepId != 0) {
            this.menuWizard.goToPreviousStep(this.s2, "", 16777215, 16777215);
            this.uiManager.btnBackOffline.setVisible(false);
            if (this.uiManager.btnBackOffline.getListeners() != null) {
                this.uiManager.btnBackOffline.getListeners().clear();
            }
            CihMBanking.sesPMTR.setSessionSavedContainer(this.sessCnt);
            CihMBanking.sesPMTR.setPreloginStep(1);
            return;
        }
        this.uiManager.GoBackOffligne();
    }

    private Container drawLoginSection() {
        Container container = new Container(new GridLayout(1, 1));
        Label label = new Label("Bienvenue");
        this.header = label;
        label.getAllStyles().setFgColor(CIHStyles.colorWhite);
        this.header.getAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(5.0f), 1));
        TableLayout tableLayout = new TableLayout(5, 1);
        this.login_content = new Container(tableLayout);
        Container drawTransparentBox = PreLoginPage.drawTransparentBox(drawLoginBox(), Integer.valueOf(CIHStyles.colorBlack), 100, 2.0f, 4.0f);
        drawTransparentBox.getAllStyles().setMarginUnit(PreLoginPage.b);
        drawTransparentBox.getAllStyles().setPaddingUnit(PreLoginPage.b);
        drawTransparentBox.getAllStyles().setMarginBottom(0.0f);
        drawTransparentBox.getAllStyles().setPaddingBottom(0.0f);
        this.login_content.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(12), this.header).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(5), new Container(new FlowLayout())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(60), drawTransparentBox).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(5), new Container(new FlowLayout())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(13), drawLoginButtonWithTouch());
        this.login_content.getAllStyles().setPaddingUnit(PreLoginPage.b);
        this.login_content.getAllStyles().setPadding(0.0f, 0.0f, 2.0f, 2.0f);
        container.add(this.login_content);
        return container;
    }

    private Container drawServicesSection() {
        Container container = new Container(new GridLayout(1, 1));
        this.services_container = container;
        container.setName("Services");
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.s1);
        arrayList.add(this.s2);
        B3GWizard b3GWizard = new B3GWizard(arrayList);
        this.menuWizard = b3GWizard;
        b3GWizard.withHeader = false;
        this.menuWizard.setSlidingDirection(false);
        Container drawWizard = this.menuWizard.drawWizard("", 16777215, 0);
        new TableLayout(2, 1);
        Container container2 = new Container(new GridLayout(1, 1));
        container2.add(drawWizard);
        container2.getAllStyles().setMarginUnit(PreLoginPage.b);
        container2.getAllStyles().setMargin(0.0f, 0.0f, 4.0f, 4.0f);
        this.services_container.add(container2);
        return this.services_container;
    }

    private Container getStep1() {
        Container container = new Container(new GridLayout(1, 1));
        Container container2 = new Container();
        Label label = new Label("Nos services");
        label.getAllStyles().setFgColor(CIHStyles.colorWhite);
        label.getAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(3.0f), 1));
        label.getAllStyles().setPaddingUnit(PreLoginPage.b);
        label.getAllStyles().setPadding(0.0f, 1.0f, 0.0f, 0.0f);
        Button button = new Button(this.uiManager.ressource.getImage("large-building-white.png"));
        Button button2 = new Button(this.uiManager.ressource.getImage("security-white.png"));
        Button button3 = new Button(this.uiManager.ressource.getImage("map-pin-white.png"));
        Button button4 = new Button(this.uiManager.ressource.getImage("meeting_room.png"));
        button.addActionListener(new 5());
        button2.addActionListener(new 6());
        button3.addActionListener(new 7());
        button4.addActionListener(new 8());
        Container gridButtonStyle = PreLoginPage.setGridButtonStyle(button, DataTools.getPortailImob());
        Container gridButtonStyle2 = PreLoginPage.setGridButtonStyle(button2, DataTools.getSolutionsAssurances());
        Container gridButtonStyle3 = PreLoginPage.setGridButtonStyle(button3, DataTools.getAgences());
        Container gridButtonStyle4 = PreLoginPage.setGridButtonStyle(button4, DataTools.getAvantages());
        gridButtonStyle.getAllStyles().setMarginUnit(PreLoginPage.b);
        gridButtonStyle.getAllStyles().setMargin(0, 0, 0, 2);
        gridButtonStyle2.getAllStyles().setMarginUnit(PreLoginPage.b);
        gridButtonStyle2.getAllStyles().setMargin(0, 0, 2, 0);
        gridButtonStyle3.getAllStyles().setMarginUnit(PreLoginPage.b);
        gridButtonStyle3.getAllStyles().setMargin(0.0f, 0.0f, 0.0f, 2.0f);
        gridButtonStyle4.getAllStyles().setMarginUnit(PreLoginPage.b);
        gridButtonStyle4.getAllStyles().setMargin(0.0f, 0.0f, 2.0f, 0.0f);
        TableLayout tableLayout = new TableLayout(4, 1);
        container2.setLayout(tableLayout);
        container2.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(10), label).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(2), new Container(new FlowLayout())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(42), GridLayout.encloseIn(2, gridButtonStyle, gridButtonStyle2)).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(4), new Container(BoxLayout.x())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(42), GridLayout.encloseIn(2, gridButtonStyle3, gridButtonStyle4));
        container.add(container2);
        return container;
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.this.uiManager.btnBackOffline.setVisible(true);
            AuthenticationPage.this.uiManager.btnBackOffline.addActionListener(AuthenticationPage.access$100(AuthenticationPage.this));
            AuthenticationPage.this.menuWizard.goToNextStep(AuthenticationPage.access$200(AuthenticationPage.this), "", 16777215, 0);
            CihMBanking.sesPMTR.setPreloginStep(2);
        }
    }

    class 6 implements ActionListener {
        6() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.this.uiManager.NavigateToPageByIdOffligne(168);
        }
    }

    class 7 implements ActionListener {
        7() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.this.uiManager.NavigateToPageByIdOffligne(83);
        }
    }

    class 8 implements ActionListener {
        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.urlAvantageWebView = "https://avantagescartescih.ma/";
            AuthenticationPage.this.uiManager.NavigateToPageByIdOffligne(169);
        }
    }

    private Container getStep2() {
        Container container = new Container(new GridLayout(1, 1));
        Container container2 = new Container();
        Button button = new Button(this.uiManager.ressource.getImage("icons-search.png"));
        button.addActionListener(new 9());
        Button button2 = new Button(this.uiManager.ressource.getImage("icons-sell.png"));
        button2.addActionListener(new 10());
        Button button3 = new Button(this.uiManager.ressource.getImage("Simulation-white.png"));
        Container gridButtonStyle = PreLoginPage.setGridButtonStyle(button, DataTools.getChercheLogement());
        Container gridButtonStyle2 = PreLoginPage.setGridButtonStyle(button2, DataTools.getVendLogement());
        Container gridButtonStyle3 = PreLoginPage.setGridButtonStyle(button3, "Je fais une simulation");
        gridButtonStyle.getAllStyles().setMarginUnit(PreLoginPage.b);
        gridButtonStyle.getAllStyles().setMargin(0, 0, 0, 2);
        gridButtonStyle2.getAllStyles().setMarginUnit(PreLoginPage.b);
        gridButtonStyle2.getAllStyles().setMargin(0, 0, 2, 0);
        TableLayout tableLayout = new TableLayout(3, 1);
        container2.setLayout(tableLayout);
        container2.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(28), new Container(BoxLayout.x())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(45), GridLayout.encloseIn(2, gridButtonStyle, gridButtonStyle2)).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(29), new Container(BoxLayout.x())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(45), GridLayout.encloseIn(2, gridButtonStyle3, new Container()));
        container.add(container2);
        container.setPreferredH(this.step1_height);
        return container;
    }

    class 9 implements ActionListener {
        9() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.setSessionSavedContainer(AuthenticationPage.access$300(AuthenticationPage.this));
            CihMBanking.sesPMTR.urlAvantageWebView = "https://www.yakeey.com/catalog-view/properties?utm_source=cih&referralOrigin=cih";
            AuthenticationPage.this.uiManager.NavigateToPageByIdOffligne(169);
        }
    }

    class 10 implements ActionListener {
        10() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.setSessionSavedContainer(AuthenticationPage.access$300(AuthenticationPage.this));
            CihMBanking.sesPMTR.urlAvantageWebView = "https://www.yakeey.com/sell?utm_source=cih&referralOrigin=cih";
            AuthenticationPage.this.uiManager.NavigateToPageByIdOffligne(169);
        }
    }

    private Container drawLoginBox() {
        Container container = new Container(new BorderLayout());
        Label label = new Label("Authentication");
        label.getAllStyles().setAlignment(1);
        label.getAllStyles().setFgColor(CIHStyles.colorWhite);
        label.getAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(3.5f), 1));
        label.getAllStyles().setBgTransparency(0);
        Container container2 = new Container(BoxLayout.y());
        container2.setScrollableY(false);
        container2.setScrollVisible(false);
        container2.add(label);
        container2.add(this.radicaux.size() > 0 ? drawLoginField() : drawEmptyLoginField());
        container2.add(drawPasswordField());
        container2.add(drawRememberMe());
        container.add("Center", container2);
        return container;
    }

    private Container drawEmptyLoginField() {
        Container container = new Container(new GridLayout(1, 1));
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container2 = new Container(tableLayout);
        Button button = new Button(CihMBanking.theme.getImage("User2.png"));
        button.setUIID("Container");
        button.getAllStyles().setBgTransparency(0);
        Button button2 = new Button(CihMBanking.theme.getImage("help.png"));
        button2.setUIID("Container");
        button2.getAllStyles().setBgTransparency(0);
        button2.addActionListener(new 11(button2));
        Button button3 = new Button();
        button3.setUIID("Container");
        button3.getAllStyles().setBgTransparency(0);
        button3.setIcon(CihMBanking.theme.getImage("DeleteTheUser.png"));
        button3.setHidden(true);
        Container encloseCenterMiddle = FlowLayout.encloseCenterMiddle(button2, button3);
        this.username_empty_field.setEditable(false);
        this.username_empty_field.setName("Empty Login");
        if (CihMBanking.RTL) {
            this.username_empty_field.setRTL(true);
        } else {
            this.username_empty_field.setRTL(false);
        }
        this.username_empty_field.setHint("Utilisateur");
        this.username_empty_field.setUIID("LoginTextF_Blan");
        this.username_empty_field.getAllStyles().setMarginUnit(PreLoginPage.b);
        this.username_empty_field.getAllStyles().setMarginLeft(3.0f);
        this.username_empty_field.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        this.username_empty_field.setConstraint(2);
        this.username_empty_field.setMaxSize(20);
        this.username_empty_field.setRows(1);
        this.username_empty_field.setColumns(20);
        this.username_empty_field.setVerticalAlignment(4);
        this.username_empty_field.getHintLabel().getAllStyles().setFgColor(CIHStyles.colorGrisTxt);
        this.username_empty_field.getAllStyles().setFgColor(16777215);
        Container container3 = new Container(new BorderLayout());
        container3.add("West", button);
        container3.add("Center", this.username_empty_field);
        container3.setLeadComponent(button);
        button.addActionListener(new 12());
        container2.add(tableLayout.createConstraint().heightPercentage(100).widthPercentage(90), container3).add(tableLayout.createConstraint().heightPercentage(100).widthPercentage(10), encloseCenterMiddle);
        container2.getAllStyles().setPaddingUnit(PreLoginPage.b);
        container2.getAllStyles().setPadding(1.0f, 1.0f, 0.0f, 1.0f);
        container.getAllStyles().setMarginUnit(PreLoginPage.b);
        container.getAllStyles().setMargin(2.0f, 0.0f, 0.0f, 0.0f);
        container.getAllStyles().setPaddingUnit(PreLoginPage.b);
        container.getAllStyles().setPadding(0.4f, 0.4f, 0.0f, 0.0f);
        container.getAllStyles().setBorder(Border.createCompoundBorder(null, Border.createLineBorder(2.0f, 15461096), null, null));
        container.add(container2);
        return container;
    }

    class 11 implements ActionListener {
        final /* synthetic */ Button val$help;

        11(Button button) {
            this.val$help = button;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.this.ShowHelpDialog(this.val$help);
        }
    }

    class 12 implements ActionListener {
        12() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.access$402(AuthenticationPage.this, true);
            AuthenticationPage.access$502(AuthenticationPage.this, false);
            AuthenticationPage.access$600(AuthenticationPage.this);
            AuthenticationPage.access$702(AuthenticationPage.this, false);
            AuthenticationPage authenticationPage = AuthenticationPage.this;
            AuthenticationPage.access$900(authenticationPage, AuthenticationPage.access$700(authenticationPage), AuthenticationPage.access$800(AuthenticationPage.this));
        }
    }

    private Container drawLoginField() {
        Container container = new Container(new GridLayout(1, 1));
        DrawListContainer(this.uiManager, "GloabalContainerLogin", "", Boolean.TRUE, this.radicaux, 1, 89, "", null, null, null, null);
        container.getAllStyles().setMarginUnit(PreLoginPage.b);
        container.getAllStyles().setMargin(2.0f, 0.0f, 0.0f, 0.0f);
        container.getAllStyles().setBorder(Border.createCompoundBorder(null, Border.createLineBorder(2.0f, 15461096), null, null));
        container.add(this.cntGloableHeader);
        return container;
    }

    private Container drawPasswordField() {
        Container container = new Container(new GridLayout(1, 1));
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container2 = new Container(tableLayout);
        this.password_field = new TextField();
        Button button = new Button(CihMBanking.theme.getImage("PasswordV1.png"));
        button.setUIID("Container");
        button.getAllStyles().setPaddingUnit(PreLoginPage.b);
        button.getAllStyles().setPadding(0.1f, 0.1f, 0.0f, 0.0f);
        Button button2 = new Button("Oublié ?");
        this.forgot_password = button2;
        button2.setUIID("Lbl_lato_Meduim_18_Bold_Italic");
        this.forgot_password.addActionListener(new 13());
        this.password_field.setCursorBlinkTimeOff(999999);
        if (CihMBanking.RTL) {
            this.password_field.setRTL(true);
        } else {
            this.password_field.setRTL(false);
        }
        this.password_field.setEditable(false);
        this.password_field.setHint("Mot de passe");
        this.password_field.setUIID("Lbl_lato_bold_13_1");
        this.password_field.setConstraint(2);
        this.password_field.setMaxSize(20);
        this.password_field.setRows(1);
        this.password_field.setColumns(20);
        this.password_field.setVerticalAlignment(4);
        this.password_field.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        this.password_field.getHintLabel().getAllStyles().setFgColor(CIHStyles.colorGrisTxt);
        Container container3 = new Container(new BorderLayout());
        container3.add("West", button);
        container3.add("Center", this.password_field);
        container3.setLeadComponent(button);
        button.addActionListener(new 14());
        container2.add(tableLayout.createConstraint().heightPercentage(100).widthPercentage(80), container3).add(tableLayout.createConstraint().heightPercentage(100).widthPercentage(20), FlowLayout.encloseRightMiddle(this.forgot_password));
        container2.getAllStyles().setPaddingUnit(PreLoginPage.b);
        container2.getAllStyles().setPadding(1.0f, 1.0f, 0.0f, 1.0f);
        container.getAllStyles().setPaddingUnit(PreLoginPage.b);
        container.getAllStyles().setPadding(1.0f, 1.0f, 0.0f, 0.0f);
        container.getAllStyles().setBorder(Border.createCompoundBorder(null, Border.createLineBorder(2.0f, 15461096), null, null));
        container.add(container2);
        return container;
    }

    class 13 implements ActionListener {
        13() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.this.uiManager.NavigateToPageByIdOffligne(121);
        }
    }

    class 14 implements ActionListener {
        14() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.access$402(AuthenticationPage.this, false);
            AuthenticationPage.access$502(AuthenticationPage.this, true);
            AuthenticationPage.access$600(AuthenticationPage.this);
            AuthenticationPage.access$702(AuthenticationPage.this, true);
            AuthenticationPage authenticationPage = AuthenticationPage.this;
            AuthenticationPage.access$900(authenticationPage, AuthenticationPage.access$700(authenticationPage), AuthenticationPage.access$1000(AuthenticationPage.this));
        }
    }

    private Container drawRememberMe() {
        Container container = new Container(new BorderLayout());
        Label label = new Label("Se souvenir de moi");
        label.setUIID("Lbl_lato_bold_15");
        label.getAllStyles().setMarginUnit(PreLoginPage.b);
        Button button = new Button();
        this.RememberBtn = button;
        button.setIcon(CihMBanking.theme.getImage("RememberOn.png"));
        this.RememberBtn.addActionListener(new 15());
        this.RememberBtn.setUIID("Container");
        container.add("East", FlowLayout.encloseCenterMiddle(this.RememberBtn));
        container.add("West", FlowLayout.encloseCenterMiddle(label));
        container.setLeadComponent(this.RememberBtn);
        container.getAllStyles().setMarginUnit(PreLoginPage.b);
        container.getAllStyles().setMargin(2, 2, 0, 0);
        return container;
    }

    class 15 implements ActionListener {
        15() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (AuthenticationPage.this.RememberBtn.getIcon().equals(CihMBanking.theme.getImage("RememberOn.png"))) {
                AuthenticationPage.this.RememberBtn.setIcon(CihMBanking.theme.getImage("RememberOff.png"));
            } else {
                AuthenticationPage.this.RememberBtn.setIcon(CihMBanking.theme.getImage("RememberOn.png"));
            }
        }
    }

    private void DrawListContainer(B3GUiManager b3GUiManager, String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2, Component component3) {
        this.cntGloableHeader = new Container();
        Container GetContainer = GetContainer(b3GUiManager, str, str2);
        this.cntGloableHeader = GetContainer;
        GetContainer.setName("Remove");
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList.size() > 0) {
            Tabs tabs = new Tabs();
            int size = arrayList.size();
            int i3 = (size / i) + (size % i);
            for (int i4 = 0; i4 < i3; i4++) {
                Container container = new Container(new BoxLayout(2));
                int i5 = i4 * i;
                for (int i6 = i5; i6 < Math.min(size - i5, i) + i5; i6++) {
                    container.setScrollableY(false);
                    container.setTensileDragEnabled(false);
                    if (i6 != i5) {
                        container.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                    }
                    Container DrawItem = DrawItem((LoginAuthentication) arrayList.get(i6));
                    container.addComponent(DrawItem);
                    container.setName(DrawItem.getName());
                    container.revalidate();
                }
                tabs.addTab(str2, container);
            }
            tabs.hideTabs();
            tabs.setUIID("g_TabsV11");
            if (arrayList.size() == i) {
                tabs.setSwipeActivated(false);
            } else {
                tabs.setSwipeActivated(true);
            }
            this.cntGloableHeader.addComponent(tabs);
            if (i3 > 1) {
                b3GUiManager.stateMachine.findCntHeaderPagerV2(this.cntGloableHeader).removeAll();
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
                    if (i7 < i3 - 1) {
                        button.getAllStyles().setMarginUnit(PreLoginPage.b);
                        button.getAllStyles().setMarginRight(0.6f);
                    }
                    b3GUiManager.stateMachine.findCntHeaderPagerV2(this.cntGloableHeader).addComponent(button);
                }
                tabs.addSelectionListener(new 16(i3, b3GUiManager));
                return;
            }
            return;
        }
        this.cntGloableHeader.addComponent(drawEmptyLoginField());
    }

    class 16 implements SelectionListener {
        final /* synthetic */ int val$totalContainers;
        final /* synthetic */ B3GUiManager val$uiManager;

        16(int i, B3GUiManager b3GUiManager) {
            this.val$totalContainers = i;
            this.val$uiManager = b3GUiManager;
        }

        public void selectionChanged(int i, int i2) {
            AuthenticationPage.access$002(AuthenticationPage.this, Integer.valueOf(i2));
            AuthenticationPage.access$1100(AuthenticationPage.this);
            if (this.val$totalContainers == 1) {
                this.val$uiManager.stateMachine.findCntHeaderPagerV2(AuthenticationPage.access$1200(AuthenticationPage.this)).removeAll();
            } else {
                for (int i3 = 0; i3 < this.val$uiManager.stateMachine.findCntHeaderPagerV2(AuthenticationPage.access$1200(AuthenticationPage.this)).getComponentCount(); i3++) {
                    if (i == i3) {
                        this.val$uiManager.stateMachine.findCntHeaderPagerV2(AuthenticationPage.access$1200(AuthenticationPage.this)).getComponentAt(i3).setEnabled(false);
                    } else if (i2 == i3) {
                        this.val$uiManager.stateMachine.findCntHeaderPagerV2(AuthenticationPage.access$1200(AuthenticationPage.this)).getComponentAt(i3).setEnabled(true);
                    } else {
                        this.val$uiManager.stateMachine.findCntHeaderPagerV2(AuthenticationPage.access$1200(AuthenticationPage.this)).getComponentAt(i3).setEnabled(false);
                    }
                }
            }
            AuthenticationPage.access$1300(AuthenticationPage.this).getParent().revalidate();
            System.err.println("oldSelected = " + i);
            System.out.println("newSelected = " + i2);
        }
    }

    private void checkTouchIdIsAvailable() {
        boolean z;
        if (Storage.getInstance().exists("veclistCnxStorage")) {
            try {
                this.list_cnx = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder("Account List size");
                Vector vector = this.list_cnx;
                printStream.println(sb.append(vector == null ? "null" : Integer.valueOf(vector.size())).toString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            int i = 0;
            while (true) {
                if (i >= this.list_cnx.size()) {
                    z = false;
                    break;
                }
                if (((LoginAuthentication) this.radicaux.get(this.active_container_index.intValue())).RadicalSvdL.equals(((AccountLastConnection) this.list_cnx.elementAt(i)).radical)) {
                    z = true;
                    break;
                }
                i++;
            }
            this.show_touch = z;
        } else {
            Storage.getInstance().writeObject("veclistCnxStorage", this.list_cnx);
            this.show_touch = false;
        }
        if (this.show_touch) {
            this.touch_cnt.setHidden(false);
        } else {
            this.touch_cnt.setHidden(true);
        }
        this.touch_cnt.revalidate();
    }

    public Container GetCntBorderSeparator(String str) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID(str);
        return container;
    }

    public Container DrawItem(LoginAuthentication loginAuthentication) {
        Container container = new Container(new GridLayout(1, 1));
        Container container2 = new Container(new LayeredLayout());
        Container container3 = new Container(new GridLayout(1, 1));
        Container container4 = new Container(new GridLayout(1, 1));
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container5 = new Container(tableLayout);
        Button button = new Button(CihMBanking.theme.getImage("User2.png"));
        button.setUIID("Container");
        button.getAllStyles().setBgTransparency(0);
        button.getAllStyles().setPaddingUnit(PreLoginPage.b);
        button.getAllStyles().setPadding(0.0f, 0.0f, 0.5f, 0.5f);
        Button button2 = new Button(CihMBanking.theme.getImage("help.png"));
        button2.setUIID("Container");
        button2.getAllStyles().setBgTransparency(0);
        button2.setHidden(true);
        button2.addActionListener(new 17(button2));
        Button button3 = new Button();
        button3.setUIID("Container");
        button3.getAllStyles().setBgTransparency(0);
        button3.setIcon(CihMBanking.theme.getImage("DeleteTheUser.png"));
        button3.setHidden(false);
        Component encloseCenterMiddle = FlowLayout.encloseCenterMiddle(button2, button3);
        TextField textField = new TextField();
        encloseCenterMiddle.setName("Icon Wrapper");
        textField.setEditable(false);
        textField.setName("Draw Item");
        if (CihMBanking.RTL) {
            textField.setRTL(true);
        } else {
            textField.setRTL(false);
        }
        textField.setHint("Utilisateur");
        textField.setUIID("LoginTextF_Blan");
        textField.getAllStyles().setMarginUnit(PreLoginPage.b);
        textField.getAllStyles().setMarginLeft(3.0f);
        textField.getHintLabel().setUIID("Lbl_lato_Meduim_18");
        textField.setConstraint(2);
        textField.setMaxSize(20);
        textField.setRows(1);
        textField.setColumns(20);
        textField.getHintLabel().getAllStyles().setFgColor(CIHStyles.colorGrisTxt);
        textField.getAllStyles().setFgColor(CIHStyles.colorGrisTxt);
        textField.getSelectedStyle().setFgColor(CIHStyles.colorGrisTxt);
        textField.getUnselectedStyle().setFgColor(CIHStyles.colorGrisTxt);
        textField.setVerticalAlignment(4);
        Label label = new Label();
        label.setText("Adnan Bahri");
        label.setUIID("ac_lblitemDetail_blanc");
        Label label2 = new Label();
        label2.setText("*****11");
        label2.setUIID("g_lblDashBoardTitleOrange_blanc");
        Container encloseY = BoxLayout.encloseY(label, label2);
        encloseY.setScrollableY(false);
        encloseY.setScrollVisible(false);
        encloseY.getAllStyles().setMarginUnit(PreLoginPage.b);
        encloseY.getAllStyles().setMarginLeft(3.0f);
        container3.add(encloseY);
        container4.add(textField);
        container2.add(container3);
        container2.add(container4);
        container3.setVisible(true);
        container4.setVisible(false);
        Container container6 = new Container(new BorderLayout());
        container6.add("West", button);
        container6.add("Center", container2);
        container6.setLeadComponent(button);
        button.addActionListener(new 18(textField, container2, container3, container4, button2, button3));
        container5.add(tableLayout.createConstraint().heightPercentage(100).widthPercentage(90), container6).add(tableLayout.createConstraint().heightPercentage(100).widthPercentage(10), encloseCenterMiddle);
        container5.getAllStyles().setPaddingUnit(PreLoginPage.b);
        container5.getAllStyles().setPadding(1.0f, 1.0f, 0.0f, 1.0f);
        new Vector();
        if (Storage.getInstance().exists("RadicalSvd")) {
            Vector vector = (Vector) Storage.getInstance().readObject("RadicalSvd");
            this.RadicalSvdL = loginAuthentication.RadicalSvdL;
            String str = "******" + this.RadicalSvdL.charAt(6);
            label.setText("");
            label2.setText(str);
            if (vector.size() == 2) {
                label.setText((String) vector.elementAt(1));
            } else if (vector.size() > 2) {
                for (int i = 0; i < vector.size(); i++) {
                    if (((String) vector.get(i)).equals(this.RadicalSvdL)) {
                        label.setText(loginAuthentication.NameSvd);
                    }
                }
            }
            button3.addActionListener(new 19(loginAuthentication, textField, container2, container3, container4, button3, button2));
        }
        container.add(container5);
        return container;
    }

    class 17 implements ActionListener {
        final /* synthetic */ Button val$help;

        17(Button button) {
            this.val$help = button;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.this.ShowHelpDialog(this.val$help);
        }
    }

    class 18 implements ActionListener {
        final /* synthetic */ Container val$container1;
        final /* synthetic */ Container val$container2;
        final /* synthetic */ Button val$delete;
        final /* synthetic */ Button val$help;
        final /* synthetic */ TextField val$input;
        final /* synthetic */ Container val$parent;

        18(TextField textField, Container container, Container container2, Container container3, Button button, Button button2) {
            this.val$input = textField;
            this.val$parent = container;
            this.val$container1 = container2;
            this.val$container2 = container3;
            this.val$help = button;
            this.val$delete = button2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.access$402(AuthenticationPage.this, true);
            AuthenticationPage.access$502(AuthenticationPage.this, false);
            AuthenticationPage.access$1402(AuthenticationPage.this, this.val$input);
            AuthenticationPage.access$1500(AuthenticationPage.this, this.val$parent, this.val$container1, this.val$container2);
            this.val$help.setHidden(AuthenticationPage.access$1600(AuthenticationPage.this));
            this.val$delete.setHidden(true ^ AuthenticationPage.access$1600(AuthenticationPage.this));
        }
    }

    class 19 implements ActionListener {
        final /* synthetic */ Container val$container1;
        final /* synthetic */ Container val$container2;
        final /* synthetic */ Button val$delete;
        final /* synthetic */ Button val$help;
        final /* synthetic */ TextField val$input;
        final /* synthetic */ LoginAuthentication val$item;
        final /* synthetic */ Container val$parent;

        19(LoginAuthentication loginAuthentication, TextField textField, Container container, Container container2, Container container3, Button button, Button button2) {
            this.val$item = loginAuthentication;
            this.val$input = textField;
            this.val$parent = container;
            this.val$container1 = container2;
            this.val$container2 = container3;
            this.val$delete = button;
            this.val$help = button2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.this.DeleteFromStorage(this.val$item.RadicalSvdL, this.val$item.NameSvd);
            int i = 0;
            while (true) {
                if (i < AuthenticationPage.this.radicaux.size()) {
                    if (this.val$item.RadicalSvdL.equals(((LoginAuthentication) AuthenticationPage.this.radicaux.get(i)).RadicalSvdL) && this.val$item.NameSvd.equals(((LoginAuthentication) AuthenticationPage.this.radicaux.get(i)).NameSvd)) {
                        AuthenticationPage.this.radicaux.remove(i);
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
            AuthenticationPage.access$1402(AuthenticationPage.this, this.val$input);
            AuthenticationPage.access$1500(AuthenticationPage.this, this.val$parent, this.val$container1, this.val$container2);
            this.val$delete.setHidden(true);
            this.val$delete.setVisible(false);
            this.val$help.setHidden(false);
            this.val$help.setVisible(true);
            AuthenticationPage.access$1400(AuthenticationPage.this).getAllStyles().setFgColor(16777215);
        }
    }

    private Container GetContainer(B3GUiManager b3GUiManager, String str, String str2) {
        Container createContainer = b3GUiManager.stateMachine.createContainer(b3GUiManager.ressource, str);
        if (str.equals("GloabalContainerLogin")) {
            b3GUiManager.stateMachine.findLblGlobalHeaderTitle(createContainer).setText(str2);
        }
        return createContainer;
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

    void setProfilPict(B3GUiManager b3GUiManager, Image image, Button button) {
        b3GUiManager.ressource.getImage("round-mask.png");
    }

    public void DeleteFromStorage(String str, String str2) {
        new Vector();
        Vector vector = (Vector) Storage.getInstance().readObject("RadicalSvd");
        new Vector();
        for (int i = 0; i < vector.size(); i += 2) {
            if (((String) vector.get(i)).equals(str)) {
                int i2 = i + 1;
                if (((String) vector.get(i2)).equals(str2)) {
                    vector.remove(i2);
                    vector.remove(i);
                    Storage.getInstance().writeObject("RadicalSvd", vector);
                    if (vector.size() == 0) {
                        Storage.getInstance().deleteStorageFile("RadicalSvd");
                    }
                    if (Storage.getInstance().exists("veclistCnxStorage")) {
                        Vector vector2 = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
                        if (!vector2.isEmpty()) {
                            int i3 = 0;
                            while (true) {
                                if (i3 >= vector2.size()) {
                                    break;
                                }
                                if (str.equals(((AccountLastConnection) vector2.elementAt(i3)).radical)) {
                                    vector2.remove(i3);
                                    Storage.getInstance().writeObject("veclistCnxStorage", vector2);
                                    break;
                                }
                                i3++;
                            }
                        }
                    }
                }
            }
        }
    }

    public void ShowHelpDialog(Button button) {
        Dialog dialog = new Dialog();
        Container createContainer = this.uib.createContainer("/cihv3", "3DInfosCnt");
        dialog.setLayout(new BorderLayout());
        dialog.getAllStyles().setBgTransparency(0);
        dialog.add("Center", createContainer);
        dialog.setDialogUIID("Container");
        dialog.setDisposeWhenPointerOutOfBounds(true);
        dialog.setBackCommand(new 20("Back", dialog));
        dialog.show(button.getAbsoluteX() + 100, this.header.getAbsoluteX() + 500, 20, 20);
    }

    class 20 extends Command {
        final /* synthetic */ Dialog val$dlg;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        20(String str, Dialog dialog) {
            super(str);
            this.val$dlg = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$dlg.dispose();
        }
    }

    private void setButtonMargins(Container container, boolean z, boolean z2, boolean z3, boolean z4) {
        container.getAllStyles().setMarginUnit(PreLoginPage.b);
        container.getAllStyles().setMargin(z ? 1.2f : 0.0f, z2 ? 1.2f : 0.0f, z3 ? 1.2f : 0.0f, z4 ? 1.2f : 0.0f);
    }

    private void createCustomKeyboardDialog() {
        Sheet sheet = keyboardDialog;
        if (sheet != null) {
            sheet.remove();
        }
        Sheet sheet2 = new Sheet(null, "", "B3G");
        keyboardDialog = sheet2;
        sheet2.getAllStyles().setBgTransparency(0);
        keyboardDialog.setPreferredH((Display.getInstance().getDisplayHeight() * 3) / 7);
        keyboardDialog.hideBackButton();
        keyboardDialog.addCloseListener(new 21());
        keyboardDialog.getContentPane().setLayout(new BorderLayout());
    }

    class 21 implements ActionListener {
        21() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.access$402(AuthenticationPage.this, false);
            AuthenticationPage.access$502(AuthenticationPage.this, false);
            AuthenticationPage.is_open = false;
            AuthenticationPage.access$1700(AuthenticationPage.this).setHidden(false);
            AuthenticationPage.access$1700(AuthenticationPage.this).setVisible(true);
            AuthenticationPage.access$1800(AuthenticationPage.this);
        }
    }

    private void openKeyboard(boolean z, TextField textField) {
        int i;
        slideOut();
        Container container = this.keyboardContainer;
        if (container != null) {
            container.remove();
        }
        Container container2 = new Container();
        this.keyboardContainer = container2;
        container2.setName("Keyboard Container");
        this.keyboardContainer.getAllStyles().setPaddingUnit(PreLoginPage.b);
        this.keyboardContainer.getAllStyles().setPadding(2.0f, 2.0f, 0.0f, 0.0f);
        keyboardDialog.getContentPane().add("South", this.keyboardContainer);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            i = 9;
            if (i2 > 9) {
                break;
            }
            arrayList.add(Integer.valueOf(i2));
            i2++;
        }
        this.keyboardContainer.setLayout(new GridLayout(4, 1));
        int i3 = 5;
        if (z) {
            Collections.shuffle(arrayList);
            int i4 = 0;
            while (i4 < i) {
                TableLayout tableLayout = new TableLayout(1, i3);
                Container container3 = new Container(tableLayout);
                for (int i5 = 0; i5 < 2; i5++) {
                    container3.add(tableLayout.createConstraint().heightPercentage(100).widthPercentage(32), createKeyboardButton(textField, ((Integer) arrayList.get(i4 + i5)).intValue(), Integer.valueOf(CIHStyles.colorWhite), Integer.valueOf(CIHStyles.colorBlack)));
                    container3.add(tableLayout.createConstraint().heightPercentage(100).widthPercentage(2), new Container(new FlowLayout()));
                }
                container3.add(tableLayout.createConstraint().heightPercentage(100).widthPercentage(32), createKeyboardButton(textField, ((Integer) arrayList.get(i4 + 2)).intValue(), Integer.valueOf(CIHStyles.colorWhite), Integer.valueOf(CIHStyles.colorBlack)));
                container3.getAllStyles().setMarginUnit(PreLoginPage.b);
                container3.getAllStyles().setMarginLeft(2.0f);
                container3.getAllStyles().setMarginRight(2.0f);
                this.keyboardContainer.add(container3);
                i4 += 3;
                i = 9;
                i3 = 5;
            }
        } else {
            for (int i6 = 1; i6 < 10; i6 += 3) {
                TableLayout tableLayout2 = new TableLayout(1, 5);
                Container container4 = new Container(tableLayout2);
                for (int i7 = 0; i7 < 2; i7++) {
                    container4.add(tableLayout2.createConstraint().heightPercentage(100).widthPercentage(32), createKeyboardButton(textField, ((Integer) arrayList.get(i6 + i7)).intValue(), Integer.valueOf(CIHStyles.colorWhite), Integer.valueOf(CIHStyles.colorBlack)));
                    container4.add(tableLayout2.createConstraint().heightPercentage(100).widthPercentage(2), new Container(new FlowLayout()));
                }
                container4.add(tableLayout2.createConstraint().heightPercentage(100).widthPercentage(32), createKeyboardButton(textField, ((Integer) arrayList.get(i6 + 2)).intValue(), Integer.valueOf(CIHStyles.colorWhite), Integer.valueOf(CIHStyles.colorBlack)));
                container4.getAllStyles().setMarginUnit(PreLoginPage.b);
                container4.getAllStyles().setMarginLeft(2.0f);
                container4.getAllStyles().setMarginRight(2.0f);
                this.keyboardContainer.add(container4);
            }
        }
        TableLayout tableLayout3 = new TableLayout(1, 5);
        Container container5 = new Container(tableLayout3);
        Container createKeyboardButton = createKeyboardButton(textField, 12, Integer.valueOf(CIHStyles.colorWhite), Integer.valueOf(CIHStyles.colorBlack));
        Container createKeyboardButton2 = createKeyboardButton(textField, ((Integer) arrayList.get(z ? 9 : 0)).intValue(), Integer.valueOf(CIHStyles.colorWhite), Integer.valueOf(CIHStyles.colorBlack));
        Container createKeyboardButton3 = createKeyboardButton(textField, 10, Integer.valueOf(CIHStyles.colorWhite), Integer.valueOf(CIHStyles.colorBlack));
        createKeyboardButton(textField, 13, Integer.valueOf(CIHStyles.colorWhite), Integer.valueOf(CIHStyles.colorBlack));
        createKeyboardButton(textField, 14, Integer.valueOf(CIHStyles.colorWhite), Integer.valueOf(CIHStyles.colorBlack));
        container5.add(tableLayout3.createConstraint().heightPercentage(100).widthPercentage(32), createKeyboardButton).add(tableLayout3.createConstraint().heightPercentage(100).widthPercentage(2), new Container(new FlowLayout())).add(tableLayout3.createConstraint().heightPercentage(100).widthPercentage(32), createKeyboardButton2).add(tableLayout3.createConstraint().heightPercentage(100).widthPercentage(2), new Container(new FlowLayout())).add(tableLayout3.createConstraint().heightPercentage(100).widthPercentage(32), createKeyboardButton3);
        container5.getAllStyles().setMarginUnit(PreLoginPage.b);
        container5.getAllStyles().setMarginLeft(2.0f);
        container5.getAllStyles().setMarginRight(2.0f);
        this.keyboardContainer.add(container5);
        this.keyboardContainer.revalidate();
        this.main_login_wrapper.setHidden(true);
        this.main_login_wrapper.setVisible(false);
        is_open = true;
        keyboardDialog.show();
    }

    private void slideOut() {
        if (this.is_services_container) {
            this.services_container.setHidden(true);
            this.services_container.setVisible(false);
            this.services_container.getParent().forceRevalidate();
            this.is_services_container = false;
        }
    }

    private void slideIn() {
        if (this.is_services_container) {
            return;
        }
        this.services_container.setHidden(false);
        this.services_container.setVisible(true);
        this.services_container.getParent().forceRevalidate();
        this.is_services_container = true;
    }

    private Container createKeyboardButton(TextField textField, int i, Integer num, Integer num2) {
        Button button;
        Container container = new Container(new GridLayout(1, 1));
        if (i < 10) {
            button = new Button(String.valueOf(i));
            button.addActionListener(new 22(i, textField));
        } else if (i == 10) {
            button = new Button("OK");
            button.addActionListener(new 23());
        } else if (i == 13) {
            button = new Button("Connexion");
            button.addActionListener(new 24());
        } else {
            button = new Button("⌫");
            button.addActionListener(new 25(textField));
            button.addLongPressListener(new 26(textField));
        }
        button.setUIID("Container");
        RoundRectBorder strokeOpacity = RoundRectBorder.create().cornerRadius(2.0f).stroke(new Stroke(2.5f, 2, 0, 4.0f)).strokeColor(16777215).strokeOpacity(0);
        container.getAllStyles().setBgColor(CIHStyles.colorBlack);
        if (i == 10 || i == 12) {
            container.getAllStyles().setBgTransparency(0);
        } else {
            container.getAllStyles().setBgTransparency(100);
        }
        container.getAllStyles().setBorder(strokeOpacity);
        container.getAllStyles().setPaddingUnit(PreLoginPage.b);
        container.getAllStyles().setPadding(2.0f, 2.0f, 0.0f, 0.0f);
        container.getAllStyles().setMarginUnit(PreLoginPage.b);
        container.getAllStyles().setMargin(0.5f, 0.5f, 0.0f, 0.0f);
        button.getAllStyles().setFgColor(num.intValue());
        button.getAllStyles().setAlignment(4);
        button.getAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(3.0f), 1));
        container.add(BoxLayout.encloseYCenter(button));
        container.setLeadComponent(button);
        return container;
    }

    class 22 implements ActionListener {
        final /* synthetic */ TextField val$input;
        final /* synthetic */ int val$key;

        22(int i, TextField textField) {
            this.val$key = i;
            this.val$input = textField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.access$1900(AuthenticationPage.this, String.valueOf(this.val$key), this.val$input, false);
        }
    }

    class 23 implements ActionListener {
        23() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.keyboardDialog.back();
            if (AuthenticationPage.access$700(AuthenticationPage.this)) {
                new UITimer(new 1()).schedule(375, false, AuthenticationPage.this.uiManager.currentForm);
            }
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                AuthenticationPage.access$2000(AuthenticationPage.this, false);
            }
        }
    }

    class 24 implements ActionListener {
        24() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.keyboardDialog.back();
        }
    }

    class 25 implements ActionListener {
        final /* synthetic */ TextField val$input;

        25(TextField textField) {
            this.val$input = textField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.access$1900(AuthenticationPage.this, "remove", this.val$input, false);
        }
    }

    class 26 implements ActionListener {
        final /* synthetic */ TextField val$input;

        26(TextField textField) {
            this.val$input = textField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AuthenticationPage.access$1900(AuthenticationPage.this, "remove", this.val$input, true);
        }
    }

    private void write_to_textfield(String str, TextField textField, boolean z) {
        if (!str.equals("remove") || textField.getText().trim().length() <= 0) {
            if (!str.equals("remove")) {
                String text = textField.getText();
                if (this.isPasswordActive) {
                    textField.setConstraint(65536);
                }
                textField.setText(text.concat(str));
            }
        } else if (z) {
            textField.clear();
        } else {
            String text2 = textField.getText();
            if (text2.length() == 1) {
                textField.clear();
            } else {
                textField.setText(text2.substring(0, text2.length() - 1));
            }
        }
        if (this.isPasswordActive || this.isContainer1Visible) {
            return;
        }
        this.username_field.getHintLabel().getAllStyles().setFgColor(CIHStyles.colorGrisTxt);
        this.username_field.getAllStyles().setFgColor(16777215);
        this.username_field.getParent().revalidate();
    }

    private void switchContainersWithAnimation(Container container, Container container2, Container container3) {
        if (this.isContainer1Visible) {
            container2.setVisible(true);
            container2.setHidden(false);
            container3.setVisible(true);
            container3.setHidden(false);
            container2.setY(0);
            container3.setY(Display.getInstance().getDisplayHeight());
            container2.getParent().animateUnlayoutAndWait(300, 20);
            container2.setY(-Display.getInstance().getDisplayHeight());
            container3.setY(0);
            container2.getParent().animateLayout(300);
            container2.setVisible(false);
            container2.setHidden(true);
            this.removed_container_indexes.add(this.active_container_index);
        }
        this.touch_cnt.setHidden(true);
        createCustomKeyboardDialog();
        this.done_connect = false;
        openKeyboard(false, this.username_field);
        this.isContainer1Visible = false;
        this.username_field.getAllStyles().setFgColor(16777215);
        container.revalidate();
    }

    private void handleLogin(boolean z) {
        String text;
        if (this.radicaux.size() > 0) {
            if (this.removed_container_indexes.isEmpty() || !this.removed_container_indexes.contains(this.active_container_index)) {
                this.uiManager.stateMachine.authentificationNew(((LoginAuthentication) this.radicaux.get(this.active_container_index.intValue())).RadicalSvdL, this.password_field.getText(), z, this.RememberBtn);
                return;
            }
            TextField textField = this.username_field;
            if (textField != null) {
                this.uiManager.stateMachine.authentificationNew(textField.getText(), this.password_field.getText(), z, this.RememberBtn);
                return;
            } else {
                System.err.println("Please enter your Radical");
                this.uiManager.stateMachine.authentificationNew("", this.password_field.getText(), z, this.RememberBtn);
                return;
            }
        }
        if (!this.username_field.getText().isEmpty()) {
            text = this.username_field.getText();
        } else {
            text = this.username_empty_field.getText();
        }
        this.uiManager.stateMachine.authentificationNew(text, this.password_field.getText(), z, this.RememberBtn);
    }

    public Container drawFingerprintIcon(Button button) {
        this.touch_container = new Container(new GridLayout(1, 1));
        button.setUIID("Container");
        this.touch_container.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(2.0f));
        this.touch_container.getAllStyles().setBgColor(CIHStyles.colorOrange);
        this.touch_container.getAllStyles().setBgTransparency(255);
        this.touch_container.getAllStyles().setMarginUnit(PreLoginPage.b);
        this.touch_container.getAllStyles().setPaddingUnit(PreLoginPage.b);
        this.touch_container.getAllStyles().setMargin(0.0f, 0.0f, 0.0f, 4.0f);
        this.touch_container.getAllStyles().setPadding(1.0f, 1.0f, 2.0f, 2.0f);
        this.touch_container.add(FlowLayout.encloseCenterMiddle(button));
        this.touch_container.setLeadComponent(button);
        return this.touch_container;
    }
}
