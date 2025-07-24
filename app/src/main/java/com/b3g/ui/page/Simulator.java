package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.services.B3gService;
import com.b3g.services.ServiceManager;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Simulator extends B3GPage {
    public SimulatorIskane sIskane = new SimulatorIskane(null, null, 0);
    public SimulatorConso sConsommation = new SimulatorConso(null, null, 0);

    public Simulator(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(this.sIskane.GetPageContainer(), "IMMOBILIER"));
            arrayList.add(new B3gContainer(this.sConsommation.GetPageContainer(), "CONSOMMATION"));
            this.uiManager.DrawTabsWithNavigation(this.thisContainer, arrayList);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        this.thisContainer.setUIID("mn_cntMenuItem");
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public void saveSimulatorEnter() {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        new Hashtable().put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setServiceId(900114);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setParamsIn(new Hashtable());
        serviceManager.Run(serviceRequest);
    }
}
