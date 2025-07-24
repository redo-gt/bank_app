package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.PopUpsManager;
import com.codename1.io.Storage;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.FocusListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ActivationPage extends B3GPage {
    private String gClientId;
    private boolean isFirstStep;
    private Container parentCnt;

    static /* synthetic */ String access$000(ActivationPage activationPage, String str) {
        return activationPage.phoneNumberFormatted(str);
    }

    static /* synthetic */ String access$100(ActivationPage activationPage) {
        return activationPage.gClientId;
    }

    static /* synthetic */ String access$102(ActivationPage activationPage, String str) {
        activationPage.gClientId = str;
        return str;
    }

    public ActivationPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.PageId = i;
        this.parentCnt = new Container(new BoxLayout(2));
        this.gClientId = "0000000";
        this.isFirstStep = true;
    }

    public ActivationPage(String str, B3GUiManager b3GUiManager) {
        this.parentCnt = new Container(new BoxLayout(2));
        this.gClientId = str;
        this.isFirstStep = false;
        this.uiManager = b3GUiManager;
    }

    public ActivationPage(Object obj) {
        Container container = new Container(new BoxLayout(2));
        this.parentCnt = container;
        container.setUIID("Container");
        this.gClientId = "0000000";
        this.isFirstStep = true;
        this.uiManager = (B3GUiManager) obj;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        if (this.isFirstStep) {
            this.parentCnt.addComponent(GetClientIdPhoneForm());
        } else {
            this.parentCnt.addComponent(getOtpForm());
        }
        return this.parentCnt;
    }

    public Form GetClientIdPhoneForm() {
        Form form = (Form) this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "ActivationForm1");
        this.uiManager.stateMachine.findBtnTransferBack(form).addActionListener(new 1());
        this.uiManager.stateMachine.findBtnTransferValidation(form).addActionListener(new 2(form));
        form.setBackCommand(new 3("Back"));
        return form;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            new ActivationMenu(ActivationPage.this.uiManager).GetPageForm().show();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Form val$cntClientIdPhoneForm;

        2(Form form) {
            this.val$cntClientIdPhoneForm = form;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String text = ActivationPage.this.uiManager.stateMachine.findTxtAmount(this.val$cntClientIdPhoneForm).getText();
            String text2 = ActivationPage.this.uiManager.stateMachine.findTextPhone(this.val$cntClientIdPhoneForm).getText();
            if ((text.length() == 16 || text.length() == 7) && text2.length() >= 10) {
                if (DataTools.checkConnection()) {
                    ServiceResponse sendClientIdPhoneProcess = ActivationPage.this.sendClientIdPhoneProcess(text.substring(0, 7), ActivationPage.access$000(ActivationPage.this, text2));
                    if (sendClientIdPhoneProcess.getStatusCode().equals("000")) {
                        ActivationPage.access$102(ActivationPage.this, text);
                        Vector vector = new Vector();
                        vector.addElement(ActivationPage.access$100(ActivationPage.this));
                        Storage.getInstance().writeObject("clientId", vector);
                        ActivationPage.this.getOtpForm().show();
                        return;
                    }
                    ActivationPage.this.uiManager.ShowMessageTransaction(ActivationPage.this.uiManager.stateMachine, DataTools.FormatUnicode(sendClientIdPhoneProcess.getStatusLabel()), null);
                    return;
                }
                new PopUpsManager().showNoConnectoinMessg();
                return;
            }
            ActivationPage.this.uiManager.ShowMessageTransaction(ActivationPage.this.uiManager.stateMachine, DataTools.FormatUnicode("Vos informations sont incorrectes"), null);
        }
    }

    class 3 extends Command {
        3(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            new ActivationMenu(ActivationPage.this.uiManager).GetPageForm().show();
        }
    }

    public Form getOtpForm() {
        Form form = (Form) this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "ActivationForm2");
        this.uiManager.stateMachine.findBtnTransferBack(form).addActionListener(new 4(form));
        this.uiManager.stateMachine.findBtnTransferValidation(form).addActionListener(new 5(form));
        return form;
    }

    class 4 implements ActionListener {
        final /* synthetic */ Form val$cntOtpForm;

        4(Form form) {
            this.val$cntOtpForm = form;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Storage.getInstance().deleteStorageFile("clientId");
            new CihMBanking().showLoginFormDirectly(this.val$cntOtpForm);
        }
    }

    class 5 implements ActionListener {
        final /* synthetic */ Form val$cntOtpForm;

        5(Form form) {
            this.val$cntOtpForm = form;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (ActivationPage.this.uiManager.stateMachine.findTxtAmount().getText().length() != 0) {
                ActivationPage activationPage = ActivationPage.this;
                ServiceResponse sheckOtp = activationPage.sheckOtp(activationPage.uiManager.stateMachine.findTxtAmount(this.val$cntOtpForm).getText(), ActivationPage.access$100(ActivationPage.this));
                if (sheckOtp.getStatusCode().equals("000")) {
                    ActivationPage.this.getNewDataForm().show();
                    return;
                } else {
                    ActivationPage.this.uiManager.ShowMessageTransaction(ActivationPage.this.uiManager.stateMachine, DataTools.FormatUnicode(sheckOtp.getStatusLabel()), null);
                    return;
                }
            }
            ActivationPage.this.uiManager.ShowMessageTransaction(ActivationPage.this.uiManager.stateMachine, DataTools.FormatUnicode("Veuillez saisir le code reçu par SMS"), null);
        }
    }

    public Form getNewDataForm() {
        Form form = (Form) this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "ActivationForm3");
        this.uiManager.stateMachine.findCntPassTransparent(form).addFocusListener(new 6(form));
        this.uiManager.stateMachine.findCntPassConfirmTransparent(form).addFocusListener(new 7(form));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Lieu de naissance de ma mère", "32");
        linkedHashMap.put("Meilleur ami d’enfance", "33");
        linkedHashMap.put("Professeur préféré", "34");
        linkedHashMap.put("Personnage Historique préféré", "35");
        linkedHashMap.put("Sport préféré", "36");
        linkedHashMap.put("Livre préféré", "37");
        ((DefaultListModel) this.uiManager.stateMachine.findCbxQuestion(form).getModel()).removeAll();
        for (int i = 0; i < linkedHashMap.size(); i++) {
            this.uiManager.stateMachine.findCbxQuestion(form).addItem(linkedHashMap.keySet().toArray()[i]);
        }
        this.uiManager.stateMachine.findCbxQuestion(form).addFocusListener(new 8(form));
        this.uiManager.stateMachine.findTxtResponse(form).addFocusListener(new 9(form));
        this.uiManager.stateMachine.findBtnTransferBack(form).addActionListener(new 10(form));
        this.uiManager.stateMachine.findBtnTransferValidation(form).addActionListener(new 11(form, linkedHashMap));
        form.setBackCommand(new 12("Back"));
        return form;
    }

    class 6 implements FocusListener {
        final /* synthetic */ Form val$cntActivationForm3;

        public void focusLost(Component component) {
        }

        6(Form form) {
            this.val$cntActivationForm3 = form;
        }

        public void focusGained(Component component) {
            ActivationPage.this.uiManager.stateMachine.findCntVirtualKeyBoardConfirm(this.val$cntActivationForm3).removeAll();
            ActivationPage.this.uiManager.DrawKeyBoard2H(ActivationPage.this.uiManager.stateMachine.findCntVirtualKeyBoardPass(this.val$cntActivationForm3), ActivationPage.this.uiManager.stateMachine.findTxtPass(this.val$cntActivationForm3), "AMOUNT");
            ActivationPage.this.uiManager.stateMachine.findCntVirtualKeyBoardPass(this.val$cntActivationForm3).revalidate();
        }
    }

    class 7 implements FocusListener {
        final /* synthetic */ Form val$cntActivationForm3;

        public void focusLost(Component component) {
        }

        7(Form form) {
            this.val$cntActivationForm3 = form;
        }

        public void focusGained(Component component) {
            ActivationPage.this.uiManager.stateMachine.findCntVirtualKeyBoardPass(this.val$cntActivationForm3).removeAll();
            ActivationPage.this.uiManager.DrawKeyBoard2H(ActivationPage.this.uiManager.stateMachine.findCntVirtualKeyBoardConfirm(this.val$cntActivationForm3), ActivationPage.this.uiManager.stateMachine.findTxtConfirmPass(this.val$cntActivationForm3), "AMOUNT");
            ActivationPage.this.uiManager.stateMachine.findCntVirtualKeyBoardConfirm(this.val$cntActivationForm3).revalidate();
        }
    }

    class 8 implements FocusListener {
        final /* synthetic */ Form val$cntActivationForm3;

        public void focusLost(Component component) {
        }

        8(Form form) {
            this.val$cntActivationForm3 = form;
        }

        public void focusGained(Component component) {
            ActivationPage.this.uiManager.stateMachine.findCntVirtualKeyBoardPass(this.val$cntActivationForm3).removeAll();
            ActivationPage.this.uiManager.stateMachine.findCntVirtualKeyBoardConfirm(this.val$cntActivationForm3).removeAll();
        }
    }

    class 9 implements FocusListener {
        final /* synthetic */ Form val$cntActivationForm3;

        public void focusLost(Component component) {
        }

        9(Form form) {
            this.val$cntActivationForm3 = form;
        }

        public void focusGained(Component component) {
            ActivationPage.this.uiManager.stateMachine.findCntVirtualKeyBoardPass(this.val$cntActivationForm3).removeAll();
            ActivationPage.this.uiManager.stateMachine.findCntVirtualKeyBoardConfirm(this.val$cntActivationForm3).removeAll();
        }
    }

    class 10 implements ActionListener {
        final /* synthetic */ Form val$cntActivationForm3;

        10(Form form) {
            this.val$cntActivationForm3 = form;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Storage.getInstance().deleteStorageFile("clientId");
            new CihMBanking().showLoginFormDirectly(this.val$cntActivationForm3);
        }
    }

    class 11 implements ActionListener {
        final /* synthetic */ Form val$cntActivationForm3;
        final /* synthetic */ Map val$questionMap;

        11(Form form, Map map) {
            this.val$cntActivationForm3 = form;
            this.val$questionMap = map;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!ActivationPage.this.uiManager.stateMachine.findTxtPass(this.val$cntActivationForm3).getText().equals("") && !ActivationPage.this.uiManager.stateMachine.findTxtConfirmPass(this.val$cntActivationForm3).getText().equals("") && !ActivationPage.this.uiManager.stateMachine.findTxtResponse(this.val$cntActivationForm3).getText().equals("")) {
                if (ActivationPage.this.uiManager.stateMachine.findTxtPass(this.val$cntActivationForm3).getText().length() < 17 && ActivationPage.this.uiManager.stateMachine.findTxtPass(this.val$cntActivationForm3).getText().length() > 5) {
                    if (ActivationPage.this.uiManager.stateMachine.findTxtPass(this.val$cntActivationForm3).getText().equals(ActivationPage.this.uiManager.stateMachine.findTxtConfirmPass(this.val$cntActivationForm3).getText())) {
                        ActivationPage activationPage = ActivationPage.this;
                        ServiceResponse sendNewData = activationPage.sendNewData(ActivationPage.access$100(activationPage), ActivationPage.this.uiManager.stateMachine.findTxtPass(this.val$cntActivationForm3).getText(), this.val$questionMap.get(ActivationPage.this.uiManager.stateMachine.findCbxQuestion(this.val$cntActivationForm3).getSelectedItem()).toString(), ActivationPage.this.uiManager.stateMachine.findTxtResponse(this.val$cntActivationForm3).getText());
                        if (sendNewData.getStatusCode().equals("000")) {
                            ActivationPage.this.uiManager.ShowMessageTransaction(ActivationPage.this.uiManager.stateMachine, DataTools.FormatUnicode(sendNewData.getStatusLabel()), null);
                            Storage.getInstance().deleteStorageFile("clientId");
                            new CihMBanking().showSplashScreen();
                            return;
                        } else {
                            ActivationPage.this.uiManager.ShowMessageTransaction(ActivationPage.this.uiManager.stateMachine, DataTools.FormatUnicode(sendNewData.getStatusLabel()), null);
                            new CihMBanking().showSplashScreen();
                            return;
                        }
                    }
                    ActivationPage.this.uiManager.ShowMessageTransaction(ActivationPage.this.uiManager.stateMachine, DataTools.FormatUnicode("Les mots de passe ne sont pas identiques"), null);
                    return;
                }
                ActivationPage.this.uiManager.ShowMessageTransaction(ActivationPage.this.uiManager.stateMachine, DataTools.FormatUnicode("Le mot de passe doit avoir une longueur minimale de 6 chiffres et une longueur maximale de 16 chiffres"), null);
                return;
            }
            ActivationPage.this.uiManager.ShowMessageTransaction(ActivationPage.this.uiManager.stateMachine, DataTools.FormatUnicode("Veuillez remplire tous les champs svp"), null);
        }
    }

    class 12 extends Command {
        12(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            new ActivationMenu(ActivationPage.this.uiManager).GetPageForm().show();
        }
    }

    public void SwitchActivationForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }

    public ServiceResponse sendClientIdPhoneProcess(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("PHONE_NUMBER", str2);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setSessionId(str);
        serviceRequest.setServiceId(900093);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse sheckOtp(String str, String str2) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str2);
        hashtable.put("CODE_OTP", str);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900102);
        serviceRequest.setSessionId(str2);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public ServiceResponse sendNewData(String str, String str2, String str3, String str4) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("PASSWORD", str2);
        hashtable.put("SECRET_QUESTION", str3);
        hashtable.put("SECRET_RESPONSE", str4);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(900094);
        serviceRequest.setSessionId(str);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    private String phoneNumberFormatted(String str) {
        return str.substring(0, 2).equals("06") ? "212" + str.substring(1) : str;
    }
}
