package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.OrderCurrencyHistoricDataService;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GRadio;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.Transition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CurrencyFundsOrderPage extends B3GPage {
    private Hashtable cityList;
    private boolean isOwnAgency = true;
    private Container orderContainer;

    static /* synthetic */ boolean access$000(CurrencyFundsOrderPage currencyFundsOrderPage) {
        return currencyFundsOrderPage.isOwnAgency;
    }

    static /* synthetic */ boolean access$002(CurrencyFundsOrderPage currencyFundsOrderPage, boolean z) {
        currencyFundsOrderPage.isOwnAgency = z;
        return z;
    }

    static /* synthetic */ Container access$100(CurrencyFundsOrderPage currencyFundsOrderPage) {
        return currencyFundsOrderPage.orderContainer;
    }

    public CurrencyFundsOrderPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            fillOrderContainer();
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
    }

    void fillOrderContainer() {
        Container container = new Container(new BoxLayout(2));
        this.orderContainer = container;
        container.setUIID("ad_CntAccountDetailsMain");
        this.cityList = new Hashtable();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new B3gContainer(getCommandsForm(), "COMMANDE DEVISE"));
        arrayList.add(new B3gContainer(getCommandshistoric(), "HISTORIQUE"));
        this.uiManager.DrawTabsWithNavigation(this.orderContainer, arrayList);
        this.thisContainer.addComponent(this.orderContainer);
    }

    public Container getCommandsForm() {
        ComboBox comboBox;
        Container container;
        Label label;
        Label label2;
        Label label3;
        Label label4;
        TextField textField;
        ComboBox comboBox2;
        String str;
        B3GRadio b3GRadio;
        ComboBox comboBox3;
        Container container2 = new Container(new BoxLayout(2));
        container2.setScrollableY(true);
        container2.getStyle().setPaddingUnit(1, 1, 1, 1);
        container2.getStyle().setPadding(0, 2, 1, 1);
        Container container3 = new Container();
        Container container4 = new Container();
        container4.setLayout(new BoxLayout(2));
        Label label5 = new Label("Devise(*)");
        label5.setUIID("g_lblNotif");
        Label label6 = new Label("Montant(*)");
        label6.setUIID("g_lblNotif");
        Label label7 = new Label("Date de Disponibilité des Fonds (*)");
        label7.setUIID("g_lblNotif");
        Label label8 = new Label("Agence(*)");
        label8.setUIID("g_lblNotif");
        Label label9 = new Label("Ville de Collecte(*)");
        label9.setUIID("g_lblNotif");
        Label label10 = new Label("Agence de Collecte(*)");
        label10.setUIID("g_lblNotif");
        Label label11 = new Label("Commentaire");
        label11.setUIID("g_lblNotif");
        ComboBox comboBox4 = new ComboBox();
        comboBox4.setUIID("listBox");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("EURO (EUR)", "EURO");
        linkedHashMap.put("DOLLAR  AMERICAIN (D.USA)", "D.USA");
        linkedHashMap.put("DOLLAR  CANADIEN (DCAN)", "DCAN");
        linkedHashMap.put("LIVRE  STERLING (LSTG)", "LSTG");
        linkedHashMap.put("COURONNE  DANEMARK (C.DAN)", "C.DAN");
        linkedHashMap.put("COURONNES  DANOISES (DK)", "DK");
        linkedHashMap.put("COURONNES  NORVEGIENNES (C.NOR)", "C.NOR");
        linkedHashMap.put("COURONNES  SUEDOISES (C.SUE)", "C.SUE");
        linkedHashMap.put("FRANCS  SUISSE (FRSUI)", "FRSUI");
        linkedHashMap.put("DINAR  BAHREINI (DB)", "DB");
        linkedHashMap.put("DIRHAM  EMIRATS  ARABES  UNIS (DHEAU)", "DHEAU");
        linkedHashMap.put("LIVRE  GIBRALTAR (GIP)", "GIP");
        linkedHashMap.put("RIAL  OMANAIS (OMR)", "OMR");
        linkedHashMap.put("RIYAL  QATARI (RQ)", "RQ");
        linkedHashMap.put("RIYAL  SAOUDIEN (RS)", "RS");
        linkedHashMap.put("YEN  JAPONAIS (YJ)", "YJ");
        for (int i = 0; i < linkedHashMap.size(); i++) {
            comboBox4.addItem(linkedHashMap.keySet().toArray()[i]);
        }
        TextField textField2 = new TextField();
        textField2.setUIID("listBoxTextField");
        textField2.setConstraint(2);
        Picker picker = new Picker();
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, 0);
        picker.setText(new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime()));
        picker.setUIID("");
        Container container5 = new Container();
        container5.setUIID("listBox");
        container5.addComponent(picker);
        container5.setLeadComponent(picker);
        B3GRadio b3GRadio2 = new B3GRadio("Agence");
        b3GRadio2.addItem("Mon agence ");
        b3GRadio2.getItem("Mon agence").addActionListener(new 1(container2, container4, container3));
        b3GRadio2.addItem("Autre");
        ComboBox comboBox5 = new ComboBox();
        comboBox5.setUIID("listBox");
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            label2 = label10;
            label3 = label9;
            comboBox = comboBox5;
            label4 = label8;
            str = "listBox";
            textField = textField2;
            b3GRadio = b3GRadio2;
            container = container5;
            comboBox2 = comboBox4;
            label = label11;
            b3GRadio2.getItem("Autre").addActionListener(new 2(comboBox, container2, container3, container4));
        } else {
            comboBox = comboBox5;
            container = container5;
            label = label11;
            label2 = label10;
            label3 = label9;
            label4 = label8;
            textField = textField2;
            comboBox2 = comboBox4;
            str = "listBox";
            b3GRadio = b3GRadio2;
        }
        ComboBox comboBox6 = new ComboBox();
        comboBox6.setUIID(str);
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            comboBox3 = comboBox;
            comboBox3.addActionListener(new 3(comboBox6, comboBox3));
        } else {
            comboBox3 = comboBox;
        }
        TextField textField3 = new TextField();
        textField3.setRows(8);
        textField3.setMaxSize(512);
        textField3.setUIID(str);
        Button button = new Button("Valider");
        button.setUIID("op_BtnOppositionValidation");
        Label label12 = new Label("* Champs obligatoire");
        label12.setUIID("ad_lblValueGreen");
        ComboBox comboBox7 = comboBox3;
        button.addActionListener(new 4(picker, textField, linkedHashMap, comboBox2, textField3, comboBox7, comboBox6));
        container2.addComponent(label5);
        container2.addComponent(comboBox2);
        Container container6 = new Container();
        container6.setUIID("EmptyContainer");
        container2.addComponent(container6);
        container2.addComponent(label6);
        container2.addComponent(textField);
        Container container7 = new Container();
        container7.setUIID("EmptyContainer");
        container2.addComponent(container7);
        container2.addComponent(label7);
        container2.addComponent(container);
        Container container8 = new Container();
        container8.setUIID("EmptyContainer");
        container2.addComponent(container8);
        container2.addComponent(label4);
        container2.addComponent(b3GRadio.GetContainer());
        Container container9 = new Container();
        container9.setUIID("EmptyContainer");
        container4.addComponent(container9);
        container4.addComponent(label3);
        container4.addComponent(comboBox7);
        Container container10 = new Container();
        container10.setUIID("EmptyContainer");
        container4.addComponent(container10);
        container4.addComponent(label2);
        container4.addComponent(comboBox6);
        container2.addComponent(container3);
        Container container11 = new Container();
        container11.setUIID("EmptyContainer");
        container2.addComponent(container11);
        container2.addComponent(label);
        container2.addComponent(textField3);
        Container container12 = new Container();
        container12.setUIID("EmptyContainer");
        container2.addComponent(container12);
        container2.addComponent(button);
        container2.addComponent(label12);
        return container2;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$agenceContainer;
        final /* synthetic */ Container val$agenceContainerEmpty;
        final /* synthetic */ Container val$cntGlobal;

        1(Container container, Container container2, Container container3) {
            this.val$cntGlobal = container;
            this.val$agenceContainer = container2;
            this.val$agenceContainerEmpty = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CurrencyFundsOrderPage.access$002(CurrencyFundsOrderPage.this, true);
            this.val$cntGlobal.replace(this.val$agenceContainer, this.val$agenceContainerEmpty, (Transition) null);
            this.val$cntGlobal.revalidate();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$agenceContainer;
        final /* synthetic */ Container val$agenceContainerEmpty;
        final /* synthetic */ ComboBox val$cbVilleCollect;
        final /* synthetic */ Container val$cntGlobal;

        2(ComboBox comboBox, Container container, Container container2, Container container3) {
            this.val$cbVilleCollect = comboBox;
            this.val$cntGlobal = container;
            this.val$agenceContainerEmpty = container2;
            this.val$agenceContainer = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CurrencyFundsOrderPage.access$002(CurrencyFundsOrderPage.this, false);
            Object[] array = CurrencyFundsOrderPage.this.getCitiesListFromServiceResponse().keySet().toArray();
            Arrays.sort(array);
            for (Object obj : array) {
                this.val$cbVilleCollect.addItem(obj.toString());
            }
            this.val$cntGlobal.replace(this.val$agenceContainerEmpty, this.val$agenceContainer, (Transition) null);
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ ComboBox val$cbAgenceCollect;
        final /* synthetic */ ComboBox val$cbVilleCollect;

        3(ComboBox comboBox, ComboBox comboBox2) {
            this.val$cbAgenceCollect = comboBox;
            this.val$cbVilleCollect = comboBox2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ((DefaultListModel) this.val$cbAgenceCollect.getModel()).removeAll();
            Object[] array = CurrencyFundsOrderPage.this.getAgencyListFromServiceResponse(this.val$cbVilleCollect.getSelectedItem().toString()).keySet().toArray();
            Arrays.sort(array);
            for (Object obj : array) {
                this.val$cbAgenceCollect.addItem(obj.toString());
            }
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ ComboBox val$cbAgenceCollect;
        final /* synthetic */ ComboBox val$cbDevise;
        final /* synthetic */ ComboBox val$cbVilleCollect;
        final /* synthetic */ TextField val$commentText;
        final /* synthetic */ Map val$deviseMap;
        final /* synthetic */ TextField val$montantText;
        final /* synthetic */ Picker val$pick;

        4(Picker picker, TextField textField, Map map, ComboBox comboBox, TextField textField2, ComboBox comboBox2, ComboBox comboBox3) {
            this.val$pick = picker;
            this.val$montantText = textField;
            this.val$deviseMap = map;
            this.val$cbDevise = comboBox;
            this.val$commentText = textField2;
            this.val$cbVilleCollect = comboBox2;
            this.val$cbAgenceCollect = comboBox3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Hashtable FillHashMapFromParams;
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(5, 2);
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.add(5, 10);
                    if (this.val$pick.getDate().getTime() >= calendar.getTime().getTime() && this.val$pick.getDate().getTime() <= calendar2.getTime().getTime()) {
                        if (Integer.parseInt(this.val$montantText.getText()) > 100) {
                            new Hashtable();
                            Picker picker = this.val$pick;
                            picker.setText(simpleDateFormat.format(picker.getDate()));
                            if (CurrencyFundsOrderPage.access$000(CurrencyFundsOrderPage.this)) {
                                FillHashMapFromParams = CurrencyFundsOrderPage.this.FillHashMapFromParams(this.val$montantText.getText(), this.val$pick.getText(), this.val$deviseMap.get(this.val$cbDevise.getSelectedItem()).toString(), "", "", this.val$commentText.getText());
                            } else {
                                FillHashMapFromParams = CurrencyFundsOrderPage.this.FillHashMapFromParams(this.val$montantText.getText(), this.val$pick.getText(), this.val$deviseMap.get(this.val$cbDevise.getSelectedItem()).toString(), this.val$cbVilleCollect.getSelectedItem().toString(), this.val$cbAgenceCollect.getSelectedItem().toString(), this.val$commentText.getText());
                            }
                            CurrencyFundsOrderPage.this.ShowDialog(58, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, FillHashMapFromParams, 18);
                            return;
                        }
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez saisir un montant valide de la commande supérieur à 100 "), null);
                        return;
                    }
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageFunds(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(simpleDateFormat.format(calendar.getTime())), DataTools.FormatUnicode(simpleDateFormat.format(calendar2.getTime())));
                    return;
                } catch (NumberFormatException unused) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez saisir le montant de la commande "), null);
                    return;
                }
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CurrencyFundsOrderPage.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }
    }

    public Container getCommandshistoric() {
        ArrayList OrderHistoricProcess = OrderHistoricProcess();
        ArrayList arrayList = new ArrayList();
        Container container = new Container(new BoxLayout(2));
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            for (int i = 0; i < OrderHistoricProcess.size(); i++) {
                ((OrderCurrencyHistoricDataService) OrderHistoricProcess.get(i)).groupKey = ((OrderCurrencyHistoricDataService) OrderHistoricProcess.get(i)).getOperationDate();
                arrayList.add((B3gService) OrderHistoricProcess.get(i));
            }
            if (arrayList.size() > 0) {
                this.uiManager.Draw_GroupBy(container, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 18, null);
            } else {
                container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
            }
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
        }
        return container;
    }

    public ServiceResponse OrderProcess(Hashtable hashtable) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900082);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse getAgencyProcess(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CITY_CODE", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900091);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse getCitiesProcess() {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900090);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ArrayList OrderHistoricProcess() {
        new ArrayList();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("SERVICE_ID_PARENT", Integer.toString(900082));
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900087);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return FillOrderArrayFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public final Hashtable FillHashMapFromParams(String str, String str2, String str3, String str4, String str5, String str6) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("AVAILABILITY_DATE", str2);
        hashtable.put("CURRENCY", str3);
        hashtable.put("AGENCY", str5);
        hashtable.put("AMOUNT", str);
        hashtable.put("CITY", str4);
        hashtable.put("COMMENT", str6.trim());
        return hashtable;
    }

    public ArrayList FillOrderArrayFromServiceResponse(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
            OrderCurrencyHistoricDataService orderCurrencyHistoricDataService = new OrderCurrencyHistoricDataService();
            orderCurrencyHistoricDataService.FillCurrencyOrderFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i)));
            arrayList.add(orderCurrencyHistoricDataService);
        }
        return arrayList;
    }

    public void ShowDialog(int i, StateMachine stateMachine, Hashtable hashtable, int i2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            try {
                ArrayList GetPopupItemDetail = stateMachine.uiManager.GetPopupItemDetail(i, this.service, stateMachine, open, createContainer, null, hashtable);
                for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                    stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
                }
                Label label = new Label(" ");
                label.setUIID("dg_lblPopUpDemo");
                label.setVerticalAlignment(4);
                stateMachine.findCntPopupBody(createContainer).addComponent(label);
                stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupBtn(dialog, i, hashtable, i2, stateMachine));
                stateMachine.findLblpopupItemTitleV2(createContainer).setText("Commande :");
                stateMachine.findLblpopupItemValueV2(createContainer).setText("Fonds Devise");
                createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
                createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
        } catch (IOException unused2) {
        }
    }

    public Container GetPopupBtn(Dialog dialog, int i, Hashtable hashtable, int i2, StateMachine stateMachine) {
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
        button.addActionListener(new 5(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(stateMachine.uiManager.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 6(dialog, hashtable));
        container.addComponent(button2);
        return container;
    }

    class 5 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        5(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ Hashtable val$data;

        6(Dialog dialog, Hashtable hashtable) {
            this.val$d = dialog;
            this.val$data = hashtable;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            CihMBanking.sesPMTR.setTabPosition(0);
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(CurrencyFundsOrderPage.this.OrderProcess(this.val$data).getStatusLabel()), null);
            CurrencyFundsOrderPage.access$100(CurrencyFundsOrderPage.this).removeAll();
            CurrencyFundsOrderPage.this.fillOrderContainer();
            CurrencyFundsOrderPage.access$100(CurrencyFundsOrderPage.this).revalidate();
        }
    }

    public final Hashtable getAgencyListFromServiceResponse(String str) {
        new ServiceResponse();
        return (Hashtable) getAgencyProcess(str).getParamsOut().get(0);
    }

    public final Hashtable getCitiesListFromServiceResponse() {
        new ServiceResponse();
        return (Hashtable) getCitiesProcess().getParamsOut().get(0);
    }
}
