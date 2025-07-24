package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.SahamSouscription;
import com.b3g.services.SinistreDeclare;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordion;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SinistreContratPage extends B3GPage {
    public B3GUiManager uiManager;
    UIBuilder uib = new UIBuilder();

    public SinistreContratPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public SinistreContratPage() {
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        ServiceResponse SouscriptionSAHAMProcess = new SahamSouscription().SouscriptionSAHAMProcess();
        if (SouscriptionSAHAMProcess.getStatusCode().equals("000")) {
            if (CihMBanking.sesPMTR.SahamSouscriptions.isEmpty()) {
                B3GUiManager b3GUiManager = this.uiManager;
                b3GUiManager.ShowMessageSinistre(b3GUiManager.stateMachine, DataTools.FormatUnicode(SouscriptionSAHAMProcess.getStatusLabel()), null);
            } else {
                this.thisContainer = getSahamSoucContaoner();
            }
        } else {
            B3GUiManager b3GUiManager2 = this.uiManager;
            b3GUiManager2.ShowMessageTransaction(b3GUiManager2.stateMachine, "Le service est momentanément indisponible. Veuillez réessayer ultérieurement.", null);
        }
        return this.thisContainer;
    }

    private Container getSahamSoucContaoner() {
        Label label = new Label("Liste des contrats d'assistance : ");
        label.setUIID("gb_lblGlobalHeaderTitleV2");
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        label.setScrollVisible(false);
        label.setEnabled(false);
        this.thisContainer = new Container(new BoxLayout(2));
        B3gAccordion b3gAccordion = new B3gAccordion();
        b3gAccordion.setCloseIcon((Image) null);
        b3gAccordion.setOpenIcon((Image) null);
        Button button = new Button(" Demande de dépannage ");
        button.setUIID("SuivantSinistre");
        Button button2 = new Button(" Demande de dépannage ");
        button2.setUIID("SuivantSinistreActivate");
        b3gAccordion.setOpenBtn(button2);
        b3gAccordion.setCloseBtn(button);
        for (int i = 0; i < CihMBanking.sesPMTR.SahamSouscriptions.size(); i++) {
            new Container(new BoxLayout(2));
            Container DrawItem = ((SahamSouscription) CihMBanking.sesPMTR.SahamSouscriptions.get(i)).DrawItem(this.uiManager.stateMachine, this.uiManager.ressource, this.PageId, this.service, this.thisContainer, this.thisContainer);
            DrawItem.setUIID("greyCntMRGPDD");
            b3gAccordion.addContent(DrawItem, NewDemande(i));
            b3gAccordion.setScrollableY(false);
            new Container(new BoxLayout(2));
            new Container(new BoxLayout(2)).setUIID("cn_greylayer");
        }
        this.thisContainer.addComponent(label);
        this.thisContainer.addComponent(b3gAccordion);
        this.thisContainer.forceRevalidate();
        this.thisContainer.setUIID("margin_0_0_2_2");
        return this.thisContainer;
    }

    public B3gAccordion drawAccordion(Container container, Container container2) {
        B3gAccordion b3gAccordion = new B3gAccordion();
        b3gAccordion.setScrollableY(false);
        b3gAccordion.setArrowUIID("Regroupement_margin_0_4_0_0");
        b3gAccordion.setCloseIcon((Image) null);
        b3gAccordion.setOpenIcon((Image) null);
        b3gAccordion.addContent(container, BoxLayout.encloseY(container2));
        b3gAccordion.revalidate();
        return b3gAccordion;
    }

    public Container NewDemande(int i) {
        Container container = new Container(new GridLayout(1, 1));
        container.setScrollableY(true);
        container.setScrollVisible(false);
        Container createContainer = this.uib.createContainer("/cihv3", "DemandeAssistanceCnt");
        ((Button) this.uib.findByName("Button1", createContainer)).addActionListener(new 1(i));
        container.add(createContainer);
        return container;
    }

    class 1 implements ActionListener {
        final /* synthetic */ int val$i;

        1(int i) {
            this.val$i = i;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (Display.getInstance().getLocationManager().isGPSDetectionSupported()) {
                if (!Display.getInstance().getLocationManager().isGPSEnabled()) {
                    SinistreContratPage.this.uiManager.ShowMessageSinistre(SinistreContratPage.this.uiManager.stateMachine, DataTools.FormatUnicode("Veuillez activer le service de localisation GPS"), null);
                    return;
                }
                ServiceResponse DeclareSinistreSaham = new SinistreDeclare().DeclareSinistreSaham(this.val$i);
                if (DeclareSinistreSaham.getStatusCode().equals("000")) {
                    SinistreContratPage sinistreContratPage = SinistreContratPage.this;
                    sinistreContratPage.ShowSAHAMPopUp(sinistreContratPage.uiManager.stateMachine, DataTools.FormatUnicode("Prière d’appeler le numéro qui sera affiché pour confirmer votre demande d’assistance."), null);
                } else {
                    SinistreContratPage.this.uiManager.ShowMessageTransactionForSinistre(SinistreContratPage.this.uiManager.stateMachine, DataTools.FormatUnicode(DeclareSinistreSaham.getStatusLabel()), null);
                }
            }
        }
    }

    public void ShowSAHAMPopUp(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUpAssistance");
            SpanLabel spanLabel = (SpanLabel) this.uib.findByName("SpanLabel", createContainer);
            Label label = (Label) this.uib.findByName("lblMsg1", createContainer);
            Label label2 = (Label) this.uib.findByName("lblMsg3", createContainer);
            Label label3 = (Label) this.uib.findByName("lblMsg2", createContainer);
            Container container = (Container) this.uib.findByName("CntPopDemoConfirmLeftBloc", createContainer);
            Container container2 = (Container) this.uib.findByName("CntPopuPDemobtnConfirm", createContainer);
            Button button = (Button) this.uib.findByName("BtnYest", createContainer);
            container.getStyle().setMargin(2, 0, 0, 0);
            label3.setHidden(true);
            ((Button) this.uib.findByName("BtnYestIcon", createContainer)).setHidden(true);
            ((Container) this.uib.findByName("CntPopDemoConfirmRightBloc", createContainer)).setHidden(true);
            label.setHidden(true);
            label2.setHidden(true);
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
            container2.getAllStyles().setMarginUnit(1);
            container2.getAllStyles().setMargin(2, 0, 0, 0);
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(false);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(230);
            dialog.getDialogStyle().setBgColor(1118481);
            dialog.getStyle().setPadding(0, 0, 0, 0);
            dialog.getStyle().setMargin(0, 0, 0, 0);
            dialog.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog.revalidate();
            stateMachine.findBtnYest(createContainer).setText("");
            spanLabel.setText(str);
            button.setIcon(open.getImage("ico_close_popup_NW.png"));
            button.addActionListener(new 2(dialog));
            stateMachine.findBtnNo(createContainer).addActionListener(new 3(dialog));
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 4(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        2(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            Display.getInstance().dial("0522647809");
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        3(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        4(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }
}
