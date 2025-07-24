package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.VirtualKeyBoard;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class LoginPage extends B3GPage {
    private VirtualKeyBoard virtualKeyBoard;
    int UserNameTextBoxIndex = 0;
    int PasswordTextBoxIndex = 0;

    public LoginPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        this.virtualKeyBoard = new VirtualKeyBoard();
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new GridLayout(2, 1));
        this.thisContainer.setUIID("Form");
        Container container = new Container();
        container.setUIID("Container");
        TableLayout tableLayout = new TableLayout(2, 1);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint(0, 0);
        createConstraint.setHeightPercentage(60);
        createConstraint.setWidthPercentage(100);
        container.setLayout(tableLayout);
        createConstraint.setVerticalAlign(2);
        Component container2 = new Container();
        container2.setUIID("cntNEWLogo");
        container.addComponent(createConstraint, container2);
        Container container3 = new Container();
        container3.setUIID("Container");
        TableLayout tableLayout2 = new TableLayout(2, 1);
        TableLayout.Constraint createConstraint2 = tableLayout2.createConstraint(0, 0);
        createConstraint2.setHeightPercentage(20);
        createConstraint2.setWidthPercentage(100);
        container3.setLayout(tableLayout2);
        container.addComponent(createConstraint2, container3);
        Component container4 = new Container();
        container4.setUIID("Container");
        container.addComponent(container4);
        this.thisContainer.addComponent(container);
        return this.thisContainer;
    }
}
