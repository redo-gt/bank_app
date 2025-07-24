package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.ReleveDgi;
import com.b3g.services.ServiceManager;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.Accordion;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DGIPaymentHistoric extends B3GPage {
    public DGIPaymentHistoric(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new LayeredLayout());
        this.thisContainer.setUIID("mn_cntMenuItem");
        Container container = new Container(BoxLayout.y());
        Label label = new Label("Paiement Vignette");
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        label.setScrollVisible(false);
        label.setEnabled(false);
        container.addComponent(label);
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("greyCnt");
        container2.setScrollVisible(false);
        container.addComponent(container2);
        container.addComponent(GetDGIPaymentHistoric());
        Container container3 = new Container(new BorderLayout());
        container3.setUIID("Container");
        Container container4 = new Container(new BorderLayout());
        container4.setUIID("Container");
        Button button = new Button(this.uiManager.ressource.getImage("flottBtnpng.png"));
        button.setUIID("margin_3_3_3_3");
        button.addActionListener(new 1());
        container3.addComponent("East", button);
        container4.addComponent("South", container3);
        this.thisContainer.add(container);
        this.thisContainer.add(container4);
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DGIPaymentHistoric.this.uiManager.NavigateToPageById(53);
        }
    }

    public Container GetDGIPaymentHistoric() {
        Container container = new Container(new BoxLayout(2));
        container.setScrollableY(true);
        container.setScrollVisible(false);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DgiHistoricProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical));
        if (arrayList.size() > 0) {
            HashMap hashMap = new HashMap();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ReleveDgi releveDgi = (ReleveDgi) it.next();
                String str = releveDgi.matricule;
                if (hashMap.containsKey(str)) {
                    ((List) hashMap.get(str)).add(releveDgi);
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(releveDgi);
                    hashMap.put(str, arrayList2);
                }
            }
            Iterator it2 = hashMap.keySet().iterator();
            while (it2.hasNext()) {
                container.add(drawVignetteItem((ArrayList) hashMap.get((String) it2.next())));
            }
        } else {
            Layout flowLayout = new FlowLayout(4, 4);
            Container container2 = new Container();
            container2.setLayout(flowLayout);
            container2.setUIID("g_CntTranspMsg");
            Container container3 = new Container(BoxLayout.y());
            SpanLabel spanLabel = new SpanLabel("Vous n'avez aucun véhicule enregistré.");
            spanLabel.setTextUIID("dg_center_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            SpanLabel spanLabel2 = new SpanLabel("pour effectuer un nouveau paiement");
            spanLabel2.setTextUIID("dg_center_lblSPError");
            spanLabel2.setIconUIID("g_cntEmpty");
            Label label = new Label("Tapez sur ");
            label.setUIID("dg_center_lblSPError");
            label.setIcon(this.uiManager.ressource.getImage("circularPlus.png"));
            label.setTextPosition(1);
            container3.addComponent(spanLabel);
            container3.addComponent(spanLabel2);
            container3.addComponent(label);
            container2.addComponent(container3);
            container.addComponent(container2);
        }
        return container;
    }

    public ArrayList DgiHistoricProcess(String str) {
        new ArrayList();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", str);
        hashtable.put("CLIENT_ID", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900139);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return FillDgiOperationArrayFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public ArrayList FillDgiOperationArrayFromServiceResponse(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
            ReleveDgi releveDgi = new ReleveDgi();
            releveDgi.FillOperationFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i)));
            arrayList.add(releveDgi);
        }
        return arrayList;
    }

    private Container drawVignetteItem(ArrayList arrayList) {
        Container container = new Container(new BorderLayout());
        container.setUIID("margin_2_0_0_0");
        Accordion accordion = new Accordion();
        accordion.setUIID("Container");
        accordion.setScrollableX(false);
        accordion.setScrollableY(false);
        accordion.setOpenIcon(this.uiManager.ressource.getImage(""));
        accordion.setCloseIcon(this.uiManager.ressource.getImage(""));
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer(this.uiManager.ressource, "AccountOperationItemV22Vignette");
        Button button = (Button) uIBuilder.findByName("btnOperationEntitiledValueV2", createContainer);
        Button button2 = new Button("Payer");
        button2.setUIID("payerVignetteBtn");
        Label label = (Label) uIBuilder.findByName("lblMarqueValueV2", createContainer);
        button2.addActionListener(new 2(arrayList));
        button.setText(((ReleveDgi) arrayList.get(0)).matricule);
        label.setText(((ReleveDgi) arrayList.get(0)).marqueVehicule.toUpperCase());
        Container container2 = new Container(BoxLayout.y());
        for (int i = 0; i < arrayList.size(); i++) {
            container2.add(DrawHistoricItem(this.uiManager.stateMachine, this.uiManager.ressource, (ReleveDgi) arrayList.get(i)));
        }
        Component.setSameHeight(createContainer, button2);
        accordion.addContent(createContainer, container2);
        Container container3 = new Container(new FlowLayout(3, 0));
        container3.setUIID("Container");
        container3.add(button2);
        container.addComponent("Center", accordion);
        container.addComponent("East", container3);
        return container;
    }

    class 2 implements ActionListener {
        final /* synthetic */ ArrayList val$ReleveDgiList;

        2(ArrayList arrayList) {
            this.val$ReleveDgiList = arrayList;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPage(new DGIPaymentFormPage(DGIPaymentHistoric.this.uiManager, this.val$ReleveDgiList.get(0), 53));
        }
    }

    public Container DrawHistoricItem(StateMachine stateMachine, Resources resources, ReleveDgi releveDgi) {
        Container createContainer = stateMachine.createContainer(resources, "AccountOperationItemV22");
        stateMachine.findBtnOperationEntitiledValueV2(createContainer).setText(releveDgi.operationDate.substring(0, 10));
        stateMachine.findLblBalanceeValueV2(createContainer).setUIID("g_lblBalanceValueF");
        stateMachine.findLblBalanceeValueV2(createContainer).setText(releveDgi.totalTTC);
        stateMachine.findLblBalanceeValueV2(createContainer).setTickerEnabled(false);
        stateMachine.findBtnOperationEntitiledValueV2(createContainer).addActionListener(new 3(releveDgi, stateMachine));
        return createContainer;
    }

    class 3 implements ActionListener {
        final /* synthetic */ StateMachine val$cndUi;
        final /* synthetic */ ReleveDgi val$pReleveDgi;

        3(ReleveDgi releveDgi, StateMachine stateMachine) {
            this.val$pReleveDgi = releveDgi;
            this.val$cndUi = stateMachine;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialog(73, this.val$pReleveDgi, this.val$cndUi, null);
        }
    }
}
