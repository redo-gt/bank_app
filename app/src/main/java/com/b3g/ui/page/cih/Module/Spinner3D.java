package com.b3g.ui.page.cih.Module;

import com.b3g.ui.page.cih.Module.SpinnerNode;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.ComponentSelector;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.scene.Bounds;
import com.codename1.ui.scene.PerspectiveCamera;
import com.codename1.ui.scene.Scene;
import java.util.Calendar;
import java.util.Date;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
class Spinner3D extends Container implements InternalPickerWidget {
    private SpinnerNode root;
    private Scene scene;
    private ScrollingContainer scroller;

    private static boolean usePerspective() {
        return false;
    }

    static /* synthetic */ SpinnerNode access$000(Spinner3D spinner3D) {
        return spinner3D.root;
    }

    static /* synthetic */ ScrollingContainer access$100(Spinner3D spinner3D) {
        return spinner3D.scroller;
    }

    private static class ScrollingContainer extends Container {
        ScrollingContainer() {
            super(BoxLayout.y());
            getUnselectedStyle().setBorder(Border.createEmpty());
        }

        public void setScrollY(int i) {
            super.setScrollY(i);
        }
    }

    public int getSelectedIndex() {
        return this.root.getSelectedIndex();
    }

    public Spinner3D(ListModel listModel) {
        super(BoxLayout.y());
        setScrollableY(false);
        this.root = new SpinnerNode();
        1 r1 = new 1();
        this.scene = r1;
        r1.setName("Scene");
        ((Bounds) this.root.boundsInLocal.get()).setWidth(Display.getInstance().getDisplayWidth());
        ((Bounds) this.root.boundsInLocal.get()).setHeight(1000.0d);
        setModel(listModel);
        this.scene.setRoot(this.root);
        if (usePerspective()) {
            this.scene.camera.set(new PerspectiveCamera(this.scene, 0.25d, 1600.0d, 4600.0d));
        }
        2 r11 = new 2();
        this.scroller = r11;
        r11.setSnapToGrid(true);
        this.scroller.setScrollVisible(false);
        this.scroller.setScrollableY(true);
        this.scroller.setName("Scroller");
        this.scroller.addScrollListener(new 3());
        this.root.addScrollListener(new 4());
        ComponentSelector.$(this.scroller, this.scene).setMargin(0).setPadding(0);
        this.scroller.setScrollY((int) this.root.getScrollY());
        Container encloseIn = LayeredLayout.encloseIn(this.scene, this.scroller);
        ComponentSelector.$(encloseIn).setBorder(Border.createEmpty()).setMargin(0).setPadding(0).setBgTransparency(0);
        encloseIn.setName("Wrapper");
        ((LayeredLayout) encloseIn.getLayout()).setInsets(this.scroller, "0 0 auto 0").setInsets(this.scene, "0 0 auto 0");
        add(encloseIn);
    }

    class 1 extends Scene {
        1() {
        }

        public void setWidth(int i) {
            super.setWidth(i);
            ((Bounds) Spinner3D.access$000(Spinner3D.this).boundsInLocal.get()).setWidth(i);
        }

        public void setHeight(int i) {
            super.setHeight(i);
            ((Bounds) Spinner3D.access$000(Spinner3D.this).boundsInLocal.get()).setHeight(i);
        }
    }

    class 2 extends ScrollingContainer {
        2() {
        }

        protected Dimension calcPreferredSize() {
            return new Dimension(500, (int) Spinner3D.access$000(Spinner3D.this).calcViewportHeight());
        }

        protected Dimension calcScrollSize() {
            return new Dimension(500, (((int) Spinner3D.access$000(Spinner3D.this).calcFlatListHeight()) + getHeight()) - ((int) Spinner3D.access$000(Spinner3D.this).calcRowHeight()));
        }

        protected int getGridPosY() {
            int calcRowHeight = (int) Spinner3D.access$000(Spinner3D.this).calcRowHeight();
            int scrollY = getScrollY();
            int i = scrollY % calcRowHeight;
            if (i >= calcRowHeight - i) {
                scrollY += calcRowHeight;
            }
            int i2 = scrollY - i;
            if (i2 > Spinner3D.access$000(Spinner3D.this).calcFlatListHeight() - calcRowHeight) {
                i2 -= calcRowHeight;
            }
            if (i2 < 0) {
                return 0;
            }
            return i2;
        }

        public void pointerPressed(int i, int i2) {
            super.pointerPressed(i, i2);
            Spinner3D.access$000(Spinner3D.this).getSelectedRowOverlay().contains(i, i2);
        }
    }

    class 3 implements ScrollListener {
        3() {
        }

        public void scrollChanged(int i, int i2, int i3, int i4) {
            Spinner3D.access$000(Spinner3D.this).setScrollY(i2);
        }
    }

    class 4 implements ScrollListener {
        4() {
        }

        public void scrollChanged(int i, int i2, int i3, int i4) {
            if (Math.abs(Spinner3D.access$100(Spinner3D.this).getScrollY() - i2) > 2) {
                Spinner3D.access$100(Spinner3D.this).setScrollY(i2);
            }
        }
    }

    public void setModel(ListModel listModel) {
        if (listModel instanceof SpinnerNumberModel) {
            listModel = new NumberModelAdapter((SpinnerNumberModel) listModel);
        }
        if (listModel instanceof SpinnerDateModel) {
            listModel = new DateModelAdapter((SpinnerDateModel) listModel);
        }
        this.root.setListModel(listModel);
        ScrollingContainer scrollingContainer = this.scroller;
        if (scrollingContainer != null) {
            scrollingContainer.setShouldCalcPreferredSize(true);
        }
    }

    public Object getValue() {
        ListModel listModel = this.root.getListModel();
        if (listModel instanceof NumberModelAdapter) {
            NumberModelAdapter numberModelAdapter = (NumberModelAdapter) listModel;
            return NumberModelAdapter.access$200(numberModelAdapter).getItemAt(numberModelAdapter.getSelectedIndex());
        }
        if (listModel instanceof DateModelAdapter) {
            DateModelAdapter dateModelAdapter = (DateModelAdapter) listModel;
            return dateModelAdapter.inner.getItemAt(dateModelAdapter.getSelectedIndex());
        }
        return listModel.getItemAt(listModel.getSelectedIndex());
    }

    public static Spinner3D create(double d, double d2, double d3, double d4) {
        return new Spinner3D(new SpinnerNumberModel(d, d2, d3, d4));
    }

    public static Spinner3D create(int i, int i2, int i3, int i4) {
        return new Spinner3D(new SpinnerNumberModel(i, i2, i3, i4));
    }

    public static Spinner3D createDate(long j, long j2, long j3) {
        return new Spinner3D(new SpinnerDateModel(j, j2, j3));
    }

    private static class DateModelAdapter implements ListModel {
        DateFormat fmt = new SimpleDateFormat("EEE MMM d");
        final SpinnerDateModel inner;

        DateModelAdapter(SpinnerDateModel spinnerDateModel) {
            this.inner = spinnerDateModel;
        }

        public String getItemAt(int i) {
            Date date = (Date) this.inner.getItemAt(i);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(11, 0);
            calendar.set(12, 0);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(new Date());
            calendar2.set(11, 23);
            calendar2.set(12, 59);
            calendar2.set(13, 59);
            return (date.getTime() < calendar.getTime().getTime() || date.getTime() >= calendar2.getTime().getTime()) ? this.fmt.format(date) : "Today";
        }

        public int getSize() {
            return this.inner.getSize();
        }

        public int getSelectedIndex() {
            return this.inner.getSelectedIndex();
        }

        public void setSelectedIndex(int i) {
            this.inner.setSelectedIndex(i);
        }

        public void addDataChangedListener(DataChangedListener dataChangedListener) {
            this.inner.addDataChangedListener(dataChangedListener);
        }

        public void removeDataChangedListener(DataChangedListener dataChangedListener) {
            this.inner.removeDataChangedListener(dataChangedListener);
        }

        public void addSelectionListener(SelectionListener selectionListener) {
            this.inner.addSelectionListener(selectionListener);
        }

        public void removeSelectionListener(SelectionListener selectionListener) {
            this.inner.removeSelectionListener(selectionListener);
        }

        public void addItem(String str) {
            this.inner.addItem(str);
        }

        public void removeItem(int i) {
            this.inner.removeItem(i);
        }
    }

    private static class NumberModelAdapter implements ListModel {
        private final SpinnerNumberModel inner;

        static /* synthetic */ SpinnerNumberModel access$200(NumberModelAdapter numberModelAdapter) {
            return numberModelAdapter.inner;
        }

        NumberModelAdapter(SpinnerNumberModel spinnerNumberModel) {
            this.inner = spinnerNumberModel;
        }

        public String getItemAt(int i) {
            return this.inner.getItemAt(i).toString();
        }

        public int getSize() {
            return this.inner.getSize();
        }

        public int getSelectedIndex() {
            return this.inner.getSelectedIndex();
        }

        public void setSelectedIndex(int i) {
            this.inner.setSelectedIndex(i);
        }

        public void addDataChangedListener(DataChangedListener dataChangedListener) {
            this.inner.addDataChangedListener(dataChangedListener);
        }

        public void removeDataChangedListener(DataChangedListener dataChangedListener) {
            this.inner.removeDataChangedListener(dataChangedListener);
        }

        public void addSelectionListener(SelectionListener selectionListener) {
            this.inner.addSelectionListener(selectionListener);
        }

        public void removeSelectionListener(SelectionListener selectionListener) {
            this.inner.removeSelectionListener(selectionListener);
        }

        public void addItem(String str) {
            this.inner.addItem(str);
        }

        public void removeItem(int i) {
            this.inner.removeItem(i);
        }
    }

    public Style getRowStyle() {
        return this.root.getRowStyle();
    }

    public Style getSelectedRowStyle() {
        return this.root.getSelectedRowStyle();
    }

    public Style getSelectedOverlayStyle() {
        return this.root.getSelectedOverlayStyle();
    }

    void setRowFormatter(SpinnerNode.RowFormatter rowFormatter) {
        this.root.setRowFormatter(rowFormatter);
    }

    public void setValue(Object obj) {
        ListModel listModel = this.root.getListModel();
        if (listModel instanceof NumberModelAdapter) {
            NumberModelAdapter.access$200((NumberModelAdapter) listModel).setValue(obj);
            return;
        }
        if (listModel instanceof DateModelAdapter) {
            ((DateModelAdapter) listModel).inner.setValue((Date) obj);
            return;
        }
        int size = listModel.getSize();
        for (int i = 0; i < size; i++) {
            Object itemAt = listModel.getItemAt(i);
            if (itemAt != null && itemAt.equals(obj)) {
                listModel.setSelectedIndex(i);
                return;
            }
        }
    }

    public void paint(Graphics graphics) {
        int alpha = graphics.getAlpha();
        graphics.setColor(this.root.getSelectedOverlayStyle().getBgColor());
        graphics.setAlpha(255);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
        graphics.setAlpha(alpha);
        super.paint(graphics);
    }
}
