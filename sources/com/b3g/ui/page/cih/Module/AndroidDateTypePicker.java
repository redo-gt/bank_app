package com.b3g.ui.page.cih.Module;

import com.codename1.components.InteractionDialog;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentSelector;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import java.util.Date;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class AndroidDateTypePicker extends Button {
    private Date date = new Date();
    public Button doneButton = new Button();
    public ActionListener l;

    static /* synthetic */ Date access$002(AndroidDateTypePicker androidDateTypePicker, Date date) {
        androidDateTypePicker.date = date;
        return date;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AndroidDateTypePicker() {
        addActionListener(new AndroidDateTypePicker$$ExternalSyntheticLambda0(this));
    }

    /* synthetic */ void lambda$new$0$com-b3g-ui-page-cih-Module-AndroidDateTypePicker(ActionEvent actionEvent) {
        showInteractionDialog();
    }

    private void showInteractionDialog() {
        boolean isTablet = Display.getInstance().isTablet();
        InteractionDialog interactionDialog = new InteractionDialog();
        ComponentSelector.select("DialogTitle", interactionDialog).getParent().setPadding(0).setMargin(0).setBorder(Border.createEmpty());
        interactionDialog.getTitleComponent().setVisible(false);
        ComponentSelector.select(interactionDialog.getTitleComponent()).setPadding(0).setMargin(0);
        interactionDialog.setUIID(isTablet ? "PickerDialogTablet" : "PickerDialog");
        interactionDialog.getUnselectedStyle().setBgColor(new Label("", "Spinner3DOverlay").getUnselectedStyle().getBgColor());
        interactionDialog.getUnselectedStyle().setBgTransparency(255);
        if (isTablet) {
            interactionDialog.getUnselectedStyle().setBorder(RoundRectBorder.create().cornerRadius(2.0f));
        }
        interactionDialog.getContentPane().setLayout(new BorderLayout());
        interactionDialog.getContentPane().setUIID(isTablet ? "PickerDialogContentTablet" : "PickerDialogContent");
        interactionDialog.getContentPane().getUnselectedStyle().setBgColor(new Label("", "Spinner3DOverlay").getUnselectedStyle().getBgColor());
        DateSpinner3D createDatePicker3D = createDatePicker3D();
        Container container = new Container(new FlowLayout(4, 4));
        container.add(createDatePicker3D);
        interactionDialog.getContentPane().add("Center", container);
        Button button = new Button("OK");
        this.doneButton = button;
        button.getAllStyles().setBgTransparency(0);
        this.doneButton.getAllStyles().setFgColor(16405787);
        this.doneButton.getAllStyles().setPadding(1, 1, 1, 1);
        this.doneButton.getAllStyles().setPaddingUnit(2);
        this.doneButton.addActionListener(new 1(interactionDialog, createDatePicker3D));
        this.doneButton.addActionListener(this.l);
        Button button2 = new Button("Cancel");
        button2.getAllStyles().setBgTransparency(0);
        button2.getAllStyles().setFgColor(16405787);
        button2.getAllStyles().setPadding(1, 1, 1, 1);
        button2.getAllStyles().setPaddingUnit(2);
        button2.addActionListener(new 2(interactionDialog));
        Container centerEastWest = BorderLayout.centerEastWest(null, this.doneButton, button2);
        centerEastWest.setUIID(isTablet ? "PickerButtonBarTablet" : "PickerButtonBar");
        interactionDialog.getContentPane().add("North", centerEastWest);
        Form componentForm = getComponentForm();
        if (componentForm == null) {
            throw new RuntimeException("Attempt to show interaction dialog while button is not on form.  Illegal state");
        }
        int max = Math.max(0, componentForm.getContentPane().getHeight() - interactionDialog.getPreferredH());
        if (max == 0) {
            container.getUnselectedStyle().setPaddingTop(0);
            container.getUnselectedStyle().setPaddingBottom(0);
        }
        interactionDialog.setWidth(Display.getInstance().getDisplayWidth());
        interactionDialog.setHeight(interactionDialog.getPreferredH());
        interactionDialog.setY(Display.getInstance().getDisplayHeight());
        interactionDialog.setX(0);
        interactionDialog.setRepositionAnimation(false);
        if (Display.getInstance().isTablet()) {
            getComponentForm().getAnimationManager().flushAnimation(new 3(interactionDialog));
        } else {
            getComponentForm().getAnimationManager().flushAnimation(new 4(interactionDialog, max));
        }
    }

    class 1 implements ActionListener {
        final /* synthetic */ InteractionDialog val$dlg;
        final /* synthetic */ InternalPickerWidget val$spinner;

        1(InteractionDialog interactionDialog, InternalPickerWidget internalPickerWidget) {
            this.val$dlg = interactionDialog;
            this.val$spinner = internalPickerWidget;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$dlg.disposeToTheBottom();
            AndroidDateTypePicker.access$002(AndroidDateTypePicker.this, (Date) this.val$spinner.getValue());
            AndroidDateTypePicker.this.updateValue();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ InteractionDialog val$dlg;

        2(InteractionDialog interactionDialog) {
            this.val$dlg = interactionDialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$dlg.disposeToTheBottom();
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ InteractionDialog val$dlg;

        3(InteractionDialog interactionDialog) {
            this.val$dlg = interactionDialog;
        }

        public void run() {
            this.val$dlg.showPopupDialog(AndroidDateTypePicker.this);
        }
    }

    class 4 implements Runnable {
        final /* synthetic */ InteractionDialog val$dlg;
        final /* synthetic */ int val$top;

        4(InteractionDialog interactionDialog, int i) {
            this.val$dlg = interactionDialog;
            this.val$top = i;
        }

        public void run() {
            this.val$dlg.show(this.val$top, 0, 0, 0);
        }
    }

    protected void updateValue() {
        if (this.date == null) {
            setText("...");
        } else {
            setText(new SimpleDateFormat("MM-yyyy").format(this.date));
        }
    }

    private DateSpinner3D createDatePicker3D() {
        DateSpinner3D dateSpinner3D = new DateSpinner3D();
        Date date = this.date;
        if (date != null) {
            dateSpinner3D.setValue(date);
        } else {
            dateSpinner3D.setValue(new Date());
        }
        return dateSpinner3D;
    }
}
