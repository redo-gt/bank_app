package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DemandeAssistancePage extends B3GPage {
    public B3GUiManager uiManager;
    UIBuilder uib = new UIBuilder();

    public DemandeAssistancePage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public DemandeAssistancePage() {
    }

    public Container GetPageContainer() {
        return new Container();
    }

    public ServiceResponse DeclareSinistreSaham() {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSION_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        for (int i = 0; i < CihMBanking.sesPMTR.ListRib.size(); i++) {
            hashtable.put("RIB" + i, CihMBanking.sesPMTR.ListRib.get(i));
        }
        hashtable.put("NBRRIB", Integer.valueOf(CihMBanking.sesPMTR.ListRib.size()));
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(169);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        ServiceResponse serviceResponse = (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
        if (serviceResponse.getStatusCode().equals("000")) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransactionForSinistre(this.uiManager.stateMachine, DataTools.FormatUnicode("Votre demande d'assistance est prise en charge. Vous receverez dans quelque minutes un appel du service assistance."), null);
        } else {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransactionForSinistre(this.uiManager.stateMachine, DataTools.FormatUnicode("ERROR"), null);
        }
        return serviceResponse;
    }

    public void ShowMessageTransactionForSinistre(StateMachine stateMachine, String str, String str2) {
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
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 1(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 1 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        1(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            DemandeAssistancePage.this.uiManager.NavigateToPageById(135);
        }
    }
}
