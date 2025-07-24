package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Messagerie;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Messagerie1 extends B3GPage {
    public B3GUiManager uiManager;
    UIBuilder uib = new UIBuilder();
    Messagerie Message = new Messagerie();

    public Messagerie1(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new LayeredLayout());
        this.thisContainer.setUIID("mn_cntMenuItem");
        Container container = new Container(BoxLayout.y());
        Label label = new Label("Ma messagerie");
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        label.setScrollVisible(false);
        label.setEnabled(false);
        container.addComponent(label);
        Container container2 = new Container(new BoxLayout(2));
        container2.setScrollVisible(false);
        container.addComponent(container2);
        container.addComponent(DrawMTCHomeMenuV2());
        Container container3 = new Container(new BorderLayout());
        container3.setUIID("Container");
        Container container4 = new Container(new BorderLayout());
        container4.setUIID("Container");
        Button button = new Button(this.uiManager.ressource.getImage("new-message.png"));
        button.setUIID("margin_3_3_3_3");
        button.addActionListener(new 1());
        container3.addComponent("East", button);
        container4.addComponent("South", container3);
        this.thisContainer.add(container);
        this.thisContainer.add(container4);
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                CihMBanking.sesPMTR.IsMessageResponse = false;
                Messagerie1.this.uiManager.NavigateToPageById(92);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(Messagerie1.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
            }
        }
    }

    private Component DrawMTCHomeMenuV2() {
        new Container(new BorderLayout());
        Container container = new Container(new BoxLayout(2));
        container.setUIID("");
        container.setScrollVisible(false);
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("gl_GloabalContainerV2NoPadMargin");
        container2.setScrollVisible(false);
        Component container3 = new Container(new BoxLayout(2));
        container3.setUIID("bf_cntBeneficarySeparator");
        container3.setScrollVisible(false);
        container2.addComponent(container3);
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container4 = new Container();
        container4.setLayout(tableLayout);
        container4.setUIID("mtc_CntTableLayoutN");
        container4.setFocusable(false);
        container4.setScrollable(false);
        Container container5 = new Container(new FlowLayout(1, 4));
        Container container6 = new Container(new LayeredLayout());
        container6.setUIID("margin_0_0_2_2");
        new Container(new FlowLayout(4, 4));
        Container container7 = new Container(new FlowLayout(4, 4));
        Label label = new Label("", this.uiManager.ressource.getImage("cercle.png"));
        container7.add(new Label(CihMBanking.sesPMTR.countMessage, "lbl_verysmall_white"));
        container6.add(label);
        container6.add(container7);
        Button button = new Button("Messages reçus");
        button.setUIID("mtc_homemenuItemText");
        button.setScrollVisible(false);
        button.setFocusable(false);
        button.addActionListener(new 2());
        container5.add(button);
        if (!CihMBanking.sesPMTR.countMessage.equals("0") && !CihMBanking.sesPMTR.countMessage.equals("")) {
            container5.add(container6);
        }
        Button button2 = new Button();
        button2.setUIID("crd_btnCardImage");
        button2.setIcon(CihMBanking.detail_arrow);
        button2.setScrollVisible(false);
        button2.setFocusable(false);
        Button button3 = new Button();
        button3.setUIID("");
        button3.setIcon(this.uiManager.ressource.getImage("mailReceived.png"));
        button3.setScrollVisible(false);
        button3.setFocusable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(15);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(75);
        TableLayout.Constraint createConstraint3 = tableLayout.createConstraint();
        createConstraint3.setWidthPercentage(10);
        container4.addComponent(createConstraint, button3);
        container4.addComponent(createConstraint2, container5);
        container4.addComponent(createConstraint3, button2);
        container4.setLeadComponent(button);
        container2.addComponent(container4);
        container.addComponent(container2);
        Container container8 = new Container(new BoxLayout(2));
        container8.setUIID("gl_GloabalContainerV2NoPadMargin");
        container8.setScrollVisible(false);
        Component container9 = new Container(new BoxLayout(2));
        container9.setUIID("bf_cntMTCGreySeparator");
        container9.setScrollVisible(false);
        container8.addComponent(container9);
        TableLayout tableLayout2 = new TableLayout(1, 3);
        Container container10 = new Container();
        container10.setLayout(tableLayout2);
        container10.setUIID("mtc_CntTableLayoutN");
        container10.setFocusable(false);
        container10.setScrollable(false);
        Button button4 = new Button();
        button4.setUIID("");
        button4.setIcon(this.uiManager.ressource.getImage("sendMail.png"));
        button4.setScrollVisible(false);
        button4.setFocusable(false);
        Button button5 = new Button("Messages envoyés");
        button5.setUIID("mtc_homemenuItemText");
        button5.setScrollVisible(false);
        button5.setFocusable(false);
        button5.addActionListener(new 3());
        Button button6 = new Button();
        button6.setUIID("crd_btnCardImage");
        button6.setIcon(CihMBanking.detail_arrow);
        button6.setScrollVisible(false);
        button6.setFocusable(false);
        TableLayout.Constraint createConstraint4 = tableLayout.createConstraint();
        createConstraint4.setWidthPercentage(15);
        createConstraint4.setVerticalAlign(4);
        createConstraint4.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint5 = tableLayout.createConstraint();
        createConstraint5.setWidthPercentage(75);
        TableLayout.Constraint createConstraint6 = tableLayout.createConstraint();
        createConstraint6.setWidthPercentage(10);
        container10.addComponent(createConstraint4, button4);
        container10.addComponent(createConstraint5, button5);
        container10.addComponent(createConstraint6, button6);
        container10.setLeadComponent(button5);
        container8.addComponent(container10);
        container.addComponent(container8);
        Settings.setNightMode(container, 0);
        return container;
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                CihMBanking.sesPMTR.setIsFavoriteBack(0);
                CihMBanking.sesPMTR.IsReceivedMessage = true;
                CihMBanking.sesPMTR.countMessage = "";
                Messagerie1.this.uiManager.NavigateToPageById(94);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(Messagerie1.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                CihMBanking.sesPMTR.setIsFavoriteBack(1);
                CihMBanking.sesPMTR.IsReceivedMessage = false;
                Messagerie1.this.uiManager.NavigateToPageById(96);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(Messagerie1.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }
    }
}
