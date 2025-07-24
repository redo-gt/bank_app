package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.AgenceDemandeHistorique;
import com.b3g.services.AgencyMapService;
import com.b3g.services.B3gService;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.RateUs;
import com.b3g.ui.page.cih.Module.LocaliserAgences;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AgenceChangePage extends B3GPage {
    static boolean check = false;
    private static String motifStr = "Autre";
    private static ArrayList mtcHistoricListARR = null;
    private static String selectedStr = "Sélectionner";
    private String CodeAgenceDestin;
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    TextField txtHBPassWord;
    AgenceDemandeHistorique dmHistAgenc = new AgenceDemandeHistorique();
    UIBuilder uib = new UIBuilder();
    Request requeest = new Request(null);

    static /* synthetic */ String access$100() {
        return motifStr;
    }

    static /* synthetic */ void access$1000(AgenceChangePage agenceChangePage, Container container, Container container2, Container container3, boolean z) {
        agenceChangePage.SwitchTransfertForms(container, container2, container3, z);
    }

    static /* synthetic */ String access$1100(AgenceChangePage agenceChangePage) {
        return agenceChangePage.VnewaliasText;
    }

    static /* synthetic */ String access$1102(AgenceChangePage agenceChangePage, String str) {
        agenceChangePage.VnewaliasText = str;
        return str;
    }

    static /* synthetic */ ArrayList access$200() {
        return mtcHistoricListARR;
    }

    static /* synthetic */ ArrayList access$202(ArrayList arrayList) {
        mtcHistoricListARR = arrayList;
        return arrayList;
    }

    static /* synthetic */ String access$300() {
        return selectedStr;
    }

    static /* synthetic */ String access$400(AgenceChangePage agenceChangePage) {
        return agenceChangePage.CodeAgenceDestin;
    }

    static /* synthetic */ String access$402(AgenceChangePage agenceChangePage, String str) {
        agenceChangePage.CodeAgenceDestin = str;
        return str;
    }

    static /* synthetic */ String access$500(AgenceChangePage agenceChangePage, Container container) {
        return agenceChangePage.GetRibFromIssuarAcountCnt(container);
    }

    static /* synthetic */ Hashtable access$600(AgenceChangePage agenceChangePage, Request request, String str) {
        return agenceChangePage.FillHashMapFromParams(request, str);
    }

    static /* synthetic */ String access$700(AgenceChangePage agenceChangePage, ServiceResponse serviceResponse) {
        return agenceChangePage.FillFeeFromServiceResponse(serviceResponse);
    }

    static /* synthetic */ void access$800(AgenceChangePage agenceChangePage, Request request, Container container) {
        agenceChangePage.ShowAgenceDetailPopUp(request, container);
    }

    static /* synthetic */ Container access$900(AgenceChangePage agenceChangePage, Container container) {
        return agenceChangePage.GetTransferSecurityForm(container);
    }

    public AgenceChangePage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    private static class Request {
        String accountNumber;
        String city;
        String codeAgence;
        String emailAgent;
        String motif;
        String newAgence;
        String oldAgence;
        String otp;
        String tarif;

        private Request() {
            this.tarif = "";
            this.accountNumber = "";
            this.otp = "";
        }

        /* synthetic */ Request(1 r1) {
            this();
        }
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (CihMBanking.sesPMTR.getIsCanRequestUpdateAgencye().equals("False")) {
                    new UITimer(new 1()).schedule(100, false, this.uiManager.currentForm);
                } else {
                    Container container = new Container(new BoxLayout(2));
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new B3gContainer(getChgmtAgenceCnt(), " CHANGEMENT AGENCE "));
                    arrayList.add(new B3gContainer(getHistorique(), " HISTORIQUE "));
                    this.uiManager.DrawTabsWithNavigation(container, arrayList);
                    this.thisContainer.addComponent(container);
                }
            } else {
                new UITimer(new 2()).schedule(100, false, this.uiManager.currentForm);
            }
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            AgenceChangePage.this.uiManager.ShowMessageTransaction(AgenceChangePage.this.uiManager.stateMachine, "Votre type de compte ne vous permet pas de changer d'agence en ligne. Pour plus d'informations, merci de contacter le Centre de relation client", null);
            AgenceChangePage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    class 2 implements Runnable {
        2() {
        }

        public void run() {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AgenceChangePage.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
            AgenceChangePage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    private Container getChgmtAgenceCnt() {
        TextArea textArea;
        Container container;
        Picker picker;
        Picker picker2;
        Button button;
        ArrayList arrayList;
        Container container2;
        TableLayout tableLayout = new TableLayout(1, 2);
        Container createContainer = this.uib.createContainer("/cihv3", "AgenceChangeCnt");
        Container container3 = (Container) this.uib.findByName("cntMain", createContainer);
        TextArea textArea2 = (TextArea) this.uib.findByName("agenceActuelleTxtF", createContainer);
        Container container4 = (Container) this.uib.findByName("villeChoisieCnt", createContainer);
        Container container5 = (Container) this.uib.findByName("agencechoisieCnt", createContainer);
        Container container6 = (Container) this.uib.findByName("motifChoisieCnt", createContainer);
        Container container7 = (Container) this.uib.findByName("adresseAgenceCnt", createContainer);
        Container container8 = (Container) this.uib.findByName("motifAutreCnt", createContainer);
        SpanLabel spanLabel = (SpanLabel) this.uib.findByName("spnLblMsg", createContainer);
        SpanLabel spanLabel2 = (SpanLabel) this.uib.findByName("AdresseSpnLbl", createContainer);
        Container container9 = (Container) this.uib.findByName("Container1", createContainer);
        Container container10 = new Container(new GridLayout(1, 1));
        Container container11 = new Container();
        Container container12 = new Container(new BoxLayout(2));
        TextArea textArea3 = (TextArea) this.uib.findByName("autreMotifTxtF", createContainer);
        Picker picker3 = new Picker();
        Picker picker4 = new Picker();
        Picker picker5 = new Picker();
        Button button2 = new Button(" VALIDER ");
        Container container13 = new Container(new FlowLayout(1, 4));
        Label label = new Label("* Champs obligatoire");
        container11.setUIID("g_cntBorderV3");
        container11.getAllStyles().setBgColor(13421772);
        container11.getAllStyles().setBgTransparency(255);
        button2.setUIID("op_BtnOppositionValidation");
        label.setUIID("ad_lblValueGreen");
        container13.getAllStyles().setMargin(1, 1, 0, 0);
        container10.getAllStyles().setMargin(1, 1, 0, 0);
        textArea2.getAllStyles().setBgColor(13421772);
        textArea2.setEditable(false);
        spanLabel.setText("* Le changement d’agence vous premettra de transférer toute votre comptes et contrats vers l’agence de votre choix tout en gardant le même numéro de compte, ainsi les prélèvements et les virements récurrents ne seront pas impactés.");
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList());
        arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getListRelationAccount());
        Container container14 = (Container) this.uib.findByName("cntAcount", createContainer);
        Container DrawListContainerTransfertAgence = this.uiManager.DrawListContainerTransfertAgence("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getCanDebitAccount(arrayList2), 1, 16, "", null);
        container14.addComponent(DrawListContainerTransfertAgence);
        container14.addComponent(this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransfertSep"));
        container7.setHidden(true);
        container8.setHidden(true);
        ArrayList ChangeAgencyCheckProcess = this.dmHistAgenc.ChangeAgencyCheckProcess();
        textArea2.setText(((AgenceDemandeHistorique) ChangeAgencyCheckProcess.get(0)).current_agency.toString());
        textArea2.getAllStyles().setFont(Font.createSystemFont(0, 1, 8));
        if (((AgenceDemandeHistorique) ChangeAgencyCheckProcess.get(0)).statutCode.equals("000")) {
            container9.setHidden(false);
            container13.add(label);
            container4.add(tableLayout.createConstraint().widthPercentage(60), drawPicker(picker3, ((AgenceDemandeHistorique) ChangeAgencyCheckProcess.get(0)).villes));
            picker2 = picker4;
            container5.add(tableLayout.createConstraint().widthPercentage(60), drawPicker(picker2, null));
            container6.add(tableLayout.createConstraint().widthPercentage(60), drawPicker(picker5, ((AgenceDemandeHistorique) ChangeAgencyCheckProcess.get(0)).motifs));
            textArea = textArea2;
            picker3.addActionListener(new 3(container7, container8, picker5, picker3, picker2, createContainer));
            picker2.addActionListener(new 4(picker3, container8, picker5, picker2, spanLabel2, container7));
            picker = picker5;
            picker.addActionListener(new 5(picker, container8));
            button = button2;
            container10.add(button);
            container = container12;
            container.add(container10);
            container.add(container13);
            arrayList = ChangeAgencyCheckProcess;
        } else {
            textArea = textArea2;
            container = container12;
            picker = picker5;
            picker2 = picker4;
            button = button2;
            container9.setHidden(true);
            arrayList = ChangeAgencyCheckProcess;
            new UITimer(new 6(arrayList)).schedule(100, false, this.uiManager.currentForm);
        }
        if (CihMBanking.sesPMTR.getIsDemo() != 0) {
            button.setUIID("op_BtnOppositionValidationGriseMarginLeft");
            container2 = container;
        } else {
            Picker picker6 = picker2;
            container2 = container;
            button.addActionListener(new 7(arrayList, textArea, picker3, picker6, picker, container8, textArea3, DrawListContainerTransfertAgence, container3));
        }
        container2.setUIID("mn_cntMenuItemNew");
        container3.addComponent(container2);
        return createContainer;
    }

    class 3 implements ActionListener {
        final /* synthetic */ Container val$adresseAgenceCnt;
        final /* synthetic */ Container val$agenceChangeCnt;
        final /* synthetic */ Container val$motifAutreCnt;
        final /* synthetic */ Picker val$pckAgences;
        final /* synthetic */ Picker val$pckMotifs;
        final /* synthetic */ Picker val$pckVilles;

        3(Container container, Container container2, Picker picker, Picker picker2, Picker picker3, Container container3) {
            this.val$adresseAgenceCnt = container;
            this.val$motifAutreCnt = container2;
            this.val$pckMotifs = picker;
            this.val$pckVilles = picker2;
            this.val$pckAgences = picker3;
            this.val$agenceChangeCnt = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!this.val$adresseAgenceCnt.isHidden()) {
                this.val$adresseAgenceCnt.setHidden(true);
            }
            if (!this.val$motifAutreCnt.isHidden() && !this.val$pckMotifs.getSelectedString().equals(AgenceChangePage.access$100())) {
                this.val$motifAutreCnt.setHidden(true);
            }
            LocaliserAgences localiserAgences = new LocaliserAgences();
            AgenceChangePage.access$202(new ArrayList());
            AgenceChangePage.access$202(localiserAgences.AgencyMapServiceProcess(this.val$pckVilles.getSelectedString(), "0", "0"));
            String[] strArr = new String[AgenceChangePage.access$200().size()];
            for (int i = 0; i < AgenceChangePage.access$200().size(); i++) {
                strArr[i] = ((AgencyMapService) AgenceChangePage.access$200().get(i)).AgenceName;
            }
            this.val$pckAgences.setStrings(strArr);
            this.val$pckAgences.setSelectedString(AgenceChangePage.access$300());
            this.val$agenceChangeCnt.getParent().revalidate();
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ SpanLabel val$AdresseSpnLbl;
        final /* synthetic */ Container val$adresseAgenceCnt;
        final /* synthetic */ Container val$motifAutreCnt;
        final /* synthetic */ Picker val$pckAgences;
        final /* synthetic */ Picker val$pckMotifs;
        final /* synthetic */ Picker val$pckVilles;

        4(Picker picker, Container container, Picker picker2, Picker picker3, SpanLabel spanLabel, Container container2) {
            this.val$pckVilles = picker;
            this.val$motifAutreCnt = container;
            this.val$pckMotifs = picker2;
            this.val$pckAgences = picker3;
            this.val$AdresseSpnLbl = spanLabel;
            this.val$adresseAgenceCnt = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$pckVilles.getSelectedString().equals(AgenceChangePage.access$300()) || this.val$pckVilles.getText().trim().length() == 0) {
                return;
            }
            if (!this.val$motifAutreCnt.isHidden() && !this.val$pckMotifs.getSelectedString().equals(AgenceChangePage.access$100())) {
                this.val$motifAutreCnt.setHidden(true);
            }
            for (int i = 0; i < AgenceChangePage.access$200().size(); i++) {
                if (((AgencyMapService) AgenceChangePage.access$200().get(i)).AgenceName.equals(this.val$pckAgences.getSelectedString())) {
                    this.val$AdresseSpnLbl.setText(((AgencyMapService) AgenceChangePage.access$200().get(i)).adresse);
                    this.val$adresseAgenceCnt.setHidden(false);
                    AgenceChangePage.access$402(AgenceChangePage.this, ((AgencyMapService) AgenceChangePage.access$200().get(i)).CodeAgence);
                }
            }
        }
    }

    class 5 implements ActionListener {
        final /* synthetic */ Container val$motifAutreCnt;
        final /* synthetic */ Picker val$pckMotifs;

        5(Picker picker, Container container) {
            this.val$pckMotifs = picker;
            this.val$motifAutreCnt = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$pckMotifs.getSelectedString().equals(AgenceChangePage.access$100())) {
                this.val$motifAutreCnt.setHidden(false);
            } else {
                this.val$motifAutreCnt.setHidden(true);
            }
        }
    }

    class 6 implements Runnable {
        final /* synthetic */ ArrayList val$OrderAgencesicList;

        6(ArrayList arrayList) {
            this.val$OrderAgencesicList = arrayList;
        }

        public void run() {
            AgenceChangePage.this.uiManager.ShowMessageTransaction(AgenceChangePage.this.uiManager.stateMachine, DataTools.FormatUnicode(((AgenceDemandeHistorique) this.val$OrderAgencesicList.get(0)).statutLabel), null);
        }
    }

    class 7 implements ActionListener {
        final /* synthetic */ ArrayList val$OrderAgencesicList;
        final /* synthetic */ TextArea val$agenceActuelleTxtF;
        final /* synthetic */ TextArea val$autreMotifTxtF;
        final /* synthetic */ Container val$cntMain;
        final /* synthetic */ Container val$issuarAccount;
        final /* synthetic */ Container val$motifAutreCnt;
        final /* synthetic */ Picker val$pckAgences;
        final /* synthetic */ Picker val$pckMotifs;
        final /* synthetic */ Picker val$pckVilles;

        7(ArrayList arrayList, TextArea textArea, Picker picker, Picker picker2, Picker picker3, Container container, TextArea textArea2, Container container2, Container container3) {
            this.val$OrderAgencesicList = arrayList;
            this.val$agenceActuelleTxtF = textArea;
            this.val$pckVilles = picker;
            this.val$pckAgences = picker2;
            this.val$pckMotifs = picker3;
            this.val$motifAutreCnt = container;
            this.val$autreMotifTxtF = textArea2;
            this.val$issuarAccount = container2;
            this.val$cntMain = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AgenceChangePage.this.requeest.codeAgence = ((AgenceDemandeHistorique) this.val$OrderAgencesicList.get(0)).codeAgence;
            if (this.val$agenceActuelleTxtF.getText().length() == 0 || this.val$pckVilles.getSelectedString().equals(AgenceChangePage.access$300()) || this.val$pckVilles.getSelectedString().equals("اختيار") || this.val$pckVilles.getSelectedString().equals("Select") || this.val$pckVilles.getSelectedString().equals("seleccionar") || this.val$pckAgences.getSelectedString().equals(AgenceChangePage.access$300()) || this.val$pckAgences.getSelectedString().equals("أخرى") || this.val$pckAgences.getSelectedString().equals("Other ") || this.val$pckAgences.getSelectedString().equals("Otra ") || this.val$pckVilles.getSelectedString().length() == 0 || this.val$pckAgences.getSelectedString().length() == 0 || ((this.val$pckMotifs.getSelectedString().equals(AgenceChangePage.access$300()) && this.val$motifAutreCnt.isHidden()) || (this.val$autreMotifTxtF.getText().length() == 0 && !this.val$motifAutreCnt.isHidden()))) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AgenceChangePage.this.uiManager.stateMachine, "Veuillez saisir l'ensemble des champs obligatoires", null);
                return;
            }
            if (AgenceChangePage.this.requeest.codeAgence.equals(AgenceChangePage.access$400(AgenceChangePage.this))) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AgenceChangePage.this.uiManager.stateMachine, "Vous ne pouvez pas effectuer le transfert vers la même agence", null);
                return;
            }
            AgenceChangePage.this.requeest.oldAgence = this.val$agenceActuelleTxtF.getText();
            AgenceChangePage.this.requeest.newAgence = this.val$pckAgences.getSelectedString();
            if (this.val$pckMotifs.getSelectedString().equals(AgenceChangePage.access$100())) {
                AgenceChangePage.this.requeest.motif = this.val$autreMotifTxtF.getText();
            } else {
                AgenceChangePage.this.requeest.motif = this.val$pckMotifs.getSelectedString();
            }
            AgenceChangePage.this.requeest.tarif = "";
            AgenceChangePage.this.requeest.emailAgent = ((AgenceDemandeHistorique) this.val$OrderAgencesicList.get(0)).mail_agence.toString();
            AgenceChangePage.this.requeest.city = this.val$pckVilles.getSelectedString();
            AgenceChangePage.this.requeest.accountNumber = AgenceChangePage.access$500(AgenceChangePage.this, this.val$issuarAccount);
            AgenceChangePage.this.requeest.codeAgence = ((AgenceDemandeHistorique) this.val$OrderAgencesicList.get(0)).codeAgence;
            AgenceDemandeHistorique agenceDemandeHistorique = AgenceChangePage.this.dmHistAgenc;
            AgenceChangePage agenceChangePage = AgenceChangePage.this;
            ServiceResponse updateChgmtAgenceProcess = agenceDemandeHistorique.updateChgmtAgenceProcess(AgenceChangePage.access$600(agenceChangePage, agenceChangePage.requeest, "01"));
            if (updateChgmtAgenceProcess.getStatusCode().equals("000")) {
                AgenceChangePage.this.requeest.tarif = AgenceChangePage.access$700(AgenceChangePage.this, updateChgmtAgenceProcess);
                AgenceChangePage agenceChangePage2 = AgenceChangePage.this;
                AgenceChangePage.access$800(agenceChangePage2, agenceChangePage2.requeest, this.val$cntMain);
                return;
            }
            AgenceChangePage.this.uiManager.ShowMessageTransaction(AgenceChangePage.this.uiManager.stateMachine, DataTools.FormatUnicode(updateChgmtAgenceProcess.getStatusLabel()), null);
        }
    }

    private Container getHistorique() {
        ArrayList historiqueProcess = this.dmHistAgenc.historiqueProcess();
        ArrayList arrayList = new ArrayList();
        Container container = new Container(new BoxLayout(2));
        Container container2 = new Container();
        container.setUIID("ad_CntAccountDetailsMainNew");
        container2.setUIID("gb_cntSeparator");
        container.add(container2);
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            for (int i = 0; i < historiqueProcess.size(); i++) {
                ((AgenceDemandeHistorique) historiqueProcess.get(i)).groupKey = ((AgenceDemandeHistorique) historiqueProcess.get(i)).request_Date;
                arrayList.add((B3gService) historiqueProcess.get(i));
            }
            if (arrayList.size() > 0) {
                this.uiManager.Draw_GroupBy(container, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 39, null);
            } else {
                container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
            }
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
        }
        container.setUIID("mn_cntMenuItem");
        return container;
    }

    private void ShowAgenceDetailPopUp(Request request, Container container) {
        new AgenceDemandeHistorique();
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        dialog.setPreferredH(Display.getInstance().getDisplayHeight());
        dialog.setPreferredW(Display.getInstance().getDisplayWidth());
        dialog.setShouldCalcPreferredSize(true);
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
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "AgenceDetails");
        ((Container) uIBuilder.findByName("Container222", createContainer)).setHidden(true);
        Label label = (Label) uIBuilder.findByName("agenceActLbl", createContainer);
        Label label2 = (Label) uIBuilder.findByName("VillechoisieLbl", createContainer);
        Label label3 = (Label) uIBuilder.findByName("agenceChoisieLbl", createContainer);
        Label label4 = (Label) uIBuilder.findByName("FeeAmount", createContainer);
        Label label5 = (Label) uIBuilder.findByName("motifChoisieLbl", createContainer);
        Container container2 = (Container) uIBuilder.findByName("cntComptes", createContainer);
        Container container3 = new Container(new BoxLayout(2));
        Button button = (Button) uIBuilder.findByName("btnAnnuler", createContainer);
        Button button2 = (Button) uIBuilder.findByName("BtnValider", createContainer);
        ArrayList client_AccountList = CihMBanking.sesPMTR.getSessionClient().getClient_AccountList();
        CihMBanking.sesPMTR.getSessionClient().getRelations();
        for (int i = 0; i < client_AccountList.size(); i++) {
            Label label6 = new Label(((Account) client_AccountList.get(i)).accountNumber);
            label6.setUIID("accountPopupCdcLabel");
            container3.add(label6);
        }
        container2.getAllStyles().setMarginTop(2);
        container2.getAllStyles().setMarginBottom(2);
        container3.getAllStyles().setMarginTop(1);
        container3.getAllStyles().setMarginBottom(1);
        container2.getAllStyles().setBgTransparency(0);
        container3.getAllStyles().setBgTransparency(0);
        container2.add(container3);
        label.setText(this.requeest.oldAgence);
        label2.setText(this.requeest.city);
        label3.setText(this.requeest.newAgence);
        label4.setText(request.tarif + " DHs");
        label5.setText(this.requeest.motif);
        button.addActionListener(new 8(dialog));
        button2.addActionListener(new 9(dialog, container));
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        createContainer.setShouldCalcPreferredSize(true);
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", false);
    }

    class 8 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        8(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 9 implements ActionListener {
        final /* synthetic */ Container val$cntMain;
        final /* synthetic */ Dialog val$d;

        9(Dialog dialog, Container container) {
            this.val$d = dialog;
            this.val$cntMain = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            CihMBanking.sesPMTR.setSessionSavedContainer(this.val$cntMain);
            AgenceChangePage agenceChangePage = AgenceChangePage.this;
            Container parent = this.val$cntMain.getParent();
            Container container = this.val$cntMain;
            AgenceChangePage.access$1000(agenceChangePage, parent, container, AgenceChangePage.access$900(AgenceChangePage.this, container.getParent()), false);
        }
    }

    private Container drawPicker(Picker picker, String[] strArr) {
        Container container = new Container(new BorderLayout());
        container.setUIID("grey_1_1_1_1");
        picker.setType(4);
        picker.setUIID("lbl_regular");
        if (strArr != null) {
            picker.setStrings(strArr);
        }
        picker.setSelectedString(selectedStr);
        picker.setAlignment(4);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("newListBox.png"));
        container.add("Center", picker).add("East", label);
        container.setLeadComponent(picker);
        return container;
    }

    private Container getMSgErreurCnt(String str) {
        TableLayout tableLayout = new TableLayout(1, 2);
        Container container = new Container(new BoxLayout(2));
        Container container2 = new Container(tableLayout);
        Container container3 = new Container(new FlowLayout(4, 4));
        Container container4 = new Container(new FlowLayout(4, 4));
        Button button = new Button(this.uiManager.ressource.getImage("icons.png"));
        SpanLabel spanLabel = new SpanLabel(str);
        spanLabel.setTextUIID("ContainerButtonImageIconsV2");
        container2.setUIID("ContainerButtonImageIconsV2");
        button.setUIID("Container");
        container3.add(spanLabel);
        container4.add(button);
        container4.getAllStyles().setPadding(0, 0, 0, 0);
        button.getAllStyles().setPadding(0, 0, 0, 0);
        container4.getAllStyles().setMargin(0, 0, 0, 0);
        button.getAllStyles().setMargin(0, 0, 0, 0);
        container2.add(tableLayout.createConstraint().widthPercentage(15), container4);
        container2.add(tableLayout.createConstraint().widthPercentage(85), container3);
        button.addActionListener(new 10(container));
        container.add(container2);
        container.setLeadComponent(button);
        return container;
    }

    class 10 implements ActionListener {
        final /* synthetic */ Container val$cnt;

        10(Container container) {
            this.val$cnt = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$cnt.setHidden(true);
            this.val$cnt.getParent().revalidate();
        }
    }

    private final Hashtable FillHashMapFromParams(Request request, String str) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSION_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("OLD_AGENCY", request.oldAgence);
        hashtable.put("NEW_AGENCY", request.newAgence);
        hashtable.put("CITY", request.city);
        hashtable.put("EmailAgent", request.emailAgent);
        hashtable.put("MOTIF", request.motif);
        hashtable.put("ACCOUNT_NUMBER", request.accountNumber);
        hashtable.put("TARIF", request.tarif);
        hashtable.put("OTP", request.otp);
        hashtable.put("CODE_AGENCE", request.codeAgence);
        hashtable.put("SERVICE_ID", str);
        return hashtable;
    }

    private String FillFeeFromServiceResponse(ServiceResponse serviceResponse) {
        return serviceResponse.getParamsOut().get("Frais").toString();
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

    private Container GetTransferSecurityForm(Container container) {
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container2 = (Container) this.uib.findByName("Container17", createContainer);
        container2.removeAll();
        container2.setLayout(BoxLayout.y());
        container2.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 11(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 12(b3GCIHEcode));
        return createContainer;
    }

    class 11 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        11(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            AgenceChangePage.this.uiManager.btnBack.getListeners().clear();
            AgenceChangePage.this.uiManager.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                AgenceChangePage.this.uiManager.GoBack();
            }
        }
    }

    class 12 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;

        12(B3GCIHEcode b3GCIHEcode) {
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AgenceChangePage.access$1102(AgenceChangePage.this, this.val$eCode1.getValue());
            String access$1100 = AgenceChangePage.access$1100(AgenceChangePage.this);
            if (access$1100.length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(AgenceChangePage.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            AgenceChangePage.this.requeest.otp = access$1100;
            AgenceDemandeHistorique agenceDemandeHistorique = AgenceChangePage.this.dmHistAgenc;
            AgenceChangePage agenceChangePage = AgenceChangePage.this;
            ServiceResponse updateChgmtAgenceProcess = agenceDemandeHistorique.updateChgmtAgenceProcess(AgenceChangePage.access$600(agenceChangePage, agenceChangePage.requeest, "02"));
            new RateUs(AgenceChangePage.this.uiManager).init();
            AgenceChangePage.this.uiManager.ShowMessageTransaction(AgenceChangePage.this.uiManager.stateMachine, DataTools.FormatUnicode(updateChgmtAgenceProcess.getStatusLabel()), null);
            AgenceChangePage.this.uiManager.NavigateToPageById(79);
        }
    }

    private void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }

    private String GetRibFromIssuarAcountCnt(Container container) {
        return ((Account) ((B3gService) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getClientProperty("AccountClient"))).rib;
    }
}
