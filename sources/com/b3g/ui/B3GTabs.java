package com.b3g.ui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3GTabs extends AbstractTabs {
    public B3GTabs(ArrayList arrayList) {
        this.t = new Tabs();
        this.TabsCnt = new Container(new BorderLayout());
        this.TabsCnt.setUIID("Container");
        this.TabsCnt.setTensileDragEnabled(false);
        this.tabItemList = arrayList;
        ((AbstractTabItem) arrayList.get(0)).isSelected = true;
    }

    public Container drawTabs(int i) {
        setTensileDragEnabled(false);
        this.header = drawHeader();
        this.TabsCnt.addComponent("North", this.header);
        this.t.setAlwaysTensile(false);
        this.t.setSnapToGrid(true);
        this.t.setTensileDragEnabled(false);
        this.t.setUIID("BlankCnt");
        for (int i2 = 0; i2 < this.tabItemList.size(); i2++) {
            ((AbstractTabItem) this.tabItemList.get(i2)).tabCnt.setUIID("white_container");
            ((AbstractTabItem) this.tabItemList.get(i2)).tabCnt.setScrollableY(false);
            ((AbstractTabItem) this.tabItemList.get(i2)).tabCnt.setScrollVisible(false);
            this.t.addTab("", ((AbstractTabItem) this.tabItemList.get(i2)).tabCnt);
        }
        for (int i3 = 0; i3 < this.tabItemList.size(); i3++) {
            goToSelectedTab(((AbstractTabItem) this.tabItemList.get(i3)).fackBtn, this.t, i3);
        }
        this.t.addSelectionListener(new 1());
        this.t.setSwipeActivated(false);
        this.TabsCnt.addComponent("Center", this.t);
        return this.TabsCnt;
    }

    class 1 implements SelectionListener {
        1() {
        }

        public void selectionChanged(int i, int i2) {
            ((AbstractTabItem) B3GTabs.this.tabItemList.get(i2)).isSelected = true;
            ((AbstractTabItem) B3GTabs.this.tabItemList.get(i)).isSelected = false;
            B3GTabs.this.header.removeAll();
            B3GTabs b3GTabs = B3GTabs.this;
            b3GTabs.header = b3GTabs.drawHeader();
            B3GTabs.this.TabsCnt.addComponent("North", B3GTabs.this.header);
            for (int i3 = 0; i3 < B3GTabs.this.tabItemList.size(); i3++) {
                B3GTabs.goToSelectedTab(((AbstractTabItem) B3GTabs.this.tabItemList.get(i3)).fackBtn, B3GTabs.this.t, i3);
            }
        }
    }

    public Container drawHeader() {
        Container container = new Container(new BorderLayout());
        FlowLayout flowLayout = new FlowLayout(1, 4);
        flowLayout.setFillRows(true);
        Container container2 = new Container(flowLayout);
        for (int i = 0; i < this.tabItemList.size(); i++) {
            container2.addComponent(((AbstractTabItem) this.tabItemList.get(i)).drawTabHeaderCnt());
        }
        container.addComponent("Center", container2);
        container.getAllStyles().setPadding(1, 1, 2, 2);
        container.getAllStyles().setPaddingUnit(2, 2, 2, 2);
        return container;
    }

    class 2 implements ActionListener {
        final /* synthetic */ int val$index;
        final /* synthetic */ Tabs val$t;

        2(Tabs tabs, int i) {
            this.val$t = tabs;
            this.val$index = i;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$t.setSelectedIndex(this.val$index, true);
            this.val$t.animate();
        }
    }

    public static void goToSelectedTab(Button button, Tabs tabs, int i) {
        button.addActionListener(new 2(tabs, i));
    }

    public void goToSelectedTab(int i) {
        ((AbstractTabItem) this.tabItemList.get(0)).isSelected = false;
        ((AbstractTabItem) this.tabItemList.get(1)).isSelected = true;
        this.header.removeAll();
        this.header = drawHeader();
        this.TabsCnt.addComponent("North", this.header);
        this.t.setSelectedIndex(i, true);
        this.t.animate();
    }
}
