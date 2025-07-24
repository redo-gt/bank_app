package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.CashOutOperatoion;
import com.b3g.services.FavoriteContactService;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.contacts.Contact;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.regex.RE;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CashOutPage extends B3GPage {
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();

    static /* synthetic */ boolean access$000(CashOutPage cashOutPage, String str, String str2) {
        return cashOutPage.sendBackPin(str, str2);
    }

    static /* synthetic */ String access$100(CashOutPage cashOutPage) {
        return cashOutPage.VnewaliasText;
    }

    static /* synthetic */ String access$102(CashOutPage cashOutPage, String str) {
        cashOutPage.VnewaliasText = str;
        return str;
    }

    static /* synthetic */ boolean access$200(CashOutPage cashOutPage, String str, String str2) {
        return cashOutPage.resetCashOutSrv(str, str2);
    }

    public CashOutPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "CashOutCnt");
        Button button = (Button) this.uib.findByName("addCashOutBtn", this.thisContainer);
        button.addActionListener(new 1());
        Container container = (Container) this.uib.findByName("CashOut", this.thisContainer);
        container.setLayout(new BoxLayout(2));
        container.setScrollVisible(false);
        container.setScrollableY(false);
        container.setUIID("mn_cntMenuItem");
        try {
            ArrayList CashOutProcess = CashOutProcess();
            new ArrayList();
            ArrayList cashOutOperations = getCashOutOperations((ServiceResponse) CashOutProcess.get(0));
            new ArrayList();
            ArrayList currentCashOut = getCurrentCashOut(cashOutOperations);
            Container container2 = new Container(new BoxLayout(2));
            container2.setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList = new ArrayList();
            if (currentCashOut.isEmpty() || currentCashOut == null) {
                arrayList.add(new B3gContainer(getCashOutCnt(((ServiceResponse) CashOutProcess.get(1)).getParamsOut()), "MAD"));
                button.setEnabled(false);
                button.setVisible(false);
            } else {
                button.setEnabled(true);
                button.setVisible(true);
                arrayList.add(new B3gContainer(getCurrentCashOutForm(currentCashOut), "MAD"));
            }
            arrayList.add(new B3gContainer(getCashOutHistoricCnt(cashOutOperations), "HISTORIQUE"));
            this.uiManager.DrawTabsWithNavigation(container2, arrayList);
            container.addComponent(container2);
        } catch (Exception unused) {
            Settings.setNightMode(container, 0);
            container.addComponent(this.uiManager.GetGoBackHome());
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CashOutPage.this.uiManager.NavigateToPageById(113);
        }
    }

    private Container getCurrentCashOutForm(ArrayList arrayList) {
        Container createContainer = this.uib.createContainer("/cihv3", "CurrentCashOutForm");
        createContainer.setScrollableY(true);
        createContainer.setScrollVisible(false);
        Container container = (Container) this.uib.findByName("Main", createContainer);
        container.setScrollVisible(false);
        for (int i = 0; i < arrayList.size(); i++) {
            container.add(getCurrentCashOut(((CashOutOperatoion) arrayList.get(i)).getBeneficiaryGsm(), ((CashOutOperatoion) arrayList.get(i)).getAmount(), ((CashOutOperatoion) arrayList.get(i)).getCreateDate(), ((CashOutOperatoion) arrayList.get(i)).getExpirationDate(), ((CashOutOperatoion) arrayList.get(i)).getOperationCode()));
        }
        Settings.setNightMode(createContainer, 0);
        return createContainer;
    }

    private Container getCurrentCashOut(String str, String str2, String str3, String str4, String str5) {
        Container container = new Container(new BorderLayout());
        container.setUIID("margin_0_1_0_0");
        Label label = new Label("01-01-2021");
        label.setUIID("g_lblDashBoardValueBlue");
        label.setText(str3);
        Container createContainer = this.uib.createContainer("/cihv3", "CurrentCashOutCnt");
        ((Label) this.uib.findByName("beneficiaryLbl", createContainer)).setText(str);
        ((Label) this.uib.findByName("amountLbl", createContainer)).setText(str2);
        ((Label) this.uib.findByName("dateLbl", createContainer)).setText(str4);
        ((Button) this.uib.findByName("sendBackBtn", createContainer)).addActionListener(new 2(str5));
        Button button = (Button) this.uib.findByName("resetCashOutBtn", createContainer);
        button.setHidden(false);
        button.setEnabled(true);
        button.addActionListener(new 3(str5));
        container.add("North", label);
        container.add("Center", createContainer);
        Settings.setNightMode(container, 0);
        return container;
    }

    class 2 implements ActionListener {
        final /* synthetic */ String val$reference;

        2(String str) {
            this.val$reference = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CashOutPage.access$000(CashOutPage.this, this.val$reference, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ String val$reference;

        3(String str) {
            this.val$reference = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CashOutPage.this.ShowConfirmation(this.val$reference);
        }
    }

    private Container getCashOutCnt(Hashtable hashtable) {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransferFormTab");
        TextField textField = (TextField) this.uib.findByName("txtBenefName", createContainer);
        createContainer.setScrollVisible(false);
        this.uiManager.stateMachine.findAddFromRepBtn(createContainer).setHidden(true);
        textField.addDataChangedListener(new CashOutPage$$ExternalSyntheticLambda0(textField, createContainer));
        new Account().uiManager = this.uiManager;
        ArrayList client_AccountList = CihMBanking.sesPMTR.getSessionClient().getClient_AccountList();
        Container DrawListContainerTransfer = this.uiManager.DrawListContainerTransfer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getCanDebitAccount(client_AccountList), 1, 16, "Aucun compte n'est disponible pour cette opération .", null, null, null);
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(DrawListContainerTransfer);
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
        this.uiManager.stateMachine.findLblOpPlfValue(createContainer).setText(hashtable.get("PlafondParOperation").toString());
        this.uiManager.stateMachine.findLblDayPlfValue(createContainer).setText(hashtable.get("PlafondPar").toString());
        if (Display.getInstance().getPlatformName().equals("ios")) {
            createContainer.setScrollableY(false);
        }
        this.uiManager.ControlTextfield(this.uiManager.stateMachine.findTxtCashOutAmount(createContainer), "AMOUNT", createContainer);
        if (client_AccountList.size() > 0) {
            this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 4(createContainer, DrawListContainerTransfer));
        } else {
            ((Container) createContainer.getComponentAt(0)).removeComponent(((Container) createContainer.getComponentAt(0)).getComponentAt(6));
            createContainer.revalidate();
        }
        createContainer.setScrollableY(false);
        createContainer.setScrollVisible(false);
        Settings.setNightMode(createContainer, 0);
        return createContainer;
    }

    static /* synthetic */ void lambda$getCashOutCnt$0(TextField textField, Container container, int i, int i2) {
        if (DataTools.isValidInput(textField.getText())) {
            textField.putClientProperty("LastValid", textField.getText());
        } else {
            textField.stopEditing();
            textField.setText((String) textField.getClientProperty("LastValid"));
            textField.startEditingAsync();
        }
        System.err.println("Click " + textField.getText());
        container.revalidate();
    }

    class 4 implements ActionListener {
        final /* synthetic */ Container val$cntCashOutForm;
        final /* synthetic */ Container val$issuarAccount;

        4(Container container, Container container2) {
            this.val$cntCashOutForm = container;
            this.val$issuarAccount = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (CashOutPage.this.uiManager.stateMachine.findTxtCashOutAmount(this.val$cntCashOutForm).getText().length() == 0 || CashOutPage.this.uiManager.stateMachine.findTxtBeneGSM(this.val$cntCashOutForm).getText().length() == 0 || CashOutPage.this.uiManager.stateMachine.findTxtBenefName(this.val$cntCashOutForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CashOutPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                    return;
                }
                if (!StringTools.phoneNumberChecker(CashOutPage.this.uiManager.stateMachine.findTxtBeneGSM(this.val$cntCashOutForm).getText())) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CashOutPage.this.uiManager.stateMachine, "Merci d'entrer un numéro GSM valide", null);
                    return;
                }
                CashOutPage cashOutPage = CashOutPage.this;
                CashOutOperatoion GetTransaction = cashOutPage.GetTransaction(this.val$issuarAccount, cashOutPage.uiManager.stateMachine.findTxtBenefName(this.val$cntCashOutForm), CashOutPage.this.uiManager.stateMachine.findTxtBeneGSM(this.val$cntCashOutForm), CashOutPage.this.uiManager.stateMachine.findTxtCashOutAmount(this.val$cntCashOutForm));
                ServiceResponse sendOtpProcessTransfer = ServiceManager.sendOtpProcessTransfer(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1002", 300017, "1", "0", GetTransaction.getAccountNumber(), GetTransaction.getBeneficiaryGsm(), GetTransaction.getAmount(), "", GetTransaction.getBeneficiary(), CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, "");
                if (sendOtpProcessTransfer.getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.setCashOutOperatoion(GetTransaction);
                    CihMBanking.sesPMTR.setSessionSavedContainer(CashOutPage.this.uiManager.stateMachine.findCntGlobalTransfer(this.val$cntCashOutForm));
                    CashOutPage.this.uiManager.btnBack.getListeners().clear();
                    CashOutPage.this.uiManager.btnBack.addActionListener(new 1());
                    CashOutPage cashOutPage2 = CashOutPage.this;
                    cashOutPage2.SwitchTransfertForms(this.val$cntCashOutForm, cashOutPage2.uiManager.stateMachine.findCntGlobalTransfer(this.val$cntCashOutForm), CashOutPage.this.GetTransferSecurityForm(this.val$cntCashOutForm), false);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CashOutPage.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcessTransfer.getStatusLabel()), null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CashOutPage.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                4.this.val$cntCashOutForm.replace((Container) 4.this.val$cntCashOutForm.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
                4.this.val$cntCashOutForm.revalidate();
                CashOutPage.this.uiManager.btnBack.getListeners().clear();
                CashOutPage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    CashOutPage.this.uiManager.GoBack();
                }
            }
        }
    }

    private Container getFavoriteAccounts(Container container, Dialog dialog, ArrayList arrayList) {
        ContactContainer contactContainer = new ContactContainer(BoxLayout.y());
        contactContainer.setScrollableY(true);
        contactContainer.setScrollVisible(false);
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                MultiButton contactListItem = contactListItem((FavoriteContactService) arrayList.get(i));
                contactListItem.setUIID("contactListItem");
                addBenAction(contactListItem, (FavoriteContactService) arrayList.get(i), container, dialog);
                contactContainer.add(contactListItem);
            }
        } else {
            contactContainer.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun favori"));
        }
        Settings.setNightMode(contactContainer, 0);
        return contactContainer;
    }

    ArrayList getFavoriteContactsProcess() {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        FavoriteContactService favoriteContactService = new FavoriteContactService();
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("SERVICE_ID", 900123);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(62);
        return favoriteContactService.FillFavoriteContactDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    private Container getCashOutHistoricCnt(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Container container = new Container(new BoxLayout(2));
        container.setScrollVisible(false);
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                ((CashOutOperatoion) arrayList.get(i)).groupKey = ((CashOutOperatoion) arrayList.get(i)).getExpirationDate();
                arrayList2.add((B3gService) arrayList.get(i));
            }
            if (arrayList2.size() > 0) {
                this.uiManager.Draw_GroupBy(container, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 20, null);
            } else {
                container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
            }
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
        }
        Settings.setNightMode(container, 0);
        return container;
    }

    ArrayList CashOutProcess() {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("Client_Id", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900138);
        return (ArrayList) serviceManager.Run(serviceRequest);
    }

    Hashtable getPlafondListfromResponse(ServiceResponse serviceResponse) {
        return serviceResponse.getParamsOut();
    }

    private ArrayList getCurrentCashOut(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            CashOutOperatoion cashOutOperatoion = (CashOutOperatoion) arrayList.get(i);
            if (cashOutOperatoion.getStatus().equals("TO_SERVE")) {
                arrayList2.add(cashOutOperatoion);
            }
        }
        return arrayList2;
    }

    ArrayList getCashOutOperations(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
            CashOutOperatoion cashOutOperatoion = new CashOutOperatoion();
            cashOutOperatoion.FillCashOutOperationFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i)));
            arrayList.add(cashOutOperatoion);
        }
        return arrayList;
    }

    public CashOutOperatoion GetTransaction(Container container, TextArea textArea, TextArea textArea2, TextArea textArea3) {
        CashOutOperatoion cashOutOperatoion = new CashOutOperatoion();
        cashOutOperatoion.setAmount(textArea3.getText());
        cashOutOperatoion.setBeneficiary(textArea.getText());
        cashOutOperatoion.setBeneficiaryGsm(StringTools.formatPhoneNumber(textArea2.getText()));
        Container container2 = (Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0);
        cashOutOperatoion.setAccountNumber(((Label) ((Container) ((Container) ((Container) container2.getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getText());
        cashOutOperatoion.setAccountNumber(((Account) ((B3gService) container2.getClientProperty("AccountClient"))).rib);
        return cashOutOperatoion;
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }

    public Container GetTransferSecurityForm(Container container) {
        Container container2 = new Container(BoxLayout.y());
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container3 = (Container) this.uib.findByName("Container17", createContainer);
        container3.removeAll();
        container3.setLayout(BoxLayout.y());
        container3.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 5(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 6(b3GCIHEcode));
        container2.add(createContainer);
        Settings.setNightMode(container2, 0);
        return container2;
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
        CashOutOperatoion op = CihMBanking.sesPMTR.getCashOutOperatoion();
        final /* synthetic */ B3GCIHEcode val$eCode1;

        6(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CashOutPage.access$102(CashOutPage.this, this.val$eCode1.getValue());
            this.op.setPass(CashOutPage.access$100(CashOutPage.this));
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(72, this.op, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 300017);
        }
    }

    private Boolean isAmountValid(String str) {
        try {
            if (Integer.parseInt(str) % 100 != 0) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    ArrayList getCanDebitAccount(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Account account = (Account) it.next();
            if (account.canDebit.equals("True") && account.eligibleService.contains("300016")) {
                arrayList2.add(account);
            }
        }
        return arrayList2;
    }

    class 7 implements ActionListener {
        final /* synthetic */ FavoriteContactService val$c;
        final /* synthetic */ Container val$cnt;
        final /* synthetic */ Dialog val$d;

        7(Container container, FavoriteContactService favoriteContactService, Dialog dialog) {
            this.val$cnt = container;
            this.val$c = favoriteContactService;
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CashOutPage.this.uiManager.stateMachine.findTxtBeneGSM(this.val$cnt).setText(StringTools.formatPhoneNumber(this.val$c.MobilePhone));
            CashOutPage.this.uiManager.stateMachine.findTxtBenefName(this.val$cnt).setText(this.val$c.Name);
            this.val$d.dispose();
        }
    }

    private void addBenAction(MultiButton multiButton, FavoriteContactService favoriteContactService, Container container, Dialog dialog) {
        multiButton.addActionListener(new 7(container, favoriteContactService, dialog));
    }

    private MultiButton contactListItem(FavoriteContactService favoriteContactService) {
        MultiButton multiButton = new MultiButton();
        multiButton.setLayout(new BorderLayout());
        Button button = new Button(favoriteContactService.Name);
        button.setUIID("ac_lblitemDetail");
        Button button2 = new Button(favoriteContactService.MobilePhone);
        button2.setUIID("g_lblBalanceValueFLFT");
        Container container = new Container(BoxLayout.y());
        container.setUIID("southborder_gris");
        multiButton.add("Center", button);
        multiButton.add("East", button2);
        multiButton.add("South", container);
        return multiButton;
    }

    private ArrayList searchBenefs(String str, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (str.length() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                String displayName = ((Contact) arrayList.get(i)).getDisplayName();
                String[] split = new RE("\\s+").split(displayName);
                if (displayName.toLowerCase().startsWith(str.toLowerCase())) {
                    arrayList2.add((Contact) arrayList.get(i));
                } else {
                    for (String str2 : split) {
                        if (str2.toLowerCase().startsWith(str.toLowerCase()) || ((Contact) arrayList.get(i)).getPhoneNumbers().toString().startsWith(str.toLowerCase())) {
                            arrayList2.add((Contact) arrayList.get(i));
                            break;
                        }
                    }
                }
            }
            return arrayList2;
        }
        arrayList2.addAll(arrayList);
        return arrayList2;
    }

    public class ContactContainer extends Container {
        public ContactContainer(Layout layout) {
            super(layout);
        }

        protected void setScrollY(int i) {
            super.setScrollY(i);
        }
    }

    private boolean sendBackPin(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("expressReference", str);
        hashtable.put("SESSION_ID", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(142);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        ServiceResponse serviceResponse = (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
        if (serviceResponse.getStatusCode().equals("000")) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, serviceResponse.getStatusLabel(), null);
            return true;
        }
        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, serviceResponse.getStatusLabel(), null);
        return false;
    }

    public void ShowConfirmation(String str) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "disableConfirmation");
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.setAlwaysTensile(false);
        dialog.setDraggable(false);
        dialog.setScrollable(false);
        dialog.setTactileTouch(false);
        dialog.setTensileDragEnabled(false);
        dialog.getDialogStyle().setBgTransparency(230);
        dialog.getDialogStyle().setBgColor(1118481);
        dialog.getStyle().setPadding(0, 0, 0, 0);
        dialog.getStyle().setMargin(0, 0, 0, 0);
        dialog.getDialogStyle().setPadding(0, 0, 0, 0);
        dialog.getDialogStyle().setMargin(0, 0, 0, 0);
        dialog.revalidate();
        ((SpanLabel) uIBuilder.findByName("SpanLabel", createContainer)).setText("Etes-vous sur de vouloir restituer le montant ?");
        ((Button) uIBuilder.findByName("confirmationBtn", createContainer)).addActionListener(new 8(str, dialog));
        ((Button) uIBuilder.findByName("cancelBtn", createContainer)).addActionListener(new 9(dialog));
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", true);
    }

    class 8 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ String val$reference;

        8(String str, Dialog dialog) {
            this.val$reference = str;
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CashOutPage.access$200(CashOutPage.this, this.val$reference, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID)) {
                CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(52);
            }
            this.val$d.dispose();
        }
    }

    class 9 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        9(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    private boolean resetCashOutSrv(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("expressReference", str);
        hashtable.put("SESSION_ID", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(144);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        ServiceResponse serviceResponse = (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
        if (serviceResponse.getStatusCode().equals("000")) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, serviceResponse.getStatusLabel(), null);
            return true;
        }
        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, serviceResponse.getStatusLabel(), null);
        return false;
    }
}
