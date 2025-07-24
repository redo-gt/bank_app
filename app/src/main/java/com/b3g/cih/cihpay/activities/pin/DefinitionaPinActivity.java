package com.b3g.cih.cihpay.activities.pin;

import a.a.a.a.a.a;
import a.a.a.a.e.a.b;
import a.a.a.a.e.a.d;
import a.a.a.a.e.a.f;
import a.a.a.a.e.a.h;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.b3g.cih.cihpay.R;
import com.b3g.cih.cihpay.activities.tokenisation.TokenisationActivity;
import com.b3g.cih.cihpay.app.AppController;
import com.b3g.cih.cihpay.models.card.Card;
import com.dejamobile.cbp.application.Failure;
import com.dejamobile.cbp.wallet.common.model.CvmType;
import com.dejamobile.cbp.wallet.common.model.MpaStatus;
import com.mukesh.OtpView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DefinitionPinActivity extends a implements b, h {
    public d g;
    public OtpView h;
    public ArrayList i = new ArrayList();
    public Button j;
    public ViewGroup k;
    public TextView l;
    public byte[] m;

    public void a() {
    }

    public void a(Intent intent) {
    }

    public void a(Failure failure) {
    }

    public void a(Failure failure, Card card) {
    }

    public void a(String str) {
    }

    public void a(String str, Intent intent) {
    }

    public void a(List list) {
    }

    public void a(boolean z) {
        try {
            if (this.g.c.getMpaStatus() == MpaStatus.ELIGIBLE) {
                try {
                    AppController.a(true);
                    this.k.setVisibility(0);
                    if (this.g.c.getMpaStatus() == MpaStatus.ELIGIBLE) {
                        this.g.d();
                    }
                } catch (RemoteException e) {
                    e.getMessage();
                }
            }
        } catch (RemoteException unused) {
        }
    }

    public void b() {
    }

    public void b(Card card) {
    }

    public void b(Failure failure) {
    }

    public final void b(String str) {
        AppController.a(true);
        this.k.setVisibility(0);
        d dVar = this.g;
        byte[] bArr = this.m;
        dVar.getClass();
        byte[] bArr2 = new byte[4];
        String str2 = "";
        for (int i = 0; i < 4; i++) {
            int intValue = Integer.valueOf("" + str.charAt(i)).intValue();
            bArr2[i] = bArr[intValue];
            str2 = str2 + "" + bArr[intValue];
        }
        Map hashMap = new HashMap();
        hashMap.put(CvmType.MOBILE_PIN.name(), bArr2);
        try {
            dVar.c.assignCvm(dVar.b.a.getString("key_wallet_id", (String) null), hashMap, new f(dVar));
        } catch (Exception e) {
            String str3 = "ERROR  entryPoint.assignCvm : " + e.getMessage();
        }
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
            if (this.g.c.getMpaStatus() == MpaStatus.STOPPED) {
                this.g.i();
            } else if (this.g.c.getMpaStatus() == MpaStatus.REGISTERED) {
                a.a.a.a.c.a.c(((a) this).a);
                a.a.a.a.c.a.b(((a) this).a);
                startActivity(new Intent(((a) this).a, TokenisationActivity.class).putExtra("CARDS", a.a.a.a.c.a.d(((a) this).a)));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.k.setVisibility(8);
        AppController.a(false);
    }

    public void e(Failure failure) {
        this.k.setVisibility(8);
        AppController.a(false);
    }

    public void f() {
        this.k.setVisibility(8);
        AppController.a(false);
    }

    public void f(Failure failure) {
        this.k.setVisibility(8);
        AppController.a(false);
    }

    public void g() {
    }

    public void g(Failure failure) {
    }

    public void h() {
        startActivity(new Intent(((a) this).a, TokenisationActivity.class).putExtra("CARDS", a.a.a.a.c.a.d(((a) this).a)));
        finish();
    }

    public void i() {
        this.k.setVisibility(8);
        AppController.a(false);
    }

    public void j() {
    }

    public void l() {
        this.k.setVisibility(8);
        AppController.a(false);
    }

    public void onBackButtonClicked(View view) {
        if (this.k.getVisibility() != 0) {
            finish();
        }
    }

    public void onBackPressed() {
        onBackButtonClicked(null);
    }

    public void onBtnDefinerClicked(View view) {
        if (!a.a.a.a.c.b.b(((a) this).a)) {
            a.a.a.a.c.b.a(((a) this).a, getString(R.string.no_connection_internet));
            return;
        }
        if (this.h.getText() == null || this.h.getText().length() != 4) {
            return;
        }
        String obj = this.h.getText().toString();
        try {
            if (!a.a.a.a.c.b.b(obj)) {
                a.a.a.a.c.b.a(((a) this).a, getString(R.string.error_pin_invalid));
                this.h.setText("");
            } else if (this.g.c.getMpaStatus() == MpaStatus.REGISTERED_WO_CVM) {
                this.m = this.g.c.generateKeyboardMapping();
                b(obj);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void onClearAllClicked(View view) {
        this.h.setText("");
    }

    public void onClearOneClicked(View view) {
        if (TextUtils.isEmpty(this.h.getText())) {
            return;
        }
        this.h.setText(this.h.getText().toString().substring(0, r3.length() - 1));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_definition_pin);
        ((a) this).a = this;
        ((a) this).b = new a.a.a.a.c.d(this);
        this.g = new d(((a) this).a, false, this, this);
        this.l = findViewById(R.id.txt_pin_description);
        ViewGroup findViewById = findViewById(R.id.lay_progress);
        this.k = findViewById;
        findViewById.setVisibility(0);
        this.j = findViewById(R.id.btn_definition);
        OtpView findViewById2 = findViewById(R.id.pin_view);
        this.h = findViewById2;
        findViewById2.setFocusable(false);
        this.h.setOtpCompletionListener(new a.a.a.a.a.k.a(this));
        this.h.addTextChangedListener(new a.a.a.a.a.k.b(this));
        a.a.a.a.c.b.a(this, this.i);
    }

    public void onLogoutButtonClicked(View view) {
    }

    public void onNumberClicked(View view) {
        this.h.setText(this.h.getText() + ((TextView) view).getText().toString() + "");
    }

    public void onPause() {
        super.onPause();
        this.g.f();
        AppController.c = false;
    }

    public void onResume() {
        super.onResume();
        this.g.g();
    }
}
