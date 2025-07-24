package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.MonConseiller;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class EpargneConditions extends B3GPage {
    Container SouthCnt;
    private String VnewaliasText;
    Button acceptIcon;
    Button acceptIcon1;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();

    static /* synthetic */ ServiceResponse access$000(EpargneConditions epargneConditions) {
        return epargneConditions.EpargneSimulation();
    }

    static /* synthetic */ String access$100(EpargneConditions epargneConditions) {
        return epargneConditions.VnewaliasText;
    }

    static /* synthetic */ String access$102(EpargneConditions epargneConditions, String str) {
        epargneConditions.VnewaliasText = str;
        return str;
    }

    public EpargneConditions(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        Container container = new Container(new BorderLayout());
        container.setUIID("Whitemargin_2_2_2_2");
        Container container2 = new Container(new BorderLayout());
        this.SouthCnt = new Container(new BoxLayout(2));
        Button button = new Button("SUIVANT");
        button.setUIID("op_BtnOppositionValidationV2");
        button.getAllStyles().setBgColor(10594728);
        button.setEnabled(false);
        Button button2 = new Button("ABANDONNER");
        button2.setUIID("OrgLabel");
        button2.addActionListener(new 1());
        button.addActionListener(new 2(container2, container));
        Container container3 = new Container(new BorderLayout());
        container3.setUIID("padding_1_1_1_1");
        new Button().setUIID("");
        Button button3 = new Button("Lu et approuvé");
        button3.setUIID("LblProfileSmalGreyEprn");
        Button button4 = new Button("");
        this.acceptIcon = button4;
        button4.setUIID("padding_1_1_1_1");
        this.acceptIcon.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("EprgneUnCheck.png"));
        this.acceptIcon.addActionListener(new 3(button));
        container3.add("West", this.acceptIcon);
        container3.add("Center", button3);
        container3.setLeadComponent(this.acceptIcon);
        Container container4 = new Container(new BorderLayout());
        container4.setUIID("padding_1_1_1_1");
        new Button().setUIID("");
        Container container5 = new Container();
        Button button5 = new Button("J’ai lu et j'accepte ");
        button5.setUIID("LblProfileSmalGreyEprn");
        Button button6 = new Button("les conditions particulières");
        button6.setUIID("Regroupement_blue_lbl");
        button6.addActionListener(new 4());
        Button button7 = new Button("");
        this.acceptIcon1 = button7;
        button7.setUIID("padding_1_1_1_1");
        this.acceptIcon1.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("EprgneUnCheck.png"));
        this.acceptIcon1.addActionListener(new 5(button));
        button5.addActionListener(new 6(button5, button));
        container5.add(button5).add(button6);
        container4.add("West", this.acceptIcon1);
        container4.add("Center", container5);
        button2.getAllStyles().setMarginUnit(1);
        button2.getAllStyles().setMargin(1, 1, 0, 0);
        this.SouthCnt.add(container3);
        this.SouthCnt.add(container4);
        this.SouthCnt.add(button);
        this.SouthCnt.add(button2);
        Label label = new Label("Déclaration sur l’honneur");
        label.setUIID("crd_CardTitle");
        container2.add("North", label);
        Container container6 = new Container(new BoxLayout(2));
        container6.setUIID("BorderGrey");
        container6.getAllStyles().setBgColor(16777215);
        container6.getAllStyles().setPaddingUnit(1);
        container6.getAllStyles().setPadding(1, 1, 1, 1);
        SpanLabel spanLabel = new SpanLabel();
        spanLabel.setTextUIID("NightBlueLbl");
        spanLabel.setText("Je soussigné(e) " + CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom + " \n");
        SpanLabel spanLabel2 = new SpanLabel();
        spanLabel2.setTextUIID("LblProfileSmalGreyEprn");
        spanLabel2.setText("Demande l’ouverture sur vos livres, d’un compte d’épargne ou « Compte sur carnet » dont le fonctionnement sera conforme à la réglementation en vigueur,");
        SpanLabel spanLabel3 = new SpanLabel();
        spanLabel3.setTextUIID("LblProfileSmalGreyEprn");
        spanLabel3.setText("Déclare sur l’honneur ne pas être titulaire d’un compte sur carnet auprès d’un autre établissement bancaire,");
        SpanLabel spanLabel4 = new SpanLabel();
        spanLabel4.setTextUIID("LblProfileSmalGreyEprn");
        spanLabel4.setText("Confirme que ce compte est destiné à servir mes besoins personnels,");
        SpanLabel spanLabel5 = new SpanLabel();
        spanLabel5.setTextUIID("LblProfileSmalGreyEprn");
        spanLabel5.setText("Certifie l’exactitude et la sincérité des informations mentionnées ci-dessus.");
        Container container7 = new Container(new BorderLayout());
        Container container8 = new Container(new BorderLayout());
        Container container9 = new Container(new BorderLayout());
        Container container10 = new Container(new BorderLayout());
        Label label2 = new Label("- ");
        Label label3 = new Label("- ");
        Label label4 = new Label("- ");
        Label label5 = new Label("- ");
        label2.setUIID("LblProfileSmalGreyEprn");
        label3.setUIID("LblProfileSmalGreyEprn");
        label4.setUIID("LblProfileSmalGreyEprn");
        label5.setUIID("LblProfileSmalGreyEprn");
        container7.add("North", label2);
        container8.add("North", label3);
        container9.add("North", label4);
        container10.add("North", label5);
        Container container11 = new Container(new BorderLayout());
        Container container12 = new Container(new BorderLayout());
        Container container13 = new Container(new BorderLayout());
        Container container14 = new Container(new BorderLayout());
        container11.add("West", container7);
        container11.add("Center", spanLabel2);
        container12.add("West", container8);
        container12.add("Center", spanLabel3);
        container13.add("West", container9);
        container13.add("Center", spanLabel4);
        container14.add("West", container10);
        container14.add("Center", spanLabel5);
        container6.add(spanLabel);
        container6.add(container11);
        container6.add(container12);
        container6.add(container13);
        container6.add(container14);
        container6.setScrollableY(true);
        new Container(new FlowLayout(4, 4));
        container2.add("Center", container6);
        container.add("Center", container2);
        container.add("South", this.SouthCnt);
        this.thisContainer.add(container);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        class 1 implements Runnable {
            1() {
            }

            class 1 implements Runnable {
                1() {
                }

                public void run() {
                    EpargneConditions.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
                }
            }

            public void run() {
                new UITimer(new 1()).schedule(100, false, EpargneConditions.this.uiManager.currentForm);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$GlobCnt;
        final /* synthetic */ Container val$YCnt;

        2(Container container, Container container2) {
            this.val$YCnt = container;
            this.val$GlobCnt = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (EpargneConditions.this.acceptIcon.getIcon().equals(CihMBanking.theme.getImage("EprgneCheck.png"))) {
                new ServiceResponse();
                try {
                    ServiceResponse access$000 = EpargneConditions.access$000(EpargneConditions.this);
                    if (access$000.getStatusCode().equals("000")) {
                        CihMBanking.sesPMTR.setSessionSavedContainer(this.val$YCnt);
                        EpargneConditions epargneConditions = EpargneConditions.this;
                        Container container = this.val$GlobCnt;
                        epargneConditions.SwitchTransfertForms(container, this.val$YCnt, epargneConditions.GetTransferSecurityForm(container), false);
                    } else {
                        EpargneConditions.this.ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(access$000.getStatusLabel()), null);
                    }
                    return;
                } catch (Exception unused) {
                    EpargneConditions.this.ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Une erreur est survenue ,  Veuillez réessayer plus tard"), null);
                    return;
                }
            }
            EpargneConditions.this.uiManager.ShowMessageTransaction(EpargneConditions.this.uiManager.stateMachine, "Merci d'accepter les conditions avant de compléter la création du compte", null);
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Button val$CreateEpargneBtn;

        3(Button button) {
            this.val$CreateEpargneBtn = button;
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                if (EpargneConditions.this.acceptIcon.getIcon().equals(CihMBanking.theme.getImage("EprgneCheck.png"))) {
                    EpargneConditions.this.acceptIcon.setIcon(CihMBanking.theme.getImage("EprgneUnCheck.png"));
                    3.this.val$CreateEpargneBtn.getAllStyles().setBgColor(10594728);
                    3.this.val$CreateEpargneBtn.setEnabled(false);
                } else {
                    EpargneConditions.this.acceptIcon.setIcon(CihMBanking.theme.getImage("EprgneCheck.png"));
                    if (EpargneConditions.this.acceptIcon1.getIcon().equals(CihMBanking.theme.getImage("EprgneCheck.png"))) {
                        3.this.val$CreateEpargneBtn.setUIID("op_BtnOppositionValidationV2");
                        3.this.val$CreateEpargneBtn.setEnabled(true);
                    }
                }
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            EpargneConditions.this.acceptIcon.addActionListener(new 1());
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            EpargneConditions.this.showConditionsPopUp();
        }
    }

    class 5 implements ActionListener {
        final /* synthetic */ Button val$CreateEpargneBtn;

        5(Button button) {
            this.val$CreateEpargneBtn = button;
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                if (EpargneConditions.this.acceptIcon1.getIcon().equals(CihMBanking.theme.getImage("EprgneCheck.png"))) {
                    EpargneConditions.this.acceptIcon1.setIcon(CihMBanking.theme.getImage("EprgneUnCheck.png"));
                    5.this.val$CreateEpargneBtn.getAllStyles().setBgColor(10594728);
                    5.this.val$CreateEpargneBtn.setEnabled(false);
                } else {
                    EpargneConditions.this.acceptIcon1.setIcon(CihMBanking.theme.getImage("EprgneCheck.png"));
                    if (EpargneConditions.this.acceptIcon.getIcon().equals(CihMBanking.theme.getImage("EprgneCheck.png"))) {
                        5.this.val$CreateEpargneBtn.setUIID("op_BtnOppositionValidationV2");
                        5.this.val$CreateEpargneBtn.setEnabled(true);
                    }
                }
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            EpargneConditions.this.acceptIcon1.addActionListener(new 1());
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ Button val$AcceptdescBtn1;
        final /* synthetic */ Button val$CreateEpargneBtn;

        6(Button button, Button button2) {
            this.val$AcceptdescBtn1 = button;
            this.val$CreateEpargneBtn = button2;
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                if (EpargneConditions.this.acceptIcon1.getIcon().equals(CihMBanking.theme.getImage("EprgneCheck.png"))) {
                    EpargneConditions.this.acceptIcon1.setIcon(CihMBanking.theme.getImage("EprgneUnCheck.png"));
                    6.this.val$CreateEpargneBtn.getAllStyles().setBgColor(10594728);
                    6.this.val$CreateEpargneBtn.setEnabled(false);
                } else {
                    EpargneConditions.this.acceptIcon1.setIcon(CihMBanking.theme.getImage("EprgneCheck.png"));
                    if (EpargneConditions.this.acceptIcon.getIcon().equals(CihMBanking.theme.getImage("EprgneCheck.png"))) {
                        6.this.val$CreateEpargneBtn.setUIID("op_BtnOppositionValidationV2");
                        6.this.val$CreateEpargneBtn.setEnabled(true);
                    }
                }
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$AcceptdescBtn1.addActionListener(new 1());
        }
    }

    public Container GetTransferSecurityForm(Container container) {
        this.SouthCnt.setHidden(true);
        Container container2 = new Container(BoxLayout.y());
        Container container3 = new Container(BoxLayout.y());
        Label label = new Label("Service sélectionné : ");
        label.setUIID("g_lblDashBoardTitleOrange");
        Label label2 = new Label("Activer Bénéficiaire");
        label2.setUIID("ad_lblValueBlue");
        Container container4 = new Container(BoxLayout.x());
        container4.add(label);
        container4.add(label2);
        Container container5 = new Container(BoxLayout.y());
        container5.setUIID("g_cntBorderV2");
        container3.add(container4);
        container3.add(container5);
        container2.add(container3);
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container6 = (Container) this.uib.findByName("Container17", createContainer);
        container6.removeAll();
        container6.setLayout(BoxLayout.y());
        container6.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 7(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 8(b3GCIHEcode));
        return createContainer;
    }

    class 7 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        7(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 0));
            EpargneConditions.this.SouthCnt.setHidden(false);
            EpargneConditions.this.uiManager.btnBack.getListeners().clear();
            EpargneConditions.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                EpargneConditions.this.SouthCnt.setHidden(false);
                EpargneConditions.this.uiManager.GoBack();
            }
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        8(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            EpargneConditions.access$102(EpargneConditions.this, this.val$eCode1.getValue());
            if (EpargneConditions.access$100(EpargneConditions.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(EpargneConditions.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
            } else {
                EpargneConditions.this.ShowDialogTransfertService();
            }
        }
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 0));
        container.revalidate();
    }

    private ServiceResponse EpargneSimulation() {
        ServiceRequest serviceRequest = new ServiceRequest();
        new ArrayList();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(10708);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse EpargneConfirmation(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("PASSID", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setIsDemo(0);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setServiceId(10709);
        ServiceResponse serviceResponse = (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
        serviceResponse.getParamsOut();
        return serviceResponse;
    }

    public void ShowMessageTransaction(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMessage");
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
            stateMachine.findSpanLabel(createContainer).setText(str);
            stateMachine.findLabel1(createContainer).setText(str2);
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 9());
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 9 implements ActionListener {
        9() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            EpargneConditions.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    public void ShowMessageTransaction(StateMachine stateMachine) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpAssistance");
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
            stateMachine.findCntPopupDemoConfirmBloc(createContainer).removeAll();
            stateMachine.findCntPopupDemoConfirmBloc(createContainer).add(ChoiceEmprunteur(dialog));
            new Container(new GridLayout(1, 1));
            new Container(new FlowLayout(4, 4));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    public Container ChoiceEmprunteur(Dialog dialog) {
        Container container = new Container(BoxLayout.y());
        container.setUIID("gl_GloabalContainerV2NoPadMargin");
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(2, 2, 5, 5);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2, 2, 2, 2);
        SpanLabel spanLabel = new SpanLabel();
        spanLabel.setTextUIID("LblProfileSmalGreyEprn");
        spanLabel.setText("1- aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        SpanLabel spanLabel2 = new SpanLabel();
        spanLabel2.setTextUIID("LblProfileSmalGreyEprn");
        spanLabel2.setText("2- bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        SpanLabel spanLabel3 = new SpanLabel();
        spanLabel3.setTextUIID("LblProfileSmalGreyEprn");
        spanLabel3.setText("3- cccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
        SpanLabel spanLabel4 = new SpanLabel();
        spanLabel4.setTextUIID("LblProfileSmalGreyEprn");
        spanLabel4.setText("3- dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        container.add(spanLabel).add(spanLabel2).add(spanLabel3).add(spanLabel4);
        Button button = new Button("OK");
        button.setUIID("op_BtnOppositionValidationV2");
        button.addActionListener(new 10(dialog));
        container.add(button);
        container.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).strokeOpacity(0).cornerRadius(1.4f));
        return container;
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

    public Dialog showConditionsPopUp() {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        dialog.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.setPreferredH(Display.getInstance().getDisplayHeight());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "EpargneConditionsParticulieres");
        Button button = (Button) uIBuilder.findByName("ActivateBtn", createContainer);
        ((Container) uIBuilder.findByName("Container3", createContainer)).getAllStyles().setBgColor(16777215);
        button.addActionListener(new 11(dialog));
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", true);
        return dialog;
    }

    class 11 implements ActionListener {
        final /* synthetic */ Dialog val$d1;

        11(Dialog dialog) {
            this.val$d1 = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d1.dispose();
        }
    }

    public void ShowDialogTransfertService() {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUp");
            ((Container) this.uib.findByName("cntPopUpLeftLabel", createContainer)).removeAll();
            ArrayList GetPopupItemDetail = GetPopupItemDetail(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine);
            for (int i = 0; i < GetPopupItemDetail.size(); i++) {
                CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.findCntPopupBody(createContainer).addComponent(label);
            CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupTransferBtn(dialog));
            createContainer.setPreferredH(CihMBanking.sesPMTR.displayHeight);
            createContainer.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(false);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(230);
            dialog.getDialogStyle().setBgColor(1118481);
            dialog.setPreferredH(CihMBanking.sesPMTR.displayHeight);
            dialog.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
            dialog.getStyle().setPadding(0, 0, 0, 0);
            dialog.getStyle().setMargin(0, 0, 0, 0);
            dialog.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog.revalidate();
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    public ArrayList GetPopupItemDetail(StateMachine stateMachine) {
        new MonConseiller();
        new MonConseiller();
        MonConseiller MonConseillerProcess = MonConseiller.MonConseillerProcess();
        ArrayList arrayList = new ArrayList();
        new DataTools();
        Container createContainer = stateMachine.createContainer("/cihv3", "PopupItem");
        ((Label) this.uib.findByName("lblPopupDetailItemTitle", createContainer)).setText("Titulaire : ");
        Container createContainer2 = stateMachine.createContainer("/cihv3", "PopupItem");
        ((Label) this.uib.findByName("lblPopupDetailItemTitle", createContainer2)).setText("Intitulé du compte : ");
        Container createContainer3 = stateMachine.createContainer("/cihv3", "PopupItem");
        ((Label) this.uib.findByName("lblPopupDetailItemTitle", createContainer3)).setText("Adresse : ");
        Container createContainer4 = stateMachine.createContainer("/cihv3", "PopupItem");
        ((Label) this.uib.findByName("lblPopupDetailItemTitle", createContainer4)).setText("Agence : ");
        SpanLabel spanLabel = (SpanLabel) this.uib.findByName("lblPopupDetailItemValue", createContainer);
        SpanLabel spanLabel2 = (SpanLabel) this.uib.findByName("lblPopupDetailItemValue", createContainer2);
        SpanLabel spanLabel3 = (SpanLabel) this.uib.findByName("lblPopupDetailItemValue", createContainer3);
        SpanLabel spanLabel4 = (SpanLabel) this.uib.findByName("lblPopupDetailItemValue", createContainer4);
        spanLabel.setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom);
        spanLabel2.setText("COMPTE D'EPARGNE");
        spanLabel3.setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().adress);
        spanLabel4.setText(MonConseillerProcess.agence);
        arrayList.add(createContainer);
        arrayList.add(createContainer2);
        arrayList.add(createContainer3);
        arrayList.add(createContainer4);
        return arrayList;
    }

    public Container GetPopupTransferBtn(Dialog dialog) {
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
        button.addActionListener(new 12(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 13(dialog));
        container.addComponent(button2);
        return container;
    }

    class 12 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        12(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 13 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        13(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            try {
                EpargneConditions epargneConditions = EpargneConditions.this;
                ServiceResponse EpargneConfirmation = epargneConditions.EpargneConfirmation(EpargneConditions.access$100(epargneConditions));
                if (EpargneConfirmation.getStatusCode().equals("000")) {
                    EpargneConditions.this.uiManager.NavigateToPageById(127);
                } else {
                    EpargneConditions.this.ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(EpargneConfirmation.getStatusLabel()), null);
                }
            } catch (Exception unused) {
                EpargneConditions.this.ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Une erreur est survenue ,  Veuillez réessayer plus tard"), null);
            }
        }
    }
}
