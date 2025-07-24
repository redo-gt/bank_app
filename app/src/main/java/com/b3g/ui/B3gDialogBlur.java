package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.animations.Transition;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.UITimer;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3gDialogBlur {
    public Container container;
    public Dialog dialog;

    public B3gDialogBlur() {
        Dialog dialog = new Dialog();
        this.dialog = dialog;
        dialog.setName("dialog");
        this.dialog.setDialogUIID("Container");
        this.dialog.setLayout(new LayeredLayout());
        this.dialog.setScrollable(false);
        this.dialog.setDisposeWhenPointerOutOfBounds(true);
        Style dialogStyle = this.dialog.getDialogStyle();
        dialogStyle.setBorder(Border.createEmpty());
        dialogStyle.setBgTransparency(0);
    }

    public void show(String str, Container container, Transition transition, String str2) {
        Button button;
        this.dialog.setTitle(str);
        container.setPreferredW(Display.getInstance().getDisplayWidth());
        this.dialog.add(container);
        this.dialog.setDisposeWhenPointerOutOfBounds(true);
        Style dialogStyle = this.dialog.getDialogStyle();
        dialogStyle.setBorder(Border.createEmpty());
        dialogStyle.setBgTransparency(0);
        Container container2 = (Container) container.getComponentAt(1);
        if (str2.equals("5")) {
            button = (Button) ((Container) container2.getComponentAt(1)).getComponentAt(0);
        } else {
            button = (Button) container2.getComponentAt(0);
        }
        new UITimer(new 1(button)).schedule(100, false, this.dialog);
        this.dialog.setTransitionInAnimator(transition);
        this.dialog.showPacked("South", true);
        this.dialog.setTransitionOutAnimator(transition);
    }

    class 1 implements Runnable {
        final /* synthetic */ Button val$b;

        1(Button button) {
            this.val$b = button;
        }

        public void run() {
            this.val$b.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("iconeMenu.png").rotate(135));
            B3gDialogBlur.this.dialog.animateLayoutFadeAndWait(0, 0);
        }
    }

    public void close() {
        this.dialog.dispose();
    }

    public void setBlurBackground(float f) {
        this.dialog.setBlurBackgroundRadius(f);
    }
}
