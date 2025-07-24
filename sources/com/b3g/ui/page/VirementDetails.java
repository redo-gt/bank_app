package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.ServiceManager;
import com.b3g.services.TransactionResume;
import com.b3g.services.TransferHistoric;
import com.b3g.tools.DataTools;
import com.b3g.tools.NativeCall;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
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
public class VirementDetails extends B3GPage {
    Button ArrtBtn;
    SpanLabel DescSpnLbl1;
    TransferHistoric Ovp;
    public TransferHistoric TH;
    Label Title1;
    private String VnewaliasText;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();

    static /* synthetic */ Container access$000(VirementDetails virementDetails, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i) {
        return virementDetails.virementRecap(str, str2, str3, str4, str5, str6, str7, i);
    }

    static /* synthetic */ String access$100(VirementDetails virementDetails) {
        return virementDetails.VnewaliasText;
    }

    static /* synthetic */ String access$102(VirementDetails virementDetails, String str) {
        virementDetails.VnewaliasText = str;
        return str;
    }

    public VirementDetails(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.Ovp = (TransferHistoric) this.service;
        Container container = new Container(new BorderLayout());
        container.setUIID("Whitemargin_2_2_2_2");
        Container container2 = new Container(new BoxLayout(2));
        if (this.Ovp.OperationType.equals("300018") || this.Ovp.OperationType.equals("300014")) {
            this.TH = (TransferHistoric) this.service;
            Label label = new Label("Recharge en faveur de " + this.Ovp.BeneficiaryName);
            this.Title1 = label;
            label.setUIID("crd_CardTitle");
            container2.add(this.Title1);
        } else {
            Label label2 = new Label("Virement en faveur de " + this.Ovp.BeneficiaryName);
            this.Title1 = label2;
            label2.setUIID("crd_CardTitle");
            container2.add(this.Title1);
        }
        Container container3 = new Container(new BoxLayout(2));
        container3.setUIID("SV_padding_0_0_2_2");
        this.DescSpnLbl1 = new SpanLabel();
        SpanLabel spanLabel = new SpanLabel();
        spanLabel.setTextUIID("dg_splblMsgCenter");
        if (this.Ovp.OperationType.equals("300018") || this.Ovp.OperationType.equals("300014")) {
            this.Ovp = (TransferHistoric) this.service;
            spanLabel.setText("Votre recharge a bien été traité\u200b");
        } else {
            spanLabel.setText("Votre virement a bien été traité\u200b");
        }
        container3.add(spanLabel);
        Container container4 = new Container(new FlowLayout(4, 4));
        Label label3 = new Label();
        label3.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("OvpConfirmed.png"));
        container4.add(label3);
        container2.add(container3).add(container4);
        Component GetContainerAvenir = GetContainerAvenir();
        Container container5 = new Container(new BoxLayout(2));
        container5.setUIID("padding_2_2_0_0");
        Button button = new Button("SUSPENDRE CETTE ECHEANCE");
        button.setUIID("sendBtn");
        Container container6 = new Container(new BoxLayout(2));
        container6.setUIID("padding_1_1_0_0");
        if (this.TH.OperationType.equals("300018") || this.TH.OperationType.equals("300014")) {
            this.ArrtBtn = new Button("REJOUER LA RECHARGE");
        } else {
            this.ArrtBtn = new Button("REJOUER CE VIREMENT");
        }
        this.ArrtBtn.setUIID("sendBtn");
        this.ArrtBtn.addActionListener(new 1(container, container2));
        container5.add(button);
        container6.add(this.ArrtBtn);
        Container container7 = new Container(new BoxLayout(2));
        container7.setUIID("padding_1_1_0_0");
        Container container8 = new Container(new LayeredLayout());
        Button button2 = new Button();
        button2.setText("EDITER LE REÇU");
        button2.setUIID("sendBtn");
        container8.add(button2);
        Label label4 = new Label("EDITER LE REÇU");
        label4.setUIID("sendBtn");
        container8.add(label4);
        container8.setLeadComponent(button2);
        TransferHistoric transferHistoric = (TransferHistoric) this.service;
        button2.addActionListener(new 2(transferHistoric));
        Container container9 = new Container(new BoxLayout(2));
        container9.setUIID("padding_1_1_0_0");
        Container container10 = new Container(new LayeredLayout());
        ShareButton shareButton = new ShareButton();
        shareButton.setText("PARTAGER");
        shareButton.setUIID("sendBtn");
        container10.add(shareButton);
        Label label5 = new Label("PARTAGER");
        label5.setUIID("sendBtn");
        container10.add(label5);
        container10.setLeadComponent(shareButton);
        shareButton.addActionListener(new 3(transferHistoric, shareButton));
        container9.add(container10);
        container7.add(container8);
        container2.add(GetContainerAvenir);
        container2.add(container6);
        container2.add(container7);
        container2.add(container9);
        container.add("Center", container2);
        this.thisContainer = container;
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$GlobCnt;
        final /* synthetic */ Container val$YCnt;

        1(Container container, Container container2) {
            this.val$GlobCnt = container;
            this.val$YCnt = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.agencyCode = null;
            String str = "";
            for (int i = 0; i < CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().size(); i++) {
                if (((Account) CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().get(i)).rib.substring(6, ((Account) CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().get(i)).rib.length() - 2).equals(VirementDetails.this.Ovp.AccountNumber)) {
                    str = ((Account) CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().get(i)).rib;
                }
            }
            String str2 = str;
            ServiceResponse sendOtpProcessTransfer = ServiceManager.sendOtpProcessTransfer(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1017", 300015, "1", "0", str, VirementDetails.this.Ovp.Beneficiary, VirementDetails.this.Ovp.Amount, "0", VirementDetails.this.Ovp.BeneficiaryName, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, VirementDetails.this.Ovp.Motif);
            if (sendOtpProcessTransfer.getStatusCode().equals("000")) {
                TransactionResume transactionResume = new TransactionResume();
                transactionResume.IssuerAccountNumber = str2;
                transactionResume.IssuerAccountOwner = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom;
                transactionResume.CreditedAccountNumber = VirementDetails.this.Ovp.Beneficiary;
                transactionResume.CreditedAccountNumberHiden = "";
                transactionResume.CreditedAccountAlias = VirementDetails.this.Ovp.BeneficiaryName;
                transactionResume.Amount = VirementDetails.this.Ovp.Amount;
                transactionResume.Motif = VirementDetails.this.Ovp.Motif;
                transactionResume.PassHB = "";
                transactionResume.CINHB = "";
                transactionResume.isTutor = "0";
                transactionResume.token = "";
                transactionResume.phoneNumber = "";
                transactionResume.TypeOperation = "TRANSFER";
                CihMBanking.sesPMTR.setSessionCurrentTransactionResume(transactionResume);
                VirementDetails virementDetails = VirementDetails.this;
                Container container = this.val$GlobCnt;
                virementDetails.SwitchTransfertForms(container, this.val$YCnt, virementDetails.GetTransferSecurityForm(container), false);
                VirementDetails.this.uiManager.btnBack.getListeners().clear();
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(VirementDetails.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcessTransfer.getStatusLabel()), null);
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ TransferHistoric val$TH;

        2(TransferHistoric transferHistoric) {
            this.val$TH = transferHistoric;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String str;
            FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
            ServiceResponse MTCSendReceiptProcess1 = ServiceManager.MTCSendReceiptProcess1(this.val$TH.Amount, this.val$TH.BeneficiaryName, this.val$TH.Motif, this.val$TH.Beneficiary, "", this.val$TH.OperationDate, this.val$TH.AccountNumber);
            if (MTCSendReceiptProcess1.getStatusCode().equals("000")) {
                byte[] decode = Base64.decode(MTCSendReceiptProcess1.getStatusLabel().getBytes(), MTCSendReceiptProcess1.getStatusLabel().getBytes().length);
                if (!Display.getInstance().getPlatformName().equals("ios")) {
                    str = "Editerreçu" + this.val$TH.AccountNumber + ".pdf";
                    ((NativeCall) NativeLookup.create(NativeCall.class)).createPdfFromByteArray(decode, str);
                } else {
                    String str2 = fileSystemStorage.getAppHomePath() + "Editer reçu" + this.val$TH.AccountNumber + ".pdf";
                    try {
                        OutputStream openOutputStream = fileSystemStorage.openOutputStream(str2);
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
                    str = str2;
                }
                Display.getInstance().execute(str);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(MTCSendReceiptProcess1.getStatusLabel()), null);
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ ShareButton val$RepBtn;
        final /* synthetic */ TransferHistoric val$TH;

        3(TransferHistoric transferHistoric, ShareButton shareButton) {
            this.val$TH = transferHistoric;
            this.val$RepBtn = shareButton;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            new Container();
            Container access$000 = VirementDetails.access$000(VirementDetails.this, StringTools.HideCardNumberVirementHistoricRecap(this.val$TH.AccountNumber), this.val$TH.Beneficiary, this.val$TH.Amount, this.val$TH.OperationDate, this.val$TH.BeneficiaryName, this.val$TH.Motif, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, 0);
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
            this.val$RepBtn.setImageToShare(str, "image/png");
        }
    }

    private Container GetContainerAvenir() {
        this.TH = (TransferHistoric) this.service;
        Container container = new Container(new BoxLayout(2));
        Container container2 = new Container(new BoxLayout(2));
        Label label = new Label(this.TH.OperationDate.substring(0, 10));
        label.setUIID("g_lblDashBoardValueBlue");
        label.getAllStyles().setPaddingUnit(1);
        label.getAllStyles().setPadding(1, 1, 0, 0);
        container2.add(label);
        Container Bloc = Bloc("N Compte", this.TH.AccountNumber);
        Container Bloc2 = Bloc("Béneficiare", this.TH.BeneficiaryName);
        if (this.TH.OperationType.equals("300018") || this.TH.OperationType.equals("300014")) {
            Container Bloc3 = Bloc("N° carte", this.TH.Beneficiary);
            Container Bloc4 = Bloc("Motif", this.TH.Motif);
            container2.add(Bloc).add(Bloc2).add(Bloc3).add(Bloc4).add(Bloc("Montant", this.TH.Amount)).add(Bloc("Commision", this.TH.FeeAmount));
        } else {
            Container Bloc5 = Bloc("RIB", this.TH.Beneficiary);
            Container Bloc6 = Bloc("Motif", this.TH.Motif);
            container2.add(Bloc).add(Bloc2).add(Bloc5).add(Bloc6).add(Bloc("Montant", this.TH.Amount)).add(Bloc("Commision", this.TH.FeeAmount));
        }
        container.add(container2);
        return container;
    }

    public Container Bloc(String str, String str2) {
        Container container = new Container(new BoxLayout(2));
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("greyCntNew2");
        Container container3 = new Container(new BoxLayout(2));
        container3.setUIID("greyCntNew2");
        Container container4 = new Container(new GridLayout(1, 2));
        container4.getAllStyles().setPaddingUnit(1);
        container4.getAllStyles().setPadding(1, 1, 0, 0);
        Label label = new Label(str);
        label.setUIID("g_lblDashBoardValue");
        SpanLabel spanLabel = new SpanLabel(str2);
        spanLabel.setUIID("lbl_regular_bold");
        container4.add(label).add(spanLabel);
        container.add(container2).add(container4).add(container3);
        return container;
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 0));
        container.revalidate();
        if (Display.getInstance().getPlatformName().equals("ios")) {
            this.txtHBPassWord.startEditingAsync();
        }
    }

    public Container GetTransferSecurityForm(Container container) {
        Container container2 = new Container(BoxLayout.y());
        Container container3 = new Container(BoxLayout.y());
        Label label = new Label("Service sélectionné : ");
        label.setUIID("g_lblDashBoardTitleOrange");
        Label label2 = new Label("Activer Bénéficiaire");
        label2.setUIID("ad_lblValueBlue");
        Container container4 = new Container(BoxLayout.x());
        container4.add(label);
        container4.add(label2);
        Container container5 = new Container(BoxLayout.y());
        container5.setUIID("g_cntBorderV2");
        container3.add(container4);
        container3.add(container5);
        container2.add(container3);
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container6 = (Container) this.uib.findByName("Container17", createContainer);
        container6.removeAll();
        container6.setLayout(BoxLayout.y());
        container6.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 4(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 5(b3GCIHEcode));
        return createContainer;
    }

    class 4 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        4(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 0));
            VirementDetails.this.uiManager.btnBack.getListeners().clear();
            VirementDetails.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                VirementDetails.this.uiManager.GoBack();
            }
        }
    }

    class 5 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        5(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
            VirementDetails.access$102(VirementDetails.this, this.val$eCode1.getValue());
            sessionCurrentTransactionResume.PassHB = VirementDetails.access$100(VirementDetails.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (VirementDetails.access$100(VirementDetails.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(VirementDetails.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(10, sessionCurrentTransactionResume, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 300013);
            }
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
