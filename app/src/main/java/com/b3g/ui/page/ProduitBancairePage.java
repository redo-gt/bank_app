package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.TransfertDATA;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.CIHStyles;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ProduitBancairePage extends B3GPage {
    private byte[] b = {1, 1, 1, 1};

    public ProduitBancairePage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (TransfertDATA) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            this.thisContainer.setScrollableY(true);
            this.thisContainer.setScrollVisible(false);
            this.thisContainer.add(draw_carte_section());
        } catch (Exception unused) {
            this.thisContainer.setLayout(new BorderLayout());
            this.thisContainer.addComponent("North", this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    public Container getFormPage() {
        Container container = new Container(BoxLayout.y());
        Container container2 = new Container(BoxLayout.y());
        Container container3 = new Container(BoxLayout.y());
        Container container4 = new Container(BoxLayout.y());
        Container container5 = new Container(BoxLayout.y());
        Button button = new Button("Cartes Bancaires", "GoButton");
        Button button2 = new Button("Epargne", "GoButton");
        new Button("CIH Place", "GoButton");
        Button button3 = new Button(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ciel3-removebg-preview.png"));
        button3.setUIID("Container");
        button3.addActionListener(new 1());
        Button button4 = new Button(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ciel2-removebg-preview.png"));
        button4.setUIID("Container");
        button4.addActionListener(new 2());
        Button button5 = new Button(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ciel1-removebg-preview.png"));
        button5.setUIID("Container");
        button5.addActionListener(new 3());
        Button button6 = new Button(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("ciel1-removebg-preview.png"));
        button6.setUIID("Container");
        button6.addActionListener(new 4());
        SpanLabel spanLabel = new SpanLabel();
        spanLabel.setText("découvrez nos produits bancaires");
        spanLabel.setTextUIID("DefaultTitleOrange");
        spanLabel.getAllStyles().setMarginUnit(0);
        spanLabel.getAllStyles().setMargin(0, 5, 0, 0);
        SpanLabel spanLabel2 = new SpanLabel("\u200b", "Container");
        spanLabel2.setText("Commander une carte e-shopping \u200bvirtuelle");
        SpanLabel spanLabel3 = new SpanLabel("Commander une carte bancaire", "Container");
        SpanLabel spanLabel4 = new SpanLabel("Ouvrir un compte sur carnet", "Container");
        SpanLabel spanLabel5 = new SpanLabel("\u200b", "Container");
        spanLabel5.setText("Market Place CIH BANK");
        spanLabel2.getTextAllStyles().setAlignment(4);
        spanLabel3.getTextAllStyles().setAlignment(4);
        spanLabel4.getTextAllStyles().setAlignment(4);
        spanLabel5.getTextAllStyles().setAlignment(4);
        spanLabel2.getAllStyles().setPaddingUnit(1);
        spanLabel2.getAllStyles().setPadding(1.5f, 1.5f, 8.0f, 8.0f);
        spanLabel3.getAllStyles().setPaddingUnit(1);
        spanLabel3.getAllStyles().setPadding(1.5f, 1.5f, 8.0f, 8.0f);
        spanLabel4.getAllStyles().setPaddingUnit(1);
        spanLabel4.getAllStyles().setPadding(1.5f, 1.5f, 8.0f, 8.0f);
        spanLabel5.getAllStyles().setPaddingUnit(1);
        spanLabel5.getAllStyles().setPadding(1.5f, 1.5f, 8.0f, 8.0f);
        container2.add(FlowLayout.encloseCenterMiddle(button5)).add(spanLabel2);
        container2.setLeadComponent(button5);
        container3.add(FlowLayout.encloseCenterMiddle(button4)).add(spanLabel3);
        container3.setLeadComponent(button4);
        container4.add(FlowLayout.encloseCenterMiddle(button3)).add(spanLabel4);
        container4.setLeadComponent(button3);
        container5.add(FlowLayout.encloseCenterMiddle(button6)).add(spanLabel5);
        container5.setLeadComponent(button6);
        container.getAllStyles().setPaddingUnit(1);
        container.getAllStyles().setPadding(1.5f, 1.5f, 1.5f, 1.5f);
        container.add(spanLabel).add(button).add(new Sep()).add(container2).add(button2).add(container4).add(new Sep());
        return container;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ProduitBancairePage.this.uiManager.NavigateToPage(new SavingAccountList(ProduitBancairePage.this.uiManager, null, 163));
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode("Ce service sera disponible prochainement"), null);
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ProduitBancairePage.this.uiManager.NavigateToPage(new OffreEShoppingPage(ProduitBancairePage.this.uiManager, null, 163));
        }
    }

    class 4 implements ActionListener {
        4() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.urlAvantageWebView = "https://www.cihplace.com";
            ProduitBancairePage.this.uiManager.NavigateToPageById(165);
        }
    }

    public Container draw_carte_section() {
        Font font;
        Container container = new Container(BoxLayout.y());
        container.getAllStyles().setPaddingUnit(this.b);
        container.getAllStyles().setPadding(0.0f, 0.0f, 2.0f, 2.0f);
        SpanLabel spanLabel = new SpanLabel("découvrez nos produits bancaires");
        spanLabel.getTextAllStyles().setFgColor(CIHStyles.colorOrange);
        SpanLabel spanLabel2 = new SpanLabel("Découvrir nos plans de retraite");
        spanLabel2.getTextAllStyles().setFgColor(CIHStyles.colorOrange);
        if (Font.isTrueTypeFileSupported()) {
            font = Font.createTrueTypeFont("Aller_Bd", "Aller_Bd.ttf").derive(Display.getInstance().convertToPixels(3.0f), 1);
        } else {
            font = CIHStyles.getFont(Float.valueOf(3.0f), 1);
        }
        spanLabel.getTextAllStyles().setFont(font);
        spanLabel2.getTextAllStyles().setFont(font);
        spanLabel.getAllStyles().setMarginUnit(this.b);
        spanLabel.getAllStyles().setMargin(0.0f, 1.4f, 0.0f, 0.0f);
        spanLabel2.getAllStyles().setMarginUnit(this.b);
        spanLabel2.getAllStyles().setMargin(1.5f, 0.0f, 0.0f, 0.0f);
        Button button = new Button();
        button.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("e-shopping-icon.png"));
        button.addActionListener(new 5());
        Button button2 = new Button();
        button2.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("gamer-icon.png"));
        button2.addActionListener(new 6());
        Button button3 = new Button();
        button3.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("epargne-icon.png"));
        button3.addActionListener(new 7());
        Button button4 = new Button();
        button4.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("noun-savings.png"));
        button4.addActionListener(new 8());
        container.add(spanLabel).add(draw_header("Souscription")).add(draw_sub_header("Cartes Bancaires")).add(draw_btn(button, "Commander une carte e-shopping \u200bvirtuelle", Float.valueOf(18.0f))).add(draw_btn(button2, "Commander une carte CODE GAMER virtuelle", Float.valueOf(18.0f))).add(draw_sub_header("Epargne")).add(draw_btn(button3, "Ouvrir un compte d’épargne \u200b", Float.valueOf(18.0f))).add(spanLabel2).add(draw_header("Découverte")).add(draw_sub_header("Plans de retraite")).add(draw_btn(button4, "Vous souhaitez constituer une retraite ou compléter votre plan de retraite . Découvrez, dès maintenant, comment optimiser votre épargne-retraite ! ", Float.valueOf(2.0f)));
        return container;
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ProduitBancairePage.this.uiManager.NavigateToPage(new OffreEShoppingPage(ProduitBancairePage.this.uiManager, null, 163));
        }
    }

    class 6 implements ActionListener {
        6() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ProduitBancairePage.this.uiManager.NavigateToPage(new OffreGamerPage(ProduitBancairePage.this.uiManager, null, 173));
        }
    }

    class 7 implements ActionListener {
        7() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ProduitBancairePage.this.uiManager.NavigateToPage(new SavingAccountList(ProduitBancairePage.this.uiManager, null, 163));
        }
    }

    class 8 implements ActionListener {
        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ProduitBancairePage.this.uiManager.NavigateToPageById(172);
        }
    }

    public Container draw_header(String str) {
        Font font;
        Container container = new Container(new BorderLayout());
        Label label = new Label(str);
        label.getAllStyles().setFgColor(1945583);
        if (Font.isTrueTypeFileSupported()) {
            font = Font.createTrueTypeFont("Aller_Bd", "Aller_Bd.ttf").derive(Display.getInstance().convertToPixels(3.0f), 1);
        } else {
            font = CIHStyles.getFont(Float.valueOf(3.0f), 1);
        }
        label.getAllStyles().setFont(font);
        container.getAllStyles().setBgColor(CIHStyles.colorWhite);
        container.getAllStyles().setBgTransparency(255);
        Border createLineBorder = Border.createLineBorder(4, CIHStyles.colorOrange);
        Border createLineBorder2 = Border.createLineBorder(3, 13224393);
        Border createCompoundBorder = Border.createCompoundBorder(createLineBorder, createLineBorder2, createLineBorder2, createLineBorder2);
        container.getAllStyles().setPaddingUnit(this.b);
        container.getAllStyles().setPadding(1.6f, 1.6f, 4.0f, 4.0f);
        container.getAllStyles().setMarginUnit(this.b);
        container.getAllStyles().setMargin(0.5f, 1.0f, 0.0f, 0.0f);
        container.add("Center", label);
        container.getAllStyles().setBorder(createCompoundBorder);
        return container;
    }

    public Container draw_sub_header(String str) {
        Font font;
        Container container = new Container(new FlowLayout(4, 4));
        Label label = new Label(str);
        if (Font.isTrueTypeFileSupported()) {
            font = Font.createTrueTypeFont("Poppins-Regular", "Poppins-Regular.ttf").derive(Display.getInstance().convertToPixels(2.4f), 0);
        } else {
            font = CIHStyles.getFont(Float.valueOf(2.4f), 0);
        }
        label.getAllStyles().setFont(font);
        label.getAllStyles().setFgColor(16777215);
        container.getAllStyles().setPaddingUnit(this.b);
        container.getAllStyles().setPadding(0.6f, 0.6f, 2.0f, 2.0f);
        container.getAllStyles().setMarginUnit(this.b);
        container.getAllStyles().setMargin(0.5f, 0.5f, 0.0f, 0.0f);
        container.getAllStyles().setBgColor(CIHStyles.colorOrange);
        container.getAllStyles().setBgTransparency(255);
        container.add(label);
        return container;
    }

    public Container draw_btn(Button button, String str, Float f) {
        Font font;
        Container container = new Container(BoxLayout.yCenter());
        Border createLineBorder = Border.createLineBorder(2, CIHStyles.colorOrange);
        Border createLineBorder2 = Border.createLineBorder(1, 13224393);
        Border createCompoundBorder = Border.createCompoundBorder(createLineBorder, createLineBorder2, createLineBorder2, createLineBorder2);
        container.getAllStyles().setBgColor(CIHStyles.colorWhite);
        container.getAllStyles().setBgTransparency(255);
        container.getAllStyles().setBorder(createCompoundBorder);
        container.getAllStyles().setPaddingUnit(this.b);
        container.getAllStyles().setPadding(1.2f, 1.2f, f.floatValue(), f.floatValue());
        container.getAllStyles().setMarginUnit(this.b);
        container.getAllStyles().setMargin(0.1f, 0.5f, 0.0f, 0.0f);
        if (Font.isTrueTypeFileSupported()) {
            font = Font.createTrueTypeFont("Poppins-Regular", "Poppins-Regular.ttf").derive(Display.getInstance().convertToPixels(2.0f), 1);
        } else {
            font = CIHStyles.getFont(Float.valueOf(2.0f), 1);
        }
        SpanLabel spanLabel = new SpanLabel(str);
        spanLabel.setTextUIID("Container");
        spanLabel.getTextAllStyles().setFont(font);
        spanLabel.getTextAllStyles().setAlignment(4);
        spanLabel.getTextAllStyles().setPaddingUnit(this.b);
        spanLabel.getTextAllStyles().setPadding(0.5f, 0.5f, 0.0f, 0.0f);
        container.add(FlowLayout.encloseCenterMiddle(button)).add(spanLabel);
        button.setUIID("Container");
        container.setLeadComponent(button);
        return container;
    }

    private class Sep extends Container {
        public Sep() {
            getAllStyles().setBgColor(15484444);
            getAllStyles().setBgTransparency(255);
            getAllStyles().setPaddingUnit(1);
            getAllStyles().setPadding(0.25f, 0.0f, 0.0f, 0.0f);
            getAllStyles().setMarginUnit(1);
            getAllStyles().setMargin(0.5f, 0.5f, 0.0f, 0.0f);
        }
    }
}
