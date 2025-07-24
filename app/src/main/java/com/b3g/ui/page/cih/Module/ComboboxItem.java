package com.b3g.ui.page.cih.Module;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.List;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.ListCellRenderer;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ComboboxItem implements ListCellRenderer {
    ComboboxItem comboboxItem;
    Container comboboxItemContainer;
    public String text;
    Button textBtn;

    public Component getListFocusComponent(List list) {
        return null;
    }

    public ComboboxItem(String str) {
        this.text = str;
    }

    public ComboboxItem() {
        this.comboboxItemContainer = new Container(BoxLayout.y());
        Container container = new Container(new FlowLayout(4, 0));
        Container container2 = new Container(new FlowLayout(4, 0));
        container2.setUIID("whiteCnt");
        container.setUIID("Container");
        Button button = new Button();
        this.textBtn = button;
        button.setUIID("lbl_regular");
        container.add(this.textBtn);
        this.comboboxItemContainer.add(container);
        this.comboboxItemContainer.add(container2);
    }

    public Component getListCellRendererComponent(List list, Object obj, int i, boolean z) {
        ComboboxItem comboboxItem = (ComboboxItem) obj;
        this.comboboxItem = comboboxItem;
        this.textBtn.setText(comboboxItem.text);
        return this.comboboxItemContainer;
    }
}
