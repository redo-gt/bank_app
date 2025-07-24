package com.b3g.ui.page.cih.Module;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GRadioWithIcon;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.B3GPage;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class LangueOffligne extends B3GPage {
    UIBuilder uib = new UIBuilder();

    public LangueOffligne(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public Container GetPageContainer() {
        int i = 2;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setScrollableY(false);
        this.uiManager.CurrentPageId = 99;
        CihMBanking.sesPMTR.setCurrentUiManager(this.uiManager);
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "changeOperator");
        createContainer.setUIID("ContainerTrspBottom1");
        Container container = (Container) uIBuilder.findByName("operatorRadioCnt", createContainer);
        Label label = (Label) uIBuilder.findByName("title", createContainer);
        SpanLabel spanLabel = (SpanLabel) uIBuilder.findByName("messageLbl", createContainer);
        label.setUIID("Lbl_exo_semiBold_18");
        spanLabel.setTextUIID("Lbl_lato_bold_13");
        Button button = (Button) uIBuilder.findByName("validBtn", createContainer);
        button.setUIID("sendBtnNew");
        B3GRadioWithIcon b3GRadioWithIcon = new B3GRadioWithIcon("", new BoxLayout(2));
        b3GRadioWithIcon.setUIID("TransparentContainer");
        b3GRadioWithIcon.addItem1("العربية     ", "ma.png", "TransparentContainer1");
        b3GRadioWithIcon.addItem1("  English  ", "en.png", "TransparentContainer1");
        b3GRadioWithIcon.addItem1("  Español  ", "es.png", "TransparentContainer1");
        b3GRadioWithIcon.addItem1("  Français  ", "fr.png", "TransparentContainer1");
        String str = Preferences.get("CihMobileLang", "Français");
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2144569262:
                if (str.equals("العربية")) {
                    c = 0;
                    break;
                }
                break;
            case -1827230247:
                if (str.equals("الفرنسية")) {
                    c = 1;
                    break;
                }
                break;
            case -1575530339:
                if (str.equals("Français")) {
                    c = 2;
                    break;
                }
                break;
            case 0:
                if (str.equals("")) {
                    c = 3;
                    break;
                }
                break;
            case 3121:
                if (str.equals("ar")) {
                    c = 4;
                    break;
                }
                break;
            case 3241:
                if (str.equals("en")) {
                    c = 5;
                    break;
                }
                break;
            case 3246:
                if (str.equals("es")) {
                    c = 6;
                    break;
                }
                break;
            case 3276:
                if (str.equals("fr")) {
                    c = 7;
                    break;
                }
                break;
            case 60895824:
                if (str.equals("English")) {
                    c = '\b';
                    break;
                }
                break;
            case 63521395:
                if (str.equals("Arabe")) {
                    c = '\t';
                    break;
                }
                break;
            case 212156143:
                if (str.equals("Español")) {
                    c = '\n';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 4:
            case '\t':
            default:
                i = 0;
                break;
            case 1:
            case 2:
            case 3:
            case 7:
                i = 3;
                break;
            case 5:
            case '\b':
                i = 1;
                break;
            case 6:
            case '\n':
                break;
        }
        b3GRadioWithIcon.setSelectedIndex(i);
        if (Preferences.get("connexionState", "first").equals("first")) {
            CihMBanking.sesPMTR.setIsFirst(false);
        } else {
            CihMBanking.sesPMTR.setIsFirst(true);
        }
        button.addActionListener(new 1(b3GRadioWithIcon));
        b3GRadioWithIcon.setRadioUIID("TransparentContainer");
        Container GetContainer = b3GRadioWithIcon.GetContainer();
        GetContainer.setUIID("TransparentContainer");
        container.add(GetContainer);
        this.uiManager.currentForm.setBackCommand(new 2("Back"));
        createContainer.setPreferredH((Display.getInstance().getDisplayHeight() * 50) / 100);
        this.thisContainer.setPreferredH(createContainer.getPreferredH());
        this.thisContainer.setPreferredW(createContainer.getPreferredW());
        this.thisContainer.add(createContainer);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ B3GRadioWithIcon val$b3GRadioLg;

        1(B3GRadioWithIcon b3GRadioWithIcon) {
            this.val$b3GRadioLg = b3GRadioWithIcon;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0031 A[PHI: r0
          0x0031: PHI (r0v10 java.lang.String) = (r0v2 java.lang.String), (r0v3 java.lang.String), (r0v4 java.lang.String), (r0v5 java.lang.String) binds: [B:9:0x002f, B:18:0x003a, B:20:0x0043, B:22:0x004c] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void actionPerformed(com.codename1.ui.events.ActionEvent r4) {
            /*
                r3 = this;
                java.lang.String r4 = ""
                com.b3g.ui.B3GRadioWithIcon r0 = r3.val$b3GRadioLg
                boolean r0 = r0.isClear()
                if (r0 == 0) goto L1d
                com.b3g.cih.online.SessionParameter r4 = com.b3g.cih.online.CihMBanking.sesPMTR
                com.b3g.ui.B3GUiManager r4 = r4.getCurrentUiManager()
                com.b3g.ui.page.cih.Module.LangueOffligne r0 = com.b3g.ui.page.cih.Module.LangueOffligne.this
                com.b3g.ui.B3GUiManager r0 = r0.uiManager
                userclasses.StateMachine r0 = r0.stateMachine
                java.lang.String r1 = "Merci de choisir votre langue"
                r2 = 0
                r4.ShowMessageTransaction(r0, r1, r2)
                goto L7a
            L1d:
                com.b3g.ui.B3GRadioWithIcon r0 = r3.val$b3GRadioLg     // Catch: java.lang.Exception -> L29
                java.lang.String r0 = r0.GetSelectedText()     // Catch: java.lang.Exception -> L29
                java.lang.String r1 = " "
                java.lang.String r4 = com.codename1.util.StringUtil.replaceAll(r0, r1, r4)     // Catch: java.lang.Exception -> L29
            L29:
                java.lang.String r0 = "Français"
                boolean r1 = r4.equals(r0)
                if (r1 == 0) goto L33
            L31:
                r4 = r0
                goto L4f
            L33:
                java.lang.String r0 = "العربية"
                boolean r1 = r4.equals(r0)
                if (r1 == 0) goto L3d
                goto L31
            L3d:
                java.lang.String r0 = "English"
                boolean r1 = r4.equals(r0)
                if (r1 == 0) goto L46
                goto L31
            L46:
                java.lang.String r0 = "Español"
                boolean r1 = r4.equals(r0)
                if (r1 == 0) goto L4f
                goto L31
            L4f:
                java.lang.String r0 = "CihMobileLang"
                com.codename1.io.Preferences.set(r0, r4)
                com.b3g.cih.online.CihMBanking.setLang(r4)
                java.lang.String r4 = "connexionState"
                java.lang.String r0 = "first"
                java.lang.String r4 = com.codename1.io.Preferences.get(r4, r0)
                boolean r4 = r4.equals(r0)
                if (r4 == 0) goto L6c
                com.b3g.cih.online.SessionParameter r4 = com.b3g.cih.online.CihMBanking.sesPMTR
                r0 = 0
                r4.setIsFirst(r0)
                goto L72
            L6c:
                com.b3g.cih.online.SessionParameter r4 = com.b3g.cih.online.CihMBanking.sesPMTR
                r0 = 1
                r4.setIsFirst(r0)
            L72:
                com.b3g.cih.online.CihMBanking r4 = new com.b3g.cih.online.CihMBanking
                r4.<init>()
                r4.showSplashScreen()
            L7a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.cih.Module.LangueOffligne.1.actionPerformed(com.codename1.ui.events.ActionEvent):void");
        }
    }

    class 2 extends Command {
        2(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LangueOffligne.this.uiManager.GoBackOffligne();
        }
    }
}
