package com.b3g;

import com.codename1.ui.Form;
import com.codename1.ui.Display;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

public class MainApp {
    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme.res");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (Display.getInstance().getCurrent() != null) {
            Display.getInstance().getCurrent().show();
            return;
        }
        Form hi = new Form("Hello Codename One");
        hi.show();
    }

    public void stop() {
        // Called when app is paused
    }

    public void destroy() {
        // Called when app is destroyed
    }
}
