package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.b3g.tools.DataTools;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.VirtualInputDevice;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.FocusListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.util.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SliderPlafond extends Container {
    public final Integer Decimal;
    public String Label;
    public Integer MaxPlf;
    public Integer MaxValue;
    public Integer MinPlfd;
    public Integer MinValue;
    private String Unit;
    public Integer Value;
    public Slider ValueSlider;
    private boolean changed;
    public Container ctnNext;
    public ArrayList listSlider;
    ArrayList listeners;
    public String monthNumber;
    public String slider;
    public DataChangedListener sliderMoved;
    public Label sliderTitle;
    public String txtSlider;
    public TextField valueTxt;
    public String varGlob;
    public VirtualInputDevice virtualInputDevice;

    static /* synthetic */ boolean access$000(SliderPlafond sliderPlafond) {
        return sliderPlafond.changed;
    }

    static /* synthetic */ boolean access$002(SliderPlafond sliderPlafond, boolean z) {
        sliderPlafond.changed = z;
        return z;
    }

    static /* synthetic */ String access$100(SliderPlafond sliderPlafond) {
        return sliderPlafond.Unit;
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

    public SliderPlafond(int i, int i2, int i3, int i4, String str, String str2, int i5, int i6, String str3, Container container) {
        String jour1;
        super(new BoxLayout(2));
        this.txtSlider = null;
        this.sliderTitle = new Label();
        this.listSlider = new ArrayList();
        this.listeners = new ArrayList();
        this.Value = Integer.valueOf(i3);
        this.Label = str;
        Integer valueOf = Integer.valueOf(i4);
        this.Decimal = valueOf;
        this.MinValue = Integer.valueOf(i);
        this.MaxValue = Integer.valueOf(i2);
        this.ValueSlider = new Slider();
        this.changed = false;
        this.Unit = str2;
        this.slider = str3;
        this.ctnNext = container;
        this.MinPlfd = Integer.valueOf(i6);
        this.MaxPlf = Integer.valueOf(i5);
        this.listSlider.add(0, jour1());
        this.listSlider.add(1, jour7());
        this.listSlider.add(2, jour15());
        this.listSlider.add(3, mois1());
        this.listSlider.add(4, mois3());
        this.listSlider.add(5, mois6());
        this.listSlider.add(6, mois12());
        this.ValueSlider.setMaxValue(this.MaxValue.intValue());
        this.ValueSlider.setMinValue(this.MinValue.intValue());
        this.ValueSlider.setEditable(true);
        this.ValueSlider.setRenderValueOnTop(false);
        this.ValueSlider.setThumbImage(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("slider.png"));
        System.err.print(this.ValueSlider.getProgress());
        if (this.ValueSlider.getProgress() >= 0 && this.ValueSlider.getProgress() <= this.ValueSlider.getMaxValue()) {
            this.sliderTitle = new Label();
            if (this.slider.equals("sliderDuree")) {
                int intValue = this.Value.intValue();
                if (intValue == 1) {
                    jour1 = jour1();
                } else if (intValue == 7) {
                    jour1 = jour7();
                } else if (intValue == 15) {
                    jour1 = jour15();
                } else if (intValue == 30) {
                    jour1 = mois1();
                } else if (intValue == 90) {
                    jour1 = mois3();
                } else if (intValue == 180) {
                    jour1 = mois6();
                } else {
                    jour1 = intValue != 365 ? null : mois12();
                }
                if (this.Value.intValue() < 7 && this.Value.intValue() > 1) {
                    this.ValueSlider.setProgress(1 - (7 / this.Value.intValue()));
                    this.sliderTitle.setText(Integer.toString(this.Value.intValue()) + " Jours");
                    this.monthNumber = Integer.toString(this.Value.intValue());
                    jour1 = this.sliderTitle.getText();
                } else if (this.Value.intValue() < 15 && this.Value.intValue() > 7) {
                    this.ValueSlider.setProgress(2 - (15 / this.Value.intValue()));
                    this.sliderTitle.setText(Integer.toString(this.Value.intValue()) + " Jours");
                    this.monthNumber = Integer.toString(this.Value.intValue());
                    jour1 = this.sliderTitle.getText();
                } else if (this.Value.intValue() < 30 && this.Value.intValue() > 15) {
                    this.ValueSlider.setProgress(3 - (30 / this.Value.intValue()));
                    this.sliderTitle.setText(Integer.toString(this.Value.intValue()) + " Jours");
                    this.monthNumber = Integer.toString(this.Value.intValue());
                    jour1 = this.sliderTitle.getText();
                } else if (this.Value.intValue() < 90 && this.Value.intValue() > 30) {
                    this.ValueSlider.setProgress(4 - (90 / this.Value.intValue()));
                    this.sliderTitle.setText(Integer.toString(this.Value.intValue()) + " Jours");
                    this.monthNumber = Integer.toString(this.Value.intValue());
                    jour1 = this.sliderTitle.getText();
                } else if (this.Value.intValue() < 180 && this.Value.intValue() > 90) {
                    this.ValueSlider.setProgress(5 - (180 / this.Value.intValue()));
                    this.sliderTitle.setText(Integer.toString(this.Value.intValue()) + " Jours");
                    this.monthNumber = Integer.toString(this.Value.intValue());
                    jour1 = this.sliderTitle.getText();
                } else if (this.Value.intValue() < 365 && this.Value.intValue() > 180) {
                    this.ValueSlider.setProgress(6 - (365 / this.Value.intValue()));
                    this.sliderTitle.setText(Integer.toString(this.Value.intValue()) + " Jours");
                    this.monthNumber = Integer.toString(this.Value.intValue());
                    jour1 = this.sliderTitle.getText();
                } else if (this.Value.intValue() <= 0) {
                    this.ValueSlider.setProgress(6);
                    this.sliderTitle.setText(" Illimitée ");
                    jour1 = this.sliderTitle.getText();
                }
                int i7 = 0;
                while (true) {
                    if (i7 >= this.listSlider.size()) {
                        break;
                    }
                    if (jour1.equals(this.listSlider.get(i7))) {
                        Slider slider = this.ValueSlider;
                        ArrayList arrayList = this.listSlider;
                        slider.setProgress(arrayList.indexOf(arrayList.get(i7)));
                        this.sliderTitle.setText(jour1);
                        break;
                    }
                    i7++;
                }
            } else {
                String str4 = String.valueOf((this.ValueSlider.getProgress() * valueOf.intValue()) + this.Value.intValue()) + " " + this.Unit;
                this.sliderTitle.setText(formatNumber((this.ValueSlider.getProgress() * valueOf.intValue()) + this.Value.intValue(), " ") + " Dhs");
                this.ValueSlider.setProgress(this.Value.intValue() / valueOf.intValue());
                this.txtSlider = this.sliderTitle.getText();
            }
        }
        if (Display.getInstance().getPlatformName().equals("win")) {
            this.valueTxt = new TextField(DataTools.FormatCurrency(this.Value.toString()) + " " + this.Unit, 8);
        } else {
            this.valueTxt = new TextField(DataTools.FormatCurrencyOld(this.Value.toString()) + " " + this.Unit, 8);
        }
        if (this.Decimal.intValue() > 1) {
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

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:6:0x027c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void actionPerformed(com.codename1.ui.events.ActionEvent r9) {
            /*
                Method dump skipped, instructions count: 738
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.SliderPlafond.1.actionPerformed(com.codename1.ui.events.ActionEvent):void");
        }
    }

    class 2 implements DataChangedListener {
        2() {
        }

        public void dataChanged(int i, int i2) {
            if (SliderPlafond.this.ValueSlider.getProgress() < 0 || SliderPlafond.this.ValueSlider.getProgress() > SliderPlafond.this.ValueSlider.getMaxValue()) {
                return;
            }
            if (SliderPlafond.this.slider.equals("sliderDuree")) {
                for (int i3 = 0; i3 < SliderPlafond.this.listSlider.size(); i3++) {
                    if (SliderPlafond.this.ValueSlider.getProgress() == SliderPlafond.this.listSlider.indexOf(SliderPlafond.this.listSlider.get(i3))) {
                        SliderPlafond.this.sliderTitle.setText((String) SliderPlafond.this.listSlider.get(i3));
                        SliderPlafond sliderPlafond = SliderPlafond.this;
                        sliderPlafond.getMonthNumber(sliderPlafond.sliderTitle.getText());
                    }
                }
            } else {
                String str = String.valueOf(SliderPlafond.this.ValueSlider.getProgress() * SliderPlafond.this.Decimal.intValue()) + " " + SliderPlafond.access$100(SliderPlafond.this);
                Label label = SliderPlafond.this.sliderTitle;
                StringBuilder sb = new StringBuilder();
                SliderPlafond sliderPlafond2 = SliderPlafond.this;
                label.setText(sb.append(sliderPlafond2.formatNumber(sliderPlafond2.ValueSlider.getProgress() * SliderPlafond.this.Decimal.intValue(), " ")).append(" Dhs").toString());
                SliderPlafond sliderPlafond3 = SliderPlafond.this;
                sliderPlafond3.txtSlider = sliderPlafond3.sliderTitle.getText();
            }
            if (!SliderPlafond.access$000(SliderPlafond.this)) {
                if (SliderPlafond.this.Decimal.intValue() == 1) {
                    Integer valueOf = Integer.valueOf(SliderPlafond.this.ValueSlider.getProgress() + SliderPlafond.this.MinValue.intValue());
                    if (Display.getInstance().getPlatformName().equals("win")) {
                        SliderPlafond.this.valueTxt.setText(DataTools.FormatCurrency(valueOf.toString()) + " " + SliderPlafond.access$100(SliderPlafond.this));
                        SliderPlafond.this.valueTxt.setAlignment(1);
                    } else {
                        SliderPlafond.this.valueTxt.setText(DataTools.FormatCurrencyOld(valueOf.toString()) + " " + SliderPlafond.access$100(SliderPlafond.this));
                    }
                } else {
                    SliderPlafond.this.valueTxt.setText(DataTools.FormatCurrencyOld(Integer.valueOf(Double.valueOf(Integer.valueOf(SliderPlafond.this.ValueSlider.getProgress() + (SliderPlafond.this.MinValue.intValue() * SliderPlafond.this.Decimal.intValue())).intValue() * SliderPlafond.this.Decimal.intValue()).intValue()).toString()) + " " + SliderPlafond.access$100(SliderPlafond.this));
                }
            }
            String str2 = SliderPlafond.this.slider;
            str2.hashCode();
            switch (str2) {
                case "sliderCm":
                    if (SliderPlafond.this.Value.equals(Integer.valueOf(SliderPlafond.this.ValueSlider.getProgress() * SliderPlafond.this.Decimal.intValue()))) {
                        CihMBanking.sesPMTR.setComIsSame(true);
                        break;
                    } else {
                        CihMBanking.sesPMTR.setComIsSame(false);
                        break;
                    }
                case "sliderPm":
                    if (SliderPlafond.this.Value.equals(Integer.valueOf(SliderPlafond.this.ValueSlider.getProgress() * SliderPlafond.this.Decimal.intValue()))) {
                        CihMBanking.sesPMTR.setPaimIsSame(true);
                        break;
                    } else {
                        CihMBanking.sesPMTR.setPaimIsSame(false);
                        break;
                    }
                case "sliderRtr":
                    if (SliderPlafond.this.Value.equals(Integer.valueOf(SliderPlafond.this.ValueSlider.getProgress() * SliderPlafond.this.Decimal.intValue()))) {
                        CihMBanking.sesPMTR.setRtrIsSame(true);
                        break;
                    } else {
                        CihMBanking.sesPMTR.setRtrIsSame(false);
                        break;
                    }
                case "sliderDuree":
                    if (SliderPlafond.this.ValueSlider.getProgress() == SliderPlafond.this.listSlider.indexOf(SliderPlafond.this.getDurInit(Double.valueOf(r3.Value.intValue())))) {
                        CihMBanking.sesPMTR.setDureeIsSame(true);
                        break;
                    } else {
                        CihMBanking.sesPMTR.setDureeIsSame(false);
                        break;
                    }
            }
            if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                CihMBanking.sesPMTR.PlafondNext = false;
                SliderPlafond.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                SliderPlafond.this.ctnNext.revalidate();
            } else {
                CihMBanking.sesPMTR.PlafondNext = true;
                SliderPlafond.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                SliderPlafond.this.ctnNext.revalidate();
            }
            if (SliderPlafond.this.ValueSlider.getProgress() == 0 && !SliderPlafond.this.slider.equals("sliderDuree")) {
                CihMBanking.sesPMTR.PlafondNext = false;
                SliderPlafond.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                SliderPlafond.this.ctnNext.revalidate();
            }
            SliderPlafond.access$002(SliderPlafond.this, false);
            SliderPlafond.this.Throw();
        }
    }

    private void DrawSlider() {
        Label label;
        Label label2;
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(new BorderLayout());
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(1, 1, 2, 2);
        new Button();
        TableLayout tableLayout = new TableLayout(1, 1);
        Container container3 = new Container();
        container3.setLayout(tableLayout);
        container3.setTensileDragEnabled(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint(0, 0);
        createConstraint.setWidthPercentage(100);
        Container container4 = new Container(new BoxLayout(2));
        container4.setUIID("EmptyContainerZero");
        Container container5 = new Container(new FlowLayout(4, 4));
        container5.addComponent(this.sliderTitle);
        this.ValueSlider.setPreferredH(container5.getPreferredH() / 2);
        container3.addComponent(createConstraint, this.ValueSlider);
        container3.getStyle().setPadding(0, 0, 3, 3);
        container3.getStyle().setMargin(0, 0, 0, 1);
        container4.addComponent(container3);
        addComponent(container2);
        addComponent(container4);
        this.valueTxt.setUIID("SimTextField");
        this.valueTxt.getAllStyles().setPaddingUnit(1);
        this.valueTxt.getAllStyles().setPaddingLeft(6);
        TextField textField = this.valueTxt;
        textField.setSelectedStyle(textField.getUnselectedStyle());
        this.valueTxt.setVerticalAlignment(4);
        this.valueTxt.setColumns(10);
        this.valueTxt.setMaxSize(12);
        this.valueTxt.setCursorBlinkTimeOff(999999);
        this.valueTxt.setTraversable(false);
        this.valueTxt.addFocusListener(new 3());
        if (this.slider.equals("sliderDuree")) {
            if (Display.getInstance().getPlatformName().equals("win")) {
                label = new Label(mois12());
            } else {
                label = new Label(mois12());
            }
            if (Display.getInstance().getPlatformName().equals("win")) {
                label2 = new Label(jour1());
            } else {
                label2 = new Label(jour1());
            }
        } else {
            if (Display.getInstance().getPlatformName().equals("win")) {
                label = new Label(formatNumber(this.MaxPlf.intValue(), " ") + " " + this.Unit);
            } else {
                label = new Label(formatNumber(this.MaxPlf.intValue(), " ") + " " + this.Unit);
            }
            if (Display.getInstance().getPlatformName().equals("win")) {
                label2 = new Label(formatNumber(this.MinPlfd.intValue(), " ") + " " + this.Unit);
            } else {
                label2 = new Label(formatNumber(this.MinPlfd.intValue(), " ") + " " + this.Unit);
            }
        }
        container2.addComponent("West", this.valueTxt);
        label.setUIID("g_lblDashBoardTitleGREY");
        label.getStyle().setPaddingTop(0);
        label.setVerticalAlignment(0);
        label2.setUIID("g_lblDashBoardTitleGREY");
        label2.getStyle().setPaddingTop(0);
        label2.setVerticalAlignment(0);
        this.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
        if (CihMBanking.RTL) {
            container.addComponent("East", label2);
            container.addComponent("West", label);
        } else {
            container.addComponent("West", label2);
            container.addComponent("East", label);
        }
        container.setUIID("EmptyContainerZero");
        container.getStyle().setPadding(0, 10, 5, 5);
        addComponent(container);
    }

    class 3 implements FocusListener {
        public void focusLost(Component component) {
        }

        3() {
        }

        public void focusGained(Component component) {
            SliderPlafond.this.valueTxt.setTraversable(false);
            SliderPlafond.this.valueTxt.setText(StringUtil.replaceAll(StringUtil.replaceAll(SliderPlafond.this.valueTxt.getText(), SliderPlafond.access$100(SliderPlafond.this), ""), " ", ""));
        }
    }

    public String getValue() {
        return StringUtil.replaceAll(StringUtil.replaceAll(this.ValueSlider.getText(), " ", ""), this.Unit, "");
    }

    public String getMonthNumber(String str) {
        str.hashCode();
        switch (str) {
            case "7 Jours":
            case "7 days":
            case "7 dias":
            case "7 أيام":
                this.monthNumber = "7";
                break;
            case "3 meses":
            case "3 months":
            case "3 Mois":
            case "3 أشهر":
                this.monthNumber = "90";
                break;
            case "1 day":
            case "1 día":
            case "يوم واحد":
            case "1 Jour":
                this.monthNumber = "1";
                break;
            case "1 mes":
            case "شهر واحد":
            case "1 Mois":
            case "1 month":
                this.monthNumber = "30";
                break;
            case "12 months":
            case "12 meses":
            case "12 Mois":
            case "12 شهرا":
                this.monthNumber = "365";
                break;
            case "15 Jours":
            case "15 يوم":
            case "15 days":
            case "15 días":
                this.monthNumber = "15";
                break;
            case "6 months":
            case "6 Mois":
            case "6 أشهر":
            case "6 meses":
                this.monthNumber = "180";
                break;
        }
        return null;
    }

    public void getValueSlidrDurInit(Double d) {
        String jour1;
        int intValue = d.intValue();
        if (intValue == 1) {
            jour1 = jour1();
        } else if (intValue == 7) {
            jour1 = jour7();
        } else if (intValue == 15) {
            jour1 = jour15();
        } else if (intValue == 30) {
            jour1 = mois1();
        } else if (intValue == 90) {
            jour1 = mois3();
        } else if (intValue == 180) {
            jour1 = mois6();
        } else {
            jour1 = intValue != 365 ? null : mois12();
        }
        if (d.intValue() < 7 && d.intValue() > 1) {
            this.ValueSlider.setProgress(1 - (7 / d.intValue()));
            this.sliderTitle.setText(Integer.toString(d.intValue()) + " Jours");
            jour1 = this.sliderTitle.getText();
        } else if (d.intValue() < 15 && d.intValue() > 7) {
            this.ValueSlider.setProgress(2 - (15 / d.intValue()));
            this.sliderTitle.setText(Integer.toString(d.intValue()) + " Jours");
            jour1 = this.sliderTitle.getText();
        } else if (d.intValue() < 30 && d.intValue() > 15) {
            this.ValueSlider.setProgress(3 - (30 / d.intValue()));
            this.sliderTitle.setText(Integer.toString(d.intValue()) + " Jours");
            jour1 = this.sliderTitle.getText();
        } else if (d.intValue() < 90 && d.intValue() > 30) {
            this.ValueSlider.setProgress(4 - (90 / d.intValue()));
            this.sliderTitle.setText(Integer.toString(d.intValue()) + " Jours");
            jour1 = this.sliderTitle.getText();
        } else if (d.intValue() < 180 && d.intValue() > 90) {
            this.ValueSlider.setProgress(5 - (180 / d.intValue()));
            this.sliderTitle.setText(Integer.toString(d.intValue()) + " Jours");
            jour1 = this.sliderTitle.getText();
        } else if (d.intValue() < 365 && d.intValue() > 180) {
            this.ValueSlider.setProgress(6 - (365 / d.intValue()));
            this.sliderTitle.setText(Integer.toString(d.intValue()) + " Jours");
            jour1 = this.sliderTitle.getText();
        } else if (d.intValue() <= 0) {
            this.ValueSlider.setProgress(6);
            this.sliderTitle.setText(" Illimitée ");
            jour1 = this.sliderTitle.getText();
        }
        for (int i = 0; i < this.listSlider.size(); i++) {
            if (jour1.equals(this.listSlider.get(i))) {
                Slider slider = this.ValueSlider;
                ArrayList arrayList = this.listSlider;
                slider.setProgress(arrayList.indexOf(arrayList.get(i)));
                this.sliderTitle.setText(jour1);
                return;
            }
        }
    }

    public String getDurInit(Double d) {
        String jour1;
        int intValue = d.intValue();
        if (intValue == 1) {
            jour1 = jour1();
        } else if (intValue == 7) {
            jour1 = jour7();
        } else if (intValue == 15) {
            jour1 = jour15();
        } else if (intValue == 30) {
            jour1 = mois1();
        } else if (intValue == 90) {
            jour1 = mois3();
        } else if (intValue == 180) {
            jour1 = mois6();
        } else {
            jour1 = intValue != 365 ? null : mois12();
        }
        if (d.intValue() < 7 && d.intValue() > 1) {
            return Integer.toString(d.intValue()) + " Jours";
        }
        if (d.intValue() < 15 && d.intValue() > 7) {
            return Integer.toString(d.intValue()) + " Jours";
        }
        if (d.intValue() < 30 && d.intValue() > 15) {
            return Integer.toString(d.intValue()) + " Jours";
        }
        if (d.intValue() < 90 && d.intValue() > 30) {
            return Integer.toString(d.intValue()) + " Jours";
        }
        if (d.intValue() < 180 && d.intValue() > 90) {
            return Integer.toString(d.intValue()) + " Jours";
        }
        if (d.intValue() >= 365 || d.intValue() <= 180) {
            return d.intValue() <= 0 ? " Illimitée " : jour1;
        }
        return Integer.toString(d.intValue()) + " Jours";
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

    public int formatNumber(String str, String str2) {
        try {
            Integer.parseInt(replace(str, str2, ""));
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return Integer.parseInt(StringUtil.replaceAll(str, str2, ""));
    }

    public String replace(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return str;
        }
        while (indexOf != -1) {
            sb.append(str.substring(0, indexOf));
            sb.append(str3);
            str = str.substring(indexOf + str2.length());
            indexOf = str.indexOf(str2);
        }
        sb.append(str);
        return sb.toString();
    }

    public String jour1() {
        String str = Preferences.get("CihMobileLang", "fr");
        str.hashCode();
        switch (str) {
            case "العربية":
            case "ar":
            case "Arabe":
                return "يوم واحد";
            case "english":
            case "en":
            case "English":
            case "Anglais":
                return "1 day";
            case "español":
            case "es":
            case "Español":
                return "1 día";
            default:
                return "1 Jour";
        }
    }

    public String jour7() {
        String str = Preferences.get("CihMobileLang", "fr");
        str.hashCode();
        switch (str) {
            case "العربية":
            case "ar":
            case "Arabe":
                return "7 أيامد";
            case "english":
            case "en":
            case "English":
            case "Anglais":
                return "7 days";
            case "español":
            case "es":
            case "Español":
                return "7 días";
            default:
                return "7 Jours";
        }
    }

    public String jour15() {
        String str = Preferences.get("CihMobileLang", "fr");
        str.hashCode();
        switch (str) {
            case "العربية":
            case "ar":
            case "Arabe":
                return "15 يوم";
            case "english":
            case "en":
            case "English":
            case "Anglais":
                return "15 days";
            case "español":
            case "es":
            case "Español":
                return "15 días";
            default:
                return "15 Jours";
        }
    }

    public String mois1() {
        String str = Preferences.get("CihMobileLang", "fr");
        str.hashCode();
        switch (str) {
            case "العربية":
            case "ar":
            case "Arabe":
                return "شهر واحد";
            case "english":
            case "en":
            case "English":
            case "Anglais":
                return "1 month";
            case "español":
            case "es":
            case "Español":
                return "1 mes";
            default:
                return "1 Mois";
        }
    }

    public String mois3() {
        String str = Preferences.get("CihMobileLang", "fr");
        str.hashCode();
        switch (str) {
            case "العربية":
            case "ar":
            case "Arabe":
                return "3 أشهر";
            case "english":
            case "en":
            case "English":
            case "Anglais":
                return "3 months";
            case "español":
            case "es":
            case "Español":
                return "3 meses";
            default:
                return "3 Mois";
        }
    }

    public String mois6() {
        String str = Preferences.get("CihMobileLang", "fr");
        str.hashCode();
        switch (str) {
            case "العربية":
            case "ar":
            case "Arabe":
                return "6 أشهر";
            case "english":
            case "en":
            case "English":
            case "Anglais":
                return "6 months";
            case "español":
            case "es":
            case "Español":
                return "6 meses";
            default:
                return "6 Mois";
        }
    }

    public String mois12() {
        String str = Preferences.get("CihMobileLang", "fr");
        str.hashCode();
        switch (str) {
            case "العربية":
            case "ar":
            case "Arabe":
                return "12 شهرا";
            case "english":
            case "en":
            case "English":
            case "Anglais":
                return "12 months";
            case "español":
            case "es":
            case "Español":
                return "12 meses";
            default:
                return "12 Mois";
        }
    }
}
