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
import com.b3g.tools.DataTools;
import com.b3g.tools.SortBenifByAlias;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordion;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TransferNewPageOld extends B3GPage {
    public static B3gAccordion A;
    public static Container BENEFCNT;
    public static Container Glob;
    public static Container cntBtns;
    public static Container cntTransfertForm;
    public static boolean isOtpPageShow;
    public static boolean selectedBenef;
    public static Beneficiary selectedBeneficiary;
    private String VnewaliasText;
    TransfertDATA trDT;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();
    private boolean isOvp = false;

    static /* synthetic */ String access$000(TransferNewPageOld transferNewPageOld) {
        return transferNewPageOld.VnewaliasText;
    }

    static /* synthetic */ String access$002(TransferNewPageOld transferNewPageOld, String str) {
        transferNewPageOld.VnewaliasText = str;
        return str;
    }

    public TransferNewPageOld(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        isOtpPageShow = false;
        TopUpNewPage.isOtpPageShow = false;
        TransfertWallet.isOtpPageShow = false;
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setUIID("mn_cntMenuItemV2");
        this.thisContainer.setName("TransfertNewPage");
        try {
            CihMBanking.sesPMTR.setToken("");
            this.trDT = new TransfertDATA().TransfertDATAProcess(28);
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(GetContainerTransferForm(this.trDT.getBeneficiaryList(), this.trDT.getbeneficiaryStatusLabel(), this.trDT.getPlafond()), "VIREMENT"));
            arrayList.add(new B3gContainer(GetContainerTransferHistoric(this.trDT.getTransferHistoricList(), this.trDT), "HISTORIQUE"));
            this.uiManager.DrawTabsWithNavigationV3(container, arrayList, cntBtns, this.uiManager.stateMachine.findTxtAmount(cntTransfertForm), this.uiManager.stateMachine.findTxtMotif(cntTransfertForm), this.txtHBPassWord);
            this.thisContainer.add(container);
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
        for (int i = 0; i < arrayList.size(); i++) {
            ((TransferHistoric) arrayList.get(i)).groupKey = ((TransferHistoric) arrayList.get(i)).OperationDate.substring(0, 10);
            arrayList2.add((B3gService) arrayList.get(i));
        }
        Container container = new Container(new BoxLayout(2));
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
        button2.addActionListener(new 1());
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
            this.uiManager.stateMachine.findBtnTransferValidation(cntTransfertForm).addActionListener(new 2(DrawListContainerTransfer));
        } else {
            ((Container) cntTransfertForm.getComponentAt(0)).removeComponent(((Container) cntTransfertForm.getComponentAt(0)).getComponentAt(6));
            cntTransfertForm.revalidate();
        }
        button3.addActionListener(new 3());
        button4.addActionListener(new 4());
        cntTransfertForm.setScrollVisible(false);
        cntBtns.add(CIHStyles.setButtonStyle1(button3, this.uiManager.ressource.getImage("add_beneficiaires2.png"))).add(CIHStyles.setButtonStyle1(button4, this.uiManager.ressource.getImage("manage_beneficiaires2.png")));
        Container container3 = (Container) this.uib.findByName("AddBen", cntTransfertForm);
        ((Container) this.uib.findByName("BlankCnt", cntTransfertForm)).setPreferredH(cntTransfertForm.getPreferredH());
        container3.addComponent(cntBtns);
        container3.revalidate();
        if (selectedBenef) {
            BENEFCNT.addComponent(DrawAcquiredAcc(selectedBeneficiary.alias, selectedBeneficiary.cardNumber));
            BENEFCNT.revalidate();
            cntTransfertForm.setScrollableY(true);
            for (int i3 = 0; i3 < this.uiManager.stateMachine.findCntGlobalTransfer(cntTransfertForm).getComponentCount(); i3++) {
                if (!this.uiManager.stateMachine.findCntGlobalTransfer(cntTransfertForm).getComponentAt(i3).getName().equals("CntAccountChosen")) {
                    this.uiManager.stateMachine.findCntGlobalTransfer(cntTransfertForm).getComponentAt(i3).setHidden(false);
                }
            }
            cntTransfertForm.revalidate();
            selectedBenef = false;
        }
        return cntTransfertForm;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                TransferNewPageOld.this.uiManager.NavigateToPage(new ContactsPage(TransferNewPageOld.this.uiManager, TransferNewPageOld.this.trDT, 150));
            }
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$issuarAccount;

        2(Container container) {
            this.val$issuarAccount = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (TransferNewPageOld.this.uiManager.stateMachine.findTxtAmount(TransferNewPageOld.cntTransfertForm).getText().length() == 0 || TransferNewPageOld.this.uiManager.stateMachine.findTxtMotif(TransferNewPageOld.cntTransfertForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferNewPageOld.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                    return;
                }
                TransferNewPageOld transferNewPageOld = TransferNewPageOld.this;
                TransactionResume GetTransaction = transferNewPageOld.GetTransaction(this.val$issuarAccount, transferNewPageOld.uiManager.stateMachine.findTxtAmount(TransferNewPageOld.cntTransfertForm), TransferNewPageOld.this.uiManager.stateMachine.findTxtMotif(TransferNewPageOld.cntTransfertForm));
                ServiceResponse sendOtpProcessTransfer = ServiceManager.sendOtpProcessTransfer(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1017", 300015, "1", "0", GetTransaction.IssuerAccountNumber, GetTransaction.CreditedAccountNumber, GetTransaction.Amount, GetTransaction.isTutor, GetTransaction.CreditedAccountAlias, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, TransferNewPageOld.this.uiManager.stateMachine.findTxtMotif(TransferNewPageOld.cntTransfertForm).getText());
                if (sendOtpProcessTransfer.getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.setSessionCurrentTransactionResume(GetTransaction);
                    CihMBanking.sesPMTR.setSessionSavedContainer(TransferNewPageOld.this.uiManager.stateMachine.findCntGlobalTransfer(TransferNewPageOld.cntTransfertForm));
                    TransferNewPageOld.this.SwitchTransfertForms(TransferNewPageOld.cntTransfertForm, TransferNewPageOld.this.uiManager.stateMachine.findCntGlobalTransfer(TransferNewPageOld.cntTransfertForm), TransferNewPageOld.this.GetTransferSecurityForm(TransferNewPageOld.cntTransfertForm), false);
                    TransferNewPageOld.cntBtns.setHidden(true);
                    TransferNewPageOld.this.uiManager.btnBack.getListeners().clear();
                    TransferNewPageOld.this.uiManager.btnBack.addActionListener(new 1());
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferNewPageOld.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcessTransfer.getStatusLabel()), null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferNewPageOld.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                TransferNewPageOld.this.uiManager.NavigateToPage(new TransferNewPageOld(TransferNewPageOld.this.uiManager, null, 150));
            }
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                TransferNewPageOld.this.uiManager.NavigateToPageById(72);
            }
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                TransferNewPageOld.this.uiManager.NavigateToPageById(51);
            }
        }
    }

    public TransactionResume GetTransaction(Container container, TextArea textArea, TextArea textArea2) {
        Beneficiary beneficiary;
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
        Container container3 = new Container();
        for (int i = 0; i < Glob.getComponentCount(); i++) {
            if (Glob.getComponentAt(i).getUIID().equals("greyCnt")) {
                container3 = (Container) ((Container) Glob.getComponentAt(i)).getComponentAt(1);
            }
        }
        if (container3.getComponentCount() > 0) {
            Container container4 = (Container) ((Container) ((Container) container3.getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
            transactionResume.CreditedAccountAlias = ((Label) container4.getComponentAt(0)).getText();
            transactionResume.CreditedAccountNumber = StringTools.RibFormatWithoutSP(((Label) container4.getComponentAt(1)).getText());
            transactionResume.field1 = "";
            transactionResume.field2 = "";
            transactionResume.TypeOperation = "TRANSFER";
        } else if (!selectedBenef && (beneficiary = selectedBeneficiary) != null) {
            transactionResume.CreditedAccountAlias = beneficiary.alias;
            transactionResume.CreditedAccountNumber = selectedBeneficiary.cardNumber;
            transactionResume.field1 = "";
            transactionResume.field2 = "";
            transactionResume.TypeOperation = "TRANSFER";
            selectedBeneficiary = null;
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

    class 5 implements DataChangedListener {
        public void dataChanged(int i, int i2) {
        }

        5() {
        }
    }

    public void HidePassWordOnKeyPressed(TextField textField) {
        textField.addDataChangeListener(new 5());
    }

    public Container GetTransferSecurityForm(Container container) {
        cntBtns.setHidden(true);
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
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 6());
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 7(b3GCIHEcode));
        return createContainer;
    }

    class 6 implements ActionListener {
        6() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransferNewPageOld.cntBtns.setHidden(false);
            TransferNewPageOld.isOtpPageShow = false;
            TransferNewPageOld.this.uiManager.NavigateToPage(new TransferNewPageOld(TransferNewPageOld.this.uiManager, null, 150));
        }
    }

    class 7 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        7(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
            TransferNewPageOld.access$002(TransferNewPageOld.this, this.val$eCode1.getValue());
            sessionCurrentTransactionResume.PassHB = TransferNewPageOld.access$000(TransferNewPageOld.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (TransferNewPageOld.access$000(TransferNewPageOld.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferNewPageOld.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
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

    public Container DrawAcquiredAcc(String str, String str2) {
        Container GetContainerNw = this.uiManager.GetContainerNw("GloabalContainerV2", "Compte à créditer");
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AccountItemBenefTransfertV2");
        this.uiManager.stateMachine.findLblAccountOwner(createContainer).setText(str);
        this.uiManager.stateMachine.findLblAccountRibValue(createContainer).setText(StringTools.RibFormatWithSP(str2));
        GetContainerNw.addComponent(createContainer);
        Settings.setNightMode(GetContainerNw, 0);
        return GetContainerNw;
    }
}
