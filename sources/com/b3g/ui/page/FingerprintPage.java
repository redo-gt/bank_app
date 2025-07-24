package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.tools.DataTools;
import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.AccountLastConnection;
import com.b3g.ui.page.cih.Module.PopUpsManager;
import com.codename1.components.SpanLabel;
import com.codename1.fingerprint.Fingerprint;
import com.codename1.io.Storage;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import com.codename1.util.FailureCallback;
import com.codename1.util.SuccessCallback;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class FingerprintPage extends B3GPage {
    Vector lst;
    TouchIDNativeCall pTouchIDNativeCall;
    public B3GUiManager uiManager;
    UIBuilder uib = new UIBuilder();

    static /* synthetic */ void access$000(FingerprintPage fingerprintPage, Button button) {
        fingerprintPage.applySuccessFingerPrintAction(button);
    }

    public FingerprintPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        boolean z;
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        Container createContainer = this.uib.createContainer("/cihv3", "FingerPrintCnt");
        Button button = (Button) this.uib.findByName("activateTouchBtn", createContainer);
        Label label = (Label) this.uib.findByName("fingerPrintTitle", createContainer);
        SpanLabel spanLabel = (SpanLabel) this.uib.findByName("touchIdMsg2", createContainer);
        SpanLabel spanLabel2 = (SpanLabel) this.uib.findByName("touchIdMsg3", createContainer);
        SpanLabel spanLabel3 = (SpanLabel) this.uib.findByName("touchIdMsg4", createContainer);
        spanLabel3.setHidden(true);
        this.thisContainer.add(createContainer);
        this.pTouchIDNativeCall = (TouchIDNativeCall) NativeLookup.create(TouchIDNativeCall.class);
        this.lst = new Vector();
        if (!Display.getInstance().isSimulator()) {
            if (this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                button.setText("Activer Biométrie");
            } else if (this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
                button.setText("Activer Touch ID");
            } else if (this.pTouchIDNativeCall.getBiometryType().equals("FACEID")) {
                button.setText("Activer Face ID");
            }
        }
        if (Storage.getInstance().exists("veclistCnxStorage")) {
            this.lst = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
        }
        int i = 0;
        while (true) {
            if (i >= this.lst.size()) {
                z = false;
                break;
            }
            if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical.equals(((AccountLastConnection) this.lst.elementAt(i)).radical)) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            button.setIcon(this.uiManager.ressource.getImage("radio_on.png"));
            if (!Display.getInstance().isSimulator()) {
                if (this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                    button.setText("Désactiver Biométrie");
                } else if (this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
                    button.setText("Désactiver Touch ID");
                } else if (this.pTouchIDNativeCall.getBiometryType().equals("FACEID")) {
                    button.setText("Désactiver Face ID");
                }
            }
        }
        if (!Display.getInstance().isSimulator()) {
            if (this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                spanLabel3.setHidden(false);
                label.setText("PARAMETRER Biométrie");
                spanLabel.setText("Connectez-vous à votre espace client par votre Biométrie.");
                spanLabel2.setText("Attention !! N’activez la sécurité par Biométrie que sur votre téléphone personnel.");
                spanLabel3.setText("Les deux fonctionnalités: Fingerprint et Face ID seront activées en même temps.");
            } else if (this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
                spanLabel3.setHidden(true);
                label.setText("PARAMETRER TOUCH ID");
                spanLabel.setText("Connectez vous à votre espace client en utilisant votre Touch ID.");
                spanLabel2.setText("Attention !! N’activez la sécurité par empreinte digitale que sur votre téléphone personnel.");
            } else if (this.pTouchIDNativeCall.getBiometryType().equals("FACEID")) {
                spanLabel3.setHidden(true);
                label.setText("PARAMETRER FACE ID");
                spanLabel.setText("Connectez vous à votre espace client en utilisant votre Face ID.");
                spanLabel2.setText("Attention !! N’activez la sécurité par Face ID que sur votre téléphone personnel.");
            }
        }
        button.addActionListener(new 1(button, spanLabel3));
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        private SuccessCallback onSuccess;
        final /* synthetic */ Button val$activateTouchBtn;
        final /* synthetic */ SpanLabel val$touchIdMsg4;

        1(Button button, SpanLabel spanLabel) {
            this.val$activateTouchBtn = button;
            this.val$touchIdMsg4 = spanLabel;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            boolean z;
            String msgActivationFaceID;
            FingerprintPage.this.lst = new Vector();
            FingerprintPage.this.lst = (Vector) Storage.getInstance().readObject("veclistCnxStorage");
            int i = 0;
            while (true) {
                if (i >= FingerprintPage.this.lst.size()) {
                    z = false;
                    break;
                }
                if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical.equals(((AccountLastConnection) FingerprintPage.this.lst.elementAt(i)).radical)) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                if (Display.getInstance().isSimulator()) {
                    return;
                }
                if (FingerprintPage.this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
                    msgActivationFaceID = "Activez la Biométrie en appliquant votre Fingerprint ou Face ID";
                } else if (FingerprintPage.this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
                    msgActivationFaceID = DataTools.getMsgActivationTouchID();
                } else {
                    msgActivationFaceID = FingerprintPage.this.pTouchIDNativeCall.getBiometryType().equals("FACEID") ? DataTools.getMsgActivationFaceID() : "";
                }
                Fingerprint.scanFingerprint(DataTools.getAvailabeBiometric(FingerprintPage.this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID"), FingerprintPage.this.pTouchIDNativeCall.getBiometryType().equals("FACEID"), FingerprintPage.this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")), msgActivationFaceID, DataTools.getNamCancelBtn(), new FingerprintPage$1$$ExternalSyntheticLambda0(this, this.val$activateTouchBtn), new 1(), false);
                FingerprintPage.this.thisContainer.revalidate();
                return;
            }
            new UITimer(new FingerprintPage$1$$ExternalSyntheticLambda1(this, this.val$touchIdMsg4, this.val$activateTouchBtn)).schedule(300, false, FingerprintPage.this.uiManager.currentForm);
            FingerprintPage.this.thisContainer.revalidate();
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-FingerprintPage$1(Button button, Object obj) {
            FingerprintPage.access$000(FingerprintPage.this, button);
        }

        class 1 implements FailureCallback {
            public void onError(Object obj, Throwable th, int i, String str) {
            }

            1() {
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x004d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-FingerprintPage$1(com.codename1.components.SpanLabel r4, com.codename1.ui.Button r5) {
            /*
                r3 = this;
                com.codename1.ui.Display r0 = com.codename1.ui.Display.getInstance()
                boolean r0 = r0.isSimulator()
                if (r0 != 0) goto L4d
                r0 = 1
                r4.setHidden(r0)
                com.b3g.ui.page.FingerprintPage r4 = com.b3g.ui.page.FingerprintPage.this
                com.b3g.tools.TouchIDNativeCall r4 = r4.pTouchIDNativeCall
                java.lang.String r4 = r4.getBiometryType()
                java.lang.String r0 = "FINGERPRINT"
                boolean r4 = r4.equals(r0)
                if (r4 == 0) goto L23
                java.lang.String r4 = "Introduisez votre mot de passe pour confirmer la désactivation de l’utilisation de votre Biométrie"
                java.lang.String r0 = "Votre Biométrie est désormée désactivée"
                goto L50
            L23:
                com.b3g.ui.page.FingerprintPage r4 = com.b3g.ui.page.FingerprintPage.this
                com.b3g.tools.TouchIDNativeCall r4 = r4.pTouchIDNativeCall
                java.lang.String r4 = r4.getBiometryType()
                java.lang.String r0 = "TOUCHID"
                boolean r4 = r4.equals(r0)
                if (r4 == 0) goto L38
                java.lang.String r4 = "Introduisez votre mot de passe pour confirmer la désactivation de l’utilisation de votre Touch ID"
                java.lang.String r0 = "Votre Touch ID est désormée désactivée"
                goto L50
            L38:
                com.b3g.ui.page.FingerprintPage r4 = com.b3g.ui.page.FingerprintPage.this
                com.b3g.tools.TouchIDNativeCall r4 = r4.pTouchIDNativeCall
                java.lang.String r4 = r4.getBiometryType()
                java.lang.String r0 = "FACEID"
                boolean r4 = r4.equals(r0)
                if (r4 == 0) goto L4d
                java.lang.String r4 = "Introduisez votre mot de passe pour confirmer la désactivation de l’utilisation de votre Face ID"
                java.lang.String r0 = "Votre Face ID est désormée désactivée"
                goto L50
            L4d:
                java.lang.String r4 = ""
                r0 = r4
            L50:
                com.b3g.ui.page.cih.Module.PopUpsManager r1 = new com.b3g.ui.page.cih.Module.PopUpsManager
                com.b3g.ui.page.FingerprintPage r2 = com.b3g.ui.page.FingerprintPage.this
                com.b3g.ui.B3GUiManager r2 = r2.uiManager
                r1.<init>(r2)
                r2 = 0
                r1.showPasswordPopUp(r5, r4, r0, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.FingerprintPage.1.lambda$actionPerformed$1$com-b3g-ui-page-FingerprintPage$1(com.codename1.components.SpanLabel, com.codename1.ui.Button):void");
        }
    }

    private void applySuccessFingerPrintAction(Button button) {
        new UITimer(new FingerprintPage$$ExternalSyntheticLambda0(this, button)).schedule(500, false, this.uiManager.currentForm);
    }

    /* synthetic */ void lambda$applySuccessFingerPrintAction$0$com-b3g-ui-page-FingerprintPage(Button button) {
        String str;
        String str2;
        PopUpsManager popUpsManager = new PopUpsManager(this.uiManager);
        if (this.pTouchIDNativeCall.getBiometryType().equals("FINGERPRINT")) {
            str = "Introduisez votre code d’accès Cih Mobile pour confirmer l’activation de la connexion par Biométrie";
            str2 = "La connexion par Biométrie est maintenant activée";
        } else if (this.pTouchIDNativeCall.getBiometryType().equals("TOUCHID")) {
            str = "Introduisez votre mot de passe pour confirmer l’activation de l’utilisation de votre Touch ID";
            str2 = "Votre Touch ID est désormée activée";
        } else if (this.pTouchIDNativeCall.getBiometryType().equals("FACEID")) {
            str = "Introduisez votre mot de passe pour confirmer l’activation de l’utilisation de votre Face ID";
            str2 = "Votre Face ID est désormée activée";
        } else {
            str = "";
            str2 = "";
        }
        popUpsManager.showPasswordPopUp(button, str, str2, true);
    }
}
