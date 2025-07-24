package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.ServiceManager;
import com.b3g.services.TransactionResume;
import com.b3g.services.TransferHistoric;
import com.b3g.services.TransferOperation;
import com.b3g.services.TransfertDATA;
import com.b3g.tools.DataTools;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordion;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TransfertWallet extends B3GPage {
    public static B3gAccordion A;
    public static Container BENEFCNT;
    public static Container Glob;
    public static boolean isOtpPageShow;
    private String VnewaliasText;
    TextField addressText;
    Container cntBtns;
    Container cntTransfertForm;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();
    private boolean isOvp = false;

    static /* synthetic */ Container access$000(TransfertWallet transfertWallet, String str, String str2) {
        return transfertWallet.DrawAcquiredAcc(str, str2);
    }

    static /* synthetic */ String access$100(TransfertWallet transfertWallet) {
        return transfertWallet.VnewaliasText;
    }

    static /* synthetic */ String access$102(TransfertWallet transfertWallet, String str) {
        transfertWallet.VnewaliasText = str;
        return str;
    }

    static /* synthetic */ ServiceResponse access$200(TransfertWallet transfertWallet, TransactionResume transactionResume) {
        return transfertWallet.simulateLanacash(transactionResume);
    }

    public TransfertWallet(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        isOtpPageShow = false;
        TransferNewPage.isOtpPageShow = false;
        TopUpNewPage.isOtpPageShow = false;
        this.thisContainer = new Container(new BorderLayout());
        this.thisContainer.setUIID("mn_cntMenuItemV2");
        this.thisContainer.setName("TransfertWallet");
        try {
            CihMBanking.sesPMTR.setToken("");
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList.addAll(getBenifProcess());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Beneficiary beneficiary = (Beneficiary) it.next();
                if (beneficiary.typeBenef.equals("300033")) {
                    arrayList2.add(beneficiary);
                }
            }
            new TransfertDATA().TransfertDATAProcess(28);
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new B3gContainer(GetContainerTransferForm(arrayList2, " ", null), "RECHARGE WE  PAY"));
            this.uiManager.DrawTabsWithNavigationV3(container, arrayList3, this.cntBtns, this.uiManager.stateMachine.findTxtAmount(this.cntTransfertForm), this.uiManager.stateMachine.findTxtMotif(this.cntTransfertForm), this.txtHBPassWord);
            this.thisContainer.add("Center", container);
        } catch (Exception unused) {
            this.thisContainer.setLayout(new BorderLayout());
            Settings.setNightMode(this.thisContainer, 0);
            this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
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
        this.cntTransfertForm = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransferFormTabGlobal");
        if (Display.getInstance().getPlatformName().equals("ios")) {
            this.cntTransfertForm.setScrollableY(false);
        }
        new Account().uiManager = this.uiManager;
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList());
        Container DrawListContainerWalletTransfer = this.uiManager.DrawListContainerWalletTransfer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getCanDebitAccount(arrayList3), 1, 16, str, null, this.uiManager.DrawListContainer("GloabalContainerV2", "WE PAY à créditer", Boolean.TRUE, arrayList, 1, 17, "Aucun bénéficiaire n'est disponible pour cette opération .", null, null, null, null), arrayList);
        this.uiManager.stateMachine.findCntAccountChosen(this.cntTransfertForm).addComponent(DrawListContainerWalletTransfer);
        this.uiManager.stateMachine.findTxtMotif(this.cntTransfertForm).setMaxSize(20);
        this.uiManager.stateMachine.findCntAccountChosen(this.cntTransfertForm).addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
        int i = 1;
        this.uiManager.stateMachine.findLblDayValue(this.cntTransfertForm).setHidden(true);
        this.uiManager.stateMachine.findLblWeekValue(this.cntTransfertForm).setHidden(true);
        this.uiManager.stateMachine.findLblDay(this.cntTransfertForm).setHidden(true);
        this.uiManager.stateMachine.findLblWeek(this.cntTransfertForm).setHidden(true);
        BENEFCNT = new Container(new BoxLayout(2));
        this.uiManager.stateMachine.findCntAccountChosen(this.cntTransfertForm).addComponent(BENEFCNT);
        B3gAccordion b3gAccordion = new B3gAccordion();
        A = b3gAccordion;
        b3gAccordion.setCloseIcon((Image) null);
        A.setOpenIcon((Image) null);
        Button button = new Button(" Choisissez votre bénéficiaire ");
        button.setUIID("op_BtnOppositionValidationV2Blue");
        Button button2 = new Button(" Choisissez votre bénéficiaire ");
        button2.setUIID("op_BtnOppositionValidationV2");
        A.setOpenBtn(button2);
        A.setCloseBtn(button);
        Glob = new Container(new BoxLayout(2));
        int i2 = 0;
        while (i2 < arrayList.size()) {
            TableLayout tableLayout = new TableLayout(i, 2);
            Button button3 = new Button();
            button3.setUIID("Container");
            Container container = new Container();
            container.setLeadComponent(button3);
            container.setLayout(tableLayout);
            container.removeAll();
            container.setUIID("Container");
            TableLayout.Constraint createConstraint = tableLayout.createConstraint();
            TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
            createConstraint.setWidthPercentage(80);
            createConstraint2.setWidthPercentage(i);
            container.addComponent(createConstraint2, button3);
            container.addComponent(createConstraint, ((Beneficiary) arrayList.get(i2)).DrawItem(this.uiManager.stateMachine, this.uiManager.ressource, 17, this.service, this.thisContainer, this.thisContainer));
            button3.addActionListener(new 1(container));
            Glob.add(container);
            i2++;
            i = 1;
        }
        A.addContent(new Container(), Glob);
        A.setScrollVisible(false);
        this.uiManager.stateMachine.findCntAccountChosen(this.cntTransfertForm).addComponent(A);
        A.addOnClickItemListener(new 2());
        this.cntBtns = new Container(new BoxLayout(2));
        Button button4 = new Button(" Ajouter un Bénéficiaire");
        button4.setIcon(this.uiManager.ressource.getImage("addBeneficery.png"));
        button4.setUIID("op_BtnOppositionValidationV2");
        this.cntBtns.setUIID("WhitCnt_PaddingLeftRigt_2");
        this.uiManager.ControlTextfield(this.uiManager.stateMachine.findTxtAmount(this.cntTransfertForm), "AMOUNT", this.cntTransfertForm);
        if (arrayList3.size() > 0 && arrayList.size() > 0) {
            this.uiManager.stateMachine.findBtnTransferValidation(this.cntTransfertForm).addActionListener(new 3(DrawListContainerWalletTransfer, arrayList));
        } else {
            ((Container) this.cntTransfertForm.getComponentAt(0)).removeComponent(((Container) this.cntTransfertForm.getComponentAt(0)).getComponentAt(6));
            this.cntTransfertForm.revalidate();
        }
        button4.addActionListener(new 4());
        this.cntTransfertForm.setScrollVisible(false);
        this.cntBtns.add(button4);
        this.cntTransfertForm.addComponent("South", this.cntBtns);
        return this.cntTransfertForm;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$cnt;

        1(Container container) {
            this.val$cnt = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransfertWallet.BENEFCNT.removeAll();
            for (int i = 0; i < TransfertWallet.Glob.getComponentCount(); i++) {
                TransfertWallet.Glob.getComponentAt(i).setUIID("Container");
                ((Container) TransfertWallet.Glob.getComponentAt(i)).revalidate();
            }
            this.val$cnt.setUIID("greyCnt");
            Container container = (Container) this.val$cnt.getComponentAt(1);
            if (container.getComponentCount() > 0) {
                Container container2 = (Container) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
                TransfertWallet.BENEFCNT.addComponent(TransfertWallet.access$000(TransfertWallet.this, ((Label) container2.getComponentAt(0)).getText(), StringTools.RibFormatWithoutSP(((Label) container2.getComponentAt(1)).getText())));
                TransfertWallet.BENEFCNT.revalidate();
            }
            TransfertWallet.A.openclose(true, TransfertWallet.Glob);
            TransfertWallet.this.cntBtns.setHidden(false);
            TransfertWallet.this.cntTransfertForm.setScrollableY(true);
            for (int i2 = 0; i2 < TransfertWallet.this.uiManager.stateMachine.findCntGlobalTransfer(TransfertWallet.this.cntTransfertForm).getComponentCount(); i2++) {
                if (!TransfertWallet.this.uiManager.stateMachine.findCntGlobalTransfer(TransfertWallet.this.cntTransfertForm).getComponentAt(i2).getName().equals("CntAccountChosen")) {
                    TransfertWallet.this.uiManager.stateMachine.findCntGlobalTransfer(TransfertWallet.this.cntTransfertForm).getComponentAt(i2).setHidden(false);
                }
            }
            TransfertWallet.this.cntTransfertForm.revalidate();
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (TransfertWallet.A.getArrowUIID().equals("op_BtnOppositionValidationV2")) {
                TransfertWallet.this.cntTransfertForm.setScrollableY(false);
                for (int i = 0; i < TransfertWallet.this.uiManager.stateMachine.findCntGlobalTransfer(TransfertWallet.this.cntTransfertForm).getComponentCount(); i++) {
                    if (!TransfertWallet.this.uiManager.stateMachine.findCntGlobalTransfer(TransfertWallet.this.cntTransfertForm).getComponentAt(i).getName().equals("CntAccountChosen")) {
                        TransfertWallet.this.uiManager.stateMachine.findCntGlobalTransfer(TransfertWallet.this.cntTransfertForm).getComponentAt(i).setHidden(true);
                    }
                }
                TransfertWallet.this.cntBtns.setHidden(true);
                TransfertWallet.this.cntBtns.revalidate();
                TransfertWallet.this.cntTransfertForm.revalidate();
                return;
            }
            TransfertWallet.this.cntTransfertForm.setScrollableY(true);
            for (int i2 = 0; i2 < TransfertWallet.this.uiManager.stateMachine.findCntGlobalTransfer(TransfertWallet.this.cntTransfertForm).getComponentCount(); i2++) {
                if (!TransfertWallet.this.uiManager.stateMachine.findCntGlobalTransfer(TransfertWallet.this.cntTransfertForm).getComponentAt(i2).getName().equals("CntAccountChosen")) {
                    TransfertWallet.this.uiManager.stateMachine.findCntGlobalTransfer(TransfertWallet.this.cntTransfertForm).getComponentAt(i2).setHidden(false);
                }
            }
            TransfertWallet.this.cntBtns.setHidden(false);
            TransfertWallet.this.cntBtns.revalidate();
            TransfertWallet.this.cntTransfertForm.revalidate();
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ ArrayList val$BeneficiaryList;
        final /* synthetic */ Container val$issuarAccount;

        3(Container container, ArrayList arrayList) {
            this.val$issuarAccount = container;
            this.val$BeneficiaryList = arrayList;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (TransfertWallet.this.uiManager.stateMachine.findTxtAmount(TransfertWallet.this.cntTransfertForm).getText().length() == 0 || TransfertWallet.this.uiManager.stateMachine.findTxtMotif(TransfertWallet.this.cntTransfertForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransfertWallet.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                    return;
                }
                TransferOperation transferOperation = new TransferOperation();
                TransfertWallet transfertWallet = TransfertWallet.this;
                TransactionResume GetTransaction = transfertWallet.GetTransaction(this.val$issuarAccount, transfertWallet.uiManager.stateMachine.findTxtAmount(TransfertWallet.this.cntTransfertForm), TransfertWallet.this.uiManager.stateMachine.findTxtMotif(TransfertWallet.this.cntTransfertForm), this.val$BeneficiaryList);
                ServiceResponse SimulationOperationProcess = transferOperation.SimulationOperationProcess(GetTransaction.CreditedAccountNumber, GetTransaction.IssuerAccountNumber, GetTransaction.Amount, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().cardProgramId, "TRANSFER", StringTools.ToUpperCase(GetTransaction.Motif), GetTransaction.CreditedAccountAlias, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList(), CihMBanking.sesPMTR.getSessionClient().getClient_CardList(), GetTransaction.field1, GetTransaction.field2, GetTransaction.isTutor, GetTransaction.phoneNumber);
                boolean parseBoolean = Boolean.parseBoolean(SimulationOperationProcess.getParamsOut().get("IsLana").toString());
                if (SimulationOperationProcess.getStatusCode().equals("000")) {
                    ServiceResponse sendOtpTopUpProcess = ServiceManager.sendOtpTopUpProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1017", 300013, "1", "0", GetTransaction.IssuerAccountNumber, GetTransaction.CreditedAccountNumber, GetTransaction.Amount, GetTransaction.isTutor);
                    if (sendOtpTopUpProcess.getStatusCode().equals("000")) {
                        CihMBanking.sesPMTR.setSessionSavedContainer(TransfertWallet.this.uiManager.stateMachine.findCntGlobalTransfer(TransfertWallet.this.cntTransfertForm));
                        GetTransaction.isLana = parseBoolean;
                        if (parseBoolean) {
                            TransfertWallet transfertWallet2 = TransfertWallet.this;
                            transfertWallet2.ShowDialog(transfertWallet2.uiManager.stateMachine, GetTransaction);
                        } else {
                            CihMBanking.sesPMTR.setSessionCurrentTransactionResume(GetTransaction);
                            TransfertWallet transfertWallet3 = TransfertWallet.this;
                            Container container = transfertWallet3.cntTransfertForm;
                            Container findCntGlobalTransfer = TransfertWallet.this.uiManager.stateMachine.findCntGlobalTransfer(TransfertWallet.this.cntTransfertForm);
                            TransfertWallet transfertWallet4 = TransfertWallet.this;
                            transfertWallet3.SwitchTransfertForms(container, findCntGlobalTransfer, transfertWallet4.GetTransferSecurityForm(transfertWallet4.cntTransfertForm), false);
                        }
                        TransfertWallet.this.uiManager.btnBack.getListeners().clear();
                        TransfertWallet.this.uiManager.btnBack.addActionListener(new 1());
                        return;
                    }
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransfertWallet.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpTopUpProcess.getStatusLabel()), null);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransfertWallet.this.uiManager.stateMachine, SimulationOperationProcess.getStatusLabel(), null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransfertWallet.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                TransfertWallet.this.cntBtns.setHidden(false);
                TransfertWallet.this.cntTransfertForm.replace((Container) TransfertWallet.this.cntTransfertForm.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
                TransfertWallet.this.cntTransfertForm.revalidate();
                TransfertWallet.this.uiManager.btnBack.getListeners().clear();
                TransfertWallet.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    TransfertWallet.this.uiManager.GoBack();
                    CihMBanking.sesPMTR.setToken("");
                }
            }
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                TransfertWallet.this.uiManager.NavigateToPageById(80);
            }
        }
    }

    public TransactionResume GetTransaction(Container container, TextArea textArea, TextArea textArea2, ArrayList arrayList) {
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
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Beneficiary beneficiary = (Beneficiary) arrayList.get(i2);
                if (beneficiary.phoneNumber.equals(phoneNumberFormatted(StringTools.RibFormatWithoutSP(((Label) container4.getComponentAt(1)).getText())))) {
                    transactionResume.CreditedAccountNumber = beneficiary.cardNumber;
                    transactionResume.phoneNumber = beneficiary.phoneNumber;
                }
            }
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
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container2 = (Container) this.uib.findByName("Container17", createContainer);
        this.cntBtns.setHidden(true);
        container2.removeAll();
        container2.setLayout(BoxLayout.y());
        container2.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 6(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 7(b3GCIHEcode));
        return createContainer;
    }

    class 6 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        6(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            TransfertWallet.this.cntBtns.setHidden(false);
            TransfertWallet.isOtpPageShow = false;
            TransfertWallet.this.uiManager.btnBack.getListeners().clear();
            TransfertWallet.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                TransfertWallet.this.uiManager.GoBack();
                CihMBanking.sesPMTR.setToken("");
            }
        }
    }

    class 7 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        7(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
            TransfertWallet.access$102(TransfertWallet.this, this.val$eCode1.getValue());
            sessionCurrentTransactionResume.PassHB = TransfertWallet.access$100(TransfertWallet.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (TransfertWallet.access$100(TransfertWallet.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransfertWallet.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
            } else if (sessionCurrentTransactionResume.isLana) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(86, sessionCurrentTransactionResume, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 161);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(85, sessionCurrentTransactionResume, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 131);
            }
        }
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
        if (Display.getInstance().getPlatformName().equals("ios")) {
            this.txtHBPassWord.startEditingAsync();
        }
    }

    public void ShowDialog(StateMachine stateMachine, TransactionResume transactionResume) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUp");
            Container container = new Container(new BoxLayout(2));
            Label label = new Label("Merci d'introduire le numéro d'identité (CIN) de votre bénéficiaire");
            label.setUIID("lbllhMediumConseil");
            TextField textField = new TextField();
            this.addressText = textField;
            textField.setConstraint(0);
            this.addressText.setUIID("textFieldPopUpStyle");
            container.addComponent(label);
            container.addComponent(this.addressText);
            stateMachine.findCntPopupBody(createContainer).addComponent(container);
            Label label2 = new Label(" ");
            label2.setUIID("dg_lblPopUpDemo");
            label2.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label2);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupBtn(dialog, transactionResume, this.addressText, stateMachine));
            stateMachine.findLblpopupItemTitleV2(createContainer).setHidden(true);
            stateMachine.findLblpopupItemValueV2(createContainer).getParent().removeComponent(stateMachine.findLblpopupItemValueV2(createContainer));
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(false);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(255);
            dialog.getDialogStyle().setBgColor(1118481);
            dialog.getStyle().setPadding(0, 0, 0, 0);
            dialog.getStyle().setMargin(0, 0, 0, 0);
            dialog.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog.revalidate();
            dialog.addComponent("Center", createContainer);
            dialog.show(0, 0, 0, 0);
        } catch (IOException unused) {
        }
    }

    public Container GetPopupBtn(Dialog dialog, TransactionResume transactionResume, TextField textField, StateMachine stateMachine) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("PRECEDENT");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(stateMachine.uiManager.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 8(dialog));
        container.addComponent(button);
        Button button2 = new Button("SUIVANT");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(stateMachine.uiManager.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 9(dialog, transactionResume));
        container.addComponent(button2);
        return container;
    }

    class 8 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        8(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 9 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ TransactionResume val$tranc;

        9(Dialog dialog, TransactionResume transactionResume) {
            this.val$d = dialog;
            this.val$tranc = transactionResume;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (!TransfertWallet.this.addressText.getText().equals("")) {
                    this.val$d.dispose();
                    this.val$tranc.CINLanacash = TransfertWallet.this.addressText.getText().toUpperCase().trim();
                    ServiceResponse access$200 = TransfertWallet.access$200(TransfertWallet.this, this.val$tranc);
                    if (access$200.getStatusCode().equals("000")) {
                        this.val$tranc.dataSimuLanacash = access$200.getStatusLabel();
                        CihMBanking.sesPMTR.setSessionCurrentTransactionResume(this.val$tranc);
                        TransfertWallet transfertWallet = TransfertWallet.this;
                        Container container = transfertWallet.cntTransfertForm;
                        Container findCntGlobalTransfer = TransfertWallet.this.uiManager.stateMachine.findCntGlobalTransfer(TransfertWallet.this.cntTransfertForm);
                        TransfertWallet transfertWallet2 = TransfertWallet.this;
                        transfertWallet.SwitchTransfertForms(container, findCntGlobalTransfer, transfertWallet2.GetTransferSecurityForm(transfertWallet2.cntTransfertForm), false);
                        return;
                    }
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(access$200.getStatusLabel()), null);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("le numéro d'identité (CIN) de votre bénéficiaire"), null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransfertWallet.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }
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

    private Container DrawAcquiredAcc(String str, String str2) {
        Container GetContainerNw = this.uiManager.GetContainerNw("GloabalContainerV2", "WE PAY à créditer");
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AccountItemBenefTransfertV2");
        this.uiManager.stateMachine.findLblAccountOwner(createContainer).setText(str);
        this.uiManager.stateMachine.findLblAccountRibValue(createContainer).setText(StringTools.RibFormatWithSP(str2));
        GetContainerNw.addComponent(createContainer);
        return GetContainerNw;
    }

    public ArrayList getBenifProcess() {
        new ArrayList();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900131);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return FillDcsDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public ArrayList FillDcsDataFromServiceResponse(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
            arrayList.add(new Beneficiary().FillBeneficiaryFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i))));
        }
        return arrayList;
    }

    private String phoneNumberFormatted(String str) {
        if (str.substring(0, 2).equals("06")) {
            return "212" + str.substring(1);
        }
        return str.substring(0, 2).equals("07") ? "212" + str.substring(1) : str;
    }

    private ServiceResponse simulateLanacash(TransactionResume transactionResume) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CIN", transactionResume.CINLanacash);
        hashtable.put("PHONE_NUMBER", phoneNumberFormatted(transactionResume.phoneNumber));
        hashtable.put("MOTIF", transactionResume.Motif);
        hashtable.put("AMOUNT", transactionResume.Amount);
        hashtable.put("ACCOUNTTODEBIT", transactionResume.IssuerAccountNumber.substring(6, 22));
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(168);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }
}
