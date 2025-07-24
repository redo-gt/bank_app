package com.b3g.ui.page.cih.Module;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.Shortcut;
import com.codename1.io.Preferences;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ShortcutStore {
    private static ShortcutStore store;
    public ArrayList currentShortcuts;
    public LinkedList shortcuts;

    public static ShortcutStore getInstance() {
        if (store == null) {
            store = new ShortcutStore();
        }
        return store;
    }

    public LinkedList getShortcuts() {
        LinkedList linkedList = new LinkedList();
        this.shortcuts = linkedList;
        initShortcutsList(linkedList);
        updateShortcutsList(this.shortcuts);
        return this.shortcuts;
    }

    private void initShortcutsList(LinkedList linkedList) {
        if (!CihMBanking.sesPMTR.isToCut()) {
            linkedList.add(new Shortcut(52));
            linkedList.add(new Shortcut(44));
            linkedList.add(new Shortcut(70));
            linkedList.add(new Shortcut(3));
            linkedList.add(new Shortcut(64));
            linkedList.add(new Shortcut(35));
        }
        linkedList.add(new Shortcut(8));
        linkedList.add(new Shortcut(25));
        linkedList.add(new Shortcut(33));
        linkedList.add(new Shortcut(39));
        linkedList.add(new Shortcut(55));
        linkedList.add(new Shortcut(4));
    }

    public static Integer[] getPreferedShortcuts() {
        Integer[] numArr = new Integer[6];
        if (!CihMBanking.sesPMTR.isToCut()) {
            numArr[0] = Integer.valueOf(Preferences.get("first_shortcut", 4));
            numArr[1] = Integer.valueOf(Preferences.get("second_shortcut", 3));
            numArr[2] = Integer.valueOf(Preferences.get("third_shortcut", 33));
            numArr[3] = Integer.valueOf(Preferences.get("thirdd_shortcut", 25));
            numArr[4] = Integer.valueOf(Preferences.get("thirddd_shortcut", 8));
            numArr[5] = Integer.valueOf(Preferences.get("thirdddd_shortcut", 55));
        } else {
            numArr[0] = Integer.valueOf(Preferences.get("first_shortcut", 8));
            numArr[1] = Integer.valueOf(Preferences.get("second_shortcut", 25));
            numArr[2] = Integer.valueOf(Preferences.get("third_shortcut", 33));
            numArr[3] = Integer.valueOf(Preferences.get("thirdd_shortcut", 39));
            numArr[4] = Integer.valueOf(Preferences.get("thirddd_shortcut", 55));
            numArr[5] = Integer.valueOf(Preferences.get("thirdddd_shortcut", 4));
        }
        return numArr;
    }

    public void updateShortcuts(Integer[] numArr) {
        Preferences.set("first_shortcut", numArr[0].intValue());
        Preferences.set("second_shortcut", numArr[1].intValue());
        Preferences.set("third_shortcut", numArr[2].intValue());
        Preferences.set("thirdd_shortcut", numArr[3].intValue());
        Preferences.set("thirddd_shortcut", numArr[4].intValue());
        Preferences.set("thirdddd_shortcut", numArr[5].intValue());
    }

    private LinkedList updateShortcutsList(LinkedList linkedList) {
        Integer[] preferedShortcuts = getPreferedShortcuts();
        this.currentShortcuts = new ArrayList();
        LinkedList linkedList2 = new LinkedList();
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            Shortcut shortcut = (Shortcut) it.next();
            if (shortcut.pageId == preferedShortcuts[0].intValue() || shortcut.pageId == preferedShortcuts[1].intValue() || shortcut.pageId == preferedShortcuts[2].intValue() || shortcut.pageId == preferedShortcuts[3].intValue() || shortcut.pageId == preferedShortcuts[4].intValue() || shortcut.pageId == preferedShortcuts[5].intValue()) {
                shortcut.isSelected = true;
                this.currentShortcuts.add(shortcut);
            }
            linkedList2.add(shortcut);
        }
        return linkedList2;
    }
}
