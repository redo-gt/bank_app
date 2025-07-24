package com.b3g.ui.page.cih.Module;

import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.B3GPage;
import com.b3g.ui.page.LocaliserAgencePage;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.GridLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class LocaliserGabOffligne extends B3GPage {
    public LocaliserGabOffligne(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        Form form = new Form(new GridLayout(1, 1));
        form.setScrollableY(false);
        form.add(new LocaliserAgencePage(this.uiManager, null, 60).GetPageContainer());
        form.show();
        return form;
    }
}
