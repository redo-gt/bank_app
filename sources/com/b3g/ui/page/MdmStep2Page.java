package com.b3g.ui.page;

import com.b3g.services.B3gService;
import com.b3g.services.Webview;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MdmStep2Page extends B3GPage {
    int cardWidth;
    EncodedImage encImage;
    Image placeholder;
    Webview w;
    UIBuilder uib = new UIBuilder();
    boolean acceptTermsState = true;

    public MdmStep2Page(Object obj, Object obj2, int i) {
        int displayWidth = (Display.getInstance().getDisplayWidth() * 10) / 100;
        this.cardWidth = displayWidth;
        Image createImage = Image.createImage(displayWidth, displayWidth, 12569042);
        this.placeholder = createImage;
        this.encImage = EncodedImage.createFromImage(createImage, false);
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container();
        Container createContainer = this.uib.createContainer("/cihv3", "AgregationCnt2");
        Label label = (Label) this.uib.findByName("LogoLbl", createContainer);
        Webview webview = new Webview();
        webview.AgregationDetails(Webview.AgregationfidbackProcess());
        label.setIcon(URLImage.createToStorage(this.encImage, webview.bankIcon, webview.bankIcon, URLImage.RESIZE_SCALE));
        Label label2 = (Label) this.uib.findByName("NomLbl", createContainer);
        ((Label) this.uib.findByName("CompteLbl", createContainer)).setText(webview.type);
        ((Label) this.uib.findByName("NumeroLbl", createContainer)).setText(webview.account);
        label2.setText(webview.bankName);
        label.setText(this.w.account);
        this.thisContainer.add(createContainer);
        return this.thisContainer;
    }
}
