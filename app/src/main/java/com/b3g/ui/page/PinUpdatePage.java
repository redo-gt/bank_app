package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizard;
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
public class PinUpdatePage extends B3GPage {
    static int counter;
    Button BtnValider;
    Container CodevaliStep2;
    private String ConfirmPin;
    TextField LoginField;
    private String NewPin;
    B3GWizard ValideSms;
    private Dialog dlg;
    Step s1;
    Step s2;
    Step s3;
    private final UIBuilder uib = new UIBuilder();
    private final String selectedStr = "Séléctionner";

    static /* synthetic */ String access$000(PinUpdatePage pinUpdatePage) {
        return pinUpdatePage.NewPin;
    }

    static /* synthetic */ String access$002(PinUpdatePage pinUpdatePage, String str) {
        pinUpdatePage.NewPin = str;
        return str;
    }

    static /* synthetic */ String access$100(PinUpdatePage pinUpdatePage) {
        return pinUpdatePage.ConfirmPin;
    }

    static /* synthetic */ String access$102(PinUpdatePage pinUpdatePage, String str) {
        pinUpdatePage.ConfirmPin = str;
        return str;
    }

    static /* synthetic */ ServiceResponse access$200(PinUpdatePage pinUpdatePage, String str, String str2) {
        return pinUpdatePage.updatePinfProcess(str, str2);
    }

    public PinUpdatePage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
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
            this.s1.stepCnt = Codevali1();
            Step step2 = new Step();
            this.s2 = step2;
            step2.icon = "2inactif.png";
            this.s2.selectedIcon = "2actif.png";
            this.s2.stepCnt = Codevalid2();
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.s1);
            arrayList.add(this.s2);
            B3GWizard b3GWizard = new B3GWizard(arrayList);
            this.ValideSms = b3GWizard;
            this.thisContainer = b3GWizard.drawWizard("", 16777215, 0);
        }
        this.thisContainer.getAllStyles().setBgColor(16777215);
        this.thisContainer.getAllStyles().setBgTransparency(255);
        this.thisContainer.getAllStyles().setPadding(0, 2, 0, 0);
        this.thisContainer.getAllStyles().setPaddingUnit(2, 2, 2, 2);
        this.thisContainer.setScrollableY(false);
        return this.thisContainer;
    }

    private Container Codevali1() {
        UIBuilder uIBuilder = new UIBuilder();
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        B3GCIHEcode b3GCIHEcode2 = new B3GCIHEcode();
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setScrollableY(true);
        this.thisContainer.setScrollVisible(false);
        Container createContainer = uIBuilder.createContainer("/cihv3", "CodevalidV3");
        this.BtnValider = (Button) uIBuilder.findByName("BtnValider", createContainer);
        Container container = (Container) uIBuilder.findByName("cntEcode1", createContainer);
        Container container2 = (Container) uIBuilder.findByName("cntEcode2", createContainer);
        Container container3 = (Container) uIBuilder.findByName("Container", createContainer);
        container3.setScrollableY(true);
        container3.setScrollVisible(false);
        b3GCIHEcode2.setFlagOpen(false);
        container.add("Center", b3GCIHEcode.getComponent());
        container2.add("Center", b3GCIHEcode2.getComponent());
        this.BtnValider.addActionListener(new 1(b3GCIHEcode, b3GCIHEcode2));
        this.thisContainer.add(createContainer);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;
        final /* synthetic */ B3GCIHEcode val$eCode2;

        1(B3GCIHEcode b3GCIHEcode, B3GCIHEcode b3GCIHEcode2) {
            this.val$eCode1 = b3GCIHEcode;
            this.val$eCode2 = b3GCIHEcode2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PinUpdatePage.access$002(PinUpdatePage.this, this.val$eCode1.getValue());
            PinUpdatePage.access$102(PinUpdatePage.this, this.val$eCode2.getValue());
            if (PinUpdatePage.access$000(PinUpdatePage.this).length() != 4 || PinUpdatePage.access$100(PinUpdatePage.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinUpdatePage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            if (!PinUpdatePage.access$000(PinUpdatePage.this).equals(PinUpdatePage.access$100(PinUpdatePage.this))) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinUpdatePage.this.uiManager.stateMachine, "Les nouveaux CIH E-Code ne sont pas identiques", null);
                return;
            }
            ServiceResponse sendOtpForPinProcess = ServiceManager.sendOtpForPinProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1023", 57, "1", "1", "", "", "", "", PinUpdatePage.access$100(PinUpdatePage.this));
            if (!sendOtpForPinProcess.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinUpdatePage.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpForPinProcess.getStatusLabel()), null);
            } else {
                PinUpdatePage.this.ValideSms.goToNextStep(PinUpdatePage.this.s1, "", 16777215, 0);
            }
            SessionParameter.setOtpTextField(PinUpdatePage.this.LoginField);
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
        this.LoginField.setCursorBlinkTimeOff(999999);
        SessionParameter.setOtpTextField(this.LoginField);
        Button button = (Button) uIBuilder.findByName("validBtn", createContainer);
        ((Button) uIBuilder.findByName("ivrBtn", createContainer)).addActionListener(new 2());
        button.setPreferredH(Display.getInstance().getDisplayWidth() / 7);
        button.addActionListener(new 3());
        ((Label) uIBuilder.findByName("gsmMsgSP", createContainer)).setText(DataTools.getEncryptedPhone(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm));
        createContainer.revalidate();
        return createContainer;
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ServiceResponse sendOtpForPinProcess = ServiceManager.sendOtpForPinProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1023", 57, "1", "1", "", "", "", "", PinUpdatePage.access$100(PinUpdatePage.this));
            if (!sendOtpForPinProcess.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinUpdatePage.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpForPinProcess.getStatusLabel()), null);
            }
            SessionParameter.setOtpTextField(PinUpdatePage.this.LoginField);
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (PinUpdatePage.this.LoginField.getText().length() != 0) {
                if (PinUpdatePage.counter < 3) {
                    PinUpdatePage pinUpdatePage = PinUpdatePage.this;
                    ServiceResponse access$200 = PinUpdatePage.access$200(pinUpdatePage, PinUpdatePage.access$000(pinUpdatePage), PinUpdatePage.this.LoginField.getText());
                    if (access$200.getStatusCode().equals("000")) {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinUpdatePage.this.uiManager.stateMachine, "Votre CIH E-Code a été bien enregistré", null);
                        PinUpdatePage.this.uiManager.NavigateToPageById(104);
                        return;
                    } else if (access$200.getStatusCode().equals("301")) {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinUpdatePage.this.uiManager.stateMachine, "CIH E-Code saisi est incorrect", null);
                        PinUpdatePage.this.uiManager.NavigateToPageById(104);
                        return;
                    } else if (access$200.getStatusCode().equals("001")) {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinUpdatePage.this.uiManager.stateMachine, "CIH E-Code saisi est incorrect", null);
                        PinUpdatePage.counter++;
                        return;
                    } else {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinUpdatePage.this.uiManager.stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
                        PinUpdatePage.this.uiManager.NavigateToPageById(104);
                        return;
                    }
                }
                PinUpdatePage.counter = 0;
                PinUpdatePage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PinUpdatePage.this.uiManager.stateMachine, "Veuillez saisir le code reçu par SMS", null);
        }
    }

    private ServiceResponse updatePinfProcess(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("OldPin", " ");
        hashtable.put("NewPin", str);
        hashtable.put("PASSID", str2);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(141);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }
}
