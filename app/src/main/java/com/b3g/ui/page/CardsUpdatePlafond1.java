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
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.StringUtil;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CardsUpdatePlafond1 extends B3GPage {
    Button BtnRetablir;
    Button BtnSuivant;
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    Container cnt;
    Container cnt2;
    Container cnt3;
    Container cntEmpty;
    Container cntMain;
    Container cntPopup;
    CardNationalPlafond crdNationalPlfd;
    Container firstCnt;
    Container secondCnt;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();
    public boolean isRetablir = false;
    public boolean isFirstNext = true;

    static /* synthetic */ void access$000(CardsUpdatePlafond1 cardsUpdatePlafond1, Container container, Container container2, Container container3, boolean z) {
        cardsUpdatePlafond1.SwitchTransfertForms(container, container2, container3, z);
    }

    static /* synthetic */ String access$100(CardsUpdatePlafond1 cardsUpdatePlafond1) {
        return cardsUpdatePlafond1.VnewaliasText;
    }

    static /* synthetic */ String access$102(CardsUpdatePlafond1 cardsUpdatePlafond1, String str) {
        cardsUpdatePlafond1.VnewaliasText = str;
        return str;
    }

    static /* synthetic */ ServiceResponse access$200(CardsUpdatePlafond1 cardsUpdatePlafond1, Hashtable hashtable, String str) {
        return cardsUpdatePlafond1.ResetPlafondCard(hashtable, str);
    }

    static /* synthetic */ ServiceResponse access$300(CardsUpdatePlafond1 cardsUpdatePlafond1, Hashtable hashtable, String str) {
        return cardsUpdatePlafond1.updatePlafondProcess(hashtable, str);
    }

    public CardsUpdatePlafond1(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        this.cntMain = new Container(new LayeredLayout());
        this.BtnSuivant = new Button(" SUIVANT ");
        this.BtnRetablir = new Button(" RÉTABLIR ");
        CihMBanking.exitApplication = true;
        this.cnt = new Container(new BoxLayout(2));
        Container container = new Container(new BoxLayout(2));
        container.setScrollableY(true);
        Container container2 = new Container(new GridLayout(1, 2));
        this.BtnRetablir.setUIID("op_BtnOppositionValidationMarginLeft");
        this.BtnSuivant.setUIID("op_BtnOppositionValidationGriseMarginLeft");
        this.BtnSuivant.setVerticalAlignment(4);
        this.BtnRetablir.setVerticalAlignment(4);
        this.BtnSuivant.setTextPosition(1);
        this.BtnRetablir.setTextPosition(1);
        container2.addComponent(this.BtnRetablir);
        container2.addComponent(this.BtnSuivant);
        Card card = (Card) CihMBanking.sesPMTR.objctCrdCurrent;
        CardNationalPlafond cardNationalPlafond = new CardNationalPlafond(this.uiManager, card, 0, container2);
        container.setUIID("mn_cntMenuItem");
        try {
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "CardDetailV2");
            this.uiManager.stateMachine.findLblCardNumberValue(createContainer).setText(card.cardNumber);
            this.uiManager.stateMachine.findLblCardEntitiledValue(createContainer).setText(card.entitled);
            this.uiManager.stateMachine.findLblCardBalanceValue(createContainer).remove();
            this.uiManager.stateMachine.findLblCardBalanceTitle(createContainer).remove();
            Container GetPageContainer = cardNationalPlafond.GetPageContainer();
            this.firstCnt = GetPageContainer;
            this.cnt.addComponent(GetPageContainer);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new B3gContainer(this.cnt, "PlAFONDS NATIONAUX"));
            container.addComponent(createContainer);
            this.uiManager.DrawTabsWithNavigation(createContainer, arrayList);
            this.cnt.addComponent(container2);
            this.BtnSuivant.addActionListener(new 1(cardNationalPlafond, container, card));
            this.BtnRetablir.addActionListener(new 2(cardNationalPlafond));
            this.cntMain.addComponent(container);
            this.thisContainer = this.cntMain;
            return this.thisContainer;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
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
            if (CihMBanking.sesPMTR.PlafondNext) {
                if (CardsUpdatePlafond1.this.isFirstNext) {
                    CihMBanking.sesPMTR.IsSuiv = true;
                    CardsUpdatePlafond1.this.secondCnt = this.val$crdNationalPlfd.getCardPlafondResumCnt();
                    CardsUpdatePlafond1 cardsUpdatePlafond1 = CardsUpdatePlafond1.this;
                    CardsUpdatePlafond1.access$000(cardsUpdatePlafond1, cardsUpdatePlafond1.cnt, CardsUpdatePlafond1.this.firstCnt, CardsUpdatePlafond1.this.secondCnt, true);
                    CihMBanking.sesPMTR.PlafondNext = true;
                    CardsUpdatePlafond1.this.isFirstNext = false;
                    CardsUpdatePlafond1.this.isRetablir = true;
                    CardsUpdatePlafond1.this.BtnRetablir.setText(" RETOUR ");
                    CardsUpdatePlafond1.this.uiManager.btnBack.getListeners().clear();
                    CardsUpdatePlafond1.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond1$1$$ExternalSyntheticLambda3(this));
                    return;
                }
                CihMBanking.sesPMTR.setSessionSavedContainer(this.val$cntOpMain);
                Container container = new Container(new BoxLayout(2));
                CardsUpdatePlafond1 cardsUpdatePlafond12 = CardsUpdatePlafond1.this;
                container.addComponent(cardsUpdatePlafond12.GetTransferSecurityForm(cardsUpdatePlafond12.cntMain, this.val$crdNationalPlfd, this.val$crd));
                CardsUpdatePlafond1 cardsUpdatePlafond13 = CardsUpdatePlafond1.this;
                CardsUpdatePlafond1.access$000(cardsUpdatePlafond13, cardsUpdatePlafond13.thisContainer, this.val$cntOpMain, container, false);
                CardsUpdatePlafond1.this.uiManager.btnBack.getListeners().clear();
                CardsUpdatePlafond1.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond1$1$$ExternalSyntheticLambda4(this));
                CardsUpdatePlafond1.this.isFirstNext = false;
            }
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-CardsUpdatePlafond1$1(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond1 cardsUpdatePlafond1 = CardsUpdatePlafond1.this;
            CardsUpdatePlafond1.access$000(cardsUpdatePlafond1, cardsUpdatePlafond1.cnt, CardsUpdatePlafond1.this.secondCnt, CardsUpdatePlafond1.this.firstCnt, true);
            CardsUpdatePlafond1.this.isRetablir = false;
            CardsUpdatePlafond1.this.isFirstNext = true;
            CardsUpdatePlafond1.this.BtnRetablir.setText(" RÉTABLIR ");
            CardsUpdatePlafond1.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond1.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond1$1$$ExternalSyntheticLambda1(this));
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-CardsUpdatePlafond1$1(ActionEvent actionEvent) {
            CardsUpdatePlafond1.this.uiManager.GoBack();
        }

        /* synthetic */ void lambda$actionPerformed$4$com-b3g-ui-page-CardsUpdatePlafond1$1(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond1.this.cntMain.replace((Container) CardsUpdatePlafond1.this.cntMain.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            CardsUpdatePlafond1.this.cntMain.revalidate();
            CardsUpdatePlafond1.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond1.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond1$1$$ExternalSyntheticLambda2(this));
        }

        /* synthetic */ void lambda$actionPerformed$3$com-b3g-ui-page-CardsUpdatePlafond1$1(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond1 cardsUpdatePlafond1 = CardsUpdatePlafond1.this;
            CardsUpdatePlafond1.access$000(cardsUpdatePlafond1, cardsUpdatePlafond1.cnt, CardsUpdatePlafond1.this.secondCnt, CardsUpdatePlafond1.this.firstCnt, true);
            CardsUpdatePlafond1.this.isRetablir = false;
            CardsUpdatePlafond1.this.isFirstNext = true;
            CardsUpdatePlafond1.this.BtnRetablir.setText(" RÉTABLIR ");
            CardsUpdatePlafond1.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond1.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond1$1$$ExternalSyntheticLambda0(this));
        }

        /* synthetic */ void lambda$actionPerformed$2$com-b3g-ui-page-CardsUpdatePlafond1$1(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond1.this.uiManager.GoBack();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ CardNationalPlafond val$crdNationalPlfd;

        2(CardNationalPlafond cardNationalPlafond) {
            this.val$crdNationalPlfd = cardNationalPlafond;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CardsUpdatePlafond1.this.isRetablir) {
                CardsUpdatePlafond1 cardsUpdatePlafond1 = CardsUpdatePlafond1.this;
                CardsUpdatePlafond1.access$000(cardsUpdatePlafond1, cardsUpdatePlafond1.cnt, CardsUpdatePlafond1.this.secondCnt, CardsUpdatePlafond1.this.firstCnt, true);
                CardsUpdatePlafond1.this.isRetablir = false;
                CardsUpdatePlafond1.this.isFirstNext = true;
                if (CihMBanking.sesPMTR.isRtrIsSame() && CihMBanking.sesPMTR.isPaimIsSame() && CihMBanking.sesPMTR.isComIsSame() && CihMBanking.sesPMTR.isDureeIsSame()) {
                    CihMBanking.sesPMTR.PlafondNext = false;
                    CardsUpdatePlafond1.this.BtnSuivant.setUIID("op_BtnOppositionValidationGriseMarginLeft");
                } else {
                    CihMBanking.sesPMTR.PlafondNext = true;
                    CardsUpdatePlafond1.this.BtnSuivant.setUIID("op_BtnOppositionValidationMarginLeft");
                }
                CardsUpdatePlafond1.this.BtnRetablir.setText(" RÉTABLIR ");
                CardsUpdatePlafond1.this.uiManager.btnBack.getListeners().clear();
                CardsUpdatePlafond1.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond1$2$$ExternalSyntheticLambda1(this));
                return;
            }
            CihMBanking.exitApplication = true;
            CihMBanking.sesPMTR.isBtnRetablirClick = true;
            CihMBanking.sesPMTR.PlafondNext = true;
            CardsUpdatePlafond1.this.isFirstNext = false;
            CardsUpdatePlafond1.this.isRetablir = true;
            CardsUpdatePlafond1.this.secondCnt = this.val$crdNationalPlfd.getCardPlafondResumCnt();
            CardsUpdatePlafond1 cardsUpdatePlafond12 = CardsUpdatePlafond1.this;
            CardsUpdatePlafond1.access$000(cardsUpdatePlafond12, cardsUpdatePlafond12.cnt, CardsUpdatePlafond1.this.firstCnt, CardsUpdatePlafond1.this.secondCnt, true);
            CardsUpdatePlafond1.this.BtnRetablir.setText(" RETOUR ");
            CardsUpdatePlafond1.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond1.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond1$2$$ExternalSyntheticLambda2(this));
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-CardsUpdatePlafond1$2(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond1.this.uiManager.GoBack();
        }

        /* synthetic */ void lambda$actionPerformed$2$com-b3g-ui-page-CardsUpdatePlafond1$2(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond1 cardsUpdatePlafond1 = CardsUpdatePlafond1.this;
            CardsUpdatePlafond1.access$000(cardsUpdatePlafond1, cardsUpdatePlafond1.cnt, CardsUpdatePlafond1.this.secondCnt, CardsUpdatePlafond1.this.firstCnt, true);
            CardsUpdatePlafond1.this.isRetablir = false;
            CardsUpdatePlafond1.this.isFirstNext = true;
            CardsUpdatePlafond1.this.BtnRetablir.setText(" RÉTABLIR ");
            CardsUpdatePlafond1.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond1.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond1$2$$ExternalSyntheticLambda0(this));
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-CardsUpdatePlafond1$2(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond1.this.uiManager.GoBack();
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
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 3(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 4(b3GCIHEcode, cardNationalPlafond, obj));
        createContainer.getAllStyles().setMargin(1, 1, 2, 2);
        return createContainer;
    }

    class 3 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        3(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            this.val$Parent.revalidate();
            CardsUpdatePlafond1.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond1.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond1$3$$ExternalSyntheticLambda0(this));
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-page-CardsUpdatePlafond1$3(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond1 cardsUpdatePlafond1 = CardsUpdatePlafond1.this;
            CardsUpdatePlafond1.access$000(cardsUpdatePlafond1, cardsUpdatePlafond1.cnt, CardsUpdatePlafond1.this.secondCnt, CardsUpdatePlafond1.this.firstCnt, true);
            CardsUpdatePlafond1.this.isRetablir = false;
            CardsUpdatePlafond1.this.isFirstNext = true;
            CardsUpdatePlafond1.this.BtnRetablir.setText(" RÉTABLIR ");
            CardsUpdatePlafond1.this.uiManager.btnBack.getListeners().clear();
            CardsUpdatePlafond1.this.uiManager.btnBack.addActionListener(new CardsUpdatePlafond1$3$$ExternalSyntheticLambda1(this));
        }

        /* synthetic */ void lambda$actionPerformed$0$com-b3g-ui-page-CardsUpdatePlafond1$3(ActionEvent actionEvent) {
            CihMBanking.exitApplication = true;
            CardsUpdatePlafond1.this.uiManager.GoBack();
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ CardNationalPlafond val$crdNationalPlfd;
        final /* synthetic */ B3GCIHEcode val$eCode1;
        final /* synthetic */ Object val$obj;

        4(B3GCIHEcode b3GCIHEcode, CardNationalPlafond cardNationalPlafond, Object obj) {
            this.val$eCode1 = b3GCIHEcode;
            this.val$crdNationalPlfd = cardNationalPlafond;
            this.val$obj = obj;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ServiceResponse access$300;
            try {
                CardsUpdatePlafond1.access$102(CardsUpdatePlafond1.this, this.val$eCode1.getValue());
                if (CardsUpdatePlafond1.access$100(CardsUpdatePlafond1.this).length() != 4) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CardsUpdatePlafond1.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                    return;
                }
                String access$100 = CardsUpdatePlafond1.access$100(CardsUpdatePlafond1.this);
                if (this.val$crdNationalPlfd.flagRet) {
                    CardsUpdatePlafond1 cardsUpdatePlafond1 = CardsUpdatePlafond1.this;
                    access$300 = CardsUpdatePlafond1.access$200(cardsUpdatePlafond1, cardsUpdatePlafond1.FillHashMapPlfdInitFromParams(this.val$obj), access$100);
                } else {
                    CardsUpdatePlafond1 cardsUpdatePlafond12 = CardsUpdatePlafond1.this;
                    access$300 = CardsUpdatePlafond1.access$300(cardsUpdatePlafond12, cardsUpdatePlafond12.FillHashMapFromParams(this.val$crdNationalPlfd, this.val$obj), access$100);
                }
                if (access$300.getStatusCode().equals("000")) {
                    CardsUpdatePlafond1 cardsUpdatePlafond13 = new CardsUpdatePlafond1(CardsUpdatePlafond1.this.uiManager, this.val$obj, 143);
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(access$300.getStatusLabel()), null);
                    CihMBanking.sesPMTR.setRtrIsSame(true);
                    CihMBanking.sesPMTR.setPaimIsSame(true);
                    CihMBanking.sesPMTR.setComIsSame(true);
                    CihMBanking.sesPMTR.setDureeIsSame(true);
                    CihMBanking.sesPMTR.isPlafondsChanged = false;
                    CihMBanking.sesPMTR.isBtnRetablirClick = false;
                    CihMBanking.exitApplication = true;
                    CardsUpdatePlafond1.this.uiManager.NavigateToPage(cardsUpdatePlafond13);
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
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CARDNUMBER", ((Card) obj).plainCardNumber);
        hashtable.put("RETRAIT_ACTION", cardNationalPlafond.actionRtr);
        hashtable.put("PAIEMENT_ACTION", cardNationalPlafond.actionPaiem);
        hashtable.put("COMMERCE_ACTION", cardNationalPlafond.actionCom);
        hashtable.put("DUREE_ACTION", cardNationalPlafond.actionDuree);
        for (int i = 0; i < CihMBanking.sesPMTR.listAncPlafonds.CardPlafondList.size(); i++) {
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
        }
        if (cardNationalPlafond.SliderRetrait.txtSlider != null) {
            hashtable.put("NVPLAFOND_RETRAIT", String.valueOf(formatNumber(cardNationalPlafond.SliderRetrait.txtSlider.substring(0, cardNationalPlafond.SliderRetrait.txtSlider.length() - 4), " ")));
        } else {
            hashtable.put("NVPLAFOND_RETRAIT", getPlafond("RetraitNat"));
        }
        if (cardNationalPlafond.SliderPaiement.txtSlider != null) {
            hashtable.put("NVPLAFOND_PAIEMENT", String.valueOf(formatNumber(cardNationalPlafond.SliderPaiement.txtSlider.substring(0, cardNationalPlafond.SliderPaiement.txtSlider.length() - 4), " ")));
        } else {
            hashtable.put("NVPLAFOND_PAIEMENT", getPlafond("PaiementNat"));
        }
        if (cardNationalPlafond.SliderCommerce.txtSlider != null) {
            hashtable.put("NVPLAFOND_COMMERCE", String.valueOf(formatNumber(cardNationalPlafond.SliderCommerce.txtSlider.substring(0, cardNationalPlafond.SliderCommerce.txtSlider.length() - 4), " ")));
        } else {
            hashtable.put("NVPLAFOND_COMMERCE", getPlafond("EComNat"));
        }
        hashtable.put("NVDURRE", cardNationalPlafond.SliderDuree.monthNumber);
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
        return str2;
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
