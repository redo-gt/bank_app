package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.CheckbookDemand;
import com.b3g.services.LCNBoockDemand;
import com.b3g.services.LCNCheckBoockDemandResume;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CheckBookDemandPage extends B3GPage {
    public CheckBookDemandPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            LCNCheckBoockDemandResume CheckbookDemandProcess = new CheckbookDemand().CheckbookDemandProcess();
            ArrayList checkbookDemandList = CheckbookDemandProcess.getCheckbookDemandList();
            ArrayList lCNBoockDemandList = CheckbookDemandProcess.getLCNBoockDemandList();
            ArrayList arrayList = new ArrayList();
            B3gContainer b3gContainer = new B3gContainer(GetCheckBookTabContainer(checkbookDemandList), "CHEQUIER");
            if (CihMBanking.sesPMTR.getTabPosition().intValue() == 0) {
                b3gContainer.IsToBeSelected = true;
            }
            arrayList.add(b3gContainer);
            B3gContainer b3gContainer2 = new B3gContainer(GetLCNBookTabContainer(lCNBoockDemandList), "LCN");
            if (CihMBanking.sesPMTR.getTabPosition().intValue() == 1) {
                b3gContainer2.IsToBeSelected = true;
            }
            arrayList.add(b3gContainer2);
            this.uiManager.DrawTabsWithNavigation(container, arrayList);
            this.thisContainer.addComponent(container);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public Container GetCheckBookTabContainer(ArrayList arrayList) {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "ChecksLCNDemandV3");
        createContainer.setScrollableY(true);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList());
        purgeForbidenAccount(arrayList2);
        Container DrawListContainer = this.uiManager.DrawListContainer("GloabalContainerV2", "Compte", Boolean.TRUE, arrayList2, 1, 23, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, this.uiManager.stateMachine.findCntPageNumberRadio1(createContainer), createContainer, null);
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(DrawListContainer);
        if (((Account) arrayList2.get(0)).codeOffre.equals("CPTCQJ11")) {
            this.uiManager.stateMachine.findCntPageNumberRadio1(createContainer).setVisible(false);
        }
        this.uiManager.stateMachine.findCntDemandList(createContainer).addComponent(GetContainerCheckbookHistoric(arrayList));
        this.uiManager.stateMachine.findRdPageNumberValue2(createContainer).setGroup("PageNumberCheckBookDemand");
        this.uiManager.stateMachine.findRdPageNumberValue3(createContainer).setGroup("PageNumberCheckBookDemand");
        this.uiManager.stateMachine.findRdPageNumberValue4(createContainer).setGroup("PageNumberCheckBookDemand");
        this.uiManager.stateMachine.findRdQuantityValue1(createContainer).setGroup("QuantityCheckBookDemand");
        this.uiManager.stateMachine.findRdQuantityValue2(createContainer).setGroup("QuantityCheckBookDemand");
        this.uiManager.stateMachine.findBtnDemandValidation(createContainer).addActionListener(new 1(DrawListContainer, new RadioButton[]{this.uiManager.stateMachine.findRdPageNumberValue2(createContainer), this.uiManager.stateMachine.findRdPageNumberValue3(createContainer), this.uiManager.stateMachine.findRdPageNumberValue4(createContainer)}, createContainer));
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            this.uiManager.stateMachine.findBtnDemandValidation(createContainer).setEnabled(false);
        }
        return createContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$AccountContainerList;
        final /* synthetic */ Container val$cntCheckBookDemandForm;
        final /* synthetic */ RadioButton[] val$radioButtons;

        1(Container container, RadioButton[] radioButtonArr, Container container2) {
            this.val$AccountContainerList = container;
            this.val$radioButtons = radioButtonArr;
            this.val$cntCheckBookDemandForm = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CheckBookDemandPage checkBookDemandPage = CheckBookDemandPage.this;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCheckBookDemandService(22, checkBookDemandPage.GetCheckbookDemand(this.val$AccountContainerList, this.val$radioButtons, checkBookDemandPage.uiManager.stateMachine.findRdQuantityValue1(this.val$cntCheckBookDemandForm), CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical), CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 11);
        }
    }

    public Container GetContainerCheckbookHistoric(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            ((CheckbookDemand) arrayList.get(i)).groupKey = ((CheckbookDemand) arrayList.get(i)).Date.substring(0, 10);
            arrayList2.add((B3gService) arrayList.get(i));
        }
        Container container = new Container(new BoxLayout(2));
        if (arrayList2.size() > 0) {
            this.uiManager.Draw_GroupBy(container, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 14, null);
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune Demande"));
        }
        return container;
    }

    public CheckbookDemand GetCheckbookDemand(Container container, RadioButton[] radioButtonArr, RadioButton radioButton, String str) {
        CheckbookDemand checkbookDemand = new CheckbookDemand();
        for (int i = 0; i < radioButtonArr.length; i++) {
            if (radioButtonArr[i].isSelected()) {
                if (i == 0) {
                    checkbookDemand.PageNumber = "10";
                } else if (i == 1) {
                    checkbookDemand.PageNumber = "25";
                } else if (i == 2) {
                    checkbookDemand.PageNumber = "50";
                }
            }
        }
        if (radioButton.isSelected()) {
            checkbookDemand.Quantity = "1";
        } else {
            checkbookDemand.Quantity = "2";
        }
        checkbookDemand.ClientId = str;
        checkbookDemand.AccountNumber = ((Account) ((B3gService) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getClientProperty("AccountDClient"))).accountNumber;
        checkbookDemand.StatusDemand = "0";
        return checkbookDemand;
    }

    public Container GetLCNBookTabContainer(ArrayList arrayList) {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "ChecksLCNDemandV2");
        createContainer.setScrollableY(true);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList());
        purgeForbidenAccount(arrayList2);
        Container DrawListContainer = this.uiManager.DrawListContainer("GloabalContainerV2", "Compte", Boolean.TRUE, arrayList2, 1, 70, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, null, null, null);
        this.uiManager.stateMachine.findCntAccountChosen(createContainer).addComponent(DrawListContainer);
        this.uiManager.stateMachine.findCntDemandList(createContainer).addComponent(GetContainerLCNbookHistoric(arrayList));
        this.uiManager.stateMachine.findCntPageNumberRadio(createContainer).removeAll();
        this.uiManager.stateMachine.findRdPageNumberValue3(createContainer).setGroup("PageNumberLCNBoockDemand");
        this.uiManager.stateMachine.findRdPageNumberValue4(createContainer).setGroup("PageNumberLCNBoockDemand");
        this.uiManager.stateMachine.findRdQuantityValue1(createContainer).setGroup("QuantityLCNBoockDemand");
        this.uiManager.stateMachine.findRdQuantityValue2(createContainer).setGroup("QuantityLCNBoockDemand");
        this.uiManager.stateMachine.findBtnDemandValidation(createContainer).addActionListener(new 2(DrawListContainer, createContainer));
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            this.uiManager.stateMachine.findBtnDemandValidation(createContainer).setEnabled(false);
        }
        return createContainer;
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$AccountContainerList;
        final /* synthetic */ Container val$cntLCNBookDemandForm;

        2(Container container, Container container2) {
            this.val$AccountContainerList = container;
            this.val$cntLCNBookDemandForm = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CheckBookDemandPage checkBookDemandPage = CheckBookDemandPage.this;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogCheckBookDemandService(24, checkBookDemandPage.GetlCNbookDemand(this.val$AccountContainerList, checkBookDemandPage.uiManager.stateMachine.findRdPageNumberValue3(this.val$cntLCNBookDemandForm), CheckBookDemandPage.this.uiManager.stateMachine.findRdQuantityValue1(this.val$cntLCNBookDemandForm), CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical), CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 11);
        }
    }

    public Container GetContainerLCNbookHistoric(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            ((LCNBoockDemand) arrayList.get(i)).groupKey = ((LCNBoockDemand) arrayList.get(i)).Date.substring(0, 10);
            arrayList2.add((B3gService) arrayList.get(i));
        }
        Container container = new Container(new BoxLayout(2));
        if (arrayList2.size() > 0) {
            this.uiManager.Draw_GroupBy(container, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 15, null);
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune Demande"));
        }
        return container;
    }

    public LCNBoockDemand GetlCNbookDemand(Container container, RadioButton radioButton, RadioButton radioButton2, String str) {
        LCNBoockDemand lCNBoockDemand = new LCNBoockDemand();
        if (radioButton.isSelected()) {
            lCNBoockDemand.PageNumber = "25";
        } else {
            lCNBoockDemand.PageNumber = "50";
        }
        if (radioButton2.isSelected()) {
            lCNBoockDemand.Quantity = "1";
        } else {
            lCNBoockDemand.Quantity = "2";
        }
        lCNBoockDemand.ClientId = str;
        lCNBoockDemand.AccountNumber = ((Account) ((B3gService) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getClientProperty("AccountDClient"))).accountNumber;
        lCNBoockDemand.StatusDemand = "0";
        return lCNBoockDemand;
    }

    void purgeForbidenAccount(ArrayList arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            String substring = ((Account) arrayList.get(i)).rib.substring(13, 17);
            if (substring.equals("2310") || substring.equals("2360") || substring.equals("2311")) {
                arrayList.remove(i);
                i--;
            }
            i++;
        }
    }
}
