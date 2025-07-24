package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
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
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.AutoCompleteTextField;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AddOvp extends B3GPage {
    public static B3gAccordion A;
    public static Container BENEFCNT;
    public static Container Glob;
    public static Container cntBtns;
    public static Container cntTransfertForm;
    public static boolean isOtpPageShow;
    String Amount;
    Button RememberBtn;
    private String VnewaliasText;
    Picker dateExcPicker;
    Picker dateFinPicker;
    DateFormat df;
    Picker frequencePicker;
    TransfertDATA trDT;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();
    private boolean isOvp = false;

    static /* synthetic */ String access$000(AddOvp addOvp) {
        return addOvp.VnewaliasText;
    }

    static /* synthetic */ String access$002(AddOvp addOvp, String str) {
        addOvp.VnewaliasText = str;
        return str;
    }

    static /* synthetic */ ServiceResponse access$100(AddOvp addOvp, TransactionResume transactionResume) {
        return addOvp.OvpConfirmation(transactionResume);
    }

    public AddOvp(Object obj, Object obj2, int i) {
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
        this.thisContainer.setName("TransfertNewPage");
        try {
            CihMBanking.sesPMTR.setToken("");
            this.trDT = new TransfertDATA().TransfertDATAProcess(28);
            this.df = new SimpleDateFormat("yyyy-MM-dd");
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(GetContainerTransferForm(this.trDT.getBeneficiaryList(), this.trDT.getbeneficiaryStatusLabel(), this.trDT.getPlafond()), "VIREMENTS PERMANENTS"));
            this.uiManager.DrawTabsWithNavigationV3(container, arrayList, cntBtns, this.uiManager.stateMachine.findTxtAmount(cntTransfertForm), this.uiManager.stateMachine.findTxtMotif(cntTransfertForm), this.txtHBPassWord);
            this.thisContainer.add(container);
        } catch (Exception unused) {
            this.thisContainer.setLayout(new BorderLayout());
            this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
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
        return container;
    }

    public Container GetContainerTransferForm(ArrayList arrayList, String str, ArrayList arrayList2) throws ParseException {
        cntTransfertForm = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "OvpAdd");
        if (Display.getInstance().getPlatformName().equals("ios")) {
            cntTransfertForm.setScrollableY(false);
        }
        new Account().uiManager = this.uiManager;
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountListVirm());
        arrayList3.addAll(CihMBanking.sesPMTR.getSessionClient().getListRelationAccount());
        Container DrawListContainerTransfer = this.uiManager.DrawListContainerTransfer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getCanDebitAccount(arrayList3), 1, 16, str, null, this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à créditer", Boolean.TRUE, getFirstAccountBeneficiary(arrayList3, arrayList), 1, 17, "Aucun compte n'est disponible pour cette opération.", null, null, null, null), CihMBanking.sesPMTR.getSessionClient().getClient_BeneficiaryList());
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(DrawListContainerTransfer);
        this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).setMaxSize(20);
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
        new Button(" Choisissez votre bénéficiaire ").setUIID("op_BtnOppositionValidationV2");
        CihMBanking.sesPMTR.setSessionSavedContainer(cntTransfertForm);
        if (!CihMBanking.sesPMTR.OvpAmount.equals("")) {
            this.uiManager.stateMachine.findTxtAmount(cntTransfertForm).setText(CihMBanking.sesPMTR.OvpAmount);
        }
        if (!CihMBanking.sesPMTR.OvpMotif.equals("")) {
            this.uiManager.stateMachine.findTxtMotif(cntTransfertForm).setText(CihMBanking.sesPMTR.OvpMotif);
        }
        ArrayList firstAccountBeneficiary = getFirstAccountBeneficiary(arrayList3, arrayList);
        this.trDT.setBeneficiaryList(firstAccountBeneficiary);
        button.addActionListener(new 1());
        container.add(new Label("Compte à créditer "));
        Glob = new Container(new BoxLayout(2));
        if (this.service != null) {
            this.uiManager.stateMachine.findBtnTransferValidation(cntTransfertForm).setUIID("op_BtnOppositionValidationOrgBg");
            Beneficiary beneficiary = (Beneficiary) this.service;
            BENEFCNT.addComponent(DrawAcquiredAcc(beneficiary.alias, beneficiary.cardNumber));
            BENEFCNT.revalidate();
        }
        int i2 = 0;
        while (i2 < firstAccountBeneficiary.size()) {
            TableLayout tableLayout = new TableLayout(1, i);
            Button button2 = new Button();
            button2.setUIID("Container");
            Container container2 = new Container();
            container2.setLeadComponent(button2);
            container2.setLayout(tableLayout);
            container2.removeAll();
            container2.setUIID("Container");
            TableLayout.Constraint createConstraint = tableLayout.createConstraint();
            TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
            createConstraint.setWidthPercentage(80);
            createConstraint2.setWidthPercentage(1);
            container2.addComponent(createConstraint2, button2);
            container2.addComponent(createConstraint, ((Beneficiary) firstAccountBeneficiary.get(i2)).DrawItem(this.uiManager.stateMachine, this.uiManager.ressource, 17, this.service, this.thisContainer, this.thisContainer));
            button2.addActionListener(new 2());
            Glob.add(container2);
            i2++;
            i = 2;
        }
        A.addContent(new Container(), Glob);
        A.setScrollVisible(false);
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(button);
        A.addOnClickItemListener(new 3());
        this.uiManager.stateMachine.findLblDayValue(cntTransfertForm).setText("100 000.00");
        this.uiManager.stateMachine.findLblWeekValue(cntTransfertForm).setText("100 000.00");
        this.uiManager.stateMachine.findLblDay(cntTransfertForm).setText("Plafond quotidien: ");
        this.uiManager.stateMachine.findLblWeek(cntTransfertForm).setText("Plafond par opération: ");
        cntBtns = new Container(new BoxLayout(2));
        Button button3 = new Button(" Ajouter un Bénéficiaire");
        button3.setIcon(this.uiManager.ressource.getImage("addBeneficery.png"));
        button3.setUIID("op_BtnOppositionValidationV2");
        cntBtns.setUIID("WhitCnt_PaddingLeftRigt_2");
        this.uiManager.ControlTextfield(this.uiManager.stateMachine.findTxtAmount(cntTransfertForm), "AMOUNT", cntTransfertForm);
        this.uiManager.stateMachine.findBtnTransferValidation(cntTransfertForm).addActionListener(new 4(DrawListContainerTransfer));
        button3.addActionListener(new 5());
        Container container3 = (Container) this.uib.findByName("ExecuteCnt", cntTransfertForm);
        Label label = new Label("Date d'exécution");
        this.dateExcPicker = new Picker();
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (CihMBanking.sesPMTR.OvpexcectDate.equals("")) {
            this.dateExcPicker.setDate(calendar.getTime());
            this.dateExcPicker.setText(simpleDateFormat.format(calendar.getTime()));
        } else {
            this.dateExcPicker.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(CihMBanking.sesPMTR.OvpexcectDate));
            this.dateExcPicker.setText(CihMBanking.sesPMTR.OvpexcectDate);
        }
        this.dateExcPicker.setUIID("");
        Container GetCntT = GetCntT("Date de naissance", null, null, drawPicker(this.dateExcPicker));
        this.dateExcPicker.addActionListener(new 6(calendar, simpleDateFormat));
        container3.add(label);
        container3.add(GetCntT);
        Container container4 = (Container) this.uib.findByName("DateFinCnt", cntTransfertForm);
        Container container5 = (Container) this.uib.findByName("RepeterCnt", cntTransfertForm);
        Container container6 = new Container(new GridLayout(1, 2));
        Label label2 = new Label("Repéter ce virement");
        Container container7 = new Container(new BoxLayout(2));
        container7.setUIID("padding_1_1_1_1");
        Label label3 = new Label("Date fin");
        this.dateFinPicker = new Picker();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(1, 1);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        if (CihMBanking.sesPMTR.OvpFindate.equals("")) {
            this.dateFinPicker.setDate(calendar2.getTime());
            this.dateFinPicker.setText(simpleDateFormat2.format(calendar2.getTime()));
        } else {
            this.dateFinPicker.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(CihMBanking.sesPMTR.OvpFindate));
            this.dateFinPicker.setText(CihMBanking.sesPMTR.OvpFindate);
        }
        this.dateFinPicker.addActionListener(new 7(simpleDateFormat));
        this.dateFinPicker.setUIID("");
        Container GetCntT2 = GetCntT("Date de naissance", null, null, drawPicker(this.dateFinPicker));
        container7.add(label3);
        container7.add(GetCntT2);
        Container container8 = new Container(new BoxLayout(2));
        container8.setUIID("padding_1_1_1_1");
        Label label4 = new Label("Fréquence");
        Picker picker = new Picker();
        this.frequencePicker = picker;
        picker.setType(4);
        Container GetCntT3 = GetCntT("Date de naissance", null, null, drawPicker(this.frequencePicker, new String[]{"Hebdomadaire", "Mensuelle", "Trimestrielle", "Semestrielle", "Annuelle"}));
        if (!CihMBanking.sesPMTR.OvpFrequence.equals("")) {
            this.frequencePicker.setSelectedString(CihMBanking.sesPMTR.OvpFrequence);
        }
        container8.add(label4);
        container8.add(GetCntT3);
        container6.add(container8).add(container7);
        container4.add(container6);
        Button button4 = new Button();
        this.RememberBtn = button4;
        button4.setIcon(CihMBanking.theme.getImage("RememberOff.png"));
        container5.setHidden(true);
        this.RememberBtn.addActionListener(new 8(container6, container4));
        this.RememberBtn.setUIID("Container");
        container5.add("West", label2);
        container5.add("East", this.RememberBtn);
        cntTransfertForm.setScrollVisible(false);
        cntBtns.add(button3);
        this.uiManager.stateMachine.findCntGlobalTransfer(cntTransfertForm).setScrollableY(true);
        this.uiManager.stateMachine.findCntGlobalTransfer(cntTransfertForm).setScrollVisible(false);
        return cntTransfertForm;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            CihMBanking.sesPMTR.OvpAmount = AddOvp.this.uiManager.stateMachine.findTxtAmount(AddOvp.cntTransfertForm).getText();
            CihMBanking.sesPMTR.OvpMotif = AddOvp.this.uiManager.stateMachine.findTxtMotif(AddOvp.cntTransfertForm).getText();
            CihMBanking.sesPMTR.OvpFindate = simpleDateFormat.format(AddOvp.this.dateFinPicker.getDate());
            CihMBanking.sesPMTR.OvpexcectDate = simpleDateFormat.format(AddOvp.this.dateExcPicker.getDate());
            CihMBanking.sesPMTR.OvpFrequence = AddOvp.this.frequencePicker.getSelectedString();
            AddOvp.this.uiManager.NavigateToPage(new OvpBnnef(AddOvp.this.uiManager, AddOvp.this.trDT, 56));
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AddOvp.BENEFCNT.removeAll();
            AddOvp.A.openclose(true, AddOvp.Glob);
            AddOvp.cntBtns.setHidden(false);
            AddOvp.cntTransfertForm.setScrollableY(true);
            for (int i = 0; i < AddOvp.this.uiManager.stateMachine.findCntGlobalTransfer(AddOvp.cntTransfertForm).getComponentCount(); i++) {
                if (!AddOvp.this.uiManager.stateMachine.findCntGlobalTransfer(AddOvp.cntTransfertForm).getComponentAt(i).getName().equals("CntAccountChosen")) {
                    AddOvp.this.uiManager.stateMachine.findCntGlobalTransfer(AddOvp.cntTransfertForm).getComponentAt(i).setHidden(false);
                }
            }
            AddOvp.cntTransfertForm.revalidate();
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (AddOvp.A.getArrowUIID().equals("op_BtnOppositionValidationV2")) {
                AddOvp.cntTransfertForm.setScrollableY(false);
                for (int i = 0; i < AddOvp.this.uiManager.stateMachine.findCntGlobalTransfer(AddOvp.cntTransfertForm).getComponentCount(); i++) {
                    if (!AddOvp.this.uiManager.stateMachine.findCntGlobalTransfer(AddOvp.cntTransfertForm).getComponentAt(i).getName().equals("CntAccountChosen")) {
                        AddOvp.this.uiManager.stateMachine.findCntGlobalTransfer(AddOvp.cntTransfertForm).getComponentAt(i).setHidden(true);
                    }
                }
                AddOvp.cntBtns.setHidden(true);
                AddOvp.cntBtns.revalidate();
                AddOvp.cntTransfertForm.revalidate();
                return;
            }
            AddOvp.cntTransfertForm.setScrollableY(true);
            for (int i2 = 0; i2 < AddOvp.this.uiManager.stateMachine.findCntGlobalTransfer(AddOvp.cntTransfertForm).getComponentCount(); i2++) {
                if (!AddOvp.this.uiManager.stateMachine.findCntGlobalTransfer(AddOvp.cntTransfertForm).getComponentAt(i2).getName().equals("CntAccountChosen")) {
                    AddOvp.this.uiManager.stateMachine.findCntGlobalTransfer(AddOvp.cntTransfertForm).getComponentAt(i2).setHidden(false);
                }
            }
            AddOvp.cntBtns.setHidden(false);
            AddOvp.cntBtns.revalidate();
            AddOvp.cntTransfertForm.revalidate();
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Container val$issuarAccount;

        4(Container container) {
            this.val$issuarAccount = container;
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x01f6  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void actionPerformed(com.codename1.ui.events.ActionEvent r12) {
            /*
                Method dump skipped, instructions count: 902
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.AddOvp.4.actionPerformed(com.codename1.ui.events.ActionEvent):void");
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                AddOvp.cntTransfertForm.replace((Container) AddOvp.cntTransfertForm.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 0));
                AddOvp.cntTransfertForm.revalidate();
                AddOvp.cntBtns.setHidden(false);
                AddOvp.this.uiManager.btnBack.getListeners().clear();
                AddOvp.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    AddOvp.this.uiManager.GoBack();
                }
            }
        }
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                AddOvp.this.uiManager.NavigateToPageById(72);
            }
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ Calendar val$calendar;
        final /* synthetic */ SimpleDateFormat val$sdfa;

        6(Calendar calendar, SimpleDateFormat simpleDateFormat) {
            this.val$calendar = calendar;
            this.val$sdfa = simpleDateFormat;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.add(5, 1);
                Date parse = simpleDateFormat.parse(AddOvp.this.df.format(AddOvp.this.dateExcPicker.getDate()));
                Date parse2 = simpleDateFormat.parse(AddOvp.this.df.format(calendar.getTime()));
                Date parse3 = simpleDateFormat.parse(AddOvp.this.df.format(AddOvp.this.dateFinPicker.getDate()));
                if (parse.getTime() - parse2.getTime() < 0) {
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.add(5, 1);
                    AddOvp.this.dateExcPicker.setDate(calendar2.getTime());
                    AddOvp.this.dateExcPicker.setText(this.val$sdfa.format(calendar2.getTime()));
                } else if (parse3.getTime() - parse.getTime() < 0) {
                    AddOvp.this.dateFinPicker.setDate(AddOvp.this.dateExcPicker.getDate());
                    AddOvp.this.dateExcPicker.setText(this.val$sdfa.format(AddOvp.this.dateExcPicker.getDate()));
                    AddOvp.this.dateFinPicker.setText(this.val$sdfa.format(AddOvp.this.dateExcPicker.getDate()));
                } else {
                    AddOvp.this.dateExcPicker.setText(this.val$sdfa.format(AddOvp.this.dateExcPicker.getDate()));
                }
            } catch (ParseException unused) {
            }
        }
    }

    class 7 implements ActionListener {
        final /* synthetic */ SimpleDateFormat val$sdfa;

        7(SimpleDateFormat simpleDateFormat) {
            this.val$sdfa = simpleDateFormat;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                if (simpleDateFormat.parse(AddOvp.this.df.format(AddOvp.this.dateFinPicker.getDate())).getTime() - simpleDateFormat.parse(AddOvp.this.df.format(AddOvp.this.dateExcPicker.getDate())).getTime() < 0) {
                    Calendar.getInstance().add(1, 1);
                    AddOvp.this.dateFinPicker.setDate(AddOvp.this.dateExcPicker.getDate());
                    AddOvp.this.dateFinPicker.setText(this.val$sdfa.format(AddOvp.this.dateExcPicker.getDate()));
                } else {
                    AddOvp.this.dateFinPicker.setText(this.val$sdfa.format(AddOvp.this.dateFinPicker.getDate()));
                }
            } catch (ParseException unused) {
            }
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ Container val$DateFinCnt;
        final /* synthetic */ Container val$HidCnt;

        8(Container container, Container container2) {
            this.val$HidCnt = container;
            this.val$DateFinCnt = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (AddOvp.this.RememberBtn.getIcon().equals(CihMBanking.theme.getImage("RememberOff.png"))) {
                AddOvp.this.RememberBtn.setIcon(CihMBanking.theme.getImage("RememberOn.png"));
                this.val$HidCnt.setHidden(false);
                this.val$DateFinCnt.revalidate();
            } else {
                AddOvp.this.RememberBtn.setIcon(CihMBanking.theme.getImage("RememberOff.png"));
                this.val$HidCnt.setHidden(true);
                this.val$DateFinCnt.revalidate();
            }
        }
    }

    public TransactionResume GetTransaction(Container container, TextArea textArea, TextArea textArea2) {
        TransactionResume transactionResume = new TransactionResume();
        transactionResume.Amount = this.Amount;
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
        new Container();
        for (int i = 0; i < Glob.getComponentCount(); i++) {
            if (Glob.getComponentAt(i).getUIID().equals("greyCnt")) {
            }
        }
        try {
            Container container3 = (Container) ((Container) ((Container) ((Container) ((Container) BENEFCNT.getComponentAt(0)).getComponentAt(2)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
            transactionResume.CreditedAccountAlias = ((Label) container3.getComponentAt(0)).getText();
            transactionResume.CreditedAccountNumber = StringTools.RibFormatWithoutSP(((Label) container3.getComponentAt(1)).getText());
        } catch (Exception unused) {
            transactionResume.CreditedAccountAlias = "";
            transactionResume.CreditedAccountNumber = "";
        }
        transactionResume.field1 = "";
        transactionResume.field2 = "";
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

    class 9 implements DataChangedListener {
        public void dataChanged(int i, int i2) {
        }

        9() {
        }
    }

    public void HidePassWordOnKeyPressed(TextField textField) {
        textField.addDataChangeListener(new 9());
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
            if (account.canDebit.equals("True") && account.eligibleService.contains("300013") && !account.rib.substring(13, 17).equals("2310")) {
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

    private Container drawPicker(Picker picker) {
        Container container = new Container(new BorderLayout());
        container.setUIID("LoginTextFSmall");
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(0, 0, 0, 0);
        picker.setUIID("lbl_small_white");
        picker.setAlignment(1);
        container.add("Center", picker).add("East", new Label());
        container.setLeadComponent(picker);
        return container;
    }

    private Container GetCntT(String str, String str2, String str3, Container container) {
        Container container2 = new Container(new BoxLayout(2));
        container2.setScrollVisible(false);
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container3 = new Container();
        container3.setLayout(tableLayout);
        container3.setFocusable(false);
        container3.setScrollable(false);
        Container container4 = new Container(new BorderLayout());
        new Label(str).setUIID("g_lblGREYTitleTextfield");
        container4.add("Center", container);
        Container container5 = new Container(new FlowLayout(4, 4));
        Label label = new Label();
        label.setIcon(this.uiManager.ressource.getImage(str3));
        container5.add(label);
        container4.add("West", container5);
        container4.setUIID("mtc_homemenuItemText");
        container4.setScrollVisible(false);
        container4.setFocusable(false);
        Button button = new Button();
        button.setUIID("crd_btnCardImage");
        button.setScrollVisible(false);
        button.setFocusable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(100);
        container3.addComponent(createConstraint, container4);
        container2.addComponent(container3);
        container2.getAllStyles().setMarginUnit(1);
        container2.setUIID("CntBlueHeaderPadding1");
        return container2;
    }

    public Container OvpBnnef(ArrayList arrayList, Container container) {
        ArrayList arrayList2 = arrayList;
        Container container2 = new Container(new BorderLayout());
        int i = 2;
        Container container3 = new Container(new BoxLayout(2));
        10 r6 = new 10(new DefaultListModel());
        r6.setMinimumElementsShownInPopup(2);
        r6.setHint("recherche");
        r6.setUIID("padding_1_1_1_1");
        r6.setAlignment(1);
        Container container4 = new Container(new BoxLayout(2));
        container4.setUIID("padding_2_2_2_2");
        Container container5 = new Container(new BoxLayout(2));
        container5.setUIID("border_grey_padding");
        container5.add(r6);
        container4.add(container5);
        Container container6 = new Container(new BoxLayout(2));
        int i2 = 0;
        int i3 = 0;
        while (i3 < arrayList.size()) {
            Container container7 = new Container(new BorderLayout());
            container7.setUIID("margin_0_0_1_1");
            Container container8 = new Container(new BoxLayout(i));
            container8.setUIID("OvpBankLogo");
            Button button = new Button();
            button.setUIID("");
            if (((Beneficiary) arrayList2.get(i3)).cardNumber.substring(i2, i).equals("23")) {
                button.setIcon(CihMBanking.theme.getImage("CihTransfertLogo.png"));
            } else {
                button.setIcon(CihMBanking.theme.getImage("Museum.png"));
            }
            Container container9 = new Container(new BoxLayout(i));
            Label label = new Label(((Beneficiary) arrayList2.get(i3)).alias);
            label.setUIID("g_lblDashBoardTitleGREY");
            Label label2 = new Label(((Beneficiary) arrayList2.get(i3)).cardNumber);
            label2.setUIID("g_lblDashBoardValueBlue");
            container9.add(label).add(label2);
            container7.add("Center", container9);
            container8.add(button);
            container7.add("West", container8);
            Button button2 = new Button();
            button2.setUIID("");
            button2.addActionListener(new 11(container));
            container7.add("East", button2);
            container7.setLeadComponent(button2);
            container6.add(container7);
            i3++;
            arrayList2 = arrayList;
            i = 2;
            i2 = 0;
        }
        container2.add("North", container4);
        container3.add(container6);
        Container container10 = new Container(new BoxLayout(2));
        container10.setUIID("padding_1_1_1_1");
        container2.add("Center", container3);
        container3.setPreferredH((Display.getInstance().getDisplayHeight() * 80) / 100);
        container3.setScrollableY(true);
        container3.setScrollVisible(false);
        Button button3 = new Button(" Ajouter un Bénéficiaire");
        button3.setIcon(this.uiManager.ressource.getImage("addBeneficery.png"));
        button3.setUIID("op_BtnOppositionValidationV2");
        container10.add(button3);
        container2.add("South", container10);
        button3.addActionListener(new 12());
        container2.setUIID("Whitemargin_2_2_2_2");
        return container2;
    }

    class 10 extends AutoCompleteTextField {
        protected boolean filter(String str) {
            return true;
        }

        10(ListModel listModel) {
            super(listModel);
        }
    }

    class 11 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        11(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                Container container = this.val$Parent;
                container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 0));
                this.val$Parent.revalidate();
            }
        }
    }

    class 12 implements ActionListener {
        12() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                AddOvp.this.uiManager.NavigateToPageById(72);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:3:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.b3g.common.ServiceResponse AddOvpSimulation(com.b3g.services.TransactionResume r10) {
        /*
            Method dump skipped, instructions count: 644
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.AddOvp.AddOvpSimulation(com.b3g.services.TransactionResume):com.b3g.common.ServiceResponse");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:6:0x00b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.b3g.common.ServiceResponse OvpConfirmation(com.b3g.services.TransactionResume r12) {
        /*
            Method dump skipped, instructions count: 670
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.AddOvp.OvpConfirmation(com.b3g.services.TransactionResume):com.b3g.common.ServiceResponse");
    }

    private Container drawPicker(Picker picker, String[] strArr) {
        Container container = new Container(new BorderLayout());
        container.setUIID("LoginTextFSmall");
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(0, 0, 0, 0);
        picker.setType(4);
        picker.setUIID("lbl_small_white");
        if (strArr != null) {
            picker.setStrings(strArr);
        }
        picker.setSelectedString("Mensuelle");
        picker.setAlignment(1);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("newListBox.png"));
        container.add("Center", picker).add("East", label);
        container.setLeadComponent(picker);
        return container;
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
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 13(container));
        this.uiManager.stateMachine.findBtnTransferValidation(cntTransfertForm).setHidden(true);
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 14(b3GCIHEcode));
        return createContainer;
    }

    class 13 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        13(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AddOvp.this.uiManager.stateMachine.findBtnTransferValidation(AddOvp.cntTransfertForm).setHidden(false);
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 0));
            AddOvp.cntBtns.setHidden(false);
            AddOvp.this.uiManager.btnBack.getListeners().clear();
            AddOvp.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                AddOvp.cntBtns.setHidden(false);
                AddOvp.isOtpPageShow = false;
                AddOvp.this.uiManager.GoBack();
            }
        }
    }

    class 14 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        14(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
            AddOvp.access$002(AddOvp.this, this.val$eCode1.getValue());
            sessionCurrentTransactionResume.PassHB = AddOvp.access$000(AddOvp.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (AddOvp.access$000(AddOvp.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AddOvp.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            try {
                ServiceResponse access$100 = AddOvp.access$100(AddOvp.this, sessionCurrentTransactionResume);
                if (access$100.getStatusCode().equals("000")) {
                    AddOvp addOvp = AddOvp.this;
                    addOvp.ShowMessageTransaction(addOvp.uiManager.stateMachine, "Opération effectuée avec succès", null);
                } else {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AddOvp.this.uiManager.stateMachine, DataTools.FormatUnicode(access$100.getStatusLabel()), null);
                }
            } catch (Exception unused) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AddOvp.this.uiManager.stateMachine, DataTools.FormatUnicode("Une erreur est survenue, Veuillez réessayer plus tard."), null);
            }
        }
    }

    public void ShowMessageTransaction(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMessage");
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
            stateMachine.findSpanLabel(createContainer).setText(str);
            stateMachine.findLabel1(createContainer).setText(str2);
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 15(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 15 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        15(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            AddOvp.this.uiManager.NavigateToPageById(46);
        }
    }
}
