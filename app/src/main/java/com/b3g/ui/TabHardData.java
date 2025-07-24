package com.b3g.ui;

import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TabHardData {
    public ArrayList GetTabsList() {
        ArrayList arrayList = new ArrayList();
        TabItem tabItem = new TabItem();
        tabItem.setUid(1);
        tabItem.setLargeTitle("Virement");
        tabItem.setNextForm(26);
        tabItem.setParent("");
        tabItem.setSeparator(0);
        tabItem.setSmallTitle("Virement");
        arrayList.add(tabItem);
        TabItem tabItem2 = new TabItem();
        tabItem2.setUid(2);
        tabItem2.setLargeTitle("Bénéficiaire");
        tabItem2.setNextForm(27);
        tabItem2.setParent("");
        tabItem2.setSeparator(0);
        tabItem2.setSmallTitle("Bénéficiaire");
        arrayList.add(tabItem2);
        TabItem tabItem3 = new TabItem();
        tabItem3.setUid(3);
        tabItem3.setLargeTitle("Historique");
        tabItem3.setNextForm(28);
        tabItem3.setParent("");
        tabItem3.setSeparator(0);
        arrayList.add(tabItem3);
        return arrayList;
    }

    public ArrayList GetAccountTabsMenu() {
        ArrayList arrayList = new ArrayList();
        TabItem tabItem = new TabItem();
        tabItem.setUid(1);
        tabItem.setLargeTitle("Opérations");
        tabItem.setNextForm(19);
        tabItem.setParent("");
        tabItem.setSeparator(0);
        tabItem.setSmallTitle("Opérations");
        arrayList.add(tabItem);
        TabItem tabItem2 = new TabItem();
        tabItem2.setUid(2);
        tabItem2.setLargeTitle("Autorisations");
        tabItem2.setNextForm(19);
        tabItem2.setParent("");
        tabItem2.setSeparator(0);
        tabItem2.setSmallTitle("Autorisations");
        arrayList.add(tabItem2);
        return arrayList;
    }

    public ArrayList GetCardDetailTabsMenu() {
        ArrayList arrayList = new ArrayList();
        TabItem tabItem = new TabItem();
        tabItem.setUid(1);
        tabItem.setLargeTitle("Opérations");
        tabItem.setNextForm(19);
        tabItem.setParent("");
        tabItem.setSeparator(0);
        tabItem.setSmallTitle("Opérations");
        arrayList.add(tabItem);
        TabItem tabItem2 = new TabItem();
        tabItem2.setUid(2);
        tabItem2.setLargeTitle("Plafonds");
        tabItem2.setNextForm(19);
        tabItem2.setParent("");
        tabItem2.setSeparator(0);
        tabItem2.setSmallTitle("Plafonds");
        arrayList.add(tabItem2);
        TabItem tabItem3 = new TabItem();
        tabItem3.setUid(3);
        tabItem3.setLargeTitle("Opérations");
        tabItem3.setNextForm(19);
        tabItem3.setParent("");
        tabItem3.setSeparator(0);
        tabItem3.setSmallTitle("Opérations");
        arrayList.add(tabItem3);
        return arrayList;
    }

    public ArrayList GetGloablTestTabsMenu() {
        ArrayList arrayList = new ArrayList();
        TabItem tabItem = new TabItem();
        tabItem.setUid(1);
        tabItem.setLargeTitle("Mes Comptes");
        tabItem.setNextForm(19);
        tabItem.setParent("");
        tabItem.setSeparator(0);
        tabItem.setSmallTitle("Opérations");
        arrayList.add(tabItem);
        return arrayList;
    }
}
