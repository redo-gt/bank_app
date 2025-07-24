package com.b3g.ui;

import com.codename1.ui.Container;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3GViewPager extends AbstractTabs {
    public Tabs t;

    public B3GViewPager(ArrayList arrayList) {
        this.TabsCnt = new Container(new LayeredLayout());
        this.TabsCnt.setUIID("Container");
        this.tabItemList = arrayList;
        ((AbstractTabItem) arrayList.get(0)).isSelected = true;
        this.t = new Tabs();
    }

    public Container drawTabs(int i) {
        setTensileDragEnabled(false);
        this.header = drawHeader();
        this.t.setUIID("Container");
        for (int i2 = 0; i2 < this.tabItemList.size(); i2++) {
            ((AbstractTabItem) this.tabItemList.get(i2)).tabCnt.setUIID("Container");
            this.t.addTab("", ((AbstractTabItem) this.tabItemList.get(i2)).tabCnt);
        }
        this.t.addSelectionListener(new 1());
        this.t.setSwipeActivated(true);
        this.t.setSelectedIndex(0);
        this.TabsCnt.addComponent(this.t);
        this.TabsCnt.addComponent(this.header);
        return this.TabsCnt;
    }

    class 1 implements SelectionListener {
        1() {
        }

        public void selectionChanged(int i, int i2) {
            ((AbstractTabItem) B3GViewPager.this.tabItemList.get(i2)).isSelected = true;
            ((AbstractTabItem) B3GViewPager.this.tabItemList.get(i)).isSelected = false;
            B3GViewPager.this.header.removeAll();
            B3GViewPager b3GViewPager = B3GViewPager.this;
            b3GViewPager.header = b3GViewPager.drawHeader();
            B3GViewPager.this.TabsCnt.addComponent(1, B3GViewPager.this.header);
        }
    }

    public Container drawHeader() {
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(new BorderLayout());
        container2.setUIID("Container");
        Container container3 = new Container(new FlowLayout(1, 4));
        container3.setUIID("Container");
        for (int i = 0; i < this.tabItemList.size(); i++) {
            container3.addComponent(((AbstractTabItem) this.tabItemList.get(i)).drawTabHeaderCnt());
        }
        Container container4 = new Container(new BorderLayout());
        container4.addComponent("East", container3);
        container2.addComponent("South", container4);
        container2.getAllStyles().setPadding(0, 1, 0, 0);
        container2.getAllStyles().setPaddingUnit(2, 2, 2, 2);
        container.addComponent("Center", container2);
        return container;
    }
}
