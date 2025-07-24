package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.services.CardPlafond;
import com.b3g.services.ServiceManager;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.SliderPlafond;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.StringUtil;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CardNationalPlafond1 extends B3GPage {
    String BtnDureeChgmt1;
    String BtnPlafondCommerce1;
    String BtnPlafondCommerceRes;
    String BtnPlafondPaiement1;
    String BtnPlafondPaiementRes;
    String BtnPlafondRetr1;
    String BtnPlafondRetrRes;
    String DureeChgmt;
    String DureeInitial;
    String DureeTemp;
    String IsDisabledCom;
    String IsDisabledDuree;
    String IsDisabledPaim;
    String IsDisabledRtr;
    String IsRemovedCardLimitsCom;
    String IsRemovedCardLimitsPaiem;
    String IsRemovedCardLimitsRtr;
    String PlfdComTemp;
    String PlfdPaimTemp;
    String PlfdRetrTemp;
    SliderPlafond SliderCommerce;
    SliderPlafond SliderDuree;
    SliderPlafond SliderPaiement;
    SliderPlafond SliderRetrait;
    String codeStatus;
    String comChgmt;
    Card crd;
    Container ctnNext;
    String paimChgmt;
    String retrChgmt;
    String statusLbl;
    UIBuilder uib = new UIBuilder();
    String actionRtr = "ACTIVER";
    String actionPaiem = "ACTIVER";
    String actionCom = "ACTIVER";
    String actionDuree = "LIMITER";
    private int btnClickPlafondRtr = 0;
    private int btnClickPlafondPaiement = 0;
    private int btnClickPlafondCommerce = 0;
    private int btnClickDurre = 0;
    StringTools stringTools = new StringTools();
    boolean flagRet = false;
    final Container rsltCnt = new Container();

    static /* synthetic */ int access$000(CardNationalPlafond1 cardNationalPlafond1) {
        return cardNationalPlafond1.btnClickPlafondPaiement;
    }

    static /* synthetic */ int access$002(CardNationalPlafond1 cardNationalPlafond1, int i) {
        cardNationalPlafond1.btnClickPlafondPaiement = i;
        return i;
    }

    static /* synthetic */ int access$012(CardNationalPlafond1 cardNationalPlafond1, int i) {
        int i2 = cardNationalPlafond1.btnClickPlafondPaiement + i;
        cardNationalPlafond1.btnClickPlafondPaiement = i2;
        return i2;
    }

    static /* synthetic */ int access$100(CardNationalPlafond1 cardNationalPlafond1) {
        return cardNationalPlafond1.btnClickPlafondCommerce;
    }

    static /* synthetic */ int access$102(CardNationalPlafond1 cardNationalPlafond1, int i) {
        cardNationalPlafond1.btnClickPlafondCommerce = i;
        return i;
    }

    static /* synthetic */ int access$112(CardNationalPlafond1 cardNationalPlafond1, int i) {
        int i2 = cardNationalPlafond1.btnClickPlafondCommerce + i;
        cardNationalPlafond1.btnClickPlafondCommerce = i2;
        return i2;
    }

    static /* synthetic */ int access$200(CardNationalPlafond1 cardNationalPlafond1) {
        return cardNationalPlafond1.btnClickDurre;
    }

    static /* synthetic */ int access$202(CardNationalPlafond1 cardNationalPlafond1, int i) {
        cardNationalPlafond1.btnClickDurre = i;
        return i;
    }

    static /* synthetic */ int access$212(CardNationalPlafond1 cardNationalPlafond1, int i) {
        int i2 = cardNationalPlafond1.btnClickDurre + i;
        cardNationalPlafond1.btnClickDurre = i2;
        return i2;
    }

    static /* synthetic */ int access$300(CardNationalPlafond1 cardNationalPlafond1) {
        return cardNationalPlafond1.btnClickPlafondRtr;
    }

    static /* synthetic */ int access$302(CardNationalPlafond1 cardNationalPlafond1, int i) {
        cardNationalPlafond1.btnClickPlafondRtr = i;
        return i;
    }

    static /* synthetic */ int access$312(CardNationalPlafond1 cardNationalPlafond1, int i) {
        int i2 = cardNationalPlafond1.btnClickPlafondRtr + i;
        cardNationalPlafond1.btnClickPlafondRtr = i2;
        return i2;
    }

    public CardNationalPlafond1(Object obj, Object obj2, int i, Container container) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        this.ctnNext = container;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        Button button;
        String str;
        Button button2;
        Button button3;
        Container container;
        Button button4;
        Button button5;
        Container container2;
        Container container3;
        Button button6;
        Button button7;
        Container container4;
        Container container5;
        Container container6;
        Container container7;
        Container container8;
        int i;
        int i2;
        int i3;
        Button button8;
        Button button9;
        Button button10;
        Button button11;
        Button button12;
        Button button13;
        Button button14;
        Container container9;
        Container container10;
        Container container11;
        Container container12;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        boolean z;
        Button button15;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        String str17;
        Button button16;
        boolean z2;
        String str18;
        String str19;
        String str20;
        String str21;
        String str22;
        String str23;
        String str24;
        Button button17;
        String str25;
        Button button18;
        CihMBanking.exitApplication = true;
        this.crd = (Card) this.service;
        String str26 = "false";
        this.crd = CardPlafondProcess(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, "false");
        CihMBanking.sesPMTR.listAncPlafonds = this.crd;
        int intValue = this.crd.productStep.intValue();
        int intValue2 = (CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue() - CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue()) / intValue;
        this.thisContainer = new Container(new BoxLayout(2));
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "GloabalContainerV2");
        this.uiManager.stateMachine.findLblGlobalHeaderTitle(createContainer).remove();
        Container createContainer2 = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "CardPlafondUpdate");
        Button button19 = (Button) this.uib.findByName("BtnPlafondRetr", createContainer2);
        Button button20 = (Button) this.uib.findByName("lblPlafondRtr", createContainer2);
        Button button21 = (Button) this.uib.findByName("BtnPlafDepRtr", createContainer2);
        Button button22 = (Button) this.uib.findByName("BtnADRtr", createContainer2);
        Button button23 = (Button) this.uib.findByName("BtnPlafondPaiement", createContainer2);
        Button button24 = (Button) this.uib.findByName("lblPlafondPaiement", createContainer2);
        Button button25 = (Button) this.uib.findByName("BtnPlafDepPaim", createContainer2);
        Button button26 = (Button) this.uib.findByName("BtnADPaim", createContainer2);
        Button button27 = (Button) this.uib.findByName("BtnPlafondCommerce", createContainer2);
        Button button28 = (Button) this.uib.findByName("lblPlafondCommerce", createContainer2);
        Button button29 = (Button) this.uib.findByName("BtnPlafDepCom", createContainer2);
        Button button30 = (Button) this.uib.findByName("BtnADCom", createContainer2);
        Button button31 = button29;
        Button button32 = (Button) this.uib.findByName("BtnDureeChgmt", createContainer2);
        Button button33 = (Button) this.uib.findByName("lblDureeChgmt", createContainer2);
        Button button34 = (Button) this.uib.findByName("BtnADDuree", createContainer2);
        Container container13 = (Container) this.uib.findByName("Container11", createContainer2);
        Container container14 = (Container) this.uib.findByName("Container131", createContainer2);
        Container container15 = (Container) this.uib.findByName("Container133", createContainer2);
        Container container16 = (Container) this.uib.findByName("Container135", createContainer2);
        Container container17 = (Container) this.uib.findByName("Container5", createContainer2);
        Container container18 = (Container) this.uib.findByName("Container51", createContainer2);
        Container container19 = (Container) this.uib.findByName("Container52", createContainer2);
        Container container20 = (Container) this.uib.findByName("Container53", createContainer2);
        Container container21 = (Container) this.uib.findByName("Container6", createContainer2);
        Container container22 = (Container) this.uib.findByName("Container61", createContainer2);
        Container container23 = (Container) this.uib.findByName("Container62", createContainer2);
        Container container24 = (Container) this.uib.findByName("Container63", createContainer2);
        Button button35 = button23;
        Container container25 = (Container) this.uib.findByName("Container", createContainer2);
        Container container26 = (Container) this.uib.findByName("Container2", createContainer2);
        Container container27 = (Container) this.uib.findByName("Container4", createContainer2);
        Container container28 = (Container) this.uib.findByName("Container3", createContainer2);
        Container container29 = new Container();
        Container container30 = container28;
        container29.setUIID("EmptyContainer");
        String str27 = "Container";
        CihMBanking.sesPMTR.PlafondNext = false;
        int i4 = 0;
        while (true) {
            button = button30;
            str = str26;
            if (i4 >= CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.size()) {
                break;
            }
            CardPlafond cardPlafond = (CardPlafond) CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.get(i4);
            Container container31 = container29;
            int i5 = i4;
            if (cardPlafond.PlafondType.equals("RetraitNat")) {
                Container container32 = container25;
                container8 = container31;
                Button button36 = button31;
                button6 = button32;
                button7 = button34;
                container4 = container17;
                container5 = container18;
                container6 = container19;
                container7 = container20;
                Container container33 = container26;
                Container container34 = container27;
                Container container35 = container30;
                i = i5;
                Button button37 = button27;
                button4 = button26;
                Button button38 = button25;
                button11 = button35;
                String str28 = str27;
                Button button39 = button22;
                Button button40 = button21;
                Button button41 = button19;
                container2 = createContainer2;
                container3 = createContainer;
                this.SliderRetrait = new SliderPlafond(0, intValue2, cardPlafond.Plafond.intValue(), intValue, null, "Dhs", CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue(), CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), "sliderRtr", this.ctnNext);
                button41.setText(formatNumber(cardPlafond.Plafond.intValue(), " ") + " Dhs");
                this.PlfdRetrTemp = button41.getText();
                this.BtnPlafondRetr1 = formatNumber(cardPlafond.Plafond.intValue(), " ");
                this.retrChgmt = button41.getText();
                if (cardPlafond.IsRemovedCardLimits.equals("true")) {
                    this.IsRemovedCardLimitsRtr = "true";
                    button17 = button40;
                    button17.setIcon(this.uiManager.ressource.getImage("off.png 2"));
                    str22 = "Plafonner";
                    button17.setText(str22);
                    this.SliderRetrait.ValueSlider.setProgress((int) (cardPlafond.Plafond.doubleValue() / intValue));
                    str19 = "g_lblDashBoardTitleGREY";
                    this.SliderRetrait.sliderTitle.setUIID(str19);
                    this.SliderRetrait.ValueSlider.setEditable(false);
                    str23 = "Déplafonné";
                    this.SliderRetrait.sliderTitle.setText(str23);
                    if (cardPlafond.IsDisabled.equals("true")) {
                        str24 = "Bloqué";
                        this.retrChgmt = str24;
                        str20 = "DESACTIVER";
                        this.actionRtr = str20;
                        this.BtnPlafondRetrRes = str24;
                        button41.setText(str24);
                        this.SliderRetrait.sliderTitle.setUIID(str19);
                        this.SliderRetrait.sliderTitle.setText(str24);
                        i2 = intValue2;
                        i3 = intValue;
                        str25 = "on.png 1";
                        str18 = str;
                        str21 = "DEPLAFONNER";
                    } else {
                        str20 = "DESACTIVER";
                        str24 = "Bloqué";
                        this.retrChgmt = str23;
                        str21 = "DEPLAFONNER";
                        this.actionRtr = str21;
                        this.BtnPlafondRetrRes = str23;
                        button41.setText(str23);
                        i2 = intValue2;
                        i3 = intValue;
                        str25 = "on.png 1";
                        str18 = str;
                    }
                } else {
                    str18 = str;
                    str19 = "g_lblDashBoardTitleGREY";
                    str20 = "DESACTIVER";
                    str21 = "DEPLAFONNER";
                    str22 = "Plafonner";
                    str23 = "Déplafonné";
                    str24 = "Bloqué";
                    button17 = button40;
                    this.IsRemovedCardLimitsRtr = str18;
                    i3 = intValue;
                    str25 = "on.png 1";
                    button17.setIcon(this.uiManager.ressource.getImage(str25));
                    button17.setText("Déplafonner");
                    i2 = intValue2;
                    this.SliderRetrait.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
                    this.SliderRetrait.ValueSlider.setEditable(true);
                    this.SliderRetrait.sliderTitle.setText(button41.getText());
                    this.BtnPlafondRetrRes = button41.getText();
                }
                if (cardPlafond.IsDisabled.equals("true")) {
                    this.IsDisabledRtr = "true";
                    button18 = button39;
                    button18.setIcon(this.uiManager.ressource.getImage("off.png 2"));
                    button18.setText("Activer");
                    this.retrChgmt = str24;
                    this.SliderRetrait.ValueSlider.setEditable(false);
                    button17.setEnabled(false);
                    button41.setText(str24);
                    button17.setIcon(this.uiManager.ressource.getImage("off.png 2"));
                    button17.setText(str22);
                    this.IsRemovedCardLimitsRtr = "true";
                    this.BtnPlafondRetrRes = str24;
                    button41.setText(str24);
                    this.actionRtr = str20;
                    this.SliderRetrait.sliderTitle.setUIID(str19);
                    this.SliderRetrait.sliderTitle.setText(str24);
                } else {
                    button18 = button39;
                    this.IsDisabledRtr = str18;
                    button18.setIcon(this.uiManager.ressource.getImage(str25));
                    button18.setText("Bloquer");
                    this.SliderRetrait.ValueSlider.setEditable(true);
                    if (cardPlafond.IsRemovedCardLimits.equals("true")) {
                        this.SliderRetrait.ValueSlider.setEditable(false);
                        this.actionRtr = str21;
                        this.BtnPlafondRetrRes = str23;
                    } else {
                        this.SliderRetrait.ValueSlider.setEnabled(true);
                        this.actionRtr = "PLAFONNER";
                        this.BtnPlafondRetrRes = button41.getText();
                    }
                    this.retrChgmt = button41.getText();
                    this.SliderRetrait.sliderTitle.setHidden(false);
                }
                if (cardPlafond.ToDisplay.equals(str18)) {
                    container32.setHidden(true);
                    container33.setUIID(str28);
                    container34.setUIID("acd_AccountOperationItemV222");
                    container35.setUIID(str28);
                    container9 = container32;
                    str3 = str28;
                    container11 = container34;
                    container12 = container35;
                    button5 = button18;
                    button8 = button17;
                    button9 = button41;
                    str2 = str18;
                    button12 = button;
                    button13 = button36;
                    button14 = button37;
                    button10 = button38;
                    container10 = container33;
                } else {
                    button5 = button18;
                    button8 = button17;
                    button9 = button41;
                    str2 = str18;
                    button12 = button;
                    container9 = container32;
                    button13 = button36;
                    container10 = container33;
                    container11 = container34;
                    container12 = container35;
                    button14 = button37;
                    button10 = button38;
                    str3 = str28;
                }
            } else {
                Button button42 = button27;
                button4 = button26;
                Button button43 = button25;
                button5 = button22;
                Button button44 = button21;
                Button button45 = button19;
                container2 = createContainer2;
                container3 = createContainer;
                Button button46 = button31;
                button6 = button32;
                button7 = button34;
                container4 = container17;
                container5 = container18;
                container6 = container19;
                container7 = container20;
                Button button47 = button35;
                Container container36 = container25;
                Container container37 = container26;
                Container container38 = container27;
                Container container39 = container30;
                String str29 = str27;
                container8 = container31;
                i = i5;
                i2 = intValue2;
                i3 = intValue;
                if (cardPlafond.PlafondType.equals("PaiementNat")) {
                    button8 = button44;
                    button9 = button45;
                    this.SliderPaiement = new SliderPlafond(0, i2, cardPlafond.Plafond.intValue(), i3, null, "Dhs", CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue(), CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), "sliderPm", this.ctnNext);
                    button47.setText(formatNumber(cardPlafond.Plafond.intValue(), " ") + " Dhs");
                    this.PlfdPaimTemp = button47.getText();
                    this.BtnPlafondPaiement1 = formatNumber(cardPlafond.Plafond.intValue(), " ");
                    this.paimChgmt = button47.getText();
                    if (cardPlafond.IsRemovedCardLimits.equals("true")) {
                        this.IsRemovedCardLimitsPaiem = "true";
                        button15 = button43;
                        button15.setIcon(this.uiManager.ressource.getImage("off.png 2"));
                        str17 = "Plafonner";
                        button15.setText(str17);
                        this.SliderPaiement.ValueSlider.setProgress((int) (cardPlafond.Plafond.doubleValue() / i3));
                        str16 = "g_lblDashBoardTitleGREY";
                        this.SliderPaiement.sliderTitle.setUIID(str16);
                        this.SliderPaiement.ValueSlider.setEditable(false);
                        str15 = "Déplafonné";
                        this.SliderPaiement.sliderTitle.setText(str15);
                        if (cardPlafond.IsDisabled.equals("true")) {
                            str14 = "Bloqué";
                            this.paimChgmt = str14;
                            str13 = "DESACTIVER";
                            this.actionPaiem = str13;
                            this.BtnPlafondPaiementRes = str14;
                            this.SliderPaiement.sliderTitle.setUIID(str16);
                            button47.setText(str14);
                            this.SliderPaiement.sliderTitle.setText(str14);
                            i3 = i3;
                            str11 = "DEPLAFONNER";
                        } else {
                            str13 = "DESACTIVER";
                            str14 = "Bloqué";
                            this.paimChgmt = str15;
                            str11 = "DEPLAFONNER";
                            this.actionPaiem = str11;
                            this.BtnPlafondPaiementRes = str15;
                            button47.setText(str15);
                            i3 = i3;
                        }
                        str12 = str;
                    } else {
                        button15 = button43;
                        str11 = "DEPLAFONNER";
                        str12 = str;
                        str13 = "DESACTIVER";
                        str14 = "Bloqué";
                        str15 = "Déplafonné";
                        str16 = "g_lblDashBoardTitleGREY";
                        str17 = "Plafonner";
                        this.IsRemovedCardLimitsPaiem = str12;
                        button15.setIcon(this.uiManager.ressource.getImage("on.png 1"));
                        button15.setText("Déplafonner");
                        this.SliderPaiement.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
                        this.SliderPaiement.ValueSlider.setEditable(true);
                        this.SliderPaiement.sliderTitle.setText(button47.getText());
                        this.BtnPlafondPaiementRes = button47.getText();
                    }
                    if (cardPlafond.IsDisabled.equals("true")) {
                        this.IsDisabledPaim = "true";
                        button16 = button4;
                        button16.setIcon(this.uiManager.ressource.getImage("off.png 2"));
                        button16.setText("Activer");
                        this.paimChgmt = str14;
                        this.SliderPaiement.ValueSlider.setEditable(false);
                        button15.setEnabled(false);
                        button15.setIcon(this.uiManager.ressource.getImage("off.png 2"));
                        button15.setText(str17);
                        this.IsRemovedCardLimitsPaiem = "true";
                        button47.setText(str14);
                        this.BtnPlafondPaiementRes = str14;
                        this.actionPaiem = str13;
                        this.SliderPaiement.sliderTitle.setUIID(str16);
                        this.SliderPaiement.sliderTitle.setText(str14);
                        z2 = true;
                    } else {
                        button16 = button4;
                        this.IsDisabledPaim = str12;
                        button16.setIcon(this.uiManager.ressource.getImage("on.png 1"));
                        button16.setText("Bloquer");
                        this.paimChgmt = button47.getText();
                        if (cardPlafond.IsRemovedCardLimits.equals("true")) {
                            this.SliderPaiement.ValueSlider.setEditable(false);
                            this.actionPaiem = str11;
                            this.BtnPlafondPaiementRes = str15;
                            z2 = true;
                        } else {
                            z2 = true;
                            this.SliderPaiement.ValueSlider.setEditable(true);
                            this.actionPaiem = "PLAFONNER";
                            this.BtnPlafondPaiementRes = button47.getText();
                        }
                        button15.setEnabled(z2);
                        this.SliderPaiement.sliderTitle.setHidden(false);
                    }
                    if (cardPlafond.ToDisplay.equals(str12)) {
                        container37.setHidden(z2);
                        container9 = container36;
                        container9.setUIID("acd_AccountOperationItemV222");
                        container38.setUIID(str29);
                        container39.setUIID("acd_AccountOperationItemV222");
                        container11 = container38;
                        button4 = button16;
                        container12 = container39;
                        button10 = button15;
                        button11 = button47;
                        str2 = str12;
                        button12 = button;
                        button13 = button46;
                        button14 = button42;
                        container10 = container37;
                        str3 = str29;
                    } else {
                        button4 = button16;
                        button10 = button15;
                        button11 = button47;
                        str2 = str12;
                        button12 = button;
                        button13 = button46;
                        button14 = button42;
                        container9 = container36;
                        container10 = container37;
                        container11 = container38;
                        container12 = container39;
                        str3 = str29;
                    }
                } else {
                    button8 = button44;
                    button9 = button45;
                    if (cardPlafond.PlafondType.equals("EComNat")) {
                        button10 = button43;
                        button11 = button47;
                        this.SliderCommerce = new SliderPlafond(0, i2, cardPlafond.Plafond.intValue(), i3, null, "Dhs", CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue(), CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), "sliderCm", this.ctnNext);
                        button14 = button42;
                        button14.setText(formatNumber(cardPlafond.Plafond.intValue(), " ") + " Dhs");
                        this.PlfdComTemp = button14.getText();
                        this.BtnPlafondCommerce1 = Integer.toString(cardPlafond.Plafond.intValue());
                        this.comChgmt = button14.getText();
                        if (cardPlafond.IsRemovedCardLimits.equals("true")) {
                            this.IsRemovedCardLimitsCom = "true";
                            button13 = button46;
                            button13.setIcon(this.uiManager.ressource.getImage("off.png 2"));
                            str9 = "Plafonner";
                            button13.setText(str9);
                            this.SliderCommerce.ValueSlider.setProgress((int) (cardPlafond.Plafond.doubleValue() / i3));
                            str8 = "g_lblDashBoardTitleGREY";
                            this.SliderCommerce.sliderTitle.setUIID(str8);
                            this.SliderCommerce.ValueSlider.setEditable(false);
                            str7 = "Déplafonné";
                            this.SliderCommerce.sliderTitle.setText(str7);
                            if (cardPlafond.IsDisabled.equals("true")) {
                                str6 = "Bloqué";
                                this.comChgmt = str6;
                                str5 = "DESACTIVER";
                                this.actionCom = str5;
                                this.BtnPlafondCommerceRes = str6;
                                button14.setText(str6);
                                this.SliderCommerce.sliderTitle.setUIID(str8);
                                this.SliderCommerce.sliderTitle.setText(str6);
                                i3 = i3;
                                str10 = "on.png 1";
                                str4 = "DEPLAFONNER";
                            } else {
                                str5 = "DESACTIVER";
                                str6 = "Bloqué";
                                this.comChgmt = str7;
                                str4 = "DEPLAFONNER";
                                this.actionCom = str4;
                                this.BtnPlafondCommerceRes = str7;
                                button14.setText(str7);
                                i3 = i3;
                                str10 = "on.png 1";
                            }
                            str2 = str;
                        } else {
                            button13 = button46;
                            str4 = "DEPLAFONNER";
                            str2 = str;
                            str5 = "DESACTIVER";
                            str6 = "Bloqué";
                            str7 = "Déplafonné";
                            str8 = "g_lblDashBoardTitleGREY";
                            str9 = "Plafonner";
                            this.IsRemovedCardLimitsCom = str2;
                            str10 = "on.png 1";
                            button13.setIcon(this.uiManager.ressource.getImage(str10));
                            button13.setText("Déplafonner");
                            i3 = i3;
                            this.SliderCommerce.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
                            this.SliderCommerce.ValueSlider.setEditable(true);
                            this.SliderCommerce.sliderTitle.setText(button14.getText());
                            this.BtnPlafondCommerceRes = button14.getText();
                        }
                        if (cardPlafond.IsDisabled.equals("true")) {
                            this.IsDisabledCom = "true";
                            button12 = button;
                            button12.setIcon(this.uiManager.ressource.getImage("off.png 2"));
                            button12.setText("Activer");
                            this.comChgmt = str6;
                            this.SliderCommerce.ValueSlider.setEditable(false);
                            button13.setEnabled(false);
                            button13.setIcon(this.uiManager.ressource.getImage("off.png 2"));
                            button13.setText(str9);
                            this.IsRemovedCardLimitsCom = "true";
                            button14.setText(str6);
                            this.BtnPlafondCommerceRes = str6;
                            this.actionCom = str5;
                            this.SliderCommerce.sliderTitle.setUIID(str8);
                            this.SliderCommerce.sliderTitle.setText(str6);
                            z = true;
                        } else {
                            button12 = button;
                            this.IsDisabledCom = str2;
                            button12.setIcon(this.uiManager.ressource.getImage(str10));
                            button12.setText("Bloquer");
                            this.comChgmt = button14.getText();
                            if (cardPlafond.IsRemovedCardLimits.equals("true")) {
                                this.SliderCommerce.ValueSlider.setEditable(false);
                                this.actionCom = str4;
                                this.BtnPlafondCommerceRes = str7;
                                z = true;
                            } else {
                                z = true;
                                this.SliderCommerce.ValueSlider.setEditable(true);
                                this.actionCom = "PLAFONNER";
                                this.BtnPlafondCommerceRes = button14.getText();
                            }
                            button13.setEnabled(z);
                            this.SliderCommerce.sliderTitle.setHidden(false);
                        }
                        if (cardPlafond.ToDisplay.equals(str2)) {
                            container11 = container38;
                            container11.setHidden(z);
                            container9 = container36;
                            container9.setUIID("acd_AccountOperationItemV222");
                            container10 = container37;
                            str3 = str29;
                            container10.setUIID(str3);
                            container12 = container39;
                            container12.setUIID("acd_AccountOperationItemV222");
                        } else {
                            container9 = container36;
                            container10 = container37;
                            container11 = container38;
                            container12 = container39;
                        }
                    } else {
                        button10 = button43;
                        button11 = button47;
                        button12 = button;
                        button13 = button46;
                        button14 = button42;
                        container9 = container36;
                        container10 = container37;
                        container11 = container38;
                        container12 = container39;
                        str2 = str;
                    }
                    str3 = str29;
                }
            }
            int i6 = i + 1;
            container27 = container11;
            container26 = container10;
            str27 = str3;
            container30 = container12;
            str26 = str2;
            button31 = button13;
            button27 = button14;
            intValue = i3;
            container29 = container8;
            button32 = button6;
            container17 = container4;
            container18 = container5;
            container19 = container6;
            container20 = container7;
            button26 = button4;
            button35 = button11;
            button22 = button5;
            createContainer2 = container2;
            createContainer = container3;
            button21 = button8;
            button25 = button10;
            i4 = i6;
            container25 = container9;
            button30 = button12;
            intValue2 = i2;
            button34 = button7;
            button19 = button9;
        }
        Container container40 = container29;
        Button button48 = button26;
        Button button49 = button25;
        Button button50 = button22;
        Button button51 = button21;
        Button button52 = button19;
        Container container41 = createContainer2;
        Container container42 = createContainer;
        Button button53 = button31;
        Button button54 = button32;
        Button button55 = button34;
        Container container43 = container17;
        Container container44 = container18;
        Container container45 = container19;
        Container container46 = container20;
        Button button56 = button35;
        int i7 = intValue;
        Button button57 = button27;
        if (CihMBanking.sesPMTR.listAncPlafonds.CanRemoveCardLimits.equals("true")) {
            button2 = button49;
        } else {
            button51.setHidden(true);
            button2 = button49;
            button2.setHidden(true);
            button53.setHidden(true);
        }
        this.SliderDuree = new SliderPlafond(0, 6, Double.valueOf(Double.parseDouble(CihMBanking.sesPMTR.listAncPlafonds.EndDate)).intValue(), 0, null, null, 0, 0, "sliderDuree", this.ctnNext);
        button54.setText(CihMBanking.sesPMTR.listAncPlafonds.EndDate);
        this.DureeChgmt = button54.getText();
        this.DureeTemp = button54.getText();
        this.BtnDureeChgmt1 = button54.getText();
        this.DureeInitial = this.SliderDuree.getDurInit(Double.valueOf(Double.parseDouble(button54.getText())));
        if (this.crd.HasUnlimitedDate.equals("true")) {
            this.IsDisabledDuree = "true";
            button3 = button55;
            button3.setIcon(this.uiManager.ressource.getImage("off.png 2"));
            button3.setText("Illimitée");
            this.SliderDuree.ValueSlider.setEditable(false);
            this.SliderDuree.ValueSlider.setProgress(6);
            this.SliderDuree.ValueSlider.getSliderEmptyUnselectedStyle();
            button54.setText("Illimitée");
            this.DureeChgmt = "Illimitée";
            this.SliderDuree.sliderTitle.setText("Illimitée");
            this.SliderDuree.sliderTitle.setUIID("g_lblDashBoardTitleGREY");
            this.actionDuree = "ILLIMITER";
            this.SliderDuree.monthNumber = "ILLIMITEE";
            this.thisContainer.revalidate();
        } else {
            button3 = button55;
            this.IsDisabledDuree = str;
            button3.setIcon(this.uiManager.ressource.getImage("on.png 1"));
            button3.setText("Limitée");
            this.SliderDuree.ValueSlider.setEditable(true);
            this.SliderDuree.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
            button54.setText(this.SliderDuree.sliderTitle.getText());
            this.DureeChgmt = this.DureeInitial;
            this.thisContainer.revalidate();
            this.SliderDuree.getMonthNumber(button54.getText());
            if (this.SliderDuree.monthNumber == null) {
                this.SliderDuree.monthNumber = button54.getText().substring(0, 3);
            }
        }
        container43.addComponent(this.SliderRetrait);
        container44.addComponent(this.SliderPaiement);
        container45.addComponent(this.SliderCommerce);
        container46.addComponent(this.SliderDuree);
        container43.setHidden(true);
        container44.setHidden(true);
        container45.setHidden(true);
        container46.setHidden(true);
        if (!container21.isHidden()) {
            container21.setHidden(true);
        }
        if (!container22.isHidden()) {
            container22.setHidden(true);
        }
        if (container23.isHidden()) {
            container = container23;
        } else {
            container = container23;
            container.setHidden(true);
        }
        if (!container24.isHidden()) {
            container24.setHidden(true);
        }
        Container container47 = container;
        Button button58 = button3;
        Button button59 = button2;
        button52.addActionListener(new 1(container13, container40, container14, container16, container15, container43, container21, button54, button57, button56, button52, button20, container22, container47, container24, container44, container45, container46));
        button56.addActionListener(new 2(container14, container40, container13, container16, container15, container44, container22, button54, button57, button52, button56, button24, container21, container47, container24, container43, container45, container46));
        button57.addActionListener(new 3(container16, container40, container13, container14, container15, container45, container47, button54, button52, button56, button57, button28, container21, container22, container24, container44, container43, container46));
        button54.addActionListener(new 4(container15, container40, container13, container16, container14, container46, container24, button52, button56, button57, button54, button33, container21, container22, container47, container44, container45, container43));
        button51.addActionListener(new 5(button51, i7, button52));
        button50.addActionListener(new 6(button50, button51, button52, i7));
        button59.addActionListener(new 7(button59, i7, button56));
        button48.addActionListener(new 8(button48, button59, button56, i7));
        button53.addActionListener(new 9(button53, i7, button57));
        button.addActionListener(new 10(button, button53, button57, i7));
        button58.addActionListener(new 11(button58, button54));
        container42.addComponent(container41);
        this.thisContainer.addComponent(container42);
        this.thisContainer = this.thisContainer;
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Button val$BtnDureeChgmt;
        final /* synthetic */ Button val$BtnPlafondCommerce;
        final /* synthetic */ Button val$BtnPlafondPaiement;
        final /* synthetic */ Button val$BtnPlafondRetr;
        final /* synthetic */ Container val$Container11;
        final /* synthetic */ Container val$Container131;
        final /* synthetic */ Container val$Container133;
        final /* synthetic */ Container val$Container135;
        final /* synthetic */ Container val$Container6;
        final /* synthetic */ Container val$Container61;
        final /* synthetic */ Container val$Container62;
        final /* synthetic */ Container val$Container63;
        final /* synthetic */ Container val$cntSliderCommerce;
        final /* synthetic */ Container val$cntSliderDuree;
        final /* synthetic */ Container val$cntSliderPaiement;
        final /* synthetic */ Container val$cntSliderRtr;
        final /* synthetic */ Container val$emptyCnt;
        final /* synthetic */ Button val$lblPlafondRtr;

        1(Container container, Container container2, Container container3, Container container4, Container container5, Container container6, Container container7, Button button, Button button2, Button button3, Button button4, Button button5, Container container8, Container container9, Container container10, Container container11, Container container12, Container container13) {
            this.val$Container11 = container;
            this.val$emptyCnt = container2;
            this.val$Container131 = container3;
            this.val$Container135 = container4;
            this.val$Container133 = container5;
            this.val$cntSliderRtr = container6;
            this.val$Container6 = container7;
            this.val$BtnDureeChgmt = button;
            this.val$BtnPlafondCommerce = button2;
            this.val$BtnPlafondPaiement = button3;
            this.val$BtnPlafondRetr = button4;
            this.val$lblPlafondRtr = button5;
            this.val$Container61 = container8;
            this.val$Container62 = container9;
            this.val$Container63 = container10;
            this.val$cntSliderPaiement = container11;
            this.val$cntSliderCommerce = container12;
            this.val$cntSliderDuree = container13;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            boolean z;
            CardNationalPlafond1.access$002(CardNationalPlafond1.this, 0);
            CardNationalPlafond1.access$102(CardNationalPlafond1.this, 0);
            CardNationalPlafond1.access$202(CardNationalPlafond1.this, 0);
            if (CardNationalPlafond1.access$300(CardNationalPlafond1.this) == 0) {
                this.val$Container11.addComponent(0, this.val$emptyCnt);
                this.val$Container131.removeComponent(this.val$emptyCnt);
                this.val$Container135.removeComponent(this.val$emptyCnt);
                this.val$Container133.removeComponent(this.val$emptyCnt);
                CardNationalPlafond1.access$312(CardNationalPlafond1.this, 1);
                z = true;
            } else {
                z = false;
            }
            if (CardNationalPlafond1.access$300(CardNationalPlafond1.this) != 0 && !z) {
                this.val$Container11.removeComponent(this.val$emptyCnt);
                this.val$cntSliderRtr.setUIID("EmptyContainer");
                this.val$Container11.setUIID("EmptyContainer");
                this.val$Container6.setHidden(true);
                this.val$cntSliderRtr.setHidden(true);
                CardNationalPlafond1.access$302(CardNationalPlafond1.this, 0);
            } else {
                this.val$cntSliderRtr.setUIID("Container");
                this.val$Container11.setUIID("Container");
                this.val$Container135.setUIID("EmptyContainer");
                this.val$Container133.setUIID("EmptyContainer");
                this.val$Container131.setUIID("EmptyContainer");
                this.val$BtnDureeChgmt.setHidden(false);
                this.val$BtnPlafondCommerce.setHidden(false);
                this.val$BtnPlafondPaiement.setHidden(false);
                this.val$BtnPlafondRetr.setHidden(false);
                this.val$lblPlafondRtr.setHidden(false);
                this.val$Container6.setHidden(false);
                this.val$Container61.setHidden(true);
                this.val$Container62.setHidden(true);
                this.val$Container63.setHidden(true);
                this.val$cntSliderRtr.setHidden(false);
                this.val$cntSliderPaiement.setHidden(true);
                this.val$cntSliderCommerce.setHidden(true);
                this.val$cntSliderDuree.setHidden(true);
                if (CardNationalPlafond1.this.IsRemovedCardLimitsPaiem.equals("true") || CardNationalPlafond1.this.IsDisabledPaim.equals("true")) {
                    this.val$BtnPlafondPaiement.setText(CardNationalPlafond1.this.paimChgmt);
                    if (this.val$BtnPlafondPaiement.getText().equals("Bloqué")) {
                        CardNationalPlafond1.this.BtnPlafondPaiement1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue());
                    } else if (this.val$BtnPlafondPaiement.getText().equals("Déplafonné")) {
                        CardNationalPlafond1.this.BtnPlafondPaiement1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue());
                    }
                } else {
                    this.val$BtnPlafondPaiement.setText(CardNationalPlafond1.this.SliderPaiement.sliderTitle.getText());
                    CardNationalPlafond1.this.BtnPlafondPaiement1 = this.val$BtnPlafondPaiement.getText().substring(0, this.val$BtnPlafondPaiement.getText().length() - 4);
                }
                if (CardNationalPlafond1.this.IsRemovedCardLimitsCom.equals("true") || CardNationalPlafond1.this.IsDisabledCom.equals("true")) {
                    this.val$BtnPlafondCommerce.setText(CardNationalPlafond1.this.comChgmt);
                    if (this.val$BtnPlafondCommerce.getText().equals("Bloqué")) {
                        CardNationalPlafond1.this.BtnPlafondCommerce1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue());
                    } else if (this.val$BtnPlafondCommerce.getText().equals("Déplafonné")) {
                        CardNationalPlafond1.this.BtnPlafondCommerce1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue());
                    }
                } else {
                    this.val$BtnPlafondCommerce.setText(CardNationalPlafond1.this.SliderCommerce.sliderTitle.getText());
                    CardNationalPlafond1.this.BtnPlafondCommerce1 = this.val$BtnPlafondCommerce.getText().substring(0, this.val$BtnPlafondCommerce.getText().length() - 4);
                }
                if (CardNationalPlafond1.this.IsDisabledDuree.equals("true")) {
                    this.val$BtnDureeChgmt.setText("Illimitée");
                    CardNationalPlafond1.this.BtnDureeChgmt1 = "0.0";
                } else {
                    this.val$BtnDureeChgmt.setText(CardNationalPlafond1.this.SliderDuree.sliderTitle.getText());
                    if (this.val$BtnDureeChgmt.getText().equals("1 Jour")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "1";
                    } else if (this.val$BtnDureeChgmt.getText().equals("7 Jours")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "7";
                    } else if (this.val$BtnDureeChgmt.getText().equals("15 Jours")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "15";
                    } else if (this.val$BtnDureeChgmt.getText().equals("1 Mois")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "30";
                    } else if (this.val$BtnDureeChgmt.getText().equals("3 Mois")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "90";
                    } else if (this.val$BtnDureeChgmt.getText().equals("6 Mois")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "180";
                    } else if (this.val$BtnDureeChgmt.getText().equals("12 Mois")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "365";
                    } else if (this.val$BtnDureeChgmt.getText().length() == 6) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = this.val$BtnDureeChgmt.getText().substring(0, this.val$BtnDureeChgmt.getText().length() - 5);
                    } else if (this.val$BtnDureeChgmt.getText().length() == 7) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = this.val$BtnDureeChgmt.getText().substring(0, this.val$BtnDureeChgmt.getText().length() - 6);
                    } else if (this.val$BtnDureeChgmt.getText().length() == 8) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = this.val$BtnDureeChgmt.getText().substring(0, this.val$BtnDureeChgmt.getText().length() - 7);
                    } else if (this.val$BtnDureeChgmt.getText().length() == 9) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = this.val$BtnDureeChgmt.getText().substring(0, this.val$BtnDureeChgmt.getText().length() - 5);
                    }
                }
            }
            CardNationalPlafond1.this.thisContainer.revalidate();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Button val$BtnDureeChgmt;
        final /* synthetic */ Button val$BtnPlafondCommerce;
        final /* synthetic */ Button val$BtnPlafondPaiement;
        final /* synthetic */ Button val$BtnPlafondRetr;
        final /* synthetic */ Container val$Container11;
        final /* synthetic */ Container val$Container131;
        final /* synthetic */ Container val$Container133;
        final /* synthetic */ Container val$Container135;
        final /* synthetic */ Container val$Container6;
        final /* synthetic */ Container val$Container61;
        final /* synthetic */ Container val$Container62;
        final /* synthetic */ Container val$Container63;
        final /* synthetic */ Container val$cntSliderCommerce;
        final /* synthetic */ Container val$cntSliderDuree;
        final /* synthetic */ Container val$cntSliderPaiement;
        final /* synthetic */ Container val$cntSliderRtr;
        final /* synthetic */ Container val$emptyCnt;
        final /* synthetic */ Button val$lblPlafondPaiement;

        2(Container container, Container container2, Container container3, Container container4, Container container5, Container container6, Container container7, Button button, Button button2, Button button3, Button button4, Button button5, Container container8, Container container9, Container container10, Container container11, Container container12, Container container13) {
            this.val$Container131 = container;
            this.val$emptyCnt = container2;
            this.val$Container11 = container3;
            this.val$Container135 = container4;
            this.val$Container133 = container5;
            this.val$cntSliderPaiement = container6;
            this.val$Container61 = container7;
            this.val$BtnDureeChgmt = button;
            this.val$BtnPlafondCommerce = button2;
            this.val$BtnPlafondRetr = button3;
            this.val$BtnPlafondPaiement = button4;
            this.val$lblPlafondPaiement = button5;
            this.val$Container6 = container8;
            this.val$Container62 = container9;
            this.val$Container63 = container10;
            this.val$cntSliderRtr = container11;
            this.val$cntSliderCommerce = container12;
            this.val$cntSliderDuree = container13;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            boolean z;
            CardNationalPlafond1.access$302(CardNationalPlafond1.this, 0);
            CardNationalPlafond1.access$102(CardNationalPlafond1.this, 0);
            CardNationalPlafond1.access$202(CardNationalPlafond1.this, 0);
            if (CardNationalPlafond1.access$000(CardNationalPlafond1.this) == 0) {
                this.val$Container131.addComponent(0, this.val$emptyCnt);
                this.val$Container11.removeComponent(this.val$emptyCnt);
                this.val$Container135.removeComponent(this.val$emptyCnt);
                this.val$Container133.removeComponent(this.val$emptyCnt);
                CardNationalPlafond1.access$012(CardNationalPlafond1.this, 1);
                z = true;
            } else {
                z = false;
            }
            if (CardNationalPlafond1.access$000(CardNationalPlafond1.this) != 0 && !z) {
                this.val$Container131.removeComponent(this.val$emptyCnt);
                this.val$cntSliderPaiement.setUIID("EmptyContainer");
                this.val$Container131.setUIID("EmptyContainer");
                this.val$Container61.setHidden(true);
                this.val$cntSliderPaiement.setHidden(true);
                CardNationalPlafond1.access$002(CardNationalPlafond1.this, 0);
            } else {
                this.val$cntSliderPaiement.setUIID("Container");
                this.val$Container131.setUIID("Container");
                this.val$Container11.setUIID("EmptyContainer");
                this.val$Container135.setUIID("EmptyContainer");
                this.val$Container133.setUIID("EmptyContainer");
                this.val$BtnDureeChgmt.setHidden(false);
                this.val$BtnPlafondCommerce.setHidden(false);
                this.val$BtnPlafondRetr.setHidden(false);
                this.val$BtnPlafondPaiement.setHidden(false);
                this.val$lblPlafondPaiement.setHidden(false);
                this.val$Container6.setHidden(true);
                this.val$Container61.setHidden(false);
                this.val$Container62.setHidden(true);
                this.val$Container63.setHidden(true);
                this.val$cntSliderPaiement.setHidden(false);
                this.val$cntSliderRtr.setHidden(true);
                this.val$cntSliderCommerce.setHidden(true);
                this.val$cntSliderDuree.setHidden(true);
                if (CardNationalPlafond1.this.IsRemovedCardLimitsRtr.equals("true") || CardNationalPlafond1.this.IsDisabledRtr.equals("true")) {
                    this.val$BtnPlafondRetr.setText(CardNationalPlafond1.this.retrChgmt);
                    if (this.val$BtnPlafondRetr.getText().equals("Bloqué")) {
                        CardNationalPlafond1.this.BtnPlafondRetr1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue());
                    } else if (this.val$BtnPlafondRetr.getText().equals("Déplafonné")) {
                        CardNationalPlafond1.this.BtnPlafondRetr1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue());
                    }
                } else {
                    this.val$BtnPlafondRetr.setText(CardNationalPlafond1.this.SliderRetrait.sliderTitle.getText());
                    CardNationalPlafond1.this.BtnPlafondRetr1 = this.val$BtnPlafondRetr.getText().substring(0, this.val$BtnPlafondRetr.getText().length() - 4);
                }
                if (CardNationalPlafond1.this.IsRemovedCardLimitsCom.equals("true") || CardNationalPlafond1.this.IsDisabledCom.equals("true")) {
                    this.val$BtnPlafondCommerce.setText(CardNationalPlafond1.this.comChgmt);
                    if (this.val$BtnPlafondCommerce.getText().equals("Bloqué")) {
                        CardNationalPlafond1.this.BtnPlafondCommerce1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue());
                    } else if (this.val$BtnPlafondCommerce.getText().equals("Déplafonné")) {
                        CardNationalPlafond1.this.BtnPlafondCommerce1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue());
                    }
                } else {
                    this.val$BtnPlafondCommerce.setText(CardNationalPlafond1.this.SliderCommerce.sliderTitle.getText());
                    CardNationalPlafond1.this.BtnPlafondCommerce1 = this.val$BtnPlafondCommerce.getText().substring(0, this.val$BtnPlafondCommerce.getText().length() - 4);
                }
                if (CardNationalPlafond1.this.IsDisabledDuree.equals("true")) {
                    this.val$BtnDureeChgmt.setText("Illimitée");
                    CardNationalPlafond1.this.BtnDureeChgmt1 = "0.0";
                } else {
                    this.val$BtnDureeChgmt.setText(CardNationalPlafond1.this.SliderDuree.sliderTitle.getText());
                    if (this.val$BtnDureeChgmt.getText().equals("1 Jour")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "1";
                    } else if (this.val$BtnDureeChgmt.getText().equals("7 Jours")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "7";
                    } else if (this.val$BtnDureeChgmt.getText().equals("15 Jours")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "15";
                    } else if (this.val$BtnDureeChgmt.getText().equals("1 Mois")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "30";
                    } else if (this.val$BtnDureeChgmt.getText().equals("3 Mois")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "90";
                    } else if (this.val$BtnDureeChgmt.getText().equals("6 Mois")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "180";
                    } else if (this.val$BtnDureeChgmt.getText().equals("12 Mois")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "365";
                    } else if (this.val$BtnDureeChgmt.getText().length() == 6) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = this.val$BtnDureeChgmt.getText().substring(0, this.val$BtnDureeChgmt.getText().length() - 5);
                    } else if (this.val$BtnDureeChgmt.getText().length() == 7) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = this.val$BtnDureeChgmt.getText().substring(0, this.val$BtnDureeChgmt.getText().length() - 6);
                    } else if (this.val$BtnDureeChgmt.getText().length() == 8) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = this.val$BtnDureeChgmt.getText().substring(0, this.val$BtnDureeChgmt.getText().length() - 7);
                    } else if (this.val$BtnDureeChgmt.getText().length() == 9) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = this.val$BtnDureeChgmt.getText().substring(0, this.val$BtnDureeChgmt.getText().length() - 5);
                    }
                }
            }
            CardNationalPlafond1.this.thisContainer.revalidate();
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Button val$BtnDureeChgmt;
        final /* synthetic */ Button val$BtnPlafondCommerce;
        final /* synthetic */ Button val$BtnPlafondPaiement;
        final /* synthetic */ Button val$BtnPlafondRetr;
        final /* synthetic */ Container val$Container11;
        final /* synthetic */ Container val$Container131;
        final /* synthetic */ Container val$Container133;
        final /* synthetic */ Container val$Container135;
        final /* synthetic */ Container val$Container6;
        final /* synthetic */ Container val$Container61;
        final /* synthetic */ Container val$Container62;
        final /* synthetic */ Container val$Container63;
        final /* synthetic */ Container val$cntSliderCommerce;
        final /* synthetic */ Container val$cntSliderDuree;
        final /* synthetic */ Container val$cntSliderPaiement;
        final /* synthetic */ Container val$cntSliderRtr;
        final /* synthetic */ Container val$emptyCnt;
        final /* synthetic */ Button val$lblPlafondCommerce;

        3(Container container, Container container2, Container container3, Container container4, Container container5, Container container6, Container container7, Button button, Button button2, Button button3, Button button4, Button button5, Container container8, Container container9, Container container10, Container container11, Container container12, Container container13) {
            this.val$Container135 = container;
            this.val$emptyCnt = container2;
            this.val$Container11 = container3;
            this.val$Container131 = container4;
            this.val$Container133 = container5;
            this.val$cntSliderCommerce = container6;
            this.val$Container62 = container7;
            this.val$BtnDureeChgmt = button;
            this.val$BtnPlafondRetr = button2;
            this.val$BtnPlafondPaiement = button3;
            this.val$BtnPlafondCommerce = button4;
            this.val$lblPlafondCommerce = button5;
            this.val$Container6 = container8;
            this.val$Container61 = container9;
            this.val$Container63 = container10;
            this.val$cntSliderPaiement = container11;
            this.val$cntSliderRtr = container12;
            this.val$cntSliderDuree = container13;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            boolean z;
            CardNationalPlafond1.access$002(CardNationalPlafond1.this, 0);
            CardNationalPlafond1.access$302(CardNationalPlafond1.this, 0);
            CardNationalPlafond1.access$202(CardNationalPlafond1.this, 0);
            if (CardNationalPlafond1.access$100(CardNationalPlafond1.this) == 0) {
                this.val$Container135.addComponent(0, this.val$emptyCnt);
                this.val$Container11.removeComponent(this.val$emptyCnt);
                this.val$Container131.removeComponent(this.val$emptyCnt);
                this.val$Container133.removeComponent(this.val$emptyCnt);
                CardNationalPlafond1.access$112(CardNationalPlafond1.this, 1);
                z = true;
            } else {
                z = false;
            }
            if (CardNationalPlafond1.access$100(CardNationalPlafond1.this) != 0 && !z) {
                this.val$Container135.removeComponent(this.val$emptyCnt);
                this.val$cntSliderCommerce.setUIID("EmptyContainer");
                this.val$Container135.setUIID("EmptyContainer");
                this.val$Container62.setHidden(true);
                this.val$cntSliderCommerce.setHidden(true);
                CardNationalPlafond1.access$102(CardNationalPlafond1.this, 0);
            } else {
                this.val$cntSliderCommerce.setUIID("Container");
                this.val$Container135.setUIID("Container");
                this.val$Container133.setUIID("EmptyContainer");
                this.val$Container131.setUIID("EmptyContainer");
                this.val$Container11.setUIID("EmptyContainer");
                this.val$BtnDureeChgmt.setHidden(false);
                this.val$BtnPlafondRetr.setHidden(false);
                this.val$BtnPlafondPaiement.setHidden(false);
                this.val$BtnPlafondCommerce.setHidden(false);
                this.val$lblPlafondCommerce.setHidden(false);
                this.val$Container6.setHidden(true);
                this.val$Container61.setHidden(true);
                this.val$Container62.setHidden(false);
                this.val$Container63.setHidden(true);
                this.val$cntSliderCommerce.setHidden(false);
                this.val$cntSliderPaiement.setHidden(true);
                this.val$cntSliderRtr.setHidden(true);
                this.val$cntSliderDuree.setHidden(true);
                if (CardNationalPlafond1.this.IsRemovedCardLimitsRtr.equals("true") || CardNationalPlafond1.this.IsDisabledRtr.equals("true")) {
                    this.val$BtnPlafondRetr.setText(CardNationalPlafond1.this.retrChgmt);
                    if (this.val$BtnPlafondRetr.getText().equals("Bloqué")) {
                        CardNationalPlafond1.this.BtnPlafondRetr1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue());
                    } else if (this.val$BtnPlafondRetr.getText().equals("Déplafonné")) {
                        CardNationalPlafond1.this.BtnPlafondRetr1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue());
                    }
                } else {
                    this.val$BtnPlafondRetr.setText(CardNationalPlafond1.this.SliderRetrait.sliderTitle.getText());
                    CardNationalPlafond1.this.BtnPlafondRetr1 = this.val$BtnPlafondRetr.getText().substring(0, this.val$BtnPlafondRetr.getText().length() - 4);
                }
                if (CardNationalPlafond1.this.IsRemovedCardLimitsPaiem.equals("true") || CardNationalPlafond1.this.IsDisabledPaim.equals("true")) {
                    this.val$BtnPlafondPaiement.setText(CardNationalPlafond1.this.paimChgmt);
                    if (this.val$BtnPlafondPaiement.getText().equals("Bloqué")) {
                        CardNationalPlafond1.this.BtnPlafondPaiement1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue());
                    } else if (this.val$BtnPlafondPaiement.getText().equals("Déplafonné")) {
                        CardNationalPlafond1.this.BtnPlafondPaiement1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue());
                    }
                } else {
                    this.val$BtnPlafondPaiement.setText(CardNationalPlafond1.this.SliderPaiement.sliderTitle.getText());
                    CardNationalPlafond1.this.BtnPlafondPaiement1 = this.val$BtnPlafondPaiement.getText().substring(0, this.val$BtnPlafondPaiement.getText().length() - 4);
                }
                if (CardNationalPlafond1.this.IsDisabledDuree.equals("true")) {
                    this.val$BtnDureeChgmt.setText("Illimitée");
                    CardNationalPlafond1.this.BtnDureeChgmt1 = "0.0";
                } else {
                    this.val$BtnDureeChgmt.setText(CardNationalPlafond1.this.SliderDuree.sliderTitle.getText());
                    if (this.val$BtnDureeChgmt.getText().equals("1 Jour")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "1";
                    } else if (this.val$BtnDureeChgmt.getText().equals("7 Jours")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "7";
                    } else if (this.val$BtnDureeChgmt.getText().equals("15 Jours")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "15";
                    } else if (this.val$BtnDureeChgmt.getText().equals("1 Mois")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "30";
                    } else if (this.val$BtnDureeChgmt.getText().equals("3 Mois")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "90";
                    } else if (this.val$BtnDureeChgmt.getText().equals("6 Mois")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "180";
                    } else if (this.val$BtnDureeChgmt.getText().equals("12 Mois")) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = "365";
                    } else if (this.val$BtnDureeChgmt.getText().length() == 6) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = this.val$BtnDureeChgmt.getText().substring(0, this.val$BtnDureeChgmt.getText().length() - 5);
                    } else if (this.val$BtnDureeChgmt.getText().length() == 7) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = this.val$BtnDureeChgmt.getText().substring(0, this.val$BtnDureeChgmt.getText().length() - 6);
                    } else if (this.val$BtnDureeChgmt.getText().length() == 8) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = this.val$BtnDureeChgmt.getText().substring(0, this.val$BtnDureeChgmt.getText().length() - 7);
                    } else if (this.val$BtnDureeChgmt.getText().length() == 9) {
                        CardNationalPlafond1.this.BtnDureeChgmt1 = this.val$BtnDureeChgmt.getText().substring(0, this.val$BtnDureeChgmt.getText().length() - 5);
                    }
                }
            }
            CardNationalPlafond1.this.thisContainer.revalidate();
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Button val$BtnDureeChgmt;
        final /* synthetic */ Button val$BtnPlafondCommerce;
        final /* synthetic */ Button val$BtnPlafondPaiement;
        final /* synthetic */ Button val$BtnPlafondRetr;
        final /* synthetic */ Container val$Container11;
        final /* synthetic */ Container val$Container131;
        final /* synthetic */ Container val$Container133;
        final /* synthetic */ Container val$Container135;
        final /* synthetic */ Container val$Container6;
        final /* synthetic */ Container val$Container61;
        final /* synthetic */ Container val$Container62;
        final /* synthetic */ Container val$Container63;
        final /* synthetic */ Container val$cntSliderCommerce;
        final /* synthetic */ Container val$cntSliderDuree;
        final /* synthetic */ Container val$cntSliderPaiement;
        final /* synthetic */ Container val$cntSliderRtr;
        final /* synthetic */ Container val$emptyCnt;
        final /* synthetic */ Button val$lblDureeChgmt;

        4(Container container, Container container2, Container container3, Container container4, Container container5, Container container6, Container container7, Button button, Button button2, Button button3, Button button4, Button button5, Container container8, Container container9, Container container10, Container container11, Container container12, Container container13) {
            this.val$Container133 = container;
            this.val$emptyCnt = container2;
            this.val$Container11 = container3;
            this.val$Container135 = container4;
            this.val$Container131 = container5;
            this.val$cntSliderDuree = container6;
            this.val$Container63 = container7;
            this.val$BtnPlafondRetr = button;
            this.val$BtnPlafondPaiement = button2;
            this.val$BtnPlafondCommerce = button3;
            this.val$BtnDureeChgmt = button4;
            this.val$lblDureeChgmt = button5;
            this.val$Container6 = container8;
            this.val$Container61 = container9;
            this.val$Container62 = container10;
            this.val$cntSliderPaiement = container11;
            this.val$cntSliderCommerce = container12;
            this.val$cntSliderRtr = container13;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            boolean z;
            CardNationalPlafond1.access$002(CardNationalPlafond1.this, 0);
            CardNationalPlafond1.access$302(CardNationalPlafond1.this, 0);
            CardNationalPlafond1.access$102(CardNationalPlafond1.this, 0);
            if (CardNationalPlafond1.access$200(CardNationalPlafond1.this) == 0) {
                this.val$Container133.addComponent(0, this.val$emptyCnt);
                this.val$Container11.removeComponent(this.val$emptyCnt);
                this.val$Container135.removeComponent(this.val$emptyCnt);
                this.val$Container131.removeComponent(this.val$emptyCnt);
                CardNationalPlafond1.access$212(CardNationalPlafond1.this, 1);
                z = true;
            } else {
                z = false;
            }
            if (CardNationalPlafond1.access$200(CardNationalPlafond1.this) != 0 && !z) {
                this.val$Container133.removeComponent(this.val$emptyCnt);
                this.val$cntSliderDuree.setUIID("EmptyContainer");
                this.val$Container133.setUIID("EmptyContainer");
                this.val$Container63.setHidden(true);
                this.val$cntSliderDuree.setHidden(true);
                CardNationalPlafond1.access$202(CardNationalPlafond1.this, 0);
            } else {
                this.val$cntSliderDuree.setUIID("Container");
                this.val$Container133.setUIID("Container");
                this.val$Container135.setUIID("EmptyContainer");
                this.val$Container131.setUIID("EmptyContainer");
                this.val$Container11.setUIID("EmptyContainer");
                this.val$BtnPlafondRetr.setHidden(false);
                this.val$BtnPlafondPaiement.setHidden(false);
                this.val$BtnPlafondCommerce.setHidden(false);
                this.val$BtnDureeChgmt.setHidden(false);
                this.val$lblDureeChgmt.setHidden(false);
                this.val$Container6.setHidden(true);
                this.val$Container61.setHidden(true);
                this.val$Container62.setHidden(true);
                this.val$Container63.setHidden(false);
                this.val$cntSliderPaiement.setHidden(true);
                this.val$cntSliderCommerce.setHidden(true);
                this.val$cntSliderRtr.setHidden(true);
                this.val$cntSliderDuree.setHidden(false);
                if (CardNationalPlafond1.this.IsRemovedCardLimitsRtr.equals("true") || CardNationalPlafond1.this.IsDisabledRtr.equals("true")) {
                    this.val$BtnPlafondRetr.setText(CardNationalPlafond1.this.retrChgmt);
                    if (this.val$BtnPlafondRetr.getText().equals("Bloqué")) {
                        CardNationalPlafond1.this.BtnPlafondRetr1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue());
                    } else if (this.val$BtnPlafondRetr.getText().equals("Déplafonné")) {
                        CardNationalPlafond1.this.BtnPlafondRetr1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue());
                    }
                } else {
                    this.val$BtnPlafondRetr.setText(CardNationalPlafond1.this.SliderRetrait.sliderTitle.getText());
                    CardNationalPlafond1.this.BtnPlafondRetr1 = this.val$BtnPlafondRetr.getText().substring(0, this.val$BtnPlafondRetr.getText().length() - 4);
                }
                if (CardNationalPlafond1.this.IsRemovedCardLimitsPaiem.equals("true") || CardNationalPlafond1.this.IsDisabledPaim.equals("true")) {
                    this.val$BtnPlafondPaiement.setText(CardNationalPlafond1.this.paimChgmt);
                    if (this.val$BtnPlafondPaiement.getText().equals("Bloqué")) {
                        CardNationalPlafond1.this.BtnPlafondPaiement1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue());
                    } else if (this.val$BtnPlafondPaiement.getText().equals("Déplafonné")) {
                        CardNationalPlafond1.this.BtnPlafondPaiement1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue());
                    }
                } else {
                    this.val$BtnPlafondPaiement.setText(CardNationalPlafond1.this.SliderPaiement.sliderTitle.getText());
                    CardNationalPlafond1.this.BtnPlafondPaiement1 = this.val$BtnPlafondPaiement.getText().substring(0, this.val$BtnPlafondPaiement.getText().length() - 4);
                }
                if (CardNationalPlafond1.this.IsRemovedCardLimitsCom.equals("true") || CardNationalPlafond1.this.IsDisabledCom.equals("true")) {
                    this.val$BtnPlafondCommerce.setText(CardNationalPlafond1.this.comChgmt);
                    if (this.val$BtnPlafondCommerce.getText().equals("Bloqué")) {
                        CardNationalPlafond1.this.BtnPlafondCommerce1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue());
                    } else if (this.val$BtnPlafondCommerce.getText().equals("Déplafonné")) {
                        CardNationalPlafond1.this.BtnPlafondCommerce1 = Integer.toString(CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue());
                    }
                } else {
                    this.val$BtnPlafondCommerce.setText(CardNationalPlafond1.this.SliderCommerce.sliderTitle.getText());
                    CardNationalPlafond1.this.BtnPlafondCommerce1 = this.val$BtnPlafondCommerce.getText().substring(0, this.val$BtnPlafondCommerce.getText().length() - 4);
                }
            }
            CardNationalPlafond1.this.thisContainer.revalidate();
        }
    }

    class 5 implements ActionListener {
        final /* synthetic */ Button val$BtnPlafDepRtr;
        final /* synthetic */ Button val$BtnPlafondRetr;
        final /* synthetic */ int val$decimal;

        5(Button button, int i, Button button2) {
            this.val$BtnPlafDepRtr = button;
            this.val$decimal = i;
            this.val$BtnPlafondRetr = button2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.PlafondNext = true;
            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
            CardNationalPlafond1.this.ctnNext.revalidate();
            if (CardNationalPlafond1.this.IsRemovedCardLimitsRtr.equals("false")) {
                CardNationalPlafond1.this.IsRemovedCardLimitsRtr = "true";
                this.val$BtnPlafDepRtr.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("off.png 2"));
                this.val$BtnPlafDepRtr.setText("Plafonner");
                CardNationalPlafond1.this.SliderRetrait.ValueSlider.setProgress((int) (CihMBanking.sesPMTR.listAncPlafonds.Maximum.doubleValue() / this.val$decimal));
                CardNationalPlafond1.this.SliderRetrait.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue(), " ") + " Dhs";
                CardNationalPlafond1.this.SliderRetrait.sliderTitle.setText("Déplafonné");
                CardNationalPlafond1.this.SliderRetrait.sliderTitle.setUIID("g_lblDashBoardTitleGREY");
                CardNationalPlafond1.this.SliderRetrait.ValueSlider.setEditable(false);
                if (CardNationalPlafond1.this.IsDisabledRtr.equals("true")) {
                    CardNationalPlafond1.this.retrChgmt = "Bloqué";
                    CardNationalPlafond1.this.actionRtr = "DESACTIVER";
                } else {
                    CardNationalPlafond1.this.retrChgmt = "Déplafonné";
                    CardNationalPlafond1.this.actionRtr = "DEPLAFONNER";
                }
                CardNationalPlafond1.this.thisContainer.revalidate();
            } else {
                CardNationalPlafond1.this.IsRemovedCardLimitsRtr = "false";
                this.val$BtnPlafDepRtr.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("on.png 1"));
                this.val$BtnPlafDepRtr.setText("Déplafonner");
                if (CardNationalPlafond1.this.IsDisabledRtr.equals("true")) {
                    CardNationalPlafond1.this.retrChgmt = "Bloqué";
                    CardNationalPlafond1.this.actionRtr = "DESACTIVER";
                } else {
                    CardNationalPlafond1.this.retrChgmt = "Activer";
                    CardNationalPlafond1.this.actionRtr = "PLAFONNER";
                }
                Slider slider = CardNationalPlafond1.this.SliderRetrait.ValueSlider;
                CardNationalPlafond1 cardNationalPlafond1 = CardNationalPlafond1.this;
                slider.setProgress(cardNationalPlafond1.formatNumber(cardNationalPlafond1.BtnPlafondRetr1, " ") / this.val$decimal);
                CardNationalPlafond1.this.SliderRetrait.sliderTitle.setText(this.val$BtnPlafondRetr.getText());
                if (CardNationalPlafond1.this.SliderRetrait.sliderTitle.getText().equals("Déplafonné")) {
                    CardNationalPlafond1.this.SliderRetrait.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderRetrait.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                } else {
                    CardNationalPlafond1.this.SliderRetrait.txtSlider = CardNationalPlafond1.this.SliderRetrait.sliderTitle.getText();
                }
                if (this.val$BtnPlafondRetr.getText().equals("Déplafonné")) {
                    CardNationalPlafond1.this.SliderRetrait.ValueSlider.setProgress(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue() / this.val$decimal);
                    CardNationalPlafond1.this.SliderRetrait.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderRetrait.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                } else if (this.val$BtnPlafondRetr.getText().equals("Bloqué")) {
                    CardNationalPlafond1.this.SliderRetrait.ValueSlider.setProgress(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue() / this.val$decimal);
                    CardNationalPlafond1.this.SliderRetrait.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderRetrait.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                }
                CardNationalPlafond1.this.SliderRetrait.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
                CardNationalPlafond1.this.SliderRetrait.ValueSlider.setEditable(true);
                CardNationalPlafond1.this.SliderRetrait.sliderTitle.setHidden(false);
                CardNationalPlafond1.this.thisContainer.revalidate();
            }
            for (int i = 0; i < CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.size(); i++) {
                CardPlafond cardPlafond = (CardPlafond) CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.get(i);
                if (cardPlafond.PlafondType.equals("RetraitNat")) {
                    if (cardPlafond.IsRemovedCardLimits.equals("true") && CardNationalPlafond1.this.IsRemovedCardLimitsRtr.equals("true")) {
                        CihMBanking.sesPMTR.setRtrIsSame(true);
                        if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                            CihMBanking.sesPMTR.PlafondNext = false;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        } else {
                            CihMBanking.sesPMTR.PlafondNext = true;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        }
                    } else if (CardNationalPlafond1.this.SliderRetrait.ValueSlider.getProgress() == 0) {
                        CihMBanking.sesPMTR.setRtrIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = false;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    } else if (cardPlafond.Plafond.equals(Double.valueOf(CardNationalPlafond1.this.SliderRetrait.ValueSlider.getProgress() * this.val$decimal))) {
                        CihMBanking.sesPMTR.setRtrIsSame(true);
                        if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                            CihMBanking.sesPMTR.PlafondNext = false;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        } else {
                            CihMBanking.sesPMTR.setRtrIsSame(false);
                            CihMBanking.sesPMTR.PlafondNext = true;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        }
                    } else {
                        CihMBanking.sesPMTR.setRtrIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = true;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    }
                }
            }
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ Button val$BtnADRtr;
        final /* synthetic */ Button val$BtnPlafDepRtr;
        final /* synthetic */ Button val$BtnPlafondRetr;
        final /* synthetic */ int val$decimal;

        6(Button button, Button button2, Button button3, int i) {
            this.val$BtnADRtr = button;
            this.val$BtnPlafDepRtr = button2;
            this.val$BtnPlafondRetr = button3;
            this.val$decimal = i;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CardNationalPlafond1.this.IsDisabledRtr.equals("false")) {
                CardNationalPlafond1.this.IsDisabledRtr = "true";
                this.val$BtnADRtr.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("off.png 2"));
                this.val$BtnADRtr.setText("Activer");
                this.val$BtnPlafDepRtr.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("off.png 2"));
                this.val$BtnPlafDepRtr.setText("Plafonner");
                CardNationalPlafond1.this.IsRemovedCardLimitsRtr = "true";
                CardNationalPlafond1.this.retrChgmt = "Bloqué";
                if (CardNationalPlafond1.this.IsDisabledRtr.equals("true")) {
                    CardNationalPlafond1.this.actionRtr = "DESACTIVER";
                } else if (CardNationalPlafond1.this.IsRemovedCardLimitsRtr.equals("true")) {
                    CardNationalPlafond1.this.actionRtr = "DEPLAFONNER";
                } else {
                    CardNationalPlafond1.this.actionRtr = "PLAFONNER";
                }
                if (CardNationalPlafond1.this.IsRemovedCardLimitsRtr.equals("true") || CardNationalPlafond1.this.IsRemovedCardLimitsRtr.equals("false")) {
                    CardNationalPlafond1.this.SliderRetrait.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderRetrait.ValueSlider.setEditable(false);
                    CardNationalPlafond1.this.SliderRetrait.ValueSlider.setProgress(0);
                    CardNationalPlafond1.this.SliderRetrait.sliderTitle.setText("Bloqué");
                    CardNationalPlafond1.this.SliderRetrait.sliderTitle.setUIID("g_lblDashBoardTitleGREY");
                    this.val$BtnPlafDepRtr.setEnabled(false);
                    CardNationalPlafond1.this.thisContainer.revalidate();
                }
            } else {
                CardNationalPlafond1.this.IsDisabledRtr = "false";
                this.val$BtnADRtr.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("on.png 1"));
                this.val$BtnADRtr.setText("Bloquer");
                CardNationalPlafond1.this.IsRemovedCardLimitsRtr = "false";
                this.val$BtnPlafDepRtr.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("on.png 1"));
                this.val$BtnPlafDepRtr.setText("Déplafonner");
                if (CardNationalPlafond1.this.IsRemovedCardLimitsRtr.equals("true")) {
                    CardNationalPlafond1.this.retrChgmt = "Déplafonné";
                    CardNationalPlafond1.this.actionRtr = "DEPLAFONNER";
                } else {
                    CardNationalPlafond1.this.retrChgmt = "Activer";
                    CardNationalPlafond1.this.actionRtr = "PLAFONNER";
                }
                this.val$BtnPlafDepRtr.setEnabled(true);
                if (this.val$BtnPlafondRetr.getText().equals("Déplafonné")) {
                    CardNationalPlafond1.this.SliderRetrait.ValueSlider.setEditable(true);
                    CardNationalPlafond1.this.SliderRetrait.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
                    CardNationalPlafond1.this.SliderRetrait.ValueSlider.setProgress(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue() / this.val$decimal);
                    CardNationalPlafond1.this.SliderRetrait.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderRetrait.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                } else {
                    CardNationalPlafond1.this.SliderRetrait.ValueSlider.setEditable(true);
                    CardNationalPlafond1.this.SliderRetrait.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
                    Slider slider = CardNationalPlafond1.this.SliderRetrait.ValueSlider;
                    CardNationalPlafond1 cardNationalPlafond1 = CardNationalPlafond1.this;
                    slider.setProgress(cardNationalPlafond1.formatNumber(cardNationalPlafond1.BtnPlafondRetr1, " ") / this.val$decimal);
                    if (this.val$BtnPlafondRetr.getText().equals("Bloqué")) {
                        CardNationalPlafond1.this.SliderRetrait.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                    } else if (this.val$BtnPlafondRetr.getText().equals("Déplafonné")) {
                        CardNationalPlafond1.this.SliderRetrait.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                    } else {
                        CardNationalPlafond1.this.SliderRetrait.sliderTitle.setText(this.val$BtnPlafondRetr.getText());
                    }
                    CardNationalPlafond1.this.SliderRetrait.sliderTitle.setHidden(false);
                    CardNationalPlafond1.this.SliderRetrait.txtSlider = CardNationalPlafond1.this.SliderRetrait.sliderTitle.getText();
                }
                CardNationalPlafond1.this.thisContainer.revalidate();
            }
            for (int i = 0; i < CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.size(); i++) {
                CardPlafond cardPlafond = (CardPlafond) CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.get(i);
                if (cardPlafond.PlafondType.equals("RetraitNat")) {
                    if (cardPlafond.IsDisabled.equals("true") && CardNationalPlafond1.this.IsDisabledRtr.equals("true")) {
                        CihMBanking.sesPMTR.setRtrIsSame(true);
                        if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                            CihMBanking.sesPMTR.PlafondNext = false;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        } else {
                            CihMBanking.sesPMTR.PlafondNext = true;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        }
                    } else if (cardPlafond.IsRemovedCardLimits.equals("true") && CardNationalPlafond1.this.IsRemovedCardLimitsRtr.equals("true") && CardNationalPlafond1.this.IsDisabledRtr.equals("true")) {
                        CihMBanking.sesPMTR.setRtrIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = true;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    } else if (CardNationalPlafond1.this.SliderRetrait.ValueSlider.getProgress() == 0 && CardNationalPlafond1.this.IsDisabledRtr.equals("false")) {
                        CihMBanking.sesPMTR.setRtrIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = false;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    } else if (cardPlafond.Plafond.equals(Double.valueOf(CardNationalPlafond1.this.SliderRetrait.ValueSlider.getProgress() * this.val$decimal))) {
                        CihMBanking.sesPMTR.setRtrIsSame(true);
                        if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                            CihMBanking.sesPMTR.PlafondNext = false;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        } else {
                            CihMBanking.sesPMTR.setRtrIsSame(false);
                            CihMBanking.sesPMTR.PlafondNext = true;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        }
                    } else {
                        CihMBanking.sesPMTR.setRtrIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = true;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    }
                }
            }
        }
    }

    class 7 implements ActionListener {
        final /* synthetic */ Button val$BtnPlafDepPaim;
        final /* synthetic */ Button val$BtnPlafondPaiement;
        final /* synthetic */ int val$decimal;

        7(Button button, int i, Button button2) {
            this.val$BtnPlafDepPaim = button;
            this.val$decimal = i;
            this.val$BtnPlafondPaiement = button2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.PlafondNext = true;
            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
            CardNationalPlafond1.this.ctnNext.revalidate();
            if (CardNationalPlafond1.this.IsRemovedCardLimitsPaiem.equals("false")) {
                CardNationalPlafond1.this.IsRemovedCardLimitsPaiem = "true";
                this.val$BtnPlafDepPaim.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("off.png 2"));
                this.val$BtnPlafDepPaim.setText("Plafonner");
                CardNationalPlafond1.this.SliderPaiement.ValueSlider.setProgress((int) (CihMBanking.sesPMTR.listAncPlafonds.Maximum.doubleValue() / this.val$decimal));
                CardNationalPlafond1.this.SliderPaiement.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue(), " ") + " Dhs";
                CardNationalPlafond1.this.SliderPaiement.sliderTitle.setText("Déplafonné");
                CardNationalPlafond1.this.SliderPaiement.sliderTitle.setUIID("g_lblDashBoardTitleGREY");
                CardNationalPlafond1.this.SliderPaiement.ValueSlider.setEditable(false);
                if (CardNationalPlafond1.this.IsDisabledPaim.equals("true")) {
                    CardNationalPlafond1.this.paimChgmt = "Bloqué";
                    CardNationalPlafond1.this.actionPaiem = "DESACTIVER";
                } else {
                    CardNationalPlafond1.this.paimChgmt = "Déplafonné";
                    CardNationalPlafond1.this.actionPaiem = "DEPLAFONNER";
                }
                CardNationalPlafond1.this.thisContainer.revalidate();
            } else {
                CardNationalPlafond1.this.IsRemovedCardLimitsPaiem = "false";
                this.val$BtnPlafDepPaim.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("on.png 1"));
                this.val$BtnPlafDepPaim.setText("Déplafonner");
                Slider slider = CardNationalPlafond1.this.SliderPaiement.ValueSlider;
                CardNationalPlafond1 cardNationalPlafond1 = CardNationalPlafond1.this;
                slider.setProgress(cardNationalPlafond1.formatNumber(cardNationalPlafond1.BtnPlafondPaiement1, " ") / this.val$decimal);
                CardNationalPlafond1.this.SliderPaiement.sliderTitle.setText(this.val$BtnPlafondPaiement.getText());
                if (CardNationalPlafond1.this.SliderPaiement.sliderTitle.getText().equals("Déplafonné")) {
                    CardNationalPlafond1.this.SliderPaiement.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderPaiement.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                } else {
                    CardNationalPlafond1.this.SliderPaiement.txtSlider = CardNationalPlafond1.this.SliderPaiement.sliderTitle.getText();
                }
                if (this.val$BtnPlafondPaiement.getText().equals("Déplafonné")) {
                    CardNationalPlafond1.this.SliderPaiement.ValueSlider.setProgress(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue() / this.val$decimal);
                    CardNationalPlafond1.this.SliderPaiement.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderPaiement.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                } else if (this.val$BtnPlafondPaiement.getText().equals("Bloqué")) {
                    CardNationalPlafond1.this.SliderPaiement.ValueSlider.setProgress(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue() / this.val$decimal);
                    CardNationalPlafond1.this.SliderPaiement.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderPaiement.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                }
                CardNationalPlafond1.this.SliderPaiement.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
                CardNationalPlafond1.this.SliderPaiement.ValueSlider.setEditable(true);
                CardNationalPlafond1.this.SliderPaiement.sliderTitle.setHidden(false);
                if (CardNationalPlafond1.this.IsDisabledPaim.equals("true")) {
                    CardNationalPlafond1.this.paimChgmt = "Bloqué";
                    CardNationalPlafond1.this.actionPaiem = "DESACTIVER";
                } else {
                    CardNationalPlafond1.this.paimChgmt = "Activer";
                    CardNationalPlafond1.this.actionPaiem = "PLAFONNER";
                }
                CardNationalPlafond1.this.thisContainer.revalidate();
            }
            for (int i = 0; i < CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.size(); i++) {
                CardPlafond cardPlafond = (CardPlafond) CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.get(i);
                if (cardPlafond.PlafondType.equals("PaiementNat")) {
                    if (cardPlafond.IsRemovedCardLimits.equals("true") && CardNationalPlafond1.this.IsRemovedCardLimitsPaiem.equals("true")) {
                        CihMBanking.sesPMTR.setPaimIsSame(true);
                        if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                            CihMBanking.sesPMTR.PlafondNext = false;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        } else {
                            CihMBanking.sesPMTR.PlafondNext = true;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        }
                    } else if (CardNationalPlafond1.this.SliderPaiement.ValueSlider.getProgress() == 0) {
                        CihMBanking.sesPMTR.setPaimIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = false;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    } else if (cardPlafond.Plafond.equals(Double.valueOf(CardNationalPlafond1.this.SliderPaiement.ValueSlider.getProgress() * this.val$decimal))) {
                        CihMBanking.sesPMTR.setPaimIsSame(true);
                        if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                            CihMBanking.sesPMTR.PlafondNext = false;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        } else {
                            CihMBanking.sesPMTR.setPaimIsSame(false);
                            CihMBanking.sesPMTR.PlafondNext = true;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        }
                    } else {
                        CihMBanking.sesPMTR.setPaimIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = true;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    }
                }
            }
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ Button val$BtnADPaim;
        final /* synthetic */ Button val$BtnPlafDepPaim;
        final /* synthetic */ Button val$BtnPlafondPaiement;
        final /* synthetic */ int val$decimal;

        8(Button button, Button button2, Button button3, int i) {
            this.val$BtnADPaim = button;
            this.val$BtnPlafDepPaim = button2;
            this.val$BtnPlafondPaiement = button3;
            this.val$decimal = i;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CardNationalPlafond1.this.IsDisabledPaim.equals("false")) {
                CardNationalPlafond1.this.IsDisabledPaim = "true";
                this.val$BtnADPaim.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("off.png 2"));
                this.val$BtnADPaim.setText("Activer");
                this.val$BtnPlafDepPaim.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("off.png 2"));
                this.val$BtnPlafDepPaim.setText("Plafonner");
                CardNationalPlafond1.this.IsRemovedCardLimitsPaiem = "true";
                CardNationalPlafond1.this.paimChgmt = "Bloqué";
                if (CardNationalPlafond1.this.IsDisabledPaim.equals("true")) {
                    CardNationalPlafond1.this.actionPaiem = "DESACTIVER";
                } else if (CardNationalPlafond1.this.IsRemovedCardLimitsPaiem.equals("true")) {
                    CardNationalPlafond1.this.actionPaiem = "DEPLAFONNER";
                } else {
                    CardNationalPlafond1.this.actionPaiem = "PLAFONNER";
                }
                if (CardNationalPlafond1.this.IsRemovedCardLimitsPaiem.equals("true") || CardNationalPlafond1.this.IsRemovedCardLimitsPaiem.equals("false")) {
                    CardNationalPlafond1.this.SliderPaiement.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderPaiement.sliderTitle.setUIID("g_lblDashBoardTitleGREY");
                    CardNationalPlafond1.this.SliderPaiement.ValueSlider.setEditable(false);
                    CardNationalPlafond1.this.SliderPaiement.ValueSlider.setProgress(0);
                    CardNationalPlafond1.this.SliderPaiement.sliderTitle.setText("Bloqué");
                    this.val$BtnPlafDepPaim.setEnabled(false);
                    CardNationalPlafond1.this.thisContainer.revalidate();
                }
            } else {
                CardNationalPlafond1.this.IsDisabledPaim = "false";
                this.val$BtnADPaim.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("on.png 1"));
                this.val$BtnADPaim.setText("Bloquer");
                CardNationalPlafond1.this.IsRemovedCardLimitsPaiem = "false";
                this.val$BtnPlafDepPaim.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("on.png 1"));
                this.val$BtnPlafDepPaim.setText("Déplafonner");
                if (CardNationalPlafond1.this.IsRemovedCardLimitsPaiem.equals("true")) {
                    CardNationalPlafond1.this.paimChgmt = "Deplafoné";
                    CardNationalPlafond1.this.actionPaiem = "DEPLAFONNER";
                } else {
                    CardNationalPlafond1.this.paimChgmt = "Activer";
                    CardNationalPlafond1.this.actionPaiem = "PLAFONNER";
                }
                this.val$BtnPlafDepPaim.setEnabled(true);
                if (this.val$BtnPlafondPaiement.getText().equals("Déplafonné")) {
                    CardNationalPlafond1.this.SliderPaiement.ValueSlider.setEditable(true);
                    CardNationalPlafond1.this.SliderPaiement.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
                    CardNationalPlafond1.this.SliderPaiement.ValueSlider.setProgress(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue() / this.val$decimal);
                    CardNationalPlafond1.this.SliderPaiement.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderPaiement.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                } else {
                    CardNationalPlafond1.this.SliderPaiement.ValueSlider.setEditable(true);
                    CardNationalPlafond1.this.SliderPaiement.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
                    Slider slider = CardNationalPlafond1.this.SliderPaiement.ValueSlider;
                    CardNationalPlafond1 cardNationalPlafond1 = CardNationalPlafond1.this;
                    slider.setProgress(cardNationalPlafond1.formatNumber(cardNationalPlafond1.BtnPlafondPaiement1, " ") / this.val$decimal);
                    if (this.val$BtnPlafondPaiement.getText().equals("Bloqué")) {
                        CardNationalPlafond1.this.SliderPaiement.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                    } else if (this.val$BtnPlafondPaiement.getText().equals("Déplafonné")) {
                        CardNationalPlafond1.this.SliderPaiement.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                    } else {
                        CardNationalPlafond1.this.SliderPaiement.sliderTitle.setText(this.val$BtnPlafondPaiement.getText());
                    }
                    CardNationalPlafond1.this.SliderPaiement.sliderTitle.setHidden(false);
                    CardNationalPlafond1.this.SliderPaiement.txtSlider = CardNationalPlafond1.this.SliderPaiement.sliderTitle.getText();
                }
                CardNationalPlafond1.this.thisContainer.revalidate();
            }
            for (int i = 0; i < CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.size(); i++) {
                CardPlafond cardPlafond = (CardPlafond) CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.get(i);
                if (cardPlafond.PlafondType.equals("PaiementNat")) {
                    if (cardPlafond.IsDisabled.equals("true") && CardNationalPlafond1.this.IsDisabledPaim.equals("true")) {
                        CihMBanking.sesPMTR.setPaimIsSame(true);
                        if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                            CihMBanking.sesPMTR.PlafondNext = false;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        } else {
                            CihMBanking.sesPMTR.PlafondNext = true;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        }
                    } else if (cardPlafond.IsRemovedCardLimits.equals("true") && CardNationalPlafond1.this.IsRemovedCardLimitsPaiem.equals("true") && CardNationalPlafond1.this.IsDisabledPaim.equals("true")) {
                        CihMBanking.sesPMTR.setPaimIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = true;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    } else if (CardNationalPlafond1.this.SliderPaiement.ValueSlider.getProgress() == 0 && CardNationalPlafond1.this.IsDisabledPaim.equals("false")) {
                        CihMBanking.sesPMTR.setPaimIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = false;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    } else if (cardPlafond.Plafond.equals(Double.valueOf(CardNationalPlafond1.this.SliderPaiement.ValueSlider.getProgress() * this.val$decimal))) {
                        CihMBanking.sesPMTR.setPaimIsSame(true);
                        if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                            CihMBanking.sesPMTR.PlafondNext = false;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        } else {
                            CihMBanking.sesPMTR.setPaimIsSame(false);
                            CihMBanking.sesPMTR.PlafondNext = true;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        }
                    } else {
                        CihMBanking.sesPMTR.setPaimIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = true;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    }
                }
            }
        }
    }

    class 9 implements ActionListener {
        final /* synthetic */ Button val$BtnPlafDepCom;
        final /* synthetic */ Button val$BtnPlafondCommerce;
        final /* synthetic */ int val$decimal;

        9(Button button, int i, Button button2) {
            this.val$BtnPlafDepCom = button;
            this.val$decimal = i;
            this.val$BtnPlafondCommerce = button2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.PlafondNext = true;
            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
            CardNationalPlafond1.this.ctnNext.revalidate();
            if (CardNationalPlafond1.this.IsRemovedCardLimitsCom.equals("false")) {
                CardNationalPlafond1.this.IsRemovedCardLimitsCom = "true";
                this.val$BtnPlafDepCom.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("off.png 2"));
                this.val$BtnPlafDepCom.setText("Plafonner");
                CardNationalPlafond1.this.SliderCommerce.ValueSlider.setProgress(CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue() / this.val$decimal);
                CardNationalPlafond1.this.SliderCommerce.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Maximum.intValue(), " ") + " Dhs";
                CardNationalPlafond1.this.SliderCommerce.sliderTitle.setText("Déplafonné");
                CardNationalPlafond1.this.SliderCommerce.sliderTitle.setUIID("g_lblDashBoardTitleGREY");
                CardNationalPlafond1.this.SliderCommerce.ValueSlider.setEditable(false);
                if (CardNationalPlafond1.this.IsDisabledCom.equals("true")) {
                    CardNationalPlafond1.this.comChgmt = "Bloqué";
                    CardNationalPlafond1.this.actionCom = "DESACTIVER";
                } else {
                    CardNationalPlafond1.this.comChgmt = "Déplafonné";
                    CardNationalPlafond1.this.actionCom = "DEPLAFONNER";
                }
                CardNationalPlafond1.this.thisContainer.revalidate();
            } else {
                CardNationalPlafond1.this.IsRemovedCardLimitsCom = "false";
                this.val$BtnPlafDepCom.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("on.png 1"));
                this.val$BtnPlafDepCom.setText("Déplafonner");
                Slider slider = CardNationalPlafond1.this.SliderCommerce.ValueSlider;
                CardNationalPlafond1 cardNationalPlafond1 = CardNationalPlafond1.this;
                slider.setProgress(cardNationalPlafond1.formatNumber(cardNationalPlafond1.BtnPlafondCommerce1, " ") / this.val$decimal);
                CardNationalPlafond1.this.SliderCommerce.sliderTitle.setText(this.val$BtnPlafondCommerce.getText());
                if (CardNationalPlafond1.this.SliderCommerce.sliderTitle.getText().equals("Déplafonné")) {
                    CardNationalPlafond1.this.SliderCommerce.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderCommerce.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                } else {
                    CardNationalPlafond1.this.SliderCommerce.txtSlider = CardNationalPlafond1.this.SliderCommerce.sliderTitle.getText();
                }
                if (this.val$BtnPlafondCommerce.getText().equals("Déplafonné")) {
                    CardNationalPlafond1.this.SliderCommerce.ValueSlider.setProgress(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue() / this.val$decimal);
                    CardNationalPlafond1.this.SliderCommerce.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderCommerce.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                } else if (this.val$BtnPlafondCommerce.getText().equals("Bloqué")) {
                    CardNationalPlafond1.this.SliderCommerce.ValueSlider.setProgress(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue() / this.val$decimal);
                    CardNationalPlafond1.this.SliderCommerce.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderCommerce.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                }
                CardNationalPlafond1.this.SliderCommerce.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
                CardNationalPlafond1.this.SliderCommerce.ValueSlider.setEditable(true);
                CardNationalPlafond1.this.SliderCommerce.sliderTitle.setHidden(false);
                if (CardNationalPlafond1.this.IsDisabledCom.equals("true")) {
                    CardNationalPlafond1.this.comChgmt = "Bloqué";
                    CardNationalPlafond1.this.actionCom = "DESACTIVER";
                } else {
                    CardNationalPlafond1.this.comChgmt = "Activer";
                    CardNationalPlafond1.this.actionCom = "PLAFONNER";
                }
                CardNationalPlafond1.this.thisContainer.revalidate();
            }
            for (int i = 0; i < CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.size(); i++) {
                CardPlafond cardPlafond = (CardPlafond) CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.get(i);
                if (cardPlafond.PlafondType.equals("EComNat")) {
                    if (cardPlafond.IsRemovedCardLimits.equals("true") && CardNationalPlafond1.this.IsRemovedCardLimitsCom.equals("true")) {
                        CihMBanking.sesPMTR.setComIsSame(true);
                        if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                            CihMBanking.sesPMTR.PlafondNext = false;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        } else {
                            CihMBanking.sesPMTR.PlafondNext = true;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        }
                    } else if (cardPlafond.IsRemovedCardLimits.equals("true") && CardNationalPlafond1.this.IsRemovedCardLimitsCom.equals("true")) {
                        CihMBanking.sesPMTR.setComIsSame(true);
                        if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                            CihMBanking.sesPMTR.PlafondNext = false;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        } else {
                            CihMBanking.sesPMTR.PlafondNext = true;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        }
                    } else if (CardNationalPlafond1.this.SliderCommerce.ValueSlider.getProgress() == 0) {
                        CihMBanking.sesPMTR.setComIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = false;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    } else if (cardPlafond.Plafond.equals(Double.valueOf(CardNationalPlafond1.this.SliderCommerce.ValueSlider.getProgress() * this.val$decimal))) {
                        CihMBanking.sesPMTR.setComIsSame(true);
                        if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                            CihMBanking.sesPMTR.PlafondNext = false;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        } else {
                            CihMBanking.sesPMTR.setComIsSame(false);
                            CihMBanking.sesPMTR.PlafondNext = true;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        }
                    } else {
                        CihMBanking.sesPMTR.setComIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = true;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    }
                }
            }
        }
    }

    class 10 implements ActionListener {
        final /* synthetic */ Button val$BtnADCom;
        final /* synthetic */ Button val$BtnPlafDepCom;
        final /* synthetic */ Button val$BtnPlafondCommerce;
        final /* synthetic */ int val$decimal;

        10(Button button, Button button2, Button button3, int i) {
            this.val$BtnADCom = button;
            this.val$BtnPlafDepCom = button2;
            this.val$BtnPlafondCommerce = button3;
            this.val$decimal = i;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CardNationalPlafond1.this.IsDisabledCom.equals("false")) {
                CardNationalPlafond1.this.IsDisabledCom = "true";
                this.val$BtnADCom.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("off.png 2"));
                this.val$BtnADCom.setText("Activer");
                this.val$BtnPlafDepCom.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("off.png 2"));
                this.val$BtnPlafDepCom.setText("Plafonner");
                CardNationalPlafond1.this.IsRemovedCardLimitsCom = "true";
                CardNationalPlafond1.this.comChgmt = "Bloqué";
                if (CardNationalPlafond1.this.IsDisabledCom.equals("true")) {
                    CardNationalPlafond1.this.actionCom = "DESACTIVER";
                } else if (CardNationalPlafond1.this.IsRemovedCardLimitsCom.equals("true")) {
                    CardNationalPlafond1.this.actionCom = "DEPLAFONNER";
                } else {
                    CardNationalPlafond1.this.actionCom = "PLAFONNER";
                }
                if (CardNationalPlafond1.this.IsRemovedCardLimitsCom.equals("true") || CardNationalPlafond1.this.IsRemovedCardLimitsCom.equals("false")) {
                    CardNationalPlafond1.this.SliderCommerce.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderCommerce.sliderTitle.setUIID("g_lblDashBoardTitleGREY");
                    CardNationalPlafond1.this.SliderCommerce.ValueSlider.setProgress(0);
                    CardNationalPlafond1.this.SliderCommerce.sliderTitle.setText("Bloqué");
                    CardNationalPlafond1.this.SliderCommerce.ValueSlider.setEditable(false);
                    this.val$BtnPlafDepCom.setEnabled(false);
                    CardNationalPlafond1.this.thisContainer.revalidate();
                }
            } else {
                CardNationalPlafond1.this.IsDisabledCom = "false";
                this.val$BtnADCom.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("on.png 1"));
                this.val$BtnADCom.setText("Bloquer");
                CardNationalPlafond1.this.IsRemovedCardLimitsCom = "false";
                this.val$BtnPlafDepCom.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("on.png 1"));
                this.val$BtnPlafDepCom.setText("Déplafonner");
                if (CardNationalPlafond1.this.IsRemovedCardLimitsCom.equals("true")) {
                    CardNationalPlafond1.this.comChgmt = "Déplafonné";
                    CardNationalPlafond1.this.actionCom = "DEPLAFONNER";
                } else {
                    CardNationalPlafond1.this.comChgmt = "Activer";
                    CardNationalPlafond1.this.actionCom = "PLAFONNER";
                }
                this.val$BtnPlafDepCom.setEnabled(true);
                if (this.val$BtnPlafondCommerce.getText().equals("Déplafonné")) {
                    CardNationalPlafond1.this.SliderCommerce.ValueSlider.setEditable(true);
                    CardNationalPlafond1.this.SliderCommerce.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
                    CardNationalPlafond1.this.SliderCommerce.ValueSlider.setProgress(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue() / this.val$decimal);
                    CardNationalPlafond1.this.SliderCommerce.txtSlider = CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs";
                    CardNationalPlafond1.this.SliderCommerce.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                } else {
                    CardNationalPlafond1.this.SliderCommerce.ValueSlider.setEditable(true);
                    CardNationalPlafond1.this.SliderCommerce.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
                    Slider slider = CardNationalPlafond1.this.SliderCommerce.ValueSlider;
                    CardNationalPlafond1 cardNationalPlafond1 = CardNationalPlafond1.this;
                    slider.setProgress(cardNationalPlafond1.formatNumber(cardNationalPlafond1.BtnPlafondCommerce1, " ") / this.val$decimal);
                    if (this.val$BtnPlafondCommerce.getText().equals("Bloqué")) {
                        CardNationalPlafond1.this.SliderCommerce.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                    } else if (this.val$BtnPlafondCommerce.getText().equals("Déplafonné")) {
                        CardNationalPlafond1.this.SliderCommerce.sliderTitle.setText(CardNationalPlafond1.this.formatNumber(CihMBanking.sesPMTR.listAncPlafonds.Minimum.intValue(), " ") + " Dhs");
                    } else {
                        CardNationalPlafond1.this.SliderCommerce.sliderTitle.setText(this.val$BtnPlafondCommerce.getText());
                    }
                    CardNationalPlafond1.this.SliderCommerce.sliderTitle.setHidden(false);
                    CardNationalPlafond1.this.SliderCommerce.txtSlider = CardNationalPlafond1.this.SliderCommerce.sliderTitle.getText();
                }
                CardNationalPlafond1.this.thisContainer.revalidate();
            }
            for (int i = 0; i < CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.size(); i++) {
                CardPlafond cardPlafond = (CardPlafond) CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.get(i);
                if (cardPlafond.PlafondType.equals("EComNat")) {
                    if (cardPlafond.IsDisabled.equals("true") && CardNationalPlafond1.this.IsDisabledCom.equals("true")) {
                        CihMBanking.sesPMTR.setComIsSame(true);
                        if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                            CihMBanking.sesPMTR.PlafondNext = false;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        } else {
                            CihMBanking.sesPMTR.PlafondNext = true;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        }
                    } else if (cardPlafond.IsRemovedCardLimits.equals("true") && CardNationalPlafond1.this.IsRemovedCardLimitsCom.equals("true") && CardNationalPlafond1.this.IsDisabledCom.equals("true")) {
                        CihMBanking.sesPMTR.setComIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = true;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    } else if (CardNationalPlafond1.this.SliderCommerce.ValueSlider.getProgress() == 0 && CardNationalPlafond1.this.IsDisabledCom.equals("false")) {
                        CihMBanking.sesPMTR.setComIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = false;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    } else if (cardPlafond.Plafond.equals(Double.valueOf(CardNationalPlafond1.this.SliderCommerce.ValueSlider.getProgress() * this.val$decimal))) {
                        CihMBanking.sesPMTR.setComIsSame(true);
                        if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                            CihMBanking.sesPMTR.PlafondNext = false;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        } else {
                            CihMBanking.sesPMTR.setComIsSame(false);
                            CihMBanking.sesPMTR.PlafondNext = true;
                            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                            CardNationalPlafond1.this.ctnNext.revalidate();
                        }
                    } else {
                        CihMBanking.sesPMTR.setComIsSame(false);
                        CihMBanking.sesPMTR.PlafondNext = true;
                        CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                        CardNationalPlafond1.this.ctnNext.revalidate();
                    }
                }
            }
        }
    }

    class 11 implements ActionListener {
        final /* synthetic */ Button val$BtnADDuree;
        final /* synthetic */ Button val$BtnDureeChgmt;

        11(Button button, Button button2) {
            this.val$BtnADDuree = button;
            this.val$BtnDureeChgmt = button2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CardNationalPlafond1.this.IsDisabledDuree.equals("false")) {
                CardNationalPlafond1.this.IsDisabledDuree = "true";
                this.val$BtnADDuree.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("off.png 2"));
                CardNationalPlafond1.this.SliderDuree.ValueSlider.setEditable(false);
                CardNationalPlafond1.this.SliderDuree.ValueSlider.getUnselectedStyle();
                this.val$BtnADDuree.setText("Illimitée");
                CardNationalPlafond1 cardNationalPlafond1 = CardNationalPlafond1.this;
                cardNationalPlafond1.DureeChgmt = cardNationalPlafond1.DureeInitial;
                CardNationalPlafond1.this.SliderDuree.ValueSlider.setProgress(6);
                CardNationalPlafond1.this.SliderDuree.sliderTitle.setText("Illimitée");
                CardNationalPlafond1.this.SliderDuree.sliderTitle.setUIID("g_lblDashBoardTitleGREY");
                CardNationalPlafond1.this.actionDuree = "ILLIMITER";
                CardNationalPlafond1.this.SliderDuree.monthNumber = "ILLIMITEE";
                CardNationalPlafond1.this.thisContainer.revalidate();
            } else {
                CardNationalPlafond1.this.IsDisabledDuree = "false";
                this.val$BtnADDuree.setIcon(CardNationalPlafond1.this.uiManager.ressource.getImage("on.png 1"));
                CardNationalPlafond1.this.SliderDuree.ValueSlider.setEditable(true);
                CardNationalPlafond1.this.SliderDuree.ValueSlider.getUnselectedStyle();
                this.val$BtnADDuree.setText("Limitée");
                CardNationalPlafond1 cardNationalPlafond12 = CardNationalPlafond1.this;
                cardNationalPlafond12.DureeChgmt = cardNationalPlafond12.DureeInitial;
                CardNationalPlafond1.this.SliderDuree.sliderTitle.setUIID("ac_lblitemDetailValueFormDSC");
                if (this.val$BtnDureeChgmt.getText().equals("Illimitée")) {
                    CardNationalPlafond1.this.BtnDureeChgmt1 = "1";
                }
                CardNationalPlafond1.this.SliderDuree.getValueSlidrDurInit(Double.valueOf(Double.parseDouble(CardNationalPlafond1.this.BtnDureeChgmt1)));
                CardNationalPlafond1.this.SliderDuree.monthNumber = CardNationalPlafond1.this.BtnDureeChgmt1;
                CardNationalPlafond1.this.actionDuree = "LIMITER";
                CardNationalPlafond1.this.thisContainer.revalidate();
            }
            if (CardNationalPlafond1.this.IsDisabledDuree.equals("true") && Double.parseDouble(CihMBanking.sesPMTR.listAncPlafonds.EndDate) == 0.0d) {
                CihMBanking.sesPMTR.setDureeIsSame(true);
                if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                    CihMBanking.sesPMTR.PlafondNext = false;
                    CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                    CardNationalPlafond1.this.ctnNext.revalidate();
                    return;
                } else {
                    CihMBanking.sesPMTR.PlafondNext = true;
                    CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                    CardNationalPlafond1.this.ctnNext.revalidate();
                    return;
                }
            }
            if (CihMBanking.sesPMTR.listAncPlafonds.EndDate.equals(CardNationalPlafond1.this.SliderDuree.monthNumber)) {
                CihMBanking.sesPMTR.setDureeIsSame(true);
                if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                    CihMBanking.sesPMTR.PlafondNext = false;
                    CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationGriseMarginLeft");
                    CardNationalPlafond1.this.ctnNext.revalidate();
                    return;
                } else {
                    CihMBanking.sesPMTR.setDureeIsSame(false);
                    CihMBanking.sesPMTR.PlafondNext = true;
                    CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
                    CardNationalPlafond1.this.ctnNext.revalidate();
                    return;
                }
            }
            CihMBanking.sesPMTR.setDureeIsSame(false);
            CihMBanking.sesPMTR.PlafondNext = true;
            CardNationalPlafond1.this.ctnNext.getComponentAt(1).setUIID("op_BtnOppositionValidationMarginLeft");
            CardNationalPlafond1.this.ctnNext.revalidate();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02a6  */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.codename1.ui.Container getCardPlafondResumCnt() {
        /*
            Method dump skipped, instructions count: 3086
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.CardNationalPlafond1.getCardPlafondResumCnt():com.codename1.ui.Container");
    }

    public String getDureeInitial(double d) {
        Double valueOf = Double.valueOf(d);
        int intValue = valueOf.intValue();
        String str = intValue != 1 ? intValue != 7 ? intValue != 15 ? intValue != 30 ? intValue != 90 ? intValue != 180 ? intValue != 365 ? "" : "12 Mois" : "6 Mois" : "3 Mois" : "1 Mois" : "15 Jours" : "7 Jours" : "1 Jour";
        if (valueOf.intValue() < 7 && valueOf.intValue() > 1) {
            return Integer.toString(valueOf.intValue()) + " Jours";
        }
        if (valueOf.intValue() < 15 && valueOf.intValue() > 7) {
            return Integer.toString(valueOf.intValue()) + " Jours";
        }
        if (valueOf.intValue() < 30 && valueOf.intValue() > 15) {
            return Integer.toString(valueOf.intValue()) + " Jours";
        }
        if (valueOf.intValue() < 90 && valueOf.intValue() > 30) {
            return Integer.toString(valueOf.intValue()) + " Jours";
        }
        if (valueOf.intValue() < 180 && valueOf.intValue() > 90) {
            return Integer.toString(valueOf.intValue()) + " Jours";
        }
        if (valueOf.intValue() >= 365 || valueOf.intValue() <= 180) {
            return valueOf.intValue() <= 0 ? "Illimitée" : str;
        }
        return Integer.toString(valueOf.intValue()) + " Jours";
    }

    public Card CardPlafondProcess(String str, String str2) {
        Card card = (Card) this.service;
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSION_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", str);
        hashtable.put("CARDNUMBER", card.plainCardNumber);
        hashtable.put("ISRETABLIRCLICK", "false");
        hashtable.put("FLAG", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(16);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return FillCardArrayDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public Card CardPlafondInitialProcess(String str) {
        Card card = (Card) this.service;
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSION_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", str);
        hashtable.put("CARDNUMBER", card.plainCardNumber);
        hashtable.put("ISRETABLIRCLICK", "true");
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(16);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return FillResetCardArrayDataFromServiceResponse((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public Card FillPlafondCardArrayDataFromServiceResponse(Hashtable hashtable) {
        Card card = new Card();
        card.CanDesactivate = hashtable.get("CanDesactivate").toString();
        card.CanRemoveCardLimits = hashtable.get("CanRemoveCardLimits").toString();
        card.EndDate = hashtable.get("EndDate").toString();
        card.HasUnlimitedDate = hashtable.get("HasUnlimitedDate").toString();
        card.Maximum = (Double) hashtable.get("Maximum");
        card.Minimum = (Double) hashtable.get("Minimum");
        card.productStep = (Double) hashtable.get("ProductStep");
        Vector vector = (Vector) hashtable.get("CardPlafondList");
        if (vector.size() > 0) {
            card.CardPlafondList = CardPlafond.FillAccountCardPlafondDataFromServiceResponse(vector);
        } else {
            card.CardPlafondList = new ArrayList();
        }
        return card;
    }

    public Card FillCardArrayDataFromServiceResponse(ServiceResponse serviceResponse) {
        Card card = new Card();
        if (serviceResponse.getStatusCode().equals("000")) {
            return FillPlafondCardArrayDataFromServiceResponse(serviceResponse.getParamsOut());
        }
        if (!serviceResponse.getStatusCode().equals("312")) {
            return card;
        }
        this.codeStatus = serviceResponse.getStatusCode();
        this.statusLbl = serviceResponse.getStatusLabel();
        return card;
    }

    public Card FillResetCardArrayDataFromServiceResponse(ServiceResponse serviceResponse) {
        Card card = new Card();
        if (serviceResponse.getStatusCode().equals("000")) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Vos plafonds ont été réinitialisés avec succès", null);
            return FillPlafondCardArrayDataFromServiceResponse(serviceResponse.getParamsOut());
        }
        if (!serviceResponse.getStatusCode().equals("312")) {
            return card;
        }
        this.codeStatus = serviceResponse.getStatusCode();
        this.statusLbl = serviceResponse.getStatusLabel();
        return card;
    }

    public String formatNumber(int i, String str) {
        int length = String.valueOf(i).length();
        if (length > 3) {
            int i2 = length % 3;
            String substring = String.valueOf(i).substring(i2, length);
            ArrayList arrayList = new ArrayList();
            if (substring.length() == 3 || substring.length() < 3) {
                arrayList.add(substring);
            } else {
                for (int i3 = 2; i3 < substring.length(); i3 += 3) {
                    arrayList.add(substring.substring(i3 - 2, i3 + 1));
                }
            }
            String str2 = String.valueOf(i).substring(0, i2) + " ";
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                str2 = str2 + ((String) arrayList.get(i4)) + " ";
            }
            return str2.trim();
        }
        return String.valueOf(i);
    }

    public int formatNumber(String str, String str2) {
        try {
            Integer.parseInt(replace(str, str2, ""));
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return Integer.parseInt(StringUtil.replaceAll(str, str2, ""));
    }

    public String replace(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return str;
        }
        while (indexOf != -1) {
            sb.append(str.substring(0, indexOf));
            sb.append(str3);
            str = str.substring(indexOf + str2.length());
            indexOf = str.indexOf(str2);
        }
        sb.append(str);
        return sb.toString();
    }
}
