package com.b3g.ui.page.cih.Module;

import com.b3g.services.B3gService;
import com.b3g.tools.NativeCall;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.B3GPage;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ApplicationPageOffligne extends B3GPage {
    UIBuilder uib = new UIBuilder();

    public ApplicationPageOffligne(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.uiManager.CurrentPageId = 84;
        Container createContainer = this.uib.createContainer("/cihv3", "AppOffligneCnt");
        Button button = (Button) this.uib.findByName("cihBankSiteBtn", createContainer);
        Button button2 = (Button) this.uib.findByName("cihOnlineBtn", createContainer);
        Button button3 = (Button) this.uib.findByName("ouvrirCmpteBtn", createContainer);
        Button button4 = (Button) this.uib.findByName("code30btn", createContainer);
        Button button5 = (Button) this.uib.findByName("code30studioBtn", createContainer);
        Button button6 = (Button) this.uib.findByName("wePayBtn", createContainer);
        Button button7 = (Button) this.uib.findByName("code18Btn", createContainer);
        Button button8 = (Button) this.uib.findByName("cihEntreprBtn", createContainer);
        Button button9 = (Button) this.uib.findByName("CihEntrprBtn", createContainer);
        Container container = (Container) this.uib.findByName("glbCnt", createContainer);
        Container container2 = (Container) this.uib.findByName("cntLiens", createContainer);
        container.setScrollableY(false);
        container.setScrollVisible(false);
        container2.setScrollVisible(false);
        button.addActionListener(new 1());
        button2.addActionListener(new 2());
        button9.addActionListener(new 3());
        button3.addActionListener(new 4());
        button4.addActionListener(new 5());
        button5.addActionListener(new 6());
        button6.addActionListener(new 7());
        button7.addActionListener(new 8());
        button8.addActionListener(new 9());
        this.uiManager.currentForm.setBackCommand(new 10("Back"));
        TableLayout tableLayout = new TableLayout(3, 1);
        Container container3 = new Container(tableLayout);
        container3.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(8), new Container(new FlowLayout())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(87), createContainer);
        this.thisContainer.add(container3);
        createContainer.setUIID("ContainerTrspBottom1");
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().execute("http://www.cihbank.ma/");
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().execute("https://www.cihnet.co.ma/");
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().execute("https://entreprises.cihonline.ma/adriaClient/login/auth");
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ((NativeCall) NativeLookup.create(NativeCall.class)).callEERActivity();
        }
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().execute("http://www.code30.ma/");
        }
    }

    class 6 implements ActionListener {
        6() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().execute("http://www.studiocode30.ma/");
        }
    }

    class 7 implements ActionListener {
        7() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                Display.getInstance().execute("https://itunes.apple.com/fr/app/we-pay/id1359858437?mt=8");
            } else if (Display.getInstance().getPlatformName().equals("and")) {
                Display.getInstance().execute("https://play.google.com/store/apps/details?id=com.b3g.cih.wepay");
            } else {
                Display.getInstance().execute("https://play.google.com/store/apps/details?id=com.b3g.cih.wepay");
            }
        }
    }

    class 8 implements ActionListener {
        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                Display.getInstance().execute("https://itunes.apple.com/fr/app/cih-mobile-junior/id1304307434?mt=8");
            } else if (Display.getInstance().getPlatformName().equals("and")) {
                Display.getInstance().execute("https://play.google.com/store/apps/details?id=com.b3g.cih.online.junior");
            } else {
                Display.getInstance().execute("https://play.google.com/store/apps/details?id=com.b3g.cih.online.junior");
            }
        }
    }

    class 9 implements ActionListener {
        9() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                Display.getInstance().execute("https://itunes.apple.com/fr/app/cih-mobile-entreprises/id973989574?mt=8");
            } else if (Display.getInstance().getPlatformName().equals("and")) {
                Display.getInstance().execute("https://play.google.com/store/apps/details?id=com.adria.cih.entreprises");
            } else {
                Display.getInstance().execute("https://play.google.com/store/apps/details?id=com.adria.cih.entreprises");
            }
        }
    }

    class 10 extends Command {
        10(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ApplicationPageOffligne.this.uiManager.GoBackOffligne();
        }
    }
}
