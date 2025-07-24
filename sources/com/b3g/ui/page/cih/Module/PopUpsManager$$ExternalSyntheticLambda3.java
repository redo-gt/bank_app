package com.b3g.ui.page.cih.Module;

import com.b3g.tools.TouchIDNativeCall;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public final /* synthetic */ class PopUpsManager$$ExternalSyntheticLambda3 implements ActionListener {
    public final /* synthetic */ PopUpsManager f$0;
    public final /* synthetic */ Dialog f$1;
    public final /* synthetic */ TouchIDNativeCall f$2;

    public /* synthetic */ PopUpsManager$$ExternalSyntheticLambda3(PopUpsManager popUpsManager, Dialog dialog, TouchIDNativeCall touchIDNativeCall) {
        this.f$0 = popUpsManager;
        this.f$1 = dialog;
        this.f$2 = touchIDNativeCall;
    }

    public final void actionPerformed(ActionEvent actionEvent) {
        this.f$0.lambda$showFingerPrintPopUp$0$com-b3g-ui-page-cih-Module-PopUpsManager(this.f$1, this.f$2, actionEvent);
    }
}
