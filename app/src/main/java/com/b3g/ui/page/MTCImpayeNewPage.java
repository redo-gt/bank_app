package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.MTCCreance;
import com.b3g.services.MTCImpaye;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MTCImpayeNewPage extends B3GPage {
    DataTools dt = new DataTools();

    static /* synthetic */ String access$000(MTCImpayeNewPage mTCImpayeNewPage) {
        return mTCImpayeNewPage.getToalFrais();
    }

    public MTCImpayeNewPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            CihMBanking.sesPMTR.setMontantReducToPass(0.0f);
            new Container(new BoxLayout(2)).setUIID("ad_CntAccountDetailsMain");
            this.thisContainer.addComponent(GetMTCImpayeContainer());
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    public Container GetMTCImpayeContainer() {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("Container");
        container.setEnabled(true);
        TableLayout tableLayout = new TableLayout(1, 2);
        Container container2 = new Container();
        container2.setLayout(tableLayout);
        container2.setUIID("Container");
        container2.setEnabled(true);
        TableLayout tableLayout2 = new TableLayout(1, 1);
        Container container3 = new Container();
        container3.setLayout(tableLayout2);
        container3.setUIID("MTCIconStyMTC");
        container3.setFocusable(false);
        container3.setScrollable(false);
        Button button = new Button();
        button.setUIID("crd_btnCardImage");
        button.setIcon(this.uiManager.ressource.getImage("black_pic.png"));
        button.setIcon(URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(button.getIcon().getWidth(), button.getIcon().getWidth(), 12569042), false), "Normal_https://www.cihnet.co.ma" + CihMBanking.sesPMTR.getSelectedMTCCreancier().LogoPath, "https://www.cihnet.co.ma" + CihMBanking.sesPMTR.getSelectedMTCCreancier().LogoPath, URLImage.RESIZE_SCALE));
        button.setScrollVisible(false);
        button.setFocusable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(100);
        createConstraint.setVerticalAlign(0);
        container3.addComponent(createConstraint, button);
        Container container4 = new Container(new BoxLayout(2));
        container4.setUIID("st_cntStatisticLeftBloc");
        container4.setFocusable(false);
        container4.setScrollVisible(false);
        Container container5 = new Container(new BoxLayout(2));
        container5.setUIID("g_cntDebitCredit");
        container5.setFocusable(false);
        container5.setScrollVisible(false);
        Label label = new Label(CihMBanking.sesPMTR.getSelectedMTCCreancier().NomCreancier);
        label.setUIID("g_lblDashBoardTitle");
        label.setFocusable(false);
        label.setScrollVisible(false);
        label.setVerticalAlignment(4);
        label.setTextPosition(1);
        Label label2 = new Label(CihMBanking.sesPMTR.getSelectedMTCCreancier().DescriptionCreancier);
        label2.setUIID("g_lblDashBoardValueBlue");
        label2.setFocusable(false);
        label2.setScrollVisible(false);
        label2.setVerticalAlignment(4);
        label2.setTextPosition(1);
        container5.addComponent(label);
        container5.addComponent(label2);
        container4.addComponent(container5);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint(0, 0);
        createConstraint2.setWidthPercentage(20);
        createConstraint2.setHorizontalAlign(4);
        container2.addComponent(createConstraint2, container3);
        TableLayout.Constraint createConstraint3 = tableLayout.createConstraint(0, 1);
        createConstraint3.setWidthPercentage(80);
        container2.addComponent(createConstraint3, container4);
        container.addComponent(container2);
        container.addComponent(GetCntBorderSeparator());
        container.addComponent(GetKeyValueData("Service sélectionné", CihMBanking.sesPMTR.getSelectedMTCCreance().NomCreance));
        container.addComponent(GetCntBorderSeparator());
        if (CihMBanking.sesPMTR.getCurrentMTCImpaye().size() > 0) {
            container.addComponent(GetKeyValueData(((MTCImpaye) CihMBanking.sesPMTR.getCurrentMTCImpaye().get(0)).IdentifiantValue, ((MTCImpaye) CihMBanking.sesPMTR.getCurrentMTCImpaye().get(0)).Identifiant));
        } else {
            container.addComponent(GetKeyValueData("Identifiant", "-"));
        }
        container.addComponent(GetCntBorderSeparator());
        container.addComponent(GetImpayeList(CihMBanking.sesPMTR.getCurrentMTCImpaye(), new Label("0.00"), "0.00"));
        container.revalidate();
        return container;
    }

    public Container GetImpayeList(ArrayList arrayList, Label label, String str) {
        BorderLayout borderLayout = new BorderLayout();
        Container container = new Container();
        container.setLayout(borderLayout);
        container.setUIID("Container");
        container.setEnabled(true);
        if (arrayList.size() > 0 && CihMBanking.sesPMTR.getIsNotaire().equals("True") && CihMBanking.sesPMTR.getSelectedMTCCreancier().CodeCreancier.equals("1025") && ((MTCImpaye) CihMBanking.sesPMTR.getCurrentMTCImpaye().get(0)).IdArticle.substring(0, 1).equals("7")) {
            MTCCreance mTCCreance = new MTCCreance();
            if (CihMBanking.sesPMTR.getMontantReduction() == 0.0f) {
                CihMBanking.sesPMTR.setMontantReduction(Float.parseFloat(mTCCreance.MTCFessProcess(CihMBanking.sesPMTR.getSelectedMTCCreancier().CodeCreancier, CihMBanking.sesPMTR.getSelectedMTCCreance().CodeCreance, getToalFrais())));
                CihMBanking.sesPMTR.setMontantReducToPass(CihMBanking.sesPMTR.getMontantReduction());
                CihMBanking.sesPMTR.isDGIWithNot = true;
            }
        }
        container.addComponent("Center", GetMTCImpayeListContainer(arrayList, label));
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("CntMTCF");
        container2.setScrollVisible(false);
        if (arrayList.size() > 0) {
            container2.addComponent(GetMTCResumeValueFlow(label));
            container2.addComponent(GetValidateButton(label, str));
        }
        container.addComponent("South", container2);
        return container;
    }

    public Container GetMTCImpayeListContainer(ArrayList arrayList, Label label) {
        ArrayList arrayList2 = new ArrayList();
        MTCImpaye mTCImpaye = new MTCImpaye();
        for (int i = 0; i < arrayList.size(); i++) {
            ((MTCImpaye) arrayList.get(i)).groupKey = ((MTCImpaye) arrayList.get(i)).DateFacture;
            arrayList2.add((B3gService) arrayList.get(i));
        }
        Container container = new Container(new BoxLayout(2));
        if (arrayList2.size() > 0) {
            this.uiManager.Draw_GroupBy(container, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 16, mTCImpaye, label);
        } else {
            container.addComponent(this.uiManager.GetCntMessage(CihMBanking.sesPMTR.getCurrentMTCImpayeListStatusLabel()));
        }
        return container;
    }

    public Container GetMTCResumeValue(Label label) {
        TableLayout tableLayout = new TableLayout(1, 1);
        Container container = new Container();
        container.setLayout(tableLayout);
        container.setUIID("crd_CntCardImage");
        container.setFocusable(false);
        container.setScrollable(false);
        Container container2 = new Container(new BoxLayout(1));
        container2.setUIID("mtc_CntimpayeValueResume");
        label.setUIID("g_lblNotifGrenPlafond");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        Label label2 = new Label("Montant Total : ");
        label2.setUIID("g_lblNotifPlafond");
        label2.setTextPosition(1);
        label2.setVerticalAlignment(4);
        Label label3 = new Label(" (DH)");
        label3.setUIID("g_lblNotifPlafond");
        label3.setTextPosition(1);
        label3.setVerticalAlignment(4);
        container2.addComponent(label2);
        container2.addComponent(label);
        container2.addComponent(label3);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(100);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(4);
        container.addComponent(createConstraint, container2);
        return container;
    }

    public Container GetMTCResumeValueFlow(Label label) {
        TableLayout tableLayout = new TableLayout(1, 1);
        Container container = new Container();
        container.setLayout(tableLayout);
        container.setUIID("crd_CntCardImage");
        container.setFocusable(false);
        container.setScrollable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(100);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(3);
        Container container2 = new Container(new BoxLayout(1));
        Container container3 = new Container(new BoxLayout(1));
        new Container(new BoxLayout(1));
        Container container4 = new Container(new BoxLayout(2));
        container4.setUIID("mtc_CntimpayeValueResume");
        label.setUIID("g_lblNotifGrenPlafond");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        Label label2 = new Label(getToalFrais());
        label2.setUIID("g_lblNotifGrenPlafond");
        label2.setTextPosition(1);
        label2.setVerticalAlignment(4);
        Label label3 = new Label("Total des Frais : ");
        label3.setUIID("g_lblNotifPlafond");
        label3.setTextPosition(1);
        label3.setVerticalAlignment(4);
        Label label4 = new Label(" (DH)");
        label4.setUIID("g_lblNotifPlafond");
        label4.setTextPosition(1);
        label4.setVerticalAlignment(4);
        Label label5 = new Label("Montant Total : ");
        label5.setUIID("g_lblNotifPlafond");
        label5.setTextPosition(1);
        label5.setVerticalAlignment(4);
        Label label6 = new Label(" (DH)");
        label6.setUIID("g_lblNotifPlafond");
        label6.setTextPosition(1);
        label6.setVerticalAlignment(4);
        container2.addComponent(label5);
        container2.addComponent(label);
        container2.addComponent(label6);
        container3.addComponent(label3);
        container3.addComponent(label2);
        container3.addComponent(label4);
        container4.addComponent(container3);
        container4.addComponent(container2);
        container.addComponent(createConstraint, container4);
        return container;
    }

    public Container GetCntBorderSeparator() {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("g_cntBorderV2");
        return container;
    }

    public Container GetKeyValueData(String str, String str2) {
        Container container = new Container(new BoxLayout(3));
        container.setFocusable(false);
        container.setScrollVisible(false);
        container.setUIID("MTCKeyValueLabel");
        Label label = new Label(str + " : ");
        label.setUIID("g_lblDashBoardTitleOrange");
        label.setFocusable(false);
        label.setScrollVisible(false);
        label.setVerticalAlignment(4);
        label.setTextPosition(1);
        Button button = new Button(str2);
        button.setUIID("ad_lblValueBlue");
        button.setFocusable(true);
        button.setScrollVisible(false);
        button.setVerticalAlignment(4);
        button.setTextPosition(1);
        container.addComponent(label);
        container.addComponent(button);
        return container;
    }

    public Container GetValidateButton(Label label, String str) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("ContainerMTCF");
        container.setFocusable(false);
        container.setScrollVisible(false);
        Button button = new Button("SUIVANT");
        button.setAlignment(4);
        button.setVerticalAlignment(4);
        button.setScrollVisible(false);
        button.setFocusable(false);
        button.setUIID("op_BtnOppositionValidation");
        button.addActionListener(new 1(label));
        container.addComponent(button);
        return container;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Label val$lblTotalImpaye;

        1(Label label) {
            this.val$lblTotalImpaye = label;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            MTCImpaye mTCImpaye = new MTCImpaye();
            String access$000 = MTCImpayeNewPage.access$000(MTCImpayeNewPage.this);
            mTCImpaye.PrixTTC = this.val$lblTotalImpaye.getText();
            if (this.val$lblTotalImpaye.getText().equals("0.0") || this.val$lblTotalImpaye.getText().equals("0.00") || this.val$lblTotalImpaye.getText().equals(access$000) || this.val$lblTotalImpaye.getText().equals(access$000 + "0")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MTCImpayeNewPage.this.uiManager.stateMachine, "Veuillez sélectionner un impayé", null);
            } else {
                MTCImpayeNewPage.this.uiManager.NavigateToPage(new MTCConfirmationPage(MTCImpayeNewPage.this.uiManager, mTCImpaye, 24));
            }
        }
    }

    private String getToalFrais() {
        Iterator it = CihMBanking.sesPMTR.getCurrentMTCImpaye().iterator();
        double d = 0.0d;
        String str = "0.00";
        while (it.hasNext()) {
            MTCImpaye mTCImpaye = (MTCImpaye) it.next();
            if (mTCImpaye.TypeArticle.equals("1") || mTCImpaye.TypeArticle.equals("2")) {
                d = ((d * 100.0d) + (DataTools.PurgeBlankFromStringAmount(mTCImpaye.PrixTTC).doubleValue() * 100.0d)) / 100.0d;
                str = DataTools.FormatCurrency(("" + d) + "0");
            }
        }
        return str;
    }
}
