package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class PinNotActivatedPage extends B3GPage {
    private UIBuilder uib = new UIBuilder();

    public PinNotActivatedPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(BoxLayout.y());
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.setScrollableY(false);
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "CodeNonExistCnt");
        ((Button) this.uib.findByName("parameterBtn", createContainer)).addActionListener(new 1());
        this.thisContainer.addComponent(createContainer);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PinNotActivatedPage.this.uiManager.NavigateToPageById(111);
        }
    }
}
