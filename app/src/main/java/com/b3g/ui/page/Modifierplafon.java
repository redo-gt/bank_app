package com.b3g.ui.page;

import com.b3g.services.B3gService;
import com.b3g.ui.B3GSlider;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Modifierplafon extends B3GPage {
    private final int RETRAIT = 1;
    private final int TPE = 2;
    private final int ECOM = 3;

    private Container getBtnsCnt() {
        return null;
    }

    public Modifierplafon(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        Label label = new Label("Modifier les plafonds", "gb_lblGlobalHeaderTitleV2");
        this.thisContainer = new Container(new BoxLayout(2));
        Container container = new Container();
        container.setUIID("g_CntBlueBorder");
        container.add(FlowLayout.encloseCenterMiddle(new Label("Plafonds au Maroc", "Lbl_blan")));
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "GloabalContainerV2");
        this.uiManager.stateMachine.findLblGlobalHeaderTitle(createContainer).remove();
        createContainer.add(label);
        createContainer.addComponent(container);
        createContainer.addComponent(getModifierCnt(1, 0, 10000, 1500));
        createContainer.addComponent(new SepCnt());
        createContainer.addComponent(getModifierCnt(2, 0, 10000, 1500));
        createContainer.addComponent(new SepCnt());
        createContainer.addComponent(getModifierCnt(3, 0, 10000, 1500));
        Button button = new Button("SAUVGARDER");
        button.setUIID("GoButton");
        button.addActionListener(new 1());
        Button button2 = new Button("RETABLIR");
        button2.setUIID("GoButton");
        button2.addActionListener(new 2());
        button.getAllStyles().setMarginUnit(1);
        button.getAllStyles().setMargin(2, 1, 0, 0);
        createContainer.addComponent(button2);
        createContainer.addComponent(button);
        this.thisContainer.getAllStyles().setPaddingUnit(1);
        this.thisContainer.getAllStyles().setPadding(2, 2, 3, 3);
        this.thisContainer.add(createContainer);
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Modifierplafon.this.uiManager.NavigateToPageById(137);
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Modifierplafon.this.uiManager.NavigateToPageById(137);
        }
    }

    private Container getModifierCnt(int i, int i2, int i3, int i4) {
        Container container = new Container(BoxLayout.y());
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1, 1, 1, 1);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(1, 1, 0, 0);
        Label label = new Label();
        label.getAllStyles().setAlignment(1);
        label.setUIID("Container");
        TextArea textArea = new TextArea();
        textArea.setUIID("lbl_black_very_small");
        textArea.getAllStyles().setPaddingUnit(1);
        textArea.getAllStyles().setPadding(1, 1, 0, 0);
        textArea.setEditable(false);
        if (i == 1) {
            label.setText("PLAFOND DE RETRAIT");
            textArea.setText("Vous disposez d’un plafond de retrait de " + i4 + " Dhs sur 30 jours");
        } else if (i == 2) {
            label.setText("PLAFOND DE paiement tpe".toUpperCase());
            textArea.setText("Vous disposez d’un plafond de paiement TPE de " + i4 + " Dhs sur 30 jours");
        } else if (i == 3) {
            label.setText("PLAFOND DE paiement internet".toUpperCase());
            textArea.setText("Vous disposez d’un plafond de paiement internet de " + i4 + " Dhs sur 30 jours");
        }
        container.add(FlowLayout.encloseLeftMiddle(label)).add(textArea).add(new B3GSlider(i2, i3, i4, 1, "", "Dhs"));
        return container;
    }

    public class SepCnt extends Container {
        public SepCnt() {
            getAllStyles().setPaddingUnit(0);
            getAllStyles().setPadding(1, 0, 3, 3);
            getAllStyles().setBgColor(7368816);
            getAllStyles().setBgTransparency(255);
        }
    }
}
