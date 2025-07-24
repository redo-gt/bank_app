package com.b3g.ui.page.cih.Module;

import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.util.EventDispatcher;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SpinnerNumberModel implements ListModel {
    private double currentValue;
    private EventDispatcher dataListener;
    private double max;
    private double min;
    boolean realValues;
    private EventDispatcher selectionListener;
    private double step;

    public void addItem(Object obj) {
    }

    public void removeItem(int i) {
    }

    void setValue(Object obj) {
        if (obj instanceof Integer) {
            this.currentValue = ((Integer) obj).doubleValue();
        } else {
            this.currentValue = ((Double) obj).doubleValue();
        }
        this.selectionListener.fireSelectionEvent(-1, -1);
    }

    Object getValue() {
        if (this.realValues) {
            return new Double(this.currentValue);
        }
        return new Integer((int) this.currentValue);
    }

    public SpinnerNumberModel(int i, int i2, int i3, int i4) {
        this.dataListener = new EventDispatcher();
        this.selectionListener = new EventDispatcher();
        this.max = i2;
        this.min = i;
        this.currentValue = i3;
        this.step = i4;
    }

    public SpinnerNumberModel(double d, double d2, double d3, double d4) {
        this.dataListener = new EventDispatcher();
        this.selectionListener = new EventDispatcher();
        this.max = d2;
        this.min = d;
        this.currentValue = d3;
        this.step = d4;
        this.realValues = true;
    }

    public Object getItemAt(int i) {
        if (this.realValues) {
            return new Double(this.min + (this.step * i));
        }
        return new Integer((int) (this.min + (this.step * i)));
    }

    public int getSize() {
        return (int) ((this.max - this.min) / this.step);
    }

    public int getSelectedIndex() {
        return getSize() - ((int) Math.floor(((this.max - this.currentValue) / this.step) + 0.5d));
    }

    public void setSelectedIndex(int i) {
        int selectedIndex = getSelectedIndex();
        this.currentValue = this.min + (i * this.step);
        this.selectionListener.fireSelectionEvent(selectedIndex, getSelectedIndex());
    }

    public void addDataChangedListener(DataChangedListener dataChangedListener) {
        this.dataListener.addListener(dataChangedListener);
    }

    public void removeDataChangedListener(DataChangedListener dataChangedListener) {
        this.dataListener.removeListener(dataChangedListener);
    }

    public void addSelectionListener(SelectionListener selectionListener) {
        this.selectionListener.addListener(selectionListener);
    }

    public void removeSelectionListener(SelectionListener selectionListener) {
        this.selectionListener.removeListener(selectionListener);
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }
}
