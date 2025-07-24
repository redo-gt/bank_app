package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Notification;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class NotificationPage extends B3GPage {
    public NotificationPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        CihMBanking.sesPMTR.listeNotifs = new Notification().NotificationProcess();
        this.thisContainer = this.uiManager.DrawListContainer("GloabalContainerV2", "Mes Notifications", Boolean.TRUE, CihMBanking.sesPMTR.listeNotifs, CihMBanking.sesPMTR.listeNotifs.size(), 0, "Vous n'avez aucune notification", null, null, null, null);
        this.thisContainer.forceRevalidate();
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }
}
