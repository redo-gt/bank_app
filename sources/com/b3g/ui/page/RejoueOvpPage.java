package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.OvpExecutionService;
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
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class RejoueOvpPage extends B3GPage {
    public static B3gAccordion A;
    public static Container BENEFCNT;
    public static Container Glob;
    public static Container cntBtns;
    public static Container cntTransfertForm;
    public static boolean isOtpPageShow;
    public OvpExecutionService OES;
    private String VnewaliasText;
    public TransfertDATA trDT;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();
    private boolean isOvp = false;

    static /* synthetic */ String access$000(RejoueOvpPage rejoueOvpPage) {
        return rejoueOvpPage.VnewaliasText;
    }

    static /* synthetic */ String access$002(RejoueOvpPage rejoueOvpPage, String str) {
        rejoueOvpPage.VnewaliasText = str;
        return str;
    }

    public RejoueOvpPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.OES = (OvpExecutionService) this.service;
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
        }
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
        ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < arrayList3.size(); i++) {
            if (!((Account) arrayList3.get(i)).rib.substring(13, 17).equals("2310")) {
                arrayList4.add((Account) arrayList3.get(i));
            }
        }
        Container DrawListContainerTransfer = this.uiManager.DrawListContainerTransfer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getCanDebitAccount(arrayList3), 1, 16, str, null, this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à créditer", Boolean.TRUE, getFirstAccountBeneficiary(arrayList4, arrayList), 1, 17, "Aucun compte n'est disponible pour cette opération.", null, null, null, null), CihMBanking.sesPMTR.getSessionClient().getClient_BeneficiaryList());
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(DrawListContainerTransfer);
        this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).setMaxSize(20);
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
        int i2 = 2;
        BENEFCNT = new Container(new BoxLayout(2));
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(BENEFCNT);
        B3gAccordion b3gAccordion = new B3gAccordion();
        A = b3gAccordion;
        b3gAccordion.setCloseIcon((Image) null);
        A.setOpenIcon((Image) null);
        Container container = new Container(new BoxLayout(2));
        Button button = new Button(" Choisissez votre bénéficiaire ");
        button.setUIID("op_BtnOppositionValidationV2Blue");
        Button button2 = new Button(" Choisissez votre bénéficiaire ");
        button2.setUIID("op_BtnOppositionValidationV2");
        A.setOpenBtn(button2);
        A.setCloseBtn(button);
        container.add(new Label("Compte à créditer "));
        Glob = new Container(new BoxLayout(2));
        new ArrayList();
        ArrayList firstAccountBeneficiary = getFirstAccountBeneficiary(arrayList3, arrayList);
        int i3 = 0;
        while (i3 < firstAccountBeneficiary.size()) {
            TableLayout tableLayout = new TableLayout(1, i2);
            Button button3 = new Button();
            button3.setUIID("Container");
            Container container2 = new Container();
            container2.setLeadComponent(button3);
            container2.setLayout(tableLayout);
            container2.removeAll();
            container2.setUIID("Container");
            TableLayout.Constraint createConstraint = tableLayout.createConstraint();
            TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
            createConstraint.setWidthPercentage(80);
            createConstraint2.setWidthPercentage(1);
            container2.addComponent(createConstraint2, button3);
            container2.addComponent(createConstraint, ((Beneficiary) firstAccountBeneficiary.get(i3)).DrawItem(this.uiManager.stateMachine, this.uiManager.ressource, 17, this.service, this.thisContainer, this.thisContainer));
            button3.addActionListener(new 1(container2));
            Glob.add(container2);
            i3++;
            i2 = 2;
        }
        BENEFCNT.addComponent(DrawAcquiredAcc(this.OES.getCreditedAccountLabel(), this.OES.getCreditedAccountNumbel()));
        A.addContent(new Container(), Glob);
        A.setScrollVisible(false);
        A.addOnClickItemListener(new 2());
        this.uiManager.stateMachine.findLblDayValue(cntTransfertForm).setText(((Plafond) arrayList2.get(0)).TransferDaily);
        this.uiManager.stateMachine.findLblWeekValue(cntTransfertForm).setText(((Plafond) arrayList2.get(0)).TransferMax);
        this.uiManager.stateMachine.findLblDay(cntTransfertForm).setText("Plafond quotidien: ");
        this.uiManager.stateMachine.findLblWeek(cntTransfertForm).setText("Plafond par opération: ");
        cntBtns = new Container(new BoxLayout(2));
        Button button4 = new Button(" Ajouter un Bénéficiaire");
        button4.setIcon(this.uiManager.ressource.getImage("addBeneficery.png"));
        button4.setUIID("op_BtnOppositionValidationV2");
        cntBtns.setUIID("WhitCnt_PaddingLeftRigt_2");
        this.uiManager.ControlTextfield(this.uiManager.stateMachine.findTxtAmount(cntTransfertForm), "AMOUNT", cntTransfertForm);
        this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).setText(this.OES.getMotif());
        this.uiManager.stateMachine.findTxtAmount(cntTransfertForm).setText(this.OES.getMontant());
        this.uiManager.stateMachine.findBtnTransferValidation(cntTransfertForm).addActionListener(new 3(DrawListContainerTransfer));
        button4.addActionListener(new 4());
        cntTransfertForm.setScrollVisible(false);
        cntBtns.add(button4);
        Container container3 = (Container) this.uib.findByName("AddBen", cntTransfertForm);
        ((Container) this.uib.findByName("BlankCnt", cntTransfertForm)).setPreferredH(cntTransfertForm.getPreferredH());
        container3.addComponent(cntBtns);
        container3.revalidate();
        return cntTransfertForm;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$cnt;

        1(Container container) {
            this.val$cnt = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            RejoueOvpPage.BENEFCNT.removeAll();
            for (int i = 0; i < RejoueOvpPage.Glob.getComponentCount(); i++) {
                RejoueOvpPage.Glob.getComponentAt(i).setUIID("Container");
                ((Container) RejoueOvpPage.Glob.getComponentAt(i)).revalidate();
            }
            this.val$cnt.setUIID("greyCnt");
            Container container = (Container) this.val$cnt.getComponentAt(1);
            if (container.getComponentCount() > 0) {
                Container container2 = (Container) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
                RejoueOvpPage.BENEFCNT.addComponent(RejoueOvpPage.this.DrawAcquiredAcc(((Label) container2.getComponentAt(0)).getText(), StringTools.RibFormatWithoutSP(((Label) container2.getComponentAt(1)).getText())));
                RejoueOvpPage.BENEFCNT.revalidate();
            }
            RejoueOvpPage.A.openclose(true, RejoueOvpPage.Glob);
            RejoueOvpPage.cntBtns.setHidden(false);
            RejoueOvpPage.cntTransfertForm.setScrollableY(true);
            for (int i2 = 0; i2 < RejoueOvpPage.this.uiManager.stateMachine.findCntGlobalTransfer(RejoueOvpPage.cntTransfertForm).getComponentCount(); i2++) {
                if (!RejoueOvpPage.this.uiManager.stateMachine.findCntGlobalTransfer(RejoueOvpPage.cntTransfertForm).getComponentAt(i2).getName().equals("CntAccountChosen")) {
                    RejoueOvpPage.this.uiManager.stateMachine.findCntGlobalTransfer(RejoueOvpPage.cntTransfertForm).getComponentAt(i2).setHidden(false);
                }
            }
            RejoueOvpPage.cntTransfertForm.revalidate();
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (RejoueOvpPage.A.getArrowUIID().equals("op_BtnOppositionValidationV2")) {
                RejoueOvpPage.cntTransfertForm.setScrollableY(false);
                for (int i = 0; i < RejoueOvpPage.this.uiManager.stateMachine.findCntGlobalTransfer(RejoueOvpPage.cntTransfertForm).getComponentCount(); i++) {
                    if (!RejoueOvpPage.this.uiManager.stateMachine.findCntGlobalTransfer(RejoueOvpPage.cntTransfertForm).getComponentAt(i).getName().equals("CntAccountChosen")) {
                        RejoueOvpPage.this.uiManager.stateMachine.findCntGlobalTransfer(RejoueOvpPage.cntTransfertForm).getComponentAt(i).setHidden(true);
                    }
                }
                RejoueOvpPage.cntBtns.setHidden(true);
                RejoueOvpPage.cntBtns.revalidate();
                RejoueOvpPage.cntTransfertForm.revalidate();
                return;
            }
            RejoueOvpPage.cntTransfertForm.setScrollableY(true);
            for (int i2 = 0; i2 < RejoueOvpPage.this.uiManager.stateMachine.findCntGlobalTransfer(RejoueOvpPage.cntTransfertForm).getComponentCount(); i2++) {
                if (!RejoueOvpPage.this.uiManager.stateMachine.findCntGlobalTransfer(RejoueOvpPage.cntTransfertForm).getComponentAt(i2).getName().equals("CntAccountChosen")) {
                    RejoueOvpPage.this.uiManager.stateMachine.findCntGlobalTransfer(RejoueOvpPage.cntTransfertForm).getComponentAt(i2).setHidden(false);
                }
            }
            RejoueOvpPage.cntBtns.setHidden(false);
            RejoueOvpPage.cntBtns.revalidate();
            RejoueOvpPage.cntTransfertForm.revalidate();
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Container val$issuarAccount;

        3(Container container) {
            this.val$issuarAccount = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (RejoueOvpPage.this.uiManager.stateMachine.findTxtAmount(RejoueOvpPage.cntTransfertForm).getText().length() == 0 || RejoueOvpPage.this.uiManager.stateMachine.findTxtMotif(RejoueOvpPage.cntTransfertForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(RejoueOvpPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                    return;
                }
                RejoueOvpPage rejoueOvpPage = RejoueOvpPage.this;
                TransactionResume GetTransaction = rejoueOvpPage.GetTransaction(this.val$issuarAccount, rejoueOvpPage.uiManager.stateMachine.findTxtAmount(RejoueOvpPage.cntTransfertForm), RejoueOvpPage.this.uiManager.stateMachine.findTxtMotif(RejoueOvpPage.cntTransfertForm));
                ServiceResponse sendOtpProcessTransfer = ServiceManager.sendOtpProcessTransfer(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1017", 300015, "1", "0", GetTransaction.IssuerAccountNumber, GetTransaction.CreditedAccountNumber, GetTransaction.Amount, GetTransaction.isTutor, GetTransaction.CreditedAccountAlias, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, RejoueOvpPage.this.uiManager.stateMachine.findTxtMotif(RejoueOvpPage.cntTransfertForm).getText());
                if (sendOtpProcessTransfer.getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.setSessionCurrentTransactionResume(GetTransaction);
                    CihMBanking.sesPMTR.setSessionSavedContainer(RejoueOvpPage.this.uiManager.stateMachine.findCntGlobalTransfer(RejoueOvpPage.cntTransfertForm));
                    RejoueOvpPage.this.SwitchTransfertForms(RejoueOvpPage.cntTransfertForm, RejoueOvpPage.this.uiManager.stateMachine.findCntGlobalTransfer(RejoueOvpPage.cntTransfertForm), RejoueOvpPage.this.GetTransferSecurityForm(RejoueOvpPage.cntTransfertForm), false);
                    RejoueOvpPage.cntBtns.setHidden(true);
                    RejoueOvpPage.this.uiManager.btnBack.getListeners().clear();
                    RejoueOvpPage.this.uiManager.btnBack.addActionListener(new 1());
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(RejoueOvpPage.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcessTransfer.getStatusLabel()), null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(RejoueOvpPage.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                RejoueOvpPage.cntTransfertForm.replace((Container) RejoueOvpPage.cntTransfertForm.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 0));
                RejoueOvpPage.cntTransfertForm.revalidate();
                RejoueOvpPage.cntBtns.setHidden(false);
                RejoueOvpPage.this.uiManager.btnBack.getListeners().clear();
                RejoueOvpPage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    RejoueOvpPage.this.uiManager.GoBack();
                }
            }
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                RejoueOvpPage.this.uiManager.NavigateToPageById(72);
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
        transactionResume.CreditedAccountAlias = this.OES.getCreditedAccountLabel();
        String str = "";
        for (int i = 0; i < this.trDT.getBeneficiaryList().size(); i++) {
            if (((Beneficiary) this.trDT.getBeneficiaryList().get(i)).cardNumber.contains(this.OES.getCreditedAccountNumbel())) {
                str = ((Beneficiary) this.trDT.getBeneficiaryList().get(i)).cardNumber;
            }
        }
        transactionResume.CreditedAccountNumber = StringTools.RibFormatWithoutSP(str);
        transactionResume.field1 = "";
        transactionResume.field2 = "";
        transactionResume.TypeOperation = "TRANSFER";
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
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 0));
            RejoueOvpPage.cntBtns.setHidden(false);
            RejoueOvpPage.this.uiManager.btnBack.getListeners().clear();
            RejoueOvpPage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                RejoueOvpPage.cntBtns.setHidden(false);
                RejoueOvpPage.isOtpPageShow = false;
                RejoueOvpPage.this.uiManager.GoBack();
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
            RejoueOvpPage.access$002(RejoueOvpPage.this, this.val$eCode1.getValue());
            sessionCurrentTransactionResume.PassHB = RejoueOvpPage.access$000(RejoueOvpPage.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (RejoueOvpPage.access$000(RejoueOvpPage.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(RejoueOvpPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
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
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.cardNumber = this.OES.getCreditedAccountNumbel();
        beneficiary.alias = this.OES.getCreditedAccountLabel();
        arrayList3.add(beneficiary);
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
        return GetContainerNw;
    }
}
