package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.GetDotation;
import com.b3g.services.ResponseDotation;
import com.b3g.services.ServiceManager;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DotationConsommationCartePrepaid extends B3GPage {
    public DotationConsommationCartePrepaid(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.setScrollableY(true);
        UIBuilder uIBuilder = new UIBuilder();
        try {
            ArrayList utilisationsProcess = new GetDotation().getUtilisationsProcess(CihMBanking.sesPMTR.dotationCode);
            Container createContainer = uIBuilder.createContainer("/cihv3", "dotationHeader");
            Container container = (Container) uIBuilder.findByName("centerCnt", createContainer);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < utilisationsProcess.size(); i++) {
                ((GetDotation) utilisationsProcess.get(i)).groupKey = ((GetDotation) utilisationsProcess.get(i)).operationDate;
                arrayList.add((B3gService) utilisationsProcess.get(i));
            }
            Container container2 = new Container(new BoxLayout(2));
            if (arrayList.size() > 0) {
                this.uiManager.Draw_GroupBy(container2, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 36, new GetDotation());
            } else {
                container2.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune opération"));
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new B3gContainer(container2, "OPERATIONS"));
            this.uiManager.DrawTabsWithNavigation(container, arrayList2);
            this.thisContainer.add(createContainer);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            return this.thisContainer;
        }
    }

    public ArrayList getResponseDotationProcess(String str) {
        new ArrayList();
        ResponseDotation responseDotation = new ResponseDotation();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSION_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("DOTATION_CODE", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(94);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return responseDotation.FillResponseDotationArrayDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }
}
