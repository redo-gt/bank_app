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
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OffreEShoppingPage extends B3GPage {
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

    static /* synthetic */ boolean access$000(OffreEShoppingPage offreEShoppingPage) {
        return offreEShoppingPage.conditionsAccepted;
    }

    static /* synthetic */ boolean access$002(OffreEShoppingPage offreEShoppingPage, boolean z) {
        offreEShoppingPage.conditionsAccepted = z;
        return z;
    }

    public OffreEShoppingPage(Object obj, Object obj2, int i) {
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
        return this.thisContainer;
    }

    private Container getStep1Cnt() {
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(BoxLayout.y());
        SpanLabel spanLabel = new SpanLabel("Souscrire à une carte E-shopping virtuelle");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        FlowLayout flowLayout = new FlowLayout(4);
        flowLayout.setFillRows(true);
        Container container3 = new Container(flowLayout);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ESHOP.png"));
        container3.add(label);
        container3.getAllStyles().setMarginUnit(1);
        container3.getAllStyles().setMargin(0, 3, 0, 0);
        SpanLabel spanLabel2 = new SpanLabel("msg_key_00001");
        spanLabel2.setTextUIID("Container");
        spanLabel2.setUIID("Container");
        spanLabel2.getTextAllStyles().setAlignment(1);
        spanLabel2.getAllStyles().setMarginUnit(1);
        spanLabel2.getAllStyles().setMargin(0, 2, 0, 0);
        Label label2 = new Label("msg_key_00002");
        label2.getAllStyles().setMarginUnit(1);
        label2.getAllStyles().setMargin(0, 2, 0, 0);
        Label label3 = new Label(" ", "Container");
        SpanLabel spanLabel3 = new SpanLabel("La carte E-shopping est une carte virtuelle dont les informations de paiement sont disponibles sur votre application Mobile");
        spanLabel3.getTextAllStyles().setFgColor(CIHStyles.colorOrange);
        container2.add(spanLabel).add(container3).add(spanLabel2).add(label2).add(getPoint(new SpanLabel("msg_key_00003"), "point1.png")).add(getPoint(new SpanLabel("msg_key_00004"), "point1.png")).add(getPoint(new SpanLabel("msg_key_00005"), "point1.png")).add(getPoint(new SpanLabel("msg_key_00006"), "point1.png")).add(getPoint(new SpanLabel("msg_key_00007"), "point1.png")).add(getPoint(new SpanLabel("msg_key_00008"), "point2.png")).add(getPoint(new SpanLabel("msg_key_000011"), "point2.png")).add(getPoint(new SpanLabel("msg_key_00009"), "point2.png")).add(spanLabel3).add(label3);
        container2.setScrollVisible(false);
        container2.setScrollableY(true);
        Button button = new Button("\u200b", "Container");
        button.setText("Commander ma carte e-shopping");
        Component buttonStyle2 = CIHStyles.setButtonStyle2(button, null);
        button.addActionListener(new 1(container2, label3));
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPaddingBottom(3);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2, 0, 3, 3);
        container.add("Center", container2);
        container.add("South", buttonStyle2);
        return container;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$mainYCnt;
        final /* synthetic */ Label val$toScroll;

        1(Container container, Label label) {
            this.val$mainYCnt = container;
            this.val$toScroll = label;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!OffreEShoppingPage.this.isScrolled) {
                this.val$mainYCnt.scrollComponentToVisible(this.val$toScroll);
                OffreEShoppingPage.this.isScrolled = true;
            } else {
                OffreEShoppingPage.this.uiManager.btnBack.getListeners().clear();
                OffreEShoppingPage.this.uiManager.btnBack.addActionListener(new OffreEShoppingPage$1$$ExternalSyntheticLambda0(this));
                OffreEShoppingPage.this.offerWizard.goToNextStep(OffreEShoppingPage.this.s1, "", 16777215, 0);
            }
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreEShoppingPage$1(ActionEvent actionEvent) {
            OffreEShoppingPage.this.offerWizard.goToPreviousStep(OffreEShoppingPage.this.s2, "", 16777215, 0);
            OffreEShoppingPage.this.uiManager.btnBack.getListeners().clear();
            OffreEShoppingPage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreEShoppingPage.this.uiManager.GoBack();
            }
        }
    }

    private Container getStep2Cnt() {
        SpanLabel spanLabel = new SpanLabel("Souscrire à une carte E-shopping virtuelle");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
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
            container3.addComponent(this.uiManager.DrawListContainerForAccountsHome("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, AccountProcessNew, 1, 1000, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, null, null, null));
        } else {
            container3.addComponent(this.uiManager.DrawListContainerForAccountsHome("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, AccountProcessNew, 2, 1000, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, null, null, null));
        }
        Container container4 = new Container(BoxLayout.x());
        Label label = new Label("MONTANT TOTAL :", "lbl_regular");
        Label label2 = new Label("  66.00  ", "g_lblNotifGrenPlafond");
        Label label3 = new Label("(DH)", "lbl_regular");
        container4.getAllStyles().setPaddingUnit(1);
        container4.getAllStyles().setPadding(3, 3, 2, 2);
        container4.add(label).add(label2).add(label3);
        Container container5 = new Container(new GridLayout(1, 2));
        Button button = new Button("Valider", "op_BtnOppositionValidationMarginRight");
        Button button2 = new Button("Precedent", "op_BtnOppositionValidationMarginRight");
        button.addActionListener(new 2());
        button2.addActionListener(new 3());
        container5.add(button2).add(button);
        container2.add(spanLabel).add(container3).add(container4).add(container5);
        container.add("Center", container2);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2, 0, 3, 3);
        return container;
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreEShoppingPage.this.uiManager.btnBack.getListeners().clear();
            OffreEShoppingPage.this.uiManager.btnBack.addActionListener(new OffreEShoppingPage$2$$ExternalSyntheticLambda0(this));
            OffreEShoppingPage.this.offerWizard.goToNextStep(OffreEShoppingPage.this.s2, "", 16777215, 0);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreEShoppingPage$2(ActionEvent actionEvent) {
            OffreEShoppingPage.this.offerWizard.goToPreviousStep(OffreEShoppingPage.this.s3, "", 16777215, 0);
            OffreEShoppingPage.this.uiManager.btnBack.getListeners().clear();
            OffreEShoppingPage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreEShoppingPage.this.offerWizard.goToPreviousStep(OffreEShoppingPage.this.s2, "", 16777215, 0);
                OffreEShoppingPage.this.uiManager.btnBack.getListeners().clear();
                OffreEShoppingPage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    OffreEShoppingPage.this.uiManager.GoBack();
                }
            }
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreEShoppingPage.this.offerWizard.goToPreviousStep(OffreEShoppingPage.this.s2, "", 16777215, 0);
        }
    }

    private Container getStep3Cnt() {
        this.mainCntStep3 = new Container(new BorderLayout());
        Container container = new Container(BoxLayout.y());
        SpanLabel spanLabel = new SpanLabel("Consentement\u200b");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0, 3, 0, 0);
        SpanLabel spanLabel2 = new SpanLabel("Le présent contrat a pour objet la mise à la disposition de la clientèle du Crédit Immobilier et Hôtelier ci-après dénommée « CIH BANK », d’une carte\ninternationale virtuelle pour le paiement sur sites internet étrangers désignée carte « E-SHOPPING ».\n");
        SpanLabel spanLabel3 = new SpanLabel("La « Banque » ou « CIH BANK » signifie Crédit Immobilier et Hôtelier, sis au 187 Avenue Hassan II 20 019. Casablanca.\nLe « Client » est le titulaire du compte qui sera adossé à la carte internationale virtuelle., Le « Client » est le seul et unique bénéficiaire de la carte et de la\ndotation conformément à la réglementation édictée par l’Office des Changes.\n");
        SpanLabel spanLabel4 = new SpanLabel("La « Carte » signifie la carte internationale virtuelle « E-SHOPPING » émise par le Crédit Immobilier et Hôtelier.");
        spanLabel2.setTextUIID("Container");
        spanLabel2.setUIID("Container");
        spanLabel3.setTextUIID("Container");
        spanLabel3.setUIID("Container");
        spanLabel4.setTextUIID("Container");
        spanLabel4.setUIID("Container");
        spanLabel2.getTextAllStyles().setAlignment(1);
        spanLabel3.getTextAllStyles().setAlignment(1);
        spanLabel4.getTextAllStyles().setAlignment(1);
        spanLabel2.getAllStyles().setMarginUnit(1);
        spanLabel2.getAllStyles().setMargin(0, 3, 0, 0);
        spanLabel3.getAllStyles().setMarginUnit(1);
        spanLabel3.getAllStyles().setMargin(0, 3, 0, 0);
        spanLabel4.getAllStyles().setMarginUnit(1);
        spanLabel4.getAllStyles().setMargin(0, 3, 0, 0);
        Container container2 = new Container(new BorderLayout());
        SpanLabel spanLabel5 = new SpanLabel("J’accepte les conditions générales d’utilisation.");
        spanLabel5.setUIID("Container");
        spanLabel5.setTextUIID("Container");
        spanLabel5.getTextAllStyles().setAlignment(1);
        spanLabel5.getTextAllStyles().setFgColor(0);
        Button button = new Button("", "Container");
        button.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("EprgneUnCheck.png"));
        button.setName("off");
        button.getAllStyles().setPaddingUnit(1);
        button.getAllStyles().setPadding(1.0f, 0.0f, 0.5f, 3.0f);
        spanLabel5.getTextAllStyles().setAlignment(1);
        spanLabel5.getTextAllStyles().setFgColor(CIHStyles.colorBleu);
        spanLabel5.getTextAllStyles().setTextDecoration(1);
        button.addActionListener(new 4(button, spanLabel5));
        Button button2 = new Button("", "Container");
        button2.setText("Suivant\u200b");
        Container buttonStyle2 = CIHStyles.setButtonStyle2(button2, null);
        button2.addActionListener(new 5());
        Button button3 = new Button("", "Container");
        button3.addActionListener(new 6());
        Container container3 = new Container();
        container3.add(spanLabel5).add(button3);
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
        this.mainCntStep3.getAllStyles().setPaddingUnit(1);
        this.mainCntStep3.getAllStyles().setPadding(2, 0, 3, 3);
        return this.mainCntStep3;
    }

    class 4 implements ActionListener {
        final /* synthetic */ Button val$checkBtn;
        final /* synthetic */ SpanLabel val$checkSp;

        4(Button button, SpanLabel spanLabel) {
            this.val$checkBtn = button;
            this.val$checkSp = spanLabel;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$checkBtn.getName().equals("off")) {
                this.val$checkSp.getTextAllStyles().setTextDecoration(1);
                this.val$checkBtn.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("EprgneCheck.png"));
                OffreEShoppingPage.access$002(OffreEShoppingPage.this, true);
                this.val$checkBtn.setName("on");
                return;
            }
            this.val$checkBtn.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("EprgneUnCheck.png"));
            OffreEShoppingPage.access$002(OffreEShoppingPage.this, false);
            this.val$checkBtn.setName("off");
        }
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!OffreEShoppingPage.access$000(OffreEShoppingPage.this)) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreEShoppingPage.this.uiManager.stateMachine, "Veuillez accepter les conditions générales d’utilisation.", null);
                return;
            }
            ServiceResponse simulateEshop = OffreEShoppingPage.this.simulateEshop();
            if (simulateEshop.getStatusCode().equals("000")) {
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder("************** ACCOUNT TO BEBIT: ");
                SessionParameter sessionParameter = CihMBanking.sesPMTR;
                printStream.println(sb.append(SessionParameter.accountToDebitEShop.rib).toString());
                OffreEShoppingPage.this.uiManager.btnBack.getListeners().clear();
                OffreEShoppingPage.this.uiManager.btnBack.addActionListener(new OffreEShoppingPage$5$$ExternalSyntheticLambda0(this));
                OffreEShoppingPage.this.offerWizard.goToNextStep(OffreEShoppingPage.this.s3, "", 16777215, 0);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(simulateEshop.getStatusLabel()), null);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreEShoppingPage$5(ActionEvent actionEvent) {
            OffreEShoppingPage.this.offerWizard.goToPreviousStep(OffreEShoppingPage.this.s4, "", 16777215, 0);
            OffreEShoppingPage.this.uiManager.btnBack.getListeners().clear();
            OffreEShoppingPage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreEShoppingPage.this.offerWizard.goToPreviousStep(OffreEShoppingPage.this.s3, "", 16777215, 0);
                OffreEShoppingPage.this.uiManager.btnBack.getListeners().clear();
                OffreEShoppingPage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    OffreEShoppingPage.this.uiManager.GoBack();
                }
            }
        }
    }

    class 6 implements ActionListener {
        6() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().execute("https://www.cihnet.co.ma/Portals/_default/Skins/CIH/images/newSkinImgs/CGESHOPPING.pdf");
        }
    }

    private Container getStep5Cnt() {
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(BoxLayout.y());
        SpanLabel spanLabel = new SpanLabel("Confirmation de la souscription a une carte e-shopping virtuelle");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0, 3, 0, 0);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("bigOK.png"));
        label.getAllStyles().setPaddingUnit(1);
        label.getAllStyles().setPadding(1, 2, 0, 0);
        FlowLayout flowLayout = new FlowLayout(4);
        flowLayout.setFillRows(true);
        Container container3 = new Container(flowLayout);
        container3.add(label);
        SpanLabel spanLabel2 = new SpanLabel();
        spanLabel2.setText("Votre carte e-shopping a été crée avec succès!");
        spanLabel2.setTextUIID("Container");
        spanLabel2.setUIID("Container");
        spanLabel2.getTextAllStyles().setAlignment(4);
        spanLabel2.getAllStyles().setPaddingUnit(1);
        spanLabel2.getAllStyles().setPadding(1, 1, 10, 10);
        SpanLabel spanLabel3 = new SpanLabel();
        spanLabel3.setText("Retrouvez votre numéro de carte virtuelle depuis la rubrique « Mes cartes », et effectuez vos achats en toute sécurité.");
        spanLabel3.setTextUIID("Container");
        spanLabel3.setUIID("Container");
        spanLabel3.getTextAllStyles().setAlignment(4);
        spanLabel3.getAllStyles().setPaddingUnit(1);
        spanLabel3.getAllStyles().setPadding(1, 1, 10, 10);
        Label label2 = new Label();
        label2.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("bigCarteEShop.png"));
        label2.getAllStyles().setPaddingUnit(1);
        label2.getAllStyles().setPadding(1.5f, 1.5f, 0.0f, 0.0f);
        FlowLayout flowLayout2 = new FlowLayout(4);
        flowLayout2.setFillRows(true);
        Container container4 = new Container(flowLayout2);
        container4.add(label2);
        Button button = new Button();
        button.setText("  Recharger ma carte E-shopping  ");
        Container buttonStyle1 = CIHStyles.setButtonStyle1(button, CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("rechargeEShop.png"));
        Button button2 = new Button();
        button2.setText("Consulter les informations de la carte");
        Container buttonStyle12 = CIHStyles.setButtonStyle1(button2, null);
        button2.addActionListener(new 7());
        button.addActionListener(new 8());
        container2.add(spanLabel).add(container3).add(spanLabel2).add(spanLabel3).add(container4);
        container.add("Center", container2);
        container.add("South", BoxLayout.encloseY(buttonStyle1, buttonStyle12));
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2, 2, 3, 3);
        return container;
    }

    class 7 implements ActionListener {
        7() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreEShoppingPage.this.uiManager.NavigateToPage(new CardsListPage(OffreEShoppingPage.this.uiManager, null, 4));
        }
    }

    class 8 implements ActionListener {
        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreEShoppingPage.this.uiManager.NavigateToPage(new TopUpNewPage(OffreEShoppingPage.this.uiManager, null, 4));
        }
    }

    private Container getPoint(SpanLabel spanLabel, String str) {
        Container container = new Container(new BorderLayout());
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage(str));
        spanLabel.setTextUIID("Container");
        spanLabel.setUIID("Container");
        spanLabel.getTextAllStyles().setAlignment(1);
        spanLabel.getAllStyles().setPaddingUnit(1);
        spanLabel.getAllStyles().setPadding(0, 0, 1, 1);
        label.getAllStyles().setPaddingUnit(1);
        label.getAllStyles().setPadding(1.0f, 0.0f, 0.25f, 0.25f);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(0, 2, 0, 0);
        container.add("West", FlowLayout.encloseIn(label));
        container.add("Center", spanLabel);
        return container;
    }

    private Container GetTransferSecurityForm() {
        Container container = new Container(BoxLayout.y());
        Label label = new Label("Service sélectionné : ");
        label.setUIID("g_lblDashBoardTitleOrange");
        SpanLabel spanLabel = new SpanLabel("Souscrire à une carte E-shopping virtuelle");
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
        button.addActionListener(new 9());
        button2.addActionListener(new 10(b3GCIHEcode));
        container4.removeAll();
        container4.setLayout(BoxLayout.y());
        container4.add(b3GCIHEcode.getComponent());
        container.add(createContainer);
        return container;
    }

    class 9 implements ActionListener {
        9() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreEShoppingPage.this.offerWizard.goToPreviousStep(OffreEShoppingPage.this.s3, "", 16777215, 0);
        }
    }

    class 10 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        10(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String value = this.val$eCode1.getValue();
            if (value.length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreEShoppingPage.this.uiManager.stateMachine, "Veuillez saisir votre CIH E-CODE.", null);
            } else {
                OffreEShoppingPage.this.ShowDialogTransfertService(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, value);
            }
        }
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
        hashtable.put("ProductCode", "040");
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
            button.addActionListener(new 11(dialog));
            Button button2 = new Button("VALIDER");
            button2.setUIID("dg_BtnYestIconTR");
            button2.setTextPosition(3);
            button2.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ico_check_popup_NW.png"));
            button2.setVerticalAlignment(4);
            container2.add(button).add(button2);
            container2.getAllStyles().setPaddingUnit(1);
            container2.getAllStyles().setPadding(5, 0, 2, 2);
            button2.addActionListener(new 12(dialog, str));
            Label label = new Label("Compte à débiter :", "dg_lblPopupDetailItemTitle");
            SessionParameter sessionParameter = CihMBanking.sesPMTR;
            Label label2 = new Label(SessionParameter.accountToDebitEShop.rib, "dg_lblPopupDetailItemValueSP");
            container.add(label);
            container.add(label2);
            Label label3 = new Label("Montant (MAD) :", "dg_lblPopupDetailItemTitle");
            Label label4 = new Label("66", "dg_lblPopupDetailItemValueSP");
            label3.getAllStyles().setPaddingUnit(1);
            label3.getAllStyles().setPaddingTop(3);
            container.add(label3);
            container.add(label4);
            container.add(container2);
            container.setPreferredH(CihMBanking.sesPMTR.displayHeight);
            container.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
            container.getAllStyles().setPaddingUnit(1);
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

    class 11 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        11(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 12 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ String val$eCode;

        12(Dialog dialog, String str) {
            this.val$d = dialog;
            this.val$eCode = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            ServiceResponse confirmeEshop = OffreEShoppingPage.this.confirmeEshop(this.val$eCode);
            if (confirmeEshop.getStatusCode().equals("000")) {
                OffreEShoppingPage.this.uiManager.btnBack.getListeners().clear();
                OffreEShoppingPage.this.uiManager.btnBack.addActionListener(new OffreEShoppingPage$12$$ExternalSyntheticLambda0(this));
                OffreEShoppingPage.this.offerWizard.goToNextStep(OffreEShoppingPage.this.s4, "", 16777215, 0);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(confirmeEshop.getStatusLabel()), null);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreEShoppingPage$12(ActionEvent actionEvent) {
            OffreEShoppingPage.this.offerWizard.goToPreviousStep(OffreEShoppingPage.this.s5, "", 16777215, 0);
            OffreEShoppingPage.this.uiManager.btnBack.getListeners().clear();
            OffreEShoppingPage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreEShoppingPage.this.offerWizard.goToPreviousStep(OffreEShoppingPage.this.s4, "", 16777215, 0);
                OffreEShoppingPage.this.uiManager.btnBack.getListeners().clear();
                OffreEShoppingPage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    OffreEShoppingPage.this.uiManager.GoBack();
                }
            }
        }
    }
}
