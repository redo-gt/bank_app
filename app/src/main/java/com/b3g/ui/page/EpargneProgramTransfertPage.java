package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.ServiceManager;
import com.b3g.services.TransactionResume;
import com.b3g.services.TransferHistoric;
import com.b3g.services.TransfertDATA;
import com.b3g.tools.DataTools;
import com.b3g.tools.SortBenifByAlias;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordion;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class EpargneProgramTransfertPage extends B3GPage {
    public static B3gAccordion A;
    public static Container BENEFCNT;
    public static Container Glob;
    public static Container cntBtns;
    public static Container cntTransfertForm;
    public static boolean isOtpPageShow;
    private String VnewaliasText;
    public Picker dateExcPicker;
    public Picker dateFinPicker;
    Picker frequencePicker;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();
    private boolean isOvp = false;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    static /* synthetic */ String access$000(EpargneProgramTransfertPage epargneProgramTransfertPage) {
        return epargneProgramTransfertPage.VnewaliasText;
    }

    static /* synthetic */ String access$002(EpargneProgramTransfertPage epargneProgramTransfertPage, String str) {
        epargneProgramTransfertPage.VnewaliasText = str;
        return str;
    }

    static /* synthetic */ ServiceResponse access$100(EpargneProgramTransfertPage epargneProgramTransfertPage, TransactionResume transactionResume) {
        return epargneProgramTransfertPage.OvpConfirmation(transactionResume);
    }

    public EpargneProgramTransfertPage(Object obj, Object obj2, int i) {
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
        this.thisContainer.setUIID("Whitemargin_2_2_2_2");
        this.thisContainer.setName("TransfertNewPage");
        try {
            CihMBanking.sesPMTR.setToken("");
            TransfertDATA TransfertDATAProcess = new TransfertDATA().TransfertDATAProcess(28);
            AccountProcess();
            new Container(new BoxLayout(2)).setUIID("ad_CntAccountDetailsMain");
            this.thisContainer.add(GetContainerTransferForm(TransfertDATAProcess.getBeneficiaryList(), TransfertDATAProcess.getbeneficiaryStatusLabel(), TransfertDATAProcess.getPlafond()));
        } catch (Exception unused) {
            this.thisContainer.setLayout(new BorderLayout());
            this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
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
        cntTransfertForm = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "EpargneProgramTransfertPage");
        if (Display.getInstance().getPlatformName().equals("ios")) {
            cntTransfertForm.setScrollableY(false);
        }
        new Account().uiManager = this.uiManager;
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountListVirm());
        arrayList3.addAll(CihMBanking.sesPMTR.getSessionClient().getListRelationAccount());
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        for (int i = 0; i < arrayList3.size(); i++) {
            if (!((Account) arrayList3.get(i)).rib.substring(13, 17).equals("2310")) {
                arrayList5.add((Account) arrayList3.get(i));
            } else {
                Beneficiary beneficiary = new Beneficiary();
                beneficiary.alias = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom;
                beneficiary.cardNumber = ((Account) arrayList3.get(i)).rib;
                beneficiary.typeBenef = "300013";
                arrayList4.add(beneficiary);
            }
        }
        Container DrawListContainerTransfer = this.uiManager.DrawListContainerTransfer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getCanDebitAccount(arrayList3), 1, 16, str, null, this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à créditer", Boolean.TRUE, getFirstAccountBeneficiary(arrayList3, arrayList4), 1, 17, "Aucun compte n'est disponible pour cette opération.", null, null, null, null), CihMBanking.sesPMTR.getSessionClient().getClient_BeneficiaryList());
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(DrawListContainerTransfer);
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
        Container container = new Container(new BoxLayout(2));
        BENEFCNT = container;
        container.getAllStyles().setMarginUnit(1);
        BENEFCNT.getAllStyles().setMargin(1, 1, 0, 0);
        this.uiManager.stateMachine.findCntAccountChosen(cntTransfertForm).addComponent(BENEFCNT);
        B3gAccordion b3gAccordion = new B3gAccordion();
        A = b3gAccordion;
        b3gAccordion.setCloseIcon((Image) null);
        A.setOpenIcon((Image) null);
        Container container2 = new Container(new BoxLayout(2));
        Button button = new Button(" Choisissez votre bénéficiaire ");
        button.setUIID("op_BtnOppositionValidationV2Blue");
        Button button2 = new Button(" Choisissez votre bénéficiaire ");
        button2.setUIID("op_BtnOppositionValidationV2");
        A.setOpenBtn(button2);
        A.setCloseBtn(button);
        container2.add(new Label("Compte à créditer "));
        Glob = new Container(new BoxLayout(2));
        new ArrayList();
        ArrayList firstAccountBeneficiary = getFirstAccountBeneficiary(arrayList3, arrayList4);
        int i2 = 0;
        while (true) {
            if (i2 >= firstAccountBeneficiary.size()) {
                break;
            }
            if (((Beneficiary) firstAccountBeneficiary.get(i2)).cardNumber.substring(13, 17).equals("2310")) {
                BENEFCNT.addComponent(DrawAcquiredAcc(((Beneficiary) firstAccountBeneficiary.get(i2)).alias, ((Beneficiary) firstAccountBeneficiary.get(i2)).cardNumber));
                ((Container) BENEFCNT.getComponentAt(0)).setUIID("OrgLabel");
                BENEFCNT.revalidate();
                break;
            }
            i2++;
        }
        A.addOnClickItemListener(new 1());
        cntBtns = new Container(new BoxLayout(2));
        Button button3 = new Button(" Ajouter un Bénéficiaire");
        button3.setIcon(this.uiManager.ressource.getImage("addBeneficery.png"));
        button3.setUIID("op_BtnOppositionValidationV2");
        cntBtns.setUIID("WhitCnt_PaddingLeftRigt_2");
        this.uiManager.ControlTextfield(this.uiManager.stateMachine.findTxtAmount(cntTransfertForm), "AMOUNT", cntTransfertForm);
        if (arrayList3.size() > 0 && arrayList4.size() > 0) {
            this.uiManager.stateMachine.findBtnTransferValidation(cntTransfertForm).addActionListener(new 2(DrawListContainerTransfer));
        } else {
            ((Container) cntTransfertForm.getComponentAt(0)).removeComponent(((Container) cntTransfertForm.getComponentAt(0)).getComponentAt(6));
            cntTransfertForm.revalidate();
        }
        this.uiManager.NavigateToPageById(72);
        Container container3 = (Container) this.uib.findByName("ExecuteCnt", cntTransfertForm);
        Label label = new Label("Date d'exécution");
        this.dateExcPicker = new Picker();
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
        this.dateExcPicker.setDate(calendar.getTime());
        this.dateExcPicker.setText(simpleDateFormat.format(calendar.getTime()));
        this.dateExcPicker.setUIID("");
        Container GetCntT = GetCntT("Date de naissance", null, null, drawPicker(this.dateExcPicker));
        this.dateExcPicker.addActionListener(new 3(calendar, simpleDateFormat));
        container3.add(label);
        container3.add(GetCntT);
        Container container4 = (Container) this.uib.findByName("DateFinCnt", cntTransfertForm);
        Container container5 = new Container(new BoxLayout(2));
        new Label("Repéter ce virement");
        new Label("Se souvenir de moi").setUIID("lbl_medium_black");
        Container container6 = new Container(new BoxLayout(2));
        container6.setUIID("padding_1_1_1_1");
        Label label2 = new Label("Date fin");
        this.dateFinPicker = new Picker();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(1, 1);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        this.dateFinPicker.setDate(calendar2.getTime());
        this.dateFinPicker.setText(simpleDateFormat2.format(calendar2.getTime()));
        Container GetCntT2 = GetCntT("Date de naissance", null, null, drawPicker(this.dateFinPicker));
        this.dateFinPicker.addActionListener(new 4(simpleDateFormat));
        container6.add(label2);
        container6.add(GetCntT2);
        Container container7 = new Container(new BoxLayout(2));
        container7.setUIID("padding_1_1_1_1");
        Label label3 = new Label("Fréquence");
        Picker picker = new Picker();
        this.frequencePicker = picker;
        picker.setType(4);
        Container GetCntT3 = GetCntT("Date de naissance", null, null, drawPicker(this.frequencePicker, new String[]{"Hebdomadaire", "Mensuelle", "Trimestrielle", "Semestrielle", "Annuelle"}));
        container7.add(label3);
        container7.add(GetCntT3);
        container5.add(container7).add(container6);
        container4.add(container5);
        Button button4 = new Button();
        button4.setIcon(CihMBanking.theme.getImage("RememberOff.png"));
        container5.setHidden(false);
        button4.addActionListener(new 5(button4, container5, container4));
        button4.setUIID("Container");
        cntTransfertForm.setScrollVisible(false);
        cntBtns.add(button3);
        this.uiManager.stateMachine.findCntGlobalTransfer(cntTransfertForm).setScrollableY(true);
        return cntTransfertForm;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (EpargneProgramTransfertPage.A.getArrowUIID().equals("op_BtnOppositionValidationV2")) {
                EpargneProgramTransfertPage.cntTransfertForm.setScrollableY(false);
                for (int i = 0; i < EpargneProgramTransfertPage.this.uiManager.stateMachine.findCntGlobalTransfer(EpargneProgramTransfertPage.cntTransfertForm).getComponentCount(); i++) {
                    if (!EpargneProgramTransfertPage.this.uiManager.stateMachine.findCntGlobalTransfer(EpargneProgramTransfertPage.cntTransfertForm).getComponentAt(i).getName().equals("CntAccountChosen")) {
                        EpargneProgramTransfertPage.this.uiManager.stateMachine.findCntGlobalTransfer(EpargneProgramTransfertPage.cntTransfertForm).getComponentAt(i).setHidden(true);
                    }
                }
                EpargneProgramTransfertPage.cntBtns.setHidden(true);
                EpargneProgramTransfertPage.cntBtns.revalidate();
                EpargneProgramTransfertPage.cntTransfertForm.revalidate();
                return;
            }
            EpargneProgramTransfertPage.cntTransfertForm.setScrollableY(true);
            for (int i2 = 0; i2 < EpargneProgramTransfertPage.this.uiManager.stateMachine.findCntGlobalTransfer(EpargneProgramTransfertPage.cntTransfertForm).getComponentCount(); i2++) {
                if (!EpargneProgramTransfertPage.this.uiManager.stateMachine.findCntGlobalTransfer(EpargneProgramTransfertPage.cntTransfertForm).getComponentAt(i2).getName().equals("CntAccountChosen")) {
                    EpargneProgramTransfertPage.this.uiManager.stateMachine.findCntGlobalTransfer(EpargneProgramTransfertPage.cntTransfertForm).getComponentAt(i2).setHidden(false);
                }
            }
            EpargneProgramTransfertPage.cntBtns.setHidden(false);
            EpargneProgramTransfertPage.cntBtns.revalidate();
            EpargneProgramTransfertPage.cntTransfertForm.revalidate();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$issuarAccount;

        2(Container container) {
            this.val$issuarAccount = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (EpargneProgramTransfertPage.this.uiManager.stateMachine.findTxtAmount(EpargneProgramTransfertPage.cntTransfertForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(EpargneProgramTransfertPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                    return;
                }
                EpargneProgramTransfertPage epargneProgramTransfertPage = EpargneProgramTransfertPage.this;
                TransactionResume GetTransaction = epargneProgramTransfertPage.GetTransaction(this.val$issuarAccount, epargneProgramTransfertPage.uiManager.stateMachine.findTxtAmount(EpargneProgramTransfertPage.cntTransfertForm));
                ServiceResponse AddOvpSimulation = EpargneProgramTransfertPage.this.AddOvpSimulation(GetTransaction);
                if (AddOvpSimulation.getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.setSessionCurrentTransactionResume(GetTransaction);
                    CihMBanking.sesPMTR.setSessionSavedContainer(EpargneProgramTransfertPage.this.uiManager.stateMachine.findCntGlobalTransfer(EpargneProgramTransfertPage.cntTransfertForm));
                    EpargneProgramTransfertPage epargneProgramTransfertPage2 = EpargneProgramTransfertPage.this;
                    epargneProgramTransfertPage2.SwitchTransfertForms(epargneProgramTransfertPage2.thisContainer, (Container) EpargneProgramTransfertPage.this.thisContainer.getComponentAt(0), EpargneProgramTransfertPage.this.GetTransferSecurityForm(EpargneProgramTransfertPage.cntTransfertForm), false);
                    EpargneProgramTransfertPage.cntBtns.setHidden(true);
                    EpargneProgramTransfertPage.this.uiManager.btnBack.getListeners().clear();
                    EpargneProgramTransfertPage.this.uiManager.btnBack.addActionListener(new 1());
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(EpargneProgramTransfertPage.this.uiManager.stateMachine, DataTools.FormatUnicode(AddOvpSimulation.getStatusLabel()), null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(EpargneProgramTransfertPage.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                EpargneProgramTransfertPage.cntTransfertForm.replace((Container) EpargneProgramTransfertPage.cntTransfertForm.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 0));
                EpargneProgramTransfertPage.cntTransfertForm.revalidate();
                EpargneProgramTransfertPage.cntBtns.setHidden(false);
                EpargneProgramTransfertPage.this.uiManager.btnBack.getListeners().clear();
                EpargneProgramTransfertPage.this.uiManager.btnBack.addActionListener(new 1());
            }

            class 1 implements ActionListener {
                1() {
                }

                public void actionPerformed(ActionEvent actionEvent) {
                    EpargneProgramTransfertPage.this.uiManager.GoBack();
                }
            }
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Calendar val$calendar;
        final /* synthetic */ SimpleDateFormat val$sdfa;

        3(Calendar calendar, SimpleDateFormat simpleDateFormat) {
            this.val$calendar = calendar;
            this.val$sdfa = simpleDateFormat;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.add(5, 1);
                Date parse = simpleDateFormat.parse(EpargneProgramTransfertPage.this.df.format(EpargneProgramTransfertPage.this.dateExcPicker.getDate()));
                Date parse2 = simpleDateFormat.parse(EpargneProgramTransfertPage.this.df.format(calendar.getTime()));
                Date parse3 = simpleDateFormat.parse(EpargneProgramTransfertPage.this.df.format(EpargneProgramTransfertPage.this.dateFinPicker.getDate()));
                if (parse.getTime() - parse2.getTime() < 0) {
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.add(5, 1);
                    EpargneProgramTransfertPage.this.dateExcPicker.setText(this.val$sdfa.format(calendar2.getTime()));
                } else if (parse3.getTime() - parse.getTime() < 0) {
                    EpargneProgramTransfertPage.this.dateFinPicker.setDate(EpargneProgramTransfertPage.this.dateExcPicker.getDate());
                    EpargneProgramTransfertPage.this.dateExcPicker.setText(this.val$sdfa.format(EpargneProgramTransfertPage.this.dateExcPicker.getDate()));
                    EpargneProgramTransfertPage.this.dateFinPicker.setText(this.val$sdfa.format(EpargneProgramTransfertPage.this.dateExcPicker.getDate()));
                } else {
                    EpargneProgramTransfertPage.this.dateExcPicker.setText(this.val$sdfa.format(EpargneProgramTransfertPage.this.dateExcPicker.getDate()));
                }
            } catch (ParseException unused) {
            }
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ SimpleDateFormat val$sdfa;

        4(SimpleDateFormat simpleDateFormat) {
            this.val$sdfa = simpleDateFormat;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                if (simpleDateFormat.parse(EpargneProgramTransfertPage.this.df.format(EpargneProgramTransfertPage.this.dateFinPicker.getDate())).getTime() - simpleDateFormat.parse(EpargneProgramTransfertPage.this.df.format(EpargneProgramTransfertPage.this.dateExcPicker.getDate())).getTime() < 0) {
                    Calendar.getInstance().add(1, 1);
                    EpargneProgramTransfertPage.this.dateFinPicker.setDate(EpargneProgramTransfertPage.this.dateExcPicker.getDate());
                    EpargneProgramTransfertPage.this.dateFinPicker.setText(this.val$sdfa.format(EpargneProgramTransfertPage.this.dateExcPicker.getDate()));
                } else {
                    EpargneProgramTransfertPage.this.dateFinPicker.setText(this.val$sdfa.format(EpargneProgramTransfertPage.this.dateFinPicker.getDate()));
                }
            } catch (ParseException unused) {
            }
        }
    }

    class 5 implements ActionListener {
        final /* synthetic */ Container val$DateFinCnt;
        final /* synthetic */ Container val$HidCnt;
        final /* synthetic */ Button val$RememberBtn;

        5(Button button, Container container, Container container2) {
            this.val$RememberBtn = button;
            this.val$HidCnt = container;
            this.val$DateFinCnt = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$RememberBtn.getIcon().equals(CihMBanking.theme.getImage("RememberOff.png"))) {
                this.val$RememberBtn.setIcon(CihMBanking.theme.getImage("RememberOn.png"));
                this.val$HidCnt.setHidden(false);
                this.val$DateFinCnt.revalidate();
            } else {
                this.val$RememberBtn.setIcon(CihMBanking.theme.getImage("RememberOff.png"));
                this.val$HidCnt.setHidden(true);
                this.val$DateFinCnt.revalidate();
            }
        }
    }

    public TransactionResume GetTransaction(Container container, TextArea textArea) {
        TransactionResume transactionResume = new TransactionResume();
        transactionResume.Amount = textArea.getText();
        transactionResume.Motif = "Epargne";
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
        Container container3 = (Container) ((Container) ((Container) ((Container) ((Container) BENEFCNT.getComponentAt(0)).getComponentAt(2)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
        transactionResume.CreditedAccountAlias = ((Label) container3.getComponentAt(0)).getText();
        transactionResume.CreditedAccountNumber = StringTools.RibFormatWithoutSP(((Label) container3.getComponentAt(1)).getText());
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

    class 6 implements DataChangedListener {
        public void dataChanged(int i, int i2) {
        }

        6() {
        }
    }

    public void HidePassWordOnKeyPressed(TextField textField) {
        textField.addDataChangeListener(new 6());
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
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 7(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 8(b3GCIHEcode));
        return createContainer;
    }

    class 7 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        7(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 0));
            EpargneProgramTransfertPage.cntBtns.setHidden(false);
            EpargneProgramTransfertPage.this.uiManager.btnBack.getListeners().clear();
            EpargneProgramTransfertPage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                EpargneProgramTransfertPage.cntBtns.setHidden(false);
                EpargneProgramTransfertPage.isOtpPageShow = false;
                EpargneProgramTransfertPage.this.uiManager.GoBack();
            }
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        8(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
            EpargneProgramTransfertPage.access$002(EpargneProgramTransfertPage.this, this.val$eCode1.getValue());
            sessionCurrentTransactionResume.PassHB = EpargneProgramTransfertPage.access$000(EpargneProgramTransfertPage.this);
            sessionCurrentTransactionResume.CINHB = " ";
            if (EpargneProgramTransfertPage.access$000(EpargneProgramTransfertPage.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(EpargneProgramTransfertPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            try {
                if (EpargneProgramTransfertPage.access$100(EpargneProgramTransfertPage.this, sessionCurrentTransactionResume).getStatusCode().equals("000")) {
                    EpargneProgramTransfertPage epargneProgramTransfertPage = EpargneProgramTransfertPage.this;
                    epargneProgramTransfertPage.ShowMessageTransaction(epargneProgramTransfertPage.uiManager.stateMachine, "Opération effectuée avec succès", null);
                } else {
                    EpargneProgramTransfertPage epargneProgramTransfertPage2 = EpargneProgramTransfertPage.this;
                    epargneProgramTransfertPage2.ShowMessageTransaction(epargneProgramTransfertPage2.uiManager.stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
                }
            } catch (Exception unused) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(EpargneProgramTransfertPage.this.uiManager.stateMachine, DataTools.FormatUnicode("Une erreur est survenue, Veuillez réessayer plus tard."), null);
            }
        }
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
        if (Display.getInstance().getPlatformName().equals("ios")) {
            this.txtHBPassWord.startEditingAsync();
        }
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
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    Beneficiary beneficiary = (Beneficiary) it.next();
                    if (beneficiary.typeBenef.equals("300013") && beneficiary.cardNumber.substring(13, 17).equals("2310")) {
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
            if (account.canDebit.equals("True") && account.eligibleService.contains("300013")) {
                arrayList2.add(account);
            }
        }
        return arrayList2;
    }

    public Container DrawAcquiredAcc(String str, String str2) {
        Container GetContainerNw = this.uiManager.GetContainerNw("GloabalContainerV2Epargne", "Compte à créditer");
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AccountItemBenefTransfertV2");
        this.uiManager.stateMachine.findLblAccountOwner(createContainer).setText(str);
        this.uiManager.stateMachine.findLblAccountRibValue(createContainer).setText(StringTools.RibFormatWithSP(str2));
        GetContainerNw.addComponent(createContainer);
        return GetContainerNw;
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

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:3:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.b3g.common.ServiceResponse AddOvpSimulation(com.b3g.services.TransactionResume r9) {
        /*
            Method dump skipped, instructions count: 348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.EpargneProgramTransfertPage.AddOvpSimulation(com.b3g.services.TransactionResume):com.b3g.common.ServiceResponse");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:6:0x00b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.b3g.common.ServiceResponse OvpConfirmation(com.b3g.services.TransactionResume r11) {
        /*
            Method dump skipped, instructions count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.EpargneProgramTransfertPage.OvpConfirmation(com.b3g.services.TransactionResume):com.b3g.common.ServiceResponse");
    }

    public Container GetTransferSecurityForm(Container container, Beneficiary beneficiary, String str) {
        Container container2 = new Container(BoxLayout.y());
        Label label = new Label("Demande de crédit");
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        label.getAllStyles().setMargin(0, 0, 0, 0);
        container2.add(label);
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransferFormTab3");
        Container container3 = (Container) this.uib.findByName("CntPassValue", createContainer);
        Container container4 = (Container) this.uib.findByName("CntPassOut", createContainer);
        ((Container) this.uib.findByName("Container", createContainer)).setUIID("");
        container4.removeAll();
        createContainer.removeComponent(this.uiManager.stateMachine.findCntBorder12(createContainer));
        container4.setLayout(BoxLayout.y());
        Container createContainer2 = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "PasswordText");
        this.txtHBPassWord = (TextField) this.uib.findByName("PassTxt", createContainer2);
        container4.add(createContainer2);
        container3.setEnabled(true);
        container4.setEnabled(true);
        this.txtHBPassWord.setEnabled(true);
        container3.setFocusable(false);
        container4.setFocusable(false);
        this.txtHBPassWord.setFocusable(true);
        SessionParameter.otpTextField = this.txtHBPassWord;
        this.uiManager.stateMachine.findCntValidateTransfer(createContainer).removeAll();
        Button button = new Button("Valider");
        button.getAllStyles().setAlignment(4);
        button.getAllStyles().setFgColor(16777215);
        button.getAllStyles().setBgColor(15818018);
        button.getAllStyles().setBgTransparency(255);
        button.getAllStyles().setPaddingUnit(1);
        button.getAllStyles().setPadding(1.5f, 1.5f, 1.0f, 1.0f);
        button.getAllStyles().setMarginUnit(1);
        button.getAllStyles().setMargin(2, 2, 0, 0);
        button.getAllStyles().setFont(Font.createTrueTypeFont("Arvo-Bold", "Arvo-Bold.ttf").derive(Display.getInstance().convertToPixels(2.5f), 0));
        this.uiManager.stateMachine.findCntValidateTransfer(createContainer).setLayout(BoxLayout.y());
        this.uiManager.stateMachine.findCntValidateTransfer(createContainer).add(button);
        button.addActionListener(new 9(createContainer2));
        button.setFocusable(true);
        button.setEnabled(true);
        this.uiManager.stateMachine.findCntKeyBoard(createContainer).revalidate();
        this.uiManager.stateMachine.findLblMotifTitle1(createContainer).setUIID("g_lblNotifOrange");
        this.uiManager.stateMachine.findIvrBtn(createContainer).addActionListener(new 10());
        container2.add(createContainer);
        this.uiManager.stateMachine.findGsmMsgSP(createContainer).setText(DataTools.getEncryptedPhone(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm));
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(1, 1, 2, 2);
        return container2;
    }

    class 9 implements ActionListener {
        final /* synthetic */ Container val$cntPassForm;

        9(Container container) {
            this.val$cntPassForm = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if (EpargneProgramTransfertPage.this.uiManager.stateMachine.findPassTxt(this.val$cntPassForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(EpargneProgramTransfertPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                } else if (EpargneProgramTransfertPage.access$100(EpargneProgramTransfertPage.this, CihMBanking.sesPMTR.getSessionCurrentTransactionResume()).getStatusCode().equals("000")) {
                    EpargneProgramTransfertPage.this.uiManager.NavigateToPageById(129);
                } else {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(EpargneProgramTransfertPage.this.uiManager.stateMachine, DataTools.FormatUnicode("Une erreur est survenue, Veuillez réessayer plus tard."), null);
                }
            } catch (Exception unused) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(EpargneProgramTransfertPage.this.uiManager.stateMachine, DataTools.FormatUnicode("Une erreur est survenue, Veuillez réessayer plus tard."), null);
            }
        }
    }

    class 10 implements ActionListener {
        10() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ServiceManager.sendOtpProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "", 900064, "0", "1", "", "", "", "");
        }
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
        picker.setSelectedString("Hebdomadaire");
        picker.setAlignment(1);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("newListBox.png"));
        container.add("Center", picker).add("East", label);
        container.setLeadComponent(picker);
        return container;
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
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 11(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 11 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        11(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            EpargneProgramTransfertPage.this.uiManager.NavigateToPageById(46);
        }
    }

    public void AccountProcess() {
        new ArrayList();
        Account account = new Account();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setServiceId(11);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setParamsIn(hashtable);
        ArrayList FillAccountArrayDataFromServiceResponse = account.FillAccountArrayDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
        CihMBanking.sesPMTR.getSessionClient().setClient_AccountList(FillAccountArrayDataFromServiceResponse);
        if (FillAccountArrayDataFromServiceResponse.size() == 1) {
            CihMBanking.sesPMTR.setCurrentAccount((Account) FillAccountArrayDataFromServiceResponse.get(0));
        }
    }
}
