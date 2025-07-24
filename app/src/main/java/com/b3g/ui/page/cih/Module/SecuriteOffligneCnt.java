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
public class SecuriteOffligneCnt extends B3GPage {
    UIBuilder uib = new UIBuilder();

    public SecuriteOffligneCnt(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.uiManager.CurrentPageId = 85;
        Container createContainer = this.uib.createContainer("/cihv3", "SecuritPopupOffligne");
        Container container = (Container) this.uib.findByName("Container12", createContainer);
        Container container2 = (Container) this.uib.findByName("cntBody", createContainer);
        Button button = (Button) this.uib.findByName("FaceBookBtn", createContainer);
        Button button2 = (Button) this.uib.findByName("linkedinBtn", createContainer);
        Button button3 = (Button) this.uib.findByName("youtubeBtn", createContainer);
        Button button4 = (Button) this.uib.findByName("twitterBtn", createContainer);
        NativeCall nativeCall = (NativeCall) NativeLookup.create(NativeCall.class);
        createContainer.setPreferredH(this.uiManager.stateMachine.findTopCnt1(this.uiManager.currentForm).getPreferredH());
        button.addActionListener(new 1(nativeCall));
        button2.addActionListener(new 2(nativeCall));
        button3.addActionListener(new 3(nativeCall));
        button4.addActionListener(new 4(nativeCall));
        this.uiManager.currentForm.setBackCommand(new 5("Back"));
        if (Display.getInstance().getDisplayHeight() < 1334) {
            container.setScrollableY(false);
            container2.setScrollableY(true);
        }
        TableLayout tableLayout = new TableLayout(3, 1);
        Container container3 = new Container(tableLayout);
        container3.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(8), new Container(new FlowLayout())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(87), createContainer);
        this.thisContainer.add(container3);
        createContainer.setUIID("ContainerTrspBottom1");
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ NativeCall val$nativeCall;

        1(NativeCall nativeCall) {
            this.val$nativeCall = nativeCall;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$nativeCall.callFacebookPage("https://www.facebook.com/cihbank/");
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ NativeCall val$nativeCall;

        2(NativeCall nativeCall) {
            this.val$nativeCall = nativeCall;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$nativeCall.callLinkedinPage(" ");
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ NativeCall val$nativeCall;

        3(NativeCall nativeCall) {
            this.val$nativeCall = nativeCall;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$nativeCall.callYoutubePage(" ");
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ NativeCall val$nativeCall;

        4(NativeCall nativeCall) {
            this.val$nativeCall = nativeCall;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$nativeCall.callTwitterPage(" ");
        }
    }

    class 5 extends Command {
        5(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            SecuriteOffligneCnt.this.uiManager.GoBackOffligne();
        }
    }
}
