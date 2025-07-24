package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.FolderMissionSaham;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.MapSaham;
import com.codename1.ui.Container;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SahamPage extends B3GPage {
    FolderMissionSaham sahamTrack;

    public SahamPage(Object obj, Object obj2, int i, FolderMissionSaham folderMissionSaham) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        this.sahamTrack = folderMissionSaham;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        return new MapSaham().drawMapp(this.uiManager, this.sahamTrack);
    }
}
