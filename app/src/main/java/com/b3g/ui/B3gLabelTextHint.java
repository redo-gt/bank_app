package com.b3g.ui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3gLabelTextHint extends Container {
    String Hint;
    public TextField textField;

    public B3gLabelTextHint(String str) {
        this.Hint = str;
        setLayout(new BoxLayout(2));
        TextField textField = new TextField();
        this.textField = textField;
        textField.setHint(this.Hint);
        this.textField.setUIID("medium_black_lbl");
        Container container = new Container(new BoxLayout(2));
        container.setUIID("greyCnt");
        add(new FloatingHint(this.textField));
        addComponent(container);
    }

    public String getText() {
        return this.textField.getText();
    }
}
