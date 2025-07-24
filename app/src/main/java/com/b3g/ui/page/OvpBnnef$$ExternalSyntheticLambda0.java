package com.b3g.ui.page;

import com.b3g.services.TransfertDATA;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.events.DataChangedListener;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public final /* synthetic */ class OvpBnnef$$ExternalSyntheticLambda0 implements DataChangedListener {
    public final /* synthetic */ TransfertDATA f$0;
    public final /* synthetic */ Container f$1;
    public final /* synthetic */ TextField f$2;

    public /* synthetic */ OvpBnnef$$ExternalSyntheticLambda0(TransfertDATA transfertDATA, Container container, TextField textField) {
        this.f$0 = transfertDATA;
        this.f$1 = container;
        this.f$2 = textField;
    }

    public final void dataChanged(int i, int i2) {
        OvpBnnef.lambda$GetPageContainer$0(this.f$0, this.f$1, this.f$2, i, i2);
    }
}
