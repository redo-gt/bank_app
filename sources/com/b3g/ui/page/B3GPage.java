package com.b3g.ui.page;

import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public abstract class B3GPage {
    public boolean OnOffSwitch;
    public int PageId;
    public String PageName = GetMyName();
    public Object PageSession;
    public boolean isNew;
    public B3gService service;
    public Container thisContainer;
    public B3GUiManager uiManager;

    public abstract Container GetPageContainer();

    public void updateMode() {
    }

    public String GetMyName() {
        try {
            String simpleName = getClass().getSimpleName();
            return simpleName.equals((Object) null) ? "-" : simpleName;
        } catch (Exception unused) {
            return "-";
        }
    }
}
