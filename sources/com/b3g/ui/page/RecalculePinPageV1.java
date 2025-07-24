package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.services.PinCard;
import com.b3g.services.ServiceManager;
import com.b3g.tools.StringTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class RecalculePinPageV1 extends B3GPage {
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    private ArrayList cards;
    private Dialog dlg;
    private TextField txtHBPassWord;
    private final UIBuilder uib = new UIBuilder();
    private final String selectedStr = "Séléctionner";
    private PinCard currentCard = new PinCard();
    private final String[] operators = {"MAROC TELECOM", "INWI", "MEDITEL", "AUTRES"};

    static /* synthetic */ ArrayList access$000(RecalculePinPageV1 recalculePinPageV1) {
        return recalculePinPageV1.cards;
    }

    static /* synthetic */ String access$100(RecalculePinPageV1 recalculePinPageV1, String str) {
        return recalculePinPageV1.getOperatorId(str);
    }

    static /* synthetic */ PinCard access$200(RecalculePinPageV1 recalculePinPageV1) {
        return recalculePinPageV1.currentCard;
    }

    static /* synthetic */ PinCard access$202(RecalculePinPageV1 recalculePinPageV1, PinCard pinCard) {
        recalculePinPageV1.currentCard = pinCard;
        return pinCard;
    }

    static /* synthetic */ String access$300(RecalculePinPageV1 recalculePinPageV1) {
        return recalculePinPageV1.VnewaliasText;
    }

    static /* synthetic */ String access$302(RecalculePinPageV1 recalculePinPageV1, String str) {
        recalculePinPageV1.VnewaliasText = str;
        return str;
    }

    public RecalculePinPageV1(Object obj, Object obj2, int i) {
        this.cards = new ArrayList();
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        this.cards = getCardsList();
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BorderLayout());
        TableLayout tableLayout = new TableLayout(1, 2);
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            SpanLabel spanLabel = new SpanLabel("Ce service n'est pas disponible en mode démonstration.");
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            this.thisContainer.add("Center", spanLabel);
            Settings.setNightMode(this.thisContainer, 0);
            return this.thisContainer;
        }
        if (!this.cards.isEmpty()) {
            Container createContainer = this.uib.createContainer("/cihv3", "RecalculPin");
            createContainer.setUIID("mn_cntMenuItem");
            this.thisContainer.add("Center", createContainer);
            Container container = (Container) this.uib.findByName("ContainerCardSelect", createContainer);
            Container container2 = (Container) this.uib.findByName("ContainerOperatorSelect", createContainer);
            Picker picker = new Picker();
            String[] strArr = new String[this.cards.size()];
            strArr[0] = "Séléctionner";
            for (int i = 0; i < this.cards.size(); i++) {
                strArr[i] = StringTools.HideCardNumber(((PinCard) this.cards.get(i)).CardNumber);
            }
            container.removeAll();
            container.add(tableLayout.createConstraint().widthPercentage(100), drawPicker(picker, strArr));
            Picker picker2 = new Picker();
            container2.removeAll();
            container2.add(tableLayout.createConstraint().widthPercentage(100), drawPicker(picker2, this.operators));
            picker2.setSelectedString(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().phoneOperator);
            ((Button) this.uib.findByName("validateBtn", createContainer)).addActionListener(new 1(picker, picker2, createContainer));
        } else {
            Container container3 = new Container(new BoxLayout(2));
            container3.add(this.uiManager.GetCntMessage("Votre carte n'est pas eligible au recalcul en ligne. Merci de prendre contact avec votre Agence."));
            container3.getAllStyles().setMarginUnit(1);
            container3.getAllStyles().setMargin(2, 0, 0, 0);
            this.thisContainer.add("Center", container3);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Picker val$cardsPicker;
        final /* synthetic */ Container val$container;
        final /* synthetic */ Picker val$operatorsPicker;

        1(Picker picker, Picker picker2, Container container) {
            this.val$cardsPicker = picker;
            this.val$operatorsPicker = picker2;
            this.val$container = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String DisHidenCardNumber = StringTools.DisHidenCardNumber(this.val$cardsPicker.getText(), RecalculePinPageV1.access$000(RecalculePinPageV1.this));
            String access$100 = RecalculePinPageV1.access$100(RecalculePinPageV1.this, this.val$operatorsPicker.getText());
            if (this.val$cardsPicker.getText().length() == 0 || this.val$cardsPicker.getSelectedString().equals("Séléctionner") || this.val$cardsPicker.getSelectedString().equals("اختيار") || this.val$cardsPicker.getSelectedString().equals("Select") || this.val$cardsPicker.getSelectedString().equals("seleccionar") || this.val$operatorsPicker.getText().length() == 0 || this.val$operatorsPicker.getSelectedString().equals("Séléctionner") || this.val$operatorsPicker.getSelectedString().equals("اختيار") || this.val$operatorsPicker.getSelectedString().equals("Select") || this.val$operatorsPicker.getSelectedString().equals("seleccionar")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(RecalculePinPageV1.this.uiManager.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            Iterator it = RecalculePinPageV1.access$000(RecalculePinPageV1.this).iterator();
            while (it.hasNext()) {
                PinCard pinCard = (PinCard) it.next();
                if (pinCard.CardNumber.equals(DisHidenCardNumber)) {
                    RecalculePinPageV1.access$202(RecalculePinPageV1.this, pinCard);
                    RecalculePinPageV1.access$200(RecalculePinPageV1.this).Operator = access$100;
                }
            }
            CihMBanking.sesPMTR.setSessionSavedContainer(RecalculePinPageV1.this.uiManager.stateMachine.findCntGlobalTransfer(this.val$container));
            RecalculePinPageV1 recalculePinPageV1 = RecalculePinPageV1.this;
            Container container = recalculePinPageV1.thisContainer;
            Container container2 = this.val$container;
            RecalculePinPageV1 recalculePinPageV12 = RecalculePinPageV1.this;
            recalculePinPageV1.SwitchTransfertForms(container, container2, recalculePinPageV12.GetTransferSecurityForm(recalculePinPageV12.thisContainer, this.val$container, RecalculePinPageV1.access$200(RecalculePinPageV1.this)), false);
        }
    }

    public Container GetTransferSecurityForm(Container container, Container container2, PinCard pinCard) {
        B3GCIHEcode b3GCIHEcode = new B3GCIHEcode();
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "Codecnt1");
        Container container3 = (Container) this.uib.findByName("Container17", createContainer);
        container3.removeAll();
        container3.setLayout(BoxLayout.y());
        container3.add(b3GCIHEcode.getComponent());
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 2(container, createContainer, container2));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 3(b3GCIHEcode, pinCard));
        return createContainer;
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$Parent;
        final /* synthetic */ Container val$cntTransfertSecurityForm;
        final /* synthetic */ Container val$firstCnt;

        2(Container container, Container container2, Container container3) {
            this.val$Parent = container;
            this.val$cntTransfertSecurityForm = container2;
            this.val$firstCnt = container3;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            RecalculePinPageV1.this.SwitchTransfertForms(this.val$Parent, this.val$cntTransfertSecurityForm, this.val$firstCnt, false);
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ PinCard val$card;
        final /* synthetic */ B3GCIHEcode val$eCode1;

        3(B3GCIHEcode b3GCIHEcode, PinCard pinCard) {
            this.val$eCode1 = b3GCIHEcode;
            this.val$card = pinCard;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            RecalculePinPageV1.access$302(RecalculePinPageV1.this, this.val$eCode1.getValue());
            String access$300 = RecalculePinPageV1.access$300(RecalculePinPageV1.this);
            if (RecalculePinPageV1.access$300(RecalculePinPageV1.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(RecalculePinPageV1.this.uiManager.stateMachine, "Veuillez saisir le mot de passe", null);
                return;
            }
            ServiceResponse SendPinProcess = RecalculePinPageV1.this.SendPinProcess(this.val$card, access$300);
            if (!SendPinProcess.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(RecalculePinPageV1.this.uiManager.stateMachine, SendPinProcess.getStatusLabel(), null);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(RecalculePinPageV1.this.uiManager.stateMachine, SendPinProcess.getStatusLabel(), null);
                CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(106);
            }
        }
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private String getOperatorId(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2093661423:
                if (str.equals("ORANGE Marruecos")) {
                    c = 0;
                    break;
                }
                break;
            case -1955522002:
                if (str.equals("ORANGE")) {
                    c = 1;
                    break;
                }
                break;
            case -1861538016:
                if (str.equals("ORANGE MAROC")) {
                    c = 2;
                    break;
                }
                break;
            case -1860552928:
                if (str.equals("ORANGE Maroc")) {
                    c = 3;
                    break;
                }
                break;
            case -1854319787:
                if (str.equals("Número extranjero")) {
                    c = 4;
                    break;
                }
                break;
            case -884157378:
                if (str.equals("ORANGE Morocco")) {
                    c = 5;
                    break;
                }
                break;
            case 72245:
                if (str.equals("IAM")) {
                    c = 6;
                    break;
                }
                break;
            case 2252471:
                if (str.equals("INWI")) {
                    c = 7;
                    break;
                }
                break;
            case 377495720:
                if (str.equals("INWI \"انوي\"")) {
                    c = '\b';
                    break;
                }
                break;
            case 877967413:
                if (str.equals("Foreign number")) {
                    c = '\t';
                    break;
                }
                break;
            case 944108448:
                if (str.equals("Numéro étranger")) {
                    c = '\n';
                    break;
                }
                break;
            case 1108019945:
                if (str.equals("Maroc Telecom")) {
                    c = 11;
                    break;
                }
                break;
            case 1111190515:
                if (str.equals("رقم أجنبي")) {
                    c = '\f';
                    break;
                }
                break;
            case 1512146854:
                if (str.equals("أورانج المغرب ORANGE")) {
                    c = '\r';
                    break;
                }
                break;
            case 1658775230:
                if (str.equals("MEDITEL")) {
                    c = 14;
                    break;
                }
                break;
            case 1696003817:
                if (str.equals("MAROC TELECOM")) {
                    c = 15;
                    break;
                }
                break;
            case 1861262649:
                if (str.equals("إتصالات المغرب")) {
                    c = 16;
                    break;
                }
                break;
            case 1941977568:
                if (str.equals("AUTRES")) {
                    c = 17;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case '\r':
            case 14:
                return "03";
            case 4:
            case '\t':
            case '\n':
            case '\f':
            case 17:
            default:
                return "00";
            case 6:
            case 11:
            case 15:
            case 16:
                return "01";
            case 7:
            case '\b':
                return "02";
        }
    }

    private ArrayList getCardsList() {
        new ArrayList();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("SERVICE_ID_PARENT", Integer.toString(900129));
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900129);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return getData((ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0));
    }

    public ArrayList getData(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        if (serviceResponse.getParamsOut().size() > 1) {
            for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
                PinCard pinCard = new PinCard();
                pinCard.FillDataFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i)));
                arrayList.add(pinCard);
            }
        }
        return arrayList;
    }

    public ServiceResponse SendPinProcess(PinCard pinCard, String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSIONID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CardNumber", pinCard.CardNumber);
        hashtable.put("CardNumber", pinCard.CardNumber);
        hashtable.put("Code", pinCard.Code);
        hashtable.put("GSM1", pinCard.GSM);
        hashtable.put("GSM2", pinCard.GSM2);
        hashtable.put("Operator", pinCard.Operator);
        hashtable.put("Pass", str);
        hashtable.put("Interface", pinCard.Interface);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(182);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private Container drawPicker(Picker picker, String[] strArr) {
        Container container = new Container(new BorderLayout());
        container.setUIID("grey_1_1_1_1");
        picker.setType(4);
        picker.setUIID("lbl_regular");
        Card card = (Card) this.service;
        if (strArr != null) {
            picker.setStrings(strArr);
        }
        if (strArr.length == 1) {
            picker.setSelectedString(strArr[0]);
        } else if (card != null) {
            picker.setSelectedString(StringTools.HideCardNumber(card.cardNumber));
        } else {
            picker.setSelectedString("Séléctionner");
        }
        picker.setAlignment(4);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("newListBox.png"));
        container.add("Center", picker).add("East", label);
        container.setLeadComponent(picker);
        return container;
    }
}
