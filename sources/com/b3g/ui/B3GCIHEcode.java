package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.UITimer;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3GCIHEcode extends Component {
    String code;
    TextField txtF;
    Label[] labels = new Label[4];
    int oldLenght = 0;
    boolean flagOpen = true;
    int bgColor = 16777215;
    int sepColor1 = 15818018;
    int sepColor = 13421772;

    public B3GCIHEcode() {
        for (int i = 0; i < 4; i++) {
            this.labels[i] = new Label("   ");
            this.labels[i].getAllStyles().setPaddingUnit(1);
            this.labels[i].getAllStyles().setPadding(1, 1, 1, 1);
            this.labels[i].setAlignment(4);
            Label label = this.labels[i];
            label.setPreferredH(label.getPreferredH());
        }
    }

    public void setFlagOpen(boolean z) {
        this.flagOpen = z;
    }

    public void setBgColor(int i) {
        this.bgColor = i;
    }

    public String getValue() {
        return this.txtF.getText();
    }

    private void concCodeWithTextAndDelete(String str) {
        this.code += str;
    }

    public Component getComponent() {
        TextField textField = new TextField();
        this.txtF = textField;
        textField.setConstraint(65538);
        this.txtF.setMaxSize(4);
        this.txtF.getAllStyles().setFgColor(this.bgColor);
        this.txtF.setVisible(false);
        this.txtF.setTextSelectionEnabled(false);
        this.txtF.setCursorBlinkTimeOff(999999);
        this.txtF.setCursorPosition(0);
        this.txtF.setUIID("ContTrspar");
        this.txtF.getAllStyles().setPaddingUnit(1);
        this.txtF.getAllStyles().setPadding(0, 150, 0, 150);
        Container container = new Container(new LayeredLayout());
        Container container2 = new Container(new LayeredLayout());
        Container container3 = new Container(new FlowLayout());
        new FlowLayout(4, 4).setFillRows(false);
        Container container4 = new Container(new GridLayout(1, 4));
        for (Label label : this.labels) {
            Container container5 = new Container(BoxLayout.y());
            container5.getAllStyles().setPaddingUnit(1);
            container5.getAllStyles().setPadding(1, 1, 4, 4);
            container5.add(label).add(new SepContainer());
            container4.add(container5);
        }
        FlowLayout flowLayout = new FlowLayout(4, 4);
        flowLayout.setFillRows(true);
        Container container6 = new Container(flowLayout);
        container3.setPreferredH(0);
        container3.setPreferredW(0);
        container3.setHeight(0);
        container3.setWidth(0);
        container3.add(this.txtF);
        container2.add(container3);
        container6.getAllStyles().setBgColor(this.bgColor);
        container6.getAllStyles().setBgTransparency(255);
        container6.add(FlowLayout.encloseIn(container4));
        container.add(container2).add(container6);
        container.setLeadComponent(this.txtF);
        this.txtF.addDataChangedListener(new 1());
        startEditing(this.flagOpen);
        return container;
    }

    class 1 implements DataChangedListener {
        1() {
        }

        public void dataChanged(int i, int i2) {
            B3GCIHEcode.this.txtF.setTextSelectionEnabled(false);
            B3GCIHEcode.this.txtF.setCursorBlinkTimeOff(999999);
            int length = B3GCIHEcode.this.txtF.getText().length();
            if (B3GCIHEcode.this.oldLenght >= length) {
                for (int i3 = 3; i3 >= length; i3--) {
                    B3GCIHEcode.this.labels[i3].setIcon(null);
                    B3GCIHEcode.this.labels[i3].setText("   ");
                }
            } else if (length < 5 && length > 0) {
                int i4 = length - 1;
                B3GCIHEcode.this.labels[i4].setText("");
                B3GCIHEcode.this.labels[i4].setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("circle-xxl.png"));
            }
            B3GCIHEcode b3GCIHEcode = B3GCIHEcode.this;
            b3GCIHEcode.oldLenght = b3GCIHEcode.txtF.getText().length();
            if (B3GCIHEcode.this.txtF.getText().length() == 4) {
                B3GCIHEcode.this.stopEditing();
            }
        }
    }

    public Component getComponent1() {
        TextField textField = new TextField();
        this.txtF = textField;
        textField.setConstraint(65538);
        this.txtF.setMaxSize(4);
        this.txtF.getAllStyles().setFgColor(this.bgColor);
        this.txtF.setVisible(false);
        this.txtF.setTextSelectionEnabled(false);
        this.txtF.setCursorBlinkTimeOff(999999);
        this.txtF.setCursorPosition(0);
        this.txtF.setUIID("ContTrspar");
        Container container = new Container(new LayeredLayout());
        Container container2 = new Container(new LayeredLayout());
        Container container3 = new Container(new FlowLayout());
        new FlowLayout(4, 4).setFillRows(false);
        Container container4 = new Container(new GridLayout(1, 4));
        for (Label label : this.labels) {
            Container container5 = new Container(BoxLayout.y());
            container5.getAllStyles().setPaddingUnit(1);
            container5.getAllStyles().setPadding(1, 1, 4, 4);
            container5.add(label).add(new SepContainer());
            container4.add(container5);
        }
        FlowLayout flowLayout = new FlowLayout(4, 4);
        flowLayout.setFillRows(true);
        Container container6 = new Container(flowLayout);
        container3.setPreferredH(0);
        container3.setPreferredW(0);
        container3.setHeight(0);
        container3.setWidth(0);
        container3.add(this.txtF);
        container2.add(container3);
        container6.getAllStyles().setBgColor(this.bgColor);
        container6.getAllStyles().setBgTransparency(255);
        container6.add(FlowLayout.encloseIn(container4));
        container.add(container2).add(container6);
        container.setLeadComponent(this.txtF);
        this.txtF.addDataChangedListener(new 2());
        startEditing(this.flagOpen);
        return container;
    }

    class 2 implements DataChangedListener {
        2() {
        }

        public void dataChanged(int i, int i2) {
            B3GCIHEcode.this.txtF.setTextSelectionEnabled(false);
            B3GCIHEcode.this.txtF.setCursorBlinkTimeOff(999999);
            int length = B3GCIHEcode.this.txtF.getText().length();
            if (B3GCIHEcode.this.oldLenght >= length) {
                for (int i3 = 3; i3 >= length; i3--) {
                    B3GCIHEcode.this.labels[i3].setIcon(null);
                    B3GCIHEcode.this.labels[i3].setText("   ");
                }
            } else if (length < 5 && length > 0) {
                int i4 = length - 1;
                B3GCIHEcode.this.labels[i4].setText("");
                B3GCIHEcode.this.labels[i4].setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("circle-xxl.png"));
            }
            B3GCIHEcode b3GCIHEcode = B3GCIHEcode.this;
            b3GCIHEcode.oldLenght = b3GCIHEcode.txtF.getText().length();
        }
    }

    public class SepContainer extends Container {
        public SepContainer() {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setFillRows(true);
            setLayout(flowLayout);
            getAllStyles().setPaddingUnit(1);
            getAllStyles().setPadding(0.15f, 0.15f, 6.0f, 6.0f);
            getAllStyles().setMarginUnit(1);
            getAllStyles().setMargin(0.0f, 1.0f, 0.5f, 0.5f);
            getAllStyles().setBgColor(B3GCIHEcode.this.sepColor);
            getAllStyles().setBgTransparency(255);
        }
    }

    public class SepContainer1 extends Container {
        public SepContainer1() {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setFillRows(true);
            setLayout(flowLayout);
            getAllStyles().setPaddingUnit(1);
            getAllStyles().setPadding(0.15f, 0.15f, 6.0f, 6.0f);
            getAllStyles().setMarginUnit(1);
            getAllStyles().setMargin(0.0f, 1.0f, 0.5f, 0.5f);
            getAllStyles().setBgColor(B3GCIHEcode.this.sepColor1);
            getAllStyles().setBgTransparency(255);
        }
    }

    private void startEditing(boolean z) {
        if (z) {
            new UITimer(new B3GCIHEcode$$ExternalSyntheticLambda0(this)).schedule(500, false, CihMBanking.sesPMTR.getCurrentUiManager().currentForm);
        }
    }

    /* synthetic */ void lambda$startEditing$0$com-b3g-ui-B3GCIHEcode() {
        this.txtF.startEditingAsync();
    }

    public void stopEditing() {
        this.txtF.stopEditing();
    }
}
