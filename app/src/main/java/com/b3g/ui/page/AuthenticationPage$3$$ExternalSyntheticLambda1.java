package com.b3g.ui.page;

import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.page.AuthenticationPage;
import com.codename1.util.FailureCallback;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public final /* synthetic */ class AuthenticationPage$3$$ExternalSyntheticLambda1 implements FailureCallback {
    public final /* synthetic */ TouchIDNativeCall f$0;

    public /* synthetic */ AuthenticationPage$3$$ExternalSyntheticLambda1(TouchIDNativeCall touchIDNativeCall) {
        this.f$0 = touchIDNativeCall;
    }

    public final void onError(Object obj, Throwable th, int i, String str) {
        AuthenticationPage.3.lambda$actionPerformed$1(this.f$0, obj, th, i, str);
    }
}
