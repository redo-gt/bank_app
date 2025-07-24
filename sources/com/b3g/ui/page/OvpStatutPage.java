package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.OvpExecutionService;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.tools.NativeCall;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GUiManager;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.Base64;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OvpStatutPage extends B3GPage {
    public OvpExecutionService OES;
    UIBuilder uib = new UIBuilder();

    static /* synthetic */ Container access$000(OvpStatutPage ovpStatutPage, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i) {
        return ovpStatutPage.virementRecap(str, str2, str3, str4, str5, str6, str7, i);
    }

    public OvpStatutPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setUIID("Whitemargin_2_2_2_2");
        this.OES = (OvpExecutionService) this.service;
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(new LayeredLayout());
        Button button = new Button();
        container.setUIID("white_containermargin_2_2_1_1");
        Container container3 = new Container(new BoxLayout(2));
        Container container4 = new Container(new BoxLayout(2));
        Container container5 = new Container(new LayeredLayout());
        ShareButton shareButton = new ShareButton();
        button.setText("EDITER LE REÇU");
        button.setUIID("op_BtnOppositionValidationV2");
        container2.add(button);
        container2.setLeadComponent(button);
        shareButton.setText("PARTAGER");
        shareButton.setUIID("op_BtnOppositionValidationV2");
        container5.add(shareButton);
        Label label = new Label("PARTAGER");
        label.setUIID("sendBtn");
        container5.add(label);
        container5.setLeadComponent(shareButton);
        shareButton.addActionListener(new 1(shareButton));
        button.addActionListener(new 2());
        container4.add(container2);
        container4.add(container5);
        Label label2 = new Label("Virement en faveur de " + this.OES.getCreditedAccountLabel());
        label2.setUIID("crd_CardTitle");
        container3.add(label2);
        Container createContainer = this.uib.createContainer("/cihv3", "OvpStatutPage");
        Label label3 = (Label) this.uib.findByName("AccountLbl", createContainer);
        Label label4 = (Label) this.uib.findByName("lblOvpAmount", createContainer);
        Label label5 = (Label) this.uib.findByName("AccountBen", createContainer);
        Label label6 = (Label) this.uib.findByName("Motif", createContainer);
        label3.setText(this.OES.getDebitedAccount());
        label4.setText(this.OES.getMontant());
        label5.setText(this.OES.getCreditedAccountLabel());
        label6.setText(this.OES.getMotif());
        Container container6 = new Container(new FlowLayout(4, 4));
        Container container7 = new Container(new BoxLayout(2));
        Label label7 = new Label();
        label7.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("OvpConfirmed.png"));
        container7.add(label7);
        container6.setPreferredH(Display.getInstance().getDisplayHeight() - (createContainer.getPreferredH() * 2));
        container6.add(container7);
        container3.add(createContainer).add(container6);
        container.add("Center", container3);
        container.add("South", container4);
        this.thisContainer.add(container);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ ShareButton val$CreateEpargneBtn;

        1(ShareButton shareButton) {
            this.val$CreateEpargneBtn = shareButton;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            new Container();
            OvpStatutPage ovpStatutPage = OvpStatutPage.this;
            Container access$000 = OvpStatutPage.access$000(ovpStatutPage, StringTools.HideCardNumberVirementHistoricRecap(ovpStatutPage.OES.getDebitedAccount()), OvpStatutPage.this.OES.getCreditedAccountNumbel(), OvpStatutPage.this.OES.getMontant(), OvpStatutPage.this.OES.getOrderDate(), OvpStatutPage.this.OES.getCreditedAccountLabel(), OvpStatutPage.this.OES.getMotif(), CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, 0);
            access$000.setHeight(1400);
            access$000.setWidth(2000);
            Image createImage = Image.createImage(2000, 1400);
            access$000.revalidate();
            access$000.setVisible(true);
            access$000.paintComponent(createImage.getGraphics(), true);
            String str = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
            try {
                OutputStream openOutputStream = FileSystemStorage.getInstance().openOutputStream(str);
                try {
                    ImageIO.getImageIO().save(createImage, openOutputStream, "png", 1.0f);
                    if (openOutputStream != null) {
                        openOutputStream.close();
                    }
                } finally {
                }
            } catch (IOException unused) {
            }
            this.val$CreateEpargneBtn.setImageToShare(str, "image/png");
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
            ServiceResponse MTCSendReceiptProcess1 = ServiceManager.MTCSendReceiptProcess1(OvpStatutPage.this.OES.getMontant(), OvpStatutPage.this.OES.getCreditedAccountLabel(), OvpStatutPage.this.OES.getMotif(), OvpStatutPage.this.OES.getDebitedAccount(), "", OvpStatutPage.this.OES.getOrderDate(), OvpStatutPage.this.OES.getCreditedAccountNumbel());
            if (MTCSendReceiptProcess1.getStatusCode().equals("000")) {
                byte[] decode = Base64.decode(MTCSendReceiptProcess1.getStatusLabel().getBytes(), MTCSendReceiptProcess1.getStatusLabel().getBytes().length);
                if (!Display.getInstance().getPlatformName().equals("ios")) {
                    ((NativeCall) NativeLookup.create(NativeCall.class)).createPdfFromByteArray(decode, "Editerreçu" + OvpStatutPage.this.OES.getCreditedAccountNumbel() + ".pdf");
                    return;
                }
                String str = fileSystemStorage.getAppHomePath() + "Editer reçu" + OvpStatutPage.this.OES.getCreditedAccountNumbel() + ".pdf";
                try {
                    OutputStream openOutputStream = fileSystemStorage.openOutputStream(str);
                    try {
                        openOutputStream.write(decode);
                        if (openOutputStream != null) {
                            openOutputStream.close();
                        }
                    } finally {
                    }
                } catch (IOException unused) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(MTCSendReceiptProcess1.getStatusLabel()), null);
                }
                Display.getInstance().execute(str);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(MTCSendReceiptProcess1.getStatusLabel()), null);
        }
    }

    private void shareImage(Container container, int i, int i2, String str, ShareButton shareButton) {
        container.setHeight(i);
        container.setWidth(i2);
        Image createImage = Image.createImage(i2, i);
        container.revalidate();
        container.setVisible(true);
        container.paintComponent(createImage.getGraphics(), true);
        String str2 = FileSystemStorage.getInstance().getAppHomePath() + str;
        try {
            OutputStream openOutputStream = FileSystemStorage.getInstance().openOutputStream(str2);
            try {
                ImageIO.getImageIO().save(createImage, openOutputStream, "png", 1.0f);
                if (openOutputStream != null) {
                    openOutputStream.close();
                }
            } finally {
            }
        } catch (IOException unused) {
        }
        shareButton.setImageToShare(str2, "image/png");
    }

    private Container virementRecap(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i) {
        String str8;
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "VirementRecapNew");
        int indexOf = str4.indexOf(" ");
        if (indexOf != -1) {
            str8 = str4.substring(0, indexOf) + " - " + str4.substring(indexOf + 1);
            System.out.println(str8);
        } else {
            System.out.println("Invalid format");
            str8 = null;
        }
        ((Label) uIBuilder.findByName("EmetteurLabel", createContainer)).setText(str2);
        ((Label) uIBuilder.findByName("EmetteurNameLabel", createContainer)).setText(str7);
        ((Label) uIBuilder.findByName("MontantLabel", createContainer)).setText(str3);
        ((Label) uIBuilder.findByName("MotifLabel", createContainer)).setText(str6);
        ((SpanLabel) uIBuilder.findByName("BeneficiaireNameSpanLabel", createContainer)).setText(str5);
        if (str4 == null || str4.isEmpty()) {
            ((Container) uIBuilder.findByName("Date", createContainer)).setVisible(false);
        } else {
            ((Label) uIBuilder.findByName("DateLabel", createContainer)).setText(str8);
        }
        return createContainer;
    }
}
