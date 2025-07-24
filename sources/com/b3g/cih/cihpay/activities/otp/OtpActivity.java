package com.b3g.cih.cihpay.activities.otp;

import a.a.a.a.c.c;
import a.a.a.a.e.a.b;
import a.a.a.a.e.a.d;
import a.a.a.a.e.a.h;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.b3g.cih.cihpay.R;
import com.b3g.cih.cihpay.activities.TermsConditionsActivity;
import com.b3g.cih.cihpay.activities.enrollement.EnrollmentActivity;
import com.b3g.cih.cihpay.activities.pin.DefinitionPinActivity;
import com.b3g.cih.cihpay.activities.tokenisation.TokenisationActivity;
import com.b3g.cih.cihpay.app.AppConfig;
import com.b3g.cih.cihpay.classes.CIHPaySmsRetrieverReceiver;
import com.b3g.cih.cihpay.models.card.Card;
import com.b3g.cih.cihpay.models.global.ParamInCheckOtpModel;
import com.b3g.cih.cihpay.models.global.RequestModel;
import com.b3g.cih.cihpay.repository.global.GlobalDelegate;
import com.b3g.cih.cihpay.validations.AppExceptions;
import com.dejamobile.cbp.application.Failure;
import com.dejamobile.cbp.wallet.common.model.MpaStatus;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import java.util.List;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OtpActivity extends a.a.a.a.a.a implements GlobalDelegate, b, h {
    public CIHPaySmsRetrieverReceiver g;
    public TextView h;
    public ViewGroup i;
    public EditText j;
    public a.a.a.a.e.b.a k;
    public d l;
    public String m = null;
    public boolean n = false;

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[Failure.Type.values().length];
            a = iArr;
            try {
                iArr[Failure.Type.UNAVAILABLE_NETWORK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static /* synthetic */ void a(Exception exc) {
    }

    public static /* synthetic */ void a(Void r0) {
    }

    public void a() {
        this.n = true;
    }

    public void a(Intent intent) {
    }

    public void a(Card card) {
    }

    public void a(GlobalDelegate.GlobalAction globalAction) {
        this.i.setVisibility(8);
        if (globalAction == GlobalDelegate.GlobalAction.CHECK_OTP) {
            try {
                if (a.a.a.a.c.a.d(((a.a.a.a.a.a) this).a) != null && !a.a.a.a.c.a.d(((a.a.a.a.a.a) this).a).isEmpty()) {
                    startActivity(new Intent(((a.a.a.a.a.a) this).a, TokenisationActivity.class));
                    finish();
                    return;
                }
                Card card = EnrollmentActivity.g;
                if (card != null) {
                    card.setDefaultCard(true);
                }
                d dVar = this.l;
                if (dVar.c == null) {
                    this.n = true;
                    dVar.g();
                    return;
                }
                try {
                    m();
                } catch (Exception e) {
                    a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, getString(R.string.system_error));
                    e.getMessage();
                }
            } catch (Exception unused) {
                a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, getString(R.string.alert_error_otp_incorrect));
            }
        }
    }

    public void a(GlobalDelegate.GlobalAction globalAction, String str) {
        this.i.setVisibility(8);
        a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, str);
        this.j.setText("");
    }

    public void a(Failure failure) {
    }

    public void a(Failure failure, Card card) {
    }

    public void a(String str) {
    }

    public void a(String str, Intent intent) {
    }

    public void a(ArrayList arrayList) {
    }

    public void a(List list) {
    }

    public void a(boolean z) {
        if (!z) {
            this.i.setVisibility(8);
        } else if (this.n) {
            m();
        }
    }

    public void b() {
    }

    public void b(Card card) {
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
            if (this.l.c.getMpaStatus() == MpaStatus.STOPPED) {
                this.l.i();
            } else if (this.n) {
                m();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void e(Failure failure) {
    }

    public void f() {
        this.i.setVisibility(8);
    }

    public void f(Failure failure) {
        this.i.setVisibility(8);
        if (a.a[failure.type.ordinal()] != 1) {
            a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, failure.name(), getString(R.string.system_error));
        } else {
            a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, getString(R.string.no_connection_internet));
        }
    }

    public void g() {
        startActivity(new Intent(((a.a.a.a.a.a) this).a, DefinitionPinActivity.class));
        finish();
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
        try {
            this.i.setVisibility(0);
            if (this.l.c.getMpaStatus() == MpaStatus.ELIGIBLE) {
                this.l.d();
            } else {
                this.l.i();
            }
        } catch (RemoteException e) {
            e.getMessage();
        }
    }

    public final void n() {
        try {
            if (a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a)) {
                Task startSmsRetriever = SmsRetriever.getClient(this).startSmsRetriever();
                startSmsRetriever.addOnSuccessListener(new OtpActivity$$ExternalSyntheticLambda0());
                startSmsRetriever.addOnFailureListener(new OtpActivity$$ExternalSyntheticLambda1());
            }
        } catch (Exception unused) {
        }
    }

    public void onBackButtonClicked(View view) {
        startActivity(new Intent(((a.a.a.a.a.a) this).a, TermsConditionsActivity.class).putExtra("FROM", this.m));
        finish();
    }

    public void onBtnValiderClicked(View view) {
        GlobalDelegate globalDelegate;
        GlobalDelegate.GlobalAction globalAction;
        Activity activity;
        int i;
        a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a);
        String trim = this.j.getText().toString().trim();
        try {
            Activity activity2 = ((a.a.a.a.a.a) this).a;
            if (trim == null || TextUtils.isEmpty(trim.trim())) {
                throw new AppExceptions(activity2.getString(R.string.alert_error_otp_required));
            }
            if (!trim.trim().matches("^[0-9]{6}$")) {
                throw new AppExceptions(activity2.getString(R.string.alert_error_otp_incorrect));
            }
            this.i.setVisibility(0);
            a.a.a.a.e.b.a aVar = this.k;
            if (a.a.a.a.c.b.b(aVar.b)) {
                ParamInCheckOtpModel paramInCheckOtpModel = new ParamInCheckOtpModel(aVar.f, aVar.g);
                paramInCheckOtpModel.setPASSID(trim);
                RequestModel requestModel = new RequestModel();
                requestModel.setServiceId(AppConfig.SERVICE_ID.CHECK_OTP.toString());
                requestModel.setSessionId(aVar.g);
                try {
                    requestModel.setParamIn(a.a.a.a.d.a.a(aVar.g, aVar.a(paramInCheckOtpModel)));
                    aVar.d.d(requestModel).enqueue(new a.a.a.a.e.b.b(aVar));
                    return;
                } catch (Exception unused) {
                    globalDelegate = aVar.c;
                    globalAction = GlobalDelegate.GlobalAction.CHECK_OTP;
                    activity = aVar.b;
                    i = R.string.system_error;
                }
            } else {
                globalDelegate = aVar.c;
                globalAction = GlobalDelegate.GlobalAction.CHECK_OTP;
                activity = aVar.b;
                i = R.string.no_connection_internet;
            }
            globalDelegate.a(globalAction, activity.getString(i));
        } catch (Exception e) {
            a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, e.getMessage());
        }
    }

    public void onCliquezIciButtonClicked(View view) {
        this.i.setVisibility(0);
        this.k.a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_otp);
        ((a.a.a.a.a.a) this).a = this;
        ((a.a.a.a.a.a) this).b = new a.a.a.a.c.d(this);
        this.k = new a.a.a.a.e.b.a(((a.a.a.a.a.a) this).a, this);
        this.l = new d(((a.a.a.a.a.a) this).a, false, this, this);
        this.h = findViewById(R.id.txt_otp_rules);
        this.i = findViewById(R.id.lay_progress);
        this.j = findViewById(R.id.edt_otp);
        if (c.b != null) {
            this.h.setText(getString(R.string.otp_rules, new Object[]{c.b.getGsm()}));
        }
        n();
        if (getIntent().getExtras() != null) {
            this.m = getIntent().getStringExtra("FROM");
        }
    }

    public void onLogoutButtonClicked(View view) {
    }

    public void onPause() {
        super.onPause();
        this.l.f();
    }

    public void onResume() {
        super.onResume();
        this.l.g();
    }

    public void onStart() {
        super/*androidx.appcompat.app.AppCompatActivity*/.onStart();
        try {
            if (a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a)) {
                CIHPaySmsRetrieverReceiver cIHPaySmsRetrieverReceiver = new CIHPaySmsRetrieverReceiver();
                this.g = cIHPaySmsRetrieverReceiver;
                cIHPaySmsRetrieverReceiver.a = new a.a.a.a.a.h.a(this);
                IntentFilter intentFilter = new IntentFilter("com.google.android.gms.auth.api.phone.SMS_RETRIEVED");
                CIHPaySmsRetrieverReceiver cIHPaySmsRetrieverReceiver2 = this.g;
                if (cIHPaySmsRetrieverReceiver2 != null) {
                    registerReceiver(cIHPaySmsRetrieverReceiver2, intentFilter);
                }
            }
        } catch (Exception unused) {
        }
    }

    public void onStop() {
        super/*androidx.appcompat.app.AppCompatActivity*/.onStop();
        try {
            if (a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a)) {
                unregisterReceiver(this.g);
            }
        } catch (Exception unused) {
        }
    }
}
