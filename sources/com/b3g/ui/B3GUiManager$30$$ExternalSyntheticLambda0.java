package com.b3g.ui;

import com.b3g.common.ServiceResponse;
import com.b3g.services.CashOutOperatoion;
import com.b3g.ui.B3GUiManager;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public final /* synthetic */ class B3GUiManager$30$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ CashOutOperatoion f$1;
    public final /* synthetic */ ServiceResponse f$2;

    public /* synthetic */ B3GUiManager$30$$ExternalSyntheticLambda0(int i, CashOutOperatoion cashOutOperatoion, ServiceResponse serviceResponse) {
        this.f$0 = i;
        this.f$1 = cashOutOperatoion;
        this.f$2 = serviceResponse;
    }

    public final void run() {
        B3GUiManager.30.lambda$actionPerformed$0(this.f$0, this.f$1, this.f$2);
    }
}
