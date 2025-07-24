package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Plafond;
import com.b3g.services.TransactionResume;
import com.b3g.services.TransferHistoric;
import com.b3g.services.TransfertDATA;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TopUpPage extends B3GPage {
    public TopUpPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        Container container = new Container(new BoxLayout(2));
        container.setUIID("mn_cntMenuItem");
        try {
            TransfertDATA TransfertDATAProcess = new TransfertDATA().TransfertDATAProcess(31);
            Container container2 = new Container(new BoxLayout(2));
            container2.setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(GetContainerTopUpForm(TransfertDATAProcess.getBeneficiaryList(), TransfertDATAProcess.getbeneficiaryStatusLabel(), TransfertDATAProcess.getPlafond()), "RECHARGE"));
            arrayList.add(new B3gContainer(GetContainerTransferHistoric(TransfertDATAProcess.getTransferHistoricList(), TransfertDATAProcess), "HISTORIQUE"));
            this.uiManager.DrawTabsWithNavigation(container2, arrayList);
            container.addComponent(container2);
        } catch (Exception unused) {
            container.addComponent(this.uiManager.GetGoBackHome());
        }
        this.thisContainer = container;
        return container;
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

    public Container GetContainerTopUpForm(ArrayList arrayList, String str, ArrayList arrayList2) {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransferFormTab");
        Account account = new Account();
        account.uiManager = this.uiManager;
        ArrayList AccountProcess = account.AccountProcess(11);
        Container DrawListContainer = this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, AccountProcess, 1, 16, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, null, null, null);
        Container DrawListContainer2 = this.uiManager.DrawListContainer("GloabalContainerV2", "Carte à recharger", Boolean.TRUE, arrayList, 1, 17, DataTools.FormatUnicode(str), null, null, null, null);
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(DrawListContainer);
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(DrawListContainer2);
        this.uiManager.stateMachine.findLblDayValue(createContainer).setText(((Plafond) arrayList2.get(0)).BinatnaDaily);
        this.uiManager.stateMachine.findLblWeekValue(createContainer).setText(((Plafond) arrayList2.get(0)).BinatnaWeekly);
        if (AccountProcess.size() > 0 && arrayList.size() > 0) {
            this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 1(createContainer, DrawListContainer, DrawListContainer2, arrayList));
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
        final /* synthetic */ ArrayList val$BeneficiaryList;
        final /* synthetic */ Container val$acquiredAccount;
        final /* synthetic */ Container val$cntTransfertForm;
        final /* synthetic */ Container val$issuarAccount;

        1(Container container, Container container2, Container container3, ArrayList arrayList) {
            this.val$cntTransfertForm = container;
            this.val$issuarAccount = container2;
            this.val$acquiredAccount = container3;
            this.val$BeneficiaryList = arrayList;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (TopUpPage.this.uiManager.stateMachine.findTxtAmount(this.val$cntTransfertForm).getText().length() == 0 || TopUpPage.this.uiManager.stateMachine.findTxtAreaMotif(this.val$cntTransfertForm).getText().length() == 0 || TopUpPage.this.uiManager.stateMachine.findTxtHBPassWord(this.val$cntTransfertForm).getText().length() == 0) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TopUpPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            TopUpPage topUpPage = TopUpPage.this;
            TransactionResume GetTransaction = topUpPage.GetTransaction(this.val$issuarAccount, this.val$acquiredAccount, topUpPage.uiManager.stateMachine.findTxtAmount(this.val$cntTransfertForm), TopUpPage.this.uiManager.stateMachine.findTxtAreaMotif(this.val$cntTransfertForm), TopUpPage.this.uiManager.stateMachine.findTxtHBPassWord(this.val$cntTransfertForm), this.val$BeneficiaryList);
            TopUpPage.this.uiManager.stateMachine.findTxtAmount(this.val$cntTransfertForm).clear();
            TopUpPage.this.uiManager.stateMachine.findTxtAreaMotif(this.val$cntTransfertForm).setText("");
            TopUpPage.this.uiManager.stateMachine.findTxtHBPassWord(this.val$cntTransfertForm).setText("");
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(10, GetTransaction, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 300014);
        }
    }

    public TransactionResume GetTransaction(Container container, Container container2, TextArea textArea, TextArea textArea2, TextArea textArea3, ArrayList arrayList) {
        TransactionResume transactionResume = new TransactionResume();
        transactionResume.Amount = textArea.getText();
        transactionResume.Motif = textArea2.getText();
        transactionResume.PassHB = textArea3.getText();
        Container container3 = (Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0);
        transactionResume.IssuerAccountOwner = ((Label) ((Container) ((Container) ((Container) container3.getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getText();
        transactionResume.IssuerAccountNumber = ((Account) ((B3gService) container3.getClientProperty("AccountClient"))).rib;
        Container container4 = (Container) ((Container) ((Container) ((Container) ((Container) ((Tabs) container2.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
        transactionResume.CreditedAccountAlias = ((Label) container4.getComponentAt(0)).getText();
        transactionResume.CreditedAccountNumber = DataTools.DisHiddenCard(((Label) container4.getComponentAt(1)).getText(), arrayList);
        transactionResume.TypeOperation = "TOPUP";
        return transactionResume;
    }
}
