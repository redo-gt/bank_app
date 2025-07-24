package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Webview;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.CN;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class WebViewTransfertPage extends B3GPage {
    private String url;
    UIBuilder uib = new UIBuilder();
    boolean acceptTermsState = false;

    public WebViewTransfertPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setUIID("mn_cntMenuItemV2");
        try {
            this.thisContainer.addComponent(DrawWebItem(CihMBanking.sesPMTR.paymentConfirmationWebViewUrl));
        } catch (Exception unused) {
            this.thisContainer.setLayout(new BorderLayout());
            this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    private Container DrawWebItem(String str) {
        try {
            BrowserComponent browserComponent = new BrowserComponent();
            browserComponent.setProperty("JavaScriptEnabled", Boolean.FALSE);
            Display.getInstance().callSerially(new 1(browserComponent, str));
            browserComponent.addBrowserNavigationCallback(new WebViewTransfertPage$$ExternalSyntheticLambda0(this, browserComponent));
            return browserComponent;
        } catch (Exception unused) {
            return new Container();
        }
    }

    class 1 implements Runnable {
        final /* synthetic */ BrowserComponent val$browser;
        final /* synthetic */ String val$url;

        1(BrowserComponent browserComponent, String str) {
            this.val$browser = browserComponent;
            this.val$url = str;
        }

        public void run() {
            this.val$browser.setURL(this.val$url);
        }
    }

    /* synthetic */ boolean lambda$DrawWebItem$4$com-b3g-ui-page-WebViewTransfertPage(BrowserComponent browserComponent, String str) {
        str.hashCode();
        if (!str.equals("http://exitmeok/")) {
            if (!str.equals("http://exitme/")) {
                return true;
            }
            browserComponent.stop();
            CN.callSerially(new WebViewTransfertPage$$ExternalSyntheticLambda4());
            return false;
        }
        try {
            ServiceResponse TransferRef = Webview.TransferRef();
            browserComponent.stop();
            if (TransferRef.getStatusCode().equals("000")) {
                CN.callSerially(new WebViewTransfertPage$$ExternalSyntheticLambda1());
            } else {
                CN.callSerially(new WebViewTransfertPage$$ExternalSyntheticLambda2());
                Display.getInstance().callSerially(new 2());
            }
        } catch (Exception unused) {
            browserComponent.stop();
            CN.callSerially(new WebViewTransfertPage$$ExternalSyntheticLambda3());
            Display.getInstance().callSerially(new 3());
        }
        return false;
    }

    static /* synthetic */ void lambda$DrawWebItem$0() {
        CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(153);
    }

    static /* synthetic */ void lambda$DrawWebItem$1() {
        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
    }

    class 2 implements Runnable {
        2() {
        }

        public void run() {
            new UITimer(new WebViewTransfertPage$2$$ExternalSyntheticLambda0()).schedule(50, false, CihMBanking.sesPMTR.getCurrentUiManager().currentForm);
        }

        static /* synthetic */ void lambda$run$0() {
            CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    static /* synthetic */ void lambda$DrawWebItem$2() {
        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
    }

    class 3 implements Runnable {
        3() {
        }

        public void run() {
            new UITimer(new WebViewTransfertPage$3$$ExternalSyntheticLambda0()).schedule(50, false, CihMBanking.sesPMTR.getCurrentUiManager().currentForm);
        }

        static /* synthetic */ void lambda$run$0() {
            CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    static /* synthetic */ void lambda$DrawWebItem$3() {
        CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(140);
    }
}
