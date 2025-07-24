package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.OvpService;
import com.b3g.services.ServiceManager;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OvpAction extends B3GPage {
    OvpService Ovp;

    public OvpAction(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:3:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.codename1.ui.Container GetPageContainer() {
        /*
            Method dump skipped, instructions count: 444
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.OvpAction.GetPageContainer():com.codename1.ui.Container");
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OvpAction ovpAction = OvpAction.this;
            if (ovpAction.SuspendreProcess(ovpAction.Ovp.refDossier).getStatusCode().equals("000")) {
                OvpAction ovpAction2 = OvpAction.this;
                ovpAction2.ShowMessageTransaction(ovpAction2.uiManager.stateMachine, "Opération effectuée avec succès", null);
            }
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OvpAction ovpAction = OvpAction.this;
            if (ovpAction.ArreterProcess(ovpAction.Ovp.refDossier).getStatusCode().equals("000")) {
                OvpAction ovpAction2 = OvpAction.this;
                ovpAction2.ShowMessageTransaction(ovpAction2.uiManager.stateMachine, "Opération effectuée avec succès", null);
            } else {
                OvpAction ovpAction3 = OvpAction.this;
                ovpAction3.ShowMessageTransaction(ovpAction3.uiManager.stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
            }
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            OvpAction ovpAction = OvpAction.this;
            if (ovpAction.ReprendreProcess(ovpAction.Ovp.refDossier).getStatusCode().equals("000")) {
                OvpAction ovpAction2 = OvpAction.this;
                ovpAction2.ShowMessageTransaction(ovpAction2.uiManager.stateMachine, "Virement permanent repris", null);
            } else {
                OvpAction ovpAction3 = OvpAction.this;
                ovpAction3.ShowMessageTransaction(ovpAction3.uiManager.stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
            }
        }
    }

    private Container GetContainerAvenir() {
        Container container = new Container(new BoxLayout(2));
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("margin_1_1_0_0");
        Label label = new Label("Compte à débiter");
        label.setUIID("g_lblDashBoardValue");
        Label label2 = new Label(this.Ovp.issuarAccountNumber);
        label2.setUIID("lbl_regular_bold");
        container2.add(label).add(label2);
        Container container3 = new Container(new BoxLayout(2));
        container3.setUIID("margin_1_1_0_0");
        Label label3 = new Label("Compte bénéficiaire");
        label3.setUIID("g_lblDashBoardValue");
        Label label4 = new Label(this.Ovp.nomBeneficiaire);
        label4.setUIID("lbl_regular_bold");
        Label label5 = new Label(this.Ovp.beneficiaryAccountNumber);
        label5.setUIID("lbl_regular_bold");
        container3.add(label3).add(label4).add(label5);
        container.add(container2).add(container3);
        container.setUIID("margin_2_2_0_0");
        return container;
    }

    public ServiceResponse SuspendreProcess(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("ContractNumber", str);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(10701);
        serviceRequest.setIsDemo(0);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse ArreterProcess(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("ContractNumber", str);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(10703);
        serviceRequest.setIsDemo(0);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse ReprendreProcess(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("ContractNumber", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(10702);
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
            OvpAction.this.uiManager.NavigateToPageById(46);
        }
    }
}
