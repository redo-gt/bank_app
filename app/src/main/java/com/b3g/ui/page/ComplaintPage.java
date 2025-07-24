package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Notice;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GRadio;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ComplaintPage extends B3GPage {
    public ComplaintPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "ProfileItemV2Win");
        this.uiManager.stateMachine.findLblProfileV2Name(createContainer).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom);
        this.uiManager.stateMachine.findLblProfilePhoneNumber(createContainer).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm);
        this.uiManager.stateMachine.findLblEmail(createContainer).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email);
        this.thisContainer.addComponent(createContainer);
        try {
            Notice notice = new Notice();
            Container createContainer2 = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "GloabalContainerV2");
            this.uiManager.stateMachine.findLblGlobalHeaderTitle(createContainer2).setText("Réclamation");
            createContainer2.getStyle().setPaddingUnit(1, 1, 1, 1);
            createContainer2.getStyle().setPadding(0, 2, 1, 1);
            Container container = new Container();
            Label label = new Label("Catégorie");
            label.setUIID("g_lblNotif");
            createContainer2.addComponent(label);
            ComboBox comboBox = new ComboBox();
            comboBox.addItem("Compte");
            comboBox.addItem("Engagement");
            comboBox.addItem("Moyens de paiement");
            comboBox.addItem("Monétique");
            comboBox.addItem("Services");
            comboBox.addItem("Bancassurance");
            comboBox.addItem("Activité du marché");
            comboBox.addItem("Opérations à l'international");
            comboBox.addItem("Données client et courrier");
            comboBox.addItem("Tarification");
            comboBox.addItem("Automate Dépôt de Cash");
            comboBox.addItem("Automate Dépôt de chèque");
            comboBox.addItem("Autre");
            comboBox.setUIID("listBox");
            createContainer2.addComponent(comboBox);
            container.setUIID("g_cntBorderV2");
            createContainer2.addComponent(container);
            Label label2 = new Label("Canal de réponse");
            label2.setUIID("g_lblNotif");
            createContainer2.addComponent(label2);
            B3GRadio b3GRadio = new B3GRadio("Canal");
            b3GRadio.setUIID("listRadio");
            b3GRadio.addItem("Email");
            b3GRadio.addItem("Courrier");
            b3GRadio.addItem("Sms");
            createContainer2.addComponent(b3GRadio.GetContainer());
            Container container2 = new Container();
            container2.setUIID("g_cntBorderV2");
            createContainer2.addComponent(container2);
            Label label3 = new Label("Votre Réclamation");
            label3.setUIID("g_lblNotif");
            createContainer2.addComponent(label3);
            TextArea textArea = new TextArea();
            textArea.setRows(8);
            textArea.setMaxSize(512);
            textArea.setUIID("listBox");
            createContainer2.addComponent(textArea);
            Container container3 = new Container();
            container3.setUIID("g_cntBorderV2");
            createContainer2.addComponent(container3);
            Button button = new Button("Valider");
            textArea.addDataChangedListener(new ComplaintPage$$ExternalSyntheticLambda0(textArea, createContainer2));
            button.addActionListener(new 1(textArea, notice, comboBox, b3GRadio));
            button.setUIID("op_BtnOppositionValidation");
            if (CihMBanking.sesPMTR.getIsDemo() == 1) {
                button.setEnabled(false);
            }
            createContainer2.addComponent(button);
            this.thisContainer.addComponent(createContainer2);
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
    }

    static /* synthetic */ void lambda$GetPageContainer$0(TextArea textArea, Container container, int i, int i2) {
        if (DataTools.ignoreCaracSpc(textArea.getText())) {
            textArea.putClientProperty("LastValidComplainPag", textArea.getText());
        } else {
            textArea.stopEditing();
            textArea.setText((String) textArea.getClientProperty("LastValidComplainPag"));
            textArea.startEditingAsync();
        }
        container.revalidate();
    }

    class 1 implements ActionListener {
        final /* synthetic */ B3GRadio val$cbCanal;
        final /* synthetic */ ComboBox val$cbCat;
        final /* synthetic */ Notice val$notMng;
        final /* synthetic */ TextArea val$txtMsg;

        1(TextArea textArea, Notice notice, ComboBox comboBox, B3GRadio b3GRadio) {
            this.val$txtMsg = textArea;
            this.val$notMng = notice;
            this.val$cbCat = comboBox;
            this.val$cbCanal = b3GRadio;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$txtMsg.getText().length() == 0) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Veuillez saisir votre message", null);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(this.val$notMng.ComplainProcess(this.val$cbCat.getSelectedItem().toString(), this.val$txtMsg.getText(), Display.getInstance().getPlatformName(), CihMBanking.sesPMTR.getAppVersion(), this.val$cbCanal.GetSelectedText())), null);
            }
            this.val$txtMsg.setText("");
        }
    }
}
