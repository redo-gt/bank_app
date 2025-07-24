package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.ServiceManager;
import com.b3g.services.StudentTransferHisoricDataService;
import com.b3g.tools.DataTools;
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
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class StudentTransferPage extends B3GPage {
    private Container orderContainer;

    static /* synthetic */ Container access$000(StudentTransferPage studentTransferPage) {
        return studentTransferPage.orderContainer;
    }

    public StudentTransferPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
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
        ArrayList arrayList = new ArrayList();
        arrayList.add(new B3gContainer(getCommandsForm(), "TRANSFERT ETUDIANT"));
        arrayList.add(new B3gContainer(getCommandshistoric(), "HISTORIQUE"));
        this.uiManager.DrawTabsWithNavigation(this.orderContainer, arrayList);
        this.thisContainer.addComponent(this.orderContainer);
    }

    public Container getCommandsForm() {
        new Account().uiManager = this.uiManager;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList());
        purgeForbidenAccount(arrayList);
        Container container = new Container(new BoxLayout(2));
        container.setScrollableY(true);
        container.getStyle().setPaddingUnit(1, 1, 1, 1);
        container.getStyle().setPadding(0, 2, 1, 1);
        new Container().setLayout(new BoxLayout(2));
        Label label = new Label("Type de transfert(*)");
        label.setUIID("g_lblNotif");
        Label label2 = new Label("Montant à transférer(*)");
        label2.setUIID("g_lblNotif");
        Label label3 = new Label("Devise de transfert(*)");
        label3.setUIID("g_lblNotif");
        Label label4 = new Label("Nom de l'étudiant(*)");
        label4.setUIID("g_lblNotif");
        Label label5 = new Label("Commentaire");
        label5.setUIID("g_lblNotif");
        Container DrawListContainerCommands = this.uiManager.DrawListContainerCommands("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, arrayList, 1, 59, null, null, null, null);
        ComboBox comboBox = new ComboBox();
        comboBox.setUIID("listBox");
        comboBox.addItem("Frais de scolarité");
        comboBox.addItem("Frais de séjour");
        comboBox.addItem("Loyer");
        ComboBox comboBox2 = new ComboBox();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        comboBox2.setUIID("listBox");
        linkedHashMap.put("DIRHAM MAROCAIN (MAD)", "MAD");
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
        for (int i = 1; i < linkedHashMap.size(); i++) {
            comboBox2.addItem(linkedHashMap.keySet().toArray()[i]);
        }
        TextField textField = new TextField();
        textField.setUIID("listBoxTextField");
        textField.setConstraint(2);
        Container container2 = new Container(new GridLayout(1, 2));
        ComboBox comboBox3 = new ComboBox();
        comboBox3.setUIID("listBox");
        for (int i2 = 0; i2 < linkedHashMap.size(); i2++) {
            comboBox3.addItem(linkedHashMap.keySet().toArray()[i2]);
        }
        textField.setNextFocusDown(comboBox3);
        container2.addComponent(textField);
        container2.addComponent(comboBox3);
        TextField textField2 = new TextField();
        textField2.setUIID("listBoxTextField");
        comboBox3.setNextFocusDown(textField2);
        TextField textField3 = new TextField();
        textField3.setRows(8);
        textField3.setMaxSize(1024);
        textField3.setUIID("listBox");
        Button button = new Button("Valider");
        button.setUIID("op_BtnOppositionValidation");
        Label label6 = new Label("* Champs obligatoire");
        label6.setUIID("ad_lblValueGreen");
        container.addComponent(DrawListContainerCommands);
        Component container3 = new Container();
        container3.setUIID("EmptyContainer");
        container.addComponent(container3);
        container.addComponent(label);
        container.addComponent(comboBox);
        Component container4 = new Container();
        container4.setUIID("EmptyContainer");
        container.addComponent(container4);
        container.addComponent(label3);
        container.addComponent(comboBox2);
        Component container5 = new Container();
        container5.setUIID("EmptyContainer");
        container.addComponent(container5);
        container.addComponent(label2);
        container.addComponent(container2);
        Component container6 = new Container();
        container6.setUIID("EmptyContainer");
        container.addComponent(container6);
        container.addComponent(label4);
        container.addComponent(textField2);
        Component container7 = new Container();
        container7.setUIID("EmptyContainer");
        container.addComponent(container7);
        container.addComponent(label5);
        container.addComponent(textField3);
        Component container8 = new Container();
        container8.setUIID("EmptyContainer");
        container.addComponent(container8);
        container.addComponent(button);
        container.addComponent(label6);
        button.addActionListener(new 1(textField2, textField, DrawListContainerCommands, comboBox, linkedHashMap, comboBox2, comboBox3, textField3, container));
        return container;
    }

    class 1 implements ActionListener {
        final /* synthetic */ ComboBox val$cbDevise;
        final /* synthetic */ ComboBox val$cbtrsType;
        final /* synthetic */ ComboBox val$cbxDevise;
        final /* synthetic */ Container val$cntGlobal;
        final /* synthetic */ TextField val$commentText;
        final /* synthetic */ Map val$deviseMap;
        final /* synthetic */ Container val$issuarAccount;
        final /* synthetic */ TextField val$montantText;
        final /* synthetic */ TextField val$studentNameText;

        1(TextField textField, TextField textField2, Container container, ComboBox comboBox, Map map, ComboBox comboBox2, ComboBox comboBox3, TextField textField3, Container container2) {
            this.val$studentNameText = textField;
            this.val$montantText = textField2;
            this.val$issuarAccount = container;
            this.val$cbtrsType = comboBox;
            this.val$deviseMap = map;
            this.val$cbDevise = comboBox2;
            this.val$cbxDevise = comboBox3;
            this.val$commentText = textField3;
            this.val$cntGlobal = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                try {
                    if (!this.val$studentNameText.getText().equals("") && Integer.parseInt(this.val$montantText.getText()) > 100) {
                        new Hashtable();
                        StudentTransferPage.this.ShowDialog(59, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, StudentTransferPage.this.FillServiceParams(this.val$issuarAccount, this.val$cbtrsType.getSelectedItem().toString(), this.val$montantText.getText(), this.val$deviseMap.get(this.val$cbDevise.getSelectedItem()).toString(), this.val$deviseMap.get(this.val$cbxDevise.getSelectedItem()).toString(), this.val$studentNameText.getText(), this.val$commentText.getText()), 11, this.val$cntGlobal);
                    } else if (this.val$studentNameText.getText().equals("")) {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Merci de saisir le nom de l'étudiant "), null);
                    } else {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Merci de saisir un montant strictement supérieur à 100 "), null);
                    }
                    return;
                } catch (NumberFormatException unused) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Merci de saisir un montant de transfert valide "), null);
                    return;
                }
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(StudentTransferPage.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }
    }

    public final Hashtable FillServiceParams(Container container, String str, String str2, String str3, String str4, String str5, String str6) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("ACCOUNT_NUMBER", ((Label) ((Container) ((Container) ((Container) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(1)).getText());
        hashtable.put("TRANSFER_CURRENCY", str3);
        hashtable.put("AMOUNT", str2);
        hashtable.put("AMOUNT_CURRENCY", str4);
        hashtable.put("STUDENT_NAME", str5);
        hashtable.put("TRANSFER_TYPE", str);
        hashtable.put("COMMENT", str6.trim());
        return hashtable;
    }

    public Container getCommandshistoric() {
        ArrayList OrderHistoricProcess = OrderHistoricProcess();
        ArrayList arrayList = new ArrayList();
        Container container = new Container(new BoxLayout(2));
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            for (int i = 0; i < OrderHistoricProcess.size(); i++) {
                ((StudentTransferHisoricDataService) OrderHistoricProcess.get(i)).groupKey = ((StudentTransferHisoricDataService) OrderHistoricProcess.get(i)).getOperationDate();
                arrayList.add((B3gService) OrderHistoricProcess.get(i));
            }
            if (arrayList.size() > 0) {
                this.uiManager.Draw_GroupBy(container, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 11, null);
            } else {
                container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
            }
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
        }
        return container;
    }

    public ArrayList FillTransferArrayFromServiceResponse(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
            StudentTransferHisoricDataService studentTransferHisoricDataService = new StudentTransferHisoricDataService();
            studentTransferHisoricDataService.FillStudentTransferFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i)));
            arrayList.add(studentTransferHisoricDataService);
        }
        return arrayList;
    }

    public ServiceResponse OrderProcess(Hashtable hashtable) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900083);
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
        hashtable.put("SERVICE_ID_PARENT", Integer.toString(900083));
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900088);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return FillTransferArrayFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public void ShowDialog(int i, StateMachine stateMachine, Hashtable hashtable, int i2, Container container) {
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
                stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupBtn(dialog, i, hashtable, i2, stateMachine, container));
                stateMachine.findLblpopupItemTitleV2(createContainer).setText("Demande :");
                stateMachine.findLblpopupItemValueV2(createContainer).setText("Transfert Etudiant");
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

    public Container GetPopupBtn(Dialog dialog, int i, Hashtable hashtable, int i2, StateMachine stateMachine, Container container) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container2 = new Container();
        container2.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(stateMachine.uiManager.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 2(dialog));
        container2.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(stateMachine.uiManager.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 3(dialog, hashtable));
        container2.addComponent(button2);
        return container2;
    }

    class 2 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        2(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ Hashtable val$data;

        3(Dialog dialog, Hashtable hashtable) {
            this.val$d = dialog;
            this.val$data = hashtable;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            CihMBanking.sesPMTR.setTabPosition(0);
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(StudentTransferPage.this.OrderProcess(this.val$data).getStatusLabel()), null);
            StudentTransferPage.access$000(StudentTransferPage.this).removeAll();
            StudentTransferPage.this.fillOrderContainer();
            StudentTransferPage.access$000(StudentTransferPage.this).revalidate();
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
