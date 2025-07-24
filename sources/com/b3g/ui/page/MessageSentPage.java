package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.MessageContent;
import com.b3g.services.Messagerie;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MessageSentPage extends B3GPage {
    ServiceResponse responseMesg;

    public MessageSentPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        Messagerie messagerie = new Messagerie();
        ArrayList arrayList = new ArrayList();
        Messagerie messagerie2 = new Messagerie();
        Messagerie messagerie3 = new Messagerie();
        new Messagerie();
        MessageContent messageContent = new MessageContent();
        messageContent.message = "Text Message 1";
        messageContent.title = "Objet 1";
        MessageContent messageContent2 = new MessageContent();
        messageContent2.message = "Text Message 2";
        messageContent2.title = "Objet 2";
        Messagerie messagerie4 = new Messagerie();
        try {
            this.responseMesg = messagerie.MessagerieSentProcess();
            CihMBanking.sesPMTR.listemessgsSent = messagerie4.FillMessageArrayDataFromServiceResponse(this.responseMesg);
            if (this.responseMesg.getStatusCode().equals("000") || this.responseMesg.getStatusCode().equals("001")) {
                if (CihMBanking.sesPMTR.listemessgsSent.size() > 0) {
                    for (int i = 0; i < CihMBanking.sesPMTR.listemessgsSent.size(); i++) {
                        ((Messagerie) CihMBanking.sesPMTR.listemessgsSent.get(i)).seen = "true";
                    }
                }
                messagerie2.date = "2018202020202020";
                messagerie3.date = "2017111111111111111";
                messagerie2.MessageCont = messageContent;
                messagerie3.MessageCont = messageContent2;
                arrayList.add(messagerie2);
                arrayList.add(messagerie3);
                this.thisContainer = this.uiManager.DrawListContainer("GloabalContainerV2", "Messages envoyés ", Boolean.TRUE, CihMBanking.sesPMTR.listemessgsSent, CihMBanking.sesPMTR.listemessgsSent.size(), 0, "Vous n'avez aucun message envoyé", null, null, null, null);
                this.thisContainer.forceRevalidate();
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, DataTools.FormatUnicode(this.responseMesg.getStatusLabel()), null);
            }
        } catch (Exception unused) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, DataTools.FormatUnicode("Une erreur est survenue, Veuillez réessayer plus tard."), null);
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }
}
