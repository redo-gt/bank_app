package com.b3g.ui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import java.util.ArrayList;
import java.util.Random;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class VirtualKeyBoard extends Container {
    TextField activeTextField;
    Button keyBoardEnter;
    String keyboardType;

    static /* synthetic */ void access$000(VirtualKeyBoard virtualKeyBoard, TextField textField) {
        virtualKeyBoard.KeyBoardDelete(textField);
    }

    public TextField getActiveTextField() {
        return this.activeTextField;
    }

    public void setActiveTextField(TextField textField) {
        this.activeTextField = textField;
    }

    public VirtualKeyBoard() {
        setUIID("CntRemoveMargin");
        this.keyBoardEnter = null;
        DrawRandomKeyBoard(3);
    }

    public void DrawRandomKeyBoard(Integer num) {
        Container container;
        setLayout(new GridLayout(num.intValue(), 1));
        ArrayList arrayList = new ArrayList();
        ArrayList GetRandomIntArray = GetRandomIntArray(10);
        for (Integer num2 = 0; num2.intValue() < 10; num2 = Integer.valueOf(num2.intValue() + 1)) {
            Button button = new Button(((Integer) GetRandomIntArray.get(num2.intValue())).toString());
            button.setUIID("lg_BtnV2_emp");
            button.addActionListener(new 1(button));
            arrayList.add(button);
        }
        for (Integer num3 = 0; num3.intValue() < num.intValue(); num3 = Integer.valueOf(num3.intValue() + 1)) {
            if (num3.intValue() < num.intValue() - 1) {
                container = new Container(new TableLayout(1, 4));
                for (Integer num4 = 0; num4.intValue() < 4; num4 = Integer.valueOf(num4.intValue() + 1)) {
                    TableLayout.Constraint createConstraint = new TableLayout(1, 4).createConstraint(0, num4.intValue());
                    createConstraint.setWidthPercentage(25);
                    createConstraint.setHeightPercentage(100);
                    container.addComponent(createConstraint, (Component) arrayList.get((num3.intValue() * 4) + num4.intValue()));
                }
            } else {
                Container container2 = new Container(new TableLayout(1, 3));
                for (Integer num5 = 0; num5.intValue() < 2; num5 = Integer.valueOf(num5.intValue() + 1)) {
                    TableLayout.Constraint createConstraint2 = new TableLayout(1, 4).createConstraint(0, num5.intValue());
                    createConstraint2.setWidthPercentage(25);
                    createConstraint2.setHeightPercentage(100);
                    container2.addComponent(createConstraint2, (Component) arrayList.get((num3.intValue() * 4) + num5.intValue()));
                }
                if (this.keyBoardEnter == null) {
                    TableLayout.Constraint createConstraint3 = new TableLayout(1, 4).createConstraint(0, 2);
                    createConstraint3.setWidthPercentage(50);
                    createConstraint3.setHeightPercentage(100);
                    Button button2 = new Button("<");
                    button2.setUIID("lg_BtnV2_emp");
                    button2.addActionListener(new 2());
                    container2.addComponent(createConstraint3, button2);
                } else {
                    TableLayout.Constraint createConstraint4 = new TableLayout(1, 4).createConstraint(0, 2);
                    createConstraint4.setWidthPercentage(25);
                    createConstraint4.setHeightPercentage(100);
                    Button button3 = new Button("<");
                    button3.setUIID("lg_BtnV2_emp");
                    button3.addActionListener(new 3());
                    container2.addComponent(createConstraint4, button3);
                    TableLayout.Constraint createConstraint5 = new TableLayout(1, 4).createConstraint(0, 3);
                    createConstraint5.setWidthPercentage(25);
                    createConstraint5.setHeightPercentage(100);
                    this.keyBoardEnter.setUIID("lg_BtnV2_emp");
                    container2.addComponent(createConstraint5, this.keyBoardEnter);
                }
                container = container2;
            }
            container.setUIID("lg_BtnV2_emp");
            addComponent(container);
        }
    }

    class 1 implements ActionListener {
        final /* synthetic */ Button val$btn;

        1(Button button) {
            this.val$btn = button;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (VirtualKeyBoard.this.activeTextField == null) {
                return;
            }
            VirtualKeyBoard.this.activeTextField.setText(VirtualKeyBoard.this.activeTextField.getText() + this.val$btn.getText());
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (VirtualKeyBoard.this.activeTextField == null) {
                return;
            }
            VirtualKeyBoard virtualKeyBoard = VirtualKeyBoard.this;
            VirtualKeyBoard.access$000(virtualKeyBoard, virtualKeyBoard.activeTextField);
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (VirtualKeyBoard.this.activeTextField == null) {
                return;
            }
            VirtualKeyBoard virtualKeyBoard = VirtualKeyBoard.this;
            VirtualKeyBoard.access$000(virtualKeyBoard, virtualKeyBoard.activeTextField);
        }
    }

    private void KeyBoardDelete(TextField textField) {
        if (textField.getText().length() > 0) {
            textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
        } else {
            textField.setText(textField.getText());
        }
    }

    public ArrayList GetRandomIntArray(int i) {
        Random random = new Random();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 != 10) {
            Integer valueOf = Integer.valueOf(random.nextInt(i));
            if (!arrayList.contains(valueOf)) {
                arrayList.add(valueOf);
                i2++;
            }
        }
        return arrayList;
    }
}
