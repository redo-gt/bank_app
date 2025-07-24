package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.EDocuments;
import com.b3g.services.ServiceManager;
import com.b3g.tools.NativeCall;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.RateUs;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.util.Base64;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MyEDocumentsPageNew extends B3GPage {
    Picker pckTypDoc = new Picker();
    ArrayList Edocs = new ArrayList();
    EDocuments edoc = new EDocuments();
    ServiceResponse sr = new ServiceResponse();

    public MyEDocumentsPageNew(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("EURO (EUR)", "EUR");
        linkedHashMap.put("DOLLAR  AMERICAIN (USD)", "USD");
        linkedHashMap.put("DOLLAR  CANADIEN (CAD)", "CAD");
        linkedHashMap.put("LIVRE  STERLING (GBP)", "GBP");
        linkedHashMap.put("COURONNES  DANOISES (DKK)", "DKK");
        linkedHashMap.put("COURONNES  NORVEGIENNES (NOK)", "NOK");
        linkedHashMap.put("COURONNES  SUEDOISES (SEK)", "SEK");
        linkedHashMap.put("FRANCS  SUISSE (CHF)", "CHF");
        linkedHashMap.put("DINAR  BAHREINI (BHD)", "BHD");
        linkedHashMap.put("DIRHAM  EMIRATS  ARABES  UNIS (AED)", "AED");
        linkedHashMap.put("LIVRE  GIBRALTAR (GIP)", "GIP");
        linkedHashMap.put("RIAL  OMANAIS (OMR)", "OMR");
        linkedHashMap.put("RIYAL  QATARI (QAR)", "QAR");
        linkedHashMap.put("RIYAL  SAOUDIEN (SAR)", "SAR");
        linkedHashMap.put("YEN  JAPONAIS (JPY)", "JPY");
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BorderLayout());
        this.thisContainer.setUIID("mn_cntMenuItemCopy");
        Container container = new Container(new BoxLayout(2));
        Label label = new Label("E-Documents");
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        container.add(label);
        Container container2 = new Container(new BoxLayout(2));
        container2.getAllStyles().setMargin(1, 2);
        container2.getAllStyles().setMargin(3, 2);
        Label label2 = new Label("Type de document :");
        label2.setUIID("g_lblNotif");
        container2.add(label2);
        container2.add(drawPicker(this.pckTypDoc, new String[]{"Relevé Bancaire", "Avis d'opération", "Formules", "Transfert à l'international"}, "Type de document"));
        container.add(container2);
        Container container3 = new Container(new BoxLayout(2));
        container3.getAllStyles().setMargin(1, 2);
        container3.getAllStyles().setMargin(3, 2);
        container3.setScrollableY(true);
        Container container4 = new Container(new BoxLayout(2));
        this.pckTypDoc.addActionListener(new MyEDocumentsPageNew$$ExternalSyntheticLambda3(this, container3, container4, linkedHashMap));
        this.thisContainer.add("North", container);
        this.thisContainer.add("Center", container3);
        this.thisContainer.add("South", container4);
        return this.thisContainer;
    }

    /* synthetic */ void lambda$GetPageContainer$4$com-b3g-ui-page-MyEDocumentsPageNew(Container container, Container container2, Map map, ActionEvent actionEvent) {
        container.removeAll();
        container2.removeAll();
        int selectedStringIndex = this.pckTypDoc.getSelectedStringIndex();
        if (selectedStringIndex == 0) {
            Label label = new Label("N° de compte :");
            label.setUIID("g_lblNotif");
            container.add(label);
            Picker picker = new Picker();
            container.add(drawPicker(picker, RecupAccNmbr(), "N° de compte"));
            Label label2 = new Label("Date de l'opération :");
            label2.setUIID("g_lblNotif");
            container.add(label2);
            Picker picker2 = new Picker();
            container.add(drawDateRBPicker(picker2));
            container2.add(cntBtnDwnld(picker2, null, null, null, null, null, picker, null, null));
            return;
        }
        if (selectedStringIndex == 1) {
            Label label3 = new Label("Type d'avis d'opération :");
            label3.setUIID("g_lblNotif");
            container.add(label3);
            Picker picker3 = new Picker();
            container.add(drawPicker(picker3, new String[]{"Avis d'opération à l'international"}, "Type d'avis d'opération"));
            Container container3 = new Container(new BoxLayout(2));
            container.add(container3);
            picker3.addActionListener(new MyEDocumentsPageNew$$ExternalSyntheticLambda0(this, container3, container2, picker3, map));
            return;
        }
        if (selectedStringIndex == 2) {
            Label label4 = new Label("Type Formule :");
            label4.setUIID("g_lblNotif");
            container.add(label4);
            Picker picker4 = new Picker();
            container.add(drawPicker(picker4, new String[]{"Vente de devise", "Achat de devise"}, "Type Formule"));
            Container container4 = new Container(new BoxLayout(2));
            container.add(container4);
            picker4.addActionListener(new MyEDocumentsPageNew$$ExternalSyntheticLambda1(this, container4, container2, picker4, map));
            return;
        }
        if (selectedStringIndex != 3) {
            return;
        }
        Label label5 = new Label("Nature de document :");
        label5.setUIID("g_lblNotif");
        container.add(label5);
        Picker picker5 = new Picker();
        container.add(drawPicker(picker5, new String[]{"SWIFT"}, "Nature de document :"));
        Container container5 = new Container(new BoxLayout(2));
        container.add(container5);
        picker5.addActionListener(new MyEDocumentsPageNew$$ExternalSyntheticLambda2(this, container5, container2, picker5, map));
    }

    /* synthetic */ void lambda$GetPageContainer$0$com-b3g-ui-page-MyEDocumentsPageNew(Container container, Container container2, Picker picker, Map map, ActionEvent actionEvent) {
        container.removeAll();
        container2.removeAll();
        if (picker.getSelectedStringIndex() != 0) {
            return;
        }
        Container container3 = new Container(new BoxLayout(2));
        Container container4 = new Container(new GridLayout(1, 2));
        Picker picker2 = new Picker();
        Container container5 = new Container(new BoxLayout(2));
        container5.getAllStyles().setMargin(3, 2);
        Label label = new Label("Date Début");
        label.setUIID("g_lblNotif");
        container5.add(label);
        container5.add(drawDatePicker(picker2));
        container4.add(container5);
        Picker picker3 = new Picker();
        Container container6 = new Container(new BoxLayout(2));
        Label label2 = new Label("Date Fin");
        label2.setUIID("g_lblNotif");
        container6.add(label2);
        container6.add(drawDatePicker(picker3));
        container4.add(container6);
        container3.add(container4);
        Container container7 = new Container(new GridLayout(1, 2));
        Container container8 = new Container(new BoxLayout(2));
        Label label3 = new Label("Montant Min :");
        label3.setUIID("g_lblNotif");
        container8.add(label3);
        container7.add(container8);
        Container container9 = new Container(new BoxLayout(2));
        Label label4 = new Label("Montant Max :");
        label4.setUIID("g_lblNotif");
        container9.add(label4);
        container7.add(container9);
        container3.add(container7);
        Container container10 = new Container(new GridLayout(1, 2));
        Container container11 = new Container(new FlowLayout());
        container11.getAllStyles().setMargin(3, 2);
        Container container12 = new Container(new FlowLayout());
        TextField textField = new TextField("", "", 20, 2);
        textField.setUIID("border_grey_1_1_1_1Copy");
        TextField textField2 = new TextField("", "", 20, 2);
        textField2.setUIID("border_grey_1_1_1_1Copy");
        container11.add(textField);
        container12.add(textField2);
        container10.add(container11).add(container12);
        container3.add(container10);
        Label label5 = new Label("Devise :");
        label5.setUIID("g_lblNotif");
        container3.add(label5);
        Picker picker4 = new Picker();
        container3.add(drawPickerDevise(picker4, map));
        container.add(container3);
        container2.add(cntBtnDwnld(picker2, picker3, textField, textField2, picker4, map, null, picker, null));
    }

    /* synthetic */ void lambda$GetPageContainer$1$com-b3g-ui-page-MyEDocumentsPageNew(Container container, Container container2, Picker picker, Map map, ActionEvent actionEvent) {
        container.removeAll();
        container2.removeAll();
        int selectedStringIndex = picker.getSelectedStringIndex();
        if (selectedStringIndex == 0 || selectedStringIndex == 1) {
            Container container3 = new Container(new BoxLayout(2));
            Container container4 = new Container(new GridLayout(1, 2));
            Picker picker2 = new Picker();
            Container container5 = new Container(new BoxLayout(2));
            container5.getAllStyles().setMargin(3, 2);
            Label label = new Label("Date Début");
            label.setUIID("g_lblNotif");
            container5.add(label);
            container5.add(drawDatePicker(picker2));
            container4.add(container5);
            Picker picker3 = new Picker();
            Container container6 = new Container(new BoxLayout(2));
            Label label2 = new Label("Date Fin");
            label2.setUIID("g_lblNotif");
            container6.add(label2);
            container6.add(drawDatePicker(picker3));
            container4.add(container6);
            container3.add(container4);
            Container container7 = new Container(new GridLayout(1, 2));
            Container container8 = new Container(new BoxLayout(2));
            Label label3 = new Label("Montant Min :");
            label3.setUIID("g_lblNotif");
            container8.add(label3);
            container7.add(container8);
            Container container9 = new Container(new BoxLayout(2));
            Label label4 = new Label("Montant Max :");
            label4.setUIID("g_lblNotif");
            container9.add(label4);
            container7.add(container9);
            container3.add(container7);
            Container container10 = new Container(new GridLayout(1, 2));
            Container container11 = new Container(new FlowLayout());
            container11.getAllStyles().setMargin(3, 2);
            Container container12 = new Container(new FlowLayout());
            TextField textField = new TextField("", "", 20, 2);
            textField.setUIID("border_grey_1_1_1_1Copy");
            TextField textField2 = new TextField("", "", 20, 2);
            textField2.setUIID("border_grey_1_1_1_1Copy");
            container11.add(textField);
            container12.add(textField2);
            container10.add(container11).add(container12);
            container3.add(container10);
            Label label5 = new Label("Devise :");
            label5.setUIID("g_lblNotif");
            container3.add(label5);
            Picker picker4 = new Picker();
            container3.add(drawPickerDevise(picker4, map));
            container.add(container3);
            container2.add(cntBtnDwnld(picker2, picker3, textField, textField2, picker4, map, null, picker, null));
        }
    }

    /* synthetic */ void lambda$GetPageContainer$3$com-b3g-ui-page-MyEDocumentsPageNew(Container container, Container container2, Picker picker, Map map, ActionEvent actionEvent) {
        container.removeAll();
        container2.removeAll();
        if (picker.getSelectedStringIndex() != 0) {
            return;
        }
        Container container3 = new Container(new BoxLayout(2));
        Label label = new Label("Évènement :");
        label.setUIID("g_lblNotif");
        container3.add(label);
        Picker picker2 = new Picker();
        container3.add(drawPicker(picker2, new String[]{"Transfert ÉMIS/REÇU"}, "Évènement :"));
        Container container4 = new Container(new BoxLayout(2));
        container3.add(container4);
        picker2.addActionListener(new MyEDocumentsPageNew$$ExternalSyntheticLambda4(this, container4, container2, picker2, map, picker));
        container.add(container3);
    }

    /* synthetic */ void lambda$GetPageContainer$2$com-b3g-ui-page-MyEDocumentsPageNew(Container container, Container container2, Picker picker, Map map, Picker picker2, ActionEvent actionEvent) {
        container.removeAll();
        container2.removeAll();
        int selectedStringIndex = picker.getSelectedStringIndex();
        if (selectedStringIndex != 0) {
            if (selectedStringIndex == 1) {
                Container container3 = new Container(new GridLayout(1, 2));
                Picker picker3 = new Picker();
                Container container4 = new Container(new BoxLayout(2));
                container4.getAllStyles().setMargin(3, 2);
                Label label = new Label("Date Début");
                label.setUIID("g_lblNotif");
                container4.add(label);
                container4.add(drawDatePicker(picker3));
                container3.add(container4);
                Picker picker4 = new Picker();
                Container container5 = new Container(new BoxLayout(2));
                Label label2 = new Label("Date Fin");
                label2.setUIID("g_lblNotif");
                container5.add(label2);
                container5.add(drawDatePicker(picker4));
                container3.add(container5);
                container.add(container3);
                container2.add(cntBtnDwnld(picker3, picker4, null, null, null, null, null, picker2, picker));
                return;
            }
            if (selectedStringIndex != 2) {
                return;
            }
        }
        Container container6 = new Container(new BoxLayout(2));
        Container container7 = new Container(new GridLayout(1, 2));
        Picker picker5 = new Picker();
        Container container8 = new Container(new BoxLayout(2));
        container8.getAllStyles().setMargin(3, 2);
        Label label3 = new Label("Date Début");
        label3.setUIID("g_lblNotif");
        container8.add(label3);
        container8.add(drawDatePicker(picker5));
        container7.add(container8);
        Picker picker6 = new Picker();
        Container container9 = new Container(new BoxLayout(2));
        Label label4 = new Label("Date Fin");
        label4.setUIID("g_lblNotif");
        container9.add(label4);
        container9.add(drawDatePicker(picker6));
        container7.add(container9);
        container6.add(container7);
        Container container10 = new Container(new GridLayout(1, 2));
        Container container11 = new Container(new BoxLayout(2));
        Label label5 = new Label("Montant Min :");
        label5.setUIID("g_lblNotif");
        container11.add(label5);
        container10.add(container11);
        Container container12 = new Container(new BoxLayout(2));
        Label label6 = new Label("Montant Max :");
        label6.setUIID("g_lblNotif");
        container12.add(label6);
        container10.add(container12);
        container6.add(container10);
        Container container13 = new Container(new GridLayout(1, 2));
        Container container14 = new Container(new FlowLayout());
        container14.getAllStyles().setMargin(3, 2);
        Container container15 = new Container(new FlowLayout());
        TextField textField = new TextField("", "", 20, 2);
        textField.setUIID("border_grey_1_1_1_1Copy");
        TextField textField2 = new TextField("", "", 20, 2);
        textField2.setUIID("border_grey_1_1_1_1Copy");
        container14.add(textField);
        container15.add(textField2);
        container13.add(container14).add(container15);
        container6.add(container13);
        Label label7 = new Label("Devise :");
        label7.setUIID("g_lblNotif");
        container6.add(label7);
        Picker picker7 = new Picker();
        container6.add(drawPickerDevise(picker7, map));
        container.add(container6);
        container2.add(cntBtnDwnld(picker5, picker6, textField, textField2, picker7, map, null, picker2, picker));
    }

    private Container drawPicker(Picker picker, Object[] objArr, String str) {
        Container container = new Container(new BorderLayout());
        container.setUIID("border_grey_1_1_1_1Copy");
        picker.setType(4);
        picker.setUIID("lbl_regular_bold_");
        if (objArr != null) {
            picker.setStrings((String[]) objArr);
        }
        picker.setSelectedString(str);
        picker.setAlignment(4);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("newListBox.png"));
        container.add("Center", picker).add("East", label);
        container.setLeadComponent(picker);
        return container;
    }

    private Container drawPickerDevise(Picker picker, Map map) {
        Container container = new Container(new BorderLayout());
        container.setUIID("border_grey_1_1_1_1Copy");
        picker.setType(4);
        picker.setUIID("lbl_regular_bold_");
        String[] strArr = new String[map.size()];
        for (int i = 0; i < map.size(); i++) {
            strArr[i] = (String) map.keySet().toArray()[i];
        }
        picker.setStrings(strArr);
        picker.setSelectedString(strArr[0]);
        picker.setAlignment(4);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("newListBox.png"));
        container.add("Center", picker).add("East", label);
        container.setLeadComponent(picker);
        return container;
    }

    private Container drawDatePicker(Picker picker) {
        Container container = new Container(new BorderLayout());
        container.setUIID("border_grey_1_1_1_1Copy");
        picker.setAlignment(4);
        picker.setUIID("lbl_regular_bold_center");
        picker.setText("- - -");
        Label label = new Label();
        label.setIcon(this.uiManager.ressource.getImage("newListBox.png"));
        container.add("Center", picker).add("East", label);
        return container;
    }

    private Container drawDateRBPicker(Picker picker) {
        Container container = new Container(new BorderLayout());
        container.setUIID("border_grey_1_1_1_1Copy");
        picker.setFormatter(new SimpleDateFormat("MM-yyyy"));
        picker.setAlignment(4);
        picker.setUIID("lbl_regular_bold_center");
        picker.setText("- - -");
        Label label = new Label();
        label.setIcon(this.uiManager.ressource.getImage("newListBox.png"));
        container.add("Center", picker).add("East", label);
        return container;
    }

    public String[] RecupAccNmbr() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList());
        arrayList.addAll(CihMBanking.sesPMTR.getSessionClient().getListRelationAccount());
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = ((Account) arrayList.get(i)).accountNumber;
        }
        return strArr;
    }

    private Container cntBtnDwnld(Picker picker, Picker picker2, TextField textField, TextField textField2, Picker picker3, Map map, Picker picker4, Picker picker5, Picker picker6) {
        Container container = new Container(new BoxLayout(2));
        container.getAllStyles().setMargin(2, 2);
        container.getAllStyles().setMargin(1, 2);
        container.getAllStyles().setMargin(3, 2);
        Button button = new Button();
        button.setText("Télécharger");
        button.setUIID("Orange_Btn");
        button.addActionListener(new 1(picker, picker4, picker3, map, picker5, picker2, textField, textField2, picker6));
        container.add(button);
        return container;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Picker val$acc_Number;
        final /* synthetic */ TextField val$amnt_Max;
        final /* synthetic */ TextField val$amnt_Min;
        final /* synthetic */ Picker val$date_Debut;
        final /* synthetic */ Picker val$date_Fin;
        final /* synthetic */ Map val$mapDevise;
        final /* synthetic */ Picker val$pckChoice;
        final /* synthetic */ Picker val$pckDevise;
        final /* synthetic */ Picker val$pck_Choice;

        1(Picker picker, Picker picker2, Picker picker3, Map map, Picker picker4, Picker picker5, TextField textField, TextField textField2, Picker picker6) {
            this.val$date_Debut = picker;
            this.val$acc_Number = picker2;
            this.val$pckDevise = picker3;
            this.val$mapDevise = map;
            this.val$pck_Choice = picker4;
            this.val$date_Fin = picker5;
            this.val$amnt_Min = textField;
            this.val$amnt_Max = textField2;
            this.val$pckChoice = picker6;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                int selectedStringIndex = MyEDocumentsPageNew.this.pckTypDoc.getSelectedStringIndex();
                String str = "";
                if (selectedStringIndex == 0) {
                    String format = new SimpleDateFormat("MM-yyyy").format(this.val$date_Debut.getDate());
                    if ("N° de compte".equals(this.val$acc_Number.getSelectedString()) || this.val$date_Debut.getText().equals("- - -")) {
                        MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                        return;
                    }
                    FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
                    String str2 = format.substring(3, format.length()) + "-" + format.substring(0, 2);
                    ServiceResponse transfExtraitProcess = MyEDocumentsPageNew.this.transfExtraitProcess(this.val$acc_Number.getSelectedString(), str2);
                    if (transfExtraitProcess.getStatusCode().equals("000")) {
                        byte[] decode = Base64.decode(transfExtraitProcess.getStatusLabel().getBytes(), transfExtraitProcess.getStatusLabel().getBytes().length);
                        if (!Display.getInstance().getPlatformName().equals("ios")) {
                            ((NativeCall) NativeLookup.create(NativeCall.class)).createPdfFromByteArray(decode, "" + this.val$acc_Number.getText() + str2 + ".pdf");
                        } else {
                            String str3 = fileSystemStorage.getAppHomePath() + "" + this.val$acc_Number.getText() + str2 + ".pdf";
                            try {
                                OutputStream openOutputStream = fileSystemStorage.openOutputStream(str3);
                                try {
                                    openOutputStream.write(decode);
                                    if (openOutputStream != null) {
                                        openOutputStream.close();
                                    }
                                } finally {
                                }
                            } catch (IOException unused) {
                                MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Une erreur s'est produite , veuillez réessayer plus tard", null);
                            }
                            Display.getInstance().execute(str3);
                        }
                        new RateUs(MyEDocumentsPageNew.this.uiManager).init();
                        return;
                    }
                    MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Le service est momentanément indisponible. Veuillez réessayer ultérieurement.", null);
                    return;
                }
                if (selectedStringIndex == 1) {
                    String obj = this.val$mapDevise.values().toArray()[this.val$pckDevise.getSelectedStringIndex()].toString();
                    if (this.val$pck_Choice.getSelectedStringIndex() != 0) {
                        return;
                    }
                    if (MyEDocumentsPageNew.this.compare_date(this.val$date_Debut.getText(), this.val$date_Fin.getText()) == 1 || this.val$date_Debut.getText().equals("- - -") || this.val$date_Fin.getText().equals("- - -")) {
                        MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Date choisie invalide", null);
                        return;
                    }
                    if (this.val$amnt_Min.getText().length() == 0 || this.val$amnt_Max.getText().length() == 0 || Integer.parseInt(this.val$amnt_Min.getText()) > Integer.parseInt(this.val$amnt_Max.getText())) {
                        MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Montant saisi invalide", null);
                        return;
                    }
                    MyEDocumentsPageNew myEDocumentsPageNew = MyEDocumentsPageNew.this;
                    myEDocumentsPageNew.Edocs = myEDocumentsPageNew.edoc.EdocAOP(this.val$date_Debut.getText(), this.val$date_Fin.getText(), this.val$amnt_Min.getText(), this.val$amnt_Max.getText(), obj, MyEDocumentsPageNew.this.RecupAccNmbr()[0]);
                    if (MyEDocumentsPageNew.this.Edocs == null) {
                        MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Une erreur s'est produite , veuillez réessayer plus tard", null);
                        return;
                    } else {
                        MyEDocumentsPageNew.this.dwld_pdf();
                        return;
                    }
                }
                if (selectedStringIndex == 2) {
                    String obj2 = this.val$mapDevise.values().toArray()[this.val$pckDevise.getSelectedStringIndex()].toString();
                    int selectedStringIndex2 = this.val$pck_Choice.getSelectedStringIndex();
                    if (selectedStringIndex2 == 0 || selectedStringIndex2 == 1) {
                        if (MyEDocumentsPageNew.this.compare_date(this.val$date_Debut.getText(), this.val$date_Fin.getText()) == 1 || this.val$date_Debut.getText().equals("- - -") || this.val$date_Fin.getText().equals("- - -")) {
                            MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Date choisie invalide", null);
                            return;
                        }
                        if (this.val$amnt_Min.getText().length() == 0 || this.val$amnt_Max.getText().length() == 0 || Integer.parseInt(this.val$amnt_Min.getText()) > Integer.parseInt(this.val$amnt_Max.getText())) {
                            MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Montant saisi invalide", null);
                            return;
                        }
                        int selectedStringIndex3 = this.val$pck_Choice.getSelectedStringIndex();
                        if (selectedStringIndex3 == 0) {
                            str = "F1";
                        } else if (selectedStringIndex3 == 1) {
                            str = "F2";
                        }
                        String str4 = str;
                        MyEDocumentsPageNew myEDocumentsPageNew2 = MyEDocumentsPageNew.this;
                        myEDocumentsPageNew2.Edocs = myEDocumentsPageNew2.edoc.EdocFormule(this.val$date_Debut.getText(), this.val$date_Fin.getText(), this.val$amnt_Min.getText(), this.val$amnt_Max.getText(), obj2, str4);
                        if (MyEDocumentsPageNew.this.Edocs == null) {
                            MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Une erreur s'est produite , veuillez réessayer plus tard", null);
                            return;
                        } else {
                            MyEDocumentsPageNew.this.dwld_pdf();
                            return;
                        }
                    }
                    return;
                }
                if (selectedStringIndex == 3 && this.val$pck_Choice.getSelectedStringIndex() == 0) {
                    int selectedStringIndex4 = this.val$pckChoice.getSelectedStringIndex();
                    if (selectedStringIndex4 == 0) {
                        String obj3 = this.val$mapDevise.values().toArray()[this.val$pckDevise.getSelectedStringIndex()].toString();
                        if (MyEDocumentsPageNew.this.compare_date(this.val$date_Debut.getText(), this.val$date_Fin.getText()) == 1 || this.val$date_Debut.getText().equals("- - -") || this.val$date_Fin.getText().equals("- - -")) {
                            MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Date choisie invalide", null);
                            return;
                        }
                        if (this.val$amnt_Min.getText().length() == 0 || this.val$amnt_Max.getText().length() == 0 || Integer.parseInt(this.val$amnt_Min.getText()) > Integer.parseInt(this.val$amnt_Max.getText())) {
                            MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Montant saisi invalide", null);
                            return;
                        }
                        MyEDocumentsPageNew myEDocumentsPageNew3 = MyEDocumentsPageNew.this;
                        myEDocumentsPageNew3.Edocs = myEDocumentsPageNew3.edoc.EdocSwift(this.val$date_Debut.getText(), this.val$date_Fin.getText(), this.val$amnt_Min.getText(), this.val$amnt_Max.getText(), obj3, "I700", "CDI");
                        if (MyEDocumentsPageNew.this.Edocs == null) {
                            MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Une erreur s'est produite , veuillez réessayer plus tard", null);
                            return;
                        } else {
                            MyEDocumentsPageNew.this.dwld_pdf();
                            return;
                        }
                    }
                    if (selectedStringIndex4 == 1) {
                        if (MyEDocumentsPageNew.this.compare_date(this.val$date_Debut.getText(), this.val$date_Fin.getText()) == 1 || this.val$date_Debut.getText().equals("- - -") || this.val$date_Fin.getText().equals("- - -")) {
                            MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Date choisie invalide", null);
                            return;
                        }
                        MyEDocumentsPageNew myEDocumentsPageNew4 = MyEDocumentsPageNew.this;
                        myEDocumentsPageNew4.Edocs = myEDocumentsPageNew4.edoc.EdocSwift(this.val$date_Debut.getText(), this.val$date_Fin.getText(), "", "", "", "I707", "CDI");
                        if (MyEDocumentsPageNew.this.Edocs == null) {
                            MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Une erreur s'est produite , veuillez réessayer plus tard", null);
                            return;
                        } else {
                            MyEDocumentsPageNew.this.dwld_pdf();
                            return;
                        }
                    }
                    if (selectedStringIndex4 != 2) {
                        return;
                    }
                    String obj4 = this.val$mapDevise.values().toArray()[this.val$pckDevise.getSelectedStringIndex()].toString();
                    if (MyEDocumentsPageNew.this.compare_date(this.val$date_Debut.getText(), this.val$date_Fin.getText()) == 1 || this.val$date_Debut.getText().equals("- - -") || this.val$date_Fin.getText().equals("- - -")) {
                        MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Date choisie invalide", null);
                        return;
                    }
                    if (this.val$amnt_Min.getText().length() == 0 || this.val$amnt_Max.getText().length() == 0 || Integer.parseInt(this.val$amnt_Min.getText()) > Integer.parseInt(this.val$amnt_Max.getText())) {
                        MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Montant saisi invalide", null);
                        return;
                    }
                    MyEDocumentsPageNew myEDocumentsPageNew5 = MyEDocumentsPageNew.this;
                    myEDocumentsPageNew5.Edocs = myEDocumentsPageNew5.edoc.EdocSwift(this.val$date_Debut.getText(), this.val$date_Fin.getText(), this.val$amnt_Min.getText(), this.val$amnt_Max.getText(), obj4, "I202", "CDI");
                    if (MyEDocumentsPageNew.this.Edocs == null) {
                        MyEDocumentsPageNew.this.uiManager.ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Une erreur s'est produite , veuillez réessayer plus tard", null);
                        return;
                    } else {
                        MyEDocumentsPageNew.this.dwld_pdf();
                        return;
                    }
                }
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MyEDocumentsPageNew.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }
    }

    public ServiceResponse transfExtraitProcess(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("ACOUNT_NUMBER", str);
        hashtable.put("START_DATE", str2);
        hashtable.put("EMAIL", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email);
        hashtable.put("SERVICEID", "02");
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900084);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public FileSystemStorage dwld_pdf() {
        FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
        if (!this.Edocs.isEmpty() && this.Edocs != null) {
            for (int i = 0; i < this.Edocs.size(); i++) {
                String str = ((EDocuments) this.Edocs.get(i)).File;
                byte[] decode = Base64.decode(str.getBytes(), str.getBytes().length);
                if (!Display.getInstance().getPlatformName().equals("ios")) {
                    ((NativeCall) NativeLookup.create(NativeCall.class)).createPdfFromByteArray(decode, "E_Documents.pdf");
                } else {
                    String str2 = fileSystemStorage.getAppHomePath() + "E_Documents.pdf";
                    try {
                        OutputStream openOutputStream = fileSystemStorage.openOutputStream(str2);
                        try {
                            openOutputStream.write(decode);
                            if (openOutputStream != null) {
                                openOutputStream.close();
                            }
                        } catch (Throwable th) {
                            if (openOutputStream != null) {
                                try {
                                    openOutputStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            }
                            throw th;
                        }
                    } catch (IOException unused) {
                        this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Une erreur s'est produite , veuillez réessayer plus tard !", null);
                    }
                    Display.getInstance().execute(str2);
                }
            }
        } else {
            this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Aucun document trouvé", null);
        }
        return fileSystemStorage;
    }

    public int compare_date(String str, String str2) {
        try {
            int compareTo = str.substring(6).compareTo(str2.substring(6));
            return (compareTo == 0 && (compareTo = str.substring(3, 5).compareTo(str2.substring(3, 5))) == 0) ? str.substring(0, 2).compareTo(str2.substring(0, 2)) : compareTo;
        } catch (Exception unused) {
            return 1;
        }
    }
}
