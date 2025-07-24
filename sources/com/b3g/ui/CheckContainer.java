package com.b3g.ui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.DefaultLookAndFeel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CheckContainer {
    private byte[] b;
    private CheckBox check;
    private Integer color;
    private String content;
    private Container wrapper;

    public CheckContainer() {
        this.b = new byte[]{1, 1, 1, 1};
        this.content = "";
        this.check = new CheckBox();
    }

    public CheckContainer(String str) {
        this.b = new byte[]{1, 1, 1, 1};
        this.content = str;
        this.check = new CheckBox();
    }

    public CheckContainer(int i) {
        this.b = new byte[]{1, 1, 1, 1};
        this.content = "";
        this.color = Integer.valueOf(i);
        this.check = new CheckBox();
    }

    public CheckContainer(String str, int i) {
        this.b = new byte[]{1, 1, 1, 1};
        this.content = str;
        this.color = Integer.valueOf(i);
        this.check = new CheckBox();
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setColor(int i) {
        this.color = Integer.valueOf(i);
    }

    public boolean isSelected() {
        return this.check.isSelected();
    }

    public void addChangeListener(ActionListener actionListener) {
        this.check.addChangeListener(actionListener);
    }

    public Container getContainer() {
        this.wrapper = new Container(BoxLayout.x());
        Display.getInstance().convertToPixels(2.0f);
        Container container = new Container(BoxLayout.y());
        SpanLabel spanLabel = new SpanLabel(this.content);
        if (this.color != null) {
            spanLabel.getTextAllStyles().setFgColor(this.color.intValue());
            this.check.getAllStyles().setFgColor(this.color.intValue());
        }
        spanLabel.getTextAllStyles().setFont(CIHStyles.create_font("exo20medium", "native:MainBold", Float.valueOf(2.2f), 1));
        spanLabel.getAllStyles().setMarginUnit(1);
        spanLabel.getAllStyles().setMarginLeft(1.0f);
        spanLabel.getTextAllStyles().setAlignment(0);
        Style style = new Style();
        style.setFgColor(this.color.intValue());
        style.setBgTransparency(0);
        FontImage createMaterial = FontImage.createMaterial((char) 59445, style);
        FontImage createMaterial2 = FontImage.createMaterial((char) 59444, style);
        ((DefaultLookAndFeel) UIManager.getInstance().getLookAndFeel()).setCheckBoxImages(createMaterial2, createMaterial);
        ((DefaultLookAndFeel) UIManager.getInstance().getLookAndFeel()).setCheckBoxFocusImages(createMaterial2, createMaterial, createMaterial2, createMaterial);
        container.add(this.check);
        this.wrapper.add(container).add(spanLabel);
        this.wrapper.setLeadComponent(this.check);
        this.wrapper.getAllStyles().setMarginUnit(this.b);
        this.wrapper.getAllStyles().setMarginBottom(1.0f);
        return this.wrapper;
    }
}
