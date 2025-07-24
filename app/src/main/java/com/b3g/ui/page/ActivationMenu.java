package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ActivationMenu {
    private B3GUiManager uiManager;

    static /* synthetic */ B3GUiManager access$000(ActivationMenu activationMenu) {
        return activationMenu.uiManager;
    }

    public ActivationMenu(B3GUiManager b3GUiManager) {
        this.uiManager = b3GUiManager;
    }

    public ActivationMenu() {
    }

    public Form GetPageForm() {
        CihMBanking.exitApplication = true;
        Form form = (Form) this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "SubscriptionActivaitonForm");
        Label label = new Label("Nouvel utilisateur");
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        label.setScrollVisible(false);
        label.setEnabled(false);
        form.addComponent(label);
        form.addComponent(DrawActivationMenu());
        form.setBackCommand(new 1("Back"));
        return form;
    }

    class 1 extends Command {
        1(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ActivationMenu.access$000(ActivationMenu.this).stateMachine.showForm("LoginV2New", (Command) null);
        }
    }

    public Container DrawActivationMenu() {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("mn_cntMenuItem");
        container.setScrollVisible(false);
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("gl_GloabalContainerV2NoPadMargin");
        container2.setScrollVisible(false);
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container3 = new Container();
        container3.setLayout(tableLayout);
        container3.setUIID("mtc_CntTableLayoutN");
        container3.setFocusable(false);
        container3.setScrollable(false);
        Button button = new Button("Abonnez vous");
        button.setUIID("mtc_homemenuItemText");
        button.setScrollVisible(false);
        button.setFocusable(false);
        button.addActionListener(new 2());
        Button button2 = new Button();
        button2.setUIID("crd_btnCardImage");
        button2.setIcon(CihMBanking.detail_arrow);
        button2.setScrollVisible(false);
        button2.setFocusable(false);
        Button button3 = new Button();
        button3.setUIID("");
        button3.setIcon(this.uiManager.ressource.getImage("AbonnezVous_w.png"));
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
        container3.addComponent(createConstraint, button3);
        container3.addComponent(createConstraint2, button);
        container3.addComponent(createConstraint3, button2);
        container3.setLeadComponent(button);
        container2.addComponent(container3);
        container.addComponent(container2);
        Container container4 = new Container(new BoxLayout(2));
        container4.setUIID("gl_GloabalContainerV2NoPadMargin");
        container4.setScrollVisible(false);
        TableLayout tableLayout2 = new TableLayout(1, 3);
        Container container5 = new Container();
        container5.setLayout(tableLayout2);
        container5.setUIID("mtc_CntTableLayoutN");
        container5.setFocusable(false);
        container5.setScrollable(false);
        Button button4 = new Button();
        button4.setUIID("");
        button4.setIcon(this.uiManager.ressource.getImage("ActiverCompte_w.png"));
        button4.setScrollVisible(false);
        button4.setFocusable(false);
        Button button5 = new Button("Activez votre compte");
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
        container5.addComponent(createConstraint4, button4);
        container5.addComponent(createConstraint5, button5);
        container5.addComponent(createConstraint6, button6);
        container5.setLeadComponent(button5);
        container4.addComponent(container5);
        container.addComponent(container4);
        return container;
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ActivationMenu.access$000(ActivationMenu.this).stateMachine.showForm("HelpsSubscription", (Command) null);
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            new ActivationPage(ActivationMenu.access$000(ActivationMenu.this)).GetClientIdPhoneForm().show();
        }
    }
}
