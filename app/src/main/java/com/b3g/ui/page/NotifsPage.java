package com.b3g.ui.page;

import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class NotifsPage extends B3GPage {
    public NotifsPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new BorderLayout());
        this.thisContainer.setUIID("BlueBorderNotifs");
        Container container = new Container(new BoxLayout(2));
        Container container2 = new Container(new FlowLayout(4, 4));
        Container container3 = new Container(new BoxLayout(2));
        Container container4 = new Container();
        Label label = new Label("Mes notifications");
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        label.getAllStyles().setMarginLeft(0);
        Container container5 = new Container(new FlowLayout(4, 4));
        Label label2 = new Label();
        label2.setIcon(this.uiManager.ressource.getImage("NotifsBigIcon.png"));
        container5.add(label2);
        container5.setUIID("margin_0_3_0_0");
        Container container6 = new Container();
        SpanLabel spanLabel = new SpanLabel("En activant ce service, vous recevrez les notifications relatif à votre relation bancaire en temps réel sur l'appareil de votre choix");
        Container container7 = new Container(new BoxLayout(2));
        Button button = new Button("J'active les notifications");
        button.setUIID("op_BtnOppositionValidationOrgBg");
        container7.add(button);
        Container container8 = new Container(new GridLayout(1, 2));
        Button button2 = new Button("Annuler");
        Button button3 = new Button("Plus tard");
        button2.setUIID("lbl_big_gris_centerNotifs");
        button3.setUIID("lbl_big_gris_centerNotifs");
        container8.add(button2);
        container8.add(button3);
        container.add(container7);
        container.add(container8);
        container4.add(label);
        container6.add(spanLabel);
        container3.add(container5);
        container3.add(container6);
        container2.add(container3);
        this.thisContainer.add("North", container4);
        this.thisContainer.add("Center", container2);
        this.thisContainer.add("South", container);
        return this.thisContainer;
    }
}
