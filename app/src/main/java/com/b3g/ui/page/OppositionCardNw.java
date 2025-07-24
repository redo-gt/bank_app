package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.tools.SyncTask;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UITimer;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OppositionCardNw extends B3GPage {
    Container CardOperationList;
    Container CntGlobalCard;
    Card Crd;
    int cardWidth;
    Container cntCard;
    Container cntCardItemV3;
    Container cntGlbCardCenterCnt;
    EncodedImage encImage;
    Image placeholder;

    static /* synthetic */ void access$100(OppositionCardNw oppositionCardNw, Image image) {
        oppositionCardNw.SetCardsImage(image);
    }

    public OppositionCardNw(Object obj, Object obj2, int i) {
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
        try {
            this.thisContainer = new Container();
            TableLayout tableLayout = new TableLayout(1, 1);
            this.thisContainer.setLayout(tableLayout);
            this.cntCardItemV3 = new Container(new BoxLayout(2));
            this.CntGlobalCard = new Container(new FlowLayout(4, 4));
            Container container = new Container(new BoxLayout(2));
            Container container2 = new Container(new LayeredLayout());
            Container container3 = new Container(new BoxLayout(2));
            Container container4 = new Container(new LayeredLayout());
            Container container5 = new Container(new BoxLayout(2));
            this.cntGlbCardCenterCnt = new Container(new FlowLayout(4, 4));
            Container container6 = new Container(new FlowLayout(1, 4));
            Container container7 = new Container(new FlowLayout(4, 4));
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
            Label label3 = new Label("  Opposition sur carte");
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
            container.setScrollableY(false);
            container.setScrollVisible(false);
            container.getAllStyles().setMargin(0, 0, 1, 1);
            container3.setScrollableY(true);
            container3.setScrollVisible(false);
            container4.setScrollableY(false);
            container4.setScrollVisible(false);
            this.cntGlbCardCenterCnt.getAllStyles().setMargin(12, 0, 3, 3);
            container7.addComponent(label);
            container6.addComponent(label2);
            container5.addComponent(container7);
            container5.addComponent(container6);
            this.cntGlbCardCenterCnt.addComponent(container5);
            if (!this.Crd.imagPath.equals(" ")) {
                Display.getInstance().callSerially(new 1());
            } else {
                this.cntCard.setUIID("cardBg");
            }
            onScrollAnimation(container3);
            onScrollAnimation(container4);
            container.getAllStyles().setFgColor(16777215);
            this.CntGlobalCard.add(this.cntCard);
            this.cntCardItemV3.add(label3);
            this.cntCardItemV3.add(this.CntGlobalCard);
            this.cntCardItemV3.add(container);
            this.thisContainer.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(100), this.cntCardItemV3);
            this.cntCardItemV3.add(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "CardOpposition2"));
            this.thisContainer.setName("thisContainer");
            container2.setPreferredH(this.thisContainer.getPreferredH());
            this.thisContainer.getAllStyles().setMarginUnit(1);
            this.thisContainer.getAllStyles().setMargin(5, 0, 5, 5);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            return this.thisContainer;
        }
    }

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            new getCardsService(OppositionCardNw.this, null).execute(OppositionCardNw.this.cntCard);
        }
    }

    class 2 implements ScrollListener {
        final /* synthetic */ Container val$listCont;

        2(Container container) {
            this.val$listCont = container;
        }

        public void scrollChanged(int i, int i2, int i3, int i4) {
            if (i2 >= 0) {
                try {
                    if (OppositionCardNw.this.CntGlobalCard.getPreferredH() > 0) {
                        OppositionCardNw.this.CntGlobalCard.setPreferredH(((OppositionCardNw.this.cardWidth * 54) / 100) - i2);
                        if (OppositionCardNw.this.CntGlobalCard.getPreferredH() < 0 || OppositionCardNw.this.CardOperationList.getHeight() - this.val$listCont.getHeight() <= i2) {
                            return;
                        }
                        if (OppositionCardNw.this.CardOperationList.getHeight() - this.val$listCont.getHeight() == i2) {
                            OppositionCardNw.this.CntGlobalCard.setPreferredH(((OppositionCardNw.this.cardWidth * 54) / 100) - OppositionCardNw.this.CntGlobalCard.getPreferredH());
                        }
                        OppositionCardNw.this.cntCardItemV3.revalidate();
                        return;
                    }
                    if (i2 <= (OppositionCardNw.this.cardWidth * 54) / 100) {
                        OppositionCardNw.this.CntGlobalCard.setPreferredH(((OppositionCardNw.this.cardWidth * 54) / 100) - i2);
                        if (OppositionCardNw.this.CntGlobalCard.getPreferredH() < 0 || OppositionCardNw.this.CardOperationList.getHeight() - this.val$listCont.getHeight() < i2) {
                            return;
                        }
                        OppositionCardNw.this.cntCardItemV3.revalidate();
                    }
                } catch (Throwable unused) {
                }
            }
        }
    }

    private void onScrollAnimation(Container container) {
        container.addScrollListener(new 2(container));
    }

    private class getCardsService extends SyncTask {
        Container c1;

        private getCardsService() {
        }

        /* synthetic */ getCardsService(OppositionCardNw oppositionCardNw, 1 r2) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Image doInBackground(Container... containerArr) {
            Container container = containerArr[0];
            this.c1 = container;
            container.add(Loading());
            this.c1.revalidate();
            return URLImage.createToStorage(OppositionCardNw.this.encImage, "https://www.cihnet.co.ma" + OppositionCardNw.this.Crd.imagPath, "https://www.cihnet.co.ma" + OppositionCardNw.this.Crd.imagPath, URLImage.RESIZE_SCALE);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onPostExecute(Image image) {
            OppositionCardNw.this.cntCard.removeAll();
            OppositionCardNw.access$100(OppositionCardNw.this, image);
            OppositionCardNw.this.cntCard.revalidate();
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
            new UITimer(new OppositionCardNw$$ExternalSyntheticLambda0(this, container, image)).schedule(10, false, this.uiManager.currentForm);
            new UITimer(new OppositionCardNw$$ExternalSyntheticLambda1(this, container)).schedule(40, false, this.uiManager.currentForm);
            this.cntCard.add(container);
        } catch (Exception unused) {
        }
    }

    /* synthetic */ void lambda$SetCardsImage$0$com-b3g-ui-page-OppositionCardNw(Container container, Image image) {
        container.getAllStyles().setBgImage(getGrayImage(image));
    }

    /* synthetic */ void lambda$SetCardsImage$1$com-b3g-ui-page-OppositionCardNw(Container container) {
        container.addComponent(this.cntGlbCardCenterCnt);
    }

    private Image getGrayImage(Image image) {
        int[] rgb = image.getRGB();
        for (int i = 0; i < rgb.length; i++) {
            int i2 = rgb[i];
            if (i2 != 0) {
                int i3 = i2 - (((i2 / 256) / 256) * 65536);
                rgb[i] = i3;
                rgb[i] = i3 - ((i3 / 256) * 256);
                int round = (int) Math.round((((r3 + r5) + r2) / 768.0d) * 256.0d);
                rgb[i] = (round * 256) + round + (round * 65536);
            }
        }
        return Image.createImage(rgb, image.getWidth(), image.getHeight());
    }
}
