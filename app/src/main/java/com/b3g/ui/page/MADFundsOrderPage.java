package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.OrderMADHistoricDataService;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCheckBox;
import com.b3g.ui.B3GRadio;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
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
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MADFundsOrderPage extends B3GPage {
    private Hashtable cityList;
    private Container commandsContainer;
    private boolean isOwnAgency = true;
    protected Log log;

    static /* synthetic */ boolean access$000(MADFundsOrderPage mADFundsOrderPage) {
        return mADFundsOrderPage.isOwnAgency;
    }

    static /* synthetic */ boolean access$002(MADFundsOrderPage mADFundsOrderPage, boolean z) {
        mADFundsOrderPage.isOwnAgency = z;
        return z;
    }

    static /* synthetic */ Hashtable access$100(MADFundsOrderPage mADFundsOrderPage) {
        return mADFundsOrderPage.cityList;
    }

    static /* synthetic */ Hashtable access$102(MADFundsOrderPage mADFundsOrderPage, Hashtable hashtable) {
        mADFundsOrderPage.cityList = hashtable;
        return hashtable;
    }

    static /* synthetic */ Container access$200(MADFundsOrderPage mADFundsOrderPage) {
        return mADFundsOrderPage.commandsContainer;
    }

    public MADFundsOrderPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        this.isNew = true;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.log = Log.getInstance();
        try {
            fillCommandsContainer();
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
    }

    void fillCommandsContainer() {
        Container container = new Container(new BoxLayout(2));
        this.commandsContainer = container;
        container.setUIID("ad_CntAccountDetailsMain");
        this.cityList = new Hashtable();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new B3gContainer(getCommandsForm(), "COMMANDE DIRHAMS"));
        arrayList.add(new B3gContainer(getCommandsHistoric(), "HISTORIQUE"));
        this.uiManager.DrawTabsWithNavigation(this.commandsContainer, arrayList);
        this.thisContainer.addComponent(this.commandsContainer);
    }

    public Container getCommandsForm() {
        B3GRadio b3GRadio;
        Label label;
        Container container;
        B3GCheckBox b3GCheckBox;
        Picker picker;
        Label label2;
        Label label3;
        ComboBox comboBox;
        Label label4;
        Label label5;
        Container container2;
        ComboBox comboBox2;
        new Account().uiManager = this.uiManager;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList());
        purgeForbidenAccount(arrayList);
        Container container3 = new Container(new BoxLayout(2));
        container3.setScrollableY(true);
        container3.getStyle().setPaddingUnit(1, 1, 1, 1);
        container3.getStyle().setPadding(0, 2, 1, 1);
        Container container4 = new Container();
        Container container5 = new Container();
        container5.setLayout(new BoxLayout(2));
        Label label6 = new Label("Montant(*)");
        label6.setUIID("g_lblNotif");
        Label label7 = new Label("Choisir les billets de la commande");
        label7.setUIID("g_lblNotif");
        Label label8 = new Label("Date de Disponibilité des Fonds (*)");
        label8.setUIID("g_lblNotif");
        Label label9 = new Label("Agence(*)");
        label9.setUIID("g_lblNotif");
        Label label10 = new Label("Ville de Collecte(*)");
        label10.setUIID("g_lblNotif");
        Label label11 = new Label("Agence de Collecte(*)");
        label11.setUIID("g_lblNotif");
        Label label12 = new Label("Commentaire");
        label12.setUIID("g_lblNotif");
        new Label("Plafond de la commande : ").setUIID("g_lblNotif");
        Container DrawListContainerCommands = this.uiManager.DrawListContainerCommands("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, arrayList, 1, 57, null, null, null, null);
        TextField textField = new TextField();
        textField.setUIID("listBoxTextField");
        textField.setConstraint(2);
        B3GCheckBox b3GCheckBox2 = new B3GCheckBox("MoneyType");
        b3GCheckBox2.addItem("200 DH");
        b3GCheckBox2.addItem("100 DH");
        b3GCheckBox2.addItem("50 DH");
        b3GCheckBox2.getItem("200 DH").setSelected(true);
        Picker picker2 = new Picker();
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, 2);
        picker2.setText(new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime()));
        picker2.setUIID("");
        Container container6 = new Container();
        container6.setUIID("listBox");
        container6.addComponent(picker2);
        container6.setLeadComponent(picker2);
        B3GRadio b3GRadio2 = new B3GRadio("Agence");
        b3GRadio2.addItem("Mon agence");
        b3GRadio2.getItem("Mon agence").addActionListener(new 1(container3, container5, container4));
        b3GRadio2.addItem("Autre");
        ComboBox comboBox3 = new ComboBox();
        comboBox3.setUIID("listBox");
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            picker = picker2;
            label2 = label11;
            container2 = container6;
            b3GRadio = b3GRadio2;
            label3 = label10;
            comboBox = comboBox3;
            label = label8;
            b3GCheckBox = b3GCheckBox2;
            label4 = label7;
            container = container4;
            label5 = label6;
            b3GRadio2.getItem("Autre").addActionListener(new 2(comboBox3, container3, container4, container5));
        } else {
            b3GRadio = b3GRadio2;
            label = label8;
            container = container4;
            b3GCheckBox = b3GCheckBox2;
            picker = picker2;
            label2 = label11;
            label3 = label10;
            comboBox = comboBox3;
            label4 = label7;
            label5 = label6;
            container2 = container6;
        }
        ComboBox comboBox4 = new ComboBox();
        comboBox4.setUIID("listBox");
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            comboBox2 = comboBox;
            comboBox2.addActionListener(new 3(comboBox4, comboBox2));
        } else {
            comboBox2 = comboBox;
        }
        TextField textField2 = new TextField();
        textField2.setRows(8);
        textField2.setMaxSize(512);
        textField2.setUIID("listBox");
        Button button = new Button("Valider");
        Label label13 = new Label("* Champs obligatoire");
        label13.setUIID("ad_lblValueGreen");
        button.setUIID("op_BtnOppositionValidation");
        container3.addComponent(DrawListContainerCommands);
        Container container7 = new Container();
        container7.setUIID("EmptyContainer");
        container3.addComponent(container7);
        container3.addComponent(label5);
        container3.addComponent(textField);
        Container container8 = new Container();
        container8.setUIID("EmptyContainer");
        container3.addComponent(container8);
        container3.addComponent(label4);
        container3.addComponent(b3GCheckBox.GetContainer());
        Container container9 = new Container();
        container9.setUIID("EmptyContainer");
        container3.addComponent(container9);
        container3.addComponent(label);
        container3.addComponent(container2);
        Container container10 = new Container();
        container10.setUIID("EmptyContainer");
        container3.addComponent(container10);
        container3.addComponent(label9);
        container3.addComponent(b3GRadio.GetContainer());
        Container container11 = new Container();
        container11.setUIID("EmptyContainer");
        container5.addComponent(container11);
        container5.addComponent(label3);
        container5.addComponent(comboBox2);
        Container container12 = new Container();
        container12.setUIID("EmptyContainer");
        container5.addComponent(container12);
        container5.addComponent(label2);
        container5.addComponent(comboBox4);
        container3.addComponent(container);
        Container container13 = new Container();
        container13.setUIID("EmptyContainer");
        container3.addComponent(container13);
        container3.addComponent(label12);
        container3.addComponent(textField2);
        Container container14 = new Container();
        container14.setUIID("EmptyContainer");
        container3.addComponent(container14);
        container3.addComponent(button);
        container3.addComponent(label13);
        button.addActionListener(new 4(picker, textField, DrawListContainerCommands, b3GCheckBox, textField2, comboBox2, comboBox4));
        return container3;
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
            MADFundsOrderPage.access$002(MADFundsOrderPage.this, true);
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
            MADFundsOrderPage.access$002(MADFundsOrderPage.this, false);
            if (MADFundsOrderPage.access$100(MADFundsOrderPage.this).isEmpty()) {
                MADFundsOrderPage mADFundsOrderPage = MADFundsOrderPage.this;
                MADFundsOrderPage.access$102(mADFundsOrderPage, mADFundsOrderPage.getCitiesListFromServiceResponse());
            }
            Object[] array = MADFundsOrderPage.access$100(MADFundsOrderPage.this).keySet().toArray();
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
            Object[] array = MADFundsOrderPage.this.getAgencyListFromServiceResponse(this.val$cbVilleCollect.getSelectedItem().toString()).keySet().toArray();
            Arrays.sort(array);
            for (Object obj : array) {
                this.val$cbAgenceCollect.addItem(obj.toString());
            }
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ ComboBox val$cbAgenceCollect;
        final /* synthetic */ ComboBox val$cbVilleCollect;
        final /* synthetic */ TextField val$commentText;
        final /* synthetic */ Container val$issuarAccount;
        final /* synthetic */ B3GCheckBox val$moneyType;
        final /* synthetic */ TextField val$montantText;
        final /* synthetic */ Picker val$pick;

        4(Picker picker, TextField textField, Container container, B3GCheckBox b3GCheckBox, TextField textField2, ComboBox comboBox, ComboBox comboBox2) {
            this.val$pick = picker;
            this.val$montantText = textField;
            this.val$issuarAccount = container;
            this.val$moneyType = b3GCheckBox;
            this.val$commentText = textField2;
            this.val$cbVilleCollect = comboBox;
            this.val$cbAgenceCollect = comboBox2;
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
                        if (Integer.parseInt(this.val$montantText.getText()) >= 100000) {
                            new Hashtable();
                            Picker picker = this.val$pick;
                            picker.setText(simpleDateFormat.format(picker.getDate()));
                            if (MADFundsOrderPage.access$000(MADFundsOrderPage.this)) {
                                FillHashMapFromParams = MADFundsOrderPage.this.FillHashMapFromParams(this.val$issuarAccount, this.val$montantText.getText(), this.val$moneyType.getSelectedItems(), this.val$pick.getText(), "", "", this.val$commentText.getText());
                            } else {
                                FillHashMapFromParams = MADFundsOrderPage.this.FillHashMapFromParams(this.val$issuarAccount, this.val$montantText.getText(), this.val$moneyType.getSelectedItems(), this.val$pick.getText(), this.val$cbVilleCollect.getSelectedItem().toString(), this.val$cbAgenceCollect.getSelectedItem().toString(), this.val$commentText.getText());
                            }
                            MADFundsOrderPage.this.ShowDialog(57, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, FillHashMapFromParams, 11);
                            return;
                        }
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez entrer un montant supérieur à 100000 Dh "), null);
                        return;
                    }
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageFunds(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(simpleDateFormat.format(calendar.getTime())), DataTools.FormatUnicode(simpleDateFormat.format(calendar2.getTime())));
                    return;
                } catch (NumberFormatException unused) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez saisir le montant de la commande "), null);
                    return;
                }
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MADFundsOrderPage.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }
    }

    public Container getCommandsHistoric() {
        ArrayList OrderHistoricProcess = OrderHistoricProcess();
        ArrayList arrayList = new ArrayList();
        Container container = new Container(new BoxLayout(2));
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            for (int i = 0; i < OrderHistoricProcess.size(); i++) {
                ((OrderMADHistoricDataService) OrderHistoricProcess.get(i)).groupKey = ((OrderMADHistoricDataService) OrderHistoricProcess.get(i)).getOperationDate();
                arrayList.add((B3gService) OrderHistoricProcess.get(i));
            }
            if (arrayList.size() > 0) {
                this.uiManager.Draw_GroupBy(container, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 10, null);
            } else {
                container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
            }
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
        }
        return container;
    }

    public ArrayList FillOrderArrayFromServiceResponse(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
            OrderMADHistoricDataService orderMADHistoricDataService = new OrderMADHistoricDataService();
            orderMADHistoricDataService.FillMADFundsOrderFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i)));
            arrayList.add(orderMADHistoricDataService);
        }
        return arrayList;
    }

    public ServiceResponse OrderProcess(Hashtable hashtable) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900081);
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
        hashtable.put("SERVICE_ID_PARENT", Integer.toString(900081));
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900086);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return FillOrderArrayFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public final Hashtable getAgencyListFromServiceResponse(String str) {
        new ServiceResponse();
        return (Hashtable) getAgencyProcess(str).getParamsOut().get(0);
    }

    public final Hashtable getCitiesListFromServiceResponse() {
        new ServiceResponse();
        return (Hashtable) getCitiesProcess().getParamsOut().get(0);
    }

    public final Hashtable FillHashMapFromParams(Container container, String str, ArrayList arrayList, String str2, String str3, String str4, String str5) {
        Hashtable hashtable = new Hashtable();
        Container container2 = (Container) ((Container) ((Container) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0);
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (((CheckBox) arrayList.get(i)).getText().equals("50 DH")) {
                    hashtable.put("MONEY_TYPE_1", "1");
                } else if (((CheckBox) arrayList.get(i)).getText().equals("100 DH")) {
                    hashtable.put("MONEY_TYPE_2", "1");
                } else {
                    hashtable.put("MONEY_TYPE_3", "1");
                }
            }
            if (hashtable.get("MONEY_TYPE_1") == null) {
                hashtable.put("MONEY_TYPE_1", "0");
            }
            if (hashtable.get("MONEY_TYPE_2") == null) {
                hashtable.put("MONEY_TYPE_2", "0");
            }
            if (hashtable.get("MONEY_TYPE_3") == null) {
                hashtable.put("MONEY_TYPE_3", "0");
            }
        }
        hashtable.put("AVAILABLITY_DATE", str2);
        hashtable.put("AGENCY", str4);
        hashtable.put("ACCOUNT_NUMBER", ((Label) container2.getComponentAt(1)).getText());
        hashtable.put("AMOUNT", str);
        hashtable.put("CITY", str3);
        hashtable.put("COMMENT", str5.trim());
        return hashtable;
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
                stateMachine.findLblpopupItemValueV2(createContainer).setText("Fonds MAD");
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
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(MADFundsOrderPage.this.OrderProcess(this.val$data).getStatusLabel()), null);
            MADFundsOrderPage.access$200(MADFundsOrderPage.this).removeAll();
            MADFundsOrderPage.this.fillCommandsContainer();
            MADFundsOrderPage.access$200(MADFundsOrderPage.this).revalidate();
        }
    }

    void purgeForbidenAccount(ArrayList arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            String substring = ((Account) arrayList.get(i)).rib.substring(13, 17);
            if (substring.equals("2310") || substring.equals("2360") || substring.equals("2311") || substring.substring(0, 2).equals("25")) {
                arrayList.remove(i);
                i--;
            }
            i++;
        }
    }
}
