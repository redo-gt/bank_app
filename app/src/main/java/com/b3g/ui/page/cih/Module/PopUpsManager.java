package com.b3g.ui.page.cih.Module;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.ActivationPush;
import com.b3g.services.BlankService;
import com.b3g.services.Client;
import com.b3g.services.Profile;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.tools.NativeCall;
import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.B3GRadioWithIcon;
import com.b3g.ui.B3GTextField;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.CihUiManager;
import com.b3g.ui.page.ConfirmEMail;
import com.b3g.ui.page.PinAddPage;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.fingerprint.Fingerprint;
import com.codename1.io.Preferences;
import com.codename1.io.Storage;
import com.codename1.l10n.L10NManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class PopUpsManager {
    static int counter;
    public static Dialog d;
    Button Btnplustard;
    B3GUiManager uiManager;
    UIBuilder uib = new UIBuilder();
    boolean acceptFingerBool = false;
    boolean isScrolledSecurityPopUp = false;

    public PopUpsManager() {
    }

    public PopUpsManager(B3GUiManager b3GUiManager) {
        this.uiManager = b3GUiManager;
        CihMBanking.exitApplication = false;
    }

    public void showTouchIdIntroPopPup() {
        boolean z;
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            new Vector();
            if (Storage.getInstance().exists("veclistCnxStorage")) {
                Vector vector = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
                int i = 0;
                while (true) {
                    if (i >= vector.size()) {
                        z = false;
                        break;
                    }
                    if (CihMBanking.sesPMTR.getStoredRadical().equals(((AccountLastConnection) vector.elementAt(i)).radical)) {
                        z = true;
                        break;
                    }
                    i++;
                }
                TouchIDNativeCall touchIDNativeCall = (TouchIDNativeCall) NativeLookup.create(TouchIDNativeCall.class);
                if (Fingerprint.isAvailable() && !z && touchIDNativeCall.isSupported()) {
                    if (!Storage.getInstance().exists("newVerion2")) {
                        Vector vector2 = new Vector();
                        vector2.addElement("380");
                        Storage.getInstance().writeObject("newVerion2", vector2);
                        return;
                    }
                    new Vector();
                    if (((String) ((Vector) Storage.getInstance().readObject("newVerion2")).elementAt(0)).equals("380")) {
                        return;
                    }
                    Vector vector3 = new Vector();
                    vector3.addElement("380");
                    Storage.getInstance().writeObject("newVerion2", vector3);
                    showFingerPrintPopUp();
                }
            }
        }
    }

    public Dialog showblockedPopUp(StateMachine stateMachine, Container container) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        dialog.setPreferredW(Display.getInstance().getDisplayWidth());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "ToolbarWithLogOut");
        Container container2 = (Container) uIBuilder.findByName("BodyCnt", createContainer);
        ((Button) uIBuilder.findByName("logOutBtn", createContainer)).addActionListener(new 1(stateMachine));
        container2.add(container);
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", true);
        return dialog;
    }

    class 1 implements ActionListener {
        final /* synthetic */ StateMachine val$stsMachine;

        1(StateMachine stateMachine) {
            this.val$stsMachine = stateMachine;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PopUpsManager.this.ShowDisconectingPopUp(this.val$stsMachine);
        }
    }

    public Dialog showblockedPushPopUp(StateMachine stateMachine, boolean z) {
        ActivationPush activationPush = new ActivationPush();
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        dialog.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.setPreferredH(Display.getInstance().getDisplayHeight());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "ToolbarWithLogOutPush");
        Button button = (Button) uIBuilder.findByName("logOutBtn", createContainer);
        Button button2 = (Button) uIBuilder.findByName("ActivateBtn", createContainer);
        Button button3 = (Button) uIBuilder.findByName("PlutardBtn", createContainer);
        SpanLabel spanLabel = (SpanLabel) uIBuilder.findByName("DescSpnLbl", createContainer);
        Container container = (Container) uIBuilder.findByName("Container6", createContainer);
        SpanLabel spanLabel2 = new SpanLabel();
        spanLabel2.getAllStyles().setMargin(1, 0, 0, 0);
        spanLabel2.setTextUIID("dg_splblMsgCenterBoldNotif");
        ((Container) uIBuilder.findByName("Container1", createContainer)).getAllStyles().setMargin(0, 1, 0, 0);
        if (z) {
            spanLabel.setText("Le service notification est déjà activé sur un autre appareil.");
            spanLabel2.setText("Souhaitez-vous le transférer sur cet appareil ?");
            Container container2 = (Container) uIBuilder.findByName("Container13", createContainer);
            container2.setLayout(new GridLayout(1, 2));
            Container container3 = (Container) uIBuilder.findByName("Container10", createContainer);
            container3.getAllStyles().setMargin(0, 0, 1, 1);
            Container container4 = (Container) uIBuilder.findByName("Container11", createContainer);
            container4.getAllStyles().setMargin(0, 0, 1, 1);
            container4.setLayout(new GridLayout(1, 1));
            container2.removeAll();
            container2.add(container4);
            container2.add(container3);
            button2.setText("OUI");
            button3.setText("NON");
            button3.setUIID("op_BtnOppositionValidation");
        } else {
            spanLabel.setText("Activer les notifications pour continuer à recevoir les informations relatives à vos comptes instantanément (achat e-commerce, versement espèce, réception virement...)");
            spanLabel2.setText("Ces notifications vont remplacer les messages sms que vous receviez auparavant");
        }
        spanLabel.setFlatten(true);
        spanLabel2.setFlatten(true);
        container.add(spanLabel2);
        button2.addActionListener(new 2(activationPush, dialog));
        button3.addActionListener(new 3(dialog));
        button.addActionListener(new 4(stateMachine));
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.addComponent("Center", createContainer);
        Settings.setNightMode(dialog, 0);
        dialog.showPacked("Center", true);
        return dialog;
    }

    class 2 implements ActionListener {
        final /* synthetic */ Dialog val$d1;
        final /* synthetic */ ActivationPush val$srv;

        2(ActivationPush activationPush, Dialog dialog) {
            this.val$srv = activationPush;
            this.val$d1 = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if (this.val$srv.Activaterocess(SessionParameter.PushID).getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.getSessionClient().getClient_Profil().pushFlag = "1";
                    if (!Display.getInstance().getPlatformName().equals("ios")) {
                        ((NativeCall) NativeLookup.create(NativeCall.class)).getNotificationPermission();
                    }
                }
                this.val$d1.dispose();
            } catch (Exception unused) {
                this.val$d1.dispose();
            }
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Dialog val$d1;

        3(Dialog dialog) {
            this.val$d1 = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            try {
                CihMBanking.sesPMTR.getSessionClient().getClient_Profil().pushFlag = "1";
                this.val$d1.dispose();
            } catch (Exception unused) {
                this.val$d1.dispose();
            }
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ StateMachine val$stsMachine;

        4(StateMachine stateMachine) {
            this.val$stsMachine = stateMachine;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PopUpsManager.this.ShowDisconectingPopUp(this.val$stsMachine);
        }
    }

    public static void showUpdatePopUpWithImage() {
        Dialog dialog = new Dialog();
        dialog.setUIID("Container");
        dialog.setLayout(new BorderLayout());
        dialog.setScrollableY(false);
        dialog.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.setPreferredH(Display.getInstance().getDisplayHeight());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "popupUpdate");
        createContainer.setUIID("Container");
        ((Button) uIBuilder.findByName("closeBtn", createContainer)).addActionListener(new 5(dialog));
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", true);
    }

    class 5 implements ActionListener {
        final /* synthetic */ Dialog val$d1;

        5(Dialog dialog) {
            this.val$d1 = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d1.dispose();
        }
    }

    public void showNoConnectoinMessg() {
        B3GUiManager b3GUiManager = this.uiManager;
        b3GUiManager.ShowMessageTransaction(b3GUiManager.stateMachine, DataTools.FormatUnicode("Merci de vérifier votre connexion internet"), null);
    }

    public Container showOperatorPopUpCont() {
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "changeOperator");
        Container container = (Container) uIBuilder.findByName("operatorRadioCnt", createContainer);
        Button button = (Button) uIBuilder.findByName("validBtn", createContainer);
        Label label = (Label) uIBuilder.findByName("title", createContainer);
        SpanLabel spanLabel = (SpanLabel) uIBuilder.findByName("messageLbl", createContainer);
        label.setText("Opérateur GSM");
        spanLabel.setText("Merci de selectionner l'opérateur par defaut de toutes vos operations");
        B3GRadioWithIcon b3GRadioWithIcon = new B3GRadioWithIcon("", new BoxLayout(2));
        b3GRadioWithIcon.setUIID("radio");
        b3GRadioWithIcon.addItem("IAM", "iam.jpg");
        b3GRadioWithIcon.addItem("ORANGE", "orange.jpg");
        b3GRadioWithIcon.addItem("INWI", "inwi.jpg");
        b3GRadioWithIcon.addItem("Numéro international", "");
        b3GRadioWithIcon.clear();
        button.addActionListener(new 6(b3GRadioWithIcon));
        container.add(b3GRadioWithIcon.GetContainer());
        return createContainer;
    }

    class 6 implements ActionListener {
        final /* synthetic */ B3GRadioWithIcon val$b3GRadioOpe;

        6(B3GRadioWithIcon b3GRadioWithIcon) {
            this.val$b3GRadioOpe = b3GRadioWithIcon;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$b3GRadioOpe.isClear()) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PopUpsManager.this.uiManager.stateMachine, "Merci de selectionner l'opérateur par defaut de toutes vos operations", null);
                return;
            }
            String GetSelectedText = this.val$b3GRadioOpe.GetSelectedText();
            GetSelectedText.hashCode();
            char c = 65535;
            switch (GetSelectedText.hashCode()) {
                case -2093661423:
                    if (GetSelectedText.equals("ORANGE Marruecos")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1955522002:
                    if (GetSelectedText.equals("ORANGE")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1860552928:
                    if (GetSelectedText.equals("ORANGE Maroc")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1854319787:
                    if (GetSelectedText.equals("Número extranjero")) {
                        c = 3;
                        break;
                    }
                    break;
                case -884157378:
                    if (GetSelectedText.equals("ORANGE Morocco")) {
                        c = 4;
                        break;
                    }
                    break;
                case -825633234:
                    if (GetSelectedText.equals("Numéro international")) {
                        c = 5;
                        break;
                    }
                    break;
                case 72245:
                    if (GetSelectedText.equals("IAM")) {
                        c = 6;
                        break;
                    }
                    break;
                case 2252471:
                    if (GetSelectedText.equals("INWI")) {
                        c = 7;
                        break;
                    }
                    break;
                case 377495720:
                    if (GetSelectedText.equals("INWI \"انوي\"")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 877967413:
                    if (GetSelectedText.equals("Foreign number")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 944108448:
                    if (GetSelectedText.equals("Numéro étranger")) {
                        c = '\n';
                        break;
                    }
                    break;
                case 1108019945:
                    if (GetSelectedText.equals("Maroc Telecom")) {
                        c = 11;
                        break;
                    }
                    break;
                case 1111190515:
                    if (GetSelectedText.equals("رقم أجنبي")) {
                        c = '\f';
                        break;
                    }
                    break;
                case 1512146854:
                    if (GetSelectedText.equals("أورانج المغرب ORANGE")) {
                        c = '\r';
                        break;
                    }
                    break;
                case 1658775230:
                    if (GetSelectedText.equals("MEDITEL")) {
                        c = 14;
                        break;
                    }
                    break;
                case 1861262649:
                    if (GetSelectedText.equals("إتصالات المغرب")) {
                        c = 15;
                        break;
                    }
                    break;
                case 1941977568:
                    if (GetSelectedText.equals("AUTRES")) {
                        c = 16;
                        break;
                    }
                    break;
            }
            String str = "3";
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 4:
                case '\r':
                case 14:
                    str = "1";
                    break;
                case 6:
                case 11:
                case 15:
                    str = "0";
                    break;
                case 7:
                case '\b':
                    str = "2";
                    break;
            }
            ServiceResponse updateProfileProcess = PopUpsManager.this.updateProfileProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, str, "", "0");
            if (updateProfileProcess.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus = "1";
                CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator = this.val$b3GRadioOpe.GetSelectedText();
                Display.getInstance().callSerially(new 1());
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PopUpsManager.this.uiManager.stateMachine, DataTools.FormatUnicode(updateProfileProcess.getStatusLabel()), null);
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                PopUpsManager.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }
    }

    public Container showPushConfPopUpCont() {
        Container container = new Container();
        TableLayout tableLayout = new TableLayout(1, 1);
        container.setLayout(tableLayout);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(100);
        createConstraint.setHeightPercentage(100);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(4);
        Container container2 = new Container(new BorderLayout());
        container2.setUIID("BlueBorderNotifs");
        Container container3 = new Container(new BoxLayout(2));
        Container container4 = new Container(new FlowLayout(4, 4));
        Container container5 = new Container(new BoxLayout(2));
        Container container6 = new Container();
        Label label = new Label("Mes notifications");
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        label.getAllStyles().setMarginLeft(0);
        Container container7 = new Container(new FlowLayout(4, 4));
        Label label2 = new Label();
        label2.setIcon(this.uiManager.ressource.getImage("NotifsBigIcon.png"));
        container7.add(label2);
        container7.setUIID("margin_0_3_0_0");
        Container container8 = new Container();
        SpanLabel spanLabel = new SpanLabel("En activant ce service, vous recevrez les notifications relatif à votre relation bancaire en temps réel sur l'appareil de votre choix");
        Container container9 = new Container(new BoxLayout(2));
        Button button = new Button("J'active les notifications");
        button.setUIID("op_BtnOppositionValidationOrgBg");
        container9.add(button);
        Container container10 = new Container(new GridLayout(1, 2));
        Button button2 = new Button("Annuler");
        Button button3 = new Button("Plus tard");
        button2.setUIID("lbl_big_gris_centerNotifs");
        button3.setUIID("lbl_big_gris_centerNotifs");
        container10.add(button2);
        container10.add(button3);
        container3.add(container9);
        container3.add(container10);
        container6.add(label);
        container8.add(spanLabel);
        container5.add(container7);
        container5.add(container8);
        container4.add(container5);
        container2.add("North", container6);
        container2.add("Center", container4);
        container2.add("South", container3);
        container.add(container2);
        return container;
    }

    public void ShowMDPPopUp(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMDP");
            SpanLabel spanLabel = (SpanLabel) this.uib.findByName("SpanLabel", createContainer);
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(false);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(230);
            dialog.getDialogStyle().setBgColor(1118481);
            dialog.getStyle().setPadding(0, 0, 0, 0);
            dialog.getStyle().setMargin(0, 0, 0, 0);
            dialog.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog.revalidate();
            spanLabel.setText(str);
            stateMachine.findBtnYest(createContainer).addActionListener(new 7(dialog, stateMachine));
            stateMachine.findBtnYestIcon(createContainer).addActionListener(new 8(dialog, stateMachine));
            stateMachine.findBtnNo(createContainer).addActionListener(new 9(dialog));
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 10(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 7 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ StateMachine val$stsMachine;

        7(Dialog dialog, StateMachine stateMachine) {
            this.val$d = dialog;
            this.val$stsMachine = stateMachine;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            this.val$stsMachine.uiManager.NavigateToPageByIdOffligne(121);
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ StateMachine val$stsMachine;

        8(Dialog dialog, StateMachine stateMachine) {
            this.val$d = dialog;
            this.val$stsMachine = stateMachine;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            this.val$stsMachine.uiManager.NavigateToPageByIdOffligne(121);
        }
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
        final /* synthetic */ Dialog val$d;

        10(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public Container showPinPopUpp(Dialog dialog) {
        PinAddPage pinAddPage = new PinAddPage(this.uiManager, dialog, 111, true);
        pinAddPage.setBtnPlutardVis(false);
        return pinAddPage.cntMain;
    }

    public Container showOtpPopUp(Dialog dialog) {
        TextField textField;
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "ChangePasswordStep2");
        Container container = (Container) uIBuilder.findByName("Container3", createContainer);
        container.setScrollableY(false);
        container.setScrollVisible(false);
        createContainer.setScrollableY(false);
        SpanLabel spanLabel = (SpanLabel) uIBuilder.findByName("messageLbl", createContainer);
        SpanLabel spanLabel2 = (SpanLabel) uIBuilder.findByName("SpanLabel1", createContainer);
        SpanLabel spanLabel3 = new SpanLabel();
        Container container2 = (Container) uIBuilder.findByName("CntPasswordHelp", createContainer);
        Label label = (Label) uIBuilder.findByName("title", createContainer);
        spanLabel.setText("Confirmer l’enregistrement de votre appareil en introduisant le code de validation reçu par SMS.");
        spanLabel2.setText("Vous pouvez à tout moment supprimer cet appareil au niveau du menu « Paramètres ».");
        spanLabel3.setText("* Même si votre appareil a déjà été enregistré, par mesure de sécurité,  CIHBANK peut vous demander le réenregistrement de votre appareil");
        label.setText("Enregistrement de l’appareil");
        spanLabel3.setTextUIID("lbl_regular");
        ((Label) uIBuilder.findByName("gsmMsgSP", createContainer)).setText(DataTools.getEncryptedPhone(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm));
        Container container3 = (Container) uIBuilder.findByName("loginTxtField", createContainer);
        if (Display.getInstance().getPlatformName().equals("ios")) {
            container3.removeAll();
            textField = new TextField();
            Button button = new Button();
            button.setIcon(this.uiManager.ressource.getImage("Password.png"));
            button.setUIID("Container");
            textField.setConstraint(2);
            textField.setMaxSize(6);
            textField.setVisible(false);
            textField.setTextSelectionEnabled(false);
            textField.setUIID("ContTrspar");
            textField.getAllStyles().setPaddingUnit(1);
            Container container4 = new Container(new LayeredLayout());
            Label label2 = new Label("Code Confidentiel");
            label2.getAllStyles().setPaddingUnit(1);
            label2.setWidth(container3.getWidth());
            label2.setHeight(container3.getHeight());
            label2.setUIID("LoginTextF_gris");
            container3.setUIID("PassField");
            textField.setFocusable(false);
            textField.setEditable(false);
            textField.setEnabled(false);
            label2.setFocusable(true);
            label2.addPointerPressedListener(new 11(textField));
            textField.addDataChangedListener(new 12(textField, label2));
            textField.setWidth(0);
            textField.setHeight(0);
            container4.add(label2);
            container3.setLeadComponent(label2);
            container3.addComponent("Center", container4);
            container3.addComponent("West", button);
            createContainer.addComponent("South", textField);
        } else if (Display.getInstance().isSimulator()) {
            textField = (TextField) uIBuilder.findByName("LoginTxt", createContainer);
            textField.setFocusable(true);
            textField.getParent().setFocusable(false);
            textField.setEnabled(true);
            SessionParameter.setOtpTextField(textField);
        } else {
            textField = (TextField) uIBuilder.findByName("LoginTxt", createContainer);
            textField.setFocusable(false);
            textField.getParent().setFocusable(false);
            textField.setEnabled(false);
            SessionParameter.setOtpTextField(textField);
        }
        Button button2 = (Button) uIBuilder.findByName("validBtn", createContainer);
        ((Button) uIBuilder.findByName("ivrBtn", createContainer)).addActionListener(new 13(textField));
        button2.setPreferredH(Display.getInstance().getDisplayWidth() / 7);
        button2.addActionListener(new 14(textField));
        createContainer.revalidate();
        container2.add(spanLabel3);
        return createContainer;
    }

    class 11 implements ActionListener {
        final /* synthetic */ TextField val$LoginField;

        11(TextField textField) {
            this.val$LoginField = textField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$LoginField.setTextSelectionEnabled(false);
            this.val$LoginField.startEditingAsync();
        }
    }

    class 12 implements DataChangedListener {
        final /* synthetic */ TextField val$LoginField;
        final /* synthetic */ Label val$lblTxtFld;

        12(TextField textField, Label label) {
            this.val$LoginField = textField;
            this.val$lblTxtFld = label;
        }

        public void dataChanged(int i, int i2) {
            this.val$LoginField.setTextSelectionEnabled(false);
            this.val$LoginField.stopEditing();
            if (this.val$LoginField.getText().length() < 6 || this.val$LoginField.getText().length() > 6) {
                this.val$LoginField.clear();
            } else {
                this.val$lblTxtFld.setText(this.val$LoginField.getText());
                this.val$lblTxtFld.getParent().revalidate();
            }
        }
    }

    class 13 implements ActionListener {
        final /* synthetic */ TextField val$LoginField;

        13(TextField textField) {
            this.val$LoginField = textField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ServiceResponse sendOtpProcess = ServiceManager.sendOtpProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1024", 0, "1", "1", "", "", "", "");
            if (!sendOtpProcess.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PopUpsManager.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcess.getStatusLabel()), null);
            }
            SessionParameter.setOtpTextField(this.val$LoginField);
        }
    }

    class 14 implements ActionListener {
        final /* synthetic */ TextField val$LoginField;

        14(TextField textField) {
            this.val$LoginField = textField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$LoginField.getText().length() != 0) {
                if (PopUpsManager.counter < 3) {
                    ArrayList validateDeviceProcess = PopUpsManager.this.validateDeviceProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, DataTools.getCompleteDeviceInfo(false), this.val$LoginField.getText());
                    if (((ServiceResponse) validateDeviceProcess.get(0)).getStatusCode().equals("000")) {
                        CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus = "1";
                        CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID = ((ServiceResponse) validateDeviceProcess.get(1)).getSessionId();
                        Profile client_Profil = CihMBanking.sesPMTR.getSessionClient().getClient_Profil();
                        String str = client_Profil.radical;
                        new Client().setClient_Profil(client_Profil);
                        CihMBanking.sesPMTR.isFirstTime = true;
                        CihMBanking.sesPMTR.setCurrentFormID(1);
                        Vector vector = new Vector();
                        Vector vector2 = new Vector();
                        if (Storage.getInstance().exists("RadicalSvd")) {
                            vector = (Vector) Storage.getInstance().readObject("RadicalSvd");
                        }
                        boolean z = false;
                        for (int i = 0; i < vector.size(); i++) {
                            if (((String) vector.get(i)).equals(str)) {
                                z = true;
                            }
                        }
                        if (!z) {
                            vector.addElement(str);
                            vector.addElement(client_Profil.nomPrenom);
                        }
                        Storage.getInstance().writeObject("RadicalSvd", vector);
                        if (Storage.getInstance().exists("ClienSexSvdAndRadicalSvd")) {
                            vector2 = (Vector) Storage.getInstance().readObject("ClienSexSvdAndRadicalSvd");
                            boolean z2 = false;
                            for (int i2 = 0; i2 < vector2.size(); i2++) {
                                if (((String) vector2.get(i2)).equals(str)) {
                                    try {
                                        if (((String) vector2.get(i2 + 1)).equals(client_Profil.clientSex)) {
                                            z2 = true;
                                        }
                                    } catch (Exception unused) {
                                    }
                                }
                            }
                            if (!z2) {
                                vector2.addElement(str);
                                vector2.addElement(client_Profil.clientSex);
                            }
                        } else {
                            vector2.addElement(str);
                            vector2.addElement(client_Profil.clientSex);
                        }
                        Storage.getInstance().writeObject("ClienSexSvdAndRadicalSvd", vector2);
                        Display.getInstance().callSerially(new 1(validateDeviceProcess));
                        return;
                    }
                    if (((ServiceResponse) validateDeviceProcess.get(0)).getStatusCode().equals("907")) {
                        CihMBanking.isConnecting = "01";
                        PopUpsManager.this.uiManager.ShowMessageTransaction(PopUpsManager.this.uiManager.stateMachine, DataTools.FormatUnicode("Code confidentiel incorrect"), null);
                        PopUpsManager.counter++;
                        return;
                    } else {
                        CihMBanking.isConnecting = "01";
                        PopUpsManager.this.uiManager.ShowMessageTransaction(PopUpsManager.this.uiManager.stateMachine, DataTools.FormatUnicode(((ServiceResponse) validateDeviceProcess.get(0)).getStatusLabel()), null);
                        return;
                    }
                }
                CihMBanking.isConnecting = "01";
                PopUpsManager.counter = 0;
                PopUpsManager.this.uiManager.stateMachine.showForm("LoginV2New", (Command) null);
                return;
            }
            PopUpsManager.this.uiManager.ShowMessageTransaction(PopUpsManager.this.uiManager.stateMachine, DataTools.FormatUnicode("Veuillez saisir le code reçu par SMS"), null);
        }

        class 1 implements Runnable {
            final /* synthetic */ ArrayList val$responseOtp;

            1(ArrayList arrayList) {
                this.val$responseOtp = arrayList;
            }

            class 1 implements Runnable {
                1() {
                }

                public void run() {
                    Account account = new Account();
                    CihMBanking.isConnecting = "03";
                    CihMBanking.isConnected = true;
                    CihMBanking.sesPMTR.DisplayWith = Display.getInstance().getDisplayWidth();
                    CihMBanking.sesPMTR.displayHeight = Display.getInstance().getDisplayHeight();
                    account.FillAccount((ServiceResponse) 1.this.val$responseOtp.get(1));
                    PopUpsManager.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
                }
            }

            public void run() {
                Display.getInstance().callSerially(new 1());
            }
        }
    }

    public static void showUpdateNotesPopUp() {
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            Dialog dialog = new Dialog();
            dialog.setUIID("Container");
            dialog.getDialogStyle().setBgTransparency(0);
            dialog.setLayout(new BorderLayout());
            dialog.setScrollableY(false);
            dialog.setPreferredW(Display.getInstance().getDisplayWidth());
            dialog.setPreferredH(Display.getInstance().getDisplayWidth());
            UIBuilder uIBuilder = new UIBuilder();
            Container createContainer = uIBuilder.createContainer("/cihv3", "PopupVignette");
            createContainer.setUIID("Container");
            Container container = (Container) uIBuilder.findByName("Container1", createContainer);
            Button button = (Button) uIBuilder.findByName("btnVignette", createContainer);
            container.setLeadComponent(button);
            button.addActionListener(new 15(dialog));
            createContainer.setPreferredH(Display.getInstance().getDisplayWidth());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", false);
            dialog.setDisposeWhenPointerOutOfBounds(true);
        }
    }

    class 15 implements ActionListener {
        final /* synthetic */ Dialog val$d1;

        15(Dialog dialog) {
            this.val$d1 = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Preferences.set("FlagRedPopupVignette", Integer.valueOf(Integer.parseInt(Preferences.get("FlagRedPopupVignette", "0")) + 1).toString());
            this.val$d1.dispose();
            CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(55);
        }
    }

    public void showFingerPrintPopUp() {
        new Vector();
        ((Vector) Storage.getInstance().readObject("flagFinger")).addElement("0");
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        dialog.setPreferredW((Display.getInstance().getDisplayWidth() * 100) / 100);
        dialog.setPreferredH((Display.getInstance().getDisplayHeight() * 100) / 100);
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "TouchIdNew");
        Container container = (Container) uIBuilder.findByName("scrollCnt", createContainer);
        Container container2 = (Container) uIBuilder.findByName("activerCnt", createContainer);
        Container container3 = (Container) uIBuilder.findByName("plusTard", createContainer);
        Label label = (Label) uIBuilder.findByName("touchTitle", createContainer);
        SpanLabel spanLabel = (SpanLabel) uIBuilder.findByName("touchIdMsg", createContainer);
        Label label2 = (Label) uIBuilder.findByName("activerLabel", createContainer);
        Label label3 = (Label) uIBuilder.findByName("touchIcon", createContainer);
        TouchIDNativeCall touchIDNativeCall = (TouchIDNativeCall) NativeLookup.create(TouchIDNativeCall.class);
        if (!Display.getInstance().isSimulator()) {
            if (touchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                label2.setText("J'active FingerPrint ");
            } else if (touchIDNativeCall.getBiometryType().equals("TOUCHID")) {
                label2.setText("J'active Touch ID ");
            } else if (touchIDNativeCall.getBiometryType().equals("FACEID")) {
                label3.setIcon(CihMBanking.theme.getImage("faceid.png"));
                label.setText("FACE ID");
                spanLabel.setText("Vous pouvez utiliser votre Face ID enregistrée pour vous connecter à votre espace client");
                label2.setText("J'active Face ID ");
            }
        }
        container.setScrollVisible(false);
        Component.setSameSize(container2, container3);
        Button button = new Button();
        Button button2 = new Button();
        button.addActionListener(new 16(dialog));
        button2.addActionListener(new PopUpsManager$$ExternalSyntheticLambda3(this, dialog, touchIDNativeCall));
        container2.setLeadComponent(button);
        container3.setLeadComponent(button2);
        createContainer.setPreferredW((Display.getInstance().getDisplayWidth() * 95) / 100);
        createContainer.setPreferredH((Display.getInstance().getDisplayHeight() * 95) / 100);
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", true);
    }

    class 16 implements ActionListener {
        final /* synthetic */ Dialog val$d1;

        16(Dialog dialog) {
            this.val$d1 = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d1.dispose();
            PopUpsManager.this.uiManager.NavigateToPageById(62);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    /* synthetic */ void lambda$showFingerPrintPopUp$0$com-b3g-ui-page-cih-Module-PopUpsManager(com.codename1.ui.Dialog r2, com.b3g.tools.TouchIDNativeCall r3, com.codename1.ui.events.ActionEvent r4) {
        /*
            r1 = this;
            r2.dispose()
            com.codename1.ui.Display r2 = com.codename1.ui.Display.getInstance()
            boolean r2 = r2.isSimulator()
            if (r2 != 0) goto L3a
            java.lang.String r2 = r3.getBiometryType()
            java.lang.String r4 = "FINGERPRINT"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L1c
            java.lang.String r2 = "Vous pouvez toujours activer la sécurité par FINGERPRINT en vous rendant sur le menu « Informations profil »"
            goto L3c
        L1c:
            java.lang.String r2 = r3.getBiometryType()
            java.lang.String r4 = "TOUCHID"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L2b
            java.lang.String r2 = "Vous pouvez toujours activer la sécurité par Touch ID en vous rendant sur le menu « Informations profil »"
            goto L3c
        L2b:
            java.lang.String r2 = r3.getBiometryType()
            java.lang.String r3 = "FACEID"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L3a
            java.lang.String r2 = "Vous pouvez toujours activer la sécurité par Face ID en vous rendant sur le menu « Informations profil »"
            goto L3c
        L3a:
            java.lang.String r2 = ""
        L3c:
            com.b3g.ui.B3GUiManager r3 = r1.uiManager
            userclasses.StateMachine r4 = r3.stateMachine
            java.lang.String r2 = com.b3g.tools.DataTools.FormatUnicode(r2)
            r0 = 0
            r3.ShowMessageTransaction(r4, r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.cih.Module.PopUpsManager.lambda$showFingerPrintPopUp$0$com-b3g-ui-page-cih-Module-PopUpsManager(com.codename1.ui.Dialog, com.b3g.tools.TouchIDNativeCall, com.codename1.ui.events.ActionEvent):void");
    }

    public static void showMultipleAccountPopUp(TextField textField, B3GTextField b3GTextField, Button button) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        dialog.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.setPreferredH(Display.getInstance().getDisplayHeight());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "multipleAccount");
        Container container = (Container) uIBuilder.findByName("AccountCnt", createContainer);
        container.setScrollableY(true);
        new Vector();
        Vector vector = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
        if (vector != null) {
            Collections.sort(vector, new 17());
            int i = 0;
            if (vector.size() > 3) {
                while (i < 3) {
                    container.add(new AccountLastConnection(((AccountLastConnection) vector.get(i)).radical, ((AccountLastConnection) vector.get(i)).lastConnectionDate).drawView(dialog, textField, b3GTextField, button));
                    i++;
                }
            } else {
                while (i < vector.size()) {
                    container.add(new AccountLastConnection(((AccountLastConnection) vector.get(i)).radical, ((AccountLastConnection) vector.get(i)).lastConnectionDate).drawView(dialog, textField, b3GTextField, button));
                    i++;
                }
            }
        }
        ((Button) uIBuilder.findByName("autreCompteBtn", createContainer)).addActionListener(new 18(dialog));
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", true);
    }

    class 17 implements Comparator {
        17() {
        }

        public int compare(AccountLastConnection accountLastConnection, AccountLastConnection accountLastConnection2) {
            return accountLastConnection2.occurrence - accountLastConnection.occurrence;
        }
    }

    class 18 implements ActionListener {
        final /* synthetic */ Dialog val$d1;

        18(Dialog dialog) {
            this.val$d1 = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d1.dispose();
        }
    }

    public void ShowUpdateAppPopUp(StateMachine stateMachine, String str, String str2, boolean z) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpConfirm");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(false);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(230);
            dialog.getDialogStyle().setBgColor(1118481);
            dialog.getStyle().setPadding(0, 0, 0, 0);
            dialog.getStyle().setMargin(0, 0, 0, 0);
            dialog.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog.revalidate();
            ((SpanLabel) stateMachine.findLblMsg1(createContainer)).setText(str);
            stateMachine.findBtnYest(createContainer).setText("Mettre à jour");
            stateMachine.findBtnNo(createContainer).setText("Plus tard");
            stateMachine.findBtnYest(createContainer).addActionListener(new 19(dialog, stateMachine));
            stateMachine.findBtnNo(createContainer).addActionListener(new 20(dialog, z, stateMachine, str2));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", false);
        } catch (IOException unused) {
        }
    }

    class 19 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ StateMachine val$stsMachine;

        19(Dialog dialog, StateMachine stateMachine) {
            this.val$d = dialog;
            this.val$stsMachine = stateMachine;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            this.val$stsMachine.showForm("LoginV2New", (Command) null);
            Display.getInstance().execute("http://smarturl.it/Cihonline");
        }
    }

    class 20 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ String val$formTitle;
        final /* synthetic */ boolean val$isBlocked;
        final /* synthetic */ StateMachine val$stsMachine;

        20(Dialog dialog, boolean z, StateMachine stateMachine, String str) {
            this.val$d = dialog;
            this.val$isBlocked = z;
            this.val$stsMachine = stateMachine;
            this.val$formTitle = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            if (this.val$isBlocked) {
                this.val$stsMachine.showForm(this.val$formTitle, (Command) null);
            } else {
                CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus = "1";
            }
        }
    }

    public void ShowODeviseLblOfflignPopUp(StateMachine stateMachine, Container container, Container container2, Dialog dialog) {
        container.setUIID("LblCoursChangeMad");
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(0.3f, 0.3f, 0.0f, 0.0f);
        dialog.getDialogStyle().setBgTransparency(200);
        dialog.setDisposeWhenPointerOutOfBounds(true);
        dialog.setLayout(new BorderLayout());
        dialog.add("Center", container);
        dialog.setDialogUIID("DeviseCntTrspOffligne");
        dialog.show(container2.getHeight() / 2, container2.getHeight() / 3, container2.getWidth() / 8, container2.getWidth() / 8);
    }

    public static Dialog ShowConfirmPopUp(StateMachine stateMachine, String str, String str2, String str3, ActionListener actionListener, ActionListener actionListener2) {
        Dialog dialog = new Dialog();
        d = dialog;
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpConfirm");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
            d.setAlwaysTensile(false);
            d.setDraggable(false);
            d.setScrollable(false);
            d.setTactileTouch(false);
            d.setTensileDragEnabled(false);
            d.getDialogStyle().setBgTransparency(230);
            d.getDialogStyle().setBgColor(1118481);
            d.getStyle().setPadding(0, 0, 0, 0);
            d.getStyle().setMargin(0, 0, 0, 0);
            d.getDialogStyle().setPadding(0, 0, 0, 0);
            d.getDialogStyle().setMargin(0, 0, 0, 0);
            d.revalidate();
            ((SpanLabel) stateMachine.findLblMsg1(createContainer)).setText(str3);
            stateMachine.findBtnYest(createContainer).setText(str);
            stateMachine.findBtnNo(createContainer).addActionListener(actionListener2);
            stateMachine.findBtnYest(createContainer).addActionListener(actionListener);
            stateMachine.findBtnNo(createContainer).setText(str2);
            d.addComponent("Center", createContainer);
            d.showPacked("Center", false);
        } catch (IOException unused) {
        }
        return d;
    }

    public void ShowDisconectingPopUp(StateMachine stateMachine) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUpDeisconect");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(false);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(230);
            dialog.getDialogStyle().setBgColor(1118481);
            dialog.getStyle().setPadding(0, 0, 0, 0);
            dialog.getStyle().setMargin(0, 0, 0, 0);
            dialog.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog.revalidate();
            stateMachine.findBtnYest(createContainer).addActionListener(new 21(stateMachine, open));
            stateMachine.findBtnYestIcon(createContainer).addActionListener(new 22(stateMachine, open));
            stateMachine.findBtnNo(createContainer).addActionListener(new 23(dialog));
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 24(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 21 implements ActionListener {
        final /* synthetic */ Resources val$myres;
        final /* synthetic */ StateMachine val$stsMachine;

        21(StateMachine stateMachine, Resources resources) {
            this.val$stsMachine = stateMachine;
            this.val$myres = resources;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PopUpsManager.this.uiManager = new CihUiManager(this.val$stsMachine, this.val$myres);
            CihMBanking.sesPMTR = null;
            CihMBanking.sesPMTR = new SessionParameter();
            this.val$stsMachine.showForm("LoginV2New", (Command) null);
        }
    }

    class 22 implements ActionListener {
        final /* synthetic */ Resources val$myres;
        final /* synthetic */ StateMachine val$stsMachine;

        22(StateMachine stateMachine, Resources resources) {
            this.val$stsMachine = stateMachine;
            this.val$myres = resources;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PopUpsManager.this.uiManager = new CihUiManager(this.val$stsMachine, this.val$myres);
            CihMBanking.sesPMTR = null;
            CihMBanking.sesPMTR = new SessionParameter();
            this.val$stsMachine.showForm("LoginV2New", (Command) null);
        }
    }

    class 23 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        23(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 24 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        24(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public void ShowAssistancePopUp(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpAssistance");
            SpanLabel spanLabel = (SpanLabel) this.uib.findByName("SpanLabel", createContainer);
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(false);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(230);
            dialog.getDialogStyle().setBgColor(1118481);
            dialog.getStyle().setPadding(0, 0, 0, 0);
            dialog.getStyle().setMargin(0, 0, 0, 0);
            dialog.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog.revalidate();
            spanLabel.setText(str);
            stateMachine.findBtnYest(createContainer).addActionListener(new 25());
            stateMachine.findBtnYestIcon(createContainer).addActionListener(new 26());
            stateMachine.findBtnNo(createContainer).addActionListener(new 27(dialog));
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 28(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 25 implements ActionListener {
        25() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().dial("4747");
        }
    }

    class 26 implements ActionListener {
        26() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().dial("4747");
        }
    }

    class 27 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        27(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 28 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        28(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
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
            button2.addActionListener(new 29(b3GTextField, str));
            TableLayout.Constraint createConstraint = tableLayout.createConstraint();
            createConstraint.setWidthPercentage(20);
            container.addComponent(createConstraint, button2);
        }
        button.setFocusable(false);
        button.addActionListener(new 30(b3GTextField));
        return container;
    }

    class 29 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;
        final /* synthetic */ String val$number;

        29(B3GTextField b3GTextField, String str) {
            this.val$LoginField = b3GTextField;
            this.val$number = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$LoginField.isIsTextFieldFocused()) {
                this.val$LoginField.getTextField().setText(this.val$LoginField.getTextField().getText() + this.val$number);
                actionEvent.consume();
            }
        }
    }

    class 30 implements ActionListener {
        final /* synthetic */ B3GTextField val$LoginField;

        30(B3GTextField b3GTextField) {
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

    public ServiceResponse updateProfileProcess(String str, String str2, String str3, String str4) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("PHONE_OPERATOR", str2);
        if (str4.equals("2")) {
            hashtable.put("LANGUE", str3);
        } else {
            hashtable.put("EMAIL", str3);
        }
        hashtable.put("ISMODIFYOPERATORONLY", str4);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setServiceId(68);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ArrayList validateDeviceProcess(String str, String str2, String str3) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("DEVICE_INFO", str2);
        hashtable.put("CODE_OTP", str3);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setServiceId(61);
        return (ArrayList) serviceManager.Run(serviceRequest);
    }

    BlankService blankServiceProcess(ServiceResponse serviceResponse) {
        return new BlankService();
    }

    public static void closeCurrentPoUp() {
        Dialog dialog = d;
        if (dialog != null) {
            dialog.dispose();
        }
    }

    public void showPasswordPopUp(Button button, String str, String str2, boolean z) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        dialog.setPreferredW(Display.getInstance().getDisplayWidth());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "ToolbarWithLogOut");
        Container container = (Container) uIBuilder.findByName("BodyCnt", createContainer);
        Container container2 = (Container) uIBuilder.findByName("leftCnt", createContainer);
        Button button2 = (Button) uIBuilder.findByName("logOutBtn", createContainer);
        Button button3 = new Button(CihMBanking.backIcon);
        button3.setUIID("Container");
        Button button4 = new Button(CihMBanking.theme.getImage("Accueil.png 2"));
        button4.setUIID("Container");
        button4.addActionListener(new 31());
        button3.addActionListener(new 32(dialog));
        container2.add(button3);
        container2.add(button4);
        button2.addActionListener(new 33(new PopUpsManager(this.uiManager)));
        Container createContainer2 = uIBuilder.createContainer("/cihv3", "ChangePasswordStep2");
        createContainer2.setScrollableY(false);
        SpanLabel spanLabel = (SpanLabel) uIBuilder.findByName("messageLbl", createContainer2);
        ((SpanLabel) uIBuilder.findByName("SpanLabel1", createContainer2)).setText("");
        Label label = (Label) uIBuilder.findByName("title", createContainer2);
        spanLabel.setText(str);
        label.setText("Mot de passe");
        Container container3 = (Container) uIBuilder.findByName("CntPasswordHelp", createContainer2);
        ((Container) uIBuilder.findByName("loginTxtField", createContainer2)).setUIID("PassField");
        container3.setHidden(true);
        TextField textField = (TextField) uIBuilder.findByName("LoginTxt", createContainer2);
        textField.setHint("Mot de passe");
        textField.setConstraint(65538);
        textField.setCursorBlinkTimeOff(999999);
        textField.setFocusable(true);
        textField.getParent().setFocusable(true);
        textField.setEditable(true);
        textField.setEnabled(true);
        ((Container) uIBuilder.findByName("btnsCnt", createContainer2)).setLayout(new GridLayout(1, 2));
        Container container4 = (Container) uIBuilder.findByName("backCnt", createContainer2);
        Button button5 = (Button) uIBuilder.findByName("validBtn", createContainer2);
        button5.setUIID("sendBtn");
        button5.getAllStyles().setMargin(0, 0, 1, 0);
        button5.getAllStyles().setMarginUnit(1);
        Button button6 = new Button("Annuler");
        button6.setUIID("sendBtn");
        button6.getAllStyles().setMargin(0, 0, 1, 0);
        button6.getAllStyles().setMarginUnit(1);
        container4.add(button6);
        button6.addActionListener(new 34(dialog));
        button5.setPreferredH(Display.getInstance().getDisplayWidth() / 7);
        button5.addActionListener(new 35(textField, z, button, dialog, str2));
        createContainer2.revalidate();
        container.add(createContainer2);
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", true);
    }

    class 31 implements ActionListener {
        31() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PopUpsManager.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    class 32 implements ActionListener {
        final /* synthetic */ Dialog val$d1;

        32(Dialog dialog) {
            this.val$d1 = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d1.dispose();
        }
    }

    class 33 implements ActionListener {
        final /* synthetic */ PopUpsManager val$p;

        33(PopUpsManager popUpsManager) {
            this.val$p = popUpsManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$p.ShowDisconectingPopUp(PopUpsManager.this.uiManager.stateMachine);
        }
    }

    class 34 implements ActionListener {
        final /* synthetic */ Dialog val$d1;

        34(Dialog dialog) {
            this.val$d1 = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d1.dispose();
        }
    }

    class 35 implements ActionListener {
        final /* synthetic */ TextField val$LoginField;
        final /* synthetic */ boolean val$addFinger;
        final /* synthetic */ Button val$btn;
        final /* synthetic */ Dialog val$d1;
        final /* synthetic */ String val$resultMsg;

        35(TextField textField, boolean z, Button button, Dialog dialog, String str) {
            this.val$LoginField = textField;
            this.val$addFinger = z;
            this.val$btn = button;
            this.val$d1 = dialog;
            this.val$resultMsg = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            boolean z;
            ServiceResponse authenticateClientProcess = PopUpsManager.this.authenticateClientProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, this.val$LoginField.getText());
            if (authenticateClientProcess.getStatusCode().equals("000")) {
                Vector vector = new Vector();
                if (Storage.getInstance().exists("veclistCnxStorage")) {
                    vector = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
                }
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                if (this.val$addFinger) {
                    this.val$btn.setIcon(PopUpsManager.this.uiManager.ressource.getImage("radio_on.png"));
                    int i = 0;
                    while (true) {
                        if (i >= vector.size()) {
                            z = false;
                            break;
                        }
                        AccountLastConnection accountLastConnection = (AccountLastConnection) vector.elementAt(i);
                        if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical.equals(accountLastConnection.radical)) {
                            z = true;
                            AccountLastConnection accountLastConnection2 = new AccountLastConnection(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, simpleDateFormat.format(date), accountLastConnection.occurrence + 1);
                            vector.remove(i);
                            vector.add(i, accountLastConnection2);
                            break;
                        }
                        i++;
                    }
                    if (!z) {
                        vector.add(new AccountLastConnection(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, simpleDateFormat.format(date), 0));
                    }
                } else {
                    this.val$btn.setIcon(PopUpsManager.this.uiManager.ressource.getImage("radio_off.PNG"));
                    int i2 = 0;
                    while (true) {
                        if (i2 >= vector.size()) {
                            break;
                        }
                        if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical.equals(((AccountLastConnection) vector.elementAt(i2)).radical)) {
                            vector.remove(i2);
                            break;
                        }
                        i2++;
                    }
                }
                Storage.getInstance().writeObject("veclistCnxStorage", vector);
                this.val$d1.dispose();
                new UITimer(new PopUpsManager$35$$ExternalSyntheticLambda0(this, this.val$resultMsg)).schedule(300, false, PopUpsManager.this.uiManager.currentForm);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PopUpsManager.this.uiManager.stateMachine, DataTools.FormatUnicode(authenticateClientProcess.getStatusLabel()), null);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-cih-Module-PopUpsManager$35(String str) {
            PopUpsManager.this.uiManager.ShowMessageTransaction(PopUpsManager.this.uiManager.stateMachine, DataTools.FormatUnicode(str), null);
            Display.getInstance().callSerially(new 1());
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new PopUpsManager$35$1$$ExternalSyntheticLambda0(this)).schedule(300, false, PopUpsManager.this.uiManager.currentForm);
            }

            /* synthetic */ void lambda$run$0$com-b3g-ui-page-cih-Module-PopUpsManager$35$1() {
                PopUpsManager.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }
    }

    public ServiceResponse authenticateClientProcess(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("PASSWORD", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setServiceId(96);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public Container showLanguePopUpCont(ComboBox comboBox) {
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "changeOperator");
        Container container = (Container) uIBuilder.findByName("operatorRadioCnt", createContainer);
        Button button = (Button) uIBuilder.findByName("validBtn", createContainer);
        int i = 2;
        B3GRadioWithIcon b3GRadioWithIcon = new B3GRadioWithIcon("", new BoxLayout(2));
        b3GRadioWithIcon.setUIID("radio");
        b3GRadioWithIcon.addItem("العربية", "ma.png");
        b3GRadioWithIcon.addItem("English", "en.png");
        b3GRadioWithIcon.addItem("Español", "es.png");
        b3GRadioWithIcon.addItem("Français", "fr.png");
        String str = Preferences.get("CihMobileLang", L10NManager.getInstance().getLanguage());
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2144569262:
                if (str.equals("العربية")) {
                    c = 0;
                    break;
                }
                break;
            case -1827230247:
                if (str.equals("الفرنسية")) {
                    c = 1;
                    break;
                }
                break;
            case -1575530339:
                if (str.equals("Français")) {
                    c = 2;
                    break;
                }
                break;
            case 0:
                if (str.equals("")) {
                    c = 3;
                    break;
                }
                break;
            case 2097:
                if (str.equals("AR")) {
                    c = 4;
                    break;
                }
                break;
            case 2217:
                if (str.equals("EN")) {
                    c = 5;
                    break;
                }
                break;
            case 2222:
                if (str.equals("ES")) {
                    c = 6;
                    break;
                }
                break;
            case 2252:
                if (str.equals("FR")) {
                    c = 7;
                    break;
                }
                break;
            case 3121:
                if (str.equals("ar")) {
                    c = '\b';
                    break;
                }
                break;
            case 3241:
                if (str.equals("en")) {
                    c = '\t';
                    break;
                }
                break;
            case 3246:
                if (str.equals("es")) {
                    c = '\n';
                    break;
                }
                break;
            case 3276:
                if (str.equals("fr")) {
                    c = 11;
                    break;
                }
                break;
            case 60895824:
                if (str.equals("English")) {
                    c = '\f';
                    break;
                }
                break;
            case 63521395:
                if (str.equals("Arabe")) {
                    c = '\r';
                    break;
                }
                break;
            case 212156143:
                if (str.equals("Español")) {
                    c = 14;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 4:
            case '\b':
            case '\r':
            default:
                i = 0;
                break;
            case 1:
            case 2:
            case 3:
            case 7:
            case 11:
                i = 3;
                break;
            case 5:
            case '\t':
            case '\f':
                i = 1;
                break;
            case 6:
            case '\n':
            case 14:
                break;
        }
        b3GRadioWithIcon.setSelectedIndex(i);
        button.addActionListener(new 36(comboBox, b3GRadioWithIcon));
        container.add(b3GRadioWithIcon.GetContainer());
        return createContainer;
    }

    class 36 implements ActionListener {
        final /* synthetic */ B3GRadioWithIcon val$b3GRadioLg;
        final /* synthetic */ ComboBox val$operatCombo;

        36(ComboBox comboBox, B3GRadioWithIcon b3GRadioWithIcon) {
            this.val$operatCombo = comboBox;
            this.val$b3GRadioLg = b3GRadioWithIcon;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:68:0x002d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void actionPerformed(com.codename1.ui.events.ActionEvent r18) {
            /*
                Method dump skipped, instructions count: 858
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.cih.Module.PopUpsManager.36.actionPerformed(com.codename1.ui.events.ActionEvent):void");
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                PopUpsManager.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }
    }

    public Dialog showPopUp(StateMachine stateMachine, Container container) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        dialog.setPreferredW(Display.getInstance().getDisplayWidth());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "ToolbarWithLogOut");
        Container container2 = (Container) uIBuilder.findByName("BodyCnt", createContainer);
        container2.setLayout(new BoxLayout(2));
        Button button = (Button) uIBuilder.findByName("logOutBtn", createContainer);
        Container container3 = (Container) uIBuilder.findByName("leftCnt", createContainer);
        Button button2 = (Button) uIBuilder.findByName("backBtn", createContainer);
        button2.setIcon(CihMBanking.backIcon);
        button2.setUIID("Container");
        Button button3 = new Button(CihMBanking.theme.getImage("Accueil.png 2"));
        button3.setUIID("Container");
        button3.addActionListener(new 37());
        button.addActionListener(new 38(stateMachine));
        button2.addActionListener(new 39(dialog));
        container3.add(button3);
        container2.add(container);
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", true);
        return dialog;
    }

    class 37 implements ActionListener {
        37() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PopUpsManager.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    class 38 implements ActionListener {
        final /* synthetic */ StateMachine val$stsMachine;

        38(StateMachine stateMachine) {
            this.val$stsMachine = stateMachine;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PopUpsManager.this.ShowDisconectingPopUp(this.val$stsMachine);
        }
    }

    class 39 implements ActionListener {
        final /* synthetic */ Dialog val$d1;

        39(Dialog dialog) {
            this.val$d1 = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d1.dispose();
        }
    }

    public static void showNotificationPopUp() {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources.open("/cihv3.res");
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(false);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(255);
            dialog.getStyle().setPadding(0, 0, 0, 0);
            dialog.getStyle().setMargin(0, 0, 0, 0);
            dialog.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog.revalidate();
            Component createContainer = new UIBuilder().createContainer("/cihv3", "PopUpNotification");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW((Display.getInstance().getDisplayWidth() * 100) / 100);
            Container container = new Container(new BorderLayout());
            container.setUIID("margin_2_2_2_2");
            Button button = new Button("Fermer");
            container.setLeadComponent(button);
            button.setUIID("ad_lblValueBlue");
            button.addActionListener(new 40(dialog));
            container.add("East", button);
            dialog.addComponent("North", container);
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 40 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        40(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public Dialog showblockedMajUpdatePopUp(StateMachine stateMachine, String[] strArr, String str) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        dialog.setPreferredW(Display.getInstance().getDisplayWidth());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "PopUpMaj");
        Container container = (Container) uIBuilder.findByName("BodyCnt", createContainer);
        Container container2 = (Container) uIBuilder.findByName("Container3", createContainer);
        Container container3 = (Container) uIBuilder.findByName("Content", createContainer);
        Container container4 = (Container) uIBuilder.findByName("BtnsCnt", createContainer);
        container4.getAllStyles().setPaddingUnit(1);
        container4.getAllStyles().setPadding(1.5f, 1.5f, 1.0f, 1.0f);
        container3.setScrollableY(true);
        container3.setScrollVisible(false);
        container2.setScrollableY(false);
        container.setScrollableY(false);
        container2.setScrollVisible(false);
        container.setScrollVisible(false);
        container2.setScrollableY(false);
        container.setScrollableY(true);
        ((Button) uIBuilder.findByName("logOutBtn", createContainer)).addActionListener(new 41(stateMachine));
        for (String str2 : strArr) {
            SpanLabel spanLabel = new SpanLabel(str2, "txtExoLargeSmall");
            spanLabel.setUIID("margin_1_1_1_1");
            spanLabel.setIcon(this.uiManager.ressource.getImage("slider_arrow_w.png"));
            spanLabel.setGap(10);
            container.add(spanLabel);
        }
        container.setScrollVisible(false);
        Label label = (Label) uIBuilder.findByName("VersionLbl", createContainer);
        label.setText(str);
        label.setHidden(true);
        ((Button) uIBuilder.findByName("ValidateBtn", createContainer)).addActionListener(new PopUpsManager$$ExternalSyntheticLambda2(dialog));
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.addComponent("Center", createContainer);
        Settings.setNightMode(createContainer, 0);
        dialog.showPacked("Center", true);
        return dialog;
    }

    class 41 implements ActionListener {
        final /* synthetic */ StateMachine val$stsMachine;

        41(StateMachine stateMachine) {
            this.val$stsMachine = stateMachine;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PopUpsManager.this.ShowDisconectingPopUp(this.val$stsMachine);
        }
    }

    static /* synthetic */ void lambda$showblockedMajUpdatePopUp$1(Dialog dialog, ActionEvent actionEvent) {
        dialog.dispose();
    }

    public Dialog showBlokedSecurityPopUp(StateMachine stateMachine, String[] strArr) {
        SpanLabel spanLabel;
        SpanLabel spanLabel2;
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        dialog.setPreferredW(Display.getInstance().getDisplayWidth());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "PopUpMaj");
        Button button = (Button) uIBuilder.findByName("logOutBtn", createContainer);
        Component component = (Container) uIBuilder.findByName("LogoCnt", createContainer);
        component.getParent().removeComponent(component);
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(new GridLayout(1, 1));
        Container container3 = new Container(BoxLayout.y());
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2.0f, 2.0f, 3.0f, 3.0f);
        container3.setScrollableY(true);
        container3.setScrollVisible(false);
        container2.setScrollVisible(false);
        container.setScrollVisible(false);
        button.addActionListener(new 42(stateMachine));
        ImageViewer imageViewer = new ImageViewer();
        imageViewer.setImage(CihMBanking.theme.getImage("Security3x.png"));
        imageViewer.getAllStyles().setPaddingUnit(1);
        imageViewer.getAllStyles().setPadding(2.0f, 2.0f, 1.0f, 1.0f);
        container3.add(FlowLayout.encloseCenter(imageViewer));
        for (int i = 0; i < strArr.length; i++) {
            if (i == 0) {
                spanLabel2 = new SpanLabel(strArr[i], "gb_lblGlobalHeaderTitleV2");
                spanLabel2.getAllStyles().setPaddingUnit(1);
                spanLabel2.getAllStyles().setPadding(2.0f, 0.5f, 0.0f, 0.0f);
                spanLabel2.setGap(10);
                if (CihMBanking.RTL) {
                    spanLabel2.getTextAllStyles().setAlignment(1);
                } else {
                    spanLabel2.getTextAllStyles().setAlignment(3);
                }
            } else if (i == 5) {
                spanLabel2 = new SpanLabel(strArr[i], "gb_lblGlobalHeaderTitleV2");
                spanLabel2.getAllStyles().setPaddingUnit(1);
                spanLabel2.getAllStyles().setPadding(0.5f, 2.0f, 0.0f, 0.0f);
                spanLabel2.setGap(10);
                if (!CihMBanking.RTL) {
                    spanLabel2.getTextAllStyles().setAlignment(1);
                } else {
                    spanLabel2.getTextAllStyles().setAlignment(3);
                }
            } else {
                if (i < 6) {
                    spanLabel = new SpanLabel(strArr[i], "txtExoLargeSmall");
                    spanLabel.setUIID("margin_1_1_1_1");
                    spanLabel.setGap(10);
                    spanLabel.getTextAllStyles().setFgColor(0);
                    if (CihMBanking.RTL) {
                        spanLabel.getTextAllStyles().setAlignment(1);
                    } else {
                        spanLabel.getTextAllStyles().setAlignment(3);
                    }
                } else {
                    spanLabel = new SpanLabel(strArr[i], "txtExoLargeSmall   ");
                    spanLabel.setUIID("margin_1_1_1_1");
                    spanLabel.setGap(10);
                    spanLabel.getTextAllStyles().setFgColor(0);
                    if (!CihMBanking.RTL) {
                        spanLabel.getTextAllStyles().setAlignment(1);
                    } else {
                        spanLabel.getTextAllStyles().setAlignment(3);
                    }
                }
                spanLabel2 = spanLabel;
            }
            container3.add(spanLabel2);
        }
        Label label = new Label(" ", "Container");
        container3.add(label);
        Button button2 = new Button("OK");
        button2.setUIID("rateBtn");
        button2.getAllStyles().setPaddingUnit(1);
        button2.getAllStyles().setPadding(1.5f, 1.5f, 1.0f, 1.0f);
        container2.add(button2);
        button2.addActionListener(new PopUpsManager$$ExternalSyntheticLambda1(this, container3, label, dialog));
        container.add("North", component);
        container.add("Center", container3);
        container.add("South", container2);
        container.setPreferredH(Display.getInstance().getDisplayHeight());
        container.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.addComponent("Center", container);
        createContainer.revalidate();
        dialog.showPacked("Center", true);
        return dialog;
    }

    class 42 implements ActionListener {
        final /* synthetic */ StateMachine val$stsMachine;

        42(StateMachine stateMachine) {
            this.val$stsMachine = stateMachine;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PopUpsManager.this.ShowDisconectingPopUp(this.val$stsMachine);
        }
    }

    /* synthetic */ void lambda$showBlokedSecurityPopUp$2$com-b3g-ui-page-cih-Module-PopUpsManager(Container container, Label label, Dialog dialog, ActionEvent actionEvent) {
        if (!this.isScrolledSecurityPopUp) {
            container.scrollComponentToVisible(label);
            this.isScrolledSecurityPopUp = true;
        } else {
            Integer valueOf = Integer.valueOf(Integer.parseInt(Preferences.get("ReadNbSecurityMsg14", "0")) + 1);
            CihMBanking.sesPMTR.isSerurityPopUpReaded = false;
            Preferences.set("ReadNbSecurityMsg14", valueOf.toString());
            dialog.dispose();
        }
    }

    public Dialog showApplePayPopUpCont(StateMachine stateMachine) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        dialog.setPreferredW(Display.getInstance().getDisplayWidth());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "PopUpMaj2");
        ((Button) uIBuilder.findByName("ContinueBtn", createContainer)).addActionListener(new 43(dialog));
        ((Button) uIBuilder.findByName("PlustardBtn", createContainer)).addActionListener(new PopUpsManager$$ExternalSyntheticLambda0(dialog));
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", true);
        return dialog;
    }

    class 43 implements ActionListener {
        final /* synthetic */ Dialog val$d1;

        43(Dialog dialog) {
            this.val$d1 = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d1.dispose();
            NativeCall nativeCall = (NativeCall) NativeLookup.create(NativeCall.class);
            Preferences.set("confirmReadNoteApplPAy", Integer.valueOf(Integer.parseInt(Preferences.get("confirmReadNoteApplPAy", "0")) + 1).toString());
            nativeCall.callCihPaySDK(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, ((Account) CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().get(0)).accountNumber, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        }
    }

    static /* synthetic */ void lambda$showApplePayPopUpCont$3(Dialog dialog, ActionEvent actionEvent) {
        Preferences.set("confirmReadNoteApplPAy", Integer.valueOf(Integer.parseInt(Preferences.get("confirmReadNoteApplPAy", "0")) + 1).toString());
        dialog.dispose();
    }

    public Dialog showBlokedConfirmEmailPopUp(StateMachine stateMachine) {
        return new ConfirmEMail(this.uiManager).getPageDialog(stateMachine);
    }

    public void showGamerOffrePopUp() {
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            Dialog dialog = new Dialog();
            dialog.setUIID("Container");
            dialog.getDialogStyle().setBgTransparency(0);
            dialog.setLayout(new BorderLayout());
            dialog.setScrollableY(false);
            dialog.setPreferredW(Display.getInstance().getDisplayWidth());
            dialog.setPreferredH(Display.getInstance().getDisplayWidth());
            CihMBanking.sesPMTR.setGamerPopupState(false);
            Preferences.set("gamerPopupCounter", Integer.valueOf(Integer.parseInt(Preferences.get("gamerPopupCounter", "0")) + 1).toString());
            Container container = new Container(new GridLayout(1, 1));
            Container container2 = new Container(new BorderLayout());
            ImageViewer imageViewer = new ImageViewer();
            imageViewer.setImage(CihMBanking.theme.getImage("gamer-popup.png"));
            Button button = new Button("");
            button.addActionListener(new 44(dialog));
            container2.add("Center", imageViewer);
            container2.add("North", button);
            container2.setLeadComponent(button);
            container.add(container2);
            container.setPreferredH(Display.getInstance().getDisplayWidth());
            container.setPreferredW(Display.getInstance().getDisplayWidth());
            dialog.addComponent("Center", container);
            dialog.showPacked("Center", false);
            dialog.setDisposeWhenPointerOutOfBounds(true);
        }
    }

    class 44 implements ActionListener {
        final /* synthetic */ Dialog val$d1;

        44(Dialog dialog) {
            this.val$d1 = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d1.dispose();
            CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(173);
        }
    }
}
