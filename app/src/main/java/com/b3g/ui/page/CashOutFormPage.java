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
import com.codename1.contacts.Contact;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
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
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.regex.RE;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CashOutFormPage extends B3GPage {
    private String VnewaliasText;
    private TextField txtHBPassWord;
    private final UIBuilder uib = new UIBuilder();

    static /* synthetic */ String access$000(CashOutFormPage cashOutFormPage) {
        return cashOutFormPage.VnewaliasText;
    }

    static /* synthetic */ String access$002(CashOutFormPage cashOutFormPage, String str) {
        cashOutFormPage.VnewaliasText = str;
        return str;
    }

    public CashOutFormPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = getCashOutCnt(((ServiceResponse) CashOutProcess().get(1)).getParamsOut());
        return this.thisContainer;
    }

    private Container getCashOutCnt(Hashtable hashtable) {
        this.thisContainer = new Container(new BorderLayout());
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransferFormTab");
        TableLayout tableLayout = new TableLayout(1, 2);
        Container container = new Container(new GridLayout(1, 1));
        container.setUIID("whiteCnt");
        Container container2 = new Container(tableLayout);
        container.add(container2);
        container2.setUIID("padding_1_1_1_1");
        Label label = new Label("Service sélectionné : ");
        label.setUIID("ad_lblValue");
        Label label2 = new Label("Transfert CIH EXPRESS");
        label2.setUIID("ad_lblValueBlue");
        container2.add(label);
        container2.add(label2);
        this.thisContainer.addComponent("North", container);
        TextField textField = (TextField) this.uib.findByName("txtBenefName", createContainer);
        createContainer.setScrollVisible(false);
        this.uiManager.stateMachine.findAddFromRepBtn(createContainer).addActionListener(new CashOutFormPage$$ExternalSyntheticLambda2(this, createContainer));
        textField.addDataChangedListener(new CashOutFormPage$$ExternalSyntheticLambda3(textField, createContainer));
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
            this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 2(createContainer, DrawListContainerTransfer));
        } else {
            ((Container) createContainer.getComponentAt(0)).removeComponent(((Container) createContainer.getComponentAt(0)).getComponentAt(6));
            createContainer.revalidate();
        }
        createContainer.setScrollVisible(false);
        this.thisContainer.addComponent("Center", createContainer);
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    /* synthetic */ void lambda$getCashOutCnt$6$com-b3g-ui-page-CashOutFormPage(Container container, ActionEvent actionEvent) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        Container container2 = new Container(new BorderLayout());
        Settings.setNightMode(container2, 0);
        ContactContainer contactContainer = new ContactContainer(BoxLayout.y());
        contactContainer.setScrollableY(true);
        contactContainer.setScrollVisible(false);
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "DialogTemplate");
        createContainer.setScrollVisible(false);
        createContainer.setScrollableY(false);
        Container container3 = (Container) uIBuilder.findByName("centerCnt", createContainer);
        container3.setScrollVisible(false);
        container3.setScrollableY(false);
        ((Label) uIBuilder.findByName("lblGlobalHeaderTitle", createContainer)).setText("Bénéficiaire Transfert ");
        Display.getInstance().scheduleBackgroundTask(new CashOutFormPage$$ExternalSyntheticLambda4(this, uIBuilder, createContainer, dialog, container2, contactContainer, container, container3));
        createContainer.setPreferredH((Display.getInstance().getDisplayHeight() * 95) / 100);
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.addComponent("Center", createContainer);
        dialog.show(0, 0, 0, 0);
        Settings.setNightMode(createContainer, 0);
        Settings.setNightMode(dialog, 0);
    }

    /* synthetic */ void lambda$getCashOutCnt$5$com-b3g-ui-page-CashOutFormPage(UIBuilder uIBuilder, Container container, Dialog dialog, Container container2, ContactContainer contactContainer, Container container3, Container container4) {
        Display.getInstance().callSerially(new CashOutFormPage$$ExternalSyntheticLambda1(this, Display.getInstance().getAllContacts(true, true, false, true, false, false), uIBuilder, container, dialog, container2, contactContainer, container3, container4));
    }

    /* synthetic */ void lambda$getCashOutCnt$4$com-b3g-ui-page-CashOutFormPage(Contact[] contactArr, UIBuilder uIBuilder, Container container, Dialog dialog, Container container2, ContactContainer contactContainer, Container container3, Container container4) {
        String str;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(contactArr));
        ((Button) uIBuilder.findByName("fermerBtn", container)).addActionListener(new CashOutFormPage$$ExternalSyntheticLambda5(dialog));
        Container container5 = new Container(BoxLayout.y());
        container5.setUIID("padding_0_1_1_1");
        TextField textField = new TextField("", "Rechercher");
        textField.setHintIcon(this.uiManager.ressource.getImage("search_hint.png"));
        textField.setUIID("textFieldTemplate");
        container5.add(textField);
        container2.add("North", container5);
        String str2 = "";
        textField.addDataChangedListener(new CashOutFormPage$$ExternalSyntheticLambda6(this, textField, arrayList, contactContainer, container3, dialog, container2, container4));
        Container container6 = new Container(BoxLayout.y());
        container6.setScrollVisible(false);
        container6.setScrollableY(true);
        Collections.sort(arrayList, new 1());
        int i = 0;
        char c = 0;
        while (i < arrayList.size()) {
            Iterator it = ((Contact) arrayList.get(i)).getPhoneNumbers().keySet().iterator();
            while (it.hasNext()) {
                String obj = ((Contact) arrayList.get(i)).getPhoneNumbers().get(it.next()).toString();
                if (StringTools.formatPhoneNumber(obj).startsWith("06")) {
                    MultiButton contactListItem = contactListItem(new FavoriteContactService(obj, ((Contact) arrayList.get(i)).getDisplayName()));
                    contactListItem.setUIID("contactListItem");
                    addBenAction(contactListItem, new FavoriteContactService(obj, ((Contact) arrayList.get(i)).getDisplayName()), container3, dialog);
                    contactContainer.add(contactListItem);
                }
            }
            char upperCase = Character.toUpperCase(((Contact) arrayList.get(i)).getDisplayName().charAt(0));
            if (upperCase != c) {
                str = str2;
                Button button = new Button(str + upperCase);
                button.setUIID("gb_lblGlobalHeaderTitleV2");
                container6.add(button);
                button.getAllStyles().setPadding(0, 0, 0, 0);
                button.getAllStyles().setMargin(0, 0, 0, 1);
                button.addActionListener(new CashOutFormPage$$ExternalSyntheticLambda7(contactContainer, upperCase));
                c = upperCase;
            } else {
                str = str2;
            }
            i++;
            str2 = str;
        }
        container2.add("Center", contactContainer);
        container2.add("East", container6);
        Container container7 = new Container(new BoxLayout(2));
        ArrayList arrayList2 = new ArrayList();
        new ArrayList();
        ArrayList favoriteContactsProcess = getFavoriteContactsProcess();
        if (favoriteContactsProcess.size() > 0) {
            arrayList2.add(new B3gContainer(getFavoriteAccounts(container3, dialog, favoriteContactsProcess), "Mes favoris"));
            arrayList2.add(new B3gContainer(container2, "Mes Contacts"));
        } else {
            arrayList2.add(new B3gContainer(container2, "Mes Contacts"));
            arrayList2.add(new B3gContainer(getFavoriteAccounts(container3, dialog, favoriteContactsProcess), "Mes favoris"));
        }
        this.uiManager.DrawTabsWithNavigation(container7, arrayList2);
        container4.add("Center", container7);
        container4.revalidate();
        Settings.setNightMode(container2, 0);
        Settings.setNightMode(container4, 0);
    }

    static /* synthetic */ void lambda$getCashOutCnt$0(Dialog dialog, ActionEvent actionEvent) {
        dialog.dispose();
    }

    /* synthetic */ void lambda$getCashOutCnt$2$com-b3g-ui-page-CashOutFormPage(TextField textField, ArrayList arrayList, ContactContainer contactContainer, Container container, Dialog dialog, Container container2, Container container3, int i, int i2) {
        ArrayList searchBenefs = searchBenefs(textField.getText(), arrayList);
        Collections.sort(searchBenefs, new CashOutFormPage$$ExternalSyntheticLambda0());
        contactContainer.removeAll();
        Iterator it = searchBenefs.iterator();
        while (it.hasNext()) {
            Contact contact = (Contact) it.next();
            Iterator it2 = contact.getPhoneNumbers().keySet().iterator();
            while (it2.hasNext()) {
                String obj = contact.getPhoneNumbers().get(it2.next()).toString();
                if (StringTools.formatPhoneNumber(obj).startsWith("06")) {
                    MultiButton contactListItem = contactListItem(new FavoriteContactService(obj, contact.getDisplayName()));
                    contactListItem.setUIID("contactListItem");
                    addBenAction(contactListItem, new FavoriteContactService(obj, contact.getDisplayName()), container, dialog);
                    contactContainer.add(contactListItem);
                }
            }
        }
        container2.revalidate();
        container3.revalidate();
    }

    static /* synthetic */ int lambda$getCashOutCnt$1(Contact contact, Contact contact2) {
        return contact.getDisplayName().compareTo(contact2.getDisplayName());
    }

    class 1 implements Comparator {
        1() {
        }

        public int compare(Contact contact, Contact contact2) {
            return contact.getDisplayName().compareTo(contact2.getDisplayName());
        }
    }

    static /* synthetic */ void lambda$getCashOutCnt$3(ContactContainer contactContainer, char c, ActionEvent actionEvent) {
        Iterator it = contactContainer.iterator();
        while (it.hasNext()) {
            MultiButton multiButton = (MultiButton) ((Component) it.next());
            if (Character.toUpperCase(((Button) multiButton.getComponentAt(3)).getText().charAt(0)) == c) {
                contactContainer.setScrollY(multiButton.getY());
                return;
            }
        }
    }

    static /* synthetic */ void lambda$getCashOutCnt$7(TextField textField, Container container, int i, int i2) {
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

    class 2 implements ActionListener {
        final /* synthetic */ Container val$cntCashOutForm;
        final /* synthetic */ Container val$issuarAccount;

        2(Container container, Container container2) {
            this.val$cntCashOutForm = container;
            this.val$issuarAccount = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (CashOutFormPage.this.uiManager.stateMachine.findTxtCashOutAmount(this.val$cntCashOutForm).getText().length() == 0 || CashOutFormPage.this.uiManager.stateMachine.findTxtBeneGSM(this.val$cntCashOutForm).getText().length() == 0 || CashOutFormPage.this.uiManager.stateMachine.findTxtBenefName(this.val$cntCashOutForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CashOutFormPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                } else if (!StringTools.phoneNumberChecker(CashOutFormPage.this.uiManager.stateMachine.findTxtBeneGSM(this.val$cntCashOutForm).getText())) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CashOutFormPage.this.uiManager.stateMachine, "Merci d'entrer un numéro GSM valide", null);
                }
                CashOutFormPage cashOutFormPage = CashOutFormPage.this;
                CashOutOperatoion GetTransaction = cashOutFormPage.GetTransaction(this.val$issuarAccount, cashOutFormPage.uiManager.stateMachine.findTxtBenefName(this.val$cntCashOutForm), CashOutFormPage.this.uiManager.stateMachine.findTxtBeneGSM(this.val$cntCashOutForm), CashOutFormPage.this.uiManager.stateMachine.findTxtCashOutAmount(this.val$cntCashOutForm));
                ServiceResponse sendOtpProcessTransfer = ServiceManager.sendOtpProcessTransfer(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1002", 300017, "1", "0", GetTransaction.getAccountNumber(), GetTransaction.getBeneficiaryGsm(), GetTransaction.getAmount(), "", GetTransaction.getBeneficiary(), CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, "");
                if (sendOtpProcessTransfer.getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.setCashOutOperatoion(GetTransaction);
                    CihMBanking.sesPMTR.setSessionSavedContainer(CashOutFormPage.this.uiManager.stateMachine.findCntGlobalTransfer(this.val$cntCashOutForm));
                    CashOutFormPage.this.uiManager.btnBack.getListeners().clear();
                    CashOutFormPage.this.uiManager.btnBack.addActionListener(new 1());
                    CashOutFormPage cashOutFormPage2 = CashOutFormPage.this;
                    cashOutFormPage2.SwitchTransfertForms(this.val$cntCashOutForm, cashOutFormPage2.uiManager.stateMachine.findCntGlobalTransfer(this.val$cntCashOutForm), CashOutFormPage.this.GetTransferSecurityForm(this.val$cntCashOutForm), false);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CashOutFormPage.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcessTransfer.getStatusLabel()), null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CashOutFormPage.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                2.this.val$cntCashOutForm.replace((Container) 2.this.val$cntCashOutForm.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
                2.this.val$cntCashOutForm.revalidate();
                CashOutFormPage.this.uiManager.btnBack.getListeners().clear();
                CashOutFormPage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    CashOutFormPage.this.uiManager.GoBack();
                }
            }
        }
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
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
            Settings.setNightMode(contactContainer, 0);
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

    class 3 implements ActionListener {
        final /* synthetic */ FavoriteContactService val$c;
        final /* synthetic */ Container val$cnt;
        final /* synthetic */ Dialog val$d;

        3(Container container, FavoriteContactService favoriteContactService, Dialog dialog) {
            this.val$cnt = container;
            this.val$c = favoriteContactService;
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CashOutFormPage.this.uiManager.stateMachine.findTxtBeneGSM(this.val$cnt).setText(StringTools.formatPhoneNumber(this.val$c.MobilePhone));
            CashOutFormPage.this.uiManager.stateMachine.findTxtBenefName(this.val$cnt).setText(this.val$c.Name);
            this.val$d.dispose();
        }
    }

    private void addBenAction(MultiButton multiButton, FavoriteContactService favoriteContactService, Container container, Dialog dialog) {
        multiButton.addActionListener(new 3(container, favoriteContactService, dialog));
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
        Settings.setNightMode(multiButton, 0);
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

    public Container GetTransferSecurityForm(Container container) {
        Container container2 = new Container(BoxLayout.y());
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container3 = (Container) this.uib.findByName("Container17", createContainer);
        container3.removeAll();
        container3.setLayout(BoxLayout.y());
        container3.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 4(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 5(b3GCIHEcode));
        container2.add(createContainer);
        return container2;
    }

    class 4 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        4(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            this.val$Parent.revalidate();
        }
    }

    class 5 implements ActionListener {
        CashOutOperatoion op = CihMBanking.sesPMTR.getCashOutOperatoion();
        final /* synthetic */ B3GCIHEcode val$eCode1;

        5(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CashOutFormPage.access$002(CashOutFormPage.this, this.val$eCode1.getValue());
            this.op.setPass(CashOutFormPage.access$000(CashOutFormPage.this));
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(72, this.op, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 300017);
        }
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

    private class ContactContainer extends Container {
        public ContactContainer(Layout layout) {
            super(layout);
        }

        protected void setScrollY(int i) {
            super.setScrollY(i);
        }
    }
}
