package com.b3g.ui.menu;

import com.b3g.cih.online.CihMBanking;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.B3GSideMenuBar;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.tree.TreeModel;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SideTreeComponent extends Container {
    private static final String KEY_DEPTH = "TREE_DEPTH";
    private static final String KEY_EXPANDED = "TREE_NODE_EXPANDED";
    private static final String KEY_OBJECT = "TREE_OBJECT";
    private static final String KEY_PARENT = "TREE_PARENT";
    private Container Expanded;
    private TreeModel model;
    private B3GUiManager uiManager;
    private ActionListener expansionListener = new 1();
    private int depthIndent = 15;
    public DataTools dt = new DataTools();

    protected void updateNodeComponentStyle(Style style, int i) {
    }

    static /* synthetic */ B3GUiManager access$000(SideTreeComponent sideTreeComponent) {
        return sideTreeComponent.uiManager;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Object clientProperty = ((Component) actionEvent.getSource()).getClientProperty("TREE_NODE_EXPANDED");
            if (clientProperty != null) {
                clientProperty.equals("true");
            }
        }
    }

    public SideTreeComponent(TreeModel treeModel, B3GUiManager b3GUiManager) {
        this.model = treeModel;
        this.uiManager = b3GUiManager;
        setLayout(new BoxLayout(2));
        setScrollVisible(false);
        buildB3GBranchContainerFocus(null, 0, this);
        setScrollableY(true);
    }

    protected void buildB3GBranchContainerFocus(Object obj, int i, Container container) {
        Vector children = this.model.getChildren(obj);
        int size = children.size();
        Integer num = new Integer(i + 1);
        for (int i2 = 0; i2 < size; i2++) {
            Object elementAt = children.elementAt(i2);
            Container createContainerNodeComponent = createContainerNodeComponent(elementAt, i);
            NodeB3G nodeB3G = (NodeB3G) elementAt;
            createContainerNodeComponent.setName(nodeB3G.GetLabel());
            if (this.model.isLeaf(elementAt)) {
                ((Button) createContainerNodeComponent.getComponentAt(0)).addActionListener(new 2(nodeB3G));
                container.addComponent(createContainerNodeComponent);
            } else {
                Container container2 = new Container(new BoxLayout(2));
                container2.addComponent(createContainerNodeComponent);
                container.addComponent(container2);
                ((Button) createContainerNodeComponent.getComponentAt(0)).addActionListener(this.expansionListener);
            }
            Button button = (Button) createContainerNodeComponent.getComponentAt(0);
            button.putClientProperty("TREE_OBJECT", elementAt);
            button.putClientProperty("TREE_PARENT", obj);
            button.putClientProperty("TREE_DEPTH", num);
            if (!this.model.isLeaf(elementAt)) {
                expandNode(button);
            }
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ NodeB3G val$ndB3GA;

        2(NodeB3G nodeB3G) {
            this.val$ndB3GA = nodeB3G;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            B3GSideMenuBar.closeCurrentMenu();
            SideTreeComponent.access$000(SideTreeComponent.this).NavigateToPageById(this.val$ndB3GA.pageId);
        }
    }

    protected Container createContainerNodeComponent(Object obj, int i) {
        Container container = new Container();
        NodeB3G nodeB3G = (NodeB3G) obj;
        if (this.model.isLeaf(nodeB3G)) {
            container.setLayout(new GridLayout(1, 1));
            container.setTensileDragEnabled(false);
            container.setUIID("SideMenuSubItemTitle");
            Button button = new Button(nodeB3G.GetLabel());
            button.setVerticalAlignment(4);
            button.setUIID("SideMenuSubItemButton");
            button.setIcon(nodeB3G.GetIcon());
            button.setPressedIcon(this.uiManager.ressource.getImage(DataTools.ReplaceString(nodeB3G.GetIcon().getImageName(), "_w", ".")));
            button.setRolloverIcon(this.uiManager.ressource.getImage(DataTools.ReplaceString(nodeB3G.GetIcon().getImageName(), "_w", ".")));
            button.setGap(10);
            button.setScrollVisible(false);
            container.addComponent(button);
            container.revalidate();
        } else {
            container.setLayout(new GridLayout(1, 1));
            container.setTensileDragEnabled(false);
            container.setUIID("SideMenuItemTitle");
            Button button2 = new Button(nodeB3G.GetLabel());
            button2.setUIID("SideMenuItemButton");
            button2.setVerticalAlignment(4);
            button2.setScrollVisible(false);
            container.addComponent(button2);
            container.revalidate();
        }
        updateNodeComponentStyle(container.getSelectedStyle(), i);
        updateNodeComponentStyle(container.getUnselectedStyle(), i);
        Settings.setNightMode(container, 0);
        return container;
    }

    private void expandNode(Component component) {
        Container container = this.Expanded;
        if (container != null && container != component.getParent()) {
            collapseOtherNode(this.Expanded.getComponentAt(0));
        }
        component.getParent().getComponentAt(0).putClientProperty("TREE_NODE_EXPANDED", "true");
        int intValue = ((Integer) component.getClientProperty("TREE_DEPTH")).intValue();
        Container parent = component.getParent().getParent();
        Object clientProperty = component.getClientProperty("TREE_OBJECT");
        new Label();
        buildB3GBranchContainerFocus(clientProperty, intValue, parent);
        parent.revalidate();
        CihMBanking.sesPMTR.setPreviousMenu(this);
    }

    private void collapseOtherNode(Component component) {
        component.getParent().getComponentAt(0).putClientProperty("TREE_NODE_EXPANDED", null);
        Container parent = component.getParent().getParent();
        new Label().setUIID("EmptyContainer");
        for (int componentCount = parent.getComponentCount() - 1; componentCount > 0; componentCount--) {
            parent.removeComponent(parent.getComponentAt(componentCount));
            parent.revalidate();
        }
        Container container = (Container) component.getParent().getParent().getComponentAt(0);
        container.setUIID("mn_cntWBRD");
        ((Button) container.getComponentAt(0)).setUIID("mn_btnItemIcon");
        container.revalidate();
        CihMBanking.sesPMTR.setPreviousMenu(this);
    }
}
