package com.b3g.ui.page.cih.Module;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.tools.NativeCall;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.page.B3GPage;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class LoginPageGlb extends B3GPage {
    AccountLastConnection acc;
    String radicalClient;
    private UIBuilder uib = new UIBuilder();

    public LoginPageGlb(Object obj, B3gService b3gService, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = b3gService;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        TableLayout tableLayout = new TableLayout(2, 1);
        this.thisContainer = new Container(tableLayout);
        this.uiManager.CurrentPageId = 57;
        try {
            new Container(new BorderLayout());
            Button button = new Button("Paiement CIH PAY");
            button.setUIID("Btn_Cih_Pay_Blue");
            button.setGap(50);
            button.getAllStyles().setBgColor(CIHStyles.colorWhite);
            button.setIcon(CihMBanking.theme.getImage("CiyPay_icn.png"));
            Container roundedFilledButtonStyle = setRoundedFilledButtonStyle(button, CihMBanking.theme.getImage("CiyPay_icn.png"), CIHStyles.colorWhite);
            roundedFilledButtonStyle.getAllStyles().setPadding(1.6f, 1.6f, 1.0f, 1.0f);
            roundedFilledButtonStyle.getAllStyles().setMargin(0, 5, 14, 14);
            if (!Display.getInstance().isSimulator()) {
                if (!Display.getInstance().getPlatformName().equals("ios")) {
                    NativeCall nativeCall = (NativeCall) NativeLookup.create(NativeCall.class);
                    if (nativeCall.isSupported() && nativeCall.hasTokenizedCards()) {
                        this.thisContainer.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(85), new LoginAuthentication(this.uiManager, null)).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(15), roundedFilledButtonStyle);
                        roundedFilledButtonStyle.setHidden(false);
                    } else {
                        this.thisContainer.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(85), new LoginAuthentication(this.uiManager, null));
                        roundedFilledButtonStyle.setHidden(false);
                    }
                    button.addActionListener(new 1(nativeCall));
                } else {
                    this.thisContainer.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(85), new LoginAuthentication(this.uiManager, null));
                }
            } else {
                this.thisContainer.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(85), new LoginAuthentication(this.uiManager, null));
            }
        } catch (IOException unused) {
        }
        this.uiManager.stateMachine.findTopCnt(this.uiManager.currentForm).setPreferredH((Display.getInstance().getDisplayHeight() * 0) / 100);
        this.uiManager.stateMachine.findCenterCnt(this.uiManager.currentForm).setPreferredH((Display.getInstance().getDisplayHeight() * 100) / 100);
        this.uiManager.stateMachine.findTabsCnt(this.uiManager.currentForm).add(this.thisContainer);
        this.uiManager.currentForm.setBackCommand(new 2("Back"));
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ NativeCall val$pNativeCall;

        1(NativeCall nativeCall) {
            this.val$pNativeCall = nativeCall;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$pNativeCall.isSupported()) {
                this.val$pNativeCall.startCihPay();
            }
        }
    }

    class 2 extends Command {
        2(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LoginPageGlb.this.uiManager.GoBackOffligne();
        }
    }

    public static Container setRoundedFilledButtonStyle(Button button, Image image, int i) {
        Container container = new Container(new GridLayout(1, 1));
        Style allStyles = button.getAllStyles();
        allStyles.setPaddingUnit(CIHStyles.b);
        allStyles.setFont(CIHStyles.create_font("exo20medium", "nativ:MainBold", Float.valueOf(3.0f), 1));
        allStyles.setBorder(RoundRectBorder.create().cornerRadius(2.0f));
        allStyles.setAlignment(4);
        allStyles.setBgColor(i);
        allStyles.setBgTransparency(255);
        if (image != null) {
            button.setIcon(image);
            button.getAllStyles().setMarginUnit(CIHStyles.b);
            button.getAllStyles().setMargin(0.0f, 0.0f, 1.0f, 0.0f);
        }
        container.add(button);
        container.setLeadComponent(button);
        return container;
    }
}
