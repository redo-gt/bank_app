package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SubscriptionHelp {
    private B3GUiManager uiManager;

    static /* synthetic */ B3GUiManager access$000(SubscriptionHelp subscriptionHelp) {
        return subscriptionHelp.uiManager;
    }

    public SubscriptionHelp(B3GUiManager b3GUiManager) {
        this.uiManager = b3GUiManager;
    }

    public Form GetPageForm() {
        CihMBanking.exitApplication = true;
        Form form = new Form();
        form.setScrollableY(true);
        form.setLayout(new BoxLayout(2));
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "SubscriptionHelp");
        this.uiManager.stateMachine.findBtnClickHere(createContainer).addActionListener(new 1());
        form.addComponent(createContainer);
        return form;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            new ActivationPage(SubscriptionHelp.access$000(SubscriptionHelp.this)).GetClientIdPhoneForm().show();
        }
    }
}
