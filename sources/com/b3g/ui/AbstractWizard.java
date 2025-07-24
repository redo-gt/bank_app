package com.b3g.ui;

import com.codename1.ui.Container;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public abstract class AbstractWizard extends Container {
    public Container footerCnt;
    public Container header;
    public boolean isSinglePage;
    public Container stepsCnt;
    public ArrayList stepsList;
    public int templateColor;
    public Container wizardCnt;

    public abstract Container drawHeader(String str, int i, int i2);

    public abstract Container drawWizard(String str, int i, int i2);
}
