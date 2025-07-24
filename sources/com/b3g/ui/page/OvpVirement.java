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
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GRadio;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.animations.Transition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OvpVirement extends B3GPage {
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();
    private boolean isOvp = false;

    static /* synthetic */ boolean access$002(OvpVirement ovpVirement, boolean z) {
        ovpVirement.isOvp = z;
        return z;
    }

    static /* synthetic */ String access$100(OvpVirement ovpVirement) {
        return ovpVirement.VnewaliasText;
    }

    static /* synthetic */ String access$102(OvpVirement ovpVirement, String str) {
        ovpVirement.VnewaliasText = str;
        return str;
    }

    public OvpVirement(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            TransfertDATA TransfertDATAProcess = new TransfertDATA().TransfertDATAProcess(28);
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(GetContainerTransferForm(TransfertDATAProcess.getBeneficiaryList(), TransfertDATAProcess.getbeneficiaryStatusLabel(), TransfertDATAProcess.getPlafond()), "VIREMENT"));
            arrayList.add(new B3gContainer(GetContainerTransferHistoric(TransfertDATAProcess.getTransferHistoricList(), TransfertDATAProcess), "HISTORIQUE"));
            this.uiManager.DrawTabsWithNavigation(container, arrayList);
            this.thisContainer.addComponent(container);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
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
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransferOvpForm");
        Container createContainer2 = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "OvpInfoCnt");
        Account account = new Account();
        account.uiManager = this.uiManager;
        ArrayList AccountProcess = account.AccountProcess(11);
        GetSavingAccountFromBeneficiaryList(arrayList, AccountProcess);
        Container DrawListContainer = this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à créditer", Boolean.TRUE, RemoveFirstCurrentClientAccount(AddCurrentClientAccount(arrayList)), 1, 17, DataTools.FormatUnicode(str), null, null, null, null);
        Container DrawListContainerTransfer = this.uiManager.DrawListContainerTransfer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, AccountProcess, 1, 16, str, null, DrawListContainer, CihMBanking.sesPMTR.getSessionClient().getClient_BeneficiaryList());
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(DrawListContainerTransfer);
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(DrawListContainer);
        B3GRadio b3GRadio = new B3GRadio("Transfertype");
        Container container = new Container();
        b3GRadio.addItem("Virement unitaire");
        b3GRadio.getItem("Virement unitaire").addActionListener(new 1(createContainer, createContainer2, container));
        b3GRadio.addItem("Virement permanent");
        b3GRadio.getItem("Virement permanent").addActionListener(new 2(createContainer, container, createContainer2));
        createContainer.addComponent(b3GRadio.GetContainer());
        this.uiManager.stateMachine.findLblDayValue(createContainer).setText(((Plafond) arrayList2.get(0)).TransferDaily);
        this.uiManager.stateMachine.findLblWeekValue(createContainer).setText(((Plafond) arrayList2.get(0)).TransferWeekly);
        this.uiManager.ControlTextfield(this.uiManager.stateMachine.findTxtAmount(createContainer), "AMOUNT", createContainer);
        if (AccountProcess.size() > 0 && arrayList.size() > 0) {
            this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 3(createContainer, DrawListContainerTransfer, DrawListContainer));
        } else {
            ((Container) createContainer.getComponentAt(0)).removeComponent(((Container) createContainer.getComponentAt(0)).getComponentAt(6));
            createContainer.revalidate();
        }
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            this.uiManager.stateMachine.findBtnTransferValidation(createContainer).setEnabled(false);
        }
        createContainer.setScrollableY(true);
        createContainer.setScrollVisible(false);
        return createContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$cntTransfertForm;
        final /* synthetic */ Container val$emptyCnt;
        final /* synthetic */ Container val$ovpInfosCnt;

        1(Container container, Container container2, Container container3) {
            this.val$cntTransfertForm = container;
            this.val$ovpInfosCnt = container2;
            this.val$emptyCnt = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OvpVirement.access$002(OvpVirement.this, false);
            this.val$cntTransfertForm.replace(this.val$ovpInfosCnt, this.val$emptyCnt, (Transition) null);
            this.val$cntTransfertForm.revalidate();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$cntTransfertForm;
        final /* synthetic */ Container val$emptyCnt;
        final /* synthetic */ Container val$ovpInfosCnt;

        2(Container container, Container container2, Container container3) {
            this.val$cntTransfertForm = container;
            this.val$emptyCnt = container2;
            this.val$ovpInfosCnt = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OvpVirement.access$002(OvpVirement.this, true);
            this.val$cntTransfertForm.replace(this.val$emptyCnt, this.val$ovpInfosCnt, (Transition) null);
            this.val$cntTransfertForm.revalidate();
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Container val$acquiredAccount;
        final /* synthetic */ Container val$cntTransfertForm;
        final /* synthetic */ Container val$issuarAccount;

        3(Container container, Container container2, Container container3) {
            this.val$cntTransfertForm = container;
            this.val$issuarAccount = container2;
            this.val$acquiredAccount = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (OvpVirement.this.uiManager.stateMachine.findTxtAmount(this.val$cntTransfertForm).getText().length() == 0 || OvpVirement.this.uiManager.stateMachine.findTxtAreaMotif(this.val$cntTransfertForm).getText().length() == 0) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OvpVirement.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            OvpVirement ovpVirement = OvpVirement.this;
            CihMBanking.sesPMTR.setSessionCurrentTransactionResume(ovpVirement.GetTransaction(this.val$issuarAccount, this.val$acquiredAccount, ovpVirement.uiManager.stateMachine.findTxtAmount(this.val$cntTransfertForm), OvpVirement.this.uiManager.stateMachine.findTxtAreaMotif(this.val$cntTransfertForm)));
            CihMBanking.sesPMTR.setSessionSavedContainer(OvpVirement.this.uiManager.stateMachine.findCntGlobalTransfer(this.val$cntTransfertForm));
            OvpVirement ovpVirement2 = OvpVirement.this;
            ovpVirement2.SwitchTransfertForms(this.val$cntTransfertForm, ovpVirement2.uiManager.stateMachine.findCntGlobalTransfer(this.val$cntTransfertForm), OvpVirement.this.GetTransferSecurityForm(this.val$cntTransfertForm), false);
        }
    }

    public TransactionResume GetTransaction(Container container, Container container2, TextArea textArea, TextArea textArea2) {
        TransactionResume transactionResume = new TransactionResume();
        transactionResume.Amount = textArea.getText();
        transactionResume.Motif = textArea2.getText();
        transactionResume.PassHB = "";
        Container container3 = (Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0);
        transactionResume.IssuerAccountOwner = ((Label) ((Container) ((Container) ((Container) container3.getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getText();
        Account account = (Account) ((B3gService) container3.getClientProperty("AccountClient"));
        transactionResume.IssuerAccountNumber = account.rib;
        if (account.legalSituation != null) {
            if (account.legalSituation.equals("MI")) {
                transactionResume.isTutor = "1";
            } else {
                transactionResume.isTutor = "0";
            }
        } else {
            transactionResume.isTutor = "0";
        }
        Container container4 = (Container) ((Container) ((Container) ((Container) ((Container) ((Tabs) container2.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
        transactionResume.CreditedAccountAlias = ((Label) container4.getComponentAt(0)).getText();
        transactionResume.CreditedAccountNumber = StringTools.RibFormatWithoutSP(((Label) container4.getComponentAt(1)).getText());
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
                beneficiary.flagDeleted = "false";
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

    class 4 implements DataChangedListener {
        public void dataChanged(int i, int i2) {
        }

        4() {
        }
    }

    public void HidePassWordOnKeyPressed(TextField textField) {
        textField.addDataChangeListener(new 4());
    }

    public Container GetTransferSecurityForm(Container container) {
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container2 = (Container) this.uib.findByName("Container17", createContainer);
        container2.removeAll();
        container2.setLayout(BoxLayout.y());
        container2.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 5(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 6(b3GCIHEcode));
        return createContainer;
    }

    class 5 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        5(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            this.val$Parent.revalidate();
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        6(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OvpVirement.access$102(OvpVirement.this, this.val$eCode1.getValue());
            TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
            sessionCurrentTransactionResume.PassHB = OvpVirement.access$100(OvpVirement.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (OvpVirement.access$100(OvpVirement.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(OvpVirement.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(10, sessionCurrentTransactionResume, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 300013);
            }
        }
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }
}
