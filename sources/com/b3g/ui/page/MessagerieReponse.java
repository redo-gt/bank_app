package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.StringUtil;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MessagerieReponse extends B3GPage {
    UIBuilder uib = new UIBuilder();

    public MessagerieReponse(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setScrollVisible(false);
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "MessagerieReponse");
        Label label = (Label) uIBuilder.findByName("ObjetLbl", createContainer);
        Container container = (Container) uIBuilder.findByName("MessageCnt", createContainer);
        BrowserComponent browserComponent = new BrowserComponent();
        CihMBanking.sesPMTR.MessageBody.Body = StringUtil.replaceAll(CihMBanking.sesPMTR.MessageBody.Body, "<p class=\"MsoNormal\"><b><span style=\"font-size:10.0pt;font-family:&quot;Tahoma&quot;,&quot;sans-serif&quot;\">De&nbsp;:</span></b><span style=\"font-size:10.0pt;font-family:&quot;Tahoma&quot;,&quot;sans-serif&quot;\"> " + CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical + "@mailcihonline.ma [mailto:" + CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical + "@mailcihonline.ma]", "");
        browserComponent.setPage(CihMBanking.sesPMTR.MessageBody.Body, "");
        container.add(browserComponent);
        container.setScrollVisible(false);
        browserComponent.setScrollVisible(false);
        label.setText(CihMBanking.sesPMTR.MessageBody.subject);
        ((Label) uIBuilder.findByName("MessageDate", createContainer)).setText(CihMBanking.sesPMTR.MessageBody.date);
        Button button = (Button) uIBuilder.findByName("RepondreBtn", createContainer);
        button.addActionListener(new 1());
        this.thisContainer.setPreferredH(createContainer.getPreferredH());
        this.thisContainer.setPreferredW(createContainer.getPreferredW());
        this.thisContainer.add(createContainer);
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                CihMBanking.sesPMTR.IsMessageResponse = true;
                MessagerieReponse.this.uiManager.NavigateToPageById(97);
            }
        }
    }
}
