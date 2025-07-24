package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.GetFormDataRetraite;
import com.b3g.services.ServiceManager;
import com.b3g.services.SimulatationRetraite;
import com.b3g.services.TransactionResume;
import com.b3g.services.TransfertDATA;
import com.b3g.tools.BenefRetraite;
import com.b3g.tools.DataTools;
import com.b3g.tools.FundsOrigin;
import com.b3g.tools.Model;
import com.b3g.tools.PayingAccount;
import com.b3g.tools.Periodicity;
import com.b3g.tools.QualiteBenef;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizard;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.Step;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.FocusListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import com.codename1.util.Base64;
import com.codename1.util.StringUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OffreRetaitePage extends B3GPage {
    Container YCnt;
    Container accountRecapStep7Cnt;
    Button addBenef;
    String[] ageList;
    Picker agePkr;
    Label ageVal;
    Double amount;
    boolean autreBenef;
    TextField autreOrigineTxtF;
    private byte[] b;
    Container benefCnt;
    ArrayList benefList;
    Label capitalVal;
    public Container cntTransfertForm;
    private boolean condition1;
    private boolean condition2;
    private boolean condition3;
    Container contractCnt;
    Label cotisationLblStep7;
    Periodicity currentPeriodicity;
    Picker dateBenefPkr;
    Label dateLblStep7;
    SimpleDateFormat df;
    String fileName;
    Container formBenefCnt;
    ImageViewer imageViewer;
    GetFormDataRetraite initDadaStep1;
    GetFormDataRetraite initDataStep2;
    boolean isScrolled;
    private boolean isTestMode;
    Container listBenefCnt;
    Container mainCenterRecapStep7Cnt;
    Container mainCntStep3;
    TextField montantTxtfStep3;
    Label montantVal;
    TextField nomBenefTxtF;
    B3GWizard offerWizard;
    Label origienLblStep7;
    Picker originePkr;
    Label periodiciteLblStep7;
    Picker periodicitrPkr;
    TextField prenomBenefTxtF;
    Label produitVal;
    Picker qualiteBenefPkr;
    String[] qualiteBenefs;
    TextField quoteBenefTxtF;
    public Container resultCnt;
    Step s1;
    Step s10;
    Step s2;
    Step s3;
    Step s4;
    Step s5;
    Step s6;
    Step s7;
    Step s8;
    Step s9;
    StringBuilder sb;
    boolean scrolled = false;
    private Button suivantBtn;
    String[] tab1;
    String[] tab2;
    String[] tab3;
    Double tottalParts;
    TransfertDATA trDT;
    private UIBuilder uib;
    Label versementInitialLblStep7;
    TextField versementInitialTxtF;
    TextField versementPeriodiqueTxtF;

    static /* synthetic */ boolean access$000(OffreRetaitePage offreRetaitePage) {
        return offreRetaitePage.isTestMode;
    }

    static /* synthetic */ void access$100(OffreRetaitePage offreRetaitePage) {
        offreRetaitePage.getStep2Subscription();
    }

    static /* synthetic */ void access$1000(OffreRetaitePage offreRetaitePage) {
        offreRetaitePage.clearBenefForm();
    }

    static /* synthetic */ boolean access$1100(OffreRetaitePage offreRetaitePage) {
        return offreRetaitePage.validateAddBenefForm();
    }

    static /* synthetic */ QualiteBenef access$1200(OffreRetaitePage offreRetaitePage, int i) {
        return offreRetaitePage.getQualiteBenefByValue(i);
    }

    static /* synthetic */ void access$1300(OffreRetaitePage offreRetaitePage) {
        offreRetaitePage.getStep7Config();
    }

    static /* synthetic */ void access$1400(OffreRetaitePage offreRetaitePage, String str, int i, Button button) {
        offreRetaitePage.showHelpPopup(str, i, button);
    }

    static /* synthetic */ boolean access$1500(OffreRetaitePage offreRetaitePage) {
        return offreRetaitePage.condition1;
    }

    static /* synthetic */ boolean access$1502(OffreRetaitePage offreRetaitePage, boolean z) {
        offreRetaitePage.condition1 = z;
        return z;
    }

    static /* synthetic */ boolean access$1600(OffreRetaitePage offreRetaitePage) {
        return offreRetaitePage.condition2;
    }

    static /* synthetic */ boolean access$1602(OffreRetaitePage offreRetaitePage, boolean z) {
        offreRetaitePage.condition2 = z;
        return z;
    }

    static /* synthetic */ boolean access$1700(OffreRetaitePage offreRetaitePage) {
        return offreRetaitePage.condition3;
    }

    static /* synthetic */ boolean access$1702(OffreRetaitePage offreRetaitePage, boolean z) {
        offreRetaitePage.condition3 = z;
        return z;
    }

    static /* synthetic */ Button access$1800(OffreRetaitePage offreRetaitePage) {
        return offreRetaitePage.suivantBtn;
    }

    static /* synthetic */ void access$1900(OffreRetaitePage offreRetaitePage, Button button, boolean z) {
        offreRetaitePage.toggleStyle(button, z);
    }

    static /* synthetic */ void access$200(OffreRetaitePage offreRetaitePage) {
        offreRetaitePage.getStep2SubscriptionTestMode();
    }

    static /* synthetic */ boolean access$300(OffreRetaitePage offreRetaitePage, Double d) {
        return offreRetaitePage.isMultiple100(d);
    }

    static /* synthetic */ void access$400(OffreRetaitePage offreRetaitePage) {
        offreRetaitePage.getStep4TestModeConfig();
    }

    static /* synthetic */ void access$500(OffreRetaitePage offreRetaitePage) {
        offreRetaitePage.getStep4Config();
    }

    static /* synthetic */ void access$600(OffreRetaitePage offreRetaitePage) {
        offreRetaitePage.getStep1TestModeConfig();
    }

    static /* synthetic */ void access$700(OffreRetaitePage offreRetaitePage) {
        offreRetaitePage.getStep1Config();
    }

    static /* synthetic */ void access$800(OffreRetaitePage offreRetaitePage) {
        offreRetaitePage.getStep5TestModeConfig();
    }

    static /* synthetic */ void access$900(OffreRetaitePage offreRetaitePage) {
        offreRetaitePage.getStep5Config();
    }

    public OffreRetaitePage(Object obj, Object obj2, int i) {
        Double valueOf = Double.valueOf(0.0d);
        this.tottalParts = valueOf;
        this.sb = new StringBuilder();
        this.df = new SimpleDateFormat("yyyy-MM-dd");
        this.initDadaStep1 = null;
        this.initDataStep2 = null;
        this.qualiteBenefs = new String[]{"qualite 1", "qualite 2", "qualite 3"};
        this.benefList = new ArrayList();
        this.autreBenef = false;
        this.amount = valueOf;
        this.isScrolled = false;
        this.b = new byte[]{1, 1, 1, 1};
        this.condition1 = false;
        this.condition2 = false;
        this.condition3 = false;
        this.uib = new UIBuilder();
        this.ageList = new String[]{"45", "50", "55", "60"};
        this.isTestMode = false;
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
            this.s1.stepCnt = getNewStep1();
            Step step2 = new Step();
            this.s2 = step2;
            step2.icon = "2inactif.png";
            this.s2.selectedIcon = "2actif.png";
            this.s2.stepCnt = getNewStep2();
            Step step3 = new Step();
            this.s3 = step3;
            step3.icon = "2inactif.png";
            this.s3.selectedIcon = "2actif.png";
            this.s3.stepCnt = getNewStep3();
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.s1);
            arrayList.add(this.s2);
            arrayList.add(this.s3);
            B3GWizard b3GWizard = new B3GWizard(arrayList);
            this.offerWizard = b3GWizard;
            b3GWizard.withHeader = false;
            this.thisContainer = this.offerWizard.drawWizard("", 16777215, 0);
        }
        this.thisContainer.getAllStyles().setBgColor(16382713);
        this.thisContainer.getAllStyles().setBgTransparency(255);
        this.thisContainer.getAllStyles().setPadding(0, 2, 0, 0);
        this.thisContainer.getAllStyles().setPaddingUnit(2, 2, 2, 2);
        return this.thisContainer;
    }

    private Container getNewStep1() {
        SpanLabel spanLabel = new SpanLabel("Avenir Retraite CIH-BANK");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(1.0f, 1.5f, 0.0f, 0.0f);
        Container container = new Container(new BorderLayout());
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1, 1, 3, 3);
        container.getAllStyles().setBgColor(16777215);
        container.getAllStyles().setBgTransparency(255);
        Label label = new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("retirment-cih.png"));
        label.getAllStyles().setAlignment(4);
        Label label2 = new Label("« Avenir Retraite » c’est:");
        label2.getAllStyles().setPaddingUnit(1);
        label2.getAllStyles().setPadding(1, 1, 2, 2);
        SpanLabel spanLabel2 = new SpanLabel("Consulter les Conditions Générales");
        Style allStyles = spanLabel2.getAllStyles();
        Float valueOf = Float.valueOf(2.0f);
        allStyles.setFont(CIHStyles.getFont(valueOf));
        spanLabel2.getTextAllStyles().setAlignment(1);
        spanLabel2.getTextAllStyles().setFgColor(CIHStyles.colorBleu);
        spanLabel2.getTextAllStyles().setTextDecoration(1);
        spanLabel2.getAllStyles().setMarginUnit(1);
        spanLabel2.getAllStyles().setMargin(1, 0, 0, 0);
        SpanLabel spanLabel3 = new SpanLabel("Guide de souscription en ligne");
        spanLabel3.getAllStyles().setFont(CIHStyles.getFont(valueOf));
        spanLabel3.getTextAllStyles().setAlignment(1);
        spanLabel3.getTextAllStyles().setFgColor(CIHStyles.colorBleu);
        spanLabel3.getTextAllStyles().setTextDecoration(1);
        spanLabel3.getAllStyles().setMarginUnit(1);
        spanLabel3.getAllStyles().setMargin(1, 2, 0, 0);
        Button button = new Button("JE FAIS MA SIMULATION", "op_BtnOppositionValidationOrgBg");
        Button button2 = new Button("JE SOUSCRIS À AVENIR RETRAITE", "op_BtnOppositionValidationOrgBg");
        Style allStyles2 = button.getAllStyles();
        Float valueOf2 = Float.valueOf(2.5f);
        allStyles2.setFont(CIHStyles.getFont(valueOf2));
        button2.getAllStyles().setFont(CIHStyles.getFont(valueOf2));
        button.getAllStyles().setMarginUnit(1);
        button.getAllStyles().setMarginBottom(2);
        Container container2 = new Container(BoxLayout.y());
        container2.add(getPoint(new SpanLabel("Une cadence d’épargne adaptée à votre rythme: des cotisations mensuelles démarrant à partir de 200 DHS avec la possibilité de réaliser, à tout moment, des versements libres."), "point1.png", 0)).add(getPoint(new SpanLabel("Un contrat accessible: Possibilité de réaliser, à tout moment, des rachats partiels."), "point1.png", 0)).add(getPoint(new SpanLabel("Des modalités de sortie flexibles: liberté de sortie en capital ou en rente."), "point1.png", 0)).add(getPoint(new SpanLabel("Des avantages fiscaux intéressants: Possibilité, à l’entrée, de déduction des cotisations du revenu global imposable et ce avec l’application d’abattement fiscal sur l’épargne constituée à la sortie."), "point1.png", 0)).add(getPoint(new SpanLabel("Un revenu pour toute la famille grâce à la pension de réversion versée à votre conjoint et à vos enfants en cas de décès."), "point1.png", 1));
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(0, 0, 4, 2);
        Container container3 = new Container(BoxLayout.y());
        container3.setScrollableY(true);
        container3.setScrollVisible(false);
        container3.add(label).add(label2).add(container2);
        container.add("North", BoxLayout.encloseY(CIHStyles.hBorder(CIHStyles.colorOrange), spanLabel));
        container.add("Center", container3);
        container.add("South", BoxLayout.encloseY(button));
        button.addActionListener(new 1());
        button2.addActionListener(new 2(container3, spanLabel3));
        return container;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (OffreRetaitePage.this.initDadaStep1 == null) {
                ServiceResponse formDataProcess = OffreRetaitePage.this.getFormDataProcess("1");
                if (formDataProcess.getStatusCode().equals("000")) {
                    OffreRetaitePage.this.initDadaStep1 = new GetFormDataRetraite();
                    OffreRetaitePage.this.initDadaStep1.FillDataForm1FromServiceResponse(formDataProcess.getParamsOut());
                    OffreRetaitePage.this.agePkr.setStrings(OffreRetaitePage.this.initDadaStep1.ageList);
                    OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
                    OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$1$$ExternalSyntheticLambda0(this));
                    OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s1, "", 16777215, 0);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Le service est momentanément indisponible. Veuillez réessayer ultérieurement.", null);
                return;
            }
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$1$$ExternalSyntheticLambda1(this));
            OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s1, "", 16777215, 0);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$1(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s2, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-OffreRetaitePage$1(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s2, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 2());
        }

        class 2 implements ActionListener {
            2() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ SpanLabel val$guideSp;
        final /* synthetic */ Container val$mainCenterCnt;

        2(Container container, SpanLabel spanLabel) {
            this.val$mainCenterCnt = container;
            this.val$guideSp = spanLabel;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!OffreRetaitePage.this.scrolled) {
                this.val$mainCenterCnt.scrollComponentToVisible(this.val$guideSp);
                OffreRetaitePage.this.scrolled = true;
            } else {
                if (OffreRetaitePage.this.initDataStep2 == null) {
                    if (!OffreRetaitePage.access$000(OffreRetaitePage.this)) {
                        OffreRetaitePage.access$100(OffreRetaitePage.this);
                        return;
                    } else {
                        OffreRetaitePage.access$200(OffreRetaitePage.this);
                        return;
                    }
                }
                OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
                OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$2$$ExternalSyntheticLambda0(this));
                OffreRetaitePage.this.offerWizard.goToStepById(OffreRetaitePage.this.offerWizard.getStepById(OffreRetaitePage.this.offerWizard.currentStepId), 4, "", 16777215, 0);
            }
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$2(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToStepById(OffreRetaitePage.this.offerWizard.getStepById(OffreRetaitePage.this.offerWizard.currentStepId), 1, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    private Container getNewStep2() {
        Container container = new Container(new BorderLayout());
        container.setScrollableY(true);
        container.setScrollVisible(false);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(2, 2, 2, 2);
        SpanLabel spanLabel = new SpanLabel("Simulation Capital");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        spanLabel.getAllStyles().setPaddingLeft(0);
        Picker picker = new Picker();
        this.agePkr = picker;
        Container drawPicker = drawPicker(picker, 4, new String[]{"45", "50", "55", "60"}, "Âge de départ à la retraite");
        Picker picker2 = new Picker();
        Container drawPicker2 = drawPicker(picker2, 4, new String[]{"Avenir retraite"}, "Sélectionnez un Produit");
        TextField textField = new TextField();
        this.montantTxtfStep3 = textField;
        textField.getAllStyles().setBorder(Border.createEmpty());
        this.montantTxtfStep3.setHint("Min 200 Dhs");
        this.montantTxtfStep3.getHintLabel().getAllStyles().setPaddingUnit(1);
        this.montantTxtfStep3.getHintLabel().getAllStyles().setMarginUnit(1);
        this.montantTxtfStep3.getHintLabel().getAllStyles().setMarginUnit(1);
        this.montantTxtfStep3.getHintLabel().getAllStyles().setMargin(0, 0, 0, 0);
        this.montantTxtfStep3.getHintLabel().getAllStyles().setPadding(0, 0, 0, 0);
        this.montantTxtfStep3.setConstraint(2);
        this.montantTxtfStep3.addFocusListener(new 3());
        Label label = new Label("Produit *:");
        Label label2 = new Label("Âge de départ à la retraite *:");
        Label label3 = new Label("Montant de la cotisation mensuelle *:");
        Button button = new Button("SIMULER", "op_BtnOppositionValidationOrgBg");
        button.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.5f)));
        button.addActionListener(new 4(picker2));
        Container container2 = new Container(BoxLayout.y());
        container2.getAllStyles().setMarginUnit(1);
        container2.getAllStyles().setMargin(2, 2, 2, 2);
        Container container3 = new Container(new BorderLayout());
        container3.add("Center", this.montantTxtfStep3);
        container3.add("East", new Label(" MAD"));
        container3.setLeadComponent(this.montantTxtfStep3);
        container3.getAllStyles().setBorder(Border.createLineBorder(2, 0));
        container3.getAllStyles().setPaddingUnit(1);
        container3.getAllStyles().setPadding(0.3f, 0.1f, 2.0f, 2.0f);
        container3.getAllStyles().setMarginUnit(1);
        container3.getAllStyles().setMargin(1, 1, 1, 1);
        container2.add(spanLabel).add(label).add(drawPicker2).add(label2).add(drawPicker).add(label3).add(container3);
        container.add("Center", container2);
        Container container4 = new Container(BoxLayout.y());
        Label label4 = new Label("* : Champs obligatoires");
        label4.getAllStyles().setFgColor(CIHStyles.colorOrange);
        label4.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.4f)));
        label4.getAllStyles().setMarginUnit(1);
        label4.getAllStyles().setMarginBottom(1.4f);
        container4.add(label4).add(button);
        container.add("South", container4);
        return container;
    }

    class 3 implements FocusListener {
        public void focusGained(Component component) {
        }

        3() {
        }

        public void focusLost(Component component) {
            try {
                OffreRetaitePage offreRetaitePage = OffreRetaitePage.this;
                offreRetaitePage.amount = Double.valueOf(Double.parseDouble(offreRetaitePage.montantTxtfStep3.getText()));
                if (OffreRetaitePage.this.amount.doubleValue() < 200.0d) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Le montant minimun autorisé est 200 Dhs", null);
                }
            } catch (Exception unused) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Le montant minimun autorisé est 200 Dhs", null);
            }
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Picker val$produitPkr;

        4(Picker picker) {
            this.val$produitPkr = picker;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Integer num;
            try {
                num = Integer.valueOf(Integer.parseInt(OffreRetaitePage.this.agePkr.getSelectedString()));
            } catch (Exception unused) {
                num = 0;
            }
            if (OffreRetaitePage.this.agePkr.getSelectedStringIndex() == -1 && this.val$produitPkr.getSelectedStringIndex() == -1 && OffreRetaitePage.this.montantTxtfStep3.getText().equals("")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Veuillez renseigner les champs obligatoires.", null);
                return;
            }
            if (OffreRetaitePage.this.agePkr.getSelectedStringIndex() == -1) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Veuillez sélectionner votre âge de départ à la retraite.", null);
                return;
            }
            if (this.val$produitPkr.getSelectedStringIndex() == -1) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Veuillez sélectionner un produit.", null);
                return;
            }
            if (num.intValue() < CihMBanking.sesPMTR.getSessionClient().getClient_Profil().age) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "L'âge de départ à la retraite doit être supérieur à votre âge.", null);
                return;
            }
            if (OffreRetaitePage.this.montantTxtfStep3.getText().equals("")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Veuillez saisir une mensualité.", null);
                return;
            }
            if (OffreRetaitePage.this.amount.doubleValue() < 200.0d) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Le montant minimun autorisé est 200 Dhs.", null);
                return;
            }
            OffreRetaitePage offreRetaitePage = OffreRetaitePage.this;
            if (!OffreRetaitePage.access$300(offreRetaitePage, Double.valueOf(Double.parseDouble(offreRetaitePage.montantTxtfStep3.getText())))) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Veuillez saisir un montant multiple de 100 Dhs.", null);
                return;
            }
            if (OffreRetaitePage.access$000(OffreRetaitePage.this)) {
                OffreRetaitePage.this.produitVal.setText(this.val$produitPkr.getSelectedString());
                OffreRetaitePage.this.ageVal.setText(OffreRetaitePage.this.agePkr.getSelectedString() + " ans");
                OffreRetaitePage.this.montantVal.setText(OffreRetaitePage.this.montantTxtfStep3.getText() + " MAD");
                OffreRetaitePage.this.capitalVal.setText("20 000");
                OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
                OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$4$$ExternalSyntheticLambda0(this));
                OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s2, "", 16777215, 0);
                return;
            }
            Integer valueOf = Integer.valueOf(num.intValue() - CihMBanking.sesPMTR.getSessionClient().getClient_Profil().age);
            OffreRetaitePage offreRetaitePage2 = OffreRetaitePage.this;
            ServiceResponse simulationRetraiteProcess = offreRetaitePage2.simulationRetraiteProcess(offreRetaitePage2.agePkr.getSelectedString(), OffreRetaitePage.this.montantTxtfStep3.getText(), OffreRetaitePage.this.agePkr.getSelectedString(), valueOf.toString());
            if (simulationRetraiteProcess.getStatusCode().equals("000")) {
                OffreRetaitePage.this.produitVal.setText(this.val$produitPkr.getSelectedString());
                OffreRetaitePage.this.ageVal.setText(OffreRetaitePage.this.agePkr.getSelectedString() + " ans");
                OffreRetaitePage.this.montantVal.setText(OffreRetaitePage.this.montantTxtfStep3.getText() + " MAD");
                SimulatationRetraite simulatationRetraite = new SimulatationRetraite();
                simulatationRetraite.FillSimulationDataFromServiceResponse(simulationRetraiteProcess.getParamsOut());
                OffreRetaitePage.this.capitalVal.setText(DataTools.formatNumber(simulatationRetraite.CapitalConstitue));
                OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
                OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$4$$ExternalSyntheticLambda1(this));
                OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s2, "", 16777215, 0);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Le service est momentanément indisponible. Veuillez réessayer ultérieurement.", null);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$4(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s3, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-OffreRetaitePage$4(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s3, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 2());
        }

        class 2 implements ActionListener {
            2() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    private Container getNewStep3() {
        Container container = new Container(new BorderLayout());
        container.setScrollableY(true);
        container.setScrollVisible(false);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(2, 2, 2, 2);
        SpanLabel spanLabel = new SpanLabel("Résultat de ma simulation");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        spanLabel.getAllStyles().setPaddingLeft(0);
        Container container2 = new Container(BoxLayout.y());
        this.resultCnt = container2;
        container2.getAllStyles().setBorder(Border.createLineBorder(2, 15000804));
        this.resultCnt.getAllStyles().setPaddingUnit(1);
        this.resultCnt.getAllStyles().setPadding(3, 0, 2, 2);
        Container container3 = new Container(new BorderLayout());
        Container container4 = new Container(new BorderLayout());
        Container container5 = new Container(new BorderLayout());
        container4.getAllStyles().setPaddingUnit(1);
        container4.getAllStyles().setPaddingTop(2);
        container5.getAllStyles().setPaddingUnit(1);
        container5.getAllStyles().setPaddingTop(2);
        Label label = new Label("Produit :");
        Label label2 = new Label("Âge de départ à la retraite :");
        Label label3 = new Label("Mensualité :");
        Label label4 = new Label();
        this.produitVal = label4;
        label4.getAllStyles().setFgColor(CIHStyles.colorOrange);
        Label label5 = new Label();
        this.ageVal = label5;
        label5.getAllStyles().setFgColor(CIHStyles.colorOrange);
        Label label6 = new Label();
        this.montantVal = label6;
        label6.getAllStyles().setFgColor(CIHStyles.colorOrange);
        Container container6 = new Container(BoxLayout.y());
        container6.getAllStyles().setBorder(Border.createLineBorder(2, CIHStyles.colorOrange));
        container6.getAllStyles().setBgColor(15922423);
        container6.getAllStyles().setBgTransparency(255);
        container6.getAllStyles().setPaddingUnit(this.b);
        container6.getAllStyles().setPadding(3, 3, 2, 2);
        container6.getAllStyles().setMarginUnit(this.b);
        container6.getAllStyles().setMargin(3, 3, 2, 2);
        Label label7 = new Label("Capital Brut à l’âge de retraite");
        label7.getAllStyles().setFgColor(CIHStyles.colorBleu);
        Label label8 = new Label();
        this.capitalVal = label8;
        label8.getAllStyles().setFgColor(CIHStyles.colorOrange);
        this.capitalVal.getAllStyles().setPaddingUnit(this.b);
        Style allStyles = this.capitalVal.getAllStyles();
        Float valueOf = Float.valueOf(2.0f);
        allStyles.setPadding(0.0f, 0.0f, 2.0f, 0.0f);
        Label label9 = new Label("Dhs");
        label9.getAllStyles().setFgColor(CIHStyles.colorOrange);
        this.capitalVal.getAllStyles().setPaddingUnit(this.b);
        this.capitalVal.getAllStyles().setPadding(0.0f, 0.0f, 0.0f, 2.0f);
        Button button = new Button();
        button.setUIID("Container");
        button.getAllStyles().setFont(CIHStyles.getFont(valueOf));
        button.setText("JE REFAIS MA SIMULATION");
        button.setUIID("Container");
        button.setAlignment(4);
        button.getAllStyles().setFgColor(CIHStyles.colorOrange);
        button.getAllStyles().setBorder(Border.createLineBorder(2, CIHStyles.colorOrange));
        button.getAllStyles().setPaddingUnit(1);
        button.getAllStyles().setPadding(1, 1, 1, 1);
        button.getAllStyles().setMarginUnit(1);
        button.getAllStyles().setMarginBottom(2);
        button.addActionListener(new 5());
        Button button2 = new Button("JE SOUSCRIS À AVENIR RETRAITE", "op_BtnOppositionValidationOrgBg");
        button2.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.5f)));
        button2.addActionListener(new 6());
        SpanLabel spanLabel2 = new SpanLabel("Pour souscrire à cette offre, merci de contacter votre gestionnaire CIH BANK");
        spanLabel2.getTextAllStyles().setFont(CIHStyles.getFont(valueOf, 1));
        spanLabel2.getTextAllStyles().setFgColor(1945583);
        spanLabel2.getAllStyles().setMarginUnit(this.b);
        spanLabel2.getAllStyles().setMargin(0.0f, 2.0f, 0.0f, 0.0f);
        Container encloseCenterMiddle = FlowLayout.encloseCenterMiddle(this.capitalVal, label9);
        encloseCenterMiddle.getAllStyles().setPaddingUnit(this.b);
        encloseCenterMiddle.getAllStyles().setPadding(1.0f, 1.0f, 0.0f, 0.0f);
        container3.add("Center", label);
        container3.add("East", this.produitVal);
        container4.add("Center", label2);
        container4.add("East", this.ageVal);
        container5.add("Center", label3);
        container5.add("East", this.montantVal);
        container6.add(FlowLayout.encloseCenterMiddle(label7)).add(encloseCenterMiddle);
        this.resultCnt.add(container3).add(container4).add(container5).add(container6);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(0, 0, 2, 2);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setFillRows(true);
        Container container7 = new Container(flowLayout);
        container7.add(this.resultCnt);
        container.add("North", spanLabel);
        container.add("Center", container7);
        container.add("South", BoxLayout.encloseY(spanLabel2, button));
        return container;
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s3, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    class 6 implements ActionListener {
        6() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.versementPeriodiqueTxtF.setText(OffreRetaitePage.this.montantTxtfStep3.getText());
            if (OffreRetaitePage.access$000(OffreRetaitePage.this)) {
                OffreRetaitePage.access$400(OffreRetaitePage.this);
            } else {
                OffreRetaitePage.access$500(OffreRetaitePage.this);
            }
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$6$$ExternalSyntheticLambda0(this));
            OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s4, "", 16777215, 0);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$6(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s5, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    private Container getStep1Cnt() {
        Container container = new Container(BoxLayout.y());
        container.setScrollableY(true);
        container.setScrollVisible(false);
        container.getAllStyles().setBgColor(15856113);
        container.getAllStyles().setBgTransparency(255);
        SpanLabel spanLabel = new SpanLabel("Découvrir nos plans de retraite");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        SpanLabel spanLabel2 = new SpanLabel("CIH BANK vous propose l’offre « Avenir Retraite » qui vous permet de préparer votre retraite en fonction de vos objectifs en termes de pension et de sécurisation de l’avenir de votre famille.");
        spanLabel2.getTextAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.0f)));
        spanLabel2.getTextAllStyles().setAlignment(1);
        spanLabel2.getAllStyles().setPaddingUnit(1);
        spanLabel2.getAllStyles().setPadding(0, 4, 1, 1);
        Button button = new Button("    Je découvre    ", "op_BtnOppositionValidationOrgBg");
        Button button2 = new Button("    Je découvre    ", "op_BtnOppositionValidationOrgBg");
        Style allStyles = button.getAllStyles();
        Float valueOf = Float.valueOf(2.5f);
        allStyles.setFont(CIHStyles.getFont(valueOf));
        button2.getAllStyles().setFont(CIHStyles.getFont(valueOf));
        button.addActionListener(new 7());
        button2.addActionListener(new OffreRetaitePage$$ExternalSyntheticLambda1(this));
        button.getAllStyles().setMarginUnit(1);
        button.getAllStyles().setMarginBottom(1);
        button2.getAllStyles().setMarginUnit(1);
        button2.getAllStyles().setMarginBottom(1);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2, 0, 3, 3);
        ArrayList arrayList = new ArrayList();
        arrayList.add("Une épargne progressive accessible à tout moment.");
        arrayList.add("Des avantages fiscaux sur les cotisations et sur le capital constitué à l’échéance de votre contrat.");
        arrayList.add("Un taux de rendement attractif.");
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("Une Epargne retraite sécurisée accessible à partir de 50 ans.");
        arrayList2.add("Des avantages fiscaux sur les cotisations et sur le capital constitué à l’échéance de votre contrat.");
        container.add(spanLabel);
        container.add(spanLabel2);
        container.add(getOffreStep1("Avenir retraite – CIH Bank", CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("retraiteCIH.png"), button, "« Avenir Retraite » c’est:", arrayList));
        container.add(getOffreStep1("Al Moustakbal Individuel - CIMR\u200b", CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("retraiteCIMR.png"), button2, "« AL MOUSTAKBAL INDIVIDUEL » c’est:", arrayList2));
        return container;
    }

    class 7 implements ActionListener {
        7() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (OffreRetaitePage.this.initDadaStep1 == null) {
                if (OffreRetaitePage.access$000(OffreRetaitePage.this)) {
                    OffreRetaitePage.access$600(OffreRetaitePage.this);
                    return;
                } else {
                    OffreRetaitePage.access$700(OffreRetaitePage.this);
                    return;
                }
            }
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$7$$ExternalSyntheticLambda0(this));
            OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s1, "", 16777215, 0);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$7(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s2, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    /* synthetic */ void lambda$getStep1Cnt$0$com-b3g-ui-page-OffreRetaitePage(ActionEvent actionEvent) {
        this.uiManager.NavigateToPageById(170);
        System.out.println("Test CIMR btn");
    }

    private void getStep1Config() {
        ServiceResponse formDataProcess = getFormDataProcess("1");
        if (formDataProcess.getStatusCode().equals("000")) {
            GetFormDataRetraite getFormDataRetraite = new GetFormDataRetraite();
            this.initDadaStep1 = getFormDataRetraite;
            getFormDataRetraite.FillDataForm1FromServiceResponse(formDataProcess.getParamsOut());
            this.agePkr.setStrings(this.initDadaStep1.ageList);
            this.uiManager.btnBack.getListeners().clear();
            this.uiManager.btnBack.addActionListener(new OffreRetaitePage$$ExternalSyntheticLambda0(this));
            this.offerWizard.goToNextStep(this.s1, "", 16777215, 0);
            return;
        }
        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Une erreur est survenue.", null);
    }

    /* synthetic */ void lambda$getStep1Config$1$com-b3g-ui-page-OffreRetaitePage(ActionEvent actionEvent) {
        this.offerWizard.goToPreviousStep(this.s2, "", 16777215, 0);
        this.uiManager.btnBack.getListeners().clear();
        this.uiManager.btnBack.addActionListener(new 8());
    }

    class 8 implements ActionListener {
        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.goBack();
        }
    }

    private void getStep1TestModeConfig() {
        this.agePkr.setStrings(this.ageList);
        this.uiManager.btnBack.getListeners().clear();
        this.uiManager.btnBack.addActionListener(new OffreRetaitePage$$ExternalSyntheticLambda5(this));
        this.offerWizard.goToNextStep(this.s1, "", 16777215, 0);
    }

    /* synthetic */ void lambda$getStep1TestModeConfig$2$com-b3g-ui-page-OffreRetaitePage(ActionEvent actionEvent) {
        this.offerWizard.goToPreviousStep(this.s2, "", 16777215, 0);
        this.uiManager.btnBack.getListeners().clear();
        this.uiManager.btnBack.addActionListener(new 9());
    }

    class 9 implements ActionListener {
        9() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.goBack();
        }
    }

    private Container getStep2Cnt() {
        SpanLabel spanLabel = new SpanLabel("Avenir Retraite CIH-BANK");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        Container container = new Container(new BorderLayout());
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1, 1, 3, 3);
        Label label = new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("retraiteCIH_BIG.png"));
        label.getAllStyles().setAlignment(4);
        Label label2 = new Label("« Avenir Retraite » c’est:");
        label2.getAllStyles().setPaddingUnit(1);
        label2.getAllStyles().setPadding(1, 1, 2, 2);
        SpanLabel spanLabel2 = new SpanLabel("Consulter les Conditions Générales");
        Style allStyles = spanLabel2.getAllStyles();
        Float valueOf = Float.valueOf(2.0f);
        allStyles.setFont(CIHStyles.getFont(valueOf));
        spanLabel2.getTextAllStyles().setAlignment(1);
        spanLabel2.getTextAllStyles().setFgColor(CIHStyles.colorBleu);
        spanLabel2.getTextAllStyles().setTextDecoration(1);
        spanLabel2.getAllStyles().setMarginUnit(1);
        spanLabel2.getAllStyles().setMargin(1, 0, 0, 0);
        SpanLabel spanLabel3 = new SpanLabel("Guide de souscription en ligne");
        spanLabel3.getAllStyles().setFont(CIHStyles.getFont(valueOf));
        spanLabel3.getTextAllStyles().setAlignment(1);
        spanLabel3.getTextAllStyles().setFgColor(CIHStyles.colorBleu);
        spanLabel3.getTextAllStyles().setTextDecoration(1);
        spanLabel3.getAllStyles().setMarginUnit(1);
        spanLabel3.getAllStyles().setMargin(1, 2, 0, 0);
        Button button = new Button("JE FAIS MA SIMULATION", "op_BtnOppositionValidationOrgBg");
        Button button2 = new Button("JE SOUSCRIS À AVENIR RETRAITE", "op_BtnOppositionValidationOrgBg");
        Style allStyles2 = button.getAllStyles();
        Float valueOf2 = Float.valueOf(2.5f);
        allStyles2.setFont(CIHStyles.getFont(valueOf2));
        button2.getAllStyles().setFont(CIHStyles.getFont(valueOf2));
        button.getAllStyles().setMarginUnit(1);
        button.getAllStyles().setMarginBottom(2);
        Container container2 = new Container(BoxLayout.y());
        container2.add(getPoint(new SpanLabel("Une cadence d’épargne adaptée à votre rythme: des cotisations mensuelles démarrant à partir de 200 DHS avec la possibilité de réaliser, à tout moment, des versements libres."), "point1.png", 0)).add(getPoint(new SpanLabel("Un contrat accessible: Possibilité de réaliser, à tout moment, des rachats partiels."), "point1.png", 0)).add(getPoint(new SpanLabel("Des modalités de sortie flexibles: liberté de sortie en capital ou en rente."), "point1.png", 0)).add(getPoint(new SpanLabel("Des avantages fiscaux intéressants: Possibilité, à l’entrée, de déduction des cotisations du revenu global imposable et ce avec l’application d’abattement fiscal sur l’épargne constituée à la sortie."), "point1.png", 1));
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(0, 0, 4, 2);
        Container container3 = new Container(BoxLayout.y());
        container3.setScrollableY(true);
        container3.setScrollVisible(false);
        container3.add(label).add(label2).add(container2);
        container.add("North", spanLabel);
        container.add("Center", container3);
        container.add("South", BoxLayout.encloseY(button, button2));
        button.addActionListener(new 10(container3, spanLabel3));
        button2.addActionListener(new 11(container3, spanLabel3));
        return container;
    }

    class 10 implements ActionListener {
        final /* synthetic */ SpanLabel val$guideSp;
        final /* synthetic */ Container val$mainCenterCnt;

        10(Container container, SpanLabel spanLabel) {
            this.val$mainCenterCnt = container;
            this.val$guideSp = spanLabel;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!OffreRetaitePage.this.scrolled) {
                this.val$mainCenterCnt.scrollComponentToVisible(this.val$guideSp);
                OffreRetaitePage.this.scrolled = true;
            } else {
                OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
                OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$10$$ExternalSyntheticLambda0(this));
                OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s2, "", 16777215, 0);
            }
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$10(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s3, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    class 11 implements ActionListener {
        final /* synthetic */ SpanLabel val$guideSp;
        final /* synthetic */ Container val$mainCenterCnt;

        11(Container container, SpanLabel spanLabel) {
            this.val$mainCenterCnt = container;
            this.val$guideSp = spanLabel;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!OffreRetaitePage.this.scrolled) {
                this.val$mainCenterCnt.scrollComponentToVisible(this.val$guideSp);
                OffreRetaitePage.this.scrolled = true;
            } else {
                if (OffreRetaitePage.this.initDataStep2 == null) {
                    if (!OffreRetaitePage.access$000(OffreRetaitePage.this)) {
                        OffreRetaitePage.access$100(OffreRetaitePage.this);
                        return;
                    } else {
                        OffreRetaitePage.access$200(OffreRetaitePage.this);
                        return;
                    }
                }
                OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
                OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$11$$ExternalSyntheticLambda0(this));
                OffreRetaitePage.this.offerWizard.goToStepById(OffreRetaitePage.this.offerWizard.getStepById(OffreRetaitePage.this.offerWizard.currentStepId), 4, "", 16777215, 0);
            }
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$11(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToStepById(OffreRetaitePage.this.offerWizard.getStepById(OffreRetaitePage.this.offerWizard.currentStepId), 1, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    private void getStep2Subscription() {
        ServiceResponse formDataProcess = getFormDataProcess("2");
        if (formDataProcess.getStatusCode().equals("000")) {
            GetFormDataRetraite getFormDataRetraite = new GetFormDataRetraite();
            this.initDataStep2 = getFormDataRetraite;
            getFormDataRetraite.FillDataForm2FromServiceResponse(formDataProcess.getParamsOut());
            this.tab1 = new String[this.initDataStep2.FundsOrigins.length];
            for (int i = 0; i < this.initDataStep2.FundsOrigins.length; i++) {
                this.tab1[i] = this.initDataStep2.FundsOrigins[i].Value;
            }
            this.tab2 = new String[this.initDataStep2.qualiteBenef.length];
            for (int i2 = 0; i2 < this.initDataStep2.qualiteBenef.length; i2++) {
                this.tab2[i2] = this.initDataStep2.qualiteBenef[i2].qualityLabel;
            }
            this.tab3 = new String[this.initDataStep2.periodicite.length];
            for (int i3 = 0; i3 < this.initDataStep2.periodicite.length; i3++) {
                this.tab3[i3] = this.initDataStep2.periodicite[i3].label;
            }
            this.originePkr.setStrings(this.tab1);
            this.originePkr.setSelectedString(this.tab1[0]);
            this.qualiteBenefPkr.setStrings(this.tab2);
            this.qualiteBenefPkr.setSelectedString(this.tab2[0]);
            this.periodicitrPkr.setStrings(this.tab3);
            this.periodicitrPkr.setSelectedString(this.tab3[0]);
            this.uiManager.btnBack.getListeners().clear();
            this.uiManager.btnBack.addActionListener(new OffreRetaitePage$$ExternalSyntheticLambda2(this));
            B3GWizard b3GWizard = this.offerWizard;
            b3GWizard.goToStepById(b3GWizard.getStepById(b3GWizard.currentStepId), 4, "", 16777215, 0);
            return;
        }
        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Une erreur est survenue.", null);
    }

    /* synthetic */ void lambda$getStep2Subscription$3$com-b3g-ui-page-OffreRetaitePage(ActionEvent actionEvent) {
        B3GWizard b3GWizard = this.offerWizard;
        b3GWizard.goToStepById(b3GWizard.getStepById(b3GWizard.currentStepId), 1, "", 16777215, 0);
        this.uiManager.btnBack.getListeners().clear();
        this.uiManager.btnBack.addActionListener(new 12());
    }

    class 12 implements ActionListener {
        12() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.goBack();
        }
    }

    private void getStep2SubscriptionTestMode() {
        this.tab1 = new String[4];
        this.tab2 = new String[4];
        this.tab3 = new String[4];
        int i = 0;
        while (i < 4) {
            int i2 = i + 1;
            this.tab1[i] = "Lorem " + i2;
            this.tab2[i] = "Lorem " + i2;
            this.tab3[i] = "Lorem " + i2;
            i = i2;
        }
        this.originePkr.setStrings(this.tab1);
        this.originePkr.setSelectedString(this.tab1[0]);
        this.qualiteBenefPkr.setStrings(this.tab2);
        this.qualiteBenefPkr.setSelectedString(this.tab2[0]);
        this.periodicitrPkr.setStrings(this.tab3);
        this.periodicitrPkr.setSelectedString(this.tab3[0]);
        this.uiManager.btnBack.getListeners().clear();
        this.uiManager.btnBack.addActionListener(new OffreRetaitePage$$ExternalSyntheticLambda7(this));
        B3GWizard b3GWizard = this.offerWizard;
        b3GWizard.goToStepById(b3GWizard.getStepById(b3GWizard.currentStepId), 4, "", 16777215, 0);
    }

    /* synthetic */ void lambda$getStep2SubscriptionTestMode$4$com-b3g-ui-page-OffreRetaitePage(ActionEvent actionEvent) {
        B3GWizard b3GWizard = this.offerWizard;
        b3GWizard.goToStepById(b3GWizard.getStepById(b3GWizard.currentStepId), 1, "", 16777215, 0);
        this.uiManager.btnBack.getListeners().clear();
        this.uiManager.btnBack.addActionListener(new 13());
    }

    class 13 implements ActionListener {
        13() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.goBack();
        }
    }

    private Container getStep3Cnt() {
        Container container = new Container(new BorderLayout());
        container.setScrollableY(true);
        container.setScrollVisible(false);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(2, 2, 2, 2);
        SpanLabel spanLabel = new SpanLabel("Simulation Capital");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        spanLabel.getAllStyles().setPaddingLeft(0);
        Picker picker = new Picker();
        this.agePkr = picker;
        Container drawPicker = drawPicker(picker, 4, new String[]{"45", "50", "55", "60"}, "Âge de départ à la retraite");
        Picker picker2 = new Picker();
        Container drawPicker2 = drawPicker(picker2, 4, new String[]{"Avenir retraite"}, "Sélectionnez un Produit");
        TextField textField = new TextField();
        this.montantTxtfStep3 = textField;
        textField.getAllStyles().setBorder(Border.createEmpty());
        this.montantTxtfStep3.setHint("Min 200 Dhs");
        this.montantTxtfStep3.getHintLabel().getAllStyles().setPaddingUnit(1);
        this.montantTxtfStep3.getHintLabel().getAllStyles().setMarginUnit(1);
        this.montantTxtfStep3.getHintLabel().getAllStyles().setMarginUnit(1);
        this.montantTxtfStep3.getHintLabel().getAllStyles().setMargin(0, 0, 0, 0);
        this.montantTxtfStep3.getHintLabel().getAllStyles().setPadding(0, 0, 0, 0);
        this.montantTxtfStep3.setConstraint(2);
        this.montantTxtfStep3.addFocusListener(new 14());
        Label label = new Label("Produit *:");
        Label label2 = new Label("Âge de départ à la retraite *:");
        Label label3 = new Label("Montant de la cotisation mensuelle *:");
        Button button = new Button("SIMULER", "op_BtnOppositionValidationOrgBg");
        button.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.5f)));
        button.addActionListener(new 15(picker2));
        Container container2 = new Container(BoxLayout.y());
        container2.getAllStyles().setMarginUnit(1);
        container2.getAllStyles().setMargin(2, 2, 2, 2);
        Container container3 = new Container(new BorderLayout());
        container3.add("Center", this.montantTxtfStep3);
        container3.add("East", new Label(" MAD"));
        container3.setLeadComponent(this.montantTxtfStep3);
        container3.getAllStyles().setBorder(Border.createLineBorder(2, 0));
        container3.getAllStyles().setPaddingUnit(1);
        container3.getAllStyles().setPadding(0.3f, 0.1f, 2.0f, 2.0f);
        container3.getAllStyles().setMarginUnit(1);
        container3.getAllStyles().setMargin(1, 1, 1, 1);
        container2.add(spanLabel).add(label).add(drawPicker2).add(label2).add(drawPicker).add(label3).add(container3);
        container.add("Center", container2);
        Container container4 = new Container(BoxLayout.y());
        Label label4 = new Label("* : Champs obligatoires");
        label4.getAllStyles().setFgColor(CIHStyles.colorOrange);
        label4.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.4f)));
        label4.getAllStyles().setMarginUnit(1);
        label4.getAllStyles().setMarginBottom(1.4f);
        container4.add(label4).add(button);
        container.add("South", container4);
        return container;
    }

    class 14 implements FocusListener {
        public void focusGained(Component component) {
        }

        14() {
        }

        public void focusLost(Component component) {
            try {
                OffreRetaitePage offreRetaitePage = OffreRetaitePage.this;
                offreRetaitePage.amount = Double.valueOf(Double.parseDouble(offreRetaitePage.montantTxtfStep3.getText()));
                if (OffreRetaitePage.this.amount.doubleValue() < 200.0d) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Le montant minimun autorisé est 200 Dhs", null);
                }
            } catch (Exception unused) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Le montant minimun autorisé est 200 Dhs", null);
            }
        }
    }

    class 15 implements ActionListener {
        final /* synthetic */ Picker val$produitPkr;

        15(Picker picker) {
            this.val$produitPkr = picker;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Integer num;
            try {
                num = Integer.valueOf(Integer.parseInt(OffreRetaitePage.this.agePkr.getSelectedString()));
            } catch (Exception unused) {
                num = 0;
            }
            if (OffreRetaitePage.this.agePkr.getSelectedStringIndex() == -1 && this.val$produitPkr.getSelectedStringIndex() == -1 && OffreRetaitePage.this.montantTxtfStep3.getText().equals("")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Veuillez renseigner les champs obligatoires.", null);
                return;
            }
            if (OffreRetaitePage.this.agePkr.getSelectedStringIndex() == -1) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Veuillez sélectionner votre âge de départ à la retraite.", null);
                return;
            }
            if (this.val$produitPkr.getSelectedStringIndex() == -1) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Veuillez sélectionner un produit.", null);
                return;
            }
            if (num.intValue() < CihMBanking.sesPMTR.getSessionClient().getClient_Profil().age) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "L age de départ à la retraite doit etre superieur a votre age.", null);
                return;
            }
            if (OffreRetaitePage.this.montantTxtfStep3.getText().equals("")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Veuillez saisir une mensualité.", null);
                return;
            }
            if (OffreRetaitePage.this.amount.doubleValue() < 200.0d) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Le montant minimun autorisé est 200 Dhs.", null);
                return;
            }
            OffreRetaitePage offreRetaitePage = OffreRetaitePage.this;
            if (!OffreRetaitePage.access$300(offreRetaitePage, Double.valueOf(Double.parseDouble(offreRetaitePage.montantTxtfStep3.getText())))) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Le montant minimun autorisé est 200 Dhs", null);
                return;
            }
            if (OffreRetaitePage.access$000(OffreRetaitePage.this)) {
                OffreRetaitePage.this.produitVal.setText(this.val$produitPkr.getSelectedString());
                OffreRetaitePage.this.ageVal.setText(OffreRetaitePage.this.agePkr.getSelectedString() + " ans");
                OffreRetaitePage.this.montantVal.setText(OffreRetaitePage.this.montantTxtfStep3.getText() + " MAD");
                OffreRetaitePage.this.capitalVal.setText("20 000");
                OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
                OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$15$$ExternalSyntheticLambda0(this));
                OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s3, "", 16777215, 0);
                return;
            }
            Integer valueOf = Integer.valueOf(num.intValue() - CihMBanking.sesPMTR.getSessionClient().getClient_Profil().age);
            OffreRetaitePage offreRetaitePage2 = OffreRetaitePage.this;
            ServiceResponse simulationRetraiteProcess = offreRetaitePage2.simulationRetraiteProcess(offreRetaitePage2.agePkr.getSelectedString(), OffreRetaitePage.this.montantTxtfStep3.getText(), OffreRetaitePage.this.agePkr.getSelectedString(), valueOf.toString());
            if (simulationRetraiteProcess.getStatusCode().equals("000")) {
                OffreRetaitePage.this.produitVal.setText(this.val$produitPkr.getSelectedString());
                OffreRetaitePage.this.ageVal.setText(OffreRetaitePage.this.agePkr.getSelectedString() + " ans");
                OffreRetaitePage.this.montantVal.setText(OffreRetaitePage.this.montantTxtfStep3.getText() + " MAD");
                SimulatationRetraite simulatationRetraite = new SimulatationRetraite();
                simulatationRetraite.FillSimulationDataFromServiceResponse(simulationRetraiteProcess.getParamsOut());
                OffreRetaitePage.this.capitalVal.setText(simulatationRetraite.CapitalConstitue);
                OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
                OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$15$$ExternalSyntheticLambda1(this));
                OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s3, "", 16777215, 0);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Une erreur est survenue.", null);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$15(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s4, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-OffreRetaitePage$15(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s4, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 2());
        }

        class 2 implements ActionListener {
            2() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    private Container getStep4Cnt() {
        Container container = new Container(new BorderLayout());
        container.setScrollableY(true);
        container.setScrollVisible(false);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(2, 2, 2, 2);
        SpanLabel spanLabel = new SpanLabel("Résultat de ma simulation");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        spanLabel.getAllStyles().setPaddingLeft(0);
        Container container2 = new Container(BoxLayout.y());
        this.resultCnt = container2;
        container2.getAllStyles().setBorder(Border.createLineBorder(2, 15000804));
        this.resultCnt.getAllStyles().setPaddingUnit(1);
        this.resultCnt.getAllStyles().setPadding(3, 0, 2, 2);
        Container container3 = new Container(new BorderLayout());
        Container container4 = new Container(new BorderLayout());
        Container container5 = new Container(new BorderLayout());
        container4.getAllStyles().setPaddingUnit(1);
        container4.getAllStyles().setPaddingTop(2);
        container5.getAllStyles().setPaddingUnit(1);
        container5.getAllStyles().setPaddingTop(2);
        Label label = new Label("Produit :");
        Label label2 = new Label("Âge de départ à la retraite :");
        Label label3 = new Label("Mensualité :");
        Label label4 = new Label();
        this.produitVal = label4;
        label4.getAllStyles().setFgColor(CIHStyles.colorOrange);
        Label label5 = new Label();
        this.ageVal = label5;
        label5.getAllStyles().setFgColor(CIHStyles.colorOrange);
        Label label6 = new Label();
        this.montantVal = label6;
        label6.getAllStyles().setFgColor(CIHStyles.colorOrange);
        Container container6 = new Container(BoxLayout.y());
        container6.getAllStyles().setBorder(Border.createLineBorder(2, CIHStyles.colorOrange));
        container6.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(1.2f).stroke(new Stroke(2.5f, 2, 0, 4.0f)).strokeColor(CIHStyles.colorOrange).strokeOpacity(255));
        container6.getAllStyles().setBgTransparency(255);
        container6.getAllStyles().setPaddingUnit(1);
        container6.getAllStyles().setPadding(3, 3, 2, 2);
        container6.getAllStyles().setMarginUnit(1);
        container6.getAllStyles().setMargin(3, 1, 2, 2);
        Label label7 = new Label("Capital Brut à l’âge de retraite");
        label7.getAllStyles().setFgColor(CIHStyles.colorBleu);
        Label label8 = new Label();
        this.capitalVal = label8;
        label8.getAllStyles().setFgColor(CIHStyles.colorOrange);
        this.capitalVal.getAllStyles().setPaddingUnit(1);
        this.capitalVal.getAllStyles().setPaddingTop(2);
        Label label9 = new Label("Dhs");
        label9.getAllStyles().setFgColor(CIHStyles.colorOrange);
        label9.getAllStyles().setPaddingUnit(1);
        label9.getAllStyles().setPaddingTop(2);
        Button button = new Button();
        button.setUIID("Container");
        button.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.0f)));
        button.setText("JE REFAIS MA SIMULATION");
        button.setUIID("Container");
        button.setAlignment(4);
        button.getAllStyles().setFgColor(CIHStyles.colorOrange);
        button.getAllStyles().setBorder(Border.createLineBorder(2, CIHStyles.colorOrange));
        button.getAllStyles().setPaddingUnit(1);
        button.getAllStyles().setPadding(1, 1, 1, 1);
        button.getAllStyles().setMarginUnit(1);
        button.getAllStyles().setMarginBottom(2);
        button.addActionListener(new 16());
        Button button2 = new Button("JE SOUSCRIS À AVENIR RETRAITE", "op_BtnOppositionValidationOrgBg");
        button2.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.5f)));
        button2.addActionListener(new 17());
        container3.add("Center", label);
        container3.add("East", this.produitVal);
        container4.add("Center", label2);
        container4.add("East", this.ageVal);
        container5.add("Center", label3);
        container5.add("East", this.montantVal);
        container6.add(FlowLayout.encloseCenterMiddle(label7)).add(FlowLayout.encloseCenterMiddle(this.capitalVal, label9));
        this.resultCnt.add(container3).add(container4).add(container5).add(container6);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(0, 0, 2, 2);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setFillRows(true);
        Container container7 = new Container(flowLayout);
        container7.add(this.resultCnt);
        container.add("North", spanLabel);
        container.add("Center", container7);
        container.add("South", BoxLayout.encloseY(button, button2));
        return container;
    }

    class 16 implements ActionListener {
        16() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s4, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    class 17 implements ActionListener {
        17() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.versementPeriodiqueTxtF.setText(OffreRetaitePage.this.montantTxtfStep3.getText());
            if (OffreRetaitePage.access$000(OffreRetaitePage.this)) {
                OffreRetaitePage.access$400(OffreRetaitePage.this);
            } else {
                OffreRetaitePage.access$500(OffreRetaitePage.this);
            }
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$17$$ExternalSyntheticLambda0(this));
            OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s4, "", 16777215, 0);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$17(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s5, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    private void getStep4Config() {
        if (this.initDataStep2 == null) {
            ServiceResponse formDataProcess = getFormDataProcess("2");
            if (formDataProcess.getStatusCode().equals("000")) {
                GetFormDataRetraite getFormDataRetraite = new GetFormDataRetraite();
                this.initDataStep2 = getFormDataRetraite;
                getFormDataRetraite.FillDataForm2FromServiceResponse(formDataProcess.getParamsOut());
                this.tab1 = new String[this.initDataStep2.FundsOrigins.length];
                for (int i = 0; i < this.initDataStep2.FundsOrigins.length; i++) {
                    this.tab1[i] = this.initDataStep2.FundsOrigins[i].Value;
                }
                this.tab2 = new String[this.initDataStep2.qualiteBenef.length];
                for (int i2 = 0; i2 < this.initDataStep2.qualiteBenef.length; i2++) {
                    this.tab2[i2] = this.initDataStep2.qualiteBenef[i2].qualityLabel;
                }
                this.tab3 = new String[this.initDataStep2.periodicite.length];
                for (int i3 = 0; i3 < this.initDataStep2.periodicite.length; i3++) {
                    this.tab3[i3] = this.initDataStep2.periodicite[i3].label;
                }
                this.originePkr.setStrings(this.tab1);
                this.originePkr.setSelectedString(this.tab1[0]);
                this.qualiteBenefPkr.setStrings(this.tab2);
                this.qualiteBenefPkr.setSelectedString(this.tab2[0]);
                this.periodicitrPkr.setStrings(this.tab3);
                this.periodicitrPkr.setSelectedString(this.tab3[0]);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Une erreur est survenue.", null);
        }
    }

    private void getStep4TestModeConfig() {
        this.tab1 = new String[4];
        this.tab2 = new String[4];
        this.tab3 = new String[4];
        int i = 0;
        while (i < 4) {
            int i2 = i + 1;
            this.tab1[i] = "Lorem " + i2;
            this.tab2[i] = "Lorem " + i2;
            this.tab3[i] = "Lorem " + i2;
            i = i2;
        }
        this.originePkr.setStrings(this.tab1);
        this.originePkr.setSelectedString(this.tab1[0]);
        this.qualiteBenefPkr.setStrings(this.tab2);
        this.qualiteBenefPkr.setSelectedString(this.tab2[0]);
        this.periodicitrPkr.setStrings(this.tab3);
        this.periodicitrPkr.setSelectedString(this.tab3[0]);
    }

    private Container drawPicker(Picker picker, int i, Object[] objArr, String str) {
        Container container = new Container(new BorderLayout());
        container.setUIID("border_grey_1_1_1_1Copy");
        picker.setType(4);
        picker.setUIID("lbl_regular_bold_");
        picker.setType(i);
        if (objArr != null) {
            picker.setStrings((String[]) objArr);
        }
        picker.setSelectedString(str);
        picker.setAlignment(1);
        Label label = new Label();
        label.setIcon(this.uiManager.ressource.getImage("newListBox.png"));
        container.add("Center", picker).add("East", label);
        container.setLeadComponent(picker);
        return container;
    }

    private Container getPoint(SpanLabel spanLabel, String str, int i) {
        Container container = new Container(new BorderLayout());
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage(str));
        spanLabel.getTextAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.0f)));
        spanLabel.getTextAllStyles().setAlignment(1);
        spanLabel.getAllStyles().setPaddingUnit(1);
        spanLabel.getAllStyles().setPadding(0, 0, 1, 1);
        label.getAllStyles().setPaddingUnit(1);
        label.getAllStyles().setPadding(1.0f, 1.0f, 0.25f, 0.25f);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(0, i, 0, 0);
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
        button.addActionListener(new 18());
        button2.addActionListener(new 19(b3GCIHEcode));
        container4.removeAll();
        container4.setLayout(BoxLayout.y());
        container4.add(b3GCIHEcode.getComponent());
        container.add(createContainer);
        return container;
    }

    class 18 implements ActionListener {
        18() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s3, "", 16777215, 0);
        }
    }

    class 19 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        19(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String value = this.val$eCode1.getValue();
            if (value.length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Veuillez saisir votre CIH E-CODE.", null);
            } else {
                OffreRetaitePage.this.ShowDialogTransfertService(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, value);
            }
        }
    }

    public static String getDateMinusYears(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(1, -i);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    public ServiceResponse simulationRetraiteProcess(String str, String str2, String str3, String str4) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("pClientId", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("pAge", Integer.valueOf(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().age));
        hashtable.put("pInitialPayment", "0");
        hashtable.put("pPeriodicPayment", str2);
        hashtable.put("pRetirementAge", str3);
        hashtable.put("pPeriod", str4);
        hashtable.put("pBirthDay", getDateMinusYears(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().age));
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(901004);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse getFormDataProcess(String str) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("pClientId", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("pStep", str);
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(901001);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse confirmeEshop(String str) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
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

    public ServiceResponse generateContractProcess() {
        SessionParameter sessionParameter = CihMBanking.sesPMTR;
        String str = SessionParameter.accountToDebitEShop.ServiceName;
        SessionParameter sessionParameter2 = CihMBanking.sesPMTR;
        Model model = new Model(new PayingAccount(str, "001", SessionParameter.accountToDebitEShop.accountNumber), this.benefList);
        model.premiumAmount = this.versementPeriodiqueTxtF.getText();
        model.setFunds(getFundsByValue(this.originePkr.getSelectedStringIndex()));
        model.initialPayementAmount = Double.valueOf(Double.parseDouble(this.versementInitialTxtF.getText()));
        model.periodicity = getPeriodicityByValue(this.periodicitrPkr.getSelectedStringIndex());
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("pClientId", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("pModel", model.toJsonString());
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(901002);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse confirmContractProcess(String str) {
        SessionParameter sessionParameter = CihMBanking.sesPMTR;
        String str2 = SessionParameter.accountToDebitEShop.ServiceName;
        SessionParameter sessionParameter2 = CihMBanking.sesPMTR;
        Model model = new Model(new PayingAccount(str2, "001", SessionParameter.accountToDebitEShop.accountNumber), this.benefList);
        model.premiumAmount = this.versementPeriodiqueTxtF.getText();
        model.setFunds(getFundsByValue(this.originePkr.getSelectedStringIndex()));
        model.initialPayementAmount = Double.valueOf(Double.parseDouble(this.versementInitialTxtF.getText()));
        model.periodicity = getPeriodicityByValue(this.periodicitrPkr.getSelectedStringIndex());
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("pClientId", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("PASSID", str);
        hashtable.put("pModel", this.sb.toString());
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(901003);
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
            button.addActionListener(new 20(dialog));
            Button button2 = new Button("VALIDER");
            button2.setUIID("dg_BtnYestIconTR");
            button2.setTextPosition(3);
            button2.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ico_check_popup_NW.png"));
            button2.setVerticalAlignment(4);
            container2.add(button).add(button2);
            container2.getAllStyles().setPaddingUnit(1);
            container2.getAllStyles().setPadding(5, 0, 2, 2);
            button2.addActionListener(new 21(dialog, str));
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

    class 20 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        20(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 21 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ String val$eCode;

        21(Dialog dialog, String str) {
            this.val$d = dialog;
            this.val$eCode = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            ServiceResponse confirmeEshop = OffreRetaitePage.this.confirmeEshop(this.val$eCode);
            if (confirmeEshop.getStatusCode().equals("000")) {
                OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
                OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$21$$ExternalSyntheticLambda0(this));
                OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s4, "", 16777215, 0);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(confirmeEshop.getStatusLabel()), null);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$21(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s5, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s4, "", 16777215, 0);
                OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
                OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    OffreRetaitePage.this.goBack();
                }
            }
        }
    }

    private Container getOffreStep1(String str, Image image, Button button, String str2, ArrayList arrayList) {
        Container container = new Container(BoxLayout.y());
        container.getAllStyles().setBgColor(16777215);
        container.getAllStyles().setBgTransparency(255);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMarginBottom(2);
        Container container2 = new Container(BoxLayout.y());
        container2.getAllStyles().setBgColor(CIHStyles.colorBleu);
        container2.getAllStyles().setBgTransparency(255);
        Label label = new Label(str);
        label.getAllStyles().setFgColor(16777215);
        container2.add(FlowLayout.encloseCenterMiddle(label));
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(1, 1, 0, 0);
        Label label2 = new Label(image);
        label2.getAllStyles().setPaddingUnit(1);
        label2.getAllStyles().setPadding(2, 1, 0, 0);
        Label label3 = new Label(str2);
        Container container3 = new Container(BoxLayout.y());
        container3.getAllStyles().setPaddingUnit(1);
        container3.getAllStyles().setPadding(1, 1, 3, 0);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            container3.add(getPoint(new SpanLabel((String) it.next(), "smallFont"), "point1.png", 0));
        }
        container.add(container2).add(FlowLayout.encloseCenterMiddle(label2)).add(label3).add(container3).add(FlowLayout.encloseCenterMiddle(button));
        return container;
    }

    private Container getStep5Cnt() {
        SpanLabel spanLabel = new SpanLabel("Je définis mon rythme d’épargne");
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
        container3.getAllStyles().setBorder(Border.createCompoundBorder(Border.createLineBorder(2, CIHStyles.colorOrange), Border.createLineBorder(2, CIHStyles.colorOrange), null, null));
        container3.getAllStyles().setMarginUnit(1);
        container3.getAllStyles().setMargin(2, 2, 0, 0);
        if (AccountProcessNew.size() > 1) {
            container3.addComponent(this.uiManager.DrawListContainerForAccountsHome("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, AccountProcessNew, 1, 1000, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, null, null, null));
        } else {
            container3.addComponent(this.uiManager.DrawListContainerForAccountsHome("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, AccountProcessNew, 2, 1000, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, null, null, null));
        }
        Container container4 = new Container(BoxLayout.y());
        TableLayout tableLayout = new TableLayout(1, 2);
        Container container5 = new Container(tableLayout);
        this.versementInitialTxtF = new TextField();
        this.versementPeriodiqueTxtF = new TextField();
        this.autreOrigineTxtF = new TextField();
        this.periodicitrPkr = new Picker();
        this.originePkr = new Picker();
        Label label = new Label("Origine à préciser * :");
        label.getAllStyles().setFont(Font.createSystemFont(0, 0, 0));
        container5.add(tableLayout.createConstraint().widthPercentage(50).heightPercentage(100), label).add(tableLayout.createConstraint().widthPercentage(50).heightPercentage(100), getTextFieldCnt("", "Spécifier vos origines", this.autreOrigineTxtF));
        container5.setHidden(true);
        this.originePkr.addActionListener(new 22(container5));
        this.versementInitialTxtF.addFocusListener(new 23(container5));
        this.versementInitialTxtF.setConstraint(2);
        this.versementPeriodiqueTxtF.setConstraint(2);
        container4.add(getFormComponentCnt(this.versementInitialTxtF, 0, null, "Montant en", "Versement initial :", "MAD ", "")).add(getFormComponentCnt(this.versementPeriodiqueTxtF, 0, null, "Montant en", "Versement périodique * :", "MAD ", "")).add(getFormComponentCnt(this.periodicitrPkr, 4, null, "Mensuelle", "Périodicité * :", "", "newListBox.png")).add(getFormComponentCnt(this.originePkr, 4, null, "Revenu", "Origine de l'épargne. :", "", "newListBox.png")).add(container5);
        Container container6 = new Container(new GridLayout(1, 2));
        Button button = new Button("Suivant", "op_BtnOppositionValidationOrgBg");
        Button button2 = new Button("Precedent", "op_BtnOppositionValidation");
        button.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.5f)));
        button2.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.5f)));
        button.addActionListener(new 24());
        button2.addActionListener(new 25());
        container6.add(button2).add(button);
        container2.add(spanLabel).add(getStepHeader(0)).add(container3).add(container4);
        Container container7 = new Container(BoxLayout.y());
        Label label2 = new Label("* : Champs obligatoires");
        label2.getAllStyles().setFgColor(CIHStyles.colorOrange);
        label2.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.4f)));
        label2.getAllStyles().setMarginUnit(1);
        label2.getAllStyles().setMarginBottom(1.4f);
        container7.add(label2).add(container6);
        container.add("Center", container2);
        container.add("South", container7);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2, 0, 3, 3);
        return container;
    }

    class 22 implements ActionListener {
        final /* synthetic */ Container val$c5;

        22(Container container) {
            this.val$c5 = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (OffreRetaitePage.this.originePkr.getSelectedStringIndex() == OffreRetaitePage.this.tab1.length - 1) {
                this.val$c5.setHidden(false);
                this.val$c5.getParent().animateLayout(500);
            } else {
                this.val$c5.setHidden(true);
                this.val$c5.getParent().animateLayout(500);
            }
        }
    }

    class 23 implements FocusListener {
        final /* synthetic */ Container val$c5;

        public void focusGained(Component component) {
        }

        23(Container container) {
            this.val$c5 = container;
        }

        public void focusLost(Component component) {
            try {
                if (Double.valueOf(Double.parseDouble(OffreRetaitePage.this.versementInitialTxtF.getText())).doubleValue() > 60000.0d) {
                    this.val$c5.setHidden(false);
                }
            } catch (Exception unused) {
            }
        }
    }

    class 24 implements ActionListener {
        24() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (OffreRetaitePage.this.versementInitialTxtF.getText().equals("")) {
                OffreRetaitePage.this.versementInitialTxtF.setText("0");
            }
            if (OffreRetaitePage.access$000(OffreRetaitePage.this)) {
                OffreRetaitePage.access$800(OffreRetaitePage.this);
            } else {
                OffreRetaitePage.access$900(OffreRetaitePage.this);
            }
        }
    }

    class 25 implements ActionListener {
        25() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("" + OffreRetaitePage.this.offerWizard.currentStepId);
            if (OffreRetaitePage.this.offerWizard.currentStepId == 4 && OffreRetaitePage.this.capitalVal.getText().equals("")) {
                OffreRetaitePage.this.offerWizard.goToStepById(OffreRetaitePage.this.offerWizard.getStepById(OffreRetaitePage.this.offerWizard.currentStepId), 1, "", 16777215, 0);
            } else {
                OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.offerWizard.getStepById(OffreRetaitePage.this.offerWizard.currentStepId), "", 16777215, 0);
            }
        }
    }

    private void getStep5TestModeConfig() {
        if (this.versementPeriodiqueTxtF.getText().equals("")) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Veuillez saisir un versement périodique.", null);
            return;
        }
        if (this.versementPeriodiqueTxtF.getText().equals("")) {
            return;
        }
        if (!isMultiple100(Double.valueOf(Double.parseDouble(this.versementPeriodiqueTxtF.getText()))) || !isMultiple100(Double.valueOf(Double.parseDouble(this.versementPeriodiqueTxtF.getText())))) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Le montant doit être un multiple de 100.", null);
            return;
        }
        if (this.originePkr.getSelectedStringIndex() == this.tab1.length - 1 && this.autreOrigineTxtF.getText().equals("")) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Veuillez spécifier les origines de votre épargne.", null);
            return;
        }
        this.uiManager.btnBack.getListeners().clear();
        this.uiManager.btnBack.addActionListener(new OffreRetaitePage$$ExternalSyntheticLambda8(this));
        this.offerWizard.goToNextStep(this.s5, "", 16777215, 0);
    }

    /* synthetic */ void lambda$getStep5TestModeConfig$5$com-b3g-ui-page-OffreRetaitePage(ActionEvent actionEvent) {
        this.offerWizard.goToPreviousStep(this.s6, "", 16777215, 0);
        this.uiManager.btnBack.getListeners().clear();
        this.uiManager.btnBack.addActionListener(new 26());
    }

    class 26 implements ActionListener {
        26() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.goBack();
        }
    }

    private void getStep5Config() {
        if (this.versementPeriodiqueTxtF.getText().equals("")) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Veuillez saisir un versement périodique.", null);
            return;
        }
        if (!validateVersementPeriodique(this.versementPeriodiqueTxtF.getText()).equals("")) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, validateVersementPeriodique(this.versementPeriodiqueTxtF.getText()), null);
            return;
        }
        if (!isMultiple100(Double.valueOf(Double.parseDouble(this.versementPeriodiqueTxtF.getText()))) || !isMultiple100(Double.valueOf(Double.parseDouble(this.versementPeriodiqueTxtF.getText())))) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Le montant doit etre un multiple de 100.", null);
            return;
        }
        if (this.originePkr.getSelectedStringIndex() == this.tab1.length - 1 && this.autreOrigineTxtF.getText().equals("")) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "veuillez specifier les origines de votre épargne.", null);
            return;
        }
        this.uiManager.btnBack.getListeners().clear();
        this.uiManager.btnBack.addActionListener(new OffreRetaitePage$$ExternalSyntheticLambda4(this));
        this.offerWizard.goToNextStep(this.s5, "", 16777215, 0);
    }

    /* synthetic */ void lambda$getStep5Config$6$com-b3g-ui-page-OffreRetaitePage(ActionEvent actionEvent) {
        this.offerWizard.goToPreviousStep(this.s6, "", 16777215, 0);
        this.uiManager.btnBack.getListeners().clear();
        this.uiManager.btnBack.addActionListener(new 27());
    }

    class 27 implements ActionListener {
        27() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.goBack();
        }
    }

    private Container getStep6Cnt() {
        Button button = new Button();
        this.addBenef = button;
        Style allStyles = button.getAllStyles();
        Float valueOf = Float.valueOf(2.0f);
        allStyles.setFont(CIHStyles.getFont(valueOf));
        this.addBenef.setText("AJOUTER BÉNÉFICIAIRE");
        this.addBenef.setUIID("Container");
        this.addBenef.setAlignment(4);
        this.addBenef.getAllStyles().setFgColor(CIHStyles.colorOrange);
        this.addBenef.getAllStyles().setBorder(Border.createLineBorder(2, CIHStyles.colorOrange));
        this.addBenef.getAllStyles().setPaddingUnit(1);
        this.addBenef.getAllStyles().setPadding(1, 1, 1, 1);
        this.addBenef.getAllStyles().setMarginUnit(1);
        this.addBenef.getAllStyles().setMarginTop(2);
        this.addBenef.addActionListener(new 28());
        SpanLabel spanLabel = new SpanLabel("Je choisis mes bénéficiaires");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        Container container = new Container(new BorderLayout());
        new Label("Je choisis mes bénéficiaire");
        RadioButton radioButton = new RadioButton("   Ayants droit ( Héritier )");
        RadioButton radioButton2 = new RadioButton("   Autres bénéficiaires");
        ButtonGroup buttonGroup = new ButtonGroup(radioButton, radioButton2);
        Style allStyles2 = radioButton2.getAllStyles();
        Float valueOf2 = Float.valueOf(2.5f);
        allStyles2.setFont(CIHStyles.getFont(valueOf2));
        radioButton.getAllStyles().setFont(CIHStyles.getFont(valueOf2));
        buttonGroup.add(radioButton);
        buttonGroup.add(radioButton2);
        buttonGroup.setSelected(0);
        radioButton.getAllStyles().setFont(CIHStyles.getFont(valueOf));
        radioButton2.getAllStyles().setFont(CIHStyles.getFont(valueOf));
        radioButton.addActionListener(new 29());
        radioButton2.addActionListener(new 30());
        radioButton2.getAllStyles().setPaddingUnit(1);
        radioButton2.getAllStyles().setPadding(2, 2, 3, 3);
        radioButton.getAllStyles().setPaddingUnit(1);
        radioButton.getAllStyles().setPadding(2, 2, 3, 3);
        Container container2 = new Container(new GridLayout(1, 2));
        Button button2 = new Button("Suivant", "op_BtnOppositionValidationOrgBg");
        Button button3 = new Button("Precedent", "op_BtnOppositionValidation");
        button2.getAllStyles().setFont(CIHStyles.getFont(valueOf2));
        button3.getAllStyles().setFont(CIHStyles.getFont(valueOf2));
        button2.addActionListener(new 31());
        button3.addActionListener(new 32(buttonGroup));
        container2.add(button3).add(button2);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2, 0, 3, 3);
        radioButton.getAllStyles().setAlignment(1);
        radioButton2.getAllStyles().setAlignment(1);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setFillRows(true);
        new Container(flowLayout);
        TextField textField = new TextField();
        this.nomBenefTxtF = textField;
        textField.setConstraint(0);
        TextField textField2 = new TextField();
        this.prenomBenefTxtF = textField2;
        textField2.setConstraint(0);
        TextField textField3 = new TextField();
        this.quoteBenefTxtF = textField3;
        textField3.setConstraint(2);
        this.dateBenefPkr = new Picker();
        this.qualiteBenefPkr = new Picker();
        this.benefCnt = new Container(BoxLayout.y());
        this.listBenefCnt = new Container(BoxLayout.y());
        Container container3 = new Container(BoxLayout.y());
        this.formBenefCnt = container3;
        container3.add(getFormComponentCnt(this.qualiteBenefPkr, 4, this.qualiteBenefs, "", "Qualité :", "", "newListBox.png")).add(getFormComponentCnt(this.nomBenefTxtF, 0, null, "", "Nom :", "", "")).add(getFormComponentCnt(this.prenomBenefTxtF, 0, null, "", "Prénom :", "", "")).add(getFormComponentCnt(this.dateBenefPkr, 1, null, "", "Date de Naissance :", "", "newListBox.png")).add(getFormComponentCnt(this.quoteBenefTxtF, 0, null, "", "Quote-part :", "% ", ""));
        this.formBenefCnt.setHidden(true);
        Container container4 = new Container(BoxLayout.y());
        this.YCnt = container4;
        container4.add(this.listBenefCnt).add(this.formBenefCnt);
        this.YCnt.setScrollVisible(false);
        this.YCnt.setScrollableY(false);
        this.benefCnt.add(FlowLayout.encloseIn(BoxLayout.encloseY(radioButton, radioButton2)));
        this.benefCnt.add(this.YCnt);
        this.YCnt.getAllStyles().setPaddingUnit(1);
        this.YCnt.getAllStyles().setPadding(0.0f, 2.0f, 0.0f, 0.0f);
        Button button4 = new Button();
        button4.setUIID("");
        button4.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("RadicalRight.png"));
        button4.getAllStyles().setMarginUnit(1);
        button4.getAllStyles().setMargin(0.0f, 0.0f, 2.0f, 0.0f);
        Label label = new Label("Je choisis mes bénéficiaires ");
        label.getAllStyles().setFont(CIHStyles.getFont(valueOf2, 1));
        Container container5 = new Container(BoxLayout.x());
        Container container6 = new Container(new FlowLayout());
        container5.getAllStyles().setMarginUnit(1);
        container5.getAllStyles().setMargin(0.0f, 0.0f, 2.0f, 0.0f);
        container6.getAllStyles().setBgColor(CIHStyles.colorBleu);
        container6.getAllStyles().setBgTransparency(144);
        container6.add(label).add(button4);
        container5.add(container6);
        button4.addActionListener(new OffreRetaitePage$$ExternalSyntheticLambda6(this, button4));
        this.benefCnt.setScrollVisible(false);
        this.benefCnt.setScrollableY(true);
        this.formBenefCnt.getAllStyles().setPaddingUnit(1);
        this.formBenefCnt.getAllStyles().setPadding(0.0f, 2.0f, 0.0f, 0.0f);
        container.add("North", BoxLayout.encloseY(spanLabel, getStepHeader(1), container5));
        container.add("Center", this.benefCnt);
        container.add("South", container2);
        return container;
    }

    class 28 implements ActionListener {
        28() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.access$1000(OffreRetaitePage.this);
        }
    }

    class 29 implements ActionListener {
        29() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.autreBenef = false;
            OffreRetaitePage.this.formBenefCnt.setHidden(true);
            OffreRetaitePage.this.listBenefCnt.setHidden(true);
            OffreRetaitePage.this.benefCnt.animateLayout(300);
        }
    }

    class 30 implements ActionListener {
        30() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.autreBenef = true;
            if (OffreRetaitePage.this.benefList.isEmpty()) {
                OffreRetaitePage.this.formBenefCnt.setHidden(false);
                OffreRetaitePage.this.listBenefCnt.setHidden(true);
            } else {
                OffreRetaitePage.this.listBenefCnt.setHidden(false);
                OffreRetaitePage.this.formBenefCnt.setHidden(true);
            }
            OffreRetaitePage.this.benefCnt.animateLayout(300);
        }
    }

    class 31 implements ActionListener {
        31() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.formBenefCnt.isVisible();
            OffreRetaitePage.this.formBenefCnt.getParent().contains(OffreRetaitePage.this.formBenefCnt);
            OffreRetaitePage.this.formBenefCnt.getHeight();
            OffreRetaitePage.this.formBenefCnt.getPreferredH();
            if (!OffreRetaitePage.this.autreBenef || OffreRetaitePage.this.formBenefCnt.getHeight() <= 0) {
                if (OffreRetaitePage.this.tottalParts.doubleValue() != 100.0d && OffreRetaitePage.this.benefList.size() > 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "le total des quotes-parts doit etre égale à 100%.", null);
                    return;
                }
                OffreRetaitePage.this.dateLblStep7.setText(OffreRetaitePage.this.df.format(new Date()));
                OffreRetaitePage.this.periodiciteLblStep7.setText(OffreRetaitePage.this.periodicitrPkr.getSelectedString());
                OffreRetaitePage.this.versementInitialLblStep7.setText(OffreRetaitePage.this.versementInitialTxtF.getText() + " MAD");
                OffreRetaitePage.this.cotisationLblStep7.setText(OffreRetaitePage.this.versementPeriodiqueTxtF.getText() + " MAD");
                OffreRetaitePage.this.origienLblStep7.setText(OffreRetaitePage.this.originePkr.getSelectedString());
                OffreRetaitePage.this.mainCenterRecapStep7Cnt.add(OffreRetaitePage.this.drawRerapBenef());
                ArrayList arrayList = new ArrayList();
                SessionParameter sessionParameter = CihMBanking.sesPMTR;
                arrayList.add(SessionParameter.accountToDebitEShop);
                OffreRetaitePage.this.accountRecapStep7Cnt.addComponent(OffreRetaitePage.this.uiManager.DrawListContainerForAccountsHome("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, arrayList, 2, 1000, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, null, null, null));
                OffreRetaitePage.this.mainCenterRecapStep7Cnt.getParent().revalidate();
                OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
                OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$31$$ExternalSyntheticLambda0(this));
                OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s6, "", 16777215, 0);
                return;
            }
            if (OffreRetaitePage.access$1100(OffreRetaitePage.this)) {
                BenefRetraite benefRetraite = new BenefRetraite();
                benefRetraite.birthDay = OffreRetaitePage.this.df.format(OffreRetaitePage.this.dateBenefPkr.getDate());
                benefRetraite.firstName = OffreRetaitePage.this.nomBenefTxtF.getText();
                benefRetraite.lastName = OffreRetaitePage.this.prenomBenefTxtF.getText();
                benefRetraite.qualityLabel = OffreRetaitePage.this.qualiteBenefPkr.getSelectedString();
                if (!OffreRetaitePage.access$000(OffreRetaitePage.this)) {
                    OffreRetaitePage offreRetaitePage = OffreRetaitePage.this;
                    benefRetraite.quality = OffreRetaitePage.access$1200(offreRetaitePage, offreRetaitePage.qualiteBenefPkr.getSelectedStringIndex()).quality;
                } else {
                    benefRetraite.quality = "Qualite Test";
                }
                benefRetraite.percent = Double.valueOf(Double.parseDouble(OffreRetaitePage.this.quoteBenefTxtF.getText()));
                OffreRetaitePage offreRetaitePage2 = OffreRetaitePage.this;
                offreRetaitePage2.drawBenef(offreRetaitePage2.listBenefCnt, benefRetraite);
                OffreRetaitePage.this.benefList.add(benefRetraite);
                if (OffreRetaitePage.this.tottalParts.doubleValue() == 100.0d) {
                    OffreRetaitePage.this.addBenef.setHidden(true);
                    OffreRetaitePage.this.addBenef.getParent().revalidate();
                } else {
                    OffreRetaitePage.this.addBenef.setHidden(false);
                    OffreRetaitePage.this.addBenef.getParent().revalidate();
                }
            }
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$31(ActionEvent actionEvent) {
            OffreRetaitePage.this.mainCenterRecapStep7Cnt.removeComponent(OffreRetaitePage.this.mainCenterRecapStep7Cnt.getComponentAt(OffreRetaitePage.this.mainCenterRecapStep7Cnt.getComponentCount() - 1));
            OffreRetaitePage.this.accountRecapStep7Cnt.removeAll();
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s7, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    class 32 implements ActionListener {
        final /* synthetic */ ButtonGroup val$radioButtonGroup;

        32(ButtonGroup buttonGroup) {
            this.val$radioButtonGroup = buttonGroup;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (OffreRetaitePage.this.formBenefCnt.getHeight() > 0) {
                this.val$radioButtonGroup.setSelected(0);
                OffreRetaitePage.this.formBenefCnt.setHidden(true);
                OffreRetaitePage.this.formBenefCnt.getParent().animateLayout(300);
                return;
            }
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s6, "", 16777215, 0);
        }
    }

    /* synthetic */ void lambda$getStep6Cnt$7$com-b3g-ui-page-OffreRetaitePage(Button button, ActionEvent actionEvent) {
        showHelpPopup("Veuillez choisir les personnes à qui l’épargne constituée sera transmise en cas de décès.", 1, button);
    }

    private Container getStep7Cnt() {
        SpanLabel spanLabel = new SpanLabel("Récapitulatif");
        this.accountRecapStep7Cnt = new Container(BoxLayout.y());
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        Container container = new Container(new BorderLayout());
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2, 0, 3, 3);
        Container container2 = new Container(BoxLayout.y());
        this.mainCenterRecapStep7Cnt = container2;
        container2.setScrollableY(true);
        this.mainCenterRecapStep7Cnt.setScrollVisible(false);
        Container container3 = new Container(BoxLayout.y());
        container3.getAllStyles().setBorder(Border.createLineBorder(2, 15000804));
        container3.getAllStyles().setPaddingUnit(1);
        container3.getAllStyles().setPadding(2, 2, 2, 2);
        container3.getAllStyles().setMarginUnit(1);
        container3.getAllStyles().setMargin(3, 3, 0, 0);
        this.dateLblStep7 = new Label();
        this.periodiciteLblStep7 = new Label();
        this.versementInitialLblStep7 = new Label();
        this.cotisationLblStep7 = new Label();
        this.origienLblStep7 = new Label();
        container3.add(getInfoKeyValCnt("Date de souscription :", this.dateLblStep7));
        container3.add(getInfoKeyValCnt("Versement initial :", this.versementInitialLblStep7));
        container3.add(getInfoKeyValCnt("Montant de la cotisation mensuelle :", this.cotisationLblStep7));
        container3.add(getInfoKeyValCnt("Périodicité :", this.periodiciteLblStep7));
        container3.add(getInfoKeyValCnt("Origine d'épargne :", this.origienLblStep7));
        Label label = new Label("Bénéficiaire(s) :");
        label.getAllStyles().setFgColor(CIHStyles.colorBleu);
        label.getAllStyles().setPaddingUnit(1);
        label.getAllStyles().setPadding(1, 1, 3, 3);
        Button button = new Button("Valider", "op_BtnOppositionValidationOrgBg");
        Button button2 = new Button("Precedent", "op_BtnOppositionValidation");
        Style allStyles = button.getAllStyles();
        Float valueOf = Float.valueOf(2.5f);
        allStyles.setFont(CIHStyles.getFont(valueOf));
        button2.getAllStyles().setFont(CIHStyles.getFont(valueOf));
        Container container4 = new Container(new GridLayout(1, 2));
        container4.add(button2).add(button);
        button.addActionListener(new 33());
        button2.addActionListener(new 34());
        this.mainCenterRecapStep7Cnt.add(this.accountRecapStep7Cnt).add(container3).add(label);
        container.add("North", spanLabel);
        container.add("Center", this.mainCenterRecapStep7Cnt);
        container.add("South", container4);
        return container;
    }

    class 33 implements ActionListener {
        33() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!OffreRetaitePage.access$000(OffreRetaitePage.this)) {
                OffreRetaitePage.access$1300(OffreRetaitePage.this);
                return;
            }
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$33$$ExternalSyntheticLambda0(this));
            OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s7, "", 16777215, 0);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$33(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s8, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    class 34 implements ActionListener {
        34() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.mainCenterRecapStep7Cnt.removeComponent(OffreRetaitePage.this.mainCenterRecapStep7Cnt.getComponentAt(OffreRetaitePage.this.mainCenterRecapStep7Cnt.getComponentCount() - 1));
            OffreRetaitePage.this.accountRecapStep7Cnt.removeAll();
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s7, "", 16777215, 0);
        }
    }

    private void getStep7Config() {
        OutputStream openOutputStream;
        ServiceResponse generateContractProcess = generateContractProcess();
        byte[] bArr = null;
        if (generateContractProcess.getStatusCode().equals("000")) {
            Hashtable hashtable = (Hashtable) ((Vector) generateContractProcess.getParamsOut().get("root")).get(0);
            this.sb = new StringBuilder(hashtableToJson(hashtable));
            try {
                Hashtable hashtable2 = (Hashtable) hashtable.get("ContractEdition");
                FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
                this.fileName = fileSystemStorage.getAppHomePath() + "contracrRetraite.pdf";
                try {
                    String replaceAll = StringUtil.replaceAll(hashtable2.get("Content").toString(), "\\", "");
                    bArr = Base64.decode(replaceAll.getBytes(), replaceAll.getBytes().length);
                } catch (Exception e) {
                    System.err.println(e);
                }
                try {
                    openOutputStream = fileSystemStorage.openOutputStream(this.fileName);
                } catch (IOException e2) {
                    System.err.println(e2);
                }
            } catch (Exception unused) {
            }
            try {
                openOutputStream.write(bArr);
                if (openOutputStream != null) {
                    openOutputStream.close();
                }
                this.uiManager.btnBack.getListeners().clear();
                this.uiManager.btnBack.addActionListener(new OffreRetaitePage$$ExternalSyntheticLambda3(this));
                this.offerWizard.goToNextStep(this.s7, "", 16777215, 0);
                return;
            } catch (Throwable th) {
                if (openOutputStream != null) {
                    try {
                        openOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Une erreur est survenue.", null);
    }

    /* synthetic */ void lambda$getStep7Config$8$com-b3g-ui-page-OffreRetaitePage(ActionEvent actionEvent) {
        this.offerWizard.goToPreviousStep(this.s8, "", 16777215, 0);
        this.uiManager.btnBack.getListeners().clear();
        this.uiManager.btnBack.addActionListener(new 35());
    }

    class 35 implements ActionListener {
        35() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.goBack();
        }
    }

    private Container getStep8Cnt() throws IOException {
        SpanLabel spanLabel = new SpanLabel("Mon contract");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        Container container = new Container(new BorderLayout());
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2, 0, 3, 3);
        Container container2 = new Container(BoxLayout.y());
        container2.setScrollVisible(false);
        container2.setScrollableY(true);
        Container container3 = new Container(BoxLayout.y());
        this.contractCnt = container3;
        container3.getAllStyles().setBorder(Border.createLineBorder(2, 15000804));
        this.contractCnt.getAllStyles().setPaddingUnit(1);
        this.contractCnt.getAllStyles().setPadding(0, 0, 0, 0);
        this.contractCnt.getAllStyles().setMarginUnit(1);
        this.contractCnt.getAllStyles().setMargin(2, 2, 2, 2);
        CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("Boleto_page-0001.png");
        this.suivantBtn = new Button("Confirmer", "op_BtnOppositionValidationOrgBg");
        Button button = new Button("Precedent", "op_BtnOppositionValidation");
        Style allStyles = this.suivantBtn.getAllStyles();
        Float valueOf = Float.valueOf(2.5f);
        allStyles.setFont(CIHStyles.getFont(valueOf));
        button.getAllStyles().setFont(CIHStyles.getFont(valueOf));
        Container container4 = new Container(new GridLayout(1, 1));
        this.suivantBtn.setEnabled(false);
        this.suivantBtn.getAllStyles().setBgColor(13619151);
        this.suivantBtn.getAllStyles().setFgColor(16382713);
        Container container5 = new Container(new GridLayout(1, 2));
        container5.add(button).add(this.suivantBtn);
        container4.add(container5);
        container4.getAllStyles().setMarginUnit(1);
        container4.getAllStyles().setMargin(0.0f, 1.4f, 0.0f, 0.0f);
        Label label = new Label("*Confirmer pour signer votre contrat");
        label.getAllStyles().setFont(Font.createSystemFont(0, 0, 8));
        label.getAllStyles().setAlignment(1);
        Label label2 = new Label(" ", "smallFont");
        Button button2 = new Button("", "smallFont");
        Button button3 = new Button("", "smallFont");
        Button button4 = new Button("", "smallFont");
        Button button5 = new Button("Voir mon contract", "sendBtn");
        button5.addActionListener(new 36());
        button5.getAllStyles().setMarginUnit(1);
        button5.getAllStyles().setMargin(6, 6, 0, 0);
        container2.add(button5).add(label).add(getContractCondition("Je confirme l’acceptation des conditions de la souscription du produit AVENIR RETRAITE.", button2, 1)).add(getContractCondition("J’accepte la signature du contrat de souscription du produit AVENIR RETRAITE électroniquement conformément à la loi n° 43-20 relative aux services de confiance pour les transactions électroniques et de son décret d’application N°2.22.687.", button3, 2)).add(getContractCondition("J’autorise CIH BANK à prélever le montant du versement initial sur mon compte.", button4, 3)).add(label2);
        Button button6 = new Button("RETOUR À L’ACCUEIL");
        this.suivantBtn.addActionListener(new 37(container2, label2));
        button.addActionListener(new 38());
        button6.addActionListener(new 39());
        container.add("North", spanLabel);
        container.add("Center", container2);
        container.add("South", BoxLayout.encloseY(container4, CIHStyles.setButtonStyle1(button6, null, Float.valueOf(0.0f))));
        return container;
    }

    class 36 implements ActionListener {
        36() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (OffreRetaitePage.access$000(OffreRetaitePage.this)) {
                return;
            }
            Display.getInstance().execute(OffreRetaitePage.this.fileName);
        }
    }

    class 37 implements ActionListener {
        final /* synthetic */ Container val$mainCenterCnt;
        final /* synthetic */ Label val$toScroll;

        37(Container container, Label label) {
            this.val$mainCenterCnt = container;
            this.val$toScroll = label;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$mainCenterCnt.scrollComponentToVisible(this.val$toScroll);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$37$$ExternalSyntheticLambda0(this));
            OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s8, "", 16777215, 0);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$37(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s9, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    class 38 implements ActionListener {
        38() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s8, "", 16777215, 0);
        }
    }

    class 39 implements ActionListener {
        39() {
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new OffreRetaitePage$39$1$$ExternalSyntheticLambda0()).schedule(300, false, CihMBanking.sesPMTR.getCurrentUiManager().currentForm);
            }

            static /* synthetic */ void lambda$run$0() {
                CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }

    private Container getStep9Cnt() {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "ChangePasswordStep2");
        TextField textField = (TextField) this.uib.findByName("LoginTxt", createContainer);
        Button button = (Button) this.uib.findByName("validBtn", createContainer);
        button.getAllStyles().setMarginUnit(1);
        button.getAllStyles().setMarginBottom(5);
        Label label = (Label) this.uib.findByName("title", createContainer);
        Container parent = label.getParent();
        parent.removeComponent(label);
        SpanLabel spanLabel = new SpanLabel("Je valide ma souscription");
        SessionParameter.setOtpTextField(textField);
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setPaddingUnit(1);
        spanLabel.getAllStyles().setPaddingBottom(5);
        parent.addComponent(0, spanLabel);
        parent.revalidate();
        String replaceAll = StringUtil.replaceAll(DataTools.getEncryptedPhone(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm), "212", "0");
        ((SpanLabel) this.uib.findByName("messageLbl", createContainer)).setText("Merci de saisir le code confidentiel reçu par SMS sur le N° " + replaceAll);
        ((SpanLabel) this.uib.findByName("lblMotifTitle11", createContainer)).setText("* Code à usage unique valable 10 minutes.");
        ((Label) this.uib.findByName("gsmMsgSP", createContainer)).setText(replaceAll);
        button.addActionListener(new 40(textField));
        createContainer.getAllStyles().setPaddingUnit(1);
        createContainer.getAllStyles().setPadding(2, 0, 3, 3);
        return createContainer;
    }

    class 40 implements ActionListener {
        final /* synthetic */ TextField val$otpTxtF;

        40(TextField textField) {
            this.val$otpTxtF = textField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (OffreRetaitePage.access$000(OffreRetaitePage.this)) {
                OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
                OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$40$$ExternalSyntheticLambda0(this));
                OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s9, "", 16777215, 0);
            } else {
                if (this.val$otpTxtF.getText().equals("")) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Veuillez saisir le code reçu par SMS", null);
                    return;
                }
                ServiceResponse confirmContractProcess = OffreRetaitePage.this.confirmContractProcess(this.val$otpTxtF.getText());
                if (confirmContractProcess.getStatusCode().equals("000")) {
                    OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
                    OffreRetaitePage.this.uiManager.btnBack.addActionListener(new OffreRetaitePage$40$$ExternalSyntheticLambda1(this));
                    OffreRetaitePage.this.offerWizard.goToNextStep(OffreRetaitePage.this.s9, "", 16777215, 0);
                } else if (confirmContractProcess.getStatusCode().equals("907")) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "Le code confidentiel n'est pas valide", null);
                } else {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, confirmContractProcess.getStatusLabel(), null);
                }
            }
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-OffreRetaitePage$40(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s10, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-OffreRetaitePage$40(ActionEvent actionEvent) {
            OffreRetaitePage.this.offerWizard.goToPreviousStep(OffreRetaitePage.this.s10, "", 16777215, 0);
            OffreRetaitePage.this.uiManager.btnBack.getListeners().clear();
            OffreRetaitePage.this.uiManager.btnBack.addActionListener(new 2());
        }

        class 2 implements ActionListener {
            2() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                OffreRetaitePage.this.goBack();
            }
        }
    }

    private Container getStep10Cnt() {
        Container container = new Container(new BorderLayout());
        ((BorderLayout) container.getLayout()).setCenterBehavior(1);
        Container container2 = new Container(BoxLayout.y());
        SpanLabel spanLabel = new SpanLabel("Souscription à Avenir retraite");
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
        spanLabel2.setText("Votre demande de souscription à Avenir retraite a été effectuée avec succès");
        spanLabel2.setTextUIID("Container");
        spanLabel2.setUIID("Container");
        spanLabel2.getTextAllStyles().setAlignment(4);
        spanLabel2.getAllStyles().setPaddingUnit(1);
        spanLabel2.getAllStyles().setPadding(1, 1, 10, 10);
        Button button = new Button();
        button.setText("  RETOUR À L’ACCUEIL  ");
        Container buttonStyle1 = CIHStyles.setButtonStyle1(button, CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("rechargeEShop.png"));
        button.addActionListener(new 41());
        Button button2 = new Button("JE TÉLÉCHARGE MON CONTRAT", "sendBtn");
        button2.addActionListener(new 42());
        button2.getAllStyles().setMarginUnit(1);
        button2.getAllStyles().setMargin(0, 2, 0, 0);
        container2.add(container3).add(spanLabel2);
        container.add("North", spanLabel);
        container.add("Center", container2);
        container.add("South", BoxLayout.encloseY(button2, buttonStyle1));
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2, 2, 3, 3);
        return container;
    }

    class 41 implements ActionListener {
        41() {
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new OffreRetaitePage$41$1$$ExternalSyntheticLambda0()).schedule(300, false, CihMBanking.sesPMTR.getCurrentUiManager().currentForm);
            }

            static /* synthetic */ void lambda$run$0() {
                CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }

    class 42 implements ActionListener {
        42() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!OffreRetaitePage.access$000(OffreRetaitePage.this)) {
                Display.getInstance().execute(OffreRetaitePage.this.fileName);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OffreRetaitePage.this.uiManager.stateMachine, "You are in Test Mode.", null);
            }
        }
    }

    private Container getTextFieldCnt(String str, String str2, TextField textField) {
        Container container = new Container(new BorderLayout());
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(1, 1, 1, 1);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(0.1f, 0.1f, 2.0f, 2.0f);
        container.getAllStyles().setBorder(Border.createLineBorder(2, 0));
        textField.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.0f)));
        textField.setAlignment(1);
        textField.setHint(str2);
        textField.setMaxSize(50);
        textField.getAllStyles().setBorder(Border.createEmpty());
        textField.getHintLabel().getAllStyles().setPaddingUnit(1);
        textField.getHintLabel().getAllStyles().setMarginUnit(1);
        textField.getHintLabel().getAllStyles().setMargin(0, 0, 0, 0);
        textField.getHintLabel().getAllStyles().setPadding(0, 0, 0, 0);
        container.add("Center", textField);
        if (!str.equals("")) {
            container.add("East", new Label(str));
        }
        container.setLeadComponent(textField);
        return container;
    }

    private Container getFormComponentCnt(Component component, int i, String[] strArr, String str, String str2, String str3, String str4) {
        TableLayout tableLayout = new TableLayout(1, 2);
        component.setUIID("Container");
        Container container = new Container(tableLayout);
        Label label = new Label(str2);
        label.getAllStyles().setFont(Font.createSystemFont(0, 0, 0));
        container.getAllStyles().setBorder(Border.createCompoundBorder(Border.createLineBorder(2, 13619151), null, null, null));
        Container container2 = new Container(new BorderLayout());
        container2.getAllStyles().setMarginUnit(1);
        container2.getAllStyles().setMargin(0.5f, 0.5f, 1.0f, 1.0f);
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(0, 0, 2, 2);
        container2.getAllStyles().setBorder(Border.createLineBorder(2, 0));
        if (component instanceof Picker) {
            Picker picker = (Picker) component;
            picker.setType(i);
            if (strArr != null) {
                picker.setStrings(strArr);
            }
            if (!str.equals("")) {
                picker.setSelectedString(str);
            }
            picker.setAlignment(1);
            container2.add("Center", component);
            if (!str4.equals("")) {
                container2.add("East", new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage(str4)));
            }
            container2.setLeadComponent(component);
        } else if (component instanceof TextField) {
            TextField textField = (TextField) component;
            textField.setAlignment(1);
            textField.setHint(str);
            container2.add("Center", component);
            if (!str3.equals("")) {
                container2.add("East", new Label(str3));
            }
        }
        if (str2.contains("Quote")) {
            Button button = new Button();
            button.setUIID("");
            button.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("RadicalRight.png"));
            button.getAllStyles().setMarginUnit(1);
            button.getAllStyles().setMargin(0.0f, 0.0f, 2.0f, 0.0f);
            button.addActionListener(new 43(button));
            container.add(tableLayout.createConstraint().widthPercentage(50).heightPercentage(100), BoxLayout.encloseX(label, button)).add(tableLayout.createConstraint().widthPercentage(50).heightPercentage(100), container2);
        } else {
            container.add(tableLayout.createConstraint().widthPercentage(50).heightPercentage(100), label).add(tableLayout.createConstraint().widthPercentage(50).heightPercentage(100), container2);
        }
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMarginTop(0.5f);
        return container;
    }

    class 43 implements ActionListener {
        final /* synthetic */ Button val$help;

        43(Button button) {
            this.val$help = button;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.access$1400(OffreRetaitePage.this, "Si vous avez indiqué un pourcentage inférieur à 100%, veuillez ajouter un autre bénéficiaire avant de passer à l’étape suivante.", 2, this.val$help);
        }
    }

    public void drawBenef(Container container, BenefRetraite benefRetraite) {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AccountBenefiaciaryItemManagV2");
        Label label = (Label) this.uib.findByName("lblAccountOwner", createContainer);
        Label label2 = (Label) this.uib.findByName("lblAccountRibValue", createContainer);
        Label label3 = (Label) this.uib.findByName("lblCardStatusTitleV2", createContainer);
        Label label4 = (Label) this.uib.findByName("lblCardStatusValueV2", createContainer);
        label4.setUIID("ac_lblitemDetailValueFormACT");
        label.setText((benefRetraite.firstName + " " + benefRetraite.lastName).toUpperCase());
        label2.setText(benefRetraite.qualityLabel);
        label3.setText("Quote-part : ");
        label4.setText(benefRetraite.percent.toString() + " %");
        Container createContainer2 = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "BeneficiaryManagementV3Cnt");
        Button button = (Button) this.uib.findByName("btnUpdate", createContainer2);
        Button button2 = (Button) this.uib.findByName("btnDelete", createContainer2);
        ((Button) this.uib.findByName("btnActDsct", createContainer2)).setHidden(true);
        this.formBenefCnt.setHidden(true);
        button.addActionListener(new 44(benefRetraite, createContainer));
        button2.addActionListener(new 45(benefRetraite, createContainer, container));
        createContainer.add(createContainer2);
        createContainer.getAllStyles().setBorder(Border.createCompoundBorder(Border.createLineBorder(2, 0), Border.createLineBorder(2, 0), null, null));
        container.add(createContainer);
        if (!this.addBenef.isChildOf(container)) {
            container.add(this.addBenef);
        } else {
            container.removeComponent(this.addBenef);
            container.add(this.addBenef);
        }
        container.setHidden(false);
        container.getParent().animate();
        container.getParent().revalidate();
    }

    class 44 implements ActionListener {
        final /* synthetic */ BenefRetraite val$b;
        final /* synthetic */ Container val$currentBenef;

        44(BenefRetraite benefRetraite, Container container) {
            this.val$b = benefRetraite;
            this.val$currentBenef = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.benefList.remove(this.val$b);
            this.val$currentBenef.removeAll();
            this.val$currentBenef.revalidate();
            OffreRetaitePage.this.qualiteBenefPkr.setSelectedString(this.val$b.qualityLabel);
            OffreRetaitePage.this.nomBenefTxtF.setText(this.val$b.firstName);
            OffreRetaitePage.this.prenomBenefTxtF.setText(this.val$b.lastName);
            OffreRetaitePage.this.quoteBenefTxtF.setText(this.val$b.percent.toString());
            OffreRetaitePage.this.YCnt.scrollComponentToVisible(OffreRetaitePage.this.quoteBenefTxtF);
            if (OffreRetaitePage.this.tottalParts.doubleValue() < 100.0d) {
                OffreRetaitePage.this.addBenef.setHidden(false);
            } else if (OffreRetaitePage.this.tottalParts.doubleValue() == 100.0d) {
                OffreRetaitePage.this.addBenef.setHidden(true);
            }
            OffreRetaitePage.this.formBenefCnt.setHidden(false);
            OffreRetaitePage.this.formBenefCnt.revalidate();
        }
    }

    class 45 implements ActionListener {
        final /* synthetic */ BenefRetraite val$b;
        final /* synthetic */ Container val$currentBenef;
        final /* synthetic */ Container val$mainCnt;

        45(BenefRetraite benefRetraite, Container container, Container container2) {
            this.val$b = benefRetraite;
            this.val$currentBenef = container;
            this.val$mainCnt = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreRetaitePage.this.benefList.remove(this.val$b);
            OffreRetaitePage offreRetaitePage = OffreRetaitePage.this;
            offreRetaitePage.tottalParts = Double.valueOf(offreRetaitePage.tottalParts.doubleValue() - this.val$b.percent.doubleValue());
            this.val$currentBenef.removeAll();
            this.val$currentBenef.revalidate();
            this.val$mainCnt.animate();
            if (OffreRetaitePage.this.benefList.size() == 0) {
                this.val$mainCnt.removeComponent(OffreRetaitePage.this.addBenef);
                OffreRetaitePage.access$1000(OffreRetaitePage.this);
            }
            if (OffreRetaitePage.this.tottalParts.doubleValue() < 100.0d) {
                OffreRetaitePage.this.addBenef.setHidden(false);
                OffreRetaitePage.this.addBenef.getParent().revalidate();
            } else if (OffreRetaitePage.this.tottalParts.doubleValue() == 100.0d) {
                OffreRetaitePage.this.addBenef.setHidden(true);
                OffreRetaitePage.this.addBenef.getParent().revalidate();
            }
            System.out.println("" + OffreRetaitePage.this.tottalParts);
        }
    }

    private void setBenefForm(Container container, BenefRetraite benefRetraite) {
        this.qualiteBenefPkr.setSelectedString(benefRetraite.qualityLabel);
        this.nomBenefTxtF.setText(benefRetraite.firstName);
        this.prenomBenefTxtF.setText(benefRetraite.lastName);
        this.dateBenefPkr.setSelectedString(benefRetraite.birthDay);
        this.quoteBenefTxtF.setText(benefRetraite.percent.toString());
        container.setHidden(false);
        container.revalidate();
    }

    private void clearBenefForm() {
        this.qualiteBenefPkr.setSelectedString(this.tab2[0]);
        this.nomBenefTxtF.setText("");
        this.prenomBenefTxtF.setText("");
        this.quoteBenefTxtF.setText("");
        this.YCnt.scrollComponentToVisible(this.quoteBenefTxtF);
        this.formBenefCnt.setHidden(false);
        this.formBenefCnt.revalidate();
    }

    private boolean validateAddBenefForm() {
        this.tottalParts = Double.valueOf(0.0d);
        if (!this.benefList.isEmpty()) {
            Iterator it = this.benefList.iterator();
            while (it.hasNext()) {
                this.tottalParts = Double.valueOf(this.tottalParts.doubleValue() + ((BenefRetraite) it.next()).percent.doubleValue());
            }
        }
        if (!this.quoteBenefTxtF.getText().equals("")) {
            this.tottalParts = Double.valueOf(this.tottalParts.doubleValue() + Double.parseDouble(this.quoteBenefTxtF.getText()));
            if (this.nomBenefTxtF.getText().equals("")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Veuillez saisir le nom de votre bénéficiaire", null);
                return false;
            }
            if (this.prenomBenefTxtF.getText().equals("")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Veuillez saisir le prenom de votre bénéficiaire", null);
                return false;
            }
            if (this.tottalParts.doubleValue() > 100.0d) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "le total des quotes-parts ne doit pas dépasser 100%.", null);
                return false;
            }
            if (this.tottalParts.doubleValue() == 100.0d || this.formBenefCnt.getHeight() != 0) {
                return true;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "le total des quotes-parts doit etre égale à 100%.", null);
            return false;
        }
        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Veuillez saisir une quote-part de votre bénéficiaire", null);
        return false;
    }

    private Container getInfoKeyValCnt(String str, Label label) {
        Container container = new Container(new BorderLayout());
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPaddingTop(2);
        Label label2 = new Label(str);
        label.getAllStyles().setFgColor(CIHStyles.colorOrange);
        container.add("Center", label2);
        container.add("East", label);
        return container;
    }

    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v15 */
    Container drawRerapBenef() {
        int i;
        int i2;
        Border border;
        Container container = new Container(BoxLayout.y());
        int i3 = 1;
        int i4 = 0;
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(2, 2, 1, 1);
        if (this.benefList.isEmpty()) {
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AccountBenefiaciaryItemManagV2");
            createContainer.getAllStyles().setPaddingUnit(1);
            createContainer.getAllStyles().setPadding(1, 1, 1, 1);
            Label label = (Label) this.uib.findByName("lblAccountOwner", createContainer);
            Label label2 = (Label) this.uib.findByName("lblAccountRibValue", createContainer);
            Label label3 = (Label) this.uib.findByName("lblCardStatusTitleV2", createContainer);
            Label label4 = (Label) this.uib.findByName("lblCardStatusValueV2", createContainer);
            label3.setHidden(true);
            label4.setHidden(true);
            label.setText("Ayants droit");
            label2.setText("( Héritier )");
            container.add(createContainer);
        } else {
            int i5 = 0;
            while (i5 < this.benefList.size()) {
                BenefRetraite benefRetraite = (BenefRetraite) this.benefList.get(i5);
                Container createContainer2 = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AccountBenefiaciaryItemManagV2");
                Style allStyles = createContainer2.getAllStyles();
                byte[] bArr = new byte[i3];
                bArr[i4] = i3;
                allStyles.setPaddingUnit(bArr);
                createContainer2.getAllStyles().setPadding(i3, i3, i3, i3);
                Label label5 = (Label) this.uib.findByName("lblAccountOwner", createContainer2);
                Label label6 = (Label) this.uib.findByName("lblAccountRibValue", createContainer2);
                Label label7 = (Label) this.uib.findByName("lblCardStatusTitleV2", createContainer2);
                Label label8 = (Label) this.uib.findByName("lblCardStatusValueV2", createContainer2);
                label8.setUIID("ac_lblitemDetailValueFormACT");
                label5.setText((benefRetraite.firstName + " " + benefRetraite.lastName).toUpperCase());
                label6.setText(benefRetraite.qualityLabel);
                label7.setText("Quote-part : ");
                label8.setText(benefRetraite.percent.toString() + " %");
                Style allStyles2 = createContainer2.getAllStyles();
                if (i5 == this.benefList.size() - 1) {
                    i = 0;
                    i2 = 2;
                    border = Border.createLineBorder(2, 0);
                } else {
                    i = 0;
                    i2 = 2;
                    border = null;
                }
                allStyles2.setBorder(Border.createCompoundBorder(Border.createLineBorder(i2, i), border, null, null));
                container.add(createContainer2);
                i5++;
                i3 = 1;
                i4 = i;
            }
        }
        return container;
    }

    private Container getContractCondition(String str, Button button, int i) {
        Container container = new Container(new BorderLayout());
        SpanLabel spanLabel = new SpanLabel(str);
        spanLabel.getTextAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.0f)));
        spanLabel.getTextAllStyles().setAlignment(1);
        spanLabel.getTextAllStyles().setFgColor(0);
        button.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("EprgneUnCheck.png"));
        button.setName("off");
        button.getAllStyles().setPaddingUnit(1);
        button.getAllStyles().setPadding(1.0f, 2.0f, 0.5f, 2.0f);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1, 1, 2, 2);
        spanLabel.getTextAllStyles().setAlignment(1);
        spanLabel.getTextAllStyles().setFgColor(CIHStyles.colorBleu);
        button.addActionListener(new 46(button, i));
        container.add("West", FlowLayout.encloseCenter(button));
        container.add("Center", spanLabel);
        container.setLeadComponent(button);
        return container;
    }

    class 46 implements ActionListener {
        final /* synthetic */ Button val$checkBtn;
        final /* synthetic */ int val$condition;

        46(Button button, int i) {
            this.val$checkBtn = button;
            this.val$condition = i;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$checkBtn.getName().equals("off")) {
                this.val$checkBtn.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("EprgneCheck.png"));
                this.val$checkBtn.setName("on");
                int i = this.val$condition;
                if (i == 1) {
                    OffreRetaitePage.access$1502(OffreRetaitePage.this, true);
                } else if (i == 2) {
                    OffreRetaitePage.access$1602(OffreRetaitePage.this, true);
                } else if (i == 3) {
                    OffreRetaitePage.access$1702(OffreRetaitePage.this, true);
                }
            } else {
                this.val$checkBtn.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("EprgneUnCheck.png"));
                this.val$checkBtn.setName("off");
                int i2 = this.val$condition;
                if (i2 == 1) {
                    OffreRetaitePage.access$1502(OffreRetaitePage.this, false);
                } else if (i2 == 2) {
                    OffreRetaitePage.access$1602(OffreRetaitePage.this, false);
                } else if (i2 == 3) {
                    OffreRetaitePage.access$1702(OffreRetaitePage.this, false);
                }
            }
            if (OffreRetaitePage.access$1500(OffreRetaitePage.this) && OffreRetaitePage.access$1600(OffreRetaitePage.this) && OffreRetaitePage.access$1700(OffreRetaitePage.this) && !OffreRetaitePage.access$1800(OffreRetaitePage.this).isEnabled()) {
                OffreRetaitePage offreRetaitePage = OffreRetaitePage.this;
                OffreRetaitePage.access$1900(offreRetaitePage, OffreRetaitePage.access$1800(offreRetaitePage), false);
            } else if (OffreRetaitePage.access$1800(OffreRetaitePage.this).isEnabled()) {
                if (OffreRetaitePage.access$1500(OffreRetaitePage.this) && OffreRetaitePage.access$1600(OffreRetaitePage.this) && OffreRetaitePage.access$1700(OffreRetaitePage.this)) {
                    return;
                }
                OffreRetaitePage offreRetaitePage2 = OffreRetaitePage.this;
                OffreRetaitePage.access$1900(offreRetaitePage2, OffreRetaitePage.access$1800(offreRetaitePage2), true);
            }
        }
    }

    private Container getStepHeader(int i) {
        Container container = new Container(new BorderLayout());
        if (i == 0) {
            Label label = new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("1inactif 3.png"));
            Container container2 = new Container(new BorderLayout());
            container2.add("Center", new HorizontalLine(6645093));
            container2.getAllStyles().setMarginUnit(1);
            container2.getAllStyles().setMargin(0, 0, 3, 3);
            container.add("East", new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("2inactif.png")));
            container.add("West", label);
            container.add("Center", container2);
        } else if (i == 1) {
            Label label2 = new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("1actif.png 1"));
            Container container3 = new Container(new BorderLayout());
            container3.add("Center", new HorizontalLine(CIHStyles.colorOrange));
            container3.getAllStyles().setMarginUnit(1);
            container3.getAllStyles().setMargin(0, 0, 3, 3);
            container.add("East", new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("2inactif 3.png")));
            container.add("West", label2);
            container.add("Center", container3);
        } else if (i == 2) {
            Label label3 = new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("1actif.png 1"));
            Container container4 = new Container(new BorderLayout());
            container4.add("Center", new HorizontalLine(CIHStyles.colorOrange));
            container4.getAllStyles().setMarginUnit(1);
            container4.getAllStyles().setMargin(0, 0, 3, 3);
            container.add("East", new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("2actif.png 1")));
            container.add("West", label3);
            container.add("Center", container4);
        }
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1, 1, 3, 3);
        return container;
    }

    private class HorizontalLine extends Component {
        int color;

        public HorizontalLine(int i) {
            this.color = i;
        }

        public void paint(Graphics graphics) {
            super.paint(graphics);
            int width = getWidth();
            int height = getHeight() / 2;
            graphics.setColor(this.color);
            graphics.drawLine(0, height, width, height);
        }
    }

    private QualiteBenef getQualiteBenefByValue(int i) {
        if (i == -1) {
            i = 0;
        }
        GetFormDataRetraite getFormDataRetraite = this.initDataStep2;
        if (getFormDataRetraite == null || getFormDataRetraite.qualiteBenef == null) {
            return null;
        }
        return this.initDataStep2.qualiteBenef[i];
    }

    private Periodicity getPeriodicityByValue(int i) {
        if (i == -1) {
            i = 0;
        }
        GetFormDataRetraite getFormDataRetraite = this.initDataStep2;
        if (getFormDataRetraite == null || getFormDataRetraite.periodicite == null) {
            return null;
        }
        return this.initDataStep2.periodicite[i];
    }

    private FundsOrigin getFundsByValue(int i) {
        if (i == -1) {
            i = 0;
        }
        GetFormDataRetraite getFormDataRetraite = this.initDataStep2;
        if (getFormDataRetraite == null || getFormDataRetraite.FundsOrigins == null) {
            return null;
        }
        return this.initDataStep2.FundsOrigins[i];
    }

    public void goBack() {
        if (this.offerWizard.currentStepId == 0) {
            this.uiManager.GoBack();
            return;
        }
        if (this.offerWizard.currentStepId == 4 && this.capitalVal.getText().equals("")) {
            B3GWizard b3GWizard = this.offerWizard;
            b3GWizard.goToStepById(b3GWizard.getStepById(b3GWizard.currentStepId), 2, "", 16777215, 0);
        }
        B3GWizard b3GWizard2 = this.offerWizard;
        b3GWizard2.goToPreviousStep(b3GWizard2.getStepById(b3GWizard2.currentStepId), "", 16777215, 0);
    }

    private String validateVersementPeriodique(String str) {
        Double valueOf;
        Periodicity periodicityByValue = getPeriodicityByValue(this.periodicitrPkr.getSelectedStringIndex());
        try {
            valueOf = Double.valueOf(Double.parseDouble(str));
        } catch (Exception unused) {
            valueOf = Double.valueOf(0.0d);
        }
        if (periodicityByValue.minAmount == null || periodicityByValue.minAmount.doubleValue() == 0.0d || valueOf.doubleValue() >= periodicityByValue.minAmount.doubleValue()) {
            return (periodicityByValue.maxAmount == null || periodicityByValue.maxAmount.doubleValue() == 0.0d || valueOf.doubleValue() <= periodicityByValue.maxAmount.doubleValue()) ? "" : "Le maximun d un versement " + periodicityByValue.label + " est de " + periodicityByValue.minAmount.toString() + " MAD.";
        }
        return "Le minumun d un versement " + periodicityByValue.label + " est de " + periodicityByValue.minAmount.toString() + " MAD.";
    }

    private Image getImageFromB64(String str) {
        try {
            byte[] decode = Base64.decode(str.getBytes());
            return Image.createImage(decode, 0, decode.length);
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    private boolean isMultiple100(Double d) {
        return d.doubleValue() % 100.0d == 0.0d;
    }

    private static String hashtableToJson(Hashtable hashtable) {
        StringBuilder sb = new StringBuilder("{");
        boolean z = true;
        for (Map.Entry entry : hashtable.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append(",");
            }
            sb.append("\"").append((String) entry.getKey()).append("\":");
            if (entry.getValue() instanceof String) {
                sb.append("\"").append(entry.getValue()).append("\"");
            } else if (entry.getValue() instanceof Hashtable) {
                sb.append(hashtableToJson((Hashtable) entry.getValue()));
            } else {
                sb.append(entry.getValue());
            }
        }
        sb.append("}");
        return StringUtil.replaceAll(sb.toString(), "=", ":");
    }

    private void toggleStyle(Button button, boolean z) {
        button.setEnabled(!z);
        if (z) {
            button.getAllStyles().setBgColor(13619151);
            button.getAllStyles().setFgColor(16382713);
        } else {
            button.getAllStyles().setBgColor(15818018);
            button.getAllStyles().setFgColor(16777215);
        }
    }

    private void showHelpPopup(String str, int i, Button button) {
        Dialog dialog = new Dialog();
        Container createContainer = this.uib.createContainer("/cihv3", "3DAgregation");
        Button button2 = (Button) this.uib.findByName("Button1", createContainer);
        SpanLabel spanLabel = (SpanLabel) this.uib.findByName("SpanLabel", createContainer);
        dialog.setLayout(BoxLayout.y());
        dialog.getAllStyles().setBgTransparency(255);
        dialog.add(createContainer);
        dialog.setDialogUIID("Container");
        dialog.setDisposeWhenPointerOutOfBounds(true);
        if (i == 2) {
            ((Container) this.uib.findByName("Container12", createContainer)).setUIID("LoginSecureBottomCntTrsp");
        }
        spanLabel.setText(str);
        spanLabel.getAllStyles().setPaddingUnit(1);
        spanLabel.getAllStyles().setPadding(0.0f, 1.4f, 1.4f, 1.4f);
        button2.addActionListener(new 47(dialog));
        dialog.setBackCommand(new 48("Back", dialog));
        int displayHeight = Display.getInstance().getDisplayHeight();
        int absoluteY = button.getAbsoluteY();
        int absoluteX = button.getAbsoluteX();
        if (i == 1) {
            int height = (button.getHeight() / 2) + absoluteY;
            int preferredH = displayHeight - ((createContainer.getPreferredH() + absoluteY) + 200);
            System.out.println("Top = " + height);
            System.out.println("bottom = " + preferredH);
            System.out.println("Left = 65");
            System.out.println("Right = 65");
            dialog.show(height, preferredH, 65, 65);
            return;
        }
        if (i == 2) {
            int preferredH2 = absoluteY - (createContainer.getPreferredH() * 2);
            int i2 = absoluteX - 10;
            int i3 = absoluteX - 300;
            System.out.println("Top = " + preferredH2);
            System.out.println("bottom = 0");
            System.out.println("Left = " + i2);
            System.out.println("Right = " + i3);
            dialog.show(preferredH2, 0, i2, i3);
        }
    }

    class 47 implements ActionListener {
        final /* synthetic */ Dialog val$dlg;

        47(Dialog dialog) {
            this.val$dlg = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$dlg.dispose();
        }
    }

    class 48 extends Command {
        final /* synthetic */ Dialog val$dlg;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        48(String str, Dialog dialog) {
            super(str);
            this.val$dlg = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$dlg.dispose();
        }
    }
}
