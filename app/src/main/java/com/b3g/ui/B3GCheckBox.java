package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3GCheckBox {
    String RadioStyle;
    final ArrayList allCheckBoxs;
    Container checkBoxContainer;
    Layout layout;
    String toggleValue;

    public B3GCheckBox(String str) {
        this.RadioStyle = "";
        this.toggleValue = str;
        this.allCheckBoxs = new ArrayList();
        this.layout = new FlowLayout();
        Container container = new Container(this.layout);
        this.checkBoxContainer = container;
        container.setUIID("g_CntTranspMsg");
    }

    public B3GCheckBox(String str, Layout layout) {
        this.RadioStyle = "";
        this.toggleValue = str;
        this.allCheckBoxs = new ArrayList();
        this.layout = layout;
        this.checkBoxContainer = new Container(this.layout);
    }

    public void setUIID(String str) {
        this.checkBoxContainer.setUIID(str);
        this.RadioStyle = str + "Item";
    }

    public void setRadioUIID(String str) {
        this.RadioStyle = str;
    }

    public CheckBox getItem(String str) {
        CheckBox checkBox = new CheckBox();
        Iterator it = this.allCheckBoxs.iterator();
        while (it.hasNext()) {
            CheckBox checkBox2 = (CheckBox) it.next();
            if (checkBox2.getText().equals(str)) {
                checkBox = checkBox2;
            }
        }
        return checkBox;
    }

    public ArrayList getSelectedItems() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.allCheckBoxs.iterator();
        while (it.hasNext()) {
            CheckBox checkBox = (CheckBox) it.next();
            if (checkBox.isSelected()) {
                arrayList.add(checkBox);
            }
        }
        return arrayList;
    }

    public void addItem(String str) {
        CheckBox checkBox = new CheckBox(str);
        checkBox.setToggle(true);
        checkBox.setText(str);
        checkBox.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("radio_off.PNG"));
        checkBox.setPressedIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("radio_on.png"));
        this.allCheckBoxs.add(checkBox);
    }

    public Container GetContainer() {
        for (int i = 0; i < this.allCheckBoxs.size(); i++) {
            ((CheckBox) this.allCheckBoxs.get(i)).setUIID(this.RadioStyle);
            this.checkBoxContainer.addComponent((Component) this.allCheckBoxs.get(i));
        }
        this.checkBoxContainer.revalidate();
        return this.checkBoxContainer;
    }

    public String GetSelectedText() {
        for (int i = 0; i < this.allCheckBoxs.size(); i++) {
            if (((CheckBox) this.allCheckBoxs.get(i)).isSelected()) {
                return ((CheckBox) this.allCheckBoxs.get(i)).getText();
            }
        }
        return "Error Selection";
    }

    public Integer GetSelectedIndex() {
        for (int i = 0; i < this.allCheckBoxs.size(); i++) {
            if (((CheckBox) this.allCheckBoxs.get(i)).isSelected()) {
                return Integer.valueOf(i);
            }
        }
        return -1;
    }
}
