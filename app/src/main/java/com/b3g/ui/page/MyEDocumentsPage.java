package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.MyEDocumentsService;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.RateUs;
import com.b3g.ui.menu.TreeMenuHardData;
import com.b3g.ui.page.cih.Module.AndroidDateTypePicker;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
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
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MyEDocumentsPage extends B3GPage {
    private static TextField addressText;
    SimpleDateFormat formatter = new SimpleDateFormat("MMM/yyyy");

    static /* synthetic */ Container access$000(MyEDocumentsPage myEDocumentsPage, ArrayList arrayList) {
        return myEDocumentsPage.drawGlobalDecriptionDocumentContainer(arrayList);
    }

    static /* synthetic */ Container access$100(MyEDocumentsPage myEDocumentsPage, ArrayList arrayList) {
        return myEDocumentsPage.drawGlobalAccountDocumentContainer(arrayList);
    }

    static /* synthetic */ void access$200(MyEDocumentsPage myEDocumentsPage, int i, Container[] containerArr) {
        myEDocumentsPage.disCollapseOtherComponent(i, containerArr);
    }

    static /* synthetic */ TextField access$300() {
        return addressText;
    }

    static /* synthetic */ boolean access$400(MyEDocumentsPage myEDocumentsPage, String str) {
        return myEDocumentsPage.mailAddresChecker(str);
    }

    public MyEDocumentsPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            ArrayList arrayList = new ArrayList();
            new ArrayList();
            new ArrayList();
            new ArrayList();
            arrayList.add(new B3gContainer(getEDocumentsForm(null, "H"), "E-DOCUMENTS"));
            this.uiManager.DrawTabsWithNavigation(this.thisContainer, arrayList);
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
    }

    public Container getEDocumentsForm(ArrayList arrayList, String str) {
        Container container = new Container(new BoxLayout(2));
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            if (!str.equals("R")) {
                Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "EdocTypCntNw");
                Container container2 = (Container) this.uiManager.stateMachine.findByName("FormulCnt", createContainer);
                Button button = (Button) this.uiManager.stateMachine.findByName("btnCollapse", createContainer);
                Container container3 = (Container) this.uiManager.stateMachine.findByName("DatePiker", createContainer);
                Container container4 = (Container) this.uiManager.stateMachine.findByName("ComptesPiker", createContainer);
                Button button2 = (Button) this.uiManager.stateMachine.findByName("btnTransfr", createContainer);
                new Container(new GridLayout(1, 1));
                Picker picker = new Picker();
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList());
                arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getListRelationAccount());
                String[] strArr = new String[arrayList2.size()];
                for (int i = 0; i < arrayList2.size(); i++) {
                    strArr[i] = ((Account) arrayList2.get(i)).accountNumber.toString();
                }
                container4.add(drawPicker(picker, strArr));
                button.setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "plusBlue.png"));
                container2.setHidden(true);
                Button androidDateTypePicker = new AndroidDateTypePicker();
                androidDateTypePicker.setText(" ");
                picker.setText(" ");
                Container drawDatePicker = drawDatePicker(androidDateTypePicker);
                container3.setLeadComponent(androidDateTypePicker);
                container3.add("Center", drawDatePicker);
                new Picker();
                androidDateTypePicker.addActionListener(new 1(androidDateTypePicker));
                button.addActionListener(new 2(container2, button, createContainer));
                button2.addActionListener(new MyEDocumentsPage$$ExternalSyntheticLambda0(this, picker, androidDateTypePicker));
                container.add(createContainer);
            }
            if (!CihMBanking.sesPMTR.getIsDemat().equals("false")) {
                container.setScrollVisible(false);
                if (arrayList != null && arrayList.size() > 0) {
                    drawGlobalDocumentContainer(container, arrayList);
                }
            }
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Ce service n'est pas disponible en mode démonstration."));
        }
        return container;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Button val$DateNaissPicker;

        1(Button button) {
            this.val$DateNaissPicker = button;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$DateNaissPicker.setAlignment(4);
            this.val$DateNaissPicker.setUIID("lbl_regular_center");
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$EdocExtraitCnt;
        final /* synthetic */ Container val$FormulCnt;
        final /* synthetic */ Button val$btnCollapse;

        2(Container container, Button button, Container container2) {
            this.val$FormulCnt = container;
            this.val$btnCollapse = button;
            this.val$EdocExtraitCnt = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$FormulCnt.isHidden()) {
                this.val$FormulCnt.setHidden(false);
                this.val$btnCollapse.setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "minusBlue.png"));
                this.val$EdocExtraitCnt.revalidate();
            } else {
                this.val$FormulCnt.setHidden(true);
                this.val$btnCollapse.setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "plusBlue.png"));
                this.val$EdocExtraitCnt.revalidate();
            }
        }
    }

    /* synthetic */ void lambda$getEDocumentsForm$0$com-b3g-ui-page-MyEDocumentsPage(Picker picker, Button button, ActionEvent actionEvent) {
        getEdocm(picker, button);
    }

    private void getEdocm(Picker picker, Button button) {
        if (!picker.getText().equals(" ") && !button.getText().trim().equals(" ") && picker.getText().trim().length() != 0 && button.getText().trim().length() != 0) {
            String str = button.getText().substring(3, button.getText().length()) + "-" + button.getText().substring(0, 2);
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
                if (fileSystemStorage.exists(fileSystemStorage.getAppHomePath() + picker.getText() + str + ".pdf")) {
                    Display.getInstance().execute(fileSystemStorage.getAppHomePath() + picker.getText() + str + ".pdf");
                } else {
                    ServiceResponse transfExtraitProcess = transfExtraitProcess(picker.getSelectedString(), str);
                    if (transfExtraitProcess.getStatusCode().equals("000")) {
                        String str2 = fileSystemStorage.getAppHomePath() + picker.getText() + str + ".pdf";
                        byte[] decode = Base64.decode(transfExtraitProcess.getStatusLabel().getBytes(), transfExtraitProcess.getStatusLabel().getBytes().length);
                        try {
                            OutputStream openOutputStream = fileSystemStorage.openOutputStream(str2);
                            try {
                                openOutputStream.write(decode);
                                if (openOutputStream != null) {
                                    openOutputStream.close();
                                }
                            } finally {
                            }
                        } catch (IOException unused) {
                        }
                        new RateUs(this.uiManager).init();
                        Display.getInstance().execute(str2);
                    } else {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageEDocument(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, str, null, transfExtraitProcess.getStatusCode());
                    }
                }
                picker.setText(" ");
                button.setText(" ");
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
            return;
        }
        this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Merci de saisir tous les champs.", null);
    }

    public ArrayList FillDcsDataFromServiceResponse(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
            MyEDocumentsService myEDocumentsService = new MyEDocumentsService();
            myEDocumentsService.FillDataFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i)));
            arrayList.add(myEDocumentsService);
        }
        return arrayList;
    }

    ArrayList filterByAccountNumber(String str, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            B3gService b3gService = (B3gService) it.next();
            if (((MyEDocumentsService) b3gService).getAccountNumber().equals(str)) {
                arrayList2.add(b3gService);
            }
        }
        return arrayList2;
    }

    ArrayList filterByRoot(String str, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            B3gService b3gService = (B3gService) it.next();
            if (((MyEDocumentsService) b3gService).getRoot().equals(str)) {
                arrayList2.add(b3gService);
            }
        }
        return arrayList2;
    }

    ArrayList filterByDescription(String str, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            B3gService b3gService = (B3gService) it.next();
            if (((MyEDocumentsService) b3gService).getDescription().equals(str)) {
                arrayList2.add(b3gService);
            }
        }
        return arrayList2;
    }

    public ArrayList getDocsProcess() {
        new ArrayList();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("SERVICEID", "01");
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900084);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        ServiceResponse serviceResponse = (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
        if (serviceResponse.getStatusCode().equals("000")) {
            return FillDcsDataFromServiceResponse(serviceResponse);
        }
        return null;
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

    private void drawDocumentrList(Container container, ArrayList arrayList) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (Display.getInstance().getPlatformName().equals("ios")) {
            Collections.reverse(arrayList);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            B3gService b3gService = (B3gService) it.next();
            String accountNumber = ((MyEDocumentsService) b3gService).getAccountNumber();
            if (linkedHashMap.get(accountNumber) == null) {
                linkedHashMap.put(accountNumber, new ArrayList());
            }
            ((ArrayList) linkedHashMap.get(accountNumber)).add(b3gService);
        }
        Container[] containerArr = new Container[linkedHashMap.keySet().size()];
        int i = 0;
        for (String str : linkedHashMap.keySet()) {
            Container container2 = new Container(new BoxLayout(2));
            containerArr[i] = container2;
            container2.setScrollableY(false);
            containerArr[i].setScrollableX(false);
            containerArr[i].setUIID("Container");
            Container container3 = new Container(new FlowLayout());
            Label label = new Label("Compte n°");
            label.setUIID("labelAccountForm");
            Label label2 = new Label(str);
            label2.setUIID("accountDocumentLabel");
            container3.addComponent(label);
            container3.addComponent(label2);
            container.addComponent(container3);
            this.uiManager.Draw_GroupBy(containerArr[i], filterByAccountNumber(str, arrayList), CihMBanking.sesPMTR.getIpadUiid(), 9, null);
            container.addComponent(containerArr[i]);
            i++;
        }
    }

    private void drawGlobalDocumentContainer(Container container, ArrayList arrayList) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (Display.getInstance().getPlatformName().equals("ios")) {
            Collections.reverse(arrayList);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            B3gService b3gService = (B3gService) it.next();
            String root = ((MyEDocumentsService) b3gService).getRoot();
            if (linkedHashMap.get(root) == null) {
                linkedHashMap.put(root, new ArrayList());
            }
            ((ArrayList) linkedHashMap.get(root)).add(b3gService);
        }
        Container[] containerArr = new Container[linkedHashMap.keySet().size()];
        int i = 0;
        for (String str : linkedHashMap.keySet()) {
            Container container2 = new Container(new BoxLayout(2));
            containerArr[i] = container2;
            container2.setScrollableY(false);
            containerArr[i].setScrollableX(false);
            new Container(new BoxLayout(2));
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "EdocTypeCnt");
            Button findBtnCollapse = this.uiManager.stateMachine.findBtnCollapse(createContainer);
            Button findBtnEdocType = this.uiManager.stateMachine.findBtnEdocType(createContainer);
            findBtnEdocType.setText(str);
            findBtnEdocType.putClientProperty("index", Integer.valueOf(i));
            findBtnEdocType.putClientProperty("collapsed", 0);
            createContainer.setLeadComponent(findBtnEdocType);
            containerArr[i].addComponent(createContainer);
            containerArr[i].addComponent(new Container(new BoxLayout(2)));
            findBtnEdocType.addActionListener(new 3(findBtnCollapse, findBtnEdocType, containerArr, str, arrayList));
            container.addComponent(containerArr[i]);
            i++;
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Button val$collapseBtn;
        final /* synthetic */ Container[] val$containers;
        final /* synthetic */ ArrayList val$documentsB3gServiceList;
        final /* synthetic */ Button val$rootBtn;
        final /* synthetic */ String val$rootFinal;

        3(Button button, Button button2, Container[] containerArr, String str, ArrayList arrayList) {
            this.val$collapseBtn = button;
            this.val$rootBtn = button2;
            this.val$containers = containerArr;
            this.val$rootFinal = str;
            this.val$documentsB3gServiceList = arrayList;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$collapseBtn.setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "minusBlue.png"));
            Integer num = (Integer) this.val$rootBtn.getClientProperty("index");
            if (((Integer) this.val$rootBtn.getClientProperty("collapsed")).intValue() == 0) {
                Container container = this.val$containers[num.intValue()];
                Component componentAt = this.val$containers[num.intValue()].getComponentAt(1);
                MyEDocumentsPage myEDocumentsPage = MyEDocumentsPage.this;
                container.replace(componentAt, MyEDocumentsPage.access$000(myEDocumentsPage, myEDocumentsPage.filterByRoot(this.val$rootFinal, this.val$documentsB3gServiceList)), (Transition) null);
                this.val$containers[num.intValue()].revalidate();
                this.val$rootBtn.putClientProperty("collapsed", 1);
                for (int i = 0; i < this.val$containers.length; i++) {
                    if (i != num.intValue()) {
                        Container container2 = this.val$containers[i];
                        container2.replace(container2.getComponentAt(1), new Container(new BoxLayout(2)), (Transition) null);
                        ((Container) ((Container) this.val$containers[i].getComponentAt(0)).getComponentAt(0)).getComponentAt(0).putClientProperty("collapsed", 0);
                        ((Button) ((Container) ((Container) this.val$containers[i].getComponentAt(0)).getComponentAt(0)).getComponentAt(1)).setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "plusBlue.png"));
                        this.val$containers[i].revalidate();
                    }
                }
                return;
            }
            this.val$collapseBtn.setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "plusBlue.png"));
            this.val$containers[num.intValue()].replace(this.val$containers[num.intValue()].getComponentAt(1), new Container(), (Transition) null);
            this.val$containers[num.intValue()].revalidate();
            this.val$rootBtn.putClientProperty("collapsed", 0);
        }
    }

    private Container drawGlobalDecriptionDocumentContainer(ArrayList arrayList) {
        Container container = new Container(new BoxLayout(2));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (Display.getInstance().getPlatformName().equals("ios")) {
            Collections.reverse(arrayList);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            B3gService b3gService = (B3gService) it.next();
            String description = ((MyEDocumentsService) b3gService).getDescription();
            if (linkedHashMap.get(description) == null) {
                linkedHashMap.put(description, new ArrayList());
            }
            ((ArrayList) linkedHashMap.get(description)).add(b3gService);
        }
        Container[] containerArr = new Container[linkedHashMap.keySet().size()];
        int i = 0;
        for (String str : linkedHashMap.keySet()) {
            Container container2 = new Container(new BoxLayout(2));
            containerArr[i] = container2;
            container2.setScrollableY(false);
            containerArr[i].setScrollableX(false);
            new Container(new BoxLayout(2));
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "EdocSubType");
            Button findSubTypeBtn = this.uiManager.stateMachine.findSubTypeBtn(createContainer);
            Button findCollapseBtn = this.uiManager.stateMachine.findCollapseBtn(createContainer);
            findSubTypeBtn.putClientProperty("index", Integer.valueOf(i));
            findSubTypeBtn.putClientProperty("collapsed", 0);
            findSubTypeBtn.setText(str);
            createContainer.setLeadComponent(findSubTypeBtn);
            containerArr[i].addComponent(createContainer);
            containerArr[i].addComponent(new Container(new BoxLayout(2)));
            findSubTypeBtn.addActionListener(new 4(findCollapseBtn, findSubTypeBtn, containerArr, str, arrayList));
            container.addComponent(containerArr[i]);
            i++;
        }
        return container;
    }

    class 4 implements ActionListener {
        final /* synthetic */ Button val$collapseBtn;
        final /* synthetic */ Container[] val$containers;
        final /* synthetic */ String val$desc;
        final /* synthetic */ Button val$descriptionBtn;
        final /* synthetic */ ArrayList val$documentsB3gServiceList;

        4(Button button, Button button2, Container[] containerArr, String str, ArrayList arrayList) {
            this.val$collapseBtn = button;
            this.val$descriptionBtn = button2;
            this.val$containers = containerArr;
            this.val$desc = str;
            this.val$documentsB3gServiceList = arrayList;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$collapseBtn.setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "minusWhite.png"));
            int intValue = ((Integer) this.val$descriptionBtn.getClientProperty("index")).intValue();
            if (((Integer) this.val$descriptionBtn.getClientProperty("collapsed")).intValue() == 0) {
                Container container = this.val$containers[intValue];
                Component componentAt = container.getComponentAt(1);
                MyEDocumentsPage myEDocumentsPage = MyEDocumentsPage.this;
                container.replace(componentAt, MyEDocumentsPage.access$100(myEDocumentsPage, myEDocumentsPage.filterByDescription(this.val$desc, this.val$documentsB3gServiceList)), (Transition) null);
                this.val$containers[intValue].revalidate();
                this.val$descriptionBtn.putClientProperty("collapsed", 1);
                int i = 0;
                while (true) {
                    Container[] containerArr = this.val$containers;
                    if (i >= containerArr.length) {
                        return;
                    }
                    if (i != intValue) {
                        Container container2 = containerArr[i];
                        container2.replace(container2.getComponentAt(1), new Container(new BoxLayout(2)), (Transition) null);
                        ((Container) ((Container) this.val$containers[i].getComponentAt(0)).getComponentAt(0)).getComponentAt(0).putClientProperty("collapsed", 0);
                        ((Button) ((Container) ((Container) this.val$containers[i].getComponentAt(0)).getComponentAt(0)).getComponentAt(1)).setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "plusWhite.png"));
                        this.val$containers[i].revalidate();
                    }
                    i++;
                }
            } else {
                this.val$collapseBtn.setIcon(TreeMenuHardData.GetImageFromStringID("/cihv3.res", "plusWhite.png"));
                Container container3 = this.val$containers[intValue];
                container3.replace(container3.getComponentAt(1), new Container(), (Transition) null);
                this.val$containers[intValue].revalidate();
                this.val$descriptionBtn.putClientProperty("collapsed", 0);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Container drawGlobalAccountDocumentContainer(ArrayList arrayList) {
        Container container = new Container(new BoxLayout(2));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (Display.getInstance().getPlatformName().equals("ios")) {
            Collections.reverse(arrayList);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            B3gService b3gService = (B3gService) it.next();
            String accountNumber = ((MyEDocumentsService) b3gService).getAccountNumber();
            if (linkedHashMap.get(accountNumber) == null) {
                linkedHashMap.put(accountNumber, new ArrayList());
            }
            ((ArrayList) linkedHashMap.get(accountNumber)).add(b3gService);
        }
        Container[] containerArr = new Container[linkedHashMap.keySet().size()];
        int i = 0;
        for (String str : linkedHashMap.keySet()) {
            containerArr[i] = new Container(new BoxLayout(2));
            Container container2 = new Container(new BoxLayout(2));
            Container container3 = new Container();
            container3.setUIID("accountNumberEdocCnt");
            Label label = new Label("Compte N° : ");
            label.setUIID("labelAccountForm");
            Button button = new Button(str);
            button.setUIID("accountDocumentLabel");
            button.putClientProperty("index", Integer.valueOf(i));
            button.putClientProperty("collapsed", 0);
            label.putClientProperty("collapsed", 0);
            container3.addComponent(label);
            container3.addComponent(button);
            container3.setLeadComponent(button);
            container2.addComponent(container3);
            containerArr[i].addComponent(container2);
            Component container4 = new Container(new BoxLayout(2));
            container4.setUIID("eDocListCnt");
            containerArr[i].addComponent(container4);
            button.addActionListener(new 5(button, containerArr, str, arrayList));
            container.addComponent(containerArr[i]);
            i++;
        }
        return container;
    }

    class 5 implements ActionListener {
        final /* synthetic */ String val$acc;
        final /* synthetic */ Button val$accountBtn;
        final /* synthetic */ Container[] val$containers;
        final /* synthetic */ ArrayList val$documentsB3gServiceList;

        5(Button button, Container[] containerArr, String str, ArrayList arrayList) {
            this.val$accountBtn = button;
            this.val$containers = containerArr;
            this.val$acc = str;
            this.val$documentsB3gServiceList = arrayList;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            int intValue = ((Integer) this.val$accountBtn.getClientProperty("index")).intValue();
            if (((Integer) this.val$accountBtn.getClientProperty("collapsed")).intValue() == 0) {
                MyEDocumentsPage.this.uiManager.Draw_GroupBy((Container) this.val$containers[intValue].getComponentAt(1), MyEDocumentsPage.this.filterByAccountNumber(this.val$acc, this.val$documentsB3gServiceList), CihMBanking.sesPMTR.getIpadUiid(), 9, null);
                this.val$containers[intValue].getComponentAt(1).setUIID("eDocListCnt");
                MyEDocumentsPage.access$200(MyEDocumentsPage.this, intValue, this.val$containers);
                this.val$containers[intValue].revalidate();
                this.val$accountBtn.putClientProperty("collapsed", 1);
                return;
            }
            Container container = new Container(new BoxLayout(2));
            container.setUIID("eDocListCnt");
            Container container2 = this.val$containers[intValue];
            container2.replace(container2.getComponentAt(1), container, (Transition) null);
            this.val$containers[intValue].revalidate();
            this.val$accountBtn.putClientProperty("collapsed", 0);
        }
    }

    private void disCollapseOtherComponent(int i, Container[] containerArr) {
        for (int i2 = 0; i2 < containerArr.length; i2++) {
            if (i2 != i) {
                Container container = containerArr[i2];
                container.replace(container.getComponentAt(1), new Container(new BoxLayout(2)), (Transition) null);
                ((Container) ((Container) containerArr[i2].getComponentAt(0)).getComponentAt(0)).getComponentAt(1).putClientProperty("collapsed", 0);
                containerArr[i2].revalidate();
            }
        }
    }

    public Container drawDatePicker(Button button) {
        Container container = new Container(new BorderLayout());
        container.setUIID("grey_1_1_1_1");
        button.setAlignment(4);
        button.setUIID("lbl_regular_center");
        Label label = new Label();
        label.setIcon(this.uiManager.ressource.getImage("newListBox.png"));
        container.add("Center", button).add("East", label);
        return container;
    }

    private Container drawPicker(Picker picker, String[] strArr) {
        Container container = new Container(new BorderLayout());
        container.setUIID("grey_1_1_1_1");
        picker.setType(4);
        picker.setUIID("lbl_regular");
        if (strArr != null) {
            picker.setStrings(strArr);
        }
        picker.setSelectedString("Sélectionner");
        picker.setAlignment(4);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("newListBox.png"));
        container.add("Center", picker).add("East", label);
        container.setLeadComponent(picker);
        return container;
    }

    public void ShowDialog(int i, StateMachine stateMachine, B3gService b3gService, int i2, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUp");
            Container container = new Container(new BoxLayout(2));
            Label label = new Label("  Envoyer à l'adresse :");
            label.setUIID("lbllhMediumConseil");
            TextField textField = new TextField(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email);
            addressText = textField;
            textField.setConstraint(1);
            addressText.setUIID("textFieldPopUpStyle");
            container.addComponent(label);
            container.addComponent(addressText);
            stateMachine.findCntPopupBody(createContainer).addComponent(container);
            Label label2 = new Label(" ");
            label2.setUIID("dg_lblPopUpDemo");
            label2.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label2);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupBtn(dialog, i, b3gService, i2, stateMachine, str, str2));
            stateMachine.findLblpopupItemTitleV2(createContainer).setText(" Envoi du document :");
            stateMachine.findLblpopupItemValueV2(createContainer).getParent().removeComponent(stateMachine.findLblpopupItemValueV2(createContainer));
            Container container2 = new Container(new FlowLayout(1, 4));
            Label label3 = new Label("Relevé ");
            Label label4 = new Label(getMonth(str2));
            Label label5 = new Label(getYear(str2));
            label3.setUIID("dg_lblTitle_B");
            label4.setUIID("dg_lblTitle_B");
            label5.setUIID("dg_lblTitle_B");
            container2.addComponent(label3);
            container2.addComponent(label4);
            container2.addComponent(label5);
            stateMachine.findCntPopUpLeftLabel(createContainer).addComponent(container2);
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
    }

    public Container GetPopupBtn(Dialog dialog, int i, B3gService b3gService, int i2, StateMachine stateMachine, String str, String str2) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("Annuler");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(stateMachine.uiManager.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 6(dialog));
        container.addComponent(button);
        Button button2 = new Button("Envoyer");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(stateMachine.uiManager.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 7(dialog, str, str2, i2));
        container.addComponent(button2);
        return container;
    }

    class 6 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        6(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 7 implements ActionListener {
        final /* synthetic */ int val$ServiceIdWS;
        final /* synthetic */ String val$acountNumber;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ String val$date;

        7(Dialog dialog, String str, String str2, int i) {
            this.val$d = dialog;
            this.val$acountNumber = str;
            this.val$date = str2;
            this.val$ServiceIdWS = i;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsDemo() == 0) {
                if (!MyEDocumentsPage.access$300().getText().equals("")) {
                    if (MyEDocumentsPage.access$400(MyEDocumentsPage.this, MyEDocumentsPage.access$300().getText())) {
                        this.val$d.dispose();
                        ServiceResponse transfExtraitProcess = MyEDocumentsPage.this.transfExtraitProcess(this.val$acountNumber, this.val$date);
                        if (this.val$ServiceIdWS == 11) {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageEDocument(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, this.val$date, null, transfExtraitProcess.getStatusCode());
                            return;
                        } else {
                            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(transfExtraitProcess.getStatusLabel()), null);
                            return;
                        }
                    }
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez entrer une adresse e-mail valide"), null);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Veuillez entrer l'adresse e-mail"), null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MyEDocumentsPage.this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
        }
    }

    private boolean mailAddresChecker(String str) {
        int indexOf = str.indexOf("@");
        String substring = str.substring(indexOf + 1);
        int indexOf2 = substring.indexOf(".");
        return (indexOf == -1 || indexOf == 0 || indexOf2 == -1 || indexOf2 <= 0 || indexOf2 == substring.length() - 1) ? false : true;
    }

    public String formatDate(String str) {
        String substring = str.substring(0, 4);
        if (str.substring(4, 6).equals("01")) {
            substring = "JANVIER " + substring;
        }
        if (str.substring(4, 6).equals("02")) {
            substring = "FEVRIER " + substring;
        }
        if (str.substring(4, 6).equals("03")) {
            substring = "MARS " + substring;
        }
        if (str.substring(4, 6).equals("04")) {
            substring = "AVRIL " + substring;
        }
        if (str.substring(4, 6).equals("05")) {
            substring = "MAI " + substring;
        }
        if (str.substring(4, 6).equals("06")) {
            substring = "JUIN " + substring;
        }
        if (str.substring(4, 6).equals("07")) {
            substring = "JUILLET " + substring;
        }
        if (str.substring(4, 6).equals("08")) {
            substring = "AOUT " + substring;
        }
        if (str.substring(4, 6).equals("09")) {
            substring = "SEPTEMBRE " + substring;
        }
        if (str.substring(4, 6).equals("10")) {
            substring = "OCTOBRE " + substring;
        }
        if (str.substring(4, 6).equals("11")) {
            substring = "NOVEMBRE " + substring;
        }
        return str.substring(4, 6).equals("12") ? "DECEMBRE " + substring : substring;
    }

    public String getMonth(String str) {
        String str2 = str.substring(4, 6).equals("01") ? "JANVIER " : "";
        if (str.substring(4, 6).equals("02")) {
            str2 = "FEVRIER ";
        }
        if (str.substring(4, 6).equals("03")) {
            str2 = "MARS ";
        }
        if (str.substring(4, 6).equals("04")) {
            str2 = "AVRIL ";
        }
        if (str.substring(4, 6).equals("05")) {
            str2 = "MAI ";
        }
        if (str.substring(4, 6).equals("06")) {
            str2 = "JUIN ";
        }
        if (str.substring(4, 6).equals("07")) {
            str2 = "JUILLET ";
        }
        if (str.substring(4, 6).equals("08")) {
            str2 = "AOUT ";
        }
        if (str.substring(4, 6).equals("09")) {
            str2 = "SEPTEMBRE ";
        }
        if (str.substring(4, 6).equals("10")) {
            str2 = "OCTOBRE ";
        }
        if (str.substring(4, 6).equals("11")) {
            str2 = "NOVEMBRE ";
        }
        return str.substring(4, 6).equals("12") ? "DECEMBRE " : str2;
    }

    public String getYear(String str) {
        return str.substring(0, 4);
    }
}
