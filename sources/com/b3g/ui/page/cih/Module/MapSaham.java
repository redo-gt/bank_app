package com.b3g.ui.page.cih.Module;

import com.b3g.common.ServiceResponse;
import com.b3g.services.FolderMissionSaham;
import com.b3g.services.SinistreCoord;
import com.b3g.ui.B3GUiManager;
import com.codename1.googlemaps.MapContainer;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MapSaham {
    static int A;
    MapContainer cnt;
    FolderMissionSaham pMission;
    int i = 0;
    ArrayList SinistreCoords = new ArrayList();
    ArrayList MapObjects = new ArrayList();
    ArrayList MapCoords = new ArrayList();

    static /* synthetic */ void lambda$createAgencyMarker$1(ActionEvent actionEvent) {
    }

    static /* synthetic */ void lambda$createAgencyMarker$2(ActionEvent actionEvent) {
    }

    static /* synthetic */ void lambda$createUserMarker$3(ActionEvent actionEvent) {
    }

    static /* synthetic */ void lambda$createUserMarker$4(ActionEvent actionEvent) {
    }

    static /* synthetic */ void access$000(MapSaham mapSaham, MapContainer mapContainer, SinistreCoord sinistreCoord, String str) {
        mapSaham.createAgencyMarker(mapContainer, sinistreCoord, str);
    }

    static /* synthetic */ void access$100(MapSaham mapSaham, MapContainer mapContainer, SinistreCoord sinistreCoord, String str) {
        mapSaham.createUserMarker(mapContainer, sinistreCoord, str);
    }

    public Container drawMapp(B3GUiManager b3GUiManager, FolderMissionSaham folderMissionSaham) {
        Location currentLocationSync;
        this.pMission = folderMissionSaham;
        this.i = 0;
        SinistreCoord sinistreCoord = new SinistreCoord(Double.parseDouble(folderMissionSaham.pGpSlatitude), Double.parseDouble(folderMissionSaham.pGpsLongitude));
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "SinistreMap");
        Container container = (Container) uIBuilder.findByName("mapCnt", createContainer);
        MapContainer mapContainer = new MapContainer();
        this.cnt = mapContainer;
        container.add(mapContainer);
        createAgencyMarker(this.cnt, sinistreCoord, null);
        this.cnt.zoom(new Coord(sinistreCoord.lat, sinistreCoord.lng), 15);
        try {
            currentLocationSync = LocationManager.getLocationManager().getCurrentLocation();
        } catch (IOException unused) {
            currentLocationSync = LocationManager.getLocationManager().getCurrentLocationSync(30000L);
        }
        if (currentLocationSync != null) {
            createUserMarker(this.cnt, new SinistreCoord(currentLocationSync.getLatitude(), currentLocationSync.getLongitude()), "ma position");
        }
        Display.getInstance().scheduleBackgroundTask(new MapSaham$$ExternalSyntheticLambda4(this, b3GUiManager));
        return createContainer;
    }

    class 1 implements Runnable {
        final /* synthetic */ B3GUiManager val$uiManager;

        1(B3GUiManager b3GUiManager) {
            this.val$uiManager = b3GUiManager;
        }

        public void run() {
            new UITimer(new MapSaham$1$$ExternalSyntheticLambda0(this)).schedule(80000, true, this.val$uiManager.currentForm);
        }

        /* synthetic */ void lambda$run$0$com-b3g-ui-page-cih-Module-MapSaham$1() {
            MapSaham mapSaham = MapSaham.this;
            mapSaham.GetNextLoc(mapSaham.cnt);
        }
    }

    /* synthetic */ void lambda$drawMapp$0$com-b3g-ui-page-cih-Module-MapSaham(B3GUiManager b3GUiManager) {
        Display.getInstance().callSeriallyAndWait(new 1(b3GUiManager));
    }

    private void createAgencyMarker(MapContainer mapContainer, SinistreCoord sinistreCoord, String str) {
        try {
            if (Display.getInstance().getPlatformName().equals("ios") || Display.getInstance().getDisplayHeight() < 1000) {
                this.MapCoords.add(sinistreCoord);
                this.MapObjects.add(mapContainer.addMarker(EncodedImage.create("/depannage.png"), new Coord(sinistreCoord.lat, sinistreCoord.lng), str, null, new MapSaham$$ExternalSyntheticLambda0()));
            } else {
                this.MapCoords.add(sinistreCoord);
                this.MapObjects.add(mapContainer.addMarker(EncodedImage.create("/depannage.png"), new Coord(sinistreCoord.lat, sinistreCoord.lng), str, null, new MapSaham$$ExternalSyntheticLambda1()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createUserMarker(MapContainer mapContainer, SinistreCoord sinistreCoord, String str) {
        try {
            if (Display.getInstance().getPlatformName().equals("ios") || Display.getInstance().getDisplayHeight() < 1000) {
                this.MapCoords.add(sinistreCoord);
                mapContainer.addMarker(EncodedImage.create("/user.png"), new Coord(sinistreCoord.lat, sinistreCoord.lng), str, null, new MapSaham$$ExternalSyntheticLambda2());
            } else {
                this.MapCoords.add(sinistreCoord);
                mapContainer.addMarker(EncodedImage.create("/user.png"), new Coord(sinistreCoord.lat, sinistreCoord.lng), str, null, new MapSaham$$ExternalSyntheticLambda3());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GetNextLoc(MapContainer mapContainer) {
        FolderMissionSaham folderMissionSaham = new FolderMissionSaham();
        ServiceResponse missionTrack = folderMissionSaham.getMissionTrack(this.pMission);
        if (missionTrack.getStatusCode().equals("000") && missionTrack.getParamsOut() != null) {
            this.pMission = folderMissionSaham.FillTrackingFromServiceResponse(missionTrack.getParamsOut(), this.pMission);
        }
        Display.getInstance().invokeAndBlock(new 2(mapContainer));
    }

    class 2 implements Runnable {
        final /* synthetic */ MapContainer val$cnt;

        2(MapContainer mapContainer) {
            this.val$cnt = mapContainer;
        }

        public void run() {
            Location currentLocationSync;
            this.val$cnt.clearMapLayers();
            SinistreCoord sinistreCoord = new SinistreCoord(Double.parseDouble(MapSaham.this.pMission.pGpSlatitude), Double.parseDouble(MapSaham.this.pMission.pGpsLongitude));
            MapSaham.access$000(MapSaham.this, this.val$cnt, sinistreCoord, null);
            this.val$cnt.zoom(new Coord(sinistreCoord.lat, sinistreCoord.lng), 16);
            try {
                currentLocationSync = LocationManager.getLocationManager().getCurrentLocation();
            } catch (IOException unused) {
                currentLocationSync = LocationManager.getLocationManager().getCurrentLocationSync(30000L);
            }
            if (currentLocationSync != null) {
                MapSaham.access$100(MapSaham.this, this.val$cnt, new SinistreCoord(currentLocationSync.getLatitude(), currentLocationSync.getLongitude()), "ma position");
            }
            this.val$cnt.revalidate();
        }
    }
}
