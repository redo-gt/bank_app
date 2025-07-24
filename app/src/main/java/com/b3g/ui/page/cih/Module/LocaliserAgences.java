package com.b3g.ui.page.cih.Module;

import com.b3g.cih.online.SessionParameter;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.AgencyMapService;
import com.b3g.services.GabMapService;
import com.b3g.services.LocaliserAgencyService;
import com.b3g.services.LocaliserGabService;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.tools.MapsNativeCall;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3GViewPager;
import com.b3g.ui.TabTitleItemModeleViewPager;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.googlemaps.MapContainer;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.system.NativeLookup;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.regex.RE;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class LocaliserAgences {
    AgencyMapService agency;
    Container autourDeMoiCnt;
    Container c1;
    MapContainer cnt;
    Dialog d;
    Container itemsListCnt;
    ArrayList list;
    ArrayList listGab;
    Hashtable response;
    Button slideDownBtn;
    ArrayList villes;
    ArrayList SrvList = new ArrayList();
    boolean isOpenned = true;
    boolean searchForAgencies = true;
    ArrayList agenceList = new ArrayList();
    ArrayList gabList = new ArrayList();
    boolean Bigup = true;
    int autourDeMoiHeight = 0;
    int autourTopCntHeight = 0;
    int autourItemCntHeight = 0;
    boolean up = true;
    int currentAgencyIndex = 0;
    boolean firstLunch = true;
    boolean isC1Created = true;

    static /* synthetic */ void access$000(LocaliserAgences localiserAgences, MapContainer mapContainer, B3GUiManager b3GUiManager, ArrayList arrayList, ArrayList arrayList2, int i, boolean z) {
        localiserAgences.loadAllMarkers(mapContainer, b3GUiManager, arrayList, arrayList2, i, z);
    }

    static /* synthetic */ ArrayList access$100(LocaliserAgences localiserAgences, String str, ArrayList arrayList) {
        return localiserAgences.searchCities(str, arrayList);
    }

    static /* synthetic */ void access$200(LocaliserAgences localiserAgences, B3GUiManager b3GUiManager) {
        localiserAgences.findmeAction(b3GUiManager);
    }

    static /* synthetic */ void access$300(LocaliserAgences localiserAgences, B3GUiManager b3GUiManager) {
        localiserAgences.autourDeMoiAction(b3GUiManager);
    }

    public Container drawMap(B3GUiManager b3GUiManager) {
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "LocaliserGab");
        Container container = (Container) uIBuilder.findByName("mapCnt", createContainer);
        Container container2 = (Container) uIBuilder.findByName("villeCnt", createContainer);
        Container container3 = (Container) uIBuilder.findByName("agenceCnt", createContainer);
        Container container4 = (Container) uIBuilder.findByName("gabCnt", createContainer);
        Button button = (Button) uIBuilder.findByName("searchBtn", createContainer);
        Button button2 = (Button) uIBuilder.findByName("autourDeMoi", createContainer);
        Button button3 = (Button) uIBuilder.findByName("findMeBtn", createContainer);
        Button button4 = (Button) uIBuilder.findByName("BackButton", createContainer);
        this.autourDeMoiCnt = (Container) uIBuilder.findByName("autourDeMoiCnt", createContainer);
        Button button5 = (Button) uIBuilder.findByName("toutesAgencesBtn", createContainer);
        Button button6 = (Button) uIBuilder.findByName("tousGabsBtn", createContainer);
        this.cnt = new MapContainer();
        if (this.agenceList.size() == 0) {
            this.agenceList = AgencyMapServiceProcess("", "", "");
        }
        if (Display.getInstance().getLocationManager().isGPSDetectionSupported() && !Display.getInstance().getLocationManager().isGPSEnabled()) {
            b3GUiManager.ShowMessageTransaction(b3GUiManager.stateMachine, DataTools.FormatUnicode("Veuillez activer le service de localisation GPS"), null);
        }
        loadAllMarkers(this.cnt, b3GUiManager, this.agenceList, null, 5, false);
        container3.setUIID("grisTransparentBg");
        container4.setUIID("white_container");
        createContainer.revalidate();
        button5.addActionListener(new 1(container3, container4, createContainer, b3GUiManager));
        button6.addActionListener(new 2(container3, container4, createContainer, b3GUiManager));
        this.villes = getCitiesProcess();
        DefaultListModel defaultListModel = new DefaultListModel();
        3 r1 = new 3(defaultListModel, defaultListModel);
        r1.setMinimumElementsShownInPopup(2);
        r1.setHint("Chercher une ville");
        r1.setUIID("Container");
        r1.setAlignment(1);
        container2.add(r1);
        button4.addActionListener(new 4(b3GUiManager));
        button3.addActionListener(new 5(b3GUiManager));
        button.addActionListener(new 6(r1, b3GUiManager));
        button2.addActionListener(new 7(b3GUiManager));
        container.add(this.cnt);
        createContainer.revalidate();
        return createContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$LocaliserGab;
        final /* synthetic */ Container val$agenceCnt;
        final /* synthetic */ Container val$gabCnt;
        final /* synthetic */ B3GUiManager val$uiManager;

        1(Container container, Container container2, Container container3, B3GUiManager b3GUiManager) {
            this.val$agenceCnt = container;
            this.val$gabCnt = container2;
            this.val$LocaliserGab = container3;
            this.val$uiManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LocaliserAgences.this.searchForAgencies = true;
            this.val$agenceCnt.setUIID("grisTransparentBg");
            this.val$gabCnt.setUIID("white_container");
            this.val$LocaliserGab.revalidate();
            if (LocaliserAgences.this.agenceList.size() == 0) {
                LocaliserAgences localiserAgences = LocaliserAgences.this;
                localiserAgences.agenceList = localiserAgences.AgencyMapServiceProcess("", "", "");
            }
            LocaliserAgences localiserAgences2 = LocaliserAgences.this;
            LocaliserAgences.access$000(localiserAgences2, localiserAgences2.cnt, this.val$uiManager, LocaliserAgences.this.agenceList, null, 5, false);
            LocaliserAgences.this.autourDeMoiCnt.animateLayout(10);
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$LocaliserGab;
        final /* synthetic */ Container val$agenceCnt;
        final /* synthetic */ Container val$gabCnt;
        final /* synthetic */ B3GUiManager val$uiManager;

        2(Container container, Container container2, Container container3, B3GUiManager b3GUiManager) {
            this.val$agenceCnt = container;
            this.val$gabCnt = container2;
            this.val$LocaliserGab = container3;
            this.val$uiManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LocaliserAgences.this.searchForAgencies = false;
            this.val$agenceCnt.setUIID("white_container");
            this.val$gabCnt.setUIID("grisTransparentBg");
            this.val$LocaliserGab.revalidate();
            if (LocaliserAgences.this.gabList.size() == 0) {
                LocaliserAgences localiserAgences = LocaliserAgences.this;
                localiserAgences.gabList = localiserAgences.GabMapServiceProcess("", "", "");
            }
            LocaliserAgences localiserAgences2 = LocaliserAgences.this;
            LocaliserAgences.access$000(localiserAgences2, localiserAgences2.cnt, this.val$uiManager, null, LocaliserAgences.this.gabList, 5, false);
            LocaliserAgences.this.autourDeMoiCnt.animateLayout(10);
        }
    }

    class 3 extends AutoCompleteTextField {
        final /* synthetic */ DefaultListModel val$options;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        3(ListModel listModel, DefaultListModel defaultListModel) {
            super(listModel);
            this.val$options = defaultListModel;
        }

        protected boolean filter(String str) {
            if (str.length() == 0) {
                return false;
            }
            LocaliserAgences localiserAgences = LocaliserAgences.this;
            ArrayList access$100 = LocaliserAgences.access$100(localiserAgences, str, localiserAgences.villes);
            if (access$100 == null || access$100.isEmpty()) {
                return false;
            }
            this.val$options.removeAll();
            Iterator it = access$100.iterator();
            while (it.hasNext()) {
                this.val$options.addItem((String) it.next());
            }
            return true;
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ B3GUiManager val$uiManager;

        4(B3GUiManager b3GUiManager) {
            this.val$uiManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$uiManager.stateMachine.showForm("LoginV2New", (Command) null);
        }
    }

    class 5 implements ActionListener {
        final /* synthetic */ B3GUiManager val$uiManager;

        5(B3GUiManager b3GUiManager) {
            this.val$uiManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getLocationManager().isGPSDetectionSupported()) {
                if (Display.getInstance().getLocationManager().isGPSEnabled()) {
                    LocaliserAgences.access$200(LocaliserAgences.this, this.val$uiManager);
                    return;
                } else {
                    B3GUiManager b3GUiManager = this.val$uiManager;
                    b3GUiManager.ShowMessageTransaction(b3GUiManager.stateMachine, DataTools.FormatUnicode("Veuillez activer le service de localisation GPS"), null);
                    return;
                }
            }
            LocaliserAgences.access$200(LocaliserAgences.this, this.val$uiManager);
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ AutoCompleteTextField val$ac;
        final /* synthetic */ B3GUiManager val$uiManager;

        6(AutoCompleteTextField autoCompleteTextField, B3GUiManager b3GUiManager) {
            this.val$ac = autoCompleteTextField;
            this.val$uiManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String text = this.val$ac.getText();
            if (LocaliserAgences.this.searchForAgencies) {
                ArrayList AgencyMapServiceProcess = LocaliserAgences.this.AgencyMapServiceProcess(text, "", "");
                LocaliserAgences localiserAgences = LocaliserAgences.this;
                LocaliserAgences.access$000(localiserAgences, localiserAgences.cnt, this.val$uiManager, AgencyMapServiceProcess, null, 11, true);
            } else {
                ArrayList GabMapServiceProcess = LocaliserAgences.this.GabMapServiceProcess(text, "", "");
                LocaliserAgences localiserAgences2 = LocaliserAgences.this;
                LocaliserAgences.access$000(localiserAgences2, localiserAgences2.cnt, this.val$uiManager, null, GabMapServiceProcess, 11, true);
            }
            if (LocaliserAgences.this.c1 != null) {
                LocaliserAgences.this.Bigup = true;
                LocaliserAgences.this.c1.setPreferredH(0);
                LocaliserAgences.this.autourDeMoiCnt.animateLayout(500);
            }
        }
    }

    class 7 implements ActionListener {
        final /* synthetic */ B3GUiManager val$uiManager;

        7(B3GUiManager b3GUiManager) {
            this.val$uiManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LocaliserAgences.access$200(LocaliserAgences.this, this.val$uiManager);
            if (Display.getInstance().getLocationManager().isGPSDetectionSupported()) {
                if (Display.getInstance().getLocationManager().isGPSEnabled()) {
                    LocaliserAgences.access$300(LocaliserAgences.this, this.val$uiManager);
                    return;
                } else {
                    B3GUiManager b3GUiManager = this.val$uiManager;
                    b3GUiManager.ShowMessageTransaction(b3GUiManager.stateMachine, DataTools.FormatUnicode("Veuillez activer le service de localisation GPS"), null);
                    return;
                }
            }
            LocaliserAgences.access$300(LocaliserAgences.this, this.val$uiManager);
        }
    }

    private void createAgencyMarker(MapContainer mapContainer, AgencyMapService agencyMapService, String str) {
        try {
            if (Display.getInstance().getPlatformName().equals("ios") || Display.getInstance().getDisplayHeight() < 1000) {
                mapContainer.addMarker(EncodedImage.create("/agence.png"), new Coord(agencyMapService.latitude, agencyMapService.longitude), str, null, new LocaliserAgences$$ExternalSyntheticLambda1(this, agencyMapService));
            } else {
                mapContainer.addMarker(EncodedImage.create("/agence.png"), new Coord(agencyMapService.latitude, agencyMapService.longitude), str, null, new LocaliserAgences$$ExternalSyntheticLambda2(this, agencyMapService));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* synthetic */ void lambda$createAgencyMarker$0$com-b3g-ui-page-cih-Module-LocaliserAgences(AgencyMapService agencyMapService, ActionEvent actionEvent) {
        showAgencyDetail(agencyMapService, null);
    }

    /* synthetic */ void lambda$createAgencyMarker$1$com-b3g-ui-page-cih-Module-LocaliserAgences(AgencyMapService agencyMapService, ActionEvent actionEvent) {
        showAgencyDetail(agencyMapService, null);
    }

    private void createGabMarker(MapContainer mapContainer, GabMapService gabMapService, B3GUiManager b3GUiManager, String str) {
        try {
            if (!"EN SERVICE".equals(gabMapService.StatusAtm.toUpperCase())) {
                if (Display.getInstance().getPlatformName().equals("ios") || Display.getInstance().getDisplayHeight() < 1000) {
                    mapContainer.addMarker(EncodedImage.create("/gabpanne_marker_84.png"), new Coord(gabMapService.Latitude, gabMapService.Longitude), str, null, new LocaliserAgences$$ExternalSyntheticLambda3(this, gabMapService));
                } else {
                    mapContainer.addMarker(EncodedImage.create("/gabpanne_marker.png"), new Coord(gabMapService.Latitude, gabMapService.Longitude), str, null, new LocaliserAgences$$ExternalSyntheticLambda4(this, gabMapService));
                }
            } else if (Display.getInstance().getPlatformName().equals("ios") || Display.getInstance().getDisplayHeight() < 1000) {
                mapContainer.addMarker(EncodedImage.create("/gab.png"), new Coord(gabMapService.Latitude, gabMapService.Longitude), str, null, new LocaliserAgences$$ExternalSyntheticLambda5(this, gabMapService));
            } else {
                mapContainer.addMarker(EncodedImage.create("/gab.png"), new Coord(gabMapService.Latitude, gabMapService.Longitude), str, null, new LocaliserAgences$$ExternalSyntheticLambda6(this, gabMapService));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* synthetic */ void lambda$createGabMarker$2$com-b3g-ui-page-cih-Module-LocaliserAgences(GabMapService gabMapService, ActionEvent actionEvent) {
        showAgencyDetail(null, gabMapService);
    }

    /* synthetic */ void lambda$createGabMarker$3$com-b3g-ui-page-cih-Module-LocaliserAgences(GabMapService gabMapService, ActionEvent actionEvent) {
        showAgencyDetail(null, gabMapService);
    }

    /* synthetic */ void lambda$createGabMarker$4$com-b3g-ui-page-cih-Module-LocaliserAgences(GabMapService gabMapService, ActionEvent actionEvent) {
        showAgencyDetail(null, gabMapService);
    }

    /* synthetic */ void lambda$createGabMarker$5$com-b3g-ui-page-cih-Module-LocaliserAgences(GabMapService gabMapService, ActionEvent actionEvent) {
        showAgencyDetail(null, gabMapService);
    }

    public ArrayList AgencyMapServiceProcess(String str, String str2, String str3) {
        new ArrayList();
        AgencyMapService agencyMapService = new AgencyMapService();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("VILLE", str.toUpperCase());
        hashtable.put("LONGITUDE", str3);
        hashtable.put("LATITUDE", str2);
        hashtable.put("TYPE", "AGENCE");
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(60);
        serviceRequest.setSessionId("4532391");
        return agencyMapService.FillAgencyMapDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public ArrayList LocaliserAgenceProcess(String str, String str2, String str3) {
        new ArrayList();
        LocaliserAgencyService localiserAgencyService = new LocaliserAgencyService();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("VILLE", str.toUpperCase());
        hashtable.put("LONGITUDE", str3);
        hashtable.put("LATITUDE", str2);
        hashtable.put("CLIENT_ID", "8765432");
        hashtable.put("SESSION_ID", "8765432");
        hashtable.put("TYPE", "AGENCE");
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(60);
        serviceRequest.setSessionId("4532391");
        return localiserAgencyService.FillAgencyMapDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public ArrayList LocaliserGabProcess(String str, String str2, String str3) {
        new ArrayList();
        LocaliserGabService localiserGabService = new LocaliserGabService();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("VILLE", str.toUpperCase());
        hashtable.put("LONGITUDE", str3);
        hashtable.put("LATITUDE", str2);
        hashtable.put("TYPE", "GAB");
        hashtable.put("CLIENT_ID", "8765432");
        hashtable.put("SESSION_ID", "8765432");
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(60);
        serviceRequest.setSessionId("4532391");
        return localiserGabService.FillAgencyMapDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public ArrayList GabMapServiceProcess(String str, String str2, String str3) {
        new ArrayList();
        GabMapService gabMapService = new GabMapService();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("VILLE", str.toUpperCase());
        hashtable.put("LONGITUDE", str3);
        hashtable.put("LATITUDE", str2);
        hashtable.put("CLIENT_ID", "8765432");
        hashtable.put("SESSION_ID", "8765432");
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(69);
        serviceRequest.setSessionId("4532391");
        return gabMapService.FillGABMapDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public ArrayList getCitiesProcess() {
        new ArrayList();
        AgencyMapService agencyMapService = new AgencyMapService();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("VILLE", "villes");
        hashtable.put("TYPE", "AGENCE");
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(60);
        serviceRequest.setSessionId("8765432");
        return agencyMapService.FillCitiesDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    private void showAgencyDetail(AgencyMapService agencyMapService, GabMapService gabMapService) {
        this.agency = agencyMapService;
        Dialog dialog = new Dialog();
        this.d = dialog;
        dialog.getStyle().setBgColor(16777215);
        this.d.setLayout(new BorderLayout());
        this.d.setAlwaysTensile(false);
        this.d.setDraggable(false);
        this.d.setScrollable(false);
        this.d.setTactileTouch(false);
        this.d.setTensileDragEnabled(false);
        this.d.getDialogStyle().setBgTransparency(255);
        this.d.getDialogStyle().setBgColor(1118481);
        this.d.getStyle().setPadding(0, 0, 0, 0);
        this.d.getStyle().setMargin(0, 0, 0, 0);
        this.d.getDialogStyle().setPadding(0, 0, 0, 0);
        this.d.getDialogStyle().setMargin(0, 0, 0, 0);
        this.d.revalidate();
        this.d.setUIID("Container");
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "AgenceMapDetail");
        createContainer.setPreferredH((Display.getInstance().getDisplayHeight() * 100) / 100);
        createContainer.setPreferredW((Display.getInstance().getDisplayWidth() * 100) / 100);
        Container container = (Container) uIBuilder.findByName("detailsCenterCnt", createContainer);
        Button button = (Button) uIBuilder.findByName("traceRoute", createContainer);
        Button button2 = (Button) uIBuilder.findByName("closeBtn", createContainer);
        if (agencyMapService == null) {
            this.agency = gabMapService.agency;
        }
        button.addActionListener(new 8(agencyMapService, gabMapService));
        if (gabMapService != null) {
            if (gabMapService.GabLabel.length() != 0) {
                container.add(addDetailItem("Nom : ", gabMapService.GabLabel, "", null));
            }
            if (gabMapService.GabAdresse.length() != 0) {
                container.add(addDetailItem("Adresse : ", gabMapService.GabAdresse, "", null));
            }
            if (gabMapService.StatusAtm.length() != 0) {
                container.add(addDetailItem("Statut : ", gabMapService.StatusAtm, "", null));
            }
            if (gabMapService.TicketAtm.length() != 0) {
                container.add(addDetailItem("Ticket : ", gabMapService.TicketAtm, "", null));
            }
        }
        if (agencyMapService != null) {
            if (this.agency.ville.length() != 0) {
                container.add(addDetailItem("Ville : ", this.agency.ville, "", null));
            }
            if (this.agency.numero_mobile.length() != 0) {
                container.add(addDetailItem("N° mobile : ", this.agency.numero_mobile, "phone-call.png", null));
            }
            if (this.agency.numero_fixe.length() != 0) {
                container.add(addDetailItem("N° fixe : ", this.agency.numero_fixe, "phone-call.png", null));
            }
        }
        if (agencyMapService != null) {
            if (this.agency.AgenceName.length() != 0) {
                container.add(addDetailItem("Nom d'agence : ", this.agency.AgenceName, "", null));
            }
            if (this.agency.ville.length() != 0) {
                container.add(addDetailItem("Ville : ", this.agency.ville, "", null));
            }
            if (this.agency.adresse.length() != 0) {
                container.add(addDetailItem("Adresse : ", this.agency.adresse, "", null));
            }
            if (this.agency.numero_mobile.length() != 0) {
                container.add(addDetailItem("N° mobile : ", this.agency.numero_mobile, "phone-call.png", null));
            }
            if (this.agency.numero_fixe.length() != 0) {
                container.add(addDetailItem("N° fixe : ", this.agency.numero_fixe, "phone-call.png", null));
            }
        }
        button2.addActionListener(new LocaliserAgences$$ExternalSyntheticLambda0(this));
        this.d.addComponent("Center", createContainer);
        this.d.showPacked("Center", false);
    }

    class 8 implements ActionListener {
        final /* synthetic */ GabMapService val$gab;
        final /* synthetic */ AgencyMapService val$pAgency;

        8(AgencyMapService agencyMapService, GabMapService gabMapService) {
            this.val$pAgency = agencyMapService;
            this.val$gab = gabMapService;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LocaliserAgences.this.d.dispose();
            if (Display.getInstance().isSimulator()) {
                return;
            }
            MapsNativeCall mapsNativeCall = (MapsNativeCall) NativeLookup.create(MapsNativeCall.class);
            if (mapsNativeCall.isSupported()) {
                if (this.val$pAgency == null) {
                    mapsNativeCall.showexternalMaps(this.val$gab.Latitude, this.val$gab.Longitude, this.val$gab.GabLabel);
                } else {
                    mapsNativeCall.showexternalMaps(LocaliserAgences.this.agency.latitude, LocaliserAgences.this.agency.longitude, LocaliserAgences.this.agency.AgenceName);
                }
            }
        }
    }

    /* synthetic */ void lambda$showAgencyDetail$6$com-b3g-ui-page-cih-Module-LocaliserAgences(ActionEvent actionEvent) {
        this.d.dispose();
    }

    private void loadAllMarkers(MapContainer mapContainer, B3GUiManager b3GUiManager, ArrayList arrayList, ArrayList arrayList2, int i, boolean z) {
        Location currentLocationSync;
        Location currentLocationSync2;
        mapContainer.clearMapLayers();
        if (arrayList != null && !arrayList.isEmpty()) {
            if (arrayList.size() == 1) {
                createAgencyMarker(mapContainer, (AgencyMapService) arrayList.get(0), "ma position");
            } else {
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    createAgencyMarker(mapContainer, (AgencyMapService) arrayList.get(i2), null);
                }
            }
            mapContainer.zoom(new Coord(((AgencyMapService) arrayList.get(0)).latitude, ((AgencyMapService) arrayList.get(0)).longitude), i);
        }
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            if (arrayList2.size() == 1) {
                createGabMarker(mapContainer, (GabMapService) arrayList2.get(0), b3GUiManager, "ma position");
            } else {
                for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                    createGabMarker(mapContainer, (GabMapService) arrayList2.get(i3), b3GUiManager, null);
                }
            }
            mapContainer.zoom(new Coord(((GabMapService) arrayList2.get(0)).Latitude, ((GabMapService) arrayList2.get(0)).Longitude), 15);
        }
        if (z) {
            return;
        }
        if (Display.getInstance().getLocationManager().isGPSDetectionSupported()) {
            if (Display.getInstance().getLocationManager().isGPSEnabled()) {
                Dialog showInifiniteBlocking = new InfiniteProgress().showInifiniteBlocking();
                showInifiniteBlocking.setUIID("Container");
                try {
                    currentLocationSync2 = LocationManager.getLocationManager().getCurrentLocation();
                } catch (IOException unused) {
                    currentLocationSync2 = LocationManager.getLocationManager().getCurrentLocationSync(30000L);
                }
                showInifiniteBlocking.dispose();
                if (currentLocationSync2 != null) {
                    double latitude = currentLocationSync2.getLatitude();
                    double longitude = currentLocationSync2.getLongitude();
                    try {
                        if (Display.getInstance().getPlatformName().equals("ios") || Display.getInstance().getDisplayHeight() < 1000) {
                            mapContainer.addMarker(EncodedImage.create("/me_marker_84.png"), new Coord(latitude, longitude), "ma position", null, null);
                        } else {
                            mapContainer.addMarker(EncodedImage.create("/me_marker.png"), new Coord(latitude, longitude), "ma position", null, null);
                        }
                        mapContainer.zoom(new Coord(latitude, longitude), 15);
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                return;
            }
            return;
        }
        Dialog showInifiniteBlocking2 = new InfiniteProgress().showInifiniteBlocking();
        showInifiniteBlocking2.setUIID("Container");
        try {
            currentLocationSync = LocationManager.getLocationManager().getCurrentLocation();
        } catch (IOException unused2) {
            currentLocationSync = LocationManager.getLocationManager().getCurrentLocationSync(30000L);
        }
        showInifiniteBlocking2.dispose();
        if (currentLocationSync != null) {
            double latitude2 = currentLocationSync.getLatitude();
            double longitude2 = currentLocationSync.getLongitude();
            try {
                if (Display.getInstance().getPlatformName().equals("ios") || Display.getInstance().getDisplayHeight() < 1000) {
                    mapContainer.addMarker(EncodedImage.create("/me_marker_84.png"), new Coord(latitude2, longitude2), "ma position", null, null);
                } else {
                    mapContainer.addMarker(EncodedImage.create("/me_marker.png"), new Coord(latitude2, longitude2), "ma position", null, null);
                }
                mapContainer.zoom(new Coord(latitude2, longitude2), 15);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private ArrayList searchCities(String str, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (str.length() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                String str2 = (String) arrayList.get(i);
                String[] split = new RE("\\s+").split(str2);
                if (str2.toLowerCase().startsWith(str.toLowerCase())) {
                    arrayList2.add((String) arrayList.get(i));
                } else {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= split.length) {
                            break;
                        }
                        if (split[i2].toLowerCase().startsWith(str.toLowerCase())) {
                            arrayList2.add((String) arrayList.get(i));
                            break;
                        }
                        i2++;
                    }
                }
            }
            return arrayList2;
        }
        arrayList2.addAll(arrayList);
        return arrayList2;
    }

    private Container addDetailItem(String str, String str2, String str3, ActionListener actionListener) {
        Container container = new Container();
        container.setUIID("margin_2_0_0_0");
        container.setLayout(new BorderLayout());
        Label label = new Label(str);
        label.setUIID("accountDocumentLabel");
        container.add("West", label);
        SpanLabel spanLabel = new SpanLabel(str2);
        spanLabel.setTextUIID("dm_lblPageNumberW");
        container.add("Center", spanLabel);
        return container;
    }

    private Container createAutourdeMoiAgenceCnt(B3GUiManager b3GUiManager, String str, String str2) {
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "agenceAutourDemoi");
        this.slideDownBtn = (Button) uIBuilder.findByName("slideDownBtn", createContainer);
        Button button = (Button) uIBuilder.findByName("afficherCarteBtn", createContainer);
        Container container = (Container) uIBuilder.findByName("autourTopCnt", createContainer);
        Container container2 = (Container) uIBuilder.findByName("itemsListCnt", createContainer);
        this.itemsListCnt = container2;
        container2.setScrollableY(true);
        ArrayList arrayList = new ArrayList();
        this.list = arrayList;
        if (arrayList.size() == 0) {
            this.list = ((LocaliserAgencyService) LocaliserAgenceProcess("", str, str2).get(0)).closestAgencies;
        }
        this.cnt.zoom(new Coord(((AgencyMapService) this.list.get(0)).latitude, ((AgencyMapService) this.list.get(0)).longitude), 18);
        for (int i = 0; i < this.list.size(); i++) {
            applyAction(b3GUiManager, (AgencyMapService) this.list.get(i), i);
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < this.list.size(); i2++) {
            TabTitleItemModeleViewPager tabTitleItemModeleViewPager = new TabTitleItemModeleViewPager();
            tabTitleItemModeleViewPager.icon = "slider_arrow.png";
            tabTitleItemModeleViewPager.selectedIcon = "slider_arrow_w.png";
            tabTitleItemModeleViewPager.tabCnt = ((AgencyMapService) this.list.get(i2)).DrawItem(null, null, 0, null, null, null);
            arrayList2.add(tabTitleItemModeleViewPager);
        }
        B3GViewPager b3GViewPager = new B3GViewPager(arrayList2);
        Container drawTabs = b3GViewPager.drawTabs(this.currentAgencyIndex);
        b3GViewPager.t.addSelectionListener(new 9());
        this.itemsListCnt.add(drawTabs);
        this.autourTopCntHeight = container.getPreferredH();
        int preferredH = this.itemsListCnt.getComponentAt(0).getPreferredH();
        this.autourItemCntHeight = preferredH;
        this.autourTopCntHeight += preferredH;
        button.addActionListener(new 10());
        this.slideDownBtn.addActionListener(new 11(b3GUiManager));
        return createContainer;
    }

    class 9 implements SelectionListener {
        9() {
        }

        public void selectionChanged(int i, int i2) {
            LocaliserAgences.this.cnt.zoom(new Coord(((AgencyMapService) LocaliserAgences.this.list.get(i2)).latitude, ((AgencyMapService) LocaliserAgences.this.list.get(i2)).longitude), 18);
            LocaliserAgences.this.currentAgencyIndex = i2;
        }
    }

    class 10 implements ActionListener {
        10() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LocaliserAgences.this.Bigup = true;
            LocaliserAgences.this.c1.setPreferredH(0);
            LocaliserAgences.this.autourDeMoiCnt.animateLayout(500);
        }
    }

    class 11 implements ActionListener {
        final /* synthetic */ B3GUiManager val$uiManager;

        11(B3GUiManager b3GUiManager) {
            this.val$uiManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            int i = 0;
            if (LocaliserAgences.this.up) {
                LocaliserAgences.this.up = false;
                LocaliserAgences.this.slideDownBtn.setIcon(this.val$uiManager.ressource.getImage("slidDown.png"));
                LocaliserAgences.this.itemsListCnt.removeAll();
                while (i < LocaliserAgences.this.list.size()) {
                    LocaliserAgences.this.itemsListCnt.add(((AgencyMapService) LocaliserAgences.this.list.get(i)).DrawItem(null, null, 0, null, null, null));
                    i++;
                }
                LocaliserAgences.this.c1.setPreferredH(Display.getInstance().getDisplayHeight());
                LocaliserAgences.this.autourDeMoiCnt.animateLayout(500);
                LocaliserAgences.this.itemsListCnt.setScrollableY(true);
                return;
            }
            LocaliserAgences.this.up = true;
            LocaliserAgences.this.slideDownBtn.setIcon(this.val$uiManager.ressource.getImage("slidUp.png"));
            LocaliserAgences.this.itemsListCnt.setScrollableY(false);
            LocaliserAgences.this.itemsListCnt.removeAll();
            ArrayList arrayList = new ArrayList();
            while (i < LocaliserAgences.this.list.size()) {
                TabTitleItemModeleViewPager tabTitleItemModeleViewPager = new TabTitleItemModeleViewPager();
                tabTitleItemModeleViewPager.icon = "slider_arrow.png";
                tabTitleItemModeleViewPager.selectedIcon = "slider_arrow_w.png";
                tabTitleItemModeleViewPager.tabCnt = ((AgencyMapService) LocaliserAgences.this.list.get(i)).DrawItem(null, null, 0, null, null, null);
                arrayList.add(tabTitleItemModeleViewPager);
                i++;
            }
            B3GViewPager b3GViewPager = new B3GViewPager(arrayList);
            Container drawTabs = b3GViewPager.drawTabs(LocaliserAgences.this.currentAgencyIndex);
            b3GViewPager.t.addSelectionListener(new 1());
            LocaliserAgences.this.itemsListCnt.add(drawTabs);
            LocaliserAgences.this.c1.setPreferredH(LocaliserAgences.this.autourTopCntHeight);
            LocaliserAgences.this.autourDeMoiCnt.animateLayout(500);
        }

        class 1 implements SelectionListener {
            1() {
            }

            public void selectionChanged(int i, int i2) {
                LocaliserAgences.this.cnt.zoom(new Coord(((AgencyMapService) LocaliserAgences.this.list.get(i2)).latitude, ((AgencyMapService) LocaliserAgences.this.list.get(i2)).longitude), 18);
                LocaliserAgences.this.currentAgencyIndex = i2;
            }
        }
    }

    private Container createAutourdeMoiGabCnt(B3GUiManager b3GUiManager, String str, String str2) {
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "agenceAutourDemoi");
        this.slideDownBtn = (Button) uIBuilder.findByName("slideDownBtn", createContainer);
        Button button = (Button) uIBuilder.findByName("afficherCarteBtn", createContainer);
        Container container = (Container) uIBuilder.findByName("autourTopCnt", createContainer);
        ((Label) uIBuilder.findByName("Label1", createContainer)).setText("GAB le plus proche");
        Container container2 = (Container) uIBuilder.findByName("itemsListCnt", createContainer);
        this.itemsListCnt = container2;
        container2.setScrollableY(true);
        ArrayList arrayList = new ArrayList();
        this.listGab = arrayList;
        if (arrayList.size() == 0) {
            this.listGab = ((LocaliserGabService) LocaliserGabProcess("", str, str2).get(0)).closestAgencies;
        }
        this.cnt.zoom(new Coord(((GabMapService) this.listGab.get(0)).Latitude, ((GabMapService) this.listGab.get(0)).Longitude), 18);
        for (int i = 0; i < this.listGab.size(); i++) {
            applyAction(b3GUiManager, (GabMapService) this.listGab.get(i), i);
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < this.listGab.size(); i2++) {
            TabTitleItemModeleViewPager tabTitleItemModeleViewPager = new TabTitleItemModeleViewPager();
            tabTitleItemModeleViewPager.icon = "slider_arrow.png";
            tabTitleItemModeleViewPager.selectedIcon = "slider_arrow_w.png";
            tabTitleItemModeleViewPager.tabCnt = ((GabMapService) this.listGab.get(i2)).DrawItem(null, null, 0, null, null, null);
            arrayList2.add(tabTitleItemModeleViewPager);
        }
        B3GViewPager b3GViewPager = new B3GViewPager(arrayList2);
        Container drawTabs = b3GViewPager.drawTabs(this.currentAgencyIndex);
        b3GViewPager.t.addSelectionListener(new 12());
        this.itemsListCnt.add(drawTabs);
        this.autourTopCntHeight = container.getPreferredH();
        int preferredH = this.itemsListCnt.getComponentAt(0).getPreferredH();
        this.autourItemCntHeight = preferredH;
        this.autourTopCntHeight += preferredH;
        button.addActionListener(new 13());
        this.slideDownBtn.addActionListener(new 14(b3GUiManager));
        return createContainer;
    }

    class 12 implements SelectionListener {
        12() {
        }

        public void selectionChanged(int i, int i2) {
            LocaliserAgences.this.cnt.zoom(new Coord(((GabMapService) LocaliserAgences.this.listGab.get(i2)).Latitude, ((GabMapService) LocaliserAgences.this.listGab.get(i2)).Longitude), 18);
            LocaliserAgences.this.currentAgencyIndex = i2;
        }
    }

    class 13 implements ActionListener {
        13() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LocaliserAgences.this.Bigup = true;
            LocaliserAgences.this.c1.setPreferredH(0);
            LocaliserAgences.this.autourDeMoiCnt.animateLayout(500);
        }
    }

    class 14 implements ActionListener {
        final /* synthetic */ B3GUiManager val$uiManager;

        14(B3GUiManager b3GUiManager) {
            this.val$uiManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            int i = 0;
            if (LocaliserAgences.this.up) {
                LocaliserAgences.this.up = false;
                LocaliserAgences.this.slideDownBtn.setIcon(this.val$uiManager.ressource.getImage("slidDown.png"));
                LocaliserAgences.this.itemsListCnt.removeAll();
                while (i < LocaliserAgences.this.listGab.size()) {
                    LocaliserAgences.this.itemsListCnt.add(((GabMapService) LocaliserAgences.this.listGab.get(i)).DrawItem(null, null, 0, null, null, null));
                    i++;
                }
                LocaliserAgences.this.c1.setPreferredH(Display.getInstance().getDisplayHeight());
                LocaliserAgences.this.autourDeMoiCnt.animateLayout(500);
                LocaliserAgences.this.itemsListCnt.setScrollableY(true);
                return;
            }
            LocaliserAgences.this.up = true;
            LocaliserAgences.this.slideDownBtn.setIcon(this.val$uiManager.ressource.getImage("slidUp.png"));
            LocaliserAgences.this.itemsListCnt.setScrollableY(false);
            LocaliserAgences.this.itemsListCnt.removeAll();
            ArrayList arrayList = new ArrayList();
            while (i < LocaliserAgences.this.listGab.size()) {
                TabTitleItemModeleViewPager tabTitleItemModeleViewPager = new TabTitleItemModeleViewPager();
                tabTitleItemModeleViewPager.icon = "slider_arrow.png";
                tabTitleItemModeleViewPager.selectedIcon = "slider_arrow_w.png";
                tabTitleItemModeleViewPager.tabCnt = ((GabMapService) LocaliserAgences.this.listGab.get(i)).DrawItem(null, null, 0, null, null, null);
                arrayList.add(tabTitleItemModeleViewPager);
                i++;
            }
            B3GViewPager b3GViewPager = new B3GViewPager(arrayList);
            Container drawTabs = b3GViewPager.drawTabs(LocaliserAgences.this.currentAgencyIndex);
            b3GViewPager.t.addSelectionListener(new 1());
            LocaliserAgences.this.itemsListCnt.add(drawTabs);
            LocaliserAgences.this.c1.setPreferredH(LocaliserAgences.this.autourTopCntHeight);
            LocaliserAgences.this.autourDeMoiCnt.animateLayout(500);
        }

        class 1 implements SelectionListener {
            1() {
            }

            public void selectionChanged(int i, int i2) {
                LocaliserAgences.this.cnt.zoom(new Coord(((GabMapService) LocaliserAgences.this.listGab.get(i2)).Latitude, ((GabMapService) LocaliserAgences.this.listGab.get(i2)).Longitude), 18);
                LocaliserAgences.this.currentAgencyIndex = i2;
            }
        }
    }

    class 15 implements ActionListener {
        final /* synthetic */ AgencyMapService val$pAgencyMapService;
        final /* synthetic */ int val$selectedIndex;
        final /* synthetic */ B3GUiManager val$uiManager;

        15(int i, AgencyMapService agencyMapService, B3GUiManager b3GUiManager) {
            this.val$selectedIndex = i;
            this.val$pAgencyMapService = agencyMapService;
            this.val$uiManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LocaliserAgences.this.currentAgencyIndex = this.val$selectedIndex;
            LocaliserAgences.this.cnt.zoom(new Coord(this.val$pAgencyMapService.latitude, this.val$pAgencyMapService.longitude), 18);
            LocaliserAgences.this.up = true;
            LocaliserAgences.this.slideDownBtn.setIcon(this.val$uiManager.ressource.getImage("slidUp.png"));
            LocaliserAgences.this.itemsListCnt.setScrollableY(false);
            LocaliserAgences.this.itemsListCnt.removeAll();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < LocaliserAgences.this.list.size(); i++) {
                TabTitleItemModeleViewPager tabTitleItemModeleViewPager = new TabTitleItemModeleViewPager();
                tabTitleItemModeleViewPager.icon = "slider_arrow.png";
                tabTitleItemModeleViewPager.selectedIcon = "slider_arrow_w.png";
                tabTitleItemModeleViewPager.tabCnt = ((AgencyMapService) LocaliserAgences.this.list.get(i)).DrawItem(null, null, 0, null, null, null);
                arrayList.add(tabTitleItemModeleViewPager);
            }
            B3GViewPager b3GViewPager = new B3GViewPager(arrayList);
            Container drawTabs = b3GViewPager.drawTabs(this.val$selectedIndex);
            b3GViewPager.t.addSelectionListener(new 1());
            LocaliserAgences.this.itemsListCnt.add(drawTabs);
            LocaliserAgences.this.c1.setPreferredH(LocaliserAgences.this.autourTopCntHeight);
            LocaliserAgences.this.autourDeMoiCnt.animateLayout(500);
        }

        class 1 implements SelectionListener {
            1() {
            }

            public void selectionChanged(int i, int i2) {
                LocaliserAgences.this.cnt.zoom(new Coord(((AgencyMapService) LocaliserAgences.this.list.get(i2)).latitude, ((AgencyMapService) LocaliserAgences.this.list.get(i2)).longitude), 18);
                LocaliserAgences.this.currentAgencyIndex = i2;
            }
        }
    }

    private void applyAction(B3GUiManager b3GUiManager, AgencyMapService agencyMapService, int i) {
        agencyMapService.listener = new 15(i, agencyMapService, b3GUiManager);
    }

    class 16 implements ActionListener {
        final /* synthetic */ GabMapService val$pAgencyMapService;
        final /* synthetic */ int val$selectedIndex;
        final /* synthetic */ B3GUiManager val$uiManager;

        16(int i, GabMapService gabMapService, B3GUiManager b3GUiManager) {
            this.val$selectedIndex = i;
            this.val$pAgencyMapService = gabMapService;
            this.val$uiManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            LocaliserAgences.this.currentAgencyIndex = this.val$selectedIndex;
            LocaliserAgences.this.cnt.zoom(new Coord(this.val$pAgencyMapService.Latitude, this.val$pAgencyMapService.Longitude), 18);
            LocaliserAgences.this.up = true;
            LocaliserAgences.this.slideDownBtn.setIcon(this.val$uiManager.ressource.getImage("slidUp.png"));
            LocaliserAgences.this.itemsListCnt.setScrollableY(false);
            LocaliserAgences.this.itemsListCnt.removeAll();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < LocaliserAgences.this.list.size(); i++) {
                TabTitleItemModeleViewPager tabTitleItemModeleViewPager = new TabTitleItemModeleViewPager();
                tabTitleItemModeleViewPager.icon = "slider_arrow.png";
                tabTitleItemModeleViewPager.selectedIcon = "slider_arrow_w.png";
                tabTitleItemModeleViewPager.tabCnt = ((AgencyMapService) LocaliserAgences.this.list.get(i)).DrawItem(null, null, 0, null, null, null);
                arrayList.add(tabTitleItemModeleViewPager);
            }
            B3GViewPager b3GViewPager = new B3GViewPager(arrayList);
            Container drawTabs = b3GViewPager.drawTabs(this.val$selectedIndex);
            b3GViewPager.t.addSelectionListener(new 1());
            LocaliserAgences.this.itemsListCnt.add(drawTabs);
            LocaliserAgences.this.c1.setPreferredH(LocaliserAgences.this.autourTopCntHeight);
            LocaliserAgences.this.autourDeMoiCnt.animateLayout(500);
        }

        class 1 implements SelectionListener {
            1() {
            }

            public void selectionChanged(int i, int i2) {
                LocaliserAgences.this.cnt.zoom(new Coord(((AgencyMapService) LocaliserAgences.this.list.get(i2)).latitude, ((AgencyMapService) LocaliserAgences.this.list.get(i2)).longitude), 18);
                LocaliserAgences.this.currentAgencyIndex = i2;
            }
        }
    }

    private void applyAction(B3GUiManager b3GUiManager, GabMapService gabMapService, int i) {
        gabMapService.listener = new 16(i, gabMapService, b3GUiManager);
    }

    private void showItineraire(double d, double d2) {
        Location currentLocationSync;
        if (SessionParameter.currentLat == 0.0d) {
            Dialog showInifiniteBlocking = new InfiniteProgress().showInifiniteBlocking();
            try {
                currentLocationSync = LocationManager.getLocationManager().getCurrentLocation();
            } catch (IOException unused) {
                currentLocationSync = LocationManager.getLocationManager().getCurrentLocationSync(30000L);
            }
            showInifiniteBlocking.dispose();
            if (currentLocationSync != null) {
                double latitude = currentLocationSync.getLatitude();
                double longitude = currentLocationSync.getLongitude();
                SessionParameter.currentLat = latitude;
                SessionParameter.currentLng = longitude;
            }
        }
        this.cnt.addPath(new Coord(SessionParameter.currentLat, SessionParameter.currentLng), new Coord(d, d2));
        this.autourDeMoiCnt.revalidate();
    }

    private void findmeAction(B3GUiManager b3GUiManager) {
        Location currentLocationSync;
        Dialog showInifiniteBlocking = new InfiniteProgress().showInifiniteBlocking();
        try {
            currentLocationSync = LocationManager.getLocationManager().getCurrentLocation();
        } catch (IOException unused) {
            currentLocationSync = LocationManager.getLocationManager().getCurrentLocationSync(30000L);
        }
        showInifiniteBlocking.dispose();
        if (currentLocationSync != null) {
            double latitude = currentLocationSync.getLatitude();
            double longitude = currentLocationSync.getLongitude();
            this.cnt.clearMapLayers();
            int i = 0;
            if (this.searchForAgencies) {
                if (this.agenceList.size() == 0) {
                    this.agenceList = AgencyMapServiceProcess("", "", "");
                }
                if (this.agenceList.isEmpty()) {
                    return;
                }
                if (this.agenceList.size() == 1) {
                    createAgencyMarker(this.cnt, (AgencyMapService) this.agenceList.get(0), "ma position");
                } else {
                    while (i < this.agenceList.size()) {
                        createAgencyMarker(this.cnt, (AgencyMapService) this.agenceList.get(i), null);
                        i++;
                    }
                }
                try {
                    if (Display.getInstance().getPlatformName().equals("ios") || Display.getInstance().getDisplayHeight() < 1000) {
                        this.cnt.addMarker(EncodedImage.create("/me_marker_84.png"), new Coord(latitude, longitude), "ma position", null, null);
                    } else {
                        this.cnt.addMarker(EncodedImage.create("/me_marker.png"), new Coord(latitude, longitude), "ma position", null, null);
                    }
                    this.cnt.zoom(new Coord(latitude, longitude), 15);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (this.gabList.size() == 0) {
                this.gabList = GabMapServiceProcess("", "", "");
            }
            if (this.gabList.isEmpty()) {
                return;
            }
            if (this.gabList.size() == 1) {
                createGabMarker(this.cnt, (GabMapService) this.gabList.get(0), b3GUiManager, "ma position");
            } else {
                while (i < this.gabList.size()) {
                    createGabMarker(this.cnt, (GabMapService) this.gabList.get(i), b3GUiManager, null);
                    i++;
                }
            }
            try {
                if (Display.getInstance().getPlatformName().equals("ios") || Display.getInstance().getDisplayHeight() < 1000) {
                    this.cnt.addMarker(EncodedImage.create("/me_marker_84.png"), new Coord(latitude, longitude), "ma position", null, null);
                } else {
                    this.cnt.addMarker(EncodedImage.create("/me_marker.png"), new Coord(latitude, longitude), "ma position", null, null);
                }
                this.cnt.zoom(new Coord(latitude, longitude), 15);
                return;
            } catch (IOException e2) {
                e2.printStackTrace();
                return;
            }
        }
        b3GUiManager.ShowMessageTransaction(b3GUiManager.stateMachine, DataTools.FormatUnicode("Votre position actuelle est temporairement indisponible"), null);
    }

    private void autourDeMoiAction(B3GUiManager b3GUiManager) {
        Location currentLocationSync;
        Dialog showInifiniteBlocking = new InfiniteProgress().showInifiniteBlocking();
        try {
            currentLocationSync = LocationManager.getLocationManager().getCurrentLocation();
        } catch (IOException unused) {
            currentLocationSync = LocationManager.getLocationManager().getCurrentLocationSync(30000L);
        }
        showInifiniteBlocking.dispose();
        if (currentLocationSync != null) {
            double latitude = currentLocationSync.getLatitude();
            double longitude = currentLocationSync.getLongitude();
            if (this.firstLunch) {
                SessionParameter.currentLat = latitude;
                SessionParameter.currentLng = longitude;
                if (this.searchForAgencies) {
                    this.c1 = createAutourdeMoiAgenceCnt(b3GUiManager, latitude + "", longitude + "");
                } else {
                    this.c1 = createAutourdeMoiGabCnt(b3GUiManager, latitude + "", longitude + "");
                }
            }
            this.autourDeMoiHeight = this.c1.getPreferredH();
            this.c1.setPreferredH(0);
            if (this.isC1Created) {
                this.isC1Created = false;
                this.autourDeMoiCnt.add("South", this.c1);
            }
            this.c1.revalidate();
            this.autourDeMoiCnt.revalidate();
            this.firstLunch = false;
            this.itemsListCnt.setScrollableY(false);
            if (this.Bigup) {
                this.Bigup = false;
                this.c1.setPreferredH(this.autourTopCntHeight);
                this.autourDeMoiCnt.animateLayout(500);
                return;
            } else {
                this.Bigup = true;
                this.c1.setPreferredH(0);
                this.autourDeMoiCnt.animateLayout(500);
                return;
            }
        }
        b3GUiManager.ShowMessageTransaction(b3GUiManager.stateMachine, DataTools.FormatUnicode("Votre position actuelle est temporairement indisponible"), null);
    }
}
