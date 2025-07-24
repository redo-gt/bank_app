package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.tools.StringTools;
import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizard;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.Step;
import com.b3g.ui.page.cih.Module.AccountLastConnection;
import com.b3g.ui.page.cih.Module.ComboboxItem;
import com.b3g.ui.page.cih.Module.PopUpsManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.fingerprint.Fingerprint;
import com.codename1.io.Storage;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import com.codename1.util.FailureCallback;
import com.codename1.util.SuccessCallback;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ProfilePage extends B3GPage {
    String VnewaliasText;
    Button b;
    Label emailLbl;
    Label langueLbl;
    ComboBox lgCombo;
    Vector lst;
    ComboBox opCombo;
    Label operatorlbl;
    PopUpsManager pPopUpsManager;
    TouchIDNativeCall pTouchIDNativeCall;
    B3GWizard profilWizard;
    Step s1;
    Step s2;
    Step s3;
    Step s4;
    Step s5;
    Container step1Cnt;
    Step stepProfil;
    String newEmail = "";
    UIBuilder uib = new UIBuilder();
    ArrayList listSteps = new ArrayList();
    boolean isPicFounded = false;
    boolean isPicStorage = false;

    static /* synthetic */ void access$000(ProfilePage profilePage, Button button) {
        profilePage.applySuccessFingerPrintAction(button);
    }

    public ProfilePage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.setScrollableY(true);
        try {
            this.listSteps = new ArrayList();
            Step step = new Step();
            this.stepProfil = step;
            step.icon = "1inactif.png";
            this.stepProfil.selectedIcon = "1actif.png";
            this.stepProfil.stepCnt = drawProfilCnt();
            this.listSteps.add(this.stepProfil);
            if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email.isEmpty()) {
                Step step2 = new Step();
                this.s1 = step2;
                step2.icon = "1inactif.png";
                this.s1.selectedIcon = "1actif.png";
                this.s1.stepCnt = drawStep1Cnt();
                this.listSteps.add(this.s1);
            }
            Step step3 = new Step();
            this.s2 = step3;
            step3.icon = "1inactif.png";
            this.s2.selectedIcon = "1actif.png";
            this.s2.stepCnt = drawStep2Cnt();
            Step step4 = new Step();
            this.s3 = step4;
            step4.icon = "1inactif.png";
            this.s3.selectedIcon = "1actif.png";
            this.s3.stepCnt = drawStep3Cnt();
            Step step5 = new Step();
            this.s4 = step5;
            step5.icon = "1inactif.png";
            this.s4.selectedIcon = "1actif.png";
            this.s4.stepCnt = drawStep4Cnt();
            Step step6 = new Step();
            this.s5 = step6;
            step6.icon = "1inactif.png";
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
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
    }

    private Container drawProfilCnt() {
        boolean z;
        Container container = new Container(new BorderLayout());
        Container createContainer = this.uib.createContainer("/cihv3", "profile");
        this.step1Cnt = createContainer;
        ((Label) this.uib.findByName("nomSpanLabel", createContainer)).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom);
        ((SpanLabel) this.uib.findByName("adresseSpanLabel", this.step1Cnt)).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().adress);
        ((SpanLabel) this.uib.findByName("postalSpanLabel", this.step1Cnt)).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().postCode);
        ((SpanLabel) this.uib.findByName("phoneSpanLabel", this.step1Cnt)).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm);
        SpanLabel spanLabel = (SpanLabel) this.uib.findByName("emailSpanLabel", this.step1Cnt);
        spanLabel.setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email);
        Container container2 = (Container) this.uib.findByName("touchBlocCnt", this.step1Cnt);
        container2.setHidden(true);
        ((Container) this.uib.findByName("Container4111", this.step1Cnt)).setHidden(true);
        Button button = (Button) this.uib.findByName("editBtn", this.step1Cnt);
        button.setHidden(true);
        ((Button) this.uib.findByName("Button", this.step1Cnt)).setHidden(true);
        ((Button) this.uib.findByName("editEmailBtn", this.step1Cnt)).addActionListener(new 1());
        Label label = (Label) this.uib.findByName("langue_lbl", this.step1Cnt);
        Label label2 = (Label) this.uib.findByName("informations_personnelles_lbl", this.step1Cnt);
        Label label3 = (Label) this.uib.findByName("contact_lbl", this.step1Cnt);
        label.setText(StringTools.capitalize(label.getText()));
        label2.setText(StringTools.capitalize(label2.getText()));
        label3.setText(StringTools.capitalize(label3.getText()));
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        label2.setUIID("gb_lblGlobalHeaderTitleV2");
        label3.setUIID("gb_lblGlobalHeaderTitleV2");
        ((Container) this.uib.findByName("Container211", this.step1Cnt)).setHidden(true);
        ((Button) this.uib.findByName("Button", this.step1Cnt)).setName("editPhotoProfil");
        Label label4 = (Label) this.uib.findByName("lblPhotoProfil", this.step1Cnt);
        label4.getAllStyles().setBgTransparency(0);
        label4.getAllStyles().setFgColor(16777215);
        label4.getAllStyles().setBgColor(16777215);
        this.operatorlbl = (Label) this.uib.findByName("operatorLbl", this.step1Cnt);
        this.langueLbl = (Label) this.uib.findByName("langueLbl", this.step1Cnt);
        if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator.toUpperCase().equals("MEDITEL")) {
            this.operatorlbl.setText("ORANGE Maroc");
        } else if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator.toUpperCase().equals("AUTRES")) {
            this.operatorlbl.setText("Numéro étranger");
        } else {
            this.operatorlbl.setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator);
        }
        this.langueLbl.setText(getLblLang());
        Container container3 = (Container) this.uib.findByName("operateurCnt", this.step1Cnt);
        Container container4 = (Container) this.uib.findByName("langueCnt", this.step1Cnt);
        Container container5 = (Container) this.uib.findByName("activeTouchIdCnt", this.step1Cnt);
        Label label5 = (Label) this.uib.findByName("touChIDTitle", this.step1Cnt);
        SpanLabel spanLabel2 = (SpanLabel) this.uib.findByName("touchIdMsg1", this.step1Cnt);
        SpanLabel spanLabel3 = (SpanLabel) this.uib.findByName("touchIdMsg2", this.step1Cnt);
        SpanLabel spanLabel4 = (SpanLabel) this.uib.findByName("touchIdMsg3", this.step1Cnt);
        this.pTouchIDNativeCall = (TouchIDNativeCall) NativeLookup.create(TouchIDNativeCall.class);
        if (!Display.getInstance().isSimulator()) {
            if (this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                container5.setHidden(true);
                label5.setText("PARAMETRER FINGERPRINT");
                spanLabel2.setText("Pour bénéficier du FingerPrint, vous devez l'activer sur votre appareil. Vous pourrai en suite le paramétrer sur votre application CIH Mobile.");
                spanLabel3.setText("Connectez vous à votre espace client en utilisant votre empreinte digitale.");
                spanLabel4.setText("Attention !! N’activez la sécurité par FingerPrint que sur votre téléphone personnel.");
            } else if (this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
                label5.setText("PARAMETRER TOUCH ID");
                spanLabel2.setText("Pour bénéficier du Touch ID, vous devez l'activer sur votre iphone. Vous pourrai en suite le paramétrer sur votre application CIH Mobile.");
                spanLabel3.setText("Connectez vous à votre espace client en utilisant votre Touch ID.");
                spanLabel4.setText("Attention !! N’activez la sécurité par empreinte digitale que sur votre téléphone personnel.");
            } else if (this.pTouchIDNativeCall.getBiometryType().equals("FACEID")) {
                container5.setHidden(true);
                label5.setText("PARAMETRER FACE ID");
                spanLabel2.setText("Pour bénéficier du Face ID, vous devez l'activer sur votre iphone. Vous pourrai en suite le paramétrer sur votre application CIH Mobile.");
                spanLabel3.setText("Connectez vous à votre espace client en utilisant votre Face ID.");
                spanLabel4.setText("Attention !! N’activez la sécurité par Face ID que sur votre téléphone personnel.");
            }
        }
        if (!Fingerprint.isAvailable() || !this.pTouchIDNativeCall.isSupported() || CihMBanking.sesPMTR.getIsDemo() != 0) {
            container2.setHidden(true);
        }
        Container container6 = (Container) this.uib.findByName("touchIDNotActivCnt", this.step1Cnt);
        Container container7 = (Container) this.uib.findByName("touchIDActivCnt", this.step1Cnt);
        Button button2 = (Button) this.uib.findByName("activateTouchBtn", this.step1Cnt);
        if (!Display.getInstance().isSimulator()) {
            if (this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                container5.setHidden(true);
                button2.setText("Activer FingerPrint");
            } else if (this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
                button2.setText("Activer Touch ID");
            } else if (this.pTouchIDNativeCall.getBiometryType().equals("FACEID")) {
                button2.setText("Activer Face ID");
            }
        }
        if (Fingerprint.isAvailable()) {
            container7.setHidden(false);
            container6.setHidden(true);
            this.step1Cnt.revalidate();
        } else {
            container7.setHidden(true);
            container6.setHidden(false);
            this.step1Cnt.revalidate();
        }
        this.lst = new Vector();
        if (Storage.getInstance().exists("veclistCnxStorage")) {
            this.lst = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
        }
        int i = 0;
        while (true) {
            if (i >= this.lst.size()) {
                z = false;
                break;
            }
            if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical.equals(((AccountLastConnection) this.lst.elementAt(i)).radical)) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            button2.setIcon(this.uiManager.ressource.getImage("radio_on.png"));
            if (!Display.getInstance().isSimulator()) {
                if (this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                    container5.setHidden(true);
                    button2.setText("Désactiver FingerPrint");
                } else if (this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
                    button2.setText("Désactiver Touch ID");
                } else if (this.pTouchIDNativeCall.getBiometryType().equals("FACEID")) {
                    button2.setText("Désactiver Face ID");
                }
            }
        }
        button2.addActionListener(new 2(button2));
        Container container8 = (Container) this.uib.findByName("modifCnt", this.step1Cnt);
        container8.setHidden(true);
        button.addActionListener(new 3(container3, container8));
        ((Button) this.uib.findByName("BtnNo", this.step1Cnt)).addActionListener(new 4(container3, container8));
        this.pPopUpsManager = new PopUpsManager(this.uiManager);
        this.langueLbl.getParent().removeComponent(this.langueLbl);
        Button button3 = new Button(getLblLang());
        this.b = button3;
        button3.setIcon(CihMBanking.theme.getImage("listbox.png"));
        this.b.setTextPosition(1);
        this.b.setUIID("ln_btnLoanDetail2");
        this.b.addActionListener(new 5());
        container4.addComponent(this.b);
        container4.setLeadComponent(this.b);
        this.step1Cnt.revalidate();
        ((Button) this.uib.findByName("BtnYest", this.step1Cnt)).addActionListener(new 6(spanLabel, container3, container8));
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.5f, 3.0f, 3.0f, 3.0f);
        container.getAllStyles().setBgColor(16382457);
        container.getAllStyles().setBgTransparency(255);
        container.add("Center", this.step1Cnt);
        return container;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ProfilePage.this.uiManager.btnBack.getListeners().clear();
            ProfilePage.this.uiManager.btnBack.addActionListener(new ProfilePage$1$$ExternalSyntheticLambda0(this));
            ProfilePage.this.profilWizard.goToNextStep(ProfilePage.this.stepProfil, "", 16777215, 0);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-ProfilePage$1(ActionEvent actionEvent) {
            ProfilePage.this.profilWizard.goToPreviousStep(ProfilePage.this.s1, "", 16777215, 0);
            ProfilePage.this.uiManager.btnBack.getListeners().clear();
            ProfilePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                ProfilePage.this.uiManager.GoBack();
            }
        }
    }

    class 2 implements ActionListener {
        private SuccessCallback onSuccess;
        final /* synthetic */ Button val$activateTouchBtn;

        2(Button button) {
            this.val$activateTouchBtn = button;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            boolean z;
            String msgActivationFaceID;
            ProfilePage.this.lst = new Vector();
            ProfilePage.this.lst = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
            int i = 0;
            while (true) {
                if (i >= ProfilePage.this.lst.size()) {
                    z = false;
                    break;
                }
                if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical.equals(((AccountLastConnection) ProfilePage.this.lst.elementAt(i)).radical)) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                if (Display.getInstance().isSimulator()) {
                    return;
                }
                if (ProfilePage.this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                    msgActivationFaceID = "Activez FingerPrint en appliquant votre empreinte digitale";
                } else if (ProfilePage.this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
                    msgActivationFaceID = DataTools.getMsgActivationTouchID();
                } else {
                    msgActivationFaceID = ProfilePage.this.pTouchIDNativeCall.getBiometryType().equals("FACEID") ? DataTools.getMsgActivationFaceID() : "";
                }
                Fingerprint.scanFingerprint(DataTools.getAvailabeBiometric(ProfilePage.this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID"), ProfilePage.this.pTouchIDNativeCall.getBiometryType().equals("FACEID"), ProfilePage.this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")), msgActivationFaceID, DataTools.getNamCancelBtn(), new ProfilePage$2$$ExternalSyntheticLambda0(this, this.val$activateTouchBtn), new 1());
                ProfilePage.this.step1Cnt.revalidate();
                return;
            }
            new UITimer(new ProfilePage$2$$ExternalSyntheticLambda1(this, this.val$activateTouchBtn)).schedule(300, false, ProfilePage.this.uiManager.currentForm);
            ProfilePage.this.step1Cnt.revalidate();
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-ProfilePage$2(Button button, Object obj) {
            ProfilePage.access$000(ProfilePage.this, button);
        }

        class 1 implements FailureCallback {
            public void onError(Object obj, Throwable th, int i, String str) {
            }

            1() {
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0049  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-ProfilePage$2(com.codename1.ui.Button r5) {
            /*
                r4 = this;
                com.codename1.ui.Display r0 = com.codename1.ui.Display.getInstance()
                boolean r0 = r0.isSimulator()
                if (r0 != 0) goto L49
                com.b3g.ui.page.ProfilePage r0 = com.b3g.ui.page.ProfilePage.this
                com.b3g.tools.TouchIDNativeCall r0 = r0.pTouchIDNativeCall
                java.lang.String r0 = r0.getBiometryType()
                java.lang.String r1 = "FINGERPRINT"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L1f
                java.lang.String r0 = "Introduisez votre mot de passe pour confirmer la désactivation de l’utilisation de votre FingerPrint"
                java.lang.String r1 = "Votre FingerPrint est désormée désactivée"
                goto L4c
            L1f:
                com.b3g.ui.page.ProfilePage r0 = com.b3g.ui.page.ProfilePage.this
                com.b3g.tools.TouchIDNativeCall r0 = r0.pTouchIDNativeCall
                java.lang.String r0 = r0.getBiometryType()
                java.lang.String r1 = "TOUCHID"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L34
                java.lang.String r0 = "Introduisez votre mot de passe pour confirmer la désactivation de l’utilisation de votre Touch ID"
                java.lang.String r1 = "Votre Touch ID est désormée désactivée"
                goto L4c
            L34:
                com.b3g.ui.page.ProfilePage r0 = com.b3g.ui.page.ProfilePage.this
                com.b3g.tools.TouchIDNativeCall r0 = r0.pTouchIDNativeCall
                java.lang.String r0 = r0.getBiometryType()
                java.lang.String r1 = "FACEID"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L49
                java.lang.String r0 = "Introduisez votre mot de passe pour confirmer la désactivation de l’utilisation de votre Face ID"
                java.lang.String r1 = "Votre Face ID est désormée désactivée"
                goto L4c
            L49:
                java.lang.String r0 = ""
                r1 = r0
            L4c:
                com.b3g.ui.page.cih.Module.PopUpsManager r2 = new com.b3g.ui.page.cih.Module.PopUpsManager
                com.b3g.ui.page.ProfilePage r3 = com.b3g.ui.page.ProfilePage.this
                com.b3g.ui.B3GUiManager r3 = r3.uiManager
                r2.<init>(r3)
                r3 = 0
                r2.showPasswordPopUp(r5, r0, r1, r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.ProfilePage.2.lambda$actionPerformed$1$com-b3g-ui-page-ProfilePage$2(com.codename1.ui.Button):void");
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Container val$modifCnt;
        final /* synthetic */ Container val$operateurCnt;

        3(Container container, Container container2) {
            this.val$operateurCnt = container;
            this.val$modifCnt = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ProfilePage.this.operatorlbl.getParent().removeComponent(ProfilePage.this.operatorlbl);
            ProfilePage.this.opCombo = new ComboBox();
            ProfilePage.this.opCombo.setUIID("ln_btnLoanDetail");
            DefaultListModel defaultListModel = new DefaultListModel();
            defaultListModel.addItem(new ComboboxItem("IAM"));
            defaultListModel.addItem(new ComboboxItem("ORANGE Maroc"));
            defaultListModel.addItem(new ComboboxItem("INWI"));
            defaultListModel.addItem(new ComboboxItem("Numéro étranger"));
            ProfilePage.this.opCombo.setModel(defaultListModel);
            ProfilePage.this.opCombo.setRenderer(new ComboboxItem());
            if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator.equals("IAM")) {
                ProfilePage.this.opCombo.setSelectedIndex(0);
            } else if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator.equals("MEDITEL")) {
                ProfilePage.this.opCombo.setSelectedIndex(1);
            } else if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator.equals("INWI")) {
                ProfilePage.this.opCombo.setSelectedIndex(2);
            } else {
                ProfilePage.this.opCombo.setSelectedIndex(3);
            }
            this.val$operateurCnt.addComponent(ProfilePage.this.opCombo);
            this.val$modifCnt.setHidden(false);
            ProfilePage.this.step1Cnt.revalidate();
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Container val$modifCnt;
        final /* synthetic */ Container val$operateurCnt;

        4(Container container, Container container2) {
            this.val$operateurCnt = container;
            this.val$modifCnt = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ProfilePage.this.operatorlbl = new Label();
            ProfilePage.this.operatorlbl.setUIID("ac_lblitemDetailValueFormR");
            ProfilePage.this.operatorlbl.setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator);
            this.val$operateurCnt.addComponent(ProfilePage.this.operatorlbl);
            ProfilePage.this.opCombo.getParent().removeComponent(ProfilePage.this.opCombo);
            this.val$modifCnt.setHidden(true);
            ProfilePage.this.step1Cnt.revalidate();
        }
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            new PopUpsManager(ProfilePage.this.uiManager).showPopUp(ProfilePage.this.uiManager.stateMachine, ProfilePage.this.pPopUpsManager.showLanguePopUpCont(ProfilePage.this.opCombo));
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ SpanLabel val$emailSpanLabel;
        final /* synthetic */ Container val$modifCnt;
        final /* synthetic */ Container val$operateurCnt;

        6(SpanLabel spanLabel, Container container, Container container2) {
            this.val$emailSpanLabel = spanLabel;
            this.val$operateurCnt = container;
            this.val$modifCnt = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator = ((ComboboxItem) ProfilePage.this.opCombo.getSelectedItem()).text;
            ServiceResponse updateProfileProcess = new PopUpsManager(ProfilePage.this.uiManager).updateProfileProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, Integer.toString(ProfilePage.this.opCombo.getSelectedIndex()), this.val$emailSpanLabel.getText(), "1");
            if (updateProfileProcess.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ProfilePage.this.uiManager.stateMachine, DataTools.FormatUnicode(updateProfileProcess.getStatusLabel()), null);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ProfilePage.this.uiManager.stateMachine, DataTools.FormatUnicode(updateProfileProcess.getStatusLabel()), null);
            }
            ProfilePage.this.operatorlbl = new Label();
            ProfilePage.this.operatorlbl.setUIID("ac_lblitemDetailValueFormR");
            ProfilePage.this.operatorlbl.setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator);
            this.val$operateurCnt.addComponent(ProfilePage.this.operatorlbl);
            ProfilePage.this.opCombo.getParent().removeComponent(ProfilePage.this.opCombo);
            ProfilePage.this.step1Cnt.revalidate();
            this.val$modifCnt.setHidden(true);
        }
    }

    private void applySuccessFingerPrintAction(Button button) {
        new UITimer(new ProfilePage$$ExternalSyntheticLambda0(this, button)).schedule(500, false, this.uiManager.currentForm);
    }

    /* synthetic */ void lambda$applySuccessFingerPrintAction$0$com-b3g-ui-page-ProfilePage(Button button) {
        String str;
        String str2;
        PopUpsManager popUpsManager = new PopUpsManager(this.uiManager);
        if (this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
            str = "Introduisez votre mot de passe pour confirmer l’activation de l’utilisation de votre FINGERPRINT";
            str2 = "Votre FingerPrint est désormée activée";
        } else if (this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
            str = "Introduisez votre mot de passe pour confirmer l’activation de l’utilisation de votre Touch ID";
            str2 = "Votre Touch ID est désormée activée";
        } else if (this.pTouchIDNativeCall.getBiometryType().equals("FACEID")) {
            str = "Introduisez votre mot de passe pour confirmer l’activation de l’utilisation de votre Face ID";
            str2 = "Votre Face ID est désormée activée";
        } else {
            str = "";
            str2 = "";
        }
        popUpsManager.showPasswordPopUp(button, str, str2, true);
    }

    void setProfilPict(B3GUiManager b3GUiManager, Image image, Label label, boolean z) {
        Image image2 = b3GUiManager.ressource.getImage("round-mask3.png");
        if (image == null) {
            Style componentStyle = UIManager.getInstance().getComponentStyle("Title");
            componentStyle.setBgColor(16777215);
            componentStyle.setFgColor(16777215);
            image = FontImage.createMaterial((char) 58288, componentStyle, 4.0f);
        } else if (z) {
            Object createMask = image2.createMask();
            image = image.fill(image2.getWidth(), image2.getHeight());
            image.applyMask(createMask);
            label.setMask(createMask);
        }
        label.setIcon(image);
    }

    public boolean isPremier(int i) {
        if (i < 0) {
            return false;
        }
        if (i == 2) {
            return false;
        }
        if (i != 0 && i != 1) {
            for (int i2 = 2; i2 <= i / 2; i2++) {
                if (i != i2 && i % i2 == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean operatorChanged() {
        String lowerCase = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator.toLowerCase();
        String lowerCase2 = ((ComboboxItem) this.opCombo.getSelectedItem()).text.toLowerCase();
        if (lowerCase2.equals("orange maroc") && lowerCase.equals("medilet")) {
            return false;
        }
        return ((lowerCase2.equals("Numéro étranger") && lowerCase.equals("autres")) || lowerCase2.equals(lowerCase)) ? false : true;
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
        button.addActionListener(new 7());
        buttonStyle2.getAllStyles().setMarginUnit(1);
        buttonStyle2.getAllStyles().setMargin(5, 2, 8, 8);
        Button button2 = new Button("\u200b", "Container");
        button2.setText("Non, je modifie\u200b\u200b");
        Container buttonStyle22 = CIHStyles.setButtonStyle2(button2, null, 16777215, CIHStyles.colorOrange);
        button2.addActionListener(new 8());
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
        container.add("North", container3);
        container.add("Center", container2);
        return container;
    }

    class 7 implements ActionListener {
        7() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ProfilePage.this.newEmail = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email;
            ProfilePage.this.emailLbl.setText(ProfilePage.this.newEmail);
            ServiceResponse simulateChangementEmail = ProfilePage.this.simulateChangementEmail();
            if (simulateChangementEmail.getStatusCode().equals("000")) {
                ProfilePage.this.uiManager.btnBack.getListeners().clear();
                ProfilePage.this.uiManager.btnBack.addActionListener(new ProfilePage$7$$ExternalSyntheticLambda0(this));
                ProfilePage.this.profilWizard.goToStepById(ProfilePage.this.s1, 3, "", 16777215, 0);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(simulateChangementEmail.getStatusLabel()), null);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-ProfilePage$7(ActionEvent actionEvent) {
            ProfilePage.this.profilWizard.goToStepById(ProfilePage.this.s3, 1, "", 16777215, 0);
            ProfilePage.this.uiManager.btnBack.getListeners().clear();
            ProfilePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                ProfilePage.this.profilWizard.goToPreviousStep(ProfilePage.this.s1, "", 16777215, 0);
                ProfilePage.this.uiManager.btnBack.getListeners().clear();
                ProfilePage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    ProfilePage.this.uiManager.GoBack();
                }
            }
        }
    }

    class 8 implements ActionListener {
        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ProfilePage.this.uiManager.btnBack.getListeners().clear();
            ProfilePage.this.uiManager.btnBack.addActionListener(new ProfilePage$8$$ExternalSyntheticLambda0(this));
            ProfilePage.this.profilWizard.goToNextStep(ProfilePage.this.s1, "", 16777215, 0);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-ProfilePage$8(ActionEvent actionEvent) {
            ProfilePage.this.profilWizard.goToPreviousStep(ProfilePage.this.s2, "", 16777215, 0);
            ProfilePage.this.uiManager.btnBack.getListeners().clear();
            ProfilePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                ProfilePage.this.profilWizard.goToPreviousStep(ProfilePage.this.s1, "", 16777215, 0);
                ProfilePage.this.uiManager.btnBack.getListeners().clear();
                ProfilePage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    ProfilePage.this.uiManager.GoBack();
                }
            }
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
        Container container6 = new Container(new BoxLayout(2));
        Button button = new Button("\u200b", "Container");
        button.setText("PLUS TARD");
        Container buttonStyle2 = CIHStyles.setButtonStyle2(button, null, 16777215, CIHStyles.colorOrange);
        button.addActionListener(new 9());
        buttonStyle2.getAllStyles().setMarginBottom(2);
        Button button2 = new Button("\u200b", "Container");
        button2.setText("VALIDER");
        Container buttonStyle22 = CIHStyles.setButtonStyle2(button2, null, 16777215, CIHStyles.colorOrange);
        button2.addActionListener(new 10(textField, textField2));
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
        container6.add(buttonStyle2).add(buttonStyle22);
        container2.getAllStyles().setBgColor(16777215);
        container2.getAllStyles().setBgTransparency(255);
        container2.add("Center", container4);
        container2.add("South", container6);
        container.add("North", container3);
        container.add("Center", container2);
        return container;
    }

    class 9 implements ActionListener {
        9() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    class 10 implements ActionListener {
        final /* synthetic */ TextField val$ConfirmEmailTxtf;
        final /* synthetic */ TextField val$emailTxtf;

        10(TextField textField, TextField textField2) {
            this.val$emailTxtf = textField;
            this.val$ConfirmEmailTxtf = textField2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$emailTxtf.getText().isEmpty() || this.val$ConfirmEmailTxtf.getText().isEmpty()) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ProfilePage.this.uiManager.stateMachine, "Veuillez saisir tous les champs obligatoires", null);
                return;
            }
            if (!DataTools.emailChecker(this.val$emailTxtf.getText())) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ProfilePage.this.uiManager.stateMachine, "Veuillez saisir une adresse mail valide", null);
                return;
            }
            if (!this.val$emailTxtf.getText().equals(this.val$ConfirmEmailTxtf.getText())) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ProfilePage.this.uiManager.stateMachine, "Les emails ne sont pas identiques", null);
                return;
            }
            if (this.val$emailTxtf.getText().toLowerCase().equals(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email.toLowerCase())) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ProfilePage.this.uiManager.stateMachine, "Cette adresse email est la même enregistrée actuellement", null);
                return;
            }
            ProfilePage.this.newEmail = this.val$emailTxtf.getText();
            ProfilePage.this.emailLbl.setText(this.val$emailTxtf.getText());
            ServiceResponse simulateChangementEmail = ProfilePage.this.simulateChangementEmail();
            if (simulateChangementEmail.getStatusCode().equals("000")) {
                ProfilePage.this.uiManager.btnBack.getListeners().clear();
                ProfilePage.this.uiManager.btnBack.addActionListener(new ProfilePage$10$$ExternalSyntheticLambda0(this));
                ProfilePage.this.profilWizard.goToNextStep(ProfilePage.this.s2, "", 16777215, 0);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(simulateChangementEmail.getStatusLabel()), null);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-ProfilePage$10(ActionEvent actionEvent) {
            ProfilePage.this.profilWizard.goToPreviousStep(ProfilePage.this.s3, "", 16777215, 0);
            ProfilePage.this.uiManager.btnBack.getListeners().clear();
            ProfilePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                ProfilePage.this.profilWizard.goToPreviousStep(ProfilePage.this.s2, "", 16777215, 0);
                ProfilePage.this.uiManager.btnBack.getListeners().clear();
                ProfilePage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    ProfilePage.this.uiManager.GoBack();
                }
            }
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
        button.addActionListener(new 11());
        Button button2 = new Button("\u200b", "Container");
        button2.setText("VALIDER");
        Container buttonStyle22 = CIHStyles.setButtonStyle2(button2, null, 16777215, CIHStyles.colorOrange);
        button2.addActionListener(new 12(textField));
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

    class 11 implements ActionListener {
        11() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    class 12 implements ActionListener {
        final /* synthetic */ TextField val$codeTxtf;

        12(TextField textField) {
            this.val$codeTxtf = textField;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$codeTxtf.getText().isEmpty()) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ProfilePage.this.uiManager.stateMachine, "Veuillez saisir le code.", null);
                return;
            }
            ServiceResponse confirmeChangementEmail_byCodeEmail = ProfilePage.this.confirmeChangementEmail_byCodeEmail(this.val$codeTxtf.getText());
            if (confirmeChangementEmail_byCodeEmail.getStatusCode().equals("000")) {
                ProfilePage.this.uiManager.btnBack.getListeners().clear();
                ProfilePage.this.uiManager.btnBack.addActionListener(new ProfilePage$12$$ExternalSyntheticLambda0(this));
                ProfilePage.this.profilWizard.goToNextStep(ProfilePage.this.s3, "", 16777215, 0);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(confirmeChangementEmail_byCodeEmail.getStatusLabel()), null);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-ProfilePage$12(ActionEvent actionEvent) {
            ProfilePage.this.profilWizard.goToPreviousStep(ProfilePage.this.s4, "", 16777215, 0);
            ProfilePage.this.uiManager.btnBack.getListeners().clear();
            ProfilePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                ProfilePage.this.profilWizard.goToPreviousStep(ProfilePage.this.s3, "", 16777215, 0);
                ProfilePage.this.uiManager.btnBack.getListeners().clear();
                ProfilePage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    ProfilePage.this.uiManager.GoBack();
                }
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
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 13());
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 14(b3GCIHEcode));
        container.add(createContainer);
        return container;
    }

    class 13 implements ActionListener {
        13() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ProfilePage.this.uiManager.GoBack();
        }
    }

    class 14 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        14(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ProfilePage.this.VnewaliasText = this.val$eCode1.getValue();
            if (ProfilePage.this.VnewaliasText.length() == 0) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ProfilePage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            ProfilePage profilePage = ProfilePage.this;
            ServiceResponse confirmeChangementEmail_byOtp = profilePage.confirmeChangementEmail_byOtp(profilePage.VnewaliasText);
            if (confirmeChangementEmail_byOtp.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email = ProfilePage.this.newEmail;
                CihMBanking.sesPMTR.getSessionClient().getClient_Profil().HasToUpdateEmail = false;
                ProfilePage.this.uiManager.btnBack.getListeners().clear();
                ProfilePage.this.uiManager.btnBack.addActionListener(new ProfilePage$14$$ExternalSyntheticLambda0(this));
                ProfilePage.this.profilWizard.goToNextStep(ProfilePage.this.s4, "", 16777215, 0);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(confirmeChangementEmail_byOtp.getStatusLabel()), null);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-ProfilePage$14(ActionEvent actionEvent) {
            ProfilePage.this.profilWizard.goToPreviousStep(ProfilePage.this.s5, "", 16777215, 0);
            ProfilePage.this.uiManager.btnBack.getListeners().clear();
            ProfilePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                ProfilePage.this.profilWizard.goToPreviousStep(ProfilePage.this.s4, "", 16777215, 0);
                ProfilePage.this.uiManager.btnBack.getListeners().clear();
                ProfilePage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    ProfilePage.this.uiManager.GoBack();
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
        button.addActionListener(new 15());
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

    class 15 implements ActionListener {
        15() {
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new ProfilePage$15$1$$ExternalSyntheticLambda0()).schedule(300, false, CihMBanking.sesPMTR.getCurrentUiManager().currentForm);
            }

            static /* synthetic */ void lambda$run$0() {
                CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
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
}
