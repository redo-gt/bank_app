package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.MTCCreancier;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MTCCreancePage extends B3GPage {
    public MTCCreancePage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            new Container(new BoxLayout(2)).setUIID("ad_CntAccountDetailsMain");
            this.thisContainer.addComponent(GetMTCCreanceContainer());
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public Container GetMTCCreanceContainer() {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "MTCCreanceForm");
        MTCCreancier mTCCreancier = (MTCCreancier) this.service;
        this.uiManager.stateMachine.findLblCreancierNameTypeValueV2(createContainer).setText(mTCCreancier.NomCreancier);
        this.uiManager.stateMachine.findLblCreancierDescriptionValueV2(createContainer).setText(mTCCreancier.DescriptionCreancier);
        this.uiManager.stateMachine.findBtnCreancierImage(createContainer).setIcon(URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(this.uiManager.stateMachine.findBtnCreancierImage(createContainer).getIcon().getWidth(), this.uiManager.stateMachine.findBtnCreancierImage(createContainer).getIcon().getWidth(), 12569042), false), "Normal_https://www.cihnet.co.ma" + mTCCreancier.LogoPath, "https://www.cihnet.co.ma" + mTCCreancier.LogoPath, URLImage.RESIZE_SCALE));
        ArrayList arrayList = mTCCreancier.MTCCreanceList;
        this.uiManager.stateMachine.findCntCreanceList(createContainer).addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Choisissez un service", Boolean.TRUE, arrayList, arrayList.size(), 900061, "Aucune Créance n'est disponible pour le moment", null, null, null, null));
        createContainer.revalidate();
        Settings.setNightMode(this.thisContainer, 0);
        return createContainer;
    }
}
