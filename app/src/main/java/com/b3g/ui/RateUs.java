package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.b3g.ui.page.Avis;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.UIBuilder;
import java.util.Date;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class RateUs {
    private final B3GUiManager manager;
    private final Dialog dialog = new Dialog();
    private final UIBuilder ui = new UIBuilder();

    public RateUs(B3GUiManager b3GUiManager) {
        this.manager = b3GUiManager;
    }

    public void init() {
        int i = Preferences.get("uses_count", 0);
        int difference = getDifference();
        String platformName = Display.getInstance().getPlatformName();
        if (i <= 5 || difference <= 64) {
            return;
        }
        platformName.hashCode();
        if (platformName.equals("and")) {
            AndroidWidget("https://play.google.com/store/apps/details?id=com.b3g.cih.online");
        } else if (platformName.equals("ios")) {
            IOSWidget("https://apps.apple.com/fr/app/cih-mobile/id664637221");
        }
        updateDate();
    }

    private void AndroidWidget(String str) {
        Container createContainer = this.ui.createContainer("/cihv3", "PopUpRateUs");
        Container container = (Container) this.ui.findByName("CntPopupBody", createContainer);
        Container createContainer2 = this.ui.createContainer("/cihv3", "DemandeRate");
        container.addComponent(createContainer2);
        Button button = (Button) this.ui.findByName("closeBtn", createContainer2);
        Button button2 = (Button) this.ui.findByName("rateUs", createContainer2);
        Button button3 = (Button) this.ui.findByName("tellUs", createContainer2);
        button.addActionListener(new RateUs$$ExternalSyntheticLambda0(this));
        button2.addActionListener(new RateUs$$ExternalSyntheticLambda1(this, str));
        button3.addActionListener(new RateUs$$ExternalSyntheticLambda2(this));
        config(createContainer);
    }

    /* synthetic */ void lambda$AndroidWidget$0$com-b3g-ui-RateUs(ActionEvent actionEvent) {
        this.dialog.dispose();
    }

    /* synthetic */ void lambda$AndroidWidget$1$com-b3g-ui-RateUs(String str, ActionEvent actionEvent) {
        Display.getInstance().execute(str);
        this.dialog.dispose();
    }

    /* synthetic */ void lambda$AndroidWidget$2$com-b3g-ui-RateUs(ActionEvent actionEvent) {
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            this.manager.NavigateToPage(new Avis(this.manager, null, 17));
            this.dialog.dispose();
        }
    }

    private void IOSWidget(String str) {
        Container createContainer = this.ui.createContainer("/cihv3", "PopUpRateUs");
        Container createContainer2 = this.ui.createContainer("/cihv3", "RateUsIOS");
        ((Container) this.ui.findByName("CntPopupBody", createContainer)).add(createContainer2);
        Button button = (Button) this.ui.findByName("closeBtn", createContainer2);
        Button button2 = (Button) this.ui.findByName("validateBtn", createContainer2);
        Container container = (Container) this.ui.findByName("starCnt", createContainer2);
        container.setLayout(new BoxLayout(2));
        Slider createStarRankSlider = createStarRankSlider();
        container.add(FlowLayout.encloseCenter(createStarRankSlider));
        button.addActionListener(new RateUs$$ExternalSyntheticLambda3(this));
        button2.addActionListener(new RateUs$$ExternalSyntheticLambda4(this, createStarRankSlider, str));
        config(createContainer);
    }

    /* synthetic */ void lambda$IOSWidget$3$com-b3g-ui-RateUs(ActionEvent actionEvent) {
        this.dialog.dispose();
    }

    /* synthetic */ void lambda$IOSWidget$4$com-b3g-ui-RateUs(Slider slider, String str, ActionEvent actionEvent) {
        if (slider.getProgress() > 0) {
            Display.getInstance().execute(str);
            this.dialog.dispose();
        }
    }

    public static void updateUsesState() {
        Preferences.set("uses_count", Preferences.get("uses_count", 0) + 1);
    }

    private void config(Container container) {
        container.setPreferredH(Display.getInstance().getDisplayHeight());
        container.setPreferredW(Display.getInstance().getDisplayWidth());
        this.dialog.setLayout(new BorderLayout());
        this.dialog.setAlwaysTensile(false);
        this.dialog.setDraggable(false);
        this.dialog.setScrollable(false);
        this.dialog.setTactileTouch(false);
        this.dialog.setTensileDragEnabled(false);
        this.dialog.getDialogStyle().setBgTransparency(230);
        this.dialog.getDialogStyle().setBgColor(1118481);
        this.dialog.getStyle().setPadding(0, 0, 0, 0);
        this.dialog.getStyle().setMargin(0, 0, 0, 0);
        this.dialog.getDialogStyle().setPadding(0, 0, 0, 0);
        this.dialog.getDialogStyle().setMargin(0, 0, 0, 0);
        this.dialog.revalidate();
        this.dialog.addComponent("Center", container);
        this.dialog.showPacked("Center", true);
    }

    private Slider createStarRankSlider() {
        Slider slider = new Slider();
        slider.setEditable(true);
        slider.setMinValue(0);
        slider.setMaxValue(10);
        Style style = new Style(45039, 0, Font.createTrueTypeFont("native:MainLight", "native:MainLight").derive(Display.getInstance().convertToPixels(5, true), 0), (byte) 0);
        Image image = FontImage.createMaterial((char) 59448, style).toImage();
        style.setOpacity(100);
        style.setFgColor(0);
        Image image2 = FontImage.createMaterial((char) 59448, style).toImage();
        initStarRankStyle(slider.getSliderEmptySelectedStyle(), image2);
        initStarRankStyle(slider.getSliderEmptyUnselectedStyle(), image2);
        initStarRankStyle(slider.getSliderFullSelectedStyle(), image);
        initStarRankStyle(slider.getSliderFullUnselectedStyle(), image);
        slider.setPreferredSize(new Dimension(image.getWidth() * 5, image.getHeight()));
        return slider;
    }

    private void initStarRankStyle(Style style, Image image) {
        style.setBackgroundType((byte) 2);
        style.setBorder(Border.createEmpty());
        style.setBgImage(image);
        style.setBgTransparency(0);
    }

    private int getDifference() {
        long j = Preferences.get("last_date_seen", 0L);
        if (j == 0) {
            return 65;
        }
        return Long.valueOf(((((new Date().getTime() - j) / 1000) / 60) / 60) / 24).intValue();
    }

    private void updateDate() {
        Preferences.set("last_date_seen", new Date().getTime());
    }
}
