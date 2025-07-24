package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizardWithoutHeader;
import com.b3g.ui.Step;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class PinAddPage extends B3GPage {
    static int counter;
    Button BtnValider;
    Button Btnplustard;
    private String ConfirnNewPin;
    TextField LoginField;
    private String NewPin;
    private String Pin;
    B3GWizardWithoutHeader ValideSms;
    public Container cntMain;
    Dialog d;
    Button plusTard;
    Step s1;
    Step s2;
    Step s3;
    private UIBuilder uib;

    static /* synthetic */ String access$000(PinAddPage pinAddPage) {
        return pinAddPage.Pin;
    }

    static /* synthetic */ String access$002(PinAddPage pinAddPage, String str) {
        pinAddPage.Pin = str;
        return str;
    }

    static /* synthetic */ String access$100(PinAddPage pinAddPage) {
        return pinAddPage.ConfirnNewPin;
    }

    static /* synthetic */ String access$102(PinAddPage pinAddPage, String str) {
        pinAddPage.ConfirnNewPin = str;
        return str;
    }

    static /* synthetic */ ServiceResponse access$200(PinAddPage pinAddPage, String str, String str2) {
        return pinAddPage.addPinfProcess(str, str2);
    }

    public PinAddPage(Object obj, Object obj2, int i) {
        this.uib = new UIBuilder();
        this.cntMain = new Container(new GridLayout(1, 1));
        this.uiManager = (B3GUiManager) obj;
        this.d = (Dialog) obj2;
        this.PageId = i;
    }

    public PinAddPage(Object obj, Object obj2, int i, boolean z) {
        this.uib = new UIBuilder();
        this.cntMain = new Container(new GridLayout(1, 1));
        this.uiManager = (B3GUiManager) obj;
        this.d = (Dialog) obj2;
        this.PageId = i;
        Container GetPageContainer = GetPageContainer();
        this.cntMain = GetPageContainer;
        GetPageContainer.setScrollVisible(false);
        setBtnPlutardVis(z);
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        Container container = new Container(new BoxLayout(2));
        container.setUIID("");
        container.setScrollVisible(false);
        container.setScrollableY(false);
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setScrollableY(true);
        this.thisContainer.setScrollVisible(false);
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            this.thisContainer = new Container(BoxLayout.y());
            Label label = new Label("");
            label.setUIID("gb_lblGlobalHeaderTitleV2");
            label.setTextPosition(1);
            label.setVerticalAlignment(4);
            label.setScrollVisible(false);
            label.setEnabled(false);
            this.thisContainer.setScrollableY(false);
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
            this.s1.stepCnt = showPinStep1PopUpp();
            Step step2 = new Step();
            this.s2 = step2;
            step2.icon = "2inactif.png";
            this.s2.selectedIcon = "2actif.png";
            this.s2.stepCnt = Codevalid2();
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.s1);
            arrayList.add(this.s2);
            B3GWizardWithoutHeader b3GWizardWithoutHeader = new B3GWizardWithoutHeader(arrayList);
            this.ValideSms = b3GWizardWithoutHeader;
            this.thisContainer = b3GWizardWithoutHeader.drawWizard("", 16777215, 0);
        }
        this.thisContainer.getAllStyles().setBgColor(16777215);
        this.thisContainer.getAllStyles().setBgTransparency(255);
        this.thisContainer.getAllStyles().setPadding(0, 2, 0, 0);
        this.thisContainer.getAllStyles().setPaddingUnit(2, 2, 2, 2);
        this.thisContainer.setScrollableY(false);
        return this.thisContainer;
    }

    public void setBtnPlutardVis(boolean z) {
        this.Btnplustard.setHidden(z);
    }

    public Container showPinStep1PopUpp() {
        UIBuilder uIBuilder = new UIBuilder();
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        B3GCIHEcode b3GCIHEcode2 = new B3GCIHEcode();
        Container createContainer = uIBuilder.createContainer("/cihv3", "Codevali");
        Container container = (Container) uIBuilder.findByName("Container4", createContainer);
        Container container2 = (Container) uIBuilder.findByName("cntEcode1", createContainer);
        Container container3 = (Container) uIBuilder.findByName("cntEcode2", createContainer);
        b3GCIHEcode.setFlagOpen(false);
        b3GCIHEcode2.setFlagOpen(false);
        container2.add("Center", b3GCIHEcode.getComponent());
        container3.add("Center", b3GCIHEcode2.getComponent());
        container.setScrollableY(true);
        container.setScrollVisible(false);
        this.BtnValider = (Button) uIBuilder.findByName("BtnValider", createContainer);
        Button button = (Button) uIBuilder.findByName("Btnplustard", createContainer);
        this.Btnplustard = button;
        button.setHidden(true);
        this.Btnplustard.addActionListener(new 1(b3GCIHEcode, b3GCIHEcode2));
        this.BtnValider.addActionListener(new 2(b3GCIHEcode, b3GCIHEcode2));
        return createContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;
        final /* synthetic */ B3GCIHEcode val$eCode2;

        1(B3GCIHEcode b3GCIHEcode, B3GCIHEcode b3GCIHEcode2) {
            this.val$eCode1 = b3GCIHEcode;
            this.val$eCode2 = b3GCIHEcode2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$eCode1.stopEditing();
            this.val$eCode2.stopEditing();
            PinAddPage.this.d.dispose();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;
        final /* synthetic */ B3GCIHEcode val$eCode2;

        2(B3GCIHEcode b3GCIHEcode, B3GCIHEcode b3GCIHEcode2) {
            this.val$eCode1 = b3GCIHEcode;
            this.val$eCode2 = b3GCIHEcode2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$eCode1.stopEditing();
            this.val$eCode2.stopEditing();
            PinAddPage.access$002(PinAddPage.this, this.val$eCode1.getValue());
            PinAddPage.access$102(PinAddPage.this, this.val$eCode2.getValue());
            if (PinAddPage.access$000(PinAddPage.this).length() != 4 || PinAddPage.access$100(PinAddPage.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinAddPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            if (!PinAddPage.access$000(PinAddPage.this).equals(PinAddPage.access$100(PinAddPage.this))) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinAddPage.this.uiManager.stateMachine, "Les codes de validation ne sont pas identiques", null);
                return;
            }
            ServiceResponse sendOtpForPinProcess = ServiceManager.sendOtpForPinProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1023", 57, "1", "1", "", "", "", "", PinAddPage.access$100(PinAddPage.this));
            if (!sendOtpForPinProcess.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinAddPage.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpForPinProcess.getStatusLabel()), null);
            } else {
                PinAddPage.this.ValideSms.goToNextStep(PinAddPage.this.s1, "", 16777215, 0);
            }
            SessionParameter.setOtpTextField(PinAddPage.this.LoginField);
        }
    }

    private Container Codevalid2() {
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "ChangePasswordStep2");
        this.LoginField = (TextField) uIBuilder.findByName("LoginTxt", createContainer);
        Container container = (Container) uIBuilder.findByName("loginTxtField", createContainer);
        this.LoginField.setCursorBlinkTimeOff(999999);
        this.LoginField.setFocusable(true);
        container.setFocusable(false);
        container.getParent().setFocusable(false);
        Button button = this.plusTard;
        if (button != null) {
            button.setHidden(true);
        }
        this.LoginField.setCursorBlinkTimeOff(999999);
        SessionParameter.setOtpTextField(this.LoginField);
        Button button2 = (Button) uIBuilder.findByName("validBtn", createContainer);
        ((Button) uIBuilder.findByName("ivrBtn", createContainer)).addActionListener(new 3());
        button2.setPreferredH(Display.getInstance().getDisplayWidth() / 7);
        button2.addActionListener(new 4());
        ((Label) uIBuilder.findByName("gsmMsgSP", createContainer)).setText(DataTools.getEncryptedPhone(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm));
        createContainer.revalidate();
        createContainer.setScrollVisible(false);
        return createContainer;
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ServiceResponse sendOtpForPinProcess = ServiceManager.sendOtpForPinProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1023", 57, "1", "1", "", "", "", "", PinAddPage.access$100(PinAddPage.this));
            if (!sendOtpForPinProcess.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinAddPage.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpForPinProcess.getStatusLabel()), null);
            }
            SessionParameter.setOtpTextField(PinAddPage.this.LoginField);
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (PinAddPage.this.LoginField.getText().length() != 0) {
                if (PinAddPage.counter < 3) {
                    PinAddPage pinAddPage = PinAddPage.this;
                    ServiceResponse access$200 = PinAddPage.access$200(pinAddPage, PinAddPage.access$000(pinAddPage), PinAddPage.this.LoginField.getText());
                    if (access$200.getStatusCode().equals("000")) {
                        if (PinAddPage.this.d != null) {
                            PinAddPage.this.d.dispose();
                        } else {
                            PinAddPage.this.uiManager.NavigateToPageById(104);
                        }
                        CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated = true;
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinAddPage.this.uiManager.stateMachine, "Votre CIH E-Code a été bien enregistré", null);
                        return;
                    }
                    if (access$200.getStatusCode().equals("002")) {
                        if (PinAddPage.this.d != null) {
                            PinAddPage.this.d.dispose();
                        } else {
                            PinAddPage.this.uiManager.NavigateToPageById(104);
                        }
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinAddPage.this.uiManager.stateMachine, "Votre CIH E-Code est déjà enregistré", null);
                        return;
                    }
                    if (access$200.getStatusCode().equals("001")) {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinAddPage.this.uiManager.stateMachine, "Le code confidentiel est incorrect", null);
                        PinAddPage.counter++;
                        return;
                    } else {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinAddPage.this.uiManager.stateMachine, DataTools.FormatUnicode(access$200.getStatusLabel()), null);
                        return;
                    }
                }
                PinAddPage.counter = 0;
                PinAddPage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinAddPage.this.uiManager.stateMachine, "Veuillez saisir le code reçu par SMS", null);
        }
    }

    private ServiceResponse addPinfProcess(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("Pin", str);
        hashtable.put("PASSID", str2);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(140);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }
}
