package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Messagerie;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.StringUtil;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MessageNew extends B3GPage {
    public B3GUiManager uiManager;
    UIBuilder uib = new UIBuilder();
    Messagerie M = new Messagerie();

    static /* synthetic */ String access$000(MessageNew messageNew) {
        return messageNew.cord();
    }

    public MessageNew(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.setScrollableY(true);
        this.thisContainer.setScrollVisible(false);
        Container createContainer = this.uib.createContainer("/cihv3", "MessageNew");
        TextField textField = (TextField) this.uib.findByName("ObjetTextField", createContainer);
        Container container = (Container) this.uib.findByName("Container9", createContainer);
        TextArea textArea = (TextArea) this.uib.findByName("MessageArea", createContainer);
        SpanLabel spanLabel = (SpanLabel) this.uib.findByName("CrdltLbl", createContainer);
        spanLabel.setText(cord() + "\n" + CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom);
        if (CihMBanking.sesPMTR.IsMessageResponse) {
            textField.setText("Objet");
        }
        ((Button) this.uib.findByName("Button1", createContainer)).addActionListener(new 1(textField, textArea));
        this.thisContainer.add(createContainer);
        Settings.setNightMode(container, 0);
        Settings.setNightMode(createContainer, 0);
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ TextArea val$MessageArea;
        final /* synthetic */ TextField val$ObjetTxt;

        1(TextField textField, TextArea textArea) {
            this.val$ObjetTxt = textField;
            this.val$MessageArea = textArea;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                MessageNew.this.M.subject = this.val$ObjetTxt.getText();
                MessageNew.this.M.Body = StringUtil.replaceAll(this.val$MessageArea.getText() + "\n" + MessageNew.access$000(MessageNew.this) + "\n" + CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, "\n", "<br />");
                ServiceResponse serviceResponse = new ServiceResponse();
                if (!CihMBanking.sesPMTR.IsMessageResponse) {
                    if (!MessageNew.this.M.Body.equals("") && !MessageNew.this.M.subject.equals("")) {
                        serviceResponse = MessageNew.this.M.MessageSendNewProcess();
                    } else if (MessageNew.this.M.Body.equals("") && MessageNew.this.M.subject.equals("")) {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MessageNew.this.uiManager.stateMachine, "Veuillez saisir l'objet et le contenu du message.", null);
                    } else if (MessageNew.this.M.Body.equals("") && !MessageNew.this.M.subject.equals("")) {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MessageNew.this.uiManager.stateMachine, "Veuillez saisir le contenu du message.", null);
                    } else if (!MessageNew.this.M.Body.equals("") && MessageNew.this.M.subject.equals("")) {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MessageNew.this.uiManager.stateMachine, "Veuillez saisir l'objet du message.", null);
                    }
                } else {
                    serviceResponse = MessageNew.this.M.MessagerieReplyProcess();
                }
                if (serviceResponse.getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MessageNew.this.uiManager.stateMachine, "Message envoyé avec succés .", null);
                } else {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MessageNew.this.uiManager.stateMachine, "Une erreur est survenue .Veuillez réessayer plus tard ", null);
                }
                MessageNew.this.uiManager.NavigateToPageById(91);
                MessageNew.this.thisContainer.revalidate();
            }
        }
    }

    private String cord() {
        String str = Preferences.get("CihMobileLang", "fr");
        str.hashCode();
        switch (str) {
            case "العربية":
            case "ar":
            case "Arabe":
                return "مع تحياتي";
            case "english":
            case "en":
            case "English":
            case "Anglais":
                return "Regards";
            case "español":
            case "es":
            case "Español":
                return "Atentamente";
            default:
                return "Cordialement";
        }
    }
}
