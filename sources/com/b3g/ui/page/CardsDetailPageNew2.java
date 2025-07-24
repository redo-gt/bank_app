package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.services.CardOperation;
import com.b3g.services.CardOpperationStatus;
import com.b3g.services.CardPlafond;
import com.b3g.services.ServiceManager;
import com.b3g.tools.CardProduct;
import com.b3g.tools.GraphicTools;
import com.b3g.tools.SyncTask;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.CardInformationAcc;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.Accordion;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UITimer;
import com.codename1.util.StringUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CardsDetailPageNew2 extends B3GPage {
    private static String URLAvantageCard = "https://avantagescartescih.ma/";
    public static boolean isCanceledAD = false;
    String BtnDureeChgmt1;
    String BtnPlafondCommerce1;
    String BtnPlafondCommerceRes;
    String BtnPlafondPaiement1;
    String BtnPlafondPaiementRes;
    String BtnPlafondRetr1;
    String BtnPlafondRetrRes;
    Container CardOperationList;
    Container CardServiceList;
    Container CntGlobalCard;
    Card Crd;
    String DureeChgmt;
    String DureeTemp;
    String IsDisabledCom;
    String IsDisabledDuree;
    String IsDisabledPaim;
    String IsDisabledRtr;
    String IsRemovedCardLimitsCom;
    String IsRemovedCardLimitsPaiem;
    String IsRemovedCardLimitsRtr;
    String PlfdComTemp;
    String PlfdPaimTemp;
    String PlfdRetrTemp;
    Accordion acc;
    int cardWidth;
    Container cntBtnFlot;
    Container cntCard;
    Container cntCardItemV3;
    Container cntGlbCardCenterCnt;
    String comChgmt;
    EncodedImage encImage;
    Container mainTabsCnt;
    String paimChgmt;
    Image placeholder;
    Container plafondsTbs;
    Container plafondsTmp;
    String retrChgmt;
    Container triangleCnt;
    private String urlWebView;
    List validProductCodesForWevViewWorldElite = Arrays.asList(new String[]{"073", "074", "075"});
    List notValidProductCodesForWevViewAutre = Arrays.asList(new String[]{"057", "048", "57", "48"});
    boolean isFirst = true;
    final int NATIONAL = 0;
    final int INTERNATIONAL = 1;
    final int ALL = 2;
    String actionRtr = "ACTIVER";
    String actionPaiem = "ACTIVER";
    String actionCom = "ACTIVER";
    String actionDuree = "LIMITER";
    ArrayList buttonList = new ArrayList();
    ArrayList accList = new ArrayList();

    static /* synthetic */ void access$100(CardsDetailPageNew2 cardsDetailPageNew2, Image image) {
        cardsDetailPageNew2.SetCardsImage(image);
    }

    static /* synthetic */ String access$200() {
        return URLAvantageCard;
    }

    static /* synthetic */ void access$300(CardsDetailPageNew2 cardsDetailPageNew2, String str) {
        cardsDetailPageNew2.setUrlWebView(str);
    }

    public CardsDetailPageNew2(Object obj, Object obj2, int i) {
        int displayWidth = (Display.getInstance().getDisplayWidth() * 90) / 100;
        this.cardWidth = displayWidth;
        Image createImage = Image.createImage(displayWidth, (displayWidth * 54) / 100, 12569042);
        this.placeholder = createImage;
        this.encImage = EncodedImage.createFromImage(createImage, false);
        this.Crd = (Card) CihMBanking.sesPMTR.objctCrdCurrent;
        this.cntCard = new Container(new LayeredLayout());
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        Container container;
        try {
            this.isFirst = true;
            Accordion accordion = new Accordion();
            this.acc = accordion;
            accordion.setUIID("accSep");
            this.acc.setScrollable(false);
            this.acc.setAutoClose(true);
            this.acc.setOpenIcon(GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), this.uiManager.ressource.getImage("blueArrowRotated.png")));
            this.acc.setCloseIcon(GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), CihMBanking.detail_arrow));
            if (!this.Crd.statusCard.equals("Y")) {
                this.acc.setFocusable(false);
                this.acc.setEnabled(false);
            }
            this.thisContainer = new Container();
            TableLayout tableLayout = new TableLayout(1, 1);
            this.thisContainer.setLayout(tableLayout);
            this.cntCardItemV3 = new Container(new BoxLayout(2));
            this.CntGlobalCard = new Container(new FlowLayout(4, 4));
            this.mainTabsCnt = new Container(new BoxLayout(2));
            Container container2 = new Container(new LayeredLayout());
            this.cntBtnFlot = new Container(new FlowLayout(4, 4));
            Container container3 = new Container(new BoxLayout(2));
            this.cntGlbCardCenterCnt = new Container(new FlowLayout(4, 4));
            Container container4 = new Container(new FlowLayout(1, 4));
            Container container5 = new Container(new FlowLayout(4, 4));
            Button button = new Button(this.uiManager.ressource.getImage("iconeMenu.png"));
            int convertToPixels = Display.getInstance().convertToPixels(4.7f);
            int convertToPixels2 = Display.getInstance().convertToPixels(2.5f);
            Label label = new Label(" " + this.Crd.cardNumber.substring(0, 4) + " " + this.Crd.cardNumber.substring(4, 8) + " " + this.Crd.cardNumber.substring(8, 12) + " " + this.Crd.cardNumber.substring(12, 16));
            Label label2 = new Label(" Exp : " + this.Crd.expirationDate.substring(3, 5) + " " + this.Crd.expirationDate.substring(5, 6) + " " + this.Crd.expirationDate.substring(8, 10) + "   ");
            if (this.Crd.productCode.equals("042") || this.Crd.productCode.equals("040") || this.Crd.categorieCarteJeune.equals("008") || this.Crd.categorieCarteJeune.equals("003") || this.Crd.categorieCarteJeune.equals("001") || this.Crd.categorieCarteJeune.equals("014")) {
                label.getStyle().setFgColor(4079166);
                label2.getStyle().setFgColor(4079166);
            } else {
                label.getStyle().setFgColor(16777215);
                label2.getStyle().setFgColor(16777215);
            }
            label.getAllStyles().setTextDecoration(32);
            label.getAllStyles().setFont(Font.createSystemFont(0, 1, 0).derive(convertToPixels, 1));
            label2.getAllStyles().setFont(Font.createSystemFont(0, 1, 8).derive(convertToPixels2, 1));
            this.cntCard.setPreferredW(this.cardWidth);
            this.cntCard.setPreferredH((this.cardWidth * 54) / 100);
            this.CntGlobalCard.setPreferredW(this.cardWidth);
            this.CntGlobalCard.setPreferredH((this.cardWidth * 54) / 100);
            label2.getAllStyles().setTextDecoration(32);
            label2.getAllStyles().setPadding(2, 0, 15, 0);
            button.setUIID("Container");
            button.getAllStyles().setMarginBottom(1);
            button.setAlignment(4);
            button.setVerticalAlignment(4);
            Label label3 = new Label("  " + this.Crd.entitled);
            label3.setUIID("SimLabelOrange");
            label3.getAllStyles().setFont(Font.createSystemFont(0, 1, 0));
            label3.getAllStyles().setPadding(1, 1, 2, 0);
            this.cntCardItemV3.setUIID("Container");
            if (CihMBanking.sesPMTR.cardfromHom) {
                this.thisContainer.setUIID("gl_GloabalContainerV2");
            } else {
                this.thisContainer.setUIID("Container");
            }
            this.cntCard.setScrollableY(false);
            this.CntGlobalCard.setScrollableY(false);
            this.CntGlobalCard.setUIID("g_CntTranspMsg");
            this.cntCardItemV3.setScrollableY(false);
            this.mainTabsCnt.setScrollableY(false);
            this.mainTabsCnt.setScrollVisible(false);
            this.mainTabsCnt.getAllStyles().setMargin(0, 0, 1, 1);
            button.getUnselectedStyle().setBorder(RoundBorder.create().shadowOpacity(90));
            this.cntGlbCardCenterCnt.getAllStyles().setMargin(12, 0, 3, 3);
            container5.addComponent(label);
            container4.addComponent(label2);
            if (this.Crd.statusCardCVV.equals("Y")) {
                this.triangleCnt = new Container(new FlowLayout(4, 4));
                Label label4 = new Label();
                label4.setIcon(this.uiManager.ressource.getImage("cvvOff.png"));
                label4.setAlignment(4);
                this.triangleCnt.add(label4);
                container = container3;
                container.addComponent(this.triangleCnt);
            } else {
                container = container3;
            }
            container.addComponent(container5);
            container.addComponent(container4);
            this.cntGlbCardCenterCnt.addComponent(container);
            if (!this.Crd.imagPath.equals(" ")) {
                Display.getInstance().callSerially(new 1());
            } else {
                this.cntCard.setUIID("cardBg");
            }
            getTabsCardCnt();
            this.mainTabsCnt.getAllStyles().setFgColor(16777215);
            this.cntBtnFlot.addComponent(button);
            this.CntGlobalCard.add(this.cntCard);
            this.cntCardItemV3.add(label3);
            this.cntCardItemV3.add(this.CntGlobalCard);
            this.cntCardItemV3.add(this.mainTabsCnt);
            this.thisContainer.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(100), this.cntCardItemV3);
            container2.add(LayeredLayout.encloseIn(FlowLayout.encloseCenterBottom(this.cntBtnFlot)));
            this.thisContainer.setName("thisContainer");
            container2.setPreferredH(this.thisContainer.getPreferredH());
            Settings.setNightMode(this.thisContainer, 0);
            Display.getInstance().callSerially(new 2());
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
    }

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            new getCardsService(CardsDetailPageNew2.this, null).execute(CardsDetailPageNew2.this.cntCard);
        }
    }

    class 2 implements Runnable {
        2() {
        }

        public void run() {
            new UITimer(new CardsDetailPageNew2$2$$ExternalSyntheticLambda0(this)).schedule(800, false, CardsDetailPageNew2.this.thisContainer.getComponentForm());
        }

        /* synthetic */ void lambda$run$0$com-b3g-ui-page-CardsDetailPageNew2$2() {
            if (CihMBanking.sesPMTR.pagecardLoad) {
                CihMBanking.sesPMTR.pagecardLoad = false;
                if (!CardsDetailPageNew2.this.Crd.virtualStatus.equals("0") && CardsDetailPageNew2.this.Crd.statusCard.equals("N") && CardsDetailPageNew2.this.Crd.cardActivationStatus.equals("N")) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationService2(112, CardsDetailPageNew2.this.Crd, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, "Veuillez confirmer l’activation de votre carte virtuelle");
                }
            }
        }
    }

    class 3 implements ScrollListener {
        final /* synthetic */ Container val$listCont;

        3(Container container) {
            this.val$listCont = container;
        }

        public void scrollChanged(int i, int i2, int i3, int i4) {
            if (i2 >= 0) {
                try {
                    if (CardsDetailPageNew2.this.CntGlobalCard.getPreferredH() > 0) {
                        CardsDetailPageNew2.this.CntGlobalCard.setPreferredH(((CardsDetailPageNew2.this.cardWidth * 54) / 100) - i2);
                        if (CardsDetailPageNew2.this.CntGlobalCard.getPreferredH() < 0 || CardsDetailPageNew2.this.CardOperationList.getHeight() - this.val$listCont.getHeight() <= i2) {
                            return;
                        }
                        if (CardsDetailPageNew2.this.CardOperationList.getHeight() - this.val$listCont.getHeight() == i2) {
                            CardsDetailPageNew2.this.CntGlobalCard.setPreferredH(((CardsDetailPageNew2.this.cardWidth * 54) / 100) - CardsDetailPageNew2.this.CntGlobalCard.getPreferredH());
                        }
                        CardsDetailPageNew2.this.cntCardItemV3.revalidate();
                        return;
                    }
                    if (i2 <= (CardsDetailPageNew2.this.cardWidth * 54) / 100) {
                        CardsDetailPageNew2.this.CntGlobalCard.setPreferredH(((CardsDetailPageNew2.this.cardWidth * 54) / 100) - i2);
                        if (CardsDetailPageNew2.this.CntGlobalCard.getPreferredH() < 0 || CardsDetailPageNew2.this.CardOperationList.getHeight() - this.val$listCont.getHeight() < i2) {
                            return;
                        }
                        CardsDetailPageNew2.this.cntCardItemV3.revalidate();
                    }
                } catch (Throwable unused) {
                }
            }
        }
    }

    private void onScrollAnimation(Container container) {
        container.addScrollListener(new 3(container));
    }

    private void getRecalculPinNew(Card card) {
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            CihMBanking.sesPMTR.objctCrdCurrent = card;
            this.uiManager.NavigateToPage(new RecalculePinPageV1(this.uiManager, card, 47));
            this.cntBtnFlot.setHidden(false);
        }
    }

    private void getRecalculPin(Card card, Dialog dialog) {
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            CihMBanking.sesPMTR.objctCrdCurrent = card;
            this.uiManager.NavigateToPage(new RecalculePinPageV1(this.uiManager, card, 47));
            this.cntBtnFlot.setHidden(false);
            dialog.dispose();
        }
    }

    private int getIter(String str) {
        if (str.equals("MS")) {
            return 2;
        }
        if (str.equals("E")) {
            return 3;
        }
        return str.equals("CB") ? 4 : 1;
    }

    private void getCardPlafondCnt(Card card, Dialog dialog) {
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            CihMBanking.sesPMTR.objctCrdCurrent = card;
            this.uiManager.NavigateToPageById(143);
            this.cntBtnFlot.setHidden(false);
            dialog.dispose();
        }
    }

    private void getOperationListCnt(Card card, Dialog dialog) {
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            CihMBanking.sesPMTR.crdNumberSession = card.plainCardNumber;
            CihMBanking.sesPMTR.objctCrdCurrent = card;
            CihMBanking.sesPMTR.IsOperationCarteClick = true;
            CihMBanking.sesPMTR.IsMsDotationClick = false;
            this.uiManager.NavigateToPageById(14);
            this.cntBtnFlot.setHidden(false);
            dialog.dispose();
        }
    }

    private void getOperationDotListCnt(Card card, Dialog dialog) {
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            CihMBanking.sesPMTR.crdNumberSession = card.plainCardNumber;
            CihMBanking.sesPMTR.objctCrdCurrent = card;
            CihMBanking.sesPMTR.IsMsDotationClick = true;
            CihMBanking.sesPMTR.IsOperationCarteClick = false;
            this.uiManager.NavigateToPageById(14);
            this.cntBtnFlot.setHidden(false);
            dialog.dispose();
        }
    }

    private void getRechrgCardCnt(Card card, Dialog dialog) {
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            CihMBanking.sesPMTR.objctCrdCurrent = card;
            this.uiManager.NavigateToPageById(9);
            this.cntBtnFlot.setHidden(false);
            dialog.dispose();
        }
    }

    private void getDebtCardCnt(Card card, Dialog dialog) {
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            CihMBanking.sesPMTR.objctCrdCurrent = card;
            this.uiManager.NavigateToPage(new PrepaidCardDebit(this.uiManager, card, 47));
            this.cntBtnFlot.setHidden(false);
            dialog.dispose();
        }
    }

    public Container GetOperationContainer(Object obj) {
        Card card = (Card) obj;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = card.CardOperationList;
        for (int i = 0; i < arrayList2.size(); i++) {
            ((CardOperation) arrayList2.get(i)).groupKey = ((CardOperation) arrayList2.get(i)).transactionDate;
            arrayList.add((B3gService) arrayList2.get(i));
        }
        this.CardOperationList = new Container(new BoxLayout(2));
        if (arrayList.size() > 0) {
            this.uiManager.Draw_GroupBy(this.CardOperationList, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 4, card);
        } else {
            this.CardOperationList.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune opération"));
        }
        return this.CardOperationList;
    }

    public Container GetAvailableBalanceContainer(Card card) {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AvailableBalanceItem");
        createContainer.getStyle().setMarginUnit(0, 0, 0, 0);
        createContainer.getStyle().setMargin(3, 0, 0, 0);
        createContainer.revalidate();
        createContainer.setScrollableY(false);
        createContainer.setFocusable(false);
        this.uiManager.stateMachine.findBtnNationalECOMBalanceAvailableValueV2(createContainer).setText(card.nationalEComBalanceAvailable);
        this.uiManager.stateMachine.findBtnNationalPaimentBalanceAvailableValueV2(createContainer).setText(card.nationalPaymentBalanceAvailable);
        this.uiManager.stateMachine.findBtnNationalRetriveBalanceAvailableValueV2(createContainer).setText(card.nationalRetriveBalanceAvailable);
        this.uiManager.stateMachine.findBtnInternationalECOMBalanceAvailableValueV2(createContainer).setText(card.internationalEComBalanceAvailable);
        this.uiManager.stateMachine.findBtnInternationalPaimentBalanceAvailableValueV2(createContainer).setText(card.internationalPaymentBalanceAvailable);
        this.uiManager.stateMachine.findBtnInternationalRetriveBalanceAvailableValueV2(createContainer).setText(card.internationalRetriveBalanceAvailable);
        return createContainer;
    }

    private class getCardsService extends SyncTask {
        Container c1;

        private getCardsService() {
        }

        /* synthetic */ getCardsService(CardsDetailPageNew2 cardsDetailPageNew2, 1 r2) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Image doInBackground(Container... containerArr) {
            Container container = containerArr[0];
            this.c1 = container;
            container.add(Loading());
            this.c1.revalidate();
            if (!CardsDetailPageNew2.this.Crd.statusCard.equals("Y") || CardsDetailPageNew2.this.Crd.statusCardCVV.equals("Y")) {
                return URLImage.createToStorage(CardsDetailPageNew2.this.encImage, "https://www.cihnet.co.ma/Portals/_default/Skins/CIH/images/newSkinImgs/cardsNonDisponible.png", "https://www.cihnet.co.ma/Portals/_default/Skins/CIH/images/newSkinImgs/cardsNonDisponible.png", URLImage.RESIZE_SCALE);
            }
            return URLImage.createToStorage(CardsDetailPageNew2.this.encImage, "https://www.cihnet.co.ma" + CardsDetailPageNew2.this.Crd.imagPath, "https://www.cihnet.co.ma" + CardsDetailPageNew2.this.Crd.imagPath, URLImage.RESIZE_SCALE);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onPostExecute(Image image) {
            CardsDetailPageNew2.this.cntCard.removeAll();
            CardsDetailPageNew2.access$100(CardsDetailPageNew2.this, image);
            CardsDetailPageNew2.this.cntCard.revalidate();
        }

        private Container Loading() {
            Container container = new Container(new FlowLayout(4, 4));
            container.getAllStyles().setMarginBottom(2);
            Label label = new Label("Chargement...");
            label.setUIID("lbl_regular");
            container.add(label);
            return container;
        }
    }

    private void SetCardsImage(Image image) {
        Container container = new Container(new LayeredLayout());
        try {
            new UITimer(new CardsDetailPageNew2$$ExternalSyntheticLambda2(container, image)).schedule(90, false, this.uiManager.currentForm);
            new UITimer(new CardsDetailPageNew2$$ExternalSyntheticLambda3(this, container)).schedule(130, false, this.uiManager.currentForm);
            new UITimer(new CardsDetailPageNew2$$ExternalSyntheticLambda4(this)).schedule(80, false, this.uiManager.currentForm);
            this.cntCard.add(container);
        } catch (Exception unused) {
        }
    }

    static /* synthetic */ void lambda$SetCardsImage$0(Container container, Image image) {
        container.getAllStyles().setBgImage(image);
    }

    /* synthetic */ void lambda$SetCardsImage$1$com-b3g-ui-page-CardsDetailPageNew2(Container container) {
        container.addComponent(this.cntGlbCardCenterCnt);
    }

    /* synthetic */ void lambda$SetCardsImage$2$com-b3g-ui-page-CardsDetailPageNew2() {
        try {
            if (this.Crd.statusCardCVV.equals("Y")) {
                this.triangleCnt.setHidden(false);
            } else {
                this.triangleCnt.setHidden(true);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:8:0x00d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getTabsCardCnt() {
        /*
            Method dump skipped, instructions count: 410
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.CardsDetailPageNew2.getTabsCardCnt():void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private Container GetserviceContainer() {
        char c;
        this.CardServiceList = new Container(new BorderLayout());
        Container container = new Container(BoxLayout.y());
        Container container2 = new Container();
        container2.setUIID("ln_btnLoanDetailMTC");
        Container container3 = new Container();
        container2.setLayout(new BorderLayout());
        container3.setLayout(new FlowLayout(4, 4));
        Button button = new Button();
        button.setUIID("aficherCarteLbl");
        button.setIcon(this.uiManager.ressource.getImage("Avantage.png"));
        Button button2 = new Button();
        button2.setUIID("Container");
        button2.setIcon(this.uiManager.ressource.getImage("retourAntg.png"));
        button2.getAllStyles().setMarginUnit(1);
        button2.getAllStyles().setMargin(0, 0, 1, 1);
        container3.add(button);
        container2.add("Center", container3);
        container2.add("East", button2);
        button2.setFocusable(false);
        container2.setLeadComponent(button);
        container2.getAllStyles().setMarginUnit(1);
        container2.getAllStyles().setMargin(0.0f, 0.0f, 1.5f, 1.5f);
        Button button3 = new Button();
        button3.setIcon(GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), this.uiManager.ressource.getImage("retrait actif.png")));
        button3.setName("withdraw.PNG");
        this.buttonList.add(button3);
        Button button4 = new Button();
        button4.setIcon(GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), this.uiManager.ressource.getImage("paiement-par-carte-active.png")));
        this.buttonList.add(button4);
        button4.setName("paiement-par-carte-active.png");
        Button button5 = new Button();
        button5.setIcon(GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), this.uiManager.ressource.getImage("paiement-sur-internet-active.png")));
        this.buttonList.add(button5);
        button5.setName("paiement-sur-internet-active.png");
        button.addActionListener(new 4());
        String str = this.Crd.productCode;
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case 47729:
                if (str.equals("023")) {
                    c2 = 0;
                    break;
                }
                break;
            case 47730:
                if (str.equals("024")) {
                    c2 = 1;
                    break;
                }
                break;
            case 47732:
                if (str.equals("026")) {
                    c2 = 2;
                    break;
                }
                break;
            case 47766:
                if (str.equals("039")) {
                    c2 = 3;
                    break;
                }
                break;
            case 47788:
                if (str.equals("040")) {
                    c2 = 4;
                    break;
                }
                break;
            case 47794:
                if (str.equals("046")) {
                    c2 = 5;
                    break;
                }
                break;
            case 47795:
                if (str.equals("047")) {
                    c = 6;
                    c2 = c;
                    break;
                }
                break;
            case 47797:
                if (str.equals("049")) {
                    c = 7;
                    c2 = c;
                    break;
                }
                break;
            case 47819:
                if (str.equals("050")) {
                    c = '\b';
                    c2 = c;
                    break;
                }
                break;
            case 47826:
                if (str.equals("057")) {
                    c = '\t';
                    c2 = c;
                    break;
                }
                break;
            case 47890:
                if (str.equals("079")) {
                    c = '\n';
                    c2 = c;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                setUrlWebView(URLAvantageCard);
                button.setText("  Avantages Carte ");
                container.add(BoxLayout.encloseY(container2, this.acc));
                createAccordion("Paiement par carte", "Activez ou désactivez les paiements TPE chez les commerçants au Maroc et à l’international\u200b", button4, "PaiementTPE", 0);
                break;
            case 1:
            case 5:
                setUrlWebView(URLAvantageCard);
                button.setText("  Avantages Carte ");
                container.add(BoxLayout.encloseY(container2, this.acc, getRechargeCarteCnt(), getDechargeCarteCnt("BINATNA")));
                createAccordion("Retrait", "Activez ou désactivez les retraits des guichets CIH bank et des autres banques", button3, "RetraitGAB", 0);
                createAccordion("Paiement par carte", "Activez ou désactivez les paiements TPE chez les commerçants au Maroc et à l’international\u200b", button4, "PaiementTPE", 0);
                break;
            case 2:
                setUrlWebView(URLAvantageCard);
                button.setText("  Avantages Carte ");
                container.add(BoxLayout.encloseY(container2, this.acc, getRechargeCarteCnt(), getDechargeCarteCnt("ALMOUSSAFIR")));
                createAccordion("Retrait", "Activez ou désactivez les retraits des guichets CIH bank et des autres banques", button3, "RetraitGAB", 1);
                createAccordion("Paiement par carte", "Activez ou désactivez les paiements TPE chez les commerçants au Maroc et à l’international\u200b", button4, "PaiementTPE", 1);
                createAccordion("Paiement sur internet", "Activez ou désactivez les paiements e-commerce\u200b\u200b", button5, "PaiementEC", 1);
                break;
            case 3:
                setUrlWebView(URLAvantageCard);
                button.setText("  Avantages Carte ");
                container.add(BoxLayout.encloseY(container2, this.acc, getSansContactCnt(), getRechargeCarteCnt(), getDechargeCarteCnt("ALMOUSSAFIR")));
                createAccordion("Retrait", "Activez ou désactivez les retraits des guichets CIH bank et des autres banques", button3, "RetraitGAB", 1);
                createAccordion("Paiement par carte", "Activez ou désactivez les paiements TPE chez les commerçants au Maroc et à l’international\u200b", button4, "PaiementTPE", 1);
                createAccordion("Paiement sur internet", "Activez ou désactivez les paiements e-commerce\u200b\u200b", button5, "PaiementEC", 1);
                break;
            case 4:
                setUrlWebView(URLAvantageCard);
                button.setText("  Avantages Carte ");
                container.add(BoxLayout.encloseY(container2, this.acc, getRechargeCarteCnt(), getDechargeCarteCnt("E-SHOPPING")));
                createAccordion("Paiement sur internet", "Activez ou désactivez les paiements e-commerce\u200b\u200b", button5, "PaiementEC", 1);
                break;
            case 6:
                setUrlWebView(URLAvantageCard);
                button.setText("  Avantages Carte ");
                container.add(BoxLayout.encloseY(container2, this.acc, getSansContactCnt(), getRenvoiCodeCnt(this.Crd)));
                createAccordion("Retrait", "Activez ou désactivez les retraits des guichets CIH bank et des autres banques", button3, "RetraitGAB", 2);
                createAccordion("Paiement par carte", "Activez ou désactivez les paiements TPE chez les commerçants au Maroc et à l’international\u200b", button4, "PaiementTPE", 2);
                createAccordion("Paiement sur internet", "Activez ou désactivez les paiements e-commerce\u200b\u200b", button5, "PaiementEC", 2);
                break;
            case 7:
                setUrlWebView(URLAvantageCard);
                button.setText("  Avantages Carte ");
                container.add(BoxLayout.encloseY(container2, this.acc, getSansContactCnt(), getDechargeCarteCnt("ALMOUSSAFIR")));
                createAccordion("Retrait", "Activez ou désactivez les retraits des guichets CIH bank et des autres banques", button3, "RetraitGAB", 2);
                createAccordion("Paiement par carte", "Activez ou désactivez les paiements TPE chez les commerçants au Maroc et à l’international\u200b", button4, "PaiementTPE", 2);
                createAccordion("Paiement sur internet", "Activez ou désactivez les paiements e-commerce\u200b\u200b", button5, "PaiementEC", 2);
                break;
            case '\b':
                setUrlWebView(URLAvantageCard);
                button.setText("  Avantages Carte ");
                container.add(BoxLayout.encloseY(container2, this.acc, getSansContactCnt(), getRenvoiCodeCnt(this.Crd)));
                createAccordion("Retrait", "Activez ou désactivez les retraits des guichets CIH bank et des autres banques", button3, "RetraitGAB", 2);
                createAccordion("Paiement par carte", "Activez ou désactivez les paiements TPE chez les commerçants au Maroc et à l’international\u200b", button4, "PaiementTPE", 2);
                createAccordion("Paiement sur internet", "Activez ou désactivez les paiements e-commerce\u200b\u200b", button5, "PaiementEC", 2);
                break;
            case '\t':
                container.add(BoxLayout.encloseY(this.acc, getRenvoiCodeCnt(this.Crd)));
                createAccordion("Retrait", "Activez ou désactivez les retraits des guichets CIH bank et des autres banques", button3, "RetraitGAB", 0);
                break;
            case '\n':
                setUrlWebView(URLAvantageCard);
                button.setText("  Avantages Carte ");
                container.add(BoxLayout.encloseY(container2, this.acc, getSansContactCnt()));
                createAccordion("Paiement par carte", "Activez ou désactivez les paiements TPE chez les commerçants au Maroc et à l’international\u200b", button4, "PaiementTPE", 2);
                createAccordion("Paiement sur internet", "Activez ou désactivez les paiements e-commerce\u200b\u200b", button5, "PaiementEC", 2);
                break;
            default:
                if (this.validProductCodesForWevViewWorldElite.contains(this.Crd.productCode)) {
                    setUrlWebView("https://www.priceless.com/cih-benefits?ac=cih");
                    button.setText("  Avantage World Elite ");
                    container.add(BoxLayout.encloseY(container2, this.acc, getSansContactCnt(), getRenvoiCodeCnt(this.Crd)));
                } else if (this.notValidProductCodesForWevViewAutre.contains(this.Crd.productCode)) {
                    container.add(BoxLayout.encloseY(this.acc, getSansContactCnt(), getRenvoiCodeCnt(this.Crd)));
                } else {
                    setUrlWebView(URLAvantageCard);
                    button.setText("  Avantages Carte ");
                    container.add(BoxLayout.encloseY(container2, this.acc, getSansContactCnt(), getRenvoiCodeCnt(this.Crd)));
                }
                createAccordion("Retrait", "Activez ou désactivez les retraits des guichets CIH bank et des autres banques", button3, "RetraitGAB", 2);
                createAccordion("Paiement par carte", "Activez ou désactivez les paiements TPE chez les commerçants au Maroc et à l’international\u200b", button4, "PaiementTPE", 2);
                if (!this.Crd.productCode.equals("024") && !this.Crd.productCode.equals("046")) {
                    createAccordion("Paiement sur internet", "Activez ou désactivez les paiements e-commerce\u200b\u200b", button5, "PaiementEC", 2);
                    break;
                }
                break;
        }
        if (this.Crd.virtualStatus.equals("1") && CardProduct.fromCode(this.Crd.productCode)) {
            container.add(getPhysiqueCartCnt(this.Crd));
        }
        this.CardServiceList.add("Center", container);
        this.CardServiceList.add("South", getOppositionCnt());
        return this.CardServiceList;
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String str = CardsDetailPageNew2.this.Crd.productCode;
            str.hashCode();
            switch (str) {
                case "023":
                    CardsDetailPageNew2.access$300(CardsDetailPageNew2.this, CardsDetailPageNew2.access$200());
                    break;
                case "024":
                case "046":
                    CardsDetailPageNew2.access$300(CardsDetailPageNew2.this, CardsDetailPageNew2.access$200());
                    break;
                case "026":
                    CardsDetailPageNew2.access$300(CardsDetailPageNew2.this, CardsDetailPageNew2.access$200());
                    break;
                case "039":
                    CardsDetailPageNew2.access$300(CardsDetailPageNew2.this, CardsDetailPageNew2.access$200());
                    break;
                case "040":
                case "079":
                    CardsDetailPageNew2.access$300(CardsDetailPageNew2.this, CardsDetailPageNew2.access$200());
                    break;
                case "047":
                    CardsDetailPageNew2.access$300(CardsDetailPageNew2.this, CardsDetailPageNew2.access$200());
                    break;
                case "049":
                    CardsDetailPageNew2.access$300(CardsDetailPageNew2.this, CardsDetailPageNew2.access$200());
                    break;
                case "050":
                    CardsDetailPageNew2.access$300(CardsDetailPageNew2.this, CardsDetailPageNew2.access$200());
                    break;
                case "083":
                    CardsDetailPageNew2.access$300(CardsDetailPageNew2.this, "https://www.priceless.com/CIH-World?ac=CIHWorld");
                    break;
                default:
                    if (CardsDetailPageNew2.this.validProductCodesForWevViewWorldElite.contains(CardsDetailPageNew2.this.Crd.productCode)) {
                        CardsDetailPageNew2.access$300(CardsDetailPageNew2.this, "https://www.priceless.com/cih-benefits?ac=cih");
                        break;
                    } else if (!CardsDetailPageNew2.this.notValidProductCodesForWevViewAutre.contains(CardsDetailPageNew2.this.Crd.productCode)) {
                        CardsDetailPageNew2.access$300(CardsDetailPageNew2.this, CardsDetailPageNew2.access$200());
                        break;
                    }
                    break;
            }
            System.out.println("URL WEBVIEW : " + CihMBanking.sesPMTR.urlAvantageWebView);
            CardsDetailPageNew2.this.uiManager.NavigateToPageById(165);
        }
    }

    public Component getBlocageContainer() {
        Object obj;
        Object obj2;
        Object obj3;
        Container container = new Container(new BorderLayout());
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setPadding(1, 1, 1, 1);
        container.getAllStyles().setMargin(1.5f, 1.5f, 1.0f, 1.0f);
        container.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(1010582364).strokeOpacity(120).stroke(new Stroke(2.0f, 2, 0, 1.0f)));
        container.getAllStyles().setBgColor(16777215);
        container.getAllStyles().setBgTransparency(255);
        Label label = new Label();
        label.getAllStyles().setMargin(8, 8, 1, 1);
        label.setIcon(GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), this.uiManager.ressource.getImage("switch2.png")));
        OnOffSwitch onOffSwitch = new OnOffSwitch();
        Label label2 = new Label();
        if (this.Crd.statusCard.equals("Y")) {
            onOffSwitch.setValue(true);
            label2.setText("Carte débloquée");
        } else if (!this.Crd.statusCard.equals("O")) {
            onOffSwitch.setValue(false);
            label2.setText("Carte bloquée");
        }
        onOffSwitch.getAllStyles().setMargin(8, 8, 0, 0);
        onOffSwitch.setUIID("Container");
        onOffSwitch.setOff(" ");
        onOffSwitch.setOn(" ");
        Container container2 = new Container(new FlowLayout(4, 4));
        container2.getAllStyles().setMargin(0.5f, 0.5f, 0.0f, 0.0f);
        container2.getAllStyles().setMarginUnit(2);
        container2.getAllStyles().setMarginLeft(2);
        container2.getAllStyles().setMarginRight(2);
        container2.add(label);
        Container container3 = new Container(new FlowLayout(2, 2));
        container3.getAllStyles().setMarginUnit(2);
        container3.getAllStyles().setMargin(0.5f, 0.5f, 0.0f, 0.0f);
        container3.getAllStyles().setMarginLeft(2);
        container3.getAllStyles().setMarginRight(2);
        container3.add(onOffSwitch);
        Container container4 = new Container(new BoxLayout(2));
        container4.getAllStyles().setMargin(0, 0, 0, 0);
        onOffSwitch.addActionListener(new 5(onOffSwitch));
        container4.add(label2);
        container.add("East", container3);
        container.add("West", container2);
        Label label3 = new Label();
        label3.setText("Blocage");
        label3.setUIID("Container");
        Font derive = Font.createTrueTypeFont("Aller", "Aller.ttf").derive(Display.getInstance().convertToPixels(2.0f), 0);
        label2.getAllStyles().setFont(derive);
        label2.getAllStyles().setFgColor(7368816);
        label3.getAllStyles().setAlignment(1);
        Container container5 = new Container(new BoxLayout(2));
        container5.getAllStyles().setMarginUnit(1);
        container5.add(label3);
        container.add("Center", BoxLayout.encloseY(container5, container4));
        FlowLayout flowLayout = new FlowLayout(1, 0);
        flowLayout.setFillRows(true);
        Container container6 = new Container(flowLayout);
        container6.add(container);
        if (this.Crd.productCode.equals("024") || this.Crd.productCode.equals("046") || this.Crd.productCode.equals("040") || this.Crd.productCode.equals("026") || this.Crd.productCode.equals("039")) {
            container6.removeAll();
            TableLayout tableLayout = new TableLayout(1, 2);
            TableLayout.Constraint createConstraint = tableLayout.createConstraint();
            TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
            createConstraint.setWidthPercentage(60);
            createConstraint2.setWidthPercentage(40);
            container6.setLayout(tableLayout);
            Container container7 = new Container(new BorderLayout());
            obj = "Y";
            container7.getAllStyles().setPaddingUnit(1);
            container7.getAllStyles().setMarginUnit(1);
            container7.getAllStyles().setPadding(1, 1, 1, 1);
            container7.getAllStyles().setMargin(1.5f, 1.5f, 1.0f, 1.0f);
            obj2 = "040";
            container7.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(1010582364).strokeOpacity(120).stroke(new Stroke(2.0f, 2, 0, 1.0f)));
            container7.getAllStyles().setBgColor(16777215);
            container7.getAllStyles().setBgTransparency(255);
            Component.setSameHeight(container, container7);
            Label label4 = new Label();
            label4.setText("Solde disponible");
            label4.setUIID("Container");
            label4.getAllStyles().setAlignment(1);
            Label label5 = new Label();
            label5.getAllStyles().setFont(derive);
            label5.getAllStyles().setFgColor(1817145);
            label5.setText(this.Crd.BalanceCard);
            container7.add("Center", BoxLayout.encloseY(label4, label5));
            container6.add(createConstraint, container);
            container6.add(createConstraint2, container7);
        } else {
            obj2 = "040";
            obj = "Y";
        }
        Container container8 = new Container(BoxLayout.y());
        CardInformationAcc cardInformationAcc = new CardInformationAcc();
        SpanLabel spanLabel = new SpanLabel("Paiement sur internet momentanément bloqué".toUpperCase());
        spanLabel.setTextUIID("Container");
        spanLabel.setUIID("Container");
        spanLabel.getTextAllStyles().setFont(derive);
        spanLabel.getTextAllStyles().setFgColor(14767974);
        spanLabel.getTextAllStyles().setAlignment(4);
        spanLabel.getAllStyles().setPaddingUnit(1);
        spanLabel.getAllStyles().setPadding(2, 0, 0, 0);
        Button button = new Button("Déverrouiller le paiement sur internet\u200b".toUpperCase());
        button.setUIID("GoButton");
        button.getAllStyles().setFont(Font.createTrueTypeFont("Aller", "Aller.ttf").derive(Display.getInstance().convertToPixels(2.5f), 0));
        button.addActionListener(new 6());
        if (this.Crd.productCode.equals(obj2) || ((this.Crd.productCode.equals("067") || this.Crd.productCode.equals("079")) && this.Crd.virtualStatus.equals("1"))) {
            obj3 = obj;
            if (this.Crd.statusCard.equals(obj3)) {
                Font.createTrueTypeFont("Aller", "Aller.ttf").derive(Display.getInstance().convertToPixels(2.0f), 0);
                if (this.Crd.statusCardCVV.equals(obj3)) {
                    container8.add(spanLabel).add(container6).add(cardInformationAcc.getAccordion()).add(button);
                } else {
                    container8.add(container6).add(cardInformationAcc.getAccordion());
                }
                return LayeredLayout.encloseIn(container8);
            }
        } else {
            obj3 = obj;
        }
        if (this.Crd.statusCardCVV.equals(obj3) && this.Crd.statusCard.equals(obj3)) {
            return container8.add(spanLabel).add(container6).add(button);
        }
        return LayeredLayout.encloseIn(container6);
    }

    class 5 implements ActionListener {
        final /* synthetic */ OnOffSwitch val$pOnOffSwitch;

        5(OnOffSwitch onOffSwitch) {
            this.val$pOnOffSwitch = onOffSwitch;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardsDetailPageNew2.isCanceledAD = false;
            CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
            if (!this.val$pOnOffSwitch.isValue()) {
                cardOpperationStatus.newCardStatus = "D";
            } else {
                cardOpperationStatus.newCardStatus = "Y";
            }
            cardOpperationStatus.motif = " ";
            cardOpperationStatus.plainCardNumber = CardsDetailPageNew2.this.Crd.plainCardNumber;
            cardOpperationStatus.cardNumber = CardsDetailPageNew2.this.Crd.cardNumber;
            cardOpperationStatus.cardOpperationStatus = CardsDetailPageNew2.this.Crd.statusCard;
            cardOpperationStatus.pClientCardHolder = CardsDetailPageNew2.this.Crd.radicalClient;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationADOService(25, cardOpperationStatus, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, cardOpperationStatus.newCardStatus.equals("D") ? 300038 : 300037);
            if (CardsDetailPageNew2.isCanceledAD) {
                this.val$pOnOffSwitch.removeActionListener(this);
                this.val$pOnOffSwitch.setValue(!r7.isValue());
                this.val$pOnOffSwitch.revalidate();
                this.val$pOnOffSwitch.getParent().revalidate();
                this.val$pOnOffSwitch.addActionListener(this);
            }
        }
    }

    class 6 implements ActionListener {
        6() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardsDetailPageNew2.isCanceledAD = false;
            CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
            cardOpperationStatus.newCardStatus = "D";
            cardOpperationStatus.motif = " ";
            cardOpperationStatus.plainCardNumber = CardsDetailPageNew2.this.Crd.plainCardNumber;
            cardOpperationStatus.cardNumber = CardsDetailPageNew2.this.Crd.cardNumber;
            cardOpperationStatus.cardOpperationStatus = "Y";
            cardOpperationStatus.pClientCardHolder = CardsDetailPageNew2.this.Crd.radicalClient;
            CihMBanking.sesPMTR.cardfromHom = true;
            CihMBanking.sesPMTR.objctCrdCurrent = CardsDetailPageNew2.this.Crd;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationService(111, cardOpperationStatus, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 10825);
        }
    }

    private void createAccordion(String str, String str2, Button button, String str3, int i) {
        OnOffSwitch onOffSwitch;
        OnOffSwitch onOffSwitch2;
        TableLayout tableLayout = new TableLayout(1, 3);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(12);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(80);
        button.setUIID("Container");
        Label label = new Label(str);
        label.setUIID("Container");
        TextArea textArea = new TextArea();
        textArea.setUIID("lbl_black_very_small");
        textArea.getAllStyles().setPaddingUnit(1);
        textArea.getAllStyles().setPadding(1, 1, 10, 1);
        textArea.setText(str2);
        textArea.setEditable(false);
        Container container = new Container(tableLayout);
        container.add(createConstraint, FlowLayout.encloseCenter(button));
        container.add(createConstraint2, FlowLayout.encloseLeftMiddle(label));
        Container container2 = new Container(BoxLayout.y());
        Container container3 = new Container(new BorderLayout());
        Container container4 = new Container(new BorderLayout());
        Font derive = Font.createTrueTypeFont("Aller", "Aller.ttf").derive(Display.getInstance().convertToPixels(2.0f), 0);
        Label label2 = new Label("Au Maroc");
        label2.getAllStyles().setFont(derive);
        label2.getAllStyles().setFgColor(7368816);
        Label label3 = new Label("À l’international");
        label3.getAllStyles().setFont(derive);
        label3.getAllStyles().setFgColor(7368816);
        onOffSwitch = new OnOffSwitch();
        onOffSwitch2 = new OnOffSwitch();
        onOffSwitch.setValue(true);
        onOffSwitch.setUIID("Container");
        onOffSwitch.setOff(" ");
        onOffSwitch.setOn(" ");
        onOffSwitch2.setValue(true);
        onOffSwitch2.setUIID("Container");
        onOffSwitch2.setOff(" ");
        onOffSwitch2.setOn(" ");
        container3.add("West", FlowLayout.encloseLeftMiddle(label2));
        container3.add("East", FlowLayout.encloseRightMiddle(onOffSwitch));
        container4.add("West", FlowLayout.encloseLeftMiddle(label3));
        container4.add("East", FlowLayout.encloseRightMiddle(onOffSwitch2));
        container3.getAllStyles().setPaddingUnit(1);
        container3.getAllStyles().setPadding(1, 0, 4, 1);
        container4.getAllStyles().setPaddingUnit(1);
        container4.getAllStyles().setPadding(0, 1, 4, 1);
        str3.hashCode();
        switch (str3) {
            case "PaiementEC":
                if (this.Crd.statusCardPaiementEnLigneInterNational.equals("Y")) {
                    onOffSwitch2.setValue(false);
                } else {
                    onOffSwitch2.setValue(true);
                }
                if (this.Crd.statusCardPaiementEnLigneNational.equals("Y")) {
                    onOffSwitch.setValue(false);
                } else {
                    onOffSwitch.setValue(true);
                }
                onOffSwitch2.addActionListener(new 9(onOffSwitch2));
                onOffSwitch.addActionListener(new 10(onOffSwitch));
                break;
            case "RetraitGAB":
                if (this.Crd.statusCardRetraitInterNational.equals("Y")) {
                    onOffSwitch2.setValue(false);
                } else {
                    onOffSwitch2.setValue(true);
                }
                if (this.Crd.statusCardRetraitNational.equals("Y")) {
                    onOffSwitch.setValue(false);
                } else {
                    onOffSwitch.setValue(true);
                }
                onOffSwitch2.addActionListener(new 7(onOffSwitch2));
                onOffSwitch.addActionListener(new 8(onOffSwitch));
                break;
            case "PaiementTPE":
                if (this.Crd.statusCardPaiementParCarteInterNational.equals("Y")) {
                    onOffSwitch2.setValue(false);
                } else {
                    onOffSwitch2.setValue(true);
                }
                if (this.Crd.statusCardPaiementParCarteNational.equals("Y")) {
                    onOffSwitch.setValue(false);
                } else {
                    onOffSwitch.setValue(true);
                }
                onOffSwitch2.addActionListener(new 11(onOffSwitch2));
                onOffSwitch.addActionListener(new 12(onOffSwitch));
                break;
        }
        container2.add(textArea);
        if (i == 0) {
            container2.add(container3);
        } else if (i == 1) {
            container2.add(container4);
        } else if (i == 2) {
            container2.add(container3);
            container2.add(container4);
        }
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.5f, 1.0f, 0.0f, 0.0f);
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(0, 2, 0, 0);
        this.acc.addContent(container, container2);
        if (this.Crd.statusCard.equals("Y")) {
            return;
        }
        this.acc.setFocusable(false);
        this.acc.setEnabled(false);
    }

    class 7 implements ActionListener {
        final /* synthetic */ OnOffSwitch val$interNatSwitch;

        7(OnOffSwitch onOffSwitch) {
            this.val$interNatSwitch = onOffSwitch;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardsDetailPageNew2.isCanceledAD = false;
            CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
            if (this.val$interNatSwitch.isValue()) {
                cardOpperationStatus.newCardStatus = "D";
            } else {
                cardOpperationStatus.newCardStatus = "Y";
            }
            cardOpperationStatus.motif = " ";
            cardOpperationStatus.plainCardNumber = CardsDetailPageNew2.this.Crd.plainCardNumber;
            cardOpperationStatus.cardNumber = CardsDetailPageNew2.this.Crd.cardNumber;
            cardOpperationStatus.cardOpperationStatus = CardsDetailPageNew2.this.Crd.statusCardRetraitInterNational;
            cardOpperationStatus.pClientCardHolder = CardsDetailPageNew2.this.Crd.radicalClient;
            int i = cardOpperationStatus.newCardStatus.equals("D") ? 10801 : 10800;
            CihMBanking.sesPMTR.cardfromHom = true;
            CihMBanking.sesPMTR.objctCrdCurrent = CardsDetailPageNew2.this.Crd;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationService(111, cardOpperationStatus, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, i);
            if (CardsDetailPageNew2.isCanceledAD) {
                this.val$interNatSwitch.removeActionListener(this);
                this.val$interNatSwitch.setValue(!r8.isValue());
                this.val$interNatSwitch.revalidate();
                this.val$interNatSwitch.getParent().revalidate();
                this.val$interNatSwitch.addActionListener(this);
            }
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ OnOffSwitch val$marocSwitch;

        8(OnOffSwitch onOffSwitch) {
            this.val$marocSwitch = onOffSwitch;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardsDetailPageNew2.isCanceledAD = false;
            CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
            if (this.val$marocSwitch.isValue()) {
                cardOpperationStatus.newCardStatus = "D";
            } else {
                cardOpperationStatus.newCardStatus = "Y";
            }
            cardOpperationStatus.motif = " ";
            cardOpperationStatus.plainCardNumber = CardsDetailPageNew2.this.Crd.plainCardNumber;
            cardOpperationStatus.cardNumber = CardsDetailPageNew2.this.Crd.cardNumber;
            cardOpperationStatus.cardOpperationStatus = CardsDetailPageNew2.this.Crd.statusCardRetraitNational;
            cardOpperationStatus.pClientCardHolder = CardsDetailPageNew2.this.Crd.radicalClient;
            int i = cardOpperationStatus.newCardStatus.equals("D") ? 10811 : 10810;
            CihMBanking.sesPMTR.cardfromHom = true;
            CihMBanking.sesPMTR.objctCrdCurrent = CardsDetailPageNew2.this.Crd;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationService(111, cardOpperationStatus, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, i);
            if (CardsDetailPageNew2.isCanceledAD) {
                this.val$marocSwitch.removeActionListener(this);
                this.val$marocSwitch.setValue(!r8.isValue());
                this.val$marocSwitch.revalidate();
                this.val$marocSwitch.getParent().revalidate();
                this.val$marocSwitch.addActionListener(this);
            }
        }
    }

    class 9 implements ActionListener {
        final /* synthetic */ OnOffSwitch val$interNatSwitch;

        9(OnOffSwitch onOffSwitch) {
            this.val$interNatSwitch = onOffSwitch;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardsDetailPageNew2.isCanceledAD = false;
            CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
            if (this.val$interNatSwitch.isValue()) {
                cardOpperationStatus.newCardStatus = "D";
            } else {
                cardOpperationStatus.newCardStatus = "Y";
            }
            cardOpperationStatus.motif = " ";
            cardOpperationStatus.plainCardNumber = CardsDetailPageNew2.this.Crd.plainCardNumber;
            cardOpperationStatus.cardNumber = CardsDetailPageNew2.this.Crd.cardNumber;
            cardOpperationStatus.cardOpperationStatus = CardsDetailPageNew2.this.Crd.statusCardPaiementEnLigneInterNational;
            cardOpperationStatus.pClientCardHolder = CardsDetailPageNew2.this.Crd.radicalClient;
            int i = cardOpperationStatus.newCardStatus.equals("D") ? 10805 : 10804;
            CihMBanking.sesPMTR.cardfromHom = true;
            CihMBanking.sesPMTR.objctCrdCurrent = CardsDetailPageNew2.this.Crd;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationService(111, cardOpperationStatus, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, i);
            if (CardsDetailPageNew2.isCanceledAD) {
                this.val$interNatSwitch.removeActionListener(this);
                this.val$interNatSwitch.setValue(!r8.isValue());
                this.val$interNatSwitch.revalidate();
                this.val$interNatSwitch.getParent().revalidate();
                this.val$interNatSwitch.addActionListener(this);
            }
        }
    }

    class 10 implements ActionListener {
        final /* synthetic */ OnOffSwitch val$marocSwitch;

        10(OnOffSwitch onOffSwitch) {
            this.val$marocSwitch = onOffSwitch;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardsDetailPageNew2.isCanceledAD = false;
            CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
            if (this.val$marocSwitch.isValue()) {
                cardOpperationStatus.newCardStatus = "D";
            } else {
                cardOpperationStatus.newCardStatus = "Y";
            }
            cardOpperationStatus.motif = " ";
            cardOpperationStatus.plainCardNumber = CardsDetailPageNew2.this.Crd.plainCardNumber;
            cardOpperationStatus.cardNumber = CardsDetailPageNew2.this.Crd.cardNumber;
            cardOpperationStatus.cardOpperationStatus = CardsDetailPageNew2.this.Crd.statusCardPaiementEnLigneNational;
            cardOpperationStatus.pClientCardHolder = CardsDetailPageNew2.this.Crd.radicalClient;
            int i = cardOpperationStatus.newCardStatus.equals("D") ? 10815 : 10814;
            CihMBanking.sesPMTR.cardfromHom = true;
            CihMBanking.sesPMTR.objctCrdCurrent = CardsDetailPageNew2.this.Crd;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationService(111, cardOpperationStatus, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, i);
            if (CardsDetailPageNew2.isCanceledAD) {
                this.val$marocSwitch.removeActionListener(this);
                this.val$marocSwitch.setValue(!r8.isValue());
                this.val$marocSwitch.revalidate();
                this.val$marocSwitch.getParent().revalidate();
                this.val$marocSwitch.addActionListener(this);
            }
        }
    }

    class 11 implements ActionListener {
        final /* synthetic */ OnOffSwitch val$interNatSwitch;

        11(OnOffSwitch onOffSwitch) {
            this.val$interNatSwitch = onOffSwitch;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardsDetailPageNew2.isCanceledAD = false;
            CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
            if (this.val$interNatSwitch.isValue()) {
                cardOpperationStatus.newCardStatus = "D";
            } else {
                cardOpperationStatus.newCardStatus = "Y";
            }
            cardOpperationStatus.motif = " ";
            cardOpperationStatus.plainCardNumber = CardsDetailPageNew2.this.Crd.plainCardNumber;
            cardOpperationStatus.cardNumber = CardsDetailPageNew2.this.Crd.cardNumber;
            cardOpperationStatus.cardOpperationStatus = CardsDetailPageNew2.this.Crd.statusCardPaiementParCarteInterNational;
            cardOpperationStatus.pClientCardHolder = CardsDetailPageNew2.this.Crd.radicalClient;
            int i = cardOpperationStatus.newCardStatus.equals("D") ? 10803 : 10802;
            CihMBanking.sesPMTR.cardfromHom = true;
            CihMBanking.sesPMTR.objctCrdCurrent = CardsDetailPageNew2.this.Crd;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationService(111, cardOpperationStatus, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, i);
            if (CardsDetailPageNew2.isCanceledAD) {
                this.val$interNatSwitch.removeActionListener(this);
                this.val$interNatSwitch.setValue(!r8.isValue());
                this.val$interNatSwitch.revalidate();
                this.val$interNatSwitch.getParent().revalidate();
                this.val$interNatSwitch.addActionListener(this);
            }
        }
    }

    class 12 implements ActionListener {
        final /* synthetic */ OnOffSwitch val$marocSwitch;

        12(OnOffSwitch onOffSwitch) {
            this.val$marocSwitch = onOffSwitch;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardsDetailPageNew2.isCanceledAD = false;
            CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
            if (this.val$marocSwitch.isValue()) {
                cardOpperationStatus.newCardStatus = "D";
            } else {
                cardOpperationStatus.newCardStatus = "Y";
            }
            cardOpperationStatus.motif = " ";
            cardOpperationStatus.plainCardNumber = CardsDetailPageNew2.this.Crd.plainCardNumber;
            cardOpperationStatus.cardNumber = CardsDetailPageNew2.this.Crd.cardNumber;
            cardOpperationStatus.cardOpperationStatus = CardsDetailPageNew2.this.Crd.statusCardPaiementParCarteNational;
            cardOpperationStatus.pClientCardHolder = CardsDetailPageNew2.this.Crd.radicalClient;
            int i = cardOpperationStatus.newCardStatus.equals("D") ? 10813 : 10812;
            CihMBanking.sesPMTR.cardfromHom = true;
            CihMBanking.sesPMTR.objctCrdCurrent = CardsDetailPageNew2.this.Crd;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationService(111, cardOpperationStatus, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, i);
            if (CardsDetailPageNew2.isCanceledAD) {
                this.val$marocSwitch.removeActionListener(this);
                this.val$marocSwitch.setValue(!r8.isValue());
                this.val$marocSwitch.revalidate();
                this.val$marocSwitch.getParent().revalidate();
                this.val$marocSwitch.addActionListener(this);
            }
        }
    }

    public class SepCnt extends Container {
        public SepCnt() {
            getAllStyles().setPaddingUnit(0);
            getAllStyles().setPadding(1, 0, 3, 3);
            getAllStyles().setBgColor(7368816);
            getAllStyles().setBgTransparency(255);
        }
    }

    public Container getOppositionCnt() {
        FlowLayout flowLayout = new FlowLayout(4, 4);
        flowLayout.setFillRows(true);
        Container container = new Container(flowLayout);
        Container container2 = new Container(BoxLayout.x());
        Button button = new Button("FAIRE OPPOSITION À MA CARTE".toUpperCase());
        button.addActionListener(new 13());
        if (!this.Crd.statusCard.equals("Y")) {
            button.setEnabled(false);
        }
        button.setUIID("Container");
        button.getAllStyles().setFgColor(14767974);
        Label label = new Label();
        label.setIcon(GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), this.uiManager.ressource.getImage("OpositionLogo.png")));
        label.getAllStyles().setPaddingUnit(1);
        label.getAllStyles().setPadding(1, 1, 1, 1);
        container2.add(label).add(button);
        container.add(container2);
        return container;
    }

    class 13 implements ActionListener {
        13() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
            cardOpperationStatus.motif = " ";
            cardOpperationStatus.plainCardNumber = CardsDetailPageNew2.this.Crd.plainCardNumber;
            cardOpperationStatus.cardNumber = CardsDetailPageNew2.this.Crd.cardNumber;
            cardOpperationStatus.cardOpperationStatus = CardsDetailPageNew2.this.Crd.statusCard;
            cardOpperationStatus.pClientCardHolder = CardsDetailPageNew2.this.Crd.radicalClient;
            cardOpperationStatus.newCardStatus = "O";
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationADOService(25, cardOpperationStatus, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 300039);
        }
    }

    public Container getRenvoiCodeCnt(Card card) {
        TableLayout tableLayout = new TableLayout(1, 2);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(12);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(88);
        Button button = new Button();
        button.setIcon(GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), this.uiManager.ressource.getImage("renvoyer-le-code-de-ma-carte-bancaire-active.png")));
        button.setUIID("Container");
        Label label = new Label("Renvoyer le code de ma carte bancaire");
        label.setUIID("Container");
        if (!this.Crd.statusCard.equals("Y")) {
            button.setEnabled(false);
        }
        CardsDetailPageNew2$$ExternalSyntheticLambda1 cardsDetailPageNew2$$ExternalSyntheticLambda1 = new CardsDetailPageNew2$$ExternalSyntheticLambda1(this, card);
        Container container = new Container(tableLayout);
        container.add(createConstraint, FlowLayout.encloseCenter(button));
        container.add(createConstraint2, FlowLayout.encloseLeftMiddle(label));
        button.addActionListener(cardsDetailPageNew2$$ExternalSyntheticLambda1);
        container.setLeadComponent(button);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.5f, 1.0f, 0.0f, 0.0f);
        return container;
    }

    /* synthetic */ void lambda$getRenvoiCodeCnt$3$com-b3g-ui-page-CardsDetailPageNew2(Card card, ActionEvent actionEvent) {
        getRecalculPinNew(card);
    }

    public Container getRechargeCarteCnt() {
        TableLayout tableLayout = new TableLayout(1, 2);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(12);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(88);
        Button button = new Button();
        button.addActionListener(new 14());
        button.setIcon(GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), this.uiManager.ressource.getImage("recharger-la-carte-active.png")));
        button.setUIID("Container");
        Label label = new Label("Recharger la carte");
        label.setUIID("Container");
        Container container = new Container(tableLayout);
        container.add(createConstraint, FlowLayout.encloseCenter(button));
        container.add(createConstraint2, FlowLayout.encloseLeftMiddle(label));
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.5f, 1.0f, 0.0f, 0.0f);
        container.setLeadComponent(button);
        return container;
    }

    class 14 implements ActionListener {
        14() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.objctCrdCurrent = CardsDetailPageNew2.this.Crd;
            CardsDetailPageNew2.this.uiManager.NavigateToPageById(9);
        }
    }

    public Container getDechargeCarteCnt(String str) {
        TableLayout tableLayout = new TableLayout(1, 2);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(12);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(88);
        Button button = new Button();
        button.setIcon(GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), this.uiManager.ressource.getImage("decharger-vers-un-compte-active.png")));
        button.addActionListener(new 15(str));
        button.setUIID("Container");
        Label label = new Label("Décharger vers un compte");
        label.setUIID("Container");
        Container container = new Container(tableLayout);
        container.add(createConstraint, FlowLayout.encloseCenter(button));
        container.add(createConstraint2, FlowLayout.encloseLeftMiddle(label));
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.5f, 1.0f, 0.0f, 0.0f);
        container.setLeadComponent(button);
        return container;
    }

    class 15 implements ActionListener {
        final /* synthetic */ String val$type;

        15(String str) {
            this.val$type = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.objctCrdCurrent = CardsDetailPageNew2.this.Crd;
            PrepaidCardDebit prepaidCardDebit = new PrepaidCardDebit(CardsDetailPageNew2.this.uiManager, CardsDetailPageNew2.this.Crd, 47);
            prepaidCardDebit.setTypeCard(this.val$type);
            CardsDetailPageNew2.this.uiManager.NavigateToPage(prepaidCardDebit);
        }
    }

    public Container getSansContactCnt() {
        TableLayout tableLayout = new TableLayout(1, 3);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(12);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(76);
        TableLayout.Constraint createConstraint3 = tableLayout.createConstraint();
        createConstraint3.setWidthPercentage(12);
        Button button = new Button();
        button.setIcon(GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), this.uiManager.ressource.getImage("paiement-sans-contact-active.png")));
        button.setUIID("Container");
        Label label = new Label("Paiement sans contact");
        label.setUIID("Container");
        OnOffSwitch onOffSwitch = new OnOffSwitch();
        onOffSwitch.setValue(true);
        onOffSwitch.getAllStyles().setMargin(0, 0, 0, 0);
        onOffSwitch.setUIID("Container");
        onOffSwitch.setOff(" ");
        onOffSwitch.setOn(" ");
        if (this.Crd.statusCardSanscontact.equals("Y")) {
            onOffSwitch.setValue(false);
        } else {
            onOffSwitch.setValue(true);
        }
        if (!this.Crd.statusCard.equals("Y")) {
            onOffSwitch.setValue(false);
            onOffSwitch.setEnabled(false);
        }
        onOffSwitch.addActionListener(new 16(onOffSwitch));
        Container container = new Container(tableLayout);
        container.add(createConstraint, FlowLayout.encloseCenter(button));
        container.add(createConstraint2, FlowLayout.encloseLeftMiddle(label));
        container.add(createConstraint3, FlowLayout.encloseRightMiddle(onOffSwitch));
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.5f, 1.0f, 0.0f, 0.0f);
        return container;
    }

    class 16 implements ActionListener {
        final /* synthetic */ OnOffSwitch val$pOnOffSwitch;

        16(OnOffSwitch onOffSwitch) {
            this.val$pOnOffSwitch = onOffSwitch;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardsDetailPageNew2.isCanceledAD = false;
            CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
            if (this.val$pOnOffSwitch.isValue()) {
                cardOpperationStatus.newCardStatus = "D";
            } else {
                cardOpperationStatus.newCardStatus = "Y";
            }
            cardOpperationStatus.motif = " ";
            cardOpperationStatus.plainCardNumber = CardsDetailPageNew2.this.Crd.plainCardNumber;
            cardOpperationStatus.cardNumber = CardsDetailPageNew2.this.Crd.cardNumber;
            cardOpperationStatus.cardOpperationStatus = CardsDetailPageNew2.this.Crd.statusCardSanscontact;
            cardOpperationStatus.pClientCardHolder = CardsDetailPageNew2.this.Crd.radicalClient;
            int i = cardOpperationStatus.newCardStatus.equals("D") ? 10807 : 10806;
            CihMBanking.sesPMTR.cardfromHom = true;
            CihMBanking.sesPMTR.objctCrdCurrent = CardsDetailPageNew2.this.Crd;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationService(111, cardOpperationStatus, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, i);
            if (CardsDetailPageNew2.isCanceledAD) {
                this.val$pOnOffSwitch.removeActionListener(this);
                this.val$pOnOffSwitch.setValue(!r8.isValue());
                this.val$pOnOffSwitch.revalidate();
                this.val$pOnOffSwitch.getParent().revalidate();
                this.val$pOnOffSwitch.addActionListener(this);
            }
        }
    }

    public Container getInfoPalafondsCnt(String str, String str2, String str3, Card card) {
        Container container = new Container(BoxLayout.y());
        if (card != null) {
            container.getAllStyles().setPaddingUnit(1);
            container.getAllStyles().setPadding(2, 2, 0, 0);
            Label label = new Label(str);
            label.getAllStyles().setAlignment(1);
            String d = card.Maximum.toString();
            int i = 0;
            for (int i2 = 0; i2 < card.CardPlafondList.size(); i2++) {
                CardPlafond cardPlafond = (CardPlafond) card.CardPlafondList.get(i2);
                if (cardPlafond.PlafondType.equals(str3)) {
                    i = cardPlafond.Plafond.intValue() / card.productStep.intValue();
                    str2 = StringUtil.replaceAll(str2, "XXXX", formatNumber(cardPlafond.Plafond.intValue(), " "));
                }
            }
            SpanLabel spanLabel = new SpanLabel(str2);
            label.setUIID("Container");
            spanLabel.setTextUIID("lbl_black_small_messgs");
            spanLabel.getTextAllStyles().setFgColor(6710886);
            Label label2 = new Label(i + "% Utilisé");
            Label label3 = new Label(d + " DHS");
            Container container2 = new Container(new BorderLayout());
            label2.setUIID("SV_lbl_small_blue");
            label3.setUIID("SV_lbl_small_blue");
            label3.getAllStyles().setFgColor(0);
            container2.add("East", label3);
            container.add(FlowLayout.encloseLeftMiddleByRow(label)).add(spanLabel).add(getPoucentageCnt(i)).add(container2);
        } else {
            container.add(FlowLayout.encloseCenterMiddle(new Label("Une erreur est survenue!!")));
        }
        return container;
    }

    public Container getPhysiqueCartCnt(Card card) {
        TableLayout tableLayout = new TableLayout(1, 2);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(12);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(88);
        Button button = new Button();
        button.setIcon(GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), this.uiManager.ressource.getImage("Cartephysique.png")));
        button.setUIID("Container");
        Label label = new Label();
        label.setText("Commander la version physique de ma carte");
        label.setUIID("Container");
        if (!this.Crd.statusCard.equals("Y")) {
            button.setEnabled(false);
        }
        CardsDetailPageNew2$$ExternalSyntheticLambda0 cardsDetailPageNew2$$ExternalSyntheticLambda0 = new CardsDetailPageNew2$$ExternalSyntheticLambda0(card);
        Container container = new Container(tableLayout);
        container.add(createConstraint, FlowLayout.encloseCenter(button));
        container.add(createConstraint2, FlowLayout.encloseLeftMiddle(label));
        button.addActionListener(cardsDetailPageNew2$$ExternalSyntheticLambda0);
        container.setLeadComponent(button);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.5f, 1.0f, 0.0f, 0.0f);
        return container;
    }

    static /* synthetic */ void lambda$getPhysiqueCartCnt$4(Card card, ActionEvent actionEvent) {
        CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationService2(113, card, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, "Veuillez confirmer la commande de votre carte");
    }

    private Container getPoucentageCnt(int i) {
        TableLayout tableLayout = new TableLayout(1, 2);
        Container container = new Container(tableLayout);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(1, 1, 0, 0);
        Container container2 = new Container();
        Container container3 = new Container();
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(1, 1, 0, 0);
        container2.getAllStyles().setBgColor(16746823);
        container2.getAllStyles().setBgTransparency(255);
        container3.getAllStyles().setPaddingUnit(1);
        container3.getAllStyles().setPadding(1, 1, 0, 0);
        container3.getAllStyles().setBgColor(16773360);
        container3.getAllStyles().setBgTransparency(255);
        return container.add(tableLayout.createConstraint().widthPercentage(i).heightPercentage(100), container2).add(tableLayout.createConstraint().widthPercentage(100 - i).heightPercentage(100), container3);
    }

    private Component deblocage_retrait() {
        Container container = new Container(new BoxLayout(2));
        Container container2 = new Container(new BoxLayout(2));
        Container container3 = new Container(new BoxLayout(2));
        SpanLabel spanLabel = new SpanLabel("En raison de plusieurs saisies incorrectes du code PIN, votre carte est bloquée sur les guichets automatiques");
        spanLabel.setIcon(this.uiManager.ressource.getImage("cvvOff.png"));
        spanLabel.setUIID("g_lblNotif");
        container2.add(spanLabel);
        Button button = new Button("DÉBLOQUER LE RETRAIT DES GUICHETS", this.uiManager.ressource.getImage("key-ios.png"));
        button.setUIID("Orange_Btn");
        button.addActionListener(new 17());
        container3.add(button);
        container.add(container2);
        container.add(container3);
        return container;
    }

    class 17 implements ActionListener {
        17() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardsDetailPageNew2.isCanceledAD = false;
            CardOpperationStatus cardOpperationStatus = new CardOpperationStatus();
            cardOpperationStatus.newCardStatus = "D";
            cardOpperationStatus.motif = " ";
            cardOpperationStatus.plainCardNumber = CardsDetailPageNew2.this.Crd.plainCardNumber;
            cardOpperationStatus.cardNumber = CardsDetailPageNew2.this.Crd.cardNumber;
            cardOpperationStatus.cardOpperationStatus = "Y";
            cardOpperationStatus.pClientCardHolder = CardsDetailPageNew2.this.Crd.radicalClient;
            CihMBanking.sesPMTR.cardfromHom = true;
            CihMBanking.sesPMTR.objctCrdCurrent = CardsDetailPageNew2.this.Crd;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCardOpperationService(111, cardOpperationStatus, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 10828);
        }
    }

    public Accordion drawAccordion(ArrayList arrayList, ArrayList arrayList2) {
        Accordion accordion = new Accordion();
        accordion.setScrollableY(true);
        accordion.setScrollVisible(false);
        Image grayImage = GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), this.uiManager.ressource.getImage("blueArrowRotated.png"));
        accordion.setCloseIcon(GraphicTools.getGrayImage(!this.Crd.statusCard.equals("Y"), CihMBanking.detail_arrow));
        accordion.setOpenIcon(grayImage);
        if (arrayList2.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                ((Container) arrayList.get(i)).getAllStyles().setPaddingUnit(1);
                ((Container) arrayList.get(i)).getAllStyles().setPadding(1.5f, 1.0f, 0.0f, 0.0f);
                accordion.addContent((Component) arrayList.get(i), BoxLayout.encloseY((Container) arrayList2.get(i)));
            }
            if (!this.Crd.statusCard.equals("Y")) {
                accordion.setFocusable(false);
                accordion.setEnabled(false);
            }
        } else {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                ((Container) arrayList.get(i2)).getAllStyles().setPaddingUnit(1);
                ((Container) arrayList.get(i2)).getAllStyles().setPadding(1.5f, 1.0f, 0.0f, 0.0f);
                accordion.addContent((Component) arrayList.get(i2), BoxLayout.encloseY(FlowLayout.encloseCenterMiddle(new Label("Une erreur est survenue!!"))));
            }
        }
        accordion.revalidate();
        Settings.setNightMode(accordion, 0);
        return accordion;
    }

    public Card CardPlafondProcess(String str, String str2) {
        Card card = this.Crd;
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSION_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", str);
        hashtable.put("CARDNUMBER", card.plainCardNumber);
        hashtable.put("ISRETABLIRCLICK", "false");
        hashtable.put("FLAG", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(16);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return FillCardArrayDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public Card FillResetCardArrayDataFromServiceResponse(ServiceResponse serviceResponse) {
        Card card = new Card();
        if (serviceResponse.getStatusCode().equals("000")) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Vos plafonds ont été réinitialisés avec succès", null);
            return FillPlafondCardArrayDataFromServiceResponse(serviceResponse.getParamsOut());
        }
        serviceResponse.getStatusCode().equals("312");
        return card;
    }

    public Card FillPlafondCardArrayDataFromServiceResponse(Hashtable hashtable) {
        Card card = new Card();
        card.CanDesactivate = hashtable.get("CanDesactivate").toString();
        card.CanRemoveCardLimits = hashtable.get("CanRemoveCardLimits").toString();
        card.EndDate = hashtable.get("EndDate").toString();
        card.HasUnlimitedDate = hashtable.get("HasUnlimitedDate").toString();
        card.Maximum = (Double) hashtable.get("Maximum");
        card.Minimum = (Double) hashtable.get("Minimum");
        card.productStep = (Double) hashtable.get("ProductStep");
        Vector vector = (Vector) hashtable.get("CardPlafondList");
        if (vector.size() > 0) {
            card.CardPlafondList = CardPlafond.FillAccountCardPlafondDataFromServiceResponse(vector);
        } else {
            card.CardPlafondList = new ArrayList();
        }
        return card;
    }

    public Card FillCardArrayDataFromServiceResponse(ServiceResponse serviceResponse) {
        Card card = new Card();
        if (serviceResponse.getStatusCode().equals("000")) {
            return FillPlafondCardArrayDataFromServiceResponse(serviceResponse.getParamsOut());
        }
        serviceResponse.getStatusCode().equals("312");
        return card;
    }

    public String formatNumber(int i, String str) {
        int length = String.valueOf(i).length();
        if (length > 3) {
            int i2 = length % 3;
            String substring = String.valueOf(i).substring(i2, length);
            ArrayList arrayList = new ArrayList();
            if (substring.length() == 3 || substring.length() < 3) {
                arrayList.add(substring);
            } else {
                for (int i3 = 2; i3 < substring.length(); i3 += 3) {
                    arrayList.add(substring.substring(i3 - 2, i3 + 1));
                }
            }
            String str2 = String.valueOf(i).substring(0, i2) + " ";
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                str2 = str2 + ((String) arrayList.get(i4)) + " ";
            }
            return str2.trim();
        }
        return String.valueOf(i);
    }

    private void DrawTabsWithNavigation(Container container, Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        Tabs tabs = new Tabs();
        tabs.hideTabs();
        tabs.setUIID("g_TabsV11");
        tabs.setAlwaysTensile(false);
        tabs.setDraggable(false);
        tabs.setSwipeActivated(false);
        Container container2 = new Container(new GridLayout(1, arrayList.size()));
        container2.setUIID("ad_CntBtnTab");
        Boolean bool = false;
        for (int i = 0; i < arrayList.size(); i++) {
            Button button = new Button(((B3gContainer) arrayList.get(i)).Title);
            button.setUIID("ad_BtnTab");
            button.setTickerEnabled(false);
            button.setFocusable(false);
            button.addActionListener(new 18(tabs, i, button, container2));
            if (((B3gContainer) arrayList.get(i)).IsToBeSelected.booleanValue()) {
                button.setEnabled(false);
                bool = true;
            }
            container2.addComponent(button);
            ((B3gContainer) arrayList.get(i)).cnt.setScrollVisible(false);
            tabs.addTab(((B3gContainer) arrayList.get(i)).Title, ((B3gContainer) arrayList.get(i)).cnt);
            if (((B3gContainer) arrayList.get(i)).IsToBeSelected.booleanValue()) {
                tabs.setSelectedIndex(i);
            }
        }
        if (!bool.booleanValue()) {
            ((Button) container2.getComponentAt(0)).setEnabled(false);
        }
        tabs.addSelectionListener(new 19(arrayList));
        container.addComponent(container2);
        container.addComponent(tabs);
        container.revalidate();
    }

    class 18 implements ActionListener {
        final /* synthetic */ Tabs val$TabsDetail;
        final /* synthetic */ Button val$btntab;
        final /* synthetic */ Container val$butCnt;
        final /* synthetic */ int val$j;

        18(Tabs tabs, int i, Button button, Container container) {
            this.val$TabsDetail = tabs;
            this.val$j = i;
            this.val$btntab = button;
            this.val$butCnt = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$TabsDetail.setSelectedIndex(this.val$j);
            this.val$btntab.setEnabled(false);
            CardsDetailPageNew2.this.DisableMenuButtonsStatus(this.val$butCnt, this.val$j);
        }
    }

    class 19 implements SelectionListener {
        final /* synthetic */ ArrayList val$cntList;

        19(ArrayList arrayList) {
            this.val$cntList = arrayList;
        }

        public void selectionChanged(int i, int i2) {
            System.err.println("====***=======" + i2);
            if (i2 == 1 && this.val$cntList.size() == 3) {
                if (CardsDetailPageNew2.this.isFirst) {
                    if (CardsDetailPageNew2.this.Crd.plafondCard == null) {
                        CardsDetailPageNew2.this.Crd.plafondCard = Card.CardPlafondProcessNew(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, "false", CardsDetailPageNew2.this.Crd);
                        for (int i3 = 0; i3 < CihMBanking.sesPMTR.getSessionClient().getClient_CardList().size(); i3++) {
                            if (((Card) CihMBanking.sesPMTR.getSessionClient().getClient_CardList().get(i3)).plainCardNumber.equals(CardsDetailPageNew2.this.Crd.plainCardNumber)) {
                                ((Card) CihMBanking.sesPMTR.getSessionClient().getClient_CardList().get(i3)).plafondCard = CardsDetailPageNew2.this.Crd.plafondCard;
                            }
                        }
                    }
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    Label label = new Label("  Plafonds au Maroc", "Container");
                    Label label2 = new Label("  Plafonds à l’international", "Container");
                    label.setIcon(GraphicTools.getGrayImage(!CardsDetailPageNew2.this.Crd.statusCard.equals("Y"), CardsDetailPageNew2.this.uiManager.ressource.getImage("plafond-au-maroc-active.png")));
                    label2.setIcon(GraphicTools.getGrayImage(!CardsDetailPageNew2.this.Crd.statusCard.equals("Y"), CardsDetailPageNew2.this.uiManager.ressource.getImage("plafond-au-international-active.png")));
                    arrayList.add(FlowLayout.encloseLeftMiddle(label));
                    arrayList.add(FlowLayout.encloseLeftMiddle(label2));
                    if (CardsDetailPageNew2.this.Crd.plafondCard != null) {
                        CardsDetailPageNew2 cardsDetailPageNew2 = CardsDetailPageNew2.this;
                        CardsDetailPageNew2 cardsDetailPageNew22 = CardsDetailPageNew2.this;
                        arrayList2.add(BoxLayout.encloseY(cardsDetailPageNew2.getInfoPalafondsCnt("PLAFOND DE RETRAIT", "Vous disposez d’un plafond de retrait de XXXX Dhs.", "RetraitNat", cardsDetailPageNew2.Crd.plafondCard), cardsDetailPageNew22.getInfoPalafondsCnt("PLAFOND DE PAIEMENT TPE", "Vous disposez d’un plafond de paiement TPE de XXXX Dhs.", "PaiementNat", cardsDetailPageNew22.Crd.plafondCard), CardsDetailPageNew2.this.getInfoPalafondsCnt("PLAFOND DE paiement internet".toUpperCase(), "Vous disposez d’un plafond de paiement internet de XXXX Dhs.", "EComNat", CardsDetailPageNew2.this.Crd.plafondCard)));
                        CardsDetailPageNew2 cardsDetailPageNew23 = CardsDetailPageNew2.this;
                        CardsDetailPageNew2 cardsDetailPageNew24 = CardsDetailPageNew2.this;
                        arrayList2.add(BoxLayout.encloseY(cardsDetailPageNew23.getInfoPalafondsCnt("PLAFOND DE RETRAIT", "Vous disposez d’un plafond de retrait de XXXX Dhs.", "RetraitInt", cardsDetailPageNew23.Crd.plafondCard), cardsDetailPageNew24.getInfoPalafondsCnt("PLAFOND DE PAIEMENT TPE", "Vous disposez d’un plafond de paiement TPE de XXXX Dhs.", "PaiementInt", cardsDetailPageNew24.Crd.plafondCard), CardsDetailPageNew2.this.getInfoPalafondsCnt("PLAFOND DE paiement internet".toUpperCase(), "Vous disposez d’un plafond de paiement internet de XXXX Dhs.", "EComInt", CardsDetailPageNew2.this.Crd.plafondCard)));
                    }
                    Button button = new Button("MODIFIER LES PLAFONDS");
                    if (!CardsDetailPageNew2.this.Crd.statusCard.equals("Y")) {
                        button.setUIID("op_BtnOppositionValidationGriseMarginLeftNew");
                        button.setEnabled(false);
                    } else {
                        button.setUIID("GoButton");
                    }
                    button.addActionListener(new 1());
                    CardsDetailPageNew2.this.plafondsTbs.setScrollableY(true);
                    CardsDetailPageNew2.this.plafondsTbs.setScrollVisible(false);
                    CardsDetailPageNew2.this.plafondsTbs.add("Center", CardsDetailPageNew2.this.drawAccordion(arrayList, arrayList2));
                    CardsDetailPageNew2.this.plafondsTbs.add("South", button);
                    CardsDetailPageNew2.this.plafondsTmp.add(CardsDetailPageNew2.this.plafondsTbs);
                    CardsDetailPageNew2.this.mainTabsCnt.animateLayout(300);
                    CardsDetailPageNew2.this.isFirst = false;
                    return;
                }
                CardsDetailPageNew2.this.isFirst = false;
            }
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                CardsDetailPageNew2.this.uiManager.NavigateToPage(new CardsUpdatePlafond(CardsDetailPageNew2.this.uiManager, CardsDetailPageNew2.this.Crd, 137));
            }
        }
    }

    public void DisableMenuButtonsStatus(Container container, int i) {
        for (int i2 = 0; i2 < container.getComponentCount(); i2++) {
            if (i2 != i) {
                ((Button) container.getComponentAt(i2)).setEnabled(true);
            }
        }
    }

    private void setUrlWebView(String str) {
        CihMBanking.sesPMTR.urlAvantageWebView = null;
        CihMBanking.sesPMTR.urlAvantageWebView = str;
    }
}
