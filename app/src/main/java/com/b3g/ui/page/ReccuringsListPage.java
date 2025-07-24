package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Recurring;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ReccuringsListPage extends B3GPage {
    public ReccuringsListPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            ArrayList RecurringProcess = new Recurring().RecurringProcess();
            this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Mes Prélèvements", Boolean.TRUE, RecurringProcess, RecurringProcess.size(), 6, CihMBanking.sesPMTR.getSessionClient().getClient_RecurringList_StatusLabel(), null, null, null, null));
            Container container = new Container();
            container.setUIID("g_CntTranspMsg");
            this.thisContainer.addComponent(container);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }
}
