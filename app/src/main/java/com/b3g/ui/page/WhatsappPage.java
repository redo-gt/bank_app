package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.ServiceManager;
import com.b3g.tools.CacheNative;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.codename1.components.SpanLabel;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class WhatsappPage extends B3GPage {
    TextField TextF1;
    TextField TextF2;
    TextField TextF3;
    TextField TextF4;
    private String VnewaliasText;
    private Dialog dlg;
    private TextField txtHBPassWord;
    private final UIBuilder ui = new UIBuilder();

    private boolean getServiceState() {
        return false;
    }

    static /* synthetic */ Container access$000(WhatsappPage whatsappPage, Container container, Container container2) {
        return whatsappPage.GetTransferSecurityForm(container, container2);
    }

    static /* synthetic */ String access$100(WhatsappPage whatsappPage) {
        return whatsappPage.VnewaliasText;
    }

    static /* synthetic */ String access$102(WhatsappPage whatsappPage, String str) {
        whatsappPage.VnewaliasText = str;
        return str;
    }

    public WhatsappPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        UIBuilder uIBuilder = new UIBuilder();
        this.thisContainer = new Container(new BoxLayout(2));
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            SpanLabel spanLabel = new SpanLabel("Ce service n'est pas disponible en mode démonstration.");
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            this.thisContainer.add("Center", spanLabel);
            return this.thisContainer;
        }
        Container createContainer = uIBuilder.createContainer("/cihv3", "WhatsAppPopUp");
        Button button = (Button) uIBuilder.findByName("activateTouchBtn", createContainer);
        ((SpanLabel) uIBuilder.findByName("numberLbl", createContainer)).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm);
        CacheNative cacheNative = (CacheNative) NativeLookup.create(CacheNative.class);
        if (!getWhatsappState(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID)) {
            button.addActionListener(new 1(cacheNative, createContainer));
        } else {
            button.setIcon(this.uiManager.ressource.getImage("radio_on.png"));
            button.setText("Désactiver service CIH M3AK");
            button.addActionListener(new 2(button));
        }
        this.thisContainer.add(createContainer);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$WhatsappCnt;
        final /* synthetic */ CacheNative val$cache;

        1(CacheNative cacheNative, Container container) {
            this.val$cache = cacheNative;
            this.val$WhatsappCnt = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!this.val$cache.getTermsConditionsState()) {
                WhatsappPage.this.uiManager.NavigateToPageById(109);
                return;
            }
            CihMBanking.sesPMTR.setSessionSavedContainer(WhatsappPage.this.uiManager.stateMachine.findCntGlobalTransfer(this.val$WhatsappCnt));
            WhatsappPage whatsappPage = WhatsappPage.this;
            Container container = whatsappPage.thisContainer;
            Container container2 = this.val$WhatsappCnt;
            WhatsappPage whatsappPage2 = WhatsappPage.this;
            whatsappPage.SwitchTransfertForms(container, container2, WhatsappPage.access$000(whatsappPage2, whatsappPage2.thisContainer, this.val$WhatsappCnt), false);
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Button val$activateTouchBtn;

        2(Button button) {
            this.val$activateTouchBtn = button;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            WhatsappPage.this.ShowConfirmation(this.val$activateTouchBtn);
        }
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }

    private Container GetTransferSecurityForm(Container container, Container container2) {
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container3 = (Container) this.ui.findByName("Container17", createContainer);
        container3.removeAll();
        container3.setLayout(BoxLayout.y());
        container3.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 3(b3GCIHEcode));
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 4(container, createContainer, container2));
        return createContainer;
    }

    class 3 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        3(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            WhatsappPage.access$102(WhatsappPage.this, this.val$eCode1.getValue());
            String access$100 = WhatsappPage.access$100(WhatsappPage.this);
            if (access$100.length() == 0) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(WhatsappPage.this.uiManager.stateMachine, "Veuillez saisir le CIH E-CODE", null);
                return;
            }
            ServiceResponse enable = WhatsappPage.this.enable(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID, access$100);
            if (!enable.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(WhatsappPage.this.uiManager.stateMachine, enable.getStatusLabel(), null);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(WhatsappPage.this.uiManager.stateMachine, "Pour bénéficier de ce service, envoyez « Bonjour » au n° (+212) 0522479947 sur Whatsapp", null);
                CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(104);
            }
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Container val$Parent;
        final /* synthetic */ Container val$cntTransfertSecurityForm;
        final /* synthetic */ Container val$firstCnt;

        4(Container container, Container container2, Container container3) {
            this.val$Parent = container;
            this.val$cntTransfertSecurityForm = container2;
            this.val$firstCnt = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            WhatsappPage.this.SwitchTransfertForms(this.val$Parent, this.val$cntTransfertSecurityForm, this.val$firstCnt, false);
        }
    }

    public ServiceResponse enable(String str, String str2, String str3) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("SESSION_ID", str2);
        hashtable.put("PASSID", str3);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(103);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse disable(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("SESSION_ID", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(104);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public void ShowConfirmation(Button button) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "disableConfirmation");
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
        ((Button) uIBuilder.findByName("confirmationBtn", createContainer)).addActionListener(new 5(dialog, button));
        ((Button) uIBuilder.findByName("cancelBtn", createContainer)).addActionListener(new 6(dialog));
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", true);
    }

    class 5 implements ActionListener {
        final /* synthetic */ Button val$activateTouchBtn;
        final /* synthetic */ Dialog val$d;

        5(Dialog dialog, Button button) {
            this.val$d = dialog;
            this.val$activateTouchBtn = button;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ServiceResponse disable = WhatsappPage.this.disable(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
            if (!disable.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(WhatsappPage.this.uiManager.stateMachine, DataTools.FormatUnicode(disable.getStatusLabel()), null);
                return;
            }
            this.val$d.dispose();
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(WhatsappPage.this.uiManager.stateMachine, disable.getStatusLabel(), null);
            this.val$activateTouchBtn.setIcon(WhatsappPage.this.uiManager.ressource.getImage("radio_off.png"));
            CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(104);
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        6(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    private boolean getWhatsappState(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("SESSION_ID", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(107);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        ServiceResponse serviceResponse = (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
        if (serviceResponse.getStatusCode().equals("000")) {
            String str3 = (String) serviceResponse.getParamsOut().get("isActivated");
            str3.hashCode();
            if (str3.equals("true")) {
                return true;
            }
        }
        return false;
    }
}
