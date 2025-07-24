package com.b3g.ui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3gFloatingActionButton extends Button {
    private static float fabDefaultSize = 3.8f;
    private Dialog current;
    private boolean isBadge;
    private boolean rectangle;
    private int shadowOpacity;
    private float sizeMm;
    private List subMenu;
    private String text;

    public static float getIconDefaultSize() {
        return fabDefaultSize;
    }

    public static void setIconDefaultSize(float f) {
        fabDefaultSize = f;
    }

    protected B3gFloatingActionButton(Image image, String str, float f) {
        this.shadowOpacity = 100;
        this.sizeMm = f;
        setIcon(image);
        setText("");
        this.text = str;
        setUIID("B3gFloatingActionButton");
        getAllStyles().setAlignment(4);
        updateBorder();
    }

    private B3gFloatingActionButton(String str) {
        this.shadowOpacity = 100;
        this.sizeMm = fabDefaultSize;
        super.setText(str);
        this.rectangle = true;
        this.shadowOpacity = 0;
        setUIID("Badge");
        updateBorder();
        this.isBadge = true;
    }

    private void updateBorder() {
        getUnselectedStyle().setBorder(RoundBorder.create().color(getUnselectedStyle().getBgColor()).shadowOpacity(this.shadowOpacity).rectangle(this.rectangle));
        getSelectedStyle().setBorder(RoundBorder.create().color(getSelectedStyle().getBgColor()).shadowOpacity(this.shadowOpacity).rectangle(this.rectangle));
        getPressedStyle().setBorder(RoundBorder.create().color(getPressedStyle().getBgColor()).shadowOpacity(this.shadowOpacity).rectangle(this.rectangle));
    }

    public void styleChanged(String str, Style style) {
        if (str.equals("bgColor")) {
            updateBorder();
        }
        if ((getIcon() instanceof FontImage) && str.equals("fgColor")) {
            setIcon(FontImage.createMaterial(((FontImage) getIcon()).getText().charAt(0), "B3gFloatingActionButton", this.sizeMm));
        }
    }

    public static B3gFloatingActionButton createBadge(String str) {
        return new B3gFloatingActionButton(str);
    }

    public static B3gFloatingActionButton createFAB(Image image) {
        return new B3gFloatingActionButton(image, null, fabDefaultSize);
    }

    public B3gFloatingActionButton createSubFAB(Image image, String str) {
        B3gFloatingActionButton b3gFloatingActionButton = new B3gFloatingActionButton(image, str, 2.8f);
        if (this.subMenu == null) {
            this.subMenu = new ArrayList();
        }
        this.subMenu.add(b3gFloatingActionButton);
        return b3gFloatingActionButton;
    }

    protected Dimension calcPreferredSize() {
        if (getIcon() != null) {
            return new Dimension((getIcon().getWidth() * 11) / 4, (getIcon().getHeight() * 11) / 4);
        }
        return super.calcPreferredSize();
    }

    public Container bindFabToContainer(Component component) {
        return bindFabToContainer(component, 3, 2);
    }

    public Container bindFabToContainer(Component component, int i, int i2) {
        FlowLayout flowLayout = new FlowLayout(i);
        flowLayout.setValign(i2);
        Form componentForm = component.getComponentForm();
        if (componentForm != null && (componentForm.getContentPane() == component || componentForm == component)) {
            Container layeredPane = componentForm.getLayeredPane(getClass(), true);
            layeredPane.setLayout(flowLayout);
            layeredPane.add(this);
            return null;
        }
        Container container = new Container(flowLayout);
        container.add(this);
        return LayeredLayout.encloseIn(component, container);
    }

    public void setText(String str) {
        if (this.isBadge) {
            super.setText(str);
        }
        this.text = str;
    }

    protected void fireActionEvent(int i, int i2) {
        Form current = Display.getInstance().getCurrent();
        if (current instanceof Dialog) {
            ((Dialog) current).dispose();
        }
        super.fireActionEvent(i, i2);
    }

    public void released(int i, int i2) {
        super.released(i, i2);
        Dialog dialog = this.current;
        if (dialog != null) {
            dialog.dispose();
            this.current = null;
        }
        List list = this.subMenu;
        if (list != null) {
            Container createPopupContent = createPopupContent(list);
            Dialog dialog2 = new Dialog();
            dialog2.setDialogUIID("Container");
            dialog2.getContentPane().setUIID("Container");
            dialog2.setLayout(new BorderLayout());
            dialog2.add("Center", createPopupContent);
            Iterator it = this.subMenu.iterator();
            while (it.hasNext()) {
                ((B3gFloatingActionButton) it.next()).current = dialog2;
            }
            dialog2.setTransitionInAnimator(CommonTransitions.createEmpty());
            dialog2.setTransitionOutAnimator(CommonTransitions.createEmpty());
            Iterator it2 = createPopupContent.iterator();
            while (it2.hasNext()) {
                ((Component) it2.next()).setVisible(false);
            }
            Form componentForm = getComponentForm();
            int tintColor = componentForm.getTintColor();
            componentForm.setTintColor(0);
            dialog2.setBlurBackgroundRadius(-1.0f);
            dialog2.addShowListener(new 1(createPopupContent));
            showPopupDialog(dialog2);
            componentForm.setTintColor(tintColor);
            Iterator it3 = this.subMenu.iterator();
            while (it3.hasNext()) {
                ((B3gFloatingActionButton) it3.next()).remove();
            }
            createPopupContent.removeAll();
        }
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$con;

        1(Container container) {
            this.val$con = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Iterator it = this.val$con.iterator();
            while (it.hasNext()) {
                Component component = (Component) it.next();
                component.setY(this.val$con.getHeight());
                component.setVisible(true);
            }
            this.val$con.animateLayout(200);
        }
    }

    protected Container createPopupContent(List list) {
        Container container = new Container(new BoxLayout(2));
        for (B3gFloatingActionButton b3gFloatingActionButton : this.subMenu) {
            b3gFloatingActionButton.setPreferredW(getWidth());
            Container container2 = new Container(new BorderLayout());
            Label label = new Label(b3gFloatingActionButton.text);
            label.setUIID("FloatingActionText");
            container2.add("Center", FlowLayout.encloseRight(label));
            container2.add("East", b3gFloatingActionButton);
            container.add(container2);
        }
        return container;
    }

    protected void showPopupDialog(Dialog dialog) {
        dialog.setPopupDirectionBiasPortrait(Boolean.TRUE);
        dialog.showPopupDialog(this);
    }
}
