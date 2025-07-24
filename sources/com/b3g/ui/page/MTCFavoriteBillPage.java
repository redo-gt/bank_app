package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceRequest;
import com.b3g.common.ServiceResponse;
import com.b3g.services.B3gService;
import com.b3g.services.MTCFavoriteBill;
import com.b3g.services.MTCImpaye;
import com.b3g.services.ServiceManager;
import com.b3g.tools.SyncTask;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MTCFavoriteBillPage extends B3GPage {
    ArrayList MTCFavoriteBillList;
    String refIndex = "";

    static /* synthetic */ void access$000(MTCFavoriteBillPage mTCFavoriteBillPage, ArrayList arrayList, Container container) {
        mTCFavoriteBillPage.SetFavorisView(arrayList, container);
    }

    static /* synthetic */ ServiceResponse access$100(MTCFavoriteBillPage mTCFavoriteBillPage, String str, String str2, String str3, String str4) {
        return mTCFavoriteBillPage.renameFavItem(str, str2, str3, str4);
    }

    public MTCFavoriteBillPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "GloabalContainerV2");
        createContainer.setScrollableY(false);
        createContainer.setScrollVisible(false);
        Container container = new Container(BoxLayout.y());
        container.setScrollableY(true);
        container.setScrollVisible(false);
        this.uiManager.stateMachine.findLblGlobalHeaderTitle(createContainer).setText("Mes Favoris");
        this.thisContainer.setUIID("mn_cntMenuItem");
        this.thisContainer.setScrollableY(true);
        this.thisContainer.setScrollVisible(false);
        try {
            this.refIndex = "";
            MTCFavoriteBill mTCFavoriteBill = new MTCFavoriteBill();
            container.removeAll();
            if (CihMBanking.sesPMTR.mtcFavoriteTopListImpayARR.size() > 0) {
                ArrayList arrayList = CihMBanking.sesPMTR.mtcFavoriteTopListImpayARR;
                this.MTCFavoriteBillList = arrayList;
                if (arrayList.size() > 0) {
                    CihMBanking.sesPMTR.setIsFavorite("0");
                    CihMBanking.sesPMTR.setCurrentMTCSelectedAlias(((MTCFavoriteBill) this.MTCFavoriteBillList.get(0)).Alias);
                }
                for (int i = 0; i < this.MTCFavoriteBillList.size(); i++) {
                    container.addComponent(drawMTCItem((MTCFavoriteBill) this.MTCFavoriteBillList.get(i), false));
                }
            } else {
                if (CihMBanking.sesPMTR.mtcFavoriteTopListARR.isEmpty()) {
                    ArrayList MTCFavoriteImpayeProcess = mTCFavoriteBill.MTCFavoriteImpayeProcess(CihMBanking.sesPMTR.getBillerType(), "", false, 900069);
                    this.MTCFavoriteBillList = MTCFavoriteImpayeProcess;
                    if (MTCFavoriteImpayeProcess.size() > 0) {
                        CihMBanking.sesPMTR.setIsFavorite("0");
                        CihMBanking.sesPMTR.setCurrentMTCSelectedAlias(((MTCFavoriteBill) this.MTCFavoriteBillList.get(0)).Alias);
                        for (int i2 = 0; i2 < this.MTCFavoriteBillList.size(); i2++) {
                            container.addComponent(drawMTCItem((MTCFavoriteBill) this.MTCFavoriteBillList.get(i2), true));
                        }
                    } else {
                        FlowLayout flowLayout = new FlowLayout();
                        flowLayout.setAlign(4);
                        flowLayout.setValign(4);
                        flowLayout.setFillRows(true);
                        Container container2 = new Container();
                        container2.setLayout(flowLayout);
                        container2.setUIID("g_CntTranspMsg");
                        SpanLabel spanLabel = new SpanLabel();
                        spanLabel.setUIID("dg_splblMsgCenter");
                        spanLabel.setScrollVisible(false);
                        spanLabel.setText("Vous n'avez aucun favori");
                        spanLabel.setIconPosition("West");
                        spanLabel.setTextUIID("dg_lblSPError");
                        spanLabel.setIconUIID("g_cntEmpty");
                        container2.addComponent(spanLabel);
                        container.addComponent(container2);
                    }
                }
                if (this.MTCFavoriteBillList.size() > 0) {
                    Display.getInstance().scheduleBackgroundTask(new MTCFavoriteBillPage$$ExternalSyntheticLambda0(this, container));
                }
            }
            createContainer.addComponent(container);
            this.thisContainer.add(createContainer);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    /* synthetic */ void lambda$GetPageContainer$1$com-b3g-ui-page-MTCFavoriteBillPage(Container container) {
        Display.getInstance().callSerially(new MTCFavoriteBillPage$$ExternalSyntheticLambda1(this, container));
    }

    /* synthetic */ void lambda$GetPageContainer$0$com-b3g-ui-page-MTCFavoriteBillPage(Container container) {
        new getFacorisService(this, null).execute(container);
    }

    private class getFacorisService extends SyncTask {
        Container c1;

        private getFacorisService() {
            this.c1 = new Container(BoxLayout.y());
        }

        /* synthetic */ getFacorisService(MTCFavoriteBillPage mTCFavoriteBillPage, 1 r2) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public ArrayList doInBackground(Container... containerArr) {
            this.c1 = containerArr[0];
            ArrayList MTCFavoriteImpayeProcess = new MTCFavoriteBill().MTCFavoriteImpayeProcess(CihMBanking.sesPMTR.getBillerType(), MTCFavoriteBillPage.this.refIndex, true, 910063);
            if (MTCFavoriteImpayeProcess.size() > 0) {
                MTCFavoriteBillPage.this.refIndex = ((MTCFavoriteBill) MTCFavoriteImpayeProcess.get(MTCFavoriteImpayeProcess.size() - 1)).ReferenceNumber;
            }
            return MTCFavoriteImpayeProcess;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onPostExecute(ArrayList arrayList) {
            MTCFavoriteBillPage.access$000(MTCFavoriteBillPage.this, arrayList, this.c1);
            this.c1.revalidate();
            if (arrayList.size() <= 0 || arrayList.size() != 5) {
                return;
            }
            MTCFavoriteBillPage.this.new getFacorisService().execute(this.c1);
        }
    }

    private void SetFavorisView(ArrayList arrayList, Container container) {
        for (int i = 0; i < container.getComponentCount(); i++) {
            try {
                if (arrayList.size() > 0) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        MTCFavoriteBill mTCFavoriteBill = (MTCFavoriteBill) it.next();
                        if (mTCFavoriteBill.ReferenceNumber.equals(((FavItem) container.getComponentAt(i)).favBill.ReferenceNumber) && mTCFavoriteBill.MTCImpayeList != null) {
                            ((FavItem) container.getComponentAt(i)).setFavBill(mTCFavoriteBill);
                            if (mTCFavoriteBill.MTCImpayeList.size() > 0) {
                                ((FavItem) container.getComponentAt(i)).labelIdentifiant.setText(((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(0)).FormInputValue);
                                if (mTCFavoriteBill.BillerId.equals("1024") && ((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(0)).Libelle.equals("Solde")) {
                                    ((FavItem) container.getComponentAt(i)).lblSoldeJwz.setText("Solde : ");
                                    ((FavItem) container.getComponentAt(i)).lblSoldeAmountJwz.setText(((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(0)).ValeurChamp + " DH");
                                }
                            } else {
                                ((FavItem) container.getComponentAt(i)).labelIdentifiant.setText("");
                            }
                        }
                    }
                }
            } catch (Exception unused) {
                return;
            }
        }
    }

    private class FavItem extends Container {
        MTCFavoriteBill favBill;
        Label labelIdentifiant;
        Label lblSoldeAmountJwz;
        Label lblSoldeJwz;

        public FavItem(MTCFavoriteBill mTCFavoriteBill) {
            this.favBill = mTCFavoriteBill;
        }

        public FavItem() {
        }

        public MTCFavoriteBill getFavBill() {
            return this.favBill;
        }

        public void setFavBill(MTCFavoriteBill mTCFavoriteBill) {
            this.favBill = mTCFavoriteBill;
        }
    }

    public FavItem drawMTCItem(MTCFavoriteBill mTCFavoriteBill, boolean z) {
        TableLayout tableLayout = new TableLayout(1, 4);
        FavItem favItem = new FavItem(mTCFavoriteBill);
        favItem.setLayout(tableLayout);
        favItem.setUIID("Container");
        favItem.setFocusable(false);
        favItem.setScrollable(false);
        TableLayout tableLayout2 = new TableLayout(1, 1);
        Container container = new Container();
        container.setLayout(tableLayout2);
        container.setUIID("MTCIconStyMTCNoMarginPading");
        container.setFocusable(false);
        container.setScrollable(false);
        Button button = new Button();
        button.setUIID("crd_btnCardImage");
        button.setIcon(this.uiManager.ressource.getImage("black_pic.png"));
        button.setIcon(URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(button.getIcon().getWidth(), button.getIcon().getWidth(), 12569042), false), "Normal_https://www.cihnet.co.ma" + mTCFavoriteBill.MTCCreancier.LogoPath, "https://www.cihnet.co.ma" + mTCFavoriteBill.MTCCreancier.LogoPath, URLImage.RESIZE_SCALE));
        button.setScrollVisible(false);
        button.setFocusable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(100);
        createConstraint.setVerticalAlign(0);
        container.addComponent(createConstraint, button);
        Container container2 = new Container(new BoxLayout(2));
        container2.setUIID("st_cntStatisticLeftBloc");
        container2.setFocusable(false);
        container2.setScrollVisible(false);
        Container container3 = new Container(new BoxLayout(1));
        Container container4 = new Container(new BoxLayout(1));
        Container container5 = new Container(new BoxLayout(2));
        container5.setUIID("g_cntDebitCredit");
        container5.setFocusable(false);
        container5.setScrollVisible(false);
        TextField textField = new TextField("" + mTCFavoriteBill.Alias);
        textField.setUIID("ac_lblitemDetailBlue");
        textField.setVerticalAlignment(4);
        textField.setEditable(false);
        Label label = new Label("Recharger");
        label.setUIID("mtc_lblitem_green");
        Label label2 = new Label("Chargement...");
        Label label3 = new Label("Solde : ");
        Label label4 = new Label();
        label3.setUIID("g_lblDashBoardTitleOrange");
        label4.setUIID("g_lblDashBoardTitleOrange");
        label2.setUIID("ac_lblitemDetailBlue");
        label2.setVerticalAlignment(4);
        label2.setTextPosition(3);
        container5.addComponent(container4);
        Button button2 = new Button();
        button2.setUIID("mtc_lblitem_green");
        button2.setVerticalAlignment(4);
        button2.setTextPosition(1);
        container5.addComponent(textField);
        favItem.labelIdentifiant = label2;
        favItem.lblSoldeJwz = label3;
        favItem.lblSoldeAmountJwz = label4;
        container4.addComponent(favItem.labelIdentifiant);
        container4.addComponent(new Label("    "));
        container4.addComponent(container3);
        container3.addComponent(favItem.lblSoldeJwz);
        container3.addComponent(favItem.lblSoldeAmountJwz);
        container3.getStyle().setMargin(0, 0, 0, 3);
        container5.addComponent(label);
        container5.addComponent(button2);
        container5.setLeadComponent(button2);
        container2.addComponent(container5);
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint(0, 0);
        createConstraint2.setWidthPercentage(20);
        createConstraint2.setHorizontalAlign(4);
        createConstraint2.setVerticalAlign(0);
        favItem.addComponent(createConstraint2, container);
        TableLayout.Constraint createConstraint3 = tableLayout.createConstraint(0, 1);
        createConstraint3.setWidthPercentage(60);
        createConstraint3.setVerticalAlign(0);
        favItem.addComponent(createConstraint3, container2);
        Button button3 = new Button();
        button3.setUIID("ln_btnLoanDetailMTC");
        button3.setText("Historique");
        button3.setTextPosition(1);
        button3.setVerticalAlignment(4);
        button3.setScrollVisible(false);
        button3.setFocusable(false);
        button3.addActionListener(new 1(mTCFavoriteBill));
        button2.addActionListener(new 2(favItem));
        Button button4 = new Button();
        button4.setUIID("crd_btnMTCFavori");
        button4.setIcon(this.uiManager.ressource.getImage("deleteBtnOrange.png"));
        button4.setScrollVisible(false);
        button4.setFocusable(false);
        button4.addActionListener(new 3(favItem));
        Button button5 = new Button();
        button5.setUIID("crd_btnMTCFavori");
        button5.setIcon(this.uiManager.ressource.getImage("editBtnBlue.png"));
        button5.setScrollVisible(false);
        button5.setFocusable(false);
        button5.addActionListener(new 4(button5, textField, container5, mTCFavoriteBill, button2));
        if (!mTCFavoriteBill.MTCCreancier.CodeCreancier.equals("1024")) {
            label3.setHidden(true);
            label4.setHidden(true);
        }
        if (z) {
            label3.setText("");
            label4.setText("");
            label2.setText("Chargement...");
        } else if (mTCFavoriteBill.MTCImpayeList.size() > 0) {
            label2.setText(((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(0)).FormInputValue);
            if (mTCFavoriteBill.BillerId.equals("1024") && ((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(0)).Libelle.equals("Solde")) {
                label3.setText("Solde : ");
                label4.setText(((MTCImpaye) mTCFavoriteBill.MTCImpayeList.get(0)).ValeurChamp + " DH");
            }
        } else {
            label2.setText("");
            label3.setText("");
            label4.setText("");
        }
        TableLayout tableLayout3 = new TableLayout(3, 1);
        Container container6 = new Container(tableLayout3);
        container6.setFocusable(false);
        container6.setScrollVisible(false);
        container6.addComponent(tableLayout3.createConstraint(0, 0).heightPercentage(45).widthPercentage(100), button5);
        container6.addComponent(tableLayout3.createConstraint(2, 0).heightPercentage(45).widthPercentage(100), button4);
        container6.addComponent(tableLayout3.createConstraint(1, 0).heightPercentage(10).widthPercentage(100), new Container());
        Container container7 = new Container(new GridLayout(1, 1));
        container7.setUIID("MTCIconStyMTCNoMarginPadingRight");
        container7.addComponent(container6);
        TableLayout.Constraint createConstraint4 = tableLayout.createConstraint(0, 2);
        createConstraint4.setWidthPercentage(10);
        createConstraint4.setVerticalAlign(4);
        favItem.addComponent(createConstraint4, new Container());
        TableLayout.Constraint createConstraint5 = tableLayout.createConstraint(0, 3);
        createConstraint5.setWidthPercentage(10);
        createConstraint5.setVerticalAlign(4);
        favItem.addComponent(createConstraint5, container7);
        favItem.setName(mTCFavoriteBill.ReferenceNumber);
        Component container8 = new Container();
        container8.setUIID("g_CntTranspMsg");
        favItem.addComponent(container8);
        return favItem;
    }

    class 1 implements ActionListener {
        final /* synthetic */ MTCFavoriteBill val$mtcFavoriteBillN;

        1(MTCFavoriteBill mTCFavoriteBill) {
            this.val$mtcFavoriteBillN = mTCFavoriteBill;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            MTCFavoriteBillPage.this.uiManager.NavigateToPage(new MTCFavoriteHistoricPage(MTCFavoriteBillPage.this.uiManager, this.val$mtcFavoriteBillN, 28));
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ FavItem val$cntGlobalTablelayoutFBN;

        2(FavItem favItem) {
            this.val$cntGlobalTablelayoutFBN = favItem;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.setCurrentMTCImpaye(this.val$cntGlobalTablelayoutFBN.favBill.MTCImpayeList);
            CihMBanking.sesPMTR.setSelectedMTCCreancier(this.val$cntGlobalTablelayoutFBN.favBill.MTCCreancier);
            CihMBanking.sesPMTR.setSelectedMTCCreance(this.val$cntGlobalTablelayoutFBN.favBill.MTCCreance);
            if (CihMBanking.sesPMTR.getBillerType() == 1) {
                if (this.val$cntGlobalTablelayoutFBN.favBill.MTCImpayeList.size() > 0) {
                    ((B3GPage) MTCFavoriteBillPage.this.uiManager.NavigationHistory.get(MTCFavoriteBillPage.this.uiManager.NavigationHistory.size() - 1)).PageSession = this.val$cntGlobalTablelayoutFBN.favBill;
                    MTCFavoriteBillPage.this.uiManager.NavigateToPageById(32);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MTCFavoriteBillPage.this.uiManager.stateMachine, "Vous n'avez aucune facture", null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MTCFavoriteBillPage.this.uiManager.stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
        }
    }

    class 3 implements ActionListener {
        final /* synthetic */ FavItem val$cntGlobalTablelayoutFBN;

        3(FavItem favItem) {
            this.val$cntGlobalTablelayoutFBN = favItem;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CihMBanking.sesPMTR.getCurrentUiManager().ShowDialogFavoriteDeleteOpperationServiceNew(32, this.val$cntGlobalTablelayoutFBN.favBill, CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, null, 29, CihMBanking.sesPMTR.getBillerType());
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Button val$btnRenameFavItem;
        final /* synthetic */ Container val$cntMTCCImpayeIndicatorFB;
        final /* synthetic */ TextField val$lblAliasValue;
        final /* synthetic */ Button val$lblCreanceImpayeList;
        final /* synthetic */ MTCFavoriteBill val$mtcFavoriteBillN;

        4(Button button, TextField textField, Container container, MTCFavoriteBill mTCFavoriteBill, Button button2) {
            this.val$btnRenameFavItem = button;
            this.val$lblAliasValue = textField;
            this.val$cntMTCCImpayeIndicatorFB = container;
            this.val$mtcFavoriteBillN = mTCFavoriteBill;
            this.val$lblCreanceImpayeList = button2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$btnRenameFavItem.getIcon().getImageName().equals("editBtnBlue.png")) {
                this.val$lblAliasValue.setUIID("ContainerBorderGray");
                this.val$lblAliasValue.setEditable(true);
                this.val$lblAliasValue.setFocusable(true);
                this.val$lblAliasValue.startEditingAsync();
                this.val$btnRenameFavItem.setIcon(MTCFavoriteBillPage.this.uiManager.ressource.getImage("confirmBtnBlue.png"));
                this.val$cntMTCCImpayeIndicatorFB.setLeadComponent(null);
            } else {
                String text = this.val$lblAliasValue.getText();
                if (!text.equals("" + this.val$mtcFavoriteBillN.Alias)) {
                    if (MTCFavoriteBillPage.access$100(MTCFavoriteBillPage.this, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().radical, CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID, this.val$mtcFavoriteBillN.ReferenceNumber, text).getStatusCode().equals("000")) {
                        this.val$mtcFavoriteBillN.Alias = text;
                    } else {
                        CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(MTCFavoriteBillPage.this.uiManager.stateMachine, "Une erreur est survenue, Veuillez réessayer plus tard", null);
                    }
                }
                this.val$lblAliasValue.stopEditing();
                this.val$lblAliasValue.setEditable(false);
                this.val$lblAliasValue.setUIID("ac_lblitemDetailBlue");
                this.val$btnRenameFavItem.setIcon(MTCFavoriteBillPage.this.uiManager.ressource.getImage("editBtnBlue.png"));
                this.val$cntMTCCImpayeIndicatorFB.setLeadComponent(this.val$lblCreanceImpayeList);
            }
            this.val$lblAliasValue.refreshTheme();
        }
    }

    private ServiceResponse renameFavItem(String str, String str2, String str3, String str4) {
        ServiceRequest serviceRequest = new ServiceRequest();
        ServiceManager serviceManager = new ServiceManager();
        Hashtable hashtable = new Hashtable();
        hashtable.put("CLIENT_ID", str);
        hashtable.put("SESSION_ID", str2);
        hashtable.put("pReferenceNumber", str3);
        hashtable.put("pAlias", str4);
        serviceRequest.setParamsIn(hashtable);
        serviceRequest.setServiceId(143);
        serviceRequest.setSessionId(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().sessionKeyID);
        return (ServiceResponse) ((ArrayList) serviceManager.Run(serviceRequest)).get(0);
    }
}
