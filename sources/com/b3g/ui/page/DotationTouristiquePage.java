package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Dotation;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DotationTouristiquePage extends B3GPage {
    Dotation pGetDotation;

    public DotationTouristiquePage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        ArrayList dotationProcess;
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.setScrollableY(true);
        UIBuilder uIBuilder = new UIBuilder();
        try {
            CihMBanking.sesPMTR.dotationCode = "300";
            this.pGetDotation = new Dotation();
            if (CihMBanking.sesPMTR.listDotationTouristique.size() > 0) {
                dotationProcess = CihMBanking.sesPMTR.listDotationTouristique;
            } else {
                dotationProcess = this.pGetDotation.getDotationProcess(CihMBanking.sesPMTR.dotationCode);
                CihMBanking.sesPMTR.listDotationTouristique = dotationProcess;
            }
            this.pGetDotation = (Dotation) dotationProcess.get(0);
            Container createContainer = uIBuilder.createContainer("/cihv3", "dotationTouristique");
            ((Label) uIBuilder.findByName("compteSoldeLbl", createContainer)).setText(this.pGetDotation.comptesValue + " MAD");
            ((Label) uIBuilder.findByName("restantLbl", createContainer)).setText(this.pGetDotation.restant + " MAD");
            ((Label) uIBuilder.findByName("plafond", createContainer)).setText(this.pGetDotation.PlafondDotation + " MAD");
            ((Label) uIBuilder.findByName("cartePrepaSoldeLbl", createContainer)).setText(this.pGetDotation.cartePrepaidValue + " MAD");
            ((Label) uIBuilder.findByName("dotationDeviseSoldeLbl", createContainer)).setText(this.pGetDotation.dotationServieDeviseValue + " MAD");
            ((Label) uIBuilder.findByName("UtiliEtrangerSoldeLbl", createContainer)).setText(this.pGetDotation.utilisationEtrangerValue + " MAD");
            ((Label) uIBuilder.findByName("rechargeCartePrepaSodeLbl", createContainer)).setText(this.pGetDotation.rechargeCartePrepaidValue + " MAD");
            ((Label) uIBuilder.findByName("dotationUilisableLbl", createContainer)).setText(this.pGetDotation.dotationUtilisableValue + " MAD");
            ((Label) uIBuilder.findByName("consomLbl", createContainer)).setText(this.pGetDotation.consommationValue + " MAD");
            Container container = (Container) uIBuilder.findByName("msgCnt", createContainer);
            Container container2 = (Container) uIBuilder.findByName("msgCnt1", createContainer);
            Label label = (Label) uIBuilder.findByName("etoileLbl", createContainer);
            Label label2 = (Label) uIBuilder.findByName("etoileLbl1", createContainer);
            SpanLabel spanLabel = (SpanLabel) uIBuilder.findByName("SpanLabelU", createContainer);
            SpanLabel spanLabel2 = (SpanLabel) uIBuilder.findByName("SpanLabel2", createContainer);
            SpanLabel spanLabel3 = (SpanLabel) uIBuilder.findByName("lblMotifTitle111", createContainer);
            String str = this.pGetDotation.year;
            String text = spanLabel.getText();
            String str2 = spanLabel2.getText() + str + ": ";
            spanLabel.setText(text + str + ": ");
            spanLabel2.setText(str2);
            spanLabel3.setText("La dotation restante pour " + str + " ne tient pas compte des opérations de change effectuées auprès d'autres guichets (Hors CIH BANK).");
            spanLabel3.setUIID("Container");
            if (!this.pGetDotation.TotalAutre.equals("--")) {
                container2.setHidden(true);
                label2.setHidden(true);
                createContainer.revalidate();
            }
            if (this.pGetDotation.EndowmentActivated) {
                container.setHidden(true);
                label.setHidden(true);
                createContainer.revalidate();
            }
            Button button = (Button) uIBuilder.findByName("cartePrepaBtn", createContainer);
            button.setIcon(CihMBanking.blueArrow);
            button.addActionListener(new 1());
            if (this.pGetDotation.cartePrepaidValue.equals("0")) {
                button.setHidden(true);
                button.setEnabled(false);
                this.thisContainer.revalidate();
            }
            Button button2 = (Button) uIBuilder.findByName("dotationDeviseBtn", createContainer);
            button2.setIcon(CihMBanking.blueArrow);
            button2.addActionListener(new 2());
            if (this.pGetDotation.dotationServieDeviseValue.equals("0")) {
                button2.setHidden(true);
                button2.setEnabled(false);
                this.thisContainer.revalidate();
            }
            Button button3 = (Button) uIBuilder.findByName("utilisaEtranBtn", createContainer);
            button3.setIcon(CihMBanking.blueArrow);
            button3.addActionListener(new 3());
            if (this.pGetDotation.utilisationEtrangerValue.equals("0")) {
                button3.setHidden(true);
                button3.setEnabled(false);
                this.thisContainer.revalidate();
            }
            Button button4 = (Button) uIBuilder.findByName("rechargeCartePRepaBtn", createContainer);
            button4.setIcon(CihMBanking.blueArrow);
            button4.addActionListener(new 4());
            if (this.pGetDotation.rechargeCartePrepaidValue.equals("0")) {
                button4.setHidden(true);
                button4.setEnabled(false);
                this.thisContainer.revalidate();
            }
            this.thisContainer.add(createContainer);
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DotationTouristiquePage.this.uiManager.NavigateToPage(new DotationCartePrepayeesPage(DotationTouristiquePage.this.uiManager, DotationTouristiquePage.this.pGetDotation, 65));
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DotationTouristiquePage.this.uiManager.NavigateToPage(new DotationServieEnDevisePage(DotationTouristiquePage.this.uiManager, DotationTouristiquePage.this.pGetDotation, 66));
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DotationTouristiquePage.this.uiManager.NavigateToPage(new DotationUtilisationEtranger(DotationTouristiquePage.this.uiManager, DotationTouristiquePage.this.pGetDotation, 68));
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DotationTouristiquePage.this.uiManager.NavigateToPage(new DotationRechargeCartePrepaid(DotationTouristiquePage.this.uiManager, DotationTouristiquePage.this.pGetDotation, 69));
        }
    }
}
