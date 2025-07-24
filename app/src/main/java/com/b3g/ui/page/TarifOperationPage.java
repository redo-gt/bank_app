package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.tools.NativeCall;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Preferences;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.Base64;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TarifOperationPage extends B3GPage {
    private final UIBuilder uib;

    public TarifOperationPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        this.uib = new UIBuilder();
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("white_container");
        Container createContainer = this.uib.createContainer("/cihv3", "Tarifdesoperation");
        Container container = (Container) this.uib.findByName("MainCnt", createContainer);
        container.getAllStyles().setMargin(3, 3, 0, 0);
        container.getAllStyles().setPadding(3, 3, 0, 0);
        Button button = new Button();
        container.addComponent(drawMenuItem("Tarif des opérations", this.uiManager.ressource.getImage("insurance_attestion_solde.png"), false, button));
        button.addActionListener(new 1());
        container.add(separator());
        this.thisContainer.add(createContainer);
        this.thisContainer.forceRevalidate();
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
            ServiceResponse TarifPrecess = TarifOperationPage.TarifPrecess();
            if (TarifPrecess.getStatusCode().equals("000")) {
                byte[] decode = Base64.decode(TarifPrecess.getStatusLabel().getBytes(), TarifPrecess.getStatusLabel().getBytes().length);
                if (!Display.getInstance().getPlatformName().equals("ios")) {
                    ((NativeCall) NativeLookup.create(NativeCall.class)).createPdfFromByteArray(decode, "TarifOpérationCIH.pdf");
                    return;
                }
                String str = fileSystemStorage.getAppHomePath() + "TarifOpérationCIH.pdf";
                try {
                    OutputStream openOutputStream = fileSystemStorage.openOutputStream(str);
                    try {
                        openOutputStream.write(decode);
                        if (openOutputStream != null) {
                            openOutputStream.close();
                        }
                    } finally {
                    }
                } catch (IOException unused) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(TarifPrecess.getStatusLabel()), null);
                }
                Display.getInstance().execute(str);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(TarifPrecess.getStatusLabel()), null);
        }
    }

    private Container drawMenuItem(String str, Image image, boolean z, Button button) {
        Container createContainer = this.uib.createContainer(this.uiManager.ressource, "InsuranceDocsItem");
        ((ImageViewer) this.uib.findByName("ImageIcon", createContainer)).setImage(image);
        ((SpanLabel) this.uib.findByName("ItemNameLbl", createContainer)).setText(str);
        Container container = (Container) this.uib.findByName("RightCnt", createContainer);
        if (!z) {
            container.removeAll();
            container.addComponent(downloadBtn(button));
            createContainer.refreshTheme();
        }
        return createContainer;
    }

    private Container separator() {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("margin_1_1_1_1");
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("hm_v2_MenuItemSep");
        Container container3 = new Container(new BoxLayout(2));
        container3.setUIID("hm_v2_MenuItemSep");
        container2.add(container3);
        container.add(container2);
        return container;
    }

    private Container downloadBtn(Button button) {
        Container encloseRightMiddle = FlowLayout.encloseRightMiddle(new Component[0]);
        button.setText("Télécharger");
        button.setIcon(this.uiManager.ressource.getImage("insurance_download.png"));
        button.setGap(15);
        button.setUIID("insuranceGrayBtnMenuItem");
        encloseRightMiddle.add(button);
        return encloseRightMiddle;
    }

    public static ServiceResponse TarifPrecess() {
        ServiceRequest serviceRequest = new ServiceRequest();
        new ArrayList();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("index", DataTools.getLangueIndex(Preferences.get("CihMobileLang", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().langue)));
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(165);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }
}
