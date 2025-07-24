package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.ServiceManager;
import com.b3g.services.Webview;
import com.b3g.tools.DataTools;
import com.b3g.tools.NativeCall;
import com.b3g.ui.B3GUiManager;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Preferences;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.Base64;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MdmPage extends B3GPage {
    public String url;
    UIBuilder uib = new UIBuilder();
    boolean acceptTermsState = false;

    public MdmPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        try {
            ServiceResponse CheckMDM = CheckMDM();
            if (CheckMDM.getStatusCode().equals("000") && CheckMDM.getStatusLabel().equals("false")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Opération non autorisée, Car vous n'êtes pas marocain(e) résidant à l’étranger", null);
                this.thisContainer = new Container();
                this.thisContainer.setUIID("mn_cntMenuItemV2");
                this.thisContainer.setLayout(new BorderLayout());
                this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
            } else if (CheckMDM.getStatusCode().equals("NotFound")) {
                this.thisContainer = new Container();
                this.thisContainer.setUIID("mn_cntMenuItemV2");
                this.thisContainer.setLayout(new BorderLayout());
                this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
            } else {
                Webview webview = new Webview();
                ServiceResponse WebViewProcess = webview.WebViewProcess();
                if (WebViewProcess.getStatusCode().equals("000")) {
                    webview.FillWebviewArrayDataFromServiceResponse(WebViewProcess);
                    this.thisContainer = new Container();
                    this.thisContainer.setUIID("mn_cntMenuItemV2");
                    this.thisContainer.setLayout(new BoxLayout(2));
                    Container createContainer = this.uib.createContainer("/cihv3", "AgregationCnt1");
                    Container container = (Container) this.uib.findByName("Container7", createContainer);
                    Container container2 = (Container) this.uib.findByName("CheckBoxCnt", createContainer);
                    Button button = (Button) this.uib.findByName("ActivateBtn", createContainer);
                    Button button2 = (Button) this.uib.findByName("acceptCheckbox", createContainer);
                    Button button3 = (Button) this.uib.findByName("conditionsBtn", createContainer);
                    container2.setLeadComponent(button2);
                    button2.addActionListener(new MdmPage$$ExternalSyntheticLambda0(this, button2, button, container));
                    button3.addActionListener(new 1());
                    button.addActionListener(new 2());
                    this.thisContainer.add(createContainer);
                } else if (WebViewProcess.getStatusCode().equals("NotFound")) {
                    this.thisContainer = new Container();
                    this.thisContainer.setUIID("mn_cntMenuItemV2");
                    this.thisContainer.setLayout(new BorderLayout());
                    this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
                } else if (WebViewProcess.getStatusCode().equals("001")) {
                    CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPage(new AcountEtrangerPage(CihMBanking.sesPMTR.getCurrentUiManager(), WebViewProcess, 156));
                } else {
                    this.thisContainer = new Container();
                    this.thisContainer.setUIID("mn_cntMenuItemV2");
                    this.thisContainer.setLayout(new BorderLayout());
                    this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
                }
            }
        } catch (Exception unused) {
            this.thisContainer = new Container();
            this.thisContainer.setUIID("mn_cntMenuItemV2");
            this.thisContainer.setLayout(new BorderLayout());
            this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    /* synthetic */ void lambda$GetPageContainer$0$com-b3g-ui-page-MdmPage(Button button, Button button2, Container container, ActionEvent actionEvent) {
        if (this.acceptTermsState) {
            button.setIcon(this.uiManager.ressource.getImage("icone_selected_ligne.png"));
            button2.setUIID("op_BtnOppositionValidation_small");
            button2.getParent().revalidate();
            container.revalidate();
        } else {
            button.setIcon(this.uiManager.ressource.getImage("icone_selected_ligne_w.png"));
            button2.getParent().revalidate();
            button2.setUIID("op_BtnOppositionValidationOrgBg_small");
            container.revalidate();
        }
        this.acceptTermsState = !this.acceptTermsState;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
            ServiceResponse ConditionGPrecess = MdmPage.ConditionGPrecess();
            if (ConditionGPrecess.getStatusCode().equals("000")) {
                String str = fileSystemStorage.getAppHomePath() + "CGA_CIH.pdf";
                byte[] decode = Base64.decode(ConditionGPrecess.getStatusLabel().getBytes(), ConditionGPrecess.getStatusLabel().getBytes().length);
                try {
                    OutputStream openOutputStream = fileSystemStorage.openOutputStream(str);
                    try {
                        openOutputStream.write(decode);
                        if (openOutputStream != null) {
                            openOutputStream.close();
                        }
                    } finally {
                    }
                } catch (IOException unused) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(ConditionGPrecess.getStatusLabel()), null);
                }
                Display.getInstance().execute(str);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(ConditionGPrecess.getStatusLabel()), null);
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (MdmPage.this.acceptTermsState) {
                NativeCall nativeCall = (NativeCall) NativeLookup.create(NativeCall.class);
                if (nativeCall.isSupported()) {
                    nativeCall.callWebView(CihMBanking.sesPMTR.aggregationWebViewUrl, "CREATE");
                }
            }
        }
    }

    public static ServiceResponse ConditionGPrecess() {
        ServiceRequest serviceRequest = new ServiceRequest();
        new ArrayList();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("index", DataTools.getLangueIndex(Preferences.get("CihMobileLang", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().langue)));
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(190);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public static ServiceResponse CheckMDM() {
        ServiceRequest serviceRequest = new ServiceRequest();
        new ArrayList();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(191);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public void ShowDialogLoanResumeService(StateMachine stateMachine, B3gService b3gService) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUp");
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            Container container = new Container(new BoxLayout(2));
            SpanLabel spanLabel = new SpanLabel("Votre opération a bien été effectuée");
            spanLabel.setFocusable(false);
            spanLabel.setScrollVisible(false);
            spanLabel.setUIID("dg_splblpopup");
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblPopUpDemoMTC_BOLD");
            spanLabel.setIconUIID("g_cntEmpty");
            SpanLabel spanLabel2 = new SpanLabel("Vous pouvez téléchargez votre reçu au niveau");
            spanLabel2.setFocusable(false);
            spanLabel2.setScrollVisible(false);
            spanLabel2.setUIID("dg_splblpopup");
            spanLabel2.setIconPosition("West");
            spanLabel2.setTextUIID("dg_lblPopUpDemoMTC1");
            spanLabel2.setIconUIID("g_cntEmpty");
            SpanLabel spanLabel3 = new SpanLabel("de l’historique des paiements");
            spanLabel3.setFocusable(false);
            spanLabel3.setScrollVisible(false);
            spanLabel3.setUIID("dg_splblpopup");
            spanLabel3.setIconPosition("West");
            spanLabel3.setTextUIID("dg_lblPopUpDemoMTC1");
            spanLabel3.setIconUIID("g_cntEmpty");
            container.addComponent(spanLabel);
            container.addComponent(spanLabel2);
            container.addComponent(spanLabel3);
            stateMachine.findCntPopupBody(createContainer).addComponent(container);
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(true);
            dialog.setScrollableY(true);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(230);
            dialog.getDialogStyle().setBgColor(1118481);
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
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 3(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        3(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            MdmPage.this.uiManager.NavigateToPageById(1);
        }
    }

    public Dialog showConditionsPopUp() {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        dialog.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.setPreferredH(Display.getInstance().getDisplayHeight());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "MdmCondition");
        Button button = (Button) uIBuilder.findByName("Okbtn", createContainer);
        ((Container) uIBuilder.findByName("Container3", createContainer)).getAllStyles().setBgColor(16777215);
        button.addActionListener(new 4(dialog));
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", true);
        return dialog;
    }

    class 4 implements ActionListener {
        final /* synthetic */ Dialog val$d1;

        4(Dialog dialog) {
            this.val$d1 = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d1.dispose();
        }
    }
}
