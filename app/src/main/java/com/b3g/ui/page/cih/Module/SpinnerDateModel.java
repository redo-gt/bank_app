package com.b3g.ui.page.cih.Module;

import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.util.EventDispatcher;
import java.util.Date;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SpinnerDateModel implements ListModel {
    private static final long DAY = 86400000;
    private long currentValue;
    private long max;
    private long min;
    private EventDispatcher dataListener = new EventDispatcher();
    private EventDispatcher selectionListener = new EventDispatcher();

    public void addItem(Object obj) {
    }

    public void removeItem(int i) {
    }

    void setValue(Date date) {
        this.currentValue = (date.getTime() - (date.getTime() % 86400000)) + 43200000;
    }

    Object getValue() {
        long j = this.currentValue;
        return new Date((j - (j % 86400000)) + 43200000);
    }

    public SpinnerDateModel(long j, long j2, long j3) {
        this.max = j2;
        this.min = j;
        this.currentValue = (j3 - (j3 % 86400000)) + 43200000;
    }

    public Object getItemAt(int i) {
        return new Date(this.min + (i * 86400000) + 43200000);
    }

    public int getSize() {
        return (int) ((this.max - this.min) / 86400000);
    }

    public int getSelectedIndex() {
        return (int) ((this.currentValue - this.min) / 86400000);
    }

    public void setSelectedIndex(int i) {
        int selectedIndex = getSelectedIndex();
        this.currentValue = this.min + (i * 86400000);
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
}
