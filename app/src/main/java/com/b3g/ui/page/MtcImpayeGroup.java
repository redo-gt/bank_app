package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.MTCCreance;
import com.b3g.services.MTCFavoriteBill;
import com.b3g.services.MTCImpaye;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MtcImpayeGroup extends B3GPage {
    public static Button selectAllBtn;
    DataTools dt = new DataTools();
    Label lblTotalImpaye = new Label("0.00");

    static /* synthetic */ String access$000(MtcImpayeGroup mtcImpayeGroup, ArrayList arrayList) {
        return mtcImpayeGroup.getTotalFraisGrp(arrayList);
    }

    static /* synthetic */ boolean access$100(MtcImpayeGroup mtcImpayeGroup) {
        return mtcImpayeGroup.verifySelected();
    }

    public MtcImpayeGroup(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        selectAllBtn = null;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.setScrollVisible(false);
        try {
            Label label = new Label("Paiement de Factures Global");
            label.setUIID("gb_lblGlobalHeaderTitleV2");
            label.setTextPosition(1);
            label.setVerticalAlignment(4);
            label.setScrollVisible(false);
            label.setEnabled(false);
            Button button = new Button();
            selectAllBtn = button;
            button.setUIID("mtc_checkBoxImpaye");
            selectAllBtn.setIcon(this.uiManager.ressource.getImage("radio_off.PNG"));
            selectAllBtn.addActionListener(new 1());
            this.thisContainer.addComponent(label);
            Container container = new Container(new BorderLayout());
            container.setUIID("margin_2_2_1_1");
            container.add("West", selectAllBtn);
            SpanLabel spanLabel = new SpanLabel("Séléctionner toutes les factures");
            spanLabel.setTextUIID("g_lblDashBoardValueBLK");
            spanLabel.setUIID("padding_0_0_1_1");
            container.add("Center", spanLabel);
            Container container2 = new Container(new BoxLayout(2));
            container2.setUIID("bf_cntBeneficarySeparator");
            container2.setScrollVisible(false);
            container2.setPreferredH(10);
            this.thisContainer.addComponent(container2);
            CihMBanking.sesPMTR.setMontantReducToPass(0.0f);
            Container container3 = new Container(new BoxLayout(2));
            container3.setUIID("gl_GloabalContainerV2NoPadMargin");
            container3.addComponent(container);
            for (int i = 0; i < CihMBanking.sesPMTR.getFavs().size(); i++) {
                container3.addComponent(GetMTCImpayeContainer((MTCFavoriteBill) CihMBanking.sesPMTR.getFavs().get(i)));
            }
            container3.getAllStyles().setPaddingUnit(1);
            container3.getAllStyles().setPadding(0, 0, 1, 1);
            this.thisContainer.addComponent(container3);
            this.thisContainer.addComponent(GetValidateButton(CihMBanking.sesPMTR.getFavs()));
            this.thisContainer.setScrollableY(true);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (MtcImpayeGroup.selectAllBtn.getIcon() == MtcImpayeGroup.this.uiManager.ressource.getImage("radio_off.PNG")) {
                for (int i = 0; i < CihMBanking.sesPMTR.getFavs().size(); i++) {
                    for (int i2 = 0; i2 < ((MTCFavoriteBill) CihMBanking.sesPMTR.getFavs().get(i)).MTCImpayeList.size(); i2++) {
                        ((MTCImpaye) ((MTCFavoriteBill) CihMBanking.sesPMTR.getFavs().get(i)).MTCImpayeList.get(i2)).chk.setEnabled(true);
                        ((MTCImpaye) ((MTCFavoriteBill) CihMBanking.sesPMTR.getFavs().get(i)).MTCImpayeList.get(i2)).setOn(MtcImpayeGroup.this.lblTotalImpaye);
                    }
                }
                MtcImpayeGroup.selectAllBtn.setIcon(MtcImpayeGroup.this.uiManager.ressource.getImage("radio_on.png"));
                return;
            }
            for (int i3 = 0; i3 < CihMBanking.sesPMTR.getFavs().size(); i3++) {
                for (int i4 = 0; i4 < ((MTCFavoriteBill) CihMBanking.sesPMTR.getFavs().get(i3)).MTCImpayeList.size(); i4++) {
                    if (((MTCImpaye) ((MTCFavoriteBill) CihMBanking.sesPMTR.getFavs().get(i3)).MTCImpayeList.get(i4)).TypeArticle.equals("0")) {
                        ((MTCImpaye) ((MTCFavoriteBill) CihMBanking.sesPMTR.getFavs().get(i3)).MTCImpayeList.get(i4)).chk.setEnabled(false);
                        ((MTCImpaye) ((MTCFavoriteBill) CihMBanking.sesPMTR.getFavs().get(i3)).MTCImpayeList.get(i4)).setOff(MtcImpayeGroup.this.lblTotalImpaye);
                    }
                }
            }
            MtcImpayeGroup.selectAllBtn.setIcon(MtcImpayeGroup.this.uiManager.ressource.getImage("radio_off.PNG"));
        }
    }

    public Container GetMTCImpayeContainer(MTCFavoriteBill mTCFavoriteBill) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("margin_1_1_1_1");
        container.setEnabled(true);
        Container container2 = new Container(new GridLayout(1, 1));
        container2.setUIID("marg_0_1_0_0");
        Container container3 = new Container();
        container3.setUIID("cn_greylayer");
        container3.setPreferredH(10);
        container2.addComponent(container3);
        container.addComponent(container2);
        TableLayout tableLayout = new TableLayout(1, 2);
        Container container4 = new Container();
        container4.setLayout(tableLayout);
        container4.setUIID("margin_0_1_0_0");
        container4.setEnabled(true);
        TableLayout tableLayout2 = new TableLayout(1, 1);
        Container container5 = new Container();
        container5.setLayout(tableLayout2);
        container5.setUIID("MTCIconStyMTC");
        container5.setFocusable(false);
        container5.setScrollable(false);
        Button button = new Button();
        button.setUIID("crd_btnCardImage");
        button.setIcon(this.uiManager.ressource.getImage("black_pic.png"));
        button.setIcon(URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(button.getIcon().getWidth(), button.getIcon().getWidth(), 12569042), false), "Normal_https://www.cihnet.co.ma" + mTCFavoriteBill.MTCCreancier.LogoPath, "https://www.cihnet.co.ma" + mTCFavoriteBill.MTCCreancier.LogoPath, URLImage.RESIZE_SCALE));
        button.setScrollVisible(false);
        button.setFocusable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(100);
        createConstraint.setVerticalAlign(0);
        container5.addComponent(createConstraint, button);
        Container container6 = new Container(new BoxLayout(2));
        container6.setUIID("st_cntStatisticLeftBloc");
        container6.setFocusable(false);
        container6.setScrollVisible(false);
        Container container7 = new Container(new BoxLayout(2));
        container7.setUIID("g_cntDebitCredit");
        container7.setFocusable(false);
        container7.setScrollVisible(false);
        Label label = new Label(mTCFavoriteBill.MTCCreancier.NomCreancier);
        label.setUIID("g_lblDashBoardTitle");
        label.setFocusable(false);
        label.setScrollVisible(false);
        label.setVerticalAlignment(4);
        label.setTextPosition(1);
        Label label2 = new Label(mTCFavoriteBill.MTCCreancier.DescriptionCreancier);
        label2.setUIID("g_lblDashBoardValueBlue");
        label2.setFocusable(false);
        label2.setScrollVisible(false);
        label2.setVerticalAlignment(4);
        label2.setTextPosition(1);
        container7.addComponent(label);
        container7.addComponent(label2);
        container6.addComponent(container7);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint(0, 0);
        createConstraint2.setWidthPercentage(20);
        createConstraint2.setHorizontalAlign(4);
        container4.addComponent(createConstraint2, container5);
        TableLayout.Constraint createConstraint3 = tableLayout.createConstraint(0, 1);
        createConstraint3.setWidthPercentage(80);
        container4.addComponent(createConstraint3, container6);
        container.addComponent(container4);
        container.addComponent(GetCntBorderSeparator());
        container.addComponent(GetKeyValueData("Service sélectionné", mTCFavoriteBill.MTCCreance.NomCreance));
        container.addComponent(GetCntBorderSeparator());
        if (mTCFavoriteBill.MTCImpayeList.size() > 0) {
            container.addComponent(GetKeyValueData(((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(0)).IdentifiantValue, ((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(0)).Identifiant));
        } else {
            container.addComponent(GetKeyValueData("Identifiant", "-"));
        }
        container.addComponent(GetCntBorderSeparator());
        container.addComponent(GetImpayeList(mTCFavoriteBill.MTCImpayeList, this.lblTotalImpaye, "0.00", mTCFavoriteBill));
        container.revalidate();
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

    public Container GetImpayeList(ArrayList arrayList, Label label, String str, MTCFavoriteBill mTCFavoriteBill) {
        BorderLayout borderLayout = new BorderLayout();
        for (int i = 0; i < arrayList.size(); i++) {
            ((MTCImpaye) arrayList.get(i)).MTCCreance = mTCFavoriteBill.MTCCreance;
            ((MTCImpaye) arrayList.get(i)).MTCCreancier = mTCFavoriteBill.MTCCreancier;
        }
        Container container = new Container();
        container.setLayout(borderLayout);
        container.setUIID("Container");
        container.setEnabled(true);
        if (arrayList.size() > 0 && CihMBanking.sesPMTR.getIsNotaire().equals("True") && mTCFavoriteBill.MTCCreancier.CodeCreancier.equals("1025") && ((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(0)).IdArticle.substring(0, 1).equals("7")) {
            MTCCreance mTCCreance = new MTCCreance();
            if (CihMBanking.sesPMTR.getMontantReduction() == 0.0f) {
                CihMBanking.sesPMTR.setMontantReduction(Float.parseFloat(mTCCreance.MTCFessProcess(mTCFavoriteBill.MTCCreancier.CodeCreancier, mTCFavoriteBill.MTCCreance.CodeCreance, getToalFrais(mTCFavoriteBill.MTCImpayeList))));
                CihMBanking.sesPMTR.setMontantReducToPass(CihMBanking.sesPMTR.getMontantReduction());
                CihMBanking.sesPMTR.isDGIWithNot = true;
            }
        }
        container.addComponent("Center", GetMTCImpayeListContainer(arrayList, label));
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("CntMTCF");
        container2.setScrollVisible(false);
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
            this.uiManager.Draw_GroupBy(container, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 87, mTCImpaye, label);
        } else {
            container.addComponent(this.uiManager.GetCntMessage(CihMBanking.sesPMTR.getCurrentMTCImpayeListStatusLabel()));
        }
        return container;
    }

    private String getToalFrais(ArrayList arrayList) {
        Iterator it = arrayList.iterator();
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

    private String getTotalFraisGrp(ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        double d = 0.0d;
        String str = "0.00";
        while (it.hasNext()) {
            Iterator it2 = ((MTCFavoriteBill) it.next()).MTCImpayeList.iterator();
            while (it2.hasNext()) {
                MTCImpaye mTCImpaye = (MTCImpaye) it2.next();
                if (mTCImpaye.TypeArticle.equals("1") || mTCImpaye.TypeArticle.equals("2")) {
                    d = ((d * 100.0d) + (DataTools.PurgeBlankFromStringAmount(mTCImpaye.PrixTTC).doubleValue() * 100.0d)) / 100.0d;
                }
            }
            str = DataTools.FormatCurrency(("" + d) + "0");
        }
        return str;
    }

    public Container GetMTCResumeValueFlow() {
        Container container = new Container(new BoxLayout(2));
        Container container2 = new Container(new FlowLayout(4, 4));
        container2.setUIID("Container");
        Label label = new Label("Montant Total : ");
        Label label2 = new Label(" (DHS)");
        label.setUIID("ad_lblValueOrgMedium");
        this.lblTotalImpaye.setUIID("ad_lblValueOrgMedium");
        label2.setUIID("ad_lblValueOrgMedium");
        container2.addComponent(label);
        container2.addComponent(this.lblTotalImpaye);
        container2.addComponent(label2);
        container.add(container2);
        container.setUIID("op_BtnOppositionValidation");
        return container;
    }

    public Container GetValidateButton(ArrayList arrayList) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("ContainerMTCF");
        container.setFocusable(false);
        container.setScrollVisible(false);
        Button button = new Button("SUIVANT");
        button.setAlignment(4);
        button.setVerticalAlignment(4);
        button.setScrollVisible(false);
        button.setFocusable(false);
        button.setUIID("op_BtnOppositionValidationOrgBg");
        button.getAllStyles().setMarginUnit(1);
        button.getAllStyles().setMargin(1, 1, 0, 0);
        button.addActionListener(new 2(arrayList));
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("margin_1_1_0_0");
        container2.add(GetMTCResumeValueFlow());
        container.addComponent(container2);
        container.addComponent(button);
        return container;
    }

    class 2 implements ActionListener {
        final /* synthetic */ ArrayList val$MTCFav;

        2(ArrayList arrayList) {
            this.val$MTCFav = arrayList;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            MTCImpaye mTCImpaye = new MTCImpaye();
            String access$000 = MtcImpayeGroup.access$000(MtcImpayeGroup.this, this.val$MTCFav);
            mTCImpaye.PrixTTC = MtcImpayeGroup.this.lblTotalImpaye.getText();
            boolean access$100 = MtcImpayeGroup.access$100(MtcImpayeGroup.this);
            if (mTCImpaye.PrixTTC.equals("0.0") || mTCImpaye.PrixTTC.equals("0.00") || mTCImpaye.PrixTTC.equals(access$000) || mTCImpaye.PrixTTC.equals(access$000 + "0")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MtcImpayeGroup.this.uiManager.stateMachine, "Veuillez sélectionner un impayé", null);
            } else if (access$100) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MtcImpayeGroup.this.uiManager.stateMachine, "Veuillez sélectionner un impayé", null);
            } else {
                MtcImpayeGroup.this.uiManager.NavigateToPage(new MtcConfirmationRegroupmentPage(MtcImpayeGroup.this.uiManager, mTCImpaye, 24));
            }
        }
    }

    private boolean verifySelected() {
        boolean z = false;
        for (int i = 0; i < CihMBanking.sesPMTR.getFavs().size() && !(z = isBillsUnselected((MTCFavoriteBill) CihMBanking.sesPMTR.getFavs().get(i))); i++) {
        }
        return z;
    }

    private boolean isBillsUnselected(MTCFavoriteBill mTCFavoriteBill) {
        MTCImpaye mTCImpaye = null;
        boolean z = false;
        for (int i = 0; i < mTCFavoriteBill.MTCImpayeList.size(); i++) {
            if (((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(i)).TypeArticle.equals("0")) {
                z = ((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(i)).IsSelected.equals("1");
                if (z) {
                    break;
                }
            } else {
                if (((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(i)).TypeArticle.equals("1")) {
                    mTCImpaye = (MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(i);
                }
                z = false;
            }
        }
        return (z || mTCImpaye == null) ? false : true;
    }
}
