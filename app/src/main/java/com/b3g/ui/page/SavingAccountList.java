package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.SavingAccount;
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
import com.codename1.ui.layouts.FlowLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SavingAccountList extends B3GPage {
    public SavingAccountList(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            ArrayList SavingAccountProcess = new SavingAccount().SavingAccountProcess(27);
            if (SavingAccountProcess.size() > 0) {
                this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Comptes d'épargne", Boolean.TRUE, SavingAccountProcess, SavingAccountProcess.size(), 20, "Vous n'avez aucun compte d'épargne", null, null, null, null));
            } else {
                Container container = new Container(new BoxLayout(2));
                container.setUIID("");
                Container container2 = new Container(new BoxLayout(2));
                Component container3 = new Container(new BoxLayout(2));
                Container container4 = new Container(new BoxLayout(2));
                Container container5 = new Container(new BoxLayout(2));
                Label label = new Label("Comptes d'épargnes");
                label.setUIID("gb_lblGlobalHeaderTitleV2");
                container2.add(label);
                container3.setUIID("bf_cntBeneficarySeparator");
                SpanLabel spanLabel = new SpanLabel();
                spanLabel.setText("Vous n'avez aucun compte d'épargne");
                spanLabel.setTextUIID("LblProfileSmalGreyEprnCenter");
                spanLabel.setTextPosition(4);
                container4.add(spanLabel);
                Container container6 = new Container(new FlowLayout(4, 4));
                container6.setUIID("");
                Button button = new Button();
                button.setUIID("OrgLabel");
                button.setText("Ouvrir un compte d'Epargne");
                button.addActionListener(new 1());
                container6.add(button);
                container6.setUIID(" margin_1_1_1_1");
                container5.add(container6);
                container.add(container2).add(container3).add(container4).add(container5);
                container.getAllStyles().setBgColor(16777215);
                this.thisContainer.add(container);
            }
            Container container7 = new Container();
            container7.setUIID("g_CntTranspMsg");
            this.thisContainer.addComponent(container7);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            SavingAccountList.this.uiManager.NavigateToPageById(122);
        }
    }
}
