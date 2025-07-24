package com.b3g.ui.page;

import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.AccountLastConnection;
import com.b3g.ui.page.cih.Module.LoginAcivateAccount;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class IdentificationGlb extends B3GPage {
    AccountLastConnection acc;
    String radicalClient;
    private UIBuilder uib = new UIBuilder();

    public IdentificationGlb(Object obj, B3gService b3gService, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = b3gService;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.uiManager.CurrentPageId = 120;
        LoginAcivateAccount loginAcivateAccount = new LoginAcivateAccount(this.uiManager);
        loginAcivateAccount.getAllStyles().setMarginUnit(1);
        loginAcivateAccount.getAllStyles().setMargin(0, 0, 4, 4);
        this.thisContainer.addComponent(loginAcivateAccount);
        this.uiManager.currentForm.setBackCommand(new 1("Back"));
        return this.thisContainer;
    }

    class 1 extends Command {
        1(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            IdentificationGlb.this.uiManager.GoBackOffligne();
        }
    }
}
