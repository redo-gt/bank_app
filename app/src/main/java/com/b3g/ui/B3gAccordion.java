package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
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
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.EventDispatcher;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3gAccordion extends Container {
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

    static /* synthetic */ Button access$100(B3gAccordion b3gAccordion) {
        return b3gAccordion.AccBtnClose;
    }

    static /* synthetic */ Button access$200(B3gAccordion b3gAccordion) {
        return b3gAccordion.AccBtnOpen;
    }

    static /* synthetic */ Image access$300(B3gAccordion b3gAccordion) {
        return b3gAccordion.closeIcon;
    }

    static /* synthetic */ boolean access$400(B3gAccordion b3gAccordion) {
        return b3gAccordion.autoClose;
    }

    static /* synthetic */ void access$500(B3gAccordion b3gAccordion, ActionEvent actionEvent) {
        b3gAccordion.fireEvent(actionEvent);
    }

    static /* synthetic */ Image access$600(B3gAccordion b3gAccordion) {
        return b3gAccordion.openIcon;
    }

    public B3gAccordion() {
        super.setLayout(new BoxLayout(2));
        this.closeIcon = FontImage.createMaterial((char) 58133, UIManager.getInstance().getComponentStyle("AccordionArrow"));
        this.openIcon = FontImage.createMaterial((char) 58131, UIManager.getInstance().getComponentStyle("AccordionArrow"));
        setScrollableY(true);
    }

    public void addContent(Component component, Component component2, Container container) {
        add(new AccordionContent(component, component2, container));
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

    public void addContentPers(Component component, Component component2) {
        add(new AccordionContent(component, component2, null, null));
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

    public void addContent(Component component, Component component2, boolean z) {
        add(new AccordionContent(component, component2, z));
    }

    public void addContentV2(Component component, Component component2, Boolean bool) {
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
        private Button arrow;
        private Component body;
        private boolean closed;
        Component header;
        private boolean layoutChang;

        static /* synthetic */ Component access$000(AccordionContent accordionContent) {
            return accordionContent.body;
        }

        public AccordionContent(Component component, Component component2) {
            this.arrow = new Button();
            setUIID("AccordionItem");
            setLayout(new BorderLayout());
            this.body = component2;
            this.header = component;
            this.closed = B3gAccordion.this.closedGlb;
            this.layoutChang = B3gAccordion.this.layoutChangGlb;
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
            if (B3gAccordion.access$100(B3gAccordion.this) != null && B3gAccordion.access$200(B3gAccordion.this) != null) {
                this.arrow.setUIID(B3gAccordion.access$100(B3gAccordion.this).getUIID());
                this.arrow.setText(B3gAccordion.access$100(B3gAccordion.this).getText());
                B3gAccordion.this.setArrowUIID(B3gAccordion.access$100(B3gAccordion.this).getUIID());
            }
            this.arrow.setUIID(B3gAccordion.this.getArrowUIID());
            this.arrow.setIcon(B3gAccordion.access$300(B3gAccordion.this));
            this.arrow.addActionListener(new 1(B3gAccordion.this));
            if (this.layoutChang) {
                container.add(this.arrow);
            } else if (container2.getComponentCount() > 0) {
                container.add("East", this.arrow);
            } else {
                container.add("Center", this.arrow);
            }
            container.setLeadComponent(this.arrow);
            if (!CihMBanking.sesPMTR.isAccordionIconDowntoBody) {
                add("North", container);
                component2.setHidden(true);
                add("Center", component2);
            } else {
                add("North", component2);
                component2.setHidden(true);
                add("Center", container);
            }
            CihMBanking.sesPMTR.isAccordionIconDowntoBody = false;
        }

        class 1 implements ActionListener {
            final /* synthetic */ B3gAccordion val$this$0;

            1(B3gAccordion b3gAccordion) {
                this.val$this$0 = b3gAccordion;
            }

            public void actionPerformed(ActionEvent actionEvent) {
                AccordionContent.this.openClose(!r0.isClosed());
                if (B3gAccordion.access$400(B3gAccordion.this)) {
                    for (int i = 0; i < B3gAccordion.this.getComponentCount(); i++) {
                        AccordionContent accordionContent = (AccordionContent) B3gAccordion.this.getComponentAt(i);
                        if (accordionContent != AccordionContent.this && !accordionContent.isClosed()) {
                            accordionContent.openClose(true);
                        }
                    }
                }
                B3gAccordion.this.animateLayout(250);
                B3gAccordion.access$500(B3gAccordion.this, actionEvent);
            }
        }

        public AccordionContent(Component component, Component component2, boolean z) {
            this.arrow = new Button();
            setUIID("AccordionItem");
            setLayout(new BorderLayout());
            this.body = component2;
            this.header = component;
            this.closed = B3gAccordion.this.closedGlb;
            this.layoutChang = B3gAccordion.this.layoutChangGlb;
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
            if (B3gAccordion.access$100(B3gAccordion.this) != null && B3gAccordion.access$200(B3gAccordion.this) != null) {
                this.arrow.setUIID(B3gAccordion.access$100(B3gAccordion.this).getUIID());
                this.arrow.setText(B3gAccordion.access$100(B3gAccordion.this).getText());
                B3gAccordion.this.setArrowUIID(B3gAccordion.access$100(B3gAccordion.this).getUIID());
            }
            this.arrow.setUIID(B3gAccordion.this.getArrowUIID());
            this.arrow.setIcon(B3gAccordion.access$300(B3gAccordion.this));
            this.arrow.addActionListener(new 2(B3gAccordion.this));
            if (this.layoutChang) {
                container.add(this.arrow);
            } else if (container2.getComponentCount() > 0) {
                container.add("East", this.arrow);
            } else {
                container.add("Center", this.arrow);
            }
            container.setLeadComponent(this.arrow);
            if (!CihMBanking.sesPMTR.isAccordionIconDowntoBody) {
                add("North", container);
                component2.setHidden(true);
                add("Center", component2);
            } else {
                add("North", component2);
                component2.setHidden(true);
                add("Center", container);
            }
            CihMBanking.sesPMTR.isAccordionIconDowntoBody = false;
            if (z) {
                openClose(!isClosed());
                if (B3gAccordion.access$400(B3gAccordion.this)) {
                    for (int i = 0; i < B3gAccordion.this.getComponentCount(); i++) {
                        AccordionContent accordionContent = (AccordionContent) B3gAccordion.this.getComponentAt(i);
                        if (accordionContent != this && !accordionContent.isClosed()) {
                            accordionContent.openClose(true);
                        }
                    }
                }
                B3gAccordion.this.animateLayout(250);
            }
        }

        class 2 implements ActionListener {
            final /* synthetic */ B3gAccordion val$this$0;

            2(B3gAccordion b3gAccordion) {
                this.val$this$0 = b3gAccordion;
            }

            public void actionPerformed(ActionEvent actionEvent) {
                AccordionContent.this.openClose(!r0.isClosed());
                if (B3gAccordion.access$400(B3gAccordion.this)) {
                    for (int i = 0; i < B3gAccordion.this.getComponentCount(); i++) {
                        AccordionContent accordionContent = (AccordionContent) B3gAccordion.this.getComponentAt(i);
                        if (accordionContent != AccordionContent.this && !accordionContent.isClosed()) {
                            accordionContent.openClose(true);
                        }
                    }
                }
                B3gAccordion.this.animateLayout(250);
                B3gAccordion.access$500(B3gAccordion.this, actionEvent);
            }
        }

        public AccordionContent(Component component, Component component2, Container container) {
            this.arrow = new Button();
            setUIID("AccordionItem");
            setLayout(new BorderLayout());
            this.body = component2;
            this.header = component;
            this.closed = B3gAccordion.this.closedGlb;
            this.layoutChang = B3gAccordion.this.layoutChangGlb;
            this.header.setSelectedStyle(component.getUnselectedStyle());
            component.setPressedStyle(component.getUnselectedStyle());
            Container container2 = new Container();
            if (this.layoutChang) {
                container2.setLayout(new FlowLayout(4, 4));
            } else {
                container2.setLayout(new BorderLayout());
            }
            Container container3 = (Container) component;
            if (container3.getComponentCount() > 0) {
                if (this.layoutChang) {
                    container2.add(component);
                } else {
                    container2.add("North", container);
                    container2.add("Center", component);
                }
                container2.setUIID(component.getUIID());
                component.setUIID("");
            }
            if (B3gAccordion.access$100(B3gAccordion.this) != null && B3gAccordion.access$200(B3gAccordion.this) != null) {
                this.arrow.setUIID(B3gAccordion.access$100(B3gAccordion.this).getUIID());
                this.arrow.setText(B3gAccordion.access$100(B3gAccordion.this).getText());
                B3gAccordion.this.setArrowUIID(B3gAccordion.access$100(B3gAccordion.this).getUIID());
            }
            this.arrow.setUIID(B3gAccordion.this.getArrowUIID());
            this.arrow.setIcon(B3gAccordion.access$300(B3gAccordion.this));
            this.arrow.addActionListener(new 3(B3gAccordion.this));
            if (this.layoutChang) {
                container2.add(this.arrow);
            } else if (container3.getComponentCount() > 0) {
                container2.add("East", this.arrow);
            } else {
                container2.add("Center", this.arrow);
            }
            this.arrow.getAllStyles().setPaddingUnit(1);
            this.arrow.getAllStyles().setPaddingRight(1);
            container2.setLeadComponent(this.arrow);
            add("North", container2);
            component2.setHidden(true);
            add("Center", component2);
        }

        class 3 implements ActionListener {
            final /* synthetic */ B3gAccordion val$this$0;

            3(B3gAccordion b3gAccordion) {
                this.val$this$0 = b3gAccordion;
            }

            public void actionPerformed(ActionEvent actionEvent) {
                AccordionContent.this.openClose(!r0.isClosed());
                if (B3gAccordion.access$400(B3gAccordion.this)) {
                    for (int i = 0; i < B3gAccordion.this.getComponentCount(); i++) {
                        AccordionContent accordionContent = (AccordionContent) B3gAccordion.this.getComponentAt(i);
                        if (accordionContent != AccordionContent.this && !accordionContent.isClosed()) {
                            accordionContent.openClose(true);
                        }
                    }
                }
                B3gAccordion.this.animateLayout(250);
                B3gAccordion.access$500(B3gAccordion.this, actionEvent);
            }
        }

        public AccordionContent(Component component, Component component2, Container container, Object obj) {
            this.arrow = new Button();
            setUIID("AccordionItem");
            setLayout(new BorderLayout());
            this.body = component2;
            this.header = component;
            this.closed = B3gAccordion.this.closedGlb;
            this.layoutChang = B3gAccordion.this.layoutChangGlb;
            this.header.setSelectedStyle(component.getUnselectedStyle());
            component.setPressedStyle(component.getUnselectedStyle());
            Container container2 = new Container(new GridLayout(1, 1));
            container2.add(component);
            container2.setUIID(component.getUIID());
            component.setUIID("");
            this.arrow = (Button) ((Container) ((Container) component).getComponentAt(0)).getComponentAt(2);
            if (B3gAccordion.access$100(B3gAccordion.this) != null && B3gAccordion.access$200(B3gAccordion.this) != null) {
                this.arrow.setUIID(B3gAccordion.access$100(B3gAccordion.this).getUIID());
                this.arrow.setText(B3gAccordion.access$100(B3gAccordion.this).getText());
                B3gAccordion.this.setArrowUIID(B3gAccordion.access$100(B3gAccordion.this).getUIID());
            }
            this.arrow.setUIID(B3gAccordion.this.getArrowUIID());
            this.arrow.setIcon(B3gAccordion.access$300(B3gAccordion.this));
            this.arrow.addActionListener(new 4(B3gAccordion.this));
            container2.setLeadComponent(this.arrow);
            add("North", container2);
            component2.setHidden(true);
            add("Center", component2);
        }

        class 4 implements ActionListener {
            final /* synthetic */ B3gAccordion val$this$0;

            4(B3gAccordion b3gAccordion) {
                this.val$this$0 = b3gAccordion;
            }

            public void actionPerformed(ActionEvent actionEvent) {
                AccordionContent.this.openClose(!r0.isClosed());
                if (B3gAccordion.access$400(B3gAccordion.this)) {
                    for (int i = 0; i < B3gAccordion.this.getComponentCount(); i++) {
                        AccordionContent accordionContent = (AccordionContent) B3gAccordion.this.getComponentAt(i);
                        if (accordionContent != AccordionContent.this && !accordionContent.isClosed()) {
                            accordionContent.openClose(true);
                        }
                    }
                }
                B3gAccordion.this.animateLayout(250);
                B3gAccordion.access$500(B3gAccordion.this, actionEvent);
            }
        }

        public boolean isClosed() {
            return this.closed;
        }

        public void openClose(boolean z) {
            this.closed = z;
            if (z) {
                this.arrow.setIcon(B3gAccordion.access$300(B3gAccordion.this));
                if (B3gAccordion.access$100(B3gAccordion.this) != null && B3gAccordion.access$200(B3gAccordion.this) != null) {
                    this.arrow.setUIID(B3gAccordion.access$100(B3gAccordion.this).getUIID());
                    this.arrow.setText(B3gAccordion.access$100(B3gAccordion.this).getText());
                    B3gAccordion b3gAccordion = B3gAccordion.this;
                    b3gAccordion.setArrowUIID(B3gAccordion.access$100(b3gAccordion).getUIID());
                }
            } else {
                this.arrow.setIcon(B3gAccordion.access$600(B3gAccordion.this));
                if (B3gAccordion.access$100(B3gAccordion.this) != null && B3gAccordion.access$200(B3gAccordion.this) != null) {
                    this.arrow.setUIID(B3gAccordion.access$200(B3gAccordion.this).getUIID());
                    this.arrow.setText(B3gAccordion.access$200(B3gAccordion.this).getText());
                    B3gAccordion b3gAccordion2 = B3gAccordion.this;
                    b3gAccordion2.setArrowUIID(B3gAccordion.access$200(b3gAccordion2).getUIID());
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
