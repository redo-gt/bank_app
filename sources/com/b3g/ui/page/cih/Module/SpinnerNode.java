package com.b3g.ui.page.cih.Module;

import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.Painter;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Rectangle2D;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.scene.Bounds;
import com.codename1.ui.scene.Node;
import com.codename1.ui.scene.NodePainter;
import com.codename1.ui.scene.Point3D;
import com.codename1.ui.scene.TextPainter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SpinnerNode extends Node {
    private double flatScrollPos;
    ListModel listModel;
    private Style overlayStyle;
    private RowFormatter rowFormatter;
    private List scrollListeners;
    private List selectionListeners;
    private Label rowTemplate = new Label("", "Spinner3DRow");
    private Label overlayTemplate = new Label("", "Spinner3DOverlay");
    private Map childIndex = new HashMap();
    private SelectionListener selectionListener = new 1();
    private DataChangedListener listChangedListener = new 2();
    private int numSides = 14;
    private Label renderer = new Label("Testing", "Spinner3DRow");
    Node selectedRowOverlay = new Node();
    private int selectedIndex = -1;
    private Style rowStyle = this.rowTemplate.getUnselectedStyle();
    private Style selectedRowStyle = this.rowTemplate.getSelectedStyle();

    public interface RowFormatter {
        String format(String str);
    }

    private static boolean usePerspective() {
        return false;
    }

    static /* synthetic */ int access$000(SpinnerNode spinnerNode) {
        return spinnerNode.selectedIndex;
    }

    static /* synthetic */ void access$100(SpinnerNode spinnerNode) {
        spinnerNode.rebuildChildren();
    }

    class 1 implements SelectionListener {
        1() {
        }

        public void selectionChanged(int i, int i2) {
            if (i2 < 0 && SpinnerNode.this.listModel != null) {
                i2 = SpinnerNode.this.listModel.getSelectedIndex();
            }
            if (i2 < 0 || i2 >= SpinnerNode.this.listModel.getSize() || i2 == SpinnerNode.access$000(SpinnerNode.this)) {
                return;
            }
            SpinnerNode.this.setSelectedIndex(i2);
        }
    }

    class 2 implements DataChangedListener {
        2() {
        }

        public void dataChanged(int i, int i2) {
            SpinnerNode.access$100(SpinnerNode.this);
        }
    }

    public void addScrollListener(ScrollListener scrollListener) {
        if (this.scrollListeners == null) {
            this.scrollListeners = new ArrayList();
        }
        this.scrollListeners.add(scrollListener);
    }

    public void removeScrollListener(ScrollListener scrollListener) {
        List list = this.scrollListeners;
        if (list != null) {
            list.remove(scrollListener);
            if (this.scrollListeners.isEmpty()) {
                this.scrollListeners = null;
            }
        }
    }

    private void fireScrollEvent(int i) {
        List list = this.scrollListeners;
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((ScrollListener) it.next()).scrollChanged(-1, i, -1, -1);
            }
        }
    }

    public SpinnerNode() {
        Style unselectedStyle = this.overlayTemplate.getUnselectedStyle();
        this.overlayStyle = unselectedStyle;
        this.selectedRowOverlay.setStyle(unselectedStyle);
        this.selectedRowOverlay.setRenderer(new 3());
    }

    class 3 implements NodePainter {
        3() {
        }

        public void paint(Graphics graphics, Rectangle rectangle, Node node) {
            Style style = node.getStyle();
            graphics.setColor(style.getBgColor());
            graphics.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            graphics.setColor(style.getFgColor());
            graphics.drawLine(rectangle.getX(), rectangle.getY(), rectangle.getWidth() + rectangle.getX(), rectangle.getY());
            graphics.drawLine(rectangle.getX(), rectangle.getY() + rectangle.getHeight(), rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight());
        }
    }

    public void setRowFormatter(RowFormatter rowFormatter) {
        if (this.rowFormatter != rowFormatter) {
            this.rowFormatter = rowFormatter;
            rebuildChildren();
        }
    }

    public Style getRowStyle() {
        return this.rowStyle;
    }

    public int getNumSides() {
        return this.numSides;
    }

    public Style getSelectedRowStyle() {
        return this.selectedRowStyle;
    }

    public Style getSelectedOverlayStyle() {
        return this.overlayStyle;
    }

    public ListModel getListModel() {
        return this.listModel;
    }

    private void rebuildChildren() {
        this.childIndex.clear();
        removeAll();
        setSelectedIndex(this.listModel.getSelectedIndex());
        add(this.selectedRowOverlay);
    }

    public void setListModel(ListModel listModel) {
        ListModel listModel2 = this.listModel;
        if (listModel2 != null) {
            listModel2.removeSelectionListener(this.selectionListener);
            this.listModel.removeDataChangedListener(this.listChangedListener);
        }
        this.listModel = listModel;
        if (listModel != null) {
            listModel.addSelectionListener(this.selectionListener);
            this.listModel.addDataChangedListener(this.listChangedListener);
        }
        rebuildChildren();
    }

    public Node getSelectedRowOverlay() {
        return this.selectedRowOverlay;
    }

    public double calcRowHeight() {
        return this.renderer.getPreferredH();
    }

    public double calcFlatListHeight() {
        return this.renderer.getPreferredH() * this.listModel.getSize();
    }

    public double calcViewportHeight() {
        return (this.renderer.getPreferredH() * this.numSides) / 3.141592653589793d;
    }

    private double calculateRotationForChild(int i) {
        return (((-(360.0d / this.numSides)) * (i % r4)) + ((this.flatScrollPos * 360.0d) / (this.numSides * this.renderer.getPreferredH()))) % 360.0d;
    }

    private double getRotationRangeForSide() {
        return 360.0d / this.numSides;
    }

    private double getFlatVisibleHeight() {
        return (this.renderer.getPreferredH() * this.numSides) / 2;
    }

    public int getSelectedIndex() {
        return (int) ((this.flatScrollPos / calcFlatListHeight()) * this.listModel.getSize());
    }

    public void setSelectedIndex(int i) {
        if (i < 0 || i > this.listModel.getSize() - 1) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
        setScrollY((i * calcFlatListHeight()) / this.listModel.getSize());
    }

    private void updateSelectedIndex() {
        int selectedIndex = getSelectedIndex();
        int i = this.selectedIndex;
        if (selectedIndex != i) {
            this.selectedIndex = selectedIndex;
            this.listModel.setSelectedIndex(selectedIndex);
            List list = this.selectionListeners;
            if (list == null || list.isEmpty()) {
                return;
            }
            Iterator it = this.selectionListeners.iterator();
            while (it.hasNext()) {
                ((SelectionListener) it.next()).selectionChanged(i, selectedIndex);
            }
        }
    }

    public void addSelectionListener(SelectionListener selectionListener) {
        if (this.selectionListeners == null) {
            this.selectionListeners = new ArrayList();
        }
        this.selectionListeners.add(selectionListener);
    }

    public void removeSelectionListener(SelectionListener selectionListener) {
        List list = this.selectionListeners;
        if (list != null) {
            list.remove(selectionListener);
        }
        if (this.selectionListeners.isEmpty()) {
            this.selectionListeners = null;
        }
    }

    private int getMinVisibleIndex(int i) {
        return i - (this.numSides / 4);
    }

    private int getMaxVisibleIndex(int i) {
        return i + (this.numSides / 4);
    }

    public void setScrollY(double d) {
        boolean z = Math.abs(d - this.flatScrollPos) > 2.0d;
        this.flatScrollPos = d;
        setNeedsLayout(true);
        if (getScene() != null) {
            getScene().repaint();
        }
        updateSelectedIndex();
        if (z) {
            fireScrollEvent((int) d);
        }
    }

    public double getScrollY() {
        return this.flatScrollPos;
    }

    private Node getOrCreateChild(int i) {
        if (this.childIndex.containsKey(Integer.valueOf(i))) {
            return (Node) this.childIndex.get(Integer.valueOf(i));
        }
        ListModel listModel = this.listModel;
        if (listModel == null) {
            return null;
        }
        Node node = new Node();
        String str = (String) listModel.getItemAt(i);
        RowFormatter rowFormatter = this.rowFormatter;
        if (rowFormatter != null) {
            str = rowFormatter.format(str);
        }
        4 r2 = new 4(str, "Spinner3DRow", node);
        r2.setSelectedStyle(getSelectedRowStyle());
        r2.setUnselectedStyle(getRowStyle());
        node.setRenderer(new TextPainter(str, 4));
        remove(this.selectedRowOverlay);
        add(node);
        add(this.selectedRowOverlay);
        this.childIndex.put(Integer.valueOf(i), node);
        return node;
    }

    class 4 extends Label {
        final /* synthetic */ Node val$n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        4(String str, String str2, Node node) {
            super(str, str2);
            this.val$n = node;
        }

        public Style getStyle() {
            if (this.val$n.hasTag("selected")) {
                return getSelectedStyle();
            }
            return getUnselectedStyle();
        }
    }

    private static class RowPainter implements Painter {
        private Style painter;
        private Style selectedStyle;
        private Style unselectedStyle;

        public void paint(Graphics graphics, Rectangle rectangle) {
        }

        private RowPainter() {
        }
    }

    protected void layoutChildren() {
        double d;
        int i;
        int i2;
        int i3;
        int i4;
        long j;
        Node node;
        double width = ((Bounds) this.boundsInLocal.get()).getWidth();
        ((Bounds) this.boundsInLocal.get()).getHeight();
        double preferredH = this.renderer.getPreferredH();
        double d2 = (this.numSides * preferredH) / 3.141592653589793d;
        int selectedIndex = getSelectedIndex();
        int minVisibleIndex = getMinVisibleIndex(selectedIndex);
        int maxVisibleIndex = getMaxVisibleIndex(selectedIndex);
        if (hasChildren()) {
            int size = this.listModel.getSize();
            int i5 = 0;
            int i6 = 0;
            while (i5 < size) {
                if ((minVisibleIndex > i6 || maxVisibleIndex < i6) && !this.childIndex.containsKey(Integer.valueOf(i5))) {
                    i6++;
                    d = width;
                    i = maxVisibleIndex;
                    i2 = minVisibleIndex;
                    i3 = size;
                    i4 = i5;
                    j = 4614256656552045848L;
                } else {
                    Node orCreateChild = getOrCreateChild(i5);
                    if (minVisibleIndex > i6 || maxVisibleIndex < i6) {
                        d = width;
                        i = maxVisibleIndex;
                        i2 = minVisibleIndex;
                        i3 = size;
                        i4 = i5;
                        j = 4614256656552045848L;
                        orCreateChild.visible.set(false);
                        i6++;
                    } else {
                        orCreateChild.visible.set(true);
                        Bounds bounds = (Bounds) orCreateChild.boundsInLocal.get();
                        bounds.setWidth(width);
                        bounds.setDepth(0.0d);
                        bounds.setHeight(d2);
                        bounds.setMinX(0.0d);
                        bounds.setMinY(0.0d);
                        i2 = minVisibleIndex;
                        i3 = size;
                        double d3 = d2 / 2.0d;
                        i4 = i5;
                        i = maxVisibleIndex;
                        d = width;
                        orCreateChild.paintingRect.set(new Rectangle(0, (int) (d3 - (preferredH / 2.0d)), (int) width, (int) preferredH));
                        if (usePerspective()) {
                            bounds.setDepth(d2);
                            double calculateRotationForChild = calculateRotationForChild(i6);
                            if (Math.abs(calculateRotationForChild) < 10.0d) {
                                node = orCreateChild;
                                node.addTags("selected");
                                node.setStyle(getSelectedRowStyle());
                                node.opacity.set(Double.valueOf(1.0d));
                            } else {
                                node = orCreateChild;
                                node.removeTags("selected");
                                double cos = Math.cos((calculateRotationForChild * 3.141592653589793d) / 180.0d);
                                node.setStyle(getRowStyle());
                                node.opacity.set(Double.valueOf(cos));
                            }
                            node.rotate.set(Double.valueOf(-calculateRotationForChild));
                            node.rotationAxis.set(new Point3D(1.0d, 0.0d, 0.0d));
                            node.layoutX.set(Double.valueOf(0.0d));
                            node.layoutY.set(Double.valueOf(0.0d));
                            node.layoutZ.set(Double.valueOf((-d2) / 2.0d));
                            j = 4614256656552045848L;
                        } else {
                            j = 4614256656552045848L;
                            double calculateRotationForChild2 = (calculateRotationForChild(i6) * 3.141592653589793d) / 180.0d;
                            double rotationRangeForSide = calculateRotationForChild2 + (((getRotationRangeForSide() * 3.141592653589793d) / 180.0d) / 2.0d);
                            double rotationRangeForSide2 = calculateRotationForChild2 - (((getRotationRangeForSide() * 3.141592653589793d) / 180.0d) / 2.0d);
                            if (Math.abs(calculateRotationForChild2) < 0.17453292519943295d) {
                                orCreateChild.addTags("selected");
                                orCreateChild.setStyle(this.selectedRowStyle);
                                orCreateChild.opacity.set(Double.valueOf(1.0d));
                            } else {
                                orCreateChild.removeTags("selected");
                                orCreateChild.setStyle(this.rowStyle);
                                orCreateChild.opacity.set(Double.valueOf(Math.cos(calculateRotationForChild2)));
                            }
                            double abs = Math.abs((Math.sin(rotationRangeForSide) - Math.sin(rotationRangeForSide2)) * d3);
                            orCreateChild.layoutX.set(Double.valueOf(0.0d));
                            orCreateChild.layoutY.set(Double.valueOf((-d3) * Math.sin(calculateRotationForChild2)));
                            orCreateChild.layoutZ.set(Double.valueOf(0.0d));
                            orCreateChild.scaleY.set(Double.valueOf(abs / preferredH));
                        }
                        i6++;
                    }
                }
                i5 = i4 + 1;
                minVisibleIndex = i2;
                size = i3;
                maxVisibleIndex = i;
                width = d;
            }
            double d4 = width;
            Bounds bounds2 = (Bounds) this.selectedRowOverlay.boundsInLocal.get();
            bounds2.setWidth(d4);
            bounds2.setHeight(preferredH);
            bounds2.setMinX(0.0d);
            bounds2.setMinY(0.0d);
            this.selectedRowOverlay.layoutX.set(Double.valueOf(0.0d));
            this.selectedRowOverlay.layoutY.set(Double.valueOf((d2 / 2.0d) - (preferredH / 2.0d)));
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(this.overlayStyle.getBgColor());
        int alpha = graphics.getAlpha();
        graphics.setAlpha(255);
        graphics.fillRect(0, 0, (int) ((Bounds) this.boundsInLocal.get()).getWidth(), (int) ((Bounds) this.boundsInLocal.get()).getHeight());
        graphics.setAlpha(alpha);
        super.render(graphics);
        int clipX = graphics.getClipX();
        int clipY = graphics.getClipY();
        int clipWidth = graphics.getClipWidth();
        int clipHeight = graphics.getClipHeight();
        Rectangle2D boundsInScene = this.selectedRowOverlay.getBoundsInScene(new Rectangle2D());
        double doubleValue = ((Double) this.scaleX.get()).doubleValue();
        double doubleValue2 = ((Double) this.scaleY.get()).doubleValue();
        double doubleValue3 = ((Double) this.translateX.get()).doubleValue();
        this.scaleX.set(Double.valueOf(doubleValue * 1.35d));
        this.scaleY.set(Double.valueOf(doubleValue2 * 1.35d));
        int alignment = getRowStyle().getAlignment();
        if (alignment == 1) {
            this.translateX.set(Double.valueOf(doubleValue3 + (((((Bounds) this.boundsInLocal.get()).getWidth() * 0.3500000000000001d) / 2.0d) / 1.35d)));
        } else if (alignment == 3) {
            this.translateX.set(Double.valueOf(doubleValue3 - (((((Bounds) this.boundsInLocal.get()).getWidth() * 0.3500000000000001d) / 2.0d) / 1.35d)));
        }
        this.selectedRowOverlay.visible.set(false);
        graphics.setClip((int) boundsInScene.getX(), ((int) boundsInScene.getY()) + 1, (int) boundsInScene.getWidth(), ((int) boundsInScene.getHeight()) - 2);
        super.render(graphics);
        this.selectedRowOverlay.visible.set(true);
        graphics.setClip(clipX, clipY, clipWidth, clipHeight);
        this.scaleX.set(Double.valueOf(doubleValue));
        this.scaleY.set(Double.valueOf(doubleValue2));
        this.translateX.set(Double.valueOf(doubleValue3));
    }
}
