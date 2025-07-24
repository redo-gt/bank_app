package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.TransactionResume;
import com.b3g.tools.DataTools;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.UITimer;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class RecapTransfertFromEtrangerPage extends B3GPage {
    UIBuilder uib = new UIBuilder();
    TransactionResume trans = new TransactionResume();

    public RecapTransfertFromEtrangerPage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new GridLayout(1, 1));
        this.thisContainer.setUIID("mn_cntMenuItemV2");
        TransactionResume sessionCurrentTransactionResume = CihMBanking.sesPMTR.getSessionCurrentTransactionResume();
        Container createContainer = this.uib.createContainer("/cihv3", "TransfertFromEtrangerRecap");
        ((Label) this.uib.findByName("lblDeviseMadValue", createContainer)).setText(sessionCurrentTransactionResume.field2 + sessionCurrentTransactionResume.field1);
        Label label = (Label) this.uib.findByName("Ibancible", createContainer);
        Label label2 = (Label) this.uib.findByName("Label6", createContainer);
        ((Label) this.uib.findByName("Label4", createContainer)).setText(DataTools.FormatCurrency(String.valueOf(CihMBanking.sesPMTR.getExchangeTransfert().customerBuy)));
        Label label3 = (Label) this.uib.findByName("Label5", createContainer);
        Label label4 = (Label) this.uib.findByName("Label2", createContainer);
        if (sessionCurrentTransactionResume.field1.equals(" EUR ")) {
            label.setText(sessionCurrentTransactionResume.Amount);
            label4.setText("Montant du transfert en Dirhams : ");
            label3.setHidden(true);
            label2.setHidden(true);
        } else {
            label2.setText(sessionCurrentTransactionResume.Amount);
            label3.setText("Montant du transfert en Euro : ");
            label4.setHidden(true);
            label.setHidden(true);
        }
        Button button = (Button) this.uib.findByName("RetourBtn", createContainer);
        Button button2 = (Button) this.uib.findByName("TransferBtn", createContainer);
        ((Label) this.uib.findByName("Label9", createContainer)).setText(CihMBanking.sesPMTR.getExchangeTransfert().date);
        ((Label) this.uib.findByName("lblEuroValue", createContainer)).setText(sessionCurrentTransactionResume.field3);
        ((Label) this.uib.findByName("Label8", createContainer)).setText(sessionCurrentTransactionResume.Motif);
        button.addActionListener(new 1());
        button2.addActionListener(new 2());
        this.thisContainer.add(createContainer);
        return this.thisContainer;
    }

    class 1 implements ActionListener {
        1() {
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new RecapTransfertFromEtrangerPage$1$1$$ExternalSyntheticLambda0(this)).schedule(300, false, RecapTransfertFromEtrangerPage.this.uiManager.currentForm);
            }

            /* synthetic */ void lambda$run$0$com-b3g-ui-page-RecapTransfertFromEtrangerPage$1$1() {
                RecapTransfertFromEtrangerPage.this.uiManager.stateMachine.showForm("MenuV2Form", (Command) null);
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }

    class 2 implements ActionListener {
        2() {
        }

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                new UITimer(new RecapTransfertFromEtrangerPage$2$1$$ExternalSyntheticLambda0()).schedule(200, false, RecapTransfertFromEtrangerPage.this.uiManager.currentForm);
            }

            static /* synthetic */ void lambda$run$0() {
                CihMBanking.sesPMTR.getCurrentUiManager().NavigateToPage(new TransferFromEtranger(CihMBanking.sesPMTR.getCurrentUiManager(), null, 149));
            }
        }

        public void actionPerformed(ActionEvent actionEvent) {
            Display.getInstance().callSerially(new 1());
        }
    }
}
