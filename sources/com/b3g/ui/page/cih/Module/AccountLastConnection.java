package com.b3g.ui.page.cih.Module;

import com.b3g.cih.online.CihMBanking;
import com.b3g.ui.B3GTextField;
import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AccountLastConnection implements Externalizable {
    public String lastConnectionDate;
    public int occurrence;
    public String radical;

    public String getObjectId() {
        return "AccountLastConnection";
    }

    public int getVersion() {
        return 1;
    }

    public AccountLastConnection() {
    }

    public AccountLastConnection(String str, String str2, int i) {
        this.radical = str;
        this.lastConnectionDate = str2;
        this.occurrence = i;
    }

    public AccountLastConnection(String str, String str2) {
        this.radical = str;
        this.lastConnectionDate = str2;
    }

    public Container drawView(Dialog dialog, TextField textField, B3GTextField b3GTextField, Button button) {
        Container container = new Container();
        container.setLayout(BoxLayout.y());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "multipeAccountRenderer");
        Label label = (Label) uIBuilder.findByName("radicalLbl", createContainer);
        Label label2 = (Label) uIBuilder.findByName("lastCnxDateLbl", createContainer);
        label.setText(new StringBuilder("*****").append(this.radical.substring(r6.length() - 2, this.radical.length())).toString());
        label2.setText(this.lastConnectionDate);
        ((Button) uIBuilder.findByName("selectBtn", createContainer)).addActionListener(new 1(dialog, textField, b3GTextField, button));
        container.add(createContainer);
        return container;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ Button val$fakeBtn1;
        final /* synthetic */ B3GTextField val$passTxt;
        final /* synthetic */ TextField val$txtF;

        1(Dialog dialog, TextField textField, B3GTextField b3GTextField, Button button) {
            this.val$d = dialog;
            this.val$txtF = textField;
            this.val$passTxt = b3GTextField;
            this.val$fakeBtn1 = button;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            this.val$txtF.setText("*****" + AccountLastConnection.this.radical.substring(AccountLastConnection.this.radical.length() - 2, AccountLastConnection.this.radical.length()));
            CihMBanking.sesPMTR.setStoredRadical(AccountLastConnection.this.radical);
            this.val$passTxt.setIsTextFieldFocused(true);
            this.val$fakeBtn1.setIcon(CihMBanking.theme.getImage("PasswordFocus.png"));
        }
    }

    public void externalize(DataOutputStream dataOutputStream) throws IOException {
        Util.writeUTF(this.radical, dataOutputStream);
        Util.writeUTF(this.lastConnectionDate, dataOutputStream);
        dataOutputStream.writeInt(this.occurrence);
    }

    public void internalize(int i, DataInputStream dataInputStream) throws IOException {
        this.radical = Util.readUTF(dataInputStream);
        this.lastConnectionDate = Util.readUTF(dataInputStream);
        this.occurrence = dataInputStream.readInt();
    }
}
