package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MDMBackOfficeDtailPage extends B3GPage {
    UIBuilder uib = new UIBuilder();
    private String urlWebViw;

    public MDMBackOfficeDtailPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setUIID("mn_cntMenuItem");
        Container createContainer = this.uib.createContainer("/cihv3", "TransKys");
        Button button = (Button) this.uib.findByName("AjouteBtn", createContainer);
        Button button2 = (Button) this.uib.findByName("TransferBtn", createContainer);
        Button button3 = (Button) this.uib.findByName("RetourBtn", createContainer);
        button.setHidden(true);
        button2.setHidden(true);
        button3.addActionListener(new 1());
        this.thisContainer.addComponent(createContainer);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new MDMBackOfficeDtailPage$1$1$$ExternalSyntheticLambda0(this)).schedule(300, false, MDMBackOfficeDtailPage.this.uiManager.currentForm);
            }

            /* synthetic */ void lambda$run$0$com-b3g-ui-page-MDMBackOfficeDtailPage$1$1() {
                MDMBackOfficeDtailPage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }
}
