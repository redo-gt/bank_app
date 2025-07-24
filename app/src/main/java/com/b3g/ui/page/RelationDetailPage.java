package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.AccountAutorisation;
import com.b3g.services.AccountOperation;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.services.Relation;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class RelationDetailPage extends B3GPage {
    public RelationDetailPage(Object obj, Object obj2, int i) {
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
            arrayList.add(new B3gContainer(GetRelationAccountDetailsContainer(), "OPERATIONS"));
            arrayList.add(new B3gContainer(GetRelationCardsContainer(), "CARTES"));
            this.uiManager.DrawTabsWithNavigation(this.thisContainer, arrayList);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            return this.thisContainer;
        }
    }

    public Container GetRelationAccountDetailsContainer() {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("mn_cntMenuItem");
        try {
            Relation relation = (Relation) this.service;
            CihMBanking.sesPMTR.setCurrentAccount(relation.getAccount());
            ArrayList arrayList = new ArrayList();
            ArrayList CerlRelOperationProcess = relation.getAccount().CerlRelOperationProcess(300021);
            for (int i = 0; i < CerlRelOperationProcess.size(); i++) {
                ((AccountOperation) CerlRelOperationProcess.get(i)).groupKey = ((AccountOperation) CerlRelOperationProcess.get(i)).date;
                arrayList.add((B3gService) CerlRelOperationProcess.get(i));
            }
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "AccountDetailV2");
            this.uiManager.stateMachine.findLblAccountNumberValue(createContainer).setText(relation.getAccount().accountNumber);
            this.uiManager.stateMachine.findLblBalanceActValue(createContainer).setText(relation.getAccount().balance);
            if (DataTools.PurgeBlankFromStringAmount(relation.getAccount().balanceBrut).doubleValue() > 0.0d) {
                this.uiManager.stateMachine.findLblBalanceActValue(createContainer).setUIID("ad_lblValueGreen");
            } else {
                this.uiManager.stateMachine.findLblBalanceActValue(createContainer).setUIID("ad_lblValueRed");
            }
            if (relation.getAccount().AccountAutorisationList.size() > 0) {
                this.uiManager.stateMachine.findLblAutorisationAmountValue(createContainer).setText(((AccountAutorisation) relation.getAccount().AccountAutorisationList.get(0)).montant);
                if (DataTools.PurgeBlankFromStringAmount(((AccountAutorisation) relation.getAccount().AccountAutorisationList.get(0)).montant).doubleValue() > 0.0d) {
                    this.uiManager.stateMachine.findLblAutorisationAmountValue(createContainer).setUIID("ad_lblValueGreen");
                } else {
                    this.uiManager.stateMachine.findLblAutorisationAmountValue(createContainer).setUIID("ad_lblValueRed");
                }
                this.uiManager.stateMachine.findLblTermDateValue(createContainer).setText(((AccountAutorisation) relation.getAccount().AccountAutorisationList.get(0)).dateEchenance);
            } else {
                Component componentAt = ((Container) createContainer.getComponentAt(0)).getComponentAt(2);
                Component componentAt2 = ((Container) createContainer.getComponentAt(0)).getComponentAt(3);
                ((Container) createContainer.getComponentAt(0)).removeComponent(componentAt);
                ((Container) createContainer.getComponentAt(0)).removeComponent(componentAt2);
                createContainer.revalidate();
            }
            Container container2 = new Container(new BoxLayout(2));
            if (arrayList.size() > 0) {
                this.uiManager.Draw_GroupBy(container2, arrayList, CihMBanking.sesPMTR.getIpadUiid(), 1, ((Relation) this.service).getAccount());
                container2.setScrollableX(false);
                container2.setScrollVisible(false);
            } else {
                container2.addComponent(this.uiManager.GetCntMessage("Vous n'avez aucune opération"));
            }
            container.addComponent(container2);
        } catch (Exception unused) {
            container.addComponent(this.uiManager.GetGoBackHome());
        }
        return container;
    }

    public Container GetRelationCardsContainer() {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("mn_cntMenuItem");
        try {
            ArrayList CardProcess = CardProcess();
            if (CardProcess.size() > 0) {
                container.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Mes Cartes", Boolean.TRUE, CardProcess, CardProcess.size(), 4, CihMBanking.sesPMTR.getSessionClient().getClient_CardList_StatusLabel(), null, null, null, null));
                Container container2 = new Container();
                container2.setUIID("g_CntTranspMsg");
                container.addComponent(container2);
            } else {
                SpanLabel spanLabel = new SpanLabel("Aucune carte de cette relation n'est disponible pour le moment .");
                spanLabel.setUIID("dg_splblMsgCenter");
                spanLabel.setScrollVisible(false);
                spanLabel.setIconPosition("West");
                spanLabel.setTextUIID("dg_lblSPError");
                spanLabel.setIconUIID("g_cntEmpty");
                container.removeAll();
                container.addComponent(spanLabel);
                container.revalidate();
            }
        } catch (Exception unused) {
            container.addComponent(this.uiManager.GetGoBackHome());
        }
        return container;
    }

    public ArrayList CardProcess() {
        Relation relation = (Relation) this.service;
        new ArrayList();
        Card card = new Card();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENTID", relation.getAccount().accountNumber.substring(0, 7));
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(15);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return card.FillCardArrayDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }
}
