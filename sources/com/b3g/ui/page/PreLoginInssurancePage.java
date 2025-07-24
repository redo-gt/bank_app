package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.GetFormDataRetraiteOffline;
import com.b3g.services.ServiceManager;
import com.b3g.services.SimulatationRetraite;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GSliderOffline;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizard;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.Step;
import com.b3g.ui.ThrowListener;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class PreLoginInssurancePage extends B3GPage implements ThrowListener {
    private Picker agePicker;
    public B3GSliderOffline ageSlider;
    private Label ageVal;
    private Label capitalVal;
    private String current_age;
    private GetFormDataRetraiteOffline initDadaStep1;
    B3GWizard menuWizard;
    public TextField montantTxt;
    private Label montantVal;
    private String period;
    private Picker productPicker;
    private Label produitVal;
    private Step s1;
    private Step s2;
    private Step s3;
    private String selected_age;
    public double amount = 0.0d;
    public Double age = Double.valueOf(18.0d);
    public int fixedHeight = 0;
    private String[] age_list = new String[0];
    private byte[] b = {1, 1, 1, 1};

    static /* synthetic */ void access$000(PreLoginInssurancePage preLoginInssurancePage) {
        preLoginInssurancePage.goBack();
    }

    static /* synthetic */ GetFormDataRetraiteOffline access$100(PreLoginInssurancePage preLoginInssurancePage) {
        return preLoginInssurancePage.initDadaStep1;
    }

    static /* synthetic */ GetFormDataRetraiteOffline access$102(PreLoginInssurancePage preLoginInssurancePage, GetFormDataRetraiteOffline getFormDataRetraiteOffline) {
        preLoginInssurancePage.initDadaStep1 = getFormDataRetraiteOffline;
        return getFormDataRetraiteOffline;
    }

    static /* synthetic */ Picker access$200(PreLoginInssurancePage preLoginInssurancePage) {
        return preLoginInssurancePage.agePicker;
    }

    static /* synthetic */ Step access$300(PreLoginInssurancePage preLoginInssurancePage) {
        return preLoginInssurancePage.s1;
    }

    static /* synthetic */ void access$400(PreLoginInssurancePage preLoginInssurancePage) {
        preLoginInssurancePage.process_data();
    }

    public PreLoginInssurancePage(Object obj, B3gService b3gService, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = b3gService;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        System.out.println(this.uiManager.NavigationHistory.size());
        this.uiManager.CurrentPageId = 168;
        this.thisContainer = new Container(new GridLayout(1, 1));
        Container container = new Container(new GridLayout(1, 1));
        B3GSliderOffline b3GSliderOffline = new B3GSliderOffline(18, 70, 24, 1, "Votre Âge :", "Ans", this.uiManager);
        this.ageSlider = b3GSliderOffline;
        b3GSliderOffline.getAllStyles().setMarginUnit(this.b);
        this.ageSlider.getAllStyles().setMargin(1.4f, 0.0f, 0.0f, 0.0f);
        this.ageSlider.addThrowListener(this);
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
        Step step3 = new Step();
        this.s3 = step3;
        step3.icon = "2inactif.png 1";
        this.s3.selectedIcon = "2actif.png 1";
        this.s3.stepCnt = getStep3();
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.s1);
        arrayList.add(this.s2);
        arrayList.add(this.s3);
        B3GWizard b3GWizard = new B3GWizard(arrayList);
        this.menuWizard = b3GWizard;
        b3GWizard.withHeader = false;
        Container drawWizard = this.menuWizard.drawWizard("", 16777215, 0);
        drawWizard.setUIID("Container");
        container.add(drawWizard);
        Container drawTransparentBox = PreLoginPage.drawTransparentBox(container, Integer.valueOf(CIHStyles.colorBlack), 100, 4.0f, 4.0f);
        drawTransparentBox.getAllStyles().setMarginUnit(this.b);
        drawTransparentBox.getAllStyles().setMargin(0.0f, 0.0f, 4.0f, 4.0f);
        TableLayout tableLayout = new TableLayout(3, 1);
        Container container2 = new Container(tableLayout);
        container2.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(10), new Container(new FlowLayout())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(87), drawTransparentBox);
        this.thisContainer.add(container2);
        this.uiManager.currentForm.setBackCommand(new 1("Back"));
        return this.thisContainer;
    }

    class 1 extends Command {
        1(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PreLoginInssurancePage.access$000(PreLoginInssurancePage.this);
        }
    }

    private Container getBoxInfo(String str, Image image, ArrayList arrayList) {
        Container container = new Container(BoxLayout.yCenter());
        container.setScrollableY(true);
        container.setScrollVisible(false);
        Label label = new Label(str);
        label.getAllStyles().setFgColor(15988988);
        label.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(3.0f), 1));
        label.getAllStyles().setMarginUnit(this.b);
        label.getAllStyles().setMargin(2.0f, 2.0f, 0.0f, 0.0f);
        Container container2 = new Container(BoxLayout.y());
        container2.setScrollableY(false);
        container2.getAllStyles().setPaddingUnit(this.b);
        container2.getAllStyles().setPadding(1, 1, 2, 0);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            container2.add(getPoint((String) it.next(), "mtc_detail_arrow_orange.png"));
        }
        Label label2 = new Label();
        label2.setIcon(image);
        container.add(FlowLayout.encloseCenterMiddle(label)).add(FlowLayout.encloseCenterMiddle(label2)).add(container2);
        return container;
    }

    private Container getPoint(String str, String str2) {
        Label label = new Label();
        label.setIcon(this.uiManager.ressource.getImage(str2));
        label.getAllStyles().setBgColor(16777215);
        label.getAllStyles().setBgTransparency(0);
        label.getAllStyles().setMarginUnit(this.b);
        label.getAllStyles().setMargin(0, 0, 0, 0);
        SpanLabel spanLabel = new SpanLabel(str);
        spanLabel.getTextAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.0f), null));
        spanLabel.getTextAllStyles().setAlignment(0);
        spanLabel.getTextAllStyles().setPaddingUnit(this.b);
        spanLabel.getTextAllStyles().setMarginUnit(this.b);
        spanLabel.getTextAllStyles().setPadding(0, 0, 0, 0);
        spanLabel.getTextAllStyles().setMargin(0, 0, 0, 0);
        spanLabel.getAllStyles().setPaddingUnit(this.b);
        spanLabel.getAllStyles().setPadding(0, 0, 1, 1);
        spanLabel.getAllStyles().setBgTransparency(0);
        spanLabel.getTextAllStyles().setFgColor(15988988);
        label.getAllStyles().setPaddingUnit(this.b);
        label.getAllStyles().setPadding(0.0f, 0.0f, 0.25f, 0.25f);
        TableLayout tableLayout = new TableLayout(1, 2);
        Container container = new Container(tableLayout);
        container.add(tableLayout.createConstraint().widthPercentage(4), FlowLayout.encloseCenter(label)).add(tableLayout.createConstraint().widthPercentage(96), spanLabel);
        return container;
    }

    private Container getStep1() {
        Container container = new Container(new BorderLayout());
        SpanLabel spanLabel = new SpanLabel("Découvrir nos plans de retraite");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getTextAllStyles().setFgColor(10077920);
        SpanLabel spanLabel2 = new SpanLabel("CIH BANK vous propose l’offre « Avenir Retraite » qui vous permet de préparer votre retraite en fonction de vos objectifs en termes de pension et de sécurisation de l’avenir de votre famille.");
        spanLabel2.getTextAllStyles().setFgColor(CIHStyles.colorWhite);
        spanLabel2.getTextAllStyles().setAlignment(1);
        spanLabel2.getTextAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.0f), 0));
        spanLabel2.getAllStyles().setMarginUnit(this.b);
        spanLabel2.getAllStyles().setMargin(0.0f, 0.0f, 2.0f, 2.0f);
        ArrayList arrayList = new ArrayList();
        arrayList.add("Une épargne progressive accessible à tout moment.");
        arrayList.add("Des avantages fiscaux sur les cotisations et sur le capital constitué à l’échéance de votre contrat.");
        arrayList.add("Un taux de rendement attractif.");
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("Une Epargne retraite sécurisée accessible à partir de 50 ans.");
        arrayList2.add("Des avantages fiscaux sur les cotisations et sur le capital constitué à l’échéance de votre contrat.");
        Button button = new Button("Faire une simulation");
        button.addActionListener(new 2());
        Container roundedFilledButtonStyle = CIHStyles.setRoundedFilledButtonStyle(button, null, 15818018);
        roundedFilledButtonStyle.getAllStyles().setMarginUnit(this.b);
        roundedFilledButtonStyle.getAllStyles().setMargin(1.0f, 0.5f, 2.0f, 2.0f);
        button.getAllStyles().setPaddingUnit(this.b);
        button.getAllStyles().setPadding(1.2f, 1.2f, 8.0f, 8.0f);
        button.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.5f), 1));
        Container container2 = new Container(BoxLayout.y());
        container2.setScrollableY(false);
        container2.setScrollVisible(false);
        container2.add(spanLabel).add(spanLabel2).add(getBoxInfo("Avenir retraite - CIH Bank", this.uiManager.ressource.getImage("avenirRetraite.png"), arrayList)).add(FlowLayout.encloseCenterMiddle(roundedFilledButtonStyle));
        container.add("Center", container2);
        return container;
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (PreLoginInssurancePage.access$100(PreLoginInssurancePage.this) == null) {
                ServiceResponse load_data = PreLoginInssurancePage.this.load_data();
                if (load_data.getStatusCode().equals("000")) {
                    PreLoginInssurancePage.access$102(PreLoginInssurancePage.this, new GetFormDataRetraiteOffline());
                    try {
                        PreLoginInssurancePage.access$100(PreLoginInssurancePage.this).CleanData(load_data.getParamsOut());
                        PreLoginInssurancePage.access$200(PreLoginInssurancePage.this).setStrings(PreLoginInssurancePage.access$100(PreLoginInssurancePage.this).get_age_list());
                        PreLoginInssurancePage.this.uiManager.btnBackOffline.getListeners().clear();
                        PreLoginInssurancePage.this.uiManager.btnBackOffline.addActionListener(new 1());
                        PreLoginInssurancePage.this.menuWizard.goToNextStep(PreLoginInssurancePage.access$300(PreLoginInssurancePage.this), "", 16777215, 0);
                        return;
                    } catch (Exception unused) {
                        PreLoginInssurancePage.access$102(PreLoginInssurancePage.this, null);
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PreLoginInssurancePage.this.uiManager.stateMachine, "Le service est momentanément indisponible. Veuillez réessayer ultérieurement.", null);
                        return;
                    }
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PreLoginInssurancePage.this.uiManager.stateMachine, "Le service est momentanément indisponible. Veuillez réessayer ultérieurement.", null);
                return;
            }
            PreLoginInssurancePage.this.uiManager.btnBackOffline.getListeners().clear();
            PreLoginInssurancePage.this.uiManager.btnBackOffline.addActionListener(new 2());
            PreLoginInssurancePage.this.menuWizard.goToNextStep(PreLoginInssurancePage.access$300(PreLoginInssurancePage.this), "", 16777215, 0);
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                PreLoginInssurancePage.access$000(PreLoginInssurancePage.this);
            }
        }

        class 2 implements ActionListener {
            2() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                PreLoginInssurancePage.access$000(PreLoginInssurancePage.this);
            }
        }
    }

    private Container getStep2() {
        Container container = new Container(new BorderLayout());
        SpanLabel spanLabel = new SpanLabel("Simulation Capital");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getTextAllStyles().setFgColor(10077920);
        Container container2 = new Container(BoxLayout.y());
        container2.setScrollableY(false);
        container2.setScrollVisible(false);
        container2.getAllStyles().setPaddingUnit(this.b);
        container2.getAllStyles().setPadding(4.0f, 2.0f, 0.0f, 0.0f);
        Label label = new Label("Produit:");
        label.getAllStyles().setMarginUnit(this.b);
        label.getAllStyles().setMargin(1.0f, 1.0f, 0.0f, 0.0f);
        label.getAllStyles().setFgColor(15988988);
        Label label2 = new Label("Âge de départ à la retraite :");
        label2.getAllStyles().setMarginUnit(this.b);
        label2.getAllStyles().setMargin(1.0f, 1.0f, 0.0f, 0.0f);
        label2.getAllStyles().setFgColor(6645093);
        label2.getAllStyles().setFgColor(15988988);
        Label label3 = new Label("Montant de la cotisation mensuelle:");
        label3.getAllStyles().setMarginUnit(this.b);
        label3.getAllStyles().setMargin(1.8f, 1.0f, 0.0f, 0.0f);
        label3.getAllStyles().setFgColor(6645093);
        label3.getAllStyles().setFgColor(15988988);
        this.produitVal = new Label();
        this.ageVal = new Label();
        this.montantVal = new Label();
        this.agePicker = new Picker();
        this.productPicker = new Picker();
        this.montantTxt = new TextField();
        Container drawPicker = drawPicker(this.agePicker, this.age_list, "Âge de départ à la retraite");
        container2.add(label);
        container2.add(drawDisbaledTextField("Avenir Retraite"));
        container2.add(this.ageSlider);
        container2.add(label2);
        container2.add(drawPicker);
        container2.add(label3);
        container2.add(drawTextField(this.montantTxt, "Min 200 Dhs"));
        Button button = new Button("SIMULER");
        button.addActionListener(new 3());
        container.add("North", spanLabel);
        container.add("Center", container2);
        container.add("South", CIHStyles.setButtonStyle2(button, null));
        button.getAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(2.5f), 1));
        return container;
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PreLoginInssurancePage.access$400(PreLoginInssurancePage.this);
        }
    }

    private Container getStep3() {
        Container container = new Container(new BorderLayout());
        container.setScrollableY(true);
        container.setScrollVisible(false);
        SpanLabel spanLabel = new SpanLabel("Résultat de ma simulation");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        spanLabel.getAllStyles().setPaddingLeft(0);
        spanLabel.getTextAllStyles().setFgColor(10077920);
        Container container2 = new Container(BoxLayout.y());
        container2.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(1.0f).stroke(new Stroke(2.0f, 2, 0, 4.0f)).strokeColor(15000804).strokeOpacity(255));
        container2.getAllStyles().setBgColor(16777215);
        container2.getAllStyles().setBgTransparency(0);
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(3, 0, 2, 2);
        Container container3 = new Container(new BorderLayout());
        Container container4 = new Container(new BorderLayout());
        Container container5 = new Container(new BorderLayout());
        container4.getAllStyles().setPaddingUnit(1);
        container4.getAllStyles().setPaddingTop(2);
        container5.getAllStyles().setPaddingUnit(1);
        container5.getAllStyles().setPaddingTop(2);
        Label label = new Label("Produit :");
        label.getAllStyles().setFgColor(15988988);
        Label label2 = new Label("Âge de départ à la retraite :");
        label2.getAllStyles().setFgColor(15988988);
        Label label3 = new Label("Mensualité :");
        label3.getAllStyles().setFgColor(15988988);
        this.produitVal.getAllStyles().setFgColor(CIHStyles.colorOrange);
        this.ageVal.getAllStyles().setFgColor(CIHStyles.colorOrange);
        this.montantVal.getAllStyles().setFgColor(CIHStyles.colorOrange);
        Container container6 = new Container(BoxLayout.y());
        container6.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(1.2f).stroke(new Stroke(2.5f, 2, 0, 4.0f)).strokeColor(CIHStyles.colorOrange).strokeOpacity(255));
        container6.getAllStyles().setBgColor(15922423);
        container6.getAllStyles().setBgTransparency(255);
        container6.getAllStyles().setPaddingUnit(this.b);
        container6.getAllStyles().setPadding(3, 3, 2, 2);
        container6.getAllStyles().setMarginUnit(this.b);
        container6.getAllStyles().setMargin(3, 3, 2, 2);
        Label label4 = new Label("Capital Brut à l’âge de retraite");
        label4.getAllStyles().setFgColor(CIHStyles.colorBleu);
        Label label5 = new Label();
        this.capitalVal = label5;
        label5.getAllStyles().setFgColor(CIHStyles.colorOrange);
        this.capitalVal.getAllStyles().setPaddingUnit(this.b);
        this.capitalVal.getAllStyles().setPadding(0.0f, 0.0f, 2.0f, 0.0f);
        Label label6 = new Label("Dhs");
        label6.getAllStyles().setFgColor(CIHStyles.colorOrange);
        this.capitalVal.getAllStyles().setPaddingUnit(this.b);
        this.capitalVal.getAllStyles().setPadding(0.0f, 0.0f, 0.0f, 2.0f);
        Button button = new Button();
        button.setText("JE REFAIS MA SIMULATION");
        button.addActionListener(new 4());
        SpanLabel spanLabel2 = new SpanLabel("Pour souscrire à cette offre, merci de contacter votre gestionnaire CIH BANK");
        spanLabel2.getTextAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainRegular", Float.valueOf(2.0f), 1));
        spanLabel2.getAllStyles().setMarginUnit(this.b);
        spanLabel2.getAllStyles().setMargin(0.0f, 2.0f, 0.0f, 0.0f);
        spanLabel2.getTextAllStyles().setFgColor(CIHStyles.colorWhite);
        Container encloseCenterMiddle = FlowLayout.encloseCenterMiddle(this.capitalVal, label6);
        encloseCenterMiddle.getAllStyles().setPaddingUnit(this.b);
        encloseCenterMiddle.getAllStyles().setPadding(1.0f, 1.0f, 0.0f, 0.0f);
        container3.add("Center", label);
        container3.add("East", this.produitVal);
        container4.add("Center", label2);
        container4.add("East", this.ageVal);
        container5.add("Center", label3);
        container5.add("East", this.montantVal);
        container6.add(FlowLayout.encloseCenterMiddle(label4)).add(encloseCenterMiddle);
        container2.add(container3).add(container4).add(container5).add(container6);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setFillRows(true);
        Container container7 = new Container(flowLayout);
        container7.add(container2);
        container.add("North", spanLabel);
        container.add("Center", container7);
        container.add("South", BoxLayout.encloseY(spanLabel2, CIHStyles.setButtonStyle2(button, null)));
        button.getAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(2.5f), 1));
        return container;
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PreLoginInssurancePage.access$000(PreLoginInssurancePage.this);
        }
    }

    public ServiceResponse load_data() {
        Hashtable hashtable = new Hashtable();
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(901007);
        serviceRequest.setSessionId("95476");
        return (ServiceResponse) ((ArrayList) new ServiceManager().Run(serviceRequest)).get(0);
    }

    public ServiceResponse simulate_request(String str, String str2, String str3) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("CurrentAge", str);
        hashtable.put("PickedAge", str2);
        hashtable.put("Amount", str3);
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(901008);
        ServiceManager serviceManager = new ServiceManager();
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(901008);
        serviceRequest.setSessionId("95476");
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private void goBack() {
        if (this.menuWizard.currentStepId != 0) {
            B3GWizard b3GWizard = this.menuWizard;
            b3GWizard.goToPreviousStep(b3GWizard.getStepById(b3GWizard.currentStepId), null, 16777215, 16777215);
        } else {
            this.uiManager.GoBackOffligne();
        }
    }

    private Container drawDisbaledTextField(String str) {
        Container container = new Container(new FlowLayout(4, 4));
        container.setUIID("border_grey_1_1_1_1_background_grey");
        Label label = new Label(str);
        label.getAllStyles().setFgColor(CIHStyles.colorGris);
        label.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.4f), 1));
        container.add(label);
        container.setPreferredH(this.fixedHeight);
        return container;
    }

    private Container drawTextField(TextField textField, String str) {
        Container container = new Container(new BorderLayout());
        container.setUIID("border_grey_1_1_1_1Copy");
        textField.getAllStyles().setBorder(Border.createEmpty());
        textField.getAllStyles().setPaddingUnit(this.b);
        textField.getAllStyles().setPadding(0.0f, 0.0f, 0.0f, 0.0f);
        textField.setHint(str);
        textField.getHintLabel().getAllStyles().setPaddingUnit(1);
        textField.getHintLabel().getAllStyles().setMarginUnit(1);
        textField.getHintLabel().getAllStyles().setPadding(0, 0, 0, 0);
        textField.setConstraint(2);
        textField.addFocusListener(new 5(textField));
        container.setPreferredH(this.fixedHeight);
        container.add("Center", textField);
        container.setLeadComponent(textField);
        return container;
    }

    class 5 implements FocusListener {
        final /* synthetic */ TextField val$txt;

        public void focusGained(Component component) {
        }

        5(TextField textField) {
            this.val$txt = textField;
        }

        public void focusLost(Component component) {
            try {
                PreLoginInssurancePage.this.amount = Double.parseDouble(this.val$txt.getText());
                if (PreLoginInssurancePage.this.amount < 200.0d) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PreLoginInssurancePage.this.uiManager.stateMachine, "Le montant minimun autorisé est 200 Dhs", null);
                }
            } catch (Exception unused) {
                PreLoginInssurancePage.this.uiManager.ShowMessageTransaction(PreLoginInssurancePage.this.uiManager.stateMachine, "Le montant minimun autorisé est 200 Dhs", null);
            }
        }
    }

    private Container drawPicker(Picker picker, Object[] objArr, String str) {
        Container container = new Container(new BorderLayout());
        container.setUIID("border_grey_1_1_1_1Copy");
        picker.setType(4);
        picker.setUIID("lbl_regular_bold_");
        if (objArr != null) {
            picker.setStrings((String[]) objArr);
        }
        picker.setSelectedString(str);
        picker.setAlignment(1);
        Label label = new Label();
        label.setIcon(this.uiManager.ressource.getImage("newListBox.png"));
        container.add("Center", picker).add("East", label);
        container.setLeadComponent(picker);
        this.fixedHeight = container.getPreferredH();
        return container;
    }

    public void Catch() {
        SetResult();
        this.PageSession = this;
    }

    private void SetResult() {
        this.age = Double.valueOf(Double.parseDouble(this.ageSlider.getValue()));
    }

    private void process_data() {
        if (this.agePicker.getSelectedStringIndex() == -1 && this.montantTxt.getText().equals("")) {
            this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Veuillez renseigner les champs obligatoires.", null);
            return;
        }
        if (this.agePicker.getSelectedStringIndex() == -1) {
            this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Veuillez sélectionner votre âge de départ à la retraite.", null);
            return;
        }
        if (Double.parseDouble(this.agePicker.getSelectedString()) < this.age.doubleValue()) {
            this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "L'âge de départ à la retraite doit être supérieur à votre âge.", null);
            return;
        }
        if (this.montantTxt.getText().equals("")) {
            this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Veuillez saisir une mensualité.", null);
            return;
        }
        if (this.amount < 200.0d) {
            this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Le montant minimun autorisé est 200 Dhs.", null);
            return;
        }
        if (!isMultiple100(Double.valueOf(Double.parseDouble(this.montantTxt.getText())))) {
            this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Veuillez saisir un montant multiple de 100 Dhs.", null);
            return;
        }
        String str = this.current_age;
        if (str != null && this.selected_age != null && this.period != null && str.equals(String.valueOf(this.age.intValue())) && this.selected_age.equals(this.agePicker.getSelectedString()) && this.period.equals(this.montantTxt.getText()) && !this.capitalVal.getText().trim().isEmpty()) {
            this.menuWizard.goToNextStep(this.s2, "", 16777215, 0);
            return;
        }
        this.current_age = String.valueOf(this.age.intValue());
        this.selected_age = this.agePicker.getSelectedString();
        String str2 = this.montantTxt.getText().toString();
        this.period = str2;
        ServiceResponse simulate_request = simulate_request(this.current_age, this.selected_age, str2);
        if (simulate_request.getStatusCode().equals("000")) {
            this.produitVal.setText("Avenir retraite");
            this.ageVal.setText(this.selected_age + " ans");
            this.montantVal.setText(this.period + " MAD");
            SimulatationRetraite simulatationRetraite = new SimulatationRetraite();
            try {
                simulatationRetraite.FillSimulationDataFromServiceResponse(simulate_request.getParamsOut());
                this.capitalVal.setText(DataTools.formatNumber(simulatationRetraite.CapitalConstitue));
                this.menuWizard.goToNextStep(this.s2, "", 16777215, 0);
            } catch (Exception unused) {
                this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Le service est momentanément indisponible. Veuillez réessayer ultérieurement.", null);
            }
        }
    }

    private boolean isMultiple100(Double d) {
        return d.doubleValue() % 100.0d == 0.0d;
    }
}
