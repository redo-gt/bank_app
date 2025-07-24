package com.b3g.ui.page;

import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TransfertKyc extends B3GPage {
    private String url;
    UIBuilder uib = new UIBuilder();
    boolean acceptTermsState = false;

    public TransfertKyc(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        return this.uib.createContainer("/cihv3", "TransKys");
    }
}
