package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizard;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.CihUiManager;
import com.b3g.ui.Step;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ConfirmEMail {
    Dialog ConfirmEmailPopUp;
    String VnewaliasText;
    String confirmefEmail;
    Label emailLbl;
    ArrayList listSteps;
    TextField otpTxtF;
    B3GWizard profilWizard;
    Step s1;
    Step s2;
    Step s3;
    Step s4;
    Step s5;
    B3GUiManager uiManager;
    UIBuilder uib = new UIBuilder();
    Container thisContainer = new Container(BoxLayout.y());
    boolean canChangeEmail = false;
    String newEmail = "";

    public ConfirmEMail(B3GUiManager b3GUiManager) {
        this.uiManager = b3GUiManager;
    }

    public Dialog getPageDialog(StateMachine stateMachine) {
        this.listSteps = new ArrayList();
        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email.isEmpty()) {
            Step step = new Step();
            this.s1 = step;
            step.icon = "1inactif.png";
            this.s1.selectedIcon = "1actif.png";
            this.s1.stepCnt = drawStep1Cnt();
            this.listSteps.add(this.s1);
        }
        Step step2 = new Step();
        this.s2 = step2;
        step2.icon = "1inactif.png";
        this.s2.selectedIcon = "1actif.png";
        this.s2.stepCnt = drawStep2Cnt();
        Step step3 = new Step();
        this.s3 = step3;
        step3.icon = "1inactif.png";
        this.s3.selectedIcon = "1actif.png";
        this.s3.stepCnt = drawStep3Cnt();
        Step step4 = new Step();
        this.s4 = step4;
        step4.icon = "1inactif.png";
        this.s4.selectedIcon = "1actif.png";
        this.s4.stepCnt = drawStep4Cnt();
        Step step5 = new Step();
        this.s5 = step5;
        step5.icon = "1inactif.png";
        this.s5.selectedIcon = "1actif.png";
        this.s5.stepCnt = drawStep5Cnt();
        this.listSteps.add(this.s2);
        this.listSteps.add(this.s3);
        this.listSteps.add(this.s4);
        this.listSteps.add(this.s5);
        B3GWizard b3GWizard = new B3GWizard(this.listSteps);
        this.profilWizard = b3GWizard;
        b3GWizard.withHeader = false;
        this.thisContainer = this.profilWizard.drawWizard("", 16777215, 0);
        Dialog dialog = new Dialog();
        this.ConfirmEmailPopUp = dialog;
        dialog.setLayout(new BorderLayout());
        this.ConfirmEmailPopUp.setPreferredW(Display.getInstance().getDisplayWidth());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "PopUpMaj");
        Button button = (Button) uIBuilder.findByName("logOutBtn", createContainer);
        Component component = (Container) uIBuilder.findByName("LogoCnt", createContainer);
        Container container = (Container) uIBuilder.findByName("leftCnt", createContainer);
        component.getParent().removeComponent(component);
        Container container2 = new Container(new BorderLayout());
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(2.0f, 2.0f, 3.0f, 3.0f);
        container2.setScrollVisible(false);
        Button button2 = new Button(CihMBanking.backIcon);
        button2.setUIID("Container");
        button2.getAllStyles().setPaddingUnit(1);
        button2.getAllStyles().setPaddingLeft(2);
        button2.getAllStyles().setPaddingRight(2);
        container.add(button2);
        button.addActionListener(new 1(stateMachine));
        button2.addActionListener(new 2());
        container2.add("North", component);
        container2.add("Center", this.thisContainer);
        container2.setPreferredH(Display.getInstance().getDisplayHeight());
        container2.setPreferredW(Display.getInstance().getDisplayWidth());
        this.ConfirmEmailPopUp.addComponent("Center", container2);
        createContainer.revalidate();
        this.ConfirmEmailPopUp.showPacked("Center", true);
        return this.ConfirmEmailPopUp;
    }

    class 1 implements ActionListener {
        final /* synthetic */ StateMachine val$stsMachine;

        1(StateMachine stateMachine) {
            this.val$stsMachine = stateMachine;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ConfirmEMail.this.ShowDisconectingPopUp(this.val$stsMachine);
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            int i = ConfirmEMail.this.profilWizard.currentStepId;
            if (i == 0) {
                ConfirmEMail.this.ConfirmEmailPopUp.dispose();
                return;
            }
            if (i == 2) {
                ConfirmEMail.this.profilWizard.goToStepById(ConfirmEMail.this.profilWizard.getStepById(ConfirmEMail.this.profilWizard.currentStepId), 0, "", 16777215, 0);
            } else if (i == 4) {
                ConfirmEMail.this.ConfirmEmailPopUp.dispose();
            } else {
                ConfirmEMail.this.profilWizard.goToPreviousStep(ConfirmEMail.this.profilWizard.getStepById(ConfirmEMail.this.profilWizard.currentStepId), "", 16777215, 0);
            }
        }
    }

    private Container drawStep1Cnt() {
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(new BorderLayout());
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setFillRows(true);
        Container container3 = new Container();
        container3.setLayout(flowLayout);
        container3.getAllStyles().setBgColor(CIHStyles.colorOrange);
        container3.getAllStyles().setBgTransparency(255);
        container3.getAllStyles().setPaddingUnit(0);
        container3.getAllStyles().setPadding(2.0f, 2.0f, 0.5f, 0.5f);
        Container container4 = new Container(BoxLayout.y());
        SpanLabel spanLabel = new SpanLabel("Validation de l'adresse e-mail");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        spanLabel.getAllStyles().setPaddingUnit(1);
        spanLabel.getAllStyles().setPaddingLeft(0);
        spanLabel.getAllStyles().setAlignment(1);
        spanLabel.getTextAllStyles().setPaddingLeft(0);
        spanLabel.getTextAllStyles().setMarginLeft(0);
        container4.add(spanLabel);
        new FlowLayout(4).setFillRows(true);
        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email.isEmpty()) {
            SpanLabel spanLabel2 = new SpanLabel("Pour une meilleure communication et gestion de votre compte nous vous invitons à valider votre adresse e-mail :");
            spanLabel2.setTextUIID("Container");
            spanLabel2.setUIID("Container");
            spanLabel2.getTextAllStyles().setAlignment(1);
            spanLabel2.getAllStyles().setMarginUnit(1);
            spanLabel2.getAllStyles().setMargin(0, 4, 0, 0);
            SpanLabel spanLabel3 = new SpanLabel(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email.toLowerCase());
            spanLabel3.setTextUIID("Container");
            spanLabel3.setUIID("Container");
            spanLabel3.getTextAllStyles().setAlignment(4);
            spanLabel3.getAllStyles().setMarginUnit(1);
            spanLabel3.getAllStyles().setMargin(0, 4, 0, 0);
            spanLabel3.getTextAllStyles().setFgColor(1945583);
            container4.add(spanLabel2).add(spanLabel3);
        }
        container4.setScrollVisible(false);
        container4.setScrollableY(true);
        Button button = new Button("\u200b", "Container");
        button.setText("Oui, je confirme\u200b");
        Container buttonStyle2 = CIHStyles.setButtonStyle2(button, null, 16777215, CIHStyles.colorOrange);
        button.addActionListener(new 3());
        buttonStyle2.getAllStyles().setMarginUnit(1);
        buttonStyle2.getAllStyles().setMargin(5, 2, 8, 8);
        Button button2 = new Button("\u200b", "Container");
        button2.setText("Non, je modifie\u200b\u200b");
        Container buttonStyle22 = CIHStyles.setButtonStyle2(button2, null, 16777215, CIHStyles.colorOrange);
        button2.addActionListener(new 4());
        Button button3 = new Button("\u200b", "Container");
        button3.setText("PLUS TARD");
        Component buttonStyle23 = CIHStyles.setButtonStyle2(button3, null, 16777215, CIHStyles.colorOrange);
        button3.addActionListener(new 5());
        buttonStyle23.getAllStyles().setMarginUnit(1);
        buttonStyle23.getAllStyles().setMarginBottom(2);
        buttonStyle22.getAllStyles().setMarginUnit(1);
        buttonStyle22.getAllStyles().setMargin(5, 2, 8, 8);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.5f, 3.0f, 3.0f, 3.0f);
        container.getAllStyles().setBgColor(16382457);
        container.getAllStyles().setBgTransparency(255);
        container4.getAllStyles().setPaddingUnit(1);
        container4.getAllStyles().setPaddingBottom(3);
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(2, 0, 3, 3);
        container4.add(buttonStyle2).add(buttonStyle22);
        SpanLabel spanLabel4 = new SpanLabel("Un email de vérification vous sera envoyé.\u200b");
        spanLabel4.setTextUIID("Container");
        spanLabel4.setUIID("Container");
        spanLabel4.getTextAllStyles().setAlignment(4);
        spanLabel4.getAllStyles().setMarginUnit(1);
        spanLabel4.getAllStyles().setMargin(5, 4, 0, 0);
        container4.add(spanLabel4);
        container2.getAllStyles().setBgColor(16777215);
        container2.getAllStyles().setBgTransparency(255);
        container2.add("Center", container4);
        container2.add("South", buttonStyle23);
        container.add("North", container3);
        container.add("Center", container2);
        return container;
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ConfirmEMail.this.newEmail = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email;
            ConfirmEMail.this.emailLbl.setText(ConfirmEMail.this.newEmail);
            ServiceResponse simulateChangementEmail = ConfirmEMail.this.simulateChangementEmail();
            if (simulateChangementEmail.getStatusCode().equals("000")) {
                ConfirmEMail.this.profilWizard.goToStepById(ConfirmEMail.this.s1, 2, "", 16777215, 0);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(simulateChangementEmail.getStatusLabel()), null);
            }
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ConfirmEMail.this.profilWizard.goToNextStep(ConfirmEMail.this.s1, "", 16777215, 0);
        }
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getSessionClient().getClient_Profil().HasToConfirmEmail = false;
            ConfirmEMail.this.ConfirmEmailPopUp.dispose();
        }
    }

    private Container drawStep2Cnt() {
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(new BorderLayout());
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setFillRows(true);
        Container container3 = new Container();
        container3.setLayout(flowLayout);
        container3.getAllStyles().setBgColor(CIHStyles.colorOrange);
        container3.getAllStyles().setBgTransparency(255);
        container3.getAllStyles().setPaddingUnit(0);
        container3.getAllStyles().setPadding(2.0f, 2.0f, 0.5f, 0.5f);
        Container container4 = new Container(BoxLayout.y());
        SpanLabel spanLabel = new SpanLabel("Validation de l'adresse e-mail");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        spanLabel.getAllStyles().setPaddingUnit(1);
        spanLabel.getAllStyles().setPaddingLeft(0);
        spanLabel.getAllStyles().setAlignment(1);
        spanLabel.getTextAllStyles().setPaddingLeft(0);
        spanLabel.getTextAllStyles().setMarginLeft(0);
        new FlowLayout(4).setFillRows(true);
        SpanLabel spanLabel2 = new SpanLabel("La vérification de votre adresse email vous permettra de communiquer avec la banque ainsi que de recevoir des notifications transactionnelles et publicitaires.");
        spanLabel2.setTextUIID("Container");
        spanLabel2.setUIID("Container");
        spanLabel2.getTextAllStyles().setAlignment(1);
        spanLabel2.getAllStyles().setMarginUnit(1);
        spanLabel2.getAllStyles().setMargin(0, 2, 0, 0);
        Container container5 = new Container(BoxLayout.y());
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        textField.setHint("Nouvelle adresse e-mail");
        textField2.setHint("Confirmation de la nouvelle adresse e-mail");
        container5.getAllStyles().setMarginUnit(1);
        container5.getAllStyles().setMargin(8, 0, 0, 0);
        Container textFieldStyle = CIHStyles.setTextFieldStyle(textField, CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("Icon feather mail.png"));
        Container textFieldStyle2 = CIHStyles.setTextFieldStyle(textField2, CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("Icon feather mail.png"));
        textField.setUIID("LoginTextF");
        textField2.setUIID("LoginTextF");
        container5.add(textFieldStyle).add(textFieldStyle2);
        container4.add(spanLabel).add(spanLabel2).add(container5);
        container4.setScrollVisible(false);
        container4.setScrollableY(true);
        Button button = new Button("\u200b", "Container");
        button.setText("VALIDER");
        Container buttonStyle2 = CIHStyles.setButtonStyle2(button, null, 16777215, CIHStyles.colorOrange);
        button.addActionListener(new 6(textField, textField2));
        buttonStyle2.getAllStyles().setMarginUnit(1);
        buttonStyle2.getAllStyles().setMarginBottom(2);
        Button button2 = new Button("\u200b", "Container");
        button2.setText("PLUS TARD");
        Container buttonStyle22 = CIHStyles.setButtonStyle2(button2, null, 16777215, CIHStyles.colorOrange);
        button2.addActionListener(new 7());
        buttonStyle22.getAllStyles().setMarginUnit(1);
        buttonStyle22.getAllStyles().setMarginBottom(2);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.5f, 3.0f, 3.0f, 3.0f);
        container.getAllStyles().setBgColor(16382457);
        container.getAllStyles().setBgTransparency(255);
        container4.getAllStyles().setPaddingUnit(1);
        container4.getAllStyles().setPaddingBottom(3);
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(2, 0, 3, 3);
        container2.getAllStyles().setBgColor(16777215);
        container2.getAllStyles().setBgTransparency(255);
        container2.add("Center", container4);
        container2.add("South", BoxLayout.encloseY(buttonStyle2, buttonStyle22));
        container.add("North", container3);
        container.add("Center", container2);
        return container;
    }

    class 6 implements ActionListener {
        final /* synthetic */ TextField val$ConfirmEmailTxtf;
        final /* synthetic */ TextField val$emailTxtf;

        6(TextField textField, TextField textField2) {
            this.val$emailTxtf = textField;
            this.val$ConfirmEmailTxtf = textField2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$emailTxtf.getText().isEmpty() || this.val$ConfirmEmailTxtf.getText().isEmpty()) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ConfirmEMail.this.uiManager.stateMachine, "Veuillez saisir tous les champs obligatoires", null);
                return;
            }
            if (!DataTools.emailChecker(this.val$emailTxtf.getText())) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ConfirmEMail.this.uiManager.stateMachine, "Veuillez saisir une adresse mail valide", null);
                return;
            }
            if (!this.val$emailTxtf.getText().equals(this.val$ConfirmEmailTxtf.getText())) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ConfirmEMail.this.uiManager.stateMachine, "Les emails ne sont pas identiques", null);
                return;
            }
            if (this.val$emailTxtf.getText().toLowerCase().equals(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email.toLowerCase())) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ConfirmEMail.this.uiManager.stateMachine, "Cette adresse email est la même enregistrée actuellement", null);
                Display.getInstance().callSerially(new 1());
                return;
            }
            ConfirmEMail.this.newEmail = this.val$emailTxtf.getText();
            ConfirmEMail.this.emailLbl.setText(this.val$emailTxtf.getText());
            ServiceResponse simulateChangementEmail = ConfirmEMail.this.simulateChangementEmail();
            if (simulateChangementEmail.getStatusCode().equals("000")) {
                ConfirmEMail.this.profilWizard.goToNextStep(ConfirmEMail.this.s2, "", 16777215, 0);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(simulateChangementEmail.getStatusLabel()), null);
            }
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                ConfirmEMail.this.ConfirmEmailPopUp.dispose();
            }
        }
    }

    class 7 implements ActionListener {
        7() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getSessionClient().getClient_Profil().HasToConfirmEmail = false;
            ConfirmEMail.this.ConfirmEmailPopUp.dispose();
        }
    }

    private Container drawStep3Cnt() {
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(new BorderLayout());
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setFillRows(true);
        Container container3 = new Container();
        container3.setLayout(flowLayout);
        container3.getAllStyles().setBgColor(CIHStyles.colorOrange);
        container3.getAllStyles().setBgTransparency(255);
        Container container4 = new Container(BoxLayout.y());
        SpanLabel spanLabel = new SpanLabel("Validation de l'adresse e-mail");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        spanLabel.getAllStyles().setPaddingUnit(1);
        spanLabel.getAllStyles().setPaddingLeft(0);
        spanLabel.getAllStyles().setAlignment(1);
        spanLabel.getAllStyles().setPaddingLeft(0);
        spanLabel.getAllStyles().setMarginLeft(0);
        new FlowLayout(4).setFillRows(true);
        TextField textField = new TextField("", "", 10, 2);
        Container textFieldStyle = CIHStyles.setTextFieldStyle(textField, CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("changerMDP.png"));
        textFieldStyle.getAllStyles().setMargin(8, 0, 0, 0);
        textField.setUIID("Container");
        textField.getAllStyles().setMargin(1, 0, 0, 0);
        textField.getAllStyles().setAlignment(1);
        SpanLabel spanLabel2 = new SpanLabel("Un e-mail de confirmation a été envoyé à:\n");
        spanLabel2.getTextAllStyles().setAlignment(4);
        Label label = new Label();
        this.emailLbl = label;
        label.getAllStyles().setFgColor(1945583);
        SpanLabel spanLabel3 = new SpanLabel("Veuillez introduire le code reçu par Email");
        spanLabel3.getTextAllStyles().setAlignment(4);
        spanLabel3.getAllStyles().setMargin(2, 0, 0, 0);
        container4.add(spanLabel).add(spanLabel2).add(FlowLayout.encloseCenter(this.emailLbl)).add(spanLabel3).add(textFieldStyle).add(FlowLayout.encloseCenter(new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("Email-amico.png"))));
        container4.setScrollVisible(false);
        container4.setScrollableY(true);
        Container container5 = new Container(new BoxLayout(2));
        Button button = new Button("\u200b", "Container");
        button.setText("PLUS TARD");
        Container buttonStyle2 = CIHStyles.setButtonStyle2(button, null, 16777215, CIHStyles.colorOrange);
        button.addActionListener(new 8());
        Button button2 = new Button("\u200b", "Container");
        button2.setText("VALIDER");
        Container buttonStyle22 = CIHStyles.setButtonStyle2(button2, null, 16777215, CIHStyles.colorOrange);
        button2.addActionListener(new 9(textField));
        buttonStyle2.getAllStyles().setMarginUnit(1);
        buttonStyle2.getAllStyles().setMarginBottom(2);
        buttonStyle22.getAllStyles().setMarginUnit(1);
        buttonStyle22.getAllStyles().setMarginBottom(2);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.5f, 3.0f, 3.0f, 3.0f);
        container.getAllStyles().setBgColor(16382457);
        container.getAllStyles().setBgTransparency(255);
        container4.getAllStyles().setPaddingUnit(1);
        container4.getAllStyles().setPaddingBottom(3);
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(2, 0, 3, 3);
        container5.add(buttonStyle2).add(buttonStyle22);
        container2.getAllStyles().setBgColor(16777215);
        container2.getAllStyles().setBgTransparency(255);
        container2.add("Center", container4);
        container2.add("South", container5);
        container3.getAllStyles().setPaddingUnit(0);
        container3.getAllStyles().setPadding(2.0f, 2.0f, 0.5f, 0.5f);
        container.add("North", container3);
        container.add("Center", container2);
        return container;
    }

    class 8 implements ActionListener {
        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    class 9 implements ActionListener {
        final /* synthetic */ TextField val$codeTxtf;

        9(TextField textField) {
            this.val$codeTxtf = textField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$codeTxtf.getText().isEmpty()) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ConfirmEMail.this.uiManager.stateMachine, "Veuillez saisir le code.", null);
                return;
            }
            ServiceResponse confirmeChangementEmail_byCodeEmail = ConfirmEMail.this.confirmeChangementEmail_byCodeEmail(this.val$codeTxtf.getText());
            if (confirmeChangementEmail_byCodeEmail.getStatusCode().equals("000")) {
                SessionParameter.setOtpTextField(ConfirmEMail.this.otpTxtF);
                ConfirmEMail.this.profilWizard.goToNextStep(ConfirmEMail.this.s3, "", 16777215, 0);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(confirmeChangementEmail_byCodeEmail.getStatusLabel()), null);
            }
        }
    }

    private Container drawStep4Cnt() {
        Container container = new Container(BoxLayout.y());
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container2 = (Container) this.uib.findByName("Container17", createContainer);
        container2.removeAll();
        container2.setLayout(BoxLayout.y());
        container2.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 10());
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 11(b3GCIHEcode));
        container.add(createContainer);
        return container;
    }

    class 10 implements ActionListener {
        10() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ConfirmEMail.this.uiManager.GoBack();
        }
    }

    class 11 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        11(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ConfirmEMail.this.VnewaliasText = this.val$eCode1.getValue();
            if (ConfirmEMail.this.VnewaliasText.length() == 0) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ConfirmEMail.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            ConfirmEMail confirmEMail = ConfirmEMail.this;
            ServiceResponse confirmeChangementEmail_byOtp = confirmEMail.confirmeChangementEmail_byOtp(confirmEMail.VnewaliasText);
            if (confirmeChangementEmail_byOtp.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email = ConfirmEMail.this.newEmail;
                CihMBanking.sesPMTR.getSessionClient().getClient_Profil().HasToUpdateEmail = false;
                ConfirmEMail.this.uiManager.btnBack.getListeners().clear();
                ConfirmEMail.this.uiManager.btnBack.addActionListener(new ConfirmEMail$11$$ExternalSyntheticLambda0(this));
                ConfirmEMail.this.profilWizard.goToNextStep(ConfirmEMail.this.s4, "", 16777215, 0);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(confirmeChangementEmail_byOtp.getStatusLabel()), null);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-ConfirmEMail$11(ActionEvent actionEvent) {
            ConfirmEMail.this.profilWizard.goToPreviousStep(ConfirmEMail.this.s5, "", 16777215, 0);
            ConfirmEMail.this.uiManager.btnBack.getListeners().clear();
            ConfirmEMail.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                ConfirmEMail.this.profilWizard.goToPreviousStep(ConfirmEMail.this.s4, "", 16777215, 0);
                ConfirmEMail.this.uiManager.btnBack.getListeners().clear();
                ConfirmEMail.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    ConfirmEMail.this.uiManager.GoBack();
                }
            }
        }
    }

    private Container drawStep5Cnt() {
        Container container = new Container(new BorderLayout());
        Container container2 = new Container();
        container2.setLayout(new BorderLayout(2));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setFillRows(true);
        Container container3 = new Container();
        container3.setLayout(flowLayout);
        container3.getAllStyles().setBgColor(CIHStyles.colorOrange);
        container3.getAllStyles().setBgTransparency(255);
        Container container4 = new Container(BoxLayout.y());
        SpanLabel spanLabel = new SpanLabel("Changement d’adresse e-mail");
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
        spanLabel.getAllStyles().setPaddingUnit(1);
        spanLabel.getAllStyles().setPaddingLeft(0);
        spanLabel.getAllStyles().setAlignment(1);
        spanLabel.getAllStyles().setPaddingLeft(0);
        spanLabel.getAllStyles().setMarginLeft(0);
        Container container5 = new Container(BoxLayout.x());
        container5.add(spanLabel);
        new FlowLayout(4).setFillRows(true);
        container4.setScrollVisible(false);
        container4.setScrollableY(true);
        Button button = new Button("\u200b", "Container");
        button.setText("retour à l'acceuil".toUpperCase());
        Container buttonStyle2 = CIHStyles.setButtonStyle2(button, null, 16777215, CIHStyles.colorOrange);
        button.addActionListener(new 12());
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.5f, 3.0f, 3.0f, 3.0f);
        container.getAllStyles().setBgColor(16382457);
        container.getAllStyles().setBgTransparency(255);
        container4.getAllStyles().setPaddingUnit(1);
        container4.getAllStyles().setPaddingBottom(3);
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(2, 0, 3, 3);
        container2.getAllStyles().setBgColor(16777215);
        container2.getAllStyles().setBgTransparency(255);
        container4.setScrollVisible(false);
        container4.setScrollableY(true);
        Label label = new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("new ok.png"));
        label.getAllStyles().setPaddingUnit(1);
        label.getAllStyles().setPadding(3, 3, 0, 0);
        SpanLabel spanLabel2 = new SpanLabel("Votre adresse e-mail a bien été confirmée.");
        spanLabel2.getTextAllStyles().setAlignment(4);
        spanLabel2.getAllStyles().setPaddingUnit(1);
        spanLabel2.getAllStyles().setPadding(0, 0, 10, 10);
        container4.add(FlowLayout.encloseCenter(label)).add(FlowLayout.encloseCenter(spanLabel2));
        container2.add("North", container5);
        container2.add("Center", container4);
        container2.add("South", buttonStyle2);
        container3.getAllStyles().setPaddingUnit(0);
        container3.getAllStyles().setPadding(2.0f, 2.0f, 0.5f, 0.5f);
        container.add("North", container3);
        container.add("Center", container2);
        return container;
    }

    class 12 implements ActionListener {
        12() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ConfirmEMail.this.ConfirmEmailPopUp.dispose();
        }
    }

    public ServiceResponse simulateChangementEmail() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("EMAIL", this.newEmail);
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(20001);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse confirmeChangementEmail_byCodeEmail(String str) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("PASSID", str);
        hashtable.put("EMAIL", this.newEmail);
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(20003);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse confirmeChangementEmail_byOtp(String str) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("PASSID", str);
        hashtable.put("EMAIL", this.newEmail);
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(20002);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
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
            stateMachine.findBtnYest(createContainer).addActionListener(new 13(stateMachine, open));
            stateMachine.findBtnYestIcon(createContainer).addActionListener(new 14(stateMachine, open));
            stateMachine.findBtnNo(createContainer).addActionListener(new 15(dialog));
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 16(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 13 implements ActionListener {
        final /* synthetic */ Resources val$myres;
        final /* synthetic */ StateMachine val$stsMachine;

        13(StateMachine stateMachine, Resources resources) {
            this.val$stsMachine = stateMachine;
            this.val$myres = resources;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ConfirmEMail.this.uiManager = new CihUiManager(this.val$stsMachine, this.val$myres);
            CihMBanking.sesPMTR = null;
            CihMBanking.sesPMTR = new SessionParameter();
            this.val$stsMachine.showForm("LoginV2New", (Command) null);
        }
    }

    class 14 implements ActionListener {
        final /* synthetic */ Resources val$myres;
        final /* synthetic */ StateMachine val$stsMachine;

        14(StateMachine stateMachine, Resources resources) {
            this.val$stsMachine = stateMachine;
            this.val$myres = resources;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ConfirmEMail.this.uiManager = new CihUiManager(this.val$stsMachine, this.val$myres);
            CihMBanking.sesPMTR = null;
            CihMBanking.sesPMTR = new SessionParameter();
            this.val$stsMachine.showForm("LoginV2New", (Command) null);
        }
    }

    class 15 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        15(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 16 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        16(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }
}
