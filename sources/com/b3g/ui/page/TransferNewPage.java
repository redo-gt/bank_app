package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.Plafond;
import com.b3g.services.ServiceManager;
import com.b3g.services.TransactionResume;
import com.b3g.services.TransferHistoric;
import com.b3g.services.TransfertDATA;
import com.b3g.tools.ContactsFilterProxyListModel;
import com.b3g.tools.DataTools;
import com.b3g.tools.SortBenifByAlias;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordion;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.StringUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TransferNewPage extends B3GPage {
    public static B3gAccordion A;
    public static Container BENEFCNT;
    public static Container Glob;
    public static Container cntBtns;
    public static Container cntTransfertForm;
    public static boolean isBenefPageShow;
    public static boolean isOtpPageShow;
    String CreditedAccountAliasNew;
    String CreditedAccountNumberNew;
    private String VnewaliasText;
    Container benefForm;
    private ArrayList beneficiaryList;
    private Image circleLineImage;
    private Object circleMask;
    private int circleMaskHeight;
    private int circleMaskWidth;
    Container cntTransferForm;
    ContactsFilterProxyListModel filter;
    HashMap index;
    private Font letterFont;
    List list;
    private TransfertTypeEmun selectedTypeTransfert;
    TransfertDATA trDT;
    TextField txtHBPassWord;
    private Dialog dlg = null;
    UIBuilder uib = new UIBuilder();
    private boolean isOvp = false;

    public enum TransfertTypeEmun {
        CLASSIQUE,
        INSTANTANE
    }

    static /* synthetic */ ArrayList access$002(TransferNewPage transferNewPage, ArrayList arrayList) {
        transferNewPage.beneficiaryList = arrayList;
        return arrayList;
    }

    static /* synthetic */ Container access$100(TransferNewPage transferNewPage) {
        return transferNewPage.getBenefForm();
    }

    static /* synthetic */ void access$200(TransferNewPage transferNewPage, TransactionResume transactionResume) {
        transferNewPage.showSelectTypeTransfertDlg(transactionResume);
    }

    static /* synthetic */ TransfertTypeEmun access$300(TransferNewPage transferNewPage) {
        return transferNewPage.selectedTypeTransfert;
    }

    static /* synthetic */ TransfertTypeEmun access$302(TransferNewPage transferNewPage, TransfertTypeEmun transfertTypeEmun) {
        transferNewPage.selectedTypeTransfert = transfertTypeEmun;
        return transfertTypeEmun;
    }

    static /* synthetic */ void access$400(TransferNewPage transferNewPage, TransactionResume transactionResume, Container container, Dialog dialog) {
        transferNewPage.executeSimulVirement(transactionResume, container, dialog);
    }

    static /* synthetic */ String access$500(TransferNewPage transferNewPage) {
        return transferNewPage.VnewaliasText;
    }

    static /* synthetic */ String access$502(TransferNewPage transferNewPage, String str) {
        transferNewPage.VnewaliasText = str;
        return str;
    }

    static /* synthetic */ Dialog access$600(TransferNewPage transferNewPage) {
        return transferNewPage.dlg;
    }

    public TransferNewPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        isOtpPageShow = false;
        isBenefPageShow = false;
        TopUpNewPage.isOtpPageShow = false;
        TransfertWallet.isOtpPageShow = false;
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setUIID("mn_cntMenuItemV2");
        this.thisContainer.setName("TransfertNewPage");
        try {
            CihMBanking.sesPMTR.setToken("");
            this.trDT = new TransfertDATA().TransfertDATAProcess(28);
            Container container = new Container(new BoxLayout(2));
            this.cntTransferForm = container;
            container.setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(GetContainerTransferForm(this.trDT.getBeneficiaryList(), this.trDT.getbeneficiaryStatusLabel(), this.trDT.getPlafond()), "VIREMENT"));
            arrayList.add(new B3gContainer(GetContainerTransferHistoric(this.trDT.getTransferHistoricList(), this.trDT), "HISTORIQUE"));
            this.uiManager.DrawTabsWithNavigationV3(this.cntTransferForm, arrayList, cntBtns, this.uiManager.stateMachine.findTxtAmount(cntTransfertForm), this.uiManager.stateMachine.findTxtMotif(cntTransfertForm), this.txtHBPassWord);
            this.thisContainer.add(this.cntTransferForm);
        } catch (Exception unused) {
            this.thisContainer.setLayout(new BorderLayout());
            this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public Container GetContainerTransferHistoric(ArrayList arrayList, TransfertDATA transfertDATA) {
        ArrayList arrayList2 = new ArrayList();
        Container container = new Container(new BoxLayout(2));
        for (int i = 0; i < arrayList.size(); i++) {
            try {
                ((TransferHistoric) arrayList.get(i)).groupKey = ((TransferHistoric) arrayList.get(i)).OperationDate.substring(0, 10);
                arrayList2.add((B3gService) arrayList.get(i));
            } catch (Exception unused) {
                container.setLayout(new BorderLayout());
                container.addComponent("North", this.uiManager.GetGoBackHome());
            }
        }
        if (arrayList2.size() > 0) {
            this.uiManager.Draw_GroupBy(container, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 8, transfertDATA);
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
        }
        return container;
    }

    public Container GetContainerTransferForm(ArrayList arrayList, String str, ArrayList arrayList2) {
        cntTransfertForm = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertFormTabGlobalLayered");
        if (Display.getInstance().getPlatformName().equals("ios")) {
            cntTransfertForm.setScrollableY(false);
        }
        new Account().uiManager = this.uiManager;
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountListVirm());
        arrayList3.addAll(CihMBanking.sesPMTR.getSessionClient().getListRelationAccount());
        Container DrawListContainerTransfer = this.uiManager.DrawListContainerTransfer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getCanDebitAccount(arrayList3), 1, 16, str, null, this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à créditer", Boolean.TRUE, getFirstAccountBeneficiary(arrayList3, arrayList), 1, 17, "Aucun compte n'est disponible pour cette opération.", null, null, null, null), CihMBanking.sesPMTR.getSessionClient().getClient_BeneficiaryList());
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(DrawListContainerTransfer);
        this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).setMaxSize(20);
        this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).addDataChangeListener(new 1());
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
        int i = 2;
        BENEFCNT = new Container(new BoxLayout(2));
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(BENEFCNT);
        new Container(new BoxLayout(2)).add(new Label("Compte à créditer "));
        Container container = new Container(new BoxLayout(2));
        Glob = container;
        Settings.setNightMode(container, 0);
        new ArrayList();
        ArrayList firstAccountBeneficiary = getFirstAccountBeneficiary(arrayList3, arrayList);
        int i2 = 0;
        while (i2 < firstAccountBeneficiary.size()) {
            TableLayout tableLayout = new TableLayout(1, i);
            Button button = new Button();
            button.setUIID("Container");
            Container container2 = new Container();
            container2.setLeadComponent(button);
            container2.setLayout(tableLayout);
            container2.removeAll();
            container2.setUIID("Container");
            TableLayout.Constraint createConstraint = tableLayout.createConstraint();
            TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
            createConstraint.setWidthPercentage(80);
            createConstraint2.setWidthPercentage(1);
            container2.addComponent(createConstraint2, button);
            container2.addComponent(createConstraint, ((Beneficiary) firstAccountBeneficiary.get(i2)).DrawItem(this.uiManager.stateMachine, this.uiManager.ressource, 17, this.service, this.thisContainer, this.thisContainer));
            Glob.add(container2);
            i2++;
            i = 2;
        }
        Button button2 = new Button(" Choisissez votre bénéficiaire ");
        button2.setUIID("op_BtnOppositionValidationV2Blue");
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(button2);
        button2.addActionListener(new 2(DrawListContainerTransfer));
        this.uiManager.stateMachine.findLblDayValue(cntTransfertForm).setText(((Plafond) arrayList2.get(0)).TransferDaily);
        this.uiManager.stateMachine.findLblWeekValue(cntTransfertForm).setText(((Plafond) arrayList2.get(0)).TransferMax);
        this.uiManager.stateMachine.findLblDay(cntTransfertForm).setText("Plafond quotidien: ");
        this.uiManager.stateMachine.findLblWeek(cntTransfertForm).setText("Plafond par opération: ");
        cntBtns = new Container(new BoxLayout(2));
        Button button3 = new Button(" Ajouter un Bénéficiaire ".toUpperCase());
        Button button4 = new Button(" gérer mes bénéficiaires ".toUpperCase());
        cntBtns.setUIID("WhitCnt_PaddingLeftRigt_2");
        this.uiManager.ControlTextfield(this.uiManager.stateMachine.findTxtAmount(cntTransfertForm), "AMOUNT", cntTransfertForm);
        if (arrayList3.size() > 0 && arrayList.size() > 0) {
            this.uiManager.stateMachine.findBtnTransferValidation(cntTransfertForm).addActionListener(new 3(DrawListContainerTransfer));
        } else {
            ((Container) cntTransfertForm.getComponentAt(0)).removeComponent(((Container) cntTransfertForm.getComponentAt(0)).getComponentAt(6));
            cntTransfertForm.revalidate();
        }
        button3.addActionListener(new 4());
        button4.addActionListener(new 5());
        cntTransfertForm.setScrollVisible(false);
        cntBtns.add(CIHStyles.setButtonStyle1(button3, this.uiManager.ressource.getImage("add_beneficiaires2.png"))).add(CIHStyles.setButtonStyle1(button4, this.uiManager.ressource.getImage("manage_beneficiaires2.png")));
        Container container3 = (Container) this.uib.findByName("AddBen", cntTransfertForm);
        ((Container) this.uib.findByName("BlankCnt", cntTransfertForm)).setPreferredH(cntTransfertForm.getPreferredH());
        container3.addComponent(cntBtns);
        container3.revalidate();
        return cntTransfertForm;
    }

    class 1 implements DataChangedListener {
        1() {
        }

        public void dataChanged(int i, int i2) {
            TransferNewPage.this.uiManager.stateMachine.findTxtMotif(TransferNewPage.cntTransfertForm).setText(TransferNewPage.this.removeSpecialCharacters(TransferNewPage.this.uiManager.stateMachine.findTxtMotif(TransferNewPage.cntTransfertForm).getText()));
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$issuarAccount;

        2(Container container) {
            this.val$issuarAccount = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                String GetCurrentIssuarAccountNumber = TransferNewPage.this.GetCurrentIssuarAccountNumber((Container) ((Tabs) this.val$issuarAccount.getComponentAt(2)).getTabComponentAt(((Tabs) this.val$issuarAccount.getComponentAt(2)).getSelectedIndex()));
                TransferNewPage transferNewPage = TransferNewPage.this;
                TransferNewPage.access$002(transferNewPage, transferNewPage.getAccountBeneficiary(GetCurrentIssuarAccountNumber));
                TransferNewPage transferNewPage2 = TransferNewPage.this;
                transferNewPage2.benefForm = TransferNewPage.access$100(transferNewPage2);
                TransferNewPage.cntBtns.setHidden(true);
                TransferNewPage.isBenefPageShow = true;
                TransferNewPage.this.SwitchBenefForms(TransferNewPage.cntTransfertForm, TransferNewPage.this.uiManager.stateMachine.findCntGlobalTransfer(TransferNewPage.cntTransfertForm), TransferNewPage.this.benefForm, false);
            }
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Container val$issuarAccount;

        3(Container container) {
            this.val$issuarAccount = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (TransferNewPage.this.uiManager.stateMachine.findTxtAmount(TransferNewPage.cntTransfertForm).getText().length() == 0 || TransferNewPage.this.uiManager.stateMachine.findTxtMotif(TransferNewPage.cntTransfertForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferNewPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                    return;
                }
                TransferNewPage transferNewPage = TransferNewPage.this;
                TransactionResume GetTransaction = transferNewPage.GetTransaction(this.val$issuarAccount, transferNewPage.uiManager.stateMachine.findTxtAmount(TransferNewPage.cntTransfertForm), TransferNewPage.this.uiManager.stateMachine.findTxtMotif(TransferNewPage.cntTransfertForm));
                if (!GetTransaction.CreditedAccountNumber.startsWith("230")) {
                    TransferNewPage.access$200(TransferNewPage.this, GetTransaction);
                    return;
                }
                TransferNewPage.access$302(TransferNewPage.this, TransfertTypeEmun.CLASSIQUE);
                TransferNewPage transferNewPage2 = TransferNewPage.this;
                TransferNewPage.access$400(transferNewPage2, GetTransaction, transferNewPage2.uiManager.stateMachine.findCntGlobalTransfer(TransferNewPage.cntTransfertForm), null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferNewPage.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                TransferNewPage.this.uiManager.NavigateToPageById(72);
            }
        }
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                TransferNewPage.this.uiManager.NavigateToPageById(51);
            }
        }
    }

    public TransactionResume GetTransaction(Container container, TextArea textArea, TextArea textArea2) {
        TransactionResume transactionResume = new TransactionResume();
        transactionResume.Amount = textArea.getText();
        transactionResume.Motif = textArea2.getText();
        transactionResume.PassHB = "";
        Container container2 = (Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0);
        transactionResume.IssuerAccountOwner = ((Label) ((Container) ((Container) ((Container) container2.getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getText();
        Account account = (Account) ((B3gService) container2.getClientProperty("AccountClient"));
        transactionResume.IssuerAccountNumber = account.rib;
        transactionResume.isTutor = "0";
        if (account.legalSituation.equals("MI")) {
            transactionResume.isTutor = "1";
        }
        if (BENEFCNT.getComponentCount() > 0) {
            transactionResume.CreditedAccountAlias = this.CreditedAccountAliasNew;
            transactionResume.CreditedAccountNumber = StringTools.RibFormatWithoutSP(this.CreditedAccountNumberNew);
            transactionResume.field1 = "";
            transactionResume.field2 = "";
            transactionResume.TypeOperation = "TRANSFER";
        } else {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Veuillez choisir le beneficiaire", null);
        }
        return transactionResume;
    }

    public ArrayList AddCurrentClientAccount(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().size() > 0) {
            for (int i = 0; i < CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().size(); i++) {
                Beneficiary beneficiary = new Beneficiary();
                beneficiary.SelectedId = ((Account) CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().get(i)).rib;
                beneficiary.adress = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().adress;
                beneficiary.alias = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom;
                beneficiary.city = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().adress;
                beneficiary.date = "-";
                beneficiary.flag = "false";
                beneficiary.lastName = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom;
                beneficiary.cardNumber = ((Account) CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().get(i)).rib;
                beneficiary.balance = "-";
                beneficiary.identity = "-";
                beneficiary.postalCode = "-";
                beneficiary.firstName = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom;
                beneficiary.radical = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical;
                beneficiary.typeBenef = "TRANSFER";
                beneficiary.typeBenefVir = "TRANSFER";
                if (!((Account) CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().get(i)).rib.substring(13, 17).equals("2310") && CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().size() > 1) {
                    arrayList2.add(beneficiary);
                }
            }
            arrayList2.addAll(arrayList);
        }
        if (CihMBanking.sesPMTR.getSessionClient().getClient_BeneficiaryList() == null) {
            CihMBanking.sesPMTR.getSessionClient().setClient_BeneficiaryList(arrayList2);
        }
        return arrayList2;
    }

    public ArrayList RemoveFirstCurrentClientAccount(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(arrayList);
        if (CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().size() > 0 && arrayList2.size() > 0) {
            boolean z = true;
            for (int i = 0; i < arrayList2.size() && z; i++) {
                if (((Account) CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().get(0)).rib.equals(((Beneficiary) arrayList2.get(i)).SelectedId)) {
                    arrayList2.remove(i);
                    z = false;
                }
            }
        }
        return arrayList2;
    }

    public void GetSavingAccountFromBeneficiaryList(ArrayList arrayList, ArrayList arrayList2) {
        if (CheckSavingAccountExisted(arrayList2)) {
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (((Beneficiary) arrayList.get(i)).cardNumber.length() == 24 && ((Beneficiary) arrayList.get(i)).cardNumber.substring(6, 13).equals(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical) && ((Beneficiary) arrayList.get(i)).cardNumber.substring(13, 17).equals("2310")) {
                Account account = new Account();
                account.accountNumber = ((Beneficiary) arrayList.get(i)).cardNumber.substring(6, 22);
                account.rib = ((Beneficiary) arrayList.get(i)).cardNumber;
                account.SelectedId = ((Beneficiary) arrayList.get(i)).cardNumber;
                account.balanceBrut = ((Beneficiary) arrayList.get(i)).balance;
                account.balance = DataTools.FormatAmountWithCurrency(((Beneficiary) arrayList.get(i)).balance, "MAD");
                arrayList2.add(account);
            }
        }
    }

    public boolean CheckSavingAccountExisted(ArrayList arrayList) {
        boolean z = false;
        for (int i = 0; i < arrayList.size() && !z; i++) {
            if (((Account) arrayList.get(i)).rib.substring(13, 17).equals("2310")) {
                z = true;
            }
        }
        return z;
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

    public Container GetTransferSecurityForm(Container container) {
        cntBtns.setHidden(true);
        isOtpPageShow = true;
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
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 7());
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 8(b3GCIHEcode));
        return createContainer;
    }

    class 7 implements ActionListener {
        7() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransferNewPage.this.uiManager.NavigateToPage(new TransferNewPage(TransferNewPage.this.uiManager, null, 150));
            TransferNewPage.cntBtns.setHidden(false);
            TransferNewPage.isOtpPageShow = false;
            TransferNewPage.isBenefPageShow = false;
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        8(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
            TransferNewPage.access$502(TransferNewPage.this, this.val$eCode1.getValue());
            sessionCurrentTransactionResume.PassHB = TransferNewPage.access$500(TransferNewPage.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (TransferNewPage.access$500(TransferNewPage.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferNewPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
            } else if (TransferNewPage.access$300(TransferNewPage.this) == TransfertTypeEmun.INSTANTANE) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(98, sessionCurrentTransactionResume, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 300019);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(10, sessionCurrentTransactionResume, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 300013);
            }
        }
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

    public void SwitchBenefForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 0));
        container.revalidate();
    }

    ArrayList getFirstAccountBeneficiary(ArrayList arrayList, ArrayList arrayList2) {
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            return new ArrayList();
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

    public Container DrawAcquiredAcc(String str, String str2) {
        Container GetContainerNw = this.uiManager.GetContainerNw("GloabalContainerV2", "Compte à créditer");
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AccountItemBenefTransfertV2");
        this.uiManager.stateMachine.findLblAccountOwner(createContainer).setText(str);
        this.uiManager.stateMachine.findLblAccountRibValue(createContainer).setText(StringTools.RibFormatWithSP(str2));
        GetContainerNw.addComponent(createContainer);
        Settings.setNightMode(GetContainerNw, 0);
        return GetContainerNw;
    }

    ArrayList getAccountBeneficiary(String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountListVirm());
        arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getListRelationAccount());
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Account account = (Account) it.next();
            if (account.accountNumber.equals(str)) {
                Iterator it2 = account.accountBeneficiaries.iterator();
                while (it2.hasNext()) {
                    Beneficiary beneficiary = (Beneficiary) it2.next();
                    if (beneficiary.serviceId.equals("300013")) {
                        arrayList.add(beneficiary);
                    }
                }
            }
        }
        Collections.sort(arrayList, new SortBenifByAlias());
        return arrayList;
    }

    public String GetCurrentIssuarAccountNumber(Container container) {
        return ((Account) ((B3gService) ((Container) container.getComponentAt(0)).getClientProperty("AccountClient"))).accountNumber;
    }

    private Container getBenefForm() {
        Container container = new Container(new BorderLayout());
        Image image = this.uiManager.ressource.getImage("circle.png");
        this.circleLineImage = this.uiManager.ressource.getImage("circle-line.png");
        this.circleMask = image.createMask();
        this.circleMaskWidth = image.getWidth();
        this.circleMaskHeight = image.getHeight();
        Font createTrueTypeFont = Font.createTrueTypeFont("native:MainRegular", "native:MainRegular");
        this.letterFont = createTrueTypeFont;
        int i = this.circleMaskHeight;
        this.letterFont = createTrueTypeFont.derive(i - (i / 2), 0);
        Container container2 = new Container(new BorderLayout());
        Container container3 = new Container(new BorderLayout());
        container2.add("Center", container3);
        Container container4 = new Container(new FlowLayout());
        container2.add("East", container4);
        container4.add(new Container(new BorderLayout()));
        container.add("Center", container2);
        new Label("Chargement des contacts...").setUIID("lbl_aveta_small_black");
        CN.scheduleBackgroundTask(new TransferNewPage$$ExternalSyntheticLambda2(this, container3));
        Container container5 = new Container(BoxLayout.y());
        TextField textField = new TextField("", "Rechercher");
        textField.setUIID("Container");
        textField.getAllStyles().setAlignment(1);
        textField.getAllStyles().setMarginUnit(1);
        textField.getAllStyles().setMargin(0, 0, 6, 6);
        textField.setHintIcon(this.uiManager.ressource.getImage("search_hint.png"));
        container5.add(textField);
        container5.setUIID("tr_txtAmount");
        container5.getAllStyles().setMarginUnit(1);
        container5.getAllStyles().setMargin(0, 0, 2, 2);
        container5.getAllStyles().setPaddingUnit(1);
        container5.getAllStyles().setPadding(1.5f, 1.5f, 1.0f, 1.0f);
        textField.addDataChangedListener(new TransferNewPage$$ExternalSyntheticLambda3(this, textField));
        Label label = new Label("Sélectionner un bénéficiaire", "DefaultTitleOrange");
        label.getAllStyles().setPaddingUnit(1);
        label.getAllStyles().setPadding(1, 1, 1, 1);
        container.add("North", BoxLayout.encloseY(label, container5));
        return container;
    }

    class 9 implements Comparator {
        9() {
        }

        public int compare(Beneficiary beneficiary, Beneficiary beneficiary2) {
            return beneficiary.alias.trim().compareTo(beneficiary2.alias.trim());
        }
    }

    /* synthetic */ void lambda$getBenefForm$0$com-b3g-ui-page-TransferNewPage(Container container) {
        try {
            Collections.sort(this.beneficiaryList, new 9());
        } catch (Exception unused) {
        }
        createGenericListCellRendererModelData(this.beneficiaryList);
        prepareContactsList(this.beneficiaryList, CihMBanking.sesPMTR.ContactsData, container);
    }

    /* synthetic */ void lambda$getBenefForm$1$com-b3g-ui-page-TransferNewPage(TextField textField, int i, int i2) {
        List list = this.list;
        list.scrollRectToVisible(0, 0, 0, 0, list.getParent());
        this.filter.filter(textField.getText());
    }

    public void prepareContactsList(ArrayList arrayList, Map[] mapArr, Container container) {
        prepareScrollIndex(arrayList);
        this.filter = new ContactsFilterProxyListModel(new DefaultListModel(mapArr));
        List list = new List(this.filter);
        this.list = list;
        list.setRenderer(new GenericListCellRenderer(createGenericRendererContainer(), createGenericRendererContainer()));
        this.list.setScrollVisible(false);
        this.list.addActionListener(new TransferNewPage$$ExternalSyntheticLambda0(this));
        CN.callSerially(new TransferNewPage$$ExternalSyntheticLambda1(this, container));
    }

    /* synthetic */ void lambda$prepareContactsList$2$com-b3g-ui-page-TransferNewPage(ActionEvent actionEvent) {
        BENEFCNT.removeAll();
        for (int i = 0; i < Glob.getComponentCount(); i++) {
            Glob.getComponentAt(i).setUIID("Container");
            ((Container) Glob.getComponentAt(i)).revalidate();
        }
        HashMap hashMap = (HashMap) this.list.getSelectedItem();
        this.CreditedAccountNumberNew = (String) hashMap.get("Phone");
        String str = (String) hashMap.get("Name");
        this.CreditedAccountAliasNew = str;
        BENEFCNT.addComponent(DrawAcquiredAcc(str, this.CreditedAccountNumberNew));
        BENEFCNT.revalidate();
        cntTransfertForm.setScrollableY(true);
        for (int i2 = 0; i2 < this.uiManager.stateMachine.findCntGlobalTransfer(cntTransfertForm).getComponentCount(); i2++) {
            if (!this.uiManager.stateMachine.findCntGlobalTransfer(cntTransfertForm).getComponentAt(i2).getName().equals("CntAccountChosen")) {
                this.uiManager.stateMachine.findCntGlobalTransfer(cntTransfertForm).getComponentAt(i2).setHidden(false);
            }
        }
        cntTransfertForm.revalidate();
        cntBtns.setHidden(false);
        isBenefPageShow = false;
        SwitchBenefForms(cntTransfertForm, this.benefForm, this.uiManager.stateMachine.findCntGlobalTransfer(cntTransfertForm), false);
    }

    /* synthetic */ void lambda$prepareContactsList$3$com-b3g-ui-page-TransferNewPage(Container container) {
        container.removeAll();
        container.add("Center", this.list);
        container.revalidate();
    }

    private Object[] createGenericListCellRendererModelData(ArrayList arrayList) {
        Map[] mapArr = new HashMap[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            Map hashMap = new HashMap();
            mapArr[i] = hashMap;
            hashMap.put("Name", ((Beneficiary) arrayList.get(i)).alias.trim());
            mapArr[i].put("Phone", ((Beneficiary) arrayList.get(i)).cardNumber);
            String iconFromCodeBanque = DataTools.getIconFromCodeBanque(((Beneficiary) arrayList.get(i)).cardNumber.substring(0, 3));
            Image image = this.uiManager.ressource.getImage(iconFromCodeBanque);
            mapArr[i].put("Image", getIcon(this.uiManager.ressource.getImage(iconFromCodeBanque).scaled((int) (image.getWidth() * 0.8d), (int) (image.getHeight() * 0.8d))));
        }
        CihMBanking.sesPMTR.ContactsData = mapArr;
        return CihMBanking.sesPMTR.ContactsData;
    }

    public Image getIcon(Image image) {
        Image createImage = Image.createImage(this.circleMaskWidth, this.circleMaskHeight, 0);
        Graphics graphics = createImage.getGraphics();
        graphics.setColor(16777215);
        graphics.fillArc(1, 1, this.circleMaskWidth - 2, this.circleMaskHeight - 2, 0, 360);
        graphics.drawImage(image, (createImage.getWidth() / 2) - (image.getWidth() / 2), (createImage.getHeight() / 2) - (image.getHeight() / 2));
        graphics.drawImage(this.circleLineImage, 0, 0);
        return createImage;
    }

    private Container createGenericRendererContainer() {
        Container container = new Container(new BorderLayout());
        container.setUIID("ContactsStyle");
        Container container2 = new Container(BoxLayout.y());
        container2.setUIID("margin_0_0_1_0");
        Label label = new Label();
        label.setName("Name");
        label.setUIID("g_lblDashBoardValue");
        Label label2 = new Label();
        label2.setName("Phone");
        label2.setUIID("g_lblDashBoardTitleBlue");
        container2.add(label).add(label2);
        container.add("Center", Container.encloseIn(new FlowLayout(1, 4), container2));
        Label label3 = new Label();
        label3.setName("Image");
        label3.getIcon();
        new Container();
        container.add("West", label3);
        container.setLeadComponent(new Button());
        label3.getAllStyles().setPaddingUnit(1);
        label3.getAllStyles().setPadding(0.0f, 0.0f, 1.0f, 2.5f);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1, 1, 1, 1);
        return container;
    }

    public void prepareScrollIndex(ArrayList arrayList) {
        this.index = new HashMap();
        for (int i = 1; i < arrayList.size(); i++) {
            char charAt = ((Beneficiary) arrayList.get(i)).alias.trim().charAt(0);
            if ((charAt > 'A' || charAt < '[') && (charAt > 'a' || charAt < 'z')) {
                this.index.put(Character.valueOf(charAt), Integer.valueOf(i));
                for (int i2 = i + 1; i2 < arrayList.size(); i2++) {
                    if (((Beneficiary) arrayList.get(i2)).alias.trim().charAt(0) != charAt) {
                        char charAt2 = ((Beneficiary) arrayList.get(i2)).alias.trim().charAt(0);
                        this.index.put(Character.valueOf(charAt2), Integer.valueOf(i2));
                        charAt = charAt2;
                    }
                }
                return;
            }
        }
    }

    private ArrayList filtreBeneficiaryListByActivation(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            if (!((Beneficiary) arrayList.get(i)).statusActivation.equals("1")) {
                arrayList2.add((Beneficiary) arrayList.get(i));
            }
        }
        return arrayList2;
    }

    private void showSelectTypeTransfertDlg(TransactionResume transactionResume) {
        Dialog dialog = this.dlg;
        if (dialog != null) {
            dialog.showAtPosition(0, 0, 0, 0, true);
            return;
        }
        Dialog dialog2 = new Dialog();
        this.dlg = dialog2;
        dialog2.setLayout(new BorderLayout());
        this.dlg.getDialogStyle().setBgColor(16777215);
        this.dlg.getDialogStyle().setBgTransparency(255);
        this.dlg.getAllStyles().setBgColor(16777215);
        this.dlg.getAllStyles().setBgTransparency(255);
        CIHStyles cIHStyles = new CIHStyles();
        Container container = new Container(new BorderLayout());
        Label label = new Label("Type de virement", "LblOrange");
        label.getAllStyles().setPaddingUnit(1);
        label.getAllStyles().setPadding(2, 4, 1, 1);
        label.getAllStyles().setMarginUnit(1);
        label.getAllStyles().setMargin(2, 3, 1, 1);
        Label label2 = new Label(this.uiManager.ressource.getImage("instantane2.png"));
        label2.setAlignment(4);
        SpanLabel spanLabel = new SpanLabel();
        SpanLabel spanLabel2 = new SpanLabel();
        SpanLabel spanLabel3 = new SpanLabel();
        SpanLabel spanLabel4 = new SpanLabel();
        Container container2 = new Container(BoxLayout.y());
        container2.add(label2);
        container2.getAllStyles().setMarginUnit(1);
        container2.getAllStyles().setMargin(3, 0, 0, 0);
        Button button = new Button(" Virement Instantané");
        button.setUIID("ac_lblItemDetailValueBoldRed");
        button.getAllStyles().setPaddingUnit(1);
        button.getAllStyles().setPadding(1, 0, 2, 0);
        button.setIcon(this.uiManager.ressource.getImage("iconsstopwatchgris.png"));
        spanLabel.setText("Délai de 30 secondes");
        spanLabel2.setText("Coût : 16.50 Dh TTC");
        Container buttonstyleCornerOrangeMultiButton = cIHStyles.getButtonstyleCornerOrangeMultiButton(button, spanLabel, spanLabel2);
        Button button2 = (Button) buttonstyleCornerOrangeMultiButton.getComponentAt(0);
        SpanLabel spanLabel5 = (SpanLabel) buttonstyleCornerOrangeMultiButton.getComponentAt(1);
        SpanLabel spanLabel6 = (SpanLabel) buttonstyleCornerOrangeMultiButton.getComponentAt(2);
        button2.getAllStyles().setPaddingUnit(1);
        button2.getAllStyles().setPadding(1, 0, 2, 0);
        Button button3 = new Button(" Virement Classique");
        button3.setUIID("ac_lblItemDetailValueBoldRed");
        button3.setIcon(this.uiManager.ressource.getImage("Virements.png"));
        spanLabel3.setText("Délai de deux jours ouvrés");
        spanLabel4.setText("Coût : 11 Dh TTC");
        Container buttonstyleCornerOrangeMultiButton2 = cIHStyles.getButtonstyleCornerOrangeMultiButton(button3, spanLabel3, spanLabel4);
        Button button4 = (Button) buttonstyleCornerOrangeMultiButton2.getComponentAt(0);
        SpanLabel spanLabel7 = (SpanLabel) buttonstyleCornerOrangeMultiButton2.getComponentAt(1);
        SpanLabel spanLabel8 = (SpanLabel) buttonstyleCornerOrangeMultiButton2.getComponentAt(2);
        button4.getAllStyles().setPaddingUnit(1);
        button4.getAllStyles().setPadding(1, 0, 2, 0);
        spanLabel5.getAllStyles().setPaddingUnit(1);
        spanLabel5.getAllStyles().setPadding(1.3f, 0.0f, 1.0f, 1.0f);
        spanLabel6.getAllStyles().setPaddingUnit(1);
        spanLabel6.getAllStyles().setPadding(0, 0, 1, 1);
        spanLabel7.getAllStyles().setPaddingUnit(1);
        spanLabel7.getAllStyles().setPadding(1.3f, 0.0f, 1.0f, 1.0f);
        spanLabel8.getAllStyles().setPaddingUnit(1);
        spanLabel8.getAllStyles().setPadding(0, 0, 1, 1);
        spanLabel5.setTextUIID("g_lblNotifVirInst");
        spanLabel6.setTextUIID("g_lblNotifVirInst");
        spanLabel7.setTextUIID("g_lblNotifVirInst");
        spanLabel8.setTextUIID("g_lblNotifVirInst");
        spanLabel5.getAllStyles().setFgColor(CIHStyles.colorGris);
        spanLabel6.getAllStyles().setFgColor(CIHStyles.colorGris);
        spanLabel7.getAllStyles().setFgColor(CIHStyles.colorGris);
        spanLabel8.getAllStyles().setFgColor(CIHStyles.colorGris);
        Button button5 = new Button("CONTINUER");
        button5.setUIID("op_BtnOppositionValidationgrisV2");
        button5.setEnabled(false);
        button5.getAllStyles().setMarginUnit(1);
        button5.getAllStyles().setMargin(0, 1, 0, 0);
        Button button6 = new Button("RETOUR");
        button6.setUIID("op_BtnOppositionValidationV2");
        container2.add(buttonstyleCornerOrangeMultiButton).add(buttonstyleCornerOrangeMultiButton2);
        button.addActionListener(new 10(button5, button2, button4, spanLabel5, spanLabel6, spanLabel7, spanLabel8, buttonstyleCornerOrangeMultiButton2, buttonstyleCornerOrangeMultiButton, container));
        button3.addActionListener(new 11(button5, button2, button4, spanLabel7, spanLabel8, spanLabel5, spanLabel6, buttonstyleCornerOrangeMultiButton, buttonstyleCornerOrangeMultiButton2, container));
        button5.addActionListener(new 12(transactionResume, container));
        button6.addActionListener(new 13());
        Container container3 = new Container(new GridLayout(2, 1));
        container3.getAllStyles().setMarginUnit(1);
        container3.getAllStyles().setMargin(2.0f, 1.0f, 1.0f, 1.0f);
        container3.add(button5);
        container3.add(button6);
        container.add("North", label);
        container.add("Center", container2);
        container.add("South", container3);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(0, 1, 1, 1);
        this.dlg.add("Center", container);
        this.dlg.showAtPosition(0, 0, 0, 0, true);
    }

    class 10 implements ActionListener {
        final /* synthetic */ Button val$btnActinst;
        final /* synthetic */ Button val$btnClasAct;
        final /* synthetic */ Button val$btnCont;
        final /* synthetic */ Container val$classiqueBtnCnt;
        final /* synthetic */ Container val$instantaneBtnCnt;
        final /* synthetic */ Container val$mainCnt;
        final /* synthetic */ SpanLabel val$p1fromCnt;
        final /* synthetic */ SpanLabel val$p2FromCnt;
        final /* synthetic */ SpanLabel val$p3fromCnt;
        final /* synthetic */ SpanLabel val$p4FromCnt;

        10(Button button, Button button2, Button button3, SpanLabel spanLabel, SpanLabel spanLabel2, SpanLabel spanLabel3, SpanLabel spanLabel4, Container container, Container container2, Container container3) {
            this.val$btnCont = button;
            this.val$btnActinst = button2;
            this.val$btnClasAct = button3;
            this.val$p1fromCnt = spanLabel;
            this.val$p2FromCnt = spanLabel2;
            this.val$p3fromCnt = spanLabel3;
            this.val$p4FromCnt = spanLabel4;
            this.val$classiqueBtnCnt = container;
            this.val$instantaneBtnCnt = container2;
            this.val$mainCnt = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransferNewPage.access$302(TransferNewPage.this, TransfertTypeEmun.INSTANTANE);
            this.val$btnCont.setEnabled(true);
            this.val$btnCont.setUIID("op_BtnOppositionValidationV2");
            this.val$btnCont.getAllStyles().setMarginUnit(1);
            this.val$btnCont.getAllStyles().setMargin(0, 1, 0, 0);
            this.val$btnActinst.setIcon(TransferNewPage.this.uiManager.ressource.getImage("iconsstopwatch.png"));
            this.val$btnClasAct.setIcon(TransferNewPage.this.uiManager.ressource.getImage("Virements.png"));
            this.val$btnClasAct.getAllStyles().setFgColor(CIHStyles.colorGris);
            this.val$btnActinst.getAllStyles().setFgColor(CIHStyles.colorOrange);
            this.val$p1fromCnt.getAllStyles().setFgColor(CIHStyles.colorBlack);
            this.val$p2FromCnt.getAllStyles().setFgColor(CIHStyles.colorBlack);
            this.val$p3fromCnt.getAllStyles().setFgColor(CIHStyles.colorGris);
            this.val$p4FromCnt.getAllStyles().setFgColor(CIHStyles.colorGris);
            this.val$classiqueBtnCnt.getAllStyles().setBorder(Border.createRoundBorder(1, 2, CIHStyles.colorGris));
            this.val$instantaneBtnCnt.getAllStyles().setBorder(Border.createRoundBorder(1, 2, CIHStyles.colorOrange));
            this.val$mainCnt.revalidate();
        }
    }

    class 11 implements ActionListener {
        final /* synthetic */ Button val$btnActinst;
        final /* synthetic */ Button val$btnClasAct;
        final /* synthetic */ Button val$btnCont;
        final /* synthetic */ Container val$classiqueBtnCnt;
        final /* synthetic */ Container val$instantaneBtnCnt;
        final /* synthetic */ Container val$mainCnt;
        final /* synthetic */ SpanLabel val$p1fromCnt;
        final /* synthetic */ SpanLabel val$p2FromCnt;
        final /* synthetic */ SpanLabel val$p3fromCnt;
        final /* synthetic */ SpanLabel val$p4FromCnt;

        11(Button button, Button button2, Button button3, SpanLabel spanLabel, SpanLabel spanLabel2, SpanLabel spanLabel3, SpanLabel spanLabel4, Container container, Container container2, Container container3) {
            this.val$btnCont = button;
            this.val$btnActinst = button2;
            this.val$btnClasAct = button3;
            this.val$p3fromCnt = spanLabel;
            this.val$p4FromCnt = spanLabel2;
            this.val$p1fromCnt = spanLabel3;
            this.val$p2FromCnt = spanLabel4;
            this.val$instantaneBtnCnt = container;
            this.val$classiqueBtnCnt = container2;
            this.val$mainCnt = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransferNewPage.access$302(TransferNewPage.this, TransfertTypeEmun.CLASSIQUE);
            this.val$btnCont.setEnabled(true);
            this.val$btnCont.setUIID("op_BtnOppositionValidationV2");
            this.val$btnCont.getAllStyles().setMarginUnit(1);
            this.val$btnCont.getAllStyles().setMargin(0, 1, 0, 0);
            this.val$btnActinst.setIcon(TransferNewPage.this.uiManager.ressource.getImage("iconsstopwatchgris.png"));
            this.val$btnClasAct.setIcon(TransferNewPage.this.uiManager.ressource.getImage("Virements_w.png"));
            this.val$btnActinst.getAllStyles().setFgColor(CIHStyles.colorGris);
            this.val$btnClasAct.getAllStyles().setFgColor(CIHStyles.colorOrange);
            this.val$p3fromCnt.getAllStyles().setFgColor(CIHStyles.colorBlack);
            this.val$p4FromCnt.getAllStyles().setFgColor(CIHStyles.colorBlack);
            this.val$p1fromCnt.getAllStyles().setFgColor(CIHStyles.colorGris);
            this.val$p2FromCnt.getAllStyles().setFgColor(CIHStyles.colorGris);
            this.val$instantaneBtnCnt.getAllStyles().setBorder(Border.createRoundBorder(1, 2, CIHStyles.colorGris));
            this.val$classiqueBtnCnt.getAllStyles().setBorder(Border.createRoundBorder(1, 2, CIHStyles.colorOrange));
            this.val$mainCnt.revalidate();
        }
    }

    class 12 implements ActionListener {
        final /* synthetic */ Container val$mainCnt;
        final /* synthetic */ TransactionResume val$tranc;

        12(TransactionResume transactionResume, Container container) {
            this.val$tranc = transactionResume;
            this.val$mainCnt = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransferNewPage transferNewPage = TransferNewPage.this;
            TransferNewPage.access$400(transferNewPage, this.val$tranc, this.val$mainCnt, TransferNewPage.access$600(transferNewPage));
        }
    }

    class 13 implements ActionListener {
        13() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransferNewPage.access$600(TransferNewPage.this).dispose();
        }
    }

    private void executeSimulVirement(TransactionResume transactionResume, Container container, Dialog dialog) {
        ServiceResponse sendOtpProcessTransfer;
        if (dialog != null) {
            dialog.dispose();
        }
        if (this.selectedTypeTransfert == TransfertTypeEmun.INSTANTANE) {
            String str = transactionResume.Amount;
            if ((Display.getInstance().getPlatformName().equals("ios") || Display.getInstance().isSimulator()) && str.contains(",")) {
                str = StringUtil.replaceAll(str, ",", ".");
            }
            sendOtpProcessTransfer = ServiceManager.sendOtpProcessTransfer(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1017", 300019, "1", "0", transactionResume.IssuerAccountNumber, transactionResume.CreditedAccountNumber, str, transactionResume.isTutor, transactionResume.CreditedAccountAlias, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).getText());
        } else {
            sendOtpProcessTransfer = ServiceManager.sendOtpProcessTransfer(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1017", 300015, "1", "0", transactionResume.IssuerAccountNumber, transactionResume.CreditedAccountNumber, transactionResume.Amount, transactionResume.isTutor, transactionResume.CreditedAccountAlias, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).getText());
        }
        if (sendOtpProcessTransfer.getStatusCode().equals("000")) {
            CihMBanking.sesPMTR.setSessionCurrentTransactionResume(transactionResume);
            CihMBanking.sesPMTR.setSessionSavedContainer(container);
            SwitchTransfertForms(cntTransfertForm, this.uiManager.stateMachine.findCntGlobalTransfer(cntTransfertForm), GetTransferSecurityForm(cntTransfertForm), false);
            return;
        }
        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcessTransfer.getStatusLabel()), null);
    }

    public String removeSpecialCharacters(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if ((charAt >= 'A' && charAt <= 'Z') || ((charAt >= 'a' && charAt <= 'z') || ((charAt >= '0' && charAt <= '9') || charAt == ' ' || charAt == '.' || charAt == ','))) {
                sb.append(charAt);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Le motif ne doit pas contenir de caractères spéciaux (@#&%!=?+-*/\\)", null);
            }
        }
        return sb.toString();
    }
}
