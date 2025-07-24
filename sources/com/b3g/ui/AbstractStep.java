package com.b3g.ui;

import com.b3g.services.B3gService;
import com.codename1.ui.Container;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public abstract class AbstractStep extends Container {
    public String bgIcon;
    public String icon;
    public int id;
    public boolean isCurrentStep;
    public boolean isSelected;
    public AbstractStep nextStep;
    public AbstractStep previousStep;
    public String selectedIcon;
    public B3gService service;
    public String statusCode;
    public Container stepCnt;
    public int stepNumber;
    public String title;
    public int titleColor;

    public abstract Container drawStepCnt();

    public abstract Container drawStepHeaderCnt(boolean z);
}
