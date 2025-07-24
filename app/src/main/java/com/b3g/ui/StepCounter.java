package com.b3g.ui;

import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class StepCounter {
    Image UnSelecteLigne;
    ArrayList UnSelectedIconList;
    int currentStep = 0;
    int nbStep = 0;
    Image selecteLigne;
    ArrayList selectedIconList;
    Container view;

    public StepCounter(ArrayList arrayList, ArrayList arrayList2, Image image, Image image2) {
        this.selectedIconList = arrayList;
        this.UnSelectedIconList = arrayList2;
        this.selecteLigne = image;
        this.UnSelecteLigne = image2;
    }

    public void init() {
        Container container = new Container(new BorderLayout());
        this.view = container;
        container.add("West", (Image) this.selectedIconList.get(0));
        this.view.add("East", (Image) this.UnSelectedIconList.get(0));
        this.view.add("Center", this.UnSelecteLigne);
    }

    public void setStep(int i) {
        this.view.add("West", (Image) this.selectedIconList.get(0));
        this.view.add("East", (Image) this.UnSelectedIconList.get(0));
        this.view.add("Center", this.UnSelecteLigne);
    }
}
