package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3GRadio {
    String RadioStyle;
    final ArrayList allButtons;
    Layout layout;
    Container radioContainer;
    String toggleValue;

    public B3GRadio(String str) {
        this.RadioStyle = "";
        this.toggleValue = str;
        this.allButtons = new ArrayList();
        this.layout = new FlowLayout();
        Container container = new Container(this.layout);
        this.radioContainer = container;
        container.setUIID("g_CntTranspMsg");
    }

    public B3GRadio(String str, Layout layout) {
        this.RadioStyle = "";
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
            RadioButton radioButton2 = (RadioButton) it.next();
            if (radioButton2.getGroup().equals(str)) {
                radioButton = radioButton2;
            }
        }
        return radioButton;
    }

    public void addItem(String str) {
        RadioButton radioButton = new RadioButton(str);
        radioButton.setToggle(true);
        radioButton.setGroup(str);
        radioButton.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("radio_off.PNG"));
        radioButton.setPressedIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("radio_on.png"));
        radioButton.addActionListener(new 1());
        this.allButtons.add(radioButton);
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            for (Integer num = 0; num.intValue() < B3GRadio.this.allButtons.size(); num = Integer.valueOf(num.intValue() + 1)) {
                ((RadioButton) B3GRadio.this.allButtons.get(num.intValue())).setSelected(false);
            }
            ((RadioButton) actionEvent.getComponent()).setSelected(true);
        }
    }

    public void addItemSelct(String str) {
        RadioButton radioButton = new RadioButton(str);
        radioButton.setToggle(true);
        radioButton.setGroup(str);
        radioButton.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("radio_off.PNG"));
        radioButton.setPressedIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("radio_on.png"));
        radioButton.addActionListener(new 2());
        this.allButtons.add(radioButton);
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ((RadioButton) actionEvent.getComponent()).setSelected(true);
        }
    }

    public Container GetContainer() {
        for (int i = 0; i < this.allButtons.size(); i++) {
            ((RadioButton) this.allButtons.get(i)).setUIID(this.RadioStyle);
            this.radioContainer.addComponent((Component) this.allButtons.get(i));
        }
        ((RadioButton) this.allButtons.get(0)).setSelected(true);
        this.radioContainer.revalidate();
        return this.radioContainer;
    }

    public String GetSelectedText() {
        for (int i = 0; i < this.allButtons.size(); i++) {
            if (((RadioButton) this.allButtons.get(i)).isSelected()) {
                return ((RadioButton) this.allButtons.get(i)).getText();
            }
        }
        return "Error Selection";
    }

    public Integer GetSelectedIndex() {
        for (int i = 0; i < this.allButtons.size(); i++) {
            if (((RadioButton) this.allButtons.get(i)).isSelected()) {
                return Integer.valueOf(i);
            }
        }
        return -1;
    }
}
