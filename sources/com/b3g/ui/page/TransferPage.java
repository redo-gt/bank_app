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
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TransferPage extends B3GPage {
    public TransferPage(Object obj, Object obj2, int i) {
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
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransferFormTab");
        Account account = new Account();
        account.uiManager = this.uiManager;
        ArrayList AccountProcess = account.AccountProcess(11);
        GetSavingAccountFromBeneficiaryList(arrayList, AccountProcess);
        Container DrawListContainer = this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à créditer", Boolean.TRUE, RemoveFirstCurrentClientAccount(AddCurrentClientAccount(arrayList)), 1, 17, DataTools.FormatUnicode(str), null, null, null, null);
        Container DrawListContainerTransfer = this.uiManager.DrawListContainerTransfer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, AccountProcess, 1, 16, str, null, DrawListContainer, CihMBanking.sesPMTR.getSessionClient().getClient_BeneficiaryList());
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(DrawListContainerTransfer);
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(DrawListContainer);
        this.uiManager.stateMachine.findLblDayValue(createContainer).setText(((Plafond) arrayList2.get(0)).TransferDaily);
        this.uiManager.stateMachine.findLblWeekValue(createContainer).setText(((Plafond) arrayList2.get(0)).TransferWeekly);
        if (AccountProcess.size() > 0 && arrayList.size() > 0) {
            this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 1(createContainer, DrawListContainerTransfer, DrawListContainer));
        } else {
            createContainer.removeComponent(createContainer.getComponentAt(8));
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
        final /* synthetic */ Container val$acquiredAccount;
        final /* synthetic */ Container val$cntTransfertForm;
        final /* synthetic */ Container val$issuarAccount;

        1(Container container, Container container2, Container container3) {
            this.val$cntTransfertForm = container;
            this.val$issuarAccount = container2;
            this.val$acquiredAccount = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (TransferPage.this.uiManager.stateMachine.findTxtAmount(this.val$cntTransfertForm).getText().length() == 0 || TransferPage.this.uiManager.stateMachine.findTxtAreaMotif(this.val$cntTransfertForm).getText().length() == 0 || TransferPage.this.uiManager.stateMachine.findTxtHBPassWord(this.val$cntTransfertForm).getText().length() == 0) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            TransferPage transferPage = TransferPage.this;
            TransactionResume GetTransaction = transferPage.GetTransaction(this.val$issuarAccount, this.val$acquiredAccount, transferPage.uiManager.stateMachine.findTxtAmount(this.val$cntTransfertForm), TransferPage.this.uiManager.stateMachine.findTxtAreaMotif(this.val$cntTransfertForm), TransferPage.this.uiManager.stateMachine.findTxtHBPassWord(this.val$cntTransfertForm));
            TransferPage.this.uiManager.stateMachine.findTxtAmount(this.val$cntTransfertForm).clear();
            TransferPage.this.uiManager.stateMachine.findTxtAreaMotif(this.val$cntTransfertForm).setText("");
            TransferPage.this.uiManager.stateMachine.findTxtHBPassWord(this.val$cntTransfertForm).setText("");
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(10, GetTransaction, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 300013);
        }
    }

    public TransactionResume GetTransaction(Container container, Container container2, TextArea textArea, TextArea textArea2, TextArea textArea3) {
        TransactionResume transactionResume = new TransactionResume();
        transactionResume.Amount = textArea.getText();
        transactionResume.Motif = textArea2.getText();
        transactionResume.PassHB = textArea3.getText();
        Container container3 = (Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0);
        transactionResume.IssuerAccountOwner = ((Label) ((Container) ((Container) ((Container) container3.getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getText();
        transactionResume.IssuerAccountNumber = ((Account) ((B3gService) container3.getClientProperty("AccountClient"))).rib;
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

    class 2 implements DataChangedListener {
        public void dataChanged(int i, int i2) {
        }

        2() {
        }
    }

    public void HidePassWordOnKeyPressed(TextField textField) {
        textField.addDataChangeListener(new 2());
    }
}
