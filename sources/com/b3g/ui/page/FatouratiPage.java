package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.FatouratyService;
import com.b3g.services.MTCCreance;
import com.b3g.services.MTCCreancier;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.tools.MtcDataManager;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class FatouratiPage extends B3GPage {
    DataTools dt = new DataTools();
    private ArrayList mtcImpayeListARR;

    static /* synthetic */ ArrayList access$000(FatouratiPage fatouratiPage) {
        return fatouratiPage.mtcImpayeListARR;
    }

    static /* synthetic */ ArrayList access$002(FatouratiPage fatouratiPage, ArrayList arrayList) {
        fatouratiPage.mtcImpayeListARR = arrayList;
        return arrayList;
    }

    public FatouratiPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        Label label = new Label("Référence Fatourati");
        label.setUIID("g_lblNotifOrange");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        label.setScrollVisible(false);
        label.setEnabled(false);
        this.thisContainer.addComponent(label);
        try {
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        if (CihMBanking.sesPMTR.getIsDemo() == 1) {
            SpanLabel spanLabel = new SpanLabel("Ce service n'est pas disponible en mode démonstration.");
            spanLabel.setUIID("dg_splblMsgCenter");
            spanLabel.setScrollVisible(false);
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblSPError");
            spanLabel.setIconUIID("g_cntEmpty");
            this.thisContainer.addComponent(spanLabel);
            return this.thisContainer;
        }
        new Container(new BoxLayout(2)).setUIID("ad_CntAccountDetailsMain");
        this.thisContainer.addComponent(getReferenceCnt());
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public Container getReferenceCnt() {
        Container container = new Container(new BoxLayout(2));
        Container container2 = new Container();
        container2.setUIID("g_cntBorderV2");
        container.addComponent(container2);
        TextField textField = new TextField();
        textField.setUIID("tr_txtAmount");
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "TransferFormTab2");
        this.uiManager.stateMachine.findCntPassValue(createContainer).removeAll();
        this.uiManager.stateMachine.findCntPassValue(createContainer).addComponent(textField);
        this.uiManager.stateMachine.findLblMotifTitle1(createContainer).setText("Référence Fatourati :");
        ((Label) this.uiManager.stateMachine.findLblMotifTitle11(createContainer)).setText("");
        container.addComponent(createContainer);
        this.uiManager.stateMachine.findCntValidateTransfer(createContainer).removeAll();
        this.uiManager.stateMachine.findCntValidateTransfer(createContainer).setLayout(new BoxLayout(2));
        Button button = new Button("VALIDER");
        button.setUIID("op_BtnOppositionValidationMarginLeft");
        button.setFocusable(true);
        button.setEnabled(true);
        button.addActionListener(new 1(textField, container));
        this.uiManager.stateMachine.findCntValidateTransfer(createContainer).addComponent(button);
        return container;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$refCnt;
        final /* synthetic */ TextField val$referenceText;

        1(TextField textField, Container container) {
            this.val$referenceText = textField;
            this.val$refCnt = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            FatouratiPage fatouratiPage = FatouratiPage.this;
            FatouratiPage.access$002(fatouratiPage, fatouratiPage.getUnpaidProcess(this.val$referenceText.getText()));
            CihMBanking.sesPMTR.setCurrentMTCImpayeFatourati(FatouratiPage.access$000(FatouratiPage.this));
            if (FatouratiPage.access$000(FatouratiPage.this).size() > 0) {
                FatouratiPage fatouratiPage2 = FatouratiPage.this;
                Container container = fatouratiPage2.thisContainer;
                Container container2 = this.val$refCnt;
                FatouratiPage fatouratiPage3 = FatouratiPage.this;
                fatouratiPage2.SwitchTransfertForms(container, container2, fatouratiPage3.GetMTCImpayeContainer(FatouratiPage.access$000(fatouratiPage3)), false);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(FatouratiPage.this.uiManager.stateMachine, "Veuillez réessayer avec une autre référence Fatourati", null);
        }
    }

    public Container GetMTCImpayeContainer(ArrayList arrayList) {
        new MtcDataManager();
        Container container = new Container(new BoxLayout(2));
        container.setUIID("Container");
        container.setEnabled(true);
        CihMBanking.sesPMTR.setCurrentMTCSelectedAlias("");
        CihMBanking.sesPMTR.setBillerType(3);
        MTCCreance mTCCreance = new MTCCreance();
        mTCCreance.NomCreance = ((FatouratyService) arrayList.get(0)).CreanceName;
        CihMBanking.sesPMTR.setSelectedMTCCreance(mTCCreance);
        mTCCreance.CodeCreance = ((FatouratyService) arrayList.get(0)).CreanceCode;
        MTCCreancier mTCCreancier = new MTCCreancier();
        mTCCreancier.NomCreancier = ((FatouratyService) arrayList.get(0)).CreancierName;
        mTCCreancier.CodeCreancier = ((FatouratyService) arrayList.get(0)).CreancierCode;
        CihMBanking.sesPMTR.setSelectedMTCCreancier(mTCCreancier);
        TableLayout tableLayout = new TableLayout(1, 2);
        Container container2 = new Container();
        container2.setLayout(tableLayout);
        container2.setUIID("Container");
        container2.setEnabled(true);
        TableLayout tableLayout2 = new TableLayout(1, 1);
        Container container3 = new Container();
        container3.setLayout(tableLayout2);
        container3.setUIID("MTCIconStyMTC");
        container3.setFocusable(false);
        container3.setScrollable(false);
        Button button = new Button();
        button.setUIID("crd_btnCardImage");
        button.setIcon(this.uiManager.ressource.getImage("black_pic.png"));
        button.setIcon(URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(button.getIcon().getWidth(), button.getIcon().getWidth(), 12569042), false), "Normal_https://www.cihnet.co.ma" + MTCCreancierPage.getPathUrlFromBinnerId(((FatouratyService) arrayList.get(0)).CreancierCode), "https://www.cihnet.co.ma" + MTCCreancierPage.getPathUrlFromBinnerId(((FatouratyService) arrayList.get(0)).CreancierCode), URLImage.RESIZE_SCALE));
        button.setScrollVisible(false);
        button.setFocusable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(100);
        createConstraint.setVerticalAlign(0);
        container3.addComponent(createConstraint, button);
        Container container4 = new Container(new BoxLayout(2));
        container4.setUIID("st_cntStatisticLeftBloc");
        container4.setFocusable(false);
        container4.setScrollVisible(false);
        Container container5 = new Container(new BoxLayout(2));
        container5.setUIID("g_cntDebitCredit");
        container5.setFocusable(false);
        container5.setScrollVisible(false);
        Label label = new Label(((FatouratyService) arrayList.get(0)).CreancierName);
        label.setUIID("g_lblDashBoardTitle");
        label.setFocusable(false);
        label.setScrollVisible(false);
        label.setVerticalAlignment(4);
        label.setTextPosition(1);
        Label label2 = new Label(((FatouratyService) arrayList.get(0)).CreancierDescription);
        label2.setUIID("g_lblDashBoardValueBlue");
        label2.setFocusable(false);
        label2.setScrollVisible(false);
        label2.setVerticalAlignment(4);
        label2.setTextPosition(1);
        container5.addComponent(label);
        container5.addComponent(label2);
        container4.addComponent(container5);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint(0, 0);
        createConstraint2.setWidthPercentage(20);
        createConstraint2.setHorizontalAlign(4);
        container2.addComponent(createConstraint2, container3);
        TableLayout.Constraint createConstraint3 = tableLayout.createConstraint(0, 1);
        createConstraint3.setWidthPercentage(80);
        container2.addComponent(createConstraint3, container4);
        container.addComponent(container2);
        container.addComponent(GetCntBorderSeparator());
        container.addComponent(GetKeyValueData("Service sélectionné", ((FatouratyService) arrayList.get(0)).CreanceName));
        container.addComponent(GetCntBorderSeparator());
        container.addComponent(GetKeyValueData(((FatouratyService) arrayList.get(0)).IdentifiantValue, ((FatouratyService) arrayList.get(0)).Identifiant));
        container.addComponent(GetCntBorderSeparator());
        container.addComponent(GetImpayeList(arrayList, new Label("0.00"), "0.00"));
        container.revalidate();
        return container;
    }

    public Container GetImpayeList(ArrayList arrayList, Label label, String str) {
        BorderLayout borderLayout = new BorderLayout();
        Container container = new Container();
        container.setLayout(borderLayout);
        container.setUIID("Container");
        container.setEnabled(true);
        container.addComponent("Center", GetMTCImpayeListContainer(arrayList, label));
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("CntMTCF");
        container2.setScrollVisible(false);
        if (arrayList.size() > 0) {
            container2.addComponent(GetMTCResumeValueFlow(label));
            container2.addComponent(GetValidateButton(label, str));
        }
        container.addComponent("South", container2);
        return container;
    }

    public Container GetMTCImpayeListContainer(ArrayList arrayList, Label label) {
        ArrayList arrayList2 = new ArrayList();
        FatouratyService fatouratyService = new FatouratyService();
        for (int i = 0; i < arrayList.size(); i++) {
            ((FatouratyService) arrayList.get(i)).groupKey = ((FatouratyService) arrayList.get(i)).DateFacture;
            arrayList2.add((B3gService) arrayList.get(i));
        }
        Container container = new Container(new BoxLayout(2));
        if (arrayList2.size() > 0) {
            this.uiManager.Draw_GroupBy(container, arrayList2, CihMBanking.sesPMTR.getIpadUiid(), 33, fatouratyService, label);
        } else {
            container.addComponent(this.uiManager.GetCntMessage(""));
        }
        return container;
    }

    public Container GetMTCResumeValue(Label label) {
        TableLayout tableLayout = new TableLayout(1, 1);
        Container container = new Container();
        container.setLayout(tableLayout);
        container.setUIID("crd_CntCardImage");
        container.setFocusable(false);
        container.setScrollable(false);
        Container container2 = new Container(new BoxLayout(1));
        container2.setUIID("mtc_CntimpayeValueResume");
        label.setUIID("g_lblNotifGrenPlafond");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        Label label2 = new Label("Montant Total : ");
        label2.setUIID("g_lblNotifPlafond");
        label2.setTextPosition(1);
        label2.setVerticalAlignment(4);
        Label label3 = new Label(" (DH)");
        label3.setUIID("g_lblNotifPlafond");
        label3.setTextPosition(1);
        label3.setVerticalAlignment(4);
        container2.addComponent(label2);
        container2.addComponent(label);
        container2.addComponent(label3);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(100);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(4);
        container.addComponent(createConstraint, container2);
        return container;
    }

    public Container GetMTCResumeValueFlow(Label label) {
        TableLayout tableLayout = new TableLayout(1, 1);
        Container container = new Container();
        container.setLayout(tableLayout);
        container.setUIID("crd_CntCardImage");
        container.setFocusable(false);
        container.setScrollable(false);
        Container container2 = new Container(new BoxLayout(1));
        container2.setUIID("mtc_CntimpayeValueResumeFlow");
        label.setUIID("g_lblNotifGrenPlafond");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        Label label2 = new Label("Montant Total : ");
        label2.setUIID("g_lblNotifPlafond");
        label2.setTextPosition(1);
        label2.setVerticalAlignment(4);
        Label label3 = new Label(" (DH)");
        label3.setUIID("g_lblNotifPlafond");
        label3.setTextPosition(1);
        label3.setVerticalAlignment(4);
        container2.addComponent(label2);
        container2.addComponent(label);
        container2.addComponent(label3);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(100);
        createConstraint.setVerticalAlign(4);
        createConstraint.setHorizontalAlign(3);
        container.addComponent(createConstraint, container2);
        return container;
    }

    public Container GetCntBorderSeparator() {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("g_cntBorderV2");
        return container;
    }

    public Container GetKeyValueData(String str, String str2) {
        Container container = new Container(new BoxLayout(3));
        container.setFocusable(false);
        container.setScrollVisible(false);
        container.setUIID("MTCKeyValueLabel");
        Label label = new Label(str + " : ");
        label.setUIID("g_lblDashBoardTitleOrange");
        label.setFocusable(false);
        label.setScrollVisible(false);
        label.setVerticalAlignment(4);
        label.setTextPosition(1);
        Button button = new Button(str2);
        button.setUIID("ad_lblValueBlue");
        button.setFocusable(true);
        button.setScrollVisible(false);
        button.setVerticalAlignment(4);
        button.setTextPosition(1);
        container.addComponent(label);
        container.addComponent(button);
        return container;
    }

    public Container GetValidateButton(Label label, String str) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("ContainerMTCF");
        container.setFocusable(false);
        container.setScrollVisible(false);
        Button button = new Button("SUIVANT");
        button.setAlignment(4);
        button.setVerticalAlignment(4);
        button.setScrollVisible(false);
        button.setFocusable(false);
        button.setUIID("op_BtnOppositionValidation");
        button.addActionListener(new 2(label, str));
        container.addComponent(button);
        return container;
    }

    class 2 implements ActionListener {
        final /* synthetic */ Label val$lblTotalImpaye;
        final /* synthetic */ String val$pFraisValue;

        2(Label label, String str) {
            this.val$lblTotalImpaye = label;
            this.val$pFraisValue = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            FatouratyService fatouratyService = new FatouratyService();
            fatouratyService.PrixTTC = this.val$lblTotalImpaye.getText();
            if (this.val$lblTotalImpaye.getText().equals("0.0") || this.val$lblTotalImpaye.getText().equals("0.00") || this.val$lblTotalImpaye.getText().equals(this.val$pFraisValue) || this.val$lblTotalImpaye.getText().equals(this.val$pFraisValue + "0")) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(FatouratiPage.this.uiManager.stateMachine, "Veuillez sélectionner un impayé", null);
                return;
            }
            MTCConfirmationFatouratiPage mTCConfirmationFatouratiPage = new MTCConfirmationFatouratiPage(FatouratiPage.this.uiManager, fatouratyService, 24);
            CihMBanking.sesPMTR.setCurrentMTCImpayeFatourati(FatouratiPage.access$000(FatouratiPage.this));
            FatouratiPage.this.uiManager.NavigateToPage(mTCConfirmationFatouratiPage);
        }
    }

    public ArrayList getUnpaidProcess(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ArrayList arrayList = new ArrayList();
        new ArrayList();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setServiceId(900104);
        hashtable.put("CLIENTID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("REFERENCE_FATOURATI", str);
        serviceRequest.setParamsIn(hashtable);
        ArrayList arrayList2 = (ArrayList) serviceManager.Run(serviceRequest);
        if (arrayList2.size() <= 0) {
            return arrayList;
        }
        ServiceResponse serviceResponse = (ServiceResponse) arrayList2.get(0);
        CihMBanking.sesPMTR.setCurrentMTCImpayeListStatusLabel(serviceResponse.getStatusLabel());
        return FillMTCImpayeArrayDataFromServiceResponse(serviceResponse);
    }

    public ArrayList FillMTCImpayeArrayDataFromServiceResponse(ServiceResponse serviceResponse) {
        ArrayList arrayList = new ArrayList();
        if (serviceResponse.getStatusCode().equals("000")) {
            for (int i = 0; i < serviceResponse.getParamsOut().size() - 1; i++) {
                new FatouratyService();
                arrayList.add(FillMTCImpayeFromServiceResponse((Hashtable) serviceResponse.getParamsOut().get(Integer.valueOf(i))));
            }
        }
        return arrayList;
    }

    public FatouratyService FillMTCImpayeFromServiceResponse(Hashtable hashtable) {
        FatouratyService fatouratyService = new FatouratyService();
        if (hashtable.get("dateFacture") != null) {
            fatouratyService.DateFacture = hashtable.get("dateFacture").toString();
        } else {
            fatouratyService.DateFacture = " ";
        }
        if (hashtable.get("description") != null) {
            fatouratyService.Description = hashtable.get("description").toString();
        } else {
            fatouratyService.Description = " ";
        }
        if (hashtable.get("idArticle") != null) {
            fatouratyService.IdArticle = hashtable.get("idArticle").toString();
        } else {
            fatouratyService.IdArticle = " ";
        }
        if (hashtable.get("prixTTC") != null) {
            fatouratyService.PrixTTC = hashtable.get("prixTTC").toString();
        } else {
            fatouratyService.PrixTTC = " ";
        }
        if (hashtable.get("typeArticle") != null) {
            fatouratyService.TypeArticle = hashtable.get("typeArticle").toString();
        } else {
            fatouratyService.TypeArticle = " ";
        }
        if (hashtable.get("referenceNumber") != null) {
            fatouratyService.ReferenceNumber = hashtable.get("referenceNumber").toString();
        } else {
            fatouratyService.ReferenceNumber = " ";
        }
        if (hashtable.get("ReferenceFatourati") != null) {
            fatouratyService.ReferenceFatourati = hashtable.get("ReferenceFatourati").toString();
        } else {
            fatouratyService.ReferenceFatourati = " ";
        }
        if (hashtable.get("ValeurFrais") != null) {
            fatouratyService.ValeurFrais = hashtable.get("ValeurFrais").toString();
        } else {
            fatouratyService.ValeurFrais = " ";
        }
        if (hashtable.get("UnpaidSize") != null) {
            fatouratyService.UnpaidSize = hashtable.get("UnpaidSize").toString();
        } else {
            fatouratyService.UnpaidSize = " ";
        }
        if (hashtable.get("Identifiant") != null) {
            fatouratyService.Identifiant = hashtable.get("Identifiant").toString();
        } else {
            fatouratyService.Identifiant = " ";
        }
        if (hashtable.get("IdentifiantValue") != null) {
            fatouratyService.IdentifiantValue = hashtable.get("IdentifiantValue").toString();
        } else {
            fatouratyService.IdentifiantValue = " ";
        }
        if (hashtable.get("FormInputTitle") != null) {
            fatouratyService.FormInputTitle = hashtable.get("FormInputTitle").toString();
        } else {
            fatouratyService.FormInputTitle = " ";
        }
        if (hashtable.get("FormInputValue") != null) {
            fatouratyService.FormInputValue = hashtable.get("FormInputValue").toString();
        } else {
            fatouratyService.FormInputValue = " ";
        }
        if (hashtable.get("FormInputLabel") != null) {
            fatouratyService.FormInputLabel = hashtable.get("FormInputLabel").toString();
        } else {
            fatouratyService.FormInputLabel = " ";
        }
        if (hashtable.get("BillerCode") != null) {
            fatouratyService.CreancierCode = hashtable.get("BillerCode").toString();
        } else {
            fatouratyService.CreancierCode = " ";
        }
        if (hashtable.get("CreanceName") != null) {
            fatouratyService.CreanceName = hashtable.get("CreanceName").toString();
        } else {
            fatouratyService.CreanceName = " ";
        }
        if (hashtable.get("CreancierDescription") != null) {
            fatouratyService.CreancierDescription = hashtable.get("CreancierDescription").toString();
        } else {
            fatouratyService.CreancierDescription = " ";
        }
        if (hashtable.get("CreancierName") != null) {
            fatouratyService.CreancierName = hashtable.get("CreancierName").toString();
        } else {
            fatouratyService.CreancierName = " ";
        }
        if (hashtable.get("BillerServiceId") != null) {
            fatouratyService.CreanceCode = hashtable.get("BillerServiceId").toString();
        } else {
            fatouratyService.CreanceCode = " ";
        }
        fatouratyService.IsSelected = "0";
        return fatouratyService;
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }
}
