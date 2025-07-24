package com.b3g.ui.page.cih.Module;

import com.b3g.services.B3gService;
import com.b3g.services.FolderMissionSaham;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.B3GPage;
import com.b3g.ui.page.SahamPage;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.GridLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SahamMapForm extends B3GPage {
    FolderMissionSaham missionSaham;

    public SahamMapForm(Object obj, Object obj2, int i, FolderMissionSaham folderMissionSaham) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        this.missionSaham = folderMissionSaham;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setScrollableY(false);
        this.thisContainer.add(new SahamPage(this.uiManager, this.service, 160, this.missionSaham).GetPageContainer());
        return this.thisContainer;
    }
}
