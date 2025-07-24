package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.b3g.tools.DataTools;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.FocusListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.util.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3GSlider extends Container {
    public final Integer Decimal;
    public String Label;
    public Integer MaxValue;
    public Integer MinValue;
    private String Unit;
    public Integer Value;
    public Slider ValueSlider;
    private boolean changed;
    ArrayList listeners;
    public DataChangedListener sliderMoved;
    public final TextField valueTxt;

    static /* synthetic */ boolean access$000(B3GSlider b3GSlider) {
        return b3GSlider.changed;
    }

    static /* synthetic */ boolean access$002(B3GSlider b3GSlider, boolean z) {
        b3GSlider.changed = z;
        return z;
    }

    static /* synthetic */ String access$100(B3GSlider b3GSlider) {
        return b3GSlider.Unit;
    }

    public void addThrowListener(ThrowListener throwListener) {
        this.listeners.add(throwListener);
    }

    public void Throw() {
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((ThrowListener) it.next()).Catch();
        }
    }

    public B3GSlider(int i, int i2, int i3, int i4, String str, String str2) {
        super(new BoxLayout(2));
        this.listeners = new ArrayList();
        this.MinValue = Integer.valueOf(i);
        this.MaxValue = Integer.valueOf(i2);
        this.Value = Integer.valueOf(i3);
        this.Label = str;
        Integer valueOf = Integer.valueOf(i4);
        this.Decimal = valueOf;
        Slider slider = new Slider();
        this.ValueSlider = slider;
        this.changed = false;
        this.Unit = str2;
        slider.setMaxValue((this.MaxValue.intValue() - this.MinValue.intValue()) * valueOf.intValue());
        this.ValueSlider.setMinValue(valueOf.intValue() * 0);
        this.ValueSlider.setEditable(true);
        this.ValueSlider.setRenderValueOnTop(false);
        this.ValueSlider.setThumbImage(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("btn_slide_110.PNG"));
        if (Display.getInstance().getPlatformName().equals("win")) {
            this.valueTxt = new TextField(DataTools.FormatCurrency(this.MinValue.toString()) + " " + this.Unit, 8);
        } else {
            this.valueTxt = new TextField(DataTools.FormatCurrencyOld(this.MinValue.toString()) + " " + this.Unit, 8);
        }
        if (valueOf.intValue() > 1) {
            this.valueTxt.setConstraint(5);
        } else {
            this.valueTxt.setConstraint(2);
        }
        this.valueTxt.addActionListener(new 1());
        this.ValueSlider.addDataChangedListener(new 2());
        DrawSlider();
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            B3GSlider.access$002(B3GSlider.this, true);
            if (B3GSlider.this.Decimal.intValue() == 1) {
                B3GSlider.this.ValueSlider.setProgress(Integer.valueOf(Integer.parseInt(StringUtil.replaceAll(StringUtil.replaceAll(B3GSlider.this.valueTxt.getText(), " ", ""), B3GSlider.access$100(B3GSlider.this), "")) - B3GSlider.this.MinValue.intValue()).intValue());
                boolean z = CihMBanking.RTL;
            } else {
                B3GSlider.this.ValueSlider.setProgress(Double.valueOf((Double.parseDouble(StringUtil.replaceAll(StringUtil.replaceAll(B3GSlider.this.valueTxt.getText(), " ", ""), B3GSlider.access$100(B3GSlider.this), "")) - B3GSlider.this.MinValue.intValue()) * B3GSlider.this.Decimal.intValue()).intValue());
                boolean z2 = CihMBanking.RTL;
            }
        }
    }

    class 2 implements DataChangedListener {
        2() {
        }

        public void dataChanged(int i, int i2) {
            if (!B3GSlider.access$000(B3GSlider.this)) {
                if (B3GSlider.this.Decimal.intValue() == 1) {
                    Integer valueOf = Integer.valueOf(B3GSlider.this.ValueSlider.getProgress() + B3GSlider.this.MinValue.intValue());
                    if (Display.getInstance().getPlatformName().equals("win")) {
                        B3GSlider.this.valueTxt.setText(DataTools.FormatCurrency(valueOf.toString()) + " " + B3GSlider.access$100(B3GSlider.this));
                        B3GSlider.this.valueTxt.setAlignment(1);
                    } else {
                        B3GSlider.this.valueTxt.setText(DataTools.FormatCurrencyOld(valueOf.toString()) + " " + B3GSlider.access$100(B3GSlider.this));
                    }
                } else {
                    B3GSlider.this.valueTxt.setText(DataTools.FormatCurrencyOld(Double.valueOf(Integer.valueOf(B3GSlider.this.ValueSlider.getProgress() + (B3GSlider.this.MinValue.intValue() * B3GSlider.this.Decimal.intValue())).intValue() / B3GSlider.this.Decimal.intValue()).toString()) + " " + B3GSlider.access$100(B3GSlider.this));
                }
            }
            B3GSlider.access$002(B3GSlider.this, false);
            B3GSlider.this.Throw();
        }
    }

    private void DrawSlider() {
        Label label;
        Label label2;
        Container container = new Container(new BorderLayout());
        Label label3 = new Label(this.Label);
        label3.setUIID("SimLabel");
        Button button = new Button();
        button.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("Edit_blue.PNG"));
        this.valueTxt.addFocusListener(new 3(button));
        if (CihMBanking.RTL) {
            this.valueTxt.setUIID("SimTextFieldRTL");
        } else {
            this.valueTxt.setUIID("SimTextField");
        }
        this.valueTxt.setVerticalAlignment(4);
        this.valueTxt.setColumns(10);
        this.valueTxt.setMaxSize(12);
        label3.setVerticalAlignment(4);
        if (label3.getText().isEmpty()) {
            container.addComponent("West", this.valueTxt);
        } else {
            container.addComponent("East", this.valueTxt);
            container.addComponent("West", label3);
        }
        addComponent(container);
        this.ValueSlider.setPreferredH(container.getPreferredH() / 3);
        this.ValueSlider.getAllStyles().setMarginUnit(1);
        this.ValueSlider.getAllStyles().setMargin(2, 2, 0, 0);
        TableLayout tableLayout = new TableLayout(1, 1);
        Container container2 = new Container();
        container2.setLayout(tableLayout);
        container2.setTensileDragEnabled(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint(0, 0);
        createConstraint.setWidthPercentage(100);
        container2.addComponent(createConstraint, this.ValueSlider);
        addComponent(container2);
        Container container3 = new Container(new BorderLayout());
        if (Display.getInstance().getPlatformName().equals("win")) {
            label = new Label(DataTools.FormatCurrency(this.MaxValue.toString()) + " " + this.Unit);
        } else {
            label = new Label(DataTools.FormatCurrencyOld(this.MaxValue.toString()) + " " + this.Unit);
        }
        label.setUIID("SimLabelOrange");
        label.setVerticalAlignment(0);
        if (Display.getInstance().getPlatformName().equals("win")) {
            label2 = new Label(DataTools.FormatCurrency(this.MinValue.toString()) + " " + this.Unit);
        } else {
            label2 = new Label(DataTools.FormatCurrencyOld(this.MinValue.toString()) + " " + this.Unit);
        }
        label2.setRTL(false);
        label2.setUIID("SimLabelOrange");
        label2.setVerticalAlignment(0);
        if (CihMBanking.RTL) {
            container3.addComponent("East", label2);
            container3.addComponent("West", label);
        } else {
            container3.addComponent("West", label2);
            container3.addComponent("East", label);
        }
        container3.setUIID("EmptyContainerZero");
        container3.getStyle().setMargin(0, 10, 0, 0);
        addComponent(container3);
    }

    class 3 implements FocusListener {
        final /* synthetic */ Button val$edit;

        3(Button button) {
            this.val$edit = button;
        }

        public void focusGained(Component component) {
            this.val$edit.setIcon(null);
            B3GSlider.this.valueTxt.setText(StringUtil.replaceAll(StringUtil.replaceAll(B3GSlider.this.valueTxt.getText(), " ", ""), B3GSlider.access$100(B3GSlider.this), ""));
        }

        public void focusLost(Component component) {
            this.val$edit.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("Edit_blue.PNG"));
            B3GSlider.this.valueTxt.setText(StringUtil.replaceAll(StringUtil.replaceAll(B3GSlider.this.valueTxt.getText(), " ", ""), B3GSlider.access$100(B3GSlider.this), "") + " " + B3GSlider.access$100(B3GSlider.this));
        }
    }

    public String getValue() {
        return StringUtil.replaceAll(StringUtil.replaceAll(this.valueTxt.getText(), " ", ""), this.Unit, "");
    }
}
