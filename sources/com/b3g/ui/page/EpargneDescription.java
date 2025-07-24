package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.UIBuilder;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class EpargneDescription extends B3GPage {
    public B3GUiManager uiManager;
    UIBuilder uib = new UIBuilder();

    public EpargneDescription(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        Container container = new Container(new BorderLayout());
        container.setUIID("Whitemargin_2_2_2_2");
        Container container2 = new Container(new BoxLayout(2));
        Container container3 = new Container(new BoxLayout(2));
        Button button = new Button("OUVRIR UN COMPTE D'EPARGNE");
        button.setUIID("op_BtnOppositionValidationV2");
        button.addActionListener(new 1());
        Label label = new Label("Compte d'epargne");
        label.setUIID("crd_CardTitle");
        container2.add(label);
        Container container4 = new Container(new BoxLayout(2));
        container4.setUIID("SV_padding_0_0_2_2");
        SpanLabel spanLabel = new SpanLabel();
        spanLabel.setTextUIID("NightBlueLbl");
        spanLabel.setText("Fructifiez votre épargne et disposez de votre argent à tout moment");
        Container container5 = new Container(new BoxLayout(2));
        SpanLabel spanLabel2 = new SpanLabel();
        spanLabel2.setTextUIID("LblProfileSmalGreyEprn");
        spanLabel2.setText("Le compte sur carnet est un compte gratuit et rémunéré qui vous permet d'épargner de l'argent dans un compte indépendant de votre compte principal.");
        SpanLabel spanLabel3 = new SpanLabel();
        spanLabel3.setTextUIID("LblProfileSmalGreyEprn");
        spanLabel3.setText("L’épargne constituée peut atteindre jusqu’à 400 000 Dirhams.");
        container5.add(spanLabel2);
        container5.add(spanLabel3);
        container5.setUIID("margin_1_1_1_1");
        SpanLabel spanLabel4 = new SpanLabel();
        spanLabel4.setTextUIID("NightBlueLbl");
        Container container6 = new Container(new BoxLayout(2));
        spanLabel4.setText("Ses avantages ? ");
        container6.setUIID("margin_1_1_1_1");
        SpanLabel spanLabel5 = new SpanLabel();
        spanLabel5.setTextUIID("LblProfileSmalGreyEprn");
        spanLabel5.setText("Bénéficiez d’une rémunération versée chaque fin de trimestre ; Profitez d’un système de date de valeur exclusif sur la place, et bénéficiez ainsi de la rémunération le jour du versement ; Alimentez votre compte sur carnet en ligne par des virements occasionnels ou permanents, ou dans n’importe quelle agence par des versements espèces; Disposez de votre argent à tout moment et sans aucune pénalité;");
        Container container7 = new Container(new BoxLayout(1));
        Container container8 = new Container(new BoxLayout(1));
        Container container9 = new Container(new BoxLayout(1));
        Container container10 = new Container(new BoxLayout(1));
        Label label2 = new Label("-");
        label2.setUIID("LblProfileSmalGreyEprn");
        Label label3 = new Label("-");
        label3.setUIID("LblProfileSmalGreyEprn");
        Label label4 = new Label("-");
        label4.setUIID("LblProfileSmalGreyEprn");
        Label label5 = new Label("-");
        label5.setUIID("LblProfileSmalGreyEprn");
        SpanLabel spanLabel6 = new SpanLabel();
        spanLabel6.setText("Vous pouvez l'alimenter en ligne par virement ponctuel ou permanent depuis votre compte courant, ou en espèces dans n’importe quelle agence Cih Bank.");
        spanLabel6.setTextUIID("LblProfileSmalGreyEprn");
        SpanLabel spanLabel7 = new SpanLabel();
        spanLabel7.setText("Votre argent reste disponible et vous pouvez le retirer à tout moment.");
        spanLabel7.setTextUIID("LblProfileSmalGreyEprn");
        SpanLabel spanLabel8 = new SpanLabel();
        spanLabel8.setText("Votre épargne constituée génère une rémunération chaque trimestre, calculée en fonction du montant et de la durée de dépôt à un taux fixé par Bank Al Maghrib.");
        spanLabel8.setTextUIID("LblProfileSmalGreyEprn");
        SpanLabel spanLabel9 = new SpanLabel();
        spanLabel9.setText("Exclusivement chez Cih Bank, vos opérations sont comptabilisées le jour même de leur exécution, sans date de valeur différente, ce qui vous permet de bénéficier d’une meilleure rémunération.");
        spanLabel9.setTextUIID("LblProfileSmalGreyEprn");
        Container container11 = new Container(new BorderLayout());
        Container container12 = new Container(new BorderLayout());
        Container container13 = new Container(new BorderLayout());
        Container container14 = new Container(new BorderLayout());
        container11.add("North", label2);
        container12.add("North", label3);
        container13.add("North", label4);
        container14.add("North", label5);
        container7.add(container11).add(spanLabel6);
        container8.add(container12).add(spanLabel7);
        container9.add(container13).add(spanLabel8);
        container10.add(container14).add(spanLabel9);
        container7.setUIID("margin_1_1_1_1");
        container8.setUIID("margin_1_1_1_1");
        container9.setUIID("margin_1_1_1_1");
        container10.setUIID("margin_1_1_1_1");
        container6.add(container7).add(container8).add(container9).add(container10);
        container4.add(spanLabel);
        container4.add(container5);
        container4.add(spanLabel4);
        container4.add(container6);
        container4.setScrollableY(true);
        container4.setScrollVisible(true);
        Container container15 = new Container(new FlowLayout(4, 4));
        Label label6 = new Label();
        label6.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("Eprn.png"));
        container15.add(label6);
        container2.add(container4);
        container3.add(button);
        container.add("Center", container2);
        container.add("South", container3);
        this.thisContainer = container;
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            EpargneDescription.this.uiManager.NavigateToPageById(123);
        }
    }
}
