package com.b3g.ui;

import com.b3g.cih.online.CihMBanking;
import com.b3g.common.ServiceResponse;
import com.b3g.services.Card;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.util.UITimer;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CardInformationAcc extends Container {
    Image UnselectedIcon;
    Container body;
    Image closeIcon;
    Container header;
    SpanLabel msgSp;
    String msgStr;
    Image openIcon;
    Image selectedIcon;
    Button titleSp;
    String titleStr;
    Label dateLbl = new Label("", "lbl_black_very_small");
    Label cvvLbl = new Label("", "lbl_black_very_small");
    Card Crd = (Card) CihMBanking.sesPMTR.objctCrdCurrent;
    B3GCIHEcodeCard eCode1 = new B3GCIHEcodeCard();
    boolean open = false;
    InfoCard infoCard = null;

    static /* synthetic */ void access$000(CardInformationAcc cardInformationAcc) {
        cardInformationAcc.openClose();
    }

    public CardInformationAcc() {
        setLayout(BoxLayout.y());
        getAllStyles().setMarginUnit(1);
        getAllStyles().setMargin(0, 1, 0, 0);
    }

    public CardInformationAcc(String str, String str2) {
        setLayout(BoxLayout.y());
        getAllStyles().setMarginUnit(1);
        getAllStyles().setMargin(2, 0, 0, 0);
    }

    private void openClose() {
        if (this.open) {
            this.body.setHidden(true);
            this.titleSp.setIcon(CihMBanking.theme.getImage("Icon_eye_1.png"));
            this.titleSp.getAllStyles().setFgColor(16777215);
            this.header.getAllStyles().setBgColor(1945583);
            this.header.getAllStyles().setBgTransparency(255);
        } else {
            if (this.infoCard == null) {
                ServiceResponse CardInfoProcess = Card.CardInfoProcess(this.Crd.plainCardNumber);
                if (CardInfoProcess.getStatusCode().equals("000")) {
                    InfoCard infoCard = new InfoCard();
                    this.infoCard = infoCard;
                    infoCard.cvv = CardInfoProcess.getParamsOut().get("CardCVV").toString();
                    this.infoCard.DateExpriration = CardInfoProcess.getParamsOut().get("CardExpiryDate").toString();
                    this.cvvLbl.setText("CVV : " + this.infoCard.cvv);
                    this.dateLbl.setText("Exp : " + this.infoCard.DateExpriration);
                } else {
                    this.cvvLbl.setText("CVV not found");
                    this.dateLbl.setText("Exp not found");
                }
            }
            Card.CardInfoProcess(this.Crd.plainCardNumber);
            this.body.setHidden(false);
            this.titleSp.setIcon(CihMBanking.theme.getImage("Icon_eye.png"));
            this.titleSp.getAllStyles().setFgColor(1945583);
            this.header.getAllStyles().setBgColor(16777215);
            this.header.getAllStyles().setBgTransparency(255);
            this.header.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(0.0f).stroke(new Stroke(2.0f, 2, 0, 4.0f)).strokeColor(1945583).strokeOpacity(255));
            this.eCode1.startEditing(true);
        }
        this.open = !this.open;
        getParent().getParent().getParent().animateLayout(400);
    }

    public Container getAccordion() {
        Container container = new Container(new BorderLayout());
        this.header = container;
        container.getAllStyles().setPaddingUnit(1);
        this.header.getAllStyles().setPadding(0.75f, 0.75f, 2.0f, 2.0f);
        this.header.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(0.0f).stroke(new Stroke(2.0f, 2, 0, 4.0f)).strokeColor(1945583).strokeOpacity(255));
        this.header.getAllStyles().setBgColor(1945583);
        this.header.getAllStyles().setBgTransparency(255);
        FlowLayout flowLayout = new FlowLayout(1, 4);
        flowLayout.setFillRows(true);
        Container container2 = new Container(flowLayout);
        Button button = new Button();
        this.titleSp = button;
        button.setUIID("Container");
        this.titleSp.setText("  Afficher les informations de la carte");
        this.titleSp.getAllStyles().setAlignment(4);
        this.titleSp.getAllStyles().setFgColor(16777215);
        this.titleSp.setIcon(CihMBanking.theme.getImage("Icon_eye_1.png"));
        container2.add(this.titleSp);
        this.titleSp.addActionListener(new 1());
        this.header.add("Center", container2);
        Container container3 = new Container(new BorderLayout());
        this.body = container3;
        container3.getAllStyles().setPaddingUnit(1);
        this.body.getAllStyles().setPadding(2, 2, 3, 3);
        FlowLayout flowLayout2 = new FlowLayout(1, 4);
        flowLayout2.setFillRows(true);
        FlowLayout flowLayout3 = new FlowLayout(1, 4);
        flowLayout3.setFillRows(true);
        FlowLayout flowLayout4 = new FlowLayout(1, 4);
        flowLayout4.setFillRows(true);
        Container container4 = new Container(flowLayout2);
        Container container5 = new Container(flowLayout3);
        Container container6 = new Container(flowLayout4);
        container5.getAllStyles().setPaddingUnit(1);
        container5.getAllStyles().setPadding(1.5f, 1.5f, 0.0f, 0.0f);
        this.body.add("North", container4);
        this.body.add("Center", container5);
        this.body.add("South", container6);
        Label label = new Label(this.Crd.plainCardNumber, "Container");
        Label label2 = new Label(CihMBanking.sesPMTR.getSessionClient().getClient_Profil().nomPrenom, "Container");
        Button button2 = new Button("Copier", "lbl_black_very_small");
        this.dateLbl.getAllStyles().setFgColor(0);
        this.cvvLbl.getAllStyles().setFgColor(0);
        label.setAlignment(1);
        button2.setAlignment(3);
        label2.setAlignment(1);
        this.dateLbl.setAlignment(1);
        this.cvvLbl.setAlignment(3);
        container4.add(label).add(button2);
        container5.add(label2);
        container6.add(this.dateLbl).add(this.cvvLbl);
        this.body.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(0.0f).stroke(new Stroke(2.0f, 2, 0, 4.0f)).strokeColor(1945583).strokeOpacity(255));
        this.body.revalidate();
        this.body.setHidden(true);
        add(this.header).add(this.body);
        button2.addActionListener(new 2(label));
        return this;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardInformationAcc.access$000(CardInformationAcc.this);
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Label val$numCardLbl;

        2(Label label) {
            this.val$numCardLbl = label;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().copyToClipboard(this.val$numCardLbl.getText());
        }
    }

    public void setHeader(Container container) {
        this.header = container;
    }

    public void setBody(Container container) {
        this.body = container;
    }

    public void setTitle(Button button) {
        this.titleSp = button;
    }

    public void setMsg(SpanLabel spanLabel) {
        this.msgSp = spanLabel;
    }

    public void setOpenIcon(Image image) {
        this.openIcon = image;
    }

    public void setCloseIcon(Image image) {
        this.closeIcon = image;
    }

    public void setSelectedIcon(Image image) {
        this.selectedIcon = image;
    }

    public void setUnselectedIcon(Image image) {
        this.UnselectedIcon = image;
    }

    public void setOpen(boolean z) {
        this.open = z;
    }

    public void setMsgSP(SpanLabel spanLabel) {
        this.msgSp = spanLabel;
    }

    public void setTitleStr(String str) {
        this.titleStr = str;
    }

    public void setMsgStr(String str) {
        this.msgStr = str;
    }

    public class B3GCIHEcodeCard extends Component {
        String code;
        TextField txtF;
        Label[] labels = new Label[4];
        int oldLenght = 0;
        boolean flagOpen = true;
        int bgColor = 16777215;
        int sepColor1 = 15818018;
        int sepColor = 13421772;

        public B3GCIHEcodeCard() {
            for (int i = 0; i < 4; i++) {
                this.labels[i] = new Label();
                this.labels[i].setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("unselectedCercle2.png"));
                this.labels[i].getAllStyles().setPaddingUnit(1);
                this.labels[i].getAllStyles().setPadding(1, 1, 1, 1);
                this.labels[i].setAlignment(4);
                Label label = this.labels[i];
                label.setPreferredH(label.getPreferredH());
            }
        }

        public void setFlagOpen(boolean z) {
            this.flagOpen = z;
        }

        public void setBgColor(int i) {
            this.bgColor = i;
        }

        public String getValue() {
            return this.txtF.getText();
        }

        private void concCodeWithTextAndDelete(String str) {
            this.code += str;
        }

        public Component getComponent() {
            TextField textField = new TextField();
            this.txtF = textField;
            textField.setConstraint(65538);
            this.txtF.setMaxSize(4);
            this.txtF.getAllStyles().setFgColor(this.bgColor);
            this.txtF.setVisible(false);
            this.txtF.setTextSelectionEnabled(false);
            this.txtF.setCursorBlinkTimeOff(999999);
            this.txtF.setCursorPosition(0);
            this.txtF.setUIID("ContTrspar");
            this.txtF.getAllStyles().setPaddingUnit(1);
            this.txtF.getAllStyles().setPadding(100, 1, 51, 51);
            Container container = new Container(new LayeredLayout());
            Container container2 = new Container(new LayeredLayout());
            Container container3 = new Container(new FlowLayout());
            new FlowLayout(4, 4).setFillRows(false);
            Container container4 = new Container(new GridLayout(1, 4));
            for (Label label : this.labels) {
                Container container5 = new Container(BoxLayout.y());
                container5.getAllStyles().setPaddingUnit(1);
                container5.getAllStyles().setPadding(1, 1, 4, 4);
                container5.add(label);
                container4.add(container5);
            }
            FlowLayout flowLayout = new FlowLayout(4, 4);
            flowLayout.setFillRows(true);
            Container container6 = new Container(flowLayout);
            container3.setPreferredH(0);
            container3.setPreferredW(0);
            container3.setHeight(0);
            container3.setWidth(0);
            container3.add(this.txtF);
            container2.add(container3);
            container6.getAllStyles().setBgColor(this.bgColor);
            container6.getAllStyles().setBgTransparency(255);
            container6.add(FlowLayout.encloseIn(container4));
            container.add(container2).add(container6);
            container.setLeadComponent(this.txtF);
            this.txtF.addDataChangedListener(new 1());
            startEditing(this.flagOpen);
            return container;
        }

        class 1 implements DataChangedListener {
            1() {
            }

            public void dataChanged(int i, int i2) {
                B3GCIHEcodeCard.this.txtF.setTextSelectionEnabled(false);
                B3GCIHEcodeCard.this.txtF.setCursorBlinkTimeOff(999999);
                int length = B3GCIHEcodeCard.this.txtF.getText().length();
                if (B3GCIHEcodeCard.this.oldLenght >= length) {
                    for (int i3 = 3; i3 >= length; i3--) {
                        B3GCIHEcodeCard.this.labels[i3].setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("unselectedCercle2.png"));
                    }
                } else if (length < 5 && length > 0) {
                    B3GCIHEcodeCard.this.labels[length - 1].setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("selectedCercle2.png"));
                }
                B3GCIHEcodeCard b3GCIHEcodeCard = B3GCIHEcodeCard.this;
                b3GCIHEcodeCard.oldLenght = b3GCIHEcodeCard.txtF.getText().length();
            }
        }

        public void startEditing(boolean z) {
            if (z) {
                new UITimer(new CardInformationAcc$B3GCIHEcodeCard$$ExternalSyntheticLambda0(this)).schedule(500, false, CihMBanking.sesPMTR.getCurrentUiManager().currentForm);
            }
        }

        /* synthetic */ void lambda$startEditing$0$com-b3g-ui-CardInformationAcc$B3GCIHEcodeCard() {
            this.txtF.startEditingAsync();
        }

        public void stopEditing() {
            this.txtF.stopEditing();
        }
    }

    public class InfoCard {
        public String CardNumber;
        public String DateExpriration;
        public String cvv;
        public String fullName;

        public InfoCard() {
        }
    }
}
