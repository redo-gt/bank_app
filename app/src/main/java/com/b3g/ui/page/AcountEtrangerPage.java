package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.AccountsAgr;
import com.b3g.services.SavingAcountAgregation;
import com.b3g.services.Webview;
import com.b3g.tools.NativeCall;
import com.b3g.ui.B3GUiManager;
import com.codename1.components.InteractionDialog;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AcountEtrangerPage extends B3GPage {
    ServiceResponse response;
    UIBuilder uib = new UIBuilder();

    public AcountEtrangerPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.response = (ServiceResponse) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new GridLayout(1, 1));
        SavingAcountAgregation savingAcountAgregation = new SavingAcountAgregation();
        if (this.response == null) {
            this.response = savingAcountAgregation.getDetailsResponse();
        }
        ArrayList AgregationDetails = savingAcountAgregation.AgregationDetails(this.response);
        if (AgregationDetails != null && !AgregationDetails.isEmpty() && AgregationDetails.size() != 0) {
            this.thisContainer = new Container(new GridLayout(1, 1));
            this.thisContainer.addComponent(DrawMdmAccountDetails(permutation(AgregationDetails)));
            AccountsAgr.parent = this.thisContainer;
        } else {
            this.thisContainer = new Container(new BorderLayout());
            this.thisContainer.setLayout(new BorderLayout());
            this.thisContainer.addComponent("Center", this.uiManager.GetCntMessage("Vous n'avez aucun Compte"));
        }
        this.thisContainer.setUIID("mn_cntMenuItemV2");
        return this.thisContainer;
    }

    private Container DrawMdmAccountDetails(ArrayList arrayList) {
        Container createContainer = this.uib.createContainer("/cihv3", "AccountEtrangerSaving");
        Container container = (Container) this.uib.findByName("MDMDetailCntGb", createContainer);
        Container container2 = (Container) this.uib.findByName("cntDetailsGlb", createContainer);
        Button button = (Button) this.uib.findByName("BtnRen", createContainer);
        Button button2 = (Button) this.uib.findByName("BtnTransAM", createContainer);
        Button button3 = (Button) this.uib.findByName("BtnAjouteCmp", createContainer);
        button2.addActionListener(new 1());
        button3.addActionListener(new 2());
        container2.setScrollableY(true);
        container.setScrollableY(true);
        container.setScrollVisible(false);
        button.addActionListener(new 3());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            container.add(((AccountsAgr) it.next()).DrawItem(null, null, this.PageId, null, null, null));
        }
        return createContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(150);
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ArrayList FillAjouterNVArrayDataFromServiceResponse = new Webview().FillAjouterNVArrayDataFromServiceResponse(Webview.WebViewNVProcess());
            NativeCall nativeCall = (NativeCall) NativeLookup.create(NativeCall.class);
            if (nativeCall.isSupported()) {
                nativeCall.callWebView(((Webview) FillAjouterNVArrayDataFromServiceResponse.get(0)).webViewURL, "CREATE");
            }
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container createContainer = AcountEtrangerPage.this.uib.createContainer("/cihv3", "3DAgregation");
            InteractionDialog interactionDialog = new InteractionDialog("");
            interactionDialog.setLayout(new BorderLayout());
            interactionDialog.add("Center", createContainer);
            Button button = new Button("");
            button.addActionListener(new AcountEtrangerPage$3$$ExternalSyntheticLambda0(interactionDialog));
            interactionDialog.addComponent("South", button);
            interactionDialog.setDisposeWhenPointerOutOfBounds(true);
            interactionDialog.getAllStyles().setBgTransparency(0);
            int displayHeight = Display.getInstance().getDisplayHeight();
            interactionDialog.show(displayHeight - ((displayHeight / 2) - ((displayHeight - 60) / 5)), 5, 20, 45);
        }

        static /* synthetic */ void lambda$actionPerformed$0(InteractionDialog interactionDialog, ActionEvent actionEvent) {
            interactionDialog.dispose();
        }
    }

    private ArrayList permutation(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            AccountsAgr accountsAgr = (AccountsAgr) it.next();
            if (accountsAgr.status.equals("ACTIF")) {
                arrayList2.add(accountsAgr);
            } else {
                arrayList3.add(accountsAgr);
            }
        }
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            arrayList2.add((AccountsAgr) it2.next());
        }
        return arrayList2;
    }
}
