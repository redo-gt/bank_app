package com.b3g.ui.page;

import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class EpargneFinalize extends B3GPage {
    UIBuilder uib = new UIBuilder();

    public EpargneFinalize(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        Container createContainer = this.uib.createContainer("/cihv3", "EpargneFinalize");
        Button button = (Button) this.uib.findByName("AlimenterBtn", createContainer);
        Button button2 = (Button) this.uib.findByName("ProgrammerBtn", createContainer);
        Button button3 = (Button) this.uib.findByName("RetourBtn", createContainer);
        button.addActionListener(new 1());
        button2.addActionListener(new 2());
        button3.addActionListener(new 3());
        this.thisContainer.add(createContainer);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            EpargneFinalize.this.uiManager.NavigateToPageById(128);
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            EpargneFinalize.this.uiManager.NavigateToPageById(129);
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            EpargneFinalize.this.uiManager.NavigateToPageById(122);
        }
    }
}
