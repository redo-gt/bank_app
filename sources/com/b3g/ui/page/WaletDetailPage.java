package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class WaletDetailPage extends B3GPage {
    public WaletDetailPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        Container container = new Container(new BoxLayout(2));
        container.setUIID("mn_cntMenuItem");
        try {
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "WaletBSDetail");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(new Container(new BoxLayout(2)), "ACHATS"));
            arrayList.add(new B3gContainer(new Container(new BoxLayout(2)), "VENTES"));
            this.uiManager.DrawTabsWithNavigation(this.uiManager.stateMachine.findCntWaletBSDetail(createContainer), arrayList);
            container.addComponent(createContainer);
        } catch (Exception unused) {
            container.addComponent(this.uiManager.GetGoBackHome());
        }
        this.thisContainer = container;
        return container;
    }
}
