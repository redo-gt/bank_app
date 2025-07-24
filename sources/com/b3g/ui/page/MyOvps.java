package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.OvpAvenir;
import com.b3g.services.OvpService;
import com.b3g.services.ServiceManager;
import com.b3g.services.TransferHistoric;
import com.b3g.services.TransfertDATA;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MyOvps extends B3GPage {
    public MyOvps(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        this.isNew = true;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        if (Settings.isNightMode()) {
            this.thisContainer.setUIID("darkCnt");
        } else {
            this.thisContainer.setUIID("Whitemargin_2_2_2_2");
        }
        try {
            new ArrayList();
            ArrayList listOvpsProcess = new OvpService().listOvpsProcess();
            new Container(new BoxLayout(2)).setUIID("ad_CntAccountDetailsMain");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(GetContainerDetails(listOvpsProcess), "VIREMENTS PERMANENTS"));
            arrayList.add(new B3gContainer(GetContainerAvenir(), "VIREMENTS A VENIR"));
            this.uiManager.DrawTabsWithNavigation(this.thisContainer, arrayList);
        } catch (Exception unused) {
            Settings.setNightMode(this.thisContainer, 0);
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public Container GetContainerTransferHistoric(ArrayList arrayList, TransfertDATA transfertDATA) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            ((TransferHistoric) arrayList.get(i)).groupKey = ((TransferHistoric) arrayList.get(i)).OperationDate.substring(0, 10);
            arrayList2.add((B3gService) arrayList.get(i));
        }
        Container container = new Container(new BoxLayout(2));
        if (arrayList2.size() > 0) {
            this.uiManager.Draw_GroupBy(container, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 8, transfertDATA);
        } else {
            container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucun historique"));
        }
        return container;
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }

    public ServiceResponse createOvpProcess(OvpService ovpService) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("AMOUNT", ovpService.getAmount());
        hashtable.put("BENEF_ACOUNT_NUMBER", ovpService.getBeneficiaryAccountNumber());
        hashtable.put("ISSUAR_ACCOUNT_NUMBER", ovpService.getIssuarAccountNumber());
        hashtable.put("ISSUAR_ACCOUNT_OWNER", ovpService.getIssuarAccountowner());
        hashtable.put("MOTIF", ovpService.getMotif());
        hashtable.put("STARTDATE", ovpService.getStartDate());
        hashtable.put("ENDDATE", ovpService.getEndDate());
        hashtable.put("PERIODICITY", ovpService.getPeriodicity());
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900108);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ArrayList getOvpListProcess() {
        new ArrayList();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setServiceId(900107);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setParamsIn(hashtable);
        return FillOvpArrayFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public ArrayList FillOvpArrayFromServiceResponse(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
            OvpService ovpService = new OvpService();
            ovpService.FillOvpFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i)));
            arrayList.add(ovpService);
        }
        return arrayList;
    }

    private Container GetContainerDetails(ArrayList arrayList) {
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(new BoxLayout(2));
        container2.setScrollableY(true);
        container2.setPreferredH(Display.getInstance().getDisplayHeight());
        Button button = new Button("AJOUTER UN VIREMENT PERMANENT");
        button.setUIID("op_BtnOppositionValidationV2");
        button.addActionListener(new 1());
        if (arrayList != null) {
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < arrayList.size(); i++) {
                if (((OvpService) arrayList.get(i)).status.equals("001") || ((OvpService) arrayList.get(i)).status.equals("006")) {
                    arrayList2.add(0, (OvpService) arrayList.get(i));
                } else {
                    arrayList2.add((OvpService) arrayList.get(i));
                }
            }
            container2.add(this.uiManager.DrawListContainer("GloabalContainerV2", "", Boolean.TRUE, arrayList2, arrayList2.size(), 0, "Vous n'avez aucun virement permanent", null, null, null, null));
        }
        container.add("Center", container2);
        container.add("South", button);
        return container;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            MyOvps.this.uiManager.NavigateToPageById(126);
        }
    }

    private Container GetContainerAvenir() {
        Container container = new Container(new BoxLayout(2));
        new ArrayList();
        ArrayList listOvpsProcess = new OvpAvenir().listOvpsProcess();
        if (listOvpsProcess.size() > 0) {
            for (int i = 0; i < listOvpsProcess.size(); i++) {
                container.add(((OvpAvenir) listOvpsProcess.get(i)).DrawItem(this.uiManager.stateMachine, this.uiManager.ressource, 17, this.service, this.thisContainer, this.thisContainer));
            }
        }
        container.setScrollableY(true);
        container.setScrollVisible(true);
        return container;
    }
}
