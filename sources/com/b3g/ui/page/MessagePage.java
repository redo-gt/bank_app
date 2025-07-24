package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Messagerie;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MessagePage extends B3GPage {
    Container cc;
    ServiceResponse responseMesg;

    public MessagePage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        Messagerie messagerie = new Messagerie();
        try {
            this.responseMesg = messagerie.MessagerieConnectProcess();
            CihMBanking.sesPMTR.listemessgs = messagerie.FillMessageArrayDataFromServiceResponse(this.responseMesg);
            if (this.responseMesg.getStatusCode().equals("000") || this.responseMesg.getStatusCode().equals("001")) {
                this.thisContainer = new Container(new BorderLayout());
                this.uiManager.currentForm.setScrollableY(false);
                this.thisContainer.setScrollableY(false);
                Container container = new Container(new BorderLayout());
                Container container2 = new Container(new GridLayout(1, 1));
                container2.setScrollableY(true);
                container2.setScrollVisible(false);
                container2.add(this.uiManager.DrawListContainer("GloabalContainerV2", "Messages reçus", Boolean.TRUE, CihMBanking.sesPMTR.listemessgs, CihMBanking.sesPMTR.listemessgs.size(), 0, "Vous n'avez aucun message reçu", null, null, null, null));
                container.add((Object) 4, (Component) container2);
                container2.addPullToRefresh(new 1(messagerie, container2, container));
                Settings.setNightMode(this.thisContainer, 0);
                this.thisContainer.add((Object) 4, (Component) container);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, DataTools.FormatUnicode(this.responseMesg.getStatusLabel()), null);
            }
        } catch (Exception unused) {
            Settings.setNightMode(this.thisContainer, 0);
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, DataTools.FormatUnicode("Une erreur est survenue, Veuillez réessayer plus tard."), null);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements Runnable {
        final /* synthetic */ Container val$mainContainer;
        final /* synthetic */ Container val$mainContainer1;
        final /* synthetic */ Messagerie val$mess;

        1(Messagerie messagerie, Container container, Container container2) {
            this.val$mess = messagerie;
            this.val$mainContainer1 = container;
            this.val$mainContainer = container2;
        }

        public void run() {
            CihMBanking.sesPMTR.listemessgs = this.val$mess.MessagerieConnectSilentProcess();
            this.val$mainContainer1.removeAll();
            this.val$mainContainer1.add(MessagePage.this.uiManager.DrawListContainer("GloabalContainerV2", "Messages reçus", Boolean.TRUE, CihMBanking.sesPMTR.listemessgs, CihMBanking.sesPMTR.listemessgs.size(), 0, "Vous n'avez aucun message reçu", null, null, null, null));
            this.val$mainContainer.revalidate();
        }
    }
}
