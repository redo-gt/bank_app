package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.SavingsInsuranceDetails;
import com.b3g.services.ServiceManager;
import com.b3g.tools.NativeCall;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordionNw;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.Base64;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class InsuranceDocsPage extends B3GPage {
    public String referenceContract;
    private final UIBuilder uib;
    private final String selectedStr = "--";
    private boolean AnnualRegulatoryIsOpen = false;
    private boolean paymentCertificateIsOpen = false;
    private Picker pck1 = new Picker();
    private Picker pck2 = new Picker();

    static /* synthetic */ boolean access$000(InsuranceDocsPage insuranceDocsPage) {
        return insuranceDocsPage.AnnualRegulatoryIsOpen;
    }

    static /* synthetic */ boolean access$002(InsuranceDocsPage insuranceDocsPage, boolean z) {
        insuranceDocsPage.AnnualRegulatoryIsOpen = z;
        return z;
    }

    static /* synthetic */ void access$100(InsuranceDocsPage insuranceDocsPage, String str, String str2) {
        insuranceDocsPage.getReleveAnnuelDoc(str, str2);
    }

    static /* synthetic */ boolean access$200(InsuranceDocsPage insuranceDocsPage) {
        return insuranceDocsPage.paymentCertificateIsOpen;
    }

    static /* synthetic */ boolean access$202(InsuranceDocsPage insuranceDocsPage, boolean z) {
        insuranceDocsPage.paymentCertificateIsOpen = z;
        return z;
    }

    static /* synthetic */ void access$300(InsuranceDocsPage insuranceDocsPage, String str, String str2) {
        insuranceDocsPage.getAttestationVersementDoc(str, str2);
    }

    static /* synthetic */ void access$400(InsuranceDocsPage insuranceDocsPage, String str) {
        insuranceDocsPage.getAttestationSoldeDoc(str);
    }

    public void setReferenceContract(String str) {
        this.referenceContract = str;
    }

    public InsuranceDocsPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
        this.uib = new UIBuilder();
    }

    public Container GetPageContainer() {
        this.thisContainer = this.uib.createContainer(this.uiManager.ressource, "InsuranceDocs");
        String[] strArr = new String[0];
        try {
            new SavingsInsuranceDetails().referenceContrat = this.referenceContract;
            Vector vector = (Vector) getAllYears(this.referenceContract).getParamsOut().get("listeAnnees");
            strArr = new String[vector.size()];
            vector.toArray(strArr);
        } catch (Exception unused) {
        }
        Image image = this.uiManager.ressource.getImage("ArrowOpen (1).png");
        Image image2 = this.uiManager.ressource.getImage("ArrowClose (1).png");
        Container container = (Container) this.uib.findByName("MainCnt", this.thisContainer);
        Container drawMenuItem = drawMenuItem("Relevé réglementaire annuel", this.uiManager.ressource.getImage("insruance_releve_annuel.png"), true, null);
        Picker picker = new Picker();
        Button button = new Button();
        Container drawAccrdContent = drawAccrdContent(picker, button, strArr);
        Container container2 = new Container(new BoxLayout(2));
        container2.add(drawAccrdContent);
        if (strArr.length == 0) {
            button.setEnabled(false);
            picker.setEnabled(false);
            container2.add(getEmptyContentContainer("Relevé réglementaire annuel sera disponible prochainement."));
        }
        B3gAccordionNw drawAccordion = drawAccordion(drawMenuItem, container2);
        drawAccordion.addOnClickItemListener(new 1(drawAccordion, image, image2));
        button.addActionListener(new 2(picker));
        container.addComponent(drawAccordion);
        container.add(separator());
        Container drawMenuItem2 = drawMenuItem("Attestation des versements", this.uiManager.ressource.getImage("insurance_attestation_versement.png"), true, null);
        Picker picker2 = new Picker();
        Button button2 = new Button();
        Container drawAccrdContent2 = drawAccrdContent(picker2, button2, strArr);
        Container container3 = new Container(new BoxLayout(2));
        container3.add(drawAccrdContent2);
        if (strArr.length == 0) {
            button2.setEnabled(false);
            picker2.setEnabled(false);
            container3.add(getEmptyContentContainer("Attestation des versements sera disponible prochainement."));
        }
        B3gAccordionNw drawAccordion2 = drawAccordion(drawMenuItem2, container3);
        drawAccordion2.addOnClickItemListener(new 3(drawAccordion2, image, image2));
        button2.addActionListener(new 4(picker2));
        container.addComponent(drawAccordion2);
        container.add(separator());
        Button button3 = new Button();
        container.addComponent(drawMenuItem("Attestation de solde actualisé", this.uiManager.ressource.getImage("insurance_attestion_solde.png"), false, button3));
        button3.addActionListener(new 5());
        container.add(separator());
        container.setScrollableY(true);
        container.setScrollVisible(false);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ B3gAccordionNw val$accordionAnnualRegulatoryStatement;
        final /* synthetic */ Image val$closeIcon;
        final /* synthetic */ Image val$openIcon;

        1(B3gAccordionNw b3gAccordionNw, Image image, Image image2) {
            this.val$accordionAnnualRegulatoryStatement = b3gAccordionNw;
            this.val$openIcon = image;
            this.val$closeIcon = image2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            B3gAccordionNw.AccordionContent accordionContent = (B3gAccordionNw.AccordionContent) this.val$accordionAnnualRegulatoryStatement.getComponentAt(0);
            if (!InsuranceDocsPage.access$000(InsuranceDocsPage.this)) {
                InsuranceDocsPage.access$002(InsuranceDocsPage.this, true);
                accordionContent.setArrowIcon(this.val$openIcon);
            } else {
                InsuranceDocsPage.access$002(InsuranceDocsPage.this, false);
                accordionContent.setArrowIcon(this.val$closeIcon);
            }
            accordionContent.revalidate();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Picker val$pickerAnnualRegulatoryStatement;

        2(Picker picker) {
            this.val$pickerAnnualRegulatoryStatement = picker;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String selectedString = this.val$pickerAnnualRegulatoryStatement.getSelectedString();
            if (!selectedString.isEmpty() && !selectedString.equals("--")) {
                InsuranceDocsPage insuranceDocsPage = InsuranceDocsPage.this;
                InsuranceDocsPage.access$100(insuranceDocsPage, insuranceDocsPage.referenceContract, selectedString);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(InsuranceDocsPage.this.uiManager.stateMachine, "Veuillez selectionner une année", null);
            }
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ B3gAccordionNw val$accordionPaymentCertificate;
        final /* synthetic */ Image val$closeIcon;
        final /* synthetic */ Image val$openIcon;

        3(B3gAccordionNw b3gAccordionNw, Image image, Image image2) {
            this.val$accordionPaymentCertificate = b3gAccordionNw;
            this.val$openIcon = image;
            this.val$closeIcon = image2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            B3gAccordionNw.AccordionContent accordionContent = (B3gAccordionNw.AccordionContent) this.val$accordionPaymentCertificate.getComponentAt(0);
            if (!InsuranceDocsPage.access$200(InsuranceDocsPage.this)) {
                InsuranceDocsPage.access$202(InsuranceDocsPage.this, true);
                accordionContent.setArrowIcon(this.val$openIcon);
            } else {
                InsuranceDocsPage.access$202(InsuranceDocsPage.this, false);
                accordionContent.setArrowIcon(this.val$closeIcon);
            }
            accordionContent.revalidate();
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Picker val$pickerPaymentCertificate;

        4(Picker picker) {
            this.val$pickerPaymentCertificate = picker;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String selectedString = this.val$pickerPaymentCertificate.getSelectedString();
            if (!selectedString.isEmpty() && !selectedString.equals("--")) {
                InsuranceDocsPage insuranceDocsPage = InsuranceDocsPage.this;
                InsuranceDocsPage.access$300(insuranceDocsPage, insuranceDocsPage.referenceContract, selectedString);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(InsuranceDocsPage.this.uiManager.stateMachine, "Veuillez selectionner une année", null);
            }
        }
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            InsuranceDocsPage insuranceDocsPage = InsuranceDocsPage.this;
            InsuranceDocsPage.access$400(insuranceDocsPage, insuranceDocsPage.referenceContract);
        }
    }

    private Container drawMenuItem(String str, Image image, boolean z, Button button) {
        Container createContainer = this.uib.createContainer(this.uiManager.ressource, "InsuranceDocsItem");
        ((ImageViewer) this.uib.findByName("ImageIcon", createContainer)).setImage(image);
        ((SpanLabel) this.uib.findByName("ItemNameLbl", createContainer)).setText(str);
        Container container = (Container) this.uib.findByName("RightCnt", createContainer);
        if (!z) {
            container.removeAll();
            container.addComponent(downloadBtn(button));
            createContainer.refreshTheme();
        }
        return createContainer;
    }

    private Container downloadBtn(Button button) {
        Container encloseRightMiddle = FlowLayout.encloseRightMiddle(new Component[0]);
        button.setText("Télécharger");
        button.setIcon(this.uiManager.ressource.getImage("insurance_download.png"));
        button.setGap(15);
        button.setUIID("insuranceGrayBtnMenuItem");
        encloseRightMiddle.add(button);
        return encloseRightMiddle;
    }

    private Container drawAccrdContent(Picker picker, Button button, String[] strArr) {
        TableLayout tableLayout = new TableLayout(1, 2);
        Container container = new Container(tableLayout);
        new Picker();
        TableLayout.Constraint widthPercentage = tableLayout.createConstraint(0, 0).widthPercentage(50);
        TableLayout.Constraint widthPercentage2 = tableLayout.createConstraint(0, 1).widthPercentage(50);
        container.add(widthPercentage, drawPicker(picker, strArr));
        container.add(widthPercentage2, downloadBtn(button));
        container.setUIID("padding_1_1_1_1");
        return container;
    }

    private Container drawPicker(Picker picker, String[] strArr) {
        Container container = new Container(new BorderLayout());
        container.setUIID("grey_1_1_1_1");
        picker.setType(4);
        picker.setUIID("lbl_regular");
        if (strArr.length >= 1 || strArr != null) {
            picker.setStrings(strArr);
        } else {
            picker.setEnabled(false);
        }
        if (strArr.length >= 1) {
            picker.setSelectedString(strArr[0]);
        } else {
            picker.setSelectedString("--");
        }
        picker.setAlignment(4);
        Label label = new Label();
        label.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("newListBox.png"));
        container.add("Center", picker).add("East", label);
        container.setLeadComponent(picker);
        return container;
    }

    private Container separator() {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("margin_1_1_1_1");
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("hm_v2_MenuItemSep");
        Container container3 = new Container(new BoxLayout(2));
        container3.setUIID("hm_v2_MenuItemSep");
        container2.add(container3);
        container.add(container2);
        return container;
    }

    private B3gAccordionNw drawAccordion(Container container, Container container2) {
        Image image = this.uiManager.ressource.getImage("ArrowOpen (1).png");
        Image image2 = this.uiManager.ressource.getImage("ArrowClose (1).png");
        B3gAccordionNw b3gAccordionNw = new B3gAccordionNw();
        b3gAccordionNw.setScrollVisible(false);
        b3gAccordionNw.setScrollableY(false);
        b3gAccordionNw.setArrowUIID("Regroupement_margin_0_4_0_0");
        b3gAccordionNw.setCloseIcon(image2);
        b3gAccordionNw.setOpenIcon(image);
        b3gAccordionNw.addContent(container, container2);
        return b3gAccordionNw;
    }

    private ServiceResponse getAllYears(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSION_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("pReferenceContract", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(152);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private ServiceResponse getAttestationSolde(String str) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSION_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("pReferenceContract", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(151);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private void getAttestationSoldeDoc(String str) {
        FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
        new SavingsInsuranceDetails();
        ServiceResponse attestationSolde = getAttestationSolde(str);
        if (attestationSolde.getStatusCode().equals("000")) {
            byte[] decode = Base64.decode(attestationSolde.getStatusLabel().getBytes(), attestationSolde.getStatusLabel().getBytes().length);
            if (!Display.getInstance().getPlatformName().equals("ios")) {
                ((NativeCall) NativeLookup.create(NativeCall.class)).createPdfFromByteArray(decode, "Attestation_Solde" + getAttestationSolde(str) + ".pdf");
                return;
            }
            String str2 = fileSystemStorage.getAppHomePath() + "Attestation_Solde" + getAttestationSolde(str) + ".pdf";
            try {
                OutputStream openOutputStream = fileSystemStorage.openOutputStream(str2);
                try {
                    openOutputStream.write(decode);
                    if (openOutputStream != null) {
                        openOutputStream.close();
                    }
                } finally {
                }
            } catch (IOException unused) {
                this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Une erreur s'est produite , veuillez réessayer plus tard", null);
            }
            Display.getInstance().execute(str2);
            return;
        }
        this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Le service est momentanément indisponible. Veuillez réessayer ultérieurement.", null);
    }

    private ServiceResponse getReleveAnnuel(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSION_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("pReferenceContract", str);
        hashtable.put("Year", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(150);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private void getReleveAnnuelDoc(String str, String str2) {
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
            ServiceResponse releveAnnuel = getReleveAnnuel(str, str2);
            if (releveAnnuel.getStatusCode().equals("000")) {
                String statusLabel = releveAnnuel.getStatusLabel();
                byte[] decode = Base64.decode(statusLabel.getBytes(), statusLabel.getBytes().length);
                if (!Display.getInstance().getPlatformName().equals("ios")) {
                    ((NativeCall) NativeLookup.create(NativeCall.class)).createPdfFromByteArray(decode, "Releve_reglementaire_annuel_" + str2 + ".pdf");
                    return;
                }
                String str3 = fileSystemStorage.getAppHomePath() + "Releve_reglementaire_annuel_" + str2 + ".pdf";
                try {
                    OutputStream openOutputStream = fileSystemStorage.openOutputStream(str3);
                    try {
                        openOutputStream.write(decode);
                        if (openOutputStream != null) {
                            openOutputStream.close();
                        }
                    } finally {
                    }
                } catch (IOException unused) {
                    this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Une erreur s'est produite , veuillez réessayer plus tard", null);
                }
                Display.getInstance().execute(str3);
                return;
            }
            this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Le service est momentanément indisponible. Veuillez réessayer ultérieurement.", null);
            return;
        }
        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
    }

    private ServiceResponse getAttestationVersement(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("SESSION_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        hashtable.put("CLIENT_ID", "4434531");
        hashtable.put("pReferenceContract", str);
        hashtable.put("Year", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(149);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private void getAttestationVersementDoc(String str, String str2) {
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
            ServiceResponse attestationVersement = getAttestationVersement(str, str2);
            if (attestationVersement.getStatusCode().equals("000")) {
                String statusLabel = attestationVersement.getStatusLabel();
                byte[] decode = Base64.decode(statusLabel.getBytes(), statusLabel.getBytes().length);
                if (!Display.getInstance().getPlatformName().equals("ios")) {
                    ((NativeCall) NativeLookup.create(NativeCall.class)).createPdfFromByteArray(decode, "Attestation_Versements_" + str2 + ".pdf");
                    return;
                }
                String str3 = fileSystemStorage.getAppHomePath() + "Attestation_Versements_" + str2 + ".pdf";
                try {
                    OutputStream openOutputStream = fileSystemStorage.openOutputStream(str3);
                    try {
                        openOutputStream.write(decode);
                        if (openOutputStream != null) {
                            openOutputStream.close();
                        }
                    } finally {
                    }
                } catch (IOException unused) {
                    this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Une erreur s'est produite , veuillez réessayer plus tard", null);
                }
                Display.getInstance().execute(str3);
                return;
            }
            this.uiManager.ShowMessageTransaction(this.uiManager.stateMachine, "Le service est momentanément indisponible. Veuillez réessayer ultérieurement.", null);
            return;
        }
        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(this.uiManager.stateMachine, "Ce service n'est pas disponible en mode démostration ", null);
    }

    private Container getEmptyContentContainer(String str) {
        Container container = new Container(new BoxLayout(2));
        SpanLabel spanLabel = new SpanLabel(str);
        spanLabel.setTextUIID("DefaultContentText");
        container.add(spanLabel);
        container.setUIID("padding_1_1_1_1");
        return container;
    }
}
