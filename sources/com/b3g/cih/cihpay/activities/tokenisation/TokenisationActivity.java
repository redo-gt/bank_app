package com.b3g.cih.cihpay.activities.tokenisation;

import a.a.a.a.c.c;
import a.a.a.a.e.a.b;
import a.a.a.a.e.a.d;
import a.a.a.a.e.a.h;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.b3g.cih.cihpay.R;
import com.b3g.cih.cihpay.activities.enrollement.EnrollmentActivity;
import com.b3g.cih.cihpay.activities.home.HomeActivity;
import com.b3g.cih.cihpay.app.AppConfig;
import com.b3g.cih.cihpay.app.AppController;
import com.b3g.cih.cihpay.models.card.Card;
import com.b3g.cih.cihpay.models.global.ParamInTokenizationModel;
import com.b3g.cih.cihpay.models.global.RequestModel;
import com.b3g.cih.cihpay.repository.global.GlobalDelegate;
import com.dejamobile.cbp.application.Failure;
import com.dejamobile.cbp.wallet.common.model.MpaStatus;
import java.util.ArrayList;
import java.util.List;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TokenisationActivity extends a.a.a.a.a.a implements b, h, GlobalDelegate {
    public d g;
    public a.a.a.a.e.b.a h;
    public ViewGroup i;
    public ProgressBar j;
    public TextView k;
    public TextView l;
    public Button m;
    public Handler n = new Handler();
    public Card o = null;
    public boolean p = false;

    public class a implements DialogInterface.OnClickListener {
        public a() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            TokenisationActivity.this.finish();
            TokenisationActivity.this.g.f();
        }
    }

    public static /* synthetic */ void $r8$lambda$J062mFWNh7TN8EiJyqqt-illHJ0(TokenisationActivity tokenisationActivity) {
        tokenisationActivity.o();
    }

    private /* synthetic */ void o() {
        try {
            if (isFinishing() || this.p) {
                return;
            }
            n();
        } catch (Exception unused) {
        }
    }

    public void a() {
    }

    public void a(Intent intent) {
    }

    public void a(Card card) {
        p();
    }

    public void a(GlobalDelegate.GlobalAction globalAction) {
    }

    public void a(GlobalDelegate.GlobalAction globalAction, String str) {
        if (globalAction == GlobalDelegate.GlobalAction.TOKENIZATION) {
            if (str.contains(getString(R.string.system_error))) {
                a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, str);
            } else {
                n();
            }
        }
    }

    public void a(Failure failure) {
    }

    public void a(Failure failure, Card card) {
        m();
    }

    public void a(String str) {
        this.p = true;
        this.o.setDcId(str);
        ((a.a.a.a.a.a) this).b.a(false);
        Activity activity = ((a.a.a.a.a.a) this).a;
        String str2 = c.c;
        SharedPreferences sharedPreferences = a.a.a.a.c.a.a;
        if (str2 != null && activity != null) {
            a.a.a.a.c.a.f(activity);
            a.a.a.a.c.a.a.edit().putString("RADICAL", str2).commit();
        }
        a.a.a.a.c.a.a(((a.a.a.a.a.a) this).a, this.o);
        a.a.a.a.c.a.c(((a.a.a.a.a.a) this).a);
        if (this.o.isDefaultCard()) {
            this.g.a(this.o);
        } else {
            m();
        }
    }

    public void a(String str, Intent intent) {
    }

    public void a(ArrayList arrayList) {
    }

    public void a(List list) {
    }

    public void a(boolean z) {
    }

    public void b() {
    }

    public void b(Card card) {
        m();
    }

    public void b(Failure failure) {
    }

    public void c() {
    }

    public void c(Failure failure) {
    }

    public void d() {
    }

    public void d(Failure failure) {
    }

    public void e() {
        try {
            if (this.g.c.getMpaStatus() != MpaStatus.REGISTERED) {
                a.a.a.a.c.a.c(((a.a.a.a.a.a) this).a);
                finish();
            }
        } catch (RemoteException unused) {
        }
    }

    public void e(Failure failure) {
    }

    public void f() {
    }

    public void f(Failure failure) {
    }

    public void g() {
    }

    public void g(Failure failure) {
    }

    public void h() {
    }

    public void i() {
    }

    public void j() {
    }

    public void l() {
    }

    public final void m() {
        this.i.setVisibility(0);
        this.j.setVisibility(8);
        this.k.setText(getString(R.string.tokenisation_done));
        this.l.setVisibility(0);
        this.m.setVisibility(0);
        this.m.setClickable(true);
    }

    public final void n() {
        try {
            if (isFinishing() || this.p || !a.a.a.a.c.b.b(((a.a.a.a.a.a) this).a)) {
                return;
            }
            a.a.a.a.c.a.c(((a.a.a.a.a.a) this).a);
            new AlertDialog.Builder(((a.a.a.a.a.a) this).a).setMessage(R.string.digitalisation_echoue).setCancelable(false).setPositiveButton(R.string.ok, new a()).show();
        } catch (Exception unused) {
        }
    }

    public void onBackButtonClicked(View view) {
        if (this.j.getVisibility() != 0) {
            this.g.f();
            a.a.a.a.c.a.c(((a.a.a.a.a.a) this).a);
            finish();
        }
    }

    public void onBackPressed() {
        onBackButtonClicked(null);
    }

    public void onBtnOkClicked(View view) {
        if (this.m.getVisibility() == 0) {
            this.g.f();
            startActivity(new Intent(((a.a.a.a.a.a) this).a, HomeActivity.class));
            finish();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        window.addFlags(4718592);
        window.addFlags(2097280);
        setContentView(R.layout.activity_tokenisation);
        c.d = -1;
        ((a.a.a.a.a.a) this).a = this;
        ((a.a.a.a.a.a) this).b = new a.a.a.a.c.d(this);
        this.g = new d(((a.a.a.a.a.a) this).a, false, this, this);
        this.h = new a.a.a.a.e.b.a(((a.a.a.a.a.a) this).a, this);
        this.i = findViewById(R.id.lay_done);
        this.j = findViewById(R.id.progressbar);
        this.k = findViewById(R.id.txt_description);
        this.l = findViewById(R.id.txt_success_description);
        this.m = findViewById(R.id.btn_ok);
        Card card = EnrollmentActivity.g;
        this.o = card;
        if (card == null) {
            finish();
            a.a.a.a.c.a.c(((a.a.a.a.a.a) this).a);
            return;
        }
        this.k.setText(getString(R.string.tokenisation_encours));
        this.i.setVisibility(8);
        this.l.setVisibility(4);
        this.m.setVisibility(4);
        this.m.setClickable(false);
        a.a.a.a.e.b.a aVar = this.h;
        Card card2 = this.o;
        aVar.getClass();
        if (card2 == null) {
            aVar.c.a(GlobalDelegate.GlobalAction.CHECK_OTP, aVar.b.getString(R.string.system_error));
            return;
        }
        ParamInTokenizationModel paramInTokenizationModel = new ParamInTokenizationModel(card2.getNumeroCarte().charAt(0) == '4' ? 'V' : 'M', "" + card2.getRadicalClient() + card2.getCodeProduit() + card2.getPsn(), aVar.g, aVar.e.a.getString("hceTechnicalId", (String) null), aVar.f, aVar.g);
        RequestModel requestModel = new RequestModel();
        requestModel.setServiceId(AppConfig.SERVICE_ID.TOKENIZATION.toString());
        requestModel.setSessionId(aVar.g);
        try {
            requestModel.setParamIn(a.a.a.a.d.a.a(aVar.g, aVar.a(paramInTokenizationModel)));
            aVar.d.b(requestModel).enqueue(new a.a.a.a.e.b.d(aVar, card2));
        } catch (Exception unused) {
            aVar.c.a(GlobalDelegate.GlobalAction.TOKENIZATION, aVar.b.getString(R.string.system_error));
        }
    }

    public void onLogoutButtonClicked(View view) {
    }

    public void onPause() {
        super.onPause();
        if (this.p) {
            this.g.f();
        }
        AppController.c = false;
    }

    public void onResume() {
        super.onResume();
        this.g.g();
        AppController.c = true;
    }

    public final void p() {
        this.n.postDelayed(new TokenisationActivity$$ExternalSyntheticLambda0(this), 60000L);
    }
}
