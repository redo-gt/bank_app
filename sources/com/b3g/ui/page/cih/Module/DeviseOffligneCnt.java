package com.b3g.ui.page.cih.Module;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.ExchangeSimService;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.B3GPage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.StringUtil;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DeviseOffligneCnt extends B3GPage {
    TextField TextField;
    Label TextField1;
    Button btn;
    Label dateLbl;
    Dialog dlg;
    UIBuilder uib = new UIBuilder();
    Picker pDevise = new Picker();
    boolean isTextFieldClick = false;
    boolean isTextField1Click = false;

    public DeviseOffligneCnt(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        Container createContainer = this.uib.createContainer("/cihv3", "DeviseOffligneCntNew");
        this.btn = (Button) this.uib.findByName("Button", createContainer);
        this.TextField = (TextField) this.uib.findByName("TextField", createContainer);
        this.TextField1 = (Label) this.uib.findByName("TextField1", createContainer);
        this.dateLbl = (Label) this.uib.findByName("dateLbl", createContainer);
        new ExchangeSimService().getExchangeRateProcess();
        this.uiManager.CurrentPageId = 86;
        CihMBanking.sesPMTR.setCurrentUiManager(this.uiManager);
        String[] strArr = new String[CihMBanking.sesPMTR.getExchangeList().size()];
        for (int i = 0; i < CihMBanking.sesPMTR.getExchangeList().size(); i++) {
            strArr[i] = " " + ((ExchangeSimService) CihMBanking.sesPMTR.getExchangeList().get(i)).codeCurrency + " ";
        }
        this.btn.setText(strArr[0]);
        this.btn.setIcon(getDrapDevise(strArr[0].toString().trim()));
        this.TextField1.setText("37.35");
        changeCurrency();
        this.TextField.addDataChangedListener(new 1());
        this.btn.addActionListener(new 2(strArr, createContainer));
        this.uiManager.currentForm.setBackCommand(new 3("Back"));
        TableLayout tableLayout = new TableLayout(3, 1);
        Container container = new Container(tableLayout);
        container.add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(8), new Container(new FlowLayout())).add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(87), createContainer);
        this.thisContainer.add(container);
        createContainer.setUIID("ContainerTrspBottom1");
        return this.thisContainer;
    }

    class 1 implements DataChangedListener {
        1() {
        }

        public void dataChanged(int i, int i2) {
            DeviseOffligneCnt.this.changeCurrency();
        }
    }

    class 2 implements ActionListener {
        final /* synthetic */ Container val$SecrOffligneCnt;
        final /* synthetic */ String[] val$deviseMap;

        2(String[] strArr, Container container) {
            this.val$deviseMap = strArr;
            this.val$SecrOffligneCnt = container;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            PopUpsManager popUpsManager = new PopUpsManager();
            DeviseOffligneCnt.this.dlg = new Dialog();
            Container drawComboItem = DeviseOffligneCnt.this.drawComboItem(this.val$deviseMap);
            DeviseOffligneCnt.this.dlg.setScrollableY(true);
            DeviseOffligneCnt.this.dlg.setScrollVisible(false);
            popUpsManager.ShowODeviseLblOfflignPopUp(DeviseOffligneCnt.this.uiManager.stateMachine, drawComboItem, this.val$SecrOffligneCnt, DeviseOffligneCnt.this.dlg);
        }
    }

    class 3 extends Command {
        3(String str) {
            super(str);
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DeviseOffligneCnt.this.uiManager.GoBackOffligne();
        }
    }

    public Container drawComboItem(String[] strArr) {
        Container container = new Container(new BoxLayout(2));
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            container.add(DrawItem(str, getDrapDevise(str.toString().trim()), getLblDevise(strArr[i].toString().trim())));
        }
        return container;
    }

    public Container DrawItem(String str, Image image, String str2) {
        Container container = new Container(new LayeredLayout());
        Container container2 = new Container(new BorderLayout());
        container.getAllStyles().setMarginUnit(1);
        container.getAllStyles().setMargin(2, 2, 2, 2);
        try {
            Button button = new Button();
            Label label = new Label(str2);
            Button button2 = new Button();
            button.setUIID("LblCoursChangeMadMeduim");
            label.setUIID("LblCoursChangeMadMeduim");
            button2.setUIID("CntCoursChangeMad");
            button.setText(" " + str);
            button.setIcon(image);
            container2.add("West", button);
            container2.add("East", label);
            container.add(container2);
            container.add(button2);
            button2.addActionListener(new 4(str, image));
            return container;
        } catch (Exception unused) {
            return new Container();
        }
    }

    class 4 implements ActionListener {
        final /* synthetic */ Image val$DrapCurrency;
        final /* synthetic */ String val$codeCurrency;

        4(String str, Image image) {
            this.val$codeCurrency = str;
            this.val$DrapCurrency = image;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            DeviseOffligneCnt.this.btn.setText(this.val$codeCurrency);
            DeviseOffligneCnt.this.btn.setIcon(this.val$DrapCurrency);
            DeviseOffligneCnt.this.changeCurrency();
            DeviseOffligneCnt.this.btn.getParent().revalidate();
            DeviseOffligneCnt.this.dlg.dispose();
        }
    }

    public void changeCurrency() {
        if (!this.TextField.getText().trim().equals("")) {
            for (int i = 0; i < CihMBanking.sesPMTR.getExchangeList().size(); i++) {
                if ((" " + ((ExchangeSimService) CihMBanking.sesPMTR.getExchangeList().get(i)).codeCurrency + " ").equals(this.btn.getText())) {
                    String FormatCurrency = DataTools.FormatCurrency(String.valueOf(Double.parseDouble(formatNumb(this.TextField.getText(), ",")) * ((ExchangeSimService) CihMBanking.sesPMTR.getExchangeList().get(i)).customerSale));
                    if (FormatCurrency.equals("0")) {
                        this.TextField1.setText("0.00");
                    } else if (Display.getInstance().getPlatformName().equals("ios")) {
                        this.TextField1.setText(StringUtil.replaceAll(FormatCurrency, ",", " "));
                    } else if (Display.getInstance().getPlatformName().equals("and")) {
                        this.TextField1.setText(StringUtil.replaceAll(FormatCurrency, ",", "."));
                    } else {
                        this.TextField1.setText(StringUtil.replaceAll(FormatCurrency, ",", "."));
                    }
                    this.dateLbl.setText(((ExchangeSimService) CihMBanking.sesPMTR.getExchangeList().get(i)).date);
                }
            }
            return;
        }
        this.TextField1.setText("0.00");
    }

    private Image getDrapDevise(String str) {
        str.hashCode();
        switch (str) {
            case "AED":
                return this.uiManager.ressource.getImage("AED.png");
            case "BHD":
                return this.uiManager.ressource.getImage("BHD.png");
            case "CAD":
                return this.uiManager.ressource.getImage("CAD.png");
            case "CHF":
                return this.uiManager.ressource.getImage("CHF.png");
            case "DKK":
                return this.uiManager.ressource.getImage("DKK.png");
            case "EUR":
                return this.uiManager.ressource.getImage("EUR.png");
            case "GBP":
                return this.uiManager.ressource.getImage("GBP.png");
            case "GIP":
                return this.uiManager.ressource.getImage("GIP.png");
            case "JPY":
                return this.uiManager.ressource.getImage("JPY.png");
            case "KWD":
                return this.uiManager.ressource.getImage("KWD.png");
            case "NOK":
                return this.uiManager.ressource.getImage("OMR.png");
            case "OMR":
                return this.uiManager.ressource.getImage("OMR.png");
            case "QAR":
                return this.uiManager.ressource.getImage("QAR.png");
            case "SAR":
                return this.uiManager.ressource.getImage("SAR.png");
            case "SEK":
                return this.uiManager.ressource.getImage("SEK.png");
            case "USD":
                return this.uiManager.ressource.getImage("USD.png");
            default:
                return null;
        }
    }

    private String getLblDevise(String str) {
        str.hashCode();
        switch (str) {
            case "AED":
                return "Dirham des Émirats ";
            case "BHD":
                return "Dinar Bahreïni ";
            case "CAD":
                return "Dollar Canadien ";
            case "CHF":
                return "Franc Suisse ";
            case "DKK":
                return "Couronne Danoise ";
            case "EUR":
                return "Euro ";
            case "GBP":
                return "Livre Sterling ";
            case "GIP":
                return "Livre de Gibraltar ";
            case "JPY":
                return "Yen japonais ";
            case "KWD":
                return "Dinar Koweïtien ";
            case "NOK":
                return "Couronne Norvégienne ";
            case "OMR":
                return "Rial Omanais ";
            case "QAR":
                return "Riyal Qatarien ";
            case "SAR":
                return "Riyal Saoudien ";
            case "SEK":
                return "Couronne Suédoise ";
            case "USD":
                return "Dollar Américain ";
            default:
                return null;
        }
    }

    public int formatNumber(String str, String str2) {
        try {
            Integer.parseInt(replace(str, str2, ""));
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return Integer.parseInt(StringUtil.replaceAll(str, str2, ""));
    }

    public String formatNumb(String str, String str2) {
        return StringUtil.replaceAll(str, str2, ".");
    }

    public String replace(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return str;
        }
        while (indexOf != -1) {
            sb.append(str.substring(0, indexOf));
            sb.append(str3);
            str = str.substring(indexOf + str2.length());
            indexOf = str.indexOf(str2);
        }
        sb.append(str);
        return sb.toString();
    }

    public String formatNumber(float f) {
        int length = String.valueOf(f).length();
        if (length > 3) {
            int i = length % 3;
            String substring = String.valueOf(f).substring(i, length);
            ArrayList arrayList = new ArrayList();
            if (substring.length() == 3 || substring.length() < 3) {
                arrayList.add(substring);
            } else {
                for (int i2 = 2; i2 < substring.length(); i2 += 3) {
                    arrayList.add(substring.substring(i2 - 2, i2 + 1));
                }
            }
            String str = String.valueOf(f).substring(0, i) + " ";
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                str = str + ((String) arrayList.get(i3)) + " ";
            }
            return str.trim();
        }
        return String.valueOf(f);
    }
}
