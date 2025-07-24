package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3GRadioWithIcon {
    String RadioStyle;
    public final ArrayList allButtons;
    boolean isClear;
    Layout layout;
    Container radioContainer;
    int selectedIndex;
    String toggleValue;

    public class B3GRadioItem extends Container {
        public String icon;
        public String icon1;
        public RadioButton radioButton;
        public RadioButton radioButton1;

        public B3GRadioItem(RadioButton radioButton, String str) {
            this.radioButton = radioButton;
            this.icon = str;
            setLeadComponent(radioButton);
            Label label = new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage(str));
            label.setUIID("Container");
            Component container = new Container(BoxLayout.x());
            container.setUIID("greyCnt");
            setLayout(new BorderLayout());
            add("West", this.radioButton);
            add("East", label);
            add("South", container);
        }

        public B3GRadioItem(RadioButton radioButton, String str, String str2) {
            this.radioButton = radioButton;
            this.icon = str;
            setLeadComponent(radioButton);
            this.radioButton.setUIID(str2);
            Label label = new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage(str));
            label.setUIID(str2);
            Component container = new Container(BoxLayout.x());
            container.setUIID("greyCnt");
            setLayout(new BorderLayout());
            add("West", this.radioButton);
            add("East", label);
            add("South", container);
        }

        public B3GRadioItem(String str, RadioButton radioButton, String str2) {
            this.radioButton = radioButton;
            this.icon = str;
            setLeadComponent(radioButton);
            this.radioButton.setUIID(str2);
            Label label = new Label(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage(str));
            label.setUIID(str2);
            Component container = new Container(BoxLayout.x());
            container.setUIID("greyCnt");
            setLayout(new BorderLayout());
            add("West", this.radioButton);
            add("Center", label);
            add("South", container);
        }
    }

    public B3GRadioWithIcon(String str) {
        this.RadioStyle = "";
        this.selectedIndex = 0;
        this.isClear = true;
        this.toggleValue = str;
        this.allButtons = new ArrayList();
        this.layout = new FlowLayout();
        Container container = new Container(this.layout);
        this.radioContainer = container;
        container.setUIID("g_CntTranspMsg");
    }

    public B3GRadioWithIcon(String str, Layout layout) {
        this.RadioStyle = "";
        this.selectedIndex = 0;
        this.isClear = true;
        this.toggleValue = str;
        this.allButtons = new ArrayList();
        this.layout = layout;
        this.radioContainer = new Container(this.layout);
    }

    public void setUIID(String str) {
        this.radioContainer.setUIID(str);
        this.RadioStyle = str + "Item";
    }

    public void setRadioUIID(String str) {
        this.RadioStyle = str;
    }

    public RadioButton getItem(String str) {
        RadioButton radioButton = new RadioButton();
        Iterator it = this.allButtons.iterator();
        while (it.hasNext()) {
            B3GRadioItem b3GRadioItem = (B3GRadioItem) it.next();
            if (b3GRadioItem.radioButton.getGroup().equals(str)) {
                radioButton = b3GRadioItem.radioButton;
            }
        }
        return radioButton;
    }

    public void addItem(String str, String str2) {
        RadioButton radioButton = new RadioButton(str);
        radioButton.setToggle(true);
        radioButton.setGroup(str);
        radioButton.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("radio_off.PNG"));
        radioButton.setPressedIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("radio_on.png"));
        radioButton.addActionListener(new 1());
        this.allButtons.add(new B3GRadioItem(radioButton, str2));
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            for (Integer num = 0; num.intValue() < B3GRadioWithIcon.this.allButtons.size(); num = Integer.valueOf(num.intValue() + 1)) {
                ((B3GRadioItem) B3GRadioWithIcon.this.allButtons.get(num.intValue())).radioButton.setSelected(false);
            }
            ((RadioButton) actionEvent.getComponent()).setSelected(true);
        }
    }

    public void addItem(String str, String str2, String str3) {
        RadioButton radioButton = new RadioButton(str);
        radioButton.setToggle(true);
        radioButton.setGroup(str);
        radioButton.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("radio_off.PNG"));
        radioButton.setPressedIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("radio_on.png"));
        radioButton.addActionListener(new 2());
        this.allButtons.add(new B3GRadioItem(radioButton, str2, str3));
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            for (Integer num = 0; num.intValue() < B3GRadioWithIcon.this.allButtons.size(); num = Integer.valueOf(num.intValue() + 1)) {
                ((B3GRadioItem) B3GRadioWithIcon.this.allButtons.get(num.intValue())).radioButton.setSelected(false);
            }
            ((RadioButton) actionEvent.getComponent()).setSelected(true);
        }
    }

    public void addItem1(String str, String str2, String str3) {
        RadioButton radioButton = new RadioButton(str);
        radioButton.setToggle(true);
        radioButton.setGroup(str);
        radioButton.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("radio_off.PNG"));
        radioButton.setPressedIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("radio_on.png"));
        radioButton.addActionListener(new 3());
        this.allButtons.add(new B3GRadioItem(str2, radioButton, str3));
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            for (Integer num = 0; num.intValue() < B3GRadioWithIcon.this.allButtons.size(); num = Integer.valueOf(num.intValue() + 1)) {
                ((B3GRadioItem) B3GRadioWithIcon.this.allButtons.get(num.intValue())).radioButton.setSelected(false);
            }
            ((RadioButton) actionEvent.getComponent()).setSelected(true);
        }
    }

    public Container GetContainer() {
        for (int i = 0; i < this.allButtons.size(); i++) {
            ((B3GRadioItem) this.allButtons.get(i)).setUIID(this.RadioStyle);
            this.radioContainer.addComponent((Component) this.allButtons.get(i));
        }
        this.radioContainer.revalidate();
        return this.radioContainer;
    }

    public void setItemBtnUid(String str) {
        for (int i = 0; i < this.allButtons.size(); i++) {
            ((B3GRadioItem) this.allButtons.get(i)).setUIID(str);
        }
    }

    public String GetSelectedText() {
        for (int i = 0; i < this.allButtons.size(); i++) {
            if (((B3GRadioItem) this.allButtons.get(i)).radioButton.isSelected()) {
                return ((B3GRadioItem) this.allButtons.get(i)).radioButton.getText();
            }
        }
        return "Error Selection";
    }

    public Integer GetSelectedIndex() {
        return Integer.valueOf(this.selectedIndex);
    }

    public void clear() {
        for (int i = 0; i < this.allButtons.size(); i++) {
            ((B3GRadioItem) this.allButtons.get(i)).radioButton.setSelected(false);
        }
    }

    public boolean isClear() {
        int i = 0;
        while (true) {
            if (i >= this.allButtons.size()) {
                break;
            }
            if (((B3GRadioItem) this.allButtons.get(i)).radioButton.isSelected()) {
                this.isClear = false;
                break;
            }
            i++;
        }
        return this.isClear;
    }

    public void setSelectedIndex(int i) {
        if (i > this.allButtons.size()) {
            clear();
            return;
        }
        for (int i2 = 0; i2 < this.allButtons.size(); i2++) {
            if (i2 == i) {
                ((B3GRadioItem) this.allButtons.get(i2)).radioButton.setSelected(true);
                this.selectedIndex = i;
                return;
            }
        }
    }
}
