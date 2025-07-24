package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.services.CardOperation;
import com.b3g.tools.GraphicTools;
import com.b3g.tools.SyncTask;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordion;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.B3gDialogBlur;
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
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.MorphTransition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UITimer;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CardsDetailPageNew extends B3GPage {
    private ArrayList CardList;
    Container CardOperationList;
    Container CntGlobalCard;
    Card Crd;
    Accordion acc;
    Button b2;
    public Container body;
    Button btnopposi;
    Container c;
    int cardWidth;
    private Boolean close = false;
    Container cntBtnFlot;
    Container cntCard;
    Container cntCardItemV3;
    Container cntGlbCardCenterCnt;
    EncodedImage encImage;
    public Container header;
    Label lbl22222;
    Label lblBlocCard;
    Label lbllogoooo;
    public Container mainCnt;
    Image placeholder;
    Button retraitBtn;
    Button retraitBtn2;
    Button retraitBtn3;
    Button retraitBtn4;
    Button retraitBtn5;
    Button retraitBtn6;
    Button retraitBtn7;

    private Accordion createQRAccordion(Accordion accordion, String str, String str2, Button button, String str3) {
        return null;
    }

    static /* synthetic */ void access$100(CardsDetailPageNew cardsDetailPageNew, Card card) {
        cardsDetailPageNew.showPopup(card);
    }

    static /* synthetic */ void access$200(CardsDetailPageNew cardsDetailPageNew, Image image) {
        cardsDetailPageNew.SetCardsImage(image);
    }

    public CardsDetailPageNew(Object obj, Object obj2, int i) {
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
        this.retraitBtn = new Button();
        this.retraitBtn2 = new Button();
        this.retraitBtn3 = new Button();
        this.retraitBtn4 = new Button();
        this.retraitBtn5 = new Button();
        this.retraitBtn6 = new Button();
        this.retraitBtn7 = new Button();
        if (this.Crd.blocked.booleanValue()) {
            this.retraitBtn.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("withdraw.png")));
            this.retraitBtn2.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("Payment2.png")));
            this.retraitBtn3.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("online-shop2.png")));
            this.retraitBtn4.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("Contactless2.png")));
            this.retraitBtn5.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("Code_cart_2.png")));
            this.retraitBtn6.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("Decharge.png")));
            this.retraitBtn7.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("Recharge-carte.png")));
            return;
        }
        this.retraitBtn.setIcon(this.uiManager.ressource.getImage("withdraw.png"));
        this.retraitBtn2.setIcon(this.uiManager.ressource.getImage("Payment2.png"));
        this.retraitBtn3.setIcon(this.uiManager.ressource.getImage("online-shop2.png"));
        this.retraitBtn4.setIcon(this.uiManager.ressource.getImage("Contactless2.png"));
        this.retraitBtn5.setIcon(this.uiManager.ressource.getImage("Code_cart_2.png"));
        this.retraitBtn6.setIcon(this.uiManager.ressource.getImage("Decharge.png"));
        this.retraitBtn7.setIcon(this.uiManager.ressource.getImage("Recharge-carte.png"));
    }

    public Container GetPageContainer() {
        TableLayout tableLayout;
        ArrayList arrayList;
        Label label;
        Container container;
        Button button;
        try {
            ArrayList arrayList2 = new ArrayList();
            this.thisContainer = new Container();
            TableLayout tableLayout2 = new TableLayout(1, 1);
            this.thisContainer.setLayout(tableLayout2);
            this.cntCardItemV3 = new Container(new BoxLayout(2));
            this.CntGlobalCard = new Container(new FlowLayout(4, 4));
            Container container2 = new Container(new BoxLayout(2));
            Container container3 = new Container();
            Container container4 = new Container();
            if (this.Crd.cardType.equals("A")) {
                tableLayout = tableLayout2;
                container3.setLayout(new GridLayout(1, 2));
                container3.add(getBlocageContainer());
                container3.add(getSoldeContainer());
                container4.setLayout(new GridLayout(1, 1));
                container4.add(getAccordion());
            } else {
                tableLayout = tableLayout2;
                if (this.Crd.cardType.equals("P")) {
                    container3.setLayout(new GridLayout(1, 2));
                    container3.add(getBlocageContainer());
                    container3.add(getSoldeContainer());
                } else {
                    container3.setLayout(new GridLayout(1, 1));
                    container3.add(getBlocageContainer());
                }
            }
            container2.add(container3);
            container2.add(container4);
            Container container5 = new Container(new LayeredLayout());
            Container container6 = new Container(new BoxLayout(2));
            Container container7 = new Container(new BoxLayout(2));
            Container container8 = new Container(new BorderLayout());
            this.cntBtnFlot = new Container(new FlowLayout(4, 4));
            Container container9 = new Container(new BoxLayout(4));
            this.cntGlbCardCenterCnt = new Container(new FlowLayout(4, 4));
            Container container10 = new Container(new FlowLayout(4, 4));
            Container container11 = new Container(new FlowLayout(1, 4));
            Container container12 = new Container(new FlowLayout(4, 4));
            Button button2 = new Button(this.uiManager.ressource.getImage("iconeMenu.png"));
            int convertToPixels = Display.getInstance().convertToPixels(4.7f);
            int convertToPixels2 = Display.getInstance().convertToPixels(2.5f);
            this.lblBlocCard = new Label();
            Label label2 = new Label(" " + this.Crd.cardNumber.substring(0, 4) + " " + this.Crd.cardNumber.substring(4, 8) + " " + this.Crd.cardNumber.substring(8, 12) + " " + this.Crd.cardNumber.substring(12, 16));
            Label label3 = new Label(" Exp : " + this.Crd.expirationDate.substring(3, 5) + " " + this.Crd.expirationDate.substring(5, 6) + " " + this.Crd.expirationDate.substring(8, 10) + "   ");
            if (this.Crd.productCode.equals("042") || this.Crd.productCode.equals("040") || this.Crd.categorieCarteJeune.equals("008") || this.Crd.categorieCarteJeune.equals("003") || this.Crd.categorieCarteJeune.equals("001") || this.Crd.categorieCarteJeune.equals("014")) {
                label2.getStyle().setFgColor(4079166);
                label3.getStyle().setFgColor(4079166);
            } else {
                label2.getStyle().setFgColor(16777215);
                label3.getStyle().setFgColor(16777215);
            }
            label2.getAllStyles().setTextDecoration(32);
            label2.getAllStyles().setFont(Font.createSystemFont(0, 1, 0).derive(convertToPixels, 1));
            label3.getAllStyles().setFont(Font.createSystemFont(0, 1, 8).derive(convertToPixels2, 1));
            this.cntCard.setPreferredW(this.cardWidth);
            this.cntCard.setPreferredH((this.cardWidth * 54) / 100);
            this.CntGlobalCard.setPreferredW(this.cardWidth);
            this.CntGlobalCard.setPreferredH((this.cardWidth * 54) / 100);
            label3.getAllStyles().setTextDecoration(32);
            label3.getAllStyles().setPadding(2, 0, 15, 0);
            button2.setUIID("Container");
            button2.getAllStyles().setMarginBottom(1);
            button2.setAlignment(4);
            button2.setVerticalAlignment(4);
            Label label4 = new Label("  " + this.Crd.entitled);
            label4.setUIID("SimLabelOrange");
            label4.getAllStyles().setFont(Font.createSystemFont(0, 1, 0));
            label4.getAllStyles().setPadding(1, 1, 2, 0);
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
            container2.setScrollableY(false);
            container2.setScrollVisible(false);
            container2.getAllStyles().setMargin(0, 0, 1, 1);
            container6.setScrollableY(true);
            container6.setScrollVisible(false);
            container7.setScrollableY(false);
            container7.setScrollVisible(false);
            container8.setScrollableY(true);
            container8.setScrollVisible(false);
            button2.getUnselectedStyle().setBorder(RoundBorder.create().shadowOpacity(90));
            this.cntGlbCardCenterCnt.getAllStyles().setMargin(12, 0, 3, 3);
            container10.addComponent(this.lblBlocCard);
            container12.addComponent(label2);
            container11.addComponent(label3);
            container9.addComponent(container10);
            container9.addComponent(container12);
            container9.addComponent(container11);
            this.cntGlbCardCenterCnt.addComponent(container9);
            if (!this.Crd.imagPath.equals(" ")) {
                Display.getInstance().callSerially(new 1());
            } else {
                this.cntCard.setUIID("cardBg");
            }
            container6.add(GetOperationContainer(this.Crd));
            container5.add(container6);
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            arrayList3.add(FlowLayout.encloseLeftMiddle(new Label("Plafonds au Maroc")));
            arrayList3.add(FlowLayout.encloseLeftMiddle(new Label("Plafonds à l’international")));
            if (this.Crd.cardType.equals("A")) {
                container7.add(createQRAccordion("Paiement par Internet", "", this.retraitBtn2, ""));
                container7.add(getDechargecompte(this.retraitBtn6));
                container7.add(getRechargecart(this.retraitBtn7));
                arrayList = arrayList2;
                arrayList.add(new B3gContainer(container7, " SERVICES "));
                arrayList.add(new B3gContainer(container8, " PLAFONDS "));
                arrayList.add(new B3gContainer(container5, " OPERATIONS "));
                label = label4;
                button = button2;
                container = container6;
            } else {
                arrayList = arrayList2;
                if (this.Crd.cardType.equals("P")) {
                    label = label4;
                    container = container6;
                    container7.add(BoxLayout.encloseY(createQRAccordion("Retrait", "", this.retraitBtn, "Activez ou désactivez les retraits des guichets CIH bank et des autres banques"), createQRAccordion("Paiement par carte", "", this.retraitBtn2, "Activez ou désactivez les paiements TPE chez les commerçants au Maroc et à l’international"), createQRAccordion("Paiement sur internet \u200b", "", this.retraitBtn3, "Activez ou désactivez les paiements e-commerce")));
                    container7.add(getCartContainer(this.retraitBtn4));
                    arrayList.add(new B3gContainer(container7, " SERVICES "));
                    arrayList.add(new B3gContainer(container8, " PLAFONDS "));
                    arrayList.add(new B3gContainer(container5, " OPERATIONS "));
                    button = button2;
                } else {
                    label = label4;
                    container = container6;
                    button = button2;
                    container7.add(BoxLayout.encloseY(createQRAccordion("Retrait", "", this.retraitBtn, "Activez ou désactivez les retraits des guichets CIH bank et des autres banques"), createQRAccordion("Paiement par carte", "À l’international", this.retraitBtn2, "Activez ou désactivez les paiements TPE chez les commerçants au Maroc et à l’international"), createQRAccordion("Paiement sur internet \u200b", "", this.retraitBtn3, "Activez ou désactivez les paiements e-commerce")));
                    container7.add(getCartContainer(this.retraitBtn4));
                    container7.add(getCartContainer1(this.retraitBtn5));
                    arrayList.add(new B3gContainer(container7, " SERVICES "));
                    arrayList.add(new B3gContainer(container5, " OPERATIONS "));
                }
            }
            Container encloseY = BoxLayout.encloseY(getInfoPalafondsCnt("PLAFOND DE PAIEMENT TPE", "Vous disposez d’un plafond de retrait de 10.000 Dhs sur 7 jours", "10.000", 30), getInfoPalafondsCnt("PLAFOND DE RETRAIT", "Vous disposez d’un plafond de retrait de 5000 Dhs sur 7 jours", "5.000", 70));
            encloseY.setScrollableY(true);
            encloseY.setScrollVisible(false);
            arrayList4.add(encloseY);
            arrayList4.add(BoxLayout.encloseY(getInfoPalafondsCnt("PLAFOND DE RETRAIT", "Vous disposez d’un plafond de retrait de 5000 Dhs sur 7 jours", "10.000", 50), getInfoPalafondsCnt("PLAFOND DE RETRAIT", "Vous disposez d’un plafond de retrait de 5000 Dhs sur 7 jours", "5.000", 10)));
            Button button3 = new Button("MODIFIER LES PLAFONDS");
            button3.setUIID("GoButton");
            button3.addActionListener(new 2());
            container8.add("Center", drawAccordion(arrayList3, arrayList4));
            container8.add("South", button3);
            container7.add(getOppositionContainer());
            this.uiManager.DrawTabsWithNavigation(container2, arrayList);
            Button button4 = button;
            button4.setHidden(true);
            button4.setName("flottButton");
            this.cntBtnFlot.setHidden(false);
            button4.addActionListener(new 3());
            onScrollAnimation(container);
            onScrollAnimation(container7);
            container2.getAllStyles().setFgColor(16777215);
            this.cntBtnFlot.addComponent(button4);
            this.CntGlobalCard.add(this.cntCard);
            this.cntCardItemV3.add(label);
            this.cntCardItemV3.add(this.CntGlobalCard);
            this.cntCardItemV3.add(container2);
            this.thisContainer.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(100), this.cntCardItemV3);
            container5.add(LayeredLayout.encloseIn(FlowLayout.encloseCenterBottom(this.cntBtnFlot)));
            this.thisContainer.setName("thisContainer");
            container5.setPreferredH(this.thisContainer.getPreferredH());
            Settings.setNightMode(this.thisContainer, 0);
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
            new getCardsService(CardsDetailPageNew.this, null).execute(CardsDetailPageNew.this.cntCard);
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardsDetailPageNew.this.uiManager.NavigateToPageById(137);
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardsDetailPageNew.this.cntBtnFlot.setHidden(true);
            CardsDetailPageNew.this.thisContainer.revalidate();
            CardsDetailPageNew cardsDetailPageNew = CardsDetailPageNew.this;
            CardsDetailPageNew.access$100(cardsDetailPageNew, cardsDetailPageNew.Crd);
        }
    }

    class 4 implements ScrollListener {
        final /* synthetic */ Container val$listCont;

        4(Container container) {
            this.val$listCont = container;
        }

        public void scrollChanged(int i, int i2, int i3, int i4) {
            if (i2 >= 0) {
                try {
                    if (CardsDetailPageNew.this.CntGlobalCard.getPreferredH() > 0) {
                        CardsDetailPageNew.this.CntGlobalCard.setPreferredH(((CardsDetailPageNew.this.cardWidth * 54) / 100) - i2);
                        if (CardsDetailPageNew.this.CntGlobalCard.getPreferredH() < 0 || CardsDetailPageNew.this.CardOperationList.getHeight() - this.val$listCont.getHeight() <= i2) {
                            return;
                        }
                        if (CardsDetailPageNew.this.CardOperationList.getHeight() - this.val$listCont.getHeight() == i2) {
                            CardsDetailPageNew.this.CntGlobalCard.setPreferredH(((CardsDetailPageNew.this.cardWidth * 54) / 100) - CardsDetailPageNew.this.CntGlobalCard.getPreferredH());
                        }
                        CardsDetailPageNew.this.cntCardItemV3.revalidate();
                        return;
                    }
                    if (i2 <= (CardsDetailPageNew.this.cardWidth * 54) / 100) {
                        CardsDetailPageNew.this.CntGlobalCard.setPreferredH(((CardsDetailPageNew.this.cardWidth * 54) / 100) - i2);
                        if (CardsDetailPageNew.this.CntGlobalCard.getPreferredH() < 0 || CardsDetailPageNew.this.CardOperationList.getHeight() - this.val$listCont.getHeight() < i2) {
                            return;
                        }
                        CardsDetailPageNew.this.cntCardItemV3.revalidate();
                    }
                } catch (Throwable unused) {
                }
            }
        }
    }

    private void onScrollAnimation(Container container) {
        container.addScrollListener(new 4(container));
    }

    public Component getOppositionContainer() {
        Container container = new Container(new BorderLayout());
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setPadding(1, 1, 1, 1);
        container.getAllStyles().setMargin(6, 6, 4, 4);
        new Stroke(2.0f, 2, 0, 1.0f);
        container.getAllStyles().setBgColor(16777215);
        container.getAllStyles().setBgTransparency(255);
        Container container2 = new Container(new FlowLayout(4, 4));
        container2.getAllStyles().setMarginUnit(2);
        container2.getAllStyles().setMarginLeft(2);
        container2.getAllStyles().setMarginRight(2);
        Label label = new Label();
        this.lbllogoooo = label;
        label.setIcon(this.uiManager.ressource.getImage("OpositionLogo.png"));
        container2.add(this.lbllogoooo);
        container.add("West", container2);
        Button button = new Button();
        this.btnopposi = button;
        button.setText("FAIRE OPPOSITION À MA CARTE");
        this.btnopposi.setUIID("Container");
        this.btnopposi.getAllStyles().setAlignment(1);
        this.btnopposi.getAllStyles().setFgColor(14767974);
        this.btnopposi.addActionListener(new 5());
        Container container3 = new Container(new BoxLayout(2));
        container3.add(this.btnopposi);
        container.add("Center", container3);
        FlowLayout flowLayout = new FlowLayout(1, 0);
        flowLayout.setFillRows(true);
        Container container4 = new Container(flowLayout);
        container4.add(container);
        return LayeredLayout.encloseIn(container4);
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.objctCrdCurrent = CardsDetailPageNew.this.Crd;
            CardsDetailPageNew.this.uiManager.NavigateToPageById(138);
        }
    }

    public Component getBlocageContainer() {
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
        label.setIcon(this.uiManager.ressource.getImage("switch2.png"));
        OnOffSwitch onOffSwitch = new OnOffSwitch();
        onOffSwitch.setValue(true);
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
        container4.getAllStyles().setMargin(0, 0, 5, 5);
        Label label2 = new Label();
        label2.setText("Carte débloquée");
        onOffSwitch.addActionListener(new CardsDetailPageNew$$ExternalSyntheticLambda10(this, label2));
        container4.add(label2);
        container.add("East", container3);
        container.add("West", container2);
        Label label3 = new Label();
        label3.setText("Blocage");
        label3.setUIID("Container");
        label2.getAllStyles().setFont(Font.createTrueTypeFont("Aller_Bd", "Aller_Bd.ttf").derive(Display.getInstance().convertToPixels(2.0f), 0));
        label2.getAllStyles().setFgColor(7368816);
        label3.getAllStyles().setAlignment(1);
        Container container5 = new Container(new BoxLayout(2));
        container5.getAllStyles().setMarginUnit(1);
        container5.add(label3);
        container.add("Center", container5);
        container.add("South", container4);
        FlowLayout flowLayout = new FlowLayout(1, 0);
        flowLayout.setFillRows(true);
        Container container6 = new Container(flowLayout);
        container6.add(container);
        return LayeredLayout.encloseIn(container6);
    }

    /* synthetic */ void lambda$getBlocageContainer$0$com-b3g-ui-page-CardsDetailPageNew(Label label, ActionEvent actionEvent) {
        if (!this.OnOffSwitch) {
            this.cntGlbCardCenterCnt.getAllStyles().setMargin(4, 0, 3, 3);
            this.btnopposi.setHidden(true);
            this.acc.setEnabled(false);
            this.lblBlocCard.setIcon(this.uiManager.ressource.getImage("locked.png"));
            this.lblBlocCard.setVisible(true);
            this.lbllogoooo.setHidden(true);
            this.c.getAllStyles().setBgImage(GraphicTools.getGrayImage(URLImage.createToStorage(this.encImage, "https://www.cihnet.co.ma" + this.Crd.imagPath, "https://www.cihnet.co.ma" + this.Crd.imagPath, URLImage.RESIZE_SCALE)));
            this.retraitBtn.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("withdraw.png")));
            this.retraitBtn2.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("Payment2.png")));
            this.retraitBtn3.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("online-shop2.png")));
            this.retraitBtn4.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("Contactless2.png")));
            this.retraitBtn5.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("Code_cart_2.png")));
            this.retraitBtn6.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("Decharge.png")));
            this.retraitBtn7.setIcon(GraphicTools.getGrayImage(this.uiManager.ressource.getImage("Recharge-carte.png")));
            this.retraitBtn.setEnabled(false);
            label.setText("Carte bloquée");
            this.retraitBtn.repaint();
            this.retraitBtn2.repaint();
            this.retraitBtn3.repaint();
            this.retraitBtn4.repaint();
            this.retraitBtn5.repaint();
            this.retraitBtn6.repaint();
            this.retraitBtn7.repaint();
            this.c.revalidate();
            this.btnopposi.repaint();
            this.lbllogoooo.repaint();
            this.lblBlocCard.repaint();
        } else {
            this.acc.setEnabled(true);
            this.btnopposi.setHidden(false);
            this.cntGlbCardCenterCnt.getAllStyles().setMargin(4, 0, 3, 3);
            this.lblBlocCard.setIcon(this.uiManager.ressource.getImage("locked.png"));
            this.lblBlocCard.setVisible(false);
            this.lbllogoooo.setHidden(false);
            this.c.getAllStyles().setBgImage(URLImage.createToStorage(this.encImage, "https://www.cihnet.co.ma" + this.Crd.imagPath, "https://www.cihnet.co.ma" + this.Crd.imagPath, URLImage.RESIZE_SCALE));
            this.retraitBtn.setIcon(this.uiManager.ressource.getImage("withdraw.png"));
            this.retraitBtn2.setIcon(this.uiManager.ressource.getImage("Payment2.png"));
            this.retraitBtn3.setIcon(this.uiManager.ressource.getImage("online-shop2.png"));
            this.retraitBtn4.setIcon(this.uiManager.ressource.getImage("Contactless2.png"));
            this.retraitBtn5.setIcon(this.uiManager.ressource.getImage("Code_cart_2.png"));
            this.retraitBtn6.setIcon(this.uiManager.ressource.getImage("Decharge.png"));
            this.retraitBtn7.setIcon(this.uiManager.ressource.getImage("Recharge-carte.png"));
            this.retraitBtn.repaint();
            this.retraitBtn2.repaint();
            this.retraitBtn3.repaint();
            this.retraitBtn4.repaint();
            this.retraitBtn5.repaint();
            this.retraitBtn6.repaint();
            this.retraitBtn7.repaint();
            label.setText("Carte débloquée");
            this.c.revalidate();
            this.btnopposi.repaint();
            this.lblBlocCard.repaint();
            this.lbllogoooo.repaint();
        }
        this.OnOffSwitch = !this.OnOffSwitch;
    }

    public Component getSoldeContainer() {
        Container container = new Container(new BorderLayout());
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setPadding(1, 1, 1, 1);
        container.getAllStyles().setMargin(1.5f, 1.5f, 4.0f, 4.0f);
        container.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(1010582364).strokeOpacity(120).stroke(new Stroke(2.0f, 2, 0, 1.0f)));
        container.getAllStyles().setBgColor(16777215);
        container.getAllStyles().setBgTransparency(255);
        Component container2 = new Container(new FlowLayout(4, 4));
        container2.getAllStyles().setMarginUnit(2);
        container2.getAllStyles().setMarginLeft(2);
        container2.getAllStyles().setMarginRight(2);
        Container container3 = new Container(new BoxLayout(2));
        container3.getAllStyles().setMargin(0, 0, 8, 8);
        Label label = new Label();
        label.setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().feeAmountCardOpperation + " MAD");
        label.getAllStyles().setFgColor(1817145);
        container3.add(label);
        container.add("East", container2);
        Label label2 = new Label();
        label2.setText("Solde disponible");
        label2.setUIID("Container");
        Font.createTrueTypeFont("Aller_Bd", "Aller_Bd.ttf").derive(Display.getInstance().convertToPixels(2.0f), 0);
        label2.getAllStyles().setAlignment(1);
        Container container4 = new Container(new BoxLayout(2));
        container4.add(label2);
        container.add("Center", container4);
        container.add("South", container3);
        FlowLayout flowLayout = new FlowLayout(1, 0);
        flowLayout.setFillRows(true);
        Container container5 = new Container(flowLayout);
        container5.add(container);
        return LayeredLayout.encloseIn(container5);
    }

    private void showPopup(Card card) {
        Container container;
        B3gDialogBlur b3gDialogBlur;
        Container container2;
        String str;
        String str2;
        String num;
        Container container3;
        B3gDialogBlur b3gDialogBlur2 = new B3gDialogBlur();
        Container container4 = new Container(new BorderLayout());
        Container container5 = new Container(new GridLayout(1, 2));
        Container container6 = new Container(new FlowLayout(4, 0));
        Button button = new Button(this.uiManager.ressource.getImage("iconeMenu.png"));
        button.setUIID("Container");
        button.setName("btnClose");
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            container6.getAllStyles().setMarginBottom(2);
        } else {
            container6.getAllStyles().setMarginBottom(4.3f);
        }
        Button button2 = new Button(this.uiManager.ressource.getImage("iconPlafondCartes.png"));
        button2.setText("Gérer Plafonds");
        Button button3 = new Button(this.uiManager.ressource.getImage("IconActionsSurCarte.png"));
        button3.setText("Gérer Cartes");
        Button button4 = new Button(this.uiManager.ressource.getImage("iocneRechargerCarte.png"));
        button4.setText(" Recharger Cartes ");
        Button button5 = new Button(this.uiManager.ressource.getImage("iconeDebiterCarte.png"));
        button5.setText("Restituer Cartes");
        Button button6 = new Button(this.uiManager.ressource.getImage("dotation.png"));
        button6.setText("Gérer Dotations");
        Button button7 = new Button(this.uiManager.ressource.getImage("iconeRecalculePin.png"));
        button7.setText("Renvoi PIN");
        button2.getUnselectedStyle().setBorder(RoundBorder.create().shadowOpacity(90));
        button3.getUnselectedStyle().setBorder(RoundBorder.create().shadowOpacity(90));
        button4.getUnselectedStyle().setBorder(RoundBorder.create().shadowOpacity(90));
        button5.getUnselectedStyle().setBorder(RoundBorder.create().shadowOpacity(90));
        button6.getUnselectedStyle().setBorder(RoundBorder.create().shadowOpacity(90));
        button7.getUnselectedStyle().setBorder(RoundBorder.create().shadowOpacity(90));
        Button button8 = new Button(" ");
        button8.setUIID("Container");
        button2.setUIID("Container");
        button3.setUIID("Container");
        button4.setUIID("Container");
        button5.setUIID("Container");
        button6.setUIID("Container");
        button7.setUIID("Container");
        button2.setTextPosition(2);
        button2.setVerticalAlignment(0);
        button3.setTextPosition(2);
        button3.setVerticalAlignment(0);
        button4.setTextPosition(2);
        button4.setVerticalAlignment(0);
        button5.setTextPosition(2);
        button5.setVerticalAlignment(0);
        button6.setTextPosition(2);
        button6.setVerticalAlignment(0);
        button7.setTextPosition(2);
        button7.setVerticalAlignment(0);
        Style allStyles = button2.getAllStyles();
        Style allStyles2 = button3.getAllStyles();
        Style allStyles3 = button4.getAllStyles();
        Style allStyles4 = button5.getAllStyles();
        Style allStyles5 = button6.getAllStyles();
        Style allStyles6 = button7.getAllStyles();
        allStyles.setMarginUnit(1);
        allStyles2.setMarginUnit(1);
        allStyles3.setMarginUnit(1);
        allStyles4.setMarginUnit(1);
        allStyles5.setMarginUnit(1);
        allStyles6.setMarginUnit(1);
        float convertToPixels = Display.getInstance().convertToPixels(2.2f);
        allStyles.setFont(Font.createSystemFont(0, 0, 8).derive(convertToPixels, 0));
        allStyles2.setFont(Font.createSystemFont(0, 0, 8).derive(convertToPixels, 0));
        allStyles3.setFont(Font.createSystemFont(0, 0, 8).derive(convertToPixels, 0));
        allStyles4.setFont(Font.createSystemFont(0, 0, 8).derive(convertToPixels, 0));
        allStyles5.setFont(Font.createSystemFont(0, 0, 8).derive(convertToPixels, 0));
        allStyles6.setFont(Font.createSystemFont(0, 0, 8).derive(convertToPixels, 0));
        CardsDetailPageNew$$ExternalSyntheticLambda0 cardsDetailPageNew$$ExternalSyntheticLambda0 = new CardsDetailPageNew$$ExternalSyntheticLambda0(b3gDialogBlur2);
        this.cntBtnFlot.setHidden(false);
        CardsDetailPageNew$$ExternalSyntheticLambda2 cardsDetailPageNew$$ExternalSyntheticLambda2 = new CardsDetailPageNew$$ExternalSyntheticLambda2(this, card, b3gDialogBlur2);
        CardsDetailPageNew$$ExternalSyntheticLambda3 cardsDetailPageNew$$ExternalSyntheticLambda3 = new CardsDetailPageNew$$ExternalSyntheticLambda3(this, card, b3gDialogBlur2);
        CardsDetailPageNew$$ExternalSyntheticLambda4 cardsDetailPageNew$$ExternalSyntheticLambda4 = new CardsDetailPageNew$$ExternalSyntheticLambda4(this, card, b3gDialogBlur2);
        CardsDetailPageNew$$ExternalSyntheticLambda5 cardsDetailPageNew$$ExternalSyntheticLambda5 = new CardsDetailPageNew$$ExternalSyntheticLambda5(this, card, b3gDialogBlur2);
        CardsDetailPageNew$$ExternalSyntheticLambda6 cardsDetailPageNew$$ExternalSyntheticLambda6 = new CardsDetailPageNew$$ExternalSyntheticLambda6(this, card, b3gDialogBlur2);
        CardsDetailPageNew$$ExternalSyntheticLambda7 cardsDetailPageNew$$ExternalSyntheticLambda7 = new CardsDetailPageNew$$ExternalSyntheticLambda7(this, card, b3gDialogBlur2);
        button8.addActionListener(cardsDetailPageNew$$ExternalSyntheticLambda0);
        button.addActionListener(cardsDetailPageNew$$ExternalSyntheticLambda0);
        button2.addActionListener(cardsDetailPageNew$$ExternalSyntheticLambda2);
        button3.addActionListener(cardsDetailPageNew$$ExternalSyntheticLambda3);
        button4.addActionListener(cardsDetailPageNew$$ExternalSyntheticLambda5);
        button5.addActionListener(cardsDetailPageNew$$ExternalSyntheticLambda6);
        button6.addActionListener(cardsDetailPageNew$$ExternalSyntheticLambda4);
        button7.addActionListener(cardsDetailPageNew$$ExternalSyntheticLambda7);
        int iter = getIter(card.cardCategory);
        if (iter == 1) {
            container = container5;
            b3gDialogBlur = b3gDialogBlur2;
            container2 = container6;
            container2.setLayout(new FlowLayout(4, 0));
            container2.add(button);
            if (card.plafondType.equals("N")) {
                str = Integer.toString(iter);
                container.setLayout(new GridLayout(1, 2));
                Container container7 = new Container(new FlowLayout(1, 4));
                Container container8 = new Container(new FlowLayout(3, 4));
                container7.add(button3);
                container8.add(button7);
                container.add(container7);
                container.add(container8);
                allStyles2.setMarginLeft(14);
                allStyles6.setMarginRight(14);
                container.getAllStyles().setMarginBottom(5);
            } else {
                String num2 = Integer.toString(iter + 1);
                container.setLayout(new LayeredLayout());
                Container container9 = new Container(new FlowLayout(4, 0));
                Container container10 = new Container(new FlowLayout(1, 2));
                Container container11 = new Container(new FlowLayout(3, 2));
                container10.add(button2);
                container9.add(button3);
                container11.add(button7);
                container.add(container10);
                container.add(container9);
                container.add(container11);
                allStyles.setMarginLeft(14);
                allStyles6.setMarginRight(14);
                allStyles2.setMarginBottom(7);
                container.getAllStyles().setMarginBottom(1);
                str = num2;
            }
        } else if (iter == 2) {
            container = container5;
            b3gDialogBlur = b3gDialogBlur2;
            container2 = container6;
            if (card.plafondType.equals("N")) {
                container2.setLayout(new FlowLayout(4, 0));
                container2.add(button);
                num = Integer.toString(iter);
                container.setLayout(new LayeredLayout());
                Container container12 = new Container(new FlowLayout(4, 0));
                Container container13 = new Container(new FlowLayout(1, 2));
                Container container14 = new Container(new FlowLayout(3, 2));
                container13.add(button7);
                container12.add(button3);
                container14.add(button6);
                container.add(container13);
                container.add(container12);
                container.add(container14);
                allStyles6.setMarginLeft(14);
                allStyles5.setMarginRight(14);
                allStyles2.setMarginBottom(7);
                container.getAllStyles().setMarginBottom(1);
                str = num;
            } else {
                String num3 = Integer.toString(iter + 3);
                container2.setLayout(new LayeredLayout());
                container.setLayout(new GridLayout(1, 2));
                Container container15 = new Container(new FlowLayout(1, 4));
                str2 = num3;
                Container container16 = new Container(new FlowLayout(3, 4));
                Container container17 = new Container(new FlowLayout(1, 0));
                Container container18 = new Container(new FlowLayout(3, 0));
                Container container19 = new Container(new FlowLayout(4, 2));
                container15.add(button2);
                container16.add(button3);
                container17.add(button7);
                container18.add(button6);
                container19.add(button);
                container.add(container15);
                container.add(container16);
                container2.add(container17);
                container2.add(container19);
                container2.add(container18);
                allStyles.setMarginLeft(26);
                allStyles2.setMarginRight(26);
                allStyles6.setMarginLeft(8);
                allStyles5.setMarginRight(8);
                container.getAllStyles().setMarginBottom(5);
                str = str2;
            }
        } else if (iter == 3) {
            container = container5;
            b3gDialogBlur = b3gDialogBlur2;
            container2 = container6;
            if (card.plafondType.equals("N")) {
                container2.setLayout(new FlowLayout(4, 0));
                container2.add(button);
                num = Integer.toString(iter);
                container.setLayout(new LayeredLayout());
                Container container20 = new Container(new FlowLayout(4, 0));
                Container container21 = new Container(new FlowLayout(1, 2));
                Container container22 = new Container(new FlowLayout(3, 2));
                container20.add(button4);
                container21.add(button3);
                container22.add(button7);
                container.add(container20);
                container.add(container21);
                container.add(container22);
                allStyles2.setMarginLeft(14);
                allStyles6.setMarginRight(14);
                allStyles3.setMarginBottom(7);
                container.getAllStyles().setMarginBottom(1);
                str = num;
            } else {
                String num4 = Integer.toString(iter + 2);
                container2.setLayout(new LayeredLayout());
                container.setLayout(new GridLayout(1, 2));
                Container container23 = new Container(new FlowLayout(1, 4));
                str2 = num4;
                Container container24 = new Container(new FlowLayout(3, 4));
                Container container25 = new Container(new FlowLayout(1, 0));
                Container container26 = new Container(new FlowLayout(3, 0));
                Container container27 = new Container(new FlowLayout(4, 2));
                container23.add(button2);
                container24.add(button3);
                container25.add(button4);
                container26.add(button7);
                container27.add(button);
                container.add(container23);
                container.add(container24);
                container2.add(container25);
                container2.add(container27);
                container2.add(container26);
                allStyles.setMarginLeft(26);
                allStyles2.setMarginRight(26);
                allStyles3.setMarginLeft(8);
                allStyles6.setMarginRight(8);
                container.getAllStyles().setMarginBottom(5);
                str = str2;
            }
        } else if (iter != 4) {
            str = "";
            container = container5;
            b3gDialogBlur = b3gDialogBlur2;
            container2 = container6;
        } else {
            if (card.plafondType.equals("N")) {
                String num5 = Integer.toString(iter + 1);
                container6.setLayout(new LayeredLayout());
                container3 = container5;
                container3.setLayout(new GridLayout(1, 2));
                Container container28 = new Container(new FlowLayout(1, 4));
                Container container29 = new Container(new FlowLayout(3, 4));
                b3gDialogBlur = b3gDialogBlur2;
                Container container30 = new Container(new FlowLayout(1, 0));
                Container container31 = new Container(new FlowLayout(3, 0));
                Container container32 = new Container(new FlowLayout(4, 2));
                container28.add(button7);
                container29.add(button3);
                container30.add(button4);
                container31.add(button5);
                container32.add(button);
                container3.add(container28);
                container3.add(container29);
                container6.add(container30);
                container6.add(container32);
                container6.add(container31);
                allStyles6.setMarginLeft(26);
                allStyles2.setMarginRight(26);
                allStyles3.setMarginLeft(8);
                allStyles4.setMarginRight(8);
                container3.getAllStyles().setMarginBottom(5);
                str = num5;
                container2 = container6;
            } else {
                container3 = container5;
                b3gDialogBlur = b3gDialogBlur2;
                String num6 = Integer.toString(iter + 1);
                container6.setLayout(new LayeredLayout());
                container3.setLayout(new GridLayout(2, 2));
                Container container33 = new Container(new FlowLayout(1, 4));
                Container container34 = new Container(new FlowLayout(3, 4));
                Container container35 = new Container(new FlowLayout(4, 0));
                Container container36 = new Container(new FlowLayout(1, 0));
                Container container37 = new Container(new FlowLayout(3, 0));
                Container container38 = new Container(new FlowLayout(4, 2));
                container33.add(button2);
                container34.add(button3);
                container35.add(button7);
                container36.add(button4);
                container37.add(button5);
                container38.add(button);
                container3.add(container33);
                container3.add(container34);
                container3.add(container35);
                container2 = container6;
                container2.add(container36);
                container2.add(container38);
                container2.add(container37);
                allStyles.setMarginLeft(26);
                allStyles2.setMarginRight(26);
                allStyles6.setMarginLeft(30);
                allStyles6.setMarginRight(30);
                allStyles6.setMarginBottom(26);
                allStyles3.setMarginLeft(8);
                allStyles4.setMarginRight(8);
                container3.getAllStyles().setMarginBottom(5);
                str = num6;
            }
            container = container3;
        }
        container4.add("Center", container);
        container4.add("South", container2);
        container.setName("blurCont");
        B3gDialogBlur b3gDialogBlur3 = b3gDialogBlur;
        b3gDialogBlur3.setBlurBackground(18.0f);
        b3gDialogBlur3.show(null, container4, MorphTransition.create(800).morph(this.thisContainer.getName(), b3gDialogBlur3.dialog.getName()), str);
    }

    static /* synthetic */ void lambda$showPopup$1(B3gDialogBlur b3gDialogBlur, ActionEvent actionEvent) {
        b3gDialogBlur.close();
    }

    /* synthetic */ void lambda$showPopup$2$com-b3g-ui-page-CardsDetailPageNew(Card card, B3gDialogBlur b3gDialogBlur, ActionEvent actionEvent) {
        getCardPlafondCnt(card, b3gDialogBlur.dialog);
    }

    /* synthetic */ void lambda$showPopup$3$com-b3g-ui-page-CardsDetailPageNew(Card card, B3gDialogBlur b3gDialogBlur, ActionEvent actionEvent) {
        getOperationListCnt(card, b3gDialogBlur.dialog);
    }

    /* synthetic */ void lambda$showPopup$4$com-b3g-ui-page-CardsDetailPageNew(Card card, B3gDialogBlur b3gDialogBlur, ActionEvent actionEvent) {
        getOperationDotListCnt(card, b3gDialogBlur.dialog);
    }

    /* synthetic */ void lambda$showPopup$5$com-b3g-ui-page-CardsDetailPageNew(Card card, B3gDialogBlur b3gDialogBlur, ActionEvent actionEvent) {
        getRechrgCardCnt(card, b3gDialogBlur.dialog);
    }

    /* synthetic */ void lambda$showPopup$6$com-b3g-ui-page-CardsDetailPageNew(Card card, B3gDialogBlur b3gDialogBlur, ActionEvent actionEvent) {
        getDebtCardCnt(card, b3gDialogBlur.dialog);
    }

    /* synthetic */ void lambda$showPopup$7$com-b3g-ui-page-CardsDetailPageNew(Card card, B3gDialogBlur b3gDialogBlur, ActionEvent actionEvent) {
        getRecalculPin(card, b3gDialogBlur.dialog);
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

    public Container getInfoPalafondsCnt(String str, String str2, String str3, int i) {
        Container container = new Container(BoxLayout.y());
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2, 2, 0, 0);
        Label label = new Label(str);
        SpanLabel spanLabel = new SpanLabel(str2);
        label.setUIID("g_lblDashBoardTitle");
        spanLabel.setTextUIID("lbl_black_small_messgs");
        spanLabel.getTextAllStyles().setFgColor(6710886);
        Label label2 = new Label(i + "% Utilisé");
        Label label3 = new Label(str3 + " DHS");
        Container container2 = new Container(new BorderLayout());
        label2.setUIID("SV_lbl_small_blue");
        label3.setUIID("SV_lbl_small_blue");
        label3.getAllStyles().setFgColor(0);
        container2.add("West", label2);
        container2.add("East", label3);
        container.add(label).add(spanLabel).add(getPoucentageCnt(i)).add(container2);
        return container;
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

    public Component getCartContainer(Button button) {
        Container container = new Container(new BorderLayout());
        container.setUIID("padding_1_1_1_1");
        OnOffSwitch onOffSwitch = new OnOffSwitch();
        onOffSwitch.setUIID("Container");
        onOffSwitch.setOff(" ");
        onOffSwitch.setOn(" ");
        Container container2 = new Container(new FlowLayout(4, 4));
        container2.getAllStyles().setMargin(0.5f, 0.5f, 0.0f, 0.0f);
        container2.getAllStyles().setMarginUnit(2);
        container2.getAllStyles().setMarginLeft(1);
        container2.getAllStyles().setMarginRight(1);
        button.setUIID("Container");
        button.setEnabled(true);
        container2.addComponent(button);
        Container container3 = new Container(new FlowLayout(4, 4));
        container3.getAllStyles().setMarginUnit(2);
        container3.getAllStyles().setMargin(0, 0, 0, 0);
        container3.getAllStyles().setMarginLeft(1);
        container3.getAllStyles().setMarginRight(1);
        container3.add(onOffSwitch);
        container.add("East", container3);
        container.add("West", container2);
        Label label = new Label();
        label.setText("Paiement sans contact");
        label.setUIID("about_link");
        Container container4 = new Container(new FlowLayout(1, 4));
        container4.getAllStyles().setMarginUnit(1);
        container4.add(label);
        container.add("Center", container4);
        FlowLayout flowLayout = new FlowLayout(1, 0);
        flowLayout.setFillRows(true);
        Container container5 = new Container(flowLayout);
        container5.add(container);
        return LayeredLayout.encloseIn(container5);
    }

    public Component getCartContainer1(Button button) {
        Container container = new Container(new BorderLayout());
        container.setUIID("padding_1_1_1_1");
        Container container2 = new Container(new FlowLayout(4, 4));
        container2.getAllStyles().setMargin(0.5f, 0.5f, 0.0f, 0.0f);
        container2.getAllStyles().setMarginUnit(2);
        container2.getAllStyles().setMarginLeft(1);
        container2.getAllStyles().setMarginRight(1);
        button.setUIID("Container");
        container2.addComponent(button);
        container.add("West", container2);
        Label label = new Label();
        label.setText("Renvoyer le code de ma carte bancaire");
        label.setUIID("about_link");
        Container container3 = new Container(new FlowLayout(1, 4));
        container3.add(label);
        container.add("Center", container3);
        FlowLayout flowLayout = new FlowLayout(1, 0);
        flowLayout.setFillRows(true);
        Container container4 = new Container(flowLayout);
        container4.add(container);
        return LayeredLayout.encloseIn(container4);
    }

    public Component getDechargecompte(Button button) {
        Container container = new Container(new BorderLayout());
        container.setUIID("padding_1_1_1_1");
        new Label();
        Container container2 = new Container(new FlowLayout(4, 4));
        container2.getAllStyles().setMargin(0.5f, 0.5f, 0.0f, 0.0f);
        container2.getAllStyles().setMarginUnit(2);
        container2.getAllStyles().setMarginLeft(1);
        container2.getAllStyles().setMarginRight(1);
        button.setUIID("Container");
        container2.addComponent(button);
        container.add("West", container2);
        Button button2 = new Button();
        button2.setText("  Décharger vers un compte");
        button2.addActionListener(new 6());
        button2.setUIID("about_link");
        Container container3 = new Container(new FlowLayout(1, 4));
        container3.add(button2);
        container.add("Center", container3);
        FlowLayout flowLayout = new FlowLayout(1, 0);
        flowLayout.setFillRows(true);
        Container container4 = new Container(flowLayout);
        container4.add(container);
        return LayeredLayout.encloseIn(container4);
    }

    class 6 implements ActionListener {
        6() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardsDetailPageNew.this.uiManager.NavigateToPageById(139);
        }
    }

    public Component getRechargecart(Button button) {
        Container container = new Container(new BorderLayout());
        container.setUIID("padding_1_1_1_1");
        Container container2 = new Container(new FlowLayout(4, 4));
        container2.getAllStyles().setMargin(0.5f, 0.5f, 0.0f, 0.0f);
        container2.getAllStyles().setMarginUnit(2);
        container2.getAllStyles().setMarginLeft(1);
        container2.getAllStyles().setMarginRight(1);
        button.setUIID("Container");
        container2.addComponent(button);
        container.add("West", container2);
        Label label = new Label();
        label.setText("Recharger la carte");
        label.setUIID("about_link");
        Container container3 = new Container(new FlowLayout(1, 4));
        container3.add(label);
        container.add("Center", container3);
        FlowLayout flowLayout = new FlowLayout(1, 0);
        flowLayout.setFillRows(true);
        Container container4 = new Container(flowLayout);
        container4.add(container);
        return LayeredLayout.encloseIn(container4);
    }

    private Accordion createQRAccordion(String str, String str2, Button button, String str3) {
        Accordion accordion = new Accordion();
        accordion.setScrollable(false);
        accordion.setAutoClose(true);
        accordion.setOpenIcon(this.uiManager.ressource.getImage("ArrowOpen (1).png"));
        accordion.setCloseIcon(this.uiManager.ressource.getImage("ArrowClose (1).png"));
        Container container = new Container(new BorderLayout());
        TableLayout tableLayout = new TableLayout(1, 3);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(10);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(80);
        Container container2 = new Container(tableLayout);
        container2.setUIID("padding_1_1_1_1");
        Container container3 = new Container(new FlowLayout(1, 4));
        button.setUIID("Container");
        container3.addComponent(button);
        TextArea textArea = new TextArea();
        textArea.setUIID("about_link");
        textArea.setText(str);
        textArea.setEditable(false);
        container2.addComponent(createConstraint, container3);
        container2.addComponent(createConstraint2, textArea);
        container.addComponent("Center", container2);
        container.addComponent("South", new Container(new BoxLayout(2)));
        Container container4 = new Container(new BoxLayout(2));
        container4.getAllStyles().setMargin(0, 0, 8, 8);
        Component textArea2 = new TextArea(str3);
        textArea2.setUIID("lbl_black_very_small");
        Container container5 = new Container(new BorderLayout());
        OnOffSwitch onOffSwitch = new OnOffSwitch();
        onOffSwitch.setUIID("Container");
        onOffSwitch.setOff(" ");
        onOffSwitch.setOn(" ");
        Container container6 = new Container(new FlowLayout(4, 4));
        container6.getAllStyles().setMarginUnit(2);
        container6.getAllStyles().setMarginLeft(1);
        container6.getAllStyles().setMarginRight(1);
        container6.add(onOffSwitch);
        Label label = new Label();
        label.setText("Au maroc");
        Font derive = Font.createTrueTypeFont("Aller_Bd", "Aller_Bd.ttf").derive(Display.getInstance().convertToPixels(2.0f), 0);
        label.getAllStyles().setFont(derive);
        label.getAllStyles().setFgColor(7368816);
        Container container7 = new Container(new FlowLayout(1, 4));
        container7.getAllStyles().setMarginUnit(1);
        container7.add(label);
        container5.add("West", container7);
        container5.add("East", container6);
        Container container8 = new Container(new BorderLayout());
        OnOffSwitch onOffSwitch2 = new OnOffSwitch();
        onOffSwitch2.setUIID("Container");
        onOffSwitch2.setOff(" ");
        onOffSwitch2.setOn(" ");
        Container container9 = new Container(new FlowLayout(4, 4));
        container9.getAllStyles().setMarginUnit(2);
        container9.getAllStyles().setMargin(0, 0, 0, 0);
        container9.getAllStyles().setMarginLeft(1);
        container9.getAllStyles().setMarginRight(1);
        container9.add(onOffSwitch2);
        Label label2 = new Label(str2);
        this.lbl22222 = label2;
        label2.getAllStyles().setFont(derive);
        this.lbl22222.getAllStyles().setFgColor(7368816);
        Container container10 = new Container(new FlowLayout(1, 4));
        container10.getAllStyles().setMarginUnit(1);
        container10.add(this.lbl22222);
        container8.add("Center", container10);
        container8.add("East", container9);
        container4.addComponent(textArea2);
        container4.add(container5);
        container4.add(container8);
        accordion.addContent(container, container4);
        accordion.revalidate();
        return accordion;
    }

    public Container getAccordion() {
        Image image = CihMBanking.detail_arrow;
        Image image2 = CihMBanking.theme.getImage("blueArrowRotated.png");
        Button button = new Button();
        this.b2 = button;
        button.setIcon(image2);
        this.mainCnt = new Container(BoxLayout.y());
        this.b2.setUIID("Container");
        this.b2.getAllStyles().setPadding(0, 0, 1, 1);
        this.header = new Container(new BorderLayout());
        new Container(new FlowLayout(4, 4));
        this.body = new Container(new BoxLayout(2));
        this.header.setLeadComponent(this.b2);
        this.header.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(0.2f).stroke(new Stroke(2.0f, 2, 0, 4.0f)).strokeColor(1945583).strokeOpacity(255));
        this.body.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(0.0f).stroke(new Stroke(2.0f, 2, 0, 4.0f)).strokeColor(1945583).strokeOpacity(255));
        this.header.getAllStyles().setBgColor(1945583);
        this.header.getAllStyles().setBgTransparency(255);
        Label label = new Label("Afficher les informations de la carte");
        label.getAllStyles().setMargin(0, 0, 4, 4);
        label.getAllStyles().setFgColor(16777215);
        this.header.add("East", this.b2);
        this.header.add("Center", label);
        this.b2.addActionListener(new CardsDetailPageNew$$ExternalSyntheticLambda1(this, label));
        Label label2 = new Label();
        label2.setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom);
        Label label3 = new Label();
        label3.setText(" Exp : " + this.Crd.expirationDate.substring(3, 5) + " " + this.Crd.expirationDate.substring(5, 6) + " " + this.Crd.expirationDate.substring(8, 10) + "   ");
        SpanLabel spanLabel = new SpanLabel();
        spanLabel.setText(" " + this.Crd.cardNumber.substring(0, 4) + " " + this.Crd.cardNumber.substring(4, 8) + " " + this.Crd.cardNumber.substring(8, 12) + " " + this.Crd.cardNumber.substring(12, 16));
        spanLabel.getTextAllStyles().setFgColor(1945583);
        this.body.add(spanLabel);
        this.body.add(label2);
        this.body.add(label3);
        this.body.setHidden(true);
        this.mainCnt.add(this.header).add(this.body);
        return this.mainCnt;
    }

    /* synthetic */ void lambda$getAccordion$8$com-b3g-ui-page-CardsDetailPageNew(Label label, ActionEvent actionEvent) {
        if (this.close.booleanValue()) {
            label.getAllStyles().setFgColor(16777215);
            this.header.getAllStyles().setBgColor(1945583);
            this.header.getAllStyles().setBgTransparency(255);
            this.body.setHidden(true);
            this.mainCnt.animateLayout(250);
        } else {
            label.getAllStyles().setFgColor(1945583);
            this.header.getAllStyles().setBgColor(16777215);
            this.header.getAllStyles().setBgTransparency(255);
            this.body.setHidden(false);
            this.mainCnt.getParent().animateLayout(250);
        }
        this.close = Boolean.valueOf(!this.close.booleanValue());
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

        /* synthetic */ getCardsService(CardsDetailPageNew cardsDetailPageNew, 1 r2) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Image doInBackground(Container... containerArr) {
            Container container = containerArr[0];
            this.c1 = container;
            container.add(Loading());
            this.c1.revalidate();
            return URLImage.createToStorage(CardsDetailPageNew.this.encImage, "https://www.cihnet.co.ma" + CardsDetailPageNew.this.Crd.imagPath, "https://www.cihnet.co.ma" + CardsDetailPageNew.this.Crd.imagPath, URLImage.RESIZE_SCALE);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onPostExecute(Image image) {
            CardsDetailPageNew.this.cntCard.removeAll();
            CardsDetailPageNew.access$200(CardsDetailPageNew.this, image);
            CardsDetailPageNew.this.cntCard.revalidate();
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
        this.c = new Container(new LayeredLayout());
        try {
            new UITimer(new CardsDetailPageNew$$ExternalSyntheticLambda8(this, image)).schedule(10, false, this.uiManager.currentForm);
            new UITimer(new CardsDetailPageNew$$ExternalSyntheticLambda9(this)).schedule(40, false, this.uiManager.currentForm);
            this.cntCard.add(this.c);
        } catch (Exception unused) {
        }
    }

    /* synthetic */ void lambda$SetCardsImage$9$com-b3g-ui-page-CardsDetailPageNew(Image image) {
        this.c.getAllStyles().setBgImage(image);
    }

    /* synthetic */ void lambda$SetCardsImage$10$com-b3g-ui-page-CardsDetailPageNew() {
        this.c.addComponent(this.cntGlbCardCenterCnt);
    }
}
