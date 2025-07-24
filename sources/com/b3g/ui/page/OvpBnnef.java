package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.TransfertDATA;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OvpBnnef extends B3GPage {
    public OvpBnnef(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        TransfertDATA transfertDATA = (TransfertDATA) this.service;
        Container container = new Container(new BorderLayout());
        container.setUIID("mn_cntMenuItem");
        Container container2 = new Container(new BoxLayout(2));
        new DefaultListModel();
        TextField textField = new TextField();
        textField.setHint("recherche");
        String str = "padding_1_1_1_1";
        textField.setUIID("padding_1_1_1_1");
        int i = 1;
        textField.setAlignment(1);
        Container container3 = new Container(new BoxLayout(2));
        container3.setUIID("padding_2_2_2_2");
        Container container4 = new Container(new BoxLayout(2));
        container4.setUIID("border_grey_padding");
        container4.add(textField);
        container3.add(container4);
        Container container5 = new Container(new BoxLayout(2));
        int i2 = 0;
        while (i2 < transfertDATA.getBeneficiaryList().size()) {
            Beneficiary beneficiary = (Beneficiary) transfertDATA.getBeneficiaryList().get(i2);
            Container container6 = new Container(new BorderLayout());
            container6.setUIID("margin_1_0_1_1");
            Container container7 = new Container(new GridLayout(i, i));
            Container container8 = new Container(new LayeredLayout());
            Container container9 = new Container();
            container9.setUIID("OvpBankLogo");
            container8.add(container9);
            Button button = new Button();
            button.setUIID("OvpBankLogo");
            Container container10 = container2;
            String str2 = str;
            if (((Beneficiary) transfertDATA.getBeneficiaryList().get(i2)).cardNumber.substring(0, 2).equals("23")) {
                button.setIcon(CihMBanking.theme.getImage("CihTransfertLogo.png"));
            } else {
                button.setIcon(CihMBanking.theme.getImage("Museum.png"));
            }
            button.setPreferredW((Display.getInstance().getDisplayWidth() * 7) / 100);
            container8.add(button);
            Container container11 = new Container(new BoxLayout(2));
            Label label = new Label(((Beneficiary) transfertDATA.getBeneficiaryList().get(i2)).alias);
            label.setUIID("g_lblDashBoardTitleGREY");
            Label label2 = new Label(((Beneficiary) transfertDATA.getBeneficiaryList().get(i2)).cardNumber);
            label2.setUIID("g_lblDashBoardValueBlue");
            container11.add(label).add(label2);
            container6.add("Center", container11);
            container7.add(container8);
            container6.add("West", container7);
            Button button2 = new Button();
            button2.setUIID("");
            button2.addActionListener(new 1(beneficiary));
            container6.add("East", button2);
            container6.setLeadComponent(button2);
            container5.add(container6);
            i2++;
            str = str2;
            container2 = container10;
            i = 1;
        }
        Container container12 = container2;
        textField.addDataChangeListener(new OvpBnnef$$ExternalSyntheticLambda0(transfertDATA, container5, textField));
        container.add("North", container3);
        container12.add(container5);
        Container container13 = new Container(new BoxLayout(2));
        container13.setUIID(str);
        container.add("Center", container12);
        container12.setPreferredH((Display.getInstance().getDisplayHeight() * 80) / 100);
        container12.setScrollableY(true);
        container12.setScrollVisible(true);
        Button button3 = new Button(" Ajouter un Bénéficiaire");
        button3.setIcon(this.uiManager.ressource.getImage("addBeneficery.png"));
        button3.setUIID("op_BtnOppositionValidationV2");
        container13.add(button3);
        container.add("South", container13);
        button3.addActionListener(new 2());
        this.thisContainer = container;
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Beneficiary val$B;

        1(Beneficiary beneficiary) {
            this.val$B = beneficiary;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AddOvp addOvp = new AddOvp(OvpBnnef.this.uiManager, this.val$B, 125);
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                OvpBnnef.this.uiManager.NavigateToPage(addOvp);
            }
        }
    }

    static /* synthetic */ void lambda$GetPageContainer$0(TransfertDATA transfertDATA, Container container, TextField textField, int i, int i2) {
        for (int i3 = 0; i3 < transfertDATA.getBeneficiaryList().size(); i3++) {
            Container container2 = (Container) container.getComponentAt(i3);
            if (!((Label) ((Container) container2.getComponentAt(0)).getComponentAt(0)).getText().toLowerCase().contains(textField.getText().toLowerCase())) {
                container2.setHidden(true);
                container2.getParent().revalidate();
            } else {
                container2.setHidden(false);
                container2.getParent().revalidate();
            }
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                OvpBnnef.this.uiManager.NavigateToPageById(72);
            }
        }
    }
}
