package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.CIHStyles;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class PlansRetraitePage extends B3GPage {
    public PlansRetraitePage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BorderLayout());
        this.thisContainer.setUIID("");
        try {
            Container container = new Container(BoxLayout.y());
            container.setScrollableY(true);
            container.setScrollVisible(false);
            container.getAllStyles().setBgColor(16382457);
            container.getAllStyles().setBgTransparency(255);
            SpanLabel spanLabel = new SpanLabel("Découvrir nos plans de retraite");
            spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV2");
            spanLabel.getAllStyles().setMarginUnit(1);
            spanLabel.getAllStyles().setMargin(0.0f, 1.5f, 0.0f, 0.0f);
            SpanLabel spanLabel2 = new SpanLabel("CIH BANK vous propose l’offre « Avenir Retraite » qui vous permet de préparer votre retraite en fonction de vos objectifs en termes de pension et de sécurisation de l’avenir de votre famille.");
            spanLabel2.getTextAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.0f)));
            spanLabel2.getTextAllStyles().setAlignment(1);
            spanLabel2.getAllStyles().setPaddingUnit(1);
            spanLabel2.getAllStyles().setPadding(0, 4, 2, 1);
            Button button = new Button("    Je découvre    ", "op_BtnOppositionValidationOrgBg");
            Button button2 = new Button("    Je découvre    ", "op_BtnOppositionValidationOrgBg");
            button.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.5f)));
            button2.getAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.5f)));
            button.addActionListener(new 1());
            button2.addActionListener(new PlansRetraitePage$$ExternalSyntheticLambda0(this));
            button.getAllStyles().setMarginUnit(1);
            button.getAllStyles().setMarginBottom(1);
            button2.getAllStyles().setMarginUnit(1);
            button2.getAllStyles().setMarginBottom(1);
            container.getAllStyles().setPaddingUnit(1);
            container.getAllStyles().setPadding(2, 0, 3, 3);
            ArrayList arrayList = new ArrayList();
            arrayList.add("Une épargne progressive accessible à tout moment.");
            arrayList.add("Des avantages fiscaux sur les cotisations et sur le capital constitué à l’échéance de votre contrat.");
            arrayList.add("Un taux de rendement attractif.");
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add("Une Epargne retraite sécurisée accessible à partir de 50 ans.");
            arrayList2.add("Des avantages fiscaux sur les cotisations et sur le capital constitué à l’échéance de votre contrat.");
            container.add(spanLabel);
            container.add(spanLabel2);
            container.add(getOffreStep1("Avenir retraite – CIH Bank", CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("avenirRetraite.png"), button, "« Avenir Retraite » c’est:", arrayList));
            this.thisContainer.add("Center", container);
        } catch (Exception unused) {
        }
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PlansRetraitePage.this.uiManager.NavigateToPage(new OffreRetaitePage(PlansRetraitePage.this.uiManager, null, 163));
        }
    }

    /* synthetic */ void lambda$GetPageContainer$0$com-b3g-ui-page-PlansRetraitePage(ActionEvent actionEvent) {
        this.uiManager.NavigateToPageById(170);
    }

    private Container getOffreStep1(String str, Image image, Button button, String str2, ArrayList arrayList) {
        Container container = new Container(BoxLayout.y());
        container.getAllStyles().setBgColor(16777215);
        container.getAllStyles().setBgTransparency(255);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMarginBottom(2);
        Container container2 = new Container(BoxLayout.y());
        container2.getAllStyles().setBgColor(CIHStyles.colorBleu);
        container2.getAllStyles().setBgTransparency(255);
        Label label = new Label(str);
        label.getAllStyles().setFgColor(16777215);
        container2.add(FlowLayout.encloseCenterMiddle(label));
        container2.getAllStyles().setPaddingUnit(1);
        container2.getAllStyles().setPadding(1, 1, 0, 0);
        Label label2 = new Label(image);
        label2.getAllStyles().setPaddingUnit(1);
        label2.getAllStyles().setPadding(2, 1, 0, 0);
        Label label3 = new Label(str2);
        Container container3 = new Container(BoxLayout.y());
        container3.getAllStyles().setPaddingUnit(1);
        container3.getAllStyles().setPadding(1, 1, 3, 0);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            container3.add(getPoint(new SpanLabel((String) it.next(), "smallFont"), "point1.png", 0));
        }
        container.add(container2).add(FlowLayout.encloseCenterMiddle(label2)).add(label3).add(container3).add(FlowLayout.encloseCenterMiddle(button));
        return container;
    }

    private Container getPoint(SpanLabel spanLabel, String str, int i) {
        Container container = new Container(new BorderLayout());
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage(str));
        spanLabel.getTextAllStyles().setFont(CIHStyles.getFont(Float.valueOf(2.0f)));
        spanLabel.getTextAllStyles().setAlignment(1);
        spanLabel.getAllStyles().setPaddingUnit(1);
        spanLabel.getAllStyles().setPadding(0, 0, 1, 1);
        label.getAllStyles().setPaddingUnit(1);
        label.getAllStyles().setPadding(1.0f, 1.0f, 0.25f, 0.25f);
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(0, i, 0, 0);
        container.add("West", FlowLayout.encloseIn(label));
        container.add("Center", spanLabel);
        return container;
    }
}
