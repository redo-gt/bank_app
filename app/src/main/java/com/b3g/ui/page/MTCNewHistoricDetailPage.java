package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.MTCCreancier;
import com.b3g.services.MTCHistoric;
import com.b3g.services.MTCImpaye;
import com.b3g.tools.DataTools;
import com.b3g.tools.NativeCall;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.io.FileSystemStorage;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.util.Base64;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MTCNewHistoricDetailPage extends B3GPage {
    public MTCNewHistoricDetailPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            new ArrayList();
            ArrayList arrayList = ((MTCHistoric) this.service).MTCImpayeList;
            Container container = new Container(new GridLayout(1, 1));
            container.setUIID("ad_CntBtnTab");
            Button button = new Button("DETAILS OPERATION");
            button.setUIID("ad_BtnTab");
            button.setEnabled(false);
            container.addComponent(button);
            this.thisContainer.addComponent(container);
            this.thisContainer.addComponent(GetHistoricGlobalResume((MTCHistoric) this.service));
            this.thisContainer.addComponent(GetPDFResumeValidation());
            new Container(new BoxLayout(2)).setUIID("ad_CntAccountDetailsMain");
            Container container2 = new Container(new GridLayout(1, 1));
            container2.setUIID("ad_CntBtnTab");
            Button button2 = new Button("LISTE DES FACTURES REGLEES");
            button2.setUIID("ad_BtnTab");
            button2.setEnabled(false);
            container2.addComponent(button2);
            this.thisContainer.addComponent(container2);
            this.thisContainer.addComponent(GetMTCHistoricDetailListContainer(arrayList, (MTCHistoric) this.service));
            new Container(new BoxLayout(2)).setUIID("ad_CntAccountDetailsMain");
            Container container3 = new Container(new GridLayout(1, 1));
            container3.setUIID("ad_CntBtnTab");
            Button button3 = new Button("EDITER LE RECU DE PAIEMENT");
            button3.setUIID("dg_BtnYest");
            button3.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("insurance_download.png"));
            container3.addComponent(button3);
            button3.addActionListener(new 1((MTCHistoric) this.service));
            this.thisContainer.addComponent(container3);
            this.thisContainer.setScrollableY(true);
            this.thisContainer.setScrollVisible(false);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ MTCHistoric val$lMTCConfirmationResumeMng;

        1(MTCHistoric mTCHistoric) {
            this.val$lMTCConfirmationResumeMng = mTCHistoric;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
            String[] split = DataTools.split(this.val$lMTCConfirmationResumeMng.OptionnalData2, ";");
            MTCHistoric mTCHistoric = this.val$lMTCConfirmationResumeMng;
            ServiceResponse MTCSendReceiptProcess1 = mTCHistoric.MTCSendReceiptProcess1(mTCHistoric, split);
            if (MTCSendReceiptProcess1.getStatusCode().equals("000")) {
                byte[] decode = Base64.decode(MTCSendReceiptProcess1.getStatusLabel().getBytes(), MTCSendReceiptProcess1.getStatusLabel().getBytes().length);
                if (!Display.getInstance().getPlatformName().equals("ios")) {
                    ((NativeCall) NativeLookup.create(NativeCall.class)).createPdfFromByteArray(decode, "PaiementFacture" + this.val$lMTCConfirmationResumeMng.BillerType + this.val$lMTCConfirmationResumeMng.FatouratiReference + ".pdf");
                    return;
                }
                String str = fileSystemStorage.getAppHomePath() + "PaiementFacture" + this.val$lMTCConfirmationResumeMng.BillerType + this.val$lMTCConfirmationResumeMng.FatouratiReference + ".pdf";
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

    public Container GetMTCHistoricDetailListContainer(ArrayList arrayList, MTCHistoric mTCHistoric) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            ((MTCImpaye) arrayList.get(i)).groupKey = ((MTCImpaye) arrayList.get(i)).DateFacture;
            arrayList2.add((B3gService) arrayList.get(i));
        }
        Container container = new Container(new BoxLayout(2));
        if (arrayList2.size() > 0) {
            this.uiManager.Draw_GroupBy(container, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 281, mTCHistoric);
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
        }
        return container;
    }

    public Container GetHistoricGlobalResume(MTCHistoric mTCHistoric) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("ad_CntAccountDetailsMain");
        container.setScrollVisible(false);
        container.setFocusable(false);
        String[] split = DataTools.split(mTCHistoric.OptionnalData2, ";");
        if (split.length > 1) {
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "MTCCreancier");
            this.uiManager.stateMachine.findLblCreancierNameTypeValueV2(createContainer).setText(split[0]);
            this.uiManager.stateMachine.findLblCreancierDescriptionValueV2(createContainer).setText(split[1]);
            EncodedImage createFromImage = EncodedImage.createFromImage(Image.createImage(this.uiManager.stateMachine.findBtnCreancierImage(createContainer).getIcon().getWidth(), this.uiManager.stateMachine.findBtnCreancierImage(createContainer).getIcon().getWidth(), 12569042), false);
            String pathUrlRechargeFromBinnerId = getPathUrlRechargeFromBinnerId(mTCHistoric.BillerId);
            if (pathUrlRechargeFromBinnerId.equals("")) {
                pathUrlRechargeFromBinnerId = MTCCreancierPage.getPathUrlFromBinnerId(mTCHistoric.BillerId);
            }
            this.uiManager.stateMachine.findBtnCreancierImage(createContainer).setIcon(URLImage.createToStorage(createFromImage, "Normal_https://www.cihnet.co.ma" + pathUrlRechargeFromBinnerId, "https://www.cihnet.co.ma" + pathUrlRechargeFromBinnerId, URLImage.RESIZE_SCALE));
            container.addComponent(createContainer);
            container.addComponent(GetCntBorderSeparator());
        }
        container.addComponent(GetKeyValueData(mTCHistoric.FormInputLabel, mTCHistoric.FormInputValue));
        container.addComponent(GetCntBorderSeparator());
        if (mTCHistoric.DebitAccountNumber.length() > 23) {
            container.addComponent(GetKeyValueData("N°Compte", mTCHistoric.DebitAccountNumber.substring(6, 21)));
            container.addComponent(GetCntBorderSeparator());
        } else {
            container.addComponent(GetKeyValueData("N°Compte", mTCHistoric.DebitAccountNumber));
            container.addComponent(GetCntBorderSeparator());
        }
        container.addComponent(GetKeyValueData("Montant", mTCHistoric.TransactionAmount + " DH"));
        container.addComponent(GetCntBorderSeparator());
        container.addComponent(GetKeyValueData("Date", mTCHistoric.OperationDate));
        container.addComponent(GetCntBorderSeparator());
        container.addComponent(GetKeyValueData("Référence de paiement", mTCHistoric.PaymentReference));
        container.addComponent(GetCntBorderSeparator());
        container.addComponent(GetKeyValueData("Référence MTC", mTCHistoric.FatouratiReference));
        container.addComponent(GetCntBorderSeparator());
        if (mTCHistoric.CRC != null && !mTCHistoric.CRC.equals("") && !mTCHistoric.CRC.equals("-")) {
            container.addComponent(GetKeyValueData("N.B", "Pour toute réclamation Appelez le " + mTCHistoric.CRC + " en indiquant la référence de paiement."));
            container.addComponent(GetCntBorderSeparator());
        }
        return container;
    }

    public Container GetCntBorderSeparator() {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("g_cntMTCBorderV2");
        return container;
    }

    public Container GetKeyValueData(String str, String str2) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(1);
        flowLayout.setValign(4);
        flowLayout.setFillRows(false);
        Container container = new Container(flowLayout);
        container.setFocusable(false);
        container.setScrollVisible(false);
        container.setUIID("MTCKeyValueLabel");
        Label label = new Label(str + " : ");
        label.setUIID("g_lblDashBoardTitleOrange");
        label.setFocusable(false);
        label.setScrollVisible(false);
        label.setVerticalAlignment(4);
        label.setTextPosition(1);
        Label label2 = new Label(str2);
        label2.setUIID("ad_lblValueBlue");
        label2.setFocusable(false);
        label2.setScrollVisible(false);
        label2.setVerticalAlignment(4);
        label2.setTextPosition(1);
        container.addComponent(label);
        container.addComponent(label2);
        return container;
    }

    public Container GetPDFResumeValidation() {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(3);
        flowLayout.setValign(4);
        flowLayout.setFillRows(false);
        Container container = new Container(flowLayout);
        container.setFocusable(false);
        container.setScrollVisible(false);
        container.setUIID("rib_CntBtnMTCResume");
        container.setEnabled(false);
        Container container2 = new Container(new BoxLayout(1));
        container2.setScrollVisible(false);
        container2.setUIID("mtc_BoxXbtn");
        Button button = new Button();
        button.setUIID("rib_BtnSendRIBByEmail");
        button.setScrollVisible(false);
        button.setVerticalAlignment(4);
        button.setTextPosition(1);
        button.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ico_email.png"));
        Button button2 = new Button("Envoyer le reçu");
        button2.setUIID("rib_BtnSendRIBByEmaillbl");
        button2.setScrollVisible(false);
        button2.setVerticalAlignment(4);
        button2.setTextPosition(3);
        button2.setEnabled(true);
        button2.addActionListener(new 2((MTCHistoric) this.service));
        container.addComponent(container2);
        return container;
    }

    class 2 implements ActionListener {
        final /* synthetic */ MTCHistoric val$mtcHistoricDetail;

        2(MTCHistoric mTCHistoric) {
            this.val$mtcHistoricDetail = mTCHistoric;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$mtcHistoricDetail.MTCHistoricSendTicketProcess().equals("000")) {
                MTCNewHistoricDetailPage.this.uiManager.ShowMessageTransaction(MTCNewHistoricDetailPage.this.uiManager.stateMachine, "Le reçu a été envoyé avec succès", null);
            } else {
                MTCNewHistoricDetailPage.this.uiManager.ShowMessageTransaction(MTCNewHistoricDetailPage.this.uiManager.stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
            }
        }
    }

    public static String getPathUrlRechargeFromBinnerId(String str) {
        ArrayList MTCCreancierProcess;
        MTCCreancier mTCCreancier = new MTCCreancier();
        if (CihMBanking.sesPMTR.MTCCreancierList != null) {
            MTCCreancierProcess = CihMBanking.sesPMTR.MTCCreancierList;
        } else {
            MTCCreancierProcess = mTCCreancier.MTCCreancierProcess("B");
        }
        String str2 = "";
        for (int i = 0; i < MTCCreancierProcess.size(); i++) {
            if (((MTCCreancier) MTCCreancierProcess.get(i)).CodeCreancier.equals(str)) {
                str2 = ((MTCCreancier) MTCCreancierProcess.get(i)).LogoPath;
            }
        }
        return str2;
    }
}
