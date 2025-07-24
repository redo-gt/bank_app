package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.Card;
import com.b3g.services.ServiceManager;
import com.b3g.services.TransactionResume;
import com.b3g.services.TransferHistoric;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class PrepaidCardDebit extends B3GPage {
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();
    String typeCard = "";

    static /* synthetic */ String access$000(PrepaidCardDebit prepaidCardDebit) {
        return prepaidCardDebit.VnewaliasText;
    }

    static /* synthetic */ String access$002(PrepaidCardDebit prepaidCardDebit, String str) {
        prepaidCardDebit.VnewaliasText = str;
        return str;
    }

    public PrepaidCardDebit(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public void setTypeCard(String str) {
        this.typeCard = str;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            new ArrayList();
            ArrayList transferHistoricProcess = transferHistoricProcess();
            TransferHistoric transferHistoric = new TransferHistoric();
            Beneficiary beneficiary = new Beneficiary();
            new ArrayList();
            new ArrayList();
            ArrayList FillTransferHistoricArrayDataFromServiceResponse = transferHistoric.FillTransferHistoricArrayDataFromServiceResponse((ServiceResponse) transferHistoricProcess.get(0));
            ArrayList FillBeneficiaryArrayDataFromServiceResponse = beneficiary.FillBeneficiaryArrayDataFromServiceResponse((ServiceResponse) transferHistoricProcess.get(1));
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(GetContainerDebitCardToAccount(FillBeneficiaryArrayDataFromServiceResponse), "DEBITER CARTE"));
            arrayList.add(new B3gContainer(GetContainerTransferHistoric(FillTransferHistoricArrayDataFromServiceResponse), "HISTORIQUE"));
            this.uiManager.DrawTabsWithNavigation(container, arrayList);
            this.thisContainer.addComponent(container);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    public Container GetContainerTransferHistoric(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            ((TransferHistoric) arrayList.get(i)).groupKey = ((TransferHistoric) arrayList.get(i)).OperationDate.substring(0, 10);
            arrayList2.add((B3gService) arrayList.get(i));
        }
        Container container = new Container(new BoxLayout(2));
        if (arrayList2.size() > 0) {
            this.uiManager.Draw_GroupBy(container, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 8, null);
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
        }
        return container;
    }

    public Container GetContainerDebitCardToAccount(ArrayList arrayList) {
        boolean z;
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "DebitCardCnt");
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList());
        ArrayList binatnaCardAmongBeneficiary = getBinatnaCardAmongBeneficiary(arrayList);
        if (binatnaCardAmongBeneficiary.size() > 0) {
            new Account().uiManager = this.uiManager;
            purgeForbidenAccount(arrayList2);
            Container DrawListContainer = this.uiManager.DrawListContainer("GloabalContainerV2", "Carte à débiter", Boolean.TRUE, binatnaCardAmongBeneficiary, 1, 18, "Aucune catre à débiter n'est disponible pour le moment .", null, null, null, null);
            Container DrawListContainer2 = this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à créditer", Boolean.TRUE, arrayList2, 1, 16, "Aucun compte à créditer n'est disponible pour le moment .", null, null, null, null);
            this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(DrawListContainer);
            this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
            this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(DrawListContainer2);
            if (Display.getInstance().getPlatformName().equals("ios")) {
                createContainer.setScrollableY(false);
            }
            this.uiManager.ControlTextfield(this.uiManager.stateMachine.findTxtAmount(createContainer), "AMOUNT", createContainer);
            if (arrayList2.size() > 0 && arrayList.size() > 0) {
                z = false;
                this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 1(createContainer, DrawListContainer, DrawListContainer2, arrayList));
            } else {
                z = false;
                ((Container) createContainer.getComponentAt(0)).removeComponent(((Container) createContainer.getComponentAt(0)).getComponentAt(6));
                createContainer.revalidate();
            }
        } else {
            z = false;
            SpanLabel spanLabel = new SpanLabel("Vous ne pouvez pas utiliser ce service car vous ne disposez d'aucune carte Binatna.");
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            createContainer.removeAll();
            createContainer.addComponent(spanLabel);
            createContainer.revalidate();
        }
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            this.uiManager.stateMachine.findBtnTransferValidation(createContainer).setEnabled(z);
        }
        createContainer.setScrollVisible(z);
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
            if (CihMBanking.sesPMTR.getSessionClient().getShowDebitCardService().equals("1")) {
                if (PrepaidCardDebit.this.uiManager.stateMachine.findTxtAmount(this.val$cntTransfertForm).getText().length() == 0 || PrepaidCardDebit.this.uiManager.stateMachine.findTxtMotif(this.val$cntTransfertForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PrepaidCardDebit.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                    return;
                }
                PrepaidCardDebit prepaidCardDebit = PrepaidCardDebit.this;
                TransactionResume GetTransaction = prepaidCardDebit.GetTransaction(this.val$issuarAccount, this.val$acquiredAccount, prepaidCardDebit.uiManager.stateMachine.findTxtAmount(this.val$cntTransfertForm), PrepaidCardDebit.this.uiManager.stateMachine.findTxtMotif(this.val$cntTransfertForm), this.val$BeneficiaryList, 900118);
                ServiceResponse sendOtpTopUpProcess = ServiceManager.sendOtpTopUpProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1021", 300021, "1", "0", GetTransaction.IssuerAccountNumber, GetTransaction.CreditedAccountNumber, GetTransaction.Amount, "");
                if (sendOtpTopUpProcess.getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.setSessionCurrentTransactionResume(GetTransaction);
                    CihMBanking.sesPMTR.setSessionSavedContainer(PrepaidCardDebit.this.uiManager.stateMachine.findCntGlobalTransfer(this.val$cntTransfertForm));
                    PrepaidCardDebit prepaidCardDebit2 = PrepaidCardDebit.this;
                    prepaidCardDebit2.SwitchTransfertForms(this.val$cntTransfertForm, prepaidCardDebit2.uiManager.stateMachine.findCntGlobalTransfer(this.val$cntTransfertForm), PrepaidCardDebit.this.GetTransferSecurityForm(this.val$cntTransfertForm), false);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PrepaidCardDebit.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpTopUpProcess.getStatusLabel()), null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PrepaidCardDebit.this.uiManager.stateMachine, "Ce service est momentanément indisponible", null);
        }
    }

    public TransactionResume GetTransaction(Container container, Container container2, TextArea textArea, TextArea textArea2, ArrayList arrayList, int i) {
        TransactionResume transactionResume = new TransactionResume();
        transactionResume.Amount = textArea.getText();
        transactionResume.Motif = textArea2.getText();
        transactionResume.PassHB = "";
        Container container3 = (Container) ((Container) ((Container) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
        transactionResume.IssuerAccountOwner = ((Label) container3.getComponentAt(0)).getText();
        transactionResume.IssuerAccountNumber = container3.getComponentAt(1).getName();
        Container container4 = (Container) ((Container) ((Container) ((Container) ((Container) ((Tabs) container2.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
        transactionResume.CreditedAccountAlias = ((Label) container4.getComponentAt(0)).getText();
        transactionResume.SelectedService = Integer.toString(i);
        if (transactionResume.SelectedService.equals(Integer.toString(900118))) {
            transactionResume.CreditedAccountNumber = ((Label) container4.getComponentAt(1)).getText();
        } else {
            transactionResume.CreditedAccountNumber = container4.getComponentAt(1).getName();
        }
        transactionResume.TypeOperation = "DebitCard";
        transactionResume.field1 = "";
        transactionResume.field2 = "";
        return transactionResume;
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }

    public Container GetTransferSecurityForm(Container container) {
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container2 = (Container) this.uib.findByName("Container17", createContainer);
        container2.removeAll();
        container2.setLayout(BoxLayout.y());
        container2.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 2(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 3(b3GCIHEcode));
        return createContainer;
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        2(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            this.val$Parent.revalidate();
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        3(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PrepaidCardDebit.access$002(PrepaidCardDebit.this, this.val$eCode1.getValue());
            TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
            sessionCurrentTransactionResume.PassHB = PrepaidCardDebit.access$000(PrepaidCardDebit.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (PrepaidCardDebit.access$000(PrepaidCardDebit.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(PrepaidCardDebit.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(71, sessionCurrentTransactionResume, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, Integer.parseInt(sessionCurrentTransactionResume.SelectedService));
            }
        }
    }

    ArrayList getBinatnaCardAmongBeneficiary(ArrayList arrayList) {
        Beneficiary beneficiary;
        Card card = (Card) CihMBanking.sesPMTR.objctCrdCurrent;
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            beneficiary = (Beneficiary) it.next();
            String str = this.typeCard;
            str.hashCode();
            switch (str) {
                case "E-SHOPPING":
                case "ALMOUSSAFIR":
                    if (!beneficiary.typeBenef.equals("300018")) {
                        break;
                    } else {
                        arrayList2.add(beneficiary);
                        break;
                    }
                case "BINATNA":
                    if (beneficiary.typeBenef.equals("300014") && beneficiary.balance.length() > 0) {
                        arrayList2.add(beneficiary);
                        break;
                    }
                    break;
            }
        }
        return getCardToShowFirst(card.plainCardNumber, arrayList2);
    }

    ArrayList getCardToShowFirst(String str, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Beneficiary beneficiary = (Beneficiary) it.next();
            if (beneficiary.cardNumber.equals(str)) {
                arrayList2.add(beneficiary);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Beneficiary beneficiary2 = (Beneficiary) it2.next();
            if (!beneficiary2.cardNumber.equals(str)) {
                arrayList2.add(beneficiary2);
            }
        }
        return arrayList2;
    }

    ArrayList getCardToShowFirstTime(String str, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Beneficiary beneficiary = (Beneficiary) it.next();
            if (!beneficiary.cardNumber.equals(str)) {
                arrayList2.add(beneficiary);
            }
        }
        return arrayList2;
    }

    void purgeForbidenAccount(ArrayList arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            String substring = ((Account) arrayList.get(i)).rib.substring(13, 17);
            if (((Account) arrayList.get(i)).canCredit.equals("False") || substring.equals("2310") || substring.equals("2360") || substring.equals("2311") || substring.substring(0, 2).equals("25")) {
                arrayList.remove(i);
                i--;
            }
            i++;
        }
    }

    public ArrayList transferHistoricProcess() {
        ServiceRequest serviceRequest = new ServiceRequest();
        new ArrayList();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        if (this.typeCard.equals("ALMOUSSAFIR") || this.typeCard.equals("E-SHOPPING")) {
            hashtable.put("TYPE_BENEF", "300018");
        } else if (this.typeCard.equals("BINATNA")) {
            hashtable.put("TYPE_BENEF", "300014");
        } else {
            hashtable.put("TYPE_BENEF", "");
        }
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900132);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ArrayList) serviceManager.Run(serviceRequest);
    }
}
