package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.Device;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.B3gAccordion;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class appareilsDetailPage extends B3GPage {
    static int countDevice;
    UIBuilder uib = new UIBuilder();
    ArrayList listAcc = new ArrayList();
    Device D = new Device();

    public appareilsDetailPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        CihMBanking.exitApplication = true;
    }

    public Container GetPageContainer() {
        Container container = new Container(new BoxLayout(2));
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("bf_cntBeneficarySeparator");
        new Container().setUIID("padding_1_1_1_1");
        SpanLabel spanLabel = new SpanLabel("Mes appareils");
        SpanLabel spanLabel2 = new SpanLabel("Vous pouvez activer les notifications que sur un seul appareil a la fois");
        SpanLabel spanLabel3 = new SpanLabel("Vous ne disposez d'aucun appareil");
        container.setUIID("padding_1_1_2_2");
        container.setScrollableY(true);
        container.setScrollVisible(false);
        spanLabel.setTextUIID("gb_lblGlobalHeaderTitleV5");
        spanLabel2.setTextUIID("lbl_regular");
        spanLabel3.setTextUIID("lbl_regular");
        Container container3 = new Container(new BorderLayout());
        Label label = new Label();
        label.setIcon(this.uiManager.ressource.getImage("Lampe.png"));
        label.getAllStyles().setMarginUnit(1);
        label.getAllStyles().setMargin(0, 0, 0, 2);
        countDevice = 0;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("whiteCnt");
        if (CihMBanking.sesPMTR.getIsDemo() != 0) {
            this.thisContainer.setLayout(new BoxLayout(2));
            Container container4 = new Container(new FlowLayout(4, 4));
            SpanLabel spanLabel4 = new SpanLabel("Ce service n'est pas disponible en mode démonstration.");
            spanLabel4.setUIID("dg_splblMsgCenter");
            spanLabel4.setScrollVisible(false);
            spanLabel4.setIconPosition("West");
            spanLabel4.setTextUIID("dg_lblSPError");
            spanLabel4.setIconUIID("g_cntEmpty");
            container4.add(spanLabel4);
            this.thisContainer.addComponent(container4);
            return this.thisContainer;
        }
        new Container(new LayeredLayout()).setUIID("whiteCnt");
        new ArrayList();
        container.add(container2);
        container.add(spanLabel);
        container.add(container3);
        Container container5 = new Container(new BoxLayout(2));
        ArrayList AppareilsListProcess = this.D.AppareilsListProcess();
        if (AppareilsListProcess.size() > 0) {
            Iterator it = AppareilsListProcess.iterator();
            while (it.hasNext()) {
                Device device = (Device) it.next();
                if (!device.deviceName.equals("WEB")) {
                    CihMBanking.sesPMTR.isAccordionIconDowntoBody = false;
                    Container drawDeviceItem = drawDeviceItem(device, this.listAcc, this.thisContainer, AppareilsListProcess, container5);
                    drawQRAccordionCnt(this.listAcc, drawDeviceItem);
                    container5.add(drawDeviceItem);
                }
            }
            container.add(container5);
        } else {
            container3.setLayout(new FlowLayout(4, 4));
            container3.add(spanLabel3);
        }
        this.thisContainer.add(container);
        this.thisContainer.revalidate();
        return this.thisContainer;
    }

    public Container drawDeviceItem(Device device, ArrayList arrayList, Container container, ArrayList arrayList2, Container container2) {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "DeviceItemCnt");
        device.cntManagementForm = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "DeviceManagCnt");
        Label label = (Label) this.uib.findByName("lblAccountOwner", createContainer);
        Label label2 = (Label) this.uib.findByName("lblAccountRibValue1", createContainer);
        Label label3 = (Label) this.uib.findByName("lblCardStatusValueV2", createContainer);
        Label label4 = (Label) this.uib.findByName("lblCardStatusValueV22", createContainer);
        Label label5 = (Label) this.uib.findByName("Status", createContainer);
        Container container3 = (Container) this.uib.findByName("cntStatut", createContainer);
        Container container4 = (Container) this.uib.findByName("cntLastCnx", createContainer);
        container3.setHidden(true);
        container4.setHidden(true);
        label.setText(device.deviceName);
        label2.setText(device.deviceModel);
        label3.setText(device.createDate);
        label4.setText("");
        label5.setText("Activé");
        label5.setUIID("g_lblDashBoardbalance");
        Image image = this.uiManager.ressource.getImage("ArrowTop.png");
        Image image2 = this.uiManager.ressource.getImage("ArrowClose (1).png");
        device.accr = new B3gAccordion();
        Button button = (Button) this.uib.findByName("btnActDsct", device.cntManagementForm);
        arrayList.add(device.accr);
        button.addActionListener(new 1(device));
        device.accr.setCloseIcon(image2);
        device.accr.setOpenIcon(image);
        device.accr.setLayoutChangGlb(true);
        device.accr.setScrollableY(false);
        device.accr.setScrollVisible(false);
        device.accr.setArrowUIID("Container");
        createContainer.setScrollableY(false);
        device.cntManagementForm.setScrollableY(false);
        device.accr.addContent(new Container(new FlowLayout(4, 4)), device.cntManagementForm);
        createContainer.putClientProperty("device", this);
        device.accr.addOnClickItemListener(new 2(container2, arrayList2, device, image2, image));
        createContainer.setUIID("whiteCnt");
        return createContainer;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Device val$item;

        1(Device device) {
            this.val$item = device;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ServiceResponse DeleteDeviceProcess = appareilsDetailPage.this.D.DeleteDeviceProcess(this.val$item.DeviceId);
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(appareilsDetailPage.this.uiManager.stateMachine, DeleteDeviceProcess.getStatusLabel(), null);
            if (DeleteDeviceProcess.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getDeviceList().clear();
                appareilsDetailPage.this.uiManager.NavigateToPageById(103);
            }
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$C;
        final /* synthetic */ Image val$closeIcon;
        final /* synthetic */ ArrayList val$deviceB3gServiceList;
        final /* synthetic */ Device val$item;
        final /* synthetic */ Image val$openIcon;

        2(Container container, ArrayList arrayList, Device device, Image image, Image image2) {
            this.val$C = container;
            this.val$deviceB3gServiceList = arrayList;
            this.val$item = device;
            this.val$closeIcon = image;
            this.val$openIcon = image2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            for (int i = 0; i < this.val$C.getComponentCount(); i++) {
                if (Settings.isNightMode()) {
                    this.val$C.getComponentAt(i).setUIID("darkCnt");
                } else {
                    this.val$C.getComponentAt(i).setUIID("whiteCnt");
                }
            }
            Iterator it = this.val$deviceB3gServiceList.iterator();
            while (it.hasNext()) {
                Device device = (Device) it.next();
                if (device.deviceName.equals("WEB") || device.DeviceId == null) {
                    break;
                }
                if (!device.DeviceId.equals(this.val$item.DeviceId)) {
                    B3gAccordion.AccordionContent accordionContent = (B3gAccordion.AccordionContent) device.accr.getComponentAt(0);
                    device.isOpen = false;
                    device.accr.setAutoClose(true);
                    accordionContent.setArrowIcon(this.val$closeIcon);
                    device.cntManagementForm.setHidden(true);
                    device.accr.setOpenIcon(this.val$closeIcon);
                } else {
                    B3gAccordion.AccordionContent accordionContent2 = (B3gAccordion.AccordionContent) this.val$item.accr.getComponentAt(0);
                    if (!this.val$item.isOpen) {
                        this.val$item.isOpen = true;
                        this.val$item.accr.setAutoClose(false);
                        this.val$item.cntManagementForm.setHidden(false);
                        accordionContent2.setArrowIcon(this.val$openIcon);
                        this.val$item.accr.setOpenIcon(this.val$openIcon);
                    } else {
                        this.val$item.isOpen = false;
                        this.val$item.accr.setAutoClose(true);
                        this.val$item.cntManagementForm.setHidden(true);
                        accordionContent2.setArrowIcon(this.val$closeIcon);
                        this.val$item.accr.setOpenIcon(this.val$closeIcon);
                    }
                }
            }
            this.val$C.revalidateWithAnimationSafety();
        }
    }

    private void drawQRAccordionCnt(ArrayList arrayList, Container container) {
        Container container2 = new Container();
        container2.setUIID("greyCnt");
        container.addComponent((Component) arrayList.get(countDevice));
        container.add(container2);
        countDevice++;
    }
}
