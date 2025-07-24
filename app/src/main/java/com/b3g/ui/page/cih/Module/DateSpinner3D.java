package com.b3g.ui.page.cih.Module;

import com.b3g.ui.page.cih.Module.SpinnerNode;
import com.codename1.l10n.L10NManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.CN;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import java.util.Calendar;
import java.util.Date;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
class DateSpinner3D extends Container implements InternalPickerWidget {
    private int currentDay;
    private int currentMonth;
    private int currentYear;
    private Spinner3D day;
    private boolean explicitCurrentYear;
    private boolean explicitEndDay;
    private boolean explicitEndMonth;
    private boolean explicitEndYear;
    private boolean explicitStartDay;
    private boolean explicitStartMonth;
    private boolean explicitStartYear;
    private int hourOfDay;
    private int millisOfDay;
    private int minuteOfDay;
    private Spinner3D month;
    private boolean monthDayYear;
    private int secondsOfDay;
    private Spinner3D year;
    private int startYear = 1970;
    private int endYear = 2100;
    private int startMonth = 1;
    private int endMonth = 13;
    private int startDay = 1;
    private int endDay = 32;
    private boolean numericMonths = false;
    private String monthRenderingPrototype = "WWW";
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
    private Container wrapper = new Container(BoxLayout.x());
    private Calendar tmpCal = Calendar.getInstance();

    static /* synthetic */ SimpleDateFormat access$000(DateSpinner3D dateSpinner3D) {
        return dateSpinner3D.monthFormat;
    }

    public DateSpinner3D() {
        this.monthDayYear = true;
        setLayout(new BorderLayout(2));
        add("Center", this.wrapper);
        Calendar calendar = Calendar.getInstance();
        this.currentDay = calendar.get(5);
        this.currentMonth = calendar.get(2) + 1;
        this.currentYear = calendar.get(1);
        String substring = L10NManager.getInstance().formatDateLongStyle(new Date()).substring(0, 1);
        this.monthDayYear = true ^ substring.toLowerCase().equals(substring.toUpperCase());
        initSpinner();
    }

    protected void initComponent() {
        super.initComponent();
        this.monthFormat.getDateFormatSymbols().setResourceBundle(getUIManager().getResourceBundle());
    }

    void initSpinner() {
        if (this.month == null) {
            Spinner3D create = Spinner3D.create(1, 32, this.currentDay, 1);
            this.day = create;
            create.setRowFormatter(new 1());
            Spinner3D create2 = Spinner3D.create(1, 13, this.currentMonth, 1);
            this.month = create2;
            create2.setRowFormatter(new 2());
            Spinner3D create3 = Spinner3D.create(this.startYear, this.endYear, this.currentYear, 1);
            this.year = create3;
            create3.setRowFormatter(new 3());
            addComponents();
        }
    }

    class 1 implements SpinnerNode.RowFormatter {
        1() {
        }

        public String format(String str) {
            if (str != null) {
                return String.valueOf(new Double(Double.parseDouble(str)).intValue());
            }
            return null;
        }
    }

    class 2 implements SpinnerNode.RowFormatter {
        2() {
        }

        public String format(String str) {
            if (str == null) {
                return null;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(2, new Double(Double.parseDouble(str)).intValue() - 1);
            calendar.set(5, 15);
            return DateSpinner3D.access$000(DateSpinner3D.this).format(calendar.getTime());
        }
    }

    class 3 implements SpinnerNode.RowFormatter {
        3() {
        }

        public String format(String str) {
            if (str != null) {
                return String.valueOf(new Double(Double.parseDouble(str)).intValue());
            }
            return null;
        }
    }

    private void addComponents() {
        if (this.year != null) {
            Label label = new Label("December", "Spinner3DRow");
            if (this.monthDayYear) {
                this.wrapper.addComponent(this.month);
                this.month.setPreferredW((int) (label.getPreferredW() * 1.5f));
                Style createProxyStyle = Style.createProxyStyle(this.month.getRowStyle(), this.month.getSelectedRowStyle());
                createProxyStyle.setAlignment(1);
                createProxyStyle.setPaddingLeft(3.0f);
                label.setText("00");
                this.day.setPreferredW(((int) (label.getPreferredW() * 1.5f)) + CN.convertToPixels(3.0f));
                Style createProxyStyle2 = Style.createProxyStyle(this.day.getRowStyle(), this.day.getSelectedRowStyle());
                createProxyStyle2.setAlignment(3);
                createProxyStyle2.setPaddingRight(3.0f);
                label.setText("0000");
                this.year.setPreferredW(((int) (label.getPreferredW() * 1.5f)) + CN.convertToPixels(3.0f));
                this.wrapper.addComponent(this.year);
                Style createProxyStyle3 = Style.createProxyStyle(this.year.getRowStyle(), this.year.getSelectedRowStyle());
                createProxyStyle3.setAlignment(3);
                createProxyStyle3.setPaddingRight(3.0f);
                return;
            }
            this.month.setPreferredW((int) (label.getPreferredW() * 1.5f));
            label.setText("00");
            this.day.setPreferredW(((int) (label.getPreferredW() * 1.5f)) + CN.convertToPixels(3.0f));
            label.setText("0000");
            this.year.setPreferredW(((int) (label.getPreferredW() * 1.5f)) + CN.convertToPixels(3.0f));
            Style createProxyStyle4 = Style.createProxyStyle(this.day.getRowStyle(), this.day.getSelectedRowStyle());
            createProxyStyle4.setAlignment(3);
            createProxyStyle4.setPaddingRight(3.0f);
            this.wrapper.addComponent(this.month);
            Style createProxyStyle5 = Style.createProxyStyle(this.month.getRowStyle(), this.month.getSelectedRowStyle());
            createProxyStyle5.setAlignment(1);
            createProxyStyle5.setPaddingLeft(3.0f);
            Style createProxyStyle6 = Style.createProxyStyle(this.year.getRowStyle(), this.year.getSelectedRowStyle());
            createProxyStyle6.setAlignment(3);
            createProxyStyle6.setPaddingRight(3.0f);
            this.wrapper.addComponent(this.year);
        }
    }

    private void rebuildMonth() {
        Spinner3D spinner3D = this.month;
        int i = this.startMonth;
        int i2 = this.endMonth;
        spinner3D.setModel(new SpinnerNumberModel(i, i2, Math.max(i, Math.min(i2, this.currentMonth)), 1));
    }

    private void rebuildDay() {
        Spinner3D spinner3D = this.day;
        int i = this.startDay;
        int i2 = this.endDay;
        spinner3D.setModel(new SpinnerNumberModel(i, i2, Math.max(i, Math.min(i2, this.currentDay)), 1));
    }

    public void setDateRange(Date date, Date date2) {
        int i;
        this.explicitStartMonth = true;
        this.explicitEndMonth = true;
        this.explicitStartDay = true;
        this.explicitEndDay = true;
        this.explicitStartYear = true;
        this.explicitEndYear = true;
        int year = date2 == null ? 2100 : getYear(date2) + 1900 + 1;
        if (!this.explicitCurrentYear && this.currentYear > year - 1) {
            this.currentYear = i;
        }
        setEndYear(year);
        int year2 = date == null ? 1970 : getYear(date) + 1900;
        if (!this.explicitCurrentYear && this.currentYear < year2) {
            this.currentYear = year2;
        }
        setStartYear(year2);
        if (date != null && date2 != null && getYear(date) == getYear(date2)) {
            this.startMonth = getMonth(date) + 1;
            this.endMonth = getMonth(date2) + 2;
        } else {
            this.startMonth = 1;
            this.endMonth = 13;
        }
        rebuildMonth();
        if (date != null && date2 != null && getYear(date) == getYear(date2) && getMonth(date) == getMonth(date2)) {
            this.startDay = getDate(date);
            this.endDay = getDate(date2) + 1;
        } else {
            this.startDay = 1;
            this.endDay = 32;
        }
        rebuildDay();
    }

    private int getDate(Date date) {
        this.tmpCal.setTime(date);
        return this.tmpCal.get(5);
    }

    private int getMonth(Date date) {
        this.tmpCal.setTime(date);
        return this.tmpCal.get(2);
    }

    private int getYear(Date date) {
        this.tmpCal.setTime(date);
        return this.tmpCal.get(1) - 1900;
    }

    public int getStartMonth() {
        return this.startMonth;
    }

    public int getEndMonth() {
        return this.endMonth;
    }

    public int getStartDay() {
        return this.startDay;
    }

    public int getEndDay() {
        return this.endDay;
    }

    public int getStartYear() {
        return this.startYear;
    }

    public void setStartYear(int i) {
        this.startYear = i;
        this.explicitStartYear = true;
        Spinner3D spinner3D = this.year;
        if (spinner3D != null) {
            spinner3D.setModel(new SpinnerNumberModel(i, this.endYear, this.currentYear, 1));
        }
    }

    public int getEndYear() {
        return this.endYear;
    }

    public void setEndYear(int i) {
        this.endYear = i;
        this.explicitEndYear = true;
        Spinner3D spinner3D = this.year;
        if (spinner3D != null) {
            spinner3D.setModel(new SpinnerNumberModel(this.startYear, i, this.currentYear, 1));
        }
    }

    public int getCurrentYear() {
        Spinner3D spinner3D = this.year;
        if (spinner3D != null) {
            return ((Integer) spinner3D.getValue()).intValue();
        }
        return this.currentYear;
    }

    public void setCurrentYear(int i) {
        this.currentYear = i;
        this.explicitCurrentYear = true;
        if (!this.explicitStartYear && this.startYear > i) {
            this.startYear = i;
        }
        if (!this.explicitEndYear && this.endYear - 1 < i) {
            this.endYear = i + 1;
        }
        if (i < this.startYear) {
            throw new IllegalArgumentException("Current year " + i + " before start year " + this.startYear);
        }
        if (i > this.endYear - 1) {
            throw new IllegalArgumentException("Current year " + i + " after end year " + this.endYear);
        }
        Spinner3D spinner3D = this.year;
        if (spinner3D != null) {
            spinner3D.setModel(new SpinnerNumberModel(this.startYear, this.endYear, i, 1));
        }
    }

    public int getCurrentDay() {
        return ((Integer) this.day.getValue()).intValue();
    }

    public void setCurrentDay(int i) {
        this.currentDay = i;
        if (!this.explicitStartDay && this.startDay > i) {
            this.startDay = i;
        }
        if (!this.explicitEndDay && this.endDay - 1 < i) {
            this.endDay = i + 1;
        }
        if (this.startDay > i) {
            throw new IllegalArgumentException("Start day " + this.startDay + " after current day " + i);
        }
        if (this.endDay - 1 < i) {
            throw new IllegalArgumentException("End day " + this.endDay + " before current day " + i);
        }
        if (this.day != null) {
            rebuildDay();
        }
    }

    public int getCurrentMonth() {
        if (this.month != null) {
            return Integer.valueOf(((((Integer) r0.getValue()).intValue() - 1) % 12) + 1).intValue();
        }
        return this.currentMonth;
    }

    public void setCurrentMonth(int i) {
        this.currentMonth = i;
        if (!this.explicitStartMonth && this.startMonth > i) {
            this.startMonth = i;
        }
        if (!this.explicitEndMonth && this.endMonth - 1 < i) {
            this.endMonth = i + 1;
        }
        if (this.startMonth > i) {
            throw new IllegalArgumentException("Start month " + this.startMonth + " after current month " + i);
        }
        if (this.endMonth - 1 < i) {
            throw new IllegalArgumentException("End month " + this.endMonth + " before current month " + i);
        }
        if (this.month != null) {
            rebuildMonth();
        }
    }

    public boolean isMonthDayYear() {
        return this.monthDayYear;
    }

    public void setMonthDayYear(boolean z) {
        this.monthDayYear = z;
        this.wrapper.removeAll();
        addComponents();
    }

    public boolean isNumericMonths() {
        return this.numericMonths;
    }

    public void setNumericMonths(boolean z) {
        this.numericMonths = z;
        Spinner3D spinner3D = this.month;
        if (spinner3D != null) {
            spinner3D.repaint();
        }
    }

    public String[] getPropertyNames() {
        return new String[]{"startYear", "endYear", "currentYear", "currentDay", "currentMonth", "monthDayYear", "numericMonths"};
    }

    public Class[] getPropertyTypes() {
        return new Class[]{Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Boolean.class, Boolean.class};
    }

    public Object getPropertyValue(String str) {
        if (str.equals("startYear")) {
            return new Integer(this.startYear);
        }
        if (str.equals("endYear")) {
            return new Integer(this.endYear);
        }
        if (str.equals("currentYear")) {
            return new Integer(this.currentYear);
        }
        if (str.equals("currentDay")) {
            return new Integer(this.currentDay);
        }
        if (str.equals("currentMonth")) {
            return new Integer(this.currentMonth);
        }
        if (str.equals("monthDayYear")) {
            return new Boolean(this.monthDayYear);
        }
        if (str.equals("numericMonths")) {
            return new Boolean(this.numericMonths);
        }
        return null;
    }

    public String setPropertyValue(String str, Object obj) {
        if (str.equals("startYear")) {
            setStartYear(Integer.parseInt(obj.toString()));
            return null;
        }
        if (str.equals("endYear")) {
            setEndYear(Integer.parseInt(obj.toString()));
            return null;
        }
        if (str.equals("currentYear")) {
            setCurrentYear(Integer.parseInt(obj.toString()));
            return null;
        }
        if (str.equals("currentDay")) {
            setCurrentDay(Integer.parseInt(obj.toString()));
            return null;
        }
        if (str.equals("currentMonth")) {
            setCurrentMonth(Integer.parseInt(obj.toString()));
            return null;
        }
        if (str.equals("monthDayYear")) {
            setMonthDayYear(((Boolean) obj).booleanValue());
            return null;
        }
        if (str.equals("numericMonths")) {
            setNumericMonths(((Boolean) obj).booleanValue());
            return null;
        }
        return super.setPropertyValue(str, obj);
    }

    public Object getValue() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(5, 15);
        calendar.set(2, getCurrentMonth() - 1);
        calendar.set(1, getCurrentYear());
        calendar.set(11, this.hourOfDay);
        calendar.set(12, this.minuteOfDay);
        calendar.set(13, this.secondsOfDay);
        calendar.set(14, this.millisOfDay);
        return calendar.getTime();
    }

    public void setValue(Object obj) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date) obj);
        setCurrentDay(calendar.get(5));
        setCurrentMonth(calendar.get(2) + 1);
        setCurrentYear(calendar.get(1));
        this.hourOfDay = calendar.get(11);
        this.minuteOfDay = calendar.get(12);
        this.secondsOfDay = calendar.get(13);
        this.millisOfDay = calendar.get(14);
    }

    public void paint(Graphics graphics) {
        int alpha = graphics.getAlpha();
        graphics.setColor(this.year.getSelectedOverlayStyle().getBgColor());
        graphics.setAlpha(255);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
        graphics.setAlpha(alpha);
        super.paint(graphics);
    }
}
