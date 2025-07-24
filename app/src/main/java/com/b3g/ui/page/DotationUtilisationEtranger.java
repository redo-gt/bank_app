package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Dotation;
import com.b3g.services.ResponseDotation;
import com.b3g.services.ServiceManager;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DotationUtilisationEtranger extends B3GPage {
    public DotationUtilisationEtranger(Object obj, Object obj2, int i) {
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
            new ResponseDotation();
            ResponseDotation responseDotation = (ResponseDotation) getResponseDotationProcess(CihMBanking.sesPMTR.dotationCode).get(0);
            Container createContainer = uIBuilder.createContainer("/cihv3", "dotationHeader");
            Container container = (Container) uIBuilder.findByName("centerCnt", createContainer);
            Label label = (Label) uIBuilder.findByName("tatouxValueLbl", createContainer);
            Label label2 = (Label) uIBuilder.findByName("lblGlobalHeaderTitle", createContainer);
            label.setText(((Dotation) this.service).utilisationEtrangerValue + " MAD");
            if (!CihMBanking.sesPMTR.dotationCode.equals("300")) {
                label2.setText("Utilisation à l'étranger");
            } else {
                label2.setText("Utilisation carte de débit");
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = responseDotation.ArrayListLieuRecharge;
            for (int i = 0; i < arrayList2.size(); i++) {
                ((ResponseDotation.LieuRecharges) arrayList2.get(i)).groupKey = ((ResponseDotation.LieuRecharges) arrayList2.get(i)).Date;
                arrayList.add((B3gService) arrayList2.get(i));
            }
            Container container2 = new Container(new BoxLayout(2));
            if (arrayList.size() > 0) {
                this.uiManager.Draw_GroupBy(container2, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 37, new ResponseDotation());
            } else {
                container2.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune opération"));
            }
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new B3gContainer(container2, "OPERATIONS"));
            this.uiManager.DrawTabsWithNavigation(container, arrayList3);
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
        serviceRequest.setServiceId(93);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return responseDotation.FillResponseDotationArrayDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }
}
