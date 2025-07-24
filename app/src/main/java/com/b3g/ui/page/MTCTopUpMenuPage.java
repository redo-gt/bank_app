package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MTCTopUpMenuPage extends B3GPage {
    public MTCTopUpMenuPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        Label label = new Label("Recharges");
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        label.setScrollVisible(false);
        label.setEnabled(false);
        this.thisContainer.addComponent(label);
        this.thisContainer.addComponent(DrawMTCHomeMenuV2());
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public Container DrawMTCHomeMenuV2() {
        Container container = new Container(new BoxLayout(2));
        container.setScrollVisible(false);
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            SpanLabel spanLabel = new SpanLabel("Ce service n'est pas disponible en mode démonstration.");
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container.addComponent(spanLabel);
            return container;
        }
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
        Button button = new Button("Liste des Opérateurs");
        button.setUIID("mtc_homemenuItemText");
        button.setScrollVisible(false);
        button.setFocusable(false);
        button.addActionListener(new 1());
        Button button2 = new Button();
        button2.setUIID("crd_btnCardImage");
        button2.setIcon(CihMBanking.detail_arrow);
        button2.setScrollVisible(false);
        button2.setFocusable(false);
        Button button3 = new Button();
        button3.setUIID("");
        button3.setIcon(this.uiManager.ressource.getImage("mtcPaiement_w.png"));
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
        container4.addComponent(createConstraint2, button);
        container4.addComponent(createConstraint3, button2);
        container4.setLeadComponent(button);
        container2.addComponent(container4);
        container.addComponent(container2);
        Container container5 = new Container(new BoxLayout(2));
        container5.setUIID("gl_GloabalContainerV2NoPadMargin");
        container5.setScrollVisible(false);
        Component container6 = new Container(new BoxLayout(2));
        container6.setUIID("bf_cntMTCGreySeparator");
        container6.setScrollVisible(false);
        container5.addComponent(container6);
        TableLayout tableLayout2 = new TableLayout(1, 3);
        Container container7 = new Container();
        container7.setLayout(tableLayout2);
        container7.setUIID("mtc_CntTableLayoutN");
        container7.setFocusable(false);
        container7.setScrollable(false);
        Button button4 = new Button();
        button4.setUIID("");
        button4.setIcon(this.uiManager.ressource.getImage("icone_favoris.png"));
        button4.setScrollVisible(false);
        button4.setFocusable(false);
        Button button5 = new Button("Mes Favoris");
        button5.setUIID("mtc_homemenuItemText");
        button5.setScrollVisible(false);
        button5.setFocusable(false);
        button5.addActionListener(new 2());
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
        container7.addComponent(createConstraint4, button4);
        container7.addComponent(createConstraint5, button5);
        container7.addComponent(createConstraint6, button6);
        container7.setLeadComponent(button5);
        container5.addComponent(container7);
        container.addComponent(container5);
        Container container8 = new Container(new BoxLayout(2));
        container8.setUIID("gl_GloabalContainerV2NoPadMargin");
        container8.setScrollVisible(false);
        Component container9 = new Container(new BoxLayout(2));
        container9.setUIID("bf_cntMTCGreySeparator");
        container9.setScrollVisible(false);
        container8.addComponent(container9);
        TableLayout tableLayout3 = new TableLayout(1, 3);
        Container container10 = new Container();
        container10.setLayout(tableLayout3);
        container10.setUIID("mtc_CntTableLayoutN");
        container10.setFocusable(false);
        container10.setScrollable(false);
        Button button7 = new Button();
        button7.setUIID("");
        button7.setIcon(this.uiManager.ressource.getImage("icone_historique.png"));
        button7.setScrollVisible(false);
        button7.setFocusable(false);
        Button button8 = new Button("Historique de Recharges");
        button8.setUIID("mtc_homemenuItemText");
        button8.setScrollVisible(false);
        button8.setFocusable(false);
        button8.addActionListener(new 3());
        Button button9 = new Button();
        button9.setUIID("crd_btnCardImage");
        button9.setIcon(CihMBanking.detail_arrow);
        button9.setScrollVisible(false);
        button9.setFocusable(false);
        TableLayout.Constraint createConstraint7 = tableLayout.createConstraint();
        createConstraint7.setWidthPercentage(15);
        createConstraint7.setVerticalAlign(4);
        createConstraint7.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint8 = tableLayout.createConstraint();
        createConstraint8.setWidthPercentage(75);
        TableLayout.Constraint createConstraint9 = tableLayout.createConstraint();
        createConstraint9.setWidthPercentage(10);
        container10.addComponent(createConstraint7, button7);
        container10.addComponent(createConstraint8, button8);
        container10.addComponent(createConstraint9, button9);
        container10.setLeadComponent(button8);
        container8.addComponent(container10);
        container.addComponent(container8);
        Settings.setNightMode(container, 0);
        Settings.setNightMode(container10, 0);
        return container;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.setIsFavoriteBack(0);
            MTCTopUpMenuPage.this.uiManager.NavigateToPageById(30);
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.setIsFavoriteBack(1);
            MTCTopUpMenuPage.this.uiManager.NavigateToPageById(29);
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            MTCTopUpMenuPage.this.uiManager.NavigateToPageById(27);
        }
    }
}
