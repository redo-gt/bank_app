package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.services.Account;
import com.b3g.services.AccountAutorisation;
import com.b3g.services.AccountOperation;
import com.b3g.services.AgenceDemandeHistorique;
import com.b3g.services.B3gService;
import com.b3g.services.BancBeneficary;
import com.b3g.services.Beneficiary;
import com.b3g.services.Card;
import com.b3g.services.CardOperation;
import com.b3g.services.CashOutOperatoion;
import com.b3g.services.CheckbookDemand;
import com.b3g.services.FatouratyService;
import com.b3g.services.GetDotation;
import com.b3g.services.LCNBoockDemand;
import com.b3g.services.MDMAccountOperation;
import com.b3g.services.MTCHistoric;
import com.b3g.services.MTCImpaye;
import com.b3g.services.Messagerie;
import com.b3g.services.MyCheckLcn;
import com.b3g.services.MyEDocumentsService;
import com.b3g.services.Notification;
import com.b3g.services.OrderCurrencyHistoricDataService;
import com.b3g.services.OrderMADHistoricDataService;
import com.b3g.services.OvpExecutionService;
import com.b3g.services.OvpService;
import com.b3g.services.PinCard;
import com.b3g.services.QRModel;
import com.b3g.services.RecurringDetail;
import com.b3g.services.ReleveDgi;
import com.b3g.services.ResponseDotation;
import com.b3g.services.StudentTransferHisoricDataService;
import com.b3g.services.TransferHistoric;
import com.b3g.services.TransfertDATA;
import com.b3g.services.TransfertHistorique;
import com.b3g.services.Walet;
import com.b3g.tools.DataTools;
import com.b3g.tools.SortBenifByAlias;
import com.b3g.tools.StringTools;
import com.b3g.tools.SyncTask;
import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.menu.SideTreeComponent;
import com.b3g.ui.menu.SideTreeComponentOffLigne;
import com.b3g.ui.menu.TreeMenuHardData;
import com.b3g.ui.menu.TreeMenuHardDataOffline;
import com.b3g.ui.page.TopUpNewPage;
import com.b3g.ui.page.TransferNewPage;
import com.b3g.ui.page.TransfertWallet;
import com.b3g.ui.page.cih.Module.CustomToolbar;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.Storage;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.Transition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import com.wefeel.QRMaker.QRMaker;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CihUiManager extends B3GUiManager {
    public static Button btnNotification;
    Button BtnMessag;
    Container LeftCnt;
    Button bouclier;
    Container btnCnt;
    Button btnHome;
    Button btnLogoOffline;
    Container centerCnt;
    Toolbar deepToolbar;
    Toolbar homeToolBar;
    Image icon;
    Image iconCerclNotif;
    boolean isHomeThere;
    boolean isNotif;
    boolean isPageNotificationHere;
    Label lbl;
    int nbrNotif;
    CihTab tabManager;
    String title;
    boolean NotifInTime = false;
    boolean showMesgIcon = true;
    String codeStatus = "OK";

    public CihUiManager() {
    }

    public CihUiManager(StateMachine stateMachine, Resources resources) {
        this.stateMachine = stateMachine;
        this.ressource = resources;
        this.tabManager = new CihTab();
        this.NavigationHistory = new ArrayList();
    }

    public void DrawNavigation(Container container, Form form) {
        SideTreeComponent sideTreeComponent;
        this.currentForm = form;
        CihMBanking.sesPMTR.setMenuStatus(0);
        int displayWidth = ((Display.getInstance().getDisplayWidth() - this.ressource.getImage("Mes_comptesV2.png").getWidth()) * 17) / 100;
        TreeMenuHardData treeMenuHardData = new TreeMenuHardData();
        if (CihMBanking.sesPMTR.isToCut()) {
            sideTreeComponent = new SideTreeComponent(treeMenuHardData.GetTDMNModel(), this);
        } else {
            sideTreeComponent = new SideTreeComponent(treeMenuHardData.GetNewModel(), this);
        }
        sideTreeComponent.setScrollVisible(false);
        CihMBanking.sesPMTR.setCurrentTreeComponent(sideTreeComponent);
        SetCommands();
    }

    public void DrawNavigationOffligne(Container container, Form form) {
        this.currentForm = form;
        CihMBanking.sesPMTR.setMenuStatus(0);
        int displayWidth = ((Display.getInstance().getDisplayWidth() - this.ressource.getImage("Mes_comptesV2.png").getWidth()) * 17) / 100;
        SideTreeComponentOffLigne sideTreeComponentOffLigne = new SideTreeComponentOffLigne(new TreeMenuHardDataOffline().GetNewModel(), this);
        sideTreeComponentOffLigne.setScrollVisible(false);
        CihMBanking.sesPMTR.setCurrentTreeComponent(sideTreeComponentOffLigne);
        SetCommandsOffligne();
    }

    void ShowDisconectingPopUp(StateMachine stateMachine) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = this.stateMachine.createContainer(open, "PopUpDeisconect");
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
            this.stateMachine.findBtnYest(createContainer).addActionListener(new 1(stateMachine, open));
            this.stateMachine.findBtnYestIcon(createContainer).addActionListener(new 2(stateMachine, open));
            this.stateMachine.findBtnNo(createContainer).addActionListener(new 3(dialog));
            this.stateMachine.findBtnNoIcon(createContainer).addActionListener(new 4(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 1 implements ActionListener {
        final /* synthetic */ Resources val$myres;
        final /* synthetic */ StateMachine val$stsMachine;

        1(StateMachine stateMachine, Resources resources) {
            this.val$stsMachine = stateMachine;
            this.val$myres = resources;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$stsMachine.uiManager = new CihUiManager(this.val$stsMachine, this.val$myres);
            CihMBanking.sesPMTR = null;
            CihMBanking.sesPMTR = new SessionParameter();
            this.val$stsMachine.showForm("LoginV2New", (Command) null);
            CihMBanking.isConnected = false;
            CihMBanking.isConnecting = "00";
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Resources val$myres;
        final /* synthetic */ StateMachine val$stsMachine;

        2(StateMachine stateMachine, Resources resources) {
            this.val$stsMachine = stateMachine;
            this.val$myres = resources;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$stsMachine.uiManager = new CihUiManager(this.val$stsMachine, this.val$myres);
            CihMBanking.sesPMTR = null;
            CihMBanking.sesPMTR = new SessionParameter();
            this.val$stsMachine.showForm("LoginV2New", (Command) null);
            CihMBanking.isConnected = false;
            CihMBanking.isConnecting = "00";
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        3(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        4(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public void SetCommands() {
        Button button;
        new Notification();
        if (CihMBanking.sesPMTR.countNotif != -1) {
            int i = CihMBanking.sesPMTR.countNotif;
        }
        if (CihMBanking.sesPMTR.notifications.size() >= 1) {
            Iterator it = CihMBanking.sesPMTR.notifications.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((Notification) it.next()).read.equals("false")) {
                    this.icon = CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("Activ1.png");
                    break;
                }
            }
        } else {
            this.icon = CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("nonActiv1.png");
        }
        this.homeToolBar = new CustomToolbar(false);
        this.MenuCommand = new Command("");
        CihMBanking.sesPMTR.getCurrentTreeComponent().revalidate();
        this.MenuCommand.putClientProperty("SideComponent", CihMBanking.sesPMTR.getCurrentTreeComponent());
        this.SideLogoCommand = new Command("");
        if (Settings.isNightMode()) {
            this.SideLogoCommand.setIcon(this.ressource.getImage("LogoHeaderV3.png"));
        } else {
            this.SideLogoCommand.setIcon(this.ressource.getImage("LogoHeaderV2.png"));
        }
        this.SideLogoCommand.setEnabled(false);
        this.LeftCnt = new Container(new FlowLayout());
        Button button2 = new Button();
        this.BtnMessag = button2;
        button2.setUIID("margin_0_0_0_0");
        if (CihMBanking.sesPMTR.countMessage.equals("") || CihMBanking.sesPMTR.countMessage.equals("0") || CihMBanking.sesPMTR.countMessage == null) {
            this.BtnMessag.setIcon(this.ressource.getImage("MessnonActiv.png"));
        } else {
            this.BtnMessag.setIcon(this.ressource.getImage("Messactiv.png"));
        }
        this.BtnMessag.addActionListener(new 5());
        this.BtnParametres = new Button();
        this.BtnParametres.setUIID("margin_0_0_0_0");
        this.BtnParametres.setIcon(this.ressource.getImage("PMTRtopParametre.png"));
        this.BtnParametres.addActionListener(new 6());
        Button button3 = new Button(this.ressource.getImage("Deconnexion.png 1"));
        button3.setUIID(null);
        button3.addActionListener(new 7());
        if (CihMBanking.sesPMTR.notifications.size() > 1) {
            Iterator it2 = CihMBanking.sesPMTR.notifications.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                } else if (((Notification) it2.next()).read.equals("false")) {
                    this.icon = CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("Activ1.png");
                    break;
                }
            }
        } else {
            this.icon = CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("nonActiv1.png");
        }
        Container container = new Container(new LayeredLayout());
        Button button4 = new Button(this.icon);
        btnNotification = button4;
        button4.setUIID(null);
        btnNotification.setDisabledIcon(this.ressource.getImage("1transparent.png"));
        btnNotification.setUIID(null);
        btnNotification.setVisible(true);
        btnNotification.addActionListener(new 8());
        this.btnBack = new Button(CihMBanking.backIcon);
        this.btnBack.setDisabledIcon(this.ressource.getImage("1transparent.png"));
        this.btnBack.setUIID(null);
        this.btnBack.setVisible(false);
        this.btnBack.getStyle().setPadding(0, 0, 1, 1);
        this.btnBack.addActionListener(new 9());
        Button button5 = new Button(this.ressource.getImage("Accueil.png 2"));
        this.btnHome = button5;
        button5.setDisabledIcon(this.ressource.getImage("1transparent.png"));
        this.btnHome.setUIID(null);
        this.btnHome.setVisible(false);
        this.btnHome.addActionListener(new 10());
        new Button(this.ressource.getImage("LogoHeaderV2.png"));
        if (Settings.isNightMode()) {
            button = new Button(this.ressource.getImage("LogoHeaderV3.png"));
        } else {
            button = new Button(this.ressource.getImage("LogoHeaderV2.png"));
        }
        button.setUIID("BlankCnt");
        Container container2 = new Container(new FlowLayout(4, 4));
        Container container3 = new Container(new LayeredLayout());
        Container container4 = new Container(new BorderLayout());
        container3.addComponent(new Label());
        container3.addComponent(container2);
        container4.addComponent("East", container3);
        container4.getStyle().setMargin(0, 1, 0, 0);
        container.addComponent(btnNotification);
        if (this.nbrNotif != 0) {
            container.addComponent(container4);
        }
        container.getStyle().setPadding(0, 0, 1, 1);
        Container container5 = new Container(new FlowLayout());
        this.btnCnt = container5;
        container5.getAllStyles().setMargin(1, 0, 0, 0);
        this.btnCnt.getAllStyles().setMarginUnit(1);
        ((FlowLayout) this.btnCnt.getLayout()).setValign(4);
        ((FlowLayout) this.btnCnt.getLayout()).setValignByRow(true);
        this.btnCnt.addComponent(this.btnBack);
        this.btnCnt.addComponent(this.btnHome);
        this.btnCnt.addComponent(this.BtnParametres);
        this.btnCnt.addComponent(button3);
        this.LeftCnt.getAllStyles().setMargin(1, 0, 0, 0);
        this.LeftCnt.getAllStyles().setMarginUnit(1);
        ((FlowLayout) this.LeftCnt.getLayout()).setValign(4);
        ((FlowLayout) this.LeftCnt.getLayout()).setValignByRow(true);
        this.LeftCnt.addComponent(this.BtnMessag);
        this.LeftCnt.addComponent(container);
        this.currentForm.setToolbar(this.homeToolBar);
        Toolbar.setOnTopSideMenu(false);
        this.currentForm.addCommand(this.SideLogoCommand);
        this.homeToolBar.addCommandToLeftSideMenu(this.MenuCommand);
        Container container6 = new Container(new BorderLayout());
        Component.setSameHeight(this.homeToolBar, container6);
        Component.setSameWidth(this.homeToolBar, container6);
        new Container(new BoxLayout(2)).setUIID("g_cntFooterV22");
        TextField textField = new TextField();
        textField.setUIID("Container");
        textField.setEditable(false);
        textField.setColumns(20);
        textField.setRows(1);
        textField.setMaxSize(124);
        Container container7 = new Container(new FlowLayout());
        ((FlowLayout) container7.getLayout()).setValign(4);
        ((FlowLayout) container7.getLayout()).setValignByRow(true);
        Container container8 = new Container(new BorderLayout());
        container8.addComponent("Center", textField);
        container8.addComponent("East", this.btnCnt);
        container8.addComponent("West", this.LeftCnt);
        container7.addComponent(container8);
        container6.addComponent("Center", container7);
        container6.setUIID("commandCnt");
        Container container9 = new Container(new LayeredLayout());
        Container container10 = new Container(new FlowLayout(4, 4));
        Container container11 = new Container();
        container11.setUIID("margin_0_0_3_3");
        container10.addComponent(button);
        container10.addComponent(container11);
        container9.removeAll();
        container9.addComponent(container10);
        container9.addComponent(container6);
        this.stateMachine.findCntHomeV2Header(this.currentForm).removeAll();
        this.homeToolBar.setTitleComponent(container9);
        this.homeToolBar.setTitleCentered(false);
        this.homeToolBar.setPreferredW(this.currentForm.getPreferredW());
        if (!Display.getInstance().isSimulator() && ((TouchIDNativeCall) NativeLookup.create(TouchIDNativeCall.class)).getBiometryType().equals("FACEID")) {
            container10.getStyle().setMargin(4, 0, 0, 0);
        }
        this.isHomeThere = false;
        this.currentForm.setSafeArea(false);
        this.currentForm.setSafeAreaRoot(false);
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            new Messagerie();
            CihUiManager.this.NavigateToPageById(91);
        }
    }

    class 6 implements ActionListener {
        6() {
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new CihUiManager$6$1$$ExternalSyntheticLambda0(this)).schedule(120, false, CihUiManager.this.currentForm);
            }

            /* synthetic */ void lambda$run$0$com-b3g-ui-CihUiManager$6$1() {
                CihUiManager.this.NavigateToPageById(104);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }

    class 7 implements ActionListener {
        7() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihUiManager cihUiManager = CihUiManager.this;
            cihUiManager.ShowDisconectingPopUp(cihUiManager.stateMachine);
        }
    }

    class 8 implements ActionListener {
        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                CihUiManager.this.isPageNotificationHere = true;
                CihUiManager.this.isHomeThere = false;
                CihUiManager.this.nbrNotif = 0;
                CihUiManager.this.NavigateToPageById(77);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihUiManager.this.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }
    }

    class 9 implements ActionListener {
        9() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihUiManager.this.GoBack();
        }
    }

    class 10 implements ActionListener {
        10() {
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new CihUiManager$10$1$$ExternalSyntheticLambda0(this)).schedule(300, false, CihUiManager.this.currentForm);
            }

            /* synthetic */ void lambda$run$0$com-b3g-ui-CihUiManager$10$1() {
                CihUiManager.this.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihUiManager.this.nbrNotif = 0;
            Display.getInstance().callSerially(new 1());
        }
    }

    public void SetCommandsOffligne() {
        this.homeToolBar = new Toolbar(true);
        this.MenuCommand = new Command("");
        Container container = new Container(new LayeredLayout());
        CihMBanking.sesPMTR.getCurrentTreeComponent().revalidate();
        this.MenuCommand.putClientProperty("SideComponent", CihMBanking.sesPMTR.getCurrentTreeComponent());
        Container container2 = new Container(new FlowLayout(3, 4));
        Container container3 = new Container(new LayeredLayout());
        Button button = new Button();
        button.setUIID("Container");
        Button button2 = new Button(this.ressource.getImage("bouclier.png"));
        this.bouclier = button2;
        button2.setDisabledIcon(this.ressource.getImage("1transparent.png"));
        this.bouclier.setUIID("BlankCnt");
        this.bouclier.setVisible(true);
        this.bouclier.getAllStyles().setPadding(0, 0, 50, 5);
        button.addActionListener(new 11(container));
        Button button3 = new Button(this.ressource.getImage("LogoHeaderV3.png"));
        this.btnLogoOffline = button3;
        button3.setUIID("BlankCnt");
        this.btnLogoOffline.setName("toolbar_logo");
        container2.setUIID("BlankCnt");
        container2.getAllStyles().setMargin(0, 1, 0, 2);
        container2.getAllStyles().setMarginUnit(1);
        container2.addComponent(this.bouclier);
        container3.add(container2);
        container3.add(button);
        this.currentForm.setToolbar(this.homeToolBar);
        Toolbar.setOnTopSideMenu(false);
        this.homeToolBar.addCommandToSideMenu(this.MenuCommand);
        Container container4 = new Container(new BorderLayout());
        Component.setSameWidth(this.homeToolBar, container4);
        this.btnBackOffline = new Button(CihMBanking.backIcon);
        this.btnBackOffline.setDisabledIcon(this.ressource.getImage("1transparent.png"));
        this.btnBackOffline.setUIID("BlankCnt");
        this.btnBackOffline.setVisible(false);
        this.btnBackOffline.getAllStyles().setMarginUnit(1);
        this.btnBackOffline.getAllStyles().setMargin(0, 1, 0, 2);
        container4.addComponent("West", this.btnBackOffline);
        container4.addComponent("East", container3);
        container4.setUIID("commandCnt");
        Container container5 = new Container(new FlowLayout(4, 4));
        container5.getAllStyles().setMargin(0, 0, 0, 5);
        container5.getAllStyles().setMarginUnit(2);
        container5.addComponent(this.btnLogoOffline);
        container.removeAll();
        container.addComponent(container5);
        container.addComponent(container4);
        if (!Display.getInstance().isSimulator() && ((TouchIDNativeCall) NativeLookup.create(TouchIDNativeCall.class)).getBiometryType().equals("FACEID")) {
            container5.getStyle().setMargin(4, 0, 0, 0);
        }
        button.setPreferredW(container4.getPreferredW());
        this.homeToolBar.setTitleComponent(container);
        this.homeToolBar.setPreferredW(this.currentForm.getPreferredW());
        Settings.setNightMode(this.currentForm, 0);
        Settings.setNightMode(this.homeToolBar, 0);
        CihMBanking.sesPMTR.setToolbarHeight(this.homeToolBar.getPreferredH());
        this.currentForm.setSafeArea(false);
        this.currentForm.setSafeAreaRoot(false);
        CihMBanking.sesPMTR.setLogoButton(this.btnLogoOffline);
    }

    class 11 implements ActionListener {
        final /* synthetic */ Container val$mainCnt;

        11(Container container) {
            this.val$mainCnt = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Dialog dialog = new Dialog();
            Container createContainer = CihUiManager.this.uib.createContainer("/cihv3", "3DSecureCont");
            Button button = (Button) CihUiManager.this.uib.findByName("Button", createContainer);
            dialog.setLayout(new BorderLayout());
            dialog.getAllStyles().setBgTransparency(0);
            dialog.add("North", createContainer);
            dialog.setDialogUIID("Container");
            dialog.setDisposeWhenPointerOutOfBounds(true);
            button.addActionListener(new 1(dialog));
            dialog.setBackCommand(new 2("Back", dialog));
            int displayHeight = Display.getInstance().getDisplayHeight();
            int preferredH = this.val$mainCnt.getPreferredH();
            dialog.show(preferredH - 25, displayHeight - ((createContainer.getPreferredH() + preferredH) + 200), 20, 5);
        }

        class 1 implements ActionListener {
            final /* synthetic */ Dialog val$dlg;

            1(Dialog dialog) {
                this.val$dlg = dialog;
            }

            public void actionPerformed(ActionEvent actionEvent) {
                this.val$dlg.dispose();
                if (CihUiManager.this.btnBackOffline.getListeners() != null) {
                    CihUiManager.this.btnBackOffline.getListeners().clear();
                }
                CihUiManager.this.NavigateToPageByIdOffligne(85);
            }
        }

        class 2 extends Command {
            final /* synthetic */ Dialog val$dlg;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            2(String str, Dialog dialog) {
                super(str);
                this.val$dlg = dialog;
            }

            public void actionPerformed(ActionEvent actionEvent) {
                this.val$dlg.dispose();
            }
        }
    }

    public void SetCommandsBis() {
        this.currentForm.removeAllCommands();
        this.ExitCommand = new 12("", this.stateMachine);
        this.ExitCommand.setIcon(this.ressource.getImage("Deconnexion.png 1"));
        this.ExitCommand.putClientProperty("TitleCommand", Boolean.TRUE);
        this.ExitCommand.putClientProperty("Right", Boolean.TRUE);
        this.HomeCommand = new 13("");
        this.HomeCommand.setIcon(this.ressource.getImage("Accueil.png 2"));
        this.HomeCommand.setDisabledIcon(this.ressource.getImage("1transparent.png"));
        this.HomeCommand.putClientProperty("TitleCommand", Boolean.TRUE);
        this.HomeCommand.putClientProperty("Right", Boolean.TRUE);
        this.BackCommand = new 14("");
        this.BackCommand.setIcon(CihMBanking.backIcon);
        this.BackCommand.setDisabledIcon(this.ressource.getImage("1transparent.png"));
        this.BackCommand.putClientProperty("TitleCommand", Boolean.TRUE);
        this.BackCommand.putClientProperty("Right", Boolean.TRUE);
        this.TitleLogoCommand = new 15("");
        this.NotificationCommand = new 16("");
        if (Settings.isNightMode()) {
            this.TitleLogoCommand.setIcon(this.ressource.getImage("LogoHeaderV3.png"));
        } else {
            this.TitleLogoCommand.setIcon(this.ressource.getImage("LogoHeaderV2.png"));
        }
        this.TitleLogoCommand.putClientProperty("TitleCommand", Boolean.TRUE);
        this.TitleLogoCommand.putClientProperty("Left", Boolean.TRUE);
        this.TitleLogoCommand.setEnabled(false);
        this.SideLogoCommand = new Command("");
        if (Settings.isNightMode()) {
            this.SideLogoCommand.setIcon(this.ressource.getImage("LogoHeaderV3.png"));
        } else {
            this.SideLogoCommand.setIcon(this.ressource.getImage("LogoHeaderV2.png"));
        }
        this.SideLogoCommand.setEnabled(false);
        this.MenuCommand = new Command("");
        CihMBanking.sesPMTR.getCurrentTreeComponent().revalidate();
        this.MenuCommand.putClientProperty("SideComponent", CihMBanking.sesPMTR.getCurrentTreeComponent());
        this.currentForm.addCommand(this.ExitCommand);
        this.currentForm.addCommand(this.SideLogoCommand);
        this.currentForm.addCommand(this.MenuCommand);
        this.currentForm.getTitleComponent().setIcon(this.ressource.getImage("LogoHeaderV2.png"));
        this.isHomeThere = false;
    }

    class 12 extends Command {
        final /* synthetic */ StateMachine val$stsMachine;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        12(String str, StateMachine stateMachine) {
            super(str);
            this.val$stsMachine = stateMachine;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihUiManager.this.ShowDisconectingPopUp(this.val$stsMachine);
        }
    }

    class 13 extends Command {
        13(String str) {
            super(str);
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new CihUiManager$13$1$$ExternalSyntheticLambda0(this)).schedule(300, false, CihUiManager.this.currentForm);
            }

            /* synthetic */ void lambda$run$0$com-b3g-ui-CihUiManager$13$1() {
                CihUiManager.this.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }

    class 14 extends Command {
        14(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihUiManager.this.GoBack();
        }
    }

    class 15 extends Command {
        15(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihUiManager.this.NavigateToPageById(1);
        }
    }

    class 16 extends Command {
        16(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihUiManager.this.stateMachine.uiManager.ShowMessageTransaction(CihUiManager.this.stateMachine, Storage.getInstance().readObject("Push notification").toString(), null);
        }
    }

    public void HandleBackVisibility() {
        Button button;
        if (Settings.isNightMode()) {
            button = new Button(this.ressource.getImage("LogoHeaderV3.png"));
        } else {
            button = new Button(this.ressource.getImage("LogoHeaderV2.png"));
        }
        button.setUIID("transparentCnt");
        if (CihMBanking.sesPMTR.getMenuType().equals("facebook")) {
            if (this.CurrentPageId == 1) {
                if (this.isHomeThere) {
                    this.currentForm.removeAllCommands();
                    this.currentForm.setToolBar(this.homeToolBar);
                    this.currentForm.addCommand(this.SideLogoCommand);
                    this.homeToolBar.addCommandToSideMenu(this.MenuCommand);
                    this.homeToolBar.setTitleComponent(button);
                    this.homeToolBar.addCommandToRightBar(this.ExitCommand);
                    this.homeToolBar.revalidate();
                }
                this.currentForm.setBackCommand(new 17(""));
                this.currentForm.revalidate();
                this.isHomeThere = false;
                return;
            }
            if (this.isHomeThere) {
                return;
            }
            Button button2 = new Button(this.ressource.getImage("Deconnexion.png 1"));
            button2.setUIID(null);
            button2.addActionListener(new 18());
            Button button3 = new Button(this.icon);
            button3.addActionListener(new 19());
            if (this.isPageNotificationHere) {
                button3.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("nonActiv1.png"));
                Notification notification = new Notification();
                if (CihMBanking.sesPMTR.listeNotifs.size() > 0 && notification.updateStatusNotifProcess(notification.FillNotificationFromParams()).getStatusCode().equals("000")) {
                    for (int i = 0; i < CihMBanking.sesPMTR.notifications.size(); i++) {
                        ((Notification) CihMBanking.sesPMTR.notifications.get(i)).statusNotif = "00";
                    }
                    new Container(new LayeredLayout());
                    button3.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("nonActiv1.png"));
                    button3.setUIID(null);
                    button3.setDisabledIcon(this.ressource.getImage("1transparent.png"));
                    button3.setVisible(true);
                    this.nbrNotif = 0;
                }
            }
            this.btnHome.setVisible(true);
            this.btnBack.setVisible(true);
            this.isHomeThere = true;
            return;
        }
        if (this.CurrentPageId == 1) {
            this.stateMachine.findBtnBack(this.currentForm).setEnabled(false);
            this.stateMachine.findBtnHome(this.currentForm).setEnabled(false);
        } else {
            if (this.CurrentPageId != 0) {
                this.stateMachine.findBtnBack(this.currentForm).setEnabled(true);
            }
            this.stateMachine.findBtnHome(this.currentForm).setEnabled(true);
        }
    }

    class 17 extends Command {
        public void actionPerformed(ActionEvent actionEvent) {
        }

        17(String str) {
            super(str);
        }
    }

    class 18 implements ActionListener {
        18() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihUiManager cihUiManager = CihUiManager.this;
            cihUiManager.ShowDisconectingPopUp(cihUiManager.stateMachine);
        }
    }

    class 19 implements ActionListener {
        19() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihUiManager.this.nbrNotif = 0;
            CihUiManager.this.NavigateToPageById(77);
        }
    }

    public void HandleBackVisibilityBis() {
        if (CihMBanking.sesPMTR.getMenuType().equals("facebook")) {
            if (this.CurrentPageId == 1) {
                if (this.isHomeThere) {
                    this.currentForm.removeCommand(this.ExitCommand);
                    this.currentForm.removeCommand(this.HomeCommand);
                    this.currentForm.removeCommand(this.BackCommand);
                    this.currentForm.removeCommand(this.SideLogoCommand);
                    this.currentForm.removeCommand(this.MenuCommand);
                    this.currentForm.removeAllCommands();
                    this.currentForm.revalidate();
                    this.currentForm.addCommand(this.ExitCommand);
                    this.currentForm.addCommand(this.SideLogoCommand);
                    this.currentForm.addCommand(this.MenuCommand);
                }
                this.currentForm.revalidate();
                this.isHomeThere = false;
                return;
            }
            if (this.isHomeThere) {
                return;
            }
            this.currentForm.removeAllCommands();
            this.currentForm.revalidate();
            this.currentForm.addCommand(this.BackCommand);
            this.currentForm.addCommand(this.HomeCommand);
            this.currentForm.addCommand(this.ExitCommand);
            this.currentForm.addCommand(this.SideLogoCommand);
            this.currentForm.addCommand(this.MenuCommand);
            this.isHomeThere = true;
            return;
        }
        if (this.CurrentPageId == 1) {
            this.stateMachine.findBtnBack(this.currentForm).setEnabled(false);
            this.stateMachine.findBtnHome(this.currentForm).setEnabled(false);
        } else {
            if (this.CurrentPageId != 0) {
                this.stateMachine.findBtnBack(this.currentForm).setEnabled(true);
            }
            this.stateMachine.findBtnHome(this.currentForm).setEnabled(true);
        }
    }

    public Container DrawListContainerTransfer(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Container container, Object obj3) {
        Container GetContainer = GetContainer(str, str2);
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList.size() > 0) {
            Tabs tabs = new Tabs();
            int size = arrayList.size();
            int i3 = (size / i) + (size % i);
            for (int i4 = 0; i4 < i3; i4++) {
                Container container2 = new Container(new BoxLayout(2));
                int i5 = i4 * i;
                for (int i6 = i5; i6 < Math.min(size - i5, i) + i5; i6++) {
                    container2.setScrollableY(false);
                    container2.setTensileDragEnabled(false);
                    if (i6 != i5) {
                        container2.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                    }
                    container2.addComponent(((B3gService) arrayList.get(i6)).DrawItem(this.stateMachine, this.ressource, i2, (B3gService) obj2, null, null));
                    container2.revalidate();
                }
                tabs.addTab(str2, container2);
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
                this.stateMachine.findCntHeaderPagerV2(GetContainer).removeAll();
                for (int i7 = 0; i7 < i3; i7++) {
                    Button button = new Button();
                    button.setIcon(this.ressource.getImage("slider_arrow_w.png"));
                    button.setDisabledIcon(this.ressource.getImage("slider_arrow.png"));
                    button.setUIID("gb_btnDot");
                    if (i7 == 0) {
                        button.setEnabled(true);
                    } else {
                        button.setEnabled(false);
                    }
                    this.stateMachine.findCntHeaderPagerV2(GetContainer).addComponent(button);
                }
                tabs.addSelectionListener(new 20(i3, GetContainer, tabs, container));
            }
        } else {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlign(4);
            flowLayout.setValign(4);
            flowLayout.setFillRows(true);
            Container container3 = new Container();
            container3.setLayout(flowLayout);
            container3.setUIID("g_CntTranspMsg");
            SpanLabel spanLabel = new SpanLabel();
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setText(str3);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container3.addComponent(spanLabel);
            GetContainer.addComponent(container3);
        }
        Settings.setNightMode(GetContainer, 0);
        return GetContainer;
    }

    class 20 implements SelectionListener {
        final /* synthetic */ Container val$cntGloableHeader;
        final /* synthetic */ Container val$pBeneficiaryContainer;
        final /* synthetic */ Tabs val$tab;
        final /* synthetic */ int val$totalContainers;

        20(int i, Container container, Tabs tabs, Container container2) {
            this.val$totalContainers = i;
            this.val$cntGloableHeader = container;
            this.val$tab = tabs;
            this.val$pBeneficiaryContainer = container2;
        }

        public void selectionChanged(int i, int i2) {
            if (this.val$totalContainers == 1) {
                CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).removeAll();
            } else {
                for (int i3 = 0; i3 < CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentCount(); i3++) {
                    if (i == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    } else if (i2 == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(true);
                    } else {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    }
                }
            }
            this.val$tab.getParent().getParent().revalidate();
            if (this.val$pBeneficiaryContainer != null) {
                CihUiManager.this.DrawListContainerBenef("GloabalContainerV2", "Compte à créditer", Boolean.TRUE, CihUiManager.this.getAccountBeneficiary(CihUiManager.this.GetCurrentIssuarAccountNumber((Container) this.val$tab.getTabComponentAt(i2))), 1, 17, "Aucun compte n'est disponible pour cette opération .", null, this.val$pBeneficiaryContainer);
                this.val$pBeneficiaryContainer.revalidate();
            }
        }
    }

    public Container DrawListContainerTransfertAgence(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2) {
        Container GetContainer = GetContainer(str, str2);
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
                    container.addComponent(((B3gService) arrayList.get(i6)).DrawItem(this.stateMachine, this.ressource, i2, (B3gService) obj2, null, null));
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
            GetContainer.addComponent(tabs);
            if (i3 > 1) {
                this.stateMachine.findCntHeaderPagerV2(GetContainer).removeAll();
                for (int i7 = 0; i7 < i3; i7++) {
                    Button button = new Button();
                    button.setIcon(this.ressource.getImage("slider_arrow_w.png"));
                    button.setDisabledIcon(this.ressource.getImage("slider_arrow.png"));
                    button.setUIID("gb_btnDot");
                    if (i7 == 0) {
                        button.setEnabled(true);
                    } else {
                        button.setEnabled(false);
                    }
                    this.stateMachine.findCntHeaderPagerV2(GetContainer).addComponent(button);
                }
                tabs.addSelectionListener(new 21(i3, GetContainer, tabs));
            }
        } else {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlign(4);
            flowLayout.setValign(4);
            flowLayout.setFillRows(true);
            Container container2 = new Container();
            container2.setLayout(flowLayout);
            container2.setUIID("g_CntTranspMsg");
            SpanLabel spanLabel = new SpanLabel();
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setText(str3);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container2.addComponent(spanLabel);
            GetContainer.addComponent(container2);
        }
        return GetContainer;
    }

    class 21 implements SelectionListener {
        final /* synthetic */ Container val$cntGloableHeader;
        final /* synthetic */ Tabs val$tab;
        final /* synthetic */ int val$totalContainers;

        21(int i, Container container, Tabs tabs) {
            this.val$totalContainers = i;
            this.val$cntGloableHeader = container;
            this.val$tab = tabs;
        }

        public void selectionChanged(int i, int i2) {
            if (this.val$totalContainers == 1) {
                CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).removeAll();
            } else {
                for (int i3 = 0; i3 < CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentCount(); i3++) {
                    if (i == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    } else if (i2 == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(true);
                    } else {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    }
                }
            }
            this.val$tab.getParent().getParent().revalidate();
        }
    }

    public Container DrawListContainerWalletTransfer(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Container container, Object obj3) {
        Container GetContainer = GetContainer(str, str2);
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList.size() > 0) {
            Tabs tabs = new Tabs();
            int size = arrayList.size();
            int i3 = (size / i) + (size % i);
            for (int i4 = 0; i4 < i3; i4++) {
                Container container2 = new Container(new BoxLayout(2));
                int i5 = i4 * i;
                for (int i6 = i5; i6 < Math.min(size - i5, i) + i5; i6++) {
                    container2.setScrollableY(false);
                    container2.setTensileDragEnabled(false);
                    if (i6 != i5) {
                        container2.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                    }
                    container2.addComponent(((B3gService) arrayList.get(i6)).DrawItem(this.stateMachine, this.ressource, i2, (B3gService) obj2, null, null));
                    container2.revalidate();
                }
                tabs.addTab(str2, container2);
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
                this.stateMachine.findCntHeaderPagerV2(GetContainer).removeAll();
                for (int i7 = 0; i7 < i3; i7++) {
                    Button button = new Button();
                    button.setIcon(this.ressource.getImage("slider_arrow_w.png"));
                    button.setDisabledIcon(this.ressource.getImage("slider_arrow.png"));
                    button.setUIID("gb_btnDot");
                    if (i7 == 0) {
                        button.setEnabled(true);
                    } else {
                        button.setEnabled(false);
                    }
                    this.stateMachine.findCntHeaderPagerV2(GetContainer).addComponent(button);
                }
                tabs.addSelectionListener(new 22(i3, GetContainer, tabs, container, obj3));
            }
        } else {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlign(4);
            flowLayout.setValign(4);
            flowLayout.setFillRows(true);
            Container container3 = new Container();
            container3.setLayout(flowLayout);
            container3.setUIID("g_CntTranspMsg");
            SpanLabel spanLabel = new SpanLabel();
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setText(str3);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container3.addComponent(spanLabel);
            GetContainer.addComponent(container3);
        }
        return GetContainer;
    }

    class 22 implements SelectionListener {
        final /* synthetic */ Container val$cntGloableHeader;
        final /* synthetic */ Object val$pB3gBeneficiaryServiceList;
        final /* synthetic */ Container val$pBeneficiaryContainer;
        final /* synthetic */ Tabs val$tab;
        final /* synthetic */ int val$totalContainers;

        22(int i, Container container, Tabs tabs, Container container2, Object obj) {
            this.val$totalContainers = i;
            this.val$cntGloableHeader = container;
            this.val$tab = tabs;
            this.val$pBeneficiaryContainer = container2;
            this.val$pB3gBeneficiaryServiceList = obj;
        }

        public void selectionChanged(int i, int i2) {
            if (this.val$totalContainers == 1) {
                CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).removeAll();
            } else {
                for (int i3 = 0; i3 < CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentCount(); i3++) {
                    if (i == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    } else if (i2 == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(true);
                    } else {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    }
                }
            }
            this.val$tab.getParent().getParent().revalidate();
            if (this.val$pBeneficiaryContainer != null) {
                CihUiManager.this.DrawListContainerBenef("GloabalContainerV2", "Wallet à créditer", Boolean.TRUE, this.val$pB3gBeneficiaryServiceList, 1, 17, "Aucune wallet n'est disponible pour cette opération .", null, this.val$pBeneficiaryContainer);
                this.val$pBeneficiaryContainer.revalidate();
            }
        }
    }

    public Container DrawListContainerDebitCard(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Container container, Object obj3) {
        Container GetContainer = GetContainer(str, str2);
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList.size() > 0) {
            Tabs tabs = new Tabs();
            int size = arrayList.size();
            int i3 = (size / i) + (size % i);
            for (int i4 = 0; i4 < i3; i4++) {
                Container container2 = new Container(new BoxLayout(2));
                int i5 = i4 * i;
                for (int i6 = i5; i6 < Math.min(size - i5, i) + i5; i6++) {
                    container2.setScrollableY(false);
                    container2.setTensileDragEnabled(false);
                    if (i6 != i5) {
                        container2.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                    }
                    container2.addComponent(((B3gService) arrayList.get(i6)).DrawItem(this.stateMachine, this.ressource, i2, (B3gService) obj2, null, null));
                    container2.revalidate();
                }
                tabs.addTab(str2, container2);
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
                this.stateMachine.findCntHeaderPagerV2(GetContainer).removeAll();
                for (int i7 = 0; i7 < i3; i7++) {
                    Button button = new Button();
                    button.setIcon(this.ressource.getImage("slider_arrow_w.png"));
                    button.setDisabledIcon(this.ressource.getImage("slider_arrow.png"));
                    button.setUIID("gb_btnDot");
                    if (i7 == 0) {
                        button.setEnabled(true);
                    } else {
                        button.setEnabled(false);
                    }
                    this.stateMachine.findCntHeaderPagerV2(GetContainer).addComponent(button);
                }
                tabs.addSelectionListener(new 23(i3, GetContainer, tabs, obj3, container));
            }
        } else {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlign(4);
            flowLayout.setValign(4);
            flowLayout.setFillRows(true);
            Container container3 = new Container();
            container3.setLayout(flowLayout);
            container3.setUIID("g_CntTranspMsg");
            SpanLabel spanLabel = new SpanLabel();
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setText(str3);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container3.addComponent(spanLabel);
            GetContainer.addComponent(container3);
        }
        return GetContainer;
    }

    class 23 implements SelectionListener {
        final /* synthetic */ Container val$cntGloableHeader;
        final /* synthetic */ Object val$pB3gBeneficiaryServiceList;
        final /* synthetic */ Container val$pBeneficiaryContainer;
        final /* synthetic */ Tabs val$tab;
        final /* synthetic */ int val$totalContainers;

        23(int i, Container container, Tabs tabs, Object obj, Container container2) {
            this.val$totalContainers = i;
            this.val$cntGloableHeader = container;
            this.val$tab = tabs;
            this.val$pB3gBeneficiaryServiceList = obj;
            this.val$pBeneficiaryContainer = container2;
        }

        public void selectionChanged(int i, int i2) {
            if (this.val$totalContainers == 1) {
                CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).removeAll();
            } else {
                for (int i3 = 0; i3 < CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentCount(); i3++) {
                    if (i == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    } else if (i2 == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(true);
                    } else {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    }
                }
            }
            this.val$tab.getParent().getParent().revalidate();
            CihUiManager.this.DrawListContainerBenef("GloabalContainerV2", "Carte à créditer", Boolean.TRUE, CihUiManager.this.removeSelectedCardFromList(CihUiManager.this.GetCurrentCardNumber((Container) this.val$tab.getTabComponentAt(i2)), this.val$pB3gBeneficiaryServiceList), 1, 18, "000", null, this.val$pBeneficiaryContainer);
            this.val$pBeneficiaryContainer.revalidate();
        }
    }

    public Container DrawListContainerCommands(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Container container, Object obj3) {
        Container GetContainer = GetContainer(str, str2);
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList.size() > 0) {
            Tabs tabs = new Tabs();
            int size = arrayList.size();
            int i3 = (size / i) + (size % i);
            for (int i4 = 0; i4 < i3; i4++) {
                Container container2 = new Container(new BoxLayout(2));
                int i5 = i4 * i;
                for (int i6 = i5; i6 < Math.min(size - i5, i) + i5; i6++) {
                    container2.setScrollableY(false);
                    container2.setTensileDragEnabled(false);
                    if (i6 != i5) {
                        container2.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                    }
                    container2.addComponent(((B3gService) arrayList.get(i6)).DrawItem(this.stateMachine, this.ressource, i2, (B3gService) obj2, null, null));
                    container2.revalidate();
                }
                tabs.addTab(str2, container2);
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
                this.stateMachine.findCntHeaderPagerV2(GetContainer).removeAll();
                for (int i7 = 0; i7 < i3; i7++) {
                    Button button = new Button();
                    button.setIcon(this.ressource.getImage("slider_arrow_w.png"));
                    button.setDisabledIcon(this.ressource.getImage("slider_arrow.png"));
                    button.setUIID("gb_btnDot");
                    if (i7 == 0) {
                        button.setEnabled(true);
                    } else {
                        button.setEnabled(false);
                    }
                    this.stateMachine.findCntHeaderPagerV2(GetContainer).addComponent(button);
                }
                tabs.addSelectionListener(new 24(i2, i3, GetContainer, tabs, obj3));
            }
        } else {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlign(4);
            flowLayout.setValign(4);
            flowLayout.setFillRows(true);
            Container container3 = new Container();
            container3.setLayout(flowLayout);
            container3.setUIID("g_CntTranspMsg");
            SpanLabel spanLabel = new SpanLabel();
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setText(str3);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container3.addComponent(spanLabel);
            GetContainer.addComponent(container3);
        }
        return GetContainer;
    }

    class 24 implements SelectionListener {
        final /* synthetic */ Container val$cntGloableHeader;
        final /* synthetic */ Object val$pB3gBeneficiaryServiceList;
        final /* synthetic */ int val$pPageId;
        final /* synthetic */ Tabs val$tab;
        final /* synthetic */ int val$totalContainers;

        24(int i, int i2, Container container, Tabs tabs, Object obj) {
            this.val$pPageId = i;
            this.val$totalContainers = i2;
            this.val$cntGloableHeader = container;
            this.val$tab = tabs;
            this.val$pB3gBeneficiaryServiceList = obj;
        }

        public void selectionChanged(int i, int i2) {
            if (this.val$pPageId != 74) {
                if (this.val$totalContainers == 1) {
                    CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).removeAll();
                } else {
                    for (int i3 = 0; i3 < CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentCount(); i3++) {
                        if (i == i3) {
                            CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                        } else if (i2 == i3) {
                            CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(true);
                        } else {
                            CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                        }
                    }
                }
            } else {
                if (this.val$totalContainers == 1) {
                    CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).removeAll();
                } else {
                    for (int i4 = 0; i4 < CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentCount(); i4++) {
                        if (i == i4) {
                            CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i4).setEnabled(false);
                        } else if (i2 == i4) {
                            CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i4).setEnabled(true);
                        } else {
                            CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i4).setEnabled(false);
                        }
                    }
                }
                ((Label) this.val$pB3gBeneficiaryServiceList).setText(CihUiManager.this.GetCurrentPinCardGSM((Container) this.val$tab.getTabComponentAt(i2)));
            }
            this.val$tab.getParent().getParent().revalidate();
        }
    }

    public Object GetCustomBeneficiaryList(String str, Object obj) {
        ArrayList arrayList = new ArrayList();
        if (!str.substring(13, 17).equals("2310")) {
            arrayList.addAll((ArrayList) obj);
            boolean z = true;
            for (int i = 0; i < arrayList.size() && z; i++) {
                if (((B3gService) arrayList.get(i)).SelectedId.equals(str)) {
                    arrayList.remove(i);
                    z = false;
                }
            }
        } else {
            arrayList.addAll((ArrayList) obj);
            loop1: while (true) {
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    if (!((B3gService) arrayList.get(i2)).SelectedId.substring(6, 13).equals(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical) || ((B3gService) arrayList.get(i2)).SelectedId.equals(str)) {
                        arrayList.remove(i2);
                    }
                }
                break loop1;
            }
        }
        return arrayList;
    }

    public String GetCurrentIssuarRibNumber(Container container) {
        return ((Account) ((B3gService) ((Container) container.getComponentAt(0)).getClientProperty("AccountClient"))).rib;
    }

    public String GetCurrentIssuarAccountNumber(Container container) {
        return ((Account) ((B3gService) ((Container) container.getComponentAt(0)).getClientProperty("AccountClient"))).accountNumber;
    }

    public Account GetCurrentIssuarAccount(Container container) {
        return (Account) ((B3gService) ((Container) container.getComponentAt(0)).getClientProperty("AccountClient"));
    }

    public String GetCurrentCardNumber(Container container) {
        return ((Beneficiary) ((B3gService) ((Container) container.getComponentAt(0)).getClientProperty("beneficiary"))).cardNumber;
    }

    public Card GetCurrentCardFromTabs(Container container, int i) {
        return (Card) ((B3gService) ((Container) container.getComponentAt(0)).getClientProperty("CardDetails" + i));
    }

    public String GetCurrentPinCardGSM(Container container) {
        return ((PinCard) ((B3gService) ((Container) container.getComponentAt(0)).getClientProperty("PinCard"))).GSM2;
    }

    public Card GetCurrentCard(Container container) {
        return (Card) ((B3gService) ((Container) container.getComponentAt(0)).getClientProperty("CardOpposition"));
    }

    public Beneficiary GetCurrentBeneficiary(Container container) {
        return (Beneficiary) ((B3gService) ((Container) container.getComponentAt(0)).getClientProperty("beneficiary"));
    }

    public Account GetCurrentRibAccount(Container container) {
        return (Account) ((B3gService) ((Container) container.getComponentAt(0)).getClientProperty("AccountRibClient"));
    }

    public Account GetCurrentAccount(Container container) {
        return (Account) ((B3gService) ((Container) container.getComponentAt(0)).getClientProperty("AccountDClient"));
    }

    public Container DrawListContainer(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2, Component component3) {
        int i3;
        Container GetContainer = GetContainer(str, str2);
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList.size() > 0) {
            Tabs tabs = new Tabs();
            int size = arrayList.size();
            int i4 = (size / i) + (size % i);
            for (int i5 = 0; i5 < i4; i5++) {
                Container container = new Container(new BoxLayout(2));
                int i6 = i5 * i;
                for (int i7 = i6; i7 < Math.min(size - i6, i) + i6; i7++) {
                    container.setScrollableY(false);
                    container.setTensileDragEnabled(false);
                    if (i7 != i6) {
                        container.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                    }
                    Container DrawItem = ((B3gService) arrayList.get(i7)).DrawItem(this.stateMachine, this.ressource, i2, null, null, null);
                    container.setUIID("padding_1_1_1_1");
                    container.addComponent(DrawItem);
                    container.setName(DrawItem.getName());
                    container.revalidate();
                    if (i2 == 5) {
                        container.getComponentAt(0).putClientProperty("CardDetails" + i5, arrayList.get(i7));
                    }
                }
                tabs.addTab(str2, container);
            }
            tabs.hideTabs();
            tabs.setUIID("g_TabsV11");
            if (arrayList.size() == i) {
                tabs.setSwipeActivated(false);
                i3 = 1;
            } else {
                i3 = 1;
                tabs.setSwipeActivated(true);
            }
            GetContainer.addComponent(tabs);
            if (i4 > i3) {
                this.stateMachine.findCntHeaderPagerV2(GetContainer).removeAll();
                for (int i8 = 0; i8 < i4; i8++) {
                    Button button = new Button();
                    button.setIcon(this.ressource.getImage("slider_arrow_w.png"));
                    button.setDisabledIcon(this.ressource.getImage("slider_arrow.png"));
                    button.setUIID("gb_btnDot");
                    if (i8 == 0) {
                        button.setEnabled(true);
                    } else {
                        button.setEnabled(false);
                    }
                    this.stateMachine.findCntHeaderPagerV2(GetContainer).addComponent(button);
                }
                tabs.addSelectionListener(new 25(i4, GetContainer, tabs, i2, component, component2, component3));
            }
        } else {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlign(4);
            flowLayout.setValign(4);
            flowLayout.setFillRows(true);
            Container container2 = new Container();
            container2.setLayout(flowLayout);
            container2.setUIID("g_CntTranspMsg");
            SpanLabel spanLabel = new SpanLabel();
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setText(str3);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container2.addComponent(spanLabel);
            GetContainer.addComponent(container2);
        }
        return GetContainer;
    }

    class 25 implements SelectionListener {
        final /* synthetic */ Container val$cntGloableHeader;
        final /* synthetic */ Component val$pComponent;
        final /* synthetic */ Component val$pComponent2;
        final /* synthetic */ int val$pPageID;
        final /* synthetic */ Component val$pParentCnt;
        final /* synthetic */ Tabs val$tab;
        final /* synthetic */ int val$totalContainers;

        25(int i, Container container, Tabs tabs, int i2, Component component, Component component2, Component component3) {
            this.val$totalContainers = i;
            this.val$cntGloableHeader = container;
            this.val$tab = tabs;
            this.val$pPageID = i2;
            this.val$pComponent = component;
            this.val$pParentCnt = component2;
            this.val$pComponent2 = component3;
        }

        public void selectionChanged(int i, int i2) {
            if (this.val$totalContainers == 1) {
                CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).removeAll();
            } else {
                for (int i3 = 0; i3 < CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentCount(); i3++) {
                    if (i == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    } else if (i2 == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(true);
                    } else {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    }
                }
            }
            this.val$tab.getParent().getParent().revalidate();
            if (this.val$pPageID == 66) {
                Beneficiary GetCurrentBeneficiary = CihUiManager.this.GetCurrentBeneficiary((Container) this.val$tab.getTabComponentAt(i2));
                if (GetCurrentBeneficiary.statusActivation.equals("0")) {
                    ((Label) this.val$pComponent).setText("Désactiver");
                    ((Button) this.val$pParentCnt).setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "Desactivate_W.png"));
                } else {
                    ((Label) this.val$pComponent).setText("Activer");
                    ((Button) this.val$pParentCnt).setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "Activate_W.png"));
                }
                if (!GetCurrentBeneficiary.isSystem.equals("0")) {
                    ((Button) this.val$pComponent2).setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "Delete.png"));
                } else {
                    ((Button) this.val$pComponent2).setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "Delete_W.png"));
                }
            }
            if (this.val$pPageID == 23) {
                if (CihUiManager.this.GetCurrentAccount((Container) this.val$tab.getTabComponentAt(i2)).codeOffre.equals("CPTCQJ11")) {
                    ((Container) this.val$pComponent).setVisible(false);
                    ((Container) this.val$pComponent).revalidate();
                } else {
                    ((Container) this.val$pComponent).setVisible(true);
                    ((Container) this.val$pComponent).revalidate();
                }
            }
            if (this.val$pPageID == 5) {
                Card GetCurrentCardFromTabs = CihUiManager.this.GetCurrentCardFromTabs((Container) this.val$tab.getTabComponentAt(i2), i2);
                if (!GetCurrentCardFromTabs.statusCard.equals("Y") && !GetCurrentCardFromTabs.virtualStatus.equals("0")) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationService2(112, GetCurrentCardFromTabs, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, "Veuillez confirmer l’activation de votre carte virtuelle");
                }
            }
            if (this.val$pPageID == 17) {
                Container container = new Container(new BoxLayout(2));
                container.setUIID("g_cntBorderV2");
                Container container2 = new Container();
                Beneficiary GetCurrentBeneficiary2 = CihUiManager.this.GetCurrentBeneficiary((Container) this.val$tab.getTabComponentAt(i2));
                B3GCheckBox b3GCheckBox = new B3GCheckBox("Notification");
                b3GCheckBox.setUIID("g_lblNotif");
                b3GCheckBox.addItem("Notifier le bénéficiaire");
                b3GCheckBox.getItem("Notifier le bénéficiaire").addActionListener(new 1(container2, container));
                String str = GetCurrentBeneficiary2.isNotif;
                String str2 = GetCurrentBeneficiary2.typeBenef;
                if (str.equals("0") && str2.equals("300014")) {
                    ((Container) this.val$pComponent).removeAll();
                    ((Container) this.val$pComponent).addComponent(b3GCheckBox.GetContainer());
                    b3GCheckBox.getItem("Notifier le bénéficiaire").setSelected(false);
                    ((Container) this.val$pComponent).addComponent(container2);
                    ((TextField) ((Container) ((Container) this.val$pComponent2).getComponentAt(1)).getComponentAt(0)).setText(GetCurrentBeneficiary2.phoneNumber);
                    CihUiManager.this.isNotif = false;
                    CihMBanking.sesPMTR.setIsNotif(CihUiManager.this.isNotif);
                    ((Container) this.val$pComponent).revalidate();
                    return;
                }
                if (GetCurrentBeneficiary2.typeBenef.equals("300014")) {
                    ((Container) this.val$pComponent).removeAll();
                }
            }
        }

        class 1 implements ActionListener {
            final /* synthetic */ Container val$borderCnt;
            final /* synthetic */ Container val$emptyNotifCnt;

            1(Container container, Container container2) {
                this.val$emptyNotifCnt = container;
                this.val$borderCnt = container2;
            }

            public void actionPerformed(ActionEvent actionEvent) {
                if (!CihUiManager.this.isNotif) {
                    ((Container) 25.this.val$pComponent).replace(this.val$emptyNotifCnt, (Container) 25.this.val$pComponent2, (Transition) null);
                    ((Container) 25.this.val$pComponent).addComponent(this.val$borderCnt);
                    ((Container) 25.this.val$pComponent).revalidate();
                    CihUiManager.this.isNotif = true;
                    CihMBanking.sesPMTR.setIsNotif(CihUiManager.this.isNotif);
                    return;
                }
                ((Container) 25.this.val$pComponent).replace((Container) 25.this.val$pComponent2, this.val$emptyNotifCnt, (Transition) null);
                ((Container) 25.this.val$pComponent).removeComponent(this.val$borderCnt);
                ((Container) 25.this.val$pComponent).revalidate();
                CihUiManager.this.isNotif = false;
                CihMBanking.sesPMTR.setIsNotif(CihUiManager.this.isNotif);
            }
        }
    }

    public Container DrawErreurContainerDshb(String str, String str2, String str3, Image image) {
        Container GetContainer = GetContainer(str, str2);
        Container container = new Container(new BorderLayout());
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(1);
        flowLayout.setValign(4);
        flowLayout.setFillRows(true);
        Container container2 = new Container();
        container2.setLayout(flowLayout);
        container2.setUIID("g_CntTranspMsg");
        FlowLayout flowLayout2 = new FlowLayout();
        flowLayout2.setAlign(3);
        flowLayout2.setValign(4);
        flowLayout2.setFillRows(true);
        Container container3 = new Container();
        container3.setLayout(flowLayout2);
        container3.setUIID("g_CntTranspMsg");
        container3.getStyle().setMargin(0, 2, 2, 3);
        SpanLabel spanLabel = new SpanLabel();
        spanLabel.setUIID("dg_splblMsgCenter");
        spanLabel.setScrollVisible(false);
        spanLabel.setText(str3);
        spanLabel.setIconPosition("West");
        spanLabel.setTextUIID("dg_lblSPError");
        spanLabel.setIconUIID("g_cntEmpty");
        container2.addComponent(spanLabel);
        container3.addComponent(new Label(image));
        container.addComponent("West", container2);
        container.addComponent("East", container3);
        container.getStyle().setPadding(0, 2, 2, 10);
        GetContainer.addComponent(container);
        return GetContainer;
    }

    public Container DrawListVerticalContainer(String str, String str2, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2) {
        Container GetContainer = GetContainer(str, str2);
        Container container = (Container) this.uib.findByName("cntHeaderPagerV3", GetContainer);
        GetContainer.setScrollableY(false);
        container.setScrollableY(false);
        container.setScrollVisible(false);
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList.size() > 0) {
            int size = arrayList.size();
            int i3 = (size / i) + (size % i);
            for (int i4 = 0; i4 < i3; i4++) {
                Container container2 = new Container(new BoxLayout(2));
                int i5 = i4 * i;
                for (int i6 = i5; i6 < Math.min(size - i5, i) + i5; i6++) {
                    container2.setScrollableY(false);
                    container2.setTensileDragEnabled(false);
                    if (i6 != i5) {
                        container2.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                    }
                    Container DrawItem = ((Beneficiary) arrayList.get(i6)).DrawItem(this.stateMachine, this.ressource, i2, null, component, component2);
                    DrawItem.setScrollableY(false);
                    container2.addComponent(DrawItem);
                    container2.setName(DrawItem.getName());
                    container2.revalidate();
                }
                Display.getInstance().scheduleBackgroundTask(new CihUiManager$$ExternalSyntheticLambda0(container, container2));
            }
            Container container3 = new Container();
            container3.setUIID("greyCnt");
            GetContainer.addComponent(container3);
        } else {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlign(4);
            flowLayout.setValign(4);
            flowLayout.setFillRows(true);
            Container container4 = new Container();
            container4.setLayout(flowLayout);
            container4.setUIID("g_CntTranspMsg");
            SpanLabel spanLabel = new SpanLabel();
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setText(str3);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container4.addComponent(spanLabel);
            container.addComponent(container4);
        }
        return GetContainer;
    }

    static /* synthetic */ void lambda$DrawListVerticalContainer$0(Container container, Container container2) {
        container.addComponent(container2);
    }

    public Container DrawListContainerCardOpperation(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2, Component component3) {
        Container GetContainer = GetContainer(str, str2);
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
                    container.addComponent(((B3gService) arrayList.get(i6)).DrawItem(this.stateMachine, this.ressource, i2, (B3gService) obj2, null, null));
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
            GetContainer.addComponent(tabs);
            if (i3 > 1) {
                this.stateMachine.findCntHeaderPagerV2(GetContainer).removeAll();
                for (int i7 = 0; i7 < i3; i7++) {
                    Button button = new Button();
                    button.setIcon(this.ressource.getImage("slider_arrow_w.png"));
                    button.setDisabledIcon(this.ressource.getImage("slider_arrow.png"));
                    button.setUIID("gb_btnDot");
                    if (i7 == 0) {
                        button.setEnabled(true);
                    } else {
                        button.setEnabled(false);
                    }
                    this.stateMachine.findCntHeaderPagerV2(GetContainer).addComponent(button);
                }
                tabs.addSelectionListener(new 26(i3, GetContainer, tabs, component3));
            }
        } else {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlign(4);
            flowLayout.setValign(4);
            flowLayout.setFillRows(true);
            Container container2 = new Container();
            container2.setLayout(flowLayout);
            container2.setUIID("g_CntTranspMsg");
            SpanLabel spanLabel = new SpanLabel();
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setText(str3);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container2.addComponent(spanLabel);
            GetContainer.addComponent(container2);
        }
        return GetContainer;
    }

    class 26 implements SelectionListener {
        final /* synthetic */ Container val$cntGloableHeader;
        final /* synthetic */ Component val$lblActivate;
        final /* synthetic */ Tabs val$tab;
        final /* synthetic */ int val$totalContainers;

        26(int i, Container container, Tabs tabs, Component component) {
            this.val$totalContainers = i;
            this.val$cntGloableHeader = container;
            this.val$tab = tabs;
            this.val$lblActivate = component;
        }

        public void selectionChanged(int i, int i2) {
            if (this.val$totalContainers == 1) {
                CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).removeAll();
            } else {
                for (int i3 = 0; i3 < CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentCount(); i3++) {
                    if (i == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    } else if (i2 == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(true);
                    } else {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    }
                }
            }
            this.val$tab.getParent().getParent().revalidate();
            Card GetCurrentCard = CihUiManager.this.GetCurrentCard((Container) this.val$tab.getTabComponentAt(i2));
            if (GetCurrentCard.statusCard.equals("Y")) {
                ((Label) this.val$lblActivate).setText("Blocage");
            } else {
                if (GetCurrentCard.statusCard.equals("O")) {
                    return;
                }
                ((Label) this.val$lblActivate).setText("Déblocage");
            }
        }
    }

    public Container DrawListContainerCardMultiSrvOpperation(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2) {
        Container GetContainer = GetContainer(str, str2);
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
                    container.addComponent(((B3gService) arrayList.get(i6)).DrawItem(this.stateMachine, this.ressource, i2, (B3gService) obj2, null, null));
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
            GetContainer.addComponent(tabs);
            if (i3 > 1) {
                this.stateMachine.findCntHeaderPagerV2(GetContainer).removeAll();
                for (int i7 = 0; i7 < i3; i7++) {
                    Button button = new Button();
                    button.setIcon(this.ressource.getImage("slider_arrow_w.png"));
                    button.setDisabledIcon(this.ressource.getImage("slider_arrow.png"));
                    button.setUIID("gb_btnDot");
                    if (i7 == 0) {
                        button.setEnabled(true);
                    } else {
                        button.setEnabled(false);
                    }
                    this.stateMachine.findCntHeaderPagerV2(GetContainer).addComponent(button);
                }
                tabs.addSelectionListener(new 27(i3, GetContainer, tabs, component, component2));
            }
        } else {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlign(4);
            flowLayout.setValign(4);
            flowLayout.setFillRows(true);
            Container container2 = new Container();
            container2.setLayout(flowLayout);
            container2.setUIID("g_CntTranspMsg");
            SpanLabel spanLabel = new SpanLabel();
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setText(str3);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container2.addComponent(spanLabel);
            GetContainer.addComponent(container2);
        }
        return GetContainer;
    }

    class 27 implements SelectionListener {
        final /* synthetic */ Container val$cntGloableHeader;
        final /* synthetic */ Component val$lblActivateTou;
        final /* synthetic */ Component val$rdActivateEcom;
        final /* synthetic */ Tabs val$tab;
        final /* synthetic */ int val$totalContainers;

        27(int i, Container container, Tabs tabs, Component component, Component component2) {
            this.val$totalContainers = i;
            this.val$cntGloableHeader = container;
            this.val$tab = tabs;
            this.val$rdActivateEcom = component;
            this.val$lblActivateTou = component2;
        }

        public void selectionChanged(int i, int i2) {
            if (this.val$totalContainers == 1) {
                CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).removeAll();
            } else {
                for (int i3 = 0; i3 < CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentCount(); i3++) {
                    if (i == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    } else if (i2 == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(true);
                    } else {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    }
                }
            }
            this.val$tab.getParent().getParent().revalidate();
            Card GetCurrentCard = CihUiManager.this.GetCurrentCard((Container) this.val$tab.getTabComponentAt(i2));
            if (GetCurrentCard.dotationEcomStatus.equals("A")) {
                ((Button) this.val$rdActivateEcom).setText("Bloquer");
            } else {
                ((Button) this.val$rdActivateEcom).setText("Débloquer");
            }
            if (GetCurrentCard.dotationTouristSattus.equals("A")) {
                ((Button) this.val$lblActivateTou).setText("Bloquer");
            } else {
                ((Button) this.val$lblActivateTou).setText("Débloquer");
            }
        }
    }

    public Container DrawListContainerRibOpperation(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2) {
        Container GetContainer = GetContainer(str, str2);
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
                    container.addComponent(((B3gService) arrayList.get(i6)).DrawItem(this.stateMachine, this.ressource, i2, (B3gService) obj2, null, null));
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
            GetContainer.addComponent(tabs);
            if (i3 > 1) {
                this.stateMachine.findCntHeaderPagerV2(GetContainer).removeAll();
                for (int i7 = 0; i7 < i3; i7++) {
                    Button button = new Button();
                    button.setIcon(this.ressource.getImage("slider_arrow_w.png"));
                    button.setDisabledIcon(this.ressource.getImage("slider_arrow.png"));
                    button.setUIID("gb_btnDot");
                    if (i7 == 0) {
                        button.setEnabled(true);
                    } else {
                        button.setEnabled(false);
                    }
                    this.stateMachine.findCntHeaderPagerV2(GetContainer).addComponent(button);
                }
                tabs.addSelectionListener(new 28(i3, GetContainer, tabs, component));
            }
        } else {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlign(4);
            flowLayout.setValign(4);
            flowLayout.setFillRows(true);
            Container container2 = new Container();
            container2.setLayout(flowLayout);
            container2.setUIID("g_CntTranspMsg");
            SpanLabel spanLabel = new SpanLabel();
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setText(str3);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container2.addComponent(spanLabel);
            GetContainer.addComponent(container2);
        }
        return GetContainer;
    }

    class 28 implements SelectionListener {
        final /* synthetic */ Container val$cntGloableHeader;
        final /* synthetic */ Component val$lblRibNumber;
        final /* synthetic */ Tabs val$tab;
        final /* synthetic */ int val$totalContainers;

        28(int i, Container container, Tabs tabs, Component component) {
            this.val$totalContainers = i;
            this.val$cntGloableHeader = container;
            this.val$tab = tabs;
            this.val$lblRibNumber = component;
        }

        public void selectionChanged(int i, int i2) {
            if (this.val$totalContainers == 1) {
                CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).removeAll();
            } else {
                for (int i3 = 0; i3 < CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentCount(); i3++) {
                    if (i == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    } else if (i2 == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(true);
                    } else {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    }
                }
            }
            this.val$tab.getParent().getParent().revalidate();
            ((Label) this.val$lblRibNumber).setText(StringTools.RibFormatWithSP(CihUiManager.this.GetCurrentRibAccount((Container) this.val$tab.getTabComponentAt(i2)).rib));
        }
    }

    public Container DrawListContainerRibOpperationNew(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2) {
        int i3;
        int i4;
        int i5 = i;
        TableLayout tableLayout = new TableLayout(2, 1);
        Container container = new Container(tableLayout);
        container.setName("GlobalWrapper");
        Container container2 = new Container(new BorderLayout());
        Container container3 = new Container(BoxLayout.xRight());
        Label label = new Label(str2);
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        TableLayout tableLayout2 = new TableLayout(1, 2);
        Container container4 = new Container(tableLayout2);
        container4.add(tableLayout2.createConstraint().widthPercentage(75).heightPercentage(100), label);
        container3.setName("cntHeaderPagerV2");
        container3.setUIID("gb_cntHeaderPagerV2");
        container4.add(tableLayout2.createConstraint().widthPercentage(25).heightPercentage(100), container3);
        container2.add("North", container4);
        ArrayList arrayList = (ArrayList) obj;
        ArrayList arrayList2 = new ArrayList();
        if (arrayList.size() > 0) {
            Tabs tabs = new Tabs();
            int size = arrayList.size();
            int i6 = (size / i5) + (size % i5);
            int i7 = 0;
            while (i7 < i6) {
                Container container5 = container3;
                Container container6 = new Container(new BorderLayout());
                container2.setScrollableY(false);
                int i8 = i7 * i5;
                int i9 = i6;
                Container container7 = container;
                int i10 = i8;
                while (i10 < Math.min(size - i8, i5) + i8) {
                    int i11 = i8;
                    Container DrawItem = ((B3gService) arrayList.get(i10)).DrawItem(this.stateMachine, this.ressource, i2, (B3gService) obj2, null, null);
                    Container container8 = new Container(new BoxLayout(2));
                    container8.setScrollableY(false);
                    container8.add(DrawItem);
                    Container DrawRibItem = DrawRibItem((Account) arrayList.get(i10));
                    container6.addComponent("North", container8);
                    container6.addComponent("Center", DrawRibItem);
                    arrayList2.add(container6);
                    container6.revalidate();
                    i10++;
                    i5 = i;
                    size = size;
                    i8 = i11;
                }
                tabs.addTab(str2, container6);
                i7++;
                i5 = i;
                i6 = i9;
                container3 = container5;
                container = container7;
            }
            int i12 = i6;
            Container container9 = container;
            Container container10 = container3;
            tabs.hideTabs();
            tabs.setUIID("g_TabsV11");
            container2.add("Center", tabs);
            container9.addComponent(tableLayout.createConstraint().heightPercentage(80).widthPercentage(100), container2);
            if (arrayList.size() == i) {
                i3 = 0;
                tabs.setSwipeActivated(false);
                i4 = 1;
            } else {
                i3 = 0;
                i4 = 1;
                tabs.setSwipeActivated(true);
            }
            CihMBanking.sesPMTR.setActiveContainer((Container) arrayList2.get(i3));
            if (i12 <= i4) {
                return container9;
            }
            container10.removeAll();
            int i13 = 0;
            while (i13 < i12) {
                Button button = new Button();
                button.setIcon(this.ressource.getImage("slider_arrow_w.png"));
                button.setDisabledIcon(this.ressource.getImage("slider_arrow.png"));
                button.setUIID("gb_btnDot");
                if (i13 == 0) {
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                }
                Container container11 = container10;
                container11.addComponent(button);
                i13++;
                container10 = container11;
            }
            tabs.addSelectionListener(new 29(i12, container10, arrayList, arrayList2, tabs));
            return container9;
        }
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setFillRows(true);
        Container container12 = new Container();
        container12.setLayout(flowLayout);
        container12.setUIID("g_CntTranspMsg");
        SpanLabel spanLabel = new SpanLabel();
        spanLabel.setUIID("dg_splblMsgCenter");
        spanLabel.setScrollVisible(false);
        spanLabel.setText(str3);
        spanLabel.setIconPosition("West");
        spanLabel.setTextUIID("dg_lblSPError");
        spanLabel.setIconUIID("g_cntEmpty");
        container12.addComponent(spanLabel);
        container.addComponent(tableLayout.createConstraint().widthPercentage(100).heightPercentage(100), container12);
        return container;
    }

    class 29 implements SelectionListener {
        final /* synthetic */ ArrayList val$dataList;
        final /* synthetic */ Container val$pager;
        final /* synthetic */ ArrayList val$screens;
        final /* synthetic */ Tabs val$tab;
        final /* synthetic */ int val$totalContainers;

        29(int i, Container container, ArrayList arrayList, ArrayList arrayList2, Tabs tabs) {
            this.val$totalContainers = i;
            this.val$pager = container;
            this.val$dataList = arrayList;
            this.val$screens = arrayList2;
            this.val$tab = tabs;
        }

        public void selectionChanged(int i, int i2) {
            if (this.val$totalContainers == 1) {
                this.val$pager.removeAll();
            } else {
                System.out.println("Old Selected = " + i);
                System.out.println("New Selected = " + i2);
                CihMBanking.sesPMTR.setActiveAccount((Account) this.val$dataList.get(i2));
                CihMBanking.sesPMTR.setActiveContainer((Container) this.val$screens.get(i2));
                for (int i3 = 0; i3 < this.val$pager.getComponentCount(); i3++) {
                    if (i == i3) {
                        this.val$pager.getComponentAt(i3).setEnabled(false);
                    } else if (i2 == i3) {
                        this.val$pager.getComponentAt(i3).setEnabled(true);
                    } else {
                        this.val$pager.getComponentAt(i3).setEnabled(false);
                    }
                }
            }
            this.val$tab.getParent().getParent().revalidate();
        }
    }

    private Container DrawRibItem(Account account) {
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(new BoxLayout(2));
        container2.setScrollableY(true);
        container2.setScrollVisible(false);
        Component container3 = new Container(new FlowLayout());
        container3.setUIID("g_cntBorderV2");
        Container container4 = new Container(new BoxLayout(2));
        container4.setUIID("g_cntV2InWithMargin2");
        container4.setScrollableY(false);
        Label label = new Label("RIB :");
        label.setUIID("ac_lblitemDetailValueBold");
        Label label2 = new Label("-");
        label2.setUIID("ac_lblitemDetailValueSmalBlue");
        label2.setText(StringTools.RibFormatWithSP(account.rib));
        container4.add(label).add(label2);
        Container container5 = new Container(new BoxLayout(2));
        container5.setUIID("g_cntV2InWithMargin2");
        container5.setScrollableY(false);
        Label label3 = new Label("IBAN :");
        label3.setUIID("ac_lblitemDetailValueBold");
        Label label4 = new Label("-");
        label4.setUIID("ac_lblitemDetailValueSmalBlue");
        label4.setText("MA64 " + DataTools.FormatIban(account.rib));
        container5.add(label3).add(label4);
        Container container6 = new Container(new BoxLayout(2));
        container6.setUIID("g_cntV2InWithMargin2");
        container6.setScrollableY(false);
        Label label5 = new Label("Code SWIFT :");
        label5.setUIID("ac_lblitemDetailValueBold");
        Label label6 = new Label("CIHMMAMC");
        label6.setUIID("ac_lblitemDetailValueSmalBlue");
        container6.add(label5).add(label6);
        ImageViewer imageViewer = new ImageViewer();
        imageViewer.getAllStyles().setMarginUnit(1, 1, 1, 1);
        imageViewer.getAllStyles().setMargin(2.0f, 0.0f, 0.0f, 0.0f);
        QRModel qRModel = new QRModel();
        try {
            int displayWidth = (Display.getInstance().getDisplayWidth() * 2) / 3;
            imageViewer.setImage(QRMaker.QRCode(qRModel.fillModel(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, account.accountNumber, account.type, true).toString()).scaled(displayWidth, displayWidth));
        } catch (Exception unused) {
        }
        container2.add(container3);
        container2.add(container4);
        container2.add(container5);
        container2.add(container6);
        container2.add(imageViewer);
        container.add((Object) 4, (Component) container2);
        return container;
    }

    public void DrawListContainerBenef(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Container container) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll((ArrayList) obj);
        ((Container) ((Container) container.getComponentAt(1)).getComponentAt(1)).removeAll();
        int i3 = 2;
        container.removeComponent(container.getComponentAt(2));
        container.revalidate();
        if (this.CurrentPageId == 8) {
            TransferNewPage.Glob.removeAll();
            TransferNewPage.BENEFCNT.removeAll();
        }
        if (arrayList.size() > 0) {
            Tabs tabs = new Tabs();
            int size = arrayList.size();
            int i4 = (size / i) + (size % i);
            int i5 = 0;
            while (i5 < i4) {
                Container container2 = new Container(new BoxLayout(i3));
                if (this.CurrentPageId == 8) {
                    Button button = new Button();
                    button.setUIID("Container");
                    container2.addComponent(button);
                    container2.setLeadComponent(button);
                    button.addActionListener(new 30(container2));
                }
                int i6 = i5 * i;
                for (int i7 = i6; i7 < Math.min(size - i6, i) + i6; i7++) {
                    container2.setScrollableY(false);
                    container2.setTensileDragEnabled(false);
                    container2.addComponent(((B3gService) arrayList.get(i7)).DrawItem(this.stateMachine, this.ressource, i2, (B3gService) obj2, null, null));
                    if (i7 != i6) {
                        container2.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                    }
                    container2.revalidate();
                }
                if (this.CurrentPageId == 8) {
                    TransferNewPage.Glob.add(container2);
                } else {
                    tabs.addTab(str2, container2);
                }
                i5++;
                i3 = 2;
            }
            tabs.hideTabs();
            tabs.setUIID("g_TabsV11");
            if (arrayList.size() == i) {
                tabs.setSwipeActivated(false);
            } else {
                tabs.setSwipeActivated(true);
            }
            container.addComponent(tabs);
            if (i4 > 1) {
                this.stateMachine.findCntHeaderPagerV2(container).removeAll();
                for (int i8 = 0; i8 < i4; i8++) {
                    Button button2 = new Button();
                    button2.setIcon(this.ressource.getImage("slider_arrow_w.png"));
                    button2.setDisabledIcon(this.ressource.getImage("slider_arrow.png"));
                    button2.setUIID("gb_btnDot");
                    if (i8 == 0) {
                        button2.setEnabled(true);
                    } else {
                        button2.setEnabled(false);
                    }
                    this.stateMachine.findCntHeaderPagerV2(container).addComponent(button2);
                }
                tabs.addSelectionListener(new 31(i4, container, tabs));
                return;
            }
            return;
        }
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setFillRows(true);
        Container container3 = new Container();
        container3.setLayout(flowLayout);
        container3.setUIID("g_CntTranspMsg");
        SpanLabel spanLabel = new SpanLabel();
        spanLabel.setUIID("dg_splblMsgCenter");
        spanLabel.setScrollVisible(false);
        spanLabel.setText(str3);
        spanLabel.setIconPosition("West");
        spanLabel.setTextUIID("dg_lblSPError");
        spanLabel.setIconUIID("g_cntEmpty");
        container3.addComponent(spanLabel);
        container.addComponent(container3);
        container.revalidate();
    }

    class 30 implements ActionListener {
        final /* synthetic */ Container val$cntTableHeaderY;

        30(Container container) {
            this.val$cntTableHeaderY = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransferNewPage.BENEFCNT.removeAll();
            for (int i = 0; i < TransferNewPage.Glob.getComponentCount(); i++) {
                TransferNewPage.Glob.getComponentAt(i).setUIID("Container");
                ((Container) TransferNewPage.Glob.getComponentAt(i)).revalidate();
            }
            this.val$cntTableHeaderY.setUIID("greyCnt");
            Container container = (Container) this.val$cntTableHeaderY.getComponentAt(1);
            if (container.getComponentCount() > 0) {
                Container container2 = (Container) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
                TransferNewPage.BENEFCNT.addComponent(CihUiManager.this.DrawAcquiredAcc(((Label) container2.getComponentAt(0)).getText(), StringTools.RibFormatWithoutSP(((Label) container2.getComponentAt(1)).getText())));
                TransferNewPage.BENEFCNT.revalidate();
            }
            TransferNewPage.A.openclose(true, TransferNewPage.Glob);
            TransferNewPage.cntBtns.setHidden(false);
            TransferNewPage.cntTransfertForm.setScrollableY(true);
            for (int i2 = 0; i2 < CihUiManager.this.stateMachine.findCntGlobalTransfer(TransferNewPage.cntTransfertForm).getComponentCount(); i2++) {
                if (!CihUiManager.this.stateMachine.findCntGlobalTransfer(TransferNewPage.cntTransfertForm).getComponentAt(i2).getName().equals("CntAccountChosen")) {
                    CihUiManager.this.stateMachine.findCntGlobalTransfer(TransferNewPage.cntTransfertForm).getComponentAt(i2).setHidden(false);
                }
            }
            TransferNewPage.cntTransfertForm.revalidate();
        }
    }

    class 31 implements SelectionListener {
        final /* synthetic */ Container val$cntGloableHeader;
        final /* synthetic */ Tabs val$tab;
        final /* synthetic */ int val$totalContainers;

        31(int i, Container container, Tabs tabs) {
            this.val$totalContainers = i;
            this.val$cntGloableHeader = container;
            this.val$tab = tabs;
        }

        public void selectionChanged(int i, int i2) {
            if (this.val$totalContainers == 1) {
                CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).removeAll();
            } else {
                for (int i3 = 0; i3 < CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentCount(); i3++) {
                    if (i == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    } else if (i2 == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(true);
                    } else {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    }
                }
            }
            this.val$tab.getParent().getParent().revalidate();
        }
    }

    Container GetContainer(String str, String str2) {
        Container createContainer = this.stateMachine.createContainer(this.ressource, str);
        if (str.equals("GloabalContainerV2")) {
            this.stateMachine.findLblGlobalHeaderTitle(createContainer).setText(str2);
        } else if (str.equals("GloabalContainerV3")) {
            this.stateMachine.findLblGlobalHeaderTitle(createContainer).setText(str2);
        } else if (str.equals("GlobalContainerDhbErr")) {
            this.stateMachine.findLblGlobalHeaderTitle(createContainer).setText(str2);
        }
        return createContainer;
    }

    public Container GetContainerNw(String str, String str2) {
        Container createContainer = this.stateMachine.createContainer(this.ressource, str);
        if (str.equals("GloabalContainerV2")) {
            this.stateMachine.findLblGlobalHeaderTitle(createContainer).setText(str2);
        }
        return createContainer;
    }

    void GetContainer(String str, String str2, Container container) {
        container.removeAll();
        container.revalidate();
        Container createContainer = this.stateMachine.createContainer(this.ressource, str);
        if (str.equals("GloabalContainerV2")) {
            this.stateMachine.findLblGlobalHeaderTitle(createContainer).setText(str2);
        }
        createContainer.revalidate();
    }

    public ArrayList GetDrawItemV2_WithPager(Object obj, int i, int i2, Object obj2) {
        ArrayList arrayList = (ArrayList) obj;
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 <= (size / i) + (size % i); i3++) {
            for (int i4 = i3; i4 < (i3 * i) + i; i4++) {
                Container container = new Container(new BoxLayout(2));
                container.setScrollableY(false);
                container.setTensileDragEnabled(false);
                if (i4 != 0) {
                    container.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                }
                container.addComponent(((B3gService) arrayList.get(i4)).DrawItem(this.stateMachine, this.ressource, i2, (B3gService) obj2, null, null));
                container.revalidate();
                arrayList2.add(container);
            }
        }
        return arrayList2;
    }

    public ArrayList GetListItemDrawedContainer(ArrayList arrayList, int i, Object obj) {
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            try {
                arrayList2.add(((B3gService) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, i, (B3gService) obj, null, null));
            } catch (Exception unused) {
            }
        }
        return arrayList2;
    }

    public ArrayList GetDrawItemV2_WithPager(ArrayList arrayList, int i) {
        ArrayList arrayList2 = new ArrayList();
        try {
            if (i == 1) {
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    Container container = new Container(new BoxLayout(2));
                    container.setScrollableY(false);
                    container.setTensileDragEnabled(false);
                    if (i2 != 0) {
                        container.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                    }
                    container.addComponent((Component) arrayList.get(i2));
                    container.revalidate();
                    arrayList2.add(container);
                }
            } else {
                int i3 = 0;
                while (true) {
                    int i4 = i3 + i;
                    if (i4 >= arrayList.size()) {
                        break;
                    }
                    Container container2 = new Container(new BoxLayout(2));
                    container2.setScrollableY(false);
                    container2.setTensileDragEnabled(false);
                    for (int i5 = i3; i5 < i4; i5++) {
                        if (i5 != i3) {
                            container2.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                        }
                        container2.addComponent((Component) arrayList.get(i5));
                    }
                    container2.revalidate();
                    arrayList2.add(container2);
                    i3 = i4;
                }
                if (i3 < arrayList.size()) {
                    Container container3 = new Container(new BoxLayout(2));
                    container3.setScrollableY(false);
                    container3.setTensileDragEnabled(false);
                    for (int i6 = i3; i6 < arrayList.size(); i6++) {
                        if (i6 != i3) {
                            container3.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                        }
                        container3.addComponent((Component) arrayList.get(i6));
                    }
                    container3.revalidate();
                    arrayList2.add(container3);
                }
            }
        } catch (Exception unused) {
        }
        return arrayList2;
    }

    public Container GetCntBorderSeparator(String str) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID(str);
        return container;
    }

    public void SetGlobalContainerTitle(Container container, String str) {
        ((Label) ((Container) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(1)).getComponentAt(0)).getComponentAt(0)).setText(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public Object GetMapB3gServiceList(ArrayList arrayList, int i) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String createDate;
        String str11;
        String str12;
        String startDate;
        String str13;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (i == 1) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                B3gService b3gService = (B3gService) it.next();
                String substring = ((AccountOperation) b3gService).accountedDate.substring(0, 10);
                if (linkedHashMap.get(substring) == null) {
                    linkedHashMap.put(substring, new ArrayList());
                }
                ((ArrayList) linkedHashMap.get(substring)).add(b3gService);
            }
        } else if (i == 2) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                B3gService b3gService2 = (B3gService) it2.next();
                String substring2 = ((AccountAutorisation) b3gService2).dateEff.substring(0, 10);
                if (linkedHashMap.get(substring2) == null) {
                    linkedHashMap.put(substring2, new ArrayList());
                }
                ((ArrayList) linkedHashMap.get(substring2)).add(b3gService2);
            }
        } else if (i == 29) {
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                B3gService b3gService3 = (B3gService) it3.next();
                MTCHistoric mTCHistoric = (MTCHistoric) b3gService3;
                DataTools.split(mTCHistoric.OptionnalData2, ";");
                String str14 = mTCHistoric.BillerId;
                if (linkedHashMap.get(str14) == null) {
                    linkedHashMap.put(str14, new ArrayList());
                }
                ((ArrayList) linkedHashMap.get(str14)).add(b3gService3);
            }
        } else if (i == 87) {
            Iterator it4 = arrayList.iterator();
            while (it4.hasNext()) {
                B3gService b3gService4 = (B3gService) it4.next();
                MTCImpaye mTCImpaye = (MTCImpaye) b3gService4;
                if (mTCImpaye.DateFacture.length() > 10) {
                    str = mTCImpaye.DateFacture.substring(0, 10);
                } else {
                    str = mTCImpaye.DateFacture;
                }
                if (linkedHashMap.get(str) == null) {
                    linkedHashMap.put(str, new ArrayList());
                }
                ((ArrayList) linkedHashMap.get(str)).add(b3gService4);
            }
        } else if (i != 281) {
            switch (i) {
                case 4:
                    Iterator it5 = arrayList.iterator();
                    while (it5.hasNext()) {
                        B3gService b3gService5 = (B3gService) it5.next();
                        String substring3 = ((CardOperation) b3gService5).transactionDate.substring(0, 10);
                        if (linkedHashMap.get(substring3) == null) {
                            linkedHashMap.put(substring3, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(substring3)).add(b3gService5);
                    }
                    break;
                case 5:
                    Iterator it6 = arrayList.iterator();
                    while (it6.hasNext()) {
                        B3gService b3gService6 = (B3gService) it6.next();
                        RecurringDetail recurringDetail = (RecurringDetail) b3gService6;
                        String str15 = "Payé";
                        if (!recurringDetail.Status.equals("P") && recurringDetail.Status.equals("I")) {
                            str15 = "Impayé";
                        }
                        if (linkedHashMap.get(str15) == null) {
                            linkedHashMap.put(str15, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(str15)).add(b3gService6);
                    }
                    break;
                case 6:
                    Iterator it7 = arrayList.iterator();
                    while (it7.hasNext()) {
                        B3gService b3gService7 = (B3gService) it7.next();
                        String str16 = ((Walet) b3gService7).Title;
                        if (linkedHashMap.get(str16) == null) {
                            linkedHashMap.put(str16, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(str16)).add(b3gService7);
                    }
                    break;
                case 7:
                    Iterator it8 = arrayList.iterator();
                    while (it8.hasNext()) {
                        B3gService b3gService8 = (B3gService) it8.next();
                        String str17 = ((BancBeneficary) b3gService8).Order;
                        if (linkedHashMap.get(str17) == null) {
                            linkedHashMap.put(str17, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(str17)).add(b3gService8);
                    }
                    break;
                case 8:
                    Iterator it9 = arrayList.iterator();
                    while (it9.hasNext()) {
                        B3gService b3gService9 = (B3gService) it9.next();
                        TransferHistoric transferHistoric = (TransferHistoric) b3gService9;
                        if (transferHistoric.OperationDate.length() > 10) {
                            str3 = transferHistoric.OperationDate.substring(0, 10);
                        } else {
                            str3 = transferHistoric.OperationDate;
                        }
                        if (linkedHashMap.get(str3) == null) {
                            linkedHashMap.put(str3, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(str3)).add(b3gService9);
                    }
                    break;
                case 9:
                    Iterator it10 = arrayList.iterator();
                    while (it10.hasNext()) {
                        B3gService b3gService10 = (B3gService) it10.next();
                        String substring4 = ((MyEDocumentsService) b3gService10).getCreationDate().substring(3, 8);
                        if (linkedHashMap.get(substring4) == null) {
                            linkedHashMap.put(substring4, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(substring4)).add(b3gService10);
                    }
                    break;
                case 10:
                    Iterator it11 = arrayList.iterator();
                    while (it11.hasNext()) {
                        B3gService b3gService11 = (B3gService) it11.next();
                        String operationDate = ((OrderMADHistoricDataService) b3gService11).getOperationDate();
                        if (linkedHashMap.get(operationDate) == null) {
                            linkedHashMap.put(operationDate, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(operationDate)).add(b3gService11);
                    }
                    break;
                case 11:
                    Iterator it12 = arrayList.iterator();
                    while (it12.hasNext()) {
                        B3gService b3gService12 = (B3gService) it12.next();
                        String operationDate2 = ((StudentTransferHisoricDataService) b3gService12).getOperationDate();
                        if (linkedHashMap.get(operationDate2) == null) {
                            linkedHashMap.put(operationDate2, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(operationDate2)).add(b3gService12);
                    }
                    break;
                case 12:
                    Iterator it13 = arrayList.iterator();
                    while (it13.hasNext()) {
                        B3gService b3gService13 = (B3gService) it13.next();
                        AccountOperation accountOperation = (AccountOperation) b3gService13;
                        if (accountOperation.date.length() > 10) {
                            str4 = accountOperation.date.substring(0, 10);
                        } else {
                            str4 = accountOperation.date;
                        }
                        if (linkedHashMap.get(str4) == null) {
                            linkedHashMap.put(str4, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(str4)).add(b3gService13);
                    }
                    break;
                case 13:
                    Iterator it14 = arrayList.iterator();
                    while (it14.hasNext()) {
                        B3gService b3gService14 = (B3gService) it14.next();
                        AccountOperation accountOperation2 = (AccountOperation) b3gService14;
                        if (accountOperation2.date.length() > 10) {
                            str5 = accountOperation2.date.substring(0, 10);
                        } else {
                            str5 = accountOperation2.date;
                        }
                        if (linkedHashMap.get(str5) == null) {
                            linkedHashMap.put(str5, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(str5)).add(b3gService14);
                    }
                    break;
                case 14:
                    Iterator it15 = arrayList.iterator();
                    while (it15.hasNext()) {
                        B3gService b3gService15 = (B3gService) it15.next();
                        CheckbookDemand checkbookDemand = (CheckbookDemand) b3gService15;
                        if (checkbookDemand.Date.length() > 10) {
                            str6 = checkbookDemand.Date.substring(0, 10);
                        } else {
                            str6 = checkbookDemand.Date;
                        }
                        if (linkedHashMap.get(str6) == null) {
                            linkedHashMap.put(str6, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(str6)).add(b3gService15);
                    }
                    break;
                case 15:
                    Iterator it16 = arrayList.iterator();
                    while (it16.hasNext()) {
                        B3gService b3gService16 = (B3gService) it16.next();
                        LCNBoockDemand lCNBoockDemand = (LCNBoockDemand) b3gService16;
                        if (lCNBoockDemand.Date.length() > 10) {
                            str7 = lCNBoockDemand.Date.substring(0, 10);
                        } else {
                            str7 = lCNBoockDemand.Date;
                        }
                        if (linkedHashMap.get(str7) == null) {
                            linkedHashMap.put(str7, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(str7)).add(b3gService16);
                    }
                    break;
                case 16:
                    Iterator it17 = arrayList.iterator();
                    while (it17.hasNext()) {
                        B3gService b3gService17 = (B3gService) it17.next();
                        MTCImpaye mTCImpaye2 = (MTCImpaye) b3gService17;
                        if (mTCImpaye2.DateFacture.length() > 10) {
                            str8 = mTCImpaye2.DateFacture.substring(0, 10);
                        } else {
                            str8 = mTCImpaye2.DateFacture;
                        }
                        if (linkedHashMap.get(str8) == null) {
                            linkedHashMap.put(str8, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(str8)).add(b3gService17);
                    }
                    break;
                case 17:
                    Iterator it18 = arrayList.iterator();
                    while (it18.hasNext()) {
                        B3gService b3gService18 = (B3gService) it18.next();
                        MTCImpaye mTCImpaye3 = (MTCImpaye) b3gService18;
                        if (mTCImpaye3.DateFacture.length() > 10) {
                            str9 = mTCImpaye3.DateFacture.substring(0, 10);
                        } else {
                            str9 = mTCImpaye3.DateFacture;
                        }
                        if (linkedHashMap.get(str9) == null) {
                            linkedHashMap.put(str9, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(str9)).add(b3gService18);
                    }
                    break;
                case 18:
                    Iterator it19 = arrayList.iterator();
                    while (it19.hasNext()) {
                        B3gService b3gService19 = (B3gService) it19.next();
                        String operationDate3 = ((OrderCurrencyHistoricDataService) b3gService19).getOperationDate();
                        if (linkedHashMap.get(operationDate3) == null) {
                            linkedHashMap.put(operationDate3, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(operationDate3)).add(b3gService19);
                    }
                    break;
                case 19:
                    Iterator it20 = arrayList.iterator();
                    while (it20.hasNext()) {
                        B3gService b3gService20 = (B3gService) it20.next();
                        MyCheckLcn myCheckLcn = (MyCheckLcn) b3gService20;
                        if (myCheckLcn.processingDate.length() > 10) {
                            str10 = myCheckLcn.processingDate.substring(0, 10);
                        } else {
                            str10 = myCheckLcn.processingDate;
                        }
                        if (linkedHashMap.get(str10) == null) {
                            linkedHashMap.put(str10, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(str10)).add(b3gService20);
                    }
                    break;
                case 20:
                    Iterator it21 = arrayList.iterator();
                    while (it21.hasNext()) {
                        B3gService b3gService21 = (B3gService) it21.next();
                        CashOutOperatoion cashOutOperatoion = (CashOutOperatoion) b3gService21;
                        if (cashOutOperatoion.getExpirationDate().length() > 10) {
                            createDate = cashOutOperatoion.getCreateDate().substring(0, 10);
                        } else {
                            createDate = cashOutOperatoion.getCreateDate();
                        }
                        if (linkedHashMap.get(createDate) == null) {
                            linkedHashMap.put(createDate, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(createDate)).add(b3gService21);
                    }
                    break;
                case 21:
                    Iterator it22 = arrayList.iterator();
                    while (it22.hasNext()) {
                        B3gService b3gService22 = (B3gService) it22.next();
                        ReleveDgi releveDgi = (ReleveDgi) b3gService22;
                        if (releveDgi.operationDate.length() > 10) {
                            str11 = releveDgi.operationDate.substring(0, 10);
                        } else {
                            str11 = releveDgi.operationDate;
                        }
                        if (linkedHashMap.get(str11) == null) {
                            linkedHashMap.put(str11, new ArrayList());
                        }
                        ((ArrayList) linkedHashMap.get(str11)).add(b3gService22);
                    }
                    break;
                default:
                    switch (i) {
                        case 33:
                            Iterator it23 = arrayList.iterator();
                            while (it23.hasNext()) {
                                B3gService b3gService23 = (B3gService) it23.next();
                                FatouratyService fatouratyService = (FatouratyService) b3gService23;
                                if (fatouratyService.DateFacture.length() > 10) {
                                    str12 = fatouratyService.DateFacture.substring(0, 10);
                                } else {
                                    str12 = fatouratyService.DateFacture;
                                }
                                if (linkedHashMap.get(str12) == null) {
                                    linkedHashMap.put(str12, new ArrayList());
                                }
                                ((ArrayList) linkedHashMap.get(str12)).add(b3gService23);
                            }
                            break;
                        case 34:
                            Iterator it24 = arrayList.iterator();
                            while (it24.hasNext()) {
                                B3gService b3gService24 = (B3gService) it24.next();
                                String statut = ((OvpExecutionService) b3gService24).getStatut();
                                if (linkedHashMap.get(statut) == null) {
                                    linkedHashMap.put(statut, new ArrayList());
                                }
                                ((ArrayList) linkedHashMap.get(statut)).add(b3gService24);
                            }
                            break;
                        case 35:
                            Iterator it25 = arrayList.iterator();
                            while (it25.hasNext()) {
                                B3gService b3gService25 = (B3gService) it25.next();
                                OvpService ovpService = (OvpService) b3gService25;
                                if (ovpService.getStartDate().length() > 10) {
                                    startDate = ovpService.getStartDate().substring(0, 10);
                                } else {
                                    startDate = ovpService.getStartDate();
                                }
                                if (linkedHashMap.get(startDate) == null) {
                                    linkedHashMap.put(startDate, new ArrayList());
                                }
                                ((ArrayList) linkedHashMap.get(startDate)).add(b3gService25);
                            }
                            break;
                        case 36:
                            Iterator it26 = arrayList.iterator();
                            while (it26.hasNext()) {
                                B3gService b3gService26 = (B3gService) it26.next();
                                String str18 = ((GetDotation) b3gService26).operationDate;
                                if (linkedHashMap.get(str18) == null) {
                                    linkedHashMap.put(str18, new ArrayList());
                                }
                                ((ArrayList) linkedHashMap.get(str18)).add(b3gService26);
                            }
                            break;
                        case 37:
                            Iterator it27 = arrayList.iterator();
                            while (it27.hasNext()) {
                                B3gService b3gService27 = (B3gService) it27.next();
                                String str19 = ((ResponseDotation.LieuRecharges) b3gService27).Date;
                                if (linkedHashMap.get(str19) == null) {
                                    linkedHashMap.put(str19, new ArrayList());
                                }
                                ((ArrayList) linkedHashMap.get(str19)).add(b3gService27);
                            }
                            break;
                        case 38:
                            Iterator it28 = arrayList.iterator();
                            while (it28.hasNext()) {
                                B3gService b3gService28 = (B3gService) it28.next();
                                String str20 = ((ResponseDotation.Recharge) b3gService28).Date;
                                if (linkedHashMap.get(str20) == null) {
                                    linkedHashMap.put(str20, new ArrayList());
                                }
                                ((ArrayList) linkedHashMap.get(str20)).add(b3gService28);
                            }
                            break;
                        case 39:
                            Iterator it29 = arrayList.iterator();
                            while (it29.hasNext()) {
                                B3gService b3gService29 = (B3gService) it29.next();
                                String str21 = " " + ((AgenceDemandeHistorique) b3gService29).request_Date;
                                if (linkedHashMap.get(str21) == null) {
                                    linkedHashMap.put(str21, new ArrayList());
                                }
                                ((ArrayList) linkedHashMap.get(str21)).add(b3gService29);
                            }
                            break;
                        case 40:
                            Iterator it30 = arrayList.iterator();
                            while (it30.hasNext()) {
                                B3gService b3gService30 = (B3gService) it30.next();
                                MTCHistoric mTCHistoric2 = (MTCHistoric) b3gService30;
                                String[] split = DataTools.split(mTCHistoric2.OptionnalData2, ";");
                                if (split.length > 1) {
                                    str13 = split[0];
                                } else {
                                    str13 = mTCHistoric2.BillerId;
                                }
                                if (linkedHashMap.get(str13) == null) {
                                    linkedHashMap.put(str13, new ArrayList());
                                }
                                ((ArrayList) linkedHashMap.get(str13)).add(b3gService30);
                            }
                            break;
                        case 41:
                            Iterator it31 = arrayList.iterator();
                            while (it31.hasNext()) {
                                B3gService b3gService31 = (B3gService) it31.next();
                                String str22 = " " + ((MDMAccountOperation) b3gService31).date;
                                if (linkedHashMap.get(str22) == null) {
                                    linkedHashMap.put(str22, new ArrayList());
                                }
                                ((ArrayList) linkedHashMap.get(str22)).add(b3gService31);
                            }
                            break;
                        case 42:
                            Iterator it32 = arrayList.iterator();
                            while (it32.hasNext()) {
                                B3gService b3gService32 = (B3gService) it32.next();
                                String str23 = " " + ((TransfertHistorique) b3gService32).date;
                                if (linkedHashMap.get(str23) == null) {
                                    linkedHashMap.put(str23, new ArrayList());
                                }
                                ((ArrayList) linkedHashMap.get(str23)).add(b3gService32);
                            }
                            break;
                    }
            }
        } else {
            Iterator it33 = arrayList.iterator();
            while (it33.hasNext()) {
                B3gService b3gService33 = (B3gService) it33.next();
                MTCImpaye mTCImpaye4 = (MTCImpaye) b3gService33;
                if (mTCImpaye4.DateFacture.length() > 10) {
                    str2 = mTCImpaye4.DateFacture.substring(0, 10);
                } else {
                    str2 = mTCImpaye4.DateFacture;
                }
                if (linkedHashMap.get(str2) == null) {
                    linkedHashMap.put(str2, new ArrayList());
                }
                ((ArrayList) linkedHashMap.get(str2)).add(b3gService33);
            }
        }
        return linkedHashMap;
    }

    public void Draw_GroupBy(Container container, ArrayList arrayList, String str, int i, B3gService b3gService) {
        container.setScrollableY(true);
        container.setScrollable(true);
        container.setScrollVisible(false);
        Container container2 = new Container(new BoxLayout(2));
        container2.setName("cntTableDataY");
        container2.addComponent(DrawData_GroupBy(arrayList, str, i, b3gService, null));
        container.addComponent(container2);
        container.revalidate();
    }

    public void Draw_GroupBy(Container container, ArrayList arrayList, String str, int i, B3gService b3gService, Component component) {
        container.setScrollableY(true);
        container.setScrollVisible(false);
        Container container2 = new Container(new BoxLayout(2));
        container2.setName("cntTableDataY");
        container2.addComponent(DrawData_GroupBy(arrayList, str, i, b3gService, component));
        container.addComponent(container2);
        container.revalidate();
    }

    public void Draw_GroupBy(Container container, ArrayList arrayList, String str, int i, B3gService b3gService, Component component, Component component2) {
        container.setScrollableY(true);
        container.setScrollable(true);
        Container container2 = new Container(new BoxLayout(2));
        container2.setName("cntTableDataY");
        container2.addComponent(DrawData_GroupBy(arrayList, str, i, b3gService, component, component2));
        container.addComponent(container2);
        container.revalidate();
    }

    public Container DrawData_GroupBy(ArrayList arrayList, String str, int i, B3gService b3gService, Component component, Component component2) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("Container");
        LinkedHashMap linkedHashMap = (LinkedHashMap) GetMapB3gServiceList(arrayList, i);
        Object[] array = linkedHashMap.keySet().toArray();
        ArrayList arrayList2 = new ArrayList();
        Collections.addAll(arrayList2, array);
        if (i != 6 && i != 7) {
            Collections.sort(arrayList2, new 32());
        } else if (i == 7) {
            Collections.sort(arrayList2, new 33());
        }
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            DrawData_One_GroupBy(container, arrayList2.get(i2).toString(), (ArrayList) linkedHashMap.get(arrayList2.get(i2)), str, i, b3gService, component, component2);
        }
        return container;
    }

    class 32 implements Comparator {
        32() {
        }

        public int compare(String str, String str2) {
            try {
                int compareTo = str2.toString().substring(6).compareTo(str.toString().substring(6));
                return (compareTo == 0 && (compareTo = str2.toString().substring(3, 5).compareTo(str.toString().substring(3, 5))) == 0) ? str2.toString().substring(0, 2).compareTo(str.toString().substring(0, 2)) : compareTo;
            } catch (Exception unused) {
                return 1;
            }
        }
    }

    class 33 implements Comparator {
        33() {
        }

        public int compare(String str, String str2) {
            try {
                return str.toString().compareTo(str2.toString());
            } catch (Exception unused) {
                return 1;
            }
        }
    }

    public Container DrawData_GroupBy(ArrayList arrayList, String str, int i, B3gService b3gService, Component component) {
        Object[] array;
        Container container = new Container(new BoxLayout(2));
        container.setUIID("Container");
        Map map = (Map) GetMapB3gServiceList(arrayList, i);
        Map map2 = i == 29 ? (Map) GetMapB3gServiceList(arrayList, 40) : null;
        if (i == 29) {
            array = map2.keySet().toArray();
        } else {
            array = map.keySet().toArray();
        }
        ArrayList arrayList2 = new ArrayList();
        Collections.addAll(arrayList2, array);
        if (i != 6 && i != 7 && i != 42 && i != 41) {
            Collections.sort(arrayList2, new 34());
        } else if (i == 7) {
            Collections.sort(arrayList2, new 35());
        }
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            if (i == 29) {
                DrawData_One_GroupBy(container, arrayList2.get(i2).toString(), (ArrayList) map2.get(arrayList2.get(i2)), str, i, b3gService, component);
            } else {
                DrawData_One_GroupBy(container, arrayList2.get(i2).toString(), (ArrayList) map.get(arrayList2.get(i2)), str, i, b3gService, component);
            }
        }
        return container;
    }

    class 34 implements Comparator {
        34() {
        }

        public int compare(String str, String str2) {
            try {
                int compareTo = str2.toString().substring(6).compareTo(str.toString().substring(6));
                return (compareTo == 0 && (compareTo = str2.toString().substring(3, 5).compareTo(str.toString().substring(3, 5))) == 0) ? str2.toString().substring(0, 2).compareTo(str.toString().substring(0, 2)) : compareTo;
            } catch (Exception unused) {
                return 1;
            }
        }
    }

    class 35 implements Comparator {
        35() {
        }

        public int compare(String str, String str2) {
            try {
                return str.toString().compareTo(str2.toString());
            } catch (Exception unused) {
                return 1;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public Container DrawData_One_GroupBy(Container container, String str, ArrayList arrayList, String str2, int i, B3gService b3gService, Component component) {
        if (i == 9) {
            container.addComponent(DrawHeaderTableLayout_GroupBy(formatDate(str), str2));
        } else if (i == 34) {
            container.addComponent(DrawHeaderTableLayout_GroupBy(StatutOvpExc(str), str2, "Red"));
        } else {
            container.addComponent(DrawHeaderTableLayout_GroupBy(str, str2));
        }
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("CntHomeV2Margin");
        int i2 = 0;
        if (i == 1) {
            while (i2 < arrayList.size()) {
                container2.addComponent(((AccountOperation) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 3, b3gService, component, null));
                i2++;
            }
        } else if (i == 2) {
            while (i2 < arrayList.size()) {
                if (i2 != 0) {
                    container2.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                }
                container2.addComponent(((AccountAutorisation) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 121, b3gService, component, null));
                i2++;
            }
        } else if (i == 29) {
            while (i2 < arrayList.size()) {
                container2.addComponent(((MTCHistoric) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 29, b3gService, component, null));
                i2++;
            }
        } else if (i == 87) {
            while (i2 < arrayList.size()) {
                container2.addComponent(((MTCImpaye) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 283, b3gService, component, null));
                i2++;
            }
        } else if (i == 281) {
            while (i2 < arrayList.size()) {
                container2.addComponent(((MTCImpaye) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 281, b3gService, component, null));
                i2++;
            }
        } else if (i == 41) {
            while (i2 < arrayList.size()) {
                container2.addComponent(((MDMAccountOperation) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                i2++;
            }
        } else if (i != 42) {
            switch (i) {
                case 4:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((CardOperation) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 5:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((RecurringDetail) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 6:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((Walet) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 9, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 7:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((BancBeneficary) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 8:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((TransferHistoric) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 9:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((MyEDocumentsService) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 10:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((OrderMADHistoricDataService) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 11:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((StudentTransferHisoricDataService) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 12:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((AccountOperation) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 19, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 13:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((AccountOperation) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 21, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 14:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((CheckbookDemand) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 22, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 15:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((LCNBoockDemand) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 24, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 16:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((MTCImpaye) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 28, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 17:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((MTCImpaye) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 282, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 18:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((OrderCurrencyHistoricDataService) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 19:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((MyCheckLcn) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 282, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 20:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((CashOutOperatoion) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 28, b3gService, component, null));
                        i2++;
                    }
                    break;
                case 21:
                    while (i2 < arrayList.size()) {
                        container2.addComponent(((ReleveDgi) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 28, b3gService, component, null));
                        i2++;
                    }
                    break;
                default:
                    switch (i) {
                        case 33:
                            while (i2 < arrayList.size()) {
                                container2.addComponent(((FatouratyService) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 28, b3gService, component, null));
                                i2++;
                            }
                            break;
                        case 34:
                            while (i2 < arrayList.size()) {
                                container2.addComponent(((OvpExecutionService) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 28, b3gService, component, null));
                                i2++;
                            }
                            break;
                        case 35:
                            while (i2 < arrayList.size()) {
                                container2.addComponent(((OvpService) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 28, b3gService, component, null));
                                i2++;
                            }
                            break;
                        case 36:
                            while (i2 < arrayList.size()) {
                                container2.addComponent(((GetDotation) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                                i2++;
                            }
                            break;
                        case 37:
                            while (i2 < arrayList.size()) {
                                container2.addComponent(((ResponseDotation.LieuRecharges) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                                i2++;
                            }
                            break;
                        case 38:
                            while (i2 < arrayList.size()) {
                                container2.addComponent(((ResponseDotation.Recharge) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                                i2++;
                            }
                            break;
                        case 39:
                            while (i2 < arrayList.size()) {
                                container2.addComponent(((AgenceDemandeHistorique) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                                i2++;
                            }
                            break;
                        default:
                            break;
                    }
            }
        } else {
            while (i2 < arrayList.size()) {
                container2.addComponent(((TransfertHistorique) arrayList.get(i2)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                i2++;
            }
        }
        container.addComponent(container2);
        return container;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public Container DrawData_One_GroupBy(Container container, String str, ArrayList arrayList, String str2, int i, B3gService b3gService, Component component, int i2) {
        if (i == 9) {
            container.addComponent(DrawHeaderTableLayout_GroupBy(formatDate(str), str2));
        } else {
            container.addComponent(DrawHeaderTableLayout_GroupBy(str, str2));
        }
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("CntHomeV2Margin");
        int i3 = 0;
        if (i == 1) {
            while (i3 < arrayList.size()) {
                container2.addComponent(((AccountOperation) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 3, b3gService, component, null));
                i3++;
            }
        } else if (i == 2) {
            while (i3 < arrayList.size()) {
                if (i3 != 0) {
                    container2.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                }
                container2.addComponent(((AccountAutorisation) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 121, b3gService, component, null));
                i3++;
            }
        } else if (i == 29) {
            container2.setUIID("Container");
            while (i3 < arrayList.size()) {
                container2.addComponent(((MTCHistoric) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 29, b3gService, component, null));
                i3++;
            }
        } else if (i != 281) {
            switch (i) {
                case 4:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((CardOperation) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 5:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((RecurringDetail) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 6:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((Walet) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 9, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 7:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((BancBeneficary) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 8:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((TransferHistoric) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 9:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((MyEDocumentsService) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 10:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((OrderMADHistoricDataService) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 11:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((StudentTransferHisoricDataService) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 12:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((AccountOperation) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 19, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 13:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((AccountOperation) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 21, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 14:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((CheckbookDemand) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 22, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 15:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((LCNBoockDemand) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 24, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 16:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((MTCImpaye) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 28, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 17:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((MTCImpaye) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 282, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 18:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((OrderCurrencyHistoricDataService) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 19:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((MyCheckLcn) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 282, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 20:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((CashOutOperatoion) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 28, b3gService, component, null));
                        i3++;
                    }
                    break;
                case 21:
                    while (i3 < arrayList.size()) {
                        container2.addComponent(((ReleveDgi) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 28, b3gService, component, null));
                        i3++;
                    }
                    break;
                default:
                    switch (i) {
                        case 33:
                            while (i3 < arrayList.size()) {
                                container2.addComponent(((FatouratyService) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 28, b3gService, component, null));
                                i3++;
                            }
                            break;
                        case 34:
                            while (i3 < arrayList.size()) {
                                container2.addComponent(((OvpExecutionService) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 28, b3gService, component, null));
                                i3++;
                            }
                            break;
                        case 35:
                            while (i3 < arrayList.size()) {
                                container2.addComponent(((OvpService) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 28, b3gService, component, null));
                                i3++;
                            }
                            break;
                        case 36:
                            while (i3 < arrayList.size()) {
                                container2.addComponent(((GetDotation) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                                i3++;
                            }
                            break;
                        case 37:
                            while (i3 < arrayList.size()) {
                                container2.addComponent(((ResponseDotation.LieuRecharges) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                                i3++;
                            }
                            break;
                        case 38:
                            while (i3 < arrayList.size()) {
                                container2.addComponent(((ResponseDotation.Recharge) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 0, b3gService, component, null));
                                i3++;
                            }
                            break;
                        default:
                            break;
                    }
            }
        } else {
            while (i3 < arrayList.size()) {
                container2.addComponent(((MTCImpaye) arrayList.get(i3)).DrawItem(this.stateMachine, this.ressource, 281, b3gService, component, null));
                i3++;
            }
        }
        if (i == 29) {
            return container2;
        }
        container.addComponent(container2);
        return container;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008a A[LOOP:2: B:23:0x0084->B:25:0x008a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ad A[LOOP:4: B:32:0x00a7->B:34:0x00ad, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.codename1.ui.Container DrawData_One_GroupBy(com.codename1.ui.Container r15, java.lang.String r16, java.util.ArrayList r17, java.lang.String r18, int r19, com.b3g.services.B3gService r20, com.codename1.ui.Component r21, com.codename1.ui.Component r22) {
        /*
            Method dump skipped, instructions count: 782
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.CihUiManager.DrawData_One_GroupBy(com.codename1.ui.Container, java.lang.String, java.util.ArrayList, java.lang.String, int, com.b3g.services.B3gService, com.codename1.ui.Component, com.codename1.ui.Component):com.codename1.ui.Container");
    }

    public Container DrawHeaderTableLayout_GroupBy(String str, String str2) {
        Container container = new Container();
        container.setUIID("CntBlueHeader2");
        TableLayout tableLayout = new TableLayout(1, 1);
        container.setLayout(tableLayout);
        Label label = new Label(str);
        label.setUIID("g_lblHeaderTable_R_Low_V23");
        TableLayout.Constraint createConstraint = tableLayout.createConstraint(0, 0);
        createConstraint.setWidthPercentage(100);
        createConstraint.setHorizontalAlign(1);
        container.addComponent(createConstraint, label);
        return container;
    }

    public void DrawTabsMenu(Container container, int i, Tabs tabs) {
        if (i != 5) {
            return;
        }
        Button button = new Button("OPERATIONS");
        button.setUIID("ad_BtnTab");
        button.addActionListener(new 36(tabs));
        Button button2 = new Button("PLAFONDS");
        button2.setUIID("ad_BtnTab");
        button2.addActionListener(new 37(tabs));
        container.addComponent(button);
        container.addComponent(button2);
    }

    class 36 implements ActionListener {
        final /* synthetic */ Tabs val$tab;

        36(Tabs tabs) {
            this.val$tab = tabs;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$tab.setSelectedIndex(0);
        }
    }

    class 37 implements ActionListener {
        final /* synthetic */ Tabs val$tab;

        37(Tabs tabs) {
            this.val$tab = tabs;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$tab.setSelectedIndex(1);
        }
    }

    public void DrawTabsWithNavigation(Container container, Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        Tabs tabs = new Tabs();
        tabs.hideTabs();
        tabs.setUIID("g_TabsV11");
        tabs.setAlwaysTensile(false);
        tabs.setDraggable(false);
        tabs.setSwipeActivated(false);
        Container container2 = new Container(new GridLayout(1, arrayList.size()));
        container2.setUIID("ad_CntBtnTab");
        Boolean bool = false;
        for (int i = 0; i < arrayList.size(); i++) {
            Button button = new Button(((B3gContainer) arrayList.get(i)).Title);
            button.setUIID("ad_BtnTab");
            button.setTickerEnabled(false);
            button.setFocusable(false);
            button.addActionListener(new 38(tabs, i, button, container2));
            if (((B3gContainer) arrayList.get(i)).IsToBeSelected.booleanValue()) {
                button.setEnabled(false);
                bool = true;
            }
            container2.addComponent(button);
            ((B3gContainer) arrayList.get(i)).cnt.setScrollVisible(false);
            tabs.addTab(((B3gContainer) arrayList.get(i)).Title, ((B3gContainer) arrayList.get(i)).cnt);
            if (((B3gContainer) arrayList.get(i)).IsToBeSelected.booleanValue()) {
                tabs.setSelectedIndex(i);
            }
        }
        if (!bool.booleanValue()) {
            ((Button) container2.getComponentAt(0)).setEnabled(false);
        }
        tabs.addSelectionListener(new 39(arrayList));
        container.addComponent(container2);
        container.addComponent(tabs);
        container.revalidate();
    }

    class 38 implements ActionListener {
        final /* synthetic */ Tabs val$TabsDetail;
        final /* synthetic */ Button val$btntab;
        final /* synthetic */ Container val$butCnt;
        final /* synthetic */ int val$j;

        38(Tabs tabs, int i, Button button, Container container) {
            this.val$TabsDetail = tabs;
            this.val$j = i;
            this.val$btntab = button;
            this.val$butCnt = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$TabsDetail.setSelectedIndex(this.val$j);
            this.val$btntab.setEnabled(false);
            CihUiManager.this.DisableMenuButtonsStatus(this.val$butCnt, this.val$j);
        }
    }

    class 39 implements SelectionListener {
        final /* synthetic */ ArrayList val$cntList;

        39(ArrayList arrayList) {
            this.val$cntList = arrayList;
        }

        public void selectionChanged(int i, int i2) {
            int i3 = 0;
            if (i2 == 0) {
                while (i3 < this.val$cntList.size()) {
                    if (((B3gContainer) this.val$cntList.get(i3)).Title.equals("COMPTES")) {
                        CihMBanking.sesPMTR.setTitle("COMPTES");
                        CihUiManager.this.title = "COMPTES";
                    }
                    i3++;
                }
            } else if (i2 == 1) {
                while (i3 < this.val$cntList.size()) {
                    if (((B3gContainer) this.val$cntList.get(i3)).Title.equals("CARTES")) {
                        CihMBanking.sesPMTR.setTitle("CARTES");
                        CihUiManager.this.title = "CARTES";
                    }
                    i3++;
                }
            } else if (i2 == 2) {
                while (i3 < this.val$cntList.size()) {
                    if (((B3gContainer) this.val$cntList.get(i3)).Title.equals("WE PAY")) {
                        CihMBanking.sesPMTR.setTitle("WE PAY");
                        CihUiManager.this.title = "WE PAY";
                    }
                    i3++;
                }
            }
            System.err.println("====***=======" + i2);
        }
    }

    public void DrawTabsWithNavigationV3(Container container, Object obj, Container container2, TextField textField, TextField textField2, TextField textField3) {
        boolean z;
        boolean z2;
        TableLayout tableLayout = new TableLayout(1, 1);
        container.setLayout(tableLayout);
        ArrayList arrayList = (ArrayList) obj;
        Tabs tabs = new Tabs();
        tabs.hideTabs();
        tabs.setUIID("g_TabsV11");
        boolean z3 = false;
        tabs.setAlwaysTensile(false);
        tabs.setDraggable(false);
        tabs.setSwipeActivated(false);
        Container container3 = new Container(new GridLayout(1, arrayList.size()));
        container3.setUIID("ad_CntBtnTab");
        Boolean bool = false;
        int i = 0;
        while (i < arrayList.size()) {
            Button button = new Button(((B3gContainer) arrayList.get(i)).Title);
            button.setUIID("ad_BtnTab");
            button.setTickerEnabled(z3);
            button.setFocusable(z3);
            button.addActionListener(new 40(button, container2, tabs, i, container3));
            if (((B3gContainer) arrayList.get(i)).IsToBeSelected.booleanValue()) {
                z = false;
                button.setEnabled(false);
                z2 = true;
                bool = true;
            } else {
                z = false;
                z2 = true;
            }
            container3.addComponent(button);
            ((B3gContainer) arrayList.get(i)).cnt.setScrollVisible(z);
            tabs.addTab(((B3gContainer) arrayList.get(i)).Title, ((B3gContainer) arrayList.get(i)).cnt);
            if (((B3gContainer) arrayList.get(i)).IsToBeSelected.booleanValue()) {
                tabs.setSelectedIndex(i);
            }
            i++;
            z3 = false;
        }
        if (!bool.booleanValue()) {
            ((Button) container3.getComponentAt(0)).setEnabled(false);
        }
        tabs.addSelectionListener(new 41(container2));
        container.addComponent(tableLayout.createConstraint().widthPercentage(100), container3);
        container.addComponent(tableLayout.createConstraint().widthPercentage(100).heightPercentage(92), tabs);
        container.revalidate();
    }

    class 40 implements ActionListener {
        final /* synthetic */ Tabs val$TabsDetail;
        final /* synthetic */ Button val$btntab;
        final /* synthetic */ Container val$butCnt;
        final /* synthetic */ Container val$cnt;
        final /* synthetic */ int val$j;

        40(Button button, Container container, Tabs tabs, int i, Container container2) {
            this.val$btntab = button;
            this.val$cnt = container;
            this.val$TabsDetail = tabs;
            this.val$j = i;
            this.val$butCnt = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$btntab.getText().equals("HISTORIQUE") || this.val$btntab.getText().equals("تاريخ العمليات") || this.val$btntab.getText().equals("HISTORY") || this.val$btntab.getText().equals("HISTORIAL") || this.val$btntab.getText().equals("HISTORIQUE")) {
                CihMBanking.sesPMTR.tabTransfertModelTitle = "HISTORIQUE";
                this.val$cnt.setHidden(true);
            } else if (this.val$btntab.getText().equals("RECHARGE") || this.val$btntab.getText().equals("التعبئة") || this.val$btntab.getText().equals("REFILL") || this.val$btntab.getText().equals("RECARGAR") || this.val$btntab.getText().equals("RECHARGE")) {
                CihMBanking.sesPMTR.tabTransfertModelTitle = "RECHARGE";
                if (TopUpNewPage.isOtpPageShow) {
                    this.val$cnt.setHidden(true);
                } else {
                    this.val$cnt.setHidden(false);
                }
            } else if (this.val$btntab.getText().equals("RECHARGE WE  PAY") || this.val$btntab.getText().equals("تعبئة \"وي باي\" WE PAY") || this.val$btntab.getText().equals("WE PAY REFILL") || this.val$btntab.getText().equals("RECARGA WE PAY") || this.val$btntab.getText().equals("RECHARGE WE PAY")) {
                CihMBanking.sesPMTR.tabTransfertModelTitle = "RECHARGE WE  PAY";
                if (TransfertWallet.isOtpPageShow) {
                    this.val$cnt.setHidden(true);
                } else {
                    this.val$cnt.setHidden(false);
                }
            } else if (this.val$btntab.getText().equals("VIREMENT") || this.val$btntab.getText().equals("التحويل") || this.val$btntab.getText().equals("TRANSFER") || this.val$btntab.getText().equals("TRANSFERENCIA ") || this.val$btntab.getText().equals("VIREMENT")) {
                CihMBanking.sesPMTR.tabTransfertModelTitle = "VIREMENT";
                if (TransferNewPage.isOtpPageShow) {
                    this.val$cnt.setHidden(true);
                } else {
                    this.val$cnt.setHidden(false);
                }
            }
            this.val$TabsDetail.setSelectedIndex(this.val$j);
            this.val$btntab.setEnabled(false);
            CihUiManager.this.DisableMenuButtonsStatus(this.val$butCnt, this.val$j);
        }
    }

    class 41 implements SelectionListener {
        final /* synthetic */ Container val$cnt;

        41(Container container) {
            this.val$cnt = container;
        }

        public void selectionChanged(int i, int i2) {
            System.err.println("====***=======" + i2 + this.val$cnt.isHidden());
        }
    }

    public void DrawTabsWithNavigationTransfert(Container container, Object obj, Container container2, TextField textField, TextField textField2, TextField textField3) {
        boolean z;
        boolean z2;
        TableLayout tableLayout = new TableLayout(1, 1);
        container.setLayout(tableLayout);
        ArrayList arrayList = (ArrayList) obj;
        Tabs tabs = new Tabs();
        tabs.hideTabs();
        tabs.setUIID("g_TabsV11");
        boolean z3 = false;
        tabs.setAlwaysTensile(false);
        tabs.setDraggable(false);
        tabs.setSwipeActivated(false);
        Container container3 = new Container(new GridLayout(1, arrayList.size()));
        container3.setUIID("ad_CntBtnTab");
        Boolean bool = false;
        int i = 0;
        while (i < arrayList.size()) {
            Button button = new Button(((B3gContainer) arrayList.get(i)).Title);
            button.setUIID("ad_BtnTab");
            button.setTickerEnabled(z3);
            button.setFocusable(z3);
            button.addActionListener(new 42(button, container2, tabs, i, container3));
            if (((B3gContainer) arrayList.get(i)).IsToBeSelected.booleanValue()) {
                z = false;
                button.setEnabled(false);
                z2 = true;
                bool = true;
            } else {
                z = false;
                z2 = true;
            }
            container3.addComponent(button);
            ((B3gContainer) arrayList.get(i)).cnt.setScrollVisible(z);
            tabs.addTab(((B3gContainer) arrayList.get(i)).Title, ((B3gContainer) arrayList.get(i)).cnt);
            if (((B3gContainer) arrayList.get(i)).IsToBeSelected.booleanValue()) {
                tabs.setSelectedIndex(i);
            }
            i++;
            z3 = false;
        }
        if (!bool.booleanValue()) {
            ((Button) container3.getComponentAt(0)).setEnabled(false);
        }
        tabs.addSelectionListener(new 43(arrayList, container, container2));
        container.addComponent(tableLayout.createConstraint().widthPercentage(100), container3);
        container.addComponent(tableLayout.createConstraint().widthPercentage(100).heightPercentage(92), tabs);
        container.revalidate();
    }

    class 42 implements ActionListener {
        final /* synthetic */ Tabs val$TabsDetail;
        final /* synthetic */ Button val$btntab;
        final /* synthetic */ Container val$butCnt;
        final /* synthetic */ Container val$cnt;
        final /* synthetic */ int val$j;

        42(Button button, Container container, Tabs tabs, int i, Container container2) {
            this.val$btntab = button;
            this.val$cnt = container;
            this.val$TabsDetail = tabs;
            this.val$j = i;
            this.val$butCnt = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$btntab.getText().equals("HISTORIQUE") || this.val$btntab.getText().equals("تاريخ العمليات") || this.val$btntab.getText().equals("HISTORY") || this.val$btntab.getText().equals("HISTORIAL") || this.val$btntab.getText().equals("HISTORIQUE")) {
                CihMBanking.sesPMTR.tabTransfertModelTitle = "HISTORIQUE";
                this.val$cnt.setHidden(true);
            } else if (this.val$btntab.getText().equals("RECHARGE") || this.val$btntab.getText().equals("التعبئة") || this.val$btntab.getText().equals("REFILL") || this.val$btntab.getText().equals("RECARGAR") || this.val$btntab.getText().equals("RECHARGE")) {
                CihMBanking.sesPMTR.tabTransfertModelTitle = "RECHARGE";
                if (TopUpNewPage.isOtpPageShow) {
                    this.val$cnt.setHidden(true);
                } else {
                    this.val$cnt.setHidden(false);
                }
            } else if (this.val$btntab.getText().equals("RECHARGE WE  PAY") || this.val$btntab.getText().equals("تعبئة \"وي باي\" WE PAY") || this.val$btntab.getText().equals("WE PAY REFILL") || this.val$btntab.getText().equals("RECARGA WE PAY") || this.val$btntab.getText().equals("RECHARGE WE PAY")) {
                CihMBanking.sesPMTR.tabTransfertModelTitle = "RECHARGE WE  PAY";
                if (TransfertWallet.isOtpPageShow) {
                    this.val$cnt.setHidden(true);
                } else {
                    this.val$cnt.setHidden(false);
                }
            } else if (this.val$btntab.getText().equals("VIREMENT") || this.val$btntab.getText().equals("التحويل") || this.val$btntab.getText().equals("TRANSFER") || this.val$btntab.getText().equals("TRANSFERENCIA ") || this.val$btntab.getText().equals("VIREMENT")) {
                CihMBanking.sesPMTR.tabTransfertModelTitle = "VIREMENT";
                if (TransferNewPage.isOtpPageShow) {
                    this.val$cnt.setHidden(true);
                } else {
                    this.val$cnt.setHidden(false);
                }
            }
            this.val$TabsDetail.setSelectedIndex(this.val$j);
            this.val$btntab.setEnabled(false);
            CihUiManager.this.DisableMenuButtonsStatus(this.val$butCnt, this.val$j);
        }
    }

    class 43 implements SelectionListener {
        final /* synthetic */ Container val$cnt;
        final /* synthetic */ ArrayList val$cntList;
        final /* synthetic */ Container val$pTabsHost;

        43(ArrayList arrayList, Container container, Container container2) {
            this.val$cntList = arrayList;
            this.val$pTabsHost = container;
            this.val$cnt = container2;
        }

        public void selectionChanged(int i, int i2) {
            if (i2 != 0 && i2 == 1) {
                ((B3gContainer) this.val$cntList.get(1)).cnt.getComponentAt(0).remove();
                ((B3gContainer) this.val$cntList.get(1)).cnt.setLayout(new BoxLayout(2));
                TransfertDATA TransfertDATAProcess = new TransfertDATA().TransfertDATAProcess(28);
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < TransfertDATAProcess.getTransferHistoricList().size(); i3++) {
                    ((TransferHistoric) TransfertDATAProcess.getTransferHistoricList().get(i3)).groupKey = ((TransferHistoric) TransfertDATAProcess.getTransferHistoricList().get(i3)).OperationDate.substring(0, 10);
                    arrayList.add((B3gService) TransfertDATAProcess.getTransferHistoricList().get(i3));
                }
                if (arrayList.size() > 0) {
                    CihUiManager.this.Draw_GroupBy(((B3gContainer) this.val$cntList.get(1)).cnt, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 8, TransfertDATAProcess);
                } else {
                    ((B3gContainer) this.val$cntList.get(1)).cnt.addComponent(CihUiManager.this.GetCntMessage("Vous n'avez aucun historique"));
                }
                this.val$pTabsHost.revalidate();
            }
            System.err.println("====***=======" + i2 + this.val$cnt.isHidden());
        }
    }

    public void DrawContainersWithNavigation(Container container, Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("g_TabsV11");
        container2.setAlwaysTensile(false);
        container2.setDraggable(false);
        Boolean bool = false;
        Container container3 = new Container(new GridLayout(1, arrayList.size()));
        container3.setUIID("ad_CntBtnTab");
        if (arrayList.size() > 0) {
            Button button = new Button(((B3gContainer) arrayList.get(0)).Title);
            button.setUIID("ad_BtnTab");
            button.addActionListener(new 44(button, container3, 0));
            if (((B3gContainer) arrayList.get(0)).IsToBeSelected.booleanValue()) {
                button.setEnabled(false);
                bool = true;
            }
            container3.addComponent(button);
            ((B3gContainer) arrayList.get(0)).cnt.setScrollVisible(false);
            container2.addComponent(((B3gContainer) arrayList.get(0)).cnt);
        }
        if (!bool.booleanValue()) {
            ((Button) container3.getComponentAt(0)).setEnabled(false);
        }
        container.addComponent(container3);
        container.addComponent(container2);
        container.revalidate();
    }

    class 44 implements ActionListener {
        final /* synthetic */ Button val$btntab;
        final /* synthetic */ Container val$butCnt;
        final /* synthetic */ int val$j;

        44(Button button, Container container, int i) {
            this.val$btntab = button;
            this.val$butCnt = container;
            this.val$j = i;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$btntab.setEnabled(false);
            CihUiManager.this.DisableMenuButtonsStatus(this.val$butCnt, this.val$j);
        }
    }

    public void DisableMenuButtonsStatus(Container container, int i) {
        for (int i2 = 0; i2 < container.getComponentCount(); i2++) {
            if (i2 != i) {
                ((Button) container.getComponentAt(i2)).setEnabled(true);
            }
        }
    }

    public Container GetCntMessage(String str) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setFillRows(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        container.setUIID("g_CntTranspMsg");
        SpanLabel spanLabel = new SpanLabel();
        spanLabel.setUIID("dg_splblMsgCenter");
        spanLabel.setScrollVisible(false);
        spanLabel.setText(str);
        spanLabel.setIconPosition("West");
        spanLabel.setTextUIID("dg_lblSPError");
        spanLabel.setIconUIID("g_cntEmpty");
        container.addComponent(spanLabel);
        container.getAllStyles().setMarginTop(2);
        return container;
    }

    public String formatDate(String str) {
        String str2 = " 20" + str.substring(3, 5);
        if (str.substring(0, 2).equals("01")) {
            str2 = "Janvier" + str2;
        }
        if (str.substring(0, 2).equals("02")) {
            str2 = "Février" + str2;
        }
        if (str.substring(0, 2).equals("03")) {
            str2 = "Mars" + str2;
        }
        if (str.substring(0, 2).equals("04")) {
            str2 = "Avril" + str2;
        }
        if (str.substring(0, 2).equals("05")) {
            str2 = "Mai" + str2;
        }
        if (str.substring(0, 2).equals("06")) {
            str2 = "Juin" + str2;
        }
        if (str.substring(0, 2).equals("07")) {
            str2 = "Juillet" + str2;
        }
        if (str.substring(0, 2).equals("08")) {
            str2 = "Août" + str2;
        }
        if (str.substring(0, 2).equals("09")) {
            str2 = "Septembre" + str2;
        }
        if (str.substring(0, 2).equals("10")) {
            str2 = "Octobre" + str2;
        }
        if (str.substring(0, 2).equals("11")) {
            str2 = "Novembre" + str2;
        }
        return str.substring(0, 2).equals("12") ? "Décembre" + str2 : str2;
    }

    ArrayList removeSelectedCardFromList(String str, Object obj) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((ArrayList) obj).iterator();
        while (it.hasNext()) {
            B3gService b3gService = (B3gService) it.next();
            if (!((Beneficiary) b3gService).cardNumber.equals(str)) {
                arrayList.add(b3gService);
            }
        }
        return arrayList;
    }

    ArrayList getAccountBeneficiary(String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountListVirm());
        arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getListRelationAccount());
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Account account = (Account) it.next();
            if (account.accountNumber.equals(str)) {
                Iterator it2 = account.accountBeneficiaries.iterator();
                while (it2.hasNext()) {
                    Beneficiary beneficiary = (Beneficiary) it2.next();
                    if (beneficiary.serviceId.equals("300013")) {
                        arrayList.add(beneficiary);
                    }
                }
            }
        }
        Collections.sort(arrayList, new SortBenifByAlias());
        return arrayList;
    }

    public void setIconBtnNotification() {
        this.nbrNotif = 0;
        SetCommands();
    }

    public void setCountBtnMessg() {
        if (CihMBanking.sesPMTR.countMessage.equals("") || CihMBanking.sesPMTR.countMessage.equals("0") || CihMBanking.sesPMTR.countMessage == null) {
            this.BtnMessag.setIcon(this.ressource.getImage("MessnonActiv.png"));
        } else {
            this.BtnMessag.setIcon(this.ressource.getImage("Messactiv.png"));
        }
    }

    public Container DrawAcquiredAcc(String str, String str2) {
        Container GetContainer = GetContainer("GloabalContainerV2", "Compte à créditer");
        Container createContainer = this.stateMachine.createContainer(this.ressource, "AccountItemBenefTransfertV2");
        this.stateMachine.findLblAccountOwner(createContainer).setText(str);
        this.stateMachine.findLblAccountRibValue(createContainer).setText(StringTools.RibFormatWithSP(str2));
        GetContainer.addComponent(createContainer);
        return GetContainer;
    }

    private class getMessCountService extends SyncTask {
        private getMessCountService() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public String doInBackground(Button... buttonArr) {
            String MessageCountSilent = new Messagerie().MessageCountSilent();
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                CihMBanking.sesPMTR.countMessage = MessageCountSilent;
                CihUiManager.this.setCountBtnMessg();
            }
            return MessageCountSilent;
        }
    }

    public Container DrawListContainerForAccountsHome(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2, Component component3) {
        int i3;
        Container GetContainer = GetContainer(str, str2);
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList.size() > 0) {
            Tabs tabs = new Tabs();
            int size = arrayList.size();
            int i4 = (size / i) + (size % i);
            Container container = new Container(new FlowLayout(4, 4));
            Button button = new Button();
            button.setUIID("OrgLabel");
            button.setText("Ouvrir un compte d'Epargne");
            button.addActionListener(new 45());
            container.add(button);
            Boolean.valueOf(false);
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                if (((Account) arrayList.get(i5)).rib.substring(13, 17).equals("2310")) {
                    Boolean.valueOf(true);
                }
            }
            for (int i6 = 0; i6 < i4; i6++) {
                Container container2 = new Container(new BoxLayout(2));
                int i7 = i6 * i;
                for (int i8 = i7; i8 < Math.min(size - i7, i) + i7; i8++) {
                    container2.setScrollableY(false);
                    container2.setTensileDragEnabled(false);
                    if (i8 != i7) {
                        container2.addComponent(GetCntBorderSeparator("g_cntBorderV2"));
                    }
                    Container DrawItem = ((B3gService) arrayList.get(i8)).DrawItem(this.stateMachine, this.ressource, i2, null, null, null);
                    container2.setUIID("padding_1_1_1_1");
                    container2.addComponent(DrawItem);
                    container2.setName(DrawItem.getName());
                    container2.revalidate();
                }
                tabs.addTab(str2, container2);
            }
            if (!CihMBanking.sesPMTR.HaveEpargne) {
                tabs.addTab("", container);
            }
            tabs.hideTabs();
            tabs.setUIID("g_TabsV11");
            if (arrayList.size() == i) {
                tabs.setSwipeActivated(false);
                i3 = 1;
            } else {
                i3 = 1;
                tabs.setSwipeActivated(true);
            }
            GetContainer.addComponent(tabs);
            if (i4 > i3 || !CihMBanking.sesPMTR.HaveEpargne) {
                this.stateMachine.findCntHeaderPagerV2(GetContainer).removeAll();
                for (int i9 = 0; i9 < i4 + 1; i9++) {
                    Button button2 = new Button();
                    button2.setIcon(this.ressource.getImage("slider_arrow_w.png"));
                    button2.setDisabledIcon(this.ressource.getImage("slider_arrow.png"));
                    button2.setUIID("gb_btnDot");
                    if (i9 == 0) {
                        button2.setEnabled(true);
                    } else {
                        button2.setEnabled(false);
                    }
                    this.stateMachine.findCntHeaderPagerV2(GetContainer).addComponent(button2);
                }
                tabs.addSelectionListener(new 46(i4, GetContainer, tabs, i2, component, component2, component3));
            }
        } else {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlign(4);
            flowLayout.setValign(4);
            flowLayout.setFillRows(true);
            Container container3 = new Container();
            container3.setLayout(flowLayout);
            container3.setUIID("g_CntTranspMsg");
            SpanLabel spanLabel = new SpanLabel();
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setText(str3);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container3.addComponent(spanLabel);
            GetContainer.addComponent(container3);
        }
        return GetContainer;
    }

    class 45 implements ActionListener {
        45() {
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new CihUiManager$45$1$$ExternalSyntheticLambda0(this)).schedule(120, false, CihUiManager.this.currentForm);
            }

            /* synthetic */ void lambda$run$0$com-b3g-ui-CihUiManager$45$1() {
                CihUiManager.this.NavigateToPageById(122);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }

    class 46 implements SelectionListener {
        final /* synthetic */ Container val$cntGloableHeader;
        final /* synthetic */ Component val$pComponent;
        final /* synthetic */ Component val$pComponent2;
        final /* synthetic */ int val$pPageID;
        final /* synthetic */ Component val$pParentCnt;
        final /* synthetic */ Tabs val$tab;
        final /* synthetic */ int val$totalContainers;

        46(int i, Container container, Tabs tabs, int i2, Component component, Component component2, Component component3) {
            this.val$totalContainers = i;
            this.val$cntGloableHeader = container;
            this.val$tab = tabs;
            this.val$pPageID = i2;
            this.val$pComponent = component;
            this.val$pParentCnt = component2;
            this.val$pComponent2 = component3;
        }

        public void selectionChanged(int i, int i2) {
            if (this.val$totalContainers == 1 && CihMBanking.sesPMTR.HaveEpargne) {
                CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).removeAll();
            } else {
                for (int i3 = 0; i3 < CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentCount(); i3++) {
                    if (i == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    } else if (i2 == i3) {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(true);
                    } else {
                        CihUiManager.this.stateMachine.findCntHeaderPagerV2(this.val$cntGloableHeader).getComponentAt(i3).setEnabled(false);
                    }
                }
            }
            this.val$tab.getParent().getParent().revalidate();
            if (this.val$pPageID == 66) {
                Beneficiary GetCurrentBeneficiary = CihUiManager.this.GetCurrentBeneficiary((Container) this.val$tab.getTabComponentAt(i2));
                if (GetCurrentBeneficiary.statusActivation.equals("0")) {
                    ((Label) this.val$pComponent).setText("Désactiver");
                    ((Button) this.val$pParentCnt).setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "Desactivate_W.png"));
                } else {
                    ((Label) this.val$pComponent).setText("Activer");
                    ((Button) this.val$pParentCnt).setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "Activate_W.png"));
                }
                if (!GetCurrentBeneficiary.isSystem.equals("0")) {
                    ((Button) this.val$pComponent2).setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "Delete.png"));
                } else {
                    ((Button) this.val$pComponent2).setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "Delete_W.png"));
                }
            }
            if (this.val$pPageID == 23) {
                if (CihUiManager.this.GetCurrentAccount((Container) this.val$tab.getTabComponentAt(i2)).codeOffre.equals("CPTCQJ11")) {
                    ((Container) this.val$pComponent).setVisible(false);
                    ((Container) this.val$pComponent).revalidate();
                } else {
                    ((Container) this.val$pComponent).setVisible(true);
                    ((Container) this.val$pComponent).revalidate();
                }
            }
            if (this.val$pPageID == 17) {
                Container container = new Container(new BoxLayout(2));
                container.setUIID("g_cntBorderV2");
                Container container2 = new Container();
                Beneficiary GetCurrentBeneficiary2 = CihUiManager.this.GetCurrentBeneficiary((Container) this.val$tab.getTabComponentAt(i2));
                B3GCheckBox b3GCheckBox = new B3GCheckBox("Notification");
                b3GCheckBox.setUIID("g_lblNotif");
                b3GCheckBox.addItem("Notifier le bénéficiaire");
                b3GCheckBox.getItem("Notifier le bénéficiaire").addActionListener(new 1(container2, container));
                String str = GetCurrentBeneficiary2.isNotif;
                String str2 = GetCurrentBeneficiary2.typeBenef;
                if (str.equals("0") && str2.equals("300014")) {
                    ((Container) this.val$pComponent).removeAll();
                    ((Container) this.val$pComponent).addComponent(b3GCheckBox.GetContainer());
                    b3GCheckBox.getItem("Notifier le bénéficiaire").setSelected(false);
                    ((Container) this.val$pComponent).addComponent(container2);
                    ((TextField) ((Container) ((Container) this.val$pComponent2).getComponentAt(1)).getComponentAt(0)).setText(GetCurrentBeneficiary2.phoneNumber);
                    CihUiManager.this.isNotif = false;
                    CihMBanking.sesPMTR.setIsNotif(CihUiManager.this.isNotif);
                    ((Container) this.val$pComponent).revalidate();
                } else if (GetCurrentBeneficiary2.typeBenef.equals("300014")) {
                    ((Container) this.val$pComponent).removeAll();
                }
            }
            if (this.val$pPageID == 1000) {
                SessionParameter sessionParameter = CihMBanking.sesPMTR;
                SessionParameter.accountToDebitEShop = CihUiManager.this.GetCurrentIssuarAccount((Container) this.val$tab.getTabComponentAt(i2));
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder("++++++++++++++ accountToDebitEShop RIB: ");
                SessionParameter sessionParameter2 = CihMBanking.sesPMTR;
                printStream.println(sb.append(SessionParameter.accountToDebitEShop.rib).toString());
                PrintStream printStream2 = System.out;
                StringBuilder sb2 = new StringBuilder("++++++++++++++ accountToDebitEShop ACCOUNT NUM: ");
                SessionParameter sessionParameter3 = CihMBanking.sesPMTR;
                printStream2.println(sb2.append(SessionParameter.accountToDebitEShop.accountNumber).toString());
            }
        }

        class 1 implements ActionListener {
            final /* synthetic */ Container val$borderCnt;
            final /* synthetic */ Container val$emptyNotifCnt;

            1(Container container, Container container2) {
                this.val$emptyNotifCnt = container;
                this.val$borderCnt = container2;
            }

            public void actionPerformed(ActionEvent actionEvent) {
                if (!CihUiManager.this.isNotif) {
                    ((Container) 46.this.val$pComponent).replace(this.val$emptyNotifCnt, (Container) 46.this.val$pComponent2, (Transition) null);
                    ((Container) 46.this.val$pComponent).addComponent(this.val$borderCnt);
                    ((Container) 46.this.val$pComponent).revalidate();
                    CihUiManager.this.isNotif = true;
                    CihMBanking.sesPMTR.setIsNotif(CihUiManager.this.isNotif);
                    return;
                }
                ((Container) 46.this.val$pComponent).replace((Container) 46.this.val$pComponent2, this.val$emptyNotifCnt, (Transition) null);
                ((Container) 46.this.val$pComponent).removeComponent(this.val$borderCnt);
                ((Container) 46.this.val$pComponent).revalidate();
                CihUiManager.this.isNotif = false;
                CihMBanking.sesPMTR.setIsNotif(CihUiManager.this.isNotif);
            }
        }
    }

    private String StatutOvpExc(String str) {
        str.hashCode();
        return !str.equals("02") ? "Rejeté" : "Executé";
    }

    private Container DrawHeaderTableLayout_GroupBy(String str, String str2, String str3) {
        Container container = new Container();
        container.setUIID("CntBlueHeader2");
        TableLayout tableLayout = new TableLayout(1, 1);
        container.setLayout(tableLayout);
        Label label = new Label(str);
        if (str.equals("Executé")) {
            label.setUIID("g_lblHeaderTable_R_Low_V23");
        } else {
            label.setUIID("g_lblHeaderTable_R_Low_V23_Red");
        }
        TableLayout.Constraint createConstraint = tableLayout.createConstraint(0, 0);
        createConstraint.setWidthPercentage(100);
        createConstraint.setHorizontalAlign(1);
        container.addComponent(createConstraint, label);
        return container;
    }
}
