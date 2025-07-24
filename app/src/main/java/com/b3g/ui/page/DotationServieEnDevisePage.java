package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Dotation;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DotationServieEnDevisePage extends B3GPage {
    public DotationServieEnDevisePage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.setScrollableY(true);
        UIBuilder uIBuilder = new UIBuilder();
        try {
            Container createContainer = uIBuilder.createContainer("/cihv3", "dotationServieEnDevise");
            Label label = (Label) uIBuilder.findByName("totauxLbl", createContainer);
            Label label2 = (Label) uIBuilder.findByName("totauxCihLbl", createContainer);
            Label label3 = (Label) uIBuilder.findByName("totauxAUtresLbl", createContainer);
            Dotation dotation = (Dotation) this.service;
            label.setText(dotation.dotationServieDeviseValue + " DHS");
            label2.setText(dotation.TotalCIH + " DHS");
            label3.setText(dotation.TotalAutre + " DHS");
            ((Button) uIBuilder.findByName("detailBtn", createContainer)).addActionListener(new 1(dotation));
            this.thisContainer.add(createContainer);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            return this.thisContainer;
        }
    }

    class 1 implements ActionListener {
        final /* synthetic */ Dotation val$pDotation;

        1(Dotation dotation) {
            this.val$pDotation = dotation;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DotationServieEnDevisePage.this.uiManager.NavigateToPage(new DotationDetailEchanges(DotationServieEnDevisePage.this.uiManager, this.val$pDotation, 67));
        }
    }
}
