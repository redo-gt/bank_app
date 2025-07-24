package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GSlider;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.ThrowListener;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.MathUtil;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class SimulatorConso extends B3GPage implements ThrowListener {
    public final B3GSlider dureeSlider;
    private double maxAmount;
    private int maxPeriod;
    private double maxRate;
    private double minAmount;
    private int minPeriod;
    private double minRate;
    public final B3GSlider montantSlider;
    final Label rslt = new Label();
    final Container rsltCnt;
    public final B3GSlider tauxSlider;

    static /* synthetic */ void access$000(SimulatorConso simulatorConso) {
        simulatorConso.SetResult();
    }

    public double getMinRate() {
        return this.minRate;
    }

    public void setMinRate(double d) {
        this.minRate = d;
    }

    public double getMaxRate() {
        return this.maxRate;
    }

    public void setMaxRate(double d) {
        this.maxRate = d;
    }

    public double getMinAmount() {
        return this.minAmount;
    }

    public void setMinAmount(double d) {
        this.minAmount = d;
    }

    public double getMaxAmount() {
        return this.maxAmount;
    }

    public void setMaxAmount(double d) {
        this.maxAmount = d;
    }

    public int getMinPeriod() {
        return this.minPeriod;
    }

    public void setMinPeriod(int i) {
        this.minPeriod = i;
    }

    public int getMaxPeriod() {
        return this.maxPeriod;
    }

    public void setMaxPeriod(int i) {
        this.maxPeriod = i;
    }

    public SimulatorConso(Object obj, Object obj2, int i) {
        Container container = new Container();
        this.rsltCnt = container;
        this.tauxSlider = new B3GSlider(7, 13, 8, 10, "Taux :", "%");
        this.montantSlider = new B3GSlider(10000, 500000, 100000, 1, "Montant :", "Dhs");
        this.dureeSlider = new B3GSlider(6, 60, 24, 1, "Durée :", "Mois");
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
        container.setLayout(new BoxLayout(2));
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.tauxSlider.addThrowListener(this);
        this.montantSlider.addThrowListener(this);
        this.dureeSlider.addThrowListener(this);
        this.thisContainer.addComponent(this.tauxSlider);
        Container container = new Container();
        container.setUIID("g_cntBorderV2Slider");
        this.thisContainer.addComponent(container);
        this.thisContainer.addComponent(this.montantSlider);
        Container container2 = new Container();
        container2.setUIID("g_cntBorderV2Slider");
        this.thisContainer.addComponent(container2);
        this.thisContainer.addComponent(this.dureeSlider);
        Container container3 = new Container();
        container3.setUIID("g_cntBorderV2Slider");
        this.thisContainer.addComponent(container3);
        Container container4 = new Container(new BoxLayout(2));
        container4.setUIID("SimBtnCnt");
        Button button = new Button();
        button.setUIID("SimBtnExec");
        button.setText("SIMULER");
        button.addActionListener(new 1());
        container4.addComponent(button);
        Button button2 = new Button();
        button2.setUIID("SimBtnExec");
        button2.setIcon(CihMBanking.sesPMTR.getCurrentUiManager().ressource.getImage("simulez.png"));
        container4.addComponent(button2);
        container4.setLeadComponent(button);
        this.rsltCnt.setUIID("lg_CntDemoButtonSimulatorUpDated");
        Label label = new Label("MENSUALITÉ : ");
        label.setUIID("SimResultLabel");
        this.rslt.setUIID("SimResult");
        SetResult();
        this.rsltCnt.addComponent(label);
        this.rsltCnt.addComponent(this.rslt);
        this.thisContainer.addComponent(this.rsltCnt);
        this.thisContainer.setUIID("EmptyContainerZero");
        this.thisContainer.getStyle().setMargin(1, 1, 1, 1);
        this.thisContainer.setScrollableY(true);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            SimulatorConso.access$000(SimulatorConso.this);
        }
    }

    private Double CalculateMonthlyPayment(Double d, Integer num, double d2) {
        try {
            double d3 = d2 / 12.0d;
            return Double.valueOf((d.doubleValue() * d3) / (1.0d - MathUtil.pow(d3 + 1.0d, num.intValue() * (-1))));
        } catch (Exception unused) {
            return Double.valueOf(0.0d);
        }
    }

    public void Catch() {
        SetResult();
        this.PageSession = this;
    }

    private void SetResult() {
        this.rslt.setText(DataTools.FormatCurrency(Float.valueOf(CalculateMonthlyPayment(Double.valueOf(Double.parseDouble(this.montantSlider.getValue())), Integer.valueOf(stringToInt(this.dureeSlider.getValue())), Double.parseDouble(this.tauxSlider.getValue()) / 100.0d).floatValue()).toString()) + " Dhs");
        this.rsltCnt.revalidate();
    }

    private int stringToInt(String str) {
        return Double.valueOf(Double.parseDouble(str)).intValue();
    }
}
