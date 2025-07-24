package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.Plafond;
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
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TransferInstantanePage extends B3GPage {
    public static B3gAccordion A;
    public static Container BENEFCNT;
    public static Container Glob;
    public static Container cntBtns;
    public static Container cntTransfertForm;
    public static boolean isOtpPageShow;
    private String VnewaliasText;
    TextField txtHBPassWord;
    public String selectedType = "";
    UIBuilder uib = new UIBuilder();
    private boolean isOvp = false;

    static /* synthetic */ void access$000(TransferInstantanePage transferInstantanePage) {
        transferInstantanePage.showChoixTypeDialog();
    }

    static /* synthetic */ String access$100(TransferInstantanePage transferInstantanePage) {
        return transferInstantanePage.VnewaliasText;
    }

    static /* synthetic */ String access$102(TransferInstantanePage transferInstantanePage, String str) {
        transferInstantanePage.VnewaliasText = str;
        return str;
    }

    public TransferInstantanePage(Object obj, Object obj2, int i) {
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
            TransfertDATA TransfertDATAProcess = new TransfertDATA().TransfertDATAProcess(28);
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(GetContainerTransferForm(TransfertDATAProcess.getBeneficiaryList(), TransfertDATAProcess.getbeneficiaryStatusLabel(), TransfertDATAProcess.getPlafond()), "VIREMENT"));
            arrayList.add(new B3gContainer(GetContainerTransferHistoric(TransfertDATAProcess.getTransferHistoricList(), TransfertDATAProcess), "HISTORIQUE"));
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
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(this.uiManager.DrawListContainerTransfer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getCanDebitAccount(arrayList3), 1, 16, str, null, this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à créditer", Boolean.TRUE, getFirstAccountBeneficiary(arrayList3, arrayList), 1, 17, "Aucun compte n'est disponible pour cette opération.", null, null, null, null), CihMBanking.sesPMTR.getSessionClient().getClient_BeneficiaryList()));
        this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).setMaxSize(20);
        this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).addDataChangeListener(new 1());
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
        int i = 2;
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
        int i2 = 0;
        while (i2 < firstAccountBeneficiary.size()) {
            TableLayout tableLayout = new TableLayout(1, i);
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
            container2.addComponent(createConstraint, ((Beneficiary) firstAccountBeneficiary.get(i2)).DrawItem(this.uiManager.stateMachine, this.uiManager.ressource, 17, this.service, this.thisContainer, this.thisContainer));
            button3.addActionListener(new 2(container2));
            Glob.add(container2);
            i2++;
            i = 2;
        }
        A.addContent(new Container(), Glob);
        A.setScrollVisible(false);
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(A);
        A.addOnClickItemListener(new 3());
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
        if (arrayList3.size() > 0 && arrayList.size() > 0) {
            this.uiManager.stateMachine.findBtnTransferValidation(cntTransfertForm).addActionListener(new 4());
        } else {
            ((Container) cntTransfertForm.getComponentAt(0)).removeComponent(((Container) cntTransfertForm.getComponentAt(0)).getComponentAt(6));
            cntTransfertForm.revalidate();
        }
        button4.addActionListener(new 5());
        cntTransfertForm.setScrollVisible(false);
        cntBtns.add(button4);
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
            TransferInstantanePage.this.uiManager.stateMachine.findTxtMotif(TransferInstantanePage.cntTransfertForm).setText(TransferInstantanePage.this.removeSpecialCharacters(TransferInstantanePage.this.uiManager.stateMachine.findTxtMotif(TransferInstantanePage.cntTransfertForm).getText()));
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$cnt;

        2(Container container) {
            this.val$cnt = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransferInstantanePage.BENEFCNT.removeAll();
            for (int i = 0; i < TransferInstantanePage.Glob.getComponentCount(); i++) {
                TransferInstantanePage.Glob.getComponentAt(i).setUIID("Container");
                ((Container) TransferInstantanePage.Glob.getComponentAt(i)).revalidate();
            }
            this.val$cnt.setUIID("greyCnt");
            Container container = (Container) this.val$cnt.getComponentAt(1);
            if (container.getComponentCount() > 0) {
                Container container2 = (Container) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
                TransferInstantanePage.BENEFCNT.addComponent(TransferInstantanePage.this.DrawAcquiredAcc(((Label) container2.getComponentAt(0)).getText(), StringTools.RibFormatWithoutSP(((Label) container2.getComponentAt(1)).getText())));
                TransferInstantanePage.BENEFCNT.revalidate();
            }
            TransferInstantanePage.A.openclose(true, TransferInstantanePage.Glob);
            TransferInstantanePage.cntBtns.setHidden(false);
            TransferInstantanePage.cntTransfertForm.setScrollableY(true);
            for (int i2 = 0; i2 < TransferInstantanePage.this.uiManager.stateMachine.findCntGlobalTransfer(TransferInstantanePage.cntTransfertForm).getComponentCount(); i2++) {
                if (!TransferInstantanePage.this.uiManager.stateMachine.findCntGlobalTransfer(TransferInstantanePage.cntTransfertForm).getComponentAt(i2).getName().equals("CntAccountChosen")) {
                    TransferInstantanePage.this.uiManager.stateMachine.findCntGlobalTransfer(TransferInstantanePage.cntTransfertForm).getComponentAt(i2).setHidden(false);
                }
            }
            TransferInstantanePage.cntTransfertForm.revalidate();
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (TransferInstantanePage.A.getArrowUIID().equals("op_BtnOppositionValidationV2")) {
                TransferInstantanePage.cntTransfertForm.setScrollableY(false);
                for (int i = 0; i < TransferInstantanePage.this.uiManager.stateMachine.findCntGlobalTransfer(TransferInstantanePage.cntTransfertForm).getComponentCount(); i++) {
                    if (!TransferInstantanePage.this.uiManager.stateMachine.findCntGlobalTransfer(TransferInstantanePage.cntTransfertForm).getComponentAt(i).getName().equals("CntAccountChosen")) {
                        TransferInstantanePage.this.uiManager.stateMachine.findCntGlobalTransfer(TransferInstantanePage.cntTransfertForm).getComponentAt(i).setHidden(true);
                    }
                }
                TransferInstantanePage.cntBtns.setHidden(true);
                TransferInstantanePage.cntBtns.revalidate();
                TransferInstantanePage.cntTransfertForm.revalidate();
                return;
            }
            TransferInstantanePage.cntTransfertForm.setScrollableY(true);
            for (int i2 = 0; i2 < TransferInstantanePage.this.uiManager.stateMachine.findCntGlobalTransfer(TransferInstantanePage.cntTransfertForm).getComponentCount(); i2++) {
                if (!TransferInstantanePage.this.uiManager.stateMachine.findCntGlobalTransfer(TransferInstantanePage.cntTransfertForm).getComponentAt(i2).getName().equals("CntAccountChosen")) {
                    TransferInstantanePage.this.uiManager.stateMachine.findCntGlobalTransfer(TransferInstantanePage.cntTransfertForm).getComponentAt(i2).setHidden(false);
                }
            }
            TransferInstantanePage.cntBtns.setHidden(false);
            TransferInstantanePage.cntBtns.revalidate();
            TransferInstantanePage.cntTransfertForm.revalidate();
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (TransferInstantanePage.this.selectedType.isEmpty()) {
                TransferInstantanePage.access$000(TransferInstantanePage.this);
            }
        }
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                TransferInstantanePage.this.uiManager.NavigateToPageById(72);
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
            TransferInstantanePage.cntBtns.setHidden(false);
            TransferInstantanePage.this.uiManager.btnBack.getListeners().clear();
            TransferInstantanePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                TransferInstantanePage.cntBtns.setHidden(false);
                TransferInstantanePage.isOtpPageShow = false;
                TransferInstantanePage.this.uiManager.GoBack();
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
            TransferInstantanePage.access$102(TransferInstantanePage.this, this.val$eCode1.getValue());
            sessionCurrentTransactionResume.PassHB = TransferInstantanePage.access$100(TransferInstantanePage.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (TransferInstantanePage.access$100(TransferInstantanePage.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferInstantanePage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
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
        return GetContainerNw;
    }

    private void showChoixTypeDialog() {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        dialog.getDialogStyle().setBgColor(16777215);
        dialog.getDialogStyle().setBgTransparency(255);
        dialog.getAllStyles().setBgColor(16777215);
        dialog.getAllStyles().setBgTransparency(255);
        Container container = new Container(new BorderLayout());
        Label label = new Label("Choisissez la rapidité !", "LblOrange");
        SpanLabel spanLabel = new SpanLabel("Avec le transfert instantané, votre argent est transféré sur le compte de Votre bénéficiaire sans délai d attente, en 10 seconde seulement.", "Container");
        Label label2 = new Label(this.uiManager.ressource.getImage("instantane.png"));
        label2.setAlignment(4);
        SpanLabel spanLabel2 = new SpanLabel("Le délai d un transfert classique est de 1 à 2 jour ouvrable et est facturé 8,25 dhs.", "Container");
        SpanLabel spanLabel3 = new SpanLabel("le virement instantané est facturé 20 dhs par opération.", "Container");
        Container container2 = new Container(BoxLayout.y());
        container2.add(spanLabel);
        container2.add(label2);
        container2.add(spanLabel2);
        container2.add(spanLabel3);
        Button button = new Button("VIREMENT INSTANTANE");
        Button button2 = new Button("VIREMENT CLASSIQUE");
        button.setUIID("op_BtnOppositionValidationV2");
        button2.setUIID("op_BtnOppositionValidationV2");
        button2.addActionListener(new 9(dialog));
        Container container3 = new Container(new GridLayout(2, 1));
        container3.add(button).add(button2);
        container.add("North", label);
        container.add("Center", container2);
        container.add("South", container3);
        dialog.add("Center", container);
        dialog.showAtPosition(0, 0, 0, 0, true);
    }

    class 9 implements ActionListener {
        final /* synthetic */ Dialog val$dlg;

        9(Dialog dialog) {
            this.val$dlg = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$dlg.dispose();
        }
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
