package com.b3g.ui.page.cih.Module;

import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Style;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Settings {
    public static final int BLACK = 0;
    public static final int DARKGRAY = 2828586;
    private static final int WHITE = 16777215;

    public static void setNightMode(Component component, int i) {
        if (!isNightMode() || component == null) {
            return;
        }
        component.getAllStyles().setBgColor(i);
        component.getAllStyles().setFgColor(getNightColor(component.getUnselectedStyle().getFgColor()));
        if (component instanceof Container) {
            for (Component component2 : ((Container) component).getChildrenAsList(true)) {
                if (component2 instanceof Container) {
                    setNightMode((Container) component2, 0);
                } else if (component2 instanceof Button) {
                    updateColorsButton((Button) component2);
                } else if ((component2 instanceof Label) || (component2 instanceof SpanLabel)) {
                    updateColorsText(component2);
                } else if (component2 instanceof ComboBox) {
                    ((ComboBox) component2).getStyle().setFgColor(16777215);
                    component2.getAllStyles().setBgColor(2828587);
                    component2.getAllStyles().setFgColor(16777215);
                    component2.getAllStyles().setFgColor(16777215);
                } else {
                    updateColors(component2);
                }
            }
        }
    }

    private static void updateColors(Component component) {
        int fgColor = component.getAllStyles().getFgColor();
        int bgColor = component.getAllStyles().getBgColor();
        if (bgColor == 16777215) {
            component.getAllStyles().setBgColor(getNightColor(bgColor));
        }
        if (fgColor == 0) {
            component.getAllStyles().setFgColor(16777215);
        } else {
            component.getAllStyles().setFgColor(getNightColor(component.getUnselectedStyle().getFgColor()));
        }
    }

    private static void updateColorsText(Component component) {
        int fgColor = component.getUnselectedStyle().getFgColor();
        int fgColor2 = component.getSelectedStyle().getFgColor();
        if (fgColor == 0) {
            component.getUnselectedStyle().setFgColor(16777215);
            component.getPressedStyle().setBgColor(0);
        }
        if (fgColor2 == 0) {
            component.getSelectedStyle().setFgColor(2828586);
        }
    }

    private static void updateColorsButton(Button button) {
        int fgColor = button.getUnselectedStyle().getFgColor();
        int bgColor = button.getUnselectedStyle().getBgColor();
        int bgColor2 = button.getPressedStyle().getBgColor();
        if (button.getUIID() == null) {
            button.setUIID("Container");
            Style style = new Style();
            style.setBgColor(16777215);
            button.setPressedStyle(style);
            updateColorsButton(button);
        }
        button.getPressedStyle().setBgColor(0);
        if (bgColor == 16777215) {
            button.getUnselectedStyle().setBgColor(0);
            button.getPressedStyle().setBgColor(0);
        } else {
            button.getUnselectedStyle().setBgColor(2828586);
            button.getSelectedStyle().setBgColor(2828586);
            button.getPressedStyle().setBgColor(2828586);
        }
        if (bgColor2 == 16777215) {
            button.getPressedStyle().setBgColor(0);
            button.getSelectedStyle().setBgColor(0);
            button.getSelectedStyle().setFgColor(16777215);
            button.getPressedStyle().setFgColor(16777215);
        }
        if (fgColor == 0) {
            button.getUnselectedStyle().setFgColor(16777215);
        }
        button.getUnselectedStyle().getFgColor();
    }

    public int getDayNightColor(int i) {
        if (isNightMode()) {
            return getNightColor(i);
        }
        return getDayColor(i);
    }

    private static int getNightColor(int i) {
        int i2;
        int i3;
        if (isNightMode()) {
            int i4 = (i / 256) / 256;
            int i5 = i - (i4 * 65536);
            int i6 = i5 / 256;
            int i7 = i5 - (i6 * 256);
            if (i4 == i6 && i6 == i7) {
                if (i4 >= 127) {
                    i2 = ((i7 - 127) / 3) + (((i6 - 127) / 3) * 256);
                    i3 = (i4 - 127) / 3;
                } else {
                    i2 = i7 + 127 + ((i6 + 127) * 256);
                    i3 = i4 + 127;
                }
                return i2 + (i3 * 65536);
            }
        }
        return i;
    }

    public int getDayColor(int i) {
        int i2;
        int i3;
        if (!isNightMode()) {
            int i4 = (i / 256) / 256;
            int i5 = i - (i4 * 65536);
            int i6 = i5 / 256;
            int i7 = i5 - (i6 * 256);
            if (i4 == i6 && i6 == i7) {
                if (i4 < 127) {
                    i2 = (i7 * 3) + 127 + (((i6 * 3) + 127) * 256);
                    i3 = (i4 * 3) + 127;
                } else {
                    i2 = (i7 - 127) + ((i6 - 127) * 256);
                    i3 = i4 - 127;
                }
                return i2 + (i3 * 65536);
            }
        }
        return i;
    }

    public static boolean isNightMode() {
        return isNightModeState() && isDeviceNightMode();
    }

    public static boolean isNightModeState() {
        return Preferences.get("dark_state", false);
    }

    public static boolean isDeviceNightMode() {
        try {
            Display.getInstance().isDarkMode();
            return Display.getInstance().isDarkMode().booleanValue();
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean darkModeIsAvailable() {
        return ((SettingsNative) NativeLookup.create(SettingsNative.class)).darkModeIsAvailable();
    }
}
