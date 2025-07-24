package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Messagerie;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MessageReplyPage extends B3GPage {
    public B3GUiManager uiManager;
    UIBuilder uib = new UIBuilder();
    Messagerie M = new Messagerie();

    public MessageReplyPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setScrollableY(true);
        this.thisContainer.setScrollVisible(false);
        Container createContainer = this.uib.createContainer("/cihv3", "MsgReply");
        Label label = (Label) this.uib.findByName("ObjLbl", createContainer);
        TextArea textArea = (TextArea) this.uib.findByName("MessageArea", createContainer);
        label.setText(CihMBanking.sesPMTR.MessageBody.subject);
        ((Button) this.uib.findByName("Button1", createContainer)).addActionListener(new 1(label, textArea));
        this.thisContainer.add(createContainer);
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ TextArea val$MessageArea;
        final /* synthetic */ Label val$ObjetTxt;

        1(Label label, TextArea textArea) {
            this.val$ObjetTxt = label;
            this.val$MessageArea = textArea;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            MessageReplyPage.this.M.subject = this.val$ObjetTxt.getText();
            MessageReplyPage.this.M.Body = this.val$MessageArea.getText();
            new ServiceResponse();
            if (MessageReplyPage.this.M.MessagerieReplyProcess().getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MessageReplyPage.this.uiManager.stateMachine, "Message envoyé avec succés.", null);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MessageReplyPage.this.uiManager.stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard.", null);
            }
            MessageReplyPage.this.uiManager.NavigateToPageById(91);
            MessageReplyPage.this.thisContainer.revalidate();
        }
    }
}
