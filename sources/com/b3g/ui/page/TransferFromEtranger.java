package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.AccountsAgr;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.ExchangeTransfertService;
import com.b3g.services.SavingAcountAgregation;
import com.b3g.services.TransactionResume;
import com.b3g.services.TransfertFromEtranger;
import com.b3g.services.TransfertHistorique;
import com.b3g.services.Webview;
import com.b3g.tools.DataTools;
import com.b3g.tools.NativeCall;
import com.b3g.tools.SortBenifByAlias;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GRadio;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordion;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import com.codename1.util.StringUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TransferFromEtranger extends B3GPage {
    public static B3gAccordion A;
    public static Container BENEFCNT;
    public static Container Glob;
    public static Container cntTransfertForm;
    public static boolean isOtpPageShow;
    Button BtnTransferValidation;
    Label DateLbl;
    private String VnewaliasText;
    Label lblDeviseMadValue;
    Label lblEuroValue;
    TextField txtAmount1;
    TextField txtContreValeur;
    TextField txtFrais;
    TextField txtHBPassWord;
    TextField txtMotif1;
    UIBuilder uib = new UIBuilder();
    private boolean isOvp = false;
    private boolean isOwnAgency = true;

    static /* synthetic */ String access$000(TransferFromEtranger transferFromEtranger, Container container) {
        return transferFromEtranger.getAccountDbtFromDrawListContainer(container);
    }

    static /* synthetic */ String access$100(TransferFromEtranger transferFromEtranger, Container container) {
        return transferFromEtranger.getAccountFromDrawListContainer(container);
    }

    static /* synthetic */ String access$200(TransferFromEtranger transferFromEtranger, String str, String str2) {
        return transferFromEtranger.caluclatTotalAmuont(str, str2);
    }

    static /* synthetic */ Double access$300(TransferFromEtranger transferFromEtranger, String str) {
        return transferFromEtranger.getDeeviseChange(str);
    }

    static /* synthetic */ String access$400(TransferFromEtranger transferFromEtranger) {
        return transferFromEtranger.VnewaliasText;
    }

    static /* synthetic */ String access$402(TransferFromEtranger transferFromEtranger, String str) {
        transferFromEtranger.VnewaliasText = str;
        return str;
    }

    public TransferFromEtranger(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        isOtpPageShow = false;
        TopUpNewPage.isOtpPageShow = false;
        TransfertWallet.isOtpPageShow = false;
        this.thisContainer = new Container(new GridLayout(1, 1));
        try {
            CihMBanking.sesPMTR.setToken("");
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(GetContainerTransferForm(), "TRANSFERT"));
            arrayList.add(new B3gContainer(GetContainerTransferHistoric(), "HISTORIQUE"));
            this.uiManager.DrawTabsWithNavigation(container, arrayList);
            this.thisContainer.add(container);
        } catch (Exception unused) {
            this.thisContainer.setLayout(new BorderLayout());
            this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public Container GetContainerTransferHistoric() {
        Container container = new Container(new BoxLayout(2));
        try {
            ArrayList FillAjouterNVArrayDataFromServiceResponse = new TransfertHistorique().FillAjouterNVArrayDataFromServiceResponse(TransfertHistorique.AccountOperationProcess1());
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < FillAjouterNVArrayDataFromServiceResponse.size(); i++) {
                ((TransfertHistorique) FillAjouterNVArrayDataFromServiceResponse.get(i)).groupKey = ((TransfertHistorique) FillAjouterNVArrayDataFromServiceResponse.get(i)).date;
                arrayList.add((B3gService) FillAjouterNVArrayDataFromServiceResponse.get(i));
            }
            if (arrayList.size() > 0) {
                this.uiManager.Draw_GroupBy(container, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 42, null);
            } else {
                container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
            }
        } catch (Exception unused) {
            container.setLayout(new BorderLayout());
            container.addComponent("North", this.uiManager.GetGoBackHome());
        }
        return container;
    }

    public Container GetContainerTransferForm() {
        ArrayList arrayList;
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransferFormEtranger");
        cntTransfertForm = createContainer;
        try {
            this.txtContreValeur = (TextField) this.uib.findByName("txtContreValeur", createContainer);
            this.lblDeviseMadValue = (Label) this.uib.findByName("lblDeviseMadValue", cntTransfertForm);
            this.lblEuroValue = (Label) this.uib.findByName("lblEuroValue", cntTransfertForm);
            Container container = (Container) this.uib.findByName("Cntdevise", cntTransfertForm);
            this.DateLbl = (Label) this.uib.findByName("DateLbl", cntTransfertForm);
            this.txtAmount1 = (TextField) this.uib.findByName("txtAmount", cntTransfertForm);
            this.txtFrais = (TextField) this.uib.findByName("txtFrais", cntTransfertForm);
            this.txtMotif1 = (TextField) this.uib.findByName("txtMotif1", cntTransfertForm);
            this.BtnTransferValidation = (Button) this.uib.findByName("BtnTransferValidation", cntTransfertForm);
            B3GRadio b3GRadio = new B3GRadio("DEVISE");
            b3GRadio.addItem(" MAD ");
            b3GRadio.addItem(" EUR ");
            new ExchangeTransfertService().GetForxMDM();
            this.lblDeviseMadValue.setText(DataTools.FormatCurrencyMDM(String.valueOf(CihMBanking.sesPMTR.getExchangeTransfert().customerBuy)));
            this.lblEuroValue.setText(DataTools.FormatCurrencyMDM(String.valueOf(1.0d / CihMBanking.sesPMTR.getExchangeTransfert().customerBuy)));
            this.DateLbl.setText(CihMBanking.sesPMTR.getExchangeTransfert().date);
            this.txtAmount1.addDataChangedListener(new 1(b3GRadio));
            b3GRadio.getItem(" MAD ").addActionListener(new 2());
            b3GRadio.getItem(" EUR ").addActionListener(new 3());
            container.setUIID("");
            container.addComponent(b3GRadio.GetContainer());
            if (Display.getInstance().getPlatformName().equals("ios")) {
                cntTransfertForm.setScrollableY(false);
            }
            new Account().uiManager = this.uiManager;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountListVirm());
            arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getListRelationAccount());
            SavingAcountAgregation savingAcountAgregation = new SavingAcountAgregation();
            ServiceResponse detailsResponse = savingAcountAgregation.getDetailsResponse();
            if (detailsResponse.getStatusCode().equals("001")) {
                arrayList = savingAcountAgregation.AgregationDetails(detailsResponse);
                AccountsAgr.parent = this.thisContainer;
            } else {
                Display.getInstance().callSerially(new 4());
                arrayList = null;
            }
            Container DrawListContainerTransfer = this.uiManager.DrawListContainerTransfer("GloabalContainerV2", "Compte à créditer", Boolean.TRUE, getCanDebitAccountMDM(getCanDebitAccount(arrayList2)), 1, 16, "", null, null, CihMBanking.sesPMTR.getSessionClient().getClient_BeneficiaryList());
            Container DrawListContainerTransfer2 = this.uiManager.DrawListContainerTransfer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getAgegAountActif(arrayList), 1, 96, "", null, null, CihMBanking.sesPMTR.getSessionClient().getClient_BeneficiaryList());
            this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(DrawListContainerTransfer2);
            this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
            this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(DrawListContainerTransfer);
            this.BtnTransferValidation.addActionListener(new 5(b3GRadio, DrawListContainerTransfer2, DrawListContainerTransfer));
        } catch (Exception unused) {
            cntTransfertForm.setLayout(new BorderLayout());
            cntTransfertForm.addComponent("North", this.uiManager.GetGoBackHome());
        }
        return cntTransfertForm;
    }

    class 1 implements DataChangedListener {
        final /* synthetic */ B3GRadio val$deviceRadio;

        1(B3GRadio b3GRadio) {
            this.val$deviceRadio = b3GRadio;
        }

        public void dataChanged(int i, int i2) {
            TransferFromEtranger.this.changeCurrency(this.val$deviceRadio.GetSelectedText());
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransferFromEtranger.this.txtAmount1.clear();
            TransferFromEtranger.this.txtMotif1.clear();
            TransferFromEtranger.this.txtContreValeur.clear();
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransferFromEtranger.this.txtAmount1.clear();
            TransferFromEtranger.this.txtMotif1.clear();
            TransferFromEtranger.this.txtContreValeur.clear();
        }
    }

    class 4 implements Runnable {
        4() {
        }

        public void run() {
            new UITimer(new TransferFromEtranger$4$$ExternalSyntheticLambda0(this)).schedule(300, false, TransferFromEtranger.this.uiManager.currentForm);
        }

        /* synthetic */ void lambda$run$0$com-b3g-ui-page-TransferFromEtranger$4() {
            TransferFromEtranger.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    class 5 implements ActionListener {
        final /* synthetic */ B3GRadio val$deviceRadio;
        final /* synthetic */ Container val$issuarAccount;
        final /* synthetic */ Container val$issuarAccount1;

        5(B3GRadio b3GRadio, Container container, Container container2) {
            this.val$deviceRadio = b3GRadio;
            this.val$issuarAccount1 = container;
            this.val$issuarAccount = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransferFromEtranger transferFromEtranger = TransferFromEtranger.this;
            String GetSelectedText = this.val$deviceRadio.GetSelectedText();
            String access$000 = TransferFromEtranger.access$000(TransferFromEtranger.this, this.val$issuarAccount1);
            String access$100 = TransferFromEtranger.access$100(TransferFromEtranger.this, this.val$issuarAccount);
            TransferFromEtranger transferFromEtranger2 = TransferFromEtranger.this;
            CihMBanking.sesPMTR.setSessionCurrentTransactionResume(transferFromEtranger.GetTransaction(GetSelectedText, access$000, access$100, TransferFromEtranger.access$200(transferFromEtranger2, transferFromEtranger2.txtAmount1.getText(), TransferFromEtranger.this.txtFrais.getText()), TransferFromEtranger.this.txtContreValeur.getText(), TransferFromEtranger.this.txtFrais.getText(), TransferFromEtranger.access$300(TransferFromEtranger.this, this.val$deviceRadio.GetSelectedText()), TransferFromEtranger.this.txtMotif1.getText(), TransferFromEtranger.this.txtContreValeur.getText()));
            TransfertFromEtranger transfertFromEtranger = new TransfertFromEtranger();
            TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
            ServiceResponse GetTransfertFromEtraner = transfertFromEtranger.GetTransfertFromEtraner(sessionCurrentTransactionResume.field1, sessionCurrentTransactionResume.IssuerAccountNumber, sessionCurrentTransactionResume.CreditedAccountNumber, sessionCurrentTransactionResume.field2, sessionCurrentTransactionResume.Amount, sessionCurrentTransactionResume.field3, TransferFromEtranger.access$300(TransferFromEtranger.this, sessionCurrentTransactionResume.field1), sessionCurrentTransactionResume.Motif);
            if (GetTransfertFromEtraner.getStatusCode().equals("000")) {
                TransferFromEtranger.this.SwitchTransfertForms(TransferFromEtranger.cntTransfertForm, TransferFromEtranger.this.uiManager.stateMachine.findCntGlobalTransfer(TransferFromEtranger.cntTransfertForm), TransferFromEtranger.this.GetTransferSecurityForm(TransferFromEtranger.cntTransfertForm), false);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferFromEtranger.this.uiManager.stateMachine, GetTransfertFromEtraner.getStatusLabel(), null);
            }
        }
    }

    private ArrayList getAgegAountActif(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            AccountsAgr accountsAgr = (AccountsAgr) it.next();
            if (accountsAgr.status.equals("ACTIF")) {
                arrayList2.add(accountsAgr);
            }
        }
        return arrayList2;
    }

    public TransactionResume GetTransaction(String str, String str2, String str3, String str4, String str5, String str6, Double d, String str7, String str8) {
        TransactionResume transactionResume = new TransactionResume();
        transactionResume.Amount = str5;
        transactionResume.Motif = str7;
        transactionResume.PassHB = "";
        transactionResume.IssuerAccountOwner = str2;
        transactionResume.IssuerAccountNumber = str2;
        transactionResume.CreditedAccountAlias = str3;
        transactionResume.CreditedAccountNumber = str3;
        transactionResume.field1 = str;
        transactionResume.field2 = str4;
        transactionResume.field3 = str6;
        transactionResume.TypeOperation = "TRANSFER";
        transactionResume.mnteuro = str8;
        return transactionResume;
    }

    class 6 implements DataChangedListener {
        public void dataChanged(int i, int i2) {
        }

        6() {
        }
    }

    public void HidePassWordOnKeyPressed(TextField textField) {
        textField.addDataChangeListener(new 6());
    }

    public void clearTExtField() {
        if (Display.getInstance().getPlatformName().equals("ios")) {
            this.txtHBPassWord.setText("");
            this.txtHBPassWord.clear();
            this.txtHBPassWord.clearClientProperties();
            this.txtHBPassWord.setFocusable(false);
        }
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 0));
        container.revalidate();
        if (Display.getInstance().getPlatformName().equals("ios")) {
            this.txtHBPassWord.startEditingAsync();
        }
    }

    ArrayList getFirstAccountBeneficiary(ArrayList arrayList, ArrayList arrayList2) {
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            return arrayList2;
        }
        ArrayList arrayList3 = new ArrayList();
        int i = 0;
        while (true) {
            if (i >= arrayList.size()) {
                break;
            }
            if (((Account) arrayList.get(i)).canDebit.equals("True") && ((Account) arrayList.get(i)).eligibleService.contains("300013")) {
                Iterator it = ((Account) arrayList.get(i)).accountBeneficiaries.iterator();
                while (it.hasNext()) {
                    Beneficiary beneficiary = (Beneficiary) it.next();
                    if (beneficiary.serviceId.equals("300013")) {
                        arrayList3.add(beneficiary);
                    }
                }
            } else {
                i++;
            }
        }
        Collections.sort(arrayList3, new SortBenifByAlias());
        return arrayList3;
    }

    ArrayList getCanDebitAccount(ArrayList arrayList) {
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Account account = (Account) it.next();
            if (account.canDebit.equals("True") && account.eligibleService.contains("300013")) {
                arrayList2.add(account);
            }
        }
        return arrayList2;
    }

    ArrayList getCanDebitAccountMDM(ArrayList arrayList) {
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Account account = (Account) it.next();
            if (account.accountNumber.contains("2140") || account.accountNumber.contains("2516") || account.accountNumber.contains("2110") || account.accountNumber.contains("2180")) {
                arrayList2.add(account);
            }
        }
        return arrayList2;
    }

    public void changeCurrency(String str) {
        if (!this.txtAmount1.getText().trim().equals("")) {
            String str2 = " " + CihMBanking.sesPMTR.getExchangeTransfert().currency + " ";
            String FormatCurrencyMDM2 = DataTools.FormatCurrencyMDM2(String.valueOf(Double.parseDouble(formatNumb(this.txtAmount1.getText(), ",")) * getDeeviseChange(str).doubleValue()));
            if (FormatCurrencyMDM2.equals("0")) {
                this.txtContreValeur.setText("");
                return;
            }
            if (Display.getInstance().getPlatformName().equals("ios")) {
                this.txtContreValeur.setText(StringUtil.replaceAll(FormatCurrencyMDM2, ",", " "));
                this.txtContreValeur.getParent().revalidate();
                return;
            } else if (Display.getInstance().getPlatformName().equals("and")) {
                this.txtContreValeur.setText(StringUtil.replaceAll(FormatCurrencyMDM2, ",", " "));
                this.txtContreValeur.getParent().revalidate();
                return;
            } else {
                this.txtContreValeur.setText(StringUtil.replaceAll(FormatCurrencyMDM2, ",", "."));
                this.txtContreValeur.getParent().revalidate();
                return;
            }
        }
        this.txtContreValeur.setText("");
    }

    private Double getDeeviseChange(String str) {
        if (str.trim().equals("MAD")) {
            return Double.valueOf(1.0d / CihMBanking.sesPMTR.getExchangeTransfert().customerBuy);
        }
        return Double.valueOf(CihMBanking.sesPMTR.getExchangeTransfert().customerBuy);
    }

    public String formatNumb(String str, String str2) {
        return StringUtil.replaceAll(str, str2, ".");
    }

    public Container DrawAcquiredAcc(String str, String str2) {
        Container GetContainerNw = this.uiManager.GetContainerNw("GloabalContainerV2", "Compte à créditer");
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AccountItemBenefTransfertV2");
        this.uiManager.stateMachine.findLblAccountOwner(createContainer).setText(str);
        this.uiManager.stateMachine.findLblAccountRibValue(createContainer).setText(StringTools.RibFormatWithSP(str2));
        GetContainerNw.addComponent(createContainer);
        return GetContainerNw;
    }

    private String getAccountFromDrawListContainer(Container container) {
        return ((Label) ((Container) ((Container) ((Container) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(1)).getText();
    }

    private String getAccountDbtFromDrawListContainer(Container container) {
        return ((Label) ((Container) ((Container) ((Container) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(2)).getText();
    }

    private String caluclatTotalAmuont(String str, String str2) {
        return String.valueOf(Double.valueOf(Double.parseDouble(str)));
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
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 7(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 8(b3GCIHEcode));
        return createContainer;
    }

    class 7 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        7(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 0));
            TransferFromEtranger.this.uiManager.btnBack.getListeners().clear();
            TransferFromEtranger.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                TransferFromEtranger.this.uiManager.GoBack();
            }
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        8(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
            TransferFromEtranger.access$402(TransferFromEtranger.this, this.val$eCode1.getValue());
            sessionCurrentTransactionResume.PassHB = TransferFromEtranger.access$400(TransferFromEtranger.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (TransferFromEtranger.access$400(TransferFromEtranger.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferFromEtranger.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
            } else {
                new UITimer(new TransferFromEtranger$8$$ExternalSyntheticLambda0(this)).schedule(500, false, TransferFromEtranger.this.uiManager.currentForm);
            }
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-TransferFromEtranger$8() {
            new TransfertFromEtranger();
            ServiceResponse Checkecode = TransfertFromEtranger.Checkecode(TransferFromEtranger.access$400(TransferFromEtranger.this));
            if (Checkecode.getStatusCode().equals("000")) {
                NativeCall nativeCall = (NativeCall) NativeLookup.create(NativeCall.class);
                if (nativeCall.isSupported()) {
                    nativeCall.callWebView(CihMBanking.sesPMTR.paymentConfirmationWebViewUrl, "TRANSFERT");
                    return;
                }
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferFromEtranger.this.uiManager.stateMachine, Checkecode.getStatusLabel(), null);
        }
    }

    public static void callBackWebViewTransfertMDM() {
        if (Webview.TransferRef().getStatusCode().equals("000")) {
            CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(153);
        } else {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
            Display.getInstance().callSerially(new 9());
        }
    }

    class 9 implements Runnable {
        9() {
        }

        public void run() {
            new UITimer(new TransferFromEtranger$9$$ExternalSyntheticLambda0()).schedule(50, false, CihMBanking.sesPMTR.getCurrentUiManager().currentForm);
        }

        static /* synthetic */ void lambda$run$0() {
            CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }
}
