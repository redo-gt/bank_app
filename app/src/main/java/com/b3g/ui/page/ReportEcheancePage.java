package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Loan;
import com.b3g.services.ReportEchanceService;
import com.b3g.ui.B3GCheckBox;
import com.b3g.ui.B3GUiManager;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import com.codename1.util.Base64;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ReportEcheancePage extends B3GPage {
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();
    private String keyStorage = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical + "flagEcheance";
    private boolean isLoanImmoSeleced = false;
    private boolean isLoanConsoSeleced = false;
    private String selectedStr = "Sélectionner";

    static /* synthetic */ boolean access$000(ReportEcheancePage reportEcheancePage, Container container, int i) {
        return reportEcheancePage.getloanItemSelected(container, i);
    }

    static /* synthetic */ String access$100(ReportEcheancePage reportEcheancePage) {
        return reportEcheancePage.selectedStr;
    }

    static /* synthetic */ boolean access$200(ReportEcheancePage reportEcheancePage) {
        return reportEcheancePage.isLoanConsoSeleced;
    }

    static /* synthetic */ boolean access$300(ReportEcheancePage reportEcheancePage) {
        return reportEcheancePage.isLoanImmoSeleced;
    }

    static /* synthetic */ void access$400(ReportEcheancePage reportEcheancePage, String str, ArrayList arrayList, String str2, String str3, String str4, String str5, boolean z, boolean z2) {
        reportEcheancePage.ShowEcheanceDetailPopUp(str, arrayList, str2, str3, str4, str5, z, z2);
    }

    public ReportEcheancePage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("white_container_padding_0_0_1_1");
        try {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (!CihMBanking.sesPMTR.getSessionClient().getClient_LoanListToReprt().isEmpty()) {
                    this.thisContainer.addComponent(getReportEchanceCnt());
                } else {
                    new UITimer(new 1()).schedule(100, false, this.uiManager.currentForm);
                }
            } else {
                new UITimer(new 2()).schedule(100, false, this.uiManager.currentForm);
            }
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            ReportEcheancePage.this.uiManager.ShowMessageTransaction(ReportEcheancePage.this.uiManager.stateMachine, "Vous n'avez aucun prêt.", null);
            ReportEcheancePage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    class 2 implements Runnable {
        2() {
        }

        public void run() {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(ReportEcheancePage.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
            ReportEcheancePage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    private Container getReportEchanceCnt() {
        Container container;
        Container container2;
        TableLayout tableLayout = new TableLayout(1, 2);
        Picker picker = new Picker();
        Picker picker2 = new Picker();
        String[] strArr = {" Arrêt temporaire de travail ", " Revenu diminué "};
        String[] strArr2 = {" Maintien de l’échéance ", " Maintien de la durée "};
        Container createContainer = this.uib.createContainer("/cihv3", "ReportEchancePage");
        Container container3 = (Container) this.uib.findByName("ContCreditCons", createContainer);
        Container container4 = (Container) this.uib.findByName("ContCreditImmo", createContainer);
        Container container5 = (Container) this.uib.findByName("CntRBCrdCons", createContainer);
        Container container6 = (Container) this.uib.findByName("CntRBCrdImm", createContainer);
        Container container7 = (Container) this.uib.findByName("CntRBCrdImm", createContainer);
        Container container8 = (Container) this.uib.findByName("motifCont", createContainer);
        Container container9 = (Container) this.uib.findByName("motifCont1", createContainer);
        Container container10 = (Container) this.uib.findByName("cntGlb", createContainer);
        Container container11 = container6;
        TextField textField = (TextField) this.uib.findByName("dureeTxtF", createContainer);
        textField.setText(getTrd());
        textField.setEditable(false);
        container7.setScrollableY(true);
        container7.setScrollVisible(false);
        container10.setScrollVisible(false);
        Label label = (Label) this.uib.findByName("Label2", createContainer);
        Label label2 = (Label) this.uib.findByName("Label3", createContainer);
        Label label3 = (Label) this.uib.findByName("Label4", createContainer);
        Container container12 = container5;
        Button button = (Button) this.uib.findByName("BtnValidation", createContainer);
        label.setUIID("g_lblDashBoardValue");
        label2.setUIID("g_lblDashBoardValue");
        label3.setUIID("g_lblDashBoardValue");
        container8.add(tableLayout.createConstraint().widthPercentage(50), drawPicker(picker, new String[]{"Arrêt temporaire de travail", "Revenu diminué"}));
        container9.add(tableLayout.createConstraint().widthPercentage(50), drawPicker(picker2, new String[]{"Maintien de l’échéance", "Maintien de la durée"}));
        ArrayList client_LoanListToReprt = CihMBanking.sesPMTR.getSessionClient().getClient_LoanListToReprt();
        container3.setHidden(true);
        container4.setHidden(true);
        Iterator it = client_LoanListToReprt.iterator();
        while (it.hasNext()) {
            Loan loan = (Loan) it.next();
            String str = loan.type;
            str.hashCode();
            if (str.equals("IMMOA")) {
                container = container12;
                container4.setHidden(false);
                container2 = container11;
                createLoanCnt(container2, loan);
            } else {
                container3.setHidden(false);
                container = container12;
                createLoanCnt(container, loan);
                container2 = container11;
            }
            container12 = container;
            container11 = container2;
        }
        button.addActionListener(new 3(container11, container12, picker, picker2, strArr, strArr2));
        return createContainer;
    }

    class 3 implements ActionListener {
        final /* synthetic */ Container val$RBCrdConsCont;
        final /* synthetic */ Container val$RBCrdImmCont;
        final /* synthetic */ String[] val$modeToSend;
        final /* synthetic */ String[] val$motifsToSend;
        final /* synthetic */ Picker val$pckMode;
        final /* synthetic */ Picker val$pckMotifs;

        3(Container container, Container container2, Picker picker, Picker picker2, String[] strArr, String[] strArr2) {
            this.val$RBCrdImmCont = container;
            this.val$RBCrdConsCont = container2;
            this.val$pckMotifs = picker;
            this.val$pckMode = picker2;
            this.val$motifsToSend = strArr;
            this.val$modeToSend = strArr2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ArrayList client_LoanListToReprt = CihMBanking.sesPMTR.getSessionClient().getClient_LoanListToReprt();
            ArrayList arrayList = new ArrayList();
            Iterator it = client_LoanListToReprt.iterator();
            int i = 0;
            String str = " ";
            int i2 = 0;
            while (it.hasNext()) {
                Loan loan = (Loan) it.next();
                String str2 = loan.type;
                str2.hashCode();
                if (str2.equals("IMMOA")) {
                    if (ReportEcheancePage.access$000(ReportEcheancePage.this, this.val$RBCrdImmCont, i)) {
                        if (str.equals(" ")) {
                            str = loan.numpret + ",";
                        } else {
                            str = str + loan.numpret + ",";
                        }
                        arrayList.add(loan);
                    }
                    i++;
                } else {
                    if (ReportEcheancePage.access$000(ReportEcheancePage.this, this.val$RBCrdConsCont, i2)) {
                        if (str.equals(" ")) {
                            str = loan.numpret + ",";
                        } else {
                            str = str + loan.numpret + ",";
                        }
                        arrayList.add(loan);
                    }
                    i2++;
                }
            }
            if (!arrayList.isEmpty() && !this.val$pckMotifs.getSelectedString().equals(ReportEcheancePage.access$100(ReportEcheancePage.this)) && !this.val$pckMotifs.getSelectedString().equals("اختيار") && !this.val$pckMotifs.getSelectedString().equals("Select") && !this.val$pckMotifs.getSelectedString().equals("seleccionar") && !this.val$pckMode.getSelectedString().equals(ReportEcheancePage.access$100(ReportEcheancePage.this)) && !this.val$pckMode.getSelectedString().equals("اختيار") && !this.val$pckMode.getSelectedString().equals("Select") && !this.val$pckMode.getSelectedString().equals("seleccionar")) {
                ReportEcheancePage.access$400(ReportEcheancePage.this, str, arrayList, this.val$pckMotifs.getSelectedString(), this.val$pckMode.getSelectedString(), this.val$motifsToSend[this.val$pckMotifs.getSelectedStringIndex()], this.val$modeToSend[this.val$pckMode.getSelectedStringIndex()], ReportEcheancePage.access$200(ReportEcheancePage.this), ReportEcheancePage.access$300(ReportEcheancePage.this));
                return;
            }
            ReportEcheancePage.this.uiManager.ShowMessageTransaction(ReportEcheancePage.this.uiManager.stateMachine, "Veuillez saisir l'ensemble des champs obligatoires", null);
        }
    }

    private void createLoanCnt(Container container, Loan loan) {
        Container container2 = new Container(BoxLayout.y());
        Container container3 = new Container(new FlowLayout(1));
        Container container4 = new Container();
        Container container5 = new Container(BoxLayout.y());
        Container container6 = new Container(BoxLayout.x());
        Container container7 = new Container(BoxLayout.y());
        Container container8 = new Container(BoxLayout.y());
        B3GCheckBox b3GCheckBox = new B3GCheckBox("");
        container7.setUIID("margin_1_1_1_1");
        container8.setUIID("g_cntBorderV2");
        Label label = new Label(loan.numpret);
        label.setUIID("g_lblBalanceValueFLFT");
        Label label2 = new Label("Montant Echéance :");
        label2.setUIID("g_lblDashBoardValue");
        Label label3 = new Label(loan.mntEch);
        label3.setUIID("g_lblBalanceValueFLFT");
        Label label4 = new Label("Dhs");
        label4.setUIID("g_lblBalanceValueFLFT");
        container6.add(label2).add(label3).add(label4);
        container5.add(label).add(container6);
        container5.setUIID("margin_0_0_1_0");
        b3GCheckBox.addItem("");
        container4.setUIID("margin_1_0_0_0");
        container4.addComponent(b3GCheckBox.GetContainer());
        container3.addComponent(container4);
        container3.addComponent(container5);
        container2.addComponent(container3);
        container7.add(container8);
        container2.add(container7);
        container.add(container2);
    }

    private boolean getloanItemSelected(Container container, int i) {
        return ((CheckBox) ((Container) ((Container) ((Container) ((Container) container.getComponentAt(i)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).isSelected();
    }

    private Container drawPicker(Picker picker, String[] strArr) {
        Container container = new Container(new BorderLayout());
        container.setUIID("grey_1_1_1_1");
        picker.setType(4);
        picker.setUIID("lbl_regular");
        if (strArr != null) {
            picker.setStrings(strArr);
        }
        picker.setSelectedString(this.selectedStr);
        picker.setAlignment(4);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("newListBox.png"));
        container.add("Center", picker).add("East", label);
        container.setLeadComponent(picker);
        return container;
    }

    private void ShowEcheanceDetailPopUp(String str, ArrayList arrayList, String str2, String str3, String str4, String str5, boolean z, boolean z2) {
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
        Container createContainer = uIBuilder.createContainer("/cihv3", "ReportEchancePopUp");
        Label label = (Label) uIBuilder.findByName("modeChoisieLbl", createContainer);
        Label label2 = (Label) uIBuilder.findByName("motifvalue", createContainer);
        Container container = (Container) uIBuilder.findByName("cntGlbConso", createContainer);
        container.setHidden(true);
        Container container2 = (Container) uIBuilder.findByName("cntGlbImmo", createContainer);
        container2.setHidden(true);
        Container container3 = (Container) uIBuilder.findByName("cntCredConsItems", createContainer);
        Container container4 = (Container) uIBuilder.findByName("cntCredImmoItem", createContainer);
        Button button = (Button) uIBuilder.findByName("btnAnnuler", createContainer);
        Button button2 = (Button) uIBuilder.findByName("BtnValider", createContainer);
        label2.setText(str2);
        label.setText(str3);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Loan loan = (Loan) it.next();
            String str6 = loan.type;
            str6.hashCode();
            if (str6.equals("IMMOA")) {
                Label label3 = new Label();
                label3.setUIID("dm_lblPageNumberW");
                label3.setText(loan.numpret);
                container4.add(label3);
                container2.setHidden(false);
            } else {
                Label label4 = new Label();
                label4.setUIID("dm_lblPageNumberW");
                label4.setText(loan.numpret);
                container3.add(label4);
                container.setHidden(false);
            }
        }
        button.addActionListener(new 4(dialog));
        button2.addActionListener(new 5(dialog, str2, str, str4, str5));
        dialog.addComponent("Center", createContainer);
        dialog.show(0, 0, 0, 0);
    }

    class 4 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        4(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 5 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ String val$loansId;
        final /* synthetic */ String val$modeToSend;
        final /* synthetic */ String val$motif;
        final /* synthetic */ String val$motifToSend;

        5(Dialog dialog, String str, String str2, String str3, String str4) {
            this.val$d = dialog;
            this.val$motif = str;
            this.val$loansId = str2;
            this.val$motifToSend = str3;
            this.val$modeToSend = str4;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            String str = this.val$motif;
            if (str.hashCode() == 0) {
                str.equals("");
            }
            ServiceResponse ReportEchanceProcess = new ReportEchanceService().ReportEchanceProcess(this.val$loansId, this.val$motifToSend, this.val$modeToSend);
            if (ReportEchanceProcess.getStatusCode().equals("000")) {
                FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
                String str2 = fileSystemStorage.getAppHomePath() + "Demande.pdf";
                ReportEcheancePage.this.uiManager.ShowMessageTransaction(ReportEcheancePage.this.uiManager.stateMachine, "Votre demande est enregistrée \nPrière de vous rendre dans les 48 heures à votre agence CihBank pour signer la demande de report.", null);
                byte[] decode = Base64.decode(ReportEchanceProcess.getStatusLabel().getBytes(), ReportEchanceProcess.getStatusLabel().getBytes().length);
                try {
                    OutputStream openOutputStream = fileSystemStorage.openOutputStream(str2);
                    try {
                        openOutputStream.write(decode);
                        Display.getInstance().execute(str2);
                        if (openOutputStream != null) {
                            openOutputStream.close();
                            return;
                        }
                        return;
                    } finally {
                    }
                } catch (IOException unused) {
                    ReportEcheancePage.this.uiManager.ShowMessageTransaction(ReportEcheancePage.this.uiManager.stateMachine, "Votre demande est enregistrée \nPrière de vous rendre dans les 48 heures à votre agence CihBank pour signer la demande de report.", null);
                    return;
                }
            }
            ReportEcheancePage.this.uiManager.ShowMessageTransaction(ReportEcheancePage.this.uiManager.stateMachine, ReportEchanceProcess.getStatusLabel(), null);
        }
    }

    public static String getTrd() {
        String trim = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().langue.trim();
        trim.hashCode();
        switch (trim) {
            case "AR":
                return "  3أشهر ";
            case "EN":
                return " 3 months";
            case "ES":
                return " 3 meses";
            case "FR":
            default:
                return " 3 mois";
        }
    }
}
