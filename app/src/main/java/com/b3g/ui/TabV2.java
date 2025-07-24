package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.tools.DataTools;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TabV2 implements ITab {
    private StateMachine cndUi;
    public DataTools dt = new DataTools();

    TabV2(StateMachine stateMachine) {
        setCndUi(stateMachine);
    }

    public StateMachine getCndUi() {
        return this.cndUi;
    }

    public void setCndUi(StateMachine stateMachine) {
        this.cndUi = stateMachine;
    }

    public void DrawTabs(Container container, Object obj, int i, String str, String str2, Object obj2, int i2) {
        try {
            if (i2 == 5) {
                Resources.open("/cihv3.res");
            } else if (i2 == 9) {
                Resources.open("/cihv3.res");
                ArrayList arrayList = new ArrayList();
                arrayList.add((B3gService) ((Account) CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().get(0)).AccountAutorisationList.get(0));
                arrayList.add((B3gService) ((Account) CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().get(0)).AccountAutorisationList.get(1));
                arrayList.add((B3gService) ((Account) CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().get(0)).AccountAutorisationList.get(2));
            } else {
                this.cndUi.showForm("Login", (Command) null);
            }
        } catch (IOException unused) {
        }
    }

    public void DrawTabsV1(Container container, Object obj, int i, String str, String str2, ArrayList arrayList, Resources resources) {
        Tabs tabs = new Tabs();
        try {
            container.addComponent(DrawTabMenuV2(obj, tabs, arrayList, resources));
            tabs.setName(str);
            tabs.setUIID(str2);
            tabs.hideTabs();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                tabs.addTab("Mes ...", (Component) arrayList.get(i2));
            }
            container.addComponent(tabs);
            container.revalidate();
        } catch (Exception unused) {
        }
    }

    public Container DrawOneItemTab(Container container) {
        Container container2 = new Container(new BorderLayout());
        try {
            container2.addComponent("Center", container);
        } catch (Exception unused) {
        }
        return container2;
    }

    public Container DrawTabMenu(Object obj, Tabs tabs) {
        ArrayList arrayList = (ArrayList) obj;
        TableLayout tableLayout = new TableLayout(1, 1);
        Container container = new Container();
        container.setLayout(tableLayout);
        container.setUIID("Container");
        container.setEnabled(true);
        GridLayout gridLayout = new GridLayout(1, arrayList.size());
        Container container2 = new Container();
        container2.setLayout(gridLayout);
        container2.setUIID("cntGridTabMenuButton");
        container2.setEnabled(true);
        for (int i = 0; i < arrayList.size(); i++) {
            container2.addComponent(DrawTabMenuOneItem(tabs, arrayList.get(i), i));
        }
        TableLayout.Constraint createConstraint = tableLayout.createConstraint(0, 0);
        createConstraint.setWidthPercentage(100);
        createConstraint.setHorizontalAlign(4);
        container.addComponent(createConstraint, container2);
        return container;
    }

    public Container DrawTabMenuV2(Object obj, Tabs tabs, ArrayList arrayList, Resources resources) {
        try {
            Container createContainer = this.cndUi.createContainer(resources, "GloabalContainerV2");
            if (arrayList.size() > 1) {
                SetPagerTab(createContainer, arrayList.size(), "gb_btnDot", resources, "slider_arrow.png", "slider_arrow_w.png");
                tabs.addSelectionListener(new 1(createContainer, arrayList, resources, tabs));
            }
            return createContainer;
        } catch (Exception unused) {
            return new Container();
        }
    }

    class 1 implements SelectionListener {
        final /* synthetic */ Tabs val$Tab;
        final /* synthetic */ Container val$cntGloableHeader;
        final /* synthetic */ ArrayList val$lisCntToTab;
        final /* synthetic */ Resources val$myres;

        1(Container container, ArrayList arrayList, Resources resources, Tabs tabs) {
            this.val$cntGloableHeader = container;
            this.val$lisCntToTab = arrayList;
            this.val$myres = resources;
            this.val$Tab = tabs;
        }

        public void selectionChanged(int i, int i2) {
            TabV2.this.SetPagerStatus(this.val$cntGloableHeader, this.val$lisCntToTab.size(), i2, this.val$myres);
            this.val$Tab.getParent().getParent().revalidate();
        }
    }

    public Container DrawTabMenuOneItem(Tabs tabs, Object obj, int i) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("cntTabItem");
        container.setEnabled(true);
        Button button = new Button(((TabItem) obj).getLargeTitle());
        button.setUIID("g_lblTabsItem");
        button.setEnabled(true);
        if (i == 0) {
            button.pressed();
        }
        button.addActionListener(new 2(tabs, i));
        container.addComponent(button);
        return container;
    }

    class 2 implements ActionListener {
        final /* synthetic */ Tabs val$Tab;
        final /* synthetic */ int val$TabIndex;

        2(Tabs tabs, int i) {
            this.val$Tab = tabs;
            this.val$TabIndex = i;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$Tab.setSelectedIndex(this.val$TabIndex);
        }
    }

    public void SetPagerTab(Container container, int i, String str, Resources resources, String str2, String str3) {
        try {
            this.cndUi.findCntHeaderPagerV2(container).removeAll();
            for (int i2 = 0; i2 < i; i2++) {
                Button button = new Button();
                button.setIcon(resources.getImage(str2));
                button.setDisabledIcon(resources.getImage(str3));
                button.setUIID(str);
                if (i2 == 0) {
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                }
                this.cndUi.findCntHeaderPagerV2(container).addComponent(button);
            }
        } catch (Exception unused) {
        }
    }

    public void SetPagerStatus(Container container, int i, int i2, Resources resources) {
        if (i == 1) {
            this.cndUi.findCntHeaderPagerV2(container).getComponentAt(i2).setEnabled(true);
            return;
        }
        for (int i3 = 0; i3 < this.cndUi.findCntHeaderPagerV2(container).getComponentCount(); i3++) {
            if (i2 == i3) {
                this.cndUi.findCntHeaderPagerV2(container).getComponentAt(i3).setEnabled(false);
            } else {
                this.cndUi.findCntHeaderPagerV2(container).getComponentAt(i3).setEnabled(true);
            }
        }
    }
}
