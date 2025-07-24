package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.services.Account;
import com.b3g.services.B3gService;
import com.b3g.services.Card;
import com.b3g.services.Shortcut;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.CIHStyles;
import com.b3g.ui.page.cih.Module.PopUpsManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.b3g.ui.page.cih.Module.ShortcutStore;
import com.codename1.io.Preferences;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import com.codename1.util.StringUtil;
import java.util.ArrayList;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Home extends B3GPage {
    static int incr;
    Boolean isDrawed;
    PopUpsManager pPopUpsManager;

    public Home(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        this.isDrawed = false;
        CihMBanking.exitApplication = true;
        this.pPopUpsManager = new PopUpsManager(this.uiManager);
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        CihMBanking.sesPMTR.setMontantReducToPass(0.0f);
        CihMBanking.sesPMTR.setToken("");
        try {
            if (this.isDrawed.booleanValue()) {
                return this.thisContainer;
            }
            Container container = this.thisContainer;
            new Container();
            container.setLayout(new BorderLayout());
            Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "ProfileItemV2");
            this.uiManager.stateMachine.findLblProfileV2Name(createContainer).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom);
            this.uiManager.stateMachine.findLblProfilePhoneNumber(createContainer).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm);
            this.uiManager.stateMachine.findLblEmail(createContainer).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email);
            this.uiManager.stateMachine.findBtnProfileV2(createContainer).addActionListener(new 1());
            Account account = new Account();
            account.uiManager = this.uiManager;
            ArrayList AccountProcessNew = account.AccountProcessNew(11);
            Container container2 = new Container(BoxLayout.y());
            if (AccountProcessNew.size() > 1) {
                container2.addComponent(this.uiManager.DrawListContainerForAccountsHome("GloabalContainerV2", "", Boolean.TRUE, AccountProcessNew, 1, 97, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, null, null, null));
            } else {
                container2.addComponent(this.uiManager.DrawListContainerForAccountsHome("GloabalContainerV2", "", Boolean.TRUE, AccountProcessNew, 2, 97, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList_StatusLabel(), null, null, null, null));
            }
            Container container3 = new Container();
            SetDashbordView(container3);
            Container container4 = new Container(new BoxLayout(2));
            Container createContainer2 = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "HomeLastBloc");
            UIBuilder uIBuilder = new UIBuilder();
            Container container5 = (Container) uIBuilder.findByName("CntAvis", createContainer2);
            Container container6 = (Container) uIBuilder.findByName("CntSimulator", createContainer2);
            Button button = new Button();
            button.setText("découvrez nos produits bancaires");
            Container buttonNewFeature = CIHStyles.setButtonNewFeature(button, CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("new_feature.png"));
            button.addActionListener(new 2());
            if (getUrl() != 2) {
                createContainer2.removeAll();
                createContainer2.add(container6);
                createContainer2.add(container5);
            }
            container4.addComponent(createContainer2);
            container4.setUIID("CntHomeV2Margin");
            Container encloseY = BoxLayout.encloseY(new Label("Bonjour " + CihMBanking.sesPMTR.civilite + " " + CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom + " !", "gb_lblGlobalHeaderTitleV2"), container2, container3);
            if (!CihMBanking.sesPMTR.isToCut()) {
                encloseY.add(buttonNewFeature);
            }
            container.add("Center", encloseY);
            container.add("South", container4);
            encloseY.setScrollableY(false);
            encloseY.setScrollVisible(false);
            Display.getInstance().scheduleBackgroundTask(new Home$$ExternalSyntheticLambda0(this));
            this.uiManager.stateMachine.findBtnSimulate1(createContainer2).addActionListener(new 8());
            this.uiManager.stateMachine.findBtnAvis1(createContainer2).addActionListener(new 9());
            encloseY.forceRevalidate();
            this.isDrawed = true;
            Settings.setNightMode(container, 0);
            return container;
        } catch (Exception unused) {
            this.thisContainer = new Container(new BoxLayout(2));
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            return new Container(new BoxLayout(2));
        }
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Home.this.uiManager.NavigateToPageById(62);
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Home.this.uiManager.NavigateToPageById(164);
        }
    }

    /* synthetic */ void lambda$GetPageContainer$0$com-b3g-ui-page-Home() {
        String str = Preferences.get("application_version", "0.0");
        Integer valueOf = Integer.valueOf(Integer.parseInt(Preferences.get("ReadNbSecurityMsg14", "0")));
        Integer valueOf2 = Integer.valueOf(Integer.parseInt(Preferences.get("confirmEmailCounter", "0")));
        Integer valueOf3 = Integer.valueOf(Integer.parseInt(Preferences.get("confirmReadNoteApplPAy", "0")));
        Integer valueOf4 = Integer.valueOf(Integer.parseInt(Preferences.get("gamerPopupCounter", "0")));
        if (!str.equals("4.61")) {
            new String[]{"Possibilité d’ajout de votre adresse email", "Déblocage du PIN en cas de saisie incorrecte répétitive"};
            Preferences.set("application_version", "4.61");
        }
        Display.getInstance().callSerially(new 3(valueOf));
        Display.getInstance().callSerially(new 4());
        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated && !SessionParameter.PinPoPupSeen) {
            this.uiManager.stateMachine.ShowPinPopUp(this.uiManager.stateMachine);
            SessionParameter.PinPoPupSeen = true;
        }
        if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().HasToConfirmEmail && valueOf2.intValue() < 3) {
            Display.getInstance().callSerially(new 5());
        }
        try {
            if (Display.getInstance().getPlatformName().equals("ios")) {
                Display.getInstance().callSerially(new 6(valueOf3));
            }
        } catch (Exception unused) {
        }
        try {
            Display.getInstance().callSerially(new 7(valueOf4));
        } catch (Exception unused2) {
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ Integer val$readNbSecurityMsg;

        3(Integer num) {
            this.val$readNbSecurityMsg = num;
        }

        public void run() {
            new UITimer(new Home$3$$ExternalSyntheticLambda0(this, this.val$readNbSecurityMsg)).schedule(1500, false, Home.this.thisContainer.getComponentForm());
        }

        /* synthetic */ void lambda$run$0$com-b3g-ui-page-Home$3(Integer num) {
            if (num.intValue() >= 2 || !CihMBanking.sesPMTR.isSerurityPopUpReaded) {
                return;
            }
            Home.this.pPopUpsManager.showBlokedSecurityPopUp(Home.this.uiManager.stateMachine, new String[]{"للحفاظ على أمان حسابك وأموالك، يرجى السلوكات التالية دائما : ", "1 - قم بحماية كلمات المرور الخاصة بك والرموز التي تتلقاها عن طريق الرسائل النصية بالحفاظ على سريتها. لا تشاركها مع أي شخص، حتى مع البنك.", "2 - تحقق من موثوقية المواقع الإلكترونية التي تقوم بإدخال تفاصيل بطاقتك البنكية عليها (الرقم، رمز التحقق، وتاريخ انتهاء الصلاحية).", "3 - احذر من العروض المغرية (مسابقة، عروض التوظيف،...) التي تطلب منك لاحقًا تقديم بياناتك أو إرسال الأموال.", "4 - تذكر أن البنك لن يطلب منك أبدًا معلوماتك البنكية أو كلمات المرور الخاصة بك ولن يكون مسؤولاً عن أي استخدام احتيالي لحسابك إذا قدمت معلوماتك إلى طرف ثالث.", "Pour garder votre compte et vos fonds en sécurité, veuillez respecter les gestes suivants :", "1 - Protégez vos mots de passe et les codes que vous recevez par SMS en les gardant secrets, ne les partagez avec personne même la banque.", "2 - Assurez-vous de la fiabilité des sites internet sur lesquels vous renseignez les informations de vos cartes bancaires (le n°, le CVV, la date d’expiration).", "3 - Faites attention aux offres alléchantes (tombola, offre de recrutement,…) qui vous demandent par la suite de renseigner vos informations ou d’envoyer de l’argent.", "4 - Rappelez-vous que la banque ne demandera jamais vos données bancaires ni vos mots de passe et elle n’est pas responsable d’usage frauduleux de votre compte si vous fournissez vos données à une personne tierce."});
        }
    }

    class 4 implements Runnable {
        4() {
        }

        public void run() {
            new UITimer(new Home$4$$ExternalSyntheticLambda0(this)).schedule(1800, false, Home.this.thisContainer.getComponentForm());
        }

        /* synthetic */ void lambda$run$0$com-b3g-ui-page-Home$4() {
            String str = CihMBanking.sesPMTR.getSessionClient().getClient_Profil().pushFlag;
            str.hashCode();
            switch (str) {
                case "0":
                case "3":
                    Home.this.pPopUpsManager.showblockedPushPopUp(Home.this.uiManager.stateMachine, true);
                    break;
                case "4":
                    Home.this.pPopUpsManager.showblockedPushPopUp(Home.this.uiManager.stateMachine, false);
                    break;
            }
        }
    }

    class 5 implements Runnable {
        5() {
        }

        public void run() {
            new UITimer(new Home$5$$ExternalSyntheticLambda0(this)).schedule(2000, false, Home.this.thisContainer.getComponentForm());
        }

        /* synthetic */ void lambda$run$0$com-b3g-ui-page-Home$5() {
            Home.this.pPopUpsManager.showBlokedConfirmEmailPopUp(Home.this.uiManager.stateMachine);
            Preferences.set("confirmEmailCounter", Integer.valueOf(Integer.parseInt(Preferences.get("confirmEmailCounter", "0")) + 1).toString());
        }
    }

    class 6 implements Runnable {
        final /* synthetic */ Integer val$confirmReadNoteApplPAy;

        6(Integer num) {
            this.val$confirmReadNoteApplPAy = num;
        }

        public void run() {
            new UITimer(new Home$6$$ExternalSyntheticLambda0(this, this.val$confirmReadNoteApplPAy)).schedule(2400, false, Home.this.thisContainer.getComponentForm());
        }

        /* synthetic */ void lambda$run$0$com-b3g-ui-page-Home$6(Integer num) {
            new Card().CheckAutorisationToken(Display.getInstance().getPlatformName().toString(), true);
            if (!CihMBanking.sesPMTR.FlagCihPay || num.intValue() >= 1) {
                return;
            }
            Home.this.pPopUpsManager.showApplePayPopUpCont(Home.this.uiManager.stateMachine);
        }
    }

    class 7 implements Runnable {
        final /* synthetic */ Integer val$gamerPopupCounter;

        7(Integer num) {
            this.val$gamerPopupCounter = num;
        }

        public void run() {
            new UITimer(new Home$7$$ExternalSyntheticLambda0(this, this.val$gamerPopupCounter)).schedule(2600, false, Home.this.thisContainer.getComponentForm());
        }

        /* synthetic */ void lambda$run$0$com-b3g-ui-page-Home$7(Integer num) {
            if (num.intValue() >= 2 || !CihMBanking.sesPMTR.gamerPopupIsShowed()) {
                return;
            }
            Home.this.pPopUpsManager.showGamerOffrePopUp();
        }
    }

    class 8 implements ActionListener {
        8() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Home.this.uiManager.NavigateToPageById(18);
        }
    }

    class 9 implements ActionListener {
        9() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Home.this.uiManager.NavigateToPageById(17);
        }
    }

    public boolean isPremier(int i) {
        if (i < 0) {
            return false;
        }
        if (i == 2) {
            return false;
        }
        if (i != 0 && i != 1) {
            for (int i2 = 2; i2 <= i / 2; i2++) {
                if (i != i2 && i % i2 == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public String replace(String str) {
        return StringUtil.replaceAll(StringUtil.replaceAll(StringUtil.replaceAll(StringUtil.replaceAll(StringUtil.replaceAll(StringUtil.replaceAll(StringUtil.replaceAll(str, "/", "_"), "\\", "_"), "%", "_"), "?", "_"), "*", "_"), ":", "_"), "=", "_");
    }

    private ArrayList PurgeSavingAccountList(ArrayList arrayList) {
        new ArrayList();
        boolean z = true;
        for (int i = 0; i < arrayList.size() && z; i++) {
            if (((Account) arrayList.get(i)).rib.substring(13, 17).equals("2310")) {
                arrayList.remove(i);
                z = false;
            }
        }
        return arrayList;
    }

    Container GetContainer(String str, String str2) {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, str);
        if (str.equals("GloabalContainerV2")) {
            this.uiManager.stateMachine.findLblGlobalHeaderTitle(createContainer).setText(str2);
        } else if (str.equals("GloabalContainerV3")) {
            this.uiManager.stateMachine.findLblGlobalHeaderTitle(createContainer).setText(str2);
        }
        return createContainer;
    }

    private void SetDashbordView(Container container) {
        container.setLayout(new BorderLayout());
        Container container2 = new Container();
        try {
            ShortcutStore.getInstance();
            Integer[] preferedShortcuts = ShortcutStore.getPreferedShortcuts();
            Shortcut shortcut = new Shortcut();
            Container drawNavigationItem = shortcut.drawNavigationItem(this.uiManager, preferedShortcuts[0]);
            Container drawNavigationItem2 = shortcut.drawNavigationItem(this.uiManager, preferedShortcuts[1]);
            Container drawNavigationItem3 = shortcut.drawNavigationItem(this.uiManager, preferedShortcuts[2]);
            Container drawNavigationItem4 = shortcut.drawNavigationItem(this.uiManager, preferedShortcuts[3]);
            Container drawNavigationItem5 = shortcut.drawNavigationItem(this.uiManager, preferedShortcuts[4]);
            Container drawNavigationItem6 = shortcut.drawNavigationItem(this.uiManager, preferedShortcuts[5]);
            container2.setLayout(new GridLayout(3, 2));
            container2.add(drawNavigationItem);
            container2.add(drawNavigationItem2);
            container2.add(drawNavigationItem3);
            container2.add(drawNavigationItem4);
            container2.add(drawNavigationItem5);
            container2.add(drawNavigationItem6);
            drawNavigationItem.getAllStyles().setMarginUnit(1);
            drawNavigationItem.getAllStyles().setMargin(0, 0, 0, 1);
            drawNavigationItem2.getAllStyles().setMarginUnit(1);
            drawNavigationItem2.getAllStyles().setMargin(0, 0, 1, 0);
            drawNavigationItem3.getAllStyles().setMarginUnit(1);
            drawNavigationItem3.getAllStyles().setMargin(0.5f, 0.0f, 0.0f, 1.0f);
            drawNavigationItem4.getAllStyles().setMarginUnit(1);
            drawNavigationItem4.getAllStyles().setMargin(0.5f, 0.0f, 1.0f, 0.0f);
            drawNavigationItem5.getAllStyles().setMarginUnit(1);
            drawNavigationItem5.getAllStyles().setMargin(0.5f, 0.0f, 0.0f, 1.0f);
            drawNavigationItem6.getAllStyles().setMarginUnit(1);
            drawNavigationItem6.getAllStyles().setMargin(0.5f, 0.0f, 1.0f, 0.0f);
            container.getAllStyles().setPaddingBottom(1);
            container.add((Object) 4, (Component) container2);
            Settings.setNightMode(container, 0);
        } catch (Exception unused) {
        }
    }

    public int getUrl() {
        if (Storage.getInstance().exists("RandomConx")) {
            return ((Integer) ((Vector) Storage.getInstance().readObject("RandomConx")).get(0)).intValue();
        }
        return 2;
    }
}
