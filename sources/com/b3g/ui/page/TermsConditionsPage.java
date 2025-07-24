package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.ServiceManager;
import com.b3g.tools.CacheNative;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TermsConditionsPage extends B3GPage {
    private String VnewaliasText;
    private TextField txtHBPassWord;
    private final UIBuilder ui = new UIBuilder();
    private boolean isSendEmail = false;
    private boolean isCGUAccepted = false;

    static /* synthetic */ boolean access$000(TermsConditionsPage termsConditionsPage) {
        return termsConditionsPage.isSendEmail;
    }

    static /* synthetic */ boolean access$002(TermsConditionsPage termsConditionsPage, boolean z) {
        termsConditionsPage.isSendEmail = z;
        return z;
    }

    static /* synthetic */ boolean access$100(TermsConditionsPage termsConditionsPage) {
        return termsConditionsPage.isCGUAccepted;
    }

    static /* synthetic */ boolean access$102(TermsConditionsPage termsConditionsPage, boolean z) {
        termsConditionsPage.isCGUAccepted = z;
        return z;
    }

    static /* synthetic */ Container access$200(TermsConditionsPage termsConditionsPage, Container container, Container container2) {
        return termsConditionsPage.GetTransferSecurityForm(container, container2);
    }

    static /* synthetic */ String access$300(TermsConditionsPage termsConditionsPage) {
        return termsConditionsPage.VnewaliasText;
    }

    static /* synthetic */ String access$302(TermsConditionsPage termsConditionsPage, String str) {
        termsConditionsPage.VnewaliasText = str;
        return str;
    }

    public TermsConditionsPage(Object obj, Object obj2, int i) {
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
        Container createContainer = uIBuilder.createContainer("/cihv3", "GeneralConditionCnt");
        Button button = (Button) uIBuilder.findByName("activateBtn", createContainer);
        Button button2 = (Button) uIBuilder.findByName("disableBtn", createContainer);
        SpanButton spanButton = new SpanButton("J'accepte la réception de l'intégralité des CGU par email");
        spanButton.setIcon(this.uiManager.ressource.getImage("icone_selected_ligne.png"));
        spanButton.setGap(15);
        spanButton.setUIID("padding_1_1_0_0");
        spanButton.setTextUIID("cguCheckBtn");
        spanButton.addActionListener(new 1(spanButton, button));
        SpanButton spanButton2 = new SpanButton("J'accepte les conditions générales");
        spanButton2.setIcon(this.uiManager.ressource.getImage("icone_selected_ligne.png"));
        spanButton2.setGap(15);
        spanButton2.setUIID("padding_1_1_0_0");
        spanButton2.setTextUIID("cguCheckBtn");
        spanButton2.addActionListener(new 2(spanButton2, button));
        Container container = (Container) uIBuilder.findByName("Container7", createContainer);
        container.add(spanButton);
        container.add(spanButton2);
        CacheNative cacheNative = (CacheNative) NativeLookup.create(CacheNative.class);
        button.setEnabled(false);
        button.addActionListener(new 3(cacheNative, createContainer));
        button2.addActionListener(new 4());
        this.thisContainer.add(createContainer);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Button val$activateBtn;
        final /* synthetic */ SpanButton val$spanBtnEmail;

        1(SpanButton spanButton, Button button) {
            this.val$spanBtnEmail = spanButton;
            this.val$activateBtn = button;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (TermsConditionsPage.this.sendEmail(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email).getStatusCode().equals("000")) {
                TermsConditionsPage.access$002(TermsConditionsPage.this, true);
            } else {
                TermsConditionsPage.access$002(TermsConditionsPage.this, false);
            }
            if (TermsConditionsPage.access$000(TermsConditionsPage.this)) {
                this.val$spanBtnEmail.setIcon(TermsConditionsPage.this.uiManager.ressource.getImage("icone_selected_ligne_w.png"));
            } else {
                this.val$spanBtnEmail.setIcon(TermsConditionsPage.this.uiManager.ressource.getImage("icone_selected_ligne.png"));
                this.val$activateBtn.setEnabled(false);
            }
            if (TermsConditionsPage.access$000(TermsConditionsPage.this) && TermsConditionsPage.access$100(TermsConditionsPage.this)) {
                this.val$activateBtn.setEnabled(true);
            }
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Button val$activateBtn;
        final /* synthetic */ SpanButton val$spanBtnValidate;

        2(SpanButton spanButton, Button button) {
            this.val$spanBtnValidate = spanButton;
            this.val$activateBtn = button;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$spanBtnValidate.setIcon(TermsConditionsPage.this.uiManager.ressource.getImage("icone_selected_ligne_w.png"));
            TermsConditionsPage.access$102(TermsConditionsPage.this, !TermsConditionsPage.access$100(r4));
            if (TermsConditionsPage.access$100(TermsConditionsPage.this)) {
                this.val$spanBtnValidate.setIcon(TermsConditionsPage.this.uiManager.ressource.getImage("icone_selected_ligne_w.png"));
                if (TermsConditionsPage.access$000(TermsConditionsPage.this)) {
                    this.val$activateBtn.setEnabled(true);
                    return;
                }
                return;
            }
            this.val$spanBtnValidate.setIcon(TermsConditionsPage.this.uiManager.ressource.getImage("icone_selected_ligne.png"));
            this.val$activateBtn.setEnabled(false);
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ CacheNative val$cache;
        final /* synthetic */ Container val$termsGonditionsCnt;

        3(CacheNative cacheNative, Container container) {
            this.val$cache = cacheNative;
            this.val$termsGonditionsCnt = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$cache.setTermsConditionsState(true);
            CihMBanking.sesPMTR.setSessionSavedContainer(TermsConditionsPage.this.uiManager.stateMachine.findCntGlobalTransfer(this.val$termsGonditionsCnt));
            TermsConditionsPage termsConditionsPage = TermsConditionsPage.this;
            Container container = termsConditionsPage.thisContainer;
            Container container2 = this.val$termsGonditionsCnt;
            TermsConditionsPage termsConditionsPage2 = TermsConditionsPage.this;
            termsConditionsPage.SwitchTransfertForms(container, container2, TermsConditionsPage.access$200(termsConditionsPage2, termsConditionsPage2.thisContainer, this.val$termsGonditionsCnt), false);
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(108);
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
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 5(b3GCIHEcode));
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 6(container, createContainer, container2));
        return createContainer;
    }

    class 5 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        5(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TermsConditionsPage.access$302(TermsConditionsPage.this, this.val$eCode1.getValue());
            String access$300 = TermsConditionsPage.access$300(TermsConditionsPage.this);
            if (access$300.length() == 0) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TermsConditionsPage.this.uiManager.stateMachine, "Veuillez saisir le CIH E-CODE", null);
                return;
            }
            ServiceResponse enable = TermsConditionsPage.this.enable(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID, access$300);
            if (!enable.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TermsConditionsPage.this.uiManager.stateMachine, enable.getStatusLabel(), null);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TermsConditionsPage.this.uiManager.stateMachine, "Pour bénéficier de ce service, envoyez « Bonjour » au n° (+212) 0522479947 sur Whatsapp", null);
                CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(104);
            }
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ Container val$Parent;
        final /* synthetic */ Container val$cntTransfertSecurityForm;
        final /* synthetic */ Container val$firstCnt;

        6(Container container, Container container2, Container container3) {
            this.val$Parent = container;
            this.val$cntTransfertSecurityForm = container2;
            this.val$firstCnt = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TermsConditionsPage.this.SwitchTransfertForms(this.val$Parent, this.val$cntTransfertSecurityForm, this.val$firstCnt, false);
        }
    }

    public ServiceResponse sendEmail(String str, String str2, String str3) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("SESSION_ID", str2);
        hashtable.put("EMAIL", str3);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(106);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.RuninSilent(serviceRequest)).get(0);
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
}
