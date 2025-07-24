package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.tools.DataTools;
import com.b3g.tools.NativeCall;
import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizard;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.Step;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.DefaultLookAndFeel;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class PreLoginPage extends B3GPage {
    public static int SlideTextColor = 14691;
    public static byte[] b = {1, 1, 1, 1};
    public static int current_step_id = 0;
    static TouchIDNativeCall pTouchIDNativeCall;
    private String RadicalSvd;
    int box_width;
    public int green = 2190709;
    private ActionListener listener;
    B3GWizard menuWizard;
    private Step s1;
    private Step s2;
    private Step s3;
    private Container sessCnt;
    int step1_height;
    private TableLayout tl_login;

    static /* synthetic */ ActionListener access$000(PreLoginPage preLoginPage) {
        return preLoginPage.listener;
    }

    static /* synthetic */ Step access$100(PreLoginPage preLoginPage) {
        return preLoginPage.s1;
    }

    static /* synthetic */ Container access$200(PreLoginPage preLoginPage) {
        return preLoginPage.sessCnt;
    }

    public PreLoginPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        System.out.println(this.thisContainer);
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
        TableLayout tableLayout = new TableLayout(7, 1);
        Container container = new Container(tableLayout);
        container.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(10), new Container(new FlowLayout())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(45), drawWelcomeSection()).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(43), drawServicesSection()).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(2), new Container(new FlowLayout()));
        this.sessCnt = container;
        this.thisContainer.add(this.sessCnt);
        this.listener = new 1();
        CihMBanking.sesPMTR.setSessionSavedContainer(this.sessCnt);
        CihMBanking.sesPMTR.setPreloginStep(1);
        CihMBanking.sesPMTR.setBtnListener(this.listener);
        this.uiManager.currentForm.setBackCommand(new 2("Back"));
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PreLoginPage.this.Previous();
        }
    }

    class 2 extends Command {
        2(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PreLoginPage.this.Previous();
        }
    }

    private Container drawWelcomeSection() {
        Container container = new Container(new GridLayout(1, 1));
        Label label = new Label("Bienvenue");
        label.getAllStyles().setFgColor(CIHStyles.colorWhite);
        label.getAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(5.0f), 1));
        Button button = new Button(this.uiManager.ressource.getImage("client-white-48.png"));
        button.addActionListener(new 3());
        Button button2 = new Button(this.uiManager.ressource.getImage("handshake-white-48.png"));
        button2.addActionListener(new 4());
        TableLayout tableLayout = new TableLayout(2, 1);
        Container container2 = new Container(tableLayout);
        container2.add(tableLayout.createConstraint().heightPercentage(20).widthPercentage(100), label).add(tableLayout.createConstraint().heightPercentage(80).widthPercentage(100), BoxLayout.encloseYCenter(drawTransparentBox(setButtonStyle(button, "Je me connecte"), Integer.valueOf(CIHStyles.colorBlack), 100, 0.0f, 4.0f), drawTransparentBox(setButtonStyle(button2, "Je deviens client"), Integer.valueOf(CIHStyles.colorBlack), 100, 0.0f, 4.0f)));
        container2.getAllStyles().setPaddingUnit(b);
        container2.getAllStyles().setPadding(0.0f, 0.0f, 4.0f, 4.0f);
        container.add(container2);
        return container;
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.setSessionSavedContainer(null);
            CihMBanking.sesPMTR.setFromMenu(false);
            CihMBanking.sesPMTR.setIsFirst(false);
            if (PreLoginPage.this.uiManager.btnBackOffline.getListeners() != null) {
                PreLoginPage.this.uiManager.btnBackOffline.getListeners().clear();
            }
            PreLoginPage.this.uiManager.NavigateToPageByIdOffligne(190);
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ((NativeCall) NativeLookup.create(NativeCall.class)).callEERActivity();
        }
    }

    private Container drawServicesSection() {
        Container container = new Container(new GridLayout(1, 1));
        Label label = new Label("Nos services");
        label.getAllStyles().setFgColor(CIHStyles.colorWhite);
        label.getAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(3.0f), 1));
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
        container2.getAllStyles().setMarginUnit(b);
        container2.getAllStyles().setMargin(0.0f, 0.0f, 4.0f, 4.0f);
        container.add(container2);
        return container;
    }

    private void showCustomKeyboard(Button button) {
        button.getAbsoluteX();
        button.getAbsoluteY();
        button.getHeight();
    }

    public Container getTabContainer() {
        Container container = new Container(new BorderLayout());
        Container container2 = new Container();
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(1, 1, 1, 1);
        container2.getAllStyles().setMarginUnit(1);
        container2.getAllStyles().setMargin(1, 1, 6, 0);
        container.removeAll();
        Tabs tabs = new Tabs();
        tabs.hideTabs();
        tabs.setUIID("Container");
        Image image = this.uiManager.ressource.getImage("slider_arrow.png");
        Image image2 = this.uiManager.ressource.getImage("slider_arrow_w.png");
        ((DefaultLookAndFeel) UIManager.getInstance().getLookAndFeel()).setRadioButtonImages(image2, image, image2, image);
        RadioButton[] radioButtonArr = new RadioButton[6];
        ButtonGroup buttonGroup = new ButtonGroup();
        getTabsContainer(radioButtonArr, buttonGroup, tabs, container2, 0, 2, "Pourquoi avoir 3 cartes ?", "On est en 2019", "", this.uiManager.ressource.getImage("V2-4.png"));
        getTabsContainer(radioButtonArr, buttonGroup, tabs, container2, 1, 3, "Je perds ma carte,", "je bloque.", "je la retrouve je dèbloque.", this.uiManager.ressource.getImage("V2-3.png"));
        getTabsContainer(radioButtonArr, buttonGroup, tabs, container2, 2, 3, "Vous souhaitez augmenter", "le plafond de votre carte ?", "Le diminuer ?", this.uiManager.ressource.getImage("V2-2.png"));
        getTabsContainer(radioButtonArr, buttonGroup, tabs, container2, 3, 3, "CIH Express", "Retirer de l'argent sans ma", "carte ?", this.uiManager.ressource.getImage("V2-1.png"));
        getTabsContainer(radioButtonArr, buttonGroup, tabs, container2, 4, 2, "Transfert ètudiant", "à l'etranger", "", this.uiManager.ressource.getImage("V2-6.png"));
        getTabsContainer(radioButtonArr, buttonGroup, tabs, container2, 5, 3, "Changer d'agence", "depuis mon smartphone", "C'est simple", this.uiManager.ressource.getImage("V2-5.png"));
        container.add("Center", tabs);
        container.add("South", container2);
        tabs.addSelectionListener(new 5(buttonGroup));
        return container;
    }

    class 5 implements SelectionListener {
        final /* synthetic */ ButtonGroup val$BG;

        5(ButtonGroup buttonGroup) {
            this.val$BG = buttonGroup;
        }

        public void selectionChanged(int i, int i2) {
            this.val$BG.setSelected(i2);
        }
    }

    public Container getTabsContainer(RadioButton[] radioButtonArr, ButtonGroup buttonGroup, Tabs tabs, Container container, int i, int i2, String str, String str2, String str3, Image image) {
        Container container2 = new Container(new BorderLayout());
        FlowLayout flowLayout = new FlowLayout(5, 4);
        flowLayout.setFillRows(true);
        Container container3 = new Container(flowLayout);
        Style allStyles = container3.getAllStyles();
        allStyles.setPaddingUnit(1);
        allStyles.setBgImage(image.scaled(image.getWidth() * 4, image.getHeight() * 4));
        allStyles.setBackgroundType((byte) 24);
        container2.add("Center", container3);
        if (i2 == 2) {
            container2.add("South", getCntTextSlide(str, str2));
        } else {
            container2.add("South", getCntTextSlide(str, str2, str3));
        }
        RadioButton radioButton = new RadioButton();
        radioButton.setUIID("Container");
        radioButton.getAllStyles().setPaddingUnit(1);
        radioButton.getAllStyles().setPadding(0.5f, 0.5f, 0.5f, 0.5f);
        tabs.addTab("Tab", container2);
        buttonGroup.add(radioButton);
        radioButton.setName("rdb" + i);
        if (i == 0) {
            radioButton.setSelected(true);
        }
        radioButton.setEnabled(false);
        container.add(radioButton);
        return container2;
    }

    public Container getCntTextSlide(String str, String str2) {
        Container container = new Container(BoxLayout.y());
        Label label = new Label(str);
        label.setUIID("txtExoLarge");
        Label label2 = new Label(str2);
        label2.setUIID("txtExoLargeMedium");
        container.add(label);
        container.add(label2);
        return container;
    }

    public Container getCntTextSlide(String str, String str2, String str3) {
        Container container = new Container(BoxLayout.y());
        Label label = new Label(str);
        label.setUIID("txtExoLarge");
        Label label2 = new Label(str2);
        label2.setUIID("txtExoLarge");
        Label label3 = new Label(str3);
        label3.setUIID("txtExoLargeMedium");
        container.add(label);
        container.add(label2);
        container.add(label3);
        return container;
    }

    public static void ShowLangDialog(StateMachine stateMachine) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources.open("/cihv3.res");
            Container ChoixdeLangue = ChoixdeLangue(dialog);
            ChoixdeLangue.setPreferredH(Display.getInstance().getDisplayHeight());
            ChoixdeLangue.setPreferredW(Display.getInstance().getDisplayWidth());
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
            dialog.addComponent("Center", ChoixdeLangue);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    public static Container ChoixdeLangue(Dialog dialog) {
        new BorderLayout();
        Container container = new Container(new FlowLayout(4, 4));
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("margin_1_1_1_1");
        container.setUIID("gl_GloabalContainerV2NoPadMargin");
        Button button = new Button("العربية");
        Button button2 = new Button("Français");
        Button button3 = new Button("Anglais");
        Button button4 = new Button("Español");
        button.setUIID("sendBtnNewLangueAr");
        button2.setUIID("sendBtnNewLangueFr");
        button3.setUIID("sendBtnNewLangueEn");
        button4.setUIID("sendBtnNewLangueEs");
        container2.add(button);
        container2.add(button2);
        container2.add(button3);
        container2.add(button4);
        button.addActionListener(new PreLoginPage$$ExternalSyntheticLambda0(dialog));
        button2.addActionListener(new PreLoginPage$$ExternalSyntheticLambda1(dialog));
        button3.addActionListener(new PreLoginPage$$ExternalSyntheticLambda2(dialog));
        button4.addActionListener(new PreLoginPage$$ExternalSyntheticLambda3(dialog));
        container2.setPreferredW(Display.getInstance().getDisplayWidth());
        container.add(container2);
        return container;
    }

    static /* synthetic */ void lambda$ChoixdeLangue$0(Dialog dialog, ActionEvent actionEvent) {
        Preferences.set("CihMobileLang", "العربية");
        CihMBanking.setLang("العربية");
        dialog.dispose();
    }

    static /* synthetic */ void lambda$ChoixdeLangue$1(Dialog dialog, ActionEvent actionEvent) {
        Preferences.set("CihMobileLang", "Français");
        CihMBanking.setLang("Français");
        dialog.dispose();
    }

    static /* synthetic */ void lambda$ChoixdeLangue$2(Dialog dialog, ActionEvent actionEvent) {
        Preferences.set("CihMobileLang", "English");
        CihMBanking.setLang("English");
        dialog.dispose();
    }

    static /* synthetic */ void lambda$ChoixdeLangue$3(Dialog dialog, ActionEvent actionEvent) {
        Preferences.set("CihMobileLang", "Español");
        CihMBanking.setLang("Español");
        dialog.dispose();
    }

    private Container getStep01() {
        Button button = new Button("  Mes projets immobiliers");
        new Button("  Mes assurances").addActionListener(new 6());
        Button button2 = new Button("  Agences CIH");
        button2.setUIID("Container");
        button2.addActionListener(new 7());
        new Button("  CIH partners").addActionListener(new 8());
        button.addActionListener(new 9());
        Container encloseYCenter = BoxLayout.encloseYCenter(new Component[0]);
        this.step1_height = encloseYCenter.getHeight();
        encloseYCenter.setScrollableY(true);
        encloseYCenter.setScrollVisible(false);
        return encloseYCenter;
    }

    class 6 implements ActionListener {
        6() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PreLoginPage.this.uiManager.NavigateToPageByIdOffligne(168);
        }
    }

    class 7 implements ActionListener {
        7() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PreLoginPage.this.uiManager.NavigateToPageByIdOffligne(83);
        }
    }

    class 8 implements ActionListener {
        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.urlAvantageWebView = "https://avantagescartescih.ma/";
            PreLoginPage.this.uiManager.NavigateToPageByIdOffligne(169);
        }
    }

    class 9 implements ActionListener {
        9() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PreLoginPage.this.uiManager.btnBackOffline.setVisible(true);
            PreLoginPage.this.uiManager.btnBackOffline.addActionListener(PreLoginPage.access$000(PreLoginPage.this));
            PreLoginPage.this.menuWizard.goToNextStep(PreLoginPage.access$100(PreLoginPage.this), "", 16777215, 0);
            CihMBanking.sesPMTR.setPreloginStep(2);
        }
    }

    private Container getStep1() {
        Container container = new Container(new GridLayout(1, 1));
        Container container2 = new Container();
        Label label = new Label("Nos services");
        label.getAllStyles().setFgColor(CIHStyles.colorWhite);
        label.getAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(3.0f), 1));
        label.getAllStyles().setPaddingUnit(b);
        label.getAllStyles().setPadding(0.0f, 1.0f, 0.0f, 0.0f);
        Button button = new Button(this.uiManager.ressource.getImage("large-building-white.png"));
        Button button2 = new Button(this.uiManager.ressource.getImage("security-white.png"));
        Button button3 = new Button(this.uiManager.ressource.getImage("map-pin-white.png"));
        Button button4 = new Button(this.uiManager.ressource.getImage("meeting_room.png"));
        button.addActionListener(new 10());
        button2.addActionListener(new 11());
        button3.addActionListener(new 12());
        button4.addActionListener(new 13());
        Container gridButtonStyle = setGridButtonStyle(button, DataTools.getPortailImob());
        Container gridButtonStyle2 = setGridButtonStyle(button2, DataTools.getSolutionsAssurances());
        Container gridButtonStyle3 = setGridButtonStyle(button3, DataTools.getAgences());
        Container gridButtonStyle4 = setGridButtonStyle(button4, DataTools.getAvantages());
        gridButtonStyle.getAllStyles().setMarginUnit(b);
        gridButtonStyle.getAllStyles().setMargin(0, 0, 0, 2);
        gridButtonStyle2.getAllStyles().setMarginUnit(b);
        gridButtonStyle2.getAllStyles().setMargin(0, 0, 2, 0);
        gridButtonStyle3.getAllStyles().setMarginUnit(b);
        gridButtonStyle3.getAllStyles().setMargin(0.0f, 0.0f, 0.0f, 2.0f);
        gridButtonStyle4.getAllStyles().setMarginUnit(b);
        gridButtonStyle4.getAllStyles().setMargin(0.0f, 0.0f, 2.0f, 0.0f);
        TableLayout tableLayout = new TableLayout(3, 1);
        container2.setLayout(tableLayout);
        container2.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(10), label).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(43), GridLayout.encloseIn(2, gridButtonStyle, gridButtonStyle2)).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(4), new Container(BoxLayout.x())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(43), GridLayout.encloseIn(2, gridButtonStyle3, gridButtonStyle4));
        container.add(container2);
        return container;
    }

    class 10 implements ActionListener {
        10() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PreLoginPage.this.uiManager.btnBackOffline.setVisible(true);
            PreLoginPage.this.uiManager.btnBackOffline.addActionListener(PreLoginPage.access$000(PreLoginPage.this));
            PreLoginPage.this.menuWizard.goToNextStep(PreLoginPage.access$100(PreLoginPage.this), "", 16777215, 0);
        }
    }

    class 11 implements ActionListener {
        11() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PreLoginPage.this.uiManager.NavigateToPageByIdOffligne(168);
        }
    }

    class 12 implements ActionListener {
        12() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PreLoginPage.this.uiManager.NavigateToPageByIdOffligne(83);
        }
    }

    class 13 implements ActionListener {
        13() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.urlAvantageWebView = "https://avantagescartescih.ma/";
            PreLoginPage.this.uiManager.NavigateToPageByIdOffligne(169);
        }
    }

    private Container getStep2() {
        Container container = new Container(new GridLayout(1, 1));
        Container container2 = new Container();
        Button button = new Button(this.uiManager.ressource.getImage("icons-search.png"));
        button.addActionListener(new 14());
        Button button2 = new Button(this.uiManager.ressource.getImage("icons-sell.png"));
        button2.addActionListener(new 15());
        Button button3 = new Button(this.uiManager.ressource.getImage("Simulation-white.png"));
        button3.addActionListener(new 16());
        Container gridButtonStyle = setGridButtonStyle(button, DataTools.getChercheLogement());
        Container gridButtonStyle2 = setGridButtonStyle(button2, DataTools.getVendLogement());
        Container gridButtonStyle3 = setGridButtonStyle(button3, "Je fais une simulation");
        gridButtonStyle.getAllStyles().setMarginUnit(b);
        gridButtonStyle.getAllStyles().setMargin(0, 0, 0, 2);
        gridButtonStyle2.getAllStyles().setMarginUnit(b);
        gridButtonStyle2.getAllStyles().setMargin(0, 0, 2, 0);
        gridButtonStyle3.getAllStyles().setMarginUnit(b);
        gridButtonStyle3.getAllStyles().setMargin(0.0f, 0.0f, 0.0f, 2.0f);
        TableLayout tableLayout = new TableLayout(3, 1);
        container2.setLayout(tableLayout);
        container2.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(28), new Container(BoxLayout.x())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(45), GridLayout.encloseIn(2, gridButtonStyle, gridButtonStyle2)).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(29), new Container(BoxLayout.x())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(45), GridLayout.encloseIn(2, gridButtonStyle3, new Container()));
        container.add(container2);
        container.setPreferredH(this.step1_height);
        return container;
    }

    class 14 implements ActionListener {
        14() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.setSessionSavedContainer(PreLoginPage.access$200(PreLoginPage.this));
            CihMBanking.sesPMTR.urlAvantageWebView = "https://www.yakeey.com/catalog-view/properties?utm_source=cih&referralOrigin=cih";
            PreLoginPage.this.uiManager.NavigateToPageByIdOffligne(169);
        }
    }

    class 15 implements ActionListener {
        15() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.setSessionSavedContainer(PreLoginPage.access$200(PreLoginPage.this));
            CihMBanking.sesPMTR.urlAvantageWebView = "https://www.yakeey.com/sell?utm_source=cih&referralOrigin=cih";
            PreLoginPage.this.uiManager.NavigateToPageByIdOffligne(169);
        }
    }

    class 16 implements ActionListener {
        16() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ((NativeCall) NativeLookup.create(NativeCall.class)).callYakeey();
        }
    }

    public void Previous() {
        if (this.menuWizard.currentStepId != 0) {
            this.menuWizard.goToPreviousStep(this.s2, "", 16777215, 16777215);
            this.uiManager.btnBackOffline.setVisible(false);
            if (this.uiManager.btnBackOffline.getListeners() != null) {
                this.uiManager.btnBackOffline.getListeners().clear();
                return;
            }
            return;
        }
        Display.getInstance().exitApplication();
    }

    public static Container setButtonStyle(Button button, String str) {
        Container container = new Container(new BorderLayout());
        button.setUIID("Container");
        Label label = new Label(str);
        label.getAllStyles().setPaddingUnit(b);
        label.getAllStyles().setPadding(0.0f, 0.0f, 2.0f, 2.0f);
        label.getAllStyles().setMarginUnit(b);
        label.getAllStyles().setMargin(0.0f, 0.0f, 2.0f, 0.0f);
        label.getAllStyles().setAlignment(4);
        label.getAllStyles().setFgColor(CIHStyles.colorWhite);
        label.getAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(2.5f), 1));
        label.getAllStyles().setBgTransparency(0);
        container.getAllStyles().setPaddingUnit(b);
        container.getAllStyles().setPadding(3.0f, 3.0f, 2.0f, 2.0f);
        container.add("Center", FlowLayout.encloseMiddle(button, label));
        container.setLeadComponent(button);
        return container;
    }

    public static Container setGridButtonStyle(Button button, String str) {
        Container container = new Container(new BorderLayout());
        button.setUIID("Container");
        SpanLabel spanLabel = new SpanLabel();
        spanLabel.setText(str);
        spanLabel.getAllStyles().setPaddingUnit(b);
        spanLabel.getAllStyles().setPadding(0.0f, 0.0f, 2.0f, 2.0f);
        spanLabel.getTextAllStyles().setAlignment(4);
        spanLabel.getTextAllStyles().setFgColor(CIHStyles.colorWhite);
        spanLabel.getTextAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(2.5f), 1));
        spanLabel.getAllStyles().setMarginUnit(b);
        spanLabel.getAllStyles().setMargin(2.0f, 0.0f, 0.0f, 0.0f);
        spanLabel.getTextAllStyles().setBgTransparency(0);
        container.getAllStyles().setPaddingUnit(b);
        container.getAllStyles().setPadding(2.0f, 2.0f, 2.0f, 2.0f);
        container.getAllStyles().setBgColor(CIHStyles.colorWhite);
        container.getAllStyles().setBgTransparency(0);
        Container encloseYCenter = BoxLayout.encloseYCenter(FlowLayout.encloseCenterMiddle(button), spanLabel);
        encloseYCenter.setScrollableY(false);
        encloseYCenter.setScrollVisible(false);
        encloseYCenter.getAllStyles().setMarginUnit(b);
        encloseYCenter.getAllStyles().setMargin(1.2f, 0.0f, 0.0f, 0.0f);
        container.add("Center", encloseYCenter);
        container.setLeadComponent(button);
        return drawTransparentBox(container, Integer.valueOf(CIHStyles.colorBlack), 100, 1.0f, 3.0f);
    }

    public static Container setRoundedFilledButtonStyle(Button button, Image image, int i) {
        Container container = new Container(new GridLayout(1, 1));
        Style allStyles = button.getAllStyles();
        allStyles.setPaddingUnit(b);
        allStyles.setPadding(2.0f, 2.0f, 1.0f, 1.0f);
        allStyles.setFont(CIHStyles.create_font("Poppins-Regular", "native:MainBold", Float.valueOf(3.0f), 1));
        allStyles.setBorder(RoundRectBorder.create().cornerRadius(2.0f));
        allStyles.setAlignment(4);
        allStyles.setBgColor(i);
        allStyles.setFgColor(16777215);
        allStyles.setBgTransparency(255);
        if (image != null) {
            button.setIcon(image);
            button.getAllStyles().setMarginUnit(b);
            button.getAllStyles().setMargin(0.0f, 0.0f, 1.0f, 0.0f);
        }
        container.add(button);
        container.setLeadComponent(button);
        return container;
    }

    public static Container setLinkButtonStyle(Button button, Integer num, boolean z) {
        Container container = new Container(new FlowLayout(4, 4));
        Style allStyles = button.getAllStyles();
        allStyles.setPaddingUnit(b);
        allStyles.setPadding(2.0f, 2.0f, 1.0f, 1.0f);
        allStyles.setUnderline(z);
        allStyles.setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(3.5f), 1));
        allStyles.setFgColor(num == null ? CIHStyles.colorWhite : num.intValue());
        container.add(button);
        container.setLeadComponent(button);
        return container;
    }

    public static Container drawTransparentBox(Container container, Integer num, Integer num2, float f, float f2) {
        container.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(2.0f).stroke(new Stroke(2.5f, 2, 0, 4.0f)).strokeColor(16777215).strokeOpacity(0));
        container.getAllStyles().setBgColor(num.intValue());
        container.getAllStyles().setBgTransparency(num2.intValue());
        container.getAllStyles().setMarginUnit(b);
        container.getAllStyles().setPaddingUnit(b);
        container.getAllStyles().setMargin(0.0f, 3.0f, 0.0f, 0.0f);
        container.getAllStyles().setPadding(f, f, f2, f2);
        return container;
    }

    public static Container drawFingerprintIcon(Button button) {
        Container container = new Container(new GridLayout(1, 1));
        button.setUIID("Container");
        container.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(2.0f));
        container.getAllStyles().setBgColor(CIHStyles.colorOrange);
        container.getAllStyles().setBgTransparency(255);
        container.getAllStyles().setMarginUnit(b);
        container.getAllStyles().setPaddingUnit(b);
        container.getAllStyles().setMargin(0, 0, 0, 8);
        container.getAllStyles().setPadding(1.0f, 1.0f, 1.0f, 1.0f);
        container.add(FlowLayout.encloseCenterMiddle(button));
        container.setLeadComponent(button);
        return container;
    }
}
