package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.ServiceManager;
import com.b3g.services.TransfertDATA;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizard;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.CheckContainer;
import com.b3g.ui.Step;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OffreCIMRPage extends B3GPage {
    private B3GWizard offerWizard;
    private Step s1;
    private Step s2;
    private Step s3;
    private UIBuilder uib = new UIBuilder();
    private byte[] b = {1, 1, 1, 1};
    private String firstName = "";
    private String lastName = "";
    private String phoneNumber = "";
    private String dateOfBirth = "";
    private String email = "";

    static /* synthetic */ void access$000(OffreCIMRPage offreCIMRPage, Button button, boolean z) {
        offreCIMRPage.toggleStyle(button, z);
    }

    static /* synthetic */ Step access$100(OffreCIMRPage offreCIMRPage) {
        return offreCIMRPage.s2;
    }

    static /* synthetic */ B3GWizard access$200(OffreCIMRPage offreCIMRPage) {
        return offreCIMRPage.offerWizard;
    }

    public OffreCIMRPage(Object obj, Object obj2, int i) {
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
        this.thisContainer.getAllStyles().setPaddingUnit(this.b);
        this.thisContainer.getAllStyles().setPadding(0, 2, 0, 0);
        return this.thisContainer;
    }

    private Container getStep1Cnt() {
        Container container = new Container(new BorderLayout());
        container.getAllStyles().setMarginUnit(this.b);
        container.getAllStyles().setMargin(2.0f, 2.0f, 2.0f, 2.0f);
        Container container2 = new Container(new BorderLayout());
        container2.getAllStyles().setBgColor(16777215);
        container2.getAllStyles().setBgTransparency(255);
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(0.0f, 2.0f, 0.0f, 0.0f);
        Container container3 = new Container(BoxLayout.y());
        container3.getAllStyles().setPaddingUnit(1);
        container3.getAllStyles().setPadding(0.0f, 0.0f, 2.0f, 2.0f);
        SpanLabel spanLabel = new SpanLabel("AL MOUSTAKBAL INDIVIDUEL-CIMR");
        spanLabel.setTextUIID("DefaultTitleOrange");
        FlowLayout flowLayout = new FlowLayout(4);
        flowLayout.setFillRows(true);
        Container container4 = new Container(flowLayout);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("future-indiv.png"));
        container4.add(label);
        container4.getAllStyles().setMarginUnit(1);
        container4.getAllStyles().setMargin(1, 2, 0, 0);
        Button button = new Button("\u200b", "Container");
        button.setText("CONTACTÉ UN EXPERT CIMR");
        button.addActionListener(new OffreCIMRPage$$ExternalSyntheticLambda0(this));
        Container buttonStyle3 = CIHStyles.setButtonStyle3(button, (Image) null, 16777215, 15818018);
        button.getAllStyles().setPaddingUnit(1);
        button.getAllStyles().setPadding(1.4f, 1.4f, 2.0f, 2.0f);
        Style allStyles = button.getAllStyles();
        Float valueOf = Float.valueOf(2.1f);
        allStyles.setFont(CIHStyles.getFont(valueOf, 1));
        container3.add(spanLabel).add(container4).add(CIHStyles.createDottedList(new String[]{"Le meilleur taux de rendement du marché", "Des avantages fiscaux intéressants", "Un capital en cas de décès", "Une pension à vie et réversible. La réversion permet de verser la pension à votre conjoint et à vos enfants en cas de décès !"}, 4868682, CIHStyles.getFont(valueOf, 1)));
        container2.add("North", CIHStyles.hBorder(15818018)).add("Center", container3).add("South", buttonStyle3);
        container.add("Center", container2);
        return container;
    }

    /* synthetic */ void lambda$getStep1Cnt$1$com-b3g-ui-page-OffreCIMRPage(ActionEvent actionEvent) {
        ServiceResponse Check_CIMR = Check_CIMR();
        if (Check_CIMR.getStatusCode().equals("000")) {
            this.uiManager.btnBack.getListeners().clear();
            this.uiManager.btnBack.addActionListener(new OffreCIMRPage$$ExternalSyntheticLambda2(this));
            this.offerWizard.goToNextStep(this.s1, "", 16777215, 0);
            return;
        }
        this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, Check_CIMR.getStatusLabel(), null);
    }

    /* synthetic */ void lambda$getStep1Cnt$0$com-b3g-ui-page-OffreCIMRPage(ActionEvent actionEvent) {
        this.offerWizard.goToPreviousStep(this.s2, "", 16777215, 0);
        this.uiManager.btnBack.getListeners().clear();
        this.uiManager.btnBack.addActionListener(new 1());
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreCIMRPage.this.uiManager.GoBack();
        }
    }

    private Container getStep2Cnt() {
        Container container = new Container(new BorderLayout());
        container.getAllStyles().setMarginUnit(this.b);
        container.getAllStyles().setMargin(2.0f, 2.0f, 2.0f, 2.0f);
        Container container2 = new Container(new BorderLayout());
        Container container3 = new Container(BoxLayout.y());
        container3.setScrollableY(true);
        container3.setScrollVisible(false);
        Style allStyles = container2.getAllStyles();
        allStyles.setBgColor(16777215);
        allStyles.setBgTransparency(255);
        allStyles.setPaddingUnit(this.b);
        allStyles.setPadding(0.0f, 1.5f, 2.0f, 2.0f);
        SpanLabel spanLabel = new SpanLabel("Vos données personnelles");
        spanLabel.setTextUIID("DefaultTitleOrange");
        SpanLabel spanLabel2 = new SpanLabel("Vos coordonnées , ci-dessous, seront transmises à la CIMR pour un appel personnalisé");
        Style textAllStyles = spanLabel2.getTextAllStyles();
        Float valueOf = Float.valueOf(2.4f);
        textAllStyles.setFont(CIHStyles.getFont(valueOf, 1));
        spanLabel2.getTextAllStyles().setFgColor(1945583);
        SpanLabel spanLabel3 = new SpanLabel();
        spanLabel3.setText("Conformément à la loi 09-08, vous disposez d’un droit d’accès, de rectification et d’opposition au traitement de vos données personnelles. Ce traitement a été autorisé par la CNDP sous le n°A-M-529/2020\u200b");
        spanLabel3.getTextAllStyles().setFgColor(4868682);
        spanLabel3.getTextAllStyles().setFont(CIHStyles.getFont(valueOf, 1));
        spanLabel3.getAllStyles().setMarginUnit(1);
        spanLabel3.getAllStyles().setMargin(0.0f, 1.0f, 0.0f, 0.0f);
        SpanLabel spanLabel4 = new SpanLabel();
        spanLabel4.setText("Vous pouvez exercer vos droits auprès du responsable du traitement en justifiant de votre identité, en envoyant un courrier électronique à l’adresse suivante : conformite@cimr.ma ou par voie postale à l’adresse suivante : CIMR-Département Compliance Intersection boulevard de l’Aéropostale et boulevard Main Street, Projet CASA ANFA, Hay Hassani, Casablanca.\u200b");
        spanLabel4.getTextAllStyles().setFgColor(4868682);
        spanLabel4.getTextAllStyles().setFont(CIHStyles.getFont(valueOf, 1));
        spanLabel4.getAllStyles().setMarginUnit(1);
        spanLabel4.getAllStyles().setMargin(0.0f, 1.0f, 0.0f, 0.0f);
        SpanLabel spanLabel5 = new SpanLabel();
        spanLabel5.setText("En cochant les cases, ci-dessous, j’accepte à ce que mes données personnelles soient collectées et traitées par la CIMR conformément à sa Politique de traitement des données personnelles.\u200b");
        spanLabel5.getTextAllStyles().setFgColor(4868682);
        spanLabel5.getTextAllStyles().setFont(CIHStyles.getFont(valueOf, 1));
        spanLabel5.getAllStyles().setMarginUnit(1);
        spanLabel5.getAllStyles().setMargin(0.0f, 1.0f, 0.0f, 0.0f);
        CheckContainer checkContainer = new CheckContainer("J’autorise la CIMR à utiliser mes données personnelles afin de m’envoyer des SMS et des emails d’information pouvant contenir des offres commerciales de la CIMR et de ses partenaires.\u200b", 1945583);
        Container container4 = new Container(new GridLayout(1, 2));
        Button button = new Button("PRÉCÉDENT");
        Button button2 = new Button("SUIVANT");
        Container buttonStyle = setButtonStyle(button, 15818018, 15658220, false);
        Container buttonStyle2 = setButtonStyle(button2, 16382713, 15818018, true);
        container4.getAllStyles().setMarginUnit(1);
        buttonStyle.getAllStyles().setMarginUnit(1);
        buttonStyle2.getAllStyles().setMarginUnit(1);
        container4.getAllStyles().setMarginTop(3.0f);
        buttonStyle.getAllStyles().setMarginRight(1.0f);
        buttonStyle2.getAllStyles().setMarginLeft(1.0f);
        buttonStyle2.setEnabled(false);
        checkContainer.addChangeListener(new 2(checkContainer, buttonStyle2));
        button2.addActionListener(new OffreCIMRPage$$ExternalSyntheticLambda4(this));
        button.addActionListener(new 3());
        container4.add(buttonStyle).add(buttonStyle2);
        container3.add(spanLabel);
        container3.add(spanLabel2);
        container3.add(drawInfosBox());
        container3.add(spanLabel3);
        container3.add(spanLabel4);
        container3.add(spanLabel5);
        container3.add(checkContainer.getContainer());
        container3.add(container4);
        container2.add("Center", container3);
        container2.getAllStyles().setBorder(Border.createCompoundBorder(Border.createLineBorder(5, CIHStyles.colorOrange), null, null, null));
        container.add("Center", container2);
        return container;
    }

    class 2 implements ActionListener {
        final /* synthetic */ CheckContainer val$check_1;
        final /* synthetic */ Container val$next_container;

        2(CheckContainer checkContainer, Container container) {
            this.val$check_1 = checkContainer;
            this.val$next_container = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$check_1.isSelected()) {
                this.val$next_container.setEnabled(true);
                OffreCIMRPage.access$000(OffreCIMRPage.this, (Button) this.val$next_container.getLeadComponent(), false);
            } else {
                this.val$next_container.setEnabled(false);
                OffreCIMRPage.access$000(OffreCIMRPage.this, (Button) this.val$next_container.getLeadComponent(), true);
            }
            this.val$next_container.revalidate();
        }
    }

    /* synthetic */ void lambda$getStep2Cnt$3$com-b3g-ui-page-OffreCIMRPage(ActionEvent actionEvent) {
        ServiceResponse sendRequest = sendRequest(this.dateOfBirth);
        if (sendRequest.getStatusCode().equals("000")) {
            this.uiManager.btnBack.getListeners().clear();
            this.uiManager.btnBack.addActionListener(new OffreCIMRPage$$ExternalSyntheticLambda1(this));
            this.offerWizard.goToNextStep(this.s2, "", 16777215, 0);
        } else if (sendRequest.getStatusCode().equals("001")) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Nous vous informons que votre demande a été transmise à la CIMR. Pour toute information merci de contacter le 0522050808.", null);
        } else {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Une erreur est survenue.", null);
        }
    }

    /* synthetic */ void lambda$getStep2Cnt$2$com-b3g-ui-page-OffreCIMRPage(ActionEvent actionEvent) {
        this.uiManager.GoBack();
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OffreCIMRPage.access$200(OffreCIMRPage.this).goToPreviousStep(OffreCIMRPage.access$100(OffreCIMRPage.this), "", 16777215, 0);
        }
    }

    private Container getStep3Cnt() {
        Container container = new Container(new BorderLayout());
        container.getAllStyles().setMarginUnit(this.b);
        container.getAllStyles().setMargin(2.0f, 2.0f, 2.0f, 2.0f);
        Container container2 = new Container(new BorderLayout());
        Style allStyles = container2.getAllStyles();
        allStyles.setBgColor(16777215);
        allStyles.setBgTransparency(255);
        allStyles.setPaddingUnit(this.b);
        allStyles.setPaddingBottom(1.5f);
        SpanLabel spanLabel = new SpanLabel("AL MOUSTAKBAL INDIVIDUEL-CIMR");
        spanLabel.setTextUIID("DefaultTitleOrange");
        spanLabel.getAllStyles().setPaddingUnit(this.b);
        spanLabel.getAllStyles().setPadding(0.0f, 0.0f, 2.0f, 2.0f);
        FlowLayout flowLayout = new FlowLayout(4);
        flowLayout.setFillRows(true);
        Container container3 = new Container(flowLayout);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("MotdePasse.png"));
        container3.add(label);
        container3.getAllStyles().setMarginUnit(1);
        container3.getAllStyles().setMargin(1, 2, 0, 0);
        SpanLabel spanLabel2 = new SpanLabel("Votre demande a bien été prise en charge. Vous serez contacté par un conseiller CIMR dans les plus brefs délais\u200b");
        spanLabel2.getTextAllStyles().setFont(CIHStyles.getFont(Float.valueOf(4.0f), 0));
        spanLabel2.getTextAllStyles().setAlignment(4);
        spanLabel2.getAllStyles().setMarginUnit(1);
        spanLabel2.getAllStyles().setMargin(0.0f, 0.0f, 3.0f, 3.0f);
        Container container4 = new Container(BoxLayout.yCenter());
        container4.add(container3).add(spanLabel2);
        Button button = new Button("RETOUR A L’ACCUEIL");
        button.addActionListener(new OffreCIMRPage$$ExternalSyntheticLambda3(this));
        Container buttonStyle = setButtonStyle(button, 16777215, 15752481, false);
        buttonStyle.getAllStyles().setMarginUnit(this.b);
        buttonStyle.getAllStyles().setMargin(0.0f, 0.0f, 2.0f, 2.0f);
        Container container5 = new Container(BoxLayout.y());
        container5.add(CIHStyles.hBorder(15818018)).add(spanLabel);
        container2.add("North", container5).add("Center", container4).add("South", buttonStyle);
        container.add("Center", container2);
        return container;
    }

    class 4 implements Runnable {
        4() {
        }

        public void run() {
            new UITimer(new OffreCIMRPage$4$$ExternalSyntheticLambda0()).schedule(300, false, CihMBanking.sesPMTR.getCurrentUiManager().currentForm);
        }

        static /* synthetic */ void lambda$run$0() {
            CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    /* synthetic */ void lambda$getStep3Cnt$4$com-b3g-ui-page-OffreCIMRPage(ActionEvent actionEvent) {
        Display.getInstance().callSerially(new 4());
    }

    public static Container createSimpleDottedList(String[] strArr) {
        Container container = new Container(BoxLayout.y());
        for (String str : strArr) {
            Container container2 = new Container(new BoxLayout(1));
            Label label = new Label("•");
            label.getAllStyles().setAlignment(0);
            SpanLabel spanLabel = new SpanLabel(str);
            spanLabel.getTextAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.0f), 0));
            label.getAllStyles().setFgColor(0);
            label.getAllStyles().setFont(Font.createTrueTypeFont("native:MainThin", "native:MainThin").derive(Display.getInstance().convertToPixels(3, true), 1));
            spanLabel.getAllStyles().setMarginLeft(2);
            container2.add(label);
            container2.add(spanLabel);
            container.add(container2);
        }
        return container;
    }

    private Container drawInfosBox() {
        Container border = setBorder(new Container(new BoxLayout(2)));
        this.firstName = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom;
        this.lastName = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom;
        String str = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().dateOfBirth;
        this.dateOfBirth = str;
        if (str != null && !str.equals("")) {
            String[] split = DataTools.split(this.dateOfBirth, "-");
            this.dateOfBirth = split[2] + "/" + split[1] + "/" + split[0];
        }
        this.phoneNumber = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm;
        this.email = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email;
        System.out.println(this.firstName);
        System.out.println(this.lastName);
        System.out.println(this.phoneNumber);
        System.out.println(this.email);
        border.add(getDataRow("Nom Complet :", this.firstName, 15752481)).add(getDataRow("Date de naissance :", this.dateOfBirth, 15752481)).add(getDataRow("Numéro de téléphone :", this.phoneNumber, 15752481)).add(getDataRow("Adresse mail :", this.email, 1945583));
        border.getAllStyles().setMarginUnit(1);
        border.getAllStyles().setPaddingUnit(1);
        border.getAllStyles().setMargin(3.0f, 2.0f, 0.0f, 0.0f);
        border.getAllStyles().setPadding(2.0f, 2.0f, 3.0f, 3.0f);
        return border;
    }

    private Container getDataRow(String str, String str2, int i) {
        Container container = new Container(new BorderLayout());
        Label label = new Label(str);
        label.getAllStyles().setFgColor(6645093);
        Style allStyles = label.getAllStyles();
        Float valueOf = Float.valueOf(2.5f);
        allStyles.setFont(CIHStyles.getFont(valueOf, 1));
        Label label2 = new Label(str2);
        label2.getAllStyles().setFgColor(i);
        label2.getAllStyles().setFont(CIHStyles.getFont(valueOf, 1));
        container.add("East", label2).add("West", label);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(1.0f, 1.0f, 0.0f, 0.0f);
        return container;
    }

    private Container setBorder(Container container) {
        container.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(0.0f).stroke(new Stroke(2.5f, 2, 0, 4.0f)).strokeColor(15000804).strokeOpacity(255));
        return container;
    }

    private Container setButtonStyle(Button button, int i, int i2, boolean z) {
        Container container = new Container(new GridLayout(1, 1));
        button.setUIID("Container");
        button.getAllStyles().setAlignment(4);
        button.getAllStyles().setBgTransparency(255);
        button.getAllStyles().setPaddingUnit(1);
        button.getAllStyles().setPadding(1.2f, 1.6f, 0.0f, 0.0f);
        button.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.5f), 1));
        if (z) {
            button.getAllStyles().setBgColor(13619151);
            button.getAllStyles().setFgColor(i);
        } else {
            button.getAllStyles().setBgColor(i2);
            button.getAllStyles().setFgColor(i);
        }
        container.add(button);
        container.setLeadComponent(button);
        return container;
    }

    private void toggleStyle(Button button, boolean z) {
        if (z) {
            button.getAllStyles().setBgColor(13619151);
            button.getAllStyles().setFgColor(16382713);
        } else {
            button.getAllStyles().setBgColor(15818018);
            button.getAllStyles().setFgColor(16777215);
        }
    }

    private Hashtable wrappUserData() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        return hashtable;
    }

    private ServiceResponse sendRequest(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable wrappUserData = wrappUserData();
        wrappUserData.put("BirthDate", str);
        serviceRequest.setParamsIn(wrappUserData);
        serviceRequest.setServiceId(901006);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        ServiceResponse serviceResponse = (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
        System.out.println("CIMR");
        System.out.println(serviceRequest.ToJsonString());
        return serviceResponse;
    }

    private ServiceResponse Check_CIMR() {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(901009);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }
}
