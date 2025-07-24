package com.b3g.ui.page.cih.Module;

import com.b3g.tools.DataTools;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GUiManager;
import com.codename1.components.InfiniteProgress;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.gzip.GZConnectionRequest;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class LocaliserGabs {
    ArrayList SrvList = new ArrayList();
    Hashtable response;
    String[] villes;

    static /* synthetic */ ArrayList access$000(LocaliserGabs localiserGabs, ArrayList arrayList) {
        return localiserGabs.getAllData(arrayList);
    }

    static /* synthetic */ void access$100(LocaliserGabs localiserGabs, MapContainer mapContainer, Gab gab) {
        localiserGabs.createMarker(mapContainer, gab);
    }

    public Container drawMap(B3GUiManager b3GUiManager) {
        int i = 0;
        this.villes = new String[]{"VILLE", "AGADIR", "AGDEZ", "AGHBALA", "AGHBALOU N KERDOUSS", "AGHWINIT", "AGOURAI", "AGUELMOUS", "AHFIR", "AHL AL GHABA", "AHL OUARZAZATE", "AHMAR", "AIN AICHA", "AIN BNI MATHAR", "AIN CHEGGAG", "AIN CHKEF", "AIN DEFALI", "AIN EL AOUDA", "AIN HARROUDA", "AIN LEUH", "AIN MEDIOUNA", "AIN TAOUJDATE", "AIN ZOHRA", "AIT AATAB", "AIT ABDELLAH", "AIT BAHA", "AIT DAOUD", "AIT ERKHA", "AIT HANI", "AIT ISHAK", "AIT IZDEG", "AIT M HAMED", "AIT MELLOUL", "AIT MIMOUN", "AIT OUFELLA", "AIT OURIBEL", "AIT OURIR", "AIT YOUSSI", "AJDIR", "AJERMOUNASS", "AKKA", "AKKA IGUIREN", "AKLIM", "AKNOUL", "AL AOUAMRA", "AL HAGOUNIA", "AL HOCEIMA", "AL JDIRIYA", "ALNIF", "AMELLAGO", "AMGALA", "AMIZMIZ", "AMOUGUER", "ANERGUI", "ANEZI", "ANJRA", "AOUFOUSS", "AOULOUZ", "AOURIR", "ARBAA AIT AHMED", "ARBAA KHATTAZAKARNE", "ARBAA MAADNA", "ARBAA RASMOUKA", "ARBAA SAIS", "ARBAA SEBBAH ZIZ", "ARBAA SEHOUL", "ARBAA TAOURIRT", "ARBAOUA", "ARGANA", "ASILAH", "ASKAOUN", "ASRIR", "ASSA", "ASSIF EL MAL", "ASSIFANE", "ASSOUL", "ATTAOUIA ECH CHAIBIYA", "AWSERD", "AZEMMOUR", "AZILAL", "AZROU", "BAB BERRET", "BAB MARZOUKA", "BAB MROUJ", "BAB TAZA", "BEN AHMED", "BEN GUERIR", "BEN MANSOUR", "BEN SLIMANE", "BEN TIB", "BEN YAZRHA", "BENI AHMED", "BENI AMEUR", "BENI AMIR", "BENI ANSAR", "BENI BOU YAHIA", "BENI DRAR", "BENI FRASSEN", "BENI GARFETT", "BENI HLAL", "BENI KHIRANE", "BENI LENNT", "BENI MELLAL", "BENI MESKINE CHARQUIA", "BENI MESKINE GHARBIA", "BENI OUKIL", "BENI SMIR", "BENI TAJJIT", "BERKANE", "BERKINE", "BERRECHID", "BHALIL", "BHATRA CHAMALIA", "BIOUGRA", "BIR ANZARAN", "BIR GANDOUZ", "BIR JDID", "BNI AAROUSS", "BNI AMMART", "BNI ATTIG", "BNI BOU IFROUR", "BNI BOUAYACH", "BNI BOUFRAH", "BNI GMIL MESTASSA", "BNI HAFIDA", "BNI HASSAN", "BNI KARRICH", "BNI OULID", "BNI OURIARHEL", "BNI SAID", "BNI SIDEL", "BNI YAALA", "BNI YAKHLAF", "BOHOUDA", "BOU AHMED", "BOU MAIZ", "BOUANANE", "BOUARFA", "BOUAROUSS", "BOUDINAR", "BOUDNIB", "BOUFAKRANE", "BOUIZAKARNE", "BOUJAD", "BOUJDOUR", "BOUJNIBA", "BOUKNADEL", "BOUKRAA", "BOULEMANE", "BOUMALNE DADES", "BOUMIA", "BOUR", "BOURED", "BOUSKOURA", "BOUZEMLANE", "BOUZNIKA", "BRADIA", "BRIKCHA", "BZOU", "CASABLANCA", "CHEFCHAOUEN", "CHEMAIA", "CHERRAGA", "CHICHAOUA", "CHOUGRANE", "CHTOUKA", "DAKHLA", "DAR BOUAZZA", "DAR CHAOUI", "DAR EL GUEDDARI", "DAR KEBDANI", "DAR OULD ZIDOUH", "DAWRA", "DAYAT AOUA", "DCHEIRA", "DCHEIRA", "DCHIRA", "DEBDOU", "DEMNATE", "DEMSIRA", "DOUIMNIAA", "DRARGA", "DRIOUCH", "EL ADREJ", "EL AIOUN(PROVINCE D'OUJDA)", "EL AOUNATE", "EL ARGOUB", "EL BROUJ", "EL GARA", "EL HAJEB", "EL JADIDA", "EL KEBAB", "EL KELAA DES SRAGHNA", "EL KELAA M'GOUNA", "EL KSIBA", "ERFOUD", "ERRACHIDIA", "ESSAOUIRA", "ES-SEMARA", "ETRANGER", "FAHS", "FAM EL HISSN", "FARKHANA", "FEDALATE", "FES", "FETOUAKA", "FIFI", "FIGUIG", "FNIDEK", "FOUM ZGUID", "FQUI BEN SALAH", "FREIJA", "FRITISSA", "FROUGA", "GOULMIMA", "GOURRAMA", "GUELIBET EL FOULA", "GUELMIM", "GUELTAT ZEMMOUR", "GUERCIF", "GUEROUANE CHAMALIA", "GUETTAYA", "GZANAIA", "HAD AIT BELFAA", "HAD BEKHATI", "HAD BNI CHIKER", "HAD BRACHOUA", "HAD DRAA", "HAD KOURT", "HAD MRAMER", "HAD OULAD BEN MOUSSA", "HAD OULAD FENNANE", "HAD RHOUALEM", "HAD SOUALEM", "HAD TAHALA", "HADDADA", "HAOUARA OULAD RAHO", "HARHOURA", "HASSI BERKANE", "HATTANE", "HAWZA", "HOUAFAT", "IAAZZANENE", "IDDA GOUGMAR", "IFNI", "IFRANE", "IFRANE ATLAS SEGHIR", "IGHREM", "IKNIOUEN", "IMILCHIL", "IMIN TANOUTE", "IMLILI", "IMMOUZZER MARMOUCHA", "IMOUZZER IDDA OU TANANE", "IMOUZZER KANDAR", "IMZOUREN", "INEZGANE", "IRHREM NOUGDAL", "IRKLAOUEN TIMAHDITE", "ISSAGUEN", "ITZER", "IZEMMOUREN", "JEBHA", "JEMAA N TIRHIRT", "JEMAA SHIM", "JERADA", "JORF", "JORF EL MELHA", "JORF EL MELHA", "KAF NSOUR", "KAHF EL RHAR", "KARIA BA MOHAMED", "KARIA BEN AOUDA", "KARIAT AREKMANE", "KASBA TADLA", "KEBDANA", "KENITRA", "KERROUCHEN", "KETAMA", "KHEMIS ATTAOUIA", "KHEMIS D ISSAFEN", "KHEMIS IDDA OU GNIDIF", "KHEMIS SAHEL", "KHEMIS TAKATE", "KHEMIS TEMSAMANE", "KHEMIS ZEMAMRA", "KHEMISSET", "KHENIFRA", "KHNICHET", "KHOURIBGA", "KSABI", "KSABI", "KSAR EL KEBIR", "KSAR ZENASA", "KSIBIA DAR BEL AMRI", "LAAIN LQLIAA", "LAAYOUNE", "LAAYOUNE PLAGE", "LAGOUIRA", "LAKLEAA AIT MELLOUL", "LALLA MIMOUNA", "LARACHE", "LKHENG", "LOUTA", "M SIED", "M TOUGA", "MAARIF OULAD M HAMED", "MAAZIZ", "MADAGH", "MAHIRIJA", "MARRAKECH", "MARTIL", "MASSA", "MDAKRA", "MDARHRA KHENG", "M'DIQ", "MECHRA BEL KSIRI", "MEDIOUNA", "MEKNASSA", "MEKNES", "MELLAB", "MELLOUSSA", "MERHRAOUA", "MESFIOUA", "MESTI", "MEZGUITEM", "MHAMID", "MIDAR", "MIDELT", "MIJIK", "MIRLEFT", "MISSOUR", "MLAL", "MOHAMMEDIA", "MOKRISSET", "MONTE ARUIT", "MOUHA OU HAMOU ZAYANI", "MOULAY ABDELLAH", "MOULAY BOUAZZA", "MOULAY DRIS ZERHOUNE", "MOULAY YACOUB", "MRIRT", "M'RIRT", "MSAGHRA AIT YADINE", "MSEMRIR", "MZEFROUN", "MZOUDA DOUIRANE", "NADOR", "NOUACEUR", "NOUIRAT", "OUALD SAID", "OUALIDIYA", "OUAOUIZARHT", "OUAOULA", "OUARZAZATE", "OUAZZANE", "OUDAYA", "OUED AMLIL", "OUED LAOU", "OUED ZEM", "OUJDA", "OULAD AAMRANE", "OULAD ABBOU", "OULAD AISSA", "OULAD AISSA HJAOUA", "OULAD ALI", "OULAD ALIANE", "OULAD AYAD", "OULAD BEN DAOUD", "OULAD BERREHIL", "OULAD BOUZIRI", "OULAD GHANEM", "OULAD HARRIZ CHARQUIA", "OULAD HARRIZ GHARBIA", "OULAD HSINE", "OULAD JEMAA LEMTA", "OULAD M BAREK", "OULAD RIYAB", "OULAD SAID EL OUAD", "OULAD SALAH", "OULAD TEIMA", "OULAD TMIM", "OULAD YAICHE", "OULAD YOUSSEF", "OULED FREJ", "OULED JERRAR", "OULMES", "OUM DREYGA", "OUNASDASS OULAD YACOUB", "OURIKA", "OURTZARH", "OUTAT EL HAJ", "OUTERBAT", "OUZGUITA", "RABAT", "RAS EL AIN CHAOUIA", "RAS EL AIN RHAMNA", "RAS EL AIN(PROVINCE DE SAFI)", "RAS EL MA", "RHAFSAI", "RHANDOR MSEDDER", "RHARBIA", "RHIATE", "RHMATE", "RHOUJDAMA", "RIBAT EL KHEIR", "RICH", "RISSANI", "ROMMANI", "SABAA AIYOUN", "SAFI", "SAIDIA", "SAKA", "SALE", "SANIAT BENBRIK", "SEBT DECHRA BRAKSA", "SEBT GUERDANE", "SEBT GZOULA", "SEBT KORIMATE", "SEBT OULAD NEMMA", "SEFROU", "SEKSAOUA", "SELOUANE", "SEMGUET", "SETTAT", "SIDI ABDERRAZZAK", "SIDI ALLAL BAHRAOUI", "SIDI ALLAL TAZI", "SIDI BENNOUR", "SIDI BETTACHE", "SIDI BOU OTHMANE", "SIDI BOUAFIF", "SIDI BOUBKER EL HAJ", "SIDI BOUSBER", "SIDI BOUZID", "SIDI CHIKER", "SIDI EL AIDI", "SIDI HAJJAJ", "SIDI HARAZEM", "SIDI KACEM", "SIDI MOHAMED AHMAR", "SIDI MOKHTAR", "SIDI MOUSSA BEN ALI", "SIDI RAHHAL", "SIDI REDOUANE", "SIDI SLIMANE", "SIDI SMAIL", "SIDI YAHIA", "SIDI YAHIA ZAIRE", "SIDI YAHYA EL RHARB", "SKHIRATE", "SKHOUR RHAMNA", "SKOURA", "SKOURA", "SMIMOU", "SOUK EL ARBAA EL RHARB", "SOUK TLETA EL RHARB", "TABANNT", "TABARRANNT", "TAFARANNT", "TAFERSIT", "TAFINGOULT", "TAFORHALT", "TAFRAOUTE", "TAFTECHT", "TAGHZIRT", "TAGOUNITE", "TAGUELFT", "TAHANNAOUTE", "TAHAR SOUK", "TAHLA", "TAINESTE", "TALEMBOTE", "TALIOUINE", "TALMEST", "TALSINNT", "TAMALLALT", "TAMANAR", "TAMENSOURT", "TAMESLOUHT AIT IMOUR", "TAMESNA", "TAMRI", "TAN TAN", "TAN TAN PLAGE", "TANALT", "TANANNT", "TANGER", "TANKOUB", "TAOUNATE", "TAOURIRT", "TAOUZ", "TARFAYA", "TARGUISTE", "TARHJIJT", "TAROUDANT", "TATA", "TATOUFT", "TAZA", "TAZNAKHT", "TAZZARINE", "TAZZARINE", "TEMARA", "TEMRA", "TENDRARA", "TEROUAL", "TETOUAN", "TFARITI", "TICHLA", "TIDDAS", "TIFLET", "TILOUGGUITE", "TINEGHIR", "TINEJDAD", "TISSA", "TISSINT", "TIT MELLIL", "TIZGUITE", "TIZI OUZLI", "TIZNIT", "TLAT AZLAF", "TLAT OULAD", "TLATA HANCHANE", "TLETA AKHSSASS", "TLETA JBEL EL HABIB", "TLETA N YACOUB", "TLETA SID BOUGUEDRA", "TLETA TAGMOUTE", "TNIN SIDI LYAMANI", "TNINE ADDAR", "TNINE BOUCHANE", "TNINE IDA OU ZEMZEM", "TNINE RHARBIA", "TOLBA", "TOUAMA", "TOUARGA", "TOUISSITE", "TOUNFITE", "TROUKOUT", "YOUSSOUFIA", "ZAG", "ZAGORA", "ZAIO", "ZAOUIA AHANSAL", "ZAOUIA CHEIKH", "ZAOUIA SIDI AHMED OU MOUS", "ZEGANGANE", "ZERADA", "ZHILIGA", "ZIAIDA", "ZIRARA", "ZOUG", "ZOUMI"};
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "LocaliserGab");
        ComboBox comboBox = (ComboBox) uIBuilder.findByName("villesComboBox", createContainer);
        Container container = (Container) uIBuilder.findByName("mapCnt", createContainer);
        Button button = (Button) uIBuilder.findByName("searchBtn", createContainer);
        Button button2 = (Button) uIBuilder.findByName("autourDeMoi", createContainer);
        Button button3 = (Button) uIBuilder.findByName("BackButton", createContainer);
        MapContainer mapContainer = new MapContainer();
        DefaultListModel defaultListModel = (DefaultListModel) comboBox.getModel();
        defaultListModel.removeAll();
        while (true) {
            String[] strArr = this.villes;
            if (i < strArr.length) {
                defaultListModel.addItem(StringTools.capitalize(strArr[i]));
                i++;
            } else {
                button3.addActionListener(new 1(b3GUiManager));
                button.addActionListener(new 2(mapContainer, comboBox));
                button2.addActionListener(new 3(mapContainer, comboBox, b3GUiManager));
                container.add(mapContainer);
                return createContainer;
            }
        }
    }

    class 1 implements ActionListener {
        final /* synthetic */ B3GUiManager val$uiManager;

        1(B3GUiManager b3GUiManager) {
            this.val$uiManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$uiManager.stateMachine.showForm("LoginV2New", (Command) null);
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ MapContainer val$cnt;
        final /* synthetic */ ComboBox val$villesComboBox;

        2(MapContainer mapContainer, ComboBox comboBox) {
            this.val$cnt = mapContainer;
            this.val$villesComboBox = comboBox;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$cnt.clearMapLayers();
            new ArrayList();
            String str = (String) this.val$villesComboBox.getSelectedItem();
            ArrayList arrayList = new ArrayList();
            arrayList.add(LocaliserGabs.this.new Argument("action", "index"));
            arrayList.add(LocaliserGabs.this.new Argument("class", "gab"));
            arrayList.add(LocaliserGabs.this.new Argument("ville", str));
            ArrayList access$000 = LocaliserGabs.access$000(LocaliserGabs.this, arrayList);
            if (access$000.isEmpty()) {
                return;
            }
            for (int i = 0; i < access$000.size(); i++) {
                LocaliserGabs.access$100(LocaliserGabs.this, this.val$cnt, (Gab) access$000.get(i));
            }
            this.val$cnt.zoom(new Coord(Double.parseDouble(((Gab) access$000.get(0)).latitude.replace(',', ' ').trim()), Double.parseDouble(((Gab) access$000.get(0)).longitude.replace(',', ' ').trim())), 11);
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ MapContainer val$cnt;
        final /* synthetic */ B3GUiManager val$uiManager;
        final /* synthetic */ ComboBox val$villesComboBox;

        3(MapContainer mapContainer, ComboBox comboBox, B3GUiManager b3GUiManager) {
            this.val$cnt = mapContainer;
            this.val$villesComboBox = comboBox;
            this.val$uiManager = b3GUiManager;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Location currentLocationSync;
            Location currentLocationSync2;
            if (Display.getInstance().getLocationManager().isGPSDetectionSupported()) {
                if (Display.getInstance().getLocationManager().isGPSEnabled()) {
                    Dialog showInifiniteBlocking = new InfiniteProgress().showInifiniteBlocking();
                    try {
                        currentLocationSync2 = LocationManager.getLocationManager().getCurrentLocation();
                    } catch (IOException unused) {
                        currentLocationSync2 = LocationManager.getLocationManager().getCurrentLocationSync(30000L);
                    }
                    showInifiniteBlocking.dispose();
                    if (currentLocationSync2 != null) {
                        double latitude = currentLocationSync2.getLatitude();
                        double longitude = currentLocationSync2.getLongitude();
                        this.val$cnt.clearMapLayers();
                        new ArrayList();
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(LocaliserGabs.this.new Argument("action", "autourdemoi"));
                        arrayList.add(LocaliserGabs.this.new Argument("class", "gab"));
                        arrayList.add(LocaliserGabs.this.new Argument("latitude", latitude + ""));
                        arrayList.add(LocaliserGabs.this.new Argument("longitude", longitude + ""));
                        ArrayList access$000 = LocaliserGabs.access$000(LocaliserGabs.this, arrayList);
                        if (access$000.isEmpty()) {
                            return;
                        }
                        for (int i = 0; i < access$000.size(); i++) {
                            LocaliserGabs.access$100(LocaliserGabs.this, this.val$cnt, (Gab) access$000.get(i));
                        }
                        try {
                            this.val$cnt.addMarker(EncodedImage.create("/cursor.png"), new Coord(latitude, longitude), null, null, null);
                            this.val$cnt.zoom(new Coord(latitude, longitude), 15);
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    B3GUiManager b3GUiManager = this.val$uiManager;
                    b3GUiManager.ShowMessageTransaction(b3GUiManager.stateMachine, DataTools.FormatUnicode("Votre position actuelle est temporairement indisponible"), null);
                    return;
                }
                B3GUiManager b3GUiManager2 = this.val$uiManager;
                b3GUiManager2.ShowMessageTransaction(b3GUiManager2.stateMachine, DataTools.FormatUnicode("Veuillez activer le service de localisation GPS"), null);
                return;
            }
            Dialog showInifiniteBlocking2 = new InfiniteProgress().showInifiniteBlocking();
            try {
                currentLocationSync = LocationManager.getLocationManager().getCurrentLocation();
            } catch (IOException unused2) {
                currentLocationSync = LocationManager.getLocationManager().getCurrentLocationSync(30000L);
            }
            showInifiniteBlocking2.dispose();
            if (currentLocationSync != null) {
                double latitude2 = currentLocationSync.getLatitude();
                double longitude2 = currentLocationSync.getLongitude();
                this.val$cnt.clearMapLayers();
                new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(LocaliserGabs.this.new Argument("action", "autourdemoi"));
                arrayList2.add(LocaliserGabs.this.new Argument("class", "gab"));
                arrayList2.add(LocaliserGabs.this.new Argument("latitude", latitude2 + ""));
                arrayList2.add(LocaliserGabs.this.new Argument("longitude", longitude2 + ""));
                ArrayList access$0002 = LocaliserGabs.access$000(LocaliserGabs.this, arrayList2);
                if (access$0002.isEmpty()) {
                    return;
                }
                for (int i2 = 0; i2 < access$0002.size(); i2++) {
                    LocaliserGabs.access$100(LocaliserGabs.this, this.val$cnt, (Gab) access$0002.get(i2));
                }
                try {
                    this.val$cnt.addMarker(EncodedImage.create("/cursor.png"), new Coord(latitude2, longitude2), null, null, null);
                    this.val$cnt.zoom(new Coord(latitude2, longitude2), 15);
                    return;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            B3GUiManager b3GUiManager3 = this.val$uiManager;
            b3GUiManager3.ShowMessageTransaction(b3GUiManager3.stateMachine, DataTools.FormatUnicode("Votre position actuelle est temporairement indisponible"), null);
        }
    }

    class 4 extends GZConnectionRequest {
        4() {
        }

        protected void readUnzipedResponse(InputStream inputStream) throws IOException {
            Reader inputStreamReader = new InputStreamReader(inputStream);
            JSONParser jSONParser = new JSONParser();
            LocaliserGabs.this.response = jSONParser.parse(inputStreamReader);
            LocaliserGabs localiserGabs = LocaliserGabs.this;
            localiserGabs.SrvList = localiserGabs.GetAllServiceResponse(localiserGabs.response);
        }
    }

    private ArrayList getAllData(ArrayList arrayList) {
        try {
            4 r0 = new 4();
            InfiniteProgress infiniteProgress = new InfiniteProgress();
            infiniteProgress.setUIID("TransparentContainer");
            infiniteProgress.setAnimation(UIManager.getInstance().getThemeImageConstant("infiniteImage"));
            Dialog showInifiniteBlocking = infiniteProgress.showInifiniteBlocking();
            r0.setPost(false);
            r0.setHttpMethod("GET");
            r0.addRequestHeader("Accept", "application/json");
            r0.addRequestHeader("Accept-Encoding", "gzip, deflate");
            r0.setUrl("http://mycih.cih.co.ma/mycih/proxy.php");
            for (int i = 0; i < arrayList.size(); i++) {
                r0.addArgument(((Argument) arrayList.get(i)).key, ((Argument) arrayList.get(i)).value);
            }
            r0.setDisposeOnCompletion(showInifiniteBlocking);
            r0.setFailSilently(true);
            r0.setSilentRetryCount(2);
            r0.setTimeout(60000);
            NetworkManager.getInstance().addToQueueAndWait(r0);
            NetworkManager.getInstance().addErrorListener(new 5());
            return this.SrvList;
        } catch (Exception unused) {
            return this.SrvList;
        }
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            actionEvent.consume();
        }
    }

    public ArrayList GetAllServiceResponse(Hashtable hashtable) {
        Vector vector = (Vector) hashtable.get("response");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < vector.size(); i++) {
            Gab gab = new Gab();
            Hashtable hashtable2 = (Hashtable) vector.get(i);
            if (hashtable2.get("longitude") != null) {
                if (hashtable2.get("longitude").toString().equals("")) {
                    gab.longitude = "0";
                } else {
                    gab.longitude = hashtable2.get("longitude").toString().trim();
                }
            } else {
                gab.longitude = "0";
            }
            if (hashtable2.get("latitude") != null) {
                if (hashtable2.get("latitude").toString().equals("")) {
                    gab.latitude = "0";
                } else {
                    gab.latitude = hashtable2.get("latitude").toString().trim();
                }
            } else {
                gab.latitude = "0";
            }
            if (hashtable2.get("ville") != null) {
                gab.ville = hashtable2.get("ville").toString().trim();
            } else {
                gab.ville = "";
            }
            if (hashtable2.get("adresse") != null) {
                gab.adresse = hashtable2.get("adresse").toString().trim();
            } else {
                gab.adresse = "";
            }
            if (hashtable2.get("nom") != null) {
                gab.nom = hashtable2.get("nom").toString().trim();
            } else {
                gab.nom = "";
            }
            if (hashtable2.get("id") != null) {
                gab.id = hashtable2.get("id").toString().trim();
            } else {
                gab.id = "0";
            }
            arrayList.add(gab);
        }
        return arrayList;
    }

    private void createMarker(MapContainer mapContainer, Gab gab) {
        try {
            mapContainer.addMarker(EncodedImage.create("/gab_marker.png"), new Coord(Double.parseDouble(gab.latitude.replace(',', ' ').trim()), Double.parseDouble(gab.longitude.replace(',', ' ').trim())), null, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class Gab {
        public String adresse;
        public String id;
        public String latitude;
        public String longitude;
        public String nom;
        public String ville;

        public Gab(String str, String str2, String str3, String str4, String str5, String str6) {
            this.longitude = str;
            this.latitude = str2;
            this.ville = str3;
            this.adresse = str4;
            this.nom = str5;
            this.id = str6;
        }

        public Gab() {
        }
    }

    private class Argument {
        public String key;
        public String value;

        public Argument(String str, String str2) {
            this.key = str;
            this.value = str2;
        }
    }
}
