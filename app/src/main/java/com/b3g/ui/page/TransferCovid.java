package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.ServiceManager;
import com.b3g.services.TransactionResume;
import com.b3g.tools.DataTools;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.ui.Container;
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
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TransferCovid extends B3GPage {
    public static Container cntTransfertForm;
    public static boolean isOtpPageShow;
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();
    private boolean isOvp = false;

    static /* synthetic */ String access$000(TransferCovid transferCovid) {
        return transferCovid.VnewaliasText;
    }

    static /* synthetic */ String access$002(TransferCovid transferCovid, String str) {
        transferCovid.VnewaliasText = str;
        return str;
    }

    public TransferCovid(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        isOtpPageShow = false;
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setUIID("mn_cntMenuItemV2");
        this.thisContainer.setName("TransfertNewPage");
        try {
            CihMBanking.sesPMTR.setToken("");
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(GetContainerTransferForm(), "Fond de solidarité séisme"));
            this.uiManager.DrawTabsWithNavigationV3(container, arrayList, new Container(), this.uiManager.stateMachine.findTxtAmount(cntTransfertForm), this.uiManager.stateMachine.findTxtMotif(cntTransfertForm), this.txtHBPassWord);
            this.thisContainer.add(container);
        } catch (Exception unused) {
            this.thisContainer.setLayout(new BorderLayout());
            this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    public Container GetContainerTransferForm() {
        boolean z;
        cntTransfertForm = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertFormTabGlobalLayered");
        if (Display.getInstance().getPlatformName().equals("ios")) {
            cntTransfertForm.setScrollableY(false);
        }
        new Account().uiManager = this.uiManager;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountListVirm());
        arrayList.addAll(CihMBanking.sesPMTR.getSessionClient().getListRelationAccount());
        Beneficiary beneficiary = new Beneficiary();
        ArrayList arrayList2 = new ArrayList();
        beneficiary.alias = "Fond de solidarité séisme";
        beneficiary.typeBenef = "";
        beneficiary.cardNumber = "001810007800020110620318";
        arrayList2.add(beneficiary);
        Container DrawListContainer = this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à créditer", Boolean.TRUE, arrayList2, 1, 17, "Aucun compte n'est disponible pour cette opération.", null, null, null, null);
        Container DrawListContainer2 = this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getCanDebitAccount(arrayList), 1, 16, "", null, null, null, null);
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(DrawListContainer2);
        this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).setMaxSize(20);
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(DrawListContainer);
        this.uiManager.stateMachine.findLblDayValue(cntTransfertForm).setHidden(true);
        this.uiManager.stateMachine.findLblWeekValue(cntTransfertForm).setHidden(true);
        this.uiManager.stateMachine.findLblDay(cntTransfertForm).setHidden(true);
        this.uiManager.stateMachine.findLblWeek(cntTransfertForm).setHidden(true);
        this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).setUIID("tr_txtAmount_gris");
        this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).setText("Fond de solidarité séisme");
        this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).setFocusable(false);
        this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).setEnabled(false);
        this.uiManager.ControlTextfield(this.uiManager.stateMachine.findTxtAmount(cntTransfertForm), "AMOUNT", cntTransfertForm);
        if (arrayList.size() > 0) {
            this.uiManager.stateMachine.findBtnTransferValidation(cntTransfertForm).addActionListener(new 1(DrawListContainer2, DrawListContainer));
            z = false;
        } else {
            z = false;
            ((Container) cntTransfertForm.getComponentAt(0)).removeComponent(((Container) cntTransfertForm.getComponentAt(0)).getComponentAt(6));
            cntTransfertForm.revalidate();
        }
        cntTransfertForm.setScrollVisible(z);
        return cntTransfertForm;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$acquiredAccount;
        final /* synthetic */ Container val$issuarAccount;

        1(Container container, Container container2) {
            this.val$issuarAccount = container;
            this.val$acquiredAccount = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (TransferCovid.this.uiManager.stateMachine.findTxtAmount(TransferCovid.cntTransfertForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferCovid.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                    return;
                }
                TransferCovid transferCovid = TransferCovid.this;
                TransactionResume GetTransaction = transferCovid.GetTransaction(this.val$issuarAccount, this.val$acquiredAccount, transferCovid.uiManager.stateMachine.findTxtAmount(TransferCovid.cntTransfertForm), TransferCovid.this.uiManager.stateMachine.findTxtMotif(TransferCovid.cntTransfertForm));
                ServiceResponse sendOtpProcessTransfer = ServiceManager.sendOtpProcessTransfer(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1017", 300015, "1", "0", GetTransaction.IssuerAccountNumber, GetTransaction.CreditedAccountNumber, GetTransaction.Amount, GetTransaction.isTutor, GetTransaction.CreditedAccountAlias, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, TransferCovid.this.uiManager.stateMachine.findTxtMotif(TransferCovid.cntTransfertForm).getText());
                if (sendOtpProcessTransfer.getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.setSessionCurrentTransactionResume(GetTransaction);
                    CihMBanking.sesPMTR.setSessionSavedContainer(TransferCovid.this.uiManager.stateMachine.findCntGlobalTransfer(TransferCovid.cntTransfertForm));
                    TransferCovid.this.SwitchTransfertForms(TransferCovid.cntTransfertForm, TransferCovid.this.uiManager.stateMachine.findCntGlobalTransfer(TransferCovid.cntTransfertForm), TransferCovid.this.GetTransferSecurityForm(TransferCovid.cntTransfertForm), false);
                    TransferCovid.this.uiManager.btnBack.getListeners().clear();
                    TransferCovid.this.uiManager.btnBack.addActionListener(new 1());
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferCovid.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcessTransfer.getStatusLabel()), null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferCovid.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                TransferCovid.cntTransfertForm.replace((Container) TransferCovid.cntTransfertForm.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 0));
                TransferCovid.cntTransfertForm.revalidate();
                TransferCovid.this.uiManager.btnBack.getListeners().clear();
                TransferCovid.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    TransferCovid.this.uiManager.GoBack();
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
        Container container4 = (Container) ((Container) ((Container) ((Container) ((Container) ((Tabs) container2.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
        transactionResume.CreditedAccountAlias = ((Label) container4.getComponentAt(0)).getText();
        transactionResume.CreditedAccountNumber = StringTools.RibFormatWithoutSP(((Label) container4.getComponentAt(1)).getText());
        transactionResume.field1 = "";
        transactionResume.field2 = "";
        transactionResume.TypeOperation = "TRANSFER";
        return transactionResume;
    }

    public Container GetTransferSecurityForm(Container container) {
        isOtpPageShow = true;
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
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 0));
            TransferCovid.this.uiManager.btnBack.getListeners().clear();
            TransferCovid.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                TransferCovid.isOtpPageShow = false;
                TransferCovid.this.uiManager.GoBack();
            }
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        3(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
            TransferCovid.access$002(TransferCovid.this, this.val$eCode1.getValue());
            sessionCurrentTransactionResume.PassHB = TransferCovid.access$000(TransferCovid.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (TransferCovid.access$000(TransferCovid.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TransferCovid.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(90, sessionCurrentTransactionResume, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 300013);
            }
        }
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 0));
        container.revalidate();
        if (Display.getInstance().getPlatformName().equals("ios")) {
            this.txtHBPassWord.startEditingAsync();
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
            if (account.canDebit.equals("True") && account.eligibleService.contains("300013") && !account.rib.substring(13, 17).equals("2310")) {
                arrayList2.add(account);
            }
        }
        return arrayList2;
    }
}
