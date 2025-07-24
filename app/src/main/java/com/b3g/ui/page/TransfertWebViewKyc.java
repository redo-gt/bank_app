package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Webview;
import com.b3g.tools.NativeCall;
import com.b3g.ui.B3GUiManager;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TransfertWebViewKyc extends B3GPage {
    static Container MDMRBACKOFFICEDtails;
    static Container MDMRTransKysReje;
    static Button RessayBtn;
    static Button RetourBtnMDMRBACKOFFICEDtails;
    static Button RetourBtnMDMRTransKysReje;
    static Button autreAction;
    static Container cntTmp;
    static Button newTransfert;
    static B3GUiManager uiManagerTmp;
    private String url;
    UIBuilder uib = new UIBuilder();
    boolean acceptTermsState = false;
    private Webview w = new Webview();

    public TransfertWebViewKyc(Object obj, Object obj2, int i) {
        B3GUiManager b3GUiManager = (B3GUiManager) obj;
        this.uiManager = b3GUiManager;
        uiManagerTmp = b3GUiManager;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new GridLayout(1, 1));
        cntTmp = this.thisContainer;
        this.thisContainer.setUIID("mn_cntMenuItemV2");
        Container createContainer = this.uib.createContainer("/cihv3", "TransKys");
        MDMRBACKOFFICEDtails = createContainer;
        autreAction = (Button) this.uib.findByName("AjouteBtn", createContainer);
        newTransfert = (Button) this.uib.findByName("TransferBtn", MDMRBACKOFFICEDtails);
        RetourBtnMDMRBACKOFFICEDtails = (Button) this.uib.findByName("RetourBtn", MDMRBACKOFFICEDtails);
        Container createContainer2 = this.uib.createContainer("/cihv3", "TransKysReje");
        MDMRTransKysReje = createContainer2;
        RessayBtn = (Button) this.uib.findByName("RessayBtn", createContainer2);
        RetourBtnMDMRTransKysReje = (Button) this.uib.findByName("RetourBtn", MDMRTransKysReje);
        ServiceResponse InitKycProcess = Webview.InitKycProcess();
        this.w.TransfertWebView(InitKycProcess);
        if (InitKycProcess.getStatusCode().equals("000")) {
            Container createContainer3 = this.uib.createContainer("/cihv3", "AgregationCnt3");
            ((Button) this.uib.findByName("ActivateBtn", createContainer3)).addActionListener(new 1());
            this.thisContainer.add(createContainer3);
        } else if (InitKycProcess.getStatusCode().equals("001")) {
            prcesssKyc(InitKycProcess, this.thisContainer, MDMRTransKysReje, RessayBtn, RetourBtnMDMRBACKOFFICEDtails, RetourBtnMDMRTransKysReje, MDMRBACKOFFICEDtails, autreAction, newTransfert, this.uiManager, false);
        } else if (InitKycProcess.getStatusCode().equals("002") || InitKycProcess.getStatusCode().equals("003")) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, InitKycProcess.getStatusLabel().toString(), null);
            this.thisContainer.setLayout(new BorderLayout());
            this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
        } else {
            this.thisContainer.setLayout(new BorderLayout());
            this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            NativeCall nativeCall = (NativeCall) NativeLookup.create(NativeCall.class);
            if (nativeCall.isSupported()) {
                nativeCall.callWebView(Webview.urlWebViewKyc, "KYC");
            }
        }
    }

    private static void prcesssKyc(ServiceResponse serviceResponse, Container container, Container container2, Button button, Button button2, Button button3, Container container3, Button button4, Button button5, B3GUiManager b3GUiManager, boolean z) {
        try {
            String obj = ((Hashtable) serviceResponse.getParamsOut().get("initTransfertStatus")).get("kycStatus").toString();
            if (obj.equals("REJECTED")) {
                if (z) {
                    CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(166);
                } else {
                    button.setHidden(true);
                    container.addComponent(container2);
                    button3.addActionListener(new 2(b3GUiManager));
                }
            } else if (obj.equals("BACKOFFICE") || obj.equals("IN_PROGRESS")) {
                if (z) {
                    CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(167);
                } else {
                    button4.setHidden(true);
                    button5.setHidden(true);
                    container.addComponent(container3);
                    button2.addActionListener(new 3(b3GUiManager));
                }
            } else if (obj.equals("FINALIZED") || obj.equals("ACCEPTED")) {
                container.addComponent(new TransferFromEtranger(CihMBanking.sesPMTR.getCurrentUiManager(), null, 149).GetPageContainer());
            }
        } catch (Exception unused) {
            if (z) {
                container.removeAll();
            }
            container.setLayout(new BorderLayout());
            container.addComponent("North", b3GUiManager.GetGoBackHome());
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ B3GUiManager val$uiManagerTmp;

        2(B3GUiManager b3GUiManager) {
            this.val$uiManagerTmp = b3GUiManager;
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new TransfertWebViewKyc$2$1$$ExternalSyntheticLambda0(2.this.val$uiManagerTmp)).schedule(300, false, 2.this.val$uiManagerTmp.currentForm);
            }

            static /* synthetic */ void lambda$run$0(B3GUiManager b3GUiManager) {
                b3GUiManager.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ B3GUiManager val$uiManagerTmp;

        3(B3GUiManager b3GUiManager) {
            this.val$uiManagerTmp = b3GUiManager;
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new TransfertWebViewKyc$3$1$$ExternalSyntheticLambda0(3.this.val$uiManagerTmp)).schedule(300, false, 3.this.val$uiManagerTmp.currentForm);
            }

            static /* synthetic */ void lambda$run$0(B3GUiManager b3GUiManager) {
                b3GUiManager.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }

    public static void callBackKycWebView() {
        if (Webview.KycFeedBAck().getStatusCode().equals("000")) {
            ServiceResponse InitKycProcess = Webview.InitKycProcess();
            if (InitKycProcess.getStatusCode().equals("000")) {
                if (InitKycProcess.getParamsOut().get("InitTransfert") != null) {
                    Webview.urlWebViewKyc = WebViewDetails((Hashtable) InitKycProcess.getParamsOut().get("InitTransfert"));
                }
                NativeCall nativeCall = (NativeCall) NativeLookup.create(NativeCall.class);
                if (nativeCall.isSupported()) {
                    nativeCall.callWebView(Webview.urlWebViewKyc, "KYC");
                    return;
                }
                return;
            }
            if (InitKycProcess.getStatusCode().equals("001")) {
                prcesssKyc(InitKycProcess, cntTmp, MDMRTransKysReje, RessayBtn, RetourBtnMDMRBACKOFFICEDtails, RetourBtnMDMRTransKysReje, MDMRBACKOFFICEDtails, autreAction, newTransfert, uiManagerTmp, true);
                return;
            }
            return;
        }
        cntTmp.removeAll();
        cntTmp.addComponent(uiManagerTmp.GetGoBackHome());
    }

    private static String WebViewDetails(Hashtable hashtable) {
        return setData(hashtable, "urlWebViewKyc");
    }

    private static String setData(Hashtable hashtable, String str) {
        return hashtable.get(str) != null ? hashtable.get(str).toString() : "--";
    }
}
