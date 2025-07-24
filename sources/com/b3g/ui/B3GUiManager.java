package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.b3g.cih.online.SessionParameter;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Account;
import com.b3g.services.AccountAutorisation;
import com.b3g.services.AccountOperation;
import com.b3g.services.AgenceDemandeHistorique;
import com.b3g.services.B3gService;
import com.b3g.services.Beneficiary;
import com.b3g.services.Card;
import com.b3g.services.CardOperation;
import com.b3g.services.CardOpperationStatus;
import com.b3g.services.CashOutOperatoion;
import com.b3g.services.CheckbookDemand;
import com.b3g.services.GetDotation;
import com.b3g.services.LCNBoockDemand;
import com.b3g.services.Loan;
import com.b3g.services.MTCConfirmationResume;
import com.b3g.services.MTCCreancier;
import com.b3g.services.MTCFavoriteBill;
import com.b3g.services.MTCHistoric;
import com.b3g.services.MTCImpaye;
import com.b3g.services.MonConseiller;
import com.b3g.services.MyCheckLcn;
import com.b3g.services.OrderCurrencyHistoricDataService;
import com.b3g.services.OrderMADHistoricDataService;
import com.b3g.services.OvpExecutionService;
import com.b3g.services.OvpService;
import com.b3g.services.PELAccount;
import com.b3g.services.RIB;
import com.b3g.services.RecurringDetail;
import com.b3g.services.ReleveDgi;
import com.b3g.services.ResponseDotation;
import com.b3g.services.SavingAccount;
import com.b3g.services.ServiceManager;
import com.b3g.services.StudentTransferHisoricDataService;
import com.b3g.services.TransactionResume;
import com.b3g.services.TransferHistoric;
import com.b3g.services.TransferOperation;
import com.b3g.services.Walet;
import com.b3g.tools.DataTools;
import com.b3g.tools.NativeCall;
import com.b3g.tools.StringTools;
import com.b3g.ui.page.AboutPage;
import com.b3g.ui.page.AccountsListPage;
import com.b3g.ui.page.AcountEtrangerPage;
import com.b3g.ui.page.AddBenFromCompte;
import com.b3g.ui.page.AddBenfPage;
import com.b3g.ui.page.AddBenfWallet;
import com.b3g.ui.page.AddOvp;
import com.b3g.ui.page.AgenceChangePage;
import com.b3g.ui.page.AgregationFeebackPage;
import com.b3g.ui.page.AgregationHistoriquePage;
import com.b3g.ui.page.AuthenticationPage;
import com.b3g.ui.page.Avis;
import com.b3g.ui.page.B3GPage;
import com.b3g.ui.page.BeneficiaryManagerPage;
import com.b3g.ui.page.BourseOnline;
import com.b3g.ui.page.BrowserOfflinePage;
import com.b3g.ui.page.BrowserPage;
import com.b3g.ui.page.CardADOppositionPage;
import com.b3g.ui.page.CardsDetailPageNew2;
import com.b3g.ui.page.CardsListPage;
import com.b3g.ui.page.CardsUpdatePlafond;
import com.b3g.ui.page.CashOutFormPage;
import com.b3g.ui.page.CashOutPage;
import com.b3g.ui.page.ChangePasswordPage;
import com.b3g.ui.page.CheckBookDemandPage;
import com.b3g.ui.page.CheckLcnDetailPage;
import com.b3g.ui.page.ComplaintPage;
import com.b3g.ui.page.CurrencyFundsOrderPage;
import com.b3g.ui.page.CustomShortcutsPage;
import com.b3g.ui.page.DGIPaymentFormPage;
import com.b3g.ui.page.DGIPaymentHistoric;
import com.b3g.ui.page.DGIPaymentMenuPage;
import com.b3g.ui.page.DechargeComptePage;
import com.b3g.ui.page.DotationCartePrepayeesPage;
import com.b3g.ui.page.DotationCosommationPage;
import com.b3g.ui.page.DotationDetailEchanges;
import com.b3g.ui.page.DotationEcomPage;
import com.b3g.ui.page.DotationRechargeCartePrepaid;
import com.b3g.ui.page.DotationServieEnDevisePage;
import com.b3g.ui.page.DotationTouristiquePage;
import com.b3g.ui.page.DotationUtilisationEtranger;
import com.b3g.ui.page.EpargneConditions;
import com.b3g.ui.page.EpargneDescription;
import com.b3g.ui.page.EpargneFinalize;
import com.b3g.ui.page.EpargneProgramPage;
import com.b3g.ui.page.EpargneProgramTransfertPage;
import com.b3g.ui.page.FatouratiPage;
import com.b3g.ui.page.FingerprintPage;
import com.b3g.ui.page.ForgotPasswdPage;
import com.b3g.ui.page.Home;
import com.b3g.ui.page.IdentificationGlb;
import com.b3g.ui.page.InsuranceAllProductsPage;
import com.b3g.ui.page.InsuranceCotisationDetailPage;
import com.b3g.ui.page.InsuranceDetailPage;
import com.b3g.ui.page.LoanListPage;
import com.b3g.ui.page.LocaliserAgencePage;
import com.b3g.ui.page.LocaliserGabPage;
import com.b3g.ui.page.Login_New_Page;
import com.b3g.ui.page.MADFundsOrderPage;
import com.b3g.ui.page.MDMBackOfficeDtailPage;
import com.b3g.ui.page.MDMRTransKysRejePage;
import com.b3g.ui.page.MTCConfirmationPage;
import com.b3g.ui.page.MTCCreancePage;
import com.b3g.ui.page.MTCCreancierPage;
import com.b3g.ui.page.MTCCreancierTopPage;
import com.b3g.ui.page.MTCFavoriteBillPage;
import com.b3g.ui.page.MTCFormPage;
import com.b3g.ui.page.MTCHistoricPage;
import com.b3g.ui.page.MTCImpayeNewPage;
import com.b3g.ui.page.MTCImpayeTopupPage;
import com.b3g.ui.page.MTCMenuPage;
import com.b3g.ui.page.MTCNewHistoricDetailPage;
import com.b3g.ui.page.MTCTopUpMenuPage;
import com.b3g.ui.page.MdmPage;
import com.b3g.ui.page.MdmStep2Page;
import com.b3g.ui.page.MessageNew;
import com.b3g.ui.page.MessagePage;
import com.b3g.ui.page.MessageReplyPage;
import com.b3g.ui.page.MessageSentPage;
import com.b3g.ui.page.Messagerie1;
import com.b3g.ui.page.MessagerieReponse;
import com.b3g.ui.page.MessagerieReponseForSend;
import com.b3g.ui.page.MonConseillerPage;
import com.b3g.ui.page.MtcImpayeGroup;
import com.b3g.ui.page.MyCheckPage;
import com.b3g.ui.page.MyEDocumentsPageNew;
import com.b3g.ui.page.MyLcnPage;
import com.b3g.ui.page.MyOvps;
import com.b3g.ui.page.NewMtcHistoricPage;
import com.b3g.ui.page.NotificationPage;
import com.b3g.ui.page.OffreCIMRPage;
import com.b3g.ui.page.OffreGamerPage;
import com.b3g.ui.page.OppositionCardNw;
import com.b3g.ui.page.OvpAction;
import com.b3g.ui.page.OvpBnnef;
import com.b3g.ui.page.OvpStatutPage;
import com.b3g.ui.page.OvpSuspendre;
import com.b3g.ui.page.ParameteresPage;
import com.b3g.ui.page.PinAddPage;
import com.b3g.ui.page.PinNotActivatedPage;
import com.b3g.ui.page.PinUpdatePage;
import com.b3g.ui.page.PlansRetraitePage;
import com.b3g.ui.page.PreLoginInssurancePage;
import com.b3g.ui.page.PreLoginPage;
import com.b3g.ui.page.PrepaidCardDebit;
import com.b3g.ui.page.ProduitBancairePage;
import com.b3g.ui.page.ProfilePage;
import com.b3g.ui.page.RecalculePinPageV1;
import com.b3g.ui.page.RecapTransfertFromEtrangerPage;
import com.b3g.ui.page.ReccuringDetailPage;
import com.b3g.ui.page.ReccuringsListPage;
import com.b3g.ui.page.RejoueOvpPage;
import com.b3g.ui.page.RelationDetailPage;
import com.b3g.ui.page.RelationalCirclePage;
import com.b3g.ui.page.RenouvelementWebViewPage;
import com.b3g.ui.page.RibPage;
import com.b3g.ui.page.SOSAssistance;
import com.b3g.ui.page.SavingAccountList;
import com.b3g.ui.page.Simulator;
import com.b3g.ui.page.SinistreContratPage;
import com.b3g.ui.page.StudentTransferPage;
import com.b3g.ui.page.SuiviDemande;
import com.b3g.ui.page.TarifOperationPage;
import com.b3g.ui.page.TermsConditionsPage;
import com.b3g.ui.page.TopUpNewPage;
import com.b3g.ui.page.TransferCovid;
import com.b3g.ui.page.TransferFromEtranger;
import com.b3g.ui.page.TransferNewPage;
import com.b3g.ui.page.TransfertKyc;
import com.b3g.ui.page.TransfertWallet;
import com.b3g.ui.page.TransfertWebView;
import com.b3g.ui.page.TransfertWebViewKyc;
import com.b3g.ui.page.VirementDetails;
import com.b3g.ui.page.WaletDetailPage;
import com.b3g.ui.page.WaletListPage;
import com.b3g.ui.page.WebViewNVPage;
import com.b3g.ui.page.WebViewPage;
import com.b3g.ui.page.WebViewTransfertPage;
import com.b3g.ui.page.WhatsappPage;
import com.b3g.ui.page.appareilsDetailPage;
import com.b3g.ui.page.cih.Module.ApplicationPageOffligne;
import com.b3g.ui.page.cih.Module.DeviseOffligneCnt;
import com.b3g.ui.page.cih.Module.LangueOffligne;
import com.b3g.ui.page.cih.Module.LocaliserGabOffligne;
import com.b3g.ui.page.cih.Module.PopUpsManager;
import com.b3g.ui.page.cih.Module.SecuriteOffligneCnt;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Preferences;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;
import com.codename1.util.Base64;
import com.codename1.util.StringUtil;
import com.codename1.util.SuccessCallback;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import userclasses.StateMachine;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public abstract class B3GUiManager {
    public Command BackCommand;
    Button BtnParametres;
    public Container CurrentContainer;
    public int CurrentPageId;
    public Command ExitCommand;
    public Command HomeCommand;
    public Command MenuCommand;
    public ArrayList NavigationHistory;
    public Command NotificationCommand;
    public Command SideLogoCommand;
    public Command TitleLogoCommand;
    private String VnewaliasText;
    public Button btnBack;
    public Button btnBackOffline;
    public Container cnttop;
    public Form currentForm;
    Picker dateExcPicker;
    Picker dateFinPicker;
    DateFormat df;
    public DataTools dtTools;
    Picker frequencePicker;
    public Resources ressource;
    public StateMachine stateMachine;
    UIBuilder uib = new UIBuilder();
    public Container cntEmpty = new Container();
    Vector vecStorageClientSex = new Vector();

    private String getBloqueDebloqueMsg(int i) {
        switch (i) {
            case 10800:
                return "Votre Retrait International a été bloqué.";
            case 10801:
                return "Votre Retrait International a été dèbloqué";
            case 10802:
                return "Votre Paiement Par Carte International a été bloqué";
            case 10803:
                return "Votre Paiement Par Carte International a été dèbloqué";
            case 10804:
                return "Votre Paiement sur Internet International a été bloqué";
            case 10805:
                return "Votre Paiement sur Internet International a été dèbloqué";
            case 10806:
                return "Votre Paiement Sans Contact a été bloqué";
            case 10807:
                return "Votre Paiement Sans Contact a été dèbloqué";
            case 10808:
            case 10809:
            default:
                return "Opération effectuée avec succés!";
            case 10810:
                return "Votre Retrait National a été bloqué";
            case 10811:
                return "Votre Retrait National a été dèbloqué";
            case 10812:
                return "Votre Paiement Par Carte National a été bloqué";
            case 10813:
                return "Votre Paiement Par Carte National a été dèbloqué";
            case 10814:
                return "Votre Paiement sur Internet National a été bloqué";
            case 10815:
                return "Votre Paiement sur Internet National été dèbloqué";
        }
    }

    public abstract void DrawContainersWithNavigation(Container container, Object obj);

    public abstract Container DrawErreurContainerDshb(String str, String str2, String str3, Image image);

    public abstract Container DrawListContainer(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2, Component component3);

    public abstract Container DrawListContainerCardMultiSrvOpperation(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2);

    public abstract Container DrawListContainerCardOpperation(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2, Component component3);

    public abstract Container DrawListContainerCommands(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Container container, Object obj3);

    public abstract Container DrawListContainerDebitCard(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Container container, Object obj3);

    public abstract Container DrawListContainerForAccountsHome(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2, Component component3);

    public abstract Container DrawListContainerRibOpperation(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2);

    public abstract Container DrawListContainerRibOpperationNew(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2);

    public abstract Container DrawListContainerTransfer(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Container container, Object obj3);

    public abstract Container DrawListContainerTransfertAgence(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2);

    public abstract Container DrawListContainerWalletTransfer(String str, String str2, Boolean bool, Object obj, int i, int i2, String str3, Object obj2, Container container, Object obj3);

    public abstract Container DrawListVerticalContainer(String str, String str2, Object obj, int i, int i2, String str3, Object obj2, Component component, Component component2);

    public abstract void DrawNavigation(Container container, Form form);

    public abstract void DrawNavigationOffligne(Container container, Form form);

    public abstract void DrawTabsMenu(Container container, int i, Tabs tabs);

    public abstract void DrawTabsWithNavigation(Container container, Object obj);

    public abstract void DrawTabsWithNavigationTransfert(Container container, Object obj, Container container2, TextField textField, TextField textField2, TextField textField3);

    public abstract void DrawTabsWithNavigationV3(Container container, Object obj, Container container2, TextField textField, TextField textField2, TextField textField3);

    public abstract void Draw_GroupBy(Container container, ArrayList arrayList, String str, int i, B3gService b3gService);

    public abstract void Draw_GroupBy(Container container, ArrayList arrayList, String str, int i, B3gService b3gService, Component component);

    public abstract void Draw_GroupBy(Container container, ArrayList arrayList, String str, int i, B3gService b3gService, Component component, Component component2);

    public abstract Container GetCntMessage(String str);

    public abstract Container GetContainerNw(String str, String str2);

    public abstract void HandleBackVisibility();

    public abstract void setCountBtnMessg();

    public abstract void setIconBtnNotification();

    static /* synthetic */ void access$000(B3GUiManager b3GUiManager, Container container, int i, int i2, String str, ShareButton shareButton) {
        b3GUiManager.shareImage(container, i, i2, str, shareButton);
    }

    static /* synthetic */ String access$100(B3GUiManager b3GUiManager) {
        return b3GUiManager.VnewaliasText;
    }

    static /* synthetic */ String access$102(B3GUiManager b3GUiManager, String str) {
        b3GUiManager.VnewaliasText = str;
        return str;
    }

    static /* synthetic */ ServiceResponse access$200(B3GUiManager b3GUiManager, TransactionResume transactionResume) {
        return b3GUiManager.OvpConfirmation(transactionResume);
    }

    static /* synthetic */ Container access$300(B3GUiManager b3GUiManager, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i) {
        return b3GUiManager.virementRecap(str, str2, str3, str4, str5, str6, str7, i);
    }

    static /* synthetic */ String access$400(B3GUiManager b3GUiManager) {
        return b3GUiManager.today();
    }

    static /* synthetic */ String access$500(B3GUiManager b3GUiManager, int i) {
        return b3GUiManager.getBloqueDebloqueMsg(i);
    }

    public void ProcessAnalytics() {
        Simulator simulator = (Simulator) GetCurrentPage();
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        new Hashtable().put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        serviceRequest.setServiceId(900114);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        Hashtable hashtable = new Hashtable();
        if (simulator.sConsommation.PageSession != null) {
            hashtable.put("SimConso", "1");
            hashtable.put("SimConsoAmount", simulator.sConsommation.montantSlider.getValue().toString());
            hashtable.put("SimConsoDuration", simulator.sConsommation.dureeSlider.getValue().toString());
            hashtable.put("SimConsoRate", simulator.sConsommation.tauxSlider.getValue().toString());
        }
        if (simulator.sIskane.PageSession != null) {
            hashtable.put("SimIskane", "1");
            hashtable.put("SimIskaneAmount", simulator.sIskane.montantSlider.getValue().toString());
            hashtable.put("SimIskaneDuration", simulator.sIskane.dureeSlider.getValue().toString());
            hashtable.put("SimIskaneRate", simulator.sIskane.tauxSlider.getValue().toString());
        }
        serviceRequest.setParamsIn(hashtable);
        serviceManager.Run(serviceRequest);
    }

    public void ResetAnalytics() {
        Simulator simulator = (Simulator) GetCurrentPage();
        simulator.sConsommation.PageSession = null;
        simulator.sIskane.PageSession = null;
    }

    public void DrawHomePage(Container container) {
        this.NavigationHistory.clear();
        Home home = new Home(this, null, 1);
        home.thisContainer = container;
        home.thisContainer.setScrollableY(false);
        home.GetPageContainer();
        this.CurrentPageId = 1;
        AddPageToNavigation(home);
        HandleBackVisibility();
        if (CihMBanking.IDActionAp != 0) {
            Display.getInstance().callSerially(new 1());
        }
    }

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            new UITimer(new B3GUiManager$1$$ExternalSyntheticLambda0(this)).schedule(600, false, B3GUiManager.this.currentForm);
        }

        /* synthetic */ void lambda$run$0$com-b3g-ui-B3GUiManager$1() {
            B3GUiManager.this.NavigateToPageById(CihMBanking.IDActionAp);
        }
    }

    public void DrawLoginPage(Container container) {
        B3GPage authenticationPage;
        container.setLayout(new GridLayout(1, 1));
        this.NavigationHistory.clear();
        String str = Preferences.get("connexionState", "first");
        B3GPage b3GPage = null;
        if (str.equals("first")) {
            authenticationPage = new PreLoginPage(this, null, 114);
        } else {
            if (str.equals("cih_mobile")) {
                if (CihMBanking.sesPMTR.getIsFirst()) {
                    authenticationPage = new PreLoginPage(this, null, 114);
                } else {
                    authenticationPage = new AuthenticationPage(this, null, 190);
                }
            }
            b3GPage.thisContainer = container;
            b3GPage.GetPageContainer();
            b3GPage.updateMode();
            this.cnttop = (Container) this.uib.findByName("Container", (Container) this.currentForm);
            ((Container) this.uib.findByName("topCnt1", (Container) this.currentForm)).setUIID("cnt_bg_1");
            AddPageToNavigation(b3GPage);
        }
        b3GPage = authenticationPage;
        b3GPage.thisContainer = container;
        b3GPage.GetPageContainer();
        b3GPage.updateMode();
        this.cnttop = (Container) this.uib.findByName("Container", (Container) this.currentForm);
        ((Container) this.uib.findByName("topCnt1", (Container) this.currentForm)).setUIID("cnt_bg_1");
        AddPageToNavigation(b3GPage);
    }

    public void NavigateToPage(B3GPage b3GPage) {
        try {
            if (GetCurrentContainer().getName() != null) {
                if (GetCurrentContainer().getName().equals("TransfertWallet") || GetCurrentContainer().getName().equals("TopUpNewPage") || GetCurrentContainer().getName().equals("TransfertNewPage")) {
                    CihMBanking.sesPMTR.actionAddBenfFromTrsfr = true;
                } else {
                    CihMBanking.sesPMTR.actionAddBenfFromTrsfr = false;
                }
            } else {
                CihMBanking.sesPMTR.actionAddBenfFromTrsfr = false;
            }
            this.btnBack.getListeners().clear();
            this.btnBack.addActionListener(new B3GUiManager$$ExternalSyntheticLambda1(this));
            if (b3GPage.PageId == 0) {
                this.stateMachine.findCntHomeV2Body(this.currentForm).setUIID("ho_cntHomeV2BodyGreyBRD");
            } else {
                this.stateMachine.findCntHomeV2Body(this.currentForm).setUIID("ho_cntHomeV2Body");
            }
            if (b3GPage.PageId == 0) {
                this.stateMachine.findCntHomeV2Body(this.currentForm).replace(GetCurrentContainer(), b3GPage.GetPageContainer(), CommonTransitions.createSlide(0, true, 300));
            } else {
                this.stateMachine.findCntHomeV2Body(this.currentForm).replace(GetCurrentContainer(), b3GPage.GetPageContainer(), CommonTransitions.createSlide(0, false, 300));
            }
            b3GPage.updateMode();
            this.stateMachine.findCntHomeV2Body(this.currentForm).revalidate();
            this.CurrentPageId = b3GPage.PageId;
            AddPageToNavigation(b3GPage);
            this.BtnParametres.setHidden(true);
            HandleBackVisibility();
        } catch (Exception unused) {
        }
    }

    /* synthetic */ void lambda$NavigateToPage$0$com-b3g-ui-B3GUiManager(ActionEvent actionEvent) {
        GoBack();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x008e A[Catch: Exception -> 0x010e, TryCatch #0 {Exception -> 0x010e, blocks: (B:2:0x0000, B:4:0x000c, B:6:0x001c, B:8:0x002c, B:11:0x003d, B:12:0x004b, B:17:0x0088, B:19:0x008e, B:21:0x0093, B:24:0x00b2, B:25:0x00d9, B:27:0x010b, B:32:0x00c6, B:33:0x00a1, B:34:0x0072, B:36:0x007c, B:37:0x0042, B:38:0x0047), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0093 A[Catch: Exception -> 0x010e, TryCatch #0 {Exception -> 0x010e, blocks: (B:2:0x0000, B:4:0x000c, B:6:0x001c, B:8:0x002c, B:11:0x003d, B:12:0x004b, B:17:0x0088, B:19:0x008e, B:21:0x0093, B:24:0x00b2, B:25:0x00d9, B:27:0x010b, B:32:0x00c6, B:33:0x00a1, B:34:0x0072, B:36:0x007c, B:37:0x0042, B:38:0x0047), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b2 A[Catch: Exception -> 0x010e, TryCatch #0 {Exception -> 0x010e, blocks: (B:2:0x0000, B:4:0x000c, B:6:0x001c, B:8:0x002c, B:11:0x003d, B:12:0x004b, B:17:0x0088, B:19:0x008e, B:21:0x0093, B:24:0x00b2, B:25:0x00d9, B:27:0x010b, B:32:0x00c6, B:33:0x00a1, B:34:0x0072, B:36:0x007c, B:37:0x0042, B:38:0x0047), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x010b A[Catch: Exception -> 0x010e, TRY_LEAVE, TryCatch #0 {Exception -> 0x010e, blocks: (B:2:0x0000, B:4:0x000c, B:6:0x001c, B:8:0x002c, B:11:0x003d, B:12:0x004b, B:17:0x0088, B:19:0x008e, B:21:0x0093, B:24:0x00b2, B:25:0x00d9, B:27:0x010b, B:32:0x00c6, B:33:0x00a1, B:34:0x0072, B:36:0x007c, B:37:0x0042, B:38:0x0047), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c6 A[Catch: Exception -> 0x010e, TryCatch #0 {Exception -> 0x010e, blocks: (B:2:0x0000, B:4:0x000c, B:6:0x001c, B:8:0x002c, B:11:0x003d, B:12:0x004b, B:17:0x0088, B:19:0x008e, B:21:0x0093, B:24:0x00b2, B:25:0x00d9, B:27:0x010b, B:32:0x00c6, B:33:0x00a1, B:34:0x0072, B:36:0x007c, B:37:0x0042, B:38:0x0047), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a1 A[Catch: Exception -> 0x010e, TryCatch #0 {Exception -> 0x010e, blocks: (B:2:0x0000, B:4:0x000c, B:6:0x001c, B:8:0x002c, B:11:0x003d, B:12:0x004b, B:17:0x0088, B:19:0x008e, B:21:0x0093, B:24:0x00b2, B:25:0x00d9, B:27:0x010b, B:32:0x00c6, B:33:0x00a1, B:34:0x0072, B:36:0x007c, B:37:0x0042, B:38:0x0047), top: B:1:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void NavigateToPageById(int r9, java.lang.Object r10) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.B3GUiManager.NavigateToPageById(int, java.lang.Object):void");
    }

    /* synthetic */ void lambda$NavigateToPageById$1$com-b3g-ui-B3GUiManager(ActionEvent actionEvent) {
        GoBack();
    }

    public void NavigateToPageById(int i) {
        B3GPage home;
        Container GetPageContainer;
        try {
            if (GetCurrentContainer().getName() != null) {
                if (GetCurrentContainer().getName().equals("TransfertWallet") || GetCurrentContainer().getName().equals("TopUpNewPage") || GetCurrentContainer().getName().equals("TransfertNewPage")) {
                    CihMBanking.sesPMTR.actionAddBenfFromTrsfr = true;
                } else {
                    CihMBanking.sesPMTR.actionAddBenfFromTrsfr = false;
                }
            } else {
                CihMBanking.sesPMTR.actionAddBenfFromTrsfr = false;
            }
            this.btnBack.getListeners().clear();
            this.btnBack.addActionListener(new B3GUiManager$$ExternalSyntheticLambda2(this));
            Container container = new Container();
            B3GPage b3GPage = null;
            switch (i) {
                case 1:
                    this.BtnParametres.setHidden(false);
                    home = new Home(this, null, i);
                    home.thisContainer = this.stateMachine.findCntMain(this.currentForm);
                    home.thisContainer.removeAll();
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 2:
                    home = new AccountsListPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 3:
                    home = new LoanListPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 4:
                    home = new CardsListPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 5:
                    home = new ReccuringsListPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 6:
                    home = new WaletListPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 7:
                    home = new InsuranceAllProductsPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 8:
                    if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus.equals("11")) {
                        SessionParameter.currentTransactionnalPage = 8;
                        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.ShowConfirmPopUp(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Pour continuer votre opération merci de procéder au changement de votre mot de passe", "MenuV2Form");
                        break;
                    } else {
                        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
                            home = new PinNotActivatedPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        } else {
                            home = new TransferNewPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        }
                        b3GPage = home;
                        container = GetPageContainer;
                        break;
                    }
                case 9:
                    if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus.equals("11")) {
                        SessionParameter.currentTransactionnalPage = 9;
                        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.ShowConfirmPopUp(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Pour continuer votre opération merci de procéder au changement de votre mot de passe", "MenuV2Form");
                        break;
                    } else {
                        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
                            home = new PinNotActivatedPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        } else {
                            home = new TopUpNewPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        }
                        b3GPage = home;
                        container = GetPageContainer;
                        break;
                    }
                case 10:
                    home = new SavingAccountList(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 11:
                    home = new AboutPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 13:
                    home = new CheckBookDemandPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 14:
                    if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus.equals("11")) {
                        SessionParameter.currentTransactionnalPage = 14;
                        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.ShowConfirmPopUp(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Pour continuer votre opération merci de procéder au changement de votre mot de passe", "MenuV2Form");
                        break;
                    } else {
                        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
                            home = new PinNotActivatedPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        } else {
                            home = new CardADOppositionPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        }
                        b3GPage = home;
                        container = GetPageContainer;
                        break;
                    }
                case 15:
                    home = new Simulator(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 16:
                    home = new RibPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 17:
                    home = new Avis(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 18:
                    home = new MonConseillerPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 19:
                    home = new ComplaintPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 20:
                    home = new MTCCreancierPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 21:
                    home = new MTCCreancePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 22:
                    home = new MTCFormPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 23:
                    home = new MTCImpayeNewPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 24:
                    home = new MTCConfirmationPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 25:
                    if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus.equals("11")) {
                        SessionParameter.currentTransactionnalPage = 25;
                        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.ShowConfirmPopUp(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Pour continuer votre opération merci de procéder au changement de votre mot de passe", "MenuV2Form");
                        break;
                    } else {
                        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
                            home = new PinNotActivatedPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        } else {
                            home = new MTCMenuPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                            CihMBanking.sesPMTR.setBillerType(0);
                        }
                        b3GPage = home;
                        container = GetPageContainer;
                        break;
                    }
                case 27:
                    home = new MTCHistoricPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 28:
                    home = new MTCNewHistoricDetailPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 29:
                    home = new MTCFavoriteBillPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 30:
                    home = new MTCCreancierTopPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 32:
                    home = new MTCImpayeTopupPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 33:
                    if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus.equals("11")) {
                        SessionParameter.currentTransactionnalPage = 33;
                        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.ShowConfirmPopUp(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Pour continuer votre opération merci de procéder au changement de votre mot de passe", "MenuV2Form");
                        break;
                    } else {
                        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
                            home = new PinNotActivatedPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        } else {
                            home = new MTCTopUpMenuPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                            CihMBanking.sesPMTR.setBillerType(1);
                        }
                        b3GPage = home;
                        container = GetPageContainer;
                        break;
                    }
                case 34:
                    home = new MADFundsOrderPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 35:
                    home = new StudentTransferPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 37:
                    home = new CurrencyFundsOrderPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 39:
                    home = new MyEDocumentsPageNew(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 41:
                    if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus.equals("11")) {
                        SessionParameter.currentTransactionnalPage = 41;
                        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.ShowConfirmPopUp(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Pour continuer votre opération merci de procéder au changement de votre mot de passe", "MenuV2Form");
                        break;
                    } else {
                        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
                            home = new PinNotActivatedPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        } else {
                            home = new FatouratiPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        }
                        b3GPage = home;
                        container = GetPageContainer;
                        break;
                    }
                case 44:
                    home = new MyCheckPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 45:
                    home = new MyLcnPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 46:
                    home = new MyOvps(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 47:
                    if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus.equals("11")) {
                        SessionParameter.currentTransactionnalPage = 47;
                        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.ShowConfirmPopUp(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Pour continuer votre opération merci de procéder au changement de votre mot de passe", "MenuV2Form");
                        break;
                    } else {
                        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
                            home = new PinNotActivatedPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        } else {
                            home = new PrepaidCardDebit(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        }
                        b3GPage = home;
                        container = GetPageContainer;
                        break;
                    }
                case 48:
                    home = new RelationalCirclePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 50:
                    home = new RelationDetailPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 51:
                    home = new BeneficiaryManagerPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 52:
                    if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus.equals("11")) {
                        SessionParameter.currentTransactionnalPage = 52;
                        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.ShowConfirmPopUp(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Pour continuer votre opération merci de procéder au changement de votre mot de passe", "MenuV2Form");
                        break;
                    } else {
                        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
                            home = new PinNotActivatedPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        } else {
                            home = new CashOutPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        }
                        b3GPage = home;
                        container = GetPageContainer;
                        break;
                    }
                case 53:
                    home = new DGIPaymentFormPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 54:
                    if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus.equals("11")) {
                        SessionParameter.currentTransactionnalPage = 54;
                        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.ShowConfirmPopUp(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Pour continuer votre opération merci de procéder au changement de votre mot de passe", "MenuV2Form");
                        break;
                    } else {
                        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
                            home = new PinNotActivatedPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        } else {
                            home = new DGIPaymentMenuPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        }
                        b3GPage = home;
                        container = GetPageContainer;
                        break;
                    }
                case 55:
                    home = new DGIPaymentHistoric(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 57:
                    home = new Login_New_Page(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 58:
                    SessionParameter.currentTransactionnalPage = 0;
                    home = new ChangePasswordPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 59:
                    home = new LocaliserGabPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 60:
                    home = new LocaliserAgencePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 61:
                    home = new CheckLcnDetailPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 62:
                    home = new ProfilePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 63:
                    home = new InsuranceCotisationDetailPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 64:
                    home = new DotationTouristiquePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 65:
                    home = new DotationCartePrepayeesPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 66:
                    home = new DotationServieEnDevisePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 67:
                    home = new DotationDetailEchanges(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 68:
                    home = new DotationUtilisationEtranger(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 69:
                    home = new DotationRechargeCartePrepaid(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 70:
                    home = new DotationEcomPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 71:
                    home = new DotationCosommationPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 72:
                    home = new AddBenFromCompte(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 73:
                    home = new AddBenfPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 74:
                    home = new NewMtcHistoricPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 76:
                    home = new NotificationPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 77:
                    home = new NotificationPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 78:
                    if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus.equals("11")) {
                        SessionParameter.currentTransactionnalPage = 78;
                        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.ShowConfirmPopUp(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Pour continuer votre opération merci de procéder au changement de votre mot de passe", "MenuV2Form");
                        break;
                    } else {
                        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
                            home = new PinNotActivatedPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        } else {
                            home = new CardsDetailPageNew2(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        }
                        b3GPage = home;
                        container = GetPageContainer;
                        break;
                    }
                case 79:
                    home = new AgenceChangePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 80:
                    home = new AddBenfWallet(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 81:
                    if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus.equals("11")) {
                        SessionParameter.currentTransactionnalPage = 81;
                        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.ShowConfirmPopUp(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Pour continuer votre opération merci de procéder au changement de votre mot de passe", "MenuV2Form");
                        break;
                    } else {
                        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
                            home = new PinNotActivatedPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        } else {
                            home = new TransfertWallet(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        }
                        b3GPage = home;
                        container = GetPageContainer;
                        break;
                    }
                case 90:
                    home = new BourseOnline(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 91:
                    home = new Messagerie1(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 92:
                    home = new MessageNew(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 94:
                    home = new MessagePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 95:
                    home = new MessagerieReponse(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 96:
                    home = new MessageSentPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 97:
                    home = new MessageReplyPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 98:
                    home = new MessagerieReponseForSend(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 101:
                    if (CihMBanking.sesPMTR.getSessionClient().getClient_Profil().accountStatus.equals("11")) {
                        SessionParameter.currentTransactionnalPage = 101;
                        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.ShowConfirmPopUp(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Pour continuer votre opération merci de procéder au changement de votre mot de passe", "MenuV2Form");
                        break;
                    } else {
                        if (!CihMBanking.sesPMTR.getSessionClient().getClient_Profil().isPinActivated) {
                            home = new PinNotActivatedPage(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        } else {
                            home = new TransferCovid(this, null, i);
                            GetPageContainer = home.GetPageContainer();
                        }
                        b3GPage = home;
                        container = GetPageContainer;
                        break;
                    }
                case 102:
                    home = new Home(this, null, i);
                    home.thisContainer = this.stateMachine.findCntMain(this.currentForm);
                    new Card().CheckAutorisationToken(Display.getInstance().getPlatformName().toString(), false);
                    if (CihMBanking.sesPMTR.FlagCihPay) {
                        NativeCall nativeCall = (NativeCall) NativeLookup.create(NativeCall.class);
                        if (Display.getInstance().getPlatformName().equals("ios")) {
                            nativeCall.callCihPaySDK(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().gsm, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, ((Account) CihMBanking.sesPMTR.getSessionClient().getClient_AccountList().get(0)).accountNumber, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
                        } else {
                            nativeCall.callRealityAgmPage();
                        }
                    } else {
                        new UITimer(new 2()).schedule(50, false, this.currentForm);
                    }
                    home.thisContainer.removeAll();
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 103:
                    home = new appareilsDetailPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 104:
                    home = new ParameteresPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 105:
                    home = new FingerprintPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 106:
                    home = new RecalculePinPageV1(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 107:
                    home = new MtcImpayeGroup(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 108:
                    home = new WhatsappPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 109:
                    home = new TermsConditionsPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 111:
                    home = new PinAddPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 112:
                    home = new PinUpdatePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 113:
                    home = new CashOutFormPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 114:
                    home = new PreLoginPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 115:
                    home = new InsuranceAllProductsPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 119:
                    home = new CustomShortcutsPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 122:
                    home = new EpargneDescription(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 123:
                    home = new EpargneConditions(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 124:
                    home = new OvpStatutPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 125:
                    home = new OvpSuspendre(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 126:
                    home = new AddOvp(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 127:
                    home = new EpargneFinalize(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 128:
                    home = new EpargneProgramPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 129:
                    home = new EpargneProgramTransfertPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 130:
                    home = new OvpBnnef(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 131:
                    home = new OvpAction(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 132:
                    home = new VirementDetails(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 133:
                    home = new RejoueOvpPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 134:
                    home = new TarifOperationPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 135:
                    home = new SinistreContratPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 137:
                    home = new CardsUpdatePlafond(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 138:
                    home = new OppositionCardNw(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 139:
                    home = new DechargeComptePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 140:
                    home = new MdmPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 141:
                    home = new MdmStep2Page(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 142:
                    home = new WebViewPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 143:
                    home = new CardsUpdatePlafond(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 144:
                    home = new AgregationHistoriquePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 145:
                    home = new AgregationFeebackPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 146:
                    home = new RenouvelementWebViewPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 147:
                    home = new TransfertWebView(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 148:
                    home = new WebViewNVPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 149:
                    home = new TransferFromEtranger(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 150:
                    home = new TransfertWebViewKyc(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 151:
                    home = new ReccuringDetailPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 152:
                    home = new WebViewTransfertPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 153:
                    home = new RecapTransfertFromEtrangerPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 154:
                    home = new TransfertKyc(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 156:
                    home = new AcountEtrangerPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 157:
                    home = new SOSAssistance(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 158:
                    home = new SuiviDemande(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 161:
                    home = new WaletDetailPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 164:
                    home = new ProduitBancairePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 165:
                    home = new BrowserPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 166:
                    home = new MDMRTransKysRejePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 167:
                    home = new MDMBackOfficeDtailPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 170:
                    home = new OffreCIMRPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 171:
                    home = new InsuranceDetailPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 172:
                    home = new PlansRetraitePage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
                case 173:
                    home = new OffreGamerPage(this, null, i);
                    GetPageContainer = home.GetPageContainer();
                    b3GPage = home;
                    container = GetPageContainer;
                    break;
            }
            if (this.CurrentPageId == 15) {
                ProcessAnalytics();
            }
            if (i == 0) {
                this.stateMachine.findCntHomeV2Body(this.currentForm).setUIID("ho_cntHomeV2BodyGreyBRD");
            } else {
                this.stateMachine.findCntHomeV2Body(this.currentForm).setUIID("ho_cntHomeV2Body");
            }
            if (i == 0) {
                this.stateMachine.findCntHomeV2Body(this.currentForm).replace(GetCurrentContainer(), container, CommonTransitions.createSlide(0, true, 300));
            } else {
                this.stateMachine.findCntHomeV2Body(this.currentForm).replace(GetCurrentContainer(), container, CommonTransitions.createSlide(0, false, 300));
            }
            this.stateMachine.findCntHomeV2Body(this.currentForm).revalidate();
            this.stateMachine.findCntHomeV2Header(this.currentForm).revalidate();
            this.CurrentPageId = i;
            CihMBanking.IDActionAp = 0;
            AddPageToNavigation(b3GPage);
            if (i == 102) {
                this.BtnParametres.setHidden(false);
            } else {
                this.BtnParametres.setHidden(true);
                HandleBackVisibility();
            }
            b3GPage.updateMode();
            this.stateMachine.findCntHomeV2Header(this.currentForm).revalidate();
            this.currentForm.revalidate();
            if (this.CurrentPageId == 15) {
                ResetAnalytics();
            }
        } catch (Exception unused) {
        }
    }

    /* synthetic */ void lambda$NavigateToPageById$2$com-b3g-ui-B3GUiManager(ActionEvent actionEvent) {
        GoBack();
    }

    class 2 implements Runnable {
        2() {
        }

        public void run() {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(B3GUiManager.this.stateMachine, DataTools.FormatUnicode(CihMBanking.sesPMTR.statusLabelCihPay), null);
        }
    }

    public void NavigateToPageByIdOffligne(int i) {
        B3GPage langueOffligne;
        Container GetPageContainer;
        try {
            if (i == this.CurrentPageId) {
                return;
            }
            new PopUpsManager(this);
            Container container = new Container();
            B3GPage b3GPage = null;
            NativeCall nativeCall = !Display.getInstance().isSimulator() ? (NativeCall) NativeLookup.create(NativeCall.class) : null;
            this.btnBackOffline.setVisible(false);
            if (i == 88) {
                Display.getInstance().dial("4747");
            } else if (i != 89) {
                if (i == 99) {
                    langueOffligne = new LangueOffligne(this, null, i);
                    GetPageContainer = langueOffligne.GetPageContainer();
                } else if (i == 114) {
                    langueOffligne = new PreLoginPage(this, null, i);
                    GetPageContainer = langueOffligne.GetPageContainer();
                } else if (i != 162) {
                    if (i == 190) {
                        langueOffligne = new AuthenticationPage(this, null, i);
                        GetPageContainer = langueOffligne.GetPageContainer();
                    } else if (i == 120) {
                        langueOffligne = new IdentificationGlb(this, null, i);
                        GetPageContainer = langueOffligne.GetPageContainer();
                    } else if (i == 121) {
                        langueOffligne = new ForgotPasswdPage(this, null, i);
                        GetPageContainer = langueOffligne.GetPageContainer();
                    } else if (i == 168) {
                        langueOffligne = new PreLoginInssurancePage(this, null, i);
                        GetPageContainer = langueOffligne.GetPageContainer();
                    } else if (i == 169) {
                        langueOffligne = new BrowserOfflinePage(this, null, i);
                        GetPageContainer = langueOffligne.GetPageContainer();
                    } else {
                        switch (i) {
                            case 82:
                                this.stateMachine.ShowDemoPopUp();
                                break;
                            case 83:
                                LocaliserGabOffligne localiserGabOffligne = new LocaliserGabOffligne(this, null, i);
                                localiserGabOffligne.GetPageContainer();
                                b3GPage = localiserGabOffligne;
                                break;
                            case 84:
                                langueOffligne = new ApplicationPageOffligne(this, null, i);
                                GetPageContainer = langueOffligne.GetPageContainer();
                                break;
                            case 85:
                                langueOffligne = new SecuriteOffligneCnt(this, null, i);
                                GetPageContainer = langueOffligne.GetPageContainer();
                                break;
                            case 86:
                                langueOffligne = new DeviseOffligneCnt(this, null, i);
                                GetPageContainer = langueOffligne.GetPageContainer();
                                break;
                        }
                    }
                } else if (!Display.getInstance().getPlatformName().equals("ios") && nativeCall.isSupported()) {
                    nativeCall.statWebView();
                }
                b3GPage = langueOffligne;
                container = GetPageContainer;
            } else {
                nativeCall.callEERActivity();
            }
            if (b3GPage.PageId != 83) {
                this.stateMachine.findTopCnt1(this.currentForm).setUIID("cnt_bg_1");
                this.stateMachine.findTabsCnt(this.currentForm).removeAll();
                Container findTabsCnt = this.stateMachine.findTabsCnt(this.currentForm);
                if (b3GPage.PageId == 190 && !CihMBanking.sesPMTR.getFromMenu()) {
                    container = CihMBanking.sesPMTR.getSessionSavedContainer();
                }
                findTabsCnt.add(container);
            }
            if (i == 120 || i == 99 || i == 57 || i == 121) {
                this.stateMachine.findContainer2(this.currentForm).setPreferredH((Display.getInstance().getDisplayHeight() * 15) / 100);
            } else {
                this.stateMachine.findContainer2(this.currentForm).setPreferredH(0);
            }
            if (i != 114 && i != 190 && i != 121 && this.NavigationHistory.size() >= 1) {
                this.btnBackOffline.setVisible(true);
                this.btnBackOffline.addActionListener(new 3());
            }
            if (i == 190) {
                Iterator it = this.NavigationHistory.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((B3GPage) it.next()).PageId == 114) {
                            this.NavigationHistory.clear();
                        }
                    }
                }
            }
            this.CurrentPageId = i;
            AddPageToNavigation(b3GPage);
            this.stateMachine.findTabsCnt(this.currentForm).forceRevalidate();
            this.currentForm.revalidate();
        } catch (Exception unused) {
        }
    }

    class 3 implements ActionListener {
        3() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            B3GUiManager.this.GoBackOffligne();
        }
    }

    public void switch_logo_icon(String str) {
        CihMBanking.sesPMTR.getLogoButton().setIcon(this.ressource.getImage("LogoHeader" + str + ".png"));
        CihMBanking.sesPMTR.getLogoButton().getParent().revalidate();
    }

    public B3GPage GetLastPageForId(int i) {
        for (int size = this.NavigationHistory.size() - 1; size >= 0; size--) {
            if (((B3GPage) this.NavigationHistory.get(size)).PageId == i) {
                return (B3GPage) this.NavigationHistory.get(size);
            }
        }
        return null;
    }

    public B3GPage GetLastPage() {
        if (this.NavigationHistory.size() <= 1) {
            return null;
        }
        return (B3GPage) this.NavigationHistory.get(r0.size() - 2);
    }

    public B3GPage GetCurrentPage() {
        if (this.NavigationHistory.size() <= 0) {
            return null;
        }
        return (B3GPage) this.NavigationHistory.get(r0.size() - 1);
    }

    public B3GPage GetLastPageSessionSet() {
        for (int size = this.NavigationHistory.size() - 1; size >= 0; size--) {
            if (((B3GPage) this.NavigationHistory.get(size)).PageSession != null) {
                return (B3GPage) this.NavigationHistory.get(size);
            }
        }
        return null;
    }

    public Container GetCurrentContainer() {
        return ((B3GPage) this.NavigationHistory.get(r0.size() - 1)).thisContainer;
    }

    public void AddPageToNavigation(B3GPage b3GPage) {
        if (this.NavigationHistory.size() > 0) {
            if (((B3GPage) this.NavigationHistory.get(r0.size() - 1)).PageId != b3GPage.PageId) {
                this.NavigationHistory.add(b3GPage);
                return;
            }
            this.NavigationHistory.set(r0.size() - 1, b3GPage);
            int size = this.NavigationHistory.size();
            for (int i = 0; i < size - 2; i++) {
                if (((B3GPage) this.NavigationHistory.get(i)).PageId == b3GPage.PageId) {
                    this.NavigationHistory.remove(i);
                }
            }
            return;
        }
        this.NavigationHistory.add(b3GPage);
    }

    public void GoBack() {
        try {
            if (this.CurrentPageId == 15) {
                ProcessAnalytics();
            }
            B3GPage b3GPage = (B3GPage) this.NavigationHistory.get(r0.size() - 2);
            if (b3GPage.PageId == 1) {
                Display.getInstance().callSerially(new 4());
                return;
            }
            if (b3GPage.PageId == 0) {
                this.NavigationHistory.remove(r0.size() - 2);
                GoBack();
            } else {
                if (this.NavigationHistory.size() - 2 < 0) {
                    return;
                }
                this.stateMachine.findCntHomeV2Body(this.currentForm).revalidate();
                Container findCntHomeV2Body = this.stateMachine.findCntHomeV2Body(this.currentForm);
                ArrayList arrayList = this.NavigationHistory;
                findCntHomeV2Body.replace(((B3GPage) arrayList.get(arrayList.size() - 1)).thisContainer, ((B3GPage) this.NavigationHistory.get(r3.size() - 2)).thisContainer, CommonTransitions.createSlide(0, false, 300));
                this.CurrentPageId = ((B3GPage) this.NavigationHistory.get(r0.size() - 2)).PageId;
                ArrayList arrayList2 = this.NavigationHistory;
                arrayList2.remove(arrayList2.size() - 1);
                HandleBackVisibility();
            }
        } catch (Exception unused) {
        }
    }

    class 4 implements Runnable {
        4() {
        }

        public void run() {
            new UITimer(new B3GUiManager$4$$ExternalSyntheticLambda0(this)).schedule(300, false, B3GUiManager.this.currentForm);
        }

        /* synthetic */ void lambda$run$0$com-b3g-ui-B3GUiManager$4() {
            B3GUiManager.this.stateMachine.showForm("MenuV2Form", (Command) null);
        }
    }

    public void GoBackOffligne() {
        try {
            if (this.CurrentPageId == 190 && AuthenticationPage.is_open) {
                AuthenticationPage.keyboardDialog.back();
                return;
            }
            B3GPage GetLastPage = GetLastPage();
            if (this.btnBackOffline.getListeners() != null) {
                this.btnBackOffline.getListeners().clear();
            }
            if (GetLastPage.PageId == 0) {
                ArrayList arrayList = this.NavigationHistory;
                arrayList.remove(arrayList.size() - 2);
                GoBackOffligne();
                return;
            }
            if (this.NavigationHistory.size() - 2 < 0) {
                return;
            }
            if (GetLastPage().PageId != 114 && GetLastPage().PageId != 190 && GetLastPage().PageId != 168 && GetLastPage().PageId != 84 && GetLastPage().PageId != 86 && GetLastPage().PageId != 85 && GetLastPage().PageId != 169) {
                this.stateMachine.findContainer2(this.currentForm).setPreferredH((Display.getInstance().getDisplayHeight() * 15) / 100);
            } else {
                this.stateMachine.findContainer2(this.currentForm).setPreferredH(0);
            }
            this.stateMachine.findTopCnt1(this.currentForm).setUIID("cnt_bg_1");
            if (GetLastPage.PageId == 114) {
                if (CihMBanking.sesPMTR.getPreloginStep() == 2) {
                    this.btnBackOffline.setVisible(true);
                    this.btnBackOffline.addActionListener(CihMBanking.sesPMTR.getBtnListener());
                } else if (CihMBanking.sesPMTR.getPreloginStep() == 1) {
                    this.btnBackOffline.setVisible(false);
                }
                this.stateMachine.findTabsCnt(this.currentForm).revalidate();
                this.stateMachine.findTabsCnt(this.currentForm).removeAll();
                this.stateMachine.findTabsCnt(this.currentForm).add(CihMBanking.sesPMTR.getSessionSavedContainer());
                this.stateMachine.findTabsCnt(this.currentForm).forceRevalidate();
            } else if (GetLastPage.PageId == 190) {
                if (CihMBanking.sesPMTR.getPreloginStep() == 2) {
                    this.btnBackOffline.setVisible(true);
                    this.btnBackOffline.addActionListener(CihMBanking.sesPMTR.getBtnListener());
                } else if (CihMBanking.sesPMTR.getPreloginStep() == 1) {
                    this.btnBackOffline.setVisible(false);
                }
                Container findTabsCnt = this.stateMachine.findTabsCnt(this.currentForm);
                ArrayList arrayList2 = this.NavigationHistory;
                findTabsCnt.removeComponent(((B3GPage) arrayList2.get(arrayList2.size() - 1)).thisContainer);
                this.stateMachine.findTabsCnt(this.currentForm).revalidate();
                this.stateMachine.findTabsCnt(this.currentForm).add(CihMBanking.sesPMTR.getSessionSavedContainer());
                this.stateMachine.findTabsCnt(this.currentForm).forceRevalidate();
            } else {
                this.stateMachine.findTabsCnt(this.currentForm).removeAll();
                this.stateMachine.findTabsCnt(this.currentForm).add(GetLastPage.thisContainer);
                this.btnBackOffline.addActionListener(new 5());
                this.stateMachine.findTabsCnt(this.currentForm).forceRevalidate();
            }
            this.CurrentPageId = GetLastPage.PageId;
            ArrayList arrayList3 = this.NavigationHistory;
            arrayList3.remove(arrayList3.size() - 1);
            if (this.NavigationHistory.size() == 1 && this.CurrentPageId == 190) {
                if (CihMBanking.sesPMTR.getPreloginStep() == 2) {
                    this.btnBackOffline.setVisible(true);
                    this.btnBackOffline.getListeners().clear();
                    this.btnBackOffline.addActionListener(CihMBanking.sesPMTR.getBtnListener());
                } else {
                    this.btnBackOffline.setVisible(false);
                }
            }
            this.stateMachine.findTabsCnt(this.currentForm).revalidate();
        } catch (Exception unused) {
        }
    }

    class 5 implements ActionListener {
        5() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            B3GUiManager.this.GoBackOffligne();
        }
    }

    public void SetKeyBoardRandomValue(Button button, Button button2, Button button3, Button button4, Button button5, Button button6, Button button7, Button button8, Button button9, Button button10) {
        ArrayList GetRandomIntArray = DataTools.GetRandomIntArray(10);
        button.setText("" + GetRandomIntArray.get(0));
        button2.setText("" + GetRandomIntArray.get(1));
        button3.setText("" + GetRandomIntArray.get(2));
        button4.setText("" + GetRandomIntArray.get(3));
        button5.setText("" + GetRandomIntArray.get(4));
        button6.setText("" + GetRandomIntArray.get(5));
        button7.setText("" + GetRandomIntArray.get(6));
        button8.setText("" + GetRandomIntArray.get(7));
        button9.setText("" + GetRandomIntArray.get(8));
        button10.setText("" + GetRandomIntArray.get(9));
    }

    public void KeyBoardDelete(TextField textField) {
        if (textField.getText().length() > 0) {
            textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
        } else {
            textField.setText(textField.getText());
        }
    }

    public void ShowDialog(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2) {
        ArrayList GetPopupItemDetail;
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            if (i == 57 || i == 59 || i == 58) {
                GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, b3gService2, null);
            } else {
                GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, b3gService2);
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            stateMachine.findLblpopupItemTitleV2(createContainer).setText(GetPopupItemTitle(i, b3gService2));
            stateMachine.findLblpopupItemValueV2(createContainer).setText(GetPopupItemTitleValue(i, b3gService2));
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
            int i2 = 0;
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
            if (i == 72) {
                Container findCntPopupBody = stateMachine.findCntPopupBody(createContainer);
                findCntPopupBody.removeAll();
                findCntPopupBody.addComponent(label);
                while (i2 < GetPopupItemDetail.size()) {
                    findCntPopupBody.addComponent((Component) GetPopupItemDetail.get(i2));
                    i2++;
                }
                Container container = new Container();
                TableLayout tableLayout = new TableLayout(1, 2);
                container.setLayout(tableLayout);
                container.addComponent(tableLayout.createConstraint().widthPercentage(50), GetShareBtnCnt(dialog));
                container.addComponent(tableLayout.createConstraint().widthPercentage(50), GetPopupCloseVirement(dialog));
                findCntPopupBody.addComponent(container);
            } else {
                while (i2 < GetPopupItemDetail.size()) {
                    stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i2));
                    i2++;
                }
                stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupClose(dialog));
            }
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    private Container GetShareBtnCnt(Dialog dialog) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        ShareButton shareButton = new ShareButton();
        shareButton.setText(" Partager ");
        shareButton.setUIID("dg_BtnYest");
        shareButton.setIcon(this.ressource.getImage("ico_share_popup_NW.png"));
        shareButton.setVerticalAlignment(4);
        shareButton.addActionListener(new 6(shareButton, dialog));
        container.setLeadComponent(shareButton);
        container.addComponent(shareButton);
        return container;
    }

    class 6 implements ActionListener {
        final /* synthetic */ ShareButton val$btnIcon;
        final /* synthetic */ Dialog val$d;

        6(ShareButton shareButton, Dialog dialog) {
            this.val$btnIcon = shareButton;
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = new Container();
            container.setUIID("shareTutoCash");
            B3GUiManager.access$000(B3GUiManager.this, container, 720, 1280, "tutorial.png", this.val$btnIcon);
            this.val$d.dispose();
        }
    }

    private void shareImage(Container container, int i, int i2, String str, ShareButton shareButton) {
        container.setHeight(i);
        container.setWidth(i2);
        Image createImage = Image.createImage(i2, i);
        container.revalidate();
        container.setVisible(true);
        container.paintComponent(createImage.getGraphics(), true);
        String str2 = FileSystemStorage.getInstance().getAppHomePath() + str;
        try {
            OutputStream openOutputStream = FileSystemStorage.getInstance().openOutputStream(str2);
            try {
                ImageIO.getImageIO().save(createImage, openOutputStream, "png", 1.0f);
                if (openOutputStream != null) {
                    openOutputStream.close();
                }
            } finally {
            }
        } catch (IOException unused) {
        }
        shareButton.setImageToShare(str2, "image/png");
    }

    public void ShowDialogTransfertService(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2, int i2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, null);
            for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            if (i2 == 300017) {
                stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupCashoutBtn(dialog, i, b3gService, i2));
            } else {
                stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupTransferBtn(dialog, i, b3gService, i2));
            }
            switch (i2) {
                case 131:
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText("Recharge We PAY");
                    break;
                case 161:
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText("Recharge We PAY");
                    break;
                case 300013:
                    if (i == 90) {
                        stateMachine.findLblpopupItemTitleV2(createContainer).setText("Fond de solidarité séisme");
                        break;
                    } else {
                        stateMachine.findLblpopupItemTitleV2(createContainer).setText("Virement");
                        break;
                    }
                case 300017:
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText("Mise à disposition");
                    break;
                case 300019:
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText("Virement instantané");
                    break;
                case 900108:
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText("Ordre de virement permanent");
                    break;
                case 900118:
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText("Débit Carte");
                    break;
                default:
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText("Recharge Carte");
                    break;
            }
            stateMachine.findLblpopupItemValueV2(createContainer).setText(GetPopupItemTitleValue(i, b3gService2));
            createContainer.setPreferredH(CihMBanking.sesPMTR.displayHeight);
            createContainer.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(false);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(230);
            dialog.getDialogStyle().setBgColor(1118481);
            dialog.setPreferredH(CihMBanking.sesPMTR.displayHeight);
            dialog.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
            dialog.getStyle().setPadding(0, 0, 0, 0);
            dialog.getStyle().setMargin(0, 0, 0, 0);
            dialog.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog.revalidate();
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    public void ShowDialogOvpService(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2, int i2, TransactionResume transactionResume) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, null);
            for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupOvptBtn(dialog, i, b3gService, i2));
            switch (i2) {
                case 131:
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText("Recharge We PAY");
                    break;
                case 300013:
                    if (i == 90) {
                        stateMachine.findLblpopupItemTitleV2(createContainer).setText("Fond de solidarité séisme");
                        break;
                    } else {
                        stateMachine.findLblpopupItemTitleV2(createContainer).setText("Virements Permanents");
                        break;
                    }
                case 300017:
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText("Mise à disposition");
                    break;
                case 900108:
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText("Ordre de virement permanent");
                    break;
                case 900118:
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText("Débit Carte");
                    break;
                default:
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText("Recharge Carte");
                    break;
            }
            stateMachine.findLblpopupItemValueV2(createContainer).setText(GetPopupItemTitleValue(i, b3gService2));
            createContainer.setPreferredH(CihMBanking.sesPMTR.displayHeight);
            createContainer.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
            dialog.setAlwaysTensile(false);
            dialog.setDraggable(false);
            dialog.setScrollable(false);
            dialog.setTactileTouch(false);
            dialog.setTensileDragEnabled(false);
            dialog.getDialogStyle().setBgTransparency(230);
            dialog.getDialogStyle().setBgColor(1118481);
            dialog.setPreferredH(CihMBanking.sesPMTR.displayHeight);
            dialog.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
            dialog.getStyle().setPadding(0, 0, 0, 0);
            dialog.getStyle().setMargin(0, 0, 0, 0);
            dialog.getDialogStyle().setPadding(0, 0, 0, 0);
            dialog.getDialogStyle().setMargin(0, 0, 0, 0);
            dialog.revalidate();
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    public Container GetPopupOvptBtn(Dialog dialog, int i, B3gService b3gService, int i2) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 7(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 8(dialog, b3gService));
        container.addComponent(button2);
        return container;
    }

    class 7 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        7(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 8 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ B3gService val$pB3gService;

        8(Dialog dialog, B3gService b3gService) {
            this.val$d = dialog;
            this.val$pB3gService = b3gService;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            B3GUiManager.this.SwitchTransfertForms(AddOvp.cntTransfertForm, B3GUiManager.this.stateMachine.findCntGlobalTransfer(AddOvp.cntTransfertForm), B3GUiManager.this.GetTransferSecurityForm(AddOvp.cntTransfertForm, this.val$pB3gService), false);
        }
    }

    public void SwitchTransfertForms(Container container, Container container2, Container container3, boolean z) {
        container.replace(container2, container3, CommonTransitions.createSlide(0, z, 0));
        container.revalidate();
    }

    public Container GetTransferSecurityForm(Container container, B3gService b3gService) {
        Container container2 = new Container(BoxLayout.y());
        Container container3 = new Container(BoxLayout.y());
        Label label = new Label("Service sélectionné : ");
        label.setUIID("g_lblDashBoardTitleOrange");
        Label label2 = new Label("Activer Bénéficiaire");
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
        Container createContainer = this.stateMachine.createContainer(this.ressource, "Codecnt1");
        Container container6 = (Container) this.uib.findByName("Container17", createContainer);
        container6.removeAll();
        container6.setLayout(BoxLayout.y());
        container6.add(b3GCIHEcode.getComponent());
        this.stateMachine.findBtnTransferBack(createContainer).addActionListener(new 9(container));
        this.stateMachine.findBtnTransferValidation(AddOvp.cntTransfertForm).setHidden(true);
        this.stateMachine.findBtnTransferValidation(createContainer).addActionListener(new 10(b3gService, b3GCIHEcode));
        return createContainer;
    }

    class 9 implements ActionListener {
        final /* synthetic */ Container val$Parent;

        9(Container container) {
            this.val$Parent = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            B3GUiManager.this.stateMachine.findBtnTransferValidation(AddOvp.cntTransfertForm).setHidden(false);
            Container container = this.val$Parent;
            container.replace((Container) container.getComponentAt(0), CihMBanking.sesPMTR.getSessionSavedContainer(), CommonTransitions.createSlide(0, true, 0));
            B3GUiManager.this.btnBack.getListeners().clear();
            B3GUiManager.this.btnBack.addActionListener(new 1());
        }

        class 1 implements ActionListener {
            1() {
            }

            public void actionPerformed(ActionEvent actionEvent) {
                B3GUiManager.this.GoBack();
            }
        }
    }

    class 10 implements ActionListener {
        final /* synthetic */ B3GCIHEcode val$eCode1;
        final /* synthetic */ B3gService val$service;

        10(B3gService b3gService, B3GCIHEcode b3GCIHEcode) {
            this.val$service = b3gService;
            this.val$eCode1 = b3GCIHEcode;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            TransactionResume transactionResume = (TransactionResume) this.val$service;
            B3GUiManager.this.df = new SimpleDateFormat("yyyy-MM-dd");
            B3GUiManager.access$102(B3GUiManager.this, this.val$eCode1.getValue());
            transactionResume.PassHB = B3GUiManager.access$100(B3GUiManager.this);
            transactionResume.CINHB = " ";
            if (B3GUiManager.access$100(B3GUiManager.this).length() != 4) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(B3GUiManager.this.stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            try {
                ServiceResponse access$200 = B3GUiManager.access$200(B3GUiManager.this, transactionResume);
                if (access$200.getStatusCode().equals("000")) {
                    B3GUiManager b3GUiManager = B3GUiManager.this;
                    b3GUiManager.ShowMessageTransactionOvp1(b3GUiManager.stateMachine, "Opération effectuée avec succès", null);
                } else {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(B3GUiManager.this.stateMachine, DataTools.FormatUnicode(access$200.getStatusLabel()), null);
                }
            } catch (Exception unused) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(B3GUiManager.this.stateMachine, DataTools.FormatUnicode("Une erreur est survenue, Veuillez réessayer plus tard."), null);
            }
        }
    }

    public void ShowMessageTransactionOvp1(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMessage");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            stateMachine.findSpanLabel(createContainer).setText(str);
            stateMachine.findLabel1(createContainer).setText(str2);
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 11(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 11 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        11(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            B3GUiManager.this.NavigateToPageById(46);
        }
    }

    private ServiceResponse OvpConfirmation(TransactionResume transactionResume) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical);
        hashtable.put("amount", transactionResume.Amount);
        hashtable.put("creditAccount", transactionResume.CreditedAccountNumber);
        if (transactionResume.CreditedAccountNumber.startsWith("230")) {
            hashtable.put("intraBank", true);
        } else {
            hashtable.put("intraBank", false);
        }
        hashtable.put("contractReference", "");
        hashtable.put("creditAccountTitle", transactionResume.CreditedAccountAlias);
        hashtable.put("debitAccount", transactionResume.IssuerAccountNumber.substring(6, transactionResume.IssuerAccountNumber.length() - 2));
        hashtable.put("motif", transactionResume.Motif);
        hashtable.put("periodicity", transactionResume.frequencePickerforOvp);
        hashtable.put("nextExecutionID", 0);
        hashtable.put("openingDate", this.df.format(new Date()));
        hashtable.put("state", "001");
        hashtable.put("nextExecutionDate", transactionResume.dateExcPickerForOvp);
        hashtable.put("deadlineDate", transactionResume.dateFinPickerForOvp);
        hashtable.put("PASSID", transactionResume.PassHB);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        serviceRequest.setServiceId(10699);
        serviceRequest.setIsDemo(0);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }

    public void ShowDialogSendCheckLcnService(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2, int i2, String str) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, null);
            for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupSendCheckBtn(dialog, i, b3gService, i2, str));
            stateMachine.findLblpopupItemTitleV2(createContainer).setText("Détail de l'image");
            try {
                stateMachine.findLblpopupItemValueV2(createContainer).setText(GetPopupItemTitleValue(i, b3gService2));
                createContainer.setPreferredH(CihMBanking.sesPMTR.displayHeight);
                createContainer.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
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
                dialog.addComponent("Center", createContainer);
                dialog.showPacked("Center", true);
            } catch (IOException unused) {
            }
        } catch (IOException unused2) {
        }
    }

    public void ShowDialogMTCService(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2, int i2, String str) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, b3gService2);
            for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupMTCConfirmationBtn(dialog, i, b3gService, i2, str));
            stateMachine.findLblpopupItemTitleV2(createContainer).setText(CihMBanking.sesPMTR.getSelectedMTCCreancier().NomCreancier);
            stateMachine.findLblpopupItemValueV2(createContainer).setText(CihMBanking.sesPMTR.getSelectedMTCCreance().NomCreance);
            createContainer.setPreferredH(CihMBanking.sesPMTR.displayHeight);
            createContainer.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
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
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    public void ShowDialogMTCResumeService(int i, B3gService b3gService, MTCCreancier mTCCreancier, StateMachine stateMachine, B3gService b3gService2, int i2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, b3gService2);
            for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            Container container = new Container(new BoxLayout(2));
            SpanLabel spanLabel = new SpanLabel("Votre opération a bien été effectuée");
            spanLabel.setFocusable(false);
            spanLabel.setScrollVisible(false);
            spanLabel.setUIID("dg_splblpopup");
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblPopUpDemoMTC_BOLD");
            spanLabel.setIconUIID("g_cntEmpty");
            SpanLabel spanLabel2 = new SpanLabel("Vous pouvez téléchargez votre reçu au niveau");
            spanLabel2.setFocusable(false);
            spanLabel2.setScrollVisible(false);
            spanLabel2.setUIID("dg_splblpopup");
            spanLabel2.setIconPosition("West");
            spanLabel2.setTextUIID("dg_lblPopUpDemoMTC1");
            spanLabel2.setIconUIID("g_cntEmpty");
            SpanLabel spanLabel3 = new SpanLabel("de l’historique des paiements");
            spanLabel3.setFocusable(false);
            spanLabel3.setScrollVisible(false);
            spanLabel3.setUIID("dg_splblpopup");
            spanLabel3.setIconPosition("West");
            spanLabel3.setTextUIID("dg_lblPopUpDemoMTC1");
            spanLabel3.setIconUIID("g_cntEmpty");
            container.addComponent(spanLabel);
            container.addComponent(spanLabel2);
            container.addComponent(spanLabel3);
            container.getAllStyles().setMarginUnit(1);
            container.getAllStyles().setMargin(0.0f, 1.3f, 0.0f, 0.0f);
            stateMachine.findCntPopupBody(createContainer).addComponent(container);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupMTCResumeBtn(dialog, i, b3gService, mTCCreancier, i2));
            try {
                stateMachine.findLblpopupItemTitleV2(createContainer).setText(CihMBanking.sesPMTR.getSelectedMTCCreancier().NomCreancier);
                stateMachine.findLblpopupItemValueV2(createContainer).setText(CihMBanking.sesPMTR.getSelectedMTCCreance().NomCreance);
            } catch (Exception unused) {
                stateMachine.findLblpopupItemTitleV2(createContainer).setText("paiement global des factures");
                stateMachine.findLblpopupItemValueV2(createContainer).setText("");
            }
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused2) {
        }
    }

    public void ShowDialogMTCRegroupementResumeService(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2, int i2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, b3gService2);
            for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            Container container = new Container(new BoxLayout(2));
            SpanLabel spanLabel = new SpanLabel("Votre opération a bien été effectuée");
            spanLabel.setFocusable(false);
            spanLabel.setScrollVisible(false);
            spanLabel.setUIID("dg_splblpopup");
            spanLabel.setIconPosition("West");
            spanLabel.setTextUIID("dg_lblPopUpDemoMTC_BOLD");
            spanLabel.setIconUIID("g_cntEmpty");
            SpanLabel spanLabel2 = new SpanLabel("Vous pouvez téléchargez votre reçu au niveau");
            spanLabel2.setFocusable(false);
            spanLabel2.setScrollVisible(false);
            spanLabel2.setUIID("dg_splblpopup");
            spanLabel2.setIconPosition("West");
            spanLabel2.setTextUIID("dg_lblPopUpDemoMTC1");
            spanLabel2.setIconUIID("g_cntEmpty");
            SpanLabel spanLabel3 = new SpanLabel("de l’historique des paiements");
            spanLabel3.setFocusable(false);
            spanLabel3.setScrollVisible(false);
            spanLabel3.setUIID("dg_splblpopup");
            spanLabel3.setIconPosition("West");
            spanLabel3.setTextUIID("dg_lblPopUpDemoMTC1");
            spanLabel3.setIconUIID("g_cntEmpty");
            container.addComponent(spanLabel);
            container.addComponent(spanLabel2);
            container.addComponent(spanLabel3);
            container.getAllStyles().setMarginUnit(1);
            container.getAllStyles().setMargin(0.0f, 1.3f, 0.0f, 0.0f);
            stateMachine.findCntPopupBody(createContainer).addComponent(container);
            try {
                stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupMTCRegroupmResumeBtn(dialog, i, b3gService, i2));
                try {
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText(CihMBanking.sesPMTR.getSelectedMTCCreancier().NomCreancier);
                    stateMachine.findLblpopupItemValueV2(createContainer).setText(CihMBanking.sesPMTR.getSelectedMTCCreance().NomCreance);
                } catch (Exception unused) {
                    stateMachine.findLblpopupItemTitleV2(createContainer).setText("paiement global des factures");
                    stateMachine.findLblpopupItemValueV2(createContainer).setText("");
                }
                createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
                createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
                stateMachine.findCntPopupBody(createContainer).setScrollableY(true);
                stateMachine.findCntPopupBody(createContainer).setScrollVisible(false);
                dialog.addComponent("Center", createContainer);
                dialog.showPacked("Center", true);
            } catch (IOException unused2) {
            }
        } catch (IOException unused3) {
        }
    }

    public void ShowDialogCheckBookDemandService(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2, int i2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, null);
            for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            if (i == 22) {
                stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupCheckBookBtn(dialog, i, b3gService, i2));
            } else {
                stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupLcnBookBtn(dialog, i, b3gService, i2));
            }
            stateMachine.findLblpopupItemTitleV2(createContainer).setText("Demande : ");
            if (i == 22) {
                stateMachine.findLblpopupItemValueV2(createContainer).setText("Chequier");
            } else {
                stateMachine.findLblpopupItemValueV2(createContainer).setText("LCN");
            }
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0086 A[Catch: IOException -> 0x0100, TryCatch #0 {IOException -> 0x0100, blocks: (B:3:0x000f, B:4:0x0027, B:6:0x002d, B:8:0x003d, B:18:0x0074, B:19:0x0097, B:23:0x007c, B:24:0x0086, B:25:0x008e), top: B:2:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008e A[Catch: IOException -> 0x0100, TryCatch #0 {IOException -> 0x0100, blocks: (B:3:0x000f, B:4:0x0027, B:6:0x002d, B:8:0x003d, B:18:0x0074, B:19:0x0097, B:23:0x007c, B:24:0x0086, B:25:0x008e), top: B:2:0x000f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void ShowDialogCardOpperationService(int r10, com.b3g.services.B3gService r11, userclasses.StateMachine r12, com.b3g.services.B3gService r13, int r14) {
        /*
            r9 = this;
            java.lang.String r13 = "Center"
            com.codename1.ui.Dialog r0 = new com.codename1.ui.Dialog
            r0.<init>()
            com.codename1.ui.layouts.BorderLayout r1 = new com.codename1.ui.layouts.BorderLayout
            r1.<init>()
            r0.setLayout(r1)
            java.lang.String r1 = "/cihv3.res"
            com.codename1.ui.util.Resources r6 = com.codename1.ui.util.Resources.open(r1)     // Catch: java.io.IOException -> L100
            java.lang.String r1 = "PopUp"
            com.codename1.ui.Container r1 = r12.createContainer(r6, r1)     // Catch: java.io.IOException -> L100
            r8 = 0
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r7 = r1
            java.util.ArrayList r2 = r2.GetPopupItemDetail(r3, r4, r5, r6, r7, r8)     // Catch: java.io.IOException -> L100
            r3 = 0
            r4 = r3
        L27:
            int r5 = r2.size()     // Catch: java.io.IOException -> L100
            if (r4 >= r5) goto L3d
            com.codename1.ui.Container r5 = r12.findCntPopupBody(r1)     // Catch: java.io.IOException -> L100
            java.lang.Object r6 = r2.get(r4)     // Catch: java.io.IOException -> L100
            com.codename1.ui.Component r6 = (com.codename1.ui.Component) r6     // Catch: java.io.IOException -> L100
            r5.addComponent(r6)     // Catch: java.io.IOException -> L100
            int r4 = r4 + 1
            goto L27
        L3d:
            com.codename1.ui.Label r2 = new com.codename1.ui.Label     // Catch: java.io.IOException -> L100
            java.lang.String r4 = " "
            r2.<init>(r4)     // Catch: java.io.IOException -> L100
            java.lang.String r4 = "dg_lblPopUpDemo"
            r2.setUIID(r4)     // Catch: java.io.IOException -> L100
            r4 = 4
            r2.setVerticalAlignment(r4)     // Catch: java.io.IOException -> L100
            com.codename1.ui.Container r4 = r12.findCntPopupBody(r1)     // Catch: java.io.IOException -> L100
            r4.addComponent(r2)     // Catch: java.io.IOException -> L100
            com.codename1.ui.Container r2 = r12.findCntPopupBody(r1)     // Catch: java.io.IOException -> L100
            com.codename1.ui.Container r4 = r9.GetPopupCardOpperationBtnNew(r0, r10, r11, r14)     // Catch: java.io.IOException -> L100
            r2.addComponent(r4)     // Catch: java.io.IOException -> L100
            r2 = 10800(0x2a30, float:1.5134E-41)
            if (r14 == r2) goto L8e
            r2 = 10811(0x2a3b, float:1.515E-41)
            java.lang.String r4 = "Déblocage"
            if (r14 == r2) goto L86
            r2 = 10825(0x2a49, float:1.5169E-41)
            if (r14 == r2) goto L7c
            r2 = 10828(0x2a4c, float:1.5173E-41)
            if (r14 == r2) goto L86
            switch(r14) {
                case 10802: goto L8e;
                case 10803: goto L86;
                case 10804: goto L8e;
                case 10805: goto L86;
                case 10806: goto L8e;
                case 10807: goto L86;
                default: goto L74;
            }
        L74:
            com.codename1.ui.Label r14 = r12.findLblpopupItemTitleV2(r1)     // Catch: java.io.IOException -> L100
            r14.setText(r4)     // Catch: java.io.IOException -> L100
            goto L97
        L7c:
            com.codename1.ui.Label r14 = r12.findLblpopupItemTitleV2(r1)     // Catch: java.io.IOException -> L100
            java.lang.String r2 = "Déblocage paiement internet"
            r14.setText(r2)     // Catch: java.io.IOException -> L100
            goto L97
        L86:
            com.codename1.ui.Label r14 = r12.findLblpopupItemTitleV2(r1)     // Catch: java.io.IOException -> L100
            r14.setText(r4)     // Catch: java.io.IOException -> L100
            goto L97
        L8e:
            com.codename1.ui.Label r14 = r12.findLblpopupItemTitleV2(r1)     // Catch: java.io.IOException -> L100
            java.lang.String r2 = "Blocage"
            r14.setText(r2)     // Catch: java.io.IOException -> L100
        L97:
            com.codename1.ui.Label r12 = r12.findLblpopupItemValueV2(r1)     // Catch: java.io.IOException -> L100
            java.lang.String r10 = r9.GetPopupItemTitleValue(r10, r11)     // Catch: java.io.IOException -> L100
            r12.setText(r10)     // Catch: java.io.IOException -> L100
            com.codename1.ui.Display r10 = com.codename1.ui.Display.getInstance()     // Catch: java.io.IOException -> L100
            int r10 = r10.getDisplayHeight()     // Catch: java.io.IOException -> L100
            r1.setPreferredH(r10)     // Catch: java.io.IOException -> L100
            com.codename1.ui.Display r10 = com.codename1.ui.Display.getInstance()     // Catch: java.io.IOException -> L100
            int r10 = r10.getDisplayWidth()     // Catch: java.io.IOException -> L100
            r1.setPreferredW(r10)     // Catch: java.io.IOException -> L100
            r0.setAlwaysTensile(r3)     // Catch: java.io.IOException -> L100
            r0.setDraggable(r3)     // Catch: java.io.IOException -> L100
            r0.setScrollable(r3)     // Catch: java.io.IOException -> L100
            r0.setTactileTouch(r3)     // Catch: java.io.IOException -> L100
            r0.setTensileDragEnabled(r3)     // Catch: java.io.IOException -> L100
            com.codename1.ui.plaf.Style r10 = r0.getDialogStyle()     // Catch: java.io.IOException -> L100
            r11 = 230(0xe6, float:3.22E-43)
            r10.setBgTransparency(r11)     // Catch: java.io.IOException -> L100
            com.codename1.ui.plaf.Style r10 = r0.getDialogStyle()     // Catch: java.io.IOException -> L100
            r11 = 1118481(0x111111, float:1.567326E-39)
            r10.setBgColor(r11)     // Catch: java.io.IOException -> L100
            com.codename1.ui.plaf.Style r10 = r0.getStyle()     // Catch: java.io.IOException -> L100
            r10.setPadding(r3, r3, r3, r3)     // Catch: java.io.IOException -> L100
            com.codename1.ui.plaf.Style r10 = r0.getStyle()     // Catch: java.io.IOException -> L100
            r10.setMargin(r3, r3, r3, r3)     // Catch: java.io.IOException -> L100
            com.codename1.ui.plaf.Style r10 = r0.getDialogStyle()     // Catch: java.io.IOException -> L100
            r10.setPadding(r3, r3, r3, r3)     // Catch: java.io.IOException -> L100
            com.codename1.ui.plaf.Style r10 = r0.getDialogStyle()     // Catch: java.io.IOException -> L100
            r10.setMargin(r3, r3, r3, r3)     // Catch: java.io.IOException -> L100
            r0.revalidate()     // Catch: java.io.IOException -> L100
            r0.addComponent(r13, r1)     // Catch: java.io.IOException -> L100
            r10 = 1
            r0.showPacked(r13, r10)     // Catch: java.io.IOException -> L100
        L100:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.B3GUiManager.ShowDialogCardOpperationService(int, com.b3g.services.B3gService, userclasses.StateMachine, com.b3g.services.B3gService, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void ShowDialogCardOpperationADOService(int r19, com.b3g.services.B3gService r20, userclasses.StateMachine r21, com.b3g.services.B3gService r22, int r23) {
        /*
            Method dump skipped, instructions count: 493
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.B3GUiManager.ShowDialogCardOpperationADOService(int, com.b3g.services.B3gService, userclasses.StateMachine, com.b3g.services.B3gService, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void ShowDialogBDCardNew(int r20, com.b3g.services.B3gService r21, userclasses.StateMachine r22, int r23) {
        /*
            Method dump skipped, instructions count: 504
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.B3GUiManager.ShowDialogBDCardNew(int, com.b3g.services.B3gService, userclasses.StateMachine, int):void");
    }

    public void ShowDialogRIBOpperationService(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2, int i2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, null);
            for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupRIBOpperationBtn(dialog, i, b3gService, i2));
            stateMachine.findLblpopupItemTitleV2(createContainer).setText("Demande RIB");
            stateMachine.findLblpopupItemValueV2(createContainer).setText(GetPopupItemTitleValue(i, b3gService));
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    public void ShowDialogFavoriteDeleteOpperationService(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2, int i2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, null);
            for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupMTCFavoriteBillOpperationBtn(dialog, i, b3gService, i2));
            stateMachine.findLblpopupItemTitleV2(createContainer).setText("Supprimer un favori");
            stateMachine.findLblpopupItemValueV2(createContainer).setText(GetPopupItemTitleValue(i, b3gService));
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    public void ShowDialogFavoriteDeleteOpperationServiceNew(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2, int i2, int i3) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, null);
            for (int i4 = 0; i4 < GetPopupItemDetail.size(); i4++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i4));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupMTCFavoriteBillOpperationBtnNew(dialog, i, b3gService, i2, i3));
            stateMachine.findLblpopupItemTitleV2(createContainer).setText("Supprimer un favori");
            stateMachine.findLblpopupItemValueV2(createContainer).setText(GetPopupItemTitleValue(i, b3gService));
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    public void ShowMessageTouch(StateMachine stateMachine, String str, String str2, SuccessCallback successCallback) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMessage");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            stateMachine.findSpanLabel(createContainer).setText(str);
            stateMachine.findLabel1(createContainer).setText(str2);
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 12(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 12 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        12(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public void ShowMessageTransaction(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMessage");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            stateMachine.findSpanLabel(createContainer).setText(str);
            stateMachine.findLabel1(createContainer).setText(str2);
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 13(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
        Settings.setNightMode(dialog, 0);
    }

    class 13 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        13(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public void ShowMessageTransactionGo(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMessage");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            stateMachine.findSpanLabel(createContainer).setText(str);
            stateMachine.findLabel1(createContainer).setText(str2);
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 14(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
        Settings.setNightMode(dialog, 0);
    }

    class 14 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        14(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            B3GUiManager.this.NavigateToPageById(1);
        }
    }

    public void ShowLastMessageTransactionTransfert(B3gService b3gService, StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "PopUpMessage");
        Container container = (Container) uIBuilder.findByName("CntPopDemoConfirmRightBloc", createContainer);
        container.removeAll();
        TableLayout tableLayout = new TableLayout(1, 2);
        container.setLayout(tableLayout);
        container.addComponent(tableLayout.createConstraint().widthPercentage(50), GetPopupShareVirement(dialog, b3gService, 1));
        container.addComponent(tableLayout.createConstraint().widthPercentage(50), GetPopupCloseVirement(dialog));
        createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
        createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
        stateMachine.findSpanLabel(createContainer).setText(str);
        stateMachine.findLabel1(createContainer).setText(str2);
        dialog.addComponent("Center", createContainer);
        dialog.showPacked("Center", true);
        Settings.setNightMode(dialog, 0);
    }

    public void ShowMessageEDocument(StateMachine stateMachine, String str, String str2, String str3) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMessage");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            if (str3.equals("999")) {
                stateMachine.findSpanLabel(createContainer).setText("Aucun resultat trouvé pour le mois");
                stateMachine.findLabel2(createContainer).setText(DataTools.getMonth(str));
                stateMachine.findLabel(createContainer).setText(str.substring(0, 4));
            } else if (str3.equals("000")) {
                stateMachine.findSpanLabel(createContainer).setText("Votre Document a bien été transféré à l'adresse:");
                stateMachine.findLabel2(createContainer).setText(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().email);
            } else {
                stateMachine.findSpanLabel(createContainer).setText(str);
                stateMachine.findLabel1(createContainer).setText(str2);
            }
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 15(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 15 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        15(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public void ShowMessageFunds(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMessage");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            stateMachine.findSpanLabel(createContainer).setText("Veuillez entrer une date de disponibilité valide entre le ");
            stateMachine.findLabel2(createContainer).setText(str);
            stateMachine.findLabel(createContainer).setText(" et le ");
            stateMachine.findLabel21(createContainer).setText(str2);
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 16(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 16 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        16(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public void ShowMessageCihExpressTransaction(StateMachine stateMachine, String str, String str2, String str3, String str4) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            UIBuilder uIBuilder = new UIBuilder();
            Container createContainer = uIBuilder.createContainer("/cihv3", "PopUpMessage");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            stateMachine.findSpanLabel(createContainer).setText(str4);
            if (str3.equals("000")) {
                Container container = (Container) uIBuilder.findByName("CntPopDemoConfirmRightBloc", createContainer);
                container.removeAll();
                TableLayout tableLayout = new TableLayout(1, 2);
                container.setLayout(tableLayout);
                container.addComponent(tableLayout.createConstraint().widthPercentage(50), GetShareBtnCnt(dialog));
                container.addComponent(tableLayout.createConstraint().widthPercentage(50), GetPopupCloseVirement(dialog));
            } else {
                stateMachine.findBtnNoIcon(createContainer).addActionListener(new 17(dialog));
            }
            stateMachine.findLabel(createContainer).setText(str);
            stateMachine.findLabel(createContainer).setHidden(true);
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (Exception unused) {
        }
    }

    class 17 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        17(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public void ShowMessageAndBack(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMessage");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            stateMachine.findSpanLabel(createContainer).setText(str);
            stateMachine.findLabel1(createContainer).setText(str2);
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 18(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 18 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        18(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            B3GUiManager.this.GoBack();
        }
    }

    public void ShowMessageModel1(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMessage");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            stateMachine.findSpanLabel(createContainer).setText(str);
            stateMachine.findLabel1(createContainer).setText(str2);
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 19(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 19 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        19(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public void ShowUpdateMessageTransaction(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "UpdatePopUp");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            str.equals("");
            stateMachine.findLabel1(createContainer).setText(str2);
            stateMachine.findUpdateBtn(createContainer).addActionListener(new 20());
            stateMachine.findBtnCancel(createContainer).addActionListener(new 21(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 20 implements ActionListener {
        20() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().execute("http://smarturl.it/Cihonline");
        }
    }

    class 21 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        21(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            Display.getInstance().exitApplication();
        }
    }

    public void ShowDialogAdviserCall(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2, int i2, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, null);
            for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupCallAdviserBtn(dialog, i, b3gService, i2, str));
            stateMachine.findLblpopupItemTitleV2(createContainer).setText("Appel vers votre ");
            stateMachine.findLblpopupItemValueV2(createContainer).setText(str2);
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    public void ShowDialogCardOpperationService2(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2, String str) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            Container container = (Container) this.uib.findByName("cntPopUpLeftLabel", createContainer);
            SpanLabel spanLabel = new SpanLabel(str);
            Container container2 = new Container();
            spanLabel.setTextUIID("dg_lblPopUpConfirm");
            container2.setUIID("EmptyContainer");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, null);
            for (int i2 = 0; i2 < GetPopupItemDetail.size(); i2++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i2));
            }
            Label label = new Label();
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupCardOpperationBtnNew2(dialog, i, b3gService));
            stateMachine.findLblpopupItemTitleV2(createContainer).setText("Veuillez confirmer la commande de votre carte virtuelle");
            stateMachine.findLblpopupItemValueV2(createContainer).setText(GetPopupItemTitleValue(i, b3gService));
            container.removeAll();
            container.add(BoxLayout.encloseY(container2, spanLabel));
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public ArrayList GetPopupItemDetail(int i, B3gService b3gService, StateMachine stateMachine, Resources resources, Container container, B3gService b3gService2) {
        String str;
        String str2;
        String str3;
        ArrayList arrayList = new ArrayList();
        new DataTools();
        if (i == 5) {
            CardOperation cardOperation = (CardOperation) b3gService;
            Container createContainer = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer).setText("Montant Opération : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer).setText(cardOperation.transactionAmount + " " + cardOperation.transactionCurrency);
            arrayList.add(createContainer);
            Container createContainer2 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer2).setText("Date Opération : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer2).setText(cardOperation.transactionDate.substring(0, 10));
            arrayList.add(createContainer2);
            Container createContainer3 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer3).setText("Lieu opération : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer3).setText(cardOperation.place);
            arrayList.add(createContainer3);
            Container createContainer4 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer4).setText("Nature opération : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer4).setText(cardOperation.operationType);
            arrayList.add(createContainer4);
        } else if (i == 19) {
            MyCheckLcn myCheckLcn = (MyCheckLcn) b3gService;
            Container createContainer5 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer5).setText("N° Compte : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer5).setText(myCheckLcn.accountNumber);
            arrayList.add(createContainer5);
            Container createContainer6 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer6).setText("N° Remise : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer6).setText(myCheckLcn.remiseNum);
            arrayList.add(createContainer6);
            Container createContainer7 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer7).setText("Date Opération : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer7).setText(myCheckLcn.processingDate);
            arrayList.add(createContainer7);
            Container createContainer8 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer8).setText("Montant : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer8).setText(myCheckLcn.amount);
            arrayList.add(createContainer8);
            Container createContainer9 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer9).setText("N° Chèque : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer9).setText(myCheckLcn.checkNum);
            arrayList.add(createContainer9);
            if (myCheckLcn.partnerClientName != null) {
                Container createContainer10 = stateMachine.createContainer(resources, "PopupItem2");
                stateMachine.findLblPopupDetailItemTitle(createContainer10).setText("Nom du Tiré : ");
                stateMachine.findLblPopupDetailItemValueSP(createContainer10).setText(myCheckLcn.partnerClientName.trim());
                arrayList.add(createContainer10);
            }
            if (myCheckLcn.partnerBankName != null) {
                Container createContainer11 = stateMachine.createContainer(resources, "PopupItem2");
                stateMachine.findLblPopupDetailItemTitle(createContainer11).setText("Banque : ");
                stateMachine.findLblPopupDetailItemValueSP(createContainer11).setText(myCheckLcn.partnerBankName.trim());
                arrayList.add(createContainer11);
            }
            if (myCheckLcn.beneficiaryName != null) {
                Container createContainer12 = stateMachine.createContainer(resources, "PopupItem2");
                stateMachine.findLblPopupDetailItemTitle(createContainer12).setText("Bénéficiaire : ");
                stateMachine.findLblPopupDetailItemValueSP(createContainer12).setText(myCheckLcn.beneficiaryName.trim());
                arrayList.add(createContainer12);
            }
            Container createContainer13 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer13).setText("Sort : ");
            myCheckLcn.getPalinStatusFromBrief(stateMachine.findLblPopupDetailItemValueSP(createContainer13), myCheckLcn.status);
            arrayList.add(createContainer13);
        } else if (i == 22) {
            CheckbookDemand checkbookDemand = (CheckbookDemand) b3gService;
            Container createContainer14 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer14).setText("N°: ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer14).setText(checkbookDemand.AccountNumber);
            arrayList.add(createContainer14);
            Container createContainer15 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer15).setText("Nombre de Feuilles : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer15).setText(checkbookDemand.PageNumber);
            arrayList.add(createContainer15);
            Container createContainer16 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer16).setText("Quantité : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer16).setText(checkbookDemand.Quantity);
            arrayList.add(createContainer16);
            if (checkbookDemand.Date != null) {
                Container createContainer17 = stateMachine.createContainer(resources, "PopupItem2");
                stateMachine.findLblPopupDetailItemTitle(createContainer17).setText("Date : ");
                stateMachine.findLblPopupDetailItemValueSP(createContainer17).setText(checkbookDemand.Date);
                arrayList.add(createContainer17);
            }
        } else if (i == 32) {
            MTCFavoriteBill mTCFavoriteBill = (MTCFavoriteBill) b3gService;
            Container createContainer18 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer18).setText("Créancier : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer18).setText(mTCFavoriteBill.MTCCreancier.NomCreancier);
            arrayList.add(createContainer18);
            Container createContainer19 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer19).setText("Créance : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer19).setText(mTCFavoriteBill.MTCCreance.NomCreance);
            arrayList.add(createContainer19);
            Container createContainer20 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer20).setText("Libellé : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer20).setText(mTCFavoriteBill.Alias);
            arrayList.add(createContainer20);
            if (mTCFavoriteBill.Identifiant != "") {
                Container createContainer21 = stateMachine.createContainer(resources, "PopupItem2");
                stateMachine.findLblPopupDetailItemTitle(createContainer21).setText("Identifiant : ");
                stateMachine.findLblPopupDetailItemValueSP(createContainer21).setText(mTCFavoriteBill.Identifiant);
                arrayList.add(createContainer21);
            }
        } else if (i == 88) {
            AgenceDemandeHistorique agenceDemandeHistorique = (AgenceDemandeHistorique) b3gService;
            Container createContainer22 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer22).setText("Date de la demande : ");
            Container createContainer23 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer23).setText("Agence actuelle : ");
            Container createContainer24 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer24).setText("Ville demandée : ");
            Container createContainer25 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer25).setText("Agence demandée : ");
            Container createContainer26 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer26).setText("Statut : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer22).setText(agenceDemandeHistorique.request_Date);
            stateMachine.findLblPopupDetailItemValueSP(createContainer23).setText(agenceDemandeHistorique.old_Agence);
            stateMachine.findLblPopupDetailItemValueSP(createContainer24).setText(agenceDemandeHistorique.city);
            stateMachine.findLblPopupDetailItemValueSP(createContainer25).setText(agenceDemandeHistorique.new_agence);
            stateMachine.findLblPopupDetailItemValueSP(createContainer26).setText(agenceDemandeHistorique.statut);
            arrayList.add(createContainer22);
            arrayList.add(createContainer23);
            arrayList.add(createContainer24);
            arrayList.add(createContainer25);
            arrayList.add(createContainer26);
        } else if (i == 95) {
            Container createContainer27 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer27).setText("Tarif : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer27).setText("33 DH TTC");
            arrayList.add(createContainer27);
            Container createContainer28 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer28).setText("Type de document : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer28).setText(((Loan) b3gService).typeDoc);
            arrayList.add(createContainer28);
            Container createContainer29 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer29).setText("");
            stateMachine.findLblPopupDetailItemValueSP(createContainer29).setText("Attention cette opération est soumise à des Frais de 33 DH TTC, voulez vous continuer ?");
            arrayList.add(createContainer29);
        } else if (i == 98) {
            TransactionResume transactionResume = (TransactionResume) b3gService;
            Container createContainer30 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer30).setText("Compte à Débiter : ");
            if (transactionResume.IssuerAccountNumber.length() == 24) {
                stateMachine.findLblPopupDetailItemValue(createContainer30).setText(transactionResume.IssuerAccountNumber.substring(6, 22));
            } else {
                stateMachine.findLblPopupDetailItemValue(createContainer30).setText(transactionResume.IssuerAccountNumber);
            }
            arrayList.add(createContainer30);
            Container createContainer31 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer31).setText("Bénéficiaire ");
            stateMachine.findLblPopupDetailItemTitle2(createContainer31).setText("(" + transactionResume.CreditedAccountAlias + ") : ");
            if (transactionResume.TypeOperation.equals("TOPUP")) {
                stateMachine.findLblPopupDetailItemValue(createContainer31).setText(StringTools.HideCardNumber(transactionResume.CreditedAccountNumber));
            } else {
                stateMachine.findLblPopupDetailItemValue(createContainer31).setText(StringTools.RibFormatWithSP(transactionResume.CreditedAccountNumber));
            }
            arrayList.add(createContainer31);
            Container createContainer32 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer32).setText("Montant (MAD) : ");
            stateMachine.findLblPopupDetailItemValue(createContainer32).setText(transactionResume.Amount);
            arrayList.add(createContainer32);
            Container createContainer33 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer33).setText("Motif : ");
            stateMachine.findLblPopupDetailItemValue(createContainer33).setText(StringTools.ToUpperCase(transactionResume.Motif));
            arrayList.add(createContainer33);
        } else if (i == 121) {
            AccountAutorisation accountAutorisation = (AccountAutorisation) b3gService;
            Container createContainer34 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer34).setText("Montant Autorisation : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer34).setText(accountAutorisation.montant);
            arrayList.add(createContainer34);
            Container createContainer35 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer35).setText("Date d'effet : ");
            if (accountAutorisation.dateEff.length() > 10) {
                stateMachine.findLblPopupDetailItemValueSP(createContainer35).setText(accountAutorisation.dateEff.substring(0, 10));
            } else {
                stateMachine.findLblPopupDetailItemValueSP(createContainer35).setText(accountAutorisation.dateEff);
            }
            arrayList.add(createContainer35);
            Container createContainer36 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer36).setText("Date d'échéance : ");
            if (accountAutorisation.dateEchenance.length() > 0) {
                stateMachine.findLblPopupDetailItemValueSP(createContainer36).setText(accountAutorisation.dateEchenance.substring(0, 10));
            } else {
                stateMachine.findLblPopupDetailItemValueSP(createContainer36).setText(accountAutorisation.dateEchenance);
            }
            arrayList.add(createContainer36);
        } else if (i == 75) {
            GetDotation getDotation = (GetDotation) b3gService;
            arrayList.add(createItemDetail("Agence CIH BANK : ", getDotation.agency, resources, stateMachine));
            arrayList.add(createItemDetail("Date de change : ", getDotation.operationDate, resources, stateMachine));
            arrayList.add(createItemDetail("Montant en MAD : ", getDotation.madAmount + " MAD", resources, stateMachine));
            arrayList.add(createItemDetail("Devise : ", getDotation.currency, resources, stateMachine));
            arrayList.add(createItemDetail("Cours : ", getDotation.exchangeRate, resources, stateMachine));
            arrayList.add(createItemDetail("Montant en devise : ", getDotation.currencyAmount, resources, stateMachine));
        } else if (i == 76) {
            ResponseDotation.LieuRecharges lieuRecharges = (ResponseDotation.LieuRecharges) b3gService;
            arrayList.add(createItemDetail("Lieu de l'opération : ", lieuRecharges.Lieu, resources, stateMachine));
            arrayList.add(createItemDetail("Date de l'opération : ", lieuRecharges.Date, resources, stateMachine));
            arrayList.add(createItemDetail("Montant en devise : ", lieuRecharges.MontantDev, resources, stateMachine));
            arrayList.add(createItemDetail("Cours : ", lieuRecharges.Cours, resources, stateMachine));
            arrayList.add(createItemDetail("Montant en MAD : ", lieuRecharges.MontantMad + " MAD", resources, stateMachine));
        } else if (i == 85) {
            TransactionResume transactionResume2 = (TransactionResume) b3gService;
            Container createContainer37 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer37).setText("Compte à Débiter : ");
            if (transactionResume2.IssuerAccountNumber.length() == 24) {
                str = "";
                stateMachine.findLblPopupDetailItemValue(createContainer37).setText(transactionResume2.IssuerAccountNumber.substring(6, 22));
            } else {
                str = "";
                stateMachine.findLblPopupDetailItemValue(createContainer37).setText(transactionResume2.IssuerAccountNumber);
            }
            arrayList.add(createContainer37);
            Container createContainer38 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer38).setText("Bénéficiaire ");
            stateMachine.findLblPopupDetailItemTitle2(createContainer38).setText("(" + transactionResume2.CreditedAccountAlias + ") : ");
            if (transactionResume2.TypeOperation.equals("TOPUP")) {
                stateMachine.findLblPopupDetailItemValue(createContainer38).setText(transactionResume2.phoneNumber);
            } else {
                stateMachine.findLblPopupDetailItemValue(createContainer38).setText(transactionResume2.phoneNumber);
            }
            arrayList.add(createContainer38);
            Container createContainer39 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer39).setText("Montant (MAD) : ");
            stateMachine.findLblPopupDetailItemValue(createContainer39).setText(transactionResume2.Amount);
            arrayList.add(createContainer39);
            Container createContainer40 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer40).setText("Motif : ");
            stateMachine.findLblPopupDetailItemValue(createContainer40).setText(StringTools.ToUpperCase(transactionResume2.Motif));
            arrayList.add(createContainer40);
            String GetFeeAmountMsgFromSession = GetFeeAmountMsgFromSession(transactionResume2.TypeOperation, transactionResume2.CreditedAccountNumber);
            String str4 = str;
            if (!GetFeeAmountMsgFromSession.equals(str4)) {
                Container createContainer41 = stateMachine.createContainer(resources, "PopupItem");
                stateMachine.findLblPopupDetailItemTitle(createContainer41).setText(str4);
                stateMachine.findLblPopupDetailItemValue(createContainer41).setText(GetFeeAmountMsgFromSession);
                arrayList.add(createContainer41);
            }
        } else if (i == 86) {
            TransactionResume transactionResume3 = (TransactionResume) b3gService;
            Container createContainer42 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer42).setText("Compte à Débiter : ");
            if (transactionResume3.IssuerAccountNumber.length() == 24) {
                str2 = "";
                stateMachine.findLblPopupDetailItemValue(createContainer42).setText(transactionResume3.IssuerAccountNumber.substring(6, 22));
            } else {
                str2 = "";
                stateMachine.findLblPopupDetailItemValue(createContainer42).setText(transactionResume3.IssuerAccountNumber);
            }
            arrayList.add(createContainer42);
            Container createContainer43 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer43).setText("Bénéficiaire ");
            stateMachine.findLblPopupDetailItemTitle2(createContainer43).setText("(" + transactionResume3.CreditedAccountAlias + ") : ");
            if (transactionResume3.TypeOperation.equals("TOPUP")) {
                stateMachine.findLblPopupDetailItemValue(createContainer43).setText(transactionResume3.phoneNumber);
            } else {
                stateMachine.findLblPopupDetailItemValue(createContainer43).setText(transactionResume3.phoneNumber);
            }
            arrayList.add(createContainer43);
            Container createContainer44 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer44).setText("CIN : ");
            stateMachine.findLblPopupDetailItemValue(createContainer44).setText(StringTools.ToUpperCase(transactionResume3.CINLanacash));
            arrayList.add(createContainer44);
            Container createContainer45 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer45).setText("Montant (MAD) : ");
            stateMachine.findLblPopupDetailItemValue(createContainer45).setText(transactionResume3.Amount);
            arrayList.add(createContainer45);
            Container createContainer46 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer46).setText("Motif : ");
            stateMachine.findLblPopupDetailItemValue(createContainer46).setText(StringTools.ToUpperCase(transactionResume3.Motif));
            arrayList.add(createContainer46);
            String GetFeeAmountMsgFromSession2 = GetFeeAmountMsgFromSession(transactionResume3.TypeOperation, transactionResume3.CreditedAccountNumber);
            String str5 = str2;
            if (!GetFeeAmountMsgFromSession2.equals(str5)) {
                Container createContainer47 = stateMachine.createContainer(resources, "PopupItem");
                stateMachine.findLblPopupDetailItemTitle(createContainer47).setText(str5);
                stateMachine.findLblPopupDetailItemValue(createContainer47).setText(GetFeeAmountMsgFromSession2);
                arrayList.add(createContainer47);
            }
        } else if (i == 90) {
            TransactionResume transactionResume4 = (TransactionResume) b3gService;
            Container createContainer48 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer48).setText("Compte à Débiter : ");
            if (transactionResume4.IssuerAccountNumber.length() == 24) {
                stateMachine.findLblPopupDetailItemValue(createContainer48).setText(transactionResume4.IssuerAccountNumber.substring(6, 22));
            } else {
                stateMachine.findLblPopupDetailItemValue(createContainer48).setText(transactionResume4.IssuerAccountNumber);
            }
            arrayList.add(createContainer48);
            Container createContainer49 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer49).setText("Bénéficiaire ");
            stateMachine.findLblPopupDetailItemTitle2(createContainer49).setText("(" + transactionResume4.CreditedAccountAlias + ") : ");
            if (transactionResume4.TypeOperation.equals("TOPUP")) {
                stateMachine.findLblPopupDetailItemValue(createContainer49).setText(StringTools.HideCardNumber(transactionResume4.CreditedAccountNumber));
            } else {
                stateMachine.findLblPopupDetailItemValue(createContainer49).setText(StringTools.RibFormatWithSP(transactionResume4.CreditedAccountNumber));
            }
            arrayList.add(createContainer49);
            Container createContainer50 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer50).setText("Montant (MAD) : ");
            stateMachine.findLblPopupDetailItemValue(createContainer50).setText(transactionResume4.Amount);
            arrayList.add(createContainer50);
            Container createContainer51 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer51).setText("Motif : ");
            stateMachine.findLblPopupDetailItemValue(createContainer51).setText(StringTools.ToUpperCase(transactionResume4.Motif));
            arrayList.add(createContainer51);
        } else if (i != 91) {
            switch (i) {
                case 7:
                    RecurringDetail recurringDetail = (RecurringDetail) b3gService;
                    Container createContainer52 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer52).setText("Montant Prélévement : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer52).setText(recurringDetail.Amount);
                    arrayList.add(createContainer52);
                    Container createContainer53 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer53).setText("Date Prélévement : ");
                    if (recurringDetail.ProcissingDate.length() > 10) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer53).setText(recurringDetail.ProcissingDate.substring(0, 10));
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer53).setText(recurringDetail.ProcissingDate);
                    }
                    arrayList.add(createContainer53);
                    if (recurringDetail.Motif.equals("Impayé")) {
                        Container createContainer54 = stateMachine.createContainer(resources, "PopupItem2");
                        stateMachine.findLblPopupDetailItemTitle(createContainer54).setText("Motif : ");
                        stateMachine.findLblPopupDetailItemValueSP(createContainer54).setText(recurringDetail.Motif);
                        arrayList.add(createContainer54);
                        break;
                    }
                    break;
                case 8:
                    AccountOperation accountOperation = (AccountOperation) b3gService;
                    Container createContainer55 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer55).setText("Montant Opération : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer55).setText(accountOperation.montant);
                    arrayList.add(createContainer55);
                    Container createContainer56 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer56).setText("Date Opération : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer56).setText(accountOperation.date.substring(0, 10));
                    arrayList.add(createContainer56);
                    Container createContainer57 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer57).setText("Date Valeur : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer57).setText(accountOperation.accountedDate.substring(0, 10));
                    arrayList.add(createContainer57);
                    Container createContainer58 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer58).setText("Libellé : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer58).setText(accountOperation.libelle);
                    arrayList.add(createContainer58);
                    stateMachine.findLblpopupItemValueV2(container).setText(CihMBanking.sesPMTR.getCurrentAccount().accountNumber);
                    break;
                case 9:
                    Walet walet = (Walet) b3gService;
                    Container createContainer59 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer59).setText("Quantité : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer59).setText(walet.Quantity);
                    arrayList.add(createContainer59);
                    Container createContainer60 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer60).setText("CMP : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer60).setText(walet.CMP);
                    arrayList.add(createContainer60);
                    Container createContainer61 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer61).setText("Prix de revient : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer61).setText(walet.CostPrice);
                    arrayList.add(createContainer61);
                    Container createContainer62 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer62).getParent().removeComponent(stateMachine.findLblPopupDetailItemTitle(createContainer62));
                    Label label = new Label("Cours ");
                    Label label2 = new Label();
                    if (CihMBanking.RTL) {
                        label2.setText(" : " + walet.DateCurrency);
                    } else {
                        label2.setText(walet.DateCurrency + " : ");
                    }
                    label.setUIID("dg_lblPopupDetailItemTitle");
                    label2.setUIID("dg_lblPopupDetailItemTitle");
                    Container container2 = new Container(new FlowLayout(1, 4));
                    container2.addComponent(label);
                    container2.addComponent(label2);
                    createContainer62.add("West", container2);
                    stateMachine.findLblPopupDetailItemValueSP(createContainer62).setText(walet.Currency);
                    arrayList.add(createContainer62);
                    Container createContainer63 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer63).setText("Valorisation : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer63).setText(walet.Valorization);
                    arrayList.add(createContainer63);
                    Container createContainer64 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer64).setText("+/- Value Latente : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer64).setText(walet.LatentValue);
                    arrayList.add(createContainer64);
                    stateMachine.findLblpopupItemValueV2(container).setText(walet.Title);
                    break;
                case 10:
                    TransactionResume transactionResume5 = (TransactionResume) b3gService;
                    Container createContainer65 = stateMachine.createContainer(resources, "PopupItem");
                    stateMachine.findLblPopupDetailItemTitle(createContainer65).setText("Compte à Débiter : ");
                    if (transactionResume5.IssuerAccountNumber.length() == 24) {
                        str3 = "";
                        stateMachine.findLblPopupDetailItemValue(createContainer65).setText(transactionResume5.IssuerAccountNumber.substring(6, 22));
                    } else {
                        str3 = "";
                        stateMachine.findLblPopupDetailItemValue(createContainer65).setText(transactionResume5.IssuerAccountNumber);
                    }
                    arrayList.add(createContainer65);
                    Container createContainer66 = stateMachine.createContainer(resources, "PopupItem");
                    stateMachine.findLblPopupDetailItemTitle(createContainer66).setText("Bénéficiaire ");
                    stateMachine.findLblPopupDetailItemTitle2(createContainer66).setText("(" + transactionResume5.CreditedAccountAlias + ") : ");
                    if (transactionResume5.TypeOperation.equals("TOPUP")) {
                        stateMachine.findLblPopupDetailItemValue(createContainer66).setText(StringTools.HideCardNumber(transactionResume5.CreditedAccountNumber));
                    } else {
                        stateMachine.findLblPopupDetailItemValue(createContainer66).setText(StringTools.RibFormatWithSP(transactionResume5.CreditedAccountNumber));
                    }
                    arrayList.add(createContainer66);
                    Container createContainer67 = stateMachine.createContainer(resources, "PopupItem");
                    stateMachine.findLblPopupDetailItemTitle(createContainer67).setText("Montant (MAD) : ");
                    stateMachine.findLblPopupDetailItemValue(createContainer67).setText(transactionResume5.Amount);
                    arrayList.add(createContainer67);
                    Container createContainer68 = stateMachine.createContainer(resources, "PopupItem");
                    stateMachine.findLblPopupDetailItemTitle(createContainer68).setText("Motif : ");
                    stateMachine.findLblPopupDetailItemValue(createContainer68).setText(StringTools.ToUpperCase(transactionResume5.Motif));
                    arrayList.add(createContainer68);
                    String GetFeeAmountMsgFromSession3 = GetFeeAmountMsgFromSession(transactionResume5.TypeOperation, transactionResume5.CreditedAccountNumber);
                    String str6 = str3;
                    if (!GetFeeAmountMsgFromSession3.equals(str6)) {
                        Container createContainer69 = stateMachine.createContainer(resources, "PopupItem");
                        stateMachine.findLblPopupDetailItemTitle(createContainer69).setText(str6);
                        stateMachine.findLblPopupDetailItemValue(createContainer69).setText(GetFeeAmountMsgFromSession3);
                        arrayList.add(createContainer69);
                        break;
                    }
                    break;
                case 11:
                    TransferHistoric transferHistoric = (TransferHistoric) b3gService;
                    Container createContainer70 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer70).setText("N°Compte : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer70).setText(transferHistoric.AccountNumber);
                    arrayList.add(createContainer70);
                    Container createContainer71 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer71).setText("Bénéficiaire : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer71).setText(transferHistoric.BeneficiaryName);
                    arrayList.add(createContainer71);
                    if (transferHistoric.OperationType.equals("300013")) {
                        Container createContainer72 = stateMachine.createContainer(resources, "PopupItem2");
                        stateMachine.findLblPopupDetailItemTitle(createContainer72).setText("RIB : ");
                        stateMachine.findLblPopupDetailItemValueSP(createContainer72).setText(transferHistoric.Beneficiary);
                        arrayList.add(createContainer72);
                    } else if (transferHistoric.OperationType.equals("300014")) {
                        Container createContainer73 = stateMachine.createContainer(resources, "PopupItem2");
                        stateMachine.findLblPopupDetailItemTitle(createContainer73).setText("N°Carte : ");
                        stateMachine.findLblPopupDetailItemValueSP(createContainer73).setText(transferHistoric.Beneficiary);
                        arrayList.add(createContainer73);
                    }
                    if (transferHistoric.Motif.length() > 0) {
                        Container createContainer74 = stateMachine.createContainer(resources, "PopupItem2");
                        stateMachine.findLblPopupDetailItemTitle(createContainer74).setText("Motif : ");
                        stateMachine.findLblPopupDetailItemValueSP(createContainer74).setText(transferHistoric.Motif);
                        arrayList.add(createContainer74);
                    }
                    Container createContainer75 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer75).setText("Montant : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer75).setText(transferHistoric.Amount);
                    arrayList.add(createContainer75);
                    Container createContainer76 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer76).setText("Commission : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer76).setText(transferHistoric.FeeAmount);
                    arrayList.add(createContainer76);
                    Container createContainer77 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer77).setText("Date : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer77).setText(transferHistoric.OperationDate);
                    arrayList.add(createContainer77);
                    break;
                case 12:
                    AccountOperation accountOperation2 = (AccountOperation) b3gService;
                    Container createContainer78 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer78).setText("Montant Opération : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer78).setText(accountOperation2.montant);
                    arrayList.add(createContainer78);
                    Container createContainer79 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer79).setText("Date Opération : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer79).setText(accountOperation2.date);
                    arrayList.add(createContainer79);
                    Container createContainer80 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer80).setText("Date Valeur : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer80).setText(accountOperation2.date);
                    arrayList.add(createContainer80);
                    Container createContainer81 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer81).setText("Libellé : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer81).setText(accountOperation2.libelle);
                    arrayList.add(createContainer81);
                    stateMachine.findLblpopupItemValueV2(container).setText(((B3gService) CihMBanking.sesPMTR.getSessionClient().getClient_SavingAccountList().get(0)).SelectedId);
                    break;
                case 13:
                    AccountOperation accountOperation3 = (AccountOperation) b3gService;
                    Container createContainer82 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer82).setText("Montant Opération : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer82).setText(accountOperation3.montant);
                    arrayList.add(createContainer82);
                    Container createContainer83 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer83).setText("Date Opération : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer83).setText(accountOperation3.date);
                    arrayList.add(createContainer83);
                    Container createContainer84 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer84).setText("Date Valeur : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer84).setText(accountOperation3.date);
                    arrayList.add(createContainer84);
                    Container createContainer85 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer85).setText("Libellé : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer85).setText(accountOperation3.libelle);
                    arrayList.add(createContainer85);
                    if (CihMBanking.sesPMTR.getSessionClient().getClient_SavingAccountList().size() > 1) {
                        stateMachine.findLblpopupItemValueV2(container).setText(((B3gService) CihMBanking.sesPMTR.getSessionClient().getClient_SavingAccountList().get(1)).SelectedId);
                        break;
                    } else {
                        stateMachine.findLblpopupItemValueV2(container).setText(((B3gService) CihMBanking.sesPMTR.getSessionClient().getClient_SavingAccountList().get(0)).SelectedId);
                        break;
                    }
                case 14:
                    MTCConfirmationResume mTCConfirmationResume = (MTCConfirmationResume) b3gService;
                    Container createContainer86 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer86).setText("N°Compte : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer86).setText(mTCConfirmationResume.AccountNumber);
                    arrayList.add(createContainer86);
                    if (CihMBanking.sesPMTR.getLineToTopUp() != null) {
                        Container createContainer87 = stateMachine.createContainer(resources, "PopupItem2");
                        stateMachine.findLblPopupDetailItemTitle(createContainer87).setText("N° de la ligne : ");
                        stateMachine.findLblPopupDetailItemValueSP(createContainer87).setText(CihMBanking.sesPMTR.getLineToTopUp());
                        arrayList.add(createContainer87);
                    }
                    Container createContainer88 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer88).setText("Montant à payer : ");
                    try {
                        if (GetLastPageSessionSet() != null && GetLastPageSessionSet().PageSession != null && CihMBanking.sesPMTR.getIsFavoriteBack() == 1) {
                            MTCFavoriteBill mTCFavoriteBill2 = (MTCFavoriteBill) GetLastPageSessionSet().PageSession;
                            Label label3 = new Label();
                            if (CihMBanking.sesPMTR.getBillerType() == 1) {
                                if (mTCFavoriteBill2.Identifiant != "") {
                                    label3.setText(mTCFavoriteBill2.Alias + " (" + mTCFavoriteBill2.Identifiant + ")");
                                } else {
                                    label3.setText(mTCFavoriteBill2.Alias);
                                }
                                label3.setUIID("ac_lblitemDetailBlue");
                                label3.setVerticalAlignment(4);
                                label3.setTextPosition(3);
                                Container createContainer89 = stateMachine.createContainer(resources, "PopupItem2");
                                stateMachine.findLblPopupDetailItemTitle(createContainer89).setText("Favori : ");
                                stateMachine.findLblPopupDetailItemValueSP(createContainer89).setText(label3.getText());
                                arrayList.add(createContainer89);
                            }
                        }
                    } catch (Exception e) {
                        ShowMessageTransaction(stateMachine, e.getMessage(), null);
                    }
                    stateMachine.findLblPopupDetailItemValueSP(createContainer88).setText(DataTools.FormatCurrency(("" + DataTools.PurgeBlankFromStringAmount(mTCConfirmationResume.TotalAmount).doubleValue()) + "0") + " DH");
                    arrayList.add(createContainer88);
                    break;
                case 15:
                    MTCHistoric mTCHistoric = (MTCHistoric) b3gService;
                    String[] split = DataTools.split(mTCHistoric.OptionnalData2, ";");
                    if (split.length > 1) {
                        Container createContainer90 = stateMachine.createContainer(resources, "PopupItem2");
                        stateMachine.findLblPopupDetailItemTitle(createContainer90).setText("Créancier : ");
                        stateMachine.findLblPopupDetailItemValueSP(createContainer90).setText(split[0]);
                        arrayList.add(createContainer90);
                        Container createContainer91 = stateMachine.createContainer(resources, "PopupItem2");
                        stateMachine.findLblPopupDetailItemTitle(createContainer91).setText("Créance : ");
                        stateMachine.findLblPopupDetailItemValueSP(createContainer91).setText(split[1]);
                        arrayList.add(createContainer91);
                    }
                    Container createContainer92 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer92).setText("N°Compte : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer92).setText(mTCHistoric.DebitAccountNumber);
                    arrayList.add(createContainer92);
                    Container createContainer93 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer93).setText("Montant : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer93).setText(mTCHistoric.TransactionAmount);
                    arrayList.add(createContainer93);
                    Container createContainer94 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer94).setText("Date : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer94).setText(mTCHistoric.OperationDate);
                    arrayList.add(createContainer94);
                    Container createContainer95 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer95).setText("N°Facture : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer95).setText(((MTCImpaye) b3gService2).IdArticle);
                    arrayList.add(createContainer95);
                    Container createContainer96 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer96).setText("Référence de paiement  : ");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer96).setText(mTCHistoric.PaymentReference);
                    arrayList.add(createContainer96);
                    break;
                case 16:
                    MTCConfirmationResume mTCConfirmationResume2 = (MTCConfirmationResume) b3gService;
                    Container createContainer97 = stateMachine.createContainer(resources, "PopupItem2");
                    createContainer97.setUIID("dgMTC_CntDetail");
                    stateMachine.findLblPopupDetailItemTitle(createContainer97).setText("Créancier : ");
                    stateMachine.findLblPopupDetailItemTitle(createContainer97).setUIID("dgMtc_lblPopupDetailItemTitle");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer97).setText(mTCConfirmationResume2.BillerName);
                    stateMachine.findLblPopupDetailItemValueSP(createContainer97).setTextUIID("dgMtc_lblPopupDetailItemValueSP");
                    arrayList.add(createContainer97);
                    Container createContainer98 = stateMachine.createContainer(resources, "PopupItem2");
                    createContainer98.setUIID("dgMTC_CntDetail");
                    stateMachine.findLblPopupDetailItemTitle(createContainer98).setText("Créance : ");
                    stateMachine.findLblPopupDetailItemTitle(createContainer98).setUIID("dgMtc_lblPopupDetailItemTitle");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer98).setText(mTCConfirmationResume2.BillerServiceName);
                    stateMachine.findLblPopupDetailItemValueSP(createContainer98).setTextUIID("dgMtc_lblPopupDetailItemValueSP");
                    arrayList.add(createContainer98);
                    Container createContainer99 = stateMachine.createContainer(resources, "PopupItem2");
                    createContainer99.setUIID("dgMTC_CntDetail");
                    stateMachine.findLblPopupDetailItemTitle(createContainer99).setText("N°Compte : ");
                    stateMachine.findLblPopupDetailItemTitle(createContainer99).setUIID("dgMtc_lblPopupDetailItemTitle");
                    if (mTCConfirmationResume2.AccountNumber.length() > 23) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer99).setText(mTCConfirmationResume2.AccountNumber.substring(6, 21));
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer99).setText(mTCConfirmationResume2.AccountNumber);
                    }
                    stateMachine.findLblPopupDetailItemValueSP(createContainer99).setTextUIID("dgMtc_lblPopupDetailItemValueSP");
                    arrayList.add(createContainer99);
                    Container createContainer100 = stateMachine.createContainer(resources, "PopupItem2");
                    createContainer100.setUIID("dgMTC_CntDetail");
                    stateMachine.findLblPopupDetailItemTitle(createContainer100).setText("Montant à payer : ");
                    stateMachine.findLblPopupDetailItemTitle(createContainer100).setUIID("dgMtc_lblPopupDetailItemTitle");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer100).setText(DataTools.FormatCurrency(("" + DataTools.PurgeBlankFromStringAmount(mTCConfirmationResume2.TotalAmount).doubleValue()) + "0") + " DH");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer100).setTextUIID("dgMtc_lblPopupDetailItemValueSP");
                    arrayList.add(createContainer100);
                    Container createContainer101 = stateMachine.createContainer(resources, "PopupItem2");
                    createContainer101.setUIID("dgMTC_CntDetail");
                    stateMachine.findLblPopupDetailItemTitle(createContainer101).setText("N°Réglement : ");
                    stateMachine.findLblPopupDetailItemTitle(createContainer101).setUIID("dgMtc_lblPopupDetailItemTitle");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer101).setText(mTCConfirmationResume2.ReferenceNumber);
                    stateMachine.findLblPopupDetailItemValueSP(createContainer101).setTextUIID("dgMtc_lblPopupDetailItemValueSP");
                    arrayList.add(createContainer101);
                    Container createContainer102 = stateMachine.createContainer(resources, "PopupItem2");
                    createContainer102.setUIID("dgMTC_CntDetail");
                    stateMachine.findLblPopupDetailItemTitle(createContainer102).setText("Référence MTC : ");
                    stateMachine.findLblPopupDetailItemTitle(createContainer102).setUIID("dgMtc_lblPopupDetailItemTitle");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer102).setText(mTCConfirmationResume2.FatouratiReference);
                    stateMachine.findLblPopupDetailItemValueSP(createContainer102).setTextUIID("dgMtc_lblPopupDetailItemValueSP");
                    arrayList.add(createContainer102);
                    Container createContainer103 = stateMachine.createContainer(resources, "PopupItem2");
                    createContainer103.setUIID("dgMTC_CntDetail");
                    stateMachine.findLblPopupDetailItemTitle(createContainer103).setText("N.B : ");
                    stateMachine.findLblPopupDetailItemTitle(createContainer103).setUIID("dgMtc_lblPopupDetailItemTitle");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer103).setText("Pour toute réclamation Appelez le " + mTCConfirmationResume2.CRC + " en indiquant la référence de paiement.");
                    stateMachine.findLblPopupDetailItemValueSP(createContainer103).setTextUIID("dgMtc_lblPopupDetailItemValueSP");
                    arrayList.add(createContainer103);
                    break;
                default:
                    switch (i) {
                        case 24:
                            LCNBoockDemand lCNBoockDemand = (LCNBoockDemand) b3gService;
                            Container createContainer104 = stateMachine.createContainer(resources, "PopupItem2");
                            stateMachine.findLblPopupDetailItemTitle(createContainer104).setText("N°: ");
                            stateMachine.findLblPopupDetailItemValueSP(createContainer104).setText(lCNBoockDemand.AccountNumber);
                            arrayList.add(createContainer104);
                            Container createContainer105 = stateMachine.createContainer(resources, "PopupItem2");
                            stateMachine.findLblPopupDetailItemTitle(createContainer105).setText("Nombre de Feuilles : ");
                            stateMachine.findLblPopupDetailItemValueSP(createContainer105).setText(lCNBoockDemand.PageNumber);
                            arrayList.add(createContainer105);
                            Container createContainer106 = stateMachine.createContainer(resources, "PopupItem2");
                            stateMachine.findLblPopupDetailItemTitle(createContainer106).setText("Quantité : ");
                            stateMachine.findLblPopupDetailItemValueSP(createContainer106).setText(lCNBoockDemand.Quantity);
                            arrayList.add(createContainer106);
                            if (lCNBoockDemand.Date != null) {
                                Container createContainer107 = stateMachine.createContainer(resources, "PopupItem2");
                                stateMachine.findLblPopupDetailItemTitle(createContainer107).setText("Date : ");
                                stateMachine.findLblPopupDetailItemValueSP(createContainer107).setText(lCNBoockDemand.Date);
                                arrayList.add(createContainer107);
                                break;
                            }
                            break;
                        case 25:
                            CardOpperationStatus cardOpperationStatus = (CardOpperationStatus) b3gService;
                            if (cardOpperationStatus.newCardStatus.equals("O")) {
                                Container createContainer108 = stateMachine.createContainer(resources, "PopupItem2");
                                stateMachine.findLblPopupDetailItemValueSP(createContainer108).getAllStyles().setFgColor(15818018);
                                stateMachine.findLblPopupDetailItemValueSP(createContainer108).setText("ATTENTION ! Ne validez cette opération qu’en cas de vol ou perte définitive de votre carte.");
                                stateMachine.findLblPopupDetailItemTitle(createContainer108).setText("");
                                arrayList.add(createContainer108);
                            }
                            Container createContainer109 = stateMachine.createContainer(resources, "PopupItem2");
                            stateMachine.findLblPopupDetailItemTitle(createContainer109).setText("N°Carte: ");
                            stateMachine.findLblPopupDetailItemValueSP(createContainer109).setText(cardOpperationStatus.cardNumber);
                            arrayList.add(createContainer109);
                            Container createContainer110 = stateMachine.createContainer(resources, "PopupItem2");
                            stateMachine.findLblPopupDetailItemTitle(createContainer110).setText("Statut actuel: ");
                            stateMachine.findLblPopupDetailItemValueSP(createContainer110).setText(DataTools.GetCardStatusLabel(cardOpperationStatus.cardOpperationStatus));
                            arrayList.add(createContainer110);
                            Container createContainer111 = stateMachine.createContainer(resources, "PopupItem2");
                            stateMachine.findLblPopupDetailItemTitle(createContainer111).setText("Nouveau statut: ");
                            stateMachine.findLblPopupDetailItemValueSP(createContainer111).setText(DataTools.GetCardStatusLabel(cardOpperationStatus.newCardStatus));
                            arrayList.add(createContainer111);
                            if (cardOpperationStatus.newCardStatus.equals("O")) {
                                Container createContainer112 = stateMachine.createContainer(resources, "PopupItem2");
                                stateMachine.findLblPopupDetailItemTitle(createContainer112).setText("Motif: ");
                                stateMachine.findLblPopupDetailItemValueSP(createContainer112).setText(cardOpperationStatus.motif);
                                arrayList.add(createContainer112);
                            }
                            if (cardOpperationStatus.newCardStatus.equals("O")) {
                                String GetFeeAmountMsgFromSession4 = GetFeeAmountMsgFromSession("CARDOPPOSITION", "");
                                if (!GetFeeAmountMsgFromSession4.equals("")) {
                                    Container createContainer113 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer113).setText("");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer113).setText(GetFeeAmountMsgFromSession4);
                                    arrayList.add(createContainer113);
                                    break;
                                }
                            }
                            break;
                        case 26:
                            RIB rib = (RIB) b3gService;
                            Container createContainer114 = stateMachine.createContainer(resources, "PopupItem2");
                            stateMachine.findLblPopupDetailItemTitle(createContainer114).setText("N°RIB :");
                            stateMachine.findLblPopupDetailItemValueSP(createContainer114).setText(StringTools.RibFormatWithSP(rib.RibNumber));
                            arrayList.add(createContainer114);
                            if (rib.channelId.equals("01")) {
                                Container createContainer115 = stateMachine.createContainer(resources, "PopupItem2");
                                stateMachine.findLblPopupDetailItemTitle(createContainer115).setText("Email :");
                                stateMachine.findLblPopupDetailItemValueSP(createContainer115).setText(rib.RibEmail);
                                arrayList.add(createContainer115);
                                break;
                            } else {
                                Container createContainer116 = stateMachine.createContainer(resources, "PopupItem2");
                                stateMachine.findLblPopupDetailItemTitle(createContainer116).setText("N° Téléphone :");
                                stateMachine.findLblPopupDetailItemValueSP(createContainer116).setText(rib.RibPhoneNumber);
                                arrayList.add(createContainer116);
                                break;
                            }
                        case 27:
                            MonConseiller monConseiller = (MonConseiller) b3gService;
                            Container createContainer117 = stateMachine.createContainer(resources, "PopupItem2");
                            stateMachine.findLblPopupDetailItemTitle(createContainer117).setText("N° Téléphone :");
                            if (CihMBanking.sesPMTR.getIsMobileCall() == 0) {
                                String[] split2 = DataTools.split(monConseiller.mobilePhone, "/");
                                if (split2.length > 1) {
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer117).setText(StringUtil.replaceAll(split2[0], " ", ""));
                                } else {
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer117).setText(monConseiller.mobilePhone);
                                }
                            } else {
                                String[] split3 = DataTools.split(monConseiller.phone, "/");
                                if (split3.length > 1) {
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer117).setText(StringUtil.replaceAll(split3[0], " ", ""));
                                } else {
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer117).setText(monConseiller.phone);
                                }
                            }
                            arrayList.add(createContainer117);
                            break;
                        default:
                            switch (i) {
                                case 61:
                                    CardOpperationStatus cardOpperationStatus2 = (CardOpperationStatus) b3gService;
                                    Container createContainer118 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer118).setText("N°Carte: ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer118).setText(cardOpperationStatus2.cardNumber);
                                    arrayList.add(createContainer118);
                                    Container createContainer119 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer119).setText("Etat actuel: ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer119).setText(cardOpperationStatus2.oldDotStatus);
                                    arrayList.add(createContainer119);
                                    Container createContainer120 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer120).setText("Nouvel état: ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer120).setText(cardOpperationStatus2.newDotStatus);
                                    arrayList.add(createContainer120);
                                    break;
                                case 62:
                                    OvpService ovpService = (OvpService) b3gService;
                                    Container createContainer121 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer121).setText("Rib bénéficiaire : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer121).setText(ovpService.getBeneficiaryAccountNumber());
                                    arrayList.add(createContainer121);
                                    Container createContainer122 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer122).setText("Compte débité : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer122).setText(ovpService.getIssuarAccountNumber());
                                    arrayList.add(createContainer122);
                                    Container createContainer123 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer123).setText("Montant : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer123).setText(ovpService.getAmount());
                                    arrayList.add(createContainer123);
                                    Container createContainer124 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer124).setText("Motif : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer124).setText(ovpService.getMotif());
                                    arrayList.add(createContainer124);
                                    Container createContainer125 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer125).setText("Date de début : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer125).setText(ovpService.getStartDate());
                                    arrayList.add(createContainer125);
                                    Container createContainer126 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer126).setText("Date de fin : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer126).setText(ovpService.getEndDate());
                                    arrayList.add(createContainer126);
                                    break;
                                case 63:
                                    OvpExecutionService ovpExecutionService = (OvpExecutionService) b3gService;
                                    Container createContainer127 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer127).setText("Référence : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer127).setText(ovpExecutionService.getRefOrder());
                                    Container createContainer128 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer128).setText("Sort : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer128).setText(ovpExecutionService.getStatut());
                                    arrayList.add(createContainer128);
                                    Container createContainer129 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer129).setText("Nom : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer129).setText(ovpExecutionService.getVirementLabel());
                                    arrayList.add(createContainer129);
                                    Container createContainer130 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer130).setText("Raison du rejet : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer130).setText(ovpExecutionService.getRejectReason());
                                    arrayList.add(createContainer130);
                                    Container createContainer131 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer131).setText("Date d'éxecution : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer131).setText(ovpExecutionService.getOrderDate());
                                    arrayList.add(createContainer131);
                                    Container createContainer132 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer132).setText("Montant : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer132).setText(ovpExecutionService.getMontant());
                                    arrayList.add(createContainer131);
                                    break;
                                case 64:
                                    MyCheckLcn myCheckLcn2 = (MyCheckLcn) b3gService;
                                    Container createContainer133 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer133).setText("N°Compte : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer133).setText(myCheckLcn2.accountNumber);
                                    arrayList.add(createContainer133);
                                    Container createContainer134 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer134).setText("N°Chèque : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer134).setText(myCheckLcn2.checkNum);
                                    arrayList.add(createContainer134);
                                    Container createContainer135 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer135).setText("Email : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer135).setText(myCheckLcn2.email);
                                    arrayList.add(createContainer135);
                                    Container createContainer136 = stateMachine.createContainer(resources, "PopupItem2");
                                    stateMachine.findLblPopupDetailItemTitle(createContainer136).setText("Commission : ");
                                    stateMachine.findLblPopupDetailItemValueSP(createContainer136).setText(myCheckLcn2.fee);
                                    arrayList.add(createContainer136);
                                    break;
                                default:
                                    switch (i) {
                                        case 71:
                                            TransactionResume transactionResume6 = (TransactionResume) b3gService;
                                            Container createContainer137 = stateMachine.createContainer(resources, "PopupItem");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer137).setText("Carte à Débiter : ");
                                            stateMachine.findLblPopupDetailItemValue(createContainer137).setText(StringTools.HideCardNumber(transactionResume6.IssuerAccountNumber));
                                            arrayList.add(createContainer137);
                                            Container createContainer138 = stateMachine.createContainer(resources, "PopupItem");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer138).setText("Bénéficiaire ");
                                            stateMachine.findLblPopupDetailItemTitle2(createContainer138).setText("(" + transactionResume6.CreditedAccountAlias + ") : ");
                                            if (transactionResume6.TypeOperation.equals("TOPUP")) {
                                                stateMachine.findLblPopupDetailItemValue(createContainer138).setText(StringTools.HideCardNumber(transactionResume6.CreditedAccountNumber));
                                            } else {
                                                stateMachine.findLblPopupDetailItemValue(createContainer138).setText(StringTools.RibFormatWithSP(transactionResume6.CreditedAccountNumber));
                                            }
                                            arrayList.add(createContainer138);
                                            Container createContainer139 = stateMachine.createContainer(resources, "PopupItem");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer139).setText("Montant (MAD) : ");
                                            stateMachine.findLblPopupDetailItemValue(createContainer139).setText(transactionResume6.Amount);
                                            arrayList.add(createContainer139);
                                            Container createContainer140 = stateMachine.createContainer(resources, "PopupItem");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer140).setText("Motif : ");
                                            stateMachine.findLblPopupDetailItemValue(createContainer140).setText(StringTools.ToUpperCase(transactionResume6.Motif));
                                            arrayList.add(createContainer140);
                                            String GetFeeAmountMsgFromSession5 = GetFeeAmountMsgFromSession(transactionResume6.TypeOperation, transactionResume6.CreditedAccountNumber);
                                            if (!GetFeeAmountMsgFromSession5.equals("")) {
                                                Container createContainer141 = stateMachine.createContainer(resources, "PopupItem");
                                                stateMachine.findLblPopupDetailItemTitle(createContainer141).setText("");
                                                stateMachine.findLblPopupDetailItemValue(createContainer141).setText(GetFeeAmountMsgFromSession5);
                                                arrayList.add(createContainer141);
                                                break;
                                            }
                                            break;
                                        case 72:
                                            CashOutOperatoion cashOutOperatoion = (CashOutOperatoion) b3gService;
                                            Container createContainer142 = stateMachine.createContainer(resources, "PopupItem2");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer142).setText("Compte à débiter : ");
                                            if (cashOutOperatoion.getAccountNumber() != null) {
                                                String accountNumber = cashOutOperatoion.getAccountNumber();
                                                if (accountNumber.length() == 16) {
                                                    stateMachine.findLblPopupDetailItemValueSP(createContainer142).setText(accountNumber);
                                                } else {
                                                    stateMachine.findLblPopupDetailItemValueSP(createContainer142).setText(accountNumber.substring(6, 22));
                                                }
                                                arrayList.add(createContainer142);
                                            }
                                            if (cashOutOperatoion.getBeneficiary() != null) {
                                                Container createContainer143 = stateMachine.createContainer(resources, "PopupItem2");
                                                stateMachine.findLblPopupDetailItemTitle(createContainer143).setText("Bénéficiaire : ");
                                                stateMachine.findLblPopupDetailItemValueSP(createContainer143).setText(cashOutOperatoion.getBeneficiary());
                                                arrayList.add(createContainer143);
                                            }
                                            Container createContainer144 = stateMachine.createContainer(resources, "PopupItem2");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer144).setText("Montant (MAD) : ");
                                            stateMachine.findLblPopupDetailItemValueSP(createContainer144).setText(cashOutOperatoion.getAmount());
                                            arrayList.add(createContainer144);
                                            Container createContainer145 = stateMachine.createContainer(resources, "PopupItem2");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer145).setText("GSM du bénéficiaire : ");
                                            stateMachine.findLblPopupDetailItemValueSP(createContainer145).setText(cashOutOperatoion.getBeneficiaryGsm());
                                            arrayList.add(createContainer145);
                                            if (cashOutOperatoion.getExpirationDate() != null && cashOutOperatoion.getOperationCode() != null) {
                                                Container createContainer146 = stateMachine.createContainer(resources, "PopupItem2");
                                                stateMachine.findLblPopupDetailItemTitle(createContainer146).setText("Référence : ");
                                                stateMachine.findLblPopupDetailItemValueSP(createContainer146).setText(cashOutOperatoion.getOperationCode());
                                                arrayList.add(createContainer146);
                                                Container createContainer147 = stateMachine.createContainer(resources, "PopupItem2");
                                                stateMachine.findLblPopupDetailItemTitle(createContainer147).setText("Date Création : ");
                                                stateMachine.findLblPopupDetailItemValueSP(createContainer147).setText(cashOutOperatoion.getCreateDate());
                                                arrayList.add(createContainer147);
                                                Container createContainer148 = stateMachine.createContainer(resources, "PopupItem2");
                                                stateMachine.findLblPopupDetailItemTitle(createContainer148).setText("Date Expiration : ");
                                                stateMachine.findLblPopupDetailItemValueSP(createContainer148).setText(cashOutOperatoion.getExpirationDate());
                                                arrayList.add(createContainer148);
                                                Container createContainer149 = stateMachine.createContainer(resources, "PopupItem2");
                                                stateMachine.findLblPopupDetailItemTitle(createContainer149).setText("Date Paiement : ");
                                                stateMachine.findLblPopupDetailItemValueSP(createContainer149).setText(cashOutOperatoion.getPayerDate());
                                                arrayList.add(createContainer149);
                                                Container createContainer150 = stateMachine.createContainer(resources, "PopupItem2");
                                                stateMachine.findLblPopupDetailItemTitle(createContainer150).setText("Statut : ");
                                                stateMachine.findLblPopupDetailItemValueSP(createContainer150).setText(cashOutOperatoion.getStatus());
                                                arrayList.add(createContainer150);
                                                break;
                                            }
                                            break;
                                        case 73:
                                            ReleveDgi releveDgi = (ReleveDgi) b3gService;
                                            releveDgi.setCarburantType();
                                            Container createContainer151 = stateMachine.createContainer(resources, "PopupItem2");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer151).setText("Sort : ");
                                            stateMachine.findLblPopupDetailItemValueSP(createContainer151).setText(releveDgi.accountNumber);
                                            arrayList.add(createContainer151);
                                            Container createContainer152 = stateMachine.createContainer(resources, "PopupItem2");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer152).setText("Montant (MAD) : ");
                                            stateMachine.findLblPopupDetailItemValueSP(createContainer152).setText(releveDgi.totalTTC);
                                            arrayList.add(createContainer152);
                                            Container createContainer153 = stateMachine.createContainer(resources, "PopupItem2");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer153).setText("Reférence  : ");
                                            stateMachine.findLblPopupDetailItemValueSP(createContainer153).setText(releveDgi.refNumber);
                                            arrayList.add(createContainer153);
                                            Container createContainer154 = stateMachine.createContainer(resources, "PopupItem2");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer154).setText("Matricule  : ");
                                            stateMachine.findLblPopupDetailItemValueSP(createContainer154).setText(releveDgi.matricule);
                                            arrayList.add(createContainer154);
                                            Container createContainer155 = stateMachine.createContainer(resources, "PopupItem2");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer155).setText("Date : ");
                                            stateMachine.findLblPopupDetailItemValueSP(createContainer155).setText(releveDgi.operationDate);
                                            arrayList.add(createContainer155);
                                            Container createContainer156 = stateMachine.createContainer(resources, "PopupItem2");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer156).setText("Puissance Fiscale : ");
                                            stateMachine.findLblPopupDetailItemValueSP(createContainer156).setText(releveDgi.puisanceFiscal + " Chevaux");
                                            arrayList.add(createContainer156);
                                            Container createContainer157 = stateMachine.createContainer(resources, "PopupItem2");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer157).setText("Carburant : ");
                                            stateMachine.findLblPopupDetailItemValueSP(createContainer157).setText(releveDgi.carburantType);
                                            arrayList.add(createContainer157);
                                            Container createContainer158 = stateMachine.createContainer(resources, "PopupItem2");
                                            stateMachine.findLblPopupDetailItemTitle(createContainer158).setText("Marque : ");
                                            stateMachine.findLblPopupDetailItemValueSP(createContainer158).setText(releveDgi.marqueVehicule);
                                            arrayList.add(createContainer158);
                                            break;
                                        default:
                                            switch (i) {
                                                case 111:
                                                    CardOpperationStatus cardOpperationStatus3 = (CardOpperationStatus) b3gService;
                                                    Container createContainer159 = stateMachine.createContainer(resources, "PopupItem2");
                                                    stateMachine.findLblPopupDetailItemTitle(createContainer159).setText("N°Carte: ");
                                                    stateMachine.findLblPopupDetailItemValueSP(createContainer159).setText(cardOpperationStatus3.cardNumber);
                                                    arrayList.add(createContainer159);
                                                    Container createContainer160 = stateMachine.createContainer(resources, "PopupItem2");
                                                    stateMachine.findLblPopupDetailItemTitle(createContainer160).setText("Statut actuel: ");
                                                    stateMachine.findLblPopupDetailItemValueSP(createContainer160).setText(DataTools.GetCardStatusLabelNew(cardOpperationStatus3.cardOpperationStatus));
                                                    arrayList.add(createContainer160);
                                                    Container createContainer161 = stateMachine.createContainer(resources, "PopupItem2");
                                                    stateMachine.findLblPopupDetailItemTitle(createContainer161).setText("Nouveau statut: ");
                                                    stateMachine.findLblPopupDetailItemValueSP(createContainer161).setText(DataTools.GetCardStatusLabelNew(cardOpperationStatus3.newCardStatus));
                                                    arrayList.add(createContainer161);
                                                    break;
                                                case 112:
                                                case 113:
                                                    Card card = (Card) b3gService;
                                                    Container createContainer162 = stateMachine.createContainer(resources, "PopupItem2");
                                                    stateMachine.findLblPopupDetailItemTitle(createContainer162).setText("N°Carte: ");
                                                    stateMachine.findLblPopupDetailItemValueSP(createContainer162).setText(card.cardNumber);
                                                    arrayList.add(createContainer162);
                                                    Container createContainer163 = stateMachine.createContainer(resources, "PopupItem2");
                                                    stateMachine.findLblPopupDetailItemTitle(createContainer163).setText("Exp: ");
                                                    stateMachine.findLblPopupDetailItemValueSP(createContainer163).setText(card.expirationDate.substring(3, 5) + " " + card.expirationDate.substring(5, 6) + " " + card.expirationDate.substring(8, 10) + "   ");
                                                    arrayList.add(createContainer163);
                                                    break;
                                            }
                                    }
                            }
                    }
            }
        } else {
            TransactionResume transactionResume7 = (TransactionResume) b3gService;
            Container createContainer164 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer164).setText("Bénéficiaire : ");
            stateMachine.findLblPopupDetailItemValue(createContainer164).setText(transactionResume7.CreditedAccountNumber);
            arrayList.add(createContainer164);
            Container createContainer165 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer165).setText("Montant (MAD) : ");
            stateMachine.findLblPopupDetailItemValue(createContainer165).setText(transactionResume7.Amount);
            arrayList.add(createContainer165);
            Container createContainer166 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer166).setText("Date début exécution :");
            stateMachine.findLblPopupDetailItemValue(createContainer166).setText(transactionResume7.dateExcPickerForOvp);
            arrayList.add(createContainer166);
            Container createContainer167 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer167).setText("Date fin :");
            stateMachine.findLblPopupDetailItemValue(createContainer167).setText(transactionResume7.dateFinPickerForOvp);
            arrayList.add(createContainer167);
            Container createContainer168 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer168).setText("Fréquence :");
            stateMachine.findLblPopupDetailItemValue(createContainer168).setText(transactionResume7.frequencePickerValueforOvp);
            arrayList.add(createContainer168);
            Container createContainer169 = stateMachine.createContainer(resources, "PopupItem");
            stateMachine.findLblPopupDetailItemTitle(createContainer169).setText("Commission :");
            stateMachine.findLblPopupDetailItemValue(createContainer169).setText("5.50 Dhs TTC");
            arrayList.add(createContainer169);
        }
        return arrayList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public ArrayList GetPopupItemDetail(int i, B3gService b3gService, StateMachine stateMachine, Resources resources, Container container, B3gService b3gService2, Hashtable hashtable) {
        ArrayList arrayList = new ArrayList();
        if (i == 68) {
            Container createContainer = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer).setText("Etat actuel : ");
            Container createContainer2 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer2).setText("Nouvel Etat : ");
            if (((Beneficiary) b3gService2).statusActivation.equals("0")) {
                stateMachine.findLblPopupDetailItemValueSP(createContainer).setText("Activé");
                stateMachine.findLblPopupDetailItemValueSP(createContainer2).setText("Désactivé");
            } else {
                stateMachine.findLblPopupDetailItemValueSP(createContainer).setText("Désactivé");
                stateMachine.findLblPopupDetailItemValueSP(createContainer2).setText("Activé");
            }
            arrayList.add(createContainer);
            arrayList.add(createContainer2);
        } else if (i == 69) {
            Beneficiary beneficiary = (Beneficiary) b3gService2;
            Container createContainer3 = stateMachine.createContainer(resources, "PopupItem2");
            if (!beneficiary.typeBenef.equals("300033")) {
                stateMachine.findLblPopupDetailItemTitle(createContainer3).setText("N° : ");
                stateMachine.findLblPopupDetailItemValueSP(createContainer3).setText(beneficiary.cardNumber);
            } else {
                stateMachine.findLblPopupDetailItemTitle(createContainer3).setText("N° du Tél : ");
                stateMachine.findLblPopupDetailItemValueSP(createContainer3).setText(beneficiary.phoneNumber);
            }
            arrayList.add(createContainer3);
            Container createContainer4 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer4).setText("Alias : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer4).setText(beneficiary.alias);
            arrayList.add(createContainer4);
        } else if (i == 73) {
            ReleveDgi releveDgi = (ReleveDgi) b3gService;
            Container createContainer5 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer5).setText("N°Compte : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer5).setText(releveDgi.accountNumber.substring(6, 22));
            arrayList.add(createContainer5);
            Container createContainer6 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer6).setText("Montant à payer : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer6).setText(releveDgi.totalTTC + " DHs");
            arrayList.add(createContainer6);
            Container createContainer7 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer7).setText("Matricule : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer7).setText(releveDgi.matricule);
            arrayList.add(createContainer7);
            Container createContainer8 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer8).setText("Marque : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer8).setText(releveDgi.marqueVehicule);
            arrayList.add(createContainer8);
            Container createContainer9 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer9).setText("Puissance Fiscale : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer9).setText(releveDgi.puisanceFiscal + " Chevaux");
            arrayList.add(createContainer9);
            Container createContainer10 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer10).setText("Carburant : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer10).setText(releveDgi.carburantType);
            arrayList.add(createContainer10);
        } else if (i != 88) {
            switch (i) {
                case 57:
                    Container createContainer11 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer11).setText("Compte : ");
                    if (b3gService == null) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer11).setText(hashtable.get("ACCOUNT_NUMBER").toString());
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer11).setText(((OrderMADHistoricDataService) b3gService).getAccountNumber());
                    }
                    arrayList.add(createContainer11);
                    Container createContainer12 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer12).setText("Montant : ");
                    if (b3gService == null) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer12).setText(hashtable.get("AMOUNT").toString() + " Dh");
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer12).setText(((OrderMADHistoricDataService) b3gService).getOrderAmount() + " Dh");
                    }
                    arrayList.add(createContainer12);
                    Container createContainer13 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer13).setText("Date de disponibilité : ");
                    if (b3gService == null) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer13).setText(hashtable.get("AVAILABLITY_DATE").toString());
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer13).setText(((OrderMADHistoricDataService) b3gService).getAvailabilityDate());
                    }
                    arrayList.add(createContainer13);
                    Container createContainer14 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer14).setText("Agence : ");
                    if (b3gService == null) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer14).setText(hashtable.get("AGENCY").toString().trim());
                        if (!hashtable.get("AGENCY").toString().equals("")) {
                            arrayList.add(createContainer14);
                        }
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer14).setText(((OrderMADHistoricDataService) b3gService).getAgency().trim());
                        arrayList.add(createContainer14);
                    }
                    if (b3gService == null) {
                        Container createContainer15 = stateMachine.createContainer(resources, "PopupItem2");
                        stateMachine.findLblPopupDetailItemTitle(createContainer15).setText("Ville : ");
                        stateMachine.findLblPopupDetailItemValueSP(createContainer15).setText(hashtable.get("CITY").toString().trim());
                        if (!hashtable.get("CITY").toString().equals("")) {
                            arrayList.add(createContainer15);
                        }
                    }
                    if (b3gService == null) {
                        Container createContainer16 = stateMachine.createContainer(resources, "PopupItem2");
                        stateMachine.findLblPopupDetailItemTitle(createContainer16).setText("Commentaire : ");
                        stateMachine.findLblPopupDetailItemValueSP(createContainer16).setText(hashtable.get("COMMENT").toString().trim());
                        break;
                    }
                    break;
                case 58:
                    Container createContainer17 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer17).setText("Devise : ");
                    if (b3gService == null) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer17).setText(hashtable.get("CURRENCY").toString());
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer17).setText(((OrderCurrencyHistoricDataService) b3gService).getCurrency());
                    }
                    arrayList.add(createContainer17);
                    Container createContainer18 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer18).setText("Montant : ");
                    if (b3gService == null) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer18).setText(hashtable.get("AMOUNT").toString());
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer18).setText(((OrderCurrencyHistoricDataService) b3gService).getOrderAmount());
                    }
                    arrayList.add(createContainer18);
                    Container createContainer19 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer19).setText("Date de disponibilité : ");
                    if (b3gService == null) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer19).setText(hashtable.get("AVAILABILITY_DATE").toString());
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer19).setText(((OrderCurrencyHistoricDataService) b3gService).getAvailabilityDate());
                    }
                    arrayList.add(createContainer19);
                    Container createContainer20 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer20).setText("Agence : ");
                    if (b3gService == null) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer20).setText(hashtable.get("AGENCY").toString().trim());
                        if (!hashtable.get("AGENCY").toString().equals("")) {
                            arrayList.add(createContainer20);
                        }
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer20).setText(((OrderCurrencyHistoricDataService) b3gService).getAgency().trim());
                    }
                    if (b3gService == null) {
                        Container createContainer21 = stateMachine.createContainer(resources, "PopupItem2");
                        stateMachine.findLblPopupDetailItemTitle(createContainer21).setText("Ville : ");
                        stateMachine.findLblPopupDetailItemValueSP(createContainer21).setText(hashtable.get("CITY").toString().trim());
                    }
                    if (b3gService == null) {
                        Container createContainer22 = stateMachine.createContainer(resources, "PopupItem2");
                        stateMachine.findLblPopupDetailItemTitle(createContainer22).setText("Commentaire : ");
                        stateMachine.findLblPopupDetailItemValueSP(createContainer22).setText(hashtable.get("COMMENT").toString().trim());
                        break;
                    }
                    break;
                case 59:
                    Container createContainer23 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer23).setText("Compte : ");
                    if (b3gService == null) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer23).setText(hashtable.get("ACCOUNT_NUMBER").toString());
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer23).setText(((StudentTransferHisoricDataService) b3gService).getAcountNumber());
                    }
                    arrayList.add(createContainer23);
                    Container createContainer24 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer24).setText("Type de transfert : ");
                    if (b3gService == null) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer24).setText(hashtable.get("TRANSFER_TYPE").toString());
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer24).setText(((StudentTransferHisoricDataService) b3gService).getTransferType());
                    }
                    arrayList.add(createContainer24);
                    Container createContainer25 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer25).setText("Montant : ");
                    if (b3gService == null) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer25).setText(hashtable.get("AMOUNT").toString() + " " + hashtable.get("AMOUNT_CURRENCY").toString());
                    } else {
                        StudentTransferHisoricDataService studentTransferHisoricDataService = (StudentTransferHisoricDataService) b3gService;
                        stateMachine.findLblPopupDetailItemValueSP(createContainer25).setText(studentTransferHisoricDataService.getAmount() + " " + studentTransferHisoricDataService.getAmountCurrency());
                    }
                    arrayList.add(createContainer25);
                    Container createContainer26 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer26).setText("Devise de Transfert : ");
                    if (b3gService == null) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer26).setText(hashtable.get("TRANSFER_CURRENCY").toString());
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer26).setText(((StudentTransferHisoricDataService) b3gService).getTransferCurrency());
                    }
                    arrayList.add(createContainer26);
                    Container createContainer27 = stateMachine.createContainer(resources, "PopupItem2");
                    stateMachine.findLblPopupDetailItemTitle(createContainer27).setText("Nom de l'étudiant : ");
                    if (b3gService == null) {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer27).setText(hashtable.get("STUDENT_NAME").toString().trim());
                    } else {
                        stateMachine.findLblPopupDetailItemValueSP(createContainer27).setText(((StudentTransferHisoricDataService) b3gService).getStudentName());
                    }
                    arrayList.add(createContainer27);
                    if (b3gService == null) {
                        Container createContainer28 = stateMachine.createContainer(resources, "PopupItem2");
                        stateMachine.findLblPopupDetailItemTitle(createContainer28).setText("Commentaire : ");
                        stateMachine.findLblPopupDetailItemValueSP(createContainer28).setText(hashtable.get("COMMENT").toString().trim());
                        break;
                    }
                    break;
            }
        } else {
            AgenceDemandeHistorique agenceDemandeHistorique = (AgenceDemandeHistorique) b3gService2;
            Container createContainer29 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer29).setText("Date de la demande : ");
            Container createContainer30 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer30).setText("Agence actuelle : ");
            Container createContainer31 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer31).setText("Ville demandée : ");
            Container createContainer32 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer32).setText("Agence demandée : ");
            Container createContainer33 = stateMachine.createContainer(resources, "PopupItem2");
            stateMachine.findLblPopupDetailItemTitle(createContainer33).setText("Statut : ");
            stateMachine.findLblPopupDetailItemValueSP(createContainer29).setText(agenceDemandeHistorique.request_Date);
            stateMachine.findLblPopupDetailItemValueSP(createContainer30).setText(agenceDemandeHistorique.old_Agence);
            stateMachine.findLblPopupDetailItemValueSP(createContainer31).setText(agenceDemandeHistorique.city);
            stateMachine.findLblPopupDetailItemValueSP(createContainer32).setText(agenceDemandeHistorique.new_agence);
            stateMachine.findLblPopupDetailItemValueSP(createContainer33).setText(agenceDemandeHistorique.status_Id);
            arrayList.add(createContainer29);
            arrayList.add(createContainer30);
            arrayList.add(createContainer31);
            arrayList.add(createContainer32);
            arrayList.add(createContainer33);
        }
        return arrayList;
    }

    private Container createItemDetail(String str, String str2, Resources resources, StateMachine stateMachine) {
        Container createContainer = stateMachine.createContainer(resources, "PopupItem2");
        stateMachine.findLblPopupDetailItemTitle(createContainer).setText(str);
        stateMachine.findLblPopupDetailItemValueSP(createContainer).setText(str2);
        return createContainer;
    }

    public String GetPopupItemTitle(int i, B3gService b3gService) {
        if (i == 5) {
            return "N°Carte";
        }
        if (i == 15) {
            return "Historique";
        }
        if (i == 19) {
            return "Détails Chèque/Lcn ";
        }
        if (i == 22) {
            return "Demande";
        }
        if (i == 64) {
            return "Détail de l'image";
        }
        if (i == 88) {
            return "Changement Agence";
        }
        if (i == 121) {
            return "N°Compte";
        }
        if (i == 72) {
            return "CIH Express";
        }
        if (i == 73) {
            return "Détails du Paiement";
        }
        if (i == 75) {
            return "Détail de change";
        }
        if (i == 76) {
            return "Détail d'opération";
        }
        switch (i) {
            case 7:
                return "N°Référence";
            case 8:
            case 12:
            case 13:
                return "N°Compte";
            case 9:
                return "Titre";
            case 10:
                return "Virement";
            case 11:
                TransferHistoric transferHistoric = (TransferHistoric) b3gService;
                return transferHistoric.OperationType.equals("300013") ? "Historique Virement" : transferHistoric.OperationType.equals("300014") ? "Historique Recharge" : "Historique Débit Carte";
            default:
                switch (i) {
                    case 57:
                    case 58:
                        return "Détails de la commande";
                    case 59:
                        return "Détails de la demande";
                    default:
                        return "";
                }
        }
    }

    public String GetPopupItemTitleValue(int i, B3gService b3gService) {
        if (i == 5) {
            return ((Card) b3gService).cardNumber;
        }
        if (i == 15) {
            return "Payement Facture";
        }
        if (i != 32) {
            if (i != 111) {
                if (i == 121) {
                    return ((Account) b3gService).accountNumber;
                }
                if (i == 7) {
                    return ((RecurringDetail) b3gService).ContractRef;
                }
                if (i == 8) {
                    return ((Account) b3gService).accountNumber;
                }
                if (i == 9) {
                    return ((Walet) b3gService).Title;
                }
                if (i != 25) {
                    if (i != 26) {
                        switch (i) {
                            case 11:
                                return ((TransferHistoric) b3gService).Item;
                            case 12:
                                return ((SavingAccount) b3gService).accountNumber;
                            case 13:
                                return ((PELAccount) b3gService).accountNumber;
                            default:
                                return "";
                        }
                    }
                    return ((RIB) b3gService).RibNumber.substring(6, 22);
                }
            }
            return ((CardOpperationStatus) b3gService).cardNumber;
        }
        return ((MTCFavoriteBill) b3gService).Alias;
    }

    public Container GetPopupClose(Dialog dialog) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button(" ");
        button.setUIID("dg_BtnYestIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 22(dialog));
        container.addComponent(button);
        return container;
    }

    class 22 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        22(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public Container GetPopupTransferBtn(Dialog dialog, int i, B3gService b3gService, int i2) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 23(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 24(dialog, b3gService, i2, i));
        container.addComponent(button2);
        return container;
    }

    class 23 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        23(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 24 implements ActionListener {
        final /* synthetic */ int val$ServiceId;
        final /* synthetic */ int val$ServiceIdWS;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ B3gService val$pB3gService;

        24(Dialog dialog, B3gService b3gService, int i, int i2) {
            this.val$d = dialog;
            this.val$pB3gService = b3gService;
            this.val$ServiceIdWS = i;
            this.val$ServiceId = i2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            new UITimer(new B3GUiManager$24$$ExternalSyntheticLambda2(this, this.val$pB3gService, this.val$ServiceIdWS, this.val$ServiceId)).schedule(500, false, B3GUiManager.this.currentForm);
        }

        /* synthetic */ void lambda$actionPerformed$2$com-b3g-ui-B3GUiManager$24(B3gService b3gService, int i, int i2) {
            ArrayList TransferOperationProcess;
            TransferOperation transferOperation = new TransferOperation();
            TransactionResume transactionResume = (TransactionResume) b3gService;
            if (i == 161) {
                TransferOperationProcess = transferOperation.TransferOperationProcess(i, transactionResume, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().cardProgramId, "TRANSFER", CihMBanking.sesPMTR.getSessionClient().getClient_AccountList(), CihMBanking.sesPMTR.getSessionClient().getClient_CardList());
            } else {
                TransferOperationProcess = transferOperation.TransferOperationProcess(i, transactionResume.CreditedAccountNumber, transactionResume.IssuerAccountNumber, transactionResume.Amount, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().cardProgramId, "TRANSFER", transactionResume.PassHB, StringTools.ToUpperCase(transactionResume.Motif), transactionResume.CreditedAccountAlias, transactionResume.CINHB, CihMBanking.sesPMTR.getSessionClient().getClient_AccountList(), CihMBanking.sesPMTR.getSessionClient().getClient_CardList(), transactionResume.field1, transactionResume.field2, transactionResume.isTutor);
            }
            new UITimer(new B3GUiManager$24$$ExternalSyntheticLambda0(TransferOperationProcess, i, i2, b3gService)).schedule(500, false, B3GUiManager.this.currentForm);
            new UITimer(new B3GUiManager$24$$ExternalSyntheticLambda1(this, i, i2)).schedule(500, false, B3GUiManager.this.currentForm);
        }

        static /* synthetic */ void lambda$actionPerformed$0(ArrayList arrayList, int i, int i2, B3gService b3gService) {
            if (arrayList == null || arrayList.size() <= 0) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(((TransferOperation) arrayList.get(0)).pStatusLabel), null);
                return;
            }
            if (i != 300019 && i != 300013 && i != 131 && i != 161) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(((TransferOperation) arrayList.get(0)).pStatusLabel), null);
                return;
            }
            if (i2 != 85 && i2 != 90 && i2 != 86) {
                if (((TransferOperation) arrayList.get(0)).pStatusCode.equals("000")) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowLastMessageTransactionTransfert(b3gService, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(((TransferOperation) arrayList.get(0)).pStatusLabel), null);
                    new RateUs(CihMBanking.sesPMTR.getCurrentUiManager()).init();
                    return;
                } else {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(((TransferOperation) arrayList.get(0)).pStatusLabel), null);
                    return;
                }
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(((TransferOperation) arrayList.get(0)).pStatusLabel), null);
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-B3GUiManager$24(int i, int i2) {
            if (i == 300019 || i == 300013 || i == 131) {
                if (i2 == 85 || i2 == 86) {
                    B3GUiManager.this.NavigateToPageById(81);
                    return;
                } else if (i2 == 90) {
                    B3GUiManager.this.NavigateToPageById(101);
                    return;
                } else {
                    B3GUiManager.this.NavigateToPageById(8);
                    return;
                }
            }
            if (i == 161) {
                B3GUiManager.this.NavigateToPageById(81);
                return;
            }
            if (i == 900118 || i == 900117) {
                B3GUiManager.this.NavigateToPageById(47);
            } else if (i == 300017) {
                B3GUiManager.this.NavigateToPageById(52);
            } else {
                B3GUiManager.this.NavigateToPageById(9);
            }
        }
    }

    public void showDialogRecapVirement(B3gService b3gService, StateMachine stateMachine, TransactionResume transactionResume) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpDialogVirement");
            Container virementRecap = virementRecap(StringTools.HideCardNumberVirementRecap(transactionResume.IssuerAccountNumber), transactionResume.CreditedAccountNumber, transactionResume.Amount, today(), transactionResume.CreditedAccountAlias, transactionResume.Motif, transactionResume.IssuerAccountOwner, 1);
            UIBuilder uIBuilder = new UIBuilder();
            ((Container) uIBuilder.findByName("Body", createContainer)).add(virementRecap);
            Container container = (Container) uIBuilder.findByName("Footer", createContainer);
            TableLayout tableLayout = new TableLayout(1, 2);
            container.setLayout(tableLayout);
            container.addComponent(tableLayout.createConstraint().widthPercentage(50), GetPopupShareVirement(dialog, b3gService, 1));
            container.addComponent(tableLayout.createConstraint().widthPercentage(50), GetPopupCloseVirement(dialog));
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    private String today() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
    }

    public Container GetPopupCloseVirement(Dialog dialog) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button(" Fermer ");
        button.setUIID("dg_BtnYest");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 25(dialog));
        container.setLeadComponent(button);
        container.addComponent(button);
        return container;
    }

    class 25 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        25(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public void ShowDialogVirement(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2) {
        ArrayList GetPopupItemDetail;
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUpVirement");
            if (i == 57 || i == 59 || i == 58) {
                GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, b3gService2, null);
            } else {
                GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, b3gService2);
            }
            for (int i2 = 0; i2 < GetPopupItemDetail.size(); i2++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i2));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            Container container = (Container) new UIBuilder().findByName("CntPopupFooter", createContainer);
            TableLayout tableLayout = new TableLayout(1, 2);
            container.setLayout(tableLayout);
            container.addComponent(tableLayout.createConstraint().widthPercentage(50), GetPopupShareVirement(dialog, b3gService, 0));
            container.addComponent(tableLayout.createConstraint().widthPercentage(50), GetPopupCloseVirement(dialog));
            stateMachine.findLblpopupItemTitleV2(createContainer).setText(GetPopupItemTitle(i, b3gService2));
            stateMachine.findLblpopupItemValueV2(createContainer).setText(GetPopupItemTitleValue(i, b3gService2));
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    public Container GetPopupShareVirement(Dialog dialog, B3gService b3gService, int i) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        ShareButton shareButton = new ShareButton();
        shareButton.setText(" Partager ");
        shareButton.setUIID("dg_BtnYest");
        shareButton.setIcon(this.ressource.getImage("ico_share_popup_NW.png"));
        shareButton.setVerticalAlignment(4);
        shareButton.addActionListener(new 26(i, b3gService, shareButton, dialog));
        container.setLeadComponent(shareButton);
        container.addComponent(shareButton);
        return container;
    }

    class 26 implements ActionListener {
        final /* synthetic */ B3gService val$Service;
        final /* synthetic */ ShareButton val$btnIcon;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ int val$type;

        26(int i, B3gService b3gService, ShareButton shareButton, Dialog dialog) {
            this.val$type = i;
            this.val$Service = b3gService;
            this.val$btnIcon = shareButton;
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Container container = new Container();
            int i = this.val$type;
            if (i == 0) {
                TransferHistoric transferHistoric = (TransferHistoric) this.val$Service;
                container = B3GUiManager.access$300(B3GUiManager.this, StringTools.HideCardNumberVirementHistoricRecap(transferHistoric.AccountNumber), transferHistoric.Beneficiary, transferHistoric.Amount, transferHistoric.OperationDate, transferHistoric.BeneficiaryName, transferHistoric.Motif, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, 0);
            } else if (i == 1) {
                TransactionResume transactionResume = (TransactionResume) this.val$Service;
                container = B3GUiManager.access$300(B3GUiManager.this, StringTools.HideCardNumberVirementRecap(transactionResume.IssuerAccountNumber), transactionResume.CreditedAccountNumber, transactionResume.Amount, B3GUiManager.access$400(B3GUiManager.this), transactionResume.CreditedAccountAlias, transactionResume.Motif, transactionResume.IssuerAccountOwner, 1);
            }
            container.setHeight(1400);
            container.setWidth(2000);
            Image createImage = Image.createImage(2000, 1400);
            container.revalidate();
            container.setVisible(true);
            container.paintComponent(createImage.getGraphics(), true);
            String str = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
            try {
                OutputStream openOutputStream = FileSystemStorage.getInstance().openOutputStream(str);
                try {
                    ImageIO.getImageIO().save(createImage, openOutputStream, "png", 1.0f);
                    if (openOutputStream != null) {
                        openOutputStream.close();
                    }
                } finally {
                }
            } catch (IOException unused) {
            }
            this.val$btnIcon.setImageToShare(str, "image/png");
            this.val$d.dispose();
        }
    }

    private Container virementRecap(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i) {
        String str8;
        UIBuilder uIBuilder = new UIBuilder();
        Container createContainer = uIBuilder.createContainer("/cihv3", "VirementRecapNew");
        int indexOf = str4.indexOf(" ");
        if (indexOf != -1) {
            str8 = str4.substring(0, indexOf) + " - " + str4.substring(indexOf + 1);
        } else {
            str8 = null;
        }
        ((Label) uIBuilder.findByName("EmetteurLabel", createContainer)).setText(str2);
        ((Label) uIBuilder.findByName("EmetteurNameLabel", createContainer)).setText(str7);
        ((Label) uIBuilder.findByName("MontantLabel", createContainer)).setText(str3);
        ((Label) uIBuilder.findByName("MotifLabel", createContainer)).setText(str6);
        ((SpanLabel) uIBuilder.findByName("BeneficiaireNameSpanLabel", createContainer)).setText(str5);
        if (str4 == null || str4.isEmpty()) {
            ((Container) uIBuilder.findByName("Date", createContainer)).setVisible(false);
        } else {
            ((Label) uIBuilder.findByName("DateLabel", createContainer)).setText(str8);
        }
        return createContainer;
    }

    public Container GetPopupSendCheckBtn(Dialog dialog, int i, B3gService b3gService, int i2, String str) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 27(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 28(dialog, b3gService, str));
        container.addComponent(button2);
        return container;
    }

    class 27 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        27(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 28 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ String val$otp;
        final /* synthetic */ B3gService val$pB3gService;

        28(Dialog dialog, B3gService b3gService, String str) {
            this.val$d = dialog;
            this.val$pB3gService = b3gService;
            this.val$otp = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            MyCheckLcn myCheckLcn = (MyCheckLcn) this.val$pB3gService;
            ServiceResponse CheckLcnValidationProcess = myCheckLcn.CheckLcnValidationProcess(myCheckLcn, this.val$otp);
            B3GUiManager b3GUiManager = B3GUiManager.this;
            b3GUiManager.ShowMessageAndBack(b3GUiManager.stateMachine, DataTools.FormatUnicode(CheckLcnValidationProcess.getStatusLabel()), null);
        }
    }

    public Container GetPopupCashoutBtn(Dialog dialog, int i, B3gService b3gService, int i2) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 29(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 30(dialog, b3gService, i2));
        container.addComponent(button2);
        return container;
    }

    class 29 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        29(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 30 implements ActionListener {
        final /* synthetic */ int val$ServiceIdWS;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ B3gService val$pB3gService;

        30(Dialog dialog, B3gService b3gService, int i) {
            this.val$d = dialog;
            this.val$pB3gService = b3gService;
            this.val$ServiceIdWS = i;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            new UITimer(new B3GUiManager$30$$ExternalSyntheticLambda2(this, this.val$pB3gService, this.val$ServiceIdWS)).schedule(500, false, B3GUiManager.this.currentForm);
        }

        /* synthetic */ void lambda$actionPerformed$2$com-b3g-ui-B3GUiManager$30(B3gService b3gService, int i) {
            CashOutOperatoion cashOutOperatoion = (CashOutOperatoion) b3gService;
            ServiceResponse CashOutProcess = cashOutOperatoion.CashOutProcess();
            new UITimer(new B3GUiManager$30$$ExternalSyntheticLambda0(i, cashOutOperatoion, CashOutProcess)).schedule(500, false, B3GUiManager.this.currentForm);
            if (CashOutProcess.getStatusCode().equals("000")) {
                cashOutOperatoion.UpdateAccountBalance(cashOutOperatoion.getAccountNumber(), cashOutOperatoion.getAmount());
            }
            new UITimer(new B3GUiManager$30$$ExternalSyntheticLambda1(this)).schedule(500, false, B3GUiManager.this.currentForm);
        }

        static /* synthetic */ void lambda$actionPerformed$0(int i, CashOutOperatoion cashOutOperatoion, ServiceResponse serviceResponse) {
            if (i == 300017) {
                cashOutOperatoion.getBeneficiary();
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageCihExpressTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(cashOutOperatoion.getBeneficiary()), null, serviceResponse.getStatusCode(), serviceResponse.getStatusLabel());
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(serviceResponse.getStatusLabel()), null);
            }
        }

        /* synthetic */ void lambda$actionPerformed$1$com-b3g-ui-B3GUiManager$30() {
            B3GUiManager.this.NavigateToPageById(52);
        }
    }

    public Container GetPopupMTCResumeBtn(Dialog dialog, int i, B3gService b3gService, MTCCreancier mTCCreancier, int i2) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("QUITTER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 31(dialog));
        container.addComponent(button);
        Button button2 = new Button("EDITER RECU");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 32(dialog, b3gService, mTCCreancier));
        container.addComponent(button2);
        return container;
    }

    class 31 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        31(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 32 implements ActionListener {
        final /* synthetic */ MTCCreancier val$creance;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ B3gService val$pB3gService;

        32(Dialog dialog, B3gService b3gService, MTCCreancier mTCCreancier) {
            this.val$d = dialog;
            this.val$pB3gService = b3gService;
            this.val$creance = mTCCreancier;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            MTCHistoric mTCHistoric = new MTCHistoric();
            MTCConfirmationResume mTCConfirmationResume = (MTCConfirmationResume) this.val$pB3gService;
            FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
            ServiceResponse MTCSendReceiptProcess1 = mTCHistoric.MTCSendReceiptProcess1(mTCConfirmationResume, this.val$creance);
            if (MTCSendReceiptProcess1.getStatusCode().equals("000")) {
                String str = fileSystemStorage.getAppHomePath() + "PaiementFacture" + mTCConfirmationResume.BillerName + mTCConfirmationResume.FatouratiReference + ".pdf";
                byte[] decode = Base64.decode(MTCSendReceiptProcess1.getStatusLabel().getBytes(), MTCSendReceiptProcess1.getStatusLabel().getBytes().length);
                try {
                    OutputStream openOutputStream = fileSystemStorage.openOutputStream(str);
                    try {
                        openOutputStream.write(decode);
                        if (openOutputStream != null) {
                            openOutputStream.close();
                        }
                    } finally {
                    }
                } catch (IOException unused) {
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(MTCSendReceiptProcess1.getStatusLabel()), null);
                }
                Display.getInstance().execute(str);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(MTCSendReceiptProcess1.getStatusLabel()), null);
        }
    }

    public Container GetPopupMTCRegroupmResumeBtn(Dialog dialog, int i, B3gService b3gService, int i2) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(false);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button();
        button.setUIID("dg_BtnCancelIcon_Padd");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 33(dialog));
        container.addComponent(button);
        Button button2 = new Button("EDITER RECU");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        return container;
    }

    class 33 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        33(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public Container GetPopupMTCConfirmationBtn(Dialog dialog, int i, B3gService b3gService, int i2, String str) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 34(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 35(dialog, b3gService, str));
        container.addComponent(button2);
        return container;
    }

    class 34 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        34(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 35 implements ActionListener {
        final /* synthetic */ String val$codeOtp;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ B3gService val$pB3gService;

        35(Dialog dialog, B3gService b3gService, String str) {
            this.val$d = dialog;
            this.val$pB3gService = b3gService;
            this.val$codeOtp = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            new MTCConfirmationResume().MTCConfirmationProcess((MTCConfirmationResume) this.val$pB3gService, this.val$codeOtp);
            if (CihMBanking.sesPMTR.getBillerType() == 0) {
                CihMBanking.sesPMTR.mtcFavoriteBillListARR.clear();
                CihMBanking.sesPMTR.mtcFavoriteBillListImpayARR.clear();
                B3GUiManager.this.NavigateToPageById(25);
            } else {
                if (CihMBanking.sesPMTR.getBillerType() == 3) {
                    B3GUiManager.this.NavigateToPageById(41);
                    return;
                }
                B3GUiManager.this.NavigateToPageById(33);
                CihMBanking.sesPMTR.mtcFavoriteTopListARR.clear();
                CihMBanking.sesPMTR.mtcFavoriteTopListImpayARR.clear();
            }
        }
    }

    public Container GetPopupCardOpperationBtn(Dialog dialog, int i, B3gService b3gService, int i2, String str) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 36(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 37(dialog, b3gService, str, i2));
        container.addComponent(button2);
        return container;
    }

    class 36 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        36(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 37 implements ActionListener {
        final /* synthetic */ int val$ServiceIdWS;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ String val$motif;
        final /* synthetic */ B3gService val$pB3gService;

        37(Dialog dialog, B3gService b3gService, String str, int i) {
            this.val$d = dialog;
            this.val$pB3gService = b3gService;
            this.val$motif = str;
            this.val$ServiceIdWS = i;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            Card card = new Card();
            CardOpperationStatus cardOpperationStatus = (CardOpperationStatus) this.val$pB3gService;
            cardOpperationStatus.motif = this.val$motif;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(((CardOpperationStatus) card.CardOperationProcess(this.val$ServiceIdWS, cardOpperationStatus.plainCardNumber, cardOpperationStatus.cardNumber, cardOpperationStatus.newCardStatus, cardOpperationStatus.cardOpperationStatus, cardOpperationStatus.motif, cardOpperationStatus.pClientCardHolder).get(0)).opeartionStatusLabel), null);
            B3GUiManager.this.NavigateToPageById(14);
        }
    }

    public Container GetPopupCardOpperationBtnNew2(Dialog dialog, int i, B3gService b3gService) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 38(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 39(b3gService, dialog, i));
        container.addComponent(button2);
        return container;
    }

    class 38 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        38(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 39 implements ActionListener {
        final /* synthetic */ int val$ServiceId;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ B3gService val$pB3gService;

        39(B3gService b3gService, Dialog dialog, int i) {
            this.val$pB3gService = b3gService;
            this.val$d = dialog;
            this.val$ServiceId = i;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            ServiceResponse ActivateCardVirt;
            Card card = (Card) this.val$pB3gService;
            this.val$d.dispose();
            int i = this.val$ServiceId;
            if (i == 112) {
                ActivateCardVirt = card.ActivateCardVirt(card.plainCardNumber);
            } else {
                ActivateCardVirt = i != 113 ? null : card.PersCardVirt(card.plainCardNumber);
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, ActivateCardVirt.getStatusLabel(), null);
            if (ActivateCardVirt.getStatusCode().equals("000")) {
                CihMBanking.sesPMTR.getSessionClient().setClient_CardList(null);
                B3GUiManager.this.NavigateToPageById(4);
            }
        }
    }

    public Container GetPopupCardOpperationBtnWithRadio(Dialog dialog, int i, B3gService b3gService, int i2, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 40(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 41(i2, radioButton, radioButton2, dialog, b3gService));
        container.addComponent(button2);
        return container;
    }

    class 40 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        40(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            CardsDetailPageNew2.isCanceledAD = true;
        }
    }

    class 41 implements ActionListener {
        final /* synthetic */ RadioButton val$LostRd;
        final /* synthetic */ int val$ServiceIdWS;
        final /* synthetic */ RadioButton val$StoolRd;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ B3gService val$pB3gService;

        41(int i, RadioButton radioButton, RadioButton radioButton2, Dialog dialog, B3gService b3gService) {
            this.val$ServiceIdWS = i;
            this.val$LostRd = radioButton;
            this.val$StoolRd = radioButton2;
            this.val$d = dialog;
            this.val$pB3gService = b3gService;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String str;
            if (this.val$ServiceIdWS == 300037) {
                str = "";
            } else if (this.val$LostRd.isSelected()) {
                str = "Perte";
            } else {
                str = this.val$StoolRd.isSelected() ? "Vol" : "Autre";
            }
            this.val$d.dispose();
            CardsDetailPageNew2.isCanceledAD = false;
            Card card = new Card();
            CardOpperationStatus cardOpperationStatus = (CardOpperationStatus) this.val$pB3gService;
            cardOpperationStatus.motif = str;
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(((CardOpperationStatus) card.CardOperationProcess(this.val$ServiceIdWS, cardOpperationStatus.plainCardNumber, cardOpperationStatus.cardNumber, cardOpperationStatus.newCardStatus, cardOpperationStatus.cardOpperationStatus, cardOpperationStatus.motif, cardOpperationStatus.pClientCardHolder).get(0)).opeartionStatusLabel), null);
            B3GUiManager.this.NavigateToPageById(4);
        }
    }

    public Container GetPopupCardOpperationBtnNew(Dialog dialog, int i, B3gService b3gService, int i2) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 42(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 43(dialog, b3gService, i2));
        container.addComponent(button2);
        return container;
    }

    class 42 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        42(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            CardsDetailPageNew2.isCanceledAD = true;
        }
    }

    class 43 implements ActionListener {
        final /* synthetic */ int val$ServiceIdWS;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ B3gService val$pB3gService;

        43(Dialog dialog, B3gService b3gService, int i) {
            this.val$d = dialog;
            this.val$pB3gService = b3gService;
            this.val$ServiceIdWS = i;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            CardsDetailPageNew2.isCanceledAD = false;
            Card card = new Card();
            CardOpperationStatus cardOpperationStatus = (CardOpperationStatus) this.val$pB3gService;
            card.CardOperationProcessNew(this.val$ServiceIdWS, cardOpperationStatus.plainCardNumber, cardOpperationStatus.cardNumber, cardOpperationStatus.newCardStatus, cardOpperationStatus.cardOpperationStatus, cardOpperationStatus.motif, cardOpperationStatus.pClientCardHolder);
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, B3GUiManager.access$500(B3GUiManager.this, this.val$ServiceIdWS), null);
            CihMBanking.sesPMTR.cardfromHom = true;
            B3GUiManager.this.NavigateToPageById(4);
        }
    }

    public Container GetPopupCardOpperationMotifRadio(Dialog dialog, int i, B3gService b3gService, int i2, Resources resources) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        RadioButton radioButton = new RadioButton();
        radioButton.setUIID("cmd_ToggleButton");
        radioButton.setIcon(resources.getImage("radio_off.PNG"));
        radioButton.setPressedIcon(resources.getImage("radio_on.png"));
        radioButton.setVerticalAlignment(4);
        radioButton.setTextPosition(1);
        radioButton.setGroup("MOTIF");
        radioButton.setToggle(true);
        Label label = new Label("Perte");
        label.setUIID("dm_lblPageNumberW");
        label.setVerticalAlignment(4);
        label.setTextPosition(1);
        label.setFocusable(false);
        FlowLayout flowLayout2 = new FlowLayout();
        flowLayout2.setAlign(4);
        flowLayout2.setValign(4);
        flowLayout2.setValignByRow(true);
        Container container2 = new Container();
        container2.setLayout(flowLayout2);
        container2.addComponent(label);
        container2.addComponent(radioButton);
        container.addComponent(container2);
        RadioButton radioButton2 = new RadioButton();
        radioButton2.setUIID("cmd_ToggleButton");
        radioButton2.setIcon(resources.getImage("radio_off.PNG"));
        radioButton2.setPressedIcon(resources.getImage("radio_on.png"));
        radioButton2.setVerticalAlignment(4);
        radioButton2.setTextPosition(1);
        radioButton2.setGroup("MOTIF");
        radioButton2.setToggle(true);
        Label label2 = new Label("Vol");
        label2.setUIID("dm_lblPageNumberW");
        label2.setVerticalAlignment(4);
        label2.setTextPosition(1);
        label2.setFocusable(false);
        FlowLayout flowLayout3 = new FlowLayout();
        flowLayout3.setAlign(4);
        flowLayout3.setValign(4);
        flowLayout3.setValignByRow(true);
        Container container3 = new Container();
        container3.setLayout(flowLayout3);
        container3.addComponent(label2);
        container3.addComponent(radioButton2);
        container.addComponent(container3);
        RadioButton radioButton3 = new RadioButton();
        radioButton3.setUIID("cmd_ToggleButton");
        radioButton3.setIcon(resources.getImage("radio_off.PNG"));
        radioButton3.setPressedIcon(resources.getImage("radio_on.png"));
        radioButton3.setVerticalAlignment(4);
        radioButton3.setTextPosition(1);
        radioButton3.setGroup("MOTIF");
        radioButton3.setToggle(true);
        Label label3 = new Label("Autre");
        label3.setUIID("dm_lblPageNumberW");
        label3.setVerticalAlignment(4);
        label3.setTextPosition(1);
        label3.setFocusable(false);
        FlowLayout flowLayout4 = new FlowLayout();
        flowLayout4.setAlign(4);
        flowLayout4.setValign(4);
        flowLayout4.setValignByRow(true);
        Container container4 = new Container();
        container4.setLayout(flowLayout4);
        container4.addComponent(label3);
        container4.addComponent(radioButton3);
        container.addComponent(container4);
        radioButton3.setSelected(true);
        return container;
    }

    public Container GetPopupCheckBookBtn(Dialog dialog, int i, B3gService b3gService, int i2) {
        String str;
        String str2;
        CheckbookDemand checkbookDemand = (CheckbookDemand) b3gService;
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        if (i2 == 11) {
            str = "ANNULER";
            str2 = "VALIDER";
        } else {
            str = "QUITTER";
            str2 = "SUPPRIMER";
        }
        if (checkbookDemand.StatusDemand.equals("0")) {
            Button button = new Button(str);
            button.setTextPosition(3);
            button.setUIID("dg_BtnCancelIcon");
            button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
            button.setVerticalAlignment(4);
            button.addActionListener(new 44(dialog));
            container.addComponent(button);
            Button button2 = new Button(str2);
            button2.setUIID("dg_BtnYestIconTR");
            button2.setTextPosition(3);
            button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
            button2.setVerticalAlignment(4);
            button2.addActionListener(new 45(dialog, i2, b3gService));
            container.addComponent(button2);
        } else {
            Button button3 = new Button("");
            button3.setUIID("dg_BtnCancelIcon");
            button3.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
            button3.setVerticalAlignment(4);
            button3.addActionListener(new 46(dialog));
            container.addComponent(button3);
        }
        return container;
    }

    class 44 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        44(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 45 implements ActionListener {
        final /* synthetic */ int val$ServiceIdWS;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ B3gService val$pB3gService;

        45(Dialog dialog, int i, B3gService b3gService) {
            this.val$d = dialog;
            this.val$ServiceIdWS = i;
            this.val$pB3gService = b3gService;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            CihMBanking.sesPMTR.setTabPosition(0);
            if (this.val$ServiceIdWS == 11) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(new CheckbookDemand().CheckbookDemandAddProcess((CheckbookDemand) this.val$pB3gService)), null);
                B3GUiManager.this.NavigateToPageById(13);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(new CheckbookDemand().CheckbookDemandDeleteProcess((CheckbookDemand) this.val$pB3gService)), null);
                B3GUiManager.this.NavigateToPageById(13);
            }
        }
    }

    class 46 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        46(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public Container GetPopupLcnBookBtn(Dialog dialog, int i, B3gService b3gService, int i2) {
        String str;
        String str2;
        LCNBoockDemand lCNBoockDemand = (LCNBoockDemand) b3gService;
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        if (i2 == 11) {
            str = "ANNULER";
            str2 = "VALIDER";
        } else {
            str = "QUITTER";
            str2 = "SUPPRIMER";
        }
        if (lCNBoockDemand.StatusDemand.equals("0")) {
            Button button = new Button(str);
            button.setTextPosition(3);
            button.setUIID("dg_BtnCancelIcon");
            button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
            button.setVerticalAlignment(4);
            button.addActionListener(new 47(dialog));
            container.addComponent(button);
            Button button2 = new Button(str2);
            button2.setUIID("dg_BtnYestIconTR");
            button2.setTextPosition(3);
            button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
            button2.setVerticalAlignment(4);
            button2.addActionListener(new 48(dialog, i2, lCNBoockDemand));
            container.addComponent(button2);
        } else {
            Button button3 = new Button("");
            button3.setUIID("dg_BtnCancelIcon");
            button3.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
            button3.setVerticalAlignment(4);
            button3.addActionListener(new 49(dialog));
            container.addComponent(button3);
        }
        return container;
    }

    class 47 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        47(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 48 implements ActionListener {
        final /* synthetic */ int val$ServiceIdWS;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ LCNBoockDemand val$lcnBookDemand;

        48(Dialog dialog, int i, LCNBoockDemand lCNBoockDemand) {
            this.val$d = dialog;
            this.val$ServiceIdWS = i;
            this.val$lcnBookDemand = lCNBoockDemand;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            CihMBanking.sesPMTR.setTabPosition(1);
            if (this.val$ServiceIdWS == 11) {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(new LCNBoockDemand().LCNBoockDemandAddProcess(this.val$lcnBookDemand)), null);
                B3GUiManager.this.NavigateToPageById(13);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(new LCNBoockDemand().LCNBoockDemandDeleteProcess(this.val$lcnBookDemand)), null);
                B3GUiManager.this.NavigateToPageById(13);
            }
        }
    }

    class 49 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        49(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    public Container GetPopupRIBOpperationBtn(Dialog dialog, int i, B3gService b3gService, int i2) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 50(dialog));
        container.addComponent(button);
        Button button2 = new Button("ENVOYER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 51(dialog, b3gService));
        container.addComponent(button2);
        return container;
    }

    class 50 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        50(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 51 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ B3gService val$pB3gService;

        51(Dialog dialog, B3gService b3gService) {
            this.val$d = dialog;
            this.val$pB3gService = b3gService;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(new RIB().RiBProcess((RIB) this.val$pB3gService).getStatusLabel()), null);
        }
    }

    public Container GetPopupMTCFavoriteBillOpperationBtn(Dialog dialog, int i, B3gService b3gService, int i2) {
        MTCFavoriteBill mTCFavoriteBill = new MTCFavoriteBill();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 52(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 53(dialog, b3gService, mTCFavoriteBill));
        container.addComponent(button2);
        return container;
    }

    class 52 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        52(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 53 implements ActionListener {
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ MTCFavoriteBill val$mtcFavoriteBillMng;
        final /* synthetic */ B3gService val$pB3gService;

        53(Dialog dialog, B3gService b3gService, MTCFavoriteBill mTCFavoriteBill) {
            this.val$d = dialog;
            this.val$pB3gService = b3gService;
            this.val$mtcFavoriteBillMng = mTCFavoriteBill;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(this.val$mtcFavoriteBillMng.MTCFavoriteDeleteProcess(((MTCFavoriteBill) this.val$pB3gService).ReferenceNumber)), null);
            CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(29);
        }
    }

    public Container GetPopupMTCFavoriteBillOpperationBtnNew(Dialog dialog, int i, B3gService b3gService, int i2, int i3) {
        MTCFavoriteBill mTCFavoriteBill = new MTCFavoriteBill();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 54(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 55(dialog, b3gService, i3, mTCFavoriteBill));
        container.addComponent(button2);
        return container;
    }

    class 54 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        54(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 55 implements ActionListener {
        final /* synthetic */ int val$billerTypeId;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ MTCFavoriteBill val$mtcFavoriteBillMng;
        final /* synthetic */ B3gService val$pB3gService;

        55(Dialog dialog, B3gService b3gService, int i, MTCFavoriteBill mTCFavoriteBill) {
            this.val$d = dialog;
            this.val$pB3gService = b3gService;
            this.val$billerTypeId = i;
            this.val$mtcFavoriteBillMng = mTCFavoriteBill;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            MTCFavoriteBill mTCFavoriteBill = (MTCFavoriteBill) this.val$pB3gService;
            if (this.val$billerTypeId == 1) {
                CihMBanking.sesPMTR.mtcFavoriteTopListImpayARR.clear();
                CihMBanking.sesPMTR.mtcFavoriteTopListARR.clear();
            } else {
                CihMBanking.sesPMTR.mtcFavoriteBillListARR.clear();
                CihMBanking.sesPMTR.mtcFavoriteBillListImpayARR.clear();
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, DataTools.FormatUnicode(this.val$mtcFavoriteBillMng.MTCFavoriteDeleteProcess(mTCFavoriteBill.ReferenceNumber)), null);
            if (this.val$billerTypeId == 1) {
                CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(29);
            } else {
                CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPageById(25);
            }
        }
    }

    public Container GetPopupCallAdviserBtn(Dialog dialog, int i, B3gService b3gService, int i2, String str) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 56(dialog));
        container.addComponent(button);
        Button button2 = new Button("APPELER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 57(str));
        container.addComponent(button2);
        return container;
    }

    class 56 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        56(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 57 implements ActionListener {
        final /* synthetic */ String val$lPhone;

        57(String str) {
            this.val$lPhone = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().dial(this.val$lPhone);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00be A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00bf A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String GetFeeAmountMsgFromSession(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = ""
            java.lang.String r1 = "0.00"
            java.lang.String r2 = "TOPUP"
            boolean r2 = r5.equals(r2)     // Catch: java.lang.Exception -> Lb6
            java.lang.String r3 = "%"
            if (r2 == 0) goto L2c
            com.b3g.cih.online.SessionParameter r5 = com.b3g.cih.online.CihMBanking.sesPMTR     // Catch: java.lang.Exception -> Lb6
            com.b3g.services.Client r5 = r5.getSessionClient()     // Catch: java.lang.Exception -> Lb6
            com.b3g.services.Profile r5 = r5.getClient_Profil()     // Catch: java.lang.Exception -> Lb6
            java.lang.String r5 = r5.feeAmountBinatnaIM     // Catch: java.lang.Exception -> Lb6
            com.b3g.cih.online.SessionParameter r6 = com.b3g.cih.online.CihMBanking.sesPMTR     // Catch: java.lang.Exception -> Lb7
            com.b3g.services.Client r6 = r6.getSessionClient()     // Catch: java.lang.Exception -> Lb7
            com.b3g.services.Profile r6 = r6.getClient_Profil()     // Catch: java.lang.Exception -> Lb7
            java.lang.String r6 = r6.feeAmountMessage     // Catch: java.lang.Exception -> Lb7
            java.lang.String r6 = com.codename1.util.StringUtil.replaceAll(r6, r3, r5)     // Catch: java.lang.Exception -> Lb7
            goto Lb8
        L2c:
            java.lang.String r2 = "TRANSFER"
            boolean r2 = r5.equals(r2)     // Catch: java.lang.Exception -> Lb6
            if (r2 == 0) goto L6c
            r5 = 0
            r2 = 3
            java.lang.String r5 = r6.substring(r5, r2)     // Catch: java.lang.Exception -> Lb6
            java.lang.String r6 = "230"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Exception -> Lb6
            if (r5 == 0) goto L4f
            com.b3g.cih.online.SessionParameter r5 = com.b3g.cih.online.CihMBanking.sesPMTR     // Catch: java.lang.Exception -> Lb6
            com.b3g.services.Client r5 = r5.getSessionClient()     // Catch: java.lang.Exception -> Lb6
            com.b3g.services.Profile r5 = r5.getClient_Profil()     // Catch: java.lang.Exception -> Lb6
            java.lang.String r5 = r5.feeAmountTransfertIM     // Catch: java.lang.Exception -> Lb6
            goto L5b
        L4f:
            com.b3g.cih.online.SessionParameter r5 = com.b3g.cih.online.CihMBanking.sesPMTR     // Catch: java.lang.Exception -> Lb6
            com.b3g.services.Client r5 = r5.getSessionClient()     // Catch: java.lang.Exception -> Lb6
            com.b3g.services.Profile r5 = r5.getClient_Profil()     // Catch: java.lang.Exception -> Lb6
            java.lang.String r5 = r5.feeAmountTransfertConfIM     // Catch: java.lang.Exception -> Lb6
        L5b:
            com.b3g.cih.online.SessionParameter r6 = com.b3g.cih.online.CihMBanking.sesPMTR     // Catch: java.lang.Exception -> Lb7
            com.b3g.services.Client r6 = r6.getSessionClient()     // Catch: java.lang.Exception -> Lb7
            com.b3g.services.Profile r6 = r6.getClient_Profil()     // Catch: java.lang.Exception -> Lb7
            java.lang.String r6 = r6.feeAmountMessage     // Catch: java.lang.Exception -> Lb7
            java.lang.String r6 = com.codename1.util.StringUtil.replaceAll(r6, r3, r5)     // Catch: java.lang.Exception -> Lb7
            goto Lb8
        L6c:
            java.lang.String r6 = "DebitCard"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Exception -> Lb6
            if (r5 == 0) goto L91
            com.b3g.cih.online.SessionParameter r5 = com.b3g.cih.online.CihMBanking.sesPMTR     // Catch: java.lang.Exception -> Lb6
            com.b3g.services.Client r5 = r5.getSessionClient()     // Catch: java.lang.Exception -> Lb6
            com.b3g.services.Profile r5 = r5.getClient_Profil()     // Catch: java.lang.Exception -> Lb6
            java.lang.String r5 = r5.feeAmountDebitCard     // Catch: java.lang.Exception -> Lb6
            com.b3g.cih.online.SessionParameter r6 = com.b3g.cih.online.CihMBanking.sesPMTR     // Catch: java.lang.Exception -> Lb7
            com.b3g.services.Client r6 = r6.getSessionClient()     // Catch: java.lang.Exception -> Lb7
            com.b3g.services.Profile r6 = r6.getClient_Profil()     // Catch: java.lang.Exception -> Lb7
            java.lang.String r6 = r6.feeAmountMessage     // Catch: java.lang.Exception -> Lb7
            java.lang.String r6 = com.codename1.util.StringUtil.replaceAll(r6, r3, r5)     // Catch: java.lang.Exception -> Lb7
            goto Lb8
        L91:
            com.b3g.cih.online.SessionParameter r5 = com.b3g.cih.online.CihMBanking.sesPMTR     // Catch: java.lang.Exception -> Lb6
            com.b3g.services.Client r5 = r5.getSessionClient()     // Catch: java.lang.Exception -> Lb6
            com.b3g.services.Profile r5 = r5.getClient_Profil()     // Catch: java.lang.Exception -> Lb6
            java.lang.String r5 = r5.feeAmountCardOpperation     // Catch: java.lang.Exception -> Lb6
            com.b3g.cih.online.SessionParameter r6 = com.b3g.cih.online.CihMBanking.sesPMTR     // Catch: java.lang.Exception -> Lb7
            com.b3g.services.Client r6 = r6.getSessionClient()     // Catch: java.lang.Exception -> Lb7
            com.b3g.services.Profile r6 = r6.getClient_Profil()     // Catch: java.lang.Exception -> Lb7
            java.lang.String r6 = r6.feeAmountMessage     // Catch: java.lang.Exception -> Lb7
            java.lang.String r6 = com.codename1.util.StringUtil.replaceAll(r6, r3, r5)     // Catch: java.lang.Exception -> Lb7
            java.lang.String r2 = "HT"
            java.lang.String r3 = "TTC"
            java.lang.String r6 = com.codename1.util.StringUtil.replaceAll(r6, r2, r3)     // Catch: java.lang.Exception -> Lb7
            goto Lb8
        Lb6:
            r5 = r1
        Lb7:
            r6 = r0
        Lb8:
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto Lbf
            return r0
        Lbf:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.B3GUiManager.GetFeeAmountMsgFromSession(java.lang.String, java.lang.String):java.lang.String");
    }

    class 58 implements ActionListener {
        58() {
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new B3GUiManager$58$1$$ExternalSyntheticLambda0(this)).schedule(300, false, B3GUiManager.this.currentForm);
            }

            /* synthetic */ void lambda$run$0$com-b3g-ui-B3GUiManager$58$1() {
                B3GUiManager.this.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }

    public Container GetGoBackHome() {
        Container createContainer = CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.createContainer(CihMBanking.sesPMTR.getCurrentUiManager().ressource, "ErrorCnt");
        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.findBtnGoBackHome(createContainer).addActionListener(new 58());
        return createContainer;
    }

    public Container GetGoBackHome(String str) {
        Container createContainer = CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.createContainer(CihMBanking.sesPMTR.getCurrentUiManager().ressource, "ErrorCnt");
        ((SpanLabel) this.uib.findByName("spAboutOneBlocValue", createContainer)).setText(str);
        CihMBanking.sesPMTR.getCurrentUiManager().stateMachine.findBtnGoBackHome(createContainer).addActionListener(new 59());
        return createContainer;
    }

    class 59 implements ActionListener {
        59() {
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new B3GUiManager$59$1$$ExternalSyntheticLambda0(this)).schedule(300, false, B3GUiManager.this.currentForm);
            }

            /* synthetic */ void lambda$run$0$com-b3g-ui-B3GUiManager$59$1() {
                B3GUiManager.this.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }

    public void DrawKeyBoard(Container container, TextArea textArea) {
        ArrayList GetRandomIntArray = DataTools.GetRandomIntArray(10);
        TableLayout tableLayout = new TableLayout(3, 4);
        container.setLayout(tableLayout);
        container.removeAll();
        for (int i = 0; i < 10; i++) {
            String str = "" + GetRandomIntArray.get(i);
            Button button = new Button("" + GetRandomIntArray.get(i));
            button.setUIID("lo_btnVirtualKeyBoard");
            button.setVerticalAlignment(4);
            button.addActionListener(new 60(textArea, str));
            TableLayout.Constraint createConstraint = tableLayout.createConstraint();
            createConstraint.setWidthPercentage(25);
            container.addComponent(createConstraint, button);
        }
        Button button2 = new Button("  ");
        button2.setUIID("lo_btnVirtualKeyBoardDeleteWithIcon");
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 61(textArea));
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(45);
        createConstraint2.setHorizontalSpan(2);
        container.addComponent(createConstraint2, button2);
    }

    class 60 implements ActionListener {
        final /* synthetic */ TextArea val$PassField;
        final /* synthetic */ String val$number;

        60(TextArea textArea, String str) {
            this.val$PassField = textArea;
            this.val$number = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$PassField.setText(this.val$PassField.getText() + this.val$number);
        }
    }

    class 61 implements ActionListener {
        final /* synthetic */ TextArea val$PassField;

        61(TextArea textArea) {
            this.val$PassField = textArea;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$PassField.getText().length() > 0) {
                TextArea textArea = this.val$PassField;
                textArea.setText(textArea.getText().substring(0, this.val$PassField.getText().length() - 1));
            } else {
                TextArea textArea2 = this.val$PassField;
                textArea2.setText(textArea2.getText());
            }
        }
    }

    public void DrawKeyBoard2H(Container container, TextArea textArea, String str) {
        ArrayList GetRandomIntArray = DataTools.GetRandomIntArray(10);
        container.setLayout(new GridLayout(3, 1));
        container.removeAll();
        container.setUIID("Container");
        Container DrawOneKeyBoardRowH = DrawOneKeyBoardRowH(textArea, GetRandomIntArray, 0, 0, 1, 4);
        Container DrawOneKeyBoardRowH2 = DrawOneKeyBoardRowH(textArea, GetRandomIntArray, 4, 1, 1, 4);
        Container DrawOneKeyBoardRowH3 = DrawOneKeyBoardRowH(textArea, GetRandomIntArray, 8, 2, 1, 3);
        container.addComponent(DrawOneKeyBoardRowH);
        container.revalidate();
        container.addComponent(DrawOneKeyBoardRowH2);
        container.revalidate();
        container.addComponent(DrawOneKeyBoardRowH3);
        container.revalidate();
    }

    public Container DrawOneKeyBoardRowH(TextArea textArea, ArrayList arrayList, int i, int i2, int i3, int i4) {
        Container container = new Container();
        TableLayout tableLayout = new TableLayout(i3, i4);
        container.setLayout(tableLayout);
        container.removeAll();
        container.setUIID("CntBtnKeyBoardV2H");
        if (i2 != 2) {
            for (int i5 = i; i5 <= i + 3; i5++) {
                String str = "" + arrayList.get(i5);
                Button button = new Button("" + arrayList.get(i5));
                button.setUIID("lg_BtnV2_emp_intern_H");
                button.setVerticalAlignment(4);
                button.addActionListener(new 62(textArea, str));
                TableLayout.Constraint createConstraint = tableLayout.createConstraint();
                createConstraint.setWidthPercentage(25);
                container.addComponent(createConstraint, button);
            }
        } else {
            for (int i6 = i; i6 <= i + 1; i6++) {
                String str2 = "" + arrayList.get(i6);
                Button button2 = new Button("" + arrayList.get(i6));
                button2.setUIID("lg_BtnV2_emp_intern_H");
                button2.setVerticalAlignment(4);
                button2.addActionListener(new 63(textArea, str2));
                TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
                createConstraint2.setWidthPercentage(25);
                container.addComponent(createConstraint2, button2);
            }
            Button button3 = new Button("<");
            button3.setUIID("lg_BtnV2_emp_intern_H");
            button3.setVerticalAlignment(4);
            button3.addActionListener(new 64(textArea));
            TableLayout.Constraint createConstraint3 = tableLayout.createConstraint();
            createConstraint3.setWidthPercentage(50);
            container.addComponent(createConstraint3, button3);
        }
        return container;
    }

    class 62 implements ActionListener {
        final /* synthetic */ TextArea val$PassField;
        final /* synthetic */ String val$number;

        62(TextArea textArea, String str) {
            this.val$PassField = textArea;
            this.val$number = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$PassField.setText(this.val$PassField.getText() + this.val$number);
        }
    }

    class 63 implements ActionListener {
        final /* synthetic */ TextArea val$PassField;
        final /* synthetic */ String val$number;

        63(TextArea textArea, String str) {
            this.val$PassField = textArea;
            this.val$number = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$PassField.setText(this.val$PassField.getText() + this.val$number);
        }
    }

    class 64 implements ActionListener {
        final /* synthetic */ TextArea val$PassField;

        64(TextArea textArea) {
            this.val$PassField = textArea;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$PassField.getText().length() > 0) {
                TextArea textArea = this.val$PassField;
                textArea.setText(textArea.getText().substring(0, this.val$PassField.getText().length() - 1));
            } else {
                TextArea textArea2 = this.val$PassField;
                textArea2.setText(textArea2.getText());
            }
        }
    }

    class 65 implements DataChangedListener {
        final /* synthetic */ Container val$ParentContainer;
        final /* synthetic */ TextField val$textField;

        65(TextField textField, Container container) {
            this.val$textField = textField;
            this.val$ParentContainer = container;
        }

        public void dataChanged(int i, int i2) {
            String[] split;
            if (this.val$textField.getText().contains(",")) {
                split = DataTools.split(this.val$textField.getText(), ",");
            } else {
                split = DataTools.split(this.val$textField.getText(), ".");
            }
            if (split.length > 1 && split[1].length() > 2) {
                this.val$textField.setText(this.val$textField.getText().substring(0, this.val$textField.getText().length() - 1));
                this.val$textField.repaint();
            }
            this.val$textField.repaint();
            this.val$ParentContainer.revalidate();
        }
    }

    public void ControlTextfield(TextField textField, String str, Container container) {
        if (str.equals("AMOUNT")) {
            textField.addDataChangeListener(new 65(textField, container));
        }
        textField.repaint();
        container.revalidate();
    }

    public void ShowDialogMTCServiceRegroupement(int i, B3gService b3gService, StateMachine stateMachine, B3gService b3gService2, int i2, String str) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Resources open = Resources.open("/cihv3.res");
            Container createContainer = stateMachine.createContainer(open, "PopUp");
            ArrayList GetPopupItemDetail = GetPopupItemDetail(i, b3gService, stateMachine, open, createContainer, b3gService2);
            for (int i3 = 0; i3 < GetPopupItemDetail.size(); i3++) {
                stateMachine.findCntPopupBody(createContainer).addComponent((Component) GetPopupItemDetail.get(i3));
            }
            Label label = new Label(" ");
            label.setUIID("dg_lblPopUpDemo");
            label.setVerticalAlignment(4);
            stateMachine.findCntPopupBody(createContainer).addComponent(label);
            stateMachine.findCntPopupBody(createContainer).addComponent(GetPopupMTCConfirmationRegroupementBtn(dialog, i, b3gService, i2, str));
            stateMachine.findLblpopupItemTitleV2(createContainer).setText("paiement global des factures");
            stateMachine.findLblpopupItemValueV2(createContainer).setText("");
            createContainer.setPreferredH(CihMBanking.sesPMTR.displayHeight);
            createContainer.setPreferredW(CihMBanking.sesPMTR.DisplayWith);
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
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    public Container GetPopupMTCConfirmationRegroupementBtn(Dialog dialog, int i, B3gService b3gService, int i2, String str) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        Button button = new Button("ANNULER");
        button.setTextPosition(3);
        button.setUIID("dg_BtnCancelIcon");
        button.setIcon(this.ressource.getImage("ico_close_popup_NW.png"));
        button.setVerticalAlignment(4);
        button.addActionListener(new 66(dialog));
        container.addComponent(button);
        Button button2 = new Button("VALIDER");
        button2.setUIID("dg_BtnYestIconTR");
        button2.setTextPosition(3);
        button2.setIcon(this.ressource.getImage("ico_check_popup_NW.png"));
        button2.setVerticalAlignment(4);
        button2.addActionListener(new 67(dialog, b3gService, str));
        container.addComponent(button2);
        return container;
    }

    class 66 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        66(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }

    class 67 implements ActionListener {
        final /* synthetic */ String val$codeOtp;
        final /* synthetic */ Dialog val$d;
        final /* synthetic */ B3gService val$pB3gService;

        67(Dialog dialog, B3gService b3gService, String str) {
            this.val$d = dialog;
            this.val$pB3gService = b3gService;
            this.val$codeOtp = str;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            new MTCConfirmationResume().MTCConfirmationRegroupementProcess((MTCConfirmationResume) this.val$pB3gService, this.val$codeOtp);
            if (CihMBanking.sesPMTR.getBillerType() == 0) {
                CihMBanking.sesPMTR.mtcFavoriteBillListARR.clear();
                CihMBanking.sesPMTR.mtcFavoriteBillListImpayARR.clear();
                B3GUiManager.this.NavigateToPageById(25);
            } else {
                if (CihMBanking.sesPMTR.getBillerType() == 3) {
                    B3GUiManager.this.NavigateToPageById(41);
                    return;
                }
                B3GUiManager.this.NavigateToPageById(33);
                CihMBanking.sesPMTR.mtcFavoriteTopListARR.clear();
                CihMBanking.sesPMTR.mtcFavoriteTopListImpayARR.clear();
            }
        }
    }

    public void ShowMessageTransactionForSinistre(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMessage");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            stateMachine.findSpanLabel(createContainer).setText(str);
            stateMachine.findLabel1(createContainer).setText(str2);
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 68(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 68 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        68(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
            B3GUiManager.this.NavigateToPageById(135);
        }
    }

    public void ShowMessageSinistre(StateMachine stateMachine, String str, String str2) {
        Dialog dialog = new Dialog();
        dialog.setLayout(new BorderLayout());
        try {
            Container createContainer = stateMachine.createContainer(Resources.open("/cihv3.res"), "PopUpMessage");
            createContainer.setPreferredH(Display.getInstance().getDisplayHeight());
            createContainer.setPreferredW(Display.getInstance().getDisplayWidth());
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
            stateMachine.findSpanLabel(createContainer).setText(str);
            stateMachine.findLabel1(createContainer).setText(str2);
            stateMachine.findBtnNoIcon(createContainer).addActionListener(new 69(dialog));
            dialog.addComponent("Center", createContainer);
            dialog.showPacked("Center", true);
        } catch (IOException unused) {
        }
    }

    class 69 implements ActionListener {
        final /* synthetic */ Dialog val$d;

        69(Dialog dialog) {
            this.val$d = dialog;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.val$d.dispose();
        }
    }
}
