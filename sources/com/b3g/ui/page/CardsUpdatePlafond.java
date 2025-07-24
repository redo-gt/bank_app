package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.services.CardPlafond;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gContainer;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.StringUtil;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CardsUpdatePlafond extends B3GPage {
    Button BtnRetablir;
    Button BtnRetablirInt;
    Button BtnSuivant;
    Button BtnSuivantInt;
    Tabs TabsDetail;
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    Container cntInt;
    Container cntMain;
    Container cntNat;
    Card currentCard;
    Container firstCntInt;
    Container firstCntNat;
    Container secondCntInt;
    Container secondCntNat;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();
    public boolean isRetablir = false;
    public boolean isFirstNext = true;

    static /* synthetic */ void access$000(CardsUpdatePlafond cardsUpdatePlafond, Container container, Container container2, Container container3, boolean z) {
        cardsUpdatePlafond.SwitchTransfertForms(container, container2, container3, z);
    }

    static /* synthetic */ String access$100(CardsUpdatePlafond cardsUpdatePlafond) {
        return cardsUpdatePlafond.VnewaliasText;
    }

    static /* synthetic */ String access$102(CardsUpdatePlafond cardsUpdatePlafond, String str) {
        cardsUpdatePlafond.VnewaliasText = str;
        return str;
    }

    static /* synthetic */ ServiceResponse access$200(CardsUpdatePlafond cardsUpdatePlafond, Hashtable hashtable, String str) {
        return cardsUpdatePlafond.ResetPlafondCard(hashtable, str);
    }

    static /* synthetic */ ServiceResponse access$300(CardsUpdatePlafond cardsUpdatePlafond, Hashtable hashtable, String str) {
        return cardsUpdatePlafond.updatePlafondProcess(hashtable, str);
    }

    public CardsUpdatePlafond(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
        this.currentCard = (Card) obj2;
    }

    public Container GetPageContainer() {
        this.cntMain = new Container(new LayeredLayout());
        this.BtnSuivant = new Button(" SUIVANT ");
        this.BtnRetablir = new Button(" RÉTABLIR ");
        this.BtnSuivantInt = new Button(" SUIVANT ");
        this.BtnRetablirInt = new Button(" RÉTABLIR ");
        CihMBanking.exitApplication = true;
        this.cntNat = new Container(new BoxLayout(2));
        this.cntInt = new Container(new BoxLayout(2));
        Container container = new Container(new BoxLayout(2));
        container.setScrollableY(true);
        Container container2 = new Container(BoxLayout.y());
        Container container3 = new Container(BoxLayout.y());
        this.BtnRetablir.setUIID("op_BtnOppositionValidationMarginLeftNew");
        this.BtnSuivant.setUIID("op_BtnOppositionValidationGriseMarginLeftNew");
        this.BtnRetablir.setVerticalAlignment(4);
        this.BtnSuivant.setVerticalAlignment(4);
        this.BtnRetablir.setTextPosition(1);
        this.BtnSuivant.setTextPosition(1);
        this.BtnRetablirInt.setUIID("op_BtnOppositionValidationMarginLeftNew");
        this.BtnSuivantInt.setUIID("op_BtnOppositionValidationGriseMarginLeftNew");
        this.BtnRetablirInt.setVerticalAlignment(4);
        this.BtnSuivantInt.setVerticalAlignment(4);
        this.BtnRetablirInt.setTextPosition(1);
        this.BtnSuivantInt.setTextPosition(1);
        container2.addComponent(this.BtnRetablir);
        container2.addComponent(this.BtnSuivant);
        container3.addComponent(this.BtnRetablirInt);
        container3.addComponent(this.BtnSuivantInt);
        Card card = this.currentCard;
        CardNationalPlafond cardNationalPlafond = new CardNationalPlafond(this.uiManager, card, 0, container2);
        CardNationalPlafond cardNationalPlafond2 = new CardNationalPlafond(this.uiManager, card, 1, container3);
        container.setUIID("mn_cntMenuItem");
        try {
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "CardDetailV2");
            this.uiManager.stateMachine.findLblCardNumberValue(createContainer).setText(card.cardNumber);
            this.uiManager.stateMachine.findLblCardEntitiledValue(createContainer).setText(card.entitled);
            this.uiManager.stateMachine.findLblCardBalanceValue(createContainer).remove();
            this.uiManager.stateMachine.findLblCardBalanceTitle(createContainer).remove();
            ((Container) this.uib.findByName("CntCardtInfo", createContainer)).setHidden(true);
            Label label = new Label("Modifier les plafonds", "gb_lblGlobalHeaderTitleV2");
            Container GetPageContainer = cardNationalPlafond.GetPageContainer();
            this.firstCntNat = GetPageContainer;
            this.cntNat.addComponent(GetPageContainer);
            Container GetPageContainer2 = cardNationalPlafond2.GetPageContainer();
            this.firstCntInt = GetPageContainer2;
            this.cntInt.addComponent(GetPageContainer2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(this.cntNat, "Plafonds au Maroc"));
            container.addComponent(label);
            container.addComponent(createContainer);
            this.uiManager.DrawTabsWithNavigation(createContainer, arrayList);
            this.TabsDetail = (Tabs) createContainer.getComponentAt(4);
            this.cntNat.addComponent(container2);
            this.cntInt.addComponent(container3);
            this.BtnSuivant.addActionListener(new 1(cardNationalPlafond, container, card));
            this.BtnSuivantInt.addActionListener(new 2(cardNationalPlafond2, container, card));
            this.BtnRetablir.addActionListener(new 3(cardNationalPlafond));
            this.BtnRetablirInt.addActionListener(new 4(cardNationalPlafond2));
            this.cntMain.addComponent(container);
            this.thisContainer = this.cntMain;
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$cntOpMain;
        final /* synthetic */ Card val$crd;
        final /* synthetic */ CardNationalPlafond val$crdNationalPlfd;

        1(CardNationalPlafond cardNationalPlafond, Container container, Card card) {
            this.val$crdNationalPlfd = cardNationalPlafond;
            this.val$cntOpMain = container;
            this.val$crd = card;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            try {
                CardsUpdatePlafond.this.TabsDetail.getSelectedIndex();
            } catch (Exception unused) {
            }
            if (CihMBanking.sesPMTR.PlafondNext) {
                if (CardsUpdatePlafond.this.isFirstNext) {
                    CihMBanking.sesPMTR.IsSuiv = true;
                    CardsUpdatePlafond.this.secondCntNat = this.val$crdNationalPlfd.getCardPlafondResumCnt();
                    CardsUpdatePlafond cardsUpdatePlafond = CardsUpdatePlafond.this;
                    CardsUpdatePlafond.access$000(cardsUpdatePlafond, cardsUpdatePlafond.cntNat, CardsUpdatePlafond.this.firstCntNat, CardsUpdatePlafond.this.secondCntNat, true);
                    CihMBanking.sesPMTR.PlafondNext = true;
                    CardsUpdatePlafond.this.isFirstNext = false;
                    CardsUpdatePlafond.this.isRetablir = true;
                    CardsUpdatePlafond.this.BtnRetablir.setText(" RETOUR ");
                    CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
                    CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$1$$ExternalSyntheticLambda1(this));
                    return;
                }
                CihMBanking.sesPMTR.setSessionSavedContainer(this.val$cntOpMain);
                Container container = new Container(new BoxLayout(2));
                CardsUpdatePlafond cardsUpdatePlafond2 = CardsUpdatePlafond.this;
                container.addComponent(cardsUpdatePlafond2.GetTransferSecurityForm(cardsUpdatePlafond2.cntMain, this.val$crdNationalPlfd, this.val$crd));
                CardsUpdatePlafond cardsUpdatePlafond3 = CardsUpdatePlafond.this;
                CardsUpdatePlafond.access$000(cardsUpdatePlafond3, cardsUpdatePlafond3.thisContainer, this.val$cntOpMain, container, false);
                CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
                CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$1$$ExternalSyntheticLambda2(this));
                CardsUpdatePlafond.this.isFirstNext = false;
            }
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-CardsUpdatePlafond$1(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond cardsUpdatePlafond = CardsUpdatePlafond.this;
            CardsUpdatePlafond.access$000(cardsUpdatePlafond, cardsUpdatePlafond.cntNat, CardsUpdatePlafond.this.secondCntNat, CardsUpdatePlafond.this.firstCntNat, true);
            CardsUpdatePlafond.this.isRetablir = false;
            CardsUpdatePlafond.this.isFirstNext = true;
            CardsUpdatePlafond.this.BtnRetablir.setText(" RÉTABLIR ");
            CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$1$$ExternalSyntheticLambda0(this));
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-CardsUpdatePlafond$1(ActionEvent actionEvent) {
            CardsUpdatePlafond.this.uiManager.GoBack();
        }

        /* synthetic */ void lambda$actionPerformed$4$com-b3g-ui-page-CardsUpdatePlafond$1(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond.this.cntMain.replace((Container) CardsUpdatePlafond.this.cntMain.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            CardsUpdatePlafond.this.cntMain.revalidate();
            CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$1$$ExternalSyntheticLambda4(this));
        }

        /* synthetic */ void lambda$actionPerformed$3$com-b3g-ui-page-CardsUpdatePlafond$1(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond cardsUpdatePlafond = CardsUpdatePlafond.this;
            CardsUpdatePlafond.access$000(cardsUpdatePlafond, cardsUpdatePlafond.cntNat, CardsUpdatePlafond.this.secondCntNat, CardsUpdatePlafond.this.firstCntNat, true);
            CardsUpdatePlafond.this.isRetablir = false;
            CardsUpdatePlafond.this.isFirstNext = true;
            CardsUpdatePlafond.this.BtnRetablir.setText(" RÉTABLIR ");
            CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$1$$ExternalSyntheticLambda3(this));
        }

        /* synthetic */ void lambda$actionPerformed$2$com-b3g-ui-page-CardsUpdatePlafond$1(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond.this.uiManager.GoBack();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$cntOpMain;
        final /* synthetic */ Card val$crd;
        final /* synthetic */ CardNationalPlafond val$crdNationalPlfd1;

        2(CardNationalPlafond cardNationalPlafond, Container container, Card card) {
            this.val$crdNationalPlfd1 = cardNationalPlafond;
            this.val$cntOpMain = container;
            this.val$crd = card;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            try {
                CardsUpdatePlafond.this.TabsDetail.getSelectedIndex();
            } catch (Exception unused) {
            }
            if (CihMBanking.sesPMTR.PlafondNext) {
                if (CardsUpdatePlafond.this.isFirstNext) {
                    CihMBanking.sesPMTR.IsSuiv = true;
                    CardsUpdatePlafond.this.secondCntInt = this.val$crdNationalPlfd1.getCardPlafondResumCnt();
                    CardsUpdatePlafond cardsUpdatePlafond = CardsUpdatePlafond.this;
                    CardsUpdatePlafond.access$000(cardsUpdatePlafond, cardsUpdatePlafond.cntInt, CardsUpdatePlafond.this.firstCntInt, CardsUpdatePlafond.this.secondCntInt, true);
                    CihMBanking.sesPMTR.PlafondNext = true;
                    CardsUpdatePlafond.this.isFirstNext = false;
                    CardsUpdatePlafond.this.isRetablir = true;
                    CardsUpdatePlafond.this.BtnRetablirInt.setText(" RETOUR ");
                    CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
                    CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$2$$ExternalSyntheticLambda1(this));
                    return;
                }
                CihMBanking.sesPMTR.setSessionSavedContainer(this.val$cntOpMain);
                Container container = new Container(new BoxLayout(2));
                CardsUpdatePlafond cardsUpdatePlafond2 = CardsUpdatePlafond.this;
                container.addComponent(cardsUpdatePlafond2.GetTransferSecurityForm(cardsUpdatePlafond2.cntMain, this.val$crdNationalPlfd1, this.val$crd));
                CardsUpdatePlafond cardsUpdatePlafond3 = CardsUpdatePlafond.this;
                CardsUpdatePlafond.access$000(cardsUpdatePlafond3, cardsUpdatePlafond3.thisContainer, this.val$cntOpMain, container, false);
                CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
                CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$2$$ExternalSyntheticLambda2(this));
                CardsUpdatePlafond.this.isFirstNext = false;
            }
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-CardsUpdatePlafond$2(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond cardsUpdatePlafond = CardsUpdatePlafond.this;
            CardsUpdatePlafond.access$000(cardsUpdatePlafond, cardsUpdatePlafond.cntInt, CardsUpdatePlafond.this.secondCntInt, CardsUpdatePlafond.this.firstCntInt, true);
            CardsUpdatePlafond.this.isRetablir = false;
            CardsUpdatePlafond.this.isFirstNext = true;
            CardsUpdatePlafond.this.BtnRetablirInt.setText(" RÉTABLIR ");
            CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$2$$ExternalSyntheticLambda4(this));
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-CardsUpdatePlafond$2(ActionEvent actionEvent) {
            CardsUpdatePlafond.this.uiManager.GoBack();
        }

        /* synthetic */ void lambda$actionPerformed$4$com-b3g-ui-page-CardsUpdatePlafond$2(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond.this.cntMain.replace((Container) CardsUpdatePlafond.this.cntMain.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            CardsUpdatePlafond.this.cntMain.revalidate();
            CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$2$$ExternalSyntheticLambda0(this));
        }

        /* synthetic */ void lambda$actionPerformed$3$com-b3g-ui-page-CardsUpdatePlafond$2(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond cardsUpdatePlafond = CardsUpdatePlafond.this;
            CardsUpdatePlafond.access$000(cardsUpdatePlafond, cardsUpdatePlafond.cntInt, CardsUpdatePlafond.this.secondCntInt, CardsUpdatePlafond.this.firstCntInt, true);
            CardsUpdatePlafond.this.isRetablir = false;
            CardsUpdatePlafond.this.isFirstNext = true;
            CardsUpdatePlafond.this.BtnRetablirInt.setText(" RÉTABLIR ");
            CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$2$$ExternalSyntheticLambda3(this));
        }

        /* synthetic */ void lambda$actionPerformed$2$com-b3g-ui-page-CardsUpdatePlafond$2(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond.this.uiManager.GoBack();
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ CardNationalPlafond val$crdNationalPlfd;

        3(CardNationalPlafond cardNationalPlafond) {
            this.val$crdNationalPlfd = cardNationalPlafond;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CardsUpdatePlafond.this.isRetablir) {
                try {
                    CardsUpdatePlafond.this.TabsDetail.getSelectedIndex();
                } catch (Exception unused) {
                }
                CardsUpdatePlafond cardsUpdatePlafond = CardsUpdatePlafond.this;
                CardsUpdatePlafond.access$000(cardsUpdatePlafond, cardsUpdatePlafond.cntNat, CardsUpdatePlafond.this.secondCntNat, CardsUpdatePlafond.this.firstCntNat, true);
                CardsUpdatePlafond.this.isRetablir = false;
                CardsUpdatePlafond.this.isFirstNext = true;
                if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                    CihMBanking.sesPMTR.PlafondNext = false;
                    CardsUpdatePlafond.this.BtnSuivant.setUIID("op_BtnOppositionValidationGriseMarginLeft");
                } else {
                    CihMBanking.sesPMTR.PlafondNext = true;
                    CardsUpdatePlafond.this.BtnSuivant.setUIID("op_BtnOppositionValidationMarginLeftNew");
                }
                CardsUpdatePlafond.this.BtnRetablir.setText(" RÉTABLIR ");
                CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
                CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$3$$ExternalSyntheticLambda0(this));
                return;
            }
            CihMBanking.exitApplication = true;
            CihMBanking.sesPMTR.isBtnRetablirClick = true;
            CihMBanking.sesPMTR.PlafondNext = true;
            CardsUpdatePlafond.this.isFirstNext = false;
            CardsUpdatePlafond.this.isRetablir = true;
            CardsUpdatePlafond.this.secondCntNat = this.val$crdNationalPlfd.getCardPlafondResumCnt();
            CardsUpdatePlafond cardsUpdatePlafond2 = CardsUpdatePlafond.this;
            CardsUpdatePlafond.access$000(cardsUpdatePlafond2, cardsUpdatePlafond2.cntNat, CardsUpdatePlafond.this.firstCntNat, CardsUpdatePlafond.this.secondCntNat, true);
            CardsUpdatePlafond.this.BtnRetablir.setText(" RETOUR ");
            CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$3$$ExternalSyntheticLambda1(this));
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-CardsUpdatePlafond$3(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond.this.uiManager.GoBack();
        }

        /* synthetic */ void lambda$actionPerformed$2$com-b3g-ui-page-CardsUpdatePlafond$3(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond cardsUpdatePlafond = CardsUpdatePlafond.this;
            CardsUpdatePlafond.access$000(cardsUpdatePlafond, cardsUpdatePlafond.cntNat, CardsUpdatePlafond.this.secondCntNat, CardsUpdatePlafond.this.firstCntNat, true);
            CardsUpdatePlafond.this.isRetablir = false;
            CardsUpdatePlafond.this.isFirstNext = true;
            CardsUpdatePlafond.this.BtnRetablir.setText(" RÉTABLIR ");
            CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$3$$ExternalSyntheticLambda2(this));
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-CardsUpdatePlafond$3(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond.this.uiManager.GoBack();
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ CardNationalPlafond val$crdNationalPlfd1;

        4(CardNationalPlafond cardNationalPlafond) {
            this.val$crdNationalPlfd1 = cardNationalPlafond;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CardsUpdatePlafond.this.isRetablir) {
                try {
                    CardsUpdatePlafond.this.TabsDetail.getSelectedIndex();
                } catch (Exception unused) {
                }
                CardsUpdatePlafond cardsUpdatePlafond = CardsUpdatePlafond.this;
                CardsUpdatePlafond.access$000(cardsUpdatePlafond, cardsUpdatePlafond.cntInt, CardsUpdatePlafond.this.secondCntInt, CardsUpdatePlafond.this.firstCntInt, true);
                CardsUpdatePlafond.this.isRetablir = false;
                CardsUpdatePlafond.this.isFirstNext = true;
                if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                    CihMBanking.sesPMTR.PlafondNext = false;
                    CardsUpdatePlafond.this.BtnSuivantInt.setUIID("op_BtnOppositionValidationGriseMarginLeft");
                } else {
                    CihMBanking.sesPMTR.PlafondNext = true;
                    CardsUpdatePlafond.this.BtnSuivantInt.setUIID("op_BtnOppositionValidationMarginLeftNew");
                }
                CardsUpdatePlafond.this.BtnRetablirInt.setText(" RÉTABLIR ");
                CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
                CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$4$$ExternalSyntheticLambda0(this));
                return;
            }
            CihMBanking.exitApplication = true;
            CihMBanking.sesPMTR.isBtnRetablirClick = true;
            CihMBanking.sesPMTR.PlafondNext = true;
            CardsUpdatePlafond.this.isFirstNext = false;
            CardsUpdatePlafond.this.isRetablir = true;
            CardsUpdatePlafond.this.secondCntInt = this.val$crdNationalPlfd1.getCardPlafondResumCnt();
            CardsUpdatePlafond cardsUpdatePlafond2 = CardsUpdatePlafond.this;
            CardsUpdatePlafond.access$000(cardsUpdatePlafond2, cardsUpdatePlafond2.cntInt, CardsUpdatePlafond.this.firstCntInt, CardsUpdatePlafond.this.secondCntInt, true);
            CardsUpdatePlafond.this.BtnRetablirInt.setText(" RETOUR ");
            CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$4$$ExternalSyntheticLambda1(this));
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-CardsUpdatePlafond$4(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond.this.uiManager.GoBack();
        }

        /* synthetic */ void lambda$actionPerformed$2$com-b3g-ui-page-CardsUpdatePlafond$4(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond cardsUpdatePlafond = CardsUpdatePlafond.this;
            CardsUpdatePlafond.access$000(cardsUpdatePlafond, cardsUpdatePlafond.cntInt, CardsUpdatePlafond.this.secondCntInt, CardsUpdatePlafond.this.firstCntInt, true);
            CardsUpdatePlafond.this.isRetablir = false;
            CardsUpdatePlafond.this.isFirstNext = true;
            CardsUpdatePlafond.this.BtnRetablirInt.setText(" RÉTABLIR ");
            CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$4$$ExternalSyntheticLambda2(this));
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-CardsUpdatePlafond$4(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond.this.uiManager.GoBack();
        }
    }

    private void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }

    public Container GetTransferSecurityForm(Container container, CardNationalPlafond cardNationalPlafond, Object obj) {
        CihMBanking.exitApplication = false;
        Container container2 = new Container(BoxLayout.y());
        Container container3 = new Container(BoxLayout.y());
        Label label = new Label("Service sélectionné : ");
        label.setUIID("g_lblDashBoardTitleOrange");
        Label label2 = new Label("Modification des plafonds");
        label2.setUIID("ad_lblValueBlue");
        Container container4 = new Container(BoxLayout.x());
        container4.add(label);
        container4.add(label2);
        Container container5 = new Container(BoxLayout.y());
        container5.setUIID("g_cntBorderV2");
        container3.add(container4);
        container3.add(container5);
        container2.add(container3);
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container6 = (Container) this.uib.findByName("Container17", createContainer);
        container6.removeAll();
        container6.setLayout(BoxLayout.y());
        container6.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 5(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 6(b3GCIHEcode, cardNationalPlafond, obj));
        createContainer.getAllStyles().setMargin(1, 1, 2, 2);
        return createContainer;
    }

    class 5 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        5(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            this.val$Parent.revalidate();
            CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$5$$ExternalSyntheticLambda1(this));
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-CardsUpdatePlafond$5(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond cardsUpdatePlafond = CardsUpdatePlafond.this;
            CardsUpdatePlafond.access$000(cardsUpdatePlafond, cardsUpdatePlafond.cntNat, CardsUpdatePlafond.this.secondCntNat, CardsUpdatePlafond.this.firstCntNat, true);
            CardsUpdatePlafond.this.isRetablir = false;
            CardsUpdatePlafond.this.isFirstNext = true;
            CardsUpdatePlafond.this.BtnRetablir.setText(" RÉTABLIR ");
            CardsUpdatePlafond.this.BtnRetablirInt.setText(" RÉTABLIR ");
            CardsUpdatePlafond.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond$5$$ExternalSyntheticLambda0(this));
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-CardsUpdatePlafond$5(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond.this.uiManager.GoBack();
        }
    }

    class 6 implements ActionListener {
        final /* synthetic */ CardNationalPlafond val$crdNationalPlfd;
        final /* synthetic */ B3GCIHEcode val$eCode1;
        final /* synthetic */ Object val$obj;

        6(B3GCIHEcode b3GCIHEcode, CardNationalPlafond cardNationalPlafond, Object obj) {
            this.val$eCode1 = b3GCIHEcode;
            this.val$crdNationalPlfd = cardNationalPlafond;
            this.val$obj = obj;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ServiceResponse access$300;
            try {
                CardsUpdatePlafond.access$102(CardsUpdatePlafond.this, this.val$eCode1.getValue());
                if (CardsUpdatePlafond.access$100(CardsUpdatePlafond.this).length() != 4) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CardsUpdatePlafond.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                    return;
                }
                String access$100 = CardsUpdatePlafond.access$100(CardsUpdatePlafond.this);
                if (this.val$crdNationalPlfd.flagRet) {
                    CardsUpdatePlafond cardsUpdatePlafond = CardsUpdatePlafond.this;
                    access$300 = CardsUpdatePlafond.access$200(cardsUpdatePlafond, cardsUpdatePlafond.FillHashMapPlfdInitFromParams(this.val$obj), access$100);
                } else {
                    CardsUpdatePlafond cardsUpdatePlafond2 = CardsUpdatePlafond.this;
                    access$300 = CardsUpdatePlafond.access$300(cardsUpdatePlafond2, cardsUpdatePlafond2.FillHashMapFromParams(this.val$crdNationalPlfd, this.val$obj), access$100);
                }
                if (access$300.getStatusCode().equals("000")) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(access$300.getStatusLabel()), null);
                    CihMBanking.sesPMTR.setRtrIsSame(true);
                    CihMBanking.sesPMTR.setPaimIsSame(true);
                    CihMBanking.sesPMTR.setComIsSame(true);
                    CihMBanking.sesPMTR.setDureeIsSame(true);
                    CihMBanking.sesPMTR.isPlafondsChanged = false;
                    CihMBanking.sesPMTR.isBtnRetablirClick = false;
                    CihMBanking.exitApplication = true;
                    for (int i = 0; i < CihMBanking.sesPMTR.getSessionClient().getClient_CardList().size(); i++) {
                        if (((Card) CihMBanking.sesPMTR.getSessionClient().getClient_CardList().get(i)).plainCardNumber.equals(CardsUpdatePlafond.this.currentCard.plainCardNumber)) {
                            ((Card) CihMBanking.sesPMTR.getSessionClient().getClient_CardList().get(i)).plafondCard = null;
                        }
                    }
                    CardsUpdatePlafond.this.uiManager.NavigateToPageById(4);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(access$300.getStatusLabel()), null);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private ServiceResponse updatePlafondProcess(Hashtable hashtable, String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        hashtable.put("PASSID", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(112);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public final Hashtable FillHashMapFromParams(CardNationalPlafond cardNationalPlafond, Object obj) {
        CardNationalPlafond cardNationalPlafond2;
        String str;
        String str2;
        Object obj2;
        String str3;
        Object obj3;
        CardsUpdatePlafond cardsUpdatePlafond;
        String str4;
        CardNationalPlafond cardNationalPlafond3 = cardNationalPlafond;
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CARDNUMBER", ((Card) obj).plainCardNumber);
        hashtable.put("RETRAIT_ACTION", cardNationalPlafond3.actionRtr);
        hashtable.put("PAIEMENT_ACTION", cardNationalPlafond3.actionPaiem);
        hashtable.put("COMMERCE_ACTION", cardNationalPlafond3.actionCom);
        hashtable.put("DUREE_ACTION", cardNationalPlafond3.actionDuree);
        String str5 = "NVPLAFONDINT_PAIEMENT";
        String str6 = "EComInt";
        String str7 = "RetraitInt";
        if (cardNationalPlafond3.selectedTabs == 0) {
            int i = 0;
            while (i < CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.size()) {
                CardPlafond cardPlafond = (CardPlafond) CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.get(i);
                if (cardPlafond.PlafondType.equals("RetraitInt")) {
                    hashtable.put("NVPLAFONDINT_RETRAIT", cardPlafond.Plafond);
                    if (cardPlafond.IsDisabled.equals("true")) {
                        hashtable.put("RETRAITINT_ACTION", "DESACTIVER");
                    } else {
                        hashtable.put("RETRAITINT_ACTION", "ACTIVER");
                    }
                }
                if (cardPlafond.PlafondType.equals("PaiementInt")) {
                    hashtable.put("NVPLAFONDINT_PAIEMENT", cardPlafond.Plafond);
                    if (cardPlafond.IsDisabled.equals("true")) {
                        hashtable.put("PAIEMENTINT_ACTION", "DESACTIVER");
                    } else {
                        hashtable.put("PAIEMENTINT_ACTION", "ACTIVER");
                    }
                }
                if (cardPlafond.PlafondType.equals("EComInt")) {
                    hashtable.put("NVPLAFONDINT_COMMERCE", cardPlafond.Plafond);
                    if (cardPlafond.IsDisabled.equals("true")) {
                        hashtable.put("COMMERCEINT_ACTION", "DESACTIVER");
                    } else {
                        hashtable.put("COMMERCEINT_ACTION", "ACTIVER");
                    }
                }
                i++;
                cardNationalPlafond3 = cardNationalPlafond;
            }
            cardNationalPlafond2 = cardNationalPlafond3;
            if (cardNationalPlafond2.SliderRetrait.txtSlider != null) {
                cardsUpdatePlafond = this;
                str4 = " ";
                hashtable.put("NVPLAFOND_RETRAIT", String.valueOf(cardsUpdatePlafond.formatNumber(cardNationalPlafond2.SliderRetrait.txtSlider.substring(0, cardNationalPlafond2.SliderRetrait.txtSlider.length() - 4), str4)));
            } else {
                cardsUpdatePlafond = this;
                str4 = " ";
                hashtable.put("NVPLAFOND_RETRAIT", cardsUpdatePlafond.getPlafond("RetraitNat"));
            }
            if (cardNationalPlafond2.SliderPaiement.txtSlider != null) {
                hashtable.put("NVPLAFOND_PAIEMENT", String.valueOf(cardsUpdatePlafond.formatNumber(cardNationalPlafond2.SliderPaiement.txtSlider.substring(0, cardNationalPlafond2.SliderPaiement.txtSlider.length() - 4), str4)));
            } else {
                hashtable.put("NVPLAFOND_PAIEMENT", cardsUpdatePlafond.getPlafond("PaiementNat"));
            }
            if (cardNationalPlafond2.SliderCommerce.txtSlider != null) {
                hashtable.put("NVPLAFOND_COMMERCE", String.valueOf(cardsUpdatePlafond.formatNumber(cardNationalPlafond2.SliderCommerce.txtSlider.substring(0, cardNationalPlafond2.SliderCommerce.txtSlider.length() - 4), str4)));
            } else {
                hashtable.put("NVPLAFOND_COMMERCE", cardsUpdatePlafond.getPlafond("EComNat"));
            }
        } else {
            cardNationalPlafond2 = cardNationalPlafond3;
            Object obj4 = "NVPLAFONDINT_COMMERCE";
            Object obj5 = "COMMERCEINT_ACTION";
            int i2 = 0;
            while (i2 < CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.size()) {
                CardPlafond cardPlafond2 = (CardPlafond) CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.get(i2);
                int i3 = i2;
                if (cardPlafond2.PlafondType.equals(str7)) {
                    str2 = str7;
                    str = str6;
                    hashtable.put("NVPLAFONDINT_RETRAIT", Integer.valueOf(formatNumber(cardNationalPlafond2.SliderRetrait.txtSlider.substring(0, cardNationalPlafond2.SliderRetrait.txtSlider.length() - 4), " ")));
                    if (cardPlafond2.IsDisabled.equals("true")) {
                        hashtable.put("RETRAITINT_ACTION", "DESACTIVER");
                    } else {
                        hashtable.put("RETRAITINT_ACTION", "ACTIVER");
                    }
                } else {
                    str = str6;
                    str2 = str7;
                }
                if (cardPlafond2.PlafondType.equals("PaiementInt")) {
                    hashtable.put(str5, Integer.valueOf(formatNumber(cardNationalPlafond2.SliderPaiement.txtSlider.substring(0, cardNationalPlafond2.SliderPaiement.txtSlider.length() - 4), " ")));
                    if (cardPlafond2.IsDisabled.equals("true")) {
                        hashtable.put("PAIEMENTINT_ACTION", "DESACTIVER");
                    } else {
                        hashtable.put("PAIEMENTINT_ACTION", "ACTIVER");
                    }
                }
                String str8 = str;
                if (cardPlafond2.PlafondType.equals(str8)) {
                    str3 = str5;
                    obj3 = obj4;
                    hashtable.put(obj3, Integer.valueOf(formatNumber(cardNationalPlafond2.SliderCommerce.txtSlider.substring(0, cardNationalPlafond2.SliderCommerce.txtSlider.length() - 4), " ")));
                    if (cardPlafond2.IsDisabled.equals("true")) {
                        obj2 = obj5;
                        hashtable.put(obj2, "DESACTIVER");
                    } else {
                        obj2 = obj5;
                        hashtable.put(obj2, "ACTIVER");
                    }
                } else {
                    obj2 = obj5;
                    str3 = str5;
                    obj3 = obj4;
                }
                obj5 = obj2;
                obj4 = obj3;
                str5 = str3;
                i2 = i3 + 1;
                str6 = str8;
                str7 = str2;
            }
            hashtable.put("NVPLAFOND_RETRAIT", getPlafond("RetraitNat"));
            hashtable.put("NVPLAFOND_PAIEMENT", getPlafond("PaiementNat"));
            hashtable.put("NVPLAFOND_COMMERCE", getPlafond("EComNat"));
        }
        hashtable.put("NVDURRE", cardNationalPlafond2.SliderDuree.monthNumber);
        return hashtable;
    }

    private String getPlafond(String str) {
        String str2 = null;
        for (int i = 0; i < CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.size(); i++) {
            CardPlafond cardPlafond = (CardPlafond) CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.get(i);
            if (str.equals(cardPlafond.PlafondType)) {
                str2 = cardPlafond.Plafond.toString();
            }
        }
        return str2.contains(".") ? DataTools.split(str2, ".")[0] : str2;
    }

    private ServiceResponse ResetPlafondCard(Hashtable hashtable, String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        hashtable.put("PASSID", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setServiceId(119);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public final Hashtable FillHashMapPlfdInitFromParams(Object obj) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSION_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CARDNUMBER", ((Card) obj).plainCardNumber);
        hashtable.put("ISRETABLIRCLICK", "true");
        return hashtable;
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
