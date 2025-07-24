package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.TransfertDATA;
import com.b3g.tools.ContactsFilterProxyListModel;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.List;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ContactsPage extends B3GPage {
    private ArrayList beneficiaryList;
    private Image circleLineImage;
    private Object circleMask;
    private int circleMaskHeight;
    private int circleMaskWidth;
    ContactsFilterProxyListModel filter;
    HashMap index;
    private Font letterFont;
    List list;

    public ContactsPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (TransfertDATA) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        return this.thisContainer;
    }
}
