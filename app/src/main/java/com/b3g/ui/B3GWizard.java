package com.b3g.ui;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class B3GWizard extends AbstractWizard {
    public int currentStepId = 0;
    public boolean withHeader = true;
    public boolean slide_to_left = true;

    public B3GWizard(ArrayList arrayList) {
        this.wizardCnt = new Container(new BorderLayout());
        this.stepsList = arrayList;
        int i = 0;
        while (i < this.stepsList.size() - 1) {
            AbstractStep abstractStep = (AbstractStep) this.stepsList.get(i);
            i++;
            abstractStep.nextStep = (AbstractStep) this.stepsList.get(i);
        }
        for (int i2 = 1; i2 < this.stepsList.size(); i2++) {
            ((AbstractStep) this.stepsList.get(i2)).previousStep = (AbstractStep) this.stepsList.get(i2 - 1);
        }
        for (int i3 = 0; i3 < this.stepsList.size() - 1; i3++) {
            ((AbstractStep) this.stepsList.get(i3)).id = i3;
        }
        ((AbstractStep) arrayList.get(0)).isSelected = true;
        ((AbstractStep) arrayList.get(0)).isCurrentStep = true;
    }

    public Container drawWizard(String str, int i, int i2) {
        this.header = drawHeader(str, i, i2);
        this.stepsCnt = getStepById(this.currentStepId).stepCnt;
        this.wizardCnt.addComponent("North", this.header);
        this.wizardCnt.addComponent("Center", this.stepsCnt);
        int i3 = 0;
        while (i3 < this.stepsList.size() - 1) {
            int i4 = i3 + 1;
            if (this.currentStepId == i4) {
                this.header.removeAll();
                ((AbstractStep) this.stepsList.get(i3)).nextStep.isSelected = true;
                ((AbstractStep) this.stepsList.get(i3)).nextStep.isCurrentStep = true;
                this.header = drawHeader(str, i, i2);
                this.header.revalidate();
                this.wizardCnt.addComponent("North", this.header);
            }
            i3 = i4;
        }
        this.wizardCnt.setScrollableY(true);
        this.wizardCnt.setScrollVisible(false);
        return this.wizardCnt;
    }

    public Container drawHeader(String str, int i, int i2) {
        this.templateColor = i2;
        Container container = new Container(new BoxLayout(2));
        if (this.withHeader) {
            container.getAllStyles().setMargin(1, 0, 0, 0);
            container.getAllStyles().setMarginUnit(2, 2, 2, 2);
            Component drawWizardHeader = new WizardHeader().drawWizardHeader(str, "", i, i2);
            Container container2 = new Container(new BorderLayout());
            FlowLayout flowLayout = new FlowLayout(1, 4);
            flowLayout.setFillRows(true);
            Container container3 = new Container(flowLayout);
            for (int i3 = 0; i3 < this.stepsList.size() - 1; i3++) {
                container3.addComponent(((AbstractStep) this.stepsList.get(i3)).drawStepHeaderCnt(false));
            }
            container2.addComponent("East", ((AbstractStep) this.stepsList.get(this.stepsList.size() - 1)).drawStepHeaderCnt(true));
            container2.addComponent("Center", container3);
            container2.getAllStyles().setPadding(1, 0, 2, 0);
            container2.getAllStyles().setPaddingUnit(2, 2, 2, 2);
            container.addComponent(drawWizardHeader);
            container.addComponent(container2);
        }
        return container;
    }

    public AbstractStep getStepById(int i) {
        selectPreviousSteps(i);
        for (int i2 = 0; i2 < this.stepsList.size(); i2++) {
            if (i2 == i) {
                return (AbstractStep) this.stepsList.get(i2);
            }
        }
        return null;
    }

    public void selectPreviousSteps(int i) {
        if (i == 0) {
            ((AbstractStep) this.stepsList.get(0)).isSelected = true;
            this.header.revalidate();
        } else {
            for (int i2 = 0; i2 < i; i2++) {
                ((AbstractStep) this.stepsList.get(i2)).isSelected = true;
                this.header.revalidate();
            }
        }
    }

    public AbstractStep getPreviousStep(int i) {
        return getStepById(i - 1);
    }

    public AbstractStep getNextStep(int i) {
        return getStepById(i + 1);
    }

    public void goToStepById(AbstractStep abstractStep, int i, String str, int i2, int i3) {
        this.header.removeAll();
        for (int i4 = 0; i4 < this.stepsList.size(); i4++) {
            if (((AbstractStep) this.stepsList.get(i4)).id == i) {
                ((AbstractStep) this.stepsList.get(i4)).isCurrentStep = true;
                ((AbstractStep) this.stepsList.get(i4)).isSelected = true;
            } else {
                ((AbstractStep) this.stepsList.get(i4)).isCurrentStep = false;
            }
        }
        this.header = drawHeader(str, i2, i3);
        this.header.revalidate();
        this.wizardCnt.addComponent("North", this.header);
        SwitchBetweenContainers(this.wizardCnt, abstractStep.stepCnt, getStepById(i).stepCnt, this.slide_to_left);
        this.wizardCnt.revalidate();
        this.currentStepId = i;
    }

    public void goToNextStep(AbstractStep abstractStep, String str, int i, int i2) {
        this.header.removeAll();
        abstractStep.nextStep.isSelected = true;
        for (int i3 = 0; i3 < this.stepsList.size(); i3++) {
            if (((AbstractStep) this.stepsList.get(i3)).id == abstractStep.nextStep.id) {
                ((AbstractStep) this.stepsList.get(i3)).isCurrentStep = true;
            } else {
                ((AbstractStep) this.stepsList.get(i3)).isCurrentStep = false;
            }
        }
        this.header = drawHeader(str, i, i2);
        this.header.revalidate();
        this.wizardCnt.addComponent("North", this.header);
        SwitchBetweenContainers(this.wizardCnt, abstractStep.stepCnt, abstractStep.nextStep.stepCnt, this.slide_to_left);
        this.wizardCnt.revalidate();
        this.currentStepId++;
    }

    public void goToPreviousStep(AbstractStep abstractStep, String str, int i, int i2) {
        this.header.removeAll();
        abstractStep.isSelected = false;
        for (int i3 = 0; i3 < this.stepsList.size(); i3++) {
            if (((AbstractStep) this.stepsList.get(i3)).id == abstractStep.previousStep.id) {
                ((AbstractStep) this.stepsList.get(i3)).isCurrentStep = true;
            } else {
                ((AbstractStep) this.stepsList.get(i3)).isCurrentStep = false;
            }
        }
        this.header = drawHeader(str, i, i2);
        this.header.revalidate();
        this.wizardCnt.addComponent("North", this.header);
        SwitchBetweenContainers(this.wizardCnt, abstractStep.stepCnt, abstractStep.previousStep.stepCnt, !this.slide_to_left);
        this.wizardCnt.revalidate();
        this.currentStepId--;
    }

    public void SwitchBetweenContainers(Container container, Container container2, Container container3, boolean z) {
        try {
            container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
            container.revalidate();
            Display.getInstance().stopEditing(container2);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void setSlidingDirection(boolean z) {
        this.slide_to_left = z;
    }
}
