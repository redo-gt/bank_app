package com.b3g.ui.page.cih.Module;

import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.LoginAuthentication;
import com.codename1.util.SuccessCallback;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public final /* synthetic */ class LoginAuthentication$6$$ExternalSyntheticLambda0 implements SuccessCallback {
    public final /* synthetic */ LoginAuthentication.6 f$0;
    public final /* synthetic */ B3GUiManager f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TouchIDNativeCall f$3;

    public /* synthetic */ LoginAuthentication$6$$ExternalSyntheticLambda0(LoginAuthentication.6 r1, B3GUiManager b3GUiManager, String str, TouchIDNativeCall touchIDNativeCall) {
        this.f$0 = r1;
        this.f$1 = b3GUiManager;
        this.f$2 = str;
        this.f$3 = touchIDNativeCall;
    }

    public final void onSucess(Object obj) {
        this.f$0.lambda$actionPerformed$0$com-b3g-ui-page-cih-Module-LoginAuthentication$6(this.f$1, this.f$2, this.f$3, obj);
    }
}
