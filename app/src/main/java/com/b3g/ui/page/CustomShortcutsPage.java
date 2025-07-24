package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Shortcut;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.ShortcutStore;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.util.LinkedList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CustomShortcutsPage extends B3GPage {
    private final ShortcutStore store = ShortcutStore.getInstance();

    static /* synthetic */ ShortcutStore access$000(CustomShortcutsPage customShortcutsPage) {
        return customShortcutsPage.store;
    }

    public CustomShortcutsPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new BorderLayout());
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.setScrollableY(false);
        this.thisContainer.setScrollVisible(false);
        Label label = new Label("Personnalisez vos raccourcis");
        label.setUIID("DefaultTitleOrange");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        label.setScrollVisible(false);
        label.setEnabled(false);
        this.thisContainer.addComponent("North", label);
        Container container = new Container(new BoxLayout(2));
        container.setUIID("ad_CntAccountDetailsMain");
        SpanLabel spanLabel = new SpanLabel("Sélectionner les 6 fonctionnalités que vous souhaitez afficher parmi les raccourcis sur votre page principale.", "DefaultContentText");
        spanLabel.setUIID("padding_1_1_1_1");
        container.setScrollableY(true);
        container.setScrollVisible(false);
        container.add(spanLabel);
        LinkedList shortcuts = this.store.getShortcuts();
        Button button = new Button("Confirmer");
        for (int i = 0; i < shortcuts.size(); i++) {
            container.add(((Shortcut) shortcuts.get(i)).ShortcutItem(this.uiManager, button));
        }
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("padding_3_3_0_0");
        button.setUIID("Orange_Btn");
        button.addActionListener(new 1());
        container2.add(button);
        this.thisContainer.add("Center", container);
        this.thisContainer.add("South", container2);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Integer[] numArr = new Integer[6];
            for (int i = 0; i < CustomShortcutsPage.access$000(CustomShortcutsPage.this).currentShortcuts.size(); i++) {
                numArr[i] = Integer.valueOf(((Shortcut) CustomShortcutsPage.access$000(CustomShortcutsPage.this).currentShortcuts.get(i)).pageId);
            }
            CustomShortcutsPage.access$000(CustomShortcutsPage.this).updateShortcuts(numArr);
            CustomShortcutsPage.this.uiManager.GoBack();
        }
    }
}
