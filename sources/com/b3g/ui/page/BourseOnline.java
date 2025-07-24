package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.ServiceManager;
import com.b3g.services.TransactionResume;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.ui.Command;
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
import com.codename1.ui.util.UITimer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class BourseOnline extends B3GPage {
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();

    static /* synthetic */ String access$000(BourseOnline bourseOnline) {
        return bourseOnline.VnewaliasText;
    }

    static /* synthetic */ String access$002(BourseOnline bourseOnline, String str) {
        bourseOnline.VnewaliasText = str;
        return str;
    }

    public BourseOnline(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        Container container = new Container(new BoxLayout(2));
        container.setUIID("ad_CntAccountDetailsMain");
        CihMBanking.sesPMTR.setBourseToken("");
        try {
            ServiceResponse CheckBourseOnline = CheckBourseOnline();
            if (CheckBourseOnline.getStatusCode().equals("000")) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new B3gContainer(GetContainerTransferForm(" ", null), "Alimentation Bourse en ligne"));
                this.uiManager.DrawTabsWithNavigation(container, arrayList);
                this.thisContainer.addComponent(container);
            } else {
                new UITimer(new 1(CheckBourseOnline)).schedule(100, false, this.uiManager.currentForm);
            }
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    class 1 implements Runnable {
        final /* synthetic */ ServiceResponse val$responseCheck;

        1(ServiceResponse serviceResponse) {
            this.val$responseCheck = serviceResponse;
        }

        public void run() {
            BourseOnline.this.uiManager.ShowMessageTransaction(BourseOnline.this.uiManager.stateMachine, this.val$responseCheck.getStatusLabel(), null);
            BourseOnline.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    public Container GetContainerTransferForm(String str, ArrayList arrayList) {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransferFormTabGlobal");
        if (Display.getInstance().getPlatformName().equals("ios")) {
            createContainer.setScrollableY(false);
        }
        new Account().uiManager = this.uiManager;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList());
        Container DrawListContainerWalletTransfer = this.uiManager.DrawListContainerWalletTransfer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getCanDebitAccount(arrayList2), 1, 16, str, null, null, null);
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(DrawListContainerWalletTransfer);
        this.uiManager.stateMachine.findTxtMotif(createContainer).setMaxSize(20);
        this.uiManager.stateMachine.findLblDayValue(createContainer).setHidden(true);
        this.uiManager.stateMachine.findLblWeekValue(createContainer).setHidden(true);
        this.uiManager.stateMachine.findLblDay(createContainer).setHidden(true);
        this.uiManager.stateMachine.findLblWeek(createContainer).setHidden(true);
        this.uiManager.ControlTextfield(this.uiManager.stateMachine.findTxtAmount(createContainer), "AMOUNT", createContainer);
        if (arrayList2.size() > 0) {
            this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 2(createContainer, DrawListContainerWalletTransfer));
        } else {
            ((Container) createContainer.getComponentAt(0)).removeComponent(((Container) createContainer.getComponentAt(0)).getComponentAt(6));
            createContainer.revalidate();
        }
        createContainer.setScrollVisible(false);
        return createContainer;
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$cntTransfertForm;
        final /* synthetic */ Container val$issuarAccount;

        2(Container container, Container container2) {
            this.val$cntTransfertForm = container;
            this.val$issuarAccount = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (BourseOnline.this.uiManager.stateMachine.findTxtAmount(this.val$cntTransfertForm).getText().length() == 0 || BourseOnline.this.uiManager.stateMachine.findTxtMotif(this.val$cntTransfertForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(BourseOnline.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                    return;
                }
                BourseOnline bourseOnline = BourseOnline.this;
                TransactionResume GetTransaction = bourseOnline.GetTransaction(this.val$issuarAccount, null, bourseOnline.uiManager.stateMachine.findTxtAmount(this.val$cntTransfertForm), BourseOnline.this.uiManager.stateMachine.findTxtMotif(this.val$cntTransfertForm));
                if (BourseOnline.this.SimulationBourseOnline(GetTransaction).getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.setSessionCurrentTransactionResume(GetTransaction);
                    CihMBanking.sesPMTR.setSessionSavedContainer(BourseOnline.this.uiManager.stateMachine.findCntGlobalTransfer(this.val$cntTransfertForm));
                    BourseOnline bourseOnline2 = BourseOnline.this;
                    bourseOnline2.SwitchTransfertForms(this.val$cntTransfertForm, bourseOnline2.uiManager.stateMachine.findCntGlobalTransfer(this.val$cntTransfertForm), BourseOnline.this.GetTransferSecurityForm(this.val$cntTransfertForm), false);
                    BourseOnline.this.uiManager.btnBack.getListeners().clear();
                    BourseOnline.this.uiManager.btnBack.addActionListener(new 1());
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(BourseOnline.this.uiManager.stateMachine, "Opération non autorisée.", null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(BourseOnline.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration", null);
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                2.this.val$cntTransfertForm.replace((Container) 2.this.val$cntTransfertForm.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
                2.this.val$cntTransfertForm.revalidate();
                BourseOnline.this.uiManager.btnBack.getListeners().clear();
                BourseOnline.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    BourseOnline.this.uiManager.GoBack();
                    CihMBanking.sesPMTR.setBourseToken("");
                }
            }
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
        transactionResume.isTutor = "0";
        if (account.legalSituation.equals("MI")) {
            transactionResume.isTutor = "1";
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

    public Container GetTransferSecurityForm(Container container) {
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container2 = (Container) this.uib.findByName("Container17", createContainer);
        container2.removeAll();
        container2.setLayout(BoxLayout.y());
        container2.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 3(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 4(b3GCIHEcode));
        return createContainer;
    }

    class 3 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        3(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            BourseOnline.this.uiManager.btnBack.getListeners().clear();
            BourseOnline.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                BourseOnline.this.uiManager.GoBack();
                CihMBanking.sesPMTR.setBourseToken("");
            }
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        4(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            BourseOnline.access$002(BourseOnline.this, this.val$eCode1.getValue());
            TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
            sessionCurrentTransactionResume.PassHB = BourseOnline.access$000(BourseOnline.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (BourseOnline.access$000(BourseOnline.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(BourseOnline.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            ServiceResponse ExecutionBourseOnline = BourseOnline.this.ExecutionBourseOnline(sessionCurrentTransactionResume.PassHB);
            if (ExecutionBourseOnline.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(BourseOnline.this.uiManager.stateMachine, ExecutionBourseOnline.getStatusLabel(), null);
                BourseOnline.this.UpdateAccountBalance(sessionCurrentTransactionResume.IssuerAccountNumber, sessionCurrentTransactionResume.Amount);
                new UITimer(new BourseOnline$4$$ExternalSyntheticLambda0(this)).schedule(500, false, BourseOnline.this.uiManager.currentForm);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(BourseOnline.this.uiManager.stateMachine, ExecutionBourseOnline.getStatusLabel(), null);
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-BourseOnline$4() {
            BourseOnline.this.uiManager.NavigateToPageById(90);
        }
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
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

    public ServiceResponse CheckBourseOnline() {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CHECK", "0");
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(138);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse SimulationBourseOnline(TransactionResume transactionResume) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("accountNumber", transactionResume.IssuerAccountNumber);
        hashtable.put("comment", transactionResume.Motif);
        hashtable.put("amount", transactionResume.Amount);
        hashtable.put("CHECK", "1");
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(138);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        try {
            ServiceResponse serviceResponse = (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
            FillBourseOnlineArrayDataFromServiceResponse(serviceResponse);
            return serviceResponse;
        } catch (Exception unused) {
            this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Une erreur s'est produite , veuillez réessayer plus tard", null);
            return null;
        }
    }

    private void FillBourseOnlineArrayDataFromServiceResponse(ServiceResponse serviceResponse) {
        if (serviceResponse.getStatusCode().equals("000")) {
            CihMBanking.sesPMTR.setBourseToken(serviceResponse.getStatusLabel());
        }
    }

    public ServiceResponse ExecutionBourseOnline(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("TOKEN", CihMBanking.sesPMTR.getBourseToken());
        hashtable.put("Pass", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(139);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        try {
            return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
        } catch (Exception unused) {
            this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Une erreur s'est produite , veuillez réessayer plus tard", null);
            return null;
        }
    }

    public void UpdateAccountBalance(String str, String str2) {
        Double StringToDouble = DataTools.StringToDouble(str2);
        Iterator it = CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().iterator();
        while (it.hasNext()) {
            Account account = (Account) it.next();
            if (account.rib.equals(str)) {
                Double valueOf = Double.valueOf(DataTools.round(Double.valueOf(DataTools.StringToDouble(account.balanceBrut).doubleValue() - StringToDouble.doubleValue())));
                account.balanceBrut = valueOf.toString();
                account.balance = DataTools.FormatAmountWithCurrency(DataTools.printCurrency(valueOf.toString()), account.devise).replace(',', '.');
            }
        }
    }
}
