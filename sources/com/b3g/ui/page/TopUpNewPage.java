package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.Card;
import com.b3g.services.Plafond;
import com.b3g.services.ServiceManager;
import com.b3g.services.TransactionResume;
import com.b3g.services.TransferHistoric;
import com.b3g.services.TransfertDATA;
import com.b3g.tools.DataTools;
import com.b3g.tools.SortBenifByAlias;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GCheckBox;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordion;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.animations.Transition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TopUpNewPage extends B3GPage {
    public static B3gAccordion A;
    public static Container BENEFCNT;
    public static Container Glob;
    public static boolean isOtpPageShow;
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    private TextField benifPhoneNumber;
    Container cntBtns;
    Container cntTransfertForm;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();
    private boolean isNotif = false;

    static /* synthetic */ boolean access$000(TopUpNewPage topUpNewPage) {
        return topUpNewPage.isNotif;
    }

    static /* synthetic */ boolean access$002(TopUpNewPage topUpNewPage, boolean z) {
        topUpNewPage.isNotif = z;
        return z;
    }

    static /* synthetic */ Container access$100(TopUpNewPage topUpNewPage, String str, String str2) {
        return topUpNewPage.DrawAcquiredAcc(str, str2);
    }

    static /* synthetic */ TextField access$200(TopUpNewPage topUpNewPage) {
        return topUpNewPage.benifPhoneNumber;
    }

    static /* synthetic */ String access$300(TopUpNewPage topUpNewPage) {
        return topUpNewPage.VnewaliasText;
    }

    static /* synthetic */ String access$302(TopUpNewPage topUpNewPage, String str) {
        topUpNewPage.VnewaliasText = str;
        return str;
    }

    public TopUpNewPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        isOtpPageShow = false;
        TransferNewPage.isOtpPageShow = false;
        TransfertWallet.isOtpPageShow = false;
        this.thisContainer = new Container(new BorderLayout());
        this.thisContainer.setUIID("mn_cntMenuItemV2");
        this.thisContainer.setName("TopUpNewPage");
        try {
            TransfertDATA TransfertDATAProcess = new TransfertDATA().TransfertDATAProcess(31);
            TransfertDATAProcess.getBeneficiaryList().addAll(TransfertDATAProcess.geteShoppingBeneficiary());
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(GetContainerTopUpForm(TransfertDATAProcess.getBeneficiaryList(), TransfertDATAProcess.getbeneficiaryStatusLabel(), TransfertDATAProcess.getPlafond()), "RECHARGE"));
            arrayList.add(new B3gContainer(GetContainerTransferHistoric(TransfertDATAProcess.getTransferHistoricList(), TransfertDATAProcess), "HISTORIQUE"));
            this.uiManager.DrawTabsWithNavigationV3(container, arrayList, this.cntBtns, this.uiManager.stateMachine.findTxtAmount(this.cntTransfertForm), this.uiManager.stateMachine.findTxtMotif(this.cntTransfertForm), this.txtHBPassWord);
            this.thisContainer.add("Center", container);
        } catch (Exception unused) {
            this.thisContainer.setLayout(new BorderLayout());
            Settings.setNightMode(this.thisContainer, 0);
            this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
        }
        Settings.setNightMode(this.thisContainer, 0);
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
        Settings.setNightMode(container, 0);
        return container;
    }

    public Container GetContainerTopUpForm(ArrayList arrayList, String str, ArrayList arrayList2) {
        Button button;
        this.cntTransfertForm = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransferFormTabGlobal");
        Container findNotificatCnt = this.uiManager.stateMachine.findNotificatCnt(this.cntTransfertForm);
        this.cntBtns = new Container(new BoxLayout(2));
        Button button2 = new Button(" Ajouter un Bénéficiaire");
        button2.setIcon(this.uiManager.ressource.getImage("addBeneficery.png"));
        button2.setUIID("op_BtnOppositionValidationV2");
        this.cntBtns.setUIID("WhitCnt_PaddingLeftRigt_2");
        Container container = new Container();
        Container container2 = new Container();
        Container container3 = new Container();
        Container container4 = new Container(new TableLayout(1, 2));
        if (Display.getInstance().getPlatformName().equals("ios")) {
            this.cntTransfertForm.setScrollableY(true);
        }
        if (!arrayList.isEmpty()) {
            new Account().uiManager = this.uiManager;
            ArrayList client_AccountList = CihMBanking.sesPMTR.getSessionClient().getClient_AccountList();
            ArrayList firstAccountBeneficiary = getFirstAccountBeneficiary(client_AccountList, arrayList);
            TextField textField = new TextField();
            this.benifPhoneNumber = textField;
            textField.setMaxSize(16);
            this.benifPhoneNumber.setUIID("tr_txtAmount");
            this.benifPhoneNumber.setConstraint(2);
            Container container5 = new Container();
            Container container6 = new Container(new BoxLayout(2));
            container6.setUIID("g_cntBorderV2");
            container5.setUIID("EmptyContainer");
            TableLayout.Constraint constraint = new TableLayout.Constraint();
            constraint.setWidthPercentage(50);
            Label label = new Label("N° GSM:");
            label.setUIID("g_lblNotif");
            if (firstAccountBeneficiary.isEmpty()) {
                button = button2;
            } else {
                button = button2;
                this.benifPhoneNumber.setText(((Beneficiary) firstAccountBeneficiary.get(0)).phoneNumber);
            }
            container2.addComponent(label);
            container3.addComponent(this.benifPhoneNumber);
            container4.addComponent(constraint, container2);
            container4.addComponent(constraint, container3);
            container4.addComponent(container5);
            B3GCheckBox b3GCheckBox = new B3GCheckBox("Notification");
            b3GCheckBox.setUIID("g_lblNotif");
            b3GCheckBox.addItem("Notifier le bénéficiaire");
            b3GCheckBox.getItem("Notifier le bénéficiaire").addActionListener(new 1(findNotificatCnt, container, container4, container6));
            Container DrawListContainer = this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getCanDebitAccount(client_AccountList), 1, 16, "Aucun compte n'est disponible pour cette opération . ", null, null, null, null);
            if (firstAccountBeneficiary.isEmpty()) {
                this.uiManager.stateMachine.findBtnTransferValidation(this.cntTransfertForm).setEnabled(false);
            }
            this.uiManager.stateMachine.findCntAccountChosen(this.cntTransfertForm).addComponent(DrawListContainer);
            this.uiManager.stateMachine.findCntAccountChosen(this.cntTransfertForm).addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
            BENEFCNT = new Container(new BoxLayout(2));
            this.uiManager.stateMachine.findCntAccountChosen(this.cntTransfertForm).addComponent(BENEFCNT);
            B3gAccordion b3gAccordion = new B3gAccordion();
            A = b3gAccordion;
            b3gAccordion.setCloseIcon((Image) null);
            A.setOpenIcon((Image) null);
            Button button3 = new Button(" Choisissez votre bénéficiaire ");
            button3.setUIID("op_BtnOppositionValidationV2Blue");
            Button button4 = new Button(" Choisissez votre bénéficiaire ");
            button4.setUIID("op_BtnOppositionValidationV2");
            A.setOpenBtn(button4);
            A.setCloseBtn(button3);
            int i = 2;
            Glob = new Container(new BoxLayout(2));
            int i2 = 0;
            while (i2 < firstAccountBeneficiary.size()) {
                TableLayout tableLayout = new TableLayout(1, i);
                Button button5 = new Button();
                button5.setUIID("Container");
                Container container7 = new Container();
                container7.setLeadComponent(button5);
                container7.setLayout(tableLayout);
                container7.removeAll();
                container7.setUIID("Container");
                TableLayout.Constraint createConstraint = tableLayout.createConstraint();
                TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
                createConstraint.setWidthPercentage(80);
                createConstraint2.setWidthPercentage(1);
                container7.addComponent(createConstraint2, button5);
                container7.addComponent(createConstraint, ((Beneficiary) firstAccountBeneficiary.get(i2)).DrawItem(this.uiManager.stateMachine, this.uiManager.ressource, 17, this.service, this.thisContainer, this.thisContainer));
                button5.addActionListener(new 2(container7, findNotificatCnt, container4));
                Glob.add(container7);
                i2++;
                i = 2;
            }
            A.addContent(new Container(), Glob);
            A.setScrollVisible(false);
            this.uiManager.stateMachine.findCntAccountChosen(this.cntTransfertForm).addComponent(A);
            A.addOnClickItemListener(new 3());
            this.uiManager.stateMachine.findLblDayValue(this.cntTransfertForm).setText(((Plafond) arrayList2.get(0)).BinatnaDaily);
            this.uiManager.stateMachine.findLblWeekValue(this.cntTransfertForm).setText(((Plafond) arrayList2.get(0)).BinatnaMax);
            this.uiManager.ControlTextfield(this.uiManager.stateMachine.findTxtAmount(this.cntTransfertForm), "AMOUNT", this.cntTransfertForm);
            if (client_AccountList.size() > 0 && arrayList.size() > 0) {
                this.uiManager.stateMachine.findBtnTransferValidation(this.cntTransfertForm).addActionListener(new 4(DrawListContainer, arrayList));
            } else {
                ((Container) this.cntTransfertForm.getComponentAt(0)).removeComponent(((Container) this.cntTransfertForm.getComponentAt(0)).getComponentAt(6));
                this.cntTransfertForm.revalidate();
            }
        } else {
            button = button2;
            Container container8 = new Container(new FlowLayout(4, 4));
            SpanLabel spanLabel = new SpanLabel("Vous n’avez aucune carte prépayée.");
            spanLabel.setUIID("ad_lblTitle");
            spanLabel.setScrollVisible(false);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("ad_lblTitle");
            spanLabel.setIconUIID("g_cntEmpty");
            container8.add(spanLabel);
            container8.getStyle().setMargin(3, 3, 3, 3);
            this.cntTransfertForm.removeAll();
            this.cntTransfertForm.addComponent("North", container8);
            this.cntTransfertForm.revalidate();
        }
        Button button6 = button;
        button6.addActionListener(new 5());
        this.cntTransfertForm.setScrollVisible(false);
        this.cntBtns.add(button6);
        this.cntTransfertForm.addComponent("South", this.cntBtns);
        Settings.setNightMode(this.cntTransfertForm, 0);
        return this.cntTransfertForm;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$borderCnt;
        final /* synthetic */ Container val$emptyNotifCnt;
        final /* synthetic */ Container val$notifContainer;
        final /* synthetic */ Container val$phoneNumberCnt;

        1(Container container, Container container2, Container container3, Container container4) {
            this.val$notifContainer = container;
            this.val$emptyNotifCnt = container2;
            this.val$phoneNumberCnt = container3;
            this.val$borderCnt = container4;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!TopUpNewPage.access$000(TopUpNewPage.this)) {
                this.val$notifContainer.replace(this.val$emptyNotifCnt, this.val$phoneNumberCnt, (Transition) null);
                this.val$notifContainer.addComponent(this.val$borderCnt);
                this.val$notifContainer.revalidate();
                TopUpNewPage.access$002(TopUpNewPage.this, true);
                CihMBanking.sesPMTR.setIsNotif(TopUpNewPage.access$000(TopUpNewPage.this));
                return;
            }
            this.val$notifContainer.replace(this.val$phoneNumberCnt, this.val$emptyNotifCnt, (Transition) null);
            this.val$notifContainer.removeComponent(this.val$borderCnt);
            this.val$notifContainer.revalidate();
            TopUpNewPage.access$002(TopUpNewPage.this, false);
            CihMBanking.sesPMTR.setIsNotif(TopUpNewPage.access$000(TopUpNewPage.this));
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$cnt;
        final /* synthetic */ Container val$notifContainer;
        final /* synthetic */ Container val$phoneNumberCnt;

        2(Container container, Container container2, Container container3) {
            this.val$cnt = container;
            this.val$notifContainer = container2;
            this.val$phoneNumberCnt = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TopUpNewPage.BENEFCNT.removeAll();
            for (int i = 0; i < TopUpNewPage.Glob.getComponentCount(); i++) {
                TopUpNewPage.Glob.getComponentAt(i).setUIID("Container");
                ((Container) TopUpNewPage.Glob.getComponentAt(i)).revalidate();
            }
            this.val$cnt.setUIID("greyCnt");
            Container container = (Container) this.val$cnt.getComponentAt(1);
            if (container.getComponentCount() > 0) {
                Container container2 = (Container) ((Container) ((Container) container.getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
                TopUpNewPage.BENEFCNT.addComponent(TopUpNewPage.access$100(TopUpNewPage.this, ((Label) container2.getComponentAt(0)).getText(), StringTools.RibFormatWithoutSP(((Label) container2.getComponentAt(1)).getText())));
                TopUpNewPage.BENEFCNT.revalidate();
            }
            TopUpNewPage.A.openclose(true, TopUpNewPage.Glob);
            TopUpNewPage.this.cntBtns.setHidden(false);
            TopUpNewPage.this.cntTransfertForm.setScrollableY(true);
            for (int i2 = 0; i2 < TopUpNewPage.this.uiManager.stateMachine.findCntGlobalTransfer(TopUpNewPage.this.cntTransfertForm).getComponentCount(); i2++) {
                if (!TopUpNewPage.this.uiManager.stateMachine.findCntGlobalTransfer(TopUpNewPage.this.cntTransfertForm).getComponentAt(i2).getName().equals("CntAccountChosen")) {
                    TopUpNewPage.this.uiManager.stateMachine.findCntGlobalTransfer(TopUpNewPage.this.cntTransfertForm).getComponentAt(i2).setHidden(false);
                }
            }
            Container container3 = new Container(new BoxLayout(2));
            container3.setUIID("g_cntBorderV2");
            Container container4 = new Container();
            Beneficiary GetCurrentBeneficiary = TopUpNewPage.this.GetCurrentBeneficiary(container);
            B3GCheckBox b3GCheckBox = new B3GCheckBox("Notification");
            b3GCheckBox.setUIID("g_lblNotif");
            b3GCheckBox.addItem("Notifier le bénéficiaire");
            b3GCheckBox.getItem("Notifier le bénéficiaire").addActionListener(new 1(container4, container3));
            String str = GetCurrentBeneficiary.isNotif;
            String str2 = GetCurrentBeneficiary.typeBenef;
            if (str.equals("0") && str2.equals("300014")) {
                this.val$notifContainer.removeAll();
                this.val$notifContainer.addComponent(b3GCheckBox.GetContainer());
                b3GCheckBox.getItem("Notifier le bénéficiaire").setSelected(false);
                this.val$notifContainer.addComponent(container4);
                ((TextField) ((Container) this.val$phoneNumberCnt.getComponentAt(1)).getComponentAt(0)).setText(GetCurrentBeneficiary.phoneNumber);
                TopUpNewPage.access$002(TopUpNewPage.this, false);
                CihMBanking.sesPMTR.setIsNotif(TopUpNewPage.access$000(TopUpNewPage.this));
                this.val$notifContainer.revalidate();
            } else if (GetCurrentBeneficiary.typeBenef.equals("300014")) {
                this.val$notifContainer.removeAll();
            }
            TopUpNewPage.this.cntTransfertForm.revalidate();
        }

        class 1 implements ActionListener {
            final /* synthetic */ Container val$borderCnt;
            final /* synthetic */ Container val$emptyNotifCnt;

            1(Container container, Container container2) {
                this.val$emptyNotifCnt = container;
                this.val$borderCnt = container2;
            }

            public void actionPerformed(ActionEvent actionEvent) {
                if (!TopUpNewPage.access$000(TopUpNewPage.this)) {
                    2.this.val$notifContainer.replace(this.val$emptyNotifCnt, 2.this.val$phoneNumberCnt, (Transition) null);
                    2.this.val$notifContainer.addComponent(this.val$borderCnt);
                    2.this.val$notifContainer.revalidate();
                    TopUpNewPage.access$002(TopUpNewPage.this, true);
                    CihMBanking.sesPMTR.setIsNotif(TopUpNewPage.access$000(TopUpNewPage.this));
                    return;
                }
                2.this.val$notifContainer.replace(2.this.val$phoneNumberCnt, this.val$emptyNotifCnt, (Transition) null);
                2.this.val$notifContainer.removeComponent(this.val$borderCnt);
                2.this.val$notifContainer.revalidate();
                TopUpNewPage.access$002(TopUpNewPage.this, false);
                CihMBanking.sesPMTR.setIsNotif(TopUpNewPage.access$000(TopUpNewPage.this));
            }
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (TopUpNewPage.A.getArrowUIID().equals("op_BtnOppositionValidationV2")) {
                TopUpNewPage.this.cntTransfertForm.setScrollableY(false);
                for (int i = 0; i < TopUpNewPage.this.uiManager.stateMachine.findCntGlobalTransfer(TopUpNewPage.this.cntTransfertForm).getComponentCount(); i++) {
                    if (!TopUpNewPage.this.uiManager.stateMachine.findCntGlobalTransfer(TopUpNewPage.this.cntTransfertForm).getComponentAt(i).getName().equals("CntAccountChosen")) {
                        TopUpNewPage.this.uiManager.stateMachine.findCntGlobalTransfer(TopUpNewPage.this.cntTransfertForm).getComponentAt(i).setHidden(true);
                    }
                }
                TopUpNewPage.this.cntBtns.setHidden(true);
                TopUpNewPage.this.cntBtns.revalidate();
                TopUpNewPage.this.cntTransfertForm.revalidate();
                return;
            }
            TopUpNewPage.this.cntTransfertForm.setScrollableY(true);
            for (int i2 = 0; i2 < TopUpNewPage.this.uiManager.stateMachine.findCntGlobalTransfer(TopUpNewPage.this.cntTransfertForm).getComponentCount(); i2++) {
                if (!TopUpNewPage.this.uiManager.stateMachine.findCntGlobalTransfer(TopUpNewPage.this.cntTransfertForm).getComponentAt(i2).getName().equals("CntAccountChosen")) {
                    TopUpNewPage.this.uiManager.stateMachine.findCntGlobalTransfer(TopUpNewPage.this.cntTransfertForm).getComponentAt(i2).setHidden(false);
                }
            }
            TopUpNewPage.this.cntBtns.setHidden(false);
            TopUpNewPage.this.cntBtns.revalidate();
            TopUpNewPage.this.cntTransfertForm.revalidate();
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ ArrayList val$BeneficiaryList;
        final /* synthetic */ Container val$issuarAccount;

        4(Container container, ArrayList arrayList) {
            this.val$issuarAccount = container;
            this.val$BeneficiaryList = arrayList;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (TopUpNewPage.this.uiManager.stateMachine.findTxtAmount(TopUpNewPage.this.cntTransfertForm).getText().length() == 0 || TopUpNewPage.this.uiManager.stateMachine.findTxtMotif(TopUpNewPage.this.cntTransfertForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TopUpNewPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                    return;
                }
                if (CihMBanking.sesPMTR.isNotif && TopUpNewPage.access$200(TopUpNewPage.this).getText().length() < 10) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TopUpNewPage.this.uiManager.stateMachine, "Veuillez entrer un numéro valide du bénéficiaire", null);
                    return;
                }
                TopUpNewPage topUpNewPage = TopUpNewPage.this;
                TransactionResume GetTransaction = topUpNewPage.GetTransaction(this.val$issuarAccount, topUpNewPage.uiManager.stateMachine.findTxtAmount(TopUpNewPage.this.cntTransfertForm), TopUpNewPage.this.uiManager.stateMachine.findTxtMotif(TopUpNewPage.this.cntTransfertForm), this.val$BeneficiaryList);
                ServiceResponse sendOtpTopUpProcess = ServiceManager.sendOtpTopUpProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1018", 300014, "1", "0", GetTransaction.IssuerAccountNumber, GetTransaction.CreditedAccountNumber, GetTransaction.Amount, "");
                if (sendOtpTopUpProcess.getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.setSessionCurrentTransactionResume(GetTransaction);
                    CihMBanking.sesPMTR.setSessionSavedContainer(TopUpNewPage.this.uiManager.stateMachine.findCntGlobalTransfer(TopUpNewPage.this.cntTransfertForm));
                    TopUpNewPage topUpNewPage2 = TopUpNewPage.this;
                    Container container = topUpNewPage2.cntTransfertForm;
                    Container findCntGlobalTransfer = TopUpNewPage.this.uiManager.stateMachine.findCntGlobalTransfer(TopUpNewPage.this.cntTransfertForm);
                    TopUpNewPage topUpNewPage3 = TopUpNewPage.this;
                    topUpNewPage2.SwitchTransfertForms(container, findCntGlobalTransfer, topUpNewPage3.GetTransferSecurityForm(topUpNewPage3.cntTransfertForm), false);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TopUpNewPage.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpTopUpProcess.getStatusLabel()), null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TopUpNewPage.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                TopUpNewPage.this.uiManager.NavigateToPageById(73);
            }
        }
    }

    public TransactionResume GetTransaction(Container container, Container container2, TextArea textArea, TextArea textArea2, ArrayList arrayList) {
        TransactionResume transactionResume = new TransactionResume();
        transactionResume.Amount = textArea.getText();
        transactionResume.Motif = textArea2.getText();
        transactionResume.PassHB = "";
        transactionResume.field2 = this.benifPhoneNumber.getText();
        Container container3 = (Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0);
        transactionResume.IssuerAccountOwner = ((Label) ((Container) ((Container) ((Container) container3.getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getText();
        transactionResume.IssuerAccountNumber = ((Account) ((B3gService) container3.getClientProperty("AccountClient"))).rib;
        Container container4 = (Container) ((Tabs) container2.getComponentAt(2)).getSelectedComponent();
        Container container5 = (Container) container4.getComponentAt(0);
        Container container6 = (Container) ((Container) ((Container) container5.getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
        transactionResume.CreditedAccountAlias = ((Label) container6.getComponentAt(0)).getText();
        transactionResume.SelectedService = container4.getName();
        transactionResume.CreditedAccountNumber = DataTools.DisHiddenCard(((Label) container6.getComponentAt(1)).getText(), arrayList);
        transactionResume.TypeOperation = "TOPUP";
        transactionResume.field1 = CihMBanking.sesPMTR.isNotif ? "1" : "0";
        return transactionResume;
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
        if (Display.getInstance().getPlatformName().equals("ios")) {
            this.txtHBPassWord.startEditingAsync();
        }
    }

    public Container GetTransferSecurityForm(Container container) {
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container2 = (Container) this.uib.findByName("Container17", createContainer);
        this.cntBtns.setHidden(true);
        container2.removeAll();
        container2.setLayout(BoxLayout.y());
        container2.add(b3GCIHEcode.getComponent());
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
            TopUpNewPage.this.cntBtns.setHidden(false);
            TopUpNewPage.isOtpPageShow = false;
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            this.val$Parent.revalidate();
        }
    }

    class 7 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        7(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TopUpNewPage.access$302(TopUpNewPage.this, this.val$eCode1.getValue());
            TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
            sessionCurrentTransactionResume.PassHB = TopUpNewPage.access$300(TopUpNewPage.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (TopUpNewPage.access$300(TopUpNewPage.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(TopUpNewPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
            } else if (sessionCurrentTransactionResume.SelectedService.equals("300018")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(10, sessionCurrentTransactionResume, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 300018);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogTransfertService(10, sessionCurrentTransactionResume, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 300014);
            }
        }
    }

    ArrayList getFirstAccountBeneficiary(ArrayList arrayList, ArrayList arrayList2) {
        Card card = (Card) CihMBanking.sesPMTR.objctCrdCurrent;
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            return arrayList2;
        }
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            Iterator it = ((Account) arrayList.get(i)).accountBeneficiaries.iterator();
            while (it.hasNext()) {
                Beneficiary beneficiary = (Beneficiary) it.next();
                if (beneficiary.serviceId.equals("300014") || beneficiary.serviceId.equals("300018")) {
                    arrayList3.add(beneficiary);
                }
            }
            if (!arrayList3.isEmpty()) {
                break;
            }
        }
        Collections.sort(arrayList3, new SortBenifByAlias());
        return card != null ? getCardToShowFirstTime(card.plainCardNumber, arrayList3) : arrayList3;
    }

    private Container DrawAcquiredAcc(String str, String str2) {
        Container GetContainerNw = this.uiManager.GetContainerNw("GloabalContainerV2", "Carte à recharger");
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AccountItemBenefTransfertV2");
        this.uiManager.stateMachine.findLblAccountOwner(createContainer).setText(str);
        this.uiManager.stateMachine.findLblAccountRibValue(createContainer).setText(StringTools.RibFormatWithSP(str2));
        GetContainerNw.addComponent(createContainer);
        return GetContainerNw;
    }

    public TransactionResume GetTransaction(Container container, TextArea textArea, TextArea textArea2, ArrayList arrayList) {
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
            transactionResume.SelectedService = container3.getName();
            transactionResume.CreditedAccountNumber = DataTools.DisHiddenCard(((Label) container4.getComponentAt(1)).getText(), arrayList);
            transactionResume.field1 = CihMBanking.sesPMTR.isNotif ? "1" : "0";
            transactionResume.field2 = "";
            transactionResume.TypeOperation = "TOPUP";
        } else {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Veuillez choisir le beneficiaire", null);
        }
        return transactionResume;
    }

    ArrayList getCanDebitAccount(ArrayList arrayList) {
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Account account = (Account) it.next();
            if (account.canDebit.equals("True") && (account.eligibleService.contains("300014") || account.eligibleService.contains("300018"))) {
                arrayList2.add(account);
            }
        }
        return arrayList2;
    }

    public Beneficiary GetCurrentBeneficiary(Container container) {
        return (Beneficiary) ((B3gService) container.getClientProperty("beneficiary"));
    }

    ArrayList getCardToShowFirstTime(String str, ArrayList arrayList) {
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
}
