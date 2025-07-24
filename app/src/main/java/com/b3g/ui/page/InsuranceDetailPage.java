package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Inssurance;
import com.b3g.services.InssuranceAvenirCompte;
import com.b3g.services.InssuranceAvenirEpargne;
import com.b3g.services.InssuranceAvenirEpargnePlus;
import com.b3g.services.InssuranceAvenirHabitation;
import com.b3g.services.InssuranceAvenirHimaya;
import com.b3g.services.InssuranceAvenirPrevoyance;
import com.b3g.services.InssuranceAvenirRetraite;
import com.b3g.services.IssurancesAvenirEducation;
import com.b3g.ui.B3GUiManager;
import com.codename1.io.JSONParser;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class InsuranceDetailPage extends B3GPage {
    private JSONParser parser = new JSONParser();

    public InsuranceDetailPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            Inssurance inssurance = (Inssurance) this.service;
            new ArrayList().add(inssurance);
            int i = 0;
            if (inssurance.ProductName.equals("Avenir Compte")) {
                ArrayList arrayList = new ArrayList();
                new ArrayList();
                CihMBanking.sesPMTR.getSessionClient().getClient_InssuranceList();
                if (inssurance.bancAssurancesAvenirCompte != null) {
                    arrayList = inssurance.bancAssurancesAvenirCompte;
                    while (i < arrayList.size()) {
                        ((InssuranceAvenirCompte) arrayList.get(i)).inssurance = inssurance;
                        i++;
                    }
                }
                ArrayList arrayList2 = arrayList;
                this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Avenir Compte", Boolean.TRUE, arrayList2, arrayList2.size(), 11, "", inssurance, null, null, null));
            } else if (inssurance.ProductName.equals("Avenir Habitation")) {
                ArrayList arrayList3 = new ArrayList();
                if (inssurance.bancAssurancesAvenirHabitation != null) {
                    arrayList3 = inssurance.bancAssurancesAvenirHabitation;
                    while (i < arrayList3.size()) {
                        ((InssuranceAvenirHabitation) arrayList3.get(i)).inssurance = inssurance;
                        i++;
                    }
                }
                ArrayList arrayList4 = arrayList3;
                this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Avenir Habitation", Boolean.TRUE, arrayList4, arrayList4.size(), 12, "", inssurance, null, null, null));
            } else if (inssurance.ProductName.equals("Avenir Himaya")) {
                ArrayList arrayList5 = new ArrayList();
                new ArrayList();
                CihMBanking.sesPMTR.getSessionClient().getClient_InssuranceList();
                if (inssurance.bancAssurancesAvenirHimaya != null) {
                    arrayList5 = inssurance.bancAssurancesAvenirHimaya;
                    while (i < arrayList5.size()) {
                        ((InssuranceAvenirHimaya) arrayList5.get(i)).inssurance = inssurance;
                        i++;
                    }
                }
                ArrayList arrayList6 = arrayList5;
                this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Avenir Himaya", Boolean.TRUE, arrayList6, arrayList6.size(), 13, "", inssurance, null, null, null));
            } else if (inssurance.ProductName.equals("Avenir Retraite")) {
                ArrayList arrayList7 = new ArrayList();
                new ArrayList();
                CihMBanking.sesPMTR.getSessionClient().getClient_InssuranceList();
                if (inssurance.bancAssurancesAvenirRetraite != null) {
                    arrayList7 = inssurance.bancAssurancesAvenirRetraite;
                    while (i < arrayList7.size()) {
                        ((InssuranceAvenirRetraite) arrayList7.get(i)).inssurance = inssurance;
                        i++;
                    }
                }
                ArrayList arrayList8 = arrayList7;
                this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Avenir Retraite", Boolean.TRUE, arrayList8, arrayList8.size(), 14, "", inssurance, null, null, null));
            } else if (inssurance.ProductName.equals("Avenir Education")) {
                ArrayList arrayList9 = new ArrayList();
                new ArrayList();
                CihMBanking.sesPMTR.getSessionClient().getClient_InssuranceList();
                if (inssurance.bancAssuranceAvenirEduc != null) {
                    arrayList9 = inssurance.bancAssuranceAvenirEduc;
                    while (i < arrayList9.size()) {
                        ((IssurancesAvenirEducation) arrayList9.get(i)).inssurance = inssurance;
                        i++;
                    }
                }
                ArrayList arrayList10 = arrayList9;
                this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Avenir Education", Boolean.TRUE, arrayList10, arrayList10.size(), 14, "", inssurance, null, null, null));
            } else if (inssurance.ProductName.equals("Avenir Prévoyance")) {
                ArrayList arrayList11 = new ArrayList();
                new ArrayList();
                CihMBanking.sesPMTR.getSessionClient().getClient_InssuranceList();
                if (inssurance.bancAssurancesAvenirPrevoyance != null) {
                    arrayList11 = inssurance.bancAssurancesAvenirPrevoyance;
                    while (i < arrayList11.size()) {
                        ((InssuranceAvenirPrevoyance) arrayList11.get(i)).inssurance = inssurance;
                        i++;
                    }
                }
                ArrayList arrayList12 = arrayList11;
                this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Avenir Prevoyence", Boolean.TRUE, arrayList12, arrayList12.size(), 15, "", inssurance, null, null, null));
            } else if (inssurance.ProductName.equals("Avenir Epargne")) {
                ArrayList arrayList13 = new ArrayList();
                new ArrayList();
                CihMBanking.sesPMTR.getSessionClient().getClient_InssuranceList();
                if (inssurance.bancAssurancesAvenirEpargne != null) {
                    arrayList13 = inssurance.bancAssurancesAvenirEpargne;
                    while (i < arrayList13.size()) {
                        ((InssuranceAvenirEpargne) arrayList13.get(i)).inssurance = inssurance;
                        i++;
                    }
                }
                ArrayList arrayList14 = arrayList13;
                this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Avenir Epargne", Boolean.TRUE, arrayList14, arrayList14.size(), 14, "", inssurance, null, null, null));
            } else if (inssurance.ProductName.equals("Avenir EpargnePlus")) {
                ArrayList arrayList15 = new ArrayList();
                new ArrayList();
                CihMBanking.sesPMTR.getSessionClient().getClient_InssuranceList();
                if (inssurance.bancAssurancesAvenirEpargnePlus != null) {
                    arrayList15 = inssurance.bancAssurancesAvenirEpargnePlus;
                    while (i < arrayList15.size()) {
                        ((InssuranceAvenirEpargnePlus) arrayList15.get(i)).inssurance = inssurance;
                        i++;
                    }
                }
                ArrayList arrayList16 = arrayList15;
                this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Avenir EpargnePlus", Boolean.TRUE, arrayList16, arrayList16.size(), 14, "", inssurance, null, null, null));
            }
            Container container = new Container();
            container.setUIID("g_CntTranspMsg");
            this.thisContainer.addComponent(container);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    public Hashtable ParamOutToHashtable(String str) {
        Hashtable hashtable = new Hashtable();
        System.err.println("Paramout To hashtable :" + str);
        if (!str.equals("\"010\"")) {
            try {
                hashtable = this.parser.parse(new InputStreamReader(new ByteArrayInputStream(str.getBytes())));
                if (hashtable.get("root") != null) {
                    Vector vector = (Vector) hashtable.get("root");
                    System.err.println("rParamOut : " + vector);
                    for (int i = 0; i < vector.size(); i++) {
                        if (vector.get(i) != null) {
                            hashtable.put(Integer.valueOf(i), (Hashtable) vector.get(i));
                        }
                    }
                }
            } catch (IOException unused) {
            }
        }
        System.err.println("HparamOut : " + hashtable);
        return hashtable;
    }
}
