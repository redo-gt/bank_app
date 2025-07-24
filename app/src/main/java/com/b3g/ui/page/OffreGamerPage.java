package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.ServiceManager;
import com.b3g.services.TransactionResume;
import com.b3g.services.TransfertDATA;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizard;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.Step;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import java.util.ArrayList;
import java.util.Hashtable;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OffreGamerPage extends B3GPage {
    public Container cntTransfertForm;
    Container mainCntStep3;
    B3GWizard offerWizard;
    Step s1;
    Step s2;
    Step s3;
    Step s4;
    Step s5;
    TransfertDATA trDT;
    boolean isScrolled = false;
    private boolean conditionsAccepted = false;
    private UIBuilder uib = new UIBuilder();
    private byte[] b = {1, 1, 1, 1};

    static /* synthetic */ boolean access$000(OffreGamerPage offreGamerPage) {
        return offreGamerPage.conditionsAccepted;
    }

    static /* synthetic */ boolean access$002(OffreGamerPage offreGamerPage, boolean z) {
        offreGamerPage.conditionsAccepted = z;
        return z;
    }

    public OffreGamerPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (TransfertDATA) obj2;
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
            this.s1.stepCnt = getStep1Cnt();
            Step step2 = new Step();
            this.s2 = step2;
            step2.icon = "2inactif.png";
            this.s2.selectedIcon = "2actif.png";
            this.s2.stepCnt = getStep2Cnt();
            Step step3 = new Step();
            this.s3 = step3;
            step3.icon = "2inactif.png";
            this.s3.selectedIcon = "2actif.png";
            this.s3.stepCnt = getStep3Cnt();
            Step step4 = new Step();
            this.s4 = step4;
            step4.icon = "1inactif.png";
            this.s4.selectedIcon = "1actif.png";
            this.s4.stepCnt = GetTransferSecurityForm();
            Step step5 = new Step();
            this.s5 = step5;
            step5.icon = "1inactif.png";
            this.s5.selectedIcon = "1actif.png";
            this.s5.stepCnt = getStep5Cnt();
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.s1);
            arrayList.add(this.s2);
            arrayList.add(this.s3);
            arrayList.add(this.s4);
            arrayList.add(this.s5);
            B3GWizard b3GWizard = new B3GWizard(arrayList);
            this.offerWizard = b3GWizard;
            b3GWizard.withHeader = false;
            this.thisContainer = this.offerWizard.drawWizard("", 16777215, 0);
        }
        this.thisContainer.getAllStyles().setBgColor(16777215);
        this.thisContainer.getAllStyles().setBgTransparency(255);
        this.thisContainer.getAllStyles().setPadding(0, 2, 0, 0);
        this.thisContainer.getAllStyles().setPaddingUnit(2, 2, 2, 2);
        this.uiManager.currentForm.setBackCommand(new 1("Back"));
        return this.thisContainer;
    }

    class 1 extends Command {
        1(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreGamerPage.this.Previous();
        }
    }

    public void Previous() {
        if (this.offerWizard.currentStepId > 0) {
            B3GWizard b3GWizard = this.offerWizard;
            b3GWizard.goToPreviousStep(b3GWizard.getStepById(b3GWizard.currentStepId), "", 16777215, 16777215);
        } else {
            this.uiManager.GoBack();
        }
    }

    private Container getStep1Cnt() {
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(BoxLayout.y());
        SpanLabel spanLabel = new SpanLabel("Souscrire à une carte CODE GAMER virtuelle");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(this.b);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        FlowLayout flowLayout = new FlowLayout(4);
        flowLayout.setFillRows(true);
        Container container3 = new Container(flowLayout);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("code_gamer.png"));
        container3.add(label);
        container3.getAllStyles().setMarginUnit(this.b);
        container3.getAllStyles().setMargin(0, 3, 0, 0);
        SpanLabel spanLabel2 = new SpanLabel("string_gamer_key_01");
        spanLabel2.setTextUIID("Container");
        spanLabel2.setUIID("Container");
        spanLabel2.getTextAllStyles().setAlignment(1);
        spanLabel2.getAllStyles().setMarginUnit(this.b);
        spanLabel2.getAllStyles().setMargin(0, 1, 0, 0);
        SpanLabel spanLabel3 = new SpanLabel("string_gamer_key_02");
        spanLabel3.setTextUIID("Container");
        spanLabel3.setUIID("Container");
        spanLabel3.getTextAllStyles().setAlignment(1);
        spanLabel3.getAllStyles().setMarginUnit(this.b);
        spanLabel3.getAllStyles().setMargin(0, 2, 0, 0);
        Label label2 = new Label("msg_key_00002");
        label2.getAllStyles().setMarginUnit(this.b);
        label2.getAllStyles().setMargin(0, 2, 0, 0);
        Label label3 = new Label(" ", "Container");
        SpanLabel spanLabel4 = new SpanLabel("string_gamer_key_10");
        SpanLabel spanLabel5 = new SpanLabel("string_gamer_key_16");
        spanLabel5.getTextAllStyles().setFgColor(CIHStyles.colorOrange);
        container2.add(spanLabel).add(container3).add(spanLabel2).add(spanLabel3).add(label2).add(getPoint(new SpanLabel("string_gamer_key_03"), "point1.png")).add(getPoint(new SpanLabel("string_gamer_key_04"), "point1.png")).add(getPoint(new SpanLabel("string_gamer_key_05"), "point1.png")).add(getPoint(new SpanLabel("string_gamer_key_06"), "point1.png")).add(getPoint(new SpanLabel("string_gamer_key_07"), "point1.png")).add(getPoint(new SpanLabel("string_gamer_key_08"), "point1.png")).add(getPoint(new SpanLabel("string_gamer_key_09"), "point1.png")).add(getPoint(new SpanLabel("string_gamer_key_17"), "-")).add(getPoint(new SpanLabel("string_gamer_key_18"), "-")).add(getPoint(new SpanLabel("string_gamer_key_19"), "-")).add(getPoint(new SpanLabel("string_gamer_key_20"), "-")).add(getPoint(new SpanLabel("string_gamer_key_21"), "-")).add(getPoint(new SpanLabel("string_gamer_key_22"), "-")).add(spanLabel5).add(spanLabel4).add(label3);
        container2.setScrollVisible(false);
        container2.setScrollableY(true);
        Button button = new Button("\u200b", "Container");
        button.setText("Commander ma carte  CODE GAMER");
        Component buttonStyle2 = CIHStyles.setButtonStyle2(button, null);
        button.addActionListener(new 2(container2, label3));
        container2.getAllStyles().setPaddingUnit(this.b);
        container2.getAllStyles().setPaddingBottom(3);
        container.getAllStyles().setPaddingUnit(this.b);
        container.getAllStyles().setPadding(2, 0, 3, 3);
        container.add("Center", container2);
        container.add("South", buttonStyle2);
        return container;
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$mainYCnt;
        final /* synthetic */ Label val$toScroll;

        2(Container container, Label label) {
            this.val$mainYCnt = container;
            this.val$toScroll = label;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!OffreGamerPage.this.isScrolled) {
                this.val$mainYCnt.scrollComponentToVisible(this.val$toScroll);
                OffreGamerPage.this.isScrolled = true;
            } else {
                OffreGamerPage.this.uiManager.btnBack.getListeners().clear();
                OffreGamerPage.this.uiManager.btnBack.addActionListener(new OffreGamerPage$2$$ExternalSyntheticLambda0(this));
                OffreGamerPage.this.offerWizard.goToNextStep(OffreGamerPage.this.s1, "", 16777215, 0);
            }
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreGamerPage$2(ActionEvent actionEvent) {
            OffreGamerPage.this.offerWizard.goToPreviousStep(OffreGamerPage.this.s2, "", 16777215, 0);
            OffreGamerPage.this.uiManager.btnBack.getListeners().clear();
            OffreGamerPage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreGamerPage.this.uiManager.GoBack();
            }
        }
    }

    private Container getStep2Cnt() {
        int i;
        SpanLabel spanLabel = new SpanLabel("Souscrire à une carte CODE GAMER virtuelle");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(this.b);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(BoxLayout.y());
        Account account = new Account();
        account.uiManager = this.uiManager;
        ArrayList AccountProcessNew = account.AccountProcessNew(11);
        SessionParameter sessionParameter = CihMBanking.sesPMTR;
        SessionParameter.accountToDebitEShop = (Account) AccountProcessNew.get(0);
        Container container3 = new Container(BoxLayout.y());
        if (AccountProcessNew.size() > 1) {
            i = 1;
            container3.addComponent(this.uiManager.DrawListContainerForAccountsHome("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, AccountProcessNew, 1, 1000, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, null, null, null));
        } else {
            i = 1;
            container3.addComponent(this.uiManager.DrawListContainerForAccountsHome("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, AccountProcessNew, 2, 1000, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, null, null, null));
        }
        Container container4 = new Container(BoxLayout.x());
        Label label = new Label("MONTANT TOTAL :", "lbl_regular");
        Label label2 = new Label("  00.00  ", "g_lblNotifGrenPlafond");
        Label label3 = new Label("(DH)", "lbl_regular");
        container4.getAllStyles().setPaddingUnit(this.b);
        container4.getAllStyles().setPadding(3, 3, 2, 2);
        container4.add(label).add(label2).add(label3);
        Container container5 = new Container(new GridLayout(i, 2));
        Button button = new Button("Valider", "op_BtnOppositionValidationMarginRight");
        Button button2 = new Button("Precedent", "op_BtnOppositionValidationMarginRight");
        button.addActionListener(new 3());
        button2.addActionListener(new 4());
        container5.add(button2).add(button);
        container2.add(spanLabel).add(container3).add(container4).add(container5);
        container.add("Center", container2);
        container.getAllStyles().setPaddingUnit(this.b);
        container.getAllStyles().setPadding(2, 0, 3, 3);
        return container;
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreGamerPage.this.uiManager.btnBack.getListeners().clear();
            OffreGamerPage.this.uiManager.btnBack.addActionListener(new OffreGamerPage$3$$ExternalSyntheticLambda0(this));
            OffreGamerPage.this.offerWizard.goToNextStep(OffreGamerPage.this.s2, "", 16777215, 0);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreGamerPage$3(ActionEvent actionEvent) {
            OffreGamerPage.this.offerWizard.goToPreviousStep(OffreGamerPage.this.s3, "", 16777215, 0);
            OffreGamerPage.this.uiManager.btnBack.getListeners().clear();
            OffreGamerPage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreGamerPage.this.offerWizard.goToPreviousStep(OffreGamerPage.this.s2, "", 16777215, 0);
                OffreGamerPage.this.uiManager.btnBack.getListeners().clear();
                OffreGamerPage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    OffreGamerPage.this.uiManager.GoBack();
                }
            }
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreGamerPage.this.uiManager.btnBack.getListeners().clear();
            OffreGamerPage.this.uiManager.btnBack.addActionListener(new OffreGamerPage$4$$ExternalSyntheticLambda0(this));
            OffreGamerPage.this.offerWizard.goToPreviousStep(OffreGamerPage.this.s2, "", 16777215, 0);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreGamerPage$4(ActionEvent actionEvent) {
            OffreGamerPage.this.uiManager.GoBack();
        }
    }

    private Container getStep3Cnt() {
        this.mainCntStep3 = new Container(new BorderLayout());
        Container container = new Container(BoxLayout.y());
        SpanLabel spanLabel = new SpanLabel("Consentement\u200b");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(this.b);
        spanLabel.getAllStyles().setMargin(0, 3, 0, 0);
        SpanLabel spanLabel2 = new SpanLabel("string_gamer_key_11");
        SpanLabel spanLabel3 = new SpanLabel("string_gamer_key_12");
        SpanLabel spanLabel4 = new SpanLabel("string_gamer_key_13");
        SpanLabel spanLabel5 = new SpanLabel("string_gamer_key_14");
        spanLabel2.getTextAllStyles().setAlignment(1);
        spanLabel3.getTextAllStyles().setAlignment(1);
        spanLabel4.getTextAllStyles().setAlignment(1);
        spanLabel5.getTextAllStyles().setAlignment(1);
        spanLabel2.getAllStyles().setMarginUnit(this.b);
        spanLabel2.getAllStyles().setMargin(0, 3, 0, 0);
        spanLabel3.getAllStyles().setMarginUnit(this.b);
        spanLabel3.getAllStyles().setMargin(0, 3, 0, 0);
        spanLabel4.getAllStyles().setMarginUnit(this.b);
        spanLabel4.getAllStyles().setMargin(0, 3, 0, 0);
        spanLabel5.getAllStyles().setMarginUnit(this.b);
        spanLabel5.getAllStyles().setMargin(0, 3, 0, 0);
        Container container2 = new Container(new BorderLayout());
        SpanLabel spanLabel6 = new SpanLabel("string_gamer_key_15");
        spanLabel6.getTextAllStyles().setAlignment(1);
        spanLabel6.getTextAllStyles().setFgColor(0);
        Button button = new Button("", "Container");
        button.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("EprgneUnCheck.png"));
        button.setName("off");
        button.getAllStyles().setPaddingUnit(this.b);
        button.getAllStyles().setPadding(1.0f, 0.0f, 0.5f, 3.0f);
        spanLabel6.getTextAllStyles().setAlignment(1);
        spanLabel6.getTextAllStyles().setFgColor(CIHStyles.colorBleu);
        spanLabel6.getTextAllStyles().setTextDecoration(1);
        button.addActionListener(new 5(button, spanLabel6));
        Button button2 = new Button("", "Container");
        button2.setText("Suivant\u200b");
        Container buttonStyle2 = CIHStyles.setButtonStyle2(button2, null);
        button2.addActionListener(new 6());
        Button button3 = new Button("", "Container");
        button3.addActionListener(new 7());
        Container container3 = new Container();
        container3.add(spanLabel6).add(button3);
        container3.setLeadComponent(button3);
        container2.add("West", FlowLayout.encloseCenter(button));
        container2.add("Center", container3);
        container2.setLeadComponent(button);
        container.add(spanLabel2).add(spanLabel3).add(spanLabel4).add(container2);
        container.setScrollableY(true);
        container.setScrollVisible(false);
        this.mainCntStep3.add("North", spanLabel);
        this.mainCntStep3.add("Center", container);
        this.mainCntStep3.add("South", buttonStyle2);
        this.mainCntStep3.getAllStyles().setPaddingUnit(this.b);
        this.mainCntStep3.getAllStyles().setPadding(2, 0, 3, 3);
        return this.mainCntStep3;
    }

    class 5 implements ActionListener {
        final /* synthetic */ Button val$checkBtn;
        final /* synthetic */ SpanLabel val$checkSp;

        5(Button button, SpanLabel spanLabel) {
            this.val$checkBtn = button;
            this.val$checkSp = spanLabel;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$checkBtn.getName().equals("off")) {
                this.val$checkSp.getTextAllStyles().setTextDecoration(1);
                this.val$checkBtn.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("EprgneCheck.png"));
                OffreGamerPage.access$002(OffreGamerPage.this, true);
                this.val$checkBtn.setName("on");
                return;
            }
            this.val$checkBtn.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("EprgneUnCheck.png"));
            OffreGamerPage.access$002(OffreGamerPage.this, false);
            this.val$checkBtn.setName("off");
        }
    }

    class 6 implements ActionListener {
        6() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!OffreGamerPage.access$000(OffreGamerPage.this)) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreGamerPage.this.uiManager.stateMachine, "Veuillez accepter les conditions générales d’utilisation.", null);
                return;
            }
            ServiceResponse simulateEshop = OffreGamerPage.this.simulateEshop();
            if (simulateEshop.getStatusCode().equals("000")) {
                OffreGamerPage.this.uiManager.btnBack.getListeners().clear();
                OffreGamerPage.this.uiManager.btnBack.addActionListener(new OffreGamerPage$6$$ExternalSyntheticLambda0(this));
                OffreGamerPage.this.offerWizard.goToNextStep(OffreGamerPage.this.s3, "", 16777215, 0);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(simulateEshop.getStatusLabel()), null);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreGamerPage$6(ActionEvent actionEvent) {
            OffreGamerPage.this.offerWizard.goToPreviousStep(OffreGamerPage.this.s4, "", 16777215, 0);
            OffreGamerPage.this.uiManager.btnBack.getListeners().clear();
            OffreGamerPage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreGamerPage.this.offerWizard.goToPreviousStep(OffreGamerPage.this.s3, "", 16777215, 0);
                OffreGamerPage.this.uiManager.btnBack.getListeners().clear();
                OffreGamerPage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    OffreGamerPage.this.uiManager.GoBack();
                }
            }
        }
    }

    class 7 implements ActionListener {
        7() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().execute("https://www.cihnet.co.ma/Portals/_default/Skins/CIH/images/newSkinImgs/CGEGAMER.pdf");
        }
    }

    private Container GetTransferSecurityForm() {
        Container container = new Container(BoxLayout.y());
        container.getAllStyles().setPaddingUnit(this.b);
        container.getAllStyles().setPadding(2.0f, 2.0f, 4.0f, 4.0f);
        Label label = new Label("Service sélectionné : ");
        label.setUIID("g_lblDashBoardTitleOrange");
        SpanLabel spanLabel = new SpanLabel("Souscrire à une carte CODE GAMER");
        spanLabel.setTextUIID("ad_lblValueBlue");
        Container container2 = new Container(BoxLayout.x());
        container2.add(label);
        container2.add(spanLabel);
        Container container3 = new Container(BoxLayout.y());
        container3.setUIID("g_cntBorderV2");
        container.add(container2);
        container.add(container3);
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container4 = (Container) this.uib.findByName("Container17", createContainer);
        Button button = (Button) this.uib.findByName("BtnTransferBack", createContainer);
        Button button2 = (Button) this.uib.findByName("BtnTransferValidation", createContainer);
        button.addActionListener(new 8());
        button2.addActionListener(new 9(b3GCIHEcode));
        container4.removeAll();
        container4.setLayout(BoxLayout.y());
        container4.add(b3GCIHEcode.getComponent());
        container.add(createContainer);
        return container;
    }

    class 8 implements ActionListener {
        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreGamerPage.this.uiManager.btnBack.getListeners().clear();
            OffreGamerPage.this.uiManager.btnBack.addActionListener(new OffreGamerPage$8$$ExternalSyntheticLambda0(this));
            OffreGamerPage.this.offerWizard.goToPreviousStep(OffreGamerPage.this.s4, "", 16777215, 0);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreGamerPage$8(ActionEvent actionEvent) {
            OffreGamerPage.this.offerWizard.goToPreviousStep(OffreGamerPage.this.s3, "", 16777215, 0);
            OffreGamerPage.this.uiManager.btnBack.getListeners().clear();
            OffreGamerPage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreGamerPage.this.offerWizard.goToPreviousStep(OffreGamerPage.this.s2, "", 16777215, 0);
                OffreGamerPage.this.uiManager.btnBack.getListeners().clear();
                OffreGamerPage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    OffreGamerPage.this.uiManager.GoBack();
                }
            }
        }
    }

    class 9 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        9(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String value = this.val$eCode1.getValue();
            if (value.length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreGamerPage.this.uiManager.stateMachine, "Veuillez saisir votre CIH E-CODE.", null);
            } else {
                OffreGamerPage.this.ShowDialogTransfertService(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, value);
            }
        }
    }

    private Container getStep5Cnt() {
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(BoxLayout.yCenter());
        SpanLabel spanLabel = new SpanLabel("Confirmation de la souscription a une carte CODE GAMER");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(this.b);
        spanLabel.getAllStyles().setMargin(0, 3, 0, 0);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("bigOK.png"));
        label.getAllStyles().setPaddingUnit(this.b);
        label.getAllStyles().setPadding(1, 2, 0, 0);
        FlowLayout flowLayout = new FlowLayout(4);
        flowLayout.setFillRows(true);
        Container container3 = new Container(flowLayout);
        container3.add(label);
        SpanLabel spanLabel2 = new SpanLabel();
        spanLabel2.setText("Votre carte CODE GAMER a été crée avec succès!");
        spanLabel2.setTextUIID("Container");
        spanLabel2.setUIID("Container");
        spanLabel2.getTextAllStyles().setAlignment(4);
        spanLabel2.getAllStyles().setPaddingUnit(this.b);
        spanLabel2.getAllStyles().setPadding(1, 1, 10, 10);
        SpanLabel spanLabel3 = new SpanLabel();
        spanLabel3.setText("Retrouvez votre numéro de carte virtuelle depuis la rubrique « Mes cartes », et effectuez vos achats en toute sécurité.");
        spanLabel3.setTextUIID("Container");
        spanLabel3.setUIID("Container");
        spanLabel3.getTextAllStyles().setAlignment(4);
        spanLabel3.getAllStyles().setPaddingUnit(this.b);
        spanLabel3.getAllStyles().setPadding(1, 1, 10, 10);
        new Button().setText("");
        container.add("North", spanLabel);
        container2.add(container3).add(spanLabel2).add(spanLabel3);
        container.add("Center", container2);
        container.getAllStyles().setPaddingUnit(this.b);
        container.getAllStyles().setPadding(2, 2, 3, 3);
        return container;
    }

    private Container getPoint(SpanLabel spanLabel, String str) {
        Container container = new Container(new BorderLayout());
        Label label = new Label();
        if (!"-".equals(str)) {
            label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage(str));
        } else {
            label.setText(str);
        }
        spanLabel.setTextUIID("Container");
        spanLabel.setUIID("Container");
        spanLabel.getTextAllStyles().setAlignment(1);
        spanLabel.getAllStyles().setPaddingUnit(this.b);
        spanLabel.getAllStyles().setPadding(0.0f, 0.0f, "-".equals(str) ? 1.75f : 1.0f, 1.0f);
        label.getAllStyles().setPaddingUnit(this.b);
        label.getAllStyles().setPadding(1.0f, 0.0f, 0.25f, 0.25f);
        container.getAllStyles().setMarginUnit(this.b);
        container.getAllStyles().setMargin(0.0f, 2.0f, "-".equals(str) ? 2.0f : 0.0f, 0.0f);
        container.add("West", FlowLayout.encloseIn(label));
        container.add("Center", spanLabel);
        return container;
    }

    public ServiceResponse simulateEshop() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        SessionParameter sessionParameter = CihMBanking.sesPMTR;
        hashtable.put("ACCOUNTNUMBER", SessionParameter.accountToDebitEShop.accountNumber);
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(10826);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse confirmeEshop(String str) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("ProductCode", "079");
        hashtable.put("PASSID", str);
        SessionParameter sessionParameter = CihMBanking.sesPMTR;
        hashtable.put("ACCOUNTNUMBER", SessionParameter.accountToDebitEShop.accountNumber);
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(10827);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public TransactionResume GetTransaction(Container container, TextArea textArea, TextArea textArea2) {
        TransactionResume transactionResume = new TransactionResume();
        transactionResume.Amount = textArea.getText();
        transactionResume.Motif = textArea2.getText();
        transactionResume.PassHB = "";
        Container container2 = (Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0);
        transactionResume.IssuerAccountOwner = ((Label) ((Container) ((Container) ((Container) container2.getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getText();
        transactionResume.IssuerAccountNumber = ((Account) ((B3gService) container2.getClientProperty("AccountClient"))).rib;
        transactionResume.isTutor = "0";
        return transactionResume;
    }

    public void ShowDialogTransfertService(StateMachine stateMachine, String str) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container container = new Container(BoxLayout.y());
            Button button = new Button("ANNULER");
            button.setTextPosition(3);
            button.setUIID("dg_BtnCancelIcon");
            Container container2 = new Container(new GridLayout(1, 2));
            button.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ico_close_popup_NW.png"));
            button.setVerticalAlignment(4);
            button.addActionListener(new 10(dialog));
            Button button2 = new Button("VALIDER");
            button2.setUIID("dg_BtnYestIconTR");
            button2.setTextPosition(3);
            button2.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ico_check_popup_NW.png"));
            button2.setVerticalAlignment(4);
            container2.add(button).add(button2);
            container2.getAllStyles().setPaddingUnit(this.b);
            container2.getAllStyles().setPadding(5, 0, 2, 2);
            button2.addActionListener(new 11(dialog, str));
            Label label = new Label("Compte à débiter :", "dg_lblPopupDetailItemTitle");
            SessionParameter sessionParameter = CihMBanking.sesPMTR;
            Label label2 = new Label(SessionParameter.accountToDebitEShop.rib, "dg_lblPopupDetailItemValueSP");
            container.add(label);
            container.add(label2);
            Label label3 = new Label("Montant (MAD) :", "dg_lblPopupDetailItemTitle");
            Label label4 = new Label("00", "dg_lblPopupDetailItemValueSP");
            label3.getAllStyles().setPaddingUnit(this.b);
            label3.getAllStyles().setPaddingTop(3);
            container.add(label3);
            container.add(label4);
            container.add(container2);
            container.setPreferredH(CihMBanking.sesPMTR.displayHeight);
            container.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
            container.getAllStyles().setPaddingUnit(this.b);
            container.getAllStyles().setPadding(5, 0, 5, 5);
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
            dialog.addComponent("Center", container);
            dialog.showPacked("Center", true);
        } catch (Exception unused) {
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

    class 11 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ String val$eCode;

        11(Dialog dialog, String str) {
            this.val$d = dialog;
            this.val$eCode = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            ServiceResponse confirmeEshop = OffreGamerPage.this.confirmeEshop(this.val$eCode);
            if (confirmeEshop.getStatusCode().equals("000")) {
                OffreGamerPage.this.uiManager.btnBack.getListeners().clear();
                OffreGamerPage.this.uiManager.btnBack.addActionListener(new OffreGamerPage$11$$ExternalSyntheticLambda0(this));
                OffreGamerPage.this.offerWizard.goToNextStep(OffreGamerPage.this.s4, "", 16777215, 0);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(confirmeEshop.getStatusLabel()), null);
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new OffreGamerPage$11$1$$ExternalSyntheticLambda0(this)).schedule(300, false, OffreGamerPage.this.uiManager.currentForm);
            }

            /* synthetic */ void lambda$run$0$com-b3g-ui-page-OffreGamerPage$11$1() {
                OffreGamerPage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreGamerPage$11(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }
}
