package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.MyCheckLcn;
import com.b3g.services.ServiceManager;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MyLcnPage extends B3GPage {
    public MyLcnPage(Object obj, Object obj2, int i) {
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
            ArrayList lcnProcess = getLcnProcess();
            ArrayList checks = getChecks((ServiceResponse) lcnProcess.get(0));
            if (checks.size() > 0) {
                for (int i = 0; i < checks.size(); i++) {
                    ((MyCheckLcn) checks.get(i)).isSent = "0";
                    ((MyCheckLcn) checks.get(i)).isLcn = "0";
                }
            }
            ArrayList checks2 = getChecks((ServiceResponse) lcnProcess.get(1));
            if (checks2.size() > 0) {
                for (int i2 = 0; i2 < checks2.size(); i2++) {
                    ((MyCheckLcn) checks2.get(i2)).isSent = "1";
                    ((MyCheckLcn) checks2.get(i2)).isLcn = "0";
                }
            }
            arrayList.add(new B3gContainer(getLcnForm(checks), "ENCAISSEMENT"));
            arrayList.add(new B3gContainer(getLcnForm(checks2), "PAIEMENT"));
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

    Container getLcnForm(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Container container = new Container(new BoxLayout(2));
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                ((MyCheckLcn) arrayList.get(i)).groupKey = ((MyCheckLcn) arrayList.get(i)).processingDate.substring(0, 10);
                arrayList2.add((B3gService) arrayList.get(i));
            }
            if (arrayList2.size() > 0) {
                this.uiManager.Draw_GroupBy(container, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 19, null);
            } else {
                container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune Lcn"));
            }
            return container;
        }
        container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune Lcn"));
        return container;
    }

    public ArrayList FillDcsDataFromServiceResponse(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
            MyCheckLcn myCheckLcn = new MyCheckLcn();
            myCheckLcn.FillDataFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i)));
            arrayList.add(myCheckLcn);
        }
        return arrayList;
    }

    ArrayList getLcnProcess() {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900029);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ArrayList) serviceManager.Run(serviceRequest);
    }

    ArrayList getChecks(ServiceResponse serviceResponse) {
        new ArrayList();
        return FillDcsDataFromServiceResponse(serviceResponse);
    }
}
