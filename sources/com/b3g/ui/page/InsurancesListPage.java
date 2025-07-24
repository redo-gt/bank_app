package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Inssurance;
import com.b3g.services.InssuranceSimulation;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class InsurancesListPage extends B3GPage {
    DateFormat df;

    private int getSeuilMontant(int i, int i2) {
        if (i2 == 4) {
            return 10000;
        }
        if (i == 1) {
            return 200;
        }
        if (i == 2) {
            return 600;
        }
        if (i != 3) {
            return i != 4 ? 0 : 2400;
        }
        return 1200;
    }

    public String frontBack(String str) {
        return str;
    }

    static /* synthetic */ int access$000(InsurancesListPage insurancesListPage, int i, int i2) {
        return insurancesListPage.getSeuilMontant(i, i2);
    }

    static /* synthetic */ boolean access$100(InsurancesListPage insurancesListPage, String str) {
        return insurancesListPage.getPasMontant(str);
    }

    static /* synthetic */ boolean access$200(InsurancesListPage insurancesListPage, Date date, Date date2, String str) {
        return insurancesListPage.CalculAge(date, date2, str);
    }

    static /* synthetic */ String access$300(InsurancesListPage insurancesListPage, String str) {
        return insurancesListPage.FillProductCode(str);
    }

    static /* synthetic */ String access$400(InsurancesListPage insurancesListPage, String str) {
        return insurancesListPage.FillPeriodicity(str);
    }

    public InsurancesListPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        int i;
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            ArrayList InssuranceProcess = new Inssurance().InssuranceProcess();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(this.uiManager.DrawListContainer("GloabalContainerV2", "", Boolean.TRUE, InssuranceProcess, InssuranceProcess.size(), 10, CihMBanking.sesPMTR.getSessionClient().getClient_InsuranceList_StatusLabel(), null, null, null, null), "Ma bancassurance"));
            arrayList.add(new B3gContainer(calcCapitalCnt(), "Simulation Capital"));
            this.uiManager.DrawTabsWithNavigation(container, arrayList);
            this.thisContainer.addComponent(container);
            Container container2 = new Container();
            container2.setUIID("g_CntTranspMsg");
            this.thisContainer.addComponent(container2);
            i = 0;
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            i = 0;
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, i);
        return this.thisContainer;
    }

    private Container calcCapitalCnt() {
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "calculCapital");
        Container container = (Container) uIBuilder.findByName("datePremVersCnt", createContainer);
        Container container2 = (Container) uIBuilder.findByName("dateEcheCnt", createContainer);
        Container container3 = (Container) uIBuilder.findByName("dateNaissCnt", createContainer);
        Container container4 = (Container) uIBuilder.findByName("periodiciteCnt", createContainer);
        Button button = (Button) uIBuilder.findByName("calculerBtn", createContainer);
        TextField textField = (TextField) uIBuilder.findByName("monatntVersTxtF", createContainer);
        ComboBox comboBox = (ComboBox) uIBuilder.findByName("codeProduitCombo", createContainer);
        ComboBox comboBox2 = (ComboBox) uIBuilder.findByName("PreiodCombo", createContainer);
        Picker picker = new Picker();
        picker.setType(1);
        picker.setUIID("border_grey_1_1_1_1");
        Picker picker2 = new Picker();
        picker2.setType(1);
        picker2.setUIID("border_grey_1_1_1_1");
        Picker picker3 = new Picker();
        picker3.setType(1);
        picker3.setUIID("border_grey_1_1_1_1");
        container.addComponent(picker);
        picker.getDate();
        container2.addComponent(picker2);
        container3.addComponent(picker3);
        this.df = new SimpleDateFormat("dd/MM/yyyy");
        comboBox.addSelectionListener(new 1(container4, container2, createContainer));
        button.addActionListener(new 2(textField, comboBox, comboBox2, picker2, picker3, picker));
        return createContainer;
    }

    class 1 implements SelectionListener {
        final /* synthetic */ Container val$calculCapitalCnt;
        final /* synthetic */ Container val$dateEcheCnt;
        final /* synthetic */ Container val$periodiciteCnt;

        1(Container container, Container container2, Container container3) {
            this.val$periodiciteCnt = container;
            this.val$dateEcheCnt = container2;
            this.val$calculCapitalCnt = container3;
        }

        public void selectionChanged(int i, int i2) {
            if (i2 == 4) {
                this.val$periodiciteCnt.setHidden(true);
                this.val$dateEcheCnt.setHidden(false);
            } else if (i2 == 2) {
                this.val$dateEcheCnt.setHidden(false);
                this.val$periodiciteCnt.setHidden(false);
            } else {
                this.val$periodiciteCnt.setHidden(false);
                this.val$dateEcheCnt.setHidden(false);
            }
            this.val$calculCapitalCnt.revalidate();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ ComboBox val$PreiodCombo;
        final /* synthetic */ ComboBox val$codeProduitCombo;
        final /* synthetic */ Picker val$dateEchePicker;
        final /* synthetic */ Picker val$dateNaissPicker;
        final /* synthetic */ Picker val$datePremVersPicker;
        final /* synthetic */ TextField val$monatntVersTxtF;

        2(TextField textField, ComboBox comboBox, ComboBox comboBox2, Picker picker, Picker picker2, Picker picker3) {
            this.val$monatntVersTxtF = textField;
            this.val$codeProduitCombo = comboBox;
            this.val$PreiodCombo = comboBox2;
            this.val$dateEchePicker = picker;
            this.val$dateNaissPicker = picker2;
            this.val$datePremVersPicker = picker3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$monatntVersTxtF.getText().length() == 0 || this.val$codeProduitCombo.getSelectedIndex() == 0) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(InsurancesListPage.this.uiManager.stateMachine, "Veuillez saisir l'ensemble des champs obligatoires", null);
                return;
            }
            if (Integer.parseInt(this.val$monatntVersTxtF.getText()) < InsurancesListPage.access$000(InsurancesListPage.this, this.val$PreiodCombo.getSelectedIndex(), this.val$codeProduitCombo.getSelectedIndex())) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(InsurancesListPage.this.uiManager.stateMachine, "Le montant est inférieur au montant minimum autorisé. Veuillez vérifier le montant à transférer.", null);
                return;
            }
            if (!InsurancesListPage.access$100(InsurancesListPage.this, this.val$monatntVersTxtF.getText())) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(InsurancesListPage.this.uiManager.stateMachine, "Les valeurs des versements augmentent par palier de 100 dhs.", null);
                return;
            }
            if (InsurancesListPage.access$200(InsurancesListPage.this, this.val$dateEchePicker.getDate(), this.val$dateNaissPicker.getDate(), (String) this.val$codeProduitCombo.getSelectedItem())) {
                return;
            }
            InsurancesListPage.this.ShowcalcCapitalDetailPopUp((String) this.val$codeProduitCombo.getSelectedItem(), (String) this.val$PreiodCombo.getSelectedItem(), this.val$monatntVersTxtF.getText(), InsurancesListPage.this.df.format(this.val$datePremVersPicker.getDate()), InsurancesListPage.this.df.format(this.val$dateNaissPicker.getDate()), InsurancesListPage.this.df.format(this.val$dateEchePicker.getDate()), ((InssuranceSimulation) new InssuranceSimulation().InssuranceCotisationProcess(InsurancesListPage.access$300(InsurancesListPage.this, (String) this.val$codeProduitCombo.getSelectedItem()), InsurancesListPage.access$400(InsurancesListPage.this, (String) this.val$PreiodCombo.getSelectedItem()), this.val$monatntVersTxtF.getText(), InsurancesListPage.this.df.format(this.val$datePremVersPicker.getDate()), InsurancesListPage.this.df.format(this.val$dateNaissPicker.getDate()), InsurancesListPage.this.df.format(this.val$dateEchePicker.getDate())).get(0)).message + "");
            Date date = new Date();
            this.val$monatntVersTxtF.clear();
            this.val$codeProduitCombo.setSelectedIndex(0);
            this.val$PreiodCombo.setSelectedIndex(0);
            try {
                this.val$dateEchePicker.setDate(InsurancesListPage.this.df.parse(InsurancesListPage.this.df.format(date)));
                this.val$dateNaissPicker.setDate(InsurancesListPage.this.df.parse(InsurancesListPage.this.df.format(date)));
                this.val$datePremVersPicker.setDate(InsurancesListPage.this.df.parse(InsurancesListPage.this.df.format(date)));
            } catch (ParseException unused) {
            }
        }
    }

    public void ShowcalcCapitalDetailPopUp(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
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
        Container createContainer = uIBuilder.createContainer("/cihv3", "calculCapitalDetail");
        Label label = (Label) uIBuilder.findByName("produitLbl", createContainer);
        Label label2 = (Label) uIBuilder.findByName("periodLbl", createContainer);
        Label label3 = (Label) uIBuilder.findByName("montantLbl", createContainer);
        Label label4 = (Label) uIBuilder.findByName("dateVersLbl", createContainer);
        Label label5 = (Label) uIBuilder.findByName("dateNaissLbl", createContainer);
        Label label6 = (Label) uIBuilder.findByName("dateEcheLbl", createContainer);
        Label label7 = (Label) uIBuilder.findByName("capitalLbl", createContainer);
        Button button = (Button) uIBuilder.findByName("closeBtn", createContainer);
        label.setText(str);
        label2.setText(str2);
        label3.setText(str3);
        label4.setText(str4);
        label5.setText(str5);
        label6.setText(str6);
        label7.setText(str7);
        button.addActionListener(new 3(dialog));
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
        createContainer.setShouldCalcPreferredSize(true);
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", false);
    }

    class 3 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        3(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    private String FillPeriodicity(String str) {
        str.equals("Mensuelle");
        String str2 = str.equals("Trimestrielle") ? "2" : "1";
        if (str.equals("Semestrielle")) {
            str2 = "3";
        }
        return str.equals("Annuelle") ? "4" : str2;
    }

    private String FillProductCode(String str) {
        str.equals("Avenir Retraite");
        String str2 = str.equals("Avenir Education") ? "481" : "291";
        if (str.equals("Avenir Epargne")) {
            str2 = "255";
        }
        return str.equals("Avenir Epargne Plus") ? "265" : str2;
    }

    private boolean getPasMontant(String str) {
        StringBuilder sb = new StringBuilder(frontBack(str));
        int length = str.trim().length();
        return sb.charAt(length + (-1)) == '0' && sb.charAt(length + (-2)) == '0';
    }

    private boolean CalculAge(Date date, Date date2, String str) {
        boolean z;
        int time = (int) ((date.getTime() - date2.getTime()) / 86400000);
        if (!str.equals("Avenir Retraite") || time <= 23725) {
            z = false;
        } else {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Pour bénéficier de ce produit vous devez avoir moins de 65 ans.", null);
            z = true;
        }
        if (!str.equals("Avenir Education") || (time >= 6570 && time <= 9125)) {
            return z;
        }
        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Pour bénéficier de ce produit vous devez être entre 18 et 25 ans.", null);
        return true;
    }
}
