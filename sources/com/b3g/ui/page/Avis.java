package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Notice;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UITimer;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Avis extends B3GPage {
    public Avis(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        Notice notice = new Notice();
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "ProfileItemV2Win");
        this.uiManager.stateMachine.findLblProfileV2Name(createContainer).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom);
        this.uiManager.stateMachine.findLblProfilePhoneNumber(createContainer).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm);
        this.uiManager.stateMachine.findLblEmail(createContainer).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email);
        this.thisContainer.addComponent(createContainer);
        try {
            Container createContainer2 = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "GloabalContainerV2");
            this.uiManager.stateMachine.findLblGlobalHeaderTitle(createContainer2).setText("Avis");
            createContainer2.getStyle().setPaddingUnit(1, 1, 1, 1);
            createContainer2.getStyle().setPadding(0, 2, 1, 1);
            createContainer2.setScrollableY(false);
            SpanLabel spanLabel = new SpanLabel("Merci de nous donner votre avis sur CIH Mobile V4");
            spanLabel.setUIID("g_lblNotif");
            createContainer2.addComponent(spanLabel);
            Label label = new Label("Votre Avis");
            label.setUIID("g_lblNotifBlue");
            createContainer2.addComponent(label);
            TextArea textArea = new TextArea();
            textArea.setScrollVisible(false);
            textArea.setRows(10);
            textArea.setMaxSize(512);
            textArea.setUIID("listBox");
            createContainer2.addComponent(textArea);
            Container container = new Container();
            container.setUIID("g_cntBorderV2");
            createContainer2.addComponent(container);
            Button button = new Button("Valider");
            button.addActionListener(new 1(textArea, notice));
            button.setUIID("op_BtnOppositionValidation");
            if (CihMBanking.sesPMTR.getIsDemo() == 1) {
                button.setEnabled(false);
            }
            createContainer2.addComponent(button);
            this.thisContainer.addComponent(createContainer2);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Notice val$notMng;
        final /* synthetic */ TextArea val$txtMsg;

        1(TextArea textArea, Notice notice) {
            this.val$txtMsg = textArea;
            this.val$notMng = notice;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$txtMsg.getText().length() == 0) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Veuillez saisir votre message", null);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(this.val$notMng.NoticeProcess(this.val$txtMsg.getText(), Display.getInstance().getPlatformName(), Display.getInstance().getProperty("Platform", "Inconnu"))), null);
            }
            this.val$txtMsg.setText("");
            Display.getInstance().callSerially(new 1());
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new Avis$1$1$$ExternalSyntheticLambda0()).schedule(300, false, CihMBanking.sesPMTR.getCurrentUiManager().currentForm);
            }

            static /* synthetic */ void lambda$run$0() {
                CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }
    }
}
