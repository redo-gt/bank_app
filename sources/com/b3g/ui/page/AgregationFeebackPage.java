package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Webview;
import com.b3g.tools.NativeCall;
import com.b3g.ui.B3GUiManager;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AgregationFeebackPage extends B3GPage {
    UIBuilder uib = new UIBuilder();

    public AgregationFeebackPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setUIID("mn_cntMenuItemV2");
        Container createContainer = this.uib.createContainer("/cihv3", "AgregationCnt2");
        Button button = (Button) this.uib.findByName("RetourBtn", createContainer);
        ((Button) this.uib.findByName("AjouteBtn", createContainer)).addActionListener(new 1());
        ((Container) this.uib.findByName("Container11", createContainer)).setHidden(true);
        ((Container) this.uib.findByName("Container10", createContainer)).setHidden(true);
        button.addActionListener(new 2());
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        createContainer.setShouldCalcPreferredSize(true);
        this.thisContainer.add(createContainer);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ArrayList FillAjouterNVArrayDataFromServiceResponse = new Webview().FillAjouterNVArrayDataFromServiceResponse(Webview.WebViewNVProcess());
            NativeCall nativeCall = (NativeCall) NativeLookup.create(NativeCall.class);
            if (nativeCall.isSupported()) {
                nativeCall.callWebView(((Webview) FillAjouterNVArrayDataFromServiceResponse.get(0)).webViewURL, "CREATE");
            }
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(156);
        }
    }
}
