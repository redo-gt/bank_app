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
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DotationRechargeCartePrepaid extends B3GPage {
    public DotationRechargeCartePrepaid(Object obj, Object obj2, int i) {
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
            Container createContainer = uIBuilder.createContainer("/cihv3", "DotationRechargeCardPrepaid");
            Container container = (Container) uIBuilder.findByName("centerCnt", createContainer);
            Label label = (Label) uIBuilder.findByName("tatouxValueLbl", createContainer);
            Label label2 = (Label) uIBuilder.findByName("lblGlobalHeaderTitle", createContainer);
            Label label3 = (Label) uIBuilder.findByName("consoValueLbl", createContainer);
            Label label4 = (Label) uIBuilder.findByName("soldeValueLbl", createContainer);
            Button button = (Button) uIBuilder.findByName("detailBtn", createContainer);
            label.setText(((Dotation) this.service).rechargeCartePrepaidValue + " MAD");
            if (CihMBanking.sesPMTR.dotationCode.equals("0301")) {
                label2.setText("Recharge carte E-Shopping");
            } else if (CihMBanking.sesPMTR.dotationCode.equals("300")) {
                label2.setText("Recharge carte Al Moussafir");
            }
            button.addActionListener(new 1());
            Container container2 = new Container(new BoxLayout(2));
            try {
                ResponseDotation responseDotation = (ResponseDotation) getResponseDotationProcess(CihMBanking.sesPMTR.dotationCode).get(0);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = responseDotation.ArrayListRecharge;
                label3.setText(responseDotation.ConsomationPrep + " MAD");
                label4.setText(responseDotation.SoldeCartePrep + " MAD");
                CihMBanking.sesPMTR.dotationCosommation = responseDotation.ConsomationPrep;
                for (int i = 0; i < arrayList2.size(); i++) {
                    ((ResponseDotation.Recharge) arrayList2.get(i)).groupKey = ((ResponseDotation.Recharge) arrayList2.get(i)).Date;
                    arrayList.add((B3gService) arrayList2.get(i));
                }
                if (arrayList.size() > 0) {
                    this.uiManager.Draw_GroupBy(container2, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 38, new ResponseDotation());
                } else {
                    container2.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune opération"));
                }
            } catch (Exception unused) {
                container2.addComponent(this.uiManager.GetCntMessage("Une erreur est survenue, Veuillez réessayer plus tard"));
            }
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new B3gContainer(container2, "OPERATIONS"));
            this.uiManager.DrawTabsWithNavigation(container, arrayList3);
            this.thisContainer.add(createContainer);
            return this.thisContainer;
        } catch (Exception unused2) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            return this.thisContainer;
        }
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DotationRechargeCartePrepaid.this.uiManager.NavigateToPageById(71);
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
        serviceRequest.setServiceId(92);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return responseDotation.FillResponseDotationArrayDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }
}
