package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.OvpAvenir;
import com.b3g.services.ServiceManager;
import com.b3g.ui.B3GUiManager;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OvpSuspendre extends B3GPage {
    public OvpSuspendre(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        OvpAvenir ovpAvenir = (OvpAvenir) this.service;
        Container container = new Container(new BorderLayout());
        container.setUIID("Whitemargin_2_2_2_2");
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(2, 2, 2, 2);
        Container container2 = new Container(new BoxLayout(2));
        Label label = new Label("Virement en faveur de " + ovpAvenir.creditAccountTitle);
        label.setUIID("crd_CardTitle");
        container2.add(label);
        Container container3 = new Container(new BoxLayout(2));
        container3.setUIID("SV_padding_0_0_2_2");
        SpanLabel spanLabel = new SpanLabel();
        spanLabel.setTextUIID("LblProfileSmalGreyEprn");
        spanLabel.setText("Ce virement sera effectué automatiquement à la date ci-dessous:\u200b");
        container3.add(spanLabel);
        Container container4 = new Container(new FlowLayout(4, 4));
        Label label2 = new Label();
        label2.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("WarningOvp.png"));
        container4.add(label2);
        container2.add(container3).add(container4);
        container2.add(GetContainerAvenir());
        Container container5 = new Container(new BoxLayout(2));
        container5.setUIID("padding_2_2_0_0");
        Button button = new Button("SUSPENDRE CETTE ECHEANCE");
        button.addActionListener(new 1(ovpAvenir));
        button.setUIID("OrgLabel");
        Container container6 = new Container(new BoxLayout(2));
        container6.setUIID("padding_2_2_0_0");
        Button button2 = new Button("ARRETER TOUTES LES ECHEANCES");
        button2.setUIID("sendBtn");
        button2.addActionListener(new 2(ovpAvenir));
        container6.add(button2);
        container5.add(button).add(container6);
        container2.add(container5);
        container.add("Center", container2);
        this.thisContainer = container;
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ OvpAvenir val$Ovp;

        1(OvpAvenir ovpAvenir) {
            this.val$Ovp = ovpAvenir;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (OvpSuspendre.this.ArreterProcess(this.val$Ovp.contractReference, 0).getStatusCode().equals("000")) {
                OvpSuspendre ovpSuspendre = OvpSuspendre.this;
                ovpSuspendre.ShowMessageTransaction(ovpSuspendre.uiManager.stateMachine, "Echéance suspendue", null);
            } else {
                OvpSuspendre ovpSuspendre2 = OvpSuspendre.this;
                ovpSuspendre2.ShowMessageTransaction(ovpSuspendre2.uiManager.stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
            }
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ OvpAvenir val$Ovp;

        2(OvpAvenir ovpAvenir) {
            this.val$Ovp = ovpAvenir;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (OvpSuspendre.this.ArreterProcess(this.val$Ovp.contractReference, 1).getStatusCode().equals("000")) {
                OvpSuspendre ovpSuspendre = OvpSuspendre.this;
                ovpSuspendre.ShowMessageTransaction(ovpSuspendre.uiManager.stateMachine, "Virement permanent arreté", null);
            } else {
                OvpSuspendre ovpSuspendre2 = OvpSuspendre.this;
                ovpSuspendre2.ShowMessageTransaction(ovpSuspendre2.uiManager.stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
            }
        }
    }

    private Container GetContainerAvenir() {
        OvpAvenir ovpAvenir = (OvpAvenir) this.service;
        Container container = new Container(new BoxLayout(2));
        Container container2 = new Container(new BoxLayout(2));
        try {
            Label label = new Label(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(ovpAvenir.nextExecutionDate)));
            label.setUIID("g_lblDashBoardValueBlue");
            Container container3 = new Container(new BorderLayout());
            Container container4 = new Container(new BoxLayout(2));
            Container container5 = new Container();
            Label label2 = new Label("En faveur de ");
            Label label3 = new Label(ovpAvenir.creditAccountTitle);
            label2.setUIID("ab_splblValue");
            label3.setUIID("lbllhSmall");
            container5.add(label2).add(label3);
            Label label4 = new Label(ovpAvenir.amount + " DHS");
            label4.setUIID("ab_splblValue");
            container4.add(container5).add(label4);
            Button button = new Button("Suspendre");
            button.setUIID("OrgLabel");
            button.addActionListener(new 3(ovpAvenir));
            container3.setUIID("acd_AccountOperationItemV222");
            container3.getAllStyles().setPaddingUnit(1);
            container3.getAllStyles().setPadding(1, 1, 2, 2);
            container3.add("Center", container4);
            container2.add(label);
            new Container(new BoxLayout(2));
            Component container6 = new Container(new BoxLayout(2));
            Component container7 = new Container(new BoxLayout(2));
            container6.setUIID("greyCntNew");
            container7.setUIID("greyCntNew");
            container6.getAllStyles().setMarginUnit(1);
            container6.getAllStyles().setMargin(1, 1, 0, 0);
            container2.add(container6);
            container2.add(container3);
            container2.add(container7);
            container.add(container2);
        } catch (Exception unused) {
        }
        return container;
    }

    class 3 implements ActionListener {
        final /* synthetic */ OvpAvenir val$Ovp;

        3(OvpAvenir ovpAvenir) {
            this.val$Ovp = ovpAvenir;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OvpSuspendre.this.SuspendreProcess(this.val$Ovp.contractReference);
            OvpSuspendre.this.uiManager.NavigateToPageById(46);
        }
    }

    public ServiceResponse SuspendreProcess(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("ContractNumber", str);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setIsDemo(0);
        serviceRequest.setServiceId(10701);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse ArreterProcess(String str, int i) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("ContractNumber", str);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        if (i == 0) {
            serviceRequest.setServiceId(10707);
        } else if (i == 1) {
            serviceRequest.setServiceId(10703);
        }
        serviceRequest.setIsDemo(0);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public void ShowMessageTransaction(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMessage");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(false);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(230);
            dialog.getDialogStyle().setBgColor(1118481);
            dialog.getStyle().setPadding(0, 0, 0, 0);
            dialog.getStyle().setMargin(0, 0, 0, 0);
            dialog.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog.revalidate();
            stateMachine.findSpanLabel(createContainer).setText(str);
            stateMachine.findLabel1(createContainer).setText(str2);
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 4(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        4(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            OvpSuspendre.this.uiManager.NavigateToPageById(46);
        }
    }
}
