package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.layouts.GridLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class BrowserPage extends B3GPage {
    private String urlWebViw;

    public BrowserPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
        setUrlWebView(CihMBanking.sesPMTR.urlAvantageWebView);
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.addComponent(DrawWebViewtem(this.urlWebViw));
        this.thisContainer.setName("Browser");
        this.thisContainer.revalidate();
        return this.thisContainer;
    }

    private void setUrlWebView(String str) {
        this.urlWebViw = str;
    }

    public Container DrawWebViewtem(String str) {
        BrowserComponent browserComponent = new BrowserComponent();
        browserComponent.setProperty("JavaScriptEnabled", Boolean.TRUE);
        Display.getInstance().callSerially(new 1(str, browserComponent));
        return browserComponent;
    }

    class 1 implements Runnable {
        final /* synthetic */ BrowserComponent val$browser;
        final /* synthetic */ String val$url;

        1(String str, BrowserComponent browserComponent) {
            this.val$url = str;
            this.val$browser = browserComponent;
        }

        public void run() {
            System.err.print(this.val$url);
            this.val$browser.setURL(this.val$url);
        }
    }
}
