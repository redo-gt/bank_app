package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Dotation;
import com.b3g.services.ResponseDotation;
import com.b3g.services.ServiceManager;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DotationCartePrepayeesPage extends B3GPage {
    public DotationCartePrepayeesPage(Object obj, Object obj2, int i) {
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
            Container createContainer = uIBuilder.createContainer("/cihv3", "dotationCartePrepaid");
            Container container = (Container) uIBuilder.findByName("centerCnt", createContainer);
            ((Label) uIBuilder.findByName("tatouxValueLbl", createContainer)).setText(((Dotation) this.service).cartePrepaidValue + " MAD");
            Label label = (Label) uIBuilder.findByName("lblGlobalHeaderTitle", createContainer);
            if (CihMBanking.sesPMTR.dotationCode.equals("0301")) {
                label.setText("Mes cartes E-Shopping");
            } else if (CihMBanking.sesPMTR.dotationCode.equals("300")) {
                label.setText("Mes cartes Al Moussafir");
            }
            try {
                ResponseDotation responseDotation = (ResponseDotation) getResponseDotationProcess(CihMBanking.sesPMTR.dotationCode).get(0);
                if (responseDotation.AvailableArrayList.size() > 0) {
                    container.add(this.uiManager.DrawListContainer("GloabalContainerV2", "", Boolean.FALSE, responseDotation.AvailableArrayList, 1, this.PageId, null, null, null, null, null));
                } else {
                    container.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune carte"));
                }
            } catch (Exception unused) {
                container.addComponent(this.uiManager.GetCntMessage("Une erreur est survenue, Veuillez réessayer plus tard"));
            }
            this.thisContainer.add(createContainer);
            return this.thisContainer;
        } catch (Exception unused2) {
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
