package com.b3g.ui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.animations.Transition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.EventDispatcher;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3gAccordionNw extends Container {
    private Button AccBtnClose;
    private Button AccBtnOpen;
    String a;
    private String arrowUIID;
    private Image closeIcon;
    private Image openIcon;
    private boolean autoClose = true;
    private final EventDispatcher listeners = new EventDispatcher();
    public boolean layoutChangGlb = false;
    public boolean closedGlb = true;

    public void getHeaderText() {
    }

    static /* synthetic */ Button access$100(B3gAccordionNw b3gAccordionNw) {
        return b3gAccordionNw.AccBtnClose;
    }

    static /* synthetic */ Button access$200(B3gAccordionNw b3gAccordionNw) {
        return b3gAccordionNw.AccBtnOpen;
    }

    static /* synthetic */ Image access$300(B3gAccordionNw b3gAccordionNw) {
        return b3gAccordionNw.closeIcon;
    }

    static /* synthetic */ boolean access$400(B3gAccordionNw b3gAccordionNw) {
        return b3gAccordionNw.autoClose;
    }

    static /* synthetic */ void access$500(B3gAccordionNw b3gAccordionNw, ActionEvent actionEvent) {
        b3gAccordionNw.fireEvent(actionEvent);
    }

    static /* synthetic */ Image access$600(B3gAccordionNw b3gAccordionNw) {
        return b3gAccordionNw.openIcon;
    }

    public B3gAccordionNw() {
        super.setLayout(new BoxLayout(2));
        this.closeIcon = FontImage.createMaterial((char) 58133, UIManager.getInstance().getComponentStyle("AccordionArrow"));
        this.openIcon = FontImage.createMaterial((char) 58131, UIManager.getInstance().getComponentStyle("AccordionArrow"));
        setScrollableY(true);
    }

    public String getArrowUIID() {
        return this.arrowUIID;
    }

    public void setArrowUIID(String str) {
        this.arrowUIID = str;
    }

    public void setLayoutChangGlb(boolean z) {
        this.layoutChangGlb = z;
    }

    public void addContent(String str, Component component) {
        addContent(new Label(str), component);
    }

    public void setCloseBtn(Button button) {
        this.AccBtnClose = button;
    }

    public void setOpenBtn(Button button) {
        this.AccBtnOpen = button;
    }

    public void setHeader(String str, Component component) {
        ((Label) ((AccordionContent) component.getParent()).header).setText(str);
    }

    public void setHeader(Component component, Component component2) {
        AccordionContent accordionContent = (AccordionContent) component2.getParent();
        accordionContent.header.getParent().replace(accordionContent.header, component, (Transition) null);
    }

    public void removeContent(Component component) {
        component.getParent().remove();
        component.remove();
    }

    public void addContent(Component component, Component component2) {
        add(new AccordionContent(component, component2));
    }

    public void addContentV2(Component component, Component component2) {
        component2.setName(component.getName());
        add(new AccordionContent(component, component2));
    }

    public Component getCurrentlyExpanded() {
        Iterator it = iterator();
        while (it.hasNext()) {
            AccordionContent accordionContent = (AccordionContent) ((Component) it.next());
            if (!accordionContent.isClosed()) {
                return AccordionContent.access$000(accordionContent);
            }
        }
        return null;
    }

    public String getCurrentNameComp() {
        Iterator it = iterator();
        if (it.hasNext()) {
            return AccordionContent.access$000((AccordionContent) ((Component) it.next())).getName();
        }
        return null;
    }

    public void openclose(Boolean bool, Component component) {
        ((AccordionContent) component.getParent()).openClose(bool.booleanValue());
    }

    public void expand(Component component) {
        if (this.autoClose) {
            Iterator it = iterator();
            while (it.hasNext()) {
                AccordionContent accordionContent = (AccordionContent) ((Component) it.next());
                accordionContent.openClose(component != AccordionContent.access$000(accordionContent));
            }
            return;
        }
        Iterator it2 = iterator();
        while (it2.hasNext()) {
            AccordionContent accordionContent2 = (AccordionContent) ((Component) it2.next());
            if (component == AccordionContent.access$000(accordionContent2)) {
                accordionContent2.openClose(false);
            }
        }
    }

    public void collapse(Component component) {
        Iterator it = iterator();
        while (it.hasNext()) {
            AccordionContent accordionContent = (AccordionContent) ((Component) it.next());
            if (component == AccordionContent.access$000(accordionContent)) {
                accordionContent.openClose(true);
            }
        }
    }

    public void setCloseIcon(Image image) {
        this.closeIcon = image;
    }

    public void setOpenIcon(Image image) {
        this.openIcon = image;
    }

    public void setCloseIcon(char c) {
        this.closeIcon = FontImage.createMaterial(c, UIManager.getInstance().getComponentStyle("AccordionArrow"));
    }

    public void setOpenIcon(char c) {
        this.openIcon = FontImage.createMaterial(c, UIManager.getInstance().getComponentStyle("AccordionArrow"));
    }

    public void setCloseIcon(char c, String str) {
        this.closeIcon = FontImage.createMaterial(c, UIManager.getInstance().getComponentStyle(str));
    }

    public void setOpenIcon(char c, String str) {
        this.openIcon = FontImage.createMaterial(c, UIManager.getInstance().getComponentStyle(str));
    }

    public void setAutoClose(boolean z) {
        this.autoClose = z;
    }

    public class AccordionContent extends Container {
        private Button arrow = new Button();
        private Component body;
        private boolean closed;
        Component header;
        private boolean layoutChang;

        static /* synthetic */ Component access$000(AccordionContent accordionContent) {
            return accordionContent.body;
        }

        public AccordionContent(Component component, Component component2) {
            setUIID("AccordionItem");
            setLayout(new BorderLayout());
            this.body = component2;
            this.header = component;
            this.closed = B3gAccordionNw.this.closedGlb;
            this.layoutChang = B3gAccordionNw.this.layoutChangGlb;
            this.header.setSelectedStyle(component.getUnselectedStyle());
            component.setPressedStyle(component.getUnselectedStyle());
            Container container = new Container();
            if (this.layoutChang) {
                container.setLayout(new FlowLayout(4, 4));
            } else {
                container.setLayout(new BorderLayout());
            }
            Container container2 = (Container) component;
            if (container2.getComponentCount() > 0) {
                if (this.layoutChang) {
                    container.add(component);
                } else {
                    container.add("Center", component);
                }
                container.setUIID(component.getUIID());
                component.setUIID("");
            }
            if (B3gAccordionNw.access$100(B3gAccordionNw.this) != null && B3gAccordionNw.access$200(B3gAccordionNw.this) != null) {
                this.arrow.setUIID(B3gAccordionNw.access$100(B3gAccordionNw.this).getUIID());
                this.arrow.setText(B3gAccordionNw.access$100(B3gAccordionNw.this).getText());
                B3gAccordionNw.this.setArrowUIID(B3gAccordionNw.access$100(B3gAccordionNw.this).getUIID());
            }
            this.arrow.setUIID(B3gAccordionNw.this.getArrowUIID());
            this.arrow.setIcon(B3gAccordionNw.access$300(B3gAccordionNw.this));
            this.arrow.addActionListener(new 1(B3gAccordionNw.this));
            if (this.layoutChang) {
                container.add(this.arrow);
            } else if (container2.getComponentCount() > 0) {
                container.add("East", this.arrow);
            } else {
                container.add("Center", this.arrow);
            }
            if (((Container) container.getComponentAt(0)).getName() != null) {
                if (((Container) container.getComponentAt(0)).getName().equals("HolaHola")) {
                    if (((Container) container.getComponentAt(0)).getComponentAt(1).getName().equals("Abcd")) {
                        ((Container) ((Container) container.getComponentAt(0)).getComponentAt(0)).setLeadComponent(this.arrow);
                    }
                } else {
                    container.setLeadComponent(this.arrow);
                }
            } else {
                container.setLeadComponent(this.arrow);
            }
            add("North", container);
            component2.setHidden(true);
            add("Center", component2);
        }

        class 1 implements ActionListener {
            final /* synthetic */ B3gAccordionNw val$this$0;

            1(B3gAccordionNw b3gAccordionNw) {
                this.val$this$0 = b3gAccordionNw;
            }

            public void actionPerformed(ActionEvent actionEvent) {
                AccordionContent.this.openClose(!r0.isClosed());
                if (B3gAccordionNw.access$400(B3gAccordionNw.this)) {
                    for (int i = 0; i < B3gAccordionNw.this.getComponentCount(); i++) {
                        AccordionContent accordionContent = (AccordionContent) B3gAccordionNw.this.getComponentAt(i);
                        if (accordionContent != AccordionContent.this && !accordionContent.isClosed()) {
                            accordionContent.openClose(true);
                        }
                    }
                }
                B3gAccordionNw.access$500(B3gAccordionNw.this, actionEvent);
            }
        }

        public boolean isClosed() {
            return this.closed;
        }

        public void openClose(boolean z) {
            this.closed = z;
            if (z) {
                this.arrow.setIcon(B3gAccordionNw.access$300(B3gAccordionNw.this));
                if (B3gAccordionNw.access$100(B3gAccordionNw.this) != null && B3gAccordionNw.access$200(B3gAccordionNw.this) != null) {
                    this.arrow.setUIID(B3gAccordionNw.access$100(B3gAccordionNw.this).getUIID());
                    this.arrow.setText(B3gAccordionNw.access$100(B3gAccordionNw.this).getText());
                    B3gAccordionNw b3gAccordionNw = B3gAccordionNw.this;
                    b3gAccordionNw.setArrowUIID(B3gAccordionNw.access$100(b3gAccordionNw).getUIID());
                }
            } else {
                this.arrow.setIcon(B3gAccordionNw.access$600(B3gAccordionNw.this));
                if (B3gAccordionNw.access$100(B3gAccordionNw.this) != null && B3gAccordionNw.access$200(B3gAccordionNw.this) != null) {
                    this.arrow.setUIID(B3gAccordionNw.access$200(B3gAccordionNw.this).getUIID());
                    this.arrow.setText(B3gAccordionNw.access$200(B3gAccordionNw.this).getText());
                    B3gAccordionNw b3gAccordionNw2 = B3gAccordionNw.this;
                    b3gAccordionNw2.setArrowUIID(B3gAccordionNw.access$200(b3gAccordionNw2).getUIID());
                }
            }
            this.body.setHidden(this.closed);
        }

        public void setArrowIcon(Image image) {
            this.arrow.setIcon(image);
        }
    }

    public void addOnClickItemListener(ActionListener actionListener) {
        this.listeners.addListener(actionListener);
    }

    public void removeOnClickItemListener(ActionListener actionListener) {
        this.listeners.removeListener(actionListener);
    }

    private void fireEvent(ActionEvent actionEvent) {
        this.listeners.fireActionEvent(actionEvent);
    }
}
