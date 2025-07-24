package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.MTCFavoriteBill;
import com.b3g.services.MTCImpaye;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableLayout;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MTCImpayeTopupPage extends B3GPage {
    public MTCImpayeTopupPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            new Container(new BoxLayout(2)).setUIID("ad_CntAccountDetailsMain");
            this.thisContainer.addComponent(GetMTCImpayeTopUpContainer());
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public Container GetMTCImpayeTopUpContainer() {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "MTCImpayeForm");
        ArrayList currentMTCImpaye = CihMBanking.sesPMTR.getCurrentMTCImpaye();
        this.uiManager.stateMachine.findLblCreancierNameTypeValueV2(createContainer).setText(CihMBanking.sesPMTR.getSelectedMTCCreancier().NomCreancier);
        this.uiManager.stateMachine.findLblCreancierDescriptionValueV2(createContainer).setText(CihMBanking.sesPMTR.getSelectedMTCCreancier().DescriptionCreancier);
        this.uiManager.stateMachine.findBtnCreancierImage(createContainer).setIcon(URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(this.uiManager.stateMachine.findBtnCreancierImage(createContainer).getIcon().getWidth(), this.uiManager.stateMachine.findBtnCreancierImage(createContainer).getIcon().getWidth(), 12569042), false), "Normal_https://www.cihnet.co.ma" + CihMBanking.sesPMTR.getSelectedMTCCreancier().LogoPath, "https://www.cihnet.co.ma" + CihMBanking.sesPMTR.getSelectedMTCCreancier().LogoPath, URLImage.RESIZE_SCALE));
        this.uiManager.stateMachine.findCntSelectedCreance(createContainer).removeAll();
        this.uiManager.stateMachine.findCntSelectedCreance(createContainer).revalidate();
        this.uiManager.stateMachine.findCntSelectedCreance(createContainer).addComponent(GetKeyValueData("Service sélectionné", CihMBanking.sesPMTR.getSelectedMTCCreance().NomCreance));
        Label label = new Label("0.00");
        createContainer.addComponent(GetMTCImpayeTopUpListContainer(currentMTCImpaye, label));
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
        if (currentMTCImpaye.size() > 0) {
            createContainer.addComponent(GetMTCResumeValueFlow(label));
            container.addComponent(button);
            createContainer.addComponent(container);
        }
        createContainer.revalidate();
        createContainer.setScrollableY(true);
        Settings.setNightMode(createContainer, 0);
        return createContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Label val$lblTotalImpaye;

        1(Label label) {
            this.val$lblTotalImpaye = label;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            MTCImpaye mTCImpaye = new MTCImpaye();
            mTCImpaye.PrixTTC = this.val$lblTotalImpaye.getText();
            if (this.val$lblTotalImpaye.getText().equals("0.0") || this.val$lblTotalImpaye.getText().equals("0.00")) {
                if (CihMBanking.sesPMTR.getBillerType() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MTCImpayeTopupPage.this.uiManager.stateMachine, "Veuillez sélectionner un impayé", null);
                    return;
                } else {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MTCImpayeTopupPage.this.uiManager.stateMachine, "Veuillez sélectionner une promotion", null);
                    return;
                }
            }
            if (CihMBanking.sesPMTR.getBillerType() == 0) {
                MTCImpayeTopupPage.this.uiManager.NavigateToPage(new MTCConfirmationPage(MTCImpayeTopupPage.this.uiManager, mTCImpaye, 24));
            } else if (!MTCImpayeTopupPage.this.CheckMultiplePromotion(CihMBanking.sesPMTR.getCurrentMTCImpaye())) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MTCImpayeTopupPage.this.uiManager.stateMachine, "Veuillez sélectionner une seule promotion", null);
            } else {
                MTCImpayeTopupPage.this.uiManager.NavigateToPage(new MTCConfirmationPage(MTCImpayeTopupPage.this.uiManager, mTCImpaye, 24));
            }
        }
    }

    public Container GetMTCImpayeTopUpListContainer(ArrayList arrayList, Label label) {
        Container container = new Container(new FlowLayout(3, 4));
        container.setUIID("margin_1_1_1_1");
        Container container2 = new Container(new FlowLayout(1, 4));
        container2.setUIID("margin_1_1_1_1");
        Label label2 = new Label("Solde : ");
        Label label3 = new Label();
        Label label4 = new Label(" DH");
        Label label5 = new Label();
        Label label6 = new Label();
        label2.setUIID("g_lblDashBoardTitleOrange");
        label3.setUIID("g_lblDashBoardTitleOrange");
        label4.setUIID("g_lblDashBoardTitleOrange");
        label5.setUIID("lbl_regular");
        label6.setUIID("lbl_regular");
        if (arrayList.size() > 0 && CihMBanking.sesPMTR.getSelectedMTCCreancier().CodeCreancier.equals("1024")) {
            if (((MTCImpaye) arrayList.get(0)).Libelle.equals("Solde")) {
                label3.setText(((MTCImpaye) arrayList.get(0)).ValeurChamp);
                container.addComponent(label2);
                container.addComponent(label3);
                container.addComponent(label4);
            }
            if (((MTCImpaye) arrayList.get(0)).labelNom.equals("Nom ou Raison sociale")) {
                label5.setText(((MTCImpaye) arrayList.get(0)).labelNom);
                label6.setText(" : " + ((MTCImpaye) arrayList.get(0)).FieldName);
                container2.addComponent(label5);
                container2.addComponent(label6);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        MTCImpaye mTCImpaye = new MTCImpaye();
        String format = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == 0) {
                ((MTCImpaye) arrayList.get(i)).IsSelectedByDefault = true;
            }
            ((MTCImpaye) arrayList.get(i)).DateFacture = format;
            ((MTCImpaye) arrayList.get(i)).groupKey = ((MTCImpaye) arrayList.get(i)).DateFacture;
            arrayList2.add((B3gService) arrayList.get(i));
        }
        Container container3 = new Container(new BoxLayout(2));
        container3.add(container2);
        container3.add(container);
        if (this.uiManager.GetCurrentPage().PageSession != null) {
            MTCFavoriteBill mTCFavoriteBill = (MTCFavoriteBill) this.uiManager.GetCurrentPage().PageSession;
            Label label7 = new Label();
            if (CihMBanking.sesPMTR.getBillerType() == 1) {
                if (mTCFavoriteBill.Identifiant != "") {
                    label7.setText(mTCFavoriteBill.Alias + " (" + mTCFavoriteBill.Identifiant + ")");
                } else {
                    label7.setText(mTCFavoriteBill.Alias);
                }
                label7.setUIID("ac_lblitemDetailBlue");
                label7.setVerticalAlignment(4);
                label7.setTextPosition(3);
                container3.addComponent(label7);
            }
        }
        if (arrayList2.size() > 0) {
            this.uiManager.Draw_GroupBy(container3, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 17, mTCImpaye, label, container3);
        } else {
            container3.addComponent(this.uiManager.GetCntMessage(CihMBanking.sesPMTR.getCurrentMTCImpayeListStatusLabel()));
        }
        return container3;
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
        Label label2 = new Label("Montant Total (DH) : ");
        label2.setUIID("g_lblNotifPlafond");
        label2.setTextPosition(1);
        label2.setVerticalAlignment(4);
        container2.addComponent(label2);
        container2.addComponent(label);
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
        Container container2 = new Container(new BoxLayout(1));
        container2.setUIID("mtc_CntimpayeValueResumeFlow");
        label.setUIID("g_lblNotifGrenPlafond");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        Label label2 = new Label("Montant Total (DH) : ");
        label2.setUIID("g_lblNotifPlafond");
        label2.setTextPosition(1);
        label2.setVerticalAlignment(4);
        container2.addComponent(label2);
        container2.addComponent(label);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(100);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(3);
        container.addComponent(createConstraint, container2);
        return container;
    }

    public boolean CheckMultiplePromotion(ArrayList arrayList) {
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (((MTCImpaye) arrayList.get(i2)).IsSelected.equals("1")) {
                i++;
            }
        }
        return i == 1;
    }

    public Container GetKeyValueData(String str, String str2) {
        Container container = new Container(new BoxLayout(3));
        container.setFocusable(false);
        container.setScrollVisible(false);
        container.setUIID("MTCKeyValueLabelForm");
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
}
