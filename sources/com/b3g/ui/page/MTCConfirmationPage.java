package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.MTCConfirmationResume;
import com.b3g.services.MTCImpaye;
import com.b3g.services.ServiceManager;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GCIHEcode;
import com.b3g.ui.B3GUiManager;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MTCConfirmationPage extends B3GPage {
    Container MTCConfirmationCnt;
    TextArea TextF1;
    TextArea TextF2;
    TextArea TextF3;
    TextArea TextF4;
    private String VnewaliasText;
    TextField txtHBPassWord;
    UIBuilder uib = new UIBuilder();

    static /* synthetic */ String access$000(MTCConfirmationPage mTCConfirmationPage) {
        return mTCConfirmationPage.VnewaliasText;
    }

    static /* synthetic */ String access$002(MTCConfirmationPage mTCConfirmationPage, String str) {
        mTCConfirmationPage.VnewaliasText = str;
        return str;
    }

    public MTCConfirmationPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.MTCConfirmationCnt = DrawMTCConfirmation(((MTCImpaye) this.service).PrixTTC);
        this.thisContainer.addComponent(this.MTCConfirmationCnt);
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 300));
        container.revalidate();
    }

    public Container GetTransferSecurityForm(Container container, MTCConfirmationResume mTCConfirmationResume) {
        int i;
        Label label;
        Container container2 = new Container(BoxLayout.y());
        Container container3 = new Container(BoxLayout.y());
        Label label2 = new Label("Service sélectionné : ");
        label2.setUIID("g_lblDashBoardTitleOrange");
        try {
            i = CihMBanking.sesPMTR.getBillerType();
        } catch (Exception unused) {
            i = 0;
        }
        if (i == 1) {
            label = new Label("Recharges");
        } else {
            label = new Label("Paiements de Factures");
        }
        label.setUIID("ad_lblValueBlue");
        Container container4 = new Container(BoxLayout.x());
        container4.add(label2);
        container4.add(label);
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
        this.uiManager.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 1(container));
        this.uiManager.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 2(b3GCIHEcode, mTCConfirmationResume));
        container2.add(createContainer);
        Settings.setNightMode(container2, 0);
        return container2;
    }

    class 1 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        1(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 300));
            this.val$Parent.revalidate();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;
        final /* synthetic */ MTCConfirmationResume val$mtcConfirmationResume;

        2(B3GCIHEcode b3GCIHEcode, MTCConfirmationResume mTCConfirmationResume) {
            this.val$eCode1 = b3GCIHEcode;
            this.val$mtcConfirmationResume = mTCConfirmationResume;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            MTCConfirmationPage.access$002(MTCConfirmationPage.this, this.val$eCode1.getValue());
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogMTCService(14, this.val$mtcConfirmationResume, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 900033, MTCConfirmationPage.access$000(MTCConfirmationPage.this));
        }
    }

    public Container DrawMTCConfirmation(String str) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("Container");
        container.setFocusable(false);
        container.setScrollVisible(false);
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("Container");
        container2.setFocusable(false);
        container2.setScrollVisible(false);
        Container DrawListContainer = this.uiManager.DrawListContainer("GloabalContainerV2", "Compte à débiter", Boolean.TRUE, getAccountToDebit(CihMBanking.sesPMTR.getSessionClient().getClient_AccountList()), 1, 16, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, null, null, null);
        container2.addComponent(DrawListContainer);
        TableLayout tableLayout = new TableLayout(1, 3);
        Container container3 = new Container();
        container3.setLayout(tableLayout);
        container3.setUIID("Container");
        container3.setFocusable(false);
        container3.setScrollable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(50);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(3);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container4 = new Container();
        container4.setLayout(flowLayout);
        container4.setUIID("Container");
        Label label = new Label("Montant Total : ");
        label.setUIID("g_lblNotifM");
        label.setFocusable(false);
        label.setScrollVisible(false);
        label.setTextPosition(1);
        label.setVerticalAlignment(4);
        container4.addComponent(label);
        container3.addComponent(createConstraint, container4);
        Object createConstraint2 = tableLayout.createConstraint();
        FlowLayout flowLayout2 = new FlowLayout();
        flowLayout2.setAlign(1);
        flowLayout2.setValign(4);
        flowLayout2.setValignByRow(true);
        Container container5 = new Container();
        container5.setLayout(flowLayout2);
        container5.setUIID("Container");
        Label label2 = new Label(str);
        label2.setUIID("g_lblNotifGrenM");
        label2.setFocusable(false);
        label2.setScrollVisible(false);
        label2.setTextPosition(1);
        label2.setVerticalAlignment(4);
        container5.addComponent(label2);
        container3.addComponent(createConstraint2, container5);
        container3.revalidate();
        Object createConstraint3 = tableLayout.createConstraint();
        FlowLayout flowLayout3 = new FlowLayout();
        flowLayout3.setAlign(1);
        flowLayout3.setValign(4);
        flowLayout3.setValignByRow(true);
        Container container6 = new Container();
        container6.setLayout(flowLayout3);
        container6.setUIID("Container");
        Label label3 = new Label(" (DH)");
        label3.setUIID("g_lblNotifM");
        label3.setFocusable(false);
        label3.setScrollVisible(false);
        label3.setTextPosition(1);
        label3.setVerticalAlignment(4);
        container6.addComponent(label3);
        container3.addComponent(createConstraint3, container6);
        container3.revalidate();
        container.addComponent(container2);
        container.addComponent(container3);
        container.addComponent(DrawBorderSep());
        container.addComponent(DrawMTCConfirmationBtn(DrawListContainer, str));
        Settings.setNightMode(container, 0);
        return container;
    }

    public Container DrawBorderSep() {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("g_cntBorderV2");
        container.setFocusable(false);
        container.setScrollable(false);
        return container;
    }

    public Container DrawMTCConfirmationBtn(Container container, String str) {
        GridLayout gridLayout = new GridLayout(1, 2);
        Container container2 = new Container();
        container2.setLayout(gridLayout);
        container2.setFocusable(false);
        container2.setScrollVisible(false);
        Button button = new Button("PRECEDENT");
        button.setUIID("op_BtnOppositionValidationMarginRight");
        button.setFocusable(false);
        button.setScrollVisible(false);
        button.setVerticalAlignment(4);
        button.setTextPosition(1);
        button.addActionListener(new 3());
        container2.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("op_BtnOppositionValidationMarginLeft");
        button2.setFocusable(false);
        button2.setScrollVisible(false);
        button2.setVerticalAlignment(4);
        button2.setTextPosition(1);
        button2.addActionListener(new 4(str, container));
        container2.addComponent(button2);
        return container2;
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (CihMBanking.sesPMTR.getIsFavoriteBack() == 0) {
                MTCConfirmationPage.this.uiManager.NavigateToPageById(22);
            } else {
                MTCConfirmationPage.this.uiManager.NavigateToPageById(29);
            }
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Container val$AccountListContainer;
        final /* synthetic */ String val$TotalAmount;

        4(String str, Container container) {
            this.val$TotalAmount = str;
            this.val$AccountListContainer = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            int i;
            String str;
            MTCConfirmationResume GetSelectedAccount = MTCConfirmationPage.this.GetSelectedAccount(this.val$AccountListContainer, DataTools.PurgeBlankFromStringAmount(this.val$TotalAmount).toString());
            try {
                i = CihMBanking.sesPMTR.getBillerType();
            } catch (Exception unused) {
                i = 0;
            }
            int i2 = 1;
            if (i == 1) {
                String[] split = DataTools.split(GetSelectedAccount.FirstFormLabel, ";");
                String encryptedPhone = DataTools.getEncryptedPhone(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm);
                String substring = encryptedPhone.substring(0, 3);
                String substring2 = encryptedPhone.substring(encryptedPhone.length() - 3, encryptedPhone.length());
                if (split[1].startsWith(substring) && split[1].endsWith(substring2)) {
                    i2 = 0;
                }
                str = "1007";
            } else {
                str = "1008";
            }
            ServiceResponse sendOtpProcessForMtcConfirmation = ServiceManager.sendOtpProcessForMtcConfirmation(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, str, 300016, "1", "0", GetSelectedAccount.Rib, "", GetSelectedAccount.TotalAmount, "", i2);
            if (sendOtpProcessForMtcConfirmation.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.setSessionSavedContainer(MTCConfirmationPage.this.MTCConfirmationCnt);
                MTCConfirmationPage mTCConfirmationPage = MTCConfirmationPage.this;
                Container container = mTCConfirmationPage.thisContainer;
                Container container2 = MTCConfirmationPage.this.MTCConfirmationCnt;
                MTCConfirmationPage mTCConfirmationPage2 = MTCConfirmationPage.this;
                mTCConfirmationPage.SwitchTransfertForms(container, container2, mTCConfirmationPage2.GetTransferSecurityForm(mTCConfirmationPage2.thisContainer, GetSelectedAccount), false);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MTCConfirmationPage.this.uiManager.stateMachine, DataTools.FormatUnicode(sendOtpProcessForMtcConfirmation.getStatusLabel()), null);
        }
    }

    public MTCConfirmationResume GetSelectedAccount(Container container, String str) {
        MTCConfirmationResume mTCConfirmationResume = new MTCConfirmationResume();
        Container container2 = (Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0);
        mTCConfirmationResume.Owner = ((Label) ((Container) ((Container) ((Container) container2.getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getText();
        Account account = (Account) ((B3gService) container2.getClientProperty("AccountClient"));
        mTCConfirmationResume.Rib = account.rib;
        mTCConfirmationResume.AccountNumber = account.accountNumber;
        mTCConfirmationResume.TotalAmount = str;
        mTCConfirmationResume.ReferenceNumber = ((MTCImpaye) CihMBanking.sesPMTR.getCurrentMTCImpaye().get(0)).ReferenceNumber;
        mTCConfirmationResume.FirstFormLabel = ((MTCImpaye) CihMBanking.sesPMTR.getCurrentMTCImpaye().get(0)).FormInputLabel;
        mTCConfirmationResume.IsSaving = CihMBanking.sesPMTR.getIsFavorite();
        return mTCConfirmationResume;
    }

    ArrayList getAccountToDebit(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Account account = (Account) it.next();
            if (account.canDebit.equals("True") && account.eligibleService.contains("300016")) {
                arrayList2.add(account);
            }
        }
        return arrayList2;
    }
}
