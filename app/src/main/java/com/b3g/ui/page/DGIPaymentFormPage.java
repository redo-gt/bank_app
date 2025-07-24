package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.MTCConfirmationResume;
import com.b3g.services.ReleveDgi;
import com.b3g.services.ServiceManager;
import com.b3g.services.YearsVignette;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GRadio;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.RateUs;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DGIPaymentFormPage extends B3GPage {
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    private Container confirmCnt;
    private Container formCnt;
    private Container getReleveCnt;
    private String mail;
    private String phoneNumber;
    private ReleveDgi releve;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();

    private String getMatriculeParam(int i) {
        String str = i == 1 ? "B" : "A";
        if (i == 2) {
            str = "D";
        }
        if (i == 3) {
            str = "H";
        }
        if (i == 4) {
            str = "E";
        }
        if (i == 5) {
            str = "T";
        }
        if (i == 6) {
            str = "Y";
        }
        if (i == 7) {
            str = "K";
        }
        if (i == 8) {
            str = "L";
        }
        if (i == 9) {
            str = "M";
        }
        if (i == 10) {
            str = "N";
        }
        if (i == 11) {
            str = "C";
        }
        if (i == 12) {
            str = "F";
        }
        if (i == 13) {
            str = "R";
        }
        if (i == 14) {
            str = "S";
        }
        return i == 15 ? "RT" : str;
    }

    static /* synthetic */ String access$000(DGIPaymentFormPage dGIPaymentFormPage, int i) {
        return dGIPaymentFormPage.getMatriculeParam(i);
    }

    static /* synthetic */ Container access$100(DGIPaymentFormPage dGIPaymentFormPage, ReleveDgi releveDgi, Container container) {
        return dGIPaymentFormPage.getDGIReleveCnt(releveDgi, container);
    }

    static /* synthetic */ Container access$200(DGIPaymentFormPage dGIPaymentFormPage) {
        return dGIPaymentFormPage.formCnt;
    }

    static /* synthetic */ Container access$300(DGIPaymentFormPage dGIPaymentFormPage) {
        return dGIPaymentFormPage.confirmCnt;
    }

    static /* synthetic */ String access$400(DGIPaymentFormPage dGIPaymentFormPage) {
        return dGIPaymentFormPage.VnewaliasText;
    }

    static /* synthetic */ String access$402(DGIPaymentFormPage dGIPaymentFormPage, String str) {
        dGIPaymentFormPage.VnewaliasText = str;
        return str;
    }

    static /* synthetic */ Container access$500(DGIPaymentFormPage dGIPaymentFormPage) {
        return dGIPaymentFormPage.getReleveCnt;
    }

    static /* synthetic */ ReleveDgi access$600(DGIPaymentFormPage dGIPaymentFormPage) {
        return dGIPaymentFormPage.releve;
    }

    public DGIPaymentFormPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            container.addComponent(getDGIFormCnt());
            this.thisContainer = container;
            Settings.setNightMode(this.thisContainer, 0);
            return container;
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
    }

    private Container getDGIFormCnt() {
        int indexOf;
        YearsVignette yearsVignette = new YearsVignette();
        if (CihMBanking.sesPMTR.YerasList == null) {
            CihMBanking.sesPMTR.YerasList = yearsVignette.DgiYearsToDisplayProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        }
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "DGIPaymentCnt");
        B3GRadio b3GRadio = new B3GRadio("TypeCarteGrise");
        b3GRadio.addItem("Electronique");
        b3GRadio.addItem("Cartonnée");
        Container findTypeCartegriseCnt = this.uiManager.stateMachine.findTypeCartegriseCnt(createContainer);
        this.uiManager.stateMachine.findEmailValue(createContainer).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email);
        this.uiManager.stateMachine.findPhoneValue(createContainer).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm);
        ReleveDgi releveDgi = (ReleveDgi) this.service;
        if (releveDgi != null) {
            if (releveDgi.matricule.indexOf("A") > -1) {
                indexOf = releveDgi.matricule.indexOf("A");
            } else if (releveDgi.matricule.indexOf("B") > -1) {
                indexOf = releveDgi.matricule.indexOf("B");
            } else if (releveDgi.matricule.indexOf("D") > -1) {
                indexOf = releveDgi.matricule.indexOf("D");
            } else if (releveDgi.matricule.indexOf("H") > -1) {
                indexOf = releveDgi.matricule.indexOf("H");
            } else if (releveDgi.matricule.indexOf("E") > -1) {
                indexOf = releveDgi.matricule.indexOf("E");
            } else if (releveDgi.matricule.indexOf("T") > -1) {
                indexOf = releveDgi.matricule.indexOf("T");
            } else if (releveDgi.matricule.indexOf("Y") > -1) {
                indexOf = releveDgi.matricule.indexOf("Y");
            } else if (releveDgi.matricule.indexOf("K") > -1) {
                indexOf = releveDgi.matricule.indexOf("K");
            } else if (releveDgi.matricule.indexOf("L") > -1) {
                indexOf = releveDgi.matricule.indexOf("L");
            } else if (releveDgi.matricule.indexOf("M") > -1) {
                indexOf = releveDgi.matricule.indexOf("M");
            } else if (releveDgi.matricule.indexOf("N") > -1) {
                indexOf = releveDgi.matricule.indexOf("N");
            } else if (releveDgi.matricule.indexOf("C") > -1) {
                indexOf = releveDgi.matricule.indexOf("C");
            } else if (releveDgi.matricule.indexOf("F") > -1) {
                indexOf = releveDgi.matricule.indexOf("F");
            } else if (releveDgi.matricule.indexOf("R") > -1) {
                indexOf = releveDgi.matricule.indexOf("R");
            } else {
                indexOf = releveDgi.matricule.indexOf("S") > -1 ? releveDgi.matricule.indexOf("S") : 0;
            }
            String substring = releveDgi.matricule.substring(0, indexOf);
            int i = indexOf + 1;
            String substring2 = releveDgi.matricule.substring(indexOf, i);
            String substring3 = releveDgi.matricule.substring(i, releveDgi.matricule.length());
            this.uiManager.stateMachine.findMatriculeValue1(createContainer).setText(substring);
            this.uiManager.stateMachine.findMatriculeValue2(createContainer).setSelectedIndex(getMatriculeindex(substring2));
            this.uiManager.stateMachine.findMatriculeValue3(createContainer).setText(substring3);
            this.uiManager.stateMachine.findPuissanceFiscalValue(createContainer).setText(releveDgi.puisanceFiscal);
            this.uiManager.stateMachine.findCarbTypeCombo(createContainer).setSelectedIndex(getCarburantTypeindex(releveDgi.carburantType));
        }
        for (int i2 = 0; i2 < CihMBanking.sesPMTR.YerasList.size(); i2++) {
            this.uiManager.stateMachine.findYearCombo2(createContainer).addItem(((YearsVignette) CihMBanking.sesPMTR.YerasList.get(i2)).year);
            if (((YearsVignette) CihMBanking.sesPMTR.YerasList.get(i2)).isDefault.equals("true")) {
                this.uiManager.stateMachine.findYearCombo2(createContainer).setSelectedItem(((YearsVignette) CihMBanking.sesPMTR.YerasList.get(i2)).year);
            }
        }
        findTypeCartegriseCnt.addComponent(b3GRadio.GetContainer());
        if (Display.getInstance().getPlatformName().equals("ios")) {
            createContainer.setScrollableY(false);
        }
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 1(createContainer, b3GRadio));
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 2());
        createContainer.setScrollVisible(false);
        this.formCnt = createContainer;
        return createContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ B3GRadio val$cardTypeRadio;
        final /* synthetic */ Container val$cntDgiForm;

        1(Container container, B3GRadio b3GRadio) {
            this.val$cntDgiForm = container;
            this.val$cardTypeRadio = b3GRadio;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (DGIPaymentFormPage.this.uiManager.stateMachine.findMatriculeValue1(this.val$cntDgiForm).getText().length() == 0 || DGIPaymentFormPage.this.uiManager.stateMachine.findMatriculeValue3(this.val$cntDgiForm).getText().length() == 0 || DGIPaymentFormPage.this.uiManager.stateMachine.findPuissanceFiscalValue(this.val$cntDgiForm).getText().length() == 0 || DGIPaymentFormPage.this.uiManager.stateMachine.findEmailValue(this.val$cntDgiForm).getText().length() == 0 || DGIPaymentFormPage.this.uiManager.stateMachine.findPhoneValue(this.val$cntDgiForm).getText().length() == 0) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(DGIPaymentFormPage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                    return;
                }
                DGIPaymentFormPage dGIPaymentFormPage = DGIPaymentFormPage.this;
                String GetSelectedText = this.val$cardTypeRadio.GetSelectedText();
                StringBuilder append = new StringBuilder().append(DGIPaymentFormPage.this.uiManager.stateMachine.findMatriculeValue1(this.val$cntDgiForm).getText());
                DGIPaymentFormPage dGIPaymentFormPage2 = DGIPaymentFormPage.this;
                ServiceResponse DgiSendDataProcess = dGIPaymentFormPage.DgiSendDataProcess(GetSelectedText, append.append(DGIPaymentFormPage.access$000(dGIPaymentFormPage2, dGIPaymentFormPage2.uiManager.stateMachine.findMatriculeValue2(this.val$cntDgiForm).getSelectedIndex())).append(DGIPaymentFormPage.this.uiManager.stateMachine.findMatriculeValue3(this.val$cntDgiForm).getText()).toString(), DGIPaymentFormPage.this.uiManager.stateMachine.findPuissanceFiscalValue(this.val$cntDgiForm).getText(), DGIPaymentFormPage.this.uiManager.stateMachine.findCarbTypeCombo(this.val$cntDgiForm).getSelectedItem().toString(), DGIPaymentFormPage.this.uiManager.stateMachine.findEmailValue(this.val$cntDgiForm).getText(), DGIPaymentFormPage.this.uiManager.stateMachine.findPhoneValue(this.val$cntDgiForm).getText(), DGIPaymentFormPage.this.uiManager.stateMachine.findYearCombo2(this.val$cntDgiForm).getSelectedItem().toString());
                if (DgiSendDataProcess.getStatusCode().equals("000")) {
                    new RateUs(DGIPaymentFormPage.this.uiManager).init();
                    ReleveDgi releveDgi = (ReleveDgi) DGIPaymentFormPage.this.getDGiReleve(DgiSendDataProcess).get(0);
                    DGIPaymentFormPage dGIPaymentFormPage3 = DGIPaymentFormPage.this;
                    Container container = dGIPaymentFormPage3.thisContainer;
                    Container container2 = this.val$cntDgiForm;
                    DGIPaymentFormPage dGIPaymentFormPage4 = DGIPaymentFormPage.this;
                    dGIPaymentFormPage3.SwitchTransfertForms(container, container2, DGIPaymentFormPage.access$100(dGIPaymentFormPage4, releveDgi, dGIPaymentFormPage4.thisContainer), false);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(DGIPaymentFormPage.this.uiManager.stateMachine, DgiSendDataProcess.getStatusLabel(), null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(DGIPaymentFormPage.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démonstration ", null);
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DGIPaymentFormPage.this.uiManager.GoBack();
        }
    }

    private Container getDGIReleveCnt(ReleveDgi releveDgi, Container container) {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "DGIInpaid");
        this.uiManager.stateMachine.findMatriculeLabel(createContainer).setText(releveDgi.matricule);
        this.uiManager.stateMachine.findPFLabel(createContainer).setText(releveDgi.puisanceFiscal);
        this.uiManager.stateMachine.findCarburantLabel(createContainer).setText(releveDgi.carburantType);
        this.uiManager.stateMachine.findMarqueLabel(createContainer).setText(releveDgi.marqueVehicule);
        this.uiManager.stateMachine.findAnneeLabel(createContainer).setText(releveDgi.annee);
        this.uiManager.stateMachine.findMontantLabel(createContainer).setText(releveDgi.montantVignette);
        this.uiManager.stateMachine.findPenaliteLabel(createContainer).setText(releveDgi.montantPenalite);
        this.uiManager.stateMachine.findMajorationLabel(createContainer).setText(releveDgi.majorationRetard);
        this.uiManager.stateMachine.findTotaltaxLabel(createContainer).setText(releveDgi.totalTaxe);
        this.uiManager.stateMachine.findFraisServicesLabel(createContainer).setText(releveDgi.fraisServices);
        this.uiManager.stateMachine.findMontantTimbreLabel(createContainer).setText(releveDgi.montantTimbre);
        this.uiManager.stateMachine.findTotalTTCLabel(createContainer).setText(releveDgi.totalTTC);
        this.uiManager.stateMachine.findTVALabel(createContainer).setText(releveDgi.tva);
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 3(container, createContainer, releveDgi));
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 4(container, createContainer));
        this.getReleveCnt = createContainer;
        return createContainer;
    }

    class 3 implements ActionListener {
        final /* synthetic */ Container val$cntDgiForm;
        final /* synthetic */ Container val$parentCnt;
        final /* synthetic */ ReleveDgi val$releve;

        3(Container container, Container container2, ReleveDgi releveDgi) {
            this.val$parentCnt = container;
            this.val$cntDgiForm = container2;
            this.val$releve = releveDgi;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DGIPaymentFormPage dGIPaymentFormPage = DGIPaymentFormPage.this;
            dGIPaymentFormPage.SwitchTransfertForms(this.val$parentCnt, this.val$cntDgiForm, dGIPaymentFormPage.DGIConfirmation(this.val$releve.totalTTC, this.val$parentCnt), false);
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Container val$cntDgiForm;
        final /* synthetic */ Container val$parentCnt;

        4(Container container, Container container2) {
            this.val$parentCnt = container;
            this.val$cntDgiForm = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DGIPaymentFormPage dGIPaymentFormPage = DGIPaymentFormPage.this;
            dGIPaymentFormPage.SwitchTransfertForms(this.val$parentCnt, this.val$cntDgiForm, DGIPaymentFormPage.access$200(dGIPaymentFormPage), false);
        }
    }

    public ServiceResponse DgiSendDataProcess(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("MATRICULE", str2);
        hashtable.put("PUISSANCEFISCAL", str3);
        hashtable.put("CARBURANT", str4);
        hashtable.put("MAIL", str5);
        hashtable.put("PHONE", str6);
        hashtable.put("YEAR", str7);
        hashtable.put("CARTETYPE", str);
        hashtable.put("IsPerso", CihMBanking.sesPMTR.IsPerso);
        this.phoneNumber = str6;
        this.mail = str5;
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900133);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public Container GetTransferSecurityForm(Container container, String str) {
        Container container2 = new Container(BoxLayout.y());
        Container container3 = new Container(BoxLayout.y());
        Label label = new Label("Service sélectionné : ");
        label.setUIID("g_lblDashBoardTitleOrange");
        Label label2 = new Label("Paiement vignette ");
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
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 5(container, container2));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 6(b3GCIHEcode));
        container2.add(createContainer);
        return container2;
    }

    class 5 implements ActionListener {
        final /* synthetic */ Container val$Parent;
        final /* synthetic */ Container val$c;

        5(Container container, Container container2) {
            this.val$Parent = container;
            this.val$c = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DGIPaymentFormPage dGIPaymentFormPage = DGIPaymentFormPage.this;
            dGIPaymentFormPage.SwitchTransfertForms(this.val$Parent, this.val$c, DGIPaymentFormPage.access$300(dGIPaymentFormPage), false);
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        6(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DGIPaymentFormPage.access$402(DGIPaymentFormPage.this, this.val$eCode1.getValue());
            String access$400 = DGIPaymentFormPage.access$400(DGIPaymentFormPage.this);
            if (DGIPaymentFormPage.access$400(DGIPaymentFormPage.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(DGIPaymentFormPage.this.uiManager.stateMachine, "Veuillez saisir le mot de passe", null);
            } else {
                new UITimer(new DGIPaymentFormPage$6$$ExternalSyntheticLambda0(this, access$400)).schedule(500, false, DGIPaymentFormPage.this.uiManager.currentForm);
            }
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-DGIPaymentFormPage$6(String str) {
            DGIPaymentFormPage.this.ShowDialog(73, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, str, 11);
        }
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
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

    ArrayList getDGiReleve(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
            ReleveDgi releveDgi = new ReleveDgi();
            releveDgi.FillDataFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i)));
            arrayList.add(releveDgi);
        }
        this.releve = (ReleveDgi) arrayList.get(0);
        return arrayList;
    }

    public Container DGIConfirmation(String str, Container container) {
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("Container");
        container2.setFocusable(false);
        container2.setScrollVisible(false);
        Container container3 = new Container(new BoxLayout(2));
        container3.setUIID("Container");
        container3.setFocusable(false);
        container3.setScrollVisible(false);
        Container DrawListContainer = this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getAccountToDebit(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList()), 1, 16, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, null, null, null);
        GetSelectedAccount(DrawListContainer);
        container3.addComponent(DrawListContainer);
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container4 = new Container();
        container4.setLayout(tableLayout);
        container4.setUIID("Container");
        container4.setFocusable(false);
        container4.setScrollable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(50);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(3);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container5 = new Container();
        container5.setLayout(flowLayout);
        container5.setUIID("Container");
        Label label = new Label("Montant Total : ");
        label.setUIID("g_lblNotifM");
        label.setFocusable(false);
        label.setScrollVisible(false);
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        container5.addComponent(label);
        container4.addComponent(createConstraint, container5);
        Object createConstraint2 = tableLayout.createConstraint();
        FlowLayout flowLayout2 = new FlowLayout();
        flowLayout2.setAlign(1);
        flowLayout2.setValign(4);
        flowLayout2.setValignByRow(true);
        Container container6 = new Container();
        container6.setLayout(flowLayout2);
        container6.setUIID("Container");
        Label label2 = new Label(str);
        label2.setUIID("g_lblNotifGrenM");
        label2.setFocusable(false);
        label2.setScrollVisible(false);
        label2.setTextPosition(1);
        label2.setVerticalAlignment(4);
        container6.addComponent(label2);
        container4.addComponent(createConstraint2, container6);
        container4.revalidate();
        Object createConstraint3 = tableLayout.createConstraint();
        FlowLayout flowLayout3 = new FlowLayout();
        flowLayout3.setAlign(1);
        flowLayout3.setValign(4);
        flowLayout3.setValignByRow(true);
        Container container7 = new Container();
        container7.setLayout(flowLayout3);
        container7.setUIID("Container");
        Label label3 = new Label(" (DH)");
        label3.setUIID("g_lblNotifM");
        label3.setFocusable(false);
        label3.setScrollVisible(false);
        label3.setTextPosition(1);
        label3.setVerticalAlignment(4);
        container7.addComponent(label3);
        container4.addComponent(createConstraint3, container7);
        container4.revalidate();
        container2.addComponent(container3);
        container2.addComponent(container4);
        container2.addComponent(DrawBorderSep());
        container2.addComponent(DrawDGIConfirmationBtn(DrawListContainer, str, container2, container));
        CihMBanking.sesPMTR.setSessionSavedContainer(container2);
        this.confirmCnt = container2;
        return container2;
    }

    ArrayList getAccountToDebit(ArrayList arrayList) {
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

    public Container DrawDGIConfirmationBtn(Container container, String str, Container container2, Container container3) {
        GridLayout gridLayout = new GridLayout(1, 2);
        Container container4 = new Container();
        container4.setLayout(gridLayout);
        container4.setFocusable(false);
        container4.setScrollVisible(false);
        Button button = new Button("PRECEDENT");
        button.setUIID("op_BtnOppositionValidationMarginRight");
        button.setFocusable(false);
        button.setScrollVisible(false);
        button.setVerticalAlignment(4);
        button.setTextPosition(1);
        button.addActionListener(new 7(container3, container2));
        container4.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("op_BtnOppositionValidationMarginLeft");
        button2.setFocusable(false);
        button2.setScrollVisible(false);
        button2.setVerticalAlignment(4);
        button2.setTextPosition(1);
        button2.addActionListener(new 8(container, str, container3, container2));
        container4.addComponent(button2);
        return container4;
    }

    class 7 implements ActionListener {
        final /* synthetic */ Container val$currentCnt;
        final /* synthetic */ Container val$parentCnt;

        7(Container container, Container container2) {
            this.val$parentCnt = container;
            this.val$currentCnt = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DGIPaymentFormPage dGIPaymentFormPage = DGIPaymentFormPage.this;
            dGIPaymentFormPage.SwitchTransfertForms(this.val$parentCnt, this.val$currentCnt, DGIPaymentFormPage.access$500(dGIPaymentFormPage), false);
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ Container val$AccountListContainer;
        final /* synthetic */ String val$TotalAmount;
        final /* synthetic */ Container val$currentCnt;
        final /* synthetic */ Container val$parentCnt;

        8(Container container, String str, Container container2, Container container3) {
            this.val$AccountListContainer = container;
            this.val$TotalAmount = str;
            this.val$parentCnt = container2;
            this.val$currentCnt = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String GetSelectedAccount = DGIPaymentFormPage.this.GetSelectedAccount(this.val$AccountListContainer);
            ServiceResponse sendOtpProcess = ServiceManager.sendOtpProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, "1006", 300016, "1", "0", GetSelectedAccount, "", this.val$TotalAmount, "");
            if (sendOtpProcess.getStatusCode().equals("000")) {
                DGIPaymentFormPage.access$600(DGIPaymentFormPage.this).accountNumber = GetSelectedAccount;
                DGIPaymentFormPage dGIPaymentFormPage = DGIPaymentFormPage.this;
                Container container = this.val$parentCnt;
                dGIPaymentFormPage.SwitchTransfertForms(container, this.val$currentCnt, dGIPaymentFormPage.GetTransferSecurityForm(container, GetSelectedAccount), false);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(DGIPaymentFormPage.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcess.getStatusLabel()), null);
        }
    }

    public Container DrawBorderSep() {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("g_cntBorderV2");
        container.setFocusable(false);
        container.setScrollable(false);
        return container;
    }

    public ServiceResponse payVignetteProcess(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CARD_PROGRAM_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().cardProgramId);
        hashtable.put("NAME", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom);
        hashtable.put("ACCOUNT_NUMBER", str);
        hashtable.put("PASS", str2);
        hashtable.put("PASSID", str2);
        hashtable.put("MATRICULE", this.releve.matricule);
        hashtable.put("PUISSANCEFISCAL", this.releve.puisanceFiscal);
        this.releve.carbTypeToSend();
        hashtable.put("CARBURANT", this.releve.carburantType);
        hashtable.put("MAIL", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email);
        hashtable.put("PHONE", this.phoneNumber);
        hashtable.put("YEAR", this.releve.annee);
        hashtable.put("MONTANT_PENALITE", this.releve.montantPenalite);
        hashtable.put("MONTANT_TIMBRE", this.releve.montantTimbre);
        hashtable.put("MONTANT_VIGNETTE", this.releve.montantVignette);
        hashtable.put("TOTAL_TTC", this.releve.totalTTC);
        hashtable.put("TOTAL_TAX", "0");
        hashtable.put("FRAIS_SERVICE", this.releve.fraisServices);
        hashtable.put("MAJORATION_RETARD", this.releve.majorationRetard);
        hashtable.put("MARQUE", this.releve.marqueVehicule);
        hashtable.put("AUDIT_NUMBER", this.releve.refNumber);
        hashtable.put("REFERENCE_NUMBER", this.releve.auditNumber);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900134);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public String GetSelectedAccount(Container container) {
        MTCConfirmationResume mTCConfirmationResume = new MTCConfirmationResume();
        Container container2 = (Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0);
        mTCConfirmationResume.Owner = ((Label) ((Container) ((Container) ((Container) container2.getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getText();
        return ((Account) ((B3gService) container2.getClientProperty("AccountClient"))).rib;
    }

    public void UpdateAccountBalance(String str, String str2) {
        try {
            Double StringToDouble = DataTools.StringToDouble(str2);
            Iterator it = CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().iterator();
            while (it.hasNext()) {
                Account account = (Account) it.next();
                if (account.rib.equals(str)) {
                    Double valueOf = Double.valueOf(DataTools.round(Double.valueOf(DataTools.StringToDouble(account.balanceBrut).doubleValue() - StringToDouble.doubleValue())));
                    account.balanceBrut = valueOf.toString();
                    account.balance = DataTools.FormatAmountWithCurrency(valueOf.toString(), account.devise);
                }
            }
        } catch (Exception unused) {
        }
    }

    public void ShowDialog(int i, StateMachine stateMachine, String str, int i2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = stateMachine.uiManager.GetPopupItemDetail(i, this.releve, stateMachine, open, createContainer, null, null);
            for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetDGIPopupBtn(dialog, i, str, i2, stateMachine));
            stateMachine.findLblpopupItemTitleV2(createContainer).setText("Paiement de la Vignette");
            stateMachine.findLblpopupItemValueV2(createContainer).setText(this.releve.auditNumber);
            createContainer.setPreferredH(CihMBanking.sesPMTR.displayHeight);
            createContainer.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(false);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(255);
            dialog.getDialogStyle().setBgColor(1118481);
            dialog.getStyle().setPadding(0, 0, 0, 0);
            dialog.getStyle().setMargin(0, 0, 0, 0);
            dialog.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog.revalidate();
            dialog.addComponent("Center", createContainer);
            dialog.show(0, 0, 0, 0);
        } catch (IOException unused) {
        }
    }

    public Container GetDGIPopupBtn(Dialog dialog, int i, String str, int i2, StateMachine stateMachine) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(stateMachine.uiManager.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 9(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(stateMachine.uiManager.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 10(dialog, str));
        container.addComponent(button2);
        return container;
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

    class 10 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ String val$pass;

        10(Dialog dialog, String str) {
            this.val$d = dialog;
            this.val$pass = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            DGIPaymentFormPage dGIPaymentFormPage = DGIPaymentFormPage.this;
            ServiceResponse payVignetteProcess = dGIPaymentFormPage.payVignetteProcess(DGIPaymentFormPage.access$600(dGIPaymentFormPage).accountNumber, this.val$pass);
            if (payVignetteProcess.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(DGIPaymentFormPage.this.uiManager.stateMachine, payVignetteProcess.getStatusLabel() + " Merci de consulter votre boite e-mail pour récuperer votre reçu.", null);
                Display.getInstance().callSerially(new 1());
                DGIPaymentFormPage dGIPaymentFormPage2 = DGIPaymentFormPage.this;
                dGIPaymentFormPage2.UpdateAccountBalance(DGIPaymentFormPage.access$600(dGIPaymentFormPage2).accountNumber, DGIPaymentFormPage.access$600(DGIPaymentFormPage.this).totalTTC);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(DGIPaymentFormPage.this.uiManager.stateMachine, payVignetteProcess.getStatusLabel(), null);
            if (payVignetteProcess.getStatusCode().equals("001")) {
                return;
            }
            Display.getInstance().callSerially(new 2());
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new DGIPaymentFormPage$10$1$$ExternalSyntheticLambda0(this)).schedule(500, false, DGIPaymentFormPage.this.uiManager.currentForm);
            }

            /* synthetic */ void lambda$run$0$com-b3g-ui-page-DGIPaymentFormPage$10$1() {
                DGIPaymentFormPage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }

        class 2 implements Runnable {
            2() {
            }

            public void run() {
                new UITimer(new DGIPaymentFormPage$10$2$$ExternalSyntheticLambda0(this)).schedule(500, false, DGIPaymentFormPage.this.uiManager.currentForm);
            }

            /* synthetic */ void lambda$run$0$com-b3g-ui-page-DGIPaymentFormPage$10$2() {
                DGIPaymentFormPage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v30 */
    private int getMatriculeindex(String str) {
        str.equals("A");
        ?? r0 = str.equals("B");
        if (str.equals("D")) {
            r0 = 2;
        }
        int i = r0;
        if (str.equals("H")) {
            i = 3;
        }
        int i2 = i;
        if (str.equals("E")) {
            i2 = 4;
        }
        int i3 = i2;
        if (str.equals("T")) {
            i3 = 5;
        }
        int i4 = i3;
        if (str.equals("Y")) {
            i4 = 6;
        }
        int i5 = i4;
        if (str.equals("K")) {
            i5 = 7;
        }
        int i6 = i5;
        if (str.equals("L")) {
            i6 = 8;
        }
        int i7 = i6;
        if (str.equals("M")) {
            i7 = 9;
        }
        int i8 = i7;
        if (str.equals("N")) {
            i8 = 10;
        }
        int i9 = i8;
        if (str.equals("C")) {
            i9 = 11;
        }
        int i10 = i9;
        if (str.equals("F")) {
            i10 = 12;
        }
        int i11 = i10;
        if (str.equals("R")) {
            i11 = 13;
        }
        int i12 = i11;
        if (str.equals("S")) {
            i12 = 14;
        }
        if (str.equals("RT")) {
            return 15;
        }
        return i12;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    private int getCarburantTypeindex(String str) {
        str.equals("DI");
        ?? r0 = str.equals("HY");
        if (str.equals("ES")) {
            r0 = 2;
        }
        int i = r0;
        if (str.equals("EL")) {
            i = 3;
        }
        if (str.equals("GP")) {
            return 3;
        }
        return i;
    }
}
