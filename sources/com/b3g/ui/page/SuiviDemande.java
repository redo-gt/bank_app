package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.FolderMissionSaham;
import com.b3g.tools.SyncTask;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.MapSaham;
import com.b3g.ui.page.cih.Module.SahamMapForm;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SuiviDemande extends B3GPage {
    public static int code_staut = 1;
    Container contAllSteps;
    UIBuilder uib = new UIBuilder();
    MapSaham lG = new MapSaham();
    TableLayout layout = new TableLayout(4, 1);
    HashMap MissionTitel = new HashMap(4);
    FolderMissionSaham folderMission = new FolderMissionSaham();
    int A = 0;
    boolean repeat = true;

    static /* synthetic */ HashMap access$000(SuiviDemande suiviDemande, ArrayList arrayList) {
        return suiviDemande.fillMissionsFromWS(arrayList);
    }

    static /* synthetic */ void access$100(SuiviDemande suiviDemande, B3GUiManager b3GUiManager, FolderMissionSaham folderMissionSaham, String str, String str2, boolean z, Container container, Container container2, int i) {
        suiviDemande.UpdateStep(b3GUiManager, folderMissionSaham, str, str2, z, container, container2, i);
    }

    public SuiviDemande(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new BoxLayout(2));
        this.contAllSteps = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("white_background_orange_top");
        this.contAllSteps.getAllStyles().setMargin(5, 2, 3, 1);
        int i = 0;
        CihMBanking.exitApplication = false;
        Label label = new Label("Suivi des demandes");
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        this.thisContainer.add(label);
        ServiceResponse folderMissions = this.folderMission.getFolderMissions(false);
        if (folderMissions.getStatusCode().equals("000")) {
            ArrayList FillMissionsrrayDataFromServiceResponse = this.folderMission.FillMissionsrrayDataFromServiceResponse(folderMissions);
            if (FillMissionsrrayDataFromServiceResponse.size() != 0) {
                FolderMissionSaham folderMissionSaham = new FolderMissionSaham();
                FolderMissionSaham folderMissionSaham2 = new FolderMissionSaham();
                FolderMissionSaham folderMissionSaham3 = new FolderMissionSaham();
                FolderMissionSaham folderMissionSaham4 = new FolderMissionSaham();
                folderMissionSaham.pMissionId = "DLSDKLSKDSLKDSLKDSLD";
                folderMissionSaham.pMissionStatut = "Intervention en cours";
                folderMissionSaham2.pMissionId = "DLSDKLSKDSLKDSLKDSLDsssss";
                folderMissionSaham2.pMissionStatut = "Acceptée";
                folderMissionSaham3.pMissionId = "DLSDKLSKDSLKDSLKDSLDsssss";
                folderMissionSaham3.pMissionStatut = "Fin d'exécution";
                folderMissionSaham4.pMissionId = "DLSDKLSKDSLKDSLKDSLDsssss";
                folderMissionSaham4.pMissionStatut = "Nouvelle Mission";
                this.MissionTitel = fillMissionsFromWS(FillMissionsrrayDataFromServiceResponse);
                drawAllStep(this.uiManager, this.contAllSteps);
                Set<Map.Entry> entrySet = this.MissionTitel.entrySet();
                Display.getInstance().scheduleBackgroundTask(new SuiviDemande$$ExternalSyntheticLambda0(this));
                Iterator it = entrySet.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    it.next();
                    i2++;
                }
                for (Map.Entry entry : entrySet) {
                    B3GUiManager b3GUiManager = this.uiManager;
                    FolderMissionSaham folderMissionSaham5 = (FolderMissionSaham) entry.getValue();
                    String obj = entry.getKey().toString();
                    Container container = this.contAllSteps;
                    UpdateStep(b3GUiManager, folderMissionSaham5, "", obj, false, container, (Container) container.getComponentAt(i), i2);
                    i++;
                }
                this.thisContainer.add(this.contAllSteps);
            } else {
                this.thisContainer = new Container(new BoxLayout(2));
                this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            }
        } else {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            new UITimer(new SuiviDemande$1$$ExternalSyntheticLambda0(this)).schedule(10000, SuiviDemande.this.repeat, SuiviDemande.this.uiManager.currentForm);
        }

        /* synthetic */ void lambda$run$0$com-b3g-ui-page-SuiviDemande$1() {
            if (SuiviDemande.this.A != 3 && SuiviDemande.this.A != 4) {
                System.err.println("GetNextStatut " + SuiviDemande.this.A);
                new GetNextStatut(SuiviDemande.this, null).execute(new Container[0]);
            } else {
                int i = SuiviDemande.this.A;
            }
        }
    }

    /* synthetic */ void lambda$GetPageContainer$0$com-b3g-ui-page-SuiviDemande() {
        Display.getInstance().callSeriallyAndWait(new 1());
    }

    private void drawAllStep(B3GUiManager b3GUiManager, Container container) {
        for (int i = 1; i <= 4; i++) {
            drawStep(b3GUiManager, "", "", "0" + i, true, container);
        }
    }

    private void drawStep(B3GUiManager b3GUiManager, String str, String str2, String str3, boolean z, Container container) {
        Container createContainer = this.uib.createContainer("/cihv3", "SuiviDemande");
        Label label = (Label) this.uib.findByName("iconLbl", createContainer);
        Container container2 = (Container) this.uib.findByName("cntLine", createContainer);
        SpanLabel spanLabel = (SpanLabel) this.uib.findByName("SpanLabel1", createContainer);
        SpanLabel spanLabel2 = (SpanLabel) this.uib.findByName("SpanLabel2", createContainer);
        Container container3 = (Container) this.uib.findByName("cntmap", createContainer);
        revalidateStep(b3GUiManager, container3, createContainer, container2, spanLabel, spanLabel2, label, null, str2, str3, z, 1);
        container.add(createContainer);
    }

    private void UpdateStep(B3GUiManager b3GUiManager, FolderMissionSaham folderMissionSaham, String str, String str2, boolean z, Container container, Container container2, int i) {
        Container container3 = (Container) container2.getComponentAt(1);
        Container container4 = (Container) container2.getComponentAt(0);
        Label label = (Label) ((Container) container4.getComponentAt(0)).getComponentAt(0);
        Container container5 = (Container) container4.getComponentAt(1);
        Container container6 = (Container) container3.getComponentAt(0);
        revalidateStep(b3GUiManager, (Container) container3.getComponentAt(1), container5, container5, (SpanLabel) container6.getComponentAt(0), (SpanLabel) container6.getComponentAt(1), label, folderMissionSaham, str, str2, z, i);
        container.revalidate();
    }

    private void revalidateStep(B3GUiManager b3GUiManager, Container container, Container container2, Container container3, SpanLabel spanLabel, SpanLabel spanLabel2, Label label, FolderMissionSaham folderMissionSaham, String str, String str2, boolean z, int i) {
        int displayHeight = (Display.getInstance().getDisplayHeight() - 100) / 5;
        int intValue = Integer.valueOf(str2).intValue();
        System.err.println("STEP" + intValue);
        System.err.println("size" + i);
        Container container4 = new Container(new LayeredLayout());
        if (z) {
            spanLabel.setText("");
            spanLabel2.setText("");
        } else {
            spanLabel.setText(folderMissionSaham.pMissionStatut);
            spanLabel2.setText(folderMissionSaham.pMissionSubStatut);
        }
        container.setHidden(z);
        if (z) {
            label.setIcon(b3GUiManager.ressource.getImage("SOSInactif.png"));
            container3.getAllStyles().setBgImage(b3GUiManager.ressource.getImage("1blackgreyop08.png"));
        } else if (intValue == i && intValue != 4) {
            label.setIcon(b3GUiManager.ressource.getImage("SOSEncours.png"));
            container3.getAllStyles().setBgImage(b3GUiManager.ressource.getImage("1blackgreyop08.png"));
        } else {
            label.setIcon(b3GUiManager.ressource.getImage("SOSActif.png"));
            container3.getAllStyles().setBgImage(b3GUiManager.ressource.getImage("1orange.png"));
        }
        container2.setName(str2);
        System.err.println("HEIGHT" + displayHeight);
        if (str2.equals("03")) {
            container2.setPreferredH(displayHeight + 300);
            int i2 = displayHeight + 200;
            container4.setPreferredH(i2);
            container.setPreferredH(i2);
        } else {
            container2.setPreferredH(displayHeight - 100);
        }
        if (str2.equals("03") && !z) {
            Container container5 = new Container(new BorderLayout());
            Container container6 = new Container(new BorderLayout());
            Button button = new Button("");
            button.setUIID("Container");
            button.setIcon(b3GUiManager.ressource.getImage("SOSExpand.png"));
            container6.add("North", button);
            container6.getAllStyles().setMargin(1, 0, 0, 1);
            container5.add("East", container6);
            container6.setLeadComponent(button);
            FolderMissionSaham folderMissionSaham2 = new FolderMissionSaham();
            ServiceResponse missionTrack = folderMissionSaham2.getMissionTrack(folderMissionSaham);
            if (missionTrack.getStatusCode().equals("000")) {
                container4.add(this.lG.drawMapp(this.uiManager, missionTrack.getParamsOut() != null ? folderMissionSaham2.FillTrackingFromServiceResponse(missionTrack.getParamsOut(), folderMissionSaham) : folderMissionSaham));
                container4.add(container5);
                button.addActionListener(new 2(b3GUiManager, folderMissionSaham));
                container.add(container4);
                return;
            }
            return;
        }
        if (str2.equals("04")) {
            container3.getAllStyles().setBgImage(b3GUiManager.ressource.getImage("1white.png"));
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ FolderMissionSaham val$pMission;
        final /* synthetic */ B3GUiManager val$uimanager;

        2(B3GUiManager b3GUiManager, FolderMissionSaham folderMissionSaham) {
            this.val$uimanager = b3GUiManager;
            this.val$pMission = folderMissionSaham;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            SuiviDemande.this.uiManager.NavigateToPage(new SahamMapForm(this.val$uimanager, null, 159, this.val$pMission));
        }
    }

    private class GetNextStatut extends SyncTask {
        private GetNextStatut() {
        }

        /* synthetic */ GetNextStatut(SuiviDemande suiviDemande, 1 r2) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public HashMap doInBackground(Container... containerArr) {
            ArrayList FillMissionsrrayDataFromServiceResponse = SuiviDemande.this.folderMission.FillMissionsrrayDataFromServiceResponse(SuiviDemande.this.folderMission.getFolderMissions(true));
            new FolderMissionSaham();
            new FolderMissionSaham();
            new FolderMissionSaham();
            return SuiviDemande.access$000(SuiviDemande.this, FillMissionsrrayDataFromServiceResponse);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onPostExecute(HashMap hashMap) {
            Set<Map.Entry> entrySet = hashMap.entrySet();
            Iterator it = entrySet.iterator();
            int i = 0;
            int i2 = 0;
            while (it.hasNext()) {
                it.next();
                i2++;
            }
            for (Map.Entry entry : entrySet) {
                SuiviDemande suiviDemande = SuiviDemande.this;
                SuiviDemande.access$100(suiviDemande, suiviDemande.uiManager, (FolderMissionSaham) entry.getValue(), "", entry.getKey().toString(), false, SuiviDemande.this.contAllSteps, (Container) SuiviDemande.this.contAllSteps.getComponentAt(i), i2);
                i++;
            }
        }
    }

    private HashMap fillMissionsFromWS(ArrayList arrayList) {
        FolderMissionSaham folderMissionSaham;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            folderMissionSaham = (FolderMissionSaham) it.next();
            String str = folderMissionSaham.pMissionStatut;
            str.hashCode();
            switch (str) {
                case "Intervention en cours":
                    FolderMissionSaham folderMissionSaham2 = new FolderMissionSaham();
                    folderMissionSaham2.pMissionId = folderMissionSaham.pMissionId;
                    folderMissionSaham2.pFolderRef = folderMissionSaham.pFolderRef;
                    folderMissionSaham2.pMissionStatut = "Votre demande est confirmée";
                    this.MissionTitel.put("01", folderMissionSaham2);
                    folderMissionSaham.pMissionStatut = "Votre demande est en cours de traitement";
                    this.MissionTitel.put("02", folderMissionSaham);
                    break;
                case "Acceptée":
                    FolderMissionSaham folderMissionSaham3 = new FolderMissionSaham();
                    FolderMissionSaham folderMissionSaham4 = new FolderMissionSaham();
                    folderMissionSaham3.pMissionId = folderMissionSaham.pMissionId;
                    folderMissionSaham4.pMissionId = folderMissionSaham.pMissionId;
                    folderMissionSaham3.pFolderRef = folderMissionSaham.pFolderRef;
                    folderMissionSaham4.pFolderRef = folderMissionSaham.pFolderRef;
                    folderMissionSaham3.pMissionStatut = "Votre demande est confirmée";
                    this.MissionTitel.put("01", folderMissionSaham3);
                    folderMissionSaham4.pMissionStatut = "Votre demande est en cours de traitement";
                    this.MissionTitel.put("02", folderMissionSaham4);
                    folderMissionSaham.pMissionStatut = "Nous sommes en route\u200b";
                    folderMissionSaham.pMissionSubStatut = "Suivez en temps réel le trajet du remorqueur\u200b";
                    this.MissionTitel.put("03", folderMissionSaham);
                    this.A = 3;
                    break;
                case "Nouvelle Mission":
                case "Exécution en cours":
                    folderMissionSaham.pMissionStatut = "Votre demande est confirmée";
                    this.MissionTitel.put("01", folderMissionSaham);
                    break;
                case "Fin d'exécution":
                    FolderMissionSaham folderMissionSaham5 = new FolderMissionSaham();
                    FolderMissionSaham folderMissionSaham6 = new FolderMissionSaham();
                    FolderMissionSaham folderMissionSaham7 = new FolderMissionSaham();
                    folderMissionSaham5.pMissionId = folderMissionSaham.pMissionId;
                    folderMissionSaham6.pMissionId = folderMissionSaham.pMissionId;
                    folderMissionSaham7.pMissionId = folderMissionSaham.pMissionId;
                    folderMissionSaham5.pFolderRef = folderMissionSaham.pFolderRef;
                    folderMissionSaham6.pFolderRef = folderMissionSaham.pFolderRef;
                    folderMissionSaham7.pFolderRef = folderMissionSaham.pFolderRef;
                    folderMissionSaham5.pMissionStatut = "Votre demande est confirmée";
                    this.MissionTitel.put("01", folderMissionSaham5);
                    folderMissionSaham6.pMissionStatut = "Votre demande est en cours de traitement";
                    this.MissionTitel.put("02", folderMissionSaham6);
                    folderMissionSaham7.pMissionStatut = "Nous sommes en route\u200b";
                    folderMissionSaham7.pMissionSubStatut = "Suivez en temps réel le trajet du remorqueur\u200b";
                    this.MissionTitel.put("03", folderMissionSaham7);
                    folderMissionSaham.pMissionStatut = "Nous sommes sur place";
                    folderMissionSaham.pMissionSubStatut = "Le remorqueur est arrivé sur le lieu indiqué";
                    this.MissionTitel.put("04", folderMissionSaham);
                    this.A = 4;
                    break;
            }
        }
        return this.MissionTitel;
    }
}
