package com.b3g.ui;

import com.b3g.tools.B3GNativeTextFieldCall;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3GNativeTextField {
    TextField CN1TxtF;
    B3GNativeTextFieldCall peer;

    public B3GNativeTextField() {
        if (!Display.getInstance().isSimulator()) {
            this.peer = (B3GNativeTextFieldCall) NativeLookup.create(B3GNativeTextFieldCall.class);
            return;
        }
        TextField textField = new TextField();
        this.CN1TxtF = textField;
        textField.setUIID("txtF_TextSP");
    }

    public Component getView() {
        if (!Display.getInstance().isSimulator()) {
            return this.peer.getView();
        }
        return this.CN1TxtF;
    }

    public void setText(String str) {
        if (!Display.getInstance().isSimulator() && Display.getInstance().getPlatformName().equals("and")) {
            this.peer.setText(str);
        } else {
            this.CN1TxtF.setText(str);
        }
    }

    public String getText() {
        if (!Display.getInstance().isSimulator() && Display.getInstance().getPlatformName().equals("and")) {
            return this.peer.getText();
        }
        return this.CN1TxtF.getText();
    }

    public void setHint(String str) {
        if (!Display.getInstance().isSimulator() && Display.getInstance().getPlatformName().equals("and")) {
            this.peer.setHint(str);
        } else {
            this.CN1TxtF.setHint(str);
        }
    }

    public String getHint() {
        if (!Display.getInstance().isSimulator()) {
            Display.getInstance().getPlatformName().equals("and");
        }
        return "";
    }

    public void setConstraint(int i) {
        if (!Display.getInstance().isSimulator() && Display.getInstance().getPlatformName().equals("and")) {
            this.peer.setConstraint(i);
            return;
        }
        if (i == 1) {
            this.CN1TxtF.setConstraint(2);
        } else if (i == 2) {
            this.CN1TxtF.setConstraint(0);
        } else {
            if (i != 3) {
                return;
            }
            this.CN1TxtF.setConstraint(65536);
        }
    }

    public void setMask(String str) {
        if (Display.getInstance().isSimulator() || !Display.getInstance().getPlatformName().equals("and")) {
            return;
        }
        this.peer.setMask(str);
    }

    public void setPhoneMask() {
        if (Display.getInstance().isSimulator() || !Display.getInstance().getPlatformName().equals("and")) {
            return;
        }
        this.peer.setPhoneMask();
    }
}
