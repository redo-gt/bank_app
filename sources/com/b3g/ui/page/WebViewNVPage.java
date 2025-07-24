package com.b3g.ui.page;

import com.b3g.services.B3gService;
import com.b3g.services.Webview;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class WebViewNVPage extends B3GPage {
    private String url;
    UIBuilder uib = new UIBuilder();
    boolean acceptTermsState = false;

    public WebViewNVPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setUIID("mn_cntMenuItemV2");
        try {
            Webview webview = new Webview();
            this.thisContainer.addComponent(webview.DrawTransWebItem(((Webview) webview.FillAjouterNVArrayDataFromServiceResponse(Webview.WebViewNVProcess()).get(0)).webViewURL));
        } catch (Exception unused) {
            this.thisContainer.setLayout(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }
}
