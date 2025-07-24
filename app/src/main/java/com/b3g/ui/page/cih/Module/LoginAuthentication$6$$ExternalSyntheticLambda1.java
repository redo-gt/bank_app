package com.b3g.ui.page.cih.Module;

import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.page.cih.Module.LoginAuthentication;
import com.codename1.util.FailureCallback;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public final /* synthetic */ class LoginAuthentication$6$$ExternalSyntheticLambda1 implements FailureCallback {
    public final /* synthetic */ TouchIDNativeCall f$0;

    public /* synthetic */ LoginAuthentication$6$$ExternalSyntheticLambda1(TouchIDNativeCall touchIDNativeCall) {
        this.f$0 = touchIDNativeCall;
    }

    public final void onError(Object obj, Throwable th, int i, String str) {
        LoginAuthentication.6.lambda$actionPerformed$1(this.f$0, obj, th, i, str);
    }
}
