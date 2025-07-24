package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordion;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SOSAssistance extends B3GPage {
    int cntTrackStep1Height;
    Boolean isDrawed;
    public B3GUiManager uiManager;
    UIBuilder uib = new UIBuilder();
    int CountList = 0;

    public SOSAssistance(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        this.isDrawed = false;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new LayeredLayout());
        this.thisContainer.setUIID("mn_cntMenuItem");
        Container container = new Container(BoxLayout.y());
        Label label = new Label("SOS Assistance");
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        label.setScrollVisible(false);
        label.setEnabled(false);
        container.addComponent(label);
        container.addComponent(DrawSinistreHomeMenuV2(container));
        this.thisContainer.add(container);
        return this.thisContainer;
    }

    private Component DrawSinistreHomeMenuV2(Container container) {
        Container container2 = new Container(new BoxLayout(2));
        Container container3 = new Container(new BorderLayout());
        SpanLabel spanLabel = new SpanLabel("Si vous rencontrez des difficultés pour activer votre demande d’assistance en ligne, merci d’appeler directement le 05 22 97 47 47");
        spanLabel.setTextUIID("mtc_homemenuItemText");
        container3.getAllStyles().setMargin(2, 0, 0, 0);
        container2.setUIID("");
        container2.setScrollVisible(false);
        Container container4 = new Container(new BoxLayout(2));
        container4.setUIID("gl_GloabalContainerV2NoPadMargin");
        container4.setScrollVisible(false);
        Component container5 = new Container(new BoxLayout(2));
        container5.setUIID("bf_cntBeneficarySeparator");
        container5.setScrollVisible(false);
        container4.addComponent(container5);
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container6 = new Container();
        container6.setLayout(tableLayout);
        container6.setUIID("mtc_CntTableLayoutN");
        container6.setFocusable(false);
        container6.setScrollable(false);
        Button button = new Button("Demande d'assistance");
        button.setUIID("mtc_homemenuItemText");
        button.setScrollVisible(false);
        button.setFocusable(false);
        button.addActionListener(new 1());
        Button button2 = new Button();
        button2.setUIID("crd_btnCardImage");
        button2.setIcon(this.uiManager.ressource.getImage("mtc_detail_arrow.png"));
        button2.setScrollVisible(false);
        button2.setFocusable(false);
        Button button3 = new Button();
        button3.setUIID("");
        button3.setIcon(this.uiManager.ressource.getImage("SOSDemande.png"));
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
        container6.addComponent(createConstraint, button3);
        container6.addComponent(createConstraint2, button);
        container6.addComponent(createConstraint3, button2);
        container6.setLeadComponent(button);
        container4.addComponent(container6);
        container2.addComponent(container4);
        container2.add(drawAccordion(drawMtcHeader("Suivi des demandes", "SOSuivi.png"), NewDemandeTest()));
        container3.add("Center", spanLabel);
        container2.add(container3);
        return container2;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            SOSAssistance.this.uiManager.NavigateToPageById(135);
        }
    }

    public Container drawMtcHeader(String str, String str2) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("gl_GloabalContainerV2NoPadMargin");
        container.setScrollVisible(false);
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container2 = new Container();
        container2.setLayout(tableLayout);
        container2.setUIID("mtc_CntTableLayoutN");
        container2.setFocusable(false);
        container2.setScrollable(false);
        Button button = new Button(str);
        button.setUIID("mtc_homemenuItemText");
        button.setScrollVisible(false);
        button.setFocusable(false);
        Button button2 = new Button();
        button2.setUIID("crd_btnCardImage");
        button2.setIcon(this.uiManager.ressource.getImage("mtc_detail_arrow.png"));
        button2.setScrollVisible(false);
        button2.setFocusable(false);
        Button button3 = new Button();
        button3.setUIID("");
        button3.setIcon(this.uiManager.ressource.getImage(str2));
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
        container2.addComponent(createConstraint, button3);
        container2.addComponent(createConstraint2, button);
        container2.addComponent(createConstraint3, button2);
        container.addComponent(container2);
        return container;
    }

    public B3gAccordion drawAccordion(Container container, Container container2) {
        B3gAccordion b3gAccordion = new B3gAccordion();
        b3gAccordion.setScrollableY(false);
        Image image = this.uiManager.ressource.getImage("mtc_detail_arrow.png");
        b3gAccordion.setCloseIcon(CihMBanking.detail_arrow);
        b3gAccordion.setOpenIcon(image);
        b3gAccordion.addContentPers(container, BoxLayout.encloseY(container2));
        b3gAccordion.revalidate();
        return b3gAccordion;
    }

    public Container NewDemandeTest() {
        Container container = new Container(new BorderLayout());
        container.setUIID("border_grey_1_1_1_1_background_grey");
        Label label = new Label("Suivi de la demande");
        label.setUIID("g_lblNotif_Bold");
        Container container2 = new Container(new BoxLayout(2));
        Label label2 = new Label(" ");
        label2.setUIID("g_lblNotifGren");
        Button button = new Button("Suivre");
        button.setUIID("SuivantSinistre_Copy");
        button.setPreferredH(button.getHeight() + 150);
        button.setPreferredW(button.getWidth() + 300);
        button.addActionListener(new 2());
        container2.add(label2);
        container2.add(button);
        container.add("West", label).add("East", container2);
        return container;
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            SOSAssistance.this.uiManager.NavigateToPageById(158);
        }
    }
}
