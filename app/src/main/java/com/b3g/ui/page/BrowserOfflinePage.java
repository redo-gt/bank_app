package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class BrowserOfflinePage extends B3GPage {
    private String urlWebViw;

    public BrowserOfflinePage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
        setUrlWebView(CihMBanking.sesPMTR.urlAvantageWebView);
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new GridLayout(1, 1));
        new TableLayout(2, 1);
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(new FlowLayout());
        int toolbarHeight = CihMBanking.sesPMTR.getToolbarHeight();
        Display.getInstance().getDisplayHeight();
        Container container3 = new Container(new GridLayout(1, 1));
        container3.add(DrawWebViewtem(this.urlWebViw));
        container2.setPreferredH(toolbarHeight);
        container.add("North", container2);
        container.add("Center", container3);
        this.thisContainer.add(container);
        this.uiManager.currentForm.setBackCommand(new 1("Back"));
        this.thisContainer.revalidate();
        return this.thisContainer;
    }

    class 1 extends Command {
        1(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            BrowserOfflinePage.this.uiManager.GoBackOffligne();
        }
    }

    private void setUrlWebView(String str) {
        this.urlWebViw = str;
    }

    public Container DrawWebViewtem(String str) {
        BrowserComponent browserComponent = new BrowserComponent();
        browserComponent.setProperty("JavaScriptEnabled", Boolean.TRUE);
        Display.getInstance().callSerially(new 2(str, browserComponent));
        return browserComponent;
    }

    class 2 implements Runnable {
        final /* synthetic */ BrowserComponent val$browser;
        final /* synthetic */ String val$url;

        2(String str, BrowserComponent browserComponent) {
            this.val$url = str;
            this.val$browser = browserComponent;
        }

        public void run() {
            System.err.print(this.val$url);
            this.val$browser.setURL(this.val$url);
        }
    }
}
