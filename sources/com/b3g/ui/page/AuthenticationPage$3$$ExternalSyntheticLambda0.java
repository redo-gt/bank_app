package com.b3g.ui.page;

import com.b3g.tools.TouchIDNativeCall;
import com.b3g.ui.page.AuthenticationPage;
import com.codename1.util.SuccessCallback;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public final /* synthetic */ class AuthenticationPage$3$$ExternalSyntheticLambda0 implements SuccessCallback {
    public final /* synthetic */ AuthenticationPage.3 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TouchIDNativeCall f$2;

    public /* synthetic */ AuthenticationPage$3$$ExternalSyntheticLambda0(AuthenticationPage.3 r1, String str, TouchIDNativeCall touchIDNativeCall) {
        this.f$0 = r1;
        this.f$1 = str;
        this.f$2 = touchIDNativeCall;
    }

    public final void onSucess(Object obj) {
        this.f$0.lambda$actionPerformed$0$com-b3g-ui-page-AuthenticationPage$3(this.f$1, this.f$2, obj);
    }
}
