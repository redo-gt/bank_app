package com.b3g.ui;

import com.codename1.ui.Container;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Tab {
    private StateMachine cndUi;

    public Tab(StateMachine stateMachine) {
        setCndUi(stateMachine);
    }

    public StateMachine getCndUi() {
        return this.cndUi;
    }

    public void setCndUi(StateMachine stateMachine) {
        this.cndUi = stateMachine;
    }

    public void DrawTabs(Container container, Object obj, int i, String str, String str2, Object obj2, int i2) {
        try {
            new TabV2(this.cndUi).DrawTabs(container, obj, i, str, str2, obj2, i2);
        } catch (Exception unused) {
        }
    }
}
