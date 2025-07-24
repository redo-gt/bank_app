package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.OvpService;
import com.b3g.services.ServiceManager;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OvpDetailPage extends B3GPage {
    UIBuilder uib = new UIBuilder();

    public OvpDetailPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        this.isNew = true;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0216 A[Catch: Exception -> 0x03ad, TryCatch #0 {Exception -> 0x03ad, blocks: (B:3:0x0041, B:4:0x005a, B:6:0x0062, B:9:0x009a, B:11:0x00a2, B:13:0x00c6, B:16:0x0105, B:19:0x010e, B:22:0x011a, B:23:0x0179, B:31:0x01f9, B:33:0x0216, B:44:0x025b, B:45:0x02aa, B:47:0x02ba, B:48:0x02c1, B:50:0x031a, B:53:0x0323, B:55:0x032b, B:56:0x0338, B:58:0x035d, B:59:0x0389, B:64:0x0373, B:65:0x0332, B:66:0x0267, B:67:0x0275, B:68:0x0283, B:69:0x0291, B:70:0x029f, B:71:0x021a, B:74:0x0224, B:77:0x022e, B:80:0x0238, B:83:0x0242, B:88:0x01ea, B:92:0x01b1, B:93:0x012f, B:95:0x0137, B:96:0x014e, B:97:0x0163, B:25:0x0188), top: B:2:0x0041, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x02ba A[Catch: Exception -> 0x03ad, TryCatch #0 {Exception -> 0x03ad, blocks: (B:3:0x0041, B:4:0x005a, B:6:0x0062, B:9:0x009a, B:11:0x00a2, B:13:0x00c6, B:16:0x0105, B:19:0x010e, B:22:0x011a, B:23:0x0179, B:31:0x01f9, B:33:0x0216, B:44:0x025b, B:45:0x02aa, B:47:0x02ba, B:48:0x02c1, B:50:0x031a, B:53:0x0323, B:55:0x032b, B:56:0x0338, B:58:0x035d, B:59:0x0389, B:64:0x0373, B:65:0x0332, B:66:0x0267, B:67:0x0275, B:68:0x0283, B:69:0x0291, B:70:0x029f, B:71:0x021a, B:74:0x0224, B:77:0x022e, B:80:0x0238, B:83:0x0242, B:88:0x01ea, B:92:0x01b1, B:93:0x012f, B:95:0x0137, B:96:0x014e, B:97:0x0163, B:25:0x0188), top: B:2:0x0041, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x035d A[Catch: Exception -> 0x03ad, TryCatch #0 {Exception -> 0x03ad, blocks: (B:3:0x0041, B:4:0x005a, B:6:0x0062, B:9:0x009a, B:11:0x00a2, B:13:0x00c6, B:16:0x0105, B:19:0x010e, B:22:0x011a, B:23:0x0179, B:31:0x01f9, B:33:0x0216, B:44:0x025b, B:45:0x02aa, B:47:0x02ba, B:48:0x02c1, B:50:0x031a, B:53:0x0323, B:55:0x032b, B:56:0x0338, B:58:0x035d, B:59:0x0389, B:64:0x0373, B:65:0x0332, B:66:0x0267, B:67:0x0275, B:68:0x0283, B:69:0x0291, B:70:0x029f, B:71:0x021a, B:74:0x0224, B:77:0x022e, B:80:0x0238, B:83:0x0242, B:88:0x01ea, B:92:0x01b1, B:93:0x012f, B:95:0x0137, B:96:0x014e, B:97:0x0163, B:25:0x0188), top: B:2:0x0041, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0373 A[Catch: Exception -> 0x03ad, TryCatch #0 {Exception -> 0x03ad, blocks: (B:3:0x0041, B:4:0x005a, B:6:0x0062, B:9:0x009a, B:11:0x00a2, B:13:0x00c6, B:16:0x0105, B:19:0x010e, B:22:0x011a, B:23:0x0179, B:31:0x01f9, B:33:0x0216, B:44:0x025b, B:45:0x02aa, B:47:0x02ba, B:48:0x02c1, B:50:0x031a, B:53:0x0323, B:55:0x032b, B:56:0x0338, B:58:0x035d, B:59:0x0389, B:64:0x0373, B:65:0x0332, B:66:0x0267, B:67:0x0275, B:68:0x0283, B:69:0x0291, B:70:0x029f, B:71:0x021a, B:74:0x0224, B:77:0x022e, B:80:0x0238, B:83:0x0242, B:88:0x01ea, B:92:0x01b1, B:93:0x012f, B:95:0x0137, B:96:0x014e, B:97:0x0163, B:25:0x0188), top: B:2:0x0041, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0332 A[Catch: Exception -> 0x03ad, TryCatch #0 {Exception -> 0x03ad, blocks: (B:3:0x0041, B:4:0x005a, B:6:0x0062, B:9:0x009a, B:11:0x00a2, B:13:0x00c6, B:16:0x0105, B:19:0x010e, B:22:0x011a, B:23:0x0179, B:31:0x01f9, B:33:0x0216, B:44:0x025b, B:45:0x02aa, B:47:0x02ba, B:48:0x02c1, B:50:0x031a, B:53:0x0323, B:55:0x032b, B:56:0x0338, B:58:0x035d, B:59:0x0389, B:64:0x0373, B:65:0x0332, B:66:0x0267, B:67:0x0275, B:68:0x0283, B:69:0x0291, B:70:0x029f, B:71:0x021a, B:74:0x0224, B:77:0x022e, B:80:0x0238, B:83:0x0242, B:88:0x01ea, B:92:0x01b1, B:93:0x012f, B:95:0x0137, B:96:0x014e, B:97:0x0163, B:25:0x0188), top: B:2:0x0041, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x029f A[Catch: Exception -> 0x03ad, TryCatch #0 {Exception -> 0x03ad, blocks: (B:3:0x0041, B:4:0x005a, B:6:0x0062, B:9:0x009a, B:11:0x00a2, B:13:0x00c6, B:16:0x0105, B:19:0x010e, B:22:0x011a, B:23:0x0179, B:31:0x01f9, B:33:0x0216, B:44:0x025b, B:45:0x02aa, B:47:0x02ba, B:48:0x02c1, B:50:0x031a, B:53:0x0323, B:55:0x032b, B:56:0x0338, B:58:0x035d, B:59:0x0389, B:64:0x0373, B:65:0x0332, B:66:0x0267, B:67:0x0275, B:68:0x0283, B:69:0x0291, B:70:0x029f, B:71:0x021a, B:74:0x0224, B:77:0x022e, B:80:0x0238, B:83:0x0242, B:88:0x01ea, B:92:0x01b1, B:93:0x012f, B:95:0x0137, B:96:0x014e, B:97:0x0163, B:25:0x0188), top: B:2:0x0041, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0242 A[Catch: Exception -> 0x03ad, TRY_LEAVE, TryCatch #0 {Exception -> 0x03ad, blocks: (B:3:0x0041, B:4:0x005a, B:6:0x0062, B:9:0x009a, B:11:0x00a2, B:13:0x00c6, B:16:0x0105, B:19:0x010e, B:22:0x011a, B:23:0x0179, B:31:0x01f9, B:33:0x0216, B:44:0x025b, B:45:0x02aa, B:47:0x02ba, B:48:0x02c1, B:50:0x031a, B:53:0x0323, B:55:0x032b, B:56:0x0338, B:58:0x035d, B:59:0x0389, B:64:0x0373, B:65:0x0332, B:66:0x0267, B:67:0x0275, B:68:0x0283, B:69:0x0291, B:70:0x029f, B:71:0x021a, B:74:0x0224, B:77:0x022e, B:80:0x0238, B:83:0x0242, B:88:0x01ea, B:92:0x01b1, B:93:0x012f, B:95:0x0137, B:96:0x014e, B:97:0x0163, B:25:0x0188), top: B:2:0x0041, inners: #3 }] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v43 */
    /* JADX WARN: Type inference failed for: r1v45 */
    /* JADX WARN: Type inference failed for: r1v47 */
    /* JADX WARN: Type inference failed for: r1v49 */
    /* JADX WARN: Type inference failed for: r1v51 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.codename1.ui.Container GetPageContainer() {
        /*
            Method dump skipped, instructions count: 966
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.OvpDetailPage.GetPageContainer():com.codename1.ui.Container");
    }

    class 1 implements ActionListener {
        final /* synthetic */ OvpService val$ovp;

        1(OvpService ovpService) {
            this.val$ovp = ovpService;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$ovp.Action = "1";
            OvpDetailPage.this.uiManager.NavigateToPage(new OvpAction(OvpDetailPage.this.uiManager, this.val$ovp, 56));
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ OvpService val$ovp;

        2(OvpService ovpService) {
            this.val$ovp = ovpService;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$ovp.Action = "2";
            OvpDetailPage.this.uiManager.NavigateToPage(new OvpAction(OvpDetailPage.this.uiManager, this.val$ovp, 56));
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ OvpService val$ovp;

        3(OvpService ovpService) {
            this.val$ovp = ovpService;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$ovp.Action = "3";
            OvpDetailPage.this.uiManager.NavigateToPage(new OvpAction(OvpDetailPage.this.uiManager, this.val$ovp, 56));
        }
    }

    public ServiceResponse ArreterProcess(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("ContractNumber", str);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(10703);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse SuspendreProcess(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("ContractNumber", str);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(10701);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse ReprendreProcess(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("ContractNumber", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(10702);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }
}
