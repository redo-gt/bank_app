package com.b3g.ui.menu;

import com.b3g.cih.online.CihMBanking;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridBagLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.tree.TreeModel;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TreeComponent extends Container {
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

    static /* synthetic */ void access$000(TreeComponent treeComponent, Component component) {
        treeComponent.collapseNode(component);
    }

    static /* synthetic */ Container access$102(TreeComponent treeComponent, Container container) {
        treeComponent.Expanded = container;
        return container;
    }

    static /* synthetic */ void access$200(TreeComponent treeComponent, Component component) {
        treeComponent.expandNode(component);
    }

    static /* synthetic */ B3GUiManager access$300(TreeComponent treeComponent) {
        return treeComponent.uiManager;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Component component = (Component) actionEvent.getSource();
            Object clientProperty = component.getClientProperty("TREE_NODE_EXPANDED");
            if (clientProperty != null && clientProperty.equals("true")) {
                TreeComponent.access$000(TreeComponent.this, component);
                TreeComponent.access$102(TreeComponent.this, null);
            } else {
                TreeComponent.access$200(TreeComponent.this, component);
                TreeComponent.access$102(TreeComponent.this, component.getParent());
            }
        }
    }

    public TreeComponent(TreeModel treeModel, B3GUiManager b3GUiManager) {
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
                ((Button) createContainerNodeComponent.getComponentAt(2)).addActionListener(new 2(nodeB3G));
                container.addComponent(createContainerNodeComponent);
            } else {
                Container container2 = new Container(new BoxLayout(2));
                container2.addComponent(createContainerNodeComponent);
                container.addComponent(container2);
                ((Button) createContainerNodeComponent.getComponentAt(2)).addActionListener(this.expansionListener);
                ((Button) createContainerNodeComponent.getComponentAt(0)).addActionListener(this.expansionListener);
            }
            Button button = (Button) createContainerNodeComponent.getComponentAt(2);
            button.putClientProperty("TREE_OBJECT", elementAt);
            button.putClientProperty("TREE_PARENT", obj);
            button.putClientProperty("TREE_DEPTH", num);
            Button button2 = (Button) createContainerNodeComponent.getComponentAt(0);
            button2.putClientProperty("TREE_OBJECT", elementAt);
            button2.putClientProperty("TREE_PARENT", obj);
            button2.putClientProperty("TREE_DEPTH", num);
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ NodeB3G val$ndB3GA;

        2(NodeB3G nodeB3G) {
            this.val$ndB3GA = nodeB3G;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TreeComponent.access$300(TreeComponent.this).NavigateToPageById(this.val$ndB3GA.pageId);
        }
    }

    protected Container createContainerNodeComponent(Object obj, int i) {
        Container container = new Container();
        NodeB3G nodeB3G = (NodeB3G) obj;
        if (this.model.isLeaf(nodeB3G)) {
            container.setUIID("mn_btnItemIconBackGrey");
            TableLayout tableLayout = new TableLayout(1, 3);
            container.setLayout(tableLayout);
            container.setTensileDragEnabled(false);
            Button button = new Button();
            button.setUIID("mn_cntMenuItemIconTransp");
            button.setIcon(nodeB3G.GetIcon());
            container.addComponent(tableLayout.createConstraint(0, 0), button);
            container.revalidate();
            Container container2 = new Container(new GridBagLayout());
            container2.setUIID("mn_cntMenuItemSpaceOrange");
            TableLayout.Constraint createConstraint = tableLayout.createConstraint(0, 1);
            createConstraint.setWidthPercentage(1);
            container.addComponent(createConstraint, container2);
            container.revalidate();
            Button button2 = new Button(nodeB3G.GetLabel());
            button2.setUIID("mn_btnItemIconChildSmall");
            button2.setVerticalAlignment(4);
            TableLayout.Constraint createConstraint2 = tableLayout.createConstraint(0, 2);
            createConstraint2.setWidthPercentage(84);
            createConstraint2.setVerticalAlign(4);
            container.addComponent(createConstraint2, button2);
            container.setLeadComponent(button2);
            container.revalidate();
        } else {
            TableLayout tableLayout2 = new TableLayout(1, 3);
            container.setLayout(tableLayout2);
            container.setTensileDragEnabled(false);
            container.setUIID("mn_cntWBRD");
            Button button3 = new Button();
            button3.setUIID("mn_btnItemIconV2Gery");
            button3.setIcon(nodeB3G.GetIcon());
            container.addComponent(tableLayout2.createConstraint(0, 0), button3);
            container.revalidate();
            Container container3 = new Container(new BoxLayout(2));
            container3.setUIID("mn_cntMenuItemSpace");
            TableLayout.Constraint createConstraint3 = tableLayout2.createConstraint(0, 1);
            createConstraint3.setWidthPercentage(1);
            createConstraint3.setVerticalAlign(4);
            container.addComponent(createConstraint3, container3);
            container.revalidate();
            Button button4 = new Button(nodeB3G.GetLabel());
            button4.setUIID("mn_btnItemIcon");
            button4.setVerticalAlignment(4);
            button4.setHeight(nodeB3G.GetIcon().getHeight());
            TableLayout.Constraint createConstraint4 = tableLayout2.createConstraint(0, 2);
            createConstraint4.setWidthPercentage(84);
            container.addComponent(createConstraint4, button4);
            container.revalidate();
        }
        updateNodeComponentStyle(container.getSelectedStyle(), i);
        updateNodeComponentStyle(container.getUnselectedStyle(), i);
        return container;
    }

    private void expandNode(Component component) {
        Container container = this.Expanded;
        if (container != null && container != component.getParent()) {
            collapseOtherNode(this.Expanded.getComponentAt(2));
        }
        component.getParent().getComponentAt(0).putClientProperty("TREE_NODE_EXPANDED", "true");
        component.getParent().getComponentAt(2).putClientProperty("TREE_NODE_EXPANDED", "true");
        int intValue = ((Integer) component.getClientProperty("TREE_DEPTH")).intValue();
        Container parent = component.getParent().getParent();
        Object clientProperty = component.getClientProperty("TREE_OBJECT");
        new Label();
        buildB3GBranchContainerFocus(clientProperty, intValue, parent);
        Container container2 = (Container) ((Button) component).getParent().getParent().getComponentAt(0);
        container2.setUIID("mn_cntMenuItemIconOrange");
        Button button = (Button) container2.getComponentAt(0);
        button.setIcon(this.uiManager.ressource.getImage(DataTools.ReplaceString(button.getIcon().getImageName(), "_W", ".")));
        button.setUIID("mn_cntMenuItemIconOrange");
        ((Button) component.getParent().getComponentAt(2)).setUIID("mn_btnItemIconLabelW");
        parent.revalidate();
        CihMBanking.sesPMTR.setPreviousMenu(this);
    }

    private void collapseNode(Component component) {
        component.getParent().getComponentAt(0).putClientProperty("TREE_NODE_EXPANDED", null);
        component.getParent().getComponentAt(2).putClientProperty("TREE_NODE_EXPANDED", null);
        Container parent = component.getParent().getParent();
        Label label = new Label();
        label.setUIID("EmptyContainer");
        for (int componentCount = parent.getComponentCount() - 1; componentCount > 0; componentCount--) {
            parent.replace(parent.getComponentAt(componentCount), label, CommonTransitions.createSlide(1, false, 300));
            parent.removeComponent(label);
        }
        Container container = (Container) component.getParent().getParent().getComponentAt(0);
        container.setUIID("mn_cntWBRD");
        Button button = (Button) container.getComponentAt(0);
        button.setIcon(this.uiManager.ressource.getImage(DataTools.DeleteString(button.getIcon().getImageName(), "_W")));
        button.setUIID("mn_btnItemIconV2Gery");
        ((Button) container.getComponentAt(2)).setUIID("mn_btnItemIcon");
        container.revalidate();
        CihMBanking.sesPMTR.setPreviousMenu(this);
    }

    private void collapseOtherNode(Component component) {
        component.getParent().getComponentAt(0).putClientProperty("TREE_NODE_EXPANDED", null);
        component.getParent().getComponentAt(2).putClientProperty("TREE_NODE_EXPANDED", null);
        Container parent = component.getParent().getParent();
        new Label().setUIID("EmptyContainer");
        for (int componentCount = parent.getComponentCount() - 1; componentCount > 0; componentCount--) {
            parent.removeComponent(parent.getComponentAt(componentCount));
            parent.revalidate();
        }
        Container container = (Container) component.getParent().getParent().getComponentAt(0);
        container.setUIID("mn_cntWBRD");
        Button button = (Button) container.getComponentAt(0);
        button.setIcon(this.uiManager.ressource.getImage(DataTools.DeleteString(button.getIcon().getImageName(), "_W")));
        button.setUIID("mn_btnItemIconV2Gery");
        ((Button) container.getComponentAt(2)).setUIID("mn_btnItemIcon");
        container.revalidate();
        CihMBanking.sesPMTR.setPreviousMenu(this);
    }
}
