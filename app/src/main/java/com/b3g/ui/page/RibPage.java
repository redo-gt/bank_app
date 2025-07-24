package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.QRModel;
import com.b3g.services.RIB;
import com.b3g.services.Relation;
import com.b3g.services.SavingAccount;
import com.b3g.tools.DataTools;
import com.b3g.tools.NativeCall;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.Base64;
import com.wefeel.QRMaker.QRMaker;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class RibPage extends B3GPage {
    private static int newHeight = 600;
    private static int newWidth = 600;
    private byte[] b = {1, 1, 1, 1};

    public RibPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            this.thisContainer.addComponent(GetRibContainerNew(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm));
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public Container GetRibContainer(String str, String str2) {
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "RibItemV2");
        Label label = (Label) uIBuilder.findByName("btnIbanValue", createContainer);
        Container container = (Container) uIBuilder.findByName("cntIbanNumber", createContainer);
        ImageViewer imageViewer = (ImageViewer) uIBuilder.findByName("qrCodeImg", createContainer);
        UIBuilder uIBuilder2 = new UIBuilder();
        Button button = (Button) uIBuilder2.findByName("BtnDownload", createContainer);
        Container container2 = (Container) uIBuilder2.findByName("ContShareRibTxt", createContainer);
        Container container3 = (Container) uIBuilder2.findByName("cntShare", createContainer);
        Container container4 = (Container) uIBuilder2.findByName("ContShareQRCode", createContainer);
        ShareButton shareButton = new ShareButton();
        shareButton.setUIID("VKBBtnNewVs2");
        shareButton.setText("PARTAGER LE RIB TEXTE");
        shareButton.setIcon(this.uiManager.ressource.getImage("icon_shareqr.png"));
        shareButton.setGap(7);
        ShareButton shareButton2 = new ShareButton();
        shareButton2.setUIID("VKBBtnNewVs2");
        shareButton2.setText("PARTAGER LE QR CODE");
        shareButton2.setIcon(this.uiManager.ressource.getImage("icon_shareqr.png"));
        shareButton2.setGap(7);
        container2.addComponent(shareButton);
        container4.addComponent(shareButton2);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList());
        purgeForbidenAccount(arrayList);
        Container DrawListContainerRibOpperation = this.uiManager.DrawListContainerRibOpperation("GloabalContainerV2", "Compte", Boolean.TRUE, arrayList, 1, 26, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, this.uiManager.stateMachine.findBtnAccountRibNumberValueV2(createContainer), this.uiManager.stateMachine.findBtnSwiftValueV2(createContainer));
        this.uiManager.stateMachine.findCntAccountContainerV2(createContainer).addComponent(DrawListContainerRibOpperation);
        if (arrayList.size() > 0) {
            this.uiManager.stateMachine.findBtnAccountRibNumberValueV2(createContainer).setText(StringTools.RibFormatWithSP(((Account) arrayList.get(0)).rib));
            label.setText("MA64 " + DataTools.FormatIban(((Account) arrayList.get(0)).rib));
        } else {
            createContainer.removeComponent(this.uiManager.stateMachine.findCntBRD2(createContainer));
            createContainer.removeComponent(this.uiManager.stateMachine.findCntAccountRibNumber(createContainer));
            createContainer.removeComponent(this.uiManager.stateMachine.findCntSwift(createContainer));
            createContainer.removeComponent(container);
            createContainer.removeComponent(this.uiManager.stateMachine.findCntTitleChekLcnDemandQuantity(createContainer));
            createContainer.removeComponent(this.uiManager.stateMachine.findCntBRDLabelSep1(createContainer));
            createContainer.removeComponent(this.uiManager.stateMachine.findCntBRDLabelSep(createContainer));
            createContainer.removeComponent(this.uiManager.stateMachine.findCntRibByPhone(createContainer));
        }
        shareButton.setTextToShare("Titulaire : " + getIssuerAccountOwner(DrawListContainerRibOpperation) + "\nRIB : " + this.uiManager.stateMachine.findBtnAccountRibNumberValueV2(createContainer).getText() + "\nIBAN : " + label.getText() + "\nCode SWIFT : " + this.uiManager.stateMachine.findBtnSwiftValueV2(createContainer).getText());
        shareButton2.addActionListener(new RibPage$$ExternalSyntheticLambda1(shareButton2, container3));
        try {
            imageViewer.setImage(QRMaker.QRCode(new QRModel().fillModel(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, getIssuerAccountNumber(DrawListContainerRibOpperation), getIssuerAccountOwner(DrawListContainerRibOpperation), true).toString()).scaled(newWidth, newHeight));
        } catch (Exception unused) {
        }
        button.addActionListener(new 1(DrawListContainerRibOpperation, createContainer));
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            button.setEnabled(false);
        }
        return createContainer;
    }

    static /* synthetic */ void lambda$GetRibContainer$0(ShareButton shareButton, Container container, ActionEvent actionEvent) {
        shareButton.setImageToShare(getScreenshot(container), "QRImage/png");
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$AccountContainerList;
        final /* synthetic */ Container val$cntRib;

        1(Container container, Container container2) {
            this.val$AccountContainerList = container;
            this.val$cntRib = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            RibPage ribPage = RibPage.this;
            RIB GetRibDemand = ribPage.GetRibDemand(this.val$AccountContainerList, "", "", ribPage.uiManager.stateMachine.findBtnAccountRibNumberValueV2(this.val$cntRib).getText(), "02");
            RIB rib = new RIB();
            FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
            ServiceResponse RiBProcess = rib.RiBProcess(GetRibDemand);
            if (RiBProcess.getStatusCode().equals("000")) {
                byte[] decode = Base64.decode(RiBProcess.getStatusLabel().getBytes(), RiBProcess.getStatusLabel().getBytes().length);
                if (!Display.getInstance().getPlatformName().equals("ios")) {
                    ((NativeCall) NativeLookup.create(NativeCall.class)).createPdfFromByteArray(decode, GetRibDemand.RibAccountNumber + "Rib.pdf");
                    return;
                }
                String str = fileSystemStorage.getAppHomePath() + GetRibDemand.RibAccountNumber + "Rib.pdf";
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
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(RiBProcess.getStatusLabel()), null);
                }
                Display.getInstance().execute(str);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(RiBProcess.getStatusLabel()), null);
        }
    }

    public Container GetRibContainerNew(String str, String str2) {
        Container container = new Container(new GridLayout(1, 1));
        container.setUIID("ad_CntAccountDetailsMain");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList());
        purgeForbidenAccount(arrayList);
        CihMBanking.sesPMTR.setActiveAccount((Account) arrayList.get(0));
        Container DrawListContainerRibOpperationNew = this.uiManager.DrawListContainerRibOpperationNew("GloabalContainerV2", "Compte", Boolean.TRUE, arrayList, 1, 26, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null);
        Font derive = Font.createTrueTypeFont("native:MainRegular", "native:MainRegular").derive(Display.getInstance().convertToPixels(2.5f), 0);
        ShareButton shareButton = new ShareButton();
        shareButton.setUIID("VKBBtnNewVs2");
        shareButton.setText("  PARTAGER TEXT");
        shareButton.getAllStyles().setFont(derive);
        shareButton.setIcon(this.uiManager.ressource.getImage("icon_shareqr.png"));
        ShareButton shareButton2 = new ShareButton();
        shareButton2.setUIID("VKBBtnNewVs2");
        shareButton2.setText("  PARTAGER QR");
        shareButton2.getAllStyles().setFont(derive);
        shareButton2.setIcon(this.uiManager.ressource.getImage("icon_shareqr.png"));
        Button button = new Button("  Télécharger");
        button.setUIID("OrgLabel2");
        button.setIcon(this.uiManager.ressource.getImage("icon_downloadqr.png"));
        button.getAllStyles().setMarginUnit(this.b);
        button.getAllStyles().setMargin(0.0f, 0.0f, 1.0f, 1.0f);
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container2 = new Container(tableLayout);
        container2.getAllStyles().setMarginUnit(this.b);
        container2.getAllStyles().setMargin(0.0f, 1.4f, 1.0f, 1.0f);
        container2.add(tableLayout.createConstraint().widthPercentage(49).heightPercentage(100), shareButton2);
        container2.add(tableLayout.createConstraint().widthPercentage(2).heightPercentage(100), new Container(BoxLayout.y()));
        container2.add(tableLayout.createConstraint().widthPercentage(49).heightPercentage(100), shareButton);
        shareButton.setTextToShare("Titulaire : " + CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom + "\nRIB : " + StringTools.RibFormatWithSP(CihMBanking.sesPMTR.getActiveAccount().rib) + "\nIBAN : MA64 " + DataTools.FormatIban(CihMBanking.sesPMTR.getActiveAccount().rib) + "\nCode SWIFT : CIHMMAMC");
        shareButton2.addActionListener(new RibPage$$ExternalSyntheticLambda0(shareButton2));
        button.addActionListener(new 2());
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            button.setEnabled(false);
        }
        DrawListContainerRibOpperationNew.add(new TableLayout(2, 1).createConstraint().widthPercentage(100).heightPercentage(18), BoxLayout.encloseYBottom(container2, button));
        container.addComponent(DrawListContainerRibOpperationNew);
        return container;
    }

    static /* synthetic */ void lambda$GetRibContainerNew$1(ShareButton shareButton, ActionEvent actionEvent) {
        shareButton.setImageToShare(getScreenshot(CihMBanking.sesPMTR.getActiveContainer()), "QRImage/png");
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            RIB GetRibDemandNew = RibPage.this.GetRibDemandNew(CihMBanking.sesPMTR.getActiveAccount().accountNumber, "", "", StringTools.RibFormatWithSP(CihMBanking.sesPMTR.getActiveAccount().rib), "02");
            RIB rib = new RIB();
            FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
            ServiceResponse RiBProcess = rib.RiBProcess(GetRibDemandNew);
            if (RiBProcess.getStatusCode().equals("000")) {
                byte[] decode = Base64.decode(RiBProcess.getStatusLabel().getBytes(), RiBProcess.getStatusLabel().getBytes().length);
                if (!Display.getInstance().getPlatformName().equals("ios")) {
                    ((NativeCall) NativeLookup.create(NativeCall.class)).createPdfFromByteArray(decode, GetRibDemandNew.RibAccountNumber + "Rib.pdf");
                    return;
                }
                String str = fileSystemStorage.getAppHomePath() + GetRibDemandNew.RibAccountNumber + "Rib.pdf";
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
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(RiBProcess.getStatusLabel()), null);
                }
                Display.getInstance().execute(str);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(RiBProcess.getStatusLabel()), null);
        }
    }

    public ArrayList GetAccountList() {
        SavingAccount savingAccount = new SavingAccount();
        ArrayList arrayList = new ArrayList();
        if (CihMBanking.sesPMTR.getSessionClient().getClient_AccountList() != null && CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().size() > 0) {
            arrayList.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList());
        }
        ArrayList SavingAccountProcess = savingAccount.SavingAccountProcess(27);
        if (SavingAccountProcess.size() > 0) {
            for (int i = 0; i < SavingAccountProcess.size(); i++) {
                if (((B3gService) SavingAccountProcess.get(i)).SelectedId.substring(7, 11).equals("2310")) {
                    Account account = new Account();
                    account.accountNumber = ((SavingAccount) SavingAccountProcess.get(i)).accountNumber;
                    account.type = ((SavingAccount) SavingAccountProcess.get(i)).type;
                    account.rib = ((SavingAccount) SavingAccountProcess.get(i)).rib;
                    arrayList.add(account);
                }
            }
        }
        if (!CihMBanking.sesPMTR.getSessionClient().getRelations().isEmpty()) {
            Iterator it = CihMBanking.sesPMTR.getSessionClient().getRelations().iterator();
            while (it.hasNext()) {
                arrayList.add(((Relation) it.next()).getAccount());
            }
        }
        return arrayList;
    }

    public RIB GetRibDemand(Container container, String str, String str2, String str3, String str4) {
        Container container2 = (Container) ((Container) ((Container) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
        RIB rib = new RIB();
        rib.RibEmail = str;
        rib.RibPhoneNumber = str2;
        rib.channelId = str4;
        rib.RibNumber = StringTools.RibFormatWithoutSP(str3);
        rib.RibAccountNumber = ((Label) container2.getComponentAt(1)).getText();
        return rib;
    }

    public RIB GetRibDemandNew(String str, String str2, String str3, String str4, String str5) {
        RIB rib = new RIB();
        rib.RibEmail = str2;
        rib.RibPhoneNumber = str3;
        rib.channelId = str5;
        rib.RibNumber = StringTools.RibFormatWithoutSP(str4);
        rib.RibAccountNumber = str;
        return rib;
    }

    void purgeForbidenAccount(ArrayList arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            String substring = ((Account) arrayList.get(i)).rib.substring(13, 17);
            if (substring.equals("2310") || substring.equals("2360") || substring.equals("2311") || substring.substring(0, 2).equals("25") || ((Account) arrayList.get(i)).accountNumber.substring(7, 11).equals("2101")) {
                arrayList.remove(i);
                i--;
            }
            i++;
        }
    }

    private String getIssuerAccountOwner(Container container) {
        return ((Label) ((Container) ((Container) ((Container) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getText();
    }

    private String getIssuerAccountNumber(Container container) {
        return ((Label) ((Container) ((Container) ((Container) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(1)).getText();
    }

    public static String getScreenshot(Container container) {
        String str = "";
        try {
            str = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() + ".png";
            Image createImage = Image.createImage(container.getWidth(), container.getHeight() + 450);
            container.paintComponent(createImage.getGraphics(), true);
            OutputStream openOutputStream = FileSystemStorage.getInstance().openOutputStream(str);
            ImageIO.getImageIO().save(createImage, openOutputStream, "png", 0.9f);
            openOutputStream.close();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }
}
