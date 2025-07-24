package com.b3g.cih.cihpay.activities.check;

import a.a.a.a.a.a;
import a.a.a.a.a.e.b;
import a.a.a.a.a.e.e;
import a.a.a.a.a.e.f;
import a.a.a.a.a.e.g;
import a.a.a.a.b.i;
import a.a.a.a.c.c;
import a.a.a.a.c.d;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import com.b3g.cih.cihpay.R;
import com.b3g.cih.cihpay.app.AppConfig;
import com.b3g.cih.cihpay.models.profile.Profile;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CheckActivity extends a {
    public ViewPager g;
    public DotsIndicator h;
    public i i;
    public ViewGroup j;
    public ViewGroup k;
    public ViewGroup l;
    public Button m;
    public g n;

    public final void m() {
        ArrayList serializableExtra = getIntent().getSerializableExtra("USER");
        String stringExtra = getIntent().getStringExtra("SESSION_ID");
        if (serializableExtra != null && !serializableExtra.isEmpty()) {
            c.b = (Profile) serializableExtra.get(0);
        }
        if (c.b == null || stringExtra == null || stringExtra.trim().isEmpty()) {
            finish();
        } else {
            c.c = c.b.getRadical();
            c.a = stringExtra;
        }
    }

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onCreate(Bundle bundle) {
        String str = "/";
        super.onCreate(bundle);
        setContentView(R.layout.activity_check);
        ((a) this).a = this;
        c.d = -1;
        findViewById(R.id.btn_next).setOnClickListener(new CheckActivity$$ExternalSyntheticLambda0(this));
        if (getIntent().getExtras() != null) {
            try {
                String stringExtra = getIntent().getStringExtra("BASE_URL");
                if (stringExtra != null && !stringExtra.trim().isEmpty()) {
                    if (stringExtra.endsWith("/")) {
                        str = "";
                    }
                    AppConfig.a = stringExtra + str;
                }
            } catch (Exception e) {
                String str2 = "initBaseUrl Failed : " + e.getMessage();
            }
            m();
            m();
        } else {
            finish();
        }
        g gVar = new ViewModelProvider(this).get(g.class);
        this.n = gVar;
        Activity activity = ((a) this).a;
        gVar.a = activity;
        gVar.b = new d(activity);
        gVar.c = new a.a.a.a.e.a.d(activity, false, gVar, gVar);
        String str3 = "" + gVar.b.a.getBoolean("key_app_tuto", true);
        gVar.e.setValue(Boolean.valueOf(gVar.b.a.getBoolean("key_app_tuto", true)));
        gVar.g.setValue(activity.getString(R.string.next));
        this.n.d.observe(this, new a.a.a.a.a.e.a(this));
        this.n.e.observe(this, new b(this));
        this.n.f.observe(this, new a.a.a.a.a.e.c(this));
        this.n.g.observe(this, new a.a.a.a.a.e.d(this));
        this.n.i.observe(this, new e(this));
        this.j = findViewById(R.id.lay_not_eligible);
        this.l = findViewById(R.id.lay_progress);
        this.k = findViewById(R.id.lay_tuto);
        this.m = findViewById(R.id.btn_next);
        this.j.setVisibility(8);
        this.l.setVisibility(8);
        if (((Boolean) this.n.e.getValue()).booleanValue()) {
            this.g = findViewById(R.id.viewpager);
            this.h = findViewById(R.id.indicatorView);
            i iVar = new i(((a) this).a);
            this.i = iVar;
            this.g.setAdapter(iVar);
            this.h.setViewPager(this.g);
            this.g.addOnPageChangeListener(new f(this));
        }
    }

    public void onLogoutButtonClicked(View view) {
    }

    public void onNextButtonClicked(View view) {
        g gVar = this.n;
        int i = gVar.h;
        if (i < 1) {
            gVar.i.setValue(Integer.valueOf(i + 1));
        } else {
            gVar.e.setValue(Boolean.FALSE);
            gVar.k();
        }
    }

    public void onPause() {
        this.n.c.f();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.n.c.g();
    }

    public void onRetourButtonClicked(View view) {
        finish();
    }
}
