package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.services.PinCard;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GWizard;
import com.b3g.ui.Step;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OppositionCardPage extends B3GPage {
    Card Crd;
    B3GWizard OppositionCard;
    public String imagPath;
    Step s1;
    Step s2;
    Step s3;
    public String statusCard;
    private final UIBuilder uib = new UIBuilder();
    private ArrayList cards = new ArrayList();
    private PinCard currentCard = new PinCard();

    public OppositionCardPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        Step step = new Step();
        this.s1 = step;
        step.stepCnt = OppositionCard();
        this.s2 = new Step();
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.s1);
        arrayList.add(this.s2);
        B3GWizard b3GWizard = new B3GWizard(arrayList);
        this.OppositionCard = b3GWizard;
        this.thisContainer = b3GWizard.drawWizard("", 16777215, 0);
        return this.thisContainer;
    }

    private Container OppositionCard() {
        UIBuilder uIBuilder = new UIBuilder();
        int displayWidth = (Display.getInstance().getDisplayWidth() * 90) / 100;
        Container createContainer = uIBuilder.createContainer("/cihv3", "CardOpposition2");
        int convertToPixels = Display.getInstance().convertToPixels(4.7f);
        int convertToPixels2 = Display.getInstance().convertToPixels(2.5f);
        Container container = (Container) uIBuilder.findByName("Cnt1", createContainer);
        Label label = (Label) uIBuilder.findByName("lblExpDate", createContainer);
        Label label2 = (Label) uIBuilder.findByName("lblCardNumber", createContainer);
        label2.setText(" " + ((Card) CihMBanking.sesPMTR.objctCrdCurrent).cardNumber.substring(0, 4) + " " + ((Card) CihMBanking.sesPMTR.objctCrdCurrent).cardNumber.substring(4, 8) + " " + ((Card) CihMBanking.sesPMTR.objctCrdCurrent).cardNumber.substring(8, 12) + " " + ((Card) CihMBanking.sesPMTR.objctCrdCurrent).cardNumber.substring(12, 16));
        label2.getAllStyles().setTextDecoration(32);
        label2.getAllStyles().setFont(Font.createSystemFont(0, 1, 0).derive(convertToPixels, 1));
        label.setText(" Exp : " + ((Card) CihMBanking.sesPMTR.objctCrdCurrent).expirationDate.substring(3, 5) + " " + ((Card) CihMBanking.sesPMTR.objctCrdCurrent).expirationDate.substring(5, 6) + " " + ((Card) CihMBanking.sesPMTR.objctCrdCurrent).expirationDate.substring(8, 10) + "   ");
        label.getAllStyles().setFont(Font.createSystemFont(0, 1, 8).derive(convertToPixels2, 1));
        label.getStyle().setFgColor(268435455);
        new Container(new FlowLayout(4, 4));
        container.setPreferredW(displayWidth);
        int i = (displayWidth * 54) / 100;
        container.setPreferredH(i);
        container.setUIID("g_CntTranspMsg");
        this.uiManager.ressource.getImage("carte_visa.png");
        EncodedImage createFromImage = EncodedImage.createFromImage(Image.createImage(displayWidth, i, 12569042), false);
        createContainer.putClientProperty("CardOpposition", this);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(4, 4, 4, 4);
        container.getAllStyles().setBgImage(URLImage.createToStorage(createFromImage, "https://www.cihnet.co.ma" + ((Card) CihMBanking.sesPMTR.objctCrdCurrent).imagPath + "A", "https://www.cihnet.co.ma" + ((Card) CihMBanking.sesPMTR.objctCrdCurrent).imagPath, URLImage.RESIZE_SCALE));
        return createContainer;
    }
}
