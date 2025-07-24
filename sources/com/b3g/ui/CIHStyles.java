package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CIHStyles {
    public static byte[] b = {1, 1, 1, 1};
    public static int colorBlack = 0;
    public static int colorBleu = 44530;
    public static int colorGris = 8355711;
    public static int colorGrisTxt = 6645093;
    public static int colorOrange = 15818018;
    public static int colorWhite = 16777215;

    public static Container setButtonStyle1(Button button, Image image) {
        Container container = new Container(new GridLayout(1, 1));
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setPadding(0.25f, 0.25f, 0.5f, 0.5f);
        container.getAllStyles().setMargin(0.5f, 0.5f, 0.5f, 0.5f);
        container.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(1.2f).stroke(new Stroke(2.5f, 2, 0, 4.0f)).strokeColor(colorOrange).strokeOpacity(255));
        button.setUIID("Container");
        button.getAllStyles().setAlignment(4);
        button.getAllStyles().setFgColor(colorOrange);
        if (image != null) {
            button.setIcon(image);
        }
        container.add(button);
        container.setLeadComponent(button);
        return container;
    }

    public static Container setButtonStyle1(Button button, Image image, Float f) {
        Container container = new Container(new GridLayout(1, 1));
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.0f, 1.0f, 0.5f, 0.5f);
        container.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(f.floatValue()).stroke(new Stroke(2.5f, 2, 0, 4.0f)).strokeColor(colorOrange).strokeOpacity(255));
        button.setUIID("Container");
        button.getAllStyles().setAlignment(4);
        button.getAllStyles().setFgColor(colorOrange);
        if (image != null) {
            button.setIcon(image);
        }
        container.add(button);
        container.setLeadComponent(button);
        return container;
    }

    public static Container setButtonStyleFondDonseisme(Button button, Image image) {
        TableLayout tableLayout = new TableLayout(1, 4);
        Container container = new Container(tableLayout);
        Label label = new Label("Fond de solidarité séisme");
        Label label2 = new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("charity_box.png"));
        label.getAllStyles().setFont(Font.createTrueTypeFont("native:MainRegular", "native:MainRegular").derive(Display.getInstance().convertToPixels(2.5f), 0));
        label.getAllStyles().setBgColor(15818018);
        label2.getAllStyles().setBgColor(16777215);
        label.getAllStyles().setBgTransparency(0);
        label2.getAllStyles().setBgTransparency(0);
        label.getAllStyles().setAlignment(4);
        label2.getAllStyles().setAlignment(4);
        label2.getAllStyles().setPadding(0, 0, 0, 0);
        label2.getAllStyles().setMarginUnit(1);
        label2.getAllStyles().setMargin(0.0f, 0.5f, 0.0f, 0.0f);
        Container encloseYCenter = BoxLayout.encloseYCenter(label2, label);
        encloseYCenter.setUIID("Container");
        container.add(tableLayout.createConstraint().widthPercentage(10).heightPercentage(100), new Container(new FlowLayout())).add(tableLayout.createConstraint().widthPercentage(15).heightPercentage(100), image).add(tableLayout.createConstraint().widthPercentage(65).heightPercentage(100), encloseYCenter).add(new Container(new FlowLayout()));
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setMarginUnit(1);
        label2.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.0f, 1.0f, 0.0f, 0.0f);
        container.getAllStyles().setMargin(0.5f, 0.5f, 0.5f, 0.5f);
        container.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(1.2f).stroke(new Stroke(2.5f, 2, 0, 4.0f)).strokeColor(colorOrange).strokeOpacity(255));
        button.setUIID("Container");
        container.setLeadComponent(button);
        return container;
    }

    public static Container setButtonStyle2(Button button, Image image) {
        Container container = new Container(new GridLayout(1, 1));
        button.setUIID("Container");
        button.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(1.8f));
        button.getAllStyles().setAlignment(4);
        button.getAllStyles().setBgColor(colorOrange);
        button.getAllStyles().setFgColor(16777215);
        button.getAllStyles().setBgTransparency(255);
        if (image != null) {
            button.setIcon(image);
        }
        container.add(button);
        container.setLeadComponent(button);
        return container;
    }

    public static Container setButtonStyle2(Button button, Image image, int i, int i2) {
        Container container = new Container(new GridLayout(1, 1));
        button.setUIID("Container");
        button.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(1.8f));
        button.getAllStyles().setAlignment(4);
        button.getAllStyles().setBgColor(i2);
        button.getAllStyles().setFgColor(i);
        button.getAllStyles().setBgTransparency(255);
        if (image != null) {
            button.setIcon(image);
        }
        container.add(button);
        container.setLeadComponent(button);
        return container;
    }

    public static Container setButtonStyle3(Button button, Image image, float f, float f2) {
        Container container = new Container(new FlowLayout(4));
        button.setUIID("Container");
        button.getAllStyles().setBorder(RoundRectBorder.createEmpty());
        button.getAllStyles().setPaddingUnit(1);
        button.getAllStyles().setPadding(f, f, f2, f2);
        button.getAllStyles().setAlignment(4);
        button.getAllStyles().setBgColor(colorOrange);
        button.getAllStyles().setFgColor(16777215);
        button.getAllStyles().setBgTransparency(255);
        if (image != null) {
            button.setIcon(image);
        }
        container.add(button);
        container.setLeadComponent(button);
        return container;
    }

    public static Container setButtonStyle3(Button button, Image image, int i, int i2) {
        Container container = new Container(new GridLayout(1, 1));
        button.setUIID("Container");
        button.getAllStyles().setBorder(RoundRectBorder.createEmpty());
        button.getAllStyles().setAlignment(4);
        button.getAllStyles().setBgColor(i2);
        button.getAllStyles().setFgColor(i);
        button.getAllStyles().setBgTransparency(255);
        if (image != null) {
            button.setIcon(image);
        }
        container.add(button);
        container.setLeadComponent(button);
        return container;
    }

    public static void setSelectedStyle(Button button, Image image) {
        button.getAllStyles().setAlignment(1);
        button.getAllStyles().setFgColor(colorBleu);
        button.getAllStyles().setTextDecoration(1);
        button.setIcon(image);
    }

    public static void setUnselectedStyle(Button button, Image image) {
        button.setUIID("Label");
        button.getAllStyles().setAlignment(1);
        button.getAllStyles().setFgColor(0);
        button.setIcon(image);
    }

    public Container getButtonstyleCornerOrangeMultiButton(Button button, SpanLabel spanLabel, SpanLabel spanLabel2) {
        Container container = new Container(new BoxLayout(2));
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setPadding(0.25f, 0.25f, 1.0f, 1.0f);
        container.getAllStyles().setMargin(1.0f, 1.0f, 0.5f, 0.5f);
        button.setUIID("Container");
        button.getAllStyles().setAlignment(1);
        button.getAllStyles().setFgColor(colorGris);
        container.getAllStyles().setBorder(Border.createRoundBorder(2, 1, colorGris));
        container.add(button).add(spanLabel).add(spanLabel2);
        container.setLeadComponent(button);
        return container;
    }

    public static Container setTextFieldStyle(TextField textField, Image image) {
        Container container = new Container(new BorderLayout());
        if (image != null) {
            Label label = new Label(image);
            label.getAllStyles().setPaddingUnit(1);
            label.getAllStyles().setPadding(0.5f, 0.5f, 2.5f, 0.5f);
            container.add("West", label);
        }
        textField.getAllStyles().setAlignment(4);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setFillRows(true);
        Container container2 = new Container(flowLayout);
        SepContainer sepContainer = new SepContainer();
        sepContainer.getAllStyles().setMarginUnit(1);
        sepContainer.getAllStyles().setMarginTop(1);
        container2.add(sepContainer);
        textField.getAllStyles().setPaddingUnit(1);
        textField.getAllStyles().setPadding(0.25f, 0.25f, 2.0f, 2.0f);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2, 1, 0, 0);
        container.add("Center", textField);
        container.add("South", container2);
        container.setLeadComponent(textField);
        return container;
    }

    public static class SepContainer extends Container {
        int sepColor = 13421772;

        public SepContainer() {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setFillRows(true);
            setLayout(flowLayout);
            getAllStyles().setPaddingUnit(1);
            getAllStyles().setPadding(0.15f, 0.15f, 6.0f, 6.0f);
            getAllStyles().setBgColor(this.sepColor);
            getAllStyles().setBgTransparency(255);
        }

        public void setColor(int i) {
            getAllStyles().setBgColor(this.sepColor);
            revalidate();
        }
    }

    public static Font getFont(Float f) {
        return Font.createSystemFont(0, 0, 8).derive(Display.getInstance().convertToPixels(f.floatValue()), 0);
    }

    public static Container hBorder(int i) {
        Container container = new Container();
        Style allStyles = container.getAllStyles();
        allStyles.setBgColor(i);
        allStyles.setBgTransparency(255);
        allStyles.setPaddingUnit(1);
        allStyles.setPadding(0.25f, 0.0f, 0.0f, 0.0f);
        allStyles.setMarginUnit(1);
        allStyles.setMargin(0.0f, 0.5f, 0.0f, 0.0f);
        return container;
    }

    public static Container createDottedList(String[] strArr, Integer num, Font font) {
        Container container = new Container(BoxLayout.y());
        for (String str : strArr) {
            Container container2 = new Container(new BoxLayout(1));
            Label label = new Label("•");
            label.getAllStyles().setAlignment(0);
            SpanLabel spanLabel = new SpanLabel(str);
            label.getAllStyles().setFgColor(0);
            label.getAllStyles().setFont(Font.createTrueTypeFont("native:MainThin", "native:MainThin").derive(Display.getInstance().convertToPixels(3, true), 1));
            spanLabel.getAllStyles().setMarginLeft(2);
            if (font != null) {
                spanLabel.getTextAllStyles().setFont(font);
            } else {
                spanLabel.getTextAllStyles().setFont(getFont(Float.valueOf(2.0f), 1));
            }
            if (num != null) {
                spanLabel.getTextAllStyles().setFgColor(num.intValue());
            }
            container2.add(label);
            container2.add(spanLabel);
            container.add(container2);
        }
        return container;
    }

    public static Font getFont(Float f, Integer num) {
        return Font.createSystemFont(0, num == null ? 0 : num.intValue(), 8).derive(Display.getInstance().convertToPixels(f.floatValue()), num != null ? num.intValue() : 0);
    }

    public static Container setButtonNewFeature(Button button, Image image) {
        Container container = new Container(new GridLayout(1, 1));
        container.setUIID("Container");
        button.setUIID("Container");
        container.getAllStyles().setMarginUnit(b);
        container.getAllStyles().setPaddingUnit(b);
        container.getAllStyles().setMargin(1.0f, 0.5f, 0.0f, 0.0f);
        container.getAllStyles().setPadding(1.0f, 1.0f, 0.0f, 0.0f);
        Style allStyles = button.getAllStyles();
        allStyles.setFont(create_font("exo20medium", "native:MainBold", Float.valueOf(2.5f), 1));
        allStyles.setPaddingUnit(b);
        allStyles.setPadding(0.5f, 0.5f, 1.0f, 1.0f);
        container.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(1.2f).stroke(new Stroke(2.5f, 2, 0, 4.0f)).strokeColor(colorOrange).strokeOpacity(255));
        allStyles.setAlignment(4);
        allStyles.setBgColor(16777215);
        allStyles.setFgColor(colorOrange);
        allStyles.setBgTransparency(0);
        Label label = new Label();
        if (image != null) {
            label.setIcon(image);
            label.getAllStyles().setMarginUnit(b);
            label.getAllStyles().setPaddingUnit(b);
            label.getAllStyles().setMargin(0.0f, 0.0f, 0.0f, 2.0f);
            label.getAllStyles().setPadding(0.5f, 0.0f, 0.0f, 0.0f);
        }
        container.add(BoxLayout.encloseYCenter(FlowLayout.encloseCenterMiddle(label, button)));
        container.setLeadComponent(button);
        return container;
    }

    public static Container setOutlineddStyle(Container container, Integer num, Float f) {
        container.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(f.floatValue()).stroke(new Stroke(4.0f, 2, 0, 4.0f)).strokeColor(num.intValue()).strokeOpacity(255));
        return container;
    }

    public static Container setRoundedFilledButtonStyle(Button button, Image image, int i) {
        Container container = new Container(new GridLayout(1, 1));
        button.setUIID("Container");
        Style allStyles = button.getAllStyles();
        allStyles.setPaddingUnit(b);
        allStyles.setPadding(2.0f, 2.0f, 1.0f, 1.0f);
        allStyles.setFont(create_font("Poppins-Regular", "native:MainBold", Float.valueOf(3.0f), 0));
        allStyles.setBorder(RoundRectBorder.create().cornerRadius(1.2f));
        allStyles.setAlignment(4);
        allStyles.setBgColor(i);
        allStyles.setFgColor(16777215);
        allStyles.setBgTransparency(255);
        if (image != null) {
            button.setIcon(image);
            button.getAllStyles().setMarginUnit(b);
            button.getAllStyles().setMargin(0.0f, 0.0f, 1.0f, 0.0f);
        }
        container.add(button);
        container.setLeadComponent(button);
        return container;
    }

    public static Font create_font(String str, String str2, Float f, Integer num) {
        int convertToPixels = Display.getInstance().convertToPixels(f.floatValue());
        if (Display.getInstance().getPlatformName().equals("ios")) {
            if (Font.isNativeFontSchemeSupported()) {
                return Font.createTrueTypeFont(str2, str2).derive(convertToPixels, num.intValue());
            }
            return getFont(f, num);
        }
        if (Font.isTrueTypeFileSupported()) {
            return Font.createTrueTypeFont(str, str + ".ttf").derive(convertToPixels, num.intValue());
        }
        if (Font.isNativeFontSchemeSupported()) {
            return Font.createTrueTypeFont(str2, str2).derive(convertToPixels, num.intValue());
        }
        return getFont(f, num);
    }
}
