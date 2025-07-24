package com.b3g.ui;

import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3GTabs1 extends AbstractTabs {
    static /* synthetic */ void access$000(B3GTabs1 b3GTabs1, Button button, Tabs tabs, int i) {
        b3GTabs1.goToSelectedTab(button, tabs, i);
    }

    public B3GTabs1(ArrayList arrayList) {
        this.TabsCnt = new Container(new BorderLayout());
        this.TabsCnt.setUIID("Container");
        this.TabsCnt.setTensileDragEnabled(false);
        this.tabItemList = arrayList;
        ((AbstractTabItem) arrayList.get(0)).isSelected = true;
    }

    public Container drawTabs(int i) {
        setTensileDragEnabled(false);
        new Container().setUIID("EmptyContainer");
        this.header = drawHeader();
        this.TabsCnt.addComponent("North", this.header);
        this.TabsCnt.setScrollVisible(false);
        this.TabsCnt.setScrollableY(false);
        Tabs tabs = new Tabs();
        tabs.setAlwaysTensile(false);
        tabs.setSnapToGrid(true);
        tabs.setTensileDragEnabled(false);
        tabs.setUIID("BlankCnt");
        for (int i2 = 0; i2 < this.tabItemList.size(); i2++) {
            ((AbstractTabItem) this.tabItemList.get(i2)).tabCnt.setUIID("Container");
            ((AbstractTabItem) this.tabItemList.get(i2)).tabCnt.setScrollableY(false);
            ((AbstractTabItem) this.tabItemList.get(i2)).tabCnt.setScrollVisible(false);
            tabs.addTab("", ((AbstractTabItem) this.tabItemList.get(i2)).tabCnt);
        }
        for (int i3 = 0; i3 < this.tabItemList.size(); i3++) {
            goToSelectedTab(((AbstractTabItem) this.tabItemList.get(i3)).fackBtn, tabs, i3);
        }
        tabs.addSelectionListener(new 1(tabs));
        tabs.setSwipeActivated(false);
        this.TabsCnt.addComponent("Center", tabs);
        return this.TabsCnt;
    }

    class 1 implements SelectionListener {
        final /* synthetic */ Tabs val$t;

        1(Tabs tabs) {
            this.val$t = tabs;
        }

        public void selectionChanged(int i, int i2) {
            ((AbstractTabItem) B3GTabs1.this.tabItemList.get(i2)).isSelected = true;
            ((AbstractTabItem) B3GTabs1.this.tabItemList.get(i)).isSelected = false;
            B3GTabs1.this.header.removeAll();
            B3GTabs1 b3GTabs1 = B3GTabs1.this;
            b3GTabs1.header = b3GTabs1.drawHeader();
            B3GTabs1.this.TabsCnt.addComponent("North", B3GTabs1.this.header);
            for (int i3 = 0; i3 < B3GTabs1.this.tabItemList.size(); i3++) {
                B3GTabs1 b3GTabs12 = B3GTabs1.this;
                B3GTabs1.access$000(b3GTabs12, ((AbstractTabItem) b3GTabs12.tabItemList.get(i3)).fackBtn, this.val$t, i3);
            }
        }
    }

    public Container drawHeader() {
        Container container = new Container(new BorderLayout());
        if (Settings.isNightMode()) {
            container.setUIID("LoginCntTrspDark");
        } else {
            container.setUIID("LoginCntTrsp");
        }
        Container container2 = new Container(new GridLayout(1, 2));
        for (int i = 0; i < this.tabItemList.size(); i++) {
            container2.addComponent(((AbstractTabItem) this.tabItemList.get(i)).drawTabHeaderCnt());
        }
        container.addComponent("Center", container2);
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

    private void goToSelectedTab(Button button, Tabs tabs, int i) {
        button.addActionListener(new 2(tabs, i));
    }

    public void goToSelectedTab(int i) {
        ((AbstractTabItem) this.tabItemList.get(0)).isSelected = false;
        ((AbstractTabItem) this.tabItemList.get(1)).isSelected = true;
        this.header.removeAll();
        this.header = drawHeader();
        this.TabsCnt.addComponent("North", this.header);
        this.t.setSwipeActivated(false);
        this.t.setSelectedIndex(i, true);
        this.t.animate();
    }
}
