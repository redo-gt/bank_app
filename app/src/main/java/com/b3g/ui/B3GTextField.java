package com.b3g.ui;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.events.FocusListener;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3GTextField {
    private boolean isTextFieldFocused = false;
    private boolean ready;
    private TextField textField;
    private Container txtFieldCnt;

    static /* synthetic */ boolean access$002(B3GTextField b3GTextField, boolean z) {
        b3GTextField.isTextFieldFocused = z;
        return z;
    }

    public B3GTextField(TextField textField) {
        this.textField = textField;
    }

    public B3GTextField(TextField textField, Container container) {
        this.textField = textField;
        this.txtFieldCnt = container;
        container.addFocusListener(new 1(textField));
    }

    class 1 implements FocusListener {
        final /* synthetic */ TextField val$textField;

        1(TextField textField) {
            this.val$textField = textField;
        }

        public void focusGained(Component component) {
            B3GTextField.access$002(B3GTextField.this, true);
            this.val$textField.clear();
            B3GTextField.this.setReady(true);
        }

        public void focusLost(Component component) {
            B3GTextField.access$002(B3GTextField.this, false);
        }
    }

    public Container getTxtFieldCnt() {
        return this.txtFieldCnt;
    }

    public void setTxtFieldCnt(Container container) {
        this.txtFieldCnt = container;
    }

    public boolean isReady() {
        return this.ready;
    }

    public void setReady(boolean z) {
        this.ready = z;
    }

    public TextField getTextField() {
        return this.textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public boolean isIsTextFieldFocused() {
        return this.isTextFieldFocused;
    }

    public void setIsTextFieldFocused(boolean z) {
        this.isTextFieldFocused = z;
    }
}
