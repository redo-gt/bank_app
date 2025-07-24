package com.b3g.ui;

import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3gLoginTextBox extends Container {
    public final Container iconContainer;
    public final TextField textField;

    public B3gLoginTextBox(String str) {
        TableLayout tableLayout = new TableLayout(1, 2);
        setLayout(tableLayout);
        setFocusable(false);
        setUIID("TextFieldContainer");
        Container container = new Container(new BoxLayout(1));
        this.iconContainer = container;
        container.setFocusable(false);
        if (str == "Password") {
            container.setUIID("LoginPasswordIcon");
        } else {
            container.setUIID("LoginPasswordIcon");
        }
        TableLayout.Constraint createConstraint = tableLayout.createConstraint(0, 0);
        createConstraint.setWidthPercentage(10);
        addComponent(createConstraint, container);
        TextField textField = new TextField();
        this.textField = textField;
        textField.setFocusable(false);
        if (str == "Password") {
            textField.setConstraint(65536);
        }
        textField.setUIID("TextFieldDefault");
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint(0, 1);
        createConstraint2.setWidthPercentage(88);
        createConstraint2.setHeightPercentage(94);
        createConstraint2.setVerticalAlign(4);
        addComponent(createConstraint2, textField);
    }
}
