package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.MTCCreancier;
import com.b3g.services.MTCFavoriteBill;
import com.b3g.services.MTCHistoric;
import com.b3g.services.MTCImpaye;
import com.b3g.services.ServiceManager;
import com.b3g.tools.SyncTask;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordion;
import com.b3g.ui.B3gAccordionNw;
import com.b3g.ui.CihUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MTCMenuPage extends B3GPage {
    ArrayList Favs;
    public ArrayList MTCCreancierList;
    ArrayList MTCFavoriteBillList;
    Button PayerBtn;
    Container SelectAll;
    B3gAccordionNw accrFavoris;
    B3gAccordionNw accrHistorique;
    Button btnFavoriteRightIcon;
    Button btnHistoricRightIcon;
    CihUiManager cihM;
    Container cntGlbFavoris;
    Container cntHistorique;
    public Boolean isFavoritClosed;
    public Boolean isHitoricClosed;
    public ArrayList mtcHistoricList;
    String refIndex;
    int widh;

    static /* synthetic */ void access$200(MTCMenuPage mTCMenuPage, ArrayList arrayList, Container container) {
        mTCMenuPage.SetFavorisView(arrayList, container);
    }

    public MTCMenuPage() {
        this.isFavoritClosed = true;
        this.isHitoricClosed = true;
        this.SelectAll = new Container(new BorderLayout());
        this.Favs = new ArrayList();
        this.widh = 0;
        this.refIndex = "";
        this.cihM = new CihUiManager();
    }

    public MTCMenuPage(Object obj, Object obj2, int i) {
        this.isFavoritClosed = true;
        this.isHitoricClosed = true;
        this.SelectAll = new Container(new BorderLayout());
        this.Favs = new ArrayList();
        this.widh = 0;
        this.refIndex = "";
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        this.cihM = new CihUiManager();
    }

    public Container GetPageContainer() {
        this.Favs.clear();
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.setScrollableY(false);
        this.thisContainer.setScrollVisible(false);
        Label label = new Label("Paiements de Factures");
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        label.setScrollVisible(false);
        label.setEnabled(false);
        System.currentTimeMillis();
        this.MTCCreancierList = new MTCCreancier().MTCCreancierProcess("B");
        CihMBanking.sesPMTR.MTCCreancierList = this.MTCCreancierList;
        System.currentTimeMillis();
        this.thisContainer.addComponent(label);
        this.thisContainer.addComponent(DrawMTCHomeMenuV2());
        if (DrawMTCHistoric() != null) {
            this.thisContainer.addComponent(DrawMTCHistoric());
        }
        if (DrawMTCHomeMenuV3(this.MTCCreancierList) != null) {
            this.thisContainer.addComponent(DrawMTCHomeMenuV3(this.MTCCreancierList));
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public Container DrawMTCHomeMenuV2() {
        this.cntGlbFavoris = new Container(new BoxLayout(2));
        this.accrFavoris = new B3gAccordionNw();
        Container container = new Container(new BoxLayout(2));
        container.setUIID("");
        container.setScrollableY(false);
        container.setScrollVisible(false);
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            SpanLabel spanLabel = new SpanLabel("Ce service n'est pas disponible en mode démonstration.");
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            container.addComponent(spanLabel);
            return container;
        }
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("gl_GloabalContainerV2NoPadMargin");
        container2.setScrollVisible(false);
        Container container3 = new Container(new BoxLayout(2));
        container3.setUIID("bf_cntBeneficarySeparator");
        container3.setScrollVisible(false);
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container4 = new Container();
        container4.setLayout(tableLayout);
        container4.setUIID("mtc_CntTableLayoutN");
        container4.setFocusable(false);
        container4.setScrollable(false);
        Button button = new Button("Liste des Facturiers");
        button.setUIID("mtc_homemenuItemText");
        button.setScrollVisible(false);
        button.setFocusable(false);
        button.addActionListener(new 1());
        Button button2 = new Button();
        button2.setUIID("crd_btnCardImage");
        button2.setIcon(this.uiManager.ressource.getImage(""));
        button2.setScrollVisible(false);
        button2.setFocusable(false);
        Button button3 = new Button();
        button3.setUIID("");
        button3.setIcon(this.uiManager.ressource.getImage("mtcPaiement_w.png"));
        button3.setScrollVisible(false);
        button3.setFocusable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(15);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(75);
        TableLayout.Constraint createConstraint3 = tableLayout.createConstraint();
        createConstraint3.setWidthPercentage(10);
        container4.addComponent(createConstraint, button3);
        container4.addComponent(createConstraint2, button);
        container4.addComponent(createConstraint3, button2);
        container4.setLeadComponent(button);
        container2.addComponent(container4);
        Container container5 = new Container(new BoxLayout(2));
        container5.setScrollVisible(false);
        TableLayout tableLayout2 = new TableLayout(1, 3);
        Container container6 = new Container();
        container6.setLayout(tableLayout2);
        container6.setUIID("mtc_CntTableLayoutN");
        container6.setFocusable(false);
        container6.setScrollable(false);
        Button button4 = new Button();
        button4.setUIID("");
        button4.setIcon(this.uiManager.ressource.getImage("icone_favoris.png"));
        button4.setScrollVisible(false);
        button4.setFocusable(false);
        Button button5 = new Button("Mes Favoris");
        button5.setUIID("mtc_homemenuItemText");
        button5.setScrollVisible(false);
        button5.setFocusable(false);
        Button button6 = new Button();
        this.btnFavoriteRightIcon = button6;
        button6.setUIID("crd_btnCardImage");
        this.btnFavoriteRightIcon.setIcon(this.uiManager.ressource.getImage(""));
        this.btnFavoriteRightIcon.setScrollVisible(false);
        this.btnFavoriteRightIcon.setFocusable(false);
        TableLayout.Constraint createConstraint4 = tableLayout.createConstraint();
        createConstraint4.setWidthPercentage(15);
        createConstraint4.setVerticalAlign(4);
        createConstraint4.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint5 = tableLayout.createConstraint();
        createConstraint5.setWidthPercentage(75);
        TableLayout.Constraint createConstraint6 = tableLayout.createConstraint();
        createConstraint6.setWidthPercentage(10);
        container6.addComponent(createConstraint4, button4);
        container6.addComponent(createConstraint5, button5);
        container6.addComponent(createConstraint6, this.btnFavoriteRightIcon);
        container5.addComponent(container6);
        Image image = this.uiManager.ressource.getImage("blueArrowRotated.png");
        Image image2 = CihMBanking.detail_arrow;
        this.accrFavoris.setArrowUIID("Regroupement_margin_0_4_0_0");
        this.accrFavoris.setCloseIcon(image2);
        this.accrFavoris.setOpenIcon(image);
        this.cntGlbFavoris.setScrollableY(true);
        this.cntGlbFavoris.setScrollVisible(false);
        this.accrFavoris.setScrollableY(true);
        this.accrFavoris.setScrollVisible(false);
        Container container7 = new Container(new BorderLayout());
        container7.add("Center", container5);
        container7.setUIID("gl_GloabalContainerV2NoPadMargin");
        Button button7 = new Button();
        button7.setName("Abcd");
        button7.setUIID("margin_0_0_1_1Center");
        button7.setIcon(this.uiManager.ressource.getImage("radio_off.PNG"));
        new Container(new BorderLayout());
        Container container8 = new Container(BoxLayout.y());
        Label label = new Label("tout");
        Label label2 = new Label("Sélectionner");
        label.setUIID("lbl_black_very_smallCenter");
        label2.setUIID("lbl_black_very_smallCenter");
        container8.add(label2);
        container8.add(label);
        this.SelectAll.add("Center", button7);
        this.SelectAll.add("South", container8);
        this.SelectAll.setHidden(true);
        this.SelectAll.setUIID("margin_0_0_1_1Center");
        this.SelectAll.setName("Abcd");
        container7.add("West", this.SelectAll);
        this.widh = label2.getPreferredW();
        container7.setName("HolaHola");
        button7.addActionListener(new 2(button7));
        this.accrFavoris.addContent(container7, this.cntGlbFavoris);
        Button button8 = new Button("PAYER");
        this.PayerBtn = button8;
        button8.setUIID("op_BtnOppositionValidation");
        this.PayerBtn.addActionListener(new 3());
        this.PayerBtn.setHidden(true);
        this.accrFavoris.addOnClickItemListener(new 4(button7, image, image2));
        container.addComponent(container3);
        container.addComponent(this.accrFavoris);
        button7.setFocusable(true);
        return container;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.setIsFavoriteBack(0);
            MTCMenuPage.this.uiManager.NavigateToPageById(20);
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Button val$SelectAllBtn;

        2(Button button) {
            this.val$SelectAllBtn = button;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$SelectAllBtn.getIcon() == MTCMenuPage.this.uiManager.ressource.getImage("radio_off.PNG")) {
                this.val$SelectAllBtn.setIcon(MTCMenuPage.this.uiManager.ressource.getImage("radio_on.png"));
                MTCMenuPage.this.Favs.clear();
                for (int i = 0; i < MTCMenuPage.this.cntGlbFavoris.getComponentCount() - 1; i++) {
                    FavItem favItem = (FavItem) MTCMenuPage.this.cntGlbFavoris.getComponentAt(i);
                    if (!favItem.getFavBillLbl().getText().equals(" 0 impayé(s)") && !favItem.getFavBillLbl().getText().equals("Chargement...") && !favItem.getFavBillLbl().getText().equals("0 impayé(s)") && !favItem.getFavBillLbl().getText().equals("Loading...") && !favItem.getFavBillLbl().getText().equals("Cargando ...") && !favItem.getFavBillLbl().getText().equals("تحميل ...")) {
                        favItem.favBillBtn.setIcon(MTCMenuPage.this.uiManager.ressource.getImage("radio_on.png"));
                        MTCMenuPage.this.Favs.add(favItem.favBill);
                    }
                }
                if (MTCMenuPage.this.Favs.size() != 0 && !MTCMenuPage.this.Favs.isEmpty()) {
                    MTCMenuPage.this.PayerBtn.setHidden(false);
                }
                MTCMenuPage.this.cntGlbFavoris.revalidate();
                return;
            }
            this.val$SelectAllBtn.setIcon(MTCMenuPage.this.uiManager.ressource.getImage("radio_off.PNG"));
            for (int i2 = 0; i2 < MTCMenuPage.this.cntGlbFavoris.getComponentCount() - 1; i2++) {
                FavItem favItem2 = (FavItem) MTCMenuPage.this.cntGlbFavoris.getComponentAt(i2);
                if (favItem2.favBillBtn.getIcon() == MTCMenuPage.this.uiManager.ressource.getImage("radio_on.png")) {
                    favItem2.favBillBtn.setIcon(MTCMenuPage.this.uiManager.ressource.getImage("radio_off.PNG"));
                    MTCMenuPage.this.Favs.remove(favItem2.favBill);
                }
            }
            MTCMenuPage.this.PayerBtn.setHidden(true);
            MTCMenuPage.this.cntGlbFavoris.revalidate();
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.setFavs(MTCMenuPage.this.Favs);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < MTCMenuPage.this.Favs.size(); i++) {
                arrayList.addAll(((MTCFavoriteBill) MTCMenuPage.this.Favs.get(i)).MTCImpayeList);
            }
            CihMBanking.sesPMTR.setCurrentMTCImpaye(arrayList);
            MTCMenuPage.this.uiManager.NavigateToPageById(107);
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Button val$SelectAllBtn;
        final /* synthetic */ Image val$closeIcon;
        final /* synthetic */ Image val$openIcon;

        4(Button button, Image image, Image image2) {
            this.val$SelectAllBtn = button;
            this.val$openIcon = image;
            this.val$closeIcon = image2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            boolean z;
            MTCMenuPage.this.Favs.clear();
            MTCMenuPage.this.PayerBtn.setHidden(true);
            this.val$SelectAllBtn.setIcon(MTCMenuPage.this.uiManager.ressource.getImage("radio_off.PNG"));
            MTCMenuPage.this.refIndex = "";
            MTCMenuPage.this.cntGlbFavoris.removeAll();
            B3gAccordionNw.AccordionContent accordionContent = (B3gAccordionNw.AccordionContent) MTCMenuPage.this.accrFavoris.getComponentAt(0);
            if (MTCMenuPage.this.isFavoritClosed.booleanValue()) {
                MTCMenuPage.this.isFavoritClosed = false;
                accordionContent.setArrowIcon(this.val$openIcon);
                if (!MTCMenuPage.this.isHitoricClosed.booleanValue()) {
                    MTCMenuPage.this.isHitoricClosed = true;
                    B3gAccordionNw.AccordionContent accordionContent2 = (B3gAccordionNw.AccordionContent) MTCMenuPage.this.accrHistorique.getComponentAt(0);
                    MTCMenuPage.this.cntHistorique.removeAll();
                    MTCMenuPage.this.cntHistorique.getParent().revalidate();
                    accordionContent2.setArrowIcon(this.val$closeIcon);
                }
                Container container = new Container(new BorderLayout());
                new Container(BoxLayout.y()).setScrollableY(false);
                container.setScrollableY(false);
                CihMBanking.sesPMTR.setIsFavoriteBack(1);
                MTCFavoriteBill mTCFavoriteBill = new MTCFavoriteBill();
                if (CihMBanking.sesPMTR.mtcFavoriteBillListImpayARR.size() > 0) {
                    MTCMenuPage.this.MTCFavoriteBillList = CihMBanking.sesPMTR.mtcFavoriteBillListImpayARR;
                    if (MTCMenuPage.this.MTCFavoriteBillList.size() > 0) {
                        CihMBanking.sesPMTR.setIsFavorite("0");
                        CihMBanking.sesPMTR.setCurrentMTCSelectedAlias(((MTCFavoriteBill) MTCMenuPage.this.MTCFavoriteBillList.get(0)).Alias);
                    }
                    for (int i = 0; i < MTCMenuPage.this.MTCFavoriteBillList.size(); i++) {
                        Container container2 = MTCMenuPage.this.cntGlbFavoris;
                        MTCMenuPage mTCMenuPage = MTCMenuPage.this;
                        container2.addComponent(mTCMenuPage.drawMTCItem((MTCFavoriteBill) mTCMenuPage.MTCFavoriteBillList.get(i), false));
                    }
                    MTCMenuPage.this.cntGlbFavoris.add(MTCMenuPage.this.PayerBtn);
                    int i2 = 0;
                    while (true) {
                        if (i2 >= MTCMenuPage.this.cntGlbFavoris.getComponentCount() - 1) {
                            z = false;
                            break;
                        }
                        FavItem favItem = (FavItem) MTCMenuPage.this.cntGlbFavoris.getComponentAt(i2);
                        if (!favItem.getFavBillLbl().getText().equals("0 impayé(s)") && !favItem.getFavBillLbl().getText().equals("Chargement...") && !favItem.getFavBillLbl().getText().equals("Loading...") && !favItem.getFavBillLbl().getText().equals("Cargando ...") && !favItem.getFavBillLbl().getText().equals("تحميل ...")) {
                            z = true;
                            break;
                        }
                        i2++;
                    }
                    if (z) {
                        MTCMenuPage.this.SelectAll.setHidden(false);
                    } else {
                        MTCMenuPage.this.SelectAll.setHidden(true);
                        MTCMenuPage.this.PayerBtn.setHidden(true);
                    }
                    MTCMenuPage.this.cntGlbFavoris.revalidate();
                } else {
                    if (CihMBanking.sesPMTR.mtcFavoriteBillListARR.isEmpty()) {
                        MTCMenuPage.this.MTCFavoriteBillList = mTCFavoriteBill.MTCFavoriteImpayeProcess(CihMBanking.sesPMTR.getBillerType(), "", false, 900069);
                        if (MTCMenuPage.this.MTCFavoriteBillList.size() > 0) {
                            CihMBanking.sesPMTR.setIsFavorite("0");
                            CihMBanking.sesPMTR.setCurrentMTCSelectedAlias(((MTCFavoriteBill) MTCMenuPage.this.MTCFavoriteBillList.get(0)).Alias);
                        }
                        if (MTCMenuPage.this.MTCFavoriteBillList.size() > 0) {
                            for (int i3 = 0; i3 < MTCMenuPage.this.MTCFavoriteBillList.size(); i3++) {
                                Container container3 = MTCMenuPage.this.cntGlbFavoris;
                                MTCMenuPage mTCMenuPage2 = MTCMenuPage.this;
                                container3.addComponent(mTCMenuPage2.drawMTCItem((MTCFavoriteBill) mTCMenuPage2.MTCFavoriteBillList.get(i3), true));
                            }
                            MTCMenuPage.this.cntGlbFavoris.add(MTCMenuPage.this.PayerBtn);
                        } else {
                            FlowLayout flowLayout = new FlowLayout();
                            flowLayout.setAlign(4);
                            flowLayout.setValign(4);
                            flowLayout.setFillRows(true);
                            Container container4 = new Container();
                            container4.setLayout(flowLayout);
                            container4.setUIID("g_CntTranspMsg");
                            SpanLabel spanLabel = new SpanLabel();
                            spanLabel.setUIID("dg_splblMsgCenter");
                            spanLabel.setScrollVisible(false);
                            spanLabel.setText("Vous n'avez aucun favori");
                            spanLabel.setIconPosition("West");
                            spanLabel.setTextUIID("dg_lblSPError");
                            spanLabel.setIconUIID("g_cntEmpty");
                            container4.addComponent(spanLabel);
                            MTCMenuPage.this.cntGlbFavoris.addComponent(container4);
                        }
                    }
                    if (MTCMenuPage.this.MTCFavoriteBillList.size() > 0) {
                        Display.getInstance().scheduleBackgroundTask(new MTCMenuPage$4$$ExternalSyntheticLambda0(this));
                    }
                }
                MTCMenuPage.this.cntGlbFavoris.revalidate();
                return;
            }
            MTCMenuPage.this.isFavoritClosed = true;
            MTCMenuPage.this.SelectAll.setHidden(true);
            accordionContent.setArrowIcon(this.val$closeIcon);
            MTCMenuPage.this.cntGlbFavoris.getParent().revalidate();
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-MTCMenuPage$4() {
            Display.getInstance().callSerially(new MTCMenuPage$4$$ExternalSyntheticLambda1(this));
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-MTCMenuPage$4() {
            new getFacorisService(MTCMenuPage.this, null).execute(MTCMenuPage.this.cntGlbFavoris);
        }
    }

    public Container DrawMTCHistoric() {
        this.cntHistorique = new Container(new BorderLayout());
        this.accrHistorique = new B3gAccordionNw();
        Container container = new Container(new BoxLayout(2));
        container.setUIID("bf_cntBeneficarySeparator");
        container.setScrollVisible(false);
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            return null;
        }
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container2 = new Container();
        container2.setLayout(tableLayout);
        container2.setUIID("mtc_CntTableLayoutN");
        container2.setFocusable(false);
        container2.setScrollable(false);
        new Container(new BorderLayout());
        Container container3 = new Container(BoxLayout.y());
        container3.setUIID("");
        Container container4 = new Container(new BoxLayout(2));
        container4.setUIID("gl_GloabalContainerV2NoPadMargin");
        container4.setScrollVisible(false);
        TableLayout tableLayout2 = new TableLayout(1, 3);
        Container container5 = new Container();
        container5.setLayout(tableLayout2);
        container5.setUIID("mtc_CntTableLayoutN");
        container5.setFocusable(false);
        container5.setScrollable(false);
        Button button = new Button();
        button.setUIID("");
        button.setIcon(this.uiManager.ressource.getImage("icone_historique.png"));
        button.setScrollVisible(false);
        button.setFocusable(false);
        Button button2 = new Button("Historique de Paiements");
        button2.setUIID("mtc_homemenuItemText");
        button2.setScrollVisible(false);
        button2.setFocusable(false);
        Image image = this.uiManager.ressource.getImage("blueArrowRotated.png");
        Image image2 = CihMBanking.detail_arrow;
        this.accrHistorique.setArrowUIID("Regroupement_margin_0_4_0_0");
        this.accrHistorique.setCloseIcon(image2);
        this.accrHistorique.setOpenIcon(image);
        this.cntHistorique.setScrollableY(true);
        this.cntHistorique.setScrollVisible(false);
        this.accrHistorique.setScrollableY(true);
        this.accrHistorique.setScrollVisible(false);
        this.accrHistorique.addOnClickItemListener(new 5(image, image2, container4));
        Button button3 = new Button();
        this.btnHistoricRightIcon = button3;
        button3.setUIID("crd_btnCardImage");
        this.btnHistoricRightIcon.setIcon(this.uiManager.ressource.getImage(""));
        this.btnHistoricRightIcon.setScrollVisible(false);
        this.btnHistoricRightIcon.setFocusable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(15);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(75);
        TableLayout.Constraint createConstraint3 = tableLayout.createConstraint();
        createConstraint3.setWidthPercentage(10);
        container5.addComponent(createConstraint, button);
        container5.addComponent(createConstraint2, button2);
        container5.addComponent(createConstraint3, this.btnHistoricRightIcon);
        container4.addComponent(container5);
        this.accrHistorique.addContent(container4, this.cntHistorique);
        container3.addComponent(container);
        container3.addComponent(this.accrHistorique);
        return container3;
    }

    class 5 implements ActionListener {
        final /* synthetic */ Image val$closeIcon;
        final /* synthetic */ Container val$cntItemHistoric;
        final /* synthetic */ Image val$openIcon;

        5(Image image, Image image2, Container container) {
            this.val$openIcon = image;
            this.val$closeIcon = image2;
            this.val$cntItemHistoric = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            MTCMenuPage.this.SelectAll.setHidden(true);
            B3gAccordionNw.AccordionContent accordionContent = (B3gAccordionNw.AccordionContent) MTCMenuPage.this.accrHistorique.getComponentAt(0);
            MTCMenuPage.this.cntHistorique.removeAll();
            if (MTCMenuPage.this.isHitoricClosed.booleanValue()) {
                MTCMenuPage.this.isHitoricClosed = false;
                accordionContent.setArrowIcon(this.val$openIcon);
                if (!MTCMenuPage.this.isFavoritClosed.booleanValue()) {
                    MTCMenuPage.this.isFavoritClosed = true;
                    B3gAccordionNw.AccordionContent accordionContent2 = (B3gAccordionNw.AccordionContent) MTCMenuPage.this.accrFavoris.getComponentAt(0);
                    MTCMenuPage.this.cntGlbFavoris.removeAll();
                    MTCMenuPage.this.cntGlbFavoris.getParent().revalidate();
                    accordionContent2.setArrowIcon(this.val$closeIcon);
                }
                MTCMenuPage.this.mtcHistoricList = new MTCHistoric().MTCHistoricProcess(CihMBanking.sesPMTR.getBillerType());
                ArrayList arrayList = new ArrayList();
                new MTCHistoric();
                for (int i = 0; i < MTCMenuPage.this.mtcHistoricList.size(); i++) {
                    ((MTCHistoric) MTCMenuPage.this.mtcHistoricList.get(i)).groupKey = ((MTCHistoric) MTCMenuPage.this.mtcHistoricList.get(i)).BillerId;
                    arrayList.add((B3gService) MTCMenuPage.this.mtcHistoricList.get(i));
                }
                Map map = (Map) MTCMenuPage.this.cihM.GetMapB3gServiceList(arrayList, 29);
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < MTCMenuPage.this.MTCCreancierList.size(); i2++) {
                    if (map.containsKey(((MTCCreancier) MTCMenuPage.this.MTCCreancierList.get(i2)).CodeCreancier)) {
                        arrayList2.add((MTCCreancier) MTCMenuPage.this.MTCCreancierList.get(i2));
                    }
                }
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                Collections.addAll(new ArrayList(), map.keySet().toArray());
                arrayList3.add(this.val$cntItemHistoric);
                if (arrayList2.size() == 0) {
                    arrayList4.add(MTCMenuPage.this.uiManager.DrawListContainer("GloabalContainerV2", "", Boolean.TRUE, arrayList2, arrayList2.size(), 900060, "Vous n'avez aucun historique", null, null, null, null));
                }
                new Container(BoxLayout.y());
                ArrayList arrayList5 = new ArrayList();
                ArrayList arrayList6 = new ArrayList();
                for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                    arrayList5.add(MTCMenuPage.this.drawMtcHistoricHeader((ArrayList) map.get(((MTCCreancier) arrayList2.get(i3)).CodeCreancier), ((MTCCreancier) arrayList2.get(i3)).NomCreancier, ((MTCCreancier) arrayList2.get(i3)).LogoPath));
                    arrayList6.add(MTCMenuPage.this.drawHistoricItem((ArrayList) map.get(((MTCCreancier) arrayList2.get(i3)).CodeCreancier), ((MTCCreancier) arrayList2.get(i3)).CodeCreancier, ((MTCCreancier) arrayList2.get(i3)).LogoPath));
                }
                if (arrayList2.size() == 0) {
                    Container DrawListContainer = MTCMenuPage.this.uiManager.DrawListContainer("GloabalContainerV2", "", Boolean.TRUE, arrayList2, arrayList2.size(), 900060, "Vous n'avez aucun historique", null, null, null, null);
                    MTCMenuPage.this.cntHistorique.removeAll();
                    MTCMenuPage.this.cntHistorique.add("Center", DrawListContainer);
                    MTCMenuPage.this.cntHistorique.getParent().revalidate();
                    return;
                }
                MTCMenuPage.this.cntHistorique.removeAll();
                MTCMenuPage.this.cntHistorique.add("Center", MTCMenuPage.this.drawAccordion(arrayList5, arrayList6));
                MTCMenuPage.this.cntHistorique.getParent().revalidate();
                return;
            }
            MTCMenuPage.this.isHitoricClosed = true;
            accordionContent.setArrowIcon(this.val$closeIcon);
            MTCMenuPage.this.cntHistorique.getParent().revalidate();
        }
    }

    public Container DrawMTCHomeMenuV3(ArrayList arrayList) {
        Container container = new Container(new BorderLayout());
        container.setUIID("Regroupement_margin_0_0_2_0");
        Container container2 = new Container(BoxLayout.y());
        container2.setUIID("");
        container2.setScrollableY(true);
        container2.setScrollVisible(false);
        Label label = new Label("Liste des Facturiers");
        label.setUIID("Regroupement_lbl_white");
        Image image = this.uiManager.ressource.getImage("Regroupement_liste_facturies.png");
        label.setScrollVisible(false);
        label.setFocusable(false);
        TableLayout tableLayout = new TableLayout(1, 2);
        Container container3 = new Container();
        container3.setLayout(tableLayout);
        container3.setUIID("Regroupement_lbl_white");
        container3.setFocusable(false);
        container3.setScrollable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(15);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(85);
        container3.add(createConstraint, image);
        container3.add(createConstraint2, label);
        container2.add(container3);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        arrayList2.add(drawMtcHeader("Téléphonie et Internet", "Regroupement_telecom.png"));
        arrayList2.add(drawMtcHeader("Eau et Electricité", "Regroupement_eauElec.png"));
        arrayList2.add(drawMtcHeader("Taxes et Administrations", "Regroupement_administration.png"));
        arrayList2.add(drawMtcHeader("Transport et Billetterie", "Regroupement_Tansport.png"));
        arrayList2.add(drawMtcHeader("Ecoles et Universités", "Regroupement_Ecoles.png"));
        arrayList2.add(drawMtcHeader("Associations", "Regroupement_Associations.png"));
        arrayList2.add(drawMtcHeader("Autres Services", "Regroupement_Autres.png"));
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        ArrayList arrayList10 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = ((MTCCreancier) arrayList.get(i)).CategorieCreancier;
            str.hashCode();
            switch (str) {
                case "EAU&ELECTRICITE":
                    arrayList4.add((MTCCreancier) arrayList.get(i));
                    break;
                case "TELECOM":
                    arrayList5.add((MTCCreancier) arrayList.get(i));
                    break;
                case "TRANSPORT":
                    arrayList7.add((MTCCreancier) arrayList.get(i));
                    break;
                case "ASSOCIATIONS":
                    arrayList9.add((MTCCreancier) arrayList.get(i));
                    break;
                case "TAXE":
                    arrayList6.add((MTCCreancier) arrayList.get(i));
                    break;
                case "AUTRES":
                    arrayList10.add((MTCCreancier) arrayList.get(i));
                    break;
                case "ECOLES":
                    arrayList8.add((MTCCreancier) arrayList.get(i));
                    break;
            }
        }
        arrayList3.add(this.uiManager.DrawListContainer("GloabalContainerV2", "", Boolean.TRUE, arrayList5, arrayList5.size(), 900060, "Aucun Créancier n'est disponible pour le moment", null, null, null, null));
        arrayList3.add(this.uiManager.DrawListContainer("GloabalContainerV2", "", Boolean.TRUE, arrayList4, arrayList4.size(), 900060, "Aucun Créancier n'est disponible pour le moment", null, null, null, null));
        arrayList3.add(this.uiManager.DrawListContainer("GloabalContainerV2", "", Boolean.TRUE, arrayList6, arrayList6.size(), 900060, "Aucun Créancier n'est disponible pour le moment", null, null, null, null));
        arrayList3.add(this.uiManager.DrawListContainer("GloabalContainerV2", "", Boolean.TRUE, arrayList7, arrayList7.size(), 900060, "Aucun Créancier n'est disponible pour le moment", null, null, null, null));
        arrayList3.add(this.uiManager.DrawListContainer("GloabalContainerV2", "", Boolean.TRUE, arrayList8, arrayList8.size(), 900060, "Aucun Créancier n'est disponible pour le moment", null, null, null, null));
        arrayList3.add(this.uiManager.DrawListContainer("GloabalContainerV2", "", Boolean.TRUE, arrayList9, arrayList9.size(), 900060, "Aucun Créancier n'est disponible pour le moment", null, null, null, null));
        arrayList3.add(this.uiManager.DrawListContainer("GloabalContainerV2", "", Boolean.TRUE, arrayList10, arrayList10.size(), 900060, "Aucun Créancier n'est disponible pour le moment", null, null, null, null));
        container2.add(drawAccordion(arrayList2, arrayList3));
        container.add("Center", container2);
        return container;
    }

    public Container drawMtcHeader(String str, String str2) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("gl_GloabalContainerV2NoPadMargin");
        container.setScrollVisible(false);
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container2 = new Container();
        container2.setLayout(tableLayout);
        container2.setUIID("mtc_CntTableLayoutN");
        container2.setFocusable(false);
        container2.setScrollable(false);
        Button button = new Button(str);
        button.setUIID("mtc_homemenuItemText");
        button.setScrollVisible(false);
        button.setFocusable(false);
        Button button2 = new Button();
        button2.setUIID("crd_btnCardImage");
        button2.setIcon(this.uiManager.ressource.getImage(""));
        button2.setScrollVisible(false);
        button2.setFocusable(false);
        Button button3 = new Button();
        button3.setUIID("");
        button3.setIcon(this.uiManager.ressource.getImage(str2));
        button3.setScrollVisible(false);
        button3.setFocusable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(15);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(4);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(75);
        TableLayout.Constraint createConstraint3 = tableLayout.createConstraint();
        createConstraint3.setWidthPercentage(10);
        new Label();
        container2.addComponent(createConstraint, button3);
        container2.addComponent(createConstraint2, button);
        container2.addComponent(createConstraint3, button2);
        container.addComponent(container2);
        return container;
    }

    public B3gAccordion drawAccordion(ArrayList arrayList, ArrayList arrayList2) {
        B3gAccordion b3gAccordion = new B3gAccordion();
        b3gAccordion.setScrollableY(false);
        Image image = this.uiManager.ressource.getImage("blueArrowRotated.png");
        Image image2 = CihMBanking.detail_arrow;
        b3gAccordion.setArrowUIID("Regroupement_margin_0_4_0_0");
        b3gAccordion.setCloseIcon(image2);
        b3gAccordion.setOpenIcon(image);
        for (int i = 0; i < arrayList.size(); i++) {
            b3gAccordion.addContent((Component) arrayList.get(i), BoxLayout.encloseY((Container) arrayList2.get(i)));
        }
        b3gAccordion.revalidate();
        return b3gAccordion;
    }

    public Container drawHistoricItem(ArrayList arrayList, String str, String str2) {
        Container DrawData_One_GroupBy;
        ArrayList arrayList2 = new ArrayList();
        new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Container container = new Container(new BoxLayout(2));
        MTCHistoric mTCHistoric = new MTCHistoric();
        if (arrayList.size() < 10) {
            DrawData_One_GroupBy = this.cihM.DrawData_One_GroupBy(container, "", arrayList, "", 29, mTCHistoric, (Component) null, 0);
        } else {
            ArrayList arrayList4 = new ArrayList();
            for (int i = 0; i < 10; i++) {
                arrayList4.add((B3gService) arrayList.get(i));
            }
            DrawData_One_GroupBy = this.cihM.DrawData_One_GroupBy(container, "", arrayList4, "", 29, mTCHistoric, (Component) null, 0);
            Container container2 = new Container(new FlowLayout(4));
            container2.getAllStyles().setBgColor(16777215);
            container2.getAllStyles().setPadding(4, 5, 0, 0);
            Button button = new Button("Plus d'historique de paiement");
            button.setUIID("Regroupement_blue_lbl");
            button.addActionListener(new 6(str, arrayList2));
            container2.add(button);
            DrawData_One_GroupBy.add(container2);
            Container container3 = new Container(new BoxLayout(2));
            container3.setUIID("SV_g_cntBorderV2");
            container3.setFocusable(false);
            DrawData_One_GroupBy.add(container3);
        }
        arrayList3.add(DrawData_One_GroupBy);
        return DrawData_One_GroupBy;
    }

    class 6 implements ActionListener {
        final /* synthetic */ String val$name;
        final /* synthetic */ ArrayList val$newMtcHistoricList;

        6(String str, ArrayList arrayList) {
            this.val$name = str;
            this.val$newMtcHistoricList = arrayList;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            for (int i = 0; i < MTCMenuPage.this.mtcHistoricList.size(); i++) {
                Util.split(((MTCHistoric) MTCMenuPage.this.mtcHistoricList.get(i)).OptionnalData2, ";");
                if (this.val$name.equals(((MTCHistoric) MTCMenuPage.this.mtcHistoricList.get(i)).BillerId)) {
                    this.val$newMtcHistoricList.add((MTCHistoric) MTCMenuPage.this.mtcHistoricList.get(i));
                }
            }
            CihMBanking.sesPMTR.mtcHistoricList = this.val$newMtcHistoricList;
            MTCMenuPage.this.uiManager.NavigateToPageById(74);
        }
    }

    public Container drawMtcHistoricHeader(ArrayList arrayList, String str, String str2) {
        UIBuilder uIBuilder = new UIBuilder();
        Container container = new Container(BoxLayout.y());
        container.setUIID("SV_cnt_bottom_border");
        Container createContainer = uIBuilder.createContainer("/cihv3", "MTCCreancier");
        ((Label) uIBuilder.findByName("lblCreancierNameTypeValueV2", createContainer)).setText(str);
        ((Label) uIBuilder.findByName("lblCreancierDescriptionValueV2", createContainer)).setText("");
        Button button = (Button) uIBuilder.findByName("btnCreancierImage", createContainer);
        button.setIcon(URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(button.getIcon().getWidth(), button.getIcon().getWidth(), 12569042), false), "Normal_https://www.cihnet.co.ma" + str2, "https://www.cihnet.co.ma" + str2, URLImage.RESIZE_SCALE));
        container.add(createContainer);
        return container;
    }

    private class getFacorisService extends SyncTask {
        Container c1;

        private getFacorisService() {
            this.c1 = new Container(BoxLayout.y());
        }

        /* synthetic */ getFacorisService(MTCMenuPage mTCMenuPage, 1 r2) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public ArrayList doInBackground(Container... containerArr) {
            this.c1 = containerArr[0];
            ArrayList MTCFavoriteImpayeProcess = new MTCFavoriteBill().MTCFavoriteImpayeProcess(CihMBanking.sesPMTR.getBillerType(), MTCMenuPage.this.refIndex, true, 910063);
            Collections.sort(MTCFavoriteImpayeProcess, new ReferenceComparator(this, null));
            if (MTCFavoriteImpayeProcess.size() > 0) {
                MTCMenuPage.this.refIndex = ((MTCFavoriteBill) MTCFavoriteImpayeProcess.get(MTCFavoriteImpayeProcess.size() - 1)).ReferenceNumber;
            }
            return MTCFavoriteImpayeProcess;
        }

        private class ReferenceComparator implements Comparator {
            private ReferenceComparator() {
            }

            /* synthetic */ ReferenceComparator(getFacorisService getfacorisservice, 1 r2) {
                this();
            }

            public int compare(MTCFavoriteBill mTCFavoriteBill, MTCFavoriteBill mTCFavoriteBill2) {
                return Long.valueOf(Long.parseLong(mTCFavoriteBill.ReferenceNumber)).compareTo(Long.valueOf(Long.parseLong(mTCFavoriteBill2.ReferenceNumber))) * (-1);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onPostExecute(ArrayList arrayList) {
            MTCMenuPage.access$200(MTCMenuPage.this, arrayList, this.c1);
            this.c1.revalidate();
            if (arrayList.size() <= 0 || arrayList.size() != 5) {
                return;
            }
            MTCMenuPage.this.new getFacorisService().execute(this.c1);
        }
    }

    private void SetFavorisView(ArrayList arrayList, Container container) {
        boolean z;
        Iterator it;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        for (int i = 0; i < container.getComponentCount(); i++) {
            try {
                if (arrayList.size() > 0) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        MTCFavoriteBill mTCFavoriteBill = (MTCFavoriteBill) it2.next();
                        boolean z5 = z3;
                        if (!mTCFavoriteBill.ReferenceNumber.equals(((FavItem) container.getComponentAt(i)).favBill.ReferenceNumber)) {
                            it = it2;
                        } else if (mTCFavoriteBill.MTCImpayeList != null) {
                            ((FavItem) container.getComponentAt(i)).setFavBill(mTCFavoriteBill);
                            int size = mTCFavoriteBill.MTCImpayeList.size();
                            it = it2;
                            int i2 = 0;
                            while (i2 < mTCFavoriteBill.MTCImpayeList.size()) {
                                MTCFavoriteBill mTCFavoriteBill2 = mTCFavoriteBill;
                                if (((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(i2)).IdArticle.equals("FRAIS")) {
                                    size--;
                                }
                                i2++;
                                mTCFavoriteBill = mTCFavoriteBill2;
                            }
                            if (size > 0) {
                                ((FavItem) container.getComponentAt(i)).favBillBtn.setEnabled(true);
                                ((FavItem) container.getComponentAt(i)).favBillBtn.setIcon(this.uiManager.ressource.getImage("radio_off.PNG"));
                            } else {
                                ((FavItem) container.getComponentAt(i)).favBillBtn.setEnabled(false);
                            }
                            ((FavItem) container.getComponentAt(i)).favBillLbl.setText(size + " impayé(s)");
                            ((FavItem) container.getComponentAt(i)).favBillLbl.setUIID("mtc_lblitem_red");
                        } else {
                            it = it2;
                            ((FavItem) container.getComponentAt(i)).favBillLbl.setText(" 0 impayé(s)");
                            ((FavItem) container.getComponentAt(i)).favBillLbl.setUIID("mtc_lblitem_red");
                            ((FavItem) container.getComponentAt(i)).favBillBtn.setEnabled(false);
                        }
                        it2 = it;
                        z3 = z5;
                    }
                    z = z3;
                } else {
                    z = z3;
                    if (((FavItem) container.getComponentAt(i)).favBillLbl.getText().equals("Chargement...") || ((FavItem) container.getComponentAt(i)).favBillLbl.getText().equals("Loading...") || ((FavItem) container.getComponentAt(i)).favBillLbl.getText().equals("Cargando ...") || ((FavItem) container.getComponentAt(i)).favBillLbl.getText().equals("تحميل ...") || ((FavItem) container.getComponentAt(i)).favBillLbl.getText().equals("تحميل...")) {
                        ((FavItem) container.getComponentAt(i)).favBillLbl.setText(" 0 impayé(s)");
                        ((FavItem) container.getComponentAt(i)).favBillLbl.setUIID("mtc_lblitem_red");
                    }
                }
                if (z2 || ((FavItem) container.getComponentAt(i)).favBillLbl.getText().equals("Chargement...") || ((FavItem) container.getComponentAt(i)).favBillLbl.getText().equals("Loading...") || ((FavItem) container.getComponentAt(i)).favBillLbl.getText().equals("Cargando ...") || ((FavItem) container.getComponentAt(i)).favBillLbl.getText().equals("تحميل ...") || ((FavItem) container.getComponentAt(i)).favBillLbl.getText().equals("تحميل...") || ((FavItem) container.getComponentAt(i)).favBillLbl.getText().equals("0 impayé(s)")) {
                    z3 = z;
                } else {
                    z2 = true;
                    z3 = true;
                }
                if (z3 && !z4) {
                    this.SelectAll.setHidden(false);
                    this.cntGlbFavoris.revalidate();
                    z4 = true;
                }
            } catch (Exception unused) {
                return;
            }
        }
    }

    private class FavItem extends Container {
        MTCFavoriteBill favBill;
        Button favBillBtn;
        Label favBillLbl;

        public Button getFavBillBtn() {
            return this.favBillBtn;
        }

        public void setFavBillBtn(Button button) {
            this.favBillBtn = button;
        }

        public FavItem(MTCFavoriteBill mTCFavoriteBill) {
            this.favBill = mTCFavoriteBill;
        }

        public FavItem() {
        }

        public Label getFavBillLbl() {
            return this.favBillLbl;
        }

        public void setFavBillCnt(Label label) {
            this.favBillLbl = label;
        }

        public MTCFavoriteBill getFavBill() {
            return this.favBill;
        }

        public void setFavBill(MTCFavoriteBill mTCFavoriteBill) {
            this.favBill = mTCFavoriteBill;
        }
    }

    public FavItem drawMTCItem(MTCFavoriteBill mTCFavoriteBill, boolean z) {
        TextField textField;
        TableLayout tableLayout = new TableLayout(1, 4);
        FavItem favItem = new FavItem(mTCFavoriteBill);
        favItem.setLayout(tableLayout);
        favItem.setUIID("Container");
        favItem.setFocusable(false);
        favItem.setScrollable(false);
        TableLayout tableLayout2 = new TableLayout(1, 1);
        Container container = new Container();
        container.setLayout(tableLayout2);
        container.setUIID("MTCIconStyMTCNoMarginPading");
        container.setFocusable(false);
        container.setScrollable(false);
        Button button = new Button();
        button.setUIID("crd_btnCardImage");
        button.setIcon(this.uiManager.ressource.getImage("black_pic.png"));
        button.setIcon(URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(button.getIcon().getWidth(), button.getIcon().getWidth(), 12569042), false), "Normal_https://www.cihnet.co.ma" + mTCFavoriteBill.MTCCreancier.LogoPath, "https://www.cihnet.co.ma" + mTCFavoriteBill.MTCCreancier.LogoPath, URLImage.RESIZE_SCALE));
        button.setScrollVisible(false);
        button.setFocusable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(100);
        createConstraint.setVerticalAlign(0);
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("st_cntStatisticLeftBloc");
        container2.setFocusable(false);
        container2.setScrollVisible(false);
        Container container3 = new Container(new BoxLayout(2));
        container3.setUIID("g_cntDebitCredit");
        container3.setFocusable(false);
        container3.setScrollVisible(false);
        TextField textField2 = new TextField("" + mTCFavoriteBill.Alias);
        textField2.setUIID("ac_lblitemDetailBlue");
        textField2.setVerticalAlignment(4);
        textField2.setEditable(false);
        Label label = new Label("Chargement...");
        label.setUIID("mtc_lblitem_red");
        Button button2 = new Button();
        button2.setUIID("mtc_lblitem_red");
        button2.setVerticalAlignment(4);
        button2.setTextPosition(1);
        container3.addComponent(textField2);
        favItem.favBillLbl = label;
        container3.addComponent(favItem.favBillLbl);
        container3.addComponent(button2);
        container3.setLeadComponent(button2);
        container2.addComponent(container3);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint(0, 0);
        createConstraint2.setWidthPercentage(25);
        createConstraint2.setHorizontalAlign(4);
        createConstraint2.setVerticalAlign(0);
        favItem.addComponent(createConstraint2, container);
        TableLayout.Constraint createConstraint3 = tableLayout.createConstraint(0, 1);
        createConstraint3.setWidthPercentage(55);
        createConstraint3.setVerticalAlign(0);
        favItem.addComponent(createConstraint3, container2);
        Button button3 = new Button();
        button3.setUIID("ln_btnLoanDetailMTC");
        button3.setText("Historique");
        button3.setTextPosition(1);
        button3.setVerticalAlignment(4);
        button3.setScrollVisible(false);
        button3.setFocusable(false);
        button3.addActionListener(new 7(mTCFavoriteBill));
        button2.addActionListener(new 8(favItem));
        Button button4 = new Button();
        button4.setEnabled(false);
        button4.setPreferredW(this.widh);
        Container container4 = new Container(new BorderLayout());
        if (mTCFavoriteBill.MTCImpayeList.size() > 0) {
            button4.setIcon(this.uiManager.ressource.getImage("radio_off.PNG"));
        } else {
            button4.setIcon(this.uiManager.ressource.getImage("radio-grey.png"));
        }
        button4.setUIID("margin_0_0_1_1Center");
        button4.addActionListener(new 9(button4, favItem));
        if (z) {
            label.setText("Chargement...");
            label.setUIID("MTCKeyValueLabel");
            textField = textField2;
        } else if (mTCFavoriteBill.MTCImpayeList != null) {
            int size = mTCFavoriteBill.MTCImpayeList.size();
            int i = 0;
            while (i < mTCFavoriteBill.MTCImpayeList.size()) {
                TextField textField3 = textField2;
                if (((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(i)).IdArticle.equals("FRAIS")) {
                    size--;
                }
                i++;
                textField2 = textField3;
            }
            textField = textField2;
            if (size > 0) {
                button4.setEnabled(true);
            } else {
                button4.setEnabled(false);
            }
            label.setText(size + " impayé(s)");
        } else {
            textField = textField2;
            label.setText(" 0 impayé(s)");
        }
        Button button5 = new Button();
        button5.setUIID("crd_btnMTCFavori");
        button5.setIcon(this.uiManager.ressource.getImage("deleteBtnOrange.png"));
        button5.setScrollVisible(false);
        button5.setFocusable(false);
        button5.addActionListener(new 10(favItem));
        Button button6 = new Button();
        button6.setUIID("crd_btnMTCFavori");
        button6.setIcon(this.uiManager.ressource.getImage("editBtnBlue.png"));
        button6.setScrollVisible(false);
        button6.setFocusable(false);
        button6.addActionListener(new 11(button6, textField, container3, mTCFavoriteBill, button2));
        TableLayout tableLayout3 = new TableLayout(3, 1);
        Container container5 = new Container(tableLayout3);
        container5.setFocusable(false);
        container5.setScrollVisible(false);
        container5.addComponent(tableLayout3.createConstraint(0, 0).heightPercentage(45).widthPercentage(100), button6);
        container5.addComponent(tableLayout3.createConstraint(2, 0).heightPercentage(45).widthPercentage(100), button5);
        container5.addComponent(tableLayout3.createConstraint(1, 0).heightPercentage(10).widthPercentage(100), new Container());
        Container container6 = new Container(new GridLayout(1, 1));
        container6.setUIID("MTCIconStyMTCNoMarginPadingRight");
        container6.addComponent(container5);
        CihMBanking.sesPMTR.getBillerType();
        TableLayout.Constraint createConstraint4 = tableLayout.createConstraint(0, 2);
        createConstraint4.setWidthPercentage(10);
        createConstraint4.setVerticalAlign(4);
        favItem.addComponent(createConstraint4, new Container());
        TableLayout.Constraint createConstraint5 = tableLayout.createConstraint(0, 3);
        createConstraint5.setWidthPercentage(10);
        createConstraint5.setVerticalAlign(4);
        favItem.addComponent(createConstraint5, container6);
        favItem.setName(mTCFavoriteBill.ReferenceNumber);
        Component container7 = new Container();
        container7.setUIID("g_CntTranspMsg");
        favItem.addComponent(container7);
        favItem.favBillBtn = button4;
        container4.add("West", button4);
        container4.add("East", button);
        container.addComponent(createConstraint, container4);
        return favItem;
    }

    class 7 implements ActionListener {
        final /* synthetic */ MTCFavoriteBill val$mtcFavoriteBillN;

        7(MTCFavoriteBill mTCFavoriteBill) {
            this.val$mtcFavoriteBillN = mTCFavoriteBill;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            MTCMenuPage.this.uiManager.NavigateToPage(new MTCFavoriteHistoricPage(MTCMenuPage.this.uiManager, this.val$mtcFavoriteBillN, 28));
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ FavItem val$cntGlobalTablelayoutFBN;

        8(FavItem favItem) {
            this.val$cntGlobalTablelayoutFBN = favItem;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.setCurrentMTCImpaye(this.val$cntGlobalTablelayoutFBN.favBill.MTCImpayeList);
            CihMBanking.sesPMTR.setSelectedMTCCreancier(this.val$cntGlobalTablelayoutFBN.favBill.MTCCreancier);
            CihMBanking.sesPMTR.setSelectedMTCCreance(this.val$cntGlobalTablelayoutFBN.favBill.MTCCreance);
            if (CihMBanking.sesPMTR.getBillerType() == 0) {
                if (this.val$cntGlobalTablelayoutFBN.favBill.MTCImpayeList.size() > 0) {
                    ((B3GPage) MTCMenuPage.this.uiManager.NavigationHistory.get(MTCMenuPage.this.uiManager.NavigationHistory.size() - 1)).PageSession = this.val$cntGlobalTablelayoutFBN.favBill;
                    MTCMenuPage.this.uiManager.NavigateToPageById(23);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MTCMenuPage.this.uiManager.stateMachine, "Vous n'avez aucune facture", null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MTCMenuPage.this.uiManager.stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
        }
    }

    class 9 implements ActionListener {
        final /* synthetic */ Button val$SelectAllBtn;
        final /* synthetic */ FavItem val$cntGlobalTablelayoutFBN;

        9(Button button, FavItem favItem) {
            this.val$SelectAllBtn = button;
            this.val$cntGlobalTablelayoutFBN = favItem;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$SelectAllBtn.getIcon() == MTCMenuPage.this.uiManager.ressource.getImage("radio_on.png")) {
                this.val$SelectAllBtn.setIcon(MTCMenuPage.this.uiManager.ressource.getImage("radio_off.PNG"));
                MTCMenuPage.this.Favs.remove(this.val$cntGlobalTablelayoutFBN.favBill);
                if (MTCMenuPage.this.Favs.size() > 0) {
                    MTCMenuPage.this.PayerBtn.setHidden(false);
                    MTCMenuPage.this.PayerBtn.getParent().revalidate();
                    return;
                } else {
                    MTCMenuPage.this.PayerBtn.setHidden(true);
                    MTCMenuPage.this.PayerBtn.getParent().revalidate();
                    return;
                }
            }
            this.val$SelectAllBtn.setIcon(MTCMenuPage.this.uiManager.ressource.getImage("radio_on.png"));
            MTCMenuPage.this.Favs.add(this.val$cntGlobalTablelayoutFBN.favBill);
            MTCMenuPage.this.PayerBtn.setHidden(false);
            MTCMenuPage.this.PayerBtn.getParent().revalidate();
        }
    }

    class 10 implements ActionListener {
        final /* synthetic */ FavItem val$cntGlobalTablelayoutFBN;

        10(FavItem favItem) {
            this.val$cntGlobalTablelayoutFBN = favItem;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogFavoriteDeleteOpperationServiceNew(32, this.val$cntGlobalTablelayoutFBN.favBill, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 29, CihMBanking.sesPMTR.getBillerType());
        }
    }

    class 11 implements ActionListener {
        final /* synthetic */ Button val$btnRenameFavItem;
        final /* synthetic */ Container val$cntMTCCImpayeIndicatorFB;
        final /* synthetic */ TextField val$lblAliasValue;
        final /* synthetic */ Button val$lblCreanceImpayeList;
        final /* synthetic */ MTCFavoriteBill val$mtcFavoriteBillN;

        11(Button button, TextField textField, Container container, MTCFavoriteBill mTCFavoriteBill, Button button2) {
            this.val$btnRenameFavItem = button;
            this.val$lblAliasValue = textField;
            this.val$cntMTCCImpayeIndicatorFB = container;
            this.val$mtcFavoriteBillN = mTCFavoriteBill;
            this.val$lblCreanceImpayeList = button2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$btnRenameFavItem.getIcon().getImageName().equals("editBtnBlue.png")) {
                this.val$lblAliasValue.setUIID("ContainerBorderGray");
                this.val$lblAliasValue.setEditable(true);
                this.val$lblAliasValue.setFocusable(true);
                this.val$lblAliasValue.startEditingAsync();
                this.val$btnRenameFavItem.setIcon(MTCMenuPage.this.uiManager.ressource.getImage("confirmBtnBlue.png"));
                this.val$cntMTCCImpayeIndicatorFB.setLeadComponent(null);
            } else {
                String text = this.val$lblAliasValue.getText();
                if (!text.equals("" + this.val$mtcFavoriteBillN.Alias)) {
                    if (MTCMenuPage.this.renameFavItem(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID, this.val$mtcFavoriteBillN.ReferenceNumber, text).getStatusCode().equals("000")) {
                        this.val$mtcFavoriteBillN.Alias = text;
                    } else {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MTCMenuPage.this.uiManager.stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
                    }
                }
                this.val$lblAliasValue.stopEditing();
                this.val$lblAliasValue.setEditable(false);
                this.val$lblAliasValue.setUIID("ac_lblitemDetailBlue");
                this.val$btnRenameFavItem.setIcon(MTCMenuPage.this.uiManager.ressource.getImage("editBtnBlue.png"));
                this.val$cntMTCCImpayeIndicatorFB.setLeadComponent(this.val$lblCreanceImpayeList);
            }
            this.val$lblAliasValue.refreshTheme();
        }
    }

    public ServiceResponse renameFavItem(String str, String str2, String str3, String str4) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("SESSION_ID", str2);
        hashtable.put("pReferenceNumber", str3);
        hashtable.put("pAlias", str4);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(143);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }
}
