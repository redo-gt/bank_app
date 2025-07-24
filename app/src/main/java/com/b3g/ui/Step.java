package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Step extends AbstractStep {
    public Container drawStepCnt() {
        return null;
    }

    public Container drawStepHeaderCnt(boolean z) {
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "WizardStepIcon");
        Button button = (Button) uIBuilder.findByName("StepIcon", createContainer);
        if (this.isSelected) {
            button.setIcon(CihMBanking.theme.getImage(this.selectedIcon));
        } else {
            button.setIcon(CihMBanking.theme.getImage(this.icon));
        }
        Container container = (Container) uIBuilder.findByName("separator", createContainer);
        if (z) {
            container.removeAll();
        }
        createContainer.revalidate();
        return createContainer;
    }
}
