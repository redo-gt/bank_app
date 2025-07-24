package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.tools.DataTools;
import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.B3GRadioWithIcon;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordion;
import com.b3g.ui.page.cih.Module.PopUpsManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.fingerprint.Fingerprint;
import com.codename1.io.Preferences;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.SuccessCallback;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ParameteresPage extends B3GPage {
    Vector lst;
    PopUpsManager pPopUpsManager;
    TouchIDNativeCall pTouchIDNativeCall;

    static /* synthetic */ int access$000(ParameteresPage parameteresPage, String str) {
        return parameteresPage.getIndiceOper(str);
    }

    public ParameteresPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        Container container = new Container(BoxLayout.y());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        container.add(drawMultiButtonHeader("Profil", "profil_icon.png"));
        arrayList.add(drawMtcHeader("Données biométrique et sécurité", "PMTRsecurity.png"));
        arrayList.add(drawMtcHeader("Affichage", "affichrage.png"));
        arrayList.add(drawMtcHeader("Mes langues", "PMTRlangues.png"));
        arrayList.add(drawMtcHeader("Mes opérateurs", "PMTRoperateur.png"));
        arrayList2.add(BiometriqueCnt());
        arrayList2.add(ModeAffichage());
        arrayList2.add(LanguageCnt());
        arrayList2.add(OperatorCnt());
        container.add(drawAccordion(new B3gAccordion(), arrayList, arrayList2));
        container.add(drawMtcHeader("Mes appareils", "PMTRdevice.png"));
        container.add(drawMtcHeader("CIH M3AK", "Whatsapp.png"));
        this.thisContainer.add(container);
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public Container drawMtcHeader(String str, String str2) {
        Container container = new Container(new BoxLayout(2));
        container.setName(str);
        container.setUIID("gl_GloabalContainerV2NoPadMargin");
        container.setScrollVisible(false);
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container2 = new Container();
        container2.setLayout(tableLayout);
        container2.setUIID("mtc_CntTableLayoutN");
        container2.setFocusable(false);
        container2.setScrollable(false);
        Button button = new Button(str);
        button.setUIID("mtc_homemenuItemText");
        button.setScrollVisible(false);
        button.setFocusable(false);
        button.setName(str);
        if (container.getName().equals("Profil")) {
            MultiButton multiButton = new MultiButton();
            multiButton.setUIID("mtc_homemenuItemText");
            multiButton.setScrollVisible(false);
            multiButton.setFocusable(false);
            multiButton.setTextLine1(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().clientSex + ". " + CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom);
            multiButton.setTextLine2("Accéder à mon profil");
            button.addActionListener(new 1());
        }
        if (str.equals("Mes appareils") || str.equals("Appareils") || str.equals("الأجهزة") || str.equals("Devices") || str.equals("Dispositivos")) {
            button.addActionListener(new 2());
        }
        if (str.equals("CIH M3AK")) {
            button.addActionListener(new 3());
        }
        Button button2 = new Button();
        button2.setUIID("crd_btnCardImage");
        button2.setIcon(this.uiManager.ressource.getImage(""));
        button2.setScrollVisible(false);
        button2.setFocusable(false);
        Button button3 = new Button();
        button3.setUIID("");
        button3.setIcon(this.uiManager.ressource.getImage(str2));
        button3.setScrollVisible(false);
        button3.setFocusable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(15);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(75);
        TableLayout.Constraint createConstraint3 = tableLayout.createConstraint();
        createConstraint3.setWidthPercentage(10);
        new Label();
        container2.addComponent(createConstraint, button3);
        container2.addComponent(createConstraint2, button);
        if (!str.equals("Mes appareils") && !str.equals("Appareils") && !str.equals("الأجهزة") && !str.equals("Devices") && !str.equals("Dispositivos")) {
            container2.addComponent(createConstraint3, button2);
        }
        if (str.equals("Mes appareils") || str.equals("Appareils") || str.equals("الأجهزة") || str.equals("Devices") || str.equals("Dispositivos")) {
            Component container3 = new Container(new BoxLayout(2));
            container3.setUIID("bf_cntBeneficarySeparator");
            container3.setScrollVisible(false);
            container.addComponent(container3);
        }
        container.addComponent(container2);
        return container;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ParameteresPage.this.uiManager.NavigateToPageById(62);
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ParameteresPage.this.uiManager.NavigateToPageById(103);
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ParameteresPage.this.uiManager.NavigateToPageById(108);
        }
    }

    public Container drawMultiButtonHeader(String str, String str2) {
        Container container = new Container(new BoxLayout(2));
        container.setName(str);
        container.setUIID("gl_GloabalContainerV2NoPadMargin");
        container.setScrollVisible(false);
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container2 = new Container();
        container2.setLayout(tableLayout);
        container2.setUIID("mtc_CntTableLayoutN");
        container2.setFocusable(false);
        container2.setScrollable(false);
        Container container3 = new Container();
        container3.setLayout(new BoxLayout(2));
        Button button = new Button();
        button.setScrollVisible(false);
        button.setFocusable(false);
        Button button2 = new Button();
        button2.setScrollVisible(false);
        button2.setFocusable(false);
        button.setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().clientSex + " " + CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom);
        button2.setText("Accéder à mon profil");
        button.setUIID("mtc_homemenuItemText");
        button2.setUIID("ac_lblitemDetailValueForm");
        container3.add(button).add(button2);
        container3.setLeadComponent(button);
        button.addActionListener(new 4());
        Button button3 = new Button();
        button3.setUIID("crd_btnCardImage");
        button3.setIcon(CihMBanking.detail_arrow);
        button3.setScrollVisible(false);
        button3.setFocusable(false);
        button3.getAllStyles().setMarginUnit(1);
        button3.getAllStyles().setMargin(0, 0, 1, 1);
        Button button4 = new Button();
        button4.setUIID("");
        button4.setIcon(this.uiManager.ressource.getImage(str2));
        button4.setScrollVisible(false);
        button4.setFocusable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(15);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(75);
        TableLayout.Constraint createConstraint3 = tableLayout.createConstraint();
        createConstraint3.setWidthPercentage(10);
        createConstraint3.setVerticalAlign(4);
        createConstraint3.setHorizontalAlign(3);
        container2.addComponent(createConstraint, button4);
        container2.addComponent(createConstraint2, container3);
        container2.addComponent(createConstraint3, button3);
        container.addComponent(container2);
        return container;
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ParameteresPage.this.uiManager.NavigateToPageById(62);
        }
    }

    public Container BiometriqueCnt() {
        this.pTouchIDNativeCall = (TouchIDNativeCall) NativeLookup.create(TouchIDNativeCall.class);
        Container container = new Container(new BoxLayout(2));
        TableLayout tableLayout = new TableLayout(1, 3);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(20);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(80);
        createConstraint2.setVerticalAlign(4);
        createConstraint2.setHorizontalAlign(1);
        Container container2 = new Container();
        container2.setLayout(tableLayout);
        Button button = new Button();
        button.setUIID("");
        button.setIcon(this.uiManager.ressource.getImage("PMTRpwd.png"));
        Label label = new Label("Mot de passe");
        label.setUIID("g_lblDashBoardTitle");
        container2.addComponent(createConstraint, button);
        container2.addComponent(createConstraint2, label);
        button.addActionListener(new 5());
        container2.setLeadComponent(button);
        container2.setUIID("gl_GloabalContainerV2NoPadMargin");
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(2, 2, 3, 0);
        TableLayout tableLayout2 = new TableLayout(1, 3);
        TableLayout.Constraint createConstraint3 = tableLayout2.createConstraint();
        createConstraint3.setWidthPercentage(20);
        createConstraint3.setVerticalAlign(4);
        createConstraint3.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint4 = tableLayout.createConstraint();
        createConstraint4.setWidthPercentage(80);
        createConstraint4.setVerticalAlign(4);
        createConstraint4.setHorizontalAlign(1);
        Container container3 = new Container();
        container3.setLayout(tableLayout2);
        Button button2 = new Button();
        button2.setUIID("");
        button2.setIcon(this.uiManager.ressource.getImage("PMTRfingerPrint.png"));
        Label label2 = new Label("Empreinte");
        label2.setUIID("g_lblDashBoardTitle");
        container3.addComponent(createConstraint3, button2);
        container3.addComponent(createConstraint4, label2);
        container3.setUIID("gl_GloabalContainerV2NoPadMargin");
        container3.getAllStyles().setPaddingUnit(1);
        container3.getAllStyles().setPadding(2, 2, 3, 0);
        container3.setLeadComponent(button2);
        TableLayout tableLayout3 = new TableLayout(1, 3);
        TableLayout.Constraint createConstraint5 = tableLayout2.createConstraint();
        createConstraint5.setWidthPercentage(20);
        createConstraint5.setVerticalAlign(4);
        createConstraint5.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint6 = tableLayout.createConstraint();
        createConstraint6.setWidthPercentage(80);
        createConstraint6.setVerticalAlign(4);
        createConstraint6.setHorizontalAlign(1);
        Container container4 = new Container();
        container4.setLayout(tableLayout3);
        Button button3 = new Button();
        button3.setUIID("");
        button3.setIcon(this.uiManager.ressource.getImage("PMTRFaceID.png"));
        Label label3 = new Label("Reconnaissance faciale");
        label3.setUIID("g_lblDashBoardTitle");
        container4.addComponent(createConstraint5, button3);
        container4.addComponent(createConstraint6, label3);
        container4.setUIID("gl_GloabalContainerV2NoPadMargin");
        container4.getAllStyles().setPaddingUnit(1);
        container4.getAllStyles().setPadding(2, 2, 3, 0);
        container4.setLeadComponent(button3);
        TableLayout tableLayout4 = new TableLayout(1, 3);
        TableLayout.Constraint createConstraint7 = tableLayout4.createConstraint();
        createConstraint7.setWidthPercentage(20);
        createConstraint7.setVerticalAlign(4);
        createConstraint7.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint8 = tableLayout4.createConstraint();
        createConstraint8.setWidthPercentage(80);
        createConstraint8.setVerticalAlign(4);
        createConstraint8.setHorizontalAlign(1);
        Container container5 = new Container();
        container5.setLayout(tableLayout4);
        Button button4 = new Button();
        button4.setUIID("");
        button4.setIcon(this.uiManager.ressource.getImage("Password13.png"));
        Label label4 = new Label("CIH E-Code");
        label4.setUIID("g_lblDashBoardTitle");
        container5.addComponent(createConstraint7, button4);
        container5.addComponent(createConstraint8, label4);
        container5.setUIID("gl_GloabalContainerV2NoPadMargin");
        container5.getAllStyles().setPaddingUnit(1);
        container5.getAllStyles().setPadding(2, 2, 3, 0);
        container5.setLeadComponent(button4);
        button4.addActionListener(new 6());
        container.add(container2);
        container.add(container3);
        container.add(container4);
        container.add(container5);
        if (!Display.getInstance().isSimulator()) {
            if (this.pTouchIDNativeCall.getBiometryType().equals("FACEID")) {
                container3.setHidden(true);
                label3.setText("FACE ID");
            } else if (this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
                container4.setHidden(true);
                label2.setText("TOUCH ID");
            } else if (this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                container4.setHidden(true);
                label2.setText("Données biométriques");
            } else {
                container4.setHidden(true);
                label2.setText("Données biométriques");
            }
        } else {
            container4.setHidden(true);
            label2.setText("Données biométriques");
        }
        button2.addActionListener(new 7());
        button3.addActionListener(new 8());
        return container;
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ParameteresPage.this.uiManager.NavigateToPageById(58);
        }
    }

    class 6 implements ActionListener {
        6() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
                ParameteresPage.this.uiManager.NavigateToPageById(111);
            } else {
                ParameteresPage.this.uiManager.NavigateToPageById(112);
            }
        }
    }

    class 7 implements ActionListener {
        7() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!ParameteresPage.this.pTouchIDNativeCall.isSupported() || !Fingerprint.isAvailable() || CihMBanking.sesPMTR.getIsDemo() != 0) {
                ParameteresPage parameteresPage = ParameteresPage.this;
                parameteresPage.ShowMessageTransaction(parameteresPage.uiManager.stateMachine);
            } else {
                ParameteresPage.this.uiManager.NavigateToPageById(105);
            }
        }
    }

    class 8 implements ActionListener {
        private SuccessCallback onSuccess;

        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!Fingerprint.isAvailable() || !ParameteresPage.this.pTouchIDNativeCall.isSupported() || CihMBanking.sesPMTR.getIsDemo() != 0) {
                ParameteresPage parameteresPage = ParameteresPage.this;
                parameteresPage.ShowMessageTransaction(parameteresPage.uiManager.stateMachine);
            } else {
                ParameteresPage.this.uiManager.NavigateToPageById(105);
            }
        }
    }

    public Container LanguageCnt() {
        Container container = new Container(new BoxLayout(2));
        container.setScrollableY(false);
        this.uiManager.CurrentPageId = 99;
        CihMBanking.sesPMTR.setCurrentUiManager(this.uiManager);
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "changeOperator");
        if (Settings.isNightMode()) {
            createContainer.setUIID("LoginCntTrspOffligneDark");
        } else {
            createContainer.setUIID("LoginCntTrspOffligne");
        }
        Container container2 = (Container) uIBuilder.findByName("operatorRadioCnt", createContainer);
        Button button = (Button) uIBuilder.findByName("validBtn", createContainer);
        B3GRadioWithIcon b3GRadioWithIcon = new B3GRadioWithIcon("", new BoxLayout(2));
        b3GRadioWithIcon.setUIID("TransparentContainer");
        b3GRadioWithIcon.addItem("العربية", "ma.png", "TransparentContainer");
        b3GRadioWithIcon.addItem("English", "en.png", "TransparentContainer");
        b3GRadioWithIcon.addItem("Español", "es.png", "TransparentContainer");
        b3GRadioWithIcon.addItem("Français", "fr.png", "TransparentContainer");
        b3GRadioWithIcon.setSelectedIndex(getLblLangIndice());
        button.addActionListener(new 9(b3GRadioWithIcon));
        b3GRadioWithIcon.setRadioUIID("TransparentContainer");
        Container GetContainer = b3GRadioWithIcon.GetContainer();
        GetContainer.setUIID("TransparentContainer");
        container2.add(GetContainer);
        container.add(createContainer);
        return container;
    }

    class 9 implements ActionListener {
        final /* synthetic */ B3GRadioWithIcon val$b3GRadioLg;

        9(B3GRadioWithIcon b3GRadioWithIcon) {
            this.val$b3GRadioLg = b3GRadioWithIcon;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$b3GRadioLg.isClear()) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ParameteresPage.this.uiManager.stateMachine, "Merci de choisir votre langue", null);
                return;
            }
            String GetSelectedText = this.val$b3GRadioLg.GetSelectedText();
            GetSelectedText.hashCode();
            String str = "";
            int i = 2;
            char c = 65535;
            switch (GetSelectedText.hashCode()) {
                case -2144569262:
                    if (GetSelectedText.equals("العربية")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1827230247:
                    if (GetSelectedText.equals("الفرنسية")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1575530339:
                    if (GetSelectedText.equals("Français")) {
                        c = 2;
                        break;
                    }
                    break;
                case 0:
                    if (GetSelectedText.equals("")) {
                        c = 3;
                        break;
                    }
                    break;
                case 3121:
                    if (GetSelectedText.equals("ar")) {
                        c = 4;
                        break;
                    }
                    break;
                case 3241:
                    if (GetSelectedText.equals("en")) {
                        c = 5;
                        break;
                    }
                    break;
                case 3246:
                    if (GetSelectedText.equals("es")) {
                        c = 6;
                        break;
                    }
                    break;
                case 3276:
                    if (GetSelectedText.equals("fr")) {
                        c = 7;
                        break;
                    }
                    break;
                case 60895824:
                    if (GetSelectedText.equals("English")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 63521395:
                    if (GetSelectedText.equals("Arabe")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 212156143:
                    if (GetSelectedText.equals("Español")) {
                        c = '\n';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 4:
                case '\t':
                    i = 0;
                    break;
                case 1:
                case 2:
                case 3:
                case 7:
                default:
                    i = 3;
                    break;
                case 5:
                case '\b':
                    i = 1;
                    break;
                case 6:
                case '\n':
                    break;
            }
            ServiceResponse updateProfileProcess = ParameteresPage.this.pPopUpsManager.updateProfileProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, Integer.toString(ParameteresPage.access$000(ParameteresPage.this, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator.toUpperCase())), Integer.toString(i), "2");
            if (updateProfileProcess.getStatusCode().equals("000")) {
                if (this.val$b3GRadioLg.GetSelectedText().equals("Français")) {
                    CihMBanking.sesPMTR.getSessionClient().getClient_Profil().langue = "FR";
                    str = "Français";
                } else if (this.val$b3GRadioLg.GetSelectedText().equals("العربية")) {
                    CihMBanking.sesPMTR.getSessionClient().getClient_Profil().langue = "AR";
                    str = "العربية";
                } else if (this.val$b3GRadioLg.GetSelectedText().equals("English")) {
                    CihMBanking.sesPMTR.getSessionClient().getClient_Profil().langue = "EN";
                    str = "English";
                } else if (this.val$b3GRadioLg.GetSelectedText().equals("Español")) {
                    CihMBanking.sesPMTR.getSessionClient().getClient_Profil().langue = "ES";
                    str = "Español";
                }
                Preferences.set("CihMobileLang", str);
                CihMBanking.setLang(str);
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ParameteresPage.this.uiManager.stateMachine, DataTools.FormatUnicode(updateProfileProcess.getStatusLabel()), null);
                Display.getInstance().callSerially(new 1());
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ParameteresPage.this.uiManager.stateMachine, DataTools.FormatUnicode(updateProfileProcess.getStatusLabel()), null);
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                ParameteresPage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }
    }

    public B3gAccordion drawAccordion(B3gAccordion b3gAccordion, ArrayList arrayList, ArrayList arrayList2) {
        b3gAccordion.setScrollableY(false);
        Image image = this.uiManager.ressource.getImage("blueArrowRotated.png");
        Image image2 = CihMBanking.detail_arrow;
        b3gAccordion.setArrowUIID("Regroupement_margin_0_4_0_0");
        b3gAccordion.setCloseIcon(image2);
        b3gAccordion.setOpenIcon(image);
        for (int i = 0; i < arrayList.size(); i++) {
            Container container = new Container(new BoxLayout(2));
            container.setUIID("bf_cntBeneficarySeparator");
            container.setScrollVisible(false);
            b3gAccordion.addContent((Component) arrayList.get(i), BoxLayout.encloseY((Container) arrayList2.get(i)), container);
        }
        return b3gAccordion;
    }

    public void ShowMessageTransaction(StateMachine stateMachine) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "FingerPrintPopUp");
            SpanLabel spanLabel = (SpanLabel) stateMachine.findByName("SpanLabel", createContainer);
            SpanLabel spanLabel2 = (SpanLabel) stateMachine.findByName("SpanLabel1", createContainer);
            if (this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                spanLabel.setText("Aucun Biométrie n'est enregistré sur votre appareil.");
                spanLabel2.setText("Veuillez ajouter votre Biométrie sur les paramètres du téléphone pour pouvoir utiliser cette fonctionnalité.");
            } else if (this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
                spanLabel.setText("Aucune Touch ID n'est enregistrée sur votre appareil.");
                spanLabel2.setText("Veuillez ajouter votre Touch ID sur les paramètres du téléphone pour pouvoir utiliser cette fonctionnalité.");
            } else if (this.pTouchIDNativeCall.getBiometryType().equals("FACEID")) {
                spanLabel.setText("Aucun Face ID n'est enregistré sur votre appareil.");
                spanLabel2.setText("Veuillez ajouter votre Face ID sur les paramètres du téléphone pour pouvoir utiliser cette fonctionnalité.");
            } else {
                spanLabel.setText("Aucun Biométrie n'est enregistré sur votre appareil.");
                spanLabel2.setText("Veuillez ajouter votre Biométrie sur les paramètres du téléphone pour pouvoir utiliser cette fonctionnalité.");
            }
            createContainer.revalidate();
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
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 10(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
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

    public Container OperatorCnt() {
        Container container = new Container(new BoxLayout(2));
        container.setScrollableY(false);
        this.uiManager.CurrentPageId = 99;
        CihMBanking.sesPMTR.setCurrentUiManager(this.uiManager);
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "changeOperator");
        if (Settings.isNightMode()) {
            createContainer.setUIID("LoginCntTrspOffligneDark");
        } else {
            createContainer.setUIID("LoginCntTrspOffligne");
        }
        Container container2 = (Container) uIBuilder.findByName("operatorRadioCnt", createContainer);
        Label label = (Label) uIBuilder.findByName("title", createContainer);
        SpanLabel spanLabel = (SpanLabel) uIBuilder.findByName("messageLbl", createContainer);
        label.setText("Choix de l'opérateur");
        spanLabel.setText("Merci de choisir votre opérateur");
        Button button = (Button) uIBuilder.findByName("validBtn", createContainer);
        B3GRadioWithIcon b3GRadioWithIcon = new B3GRadioWithIcon("", new BoxLayout(2));
        b3GRadioWithIcon.setUIID("TransparentContainer");
        b3GRadioWithIcon.addItem("Maroc Télécom", null, "TransparentContainer");
        b3GRadioWithIcon.addItem("ORANGE Maroc", null, "TransparentContainer");
        b3GRadioWithIcon.addItem("INWI", null, "TransparentContainer");
        b3GRadioWithIcon.addItem("Numéro étranger", null, "TransparentContainer");
        b3GRadioWithIcon.setSelectedIndex(getIndiceOper(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator.toUpperCase()));
        this.pPopUpsManager = new PopUpsManager(this.uiManager);
        button.addActionListener(new 11(b3GRadioWithIcon));
        b3GRadioWithIcon.setRadioUIID("TransparentContainer");
        Container GetContainer = b3GRadioWithIcon.GetContainer();
        GetContainer.setUIID("TransparentContainer");
        container2.add(GetContainer);
        container.add(createContainer);
        return container;
    }

    class 11 implements ActionListener {
        final /* synthetic */ B3GRadioWithIcon val$b3GRadioLg;

        11(B3GRadioWithIcon b3GRadioWithIcon) {
            this.val$b3GRadioLg = b3GRadioWithIcon;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$b3GRadioLg.isClear()) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ParameteresPage.this.uiManager.stateMachine, "Merci de choisir votre Operateur", null);
                return;
            }
            ServiceResponse updateProfileProcess = ParameteresPage.this.pPopUpsManager.updateProfileProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, Integer.toString(ParameteresPage.access$000(ParameteresPage.this, this.val$b3GRadioLg.GetSelectedText())), CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email, "1");
            if (updateProfileProcess.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ParameteresPage.this.uiManager.stateMachine, DataTools.FormatUnicode(updateProfileProcess.getStatusLabel()), null);
                CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator = this.val$b3GRadioLg.GetSelectedText();
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ParameteresPage.this.uiManager.stateMachine, DataTools.FormatUnicode(updateProfileProcess.getStatusLabel()), null);
        }
    }

    private Container ModeCnt() {
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "ModeSettings");
        Button button = (Button) uIBuilder.findByName("activateBtn", createContainer);
        Boolean valueOf = Boolean.valueOf(Preferences.get("dark_state", false));
        if (Settings.isDeviceNightMode()) {
            if (!valueOf.booleanValue()) {
                button.setText("Activer le mode sombre");
                button.setIcon(this.uiManager.ressource.getImage("radio_off.PNG"));
            } else {
                button.setText("Désactiver le mode sombre");
                button.setIcon(this.uiManager.ressource.getImage("radio_on.png"));
            }
            button.addActionListener(new ParameteresPage$$ExternalSyntheticLambda0(this, button, createContainer));
        } else {
            button.setText("Activer le mode sombre");
            button.setIcon(this.uiManager.ressource.getImage("radio_off.PNG"));
            button.addActionListener(new ParameteresPage$$ExternalSyntheticLambda1(this));
        }
        createContainer.revalidate();
        return createContainer;
    }

    /* synthetic */ void lambda$ModeCnt$0$com-b3g-ui-page-ParameteresPage(Button button, Container container, ActionEvent actionEvent) {
        UpdateBtnModeState(button);
        container.revalidate();
    }

    /* synthetic */ void lambda$ModeCnt$1$com-b3g-ui-page-ParameteresPage(ActionEvent actionEvent) {
        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Veuillez activer le mode sombre sur votre appareil.", null);
    }

    private void UpdateBtnModeState(Button button) {
        if (Settings.isNightMode()) {
            Preferences.set("dark_state", false);
            button.setText("Activer le mode sombre");
            button.setIcon(this.uiManager.ressource.getImage("radio_off.PNG"));
        } else {
            Preferences.set("dark_state", true);
            button.setText("Désactiver le mode sombre");
            button.setIcon(this.uiManager.ressource.getImage("radio_on.png"));
        }
        this.uiManager.GoBack();
    }

    private String getLblLang() {
        String trim = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().langue.trim();
        trim.hashCode();
        switch (trim) {
            case "AR":
                return "العربية";
            case "EN":
                return "English";
            case "ES":
                return "Español";
            case "FR":
                return "Français";
            default:
                return "";
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private int getLblLangIndice() {
        String trim = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().langue.trim();
        trim.hashCode();
        char c = 65535;
        switch (trim.hashCode()) {
            case 2097:
                if (trim.equals("AR")) {
                    c = 0;
                    break;
                }
                break;
            case 2217:
                if (trim.equals("EN")) {
                    c = 1;
                    break;
                }
                break;
            case 2222:
                if (trim.equals("ES")) {
                    c = 2;
                    break;
                }
                break;
            case 2252:
                if (trim.equals("FR")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            default:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private int getIndiceOper(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2093661423:
                if (str.equals("ORANGE Marruecos")) {
                    c = 0;
                    break;
                }
                break;
            case -1955522002:
                if (str.equals("ORANGE")) {
                    c = 1;
                    break;
                }
                break;
            case -1861538016:
                if (str.equals("ORANGE MAROC")) {
                    c = 2;
                    break;
                }
                break;
            case -1860552928:
                if (str.equals("ORANGE Maroc")) {
                    c = 3;
                    break;
                }
                break;
            case -1854319787:
                if (str.equals("Número extranjero")) {
                    c = 4;
                    break;
                }
                break;
            case -884157378:
                if (str.equals("ORANGE Morocco")) {
                    c = 5;
                    break;
                }
                break;
            case 72245:
                if (str.equals("IAM")) {
                    c = 6;
                    break;
                }
                break;
            case 2252471:
                if (str.equals("INWI")) {
                    c = 7;
                    break;
                }
                break;
            case 377495720:
                if (str.equals("INWI \"انوي\"")) {
                    c = '\b';
                    break;
                }
                break;
            case 877967413:
                if (str.equals("Foreign number")) {
                    c = '\t';
                    break;
                }
                break;
            case 944108448:
                if (str.equals("Numéro étranger")) {
                    c = '\n';
                    break;
                }
                break;
            case 1108019945:
                if (str.equals("Maroc Telecom")) {
                    c = 11;
                    break;
                }
                break;
            case 1111190515:
                if (str.equals("رقم أجنبي")) {
                    c = '\f';
                    break;
                }
                break;
            case 1512146854:
                if (str.equals("أورانج المغرب ORANGE")) {
                    c = '\r';
                    break;
                }
                break;
            case 1658775230:
                if (str.equals("MEDITEL")) {
                    c = 14;
                    break;
                }
                break;
            case 1696003817:
                if (str.equals("MAROC TELECOM")) {
                    c = 15;
                    break;
                }
                break;
            case 1861262649:
                if (str.equals("إتصالات المغرب")) {
                    c = 16;
                    break;
                }
                break;
            case 1941977568:
                if (str.equals("AUTRES")) {
                    c = 17;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case '\r':
            case 14:
                return 1;
            case 4:
            case '\t':
            case '\n':
            case '\f':
            case 17:
            default:
                return 3;
            case 6:
            case 11:
            case 15:
            case 16:
                return 0;
            case 7:
            case '\b':
                return 2;
        }
    }

    public Container ModeAffichage() {
        this.uiManager.CurrentPageId = 99;
        CihMBanking.sesPMTR.setCurrentUiManager(this.uiManager);
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "ModeAffichage");
        createContainer.setUIID("gl_GloabalContainerV2NoPadMargin");
        createContainer.getAllStyles().setPaddingUnit(1);
        createContainer.getAllStyles().setPadding(2, 2, 3, 0);
        Button button = (Button) uIBuilder.findByName("AffichageBtn", createContainer);
        createContainer.setLeadComponent(button);
        button.addActionListener(new 12());
        return createContainer;
    }

    class 12 implements ActionListener {
        12() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ParameteresPage.this.uiManager.NavigateToPageById(119);
        }
    }
}
