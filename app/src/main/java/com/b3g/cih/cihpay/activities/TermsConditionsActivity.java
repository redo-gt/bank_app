package com.b3g.cih.cihpay.activities;

import a.a.a.a.c.b;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.b3g.cih.cihpay.R;
import com.b3g.cih.cihpay.activities.otp.OtpActivity;
import com.b3g.cih.cihpay.models.card.Card;
import com.b3g.cih.cihpay.repository.global.GlobalDelegate;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TermsConditionsActivity extends a.a.a.a.a.a implements GlobalDelegate {
    public CheckBox g;
    public Button h;
    public ViewGroup i;
    public ViewGroup j;
    public a.a.a.a.e.b.a k;
    public String l = null;

    public class a implements CompoundButton.OnCheckedChangeListener {
        public a() {
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            TermsConditionsActivity.this.h.setBackgroundResource(z ? R.drawable.btn_app_theme : R.drawable.border_enabled_button);
            TermsConditionsActivity.this.h.setClickable(z);
        }
    }

    public void a(Card card) {
    }

    public void a(GlobalDelegate.GlobalAction globalAction) {
        this.i.setVisibility(8);
        startActivity(new Intent(((a.a.a.a.a.a) this).a, OtpActivity.class).putExtra("FROM", this.l));
        finish();
    }

    public void a(GlobalDelegate.GlobalAction globalAction, String str) {
        this.i.setVisibility(8);
        if (globalAction == GlobalDelegate.GlobalAction.SEND_OTP) {
            b.a(((a.a.a.a.a.a) this).a, str);
        }
    }

    public void a(ArrayList arrayList) {
    }

    public final void m() {
        this.j = findViewById(R.id.lay_accept);
        this.i = findViewById(R.id.lay_progress);
        this.g = findViewById(R.id.checkbox);
        Button findViewById = findViewById(R.id.btn_accepter);
        this.h = findViewById;
        findViewById.setOnClickListener(new TermsConditionsActivity$$ExternalSyntheticLambda0(this));
        this.i.setVisibility(8);
        this.g.setOnCheckedChangeListener(new a());
    }

    public void onAccepterButtonClicked(View view) {
        if (this.g.isChecked()) {
            if (!b.b(((a.a.a.a.a.a) this).a)) {
                b.a(((a.a.a.a.a.a) this).a, getString(R.string.no_connection_internet));
            } else {
                this.i.setVisibility(0);
                this.k.a();
            }
        }
    }

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_terms_conditions);
        ((a.a.a.a.a.a) this).a = this;
        this.k = new a.a.a.a.e.b.a(this, this);
        m();
        if (getIntent().getExtras() != null) {
            String stringExtra = getIntent().getStringExtra("FROM");
            this.l = stringExtra;
            if (stringExtra != null) {
                this.j.setVisibility(8);
            }
        }
    }

    public void onLogoutButtonClicked(View view) {
    }
}
