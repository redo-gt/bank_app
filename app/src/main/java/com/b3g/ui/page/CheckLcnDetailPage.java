package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.MyCheckLcn;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CheckLcnDetailPage extends B3GPage {
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    UIBuilder uib = new UIBuilder();

    static /* synthetic */ String access$000(CheckLcnDetailPage checkLcnDetailPage) {
        return checkLcnDetailPage.VnewaliasText;
    }

    static /* synthetic */ String access$002(CheckLcnDetailPage checkLcnDetailPage, String str) {
        checkLcnDetailPage.VnewaliasText = str;
        return str;
    }

    public CheckLcnDetailPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            this.thisContainer.addComponent(GetTransferSecurityForm(this.thisContainer, (MyCheckLcn) this.service));
            Container container = new Container();
            container.setUIID("g_CntTranspMsg");
            this.thisContainer.addComponent(container);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    public Container GetTransferSecurityForm(Container container, MyCheckLcn myCheckLcn) {
        Container container2 = new Container(BoxLayout.y());
        Container container3 = new Container(BoxLayout.y());
        Label label = new Label("Service sélectionné : ");
        label.setUIID("g_lblDashBoardTitleOrange");
        Label label2 = new Label("Envoi d'image par email");
        label2.setUIID("ad_lblValueBlue");
        Container container4 = new Container(BoxLayout.x());
        container4.add(label);
        container4.add(label2);
        Container container5 = new Container(BoxLayout.y());
        container5.setUIID("g_cntBorderV2");
        container3.add(container4);
        container3.add(container5);
        container2.add(container3);
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container6 = (Container) this.uib.findByName("Container17", createContainer);
        container6.removeAll();
        container6.setLayout(BoxLayout.y());
        container6.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 1());
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 2(b3GCIHEcode, myCheckLcn));
        container2.add(createContainer);
        return container2;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CheckLcnDetailPage.this.uiManager.GoBack();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;
        final /* synthetic */ MyCheckLcn val$pMyCheckLcn;

        2(B3GCIHEcode b3GCIHEcode, MyCheckLcn myCheckLcn) {
            this.val$eCode1 = b3GCIHEcode;
            this.val$pMyCheckLcn = myCheckLcn;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CheckLcnDetailPage.access$002(CheckLcnDetailPage.this, this.val$eCode1.getValue());
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogSendCheckLcnService(64, this.val$pMyCheckLcn, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 64, CheckLcnDetailPage.access$000(CheckLcnDetailPage.this));
        }
    }
}
