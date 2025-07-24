package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.MTCForm;
import com.b3g.services.MTCImpaye;
import com.b3g.services.MTCImpayeInputValue;
import com.b3g.services.MTCItemValue;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.util.StringUtil;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class MTCFormPage extends B3GPage {
    public MTCFormPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = false;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            Container container = new Container(new BoxLayout(2));
            container.setUIID("ad_CntAccountDetailsMain");
            container.addComponent(GetMTCFormContainer(CihMBanking.sesPMTR.getSelectedMTCCreancier().CodeCreancier, CihMBanking.sesPMTR.getSelectedMTCCreance().CodeCreance));
            this.thisContainer.addComponent(container);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    public Container GetMTCFormContainer(String str, String str2) {
        Container createContainer = this.uiManager.stateMachine.createContainer(this.uiManager.ressource, "MTCFormFrom");
        ArrayList MTCFormProcess = new MTCForm().MTCFormProcess(str, str2);
        this.uiManager.stateMachine.findLblCreancierNameTypeValueV2(createContainer).setText(CihMBanking.sesPMTR.getSelectedMTCCreancier().NomCreancier);
        this.uiManager.stateMachine.findLblCreancierDescriptionValueV2(createContainer).setText(CihMBanking.sesPMTR.getSelectedMTCCreancier().DescriptionCreancier);
        this.uiManager.stateMachine.findBtnCreancierImage(createContainer).setIcon(URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(this.uiManager.stateMachine.findBtnCreancierImage(createContainer).getIcon().getWidth(), this.uiManager.stateMachine.findBtnCreancierImage(createContainer).getIcon().getWidth(), 12569042), false), "Normal_https://www.cihnet.co.ma" + CihMBanking.sesPMTR.getSelectedMTCCreancier().LogoPath, "https://www.cihnet.co.ma" + CihMBanking.sesPMTR.getSelectedMTCCreancier().LogoPath, URLImage.RESIZE_SCALE));
        this.uiManager.stateMachine.findCntSelectedCreance(createContainer).removeAll();
        this.uiManager.stateMachine.findCntSelectedCreance(createContainer).revalidate();
        this.uiManager.stateMachine.findCntSelectedCreance(createContainer).addComponent(GetKeyValueData("Service sélectionné", CihMBanking.sesPMTR.getSelectedMTCCreance().NomCreance));
        if (MTCFormProcess.size() > 0) {
            this.uiManager.stateMachine.findCntFormList(createContainer).addComponent(GetFormContainer(MTCFormProcess));
        } else {
            this.uiManager.stateMachine.findCntFormList(createContainer).addComponent(GetErrorMessageContainer("Une erreur est survenue, Veuillez réessayer plus tard"));
        }
        createContainer.revalidate();
        Settings.setNightMode(createContainer, 0);
        return createContainer;
    }

    public Container GetFormContainer(ArrayList arrayList) {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("ad_CntAccountDetailsMain");
        container.addComponent(DrawBorderSep());
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            if (((MTCForm) arrayList.get(i)).TypeChamp.equals("libelle")) {
                arrayList2.add(DrawFlowCnt(DrawMTCLabel((MTCForm) arrayList.get(i)), 0));
            } else if (((MTCForm) arrayList.get(i)).TypeChamp.equals("text")) {
                TextField DrawMTCText = DrawMTCText((MTCForm) arrayList.get(i));
                arrayList2.add(DrawFlowCnt(DrawMTCLabel((MTCForm) arrayList.get(i)), 0));
                arrayList2.add(DrawFlowCnt(DrawMTCText, 1));
                if (CihMBanking.sesPMTR.getBillerType() == 1) {
                    DrawMTCText.addDataChangeListener(new 1(DrawMTCText));
                }
            } else if (((MTCForm) arrayList.get(i)).TypeChamp.equals("password")) {
                arrayList2.add(DrawFlowCnt(DrawMTCLabel((MTCForm) arrayList.get(i)), 0));
                arrayList2.add(DrawFlowCnt(DrawMTCText((MTCForm) arrayList.get(i)), 1));
            } else if (!((MTCForm) arrayList.get(i)).TypeChamp.equals("checkbox") && ((MTCForm) arrayList.get(i)).TypeChamp.equals("select") && ((MTCForm) arrayList.get(i)).ValueList.size() > 0) {
                ComboBox comboBox = new ComboBox();
                comboBox.setUIID("listBox");
                PopulateCombox(comboBox, ((MTCForm) arrayList.get(i)).ValueList);
                Container container2 = new Container(new BoxLayout(2));
                container2.setName("CntSelect");
                container2.addComponent(comboBox);
                arrayList2.add(container2);
            }
            if (arrayList2.size() > 1) {
                container.addComponent(DrawOneTableLayout(arrayList2, i));
                container.addComponent(DrawBorderSep());
                container.revalidate();
                arrayList2 = new ArrayList();
            } else if (arrayList2.size() == 1) {
                container.addComponent((Component) arrayList2.get(0));
                container.addComponent(DrawBorderSep());
                container.revalidate();
                arrayList2 = new ArrayList();
            }
        }
        if (arrayList.size() > 0) {
            Container DrawAlias = DrawAlias();
            container.addComponent(DrawAlias);
            container.addComponent(DrawBorderSep());
            Container DrawFavoriteBillCheck = DrawFavoriteBillCheck(container, DrawAlias);
            container.addComponent(DrawFavoriteBillCheck);
            container.addComponent(DrawBorderSep());
            container.addComponent(AddCntValidateForm(container, DrawFavoriteBillCheck, DrawAlias, arrayList));
        }
        return container;
    }

    class 1 implements DataChangedListener {
        final /* synthetic */ TextField val$text;

        1(TextField textField) {
            this.val$text = textField;
        }

        public void dataChanged(int i, int i2) {
            CihMBanking.sesPMTR.setLineToTopUp(this.val$text.getText());
        }
    }

    public TextField DrawMTCText(MTCForm mTCForm) {
        TextField textField = new TextField();
        textField.setUIID("tr_txtAmount");
        textField.setFocusable(true);
        textField.setScrollVisible(false);
        textField.setColumns(10);
        textField.setRows(1);
        textField.setName(mTCForm.NomChamp);
        if (mTCForm.FormatChamp.equals("1")) {
            textField.setConstraint(0);
        } else if (mTCForm.FormatChamp.equals("2")) {
            textField.setConstraint(2);
        } else {
            textField.setConstraint(5);
        }
        return textField;
    }

    public Label DrawMTCLabel(MTCForm mTCForm) {
        Label label = new Label();
        label.setText(StringUtil.replaceAll(mTCForm.Libelle, "Numéro", "N°"));
        label.setFocusable(false);
        label.setUIID("g_lblNotif");
        label.setVerticalAlignment(4);
        label.setAlignment(1);
        label.setName(mTCForm.NomChamp);
        return label;
    }

    public Label DrawMTCAliasTitle(String str) {
        Label label = new Label();
        label.setText(str);
        label.setFocusable(false);
        label.setUIID("g_lblNotif");
        label.setVerticalAlignment(4);
        label.setAlignment(1);
        label.setName("lblAliasTitle");
        return label;
    }

    public TextField DrawMTCAliasText() {
        TextField textField = new TextField();
        textField.setUIID("tr_txtAmount");
        textField.setFocusable(true);
        textField.setScrollVisible(false);
        textField.setColumns(10);
        textField.setRows(1);
        textField.setName("txtAlias");
        textField.setConstraint(0);
        return textField;
    }

    public Container DrawBorderSep() {
        Container container = new Container(new BoxLayout(2));
        container.setUIID("g_cntBorderV2");
        container.setFocusable(false);
        container.setScrollable(false);
        return container;
    }

    public Container DrawFlowCnt(Component component, int i) {
        FlowLayout flowLayout = new FlowLayout();
        if (i == 0) {
            flowLayout.setAlign(1);
        } else {
            flowLayout.setAlign(3);
        }
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        container.setUIID("Container");
        container.addComponent(component);
        container.revalidate();
        return container;
    }

    public Container DrawOneTableLayout(ArrayList arrayList, int i) {
        TableLayout tableLayout = new TableLayout(1, 2);
        Container container = new Container();
        container.setLayout(tableLayout);
        container.setUIID("Container");
        container.setFocusable(false);
        container.setScrollable(false);
        container.setName("i" + i);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(50);
        container.addComponent(createConstraint, (Component) arrayList.get(0));
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(50);
        container.addComponent(createConstraint2, (Component) arrayList.get(1));
        container.revalidate();
        return container;
    }

    public Container AddCntValidateForm(Container container, Container container2, Container container3, ArrayList arrayList) {
        Container container4 = new Container(new BoxLayout(2));
        container4.setUIID("Container");
        container4.setFocusable(false);
        container4.setScrollable(false);
        Button button = new Button("SUIVANT");
        button.setUIID("op_BtnOppositionValidation");
        button.setVerticalAlignment(4);
        button.setAlignment(4);
        button.addActionListener(new 2(container, arrayList, (CheckBox) container2.getComponentAt(0), container3, new MTCImpaye()));
        container4.addComponent(button);
        return container4;
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$CntForm;
        final /* synthetic */ Container val$cntAlias;
        final /* synthetic */ CheckBox val$favoriteCheck;
        final /* synthetic */ MTCImpaye val$mtcImpaye;
        final /* synthetic */ ArrayList val$pMTCFormList;

        2(Container container, ArrayList arrayList, CheckBox checkBox, Container container2, MTCImpaye mTCImpaye) {
            this.val$CntForm = container;
            this.val$pMTCFormList = arrayList;
            this.val$favoriteCheck = checkBox;
            this.val$cntAlias = container2;
            this.val$mtcImpaye = mTCImpaye;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String str;
            new ArrayList();
            String GetFormInputValueParameter = MTCFormPage.this.GetFormInputValueParameter(this.val$CntForm, this.val$pMTCFormList);
            String str2 = "-";
            if (this.val$favoriteCheck.isSelected()) {
                str = "1";
                CihMBanking.sesPMTR.setIsFavorite("1");
                if (this.val$cntAlias.getComponentCount() == 2) {
                    str2 = ((TextField) ((Container) this.val$cntAlias.getComponentAt(1)).getComponentAt(0)).getText();
                }
            } else {
                str = "0";
                CihMBanking.sesPMTR.setIsFavorite("0");
            }
            String str3 = str2;
            String str4 = str;
            if (GetFormInputValueParameter.length() > 2) {
                if (!MTCFormPage.findCharSequence(":;", GetFormInputValueParameter)) {
                    if (str3.length() > 0) {
                        CihMBanking.sesPMTR.setCurrentMTCImpaye(this.val$mtcImpaye.MTCImpayeProcess(900063, CihMBanking.sesPMTR.getSelectedMTCCreancier().CodeCreancier, CihMBanking.sesPMTR.getSelectedMTCCreance().CodeCreance, GetFormInputValueParameter, str4, CihMBanking.sesPMTR.getSelectedMTCCreancier().MTCCreancierToString(), CihMBanking.sesPMTR.getSelectedMTCCreance().MTCCreanceToString(), str3, CihMBanking.sesPMTR.getBillerType(), CihMBanking.sesPMTR.getMTCFormFirstLabel()));
                        CihMBanking.sesPMTR.setCurrentMTCFormInput(GetFormInputValueParameter);
                        CihMBanking.sesPMTR.setCurrentMTCSelectedAlias(str3);
                        if (CihMBanking.sesPMTR.getBillerType() == 0) {
                            MTCFormPage.this.uiManager.NavigateToPageById(23);
                            return;
                        } else {
                            MTCFormPage.this.uiManager.NavigateToPageById(32);
                            return;
                        }
                    }
                    CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Veuillez saisir l'alias", null);
                    return;
                }
                CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Veuillez compléter le formulaire", null);
                return;
            }
            CihMBanking.sesPMTR.getCurrentUiManager().ShowMessageTransaction(CihMBanking.sesPMTR.getCurrentUiManager().stateMachine, "Veuillez compléter le formulaire", null);
        }
    }

    public String GetFormInputValueParameter(Container container, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        String str = "";
        for (int i = 0; i < container.getComponentCount(); i++) {
            String name = ((Container) container.getComponentAt(i)).getName();
            if (((Container) container.getComponentAt(i)).getLayout().toString().equals("TableLayout") && !name.equals("cntAlias") && !name.equals("CntSelect") && ((Container) container.getComponentAt(i)).getComponentCount() == 2) {
                TextField textField = (TextField) ((Container) ((Container) container.getComponentAt(i)).getComponentAt(1)).getComponentAt(0);
                if (i == 1) {
                    CihMBanking.sesPMTR.setMTCFormFirstLabel(((Label) ((Container) ((Container) container.getComponentAt(i)).getComponentAt(0)).getComponentAt(0)).getText() + ";" + textField.getText());
                }
                MTCImpayeInputValue mTCImpayeInputValue = new MTCImpayeInputValue();
                mTCImpayeInputValue.setFromControlName(textField.getName());
                mTCImpayeInputValue.setFromControlValue(textField.getText());
                str = (str + textField.getName() + ":" + textField.getText()) + ";";
                arrayList2.add(mTCImpayeInputValue);
            }
            if (name != null && name.equals("CntSelect")) {
                str = str + GetComboBoxSelectedValue((Container) container.getComponentAt(i), arrayList);
            }
        }
        return str;
    }

    public Container DrawFavoriteBillCheck(Container container, Container container2) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setValignByRow(true);
        Container container3 = new Container();
        container3.setLayout(flowLayout);
        container3.setUIID("ContainerCheckFavorite");
        CheckBox checkBox = new CheckBox();
        checkBox.setUIID("mtc_checkBox");
        checkBox.setIcon(this.uiManager.ressource.getImage("radio_off.PNG"));
        checkBox.setPressedIcon(this.uiManager.ressource.getImage("radio_on.png"));
        checkBox.setToggle(true);
        checkBox.setTactileTouch(true);
        checkBox.setVerticalAlignment(4);
        checkBox.setTextPosition(1);
        checkBox.setText("Ajouter aux Favoris");
        checkBox.addActionListener(new 3(checkBox, container2, container));
        container3.addComponent(checkBox);
        container3.revalidate();
        return container3;
    }

    class 3 implements ActionListener {
        final /* synthetic */ CheckBox val$chk;
        final /* synthetic */ Container val$cntAlias;
        final /* synthetic */ Container val$cntGlobal;

        3(CheckBox checkBox, Container container, Container container2) {
            this.val$chk = checkBox;
            this.val$cntAlias = container;
            this.val$cntGlobal = container2;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.val$chk.isSelected()) {
                this.val$cntAlias.setVisible(true);
                this.val$cntAlias.setEnabled(true);
                this.val$cntAlias.repaint();
                this.val$cntGlobal.revalidate();
                return;
            }
            this.val$cntAlias.setVisible(false);
            this.val$cntAlias.setEnabled(false);
            this.val$cntAlias.repaint();
            this.val$cntGlobal.revalidate();
        }
    }

    public Container DrawAlias() {
        TableLayout tableLayout = new TableLayout(1, 2);
        Container container = new Container();
        container.setName("cntAlias");
        container.setLayout(tableLayout);
        container.setUIID("Container");
        container.setFocusable(false);
        container.setScrollable(false);
        TableLayout.Constraint createConstraint = tableLayout.createConstraint();
        createConstraint.setWidthPercentage(50);
        container.addComponent(createConstraint, DrawFlowCnt(DrawMTCAliasTitle("libellé"), 0));
        TableLayout.Constraint createConstraint2 = tableLayout.createConstraint();
        createConstraint2.setWidthPercentage(50);
        container.addComponent(createConstraint2, DrawFlowCnt(DrawMTCAliasText(), 1));
        container.setVisible(false);
        container.setEnabled(false);
        container.revalidate();
        return container;
    }

    public String GetSelectedValue(Container container) {
        new MTCItemValue();
        MTCItemValue mTCItemValue = (MTCItemValue) ((Container) ((Container) ((Tabs) container.getComponentAt(2)).getSelectedComponent()).getComponentAt(0)).getClientProperty("MTCItemValue");
        return ("" + mTCItemValue.FiledName + ":" + mTCItemValue.Value) + ";";
    }

    public String GetComboBoxSelectedValue(Container container, ArrayList arrayList) {
        return GetValueResume(((ComboBox) container.getComponentAt(0)).getSelectedItem().toString(), arrayList);
    }

    public String GetValueResume(String str, ArrayList arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (((MTCForm) arrayList.get(i)).TypeChamp.equals("select")) {
                for (int i2 = 0; i2 < ((MTCForm) arrayList.get(i)).ValueList.size(); i2++) {
                    String[] split = DataTools.split(str, " ");
                    if (((MTCItemValue) ((MTCForm) arrayList.get(i)).ValueList.get(i2)).Value.equals(split[0])) {
                        if (split.length > 1) {
                            if (!split[1].equals("DH")) {
                                return "";
                            }
                            return ("" + ((MTCForm) arrayList.get(i)).NomChamp + ":" + split[0]) + ";";
                        }
                        return ("" + ((MTCForm) arrayList.get(i)).NomChamp + ":" + split[0]) + ";";
                    }
                }
            }
        }
        return "";
    }

    public void PopulateCombox(ComboBox comboBox, ArrayList arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (CihMBanking.sesPMTR.getBillerType() != 0) {
                comboBox.addItem(((MTCItemValue) arrayList.get(i)).Value + " DH");
            } else {
                comboBox.addItem(((MTCItemValue) arrayList.get(i)).Value);
            }
        }
    }

    public Container GetKeyValueData(String str, String str2) {
        Container container = new Container(new BoxLayout(3));
        container.setFocusable(false);
        container.setScrollVisible(false);
        container.setUIID("MTCKeyValueLabelForm");
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
        container.setLeadComponent(button);
        return container;
    }

    public Container GetErrorMessageContainer(String str) {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlign(4);
        flowLayout.setValign(4);
        flowLayout.setFillRows(true);
        Container container = new Container();
        container.setLayout(flowLayout);
        container.setUIID("g_CntTranspMsg");
        SpanLabel spanLabel = new SpanLabel();
        spanLabel.setUIID("dg_splblMsgCenter");
        spanLabel.setScrollVisible(false);
        spanLabel.setText(str);
        spanLabel.setIconPosition("West");
        spanLabel.setTextUIID("dg_lblSPError");
        spanLabel.setIconUIID("g_cntEmpty");
        container.addComponent(spanLabel);
        return container;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
    
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean findCharSequence(java.lang.String r8, java.lang.String r9) {
        /*
            int r0 = r9.length()
            int r1 = r8.length()
            int r0 = r0 - r1
            r1 = 0
            r2 = r1
        Lb:
            if (r2 > r0) goto L2d
            int r3 = r8.length()
            r5 = r1
            r4 = r2
        L13:
            int r6 = r3 + (-1)
            if (r3 == 0) goto L2c
            int r3 = r4 + 1
            char r4 = r9.charAt(r4)
            int r7 = r5 + 1
            char r5 = r8.charAt(r5)
            if (r4 == r5) goto L28
            int r2 = r2 + 1
            goto Lb
        L28:
            r4 = r3
            r3 = r6
            r5 = r7
            goto L13
        L2c:
            r1 = 1
        L2d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.page.MTCFormPage.findCharSequence(java.lang.String, java.lang.String):boolean");
    }
}
