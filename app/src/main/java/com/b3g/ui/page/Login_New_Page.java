package com.b3g.ui.page;

import com.b3g.services.B3gService;
import com.b3g.ui.B3GTabs;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.TabTitleItemModele1;
import com.b3g.ui.page.cih.Module.LoginAcivateAccount;
import com.b3g.ui.page.cih.Module.LoginAuthentication;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Login_New_Page extends B3GPage {
    public ArrayList listTabItems;

    public Login_New_Page(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "LoginNew");
        createContainer.getAllStyles().setBgColor(16777215);
        createContainer.getAllStyles().setBgTransparency(255);
        Container container = (Container) uIBuilder.findByName("topCnt", createContainer);
        Container container2 = (Container) uIBuilder.findByName("TabsCnt", createContainer);
        Container container3 = (Container) uIBuilder.findByName("bottomCnt", createContainer);
        container.setPreferredSize(new Dimension(Display.getInstance().getDisplayWidth(), (Display.getInstance().getDisplayHeight() * 30) / 100));
        container2.setPreferredSize(new Dimension(Display.getInstance().getDisplayWidth(), (Display.getInstance().getDisplayHeight() * 60) / 100));
        container3.setPreferredSize(new Dimension(Display.getInstance().getDisplayWidth(), (Display.getInstance().getDisplayHeight() * 10) / 100));
        Button button = (Button) uIBuilder.findByName("localiserGAB", createContainer);
        TabTitleItemModele1 tabTitleItemModele1 = new TabTitleItemModele1();
        tabTitleItemModele1.title = "Authentication";
        try {
            tabTitleItemModele1.tabCnt = new LoginAuthentication(this.uiManager, null);
        } catch (IOException unused) {
        }
        TabTitleItemModele1 tabTitleItemModele12 = new TabTitleItemModele1();
        tabTitleItemModele12.title = "Activer mon compte";
        tabTitleItemModele12.tabCnt = new LoginAcivateAccount(this.uiManager);
        ArrayList arrayList = new ArrayList();
        this.listTabItems = arrayList;
        arrayList.add(tabTitleItemModele1);
        this.listTabItems.add(tabTitleItemModele12);
        container2.addComponent(new B3GTabs(this.listTabItems).drawTabs(0));
        button.addActionListener(new 1());
        return createContainer;
    }

    class 1 implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
        }

        1() {
        }
    }
}
