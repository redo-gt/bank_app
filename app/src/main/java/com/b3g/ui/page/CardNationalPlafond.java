package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.services.CardPlafond;
import com.b3g.services.ServiceManager;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.SliderPlafond;
import com.codename1.ui.Container;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.StringUtil;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CardNationalPlafond extends B3GPage {
    String BtnDureeChgmt1;
    String BtnPlafondCommerce1;
    String BtnPlafondCommerceRes;
    String BtnPlafondPaiement1;
    String BtnPlafondPaiementRes;
    String BtnPlafondRetr1;
    String BtnPlafondRetrRes;
    String DureeChgmt;
    String DureeInitial;
    String DureeTemp;
    String IsDisabledCom;
    String IsDisabledDuree;
    String IsDisabledPaim;
    String IsDisabledRtr;
    String IsRemovedCardLimitsCom;
    String IsRemovedCardLimitsPaiem;
    String IsRemovedCardLimitsRtr;
    String PlfdComTemp;
    String PlfdPaimTemp;
    String PlfdRetrTemp;
    SliderPlafond SliderCommerce;
    SliderPlafond SliderDuree;
    SliderPlafond SliderPaiement;
    SliderPlafond SliderRetrait;
    String codeStatus;
    String comChgmt;
    Card crd;
    Container ctnNext;
    String paimChgmt;
    String retrChgmt;
    public int selectedTabs;
    String statusLbl;
    UIBuilder uib = new UIBuilder();
    String actionRtr = "ACTIVER";
    String actionPaiem = "ACTIVER";
    String actionCom = "ACTIVER";
    String actionDuree = "LIMITER";
    StringTools stringTools = new StringTools();
    boolean flagRet = false;
    final Container rsltCnt = new Container();

    public CardNationalPlafond(Object obj, Object obj2, int i, Container container) {
        this.selectedTabs = 0;
        this.selectedTabs = i;
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = 0;
        this.ctnNext = container;
        CihMBanking.exitApplication = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:164:0x15f4  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x1602  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x160d  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x15fb  */
    /* JADX WARN: Type inference failed for: r4v101 */
    /* JADX WARN: Type inference failed for: r4v98 */
    /* JADX WARN: Type inference failed for: r4v99, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.codename1.ui.Container GetPageContainer() {
        /*
            Method dump skipped, instructions count: 5669
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.CardNationalPlafond.GetPageContainer():com.codename1.ui.Container");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02a6  */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.codename1.ui.Container getCardPlafondResumCnt() {
        /*
            Method dump skipped, instructions count: 3086
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.CardNationalPlafond.getCardPlafondResumCnt():com.codename1.ui.Container");
    }

    public String getDureeInitial(double d) {
        Double valueOf = Double.valueOf(d);
        int intValue = valueOf.intValue();
        String str = intValue != 1 ? intValue != 7 ? intValue != 15 ? intValue != 30 ? intValue != 90 ? intValue != 180 ? intValue != 365 ? "" : "12 Mois" : "6 Mois" : "3 Mois" : "1 Mois" : "15 Jours" : "7 Jours" : "1 Jour";
        if (valueOf.intValue() < 7 && valueOf.intValue() > 1) {
            return Integer.toString(valueOf.intValue()) + " Jours";
        }
        if (valueOf.intValue() < 15 && valueOf.intValue() > 7) {
            return Integer.toString(valueOf.intValue()) + " Jours";
        }
        if (valueOf.intValue() < 30 && valueOf.intValue() > 15) {
            return Integer.toString(valueOf.intValue()) + " Jours";
        }
        if (valueOf.intValue() < 90 && valueOf.intValue() > 30) {
            return Integer.toString(valueOf.intValue()) + " Jours";
        }
        if (valueOf.intValue() < 180 && valueOf.intValue() > 90) {
            return Integer.toString(valueOf.intValue()) + " Jours";
        }
        if (valueOf.intValue() >= 365 || valueOf.intValue() <= 180) {
            return valueOf.intValue() <= 0 ? "Illimitée" : str;
        }
        return Integer.toString(valueOf.intValue()) + " Jours";
    }

    public Card CardPlafondProcess(String str, String str2) {
        Card card = (Card) this.service;
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSION_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", str);
        hashtable.put("CARDNUMBER", card.plainCardNumber);
        hashtable.put("ISRETABLIRCLICK", "false");
        hashtable.put("FLAG", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(16);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return FillCardArrayDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public Card CardPlafondInitialProcess(String str) {
        Card card = (Card) this.service;
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSION_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", str);
        hashtable.put("CARDNUMBER", card.plainCardNumber);
        hashtable.put("ISRETABLIRCLICK", "true");
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(16);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return FillResetCardArrayDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public Card FillPlafondCardArrayDataFromServiceResponse(Hashtable hashtable) {
        Card card = new Card();
        card.CanDesactivate = hashtable.get("CanDesactivate").toString();
        card.CanRemoveCardLimits = hashtable.get("CanRemoveCardLimits").toString();
        card.EndDate = hashtable.get("EndDate").toString();
        card.HasUnlimitedDate = hashtable.get("HasUnlimitedDate").toString();
        card.Maximum = (Double) hashtable.get("Maximum");
        card.Minimum = (Double) hashtable.get("Minimum");
        card.productStep = (Double) hashtable.get("ProductStep");
        Vector vector = (Vector) hashtable.get("CardPlafondList");
        if (vector.size() > 0) {
            card.CardPlafondList = CardPlafond.FillAccountCardPlafondDataFromServiceResponse(vector);
        } else {
            card.CardPlafondList = new ArrayList();
        }
        return card;
    }

    public Card FillCardArrayDataFromServiceResponse(ServiceResponse serviceResponse) {
        Card card = new Card();
        if (serviceResponse.getStatusCode().equals("000")) {
            return FillPlafondCardArrayDataFromServiceResponse(serviceResponse.getParamsOut());
        }
        if (!serviceResponse.getStatusCode().equals("312")) {
            return card;
        }
        this.codeStatus = serviceResponse.getStatusCode();
        this.statusLbl = serviceResponse.getStatusLabel();
        return card;
    }

    public Card FillResetCardArrayDataFromServiceResponse(ServiceResponse serviceResponse) {
        Card card = new Card();
        if (serviceResponse.getStatusCode().equals("000")) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Vos plafonds ont été réinitialisés avec succès", null);
            return FillPlafondCardArrayDataFromServiceResponse(serviceResponse.getParamsOut());
        }
        if (!serviceResponse.getStatusCode().equals("312")) {
            return card;
        }
        this.codeStatus = serviceResponse.getStatusCode();
        this.statusLbl = serviceResponse.getStatusLabel();
        return card;
    }

    public String formatNumber(int i, String str) {
        int length = String.valueOf(i).length();
        if (length > 3) {
            int i2 = length % 3;
            String substring = String.valueOf(i).substring(i2, length);
            ArrayList arrayList = new ArrayList();
            if (substring.length() == 3 || substring.length() < 3) {
                arrayList.add(substring);
            } else {
                for (int i3 = 2; i3 < substring.length(); i3 += 3) {
                    arrayList.add(substring.substring(i3 - 2, i3 + 1));
                }
            }
            String str2 = String.valueOf(i).substring(0, i2) + " ";
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                str2 = str2 + ((String) arrayList.get(i4)) + " ";
            }
            return str2.trim();
        }
        return String.valueOf(i);
    }

    public int formatNumber(String str, String str2) {
        try {
            Integer.parseInt(replace(str, str2, ""));
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return Integer.parseInt(StringUtil.replaceAll(str, str2, ""));
    }

    public String replace(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return str;
        }
        while (indexOf != -1) {
            sb.append(str.substring(0, indexOf));
            sb.append(str3);
            str = str.substring(indexOf + str2.length());
            indexOf = str.indexOf(str2);
        }
        sb.append(str);
        return sb.toString();
    }
}
