package com.b3g.cih.cihpay.activities.enrollement;

import a.a.a.a.b.h;
import a.a.a.a.e.b.c;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.b3g.cih.cihpay.R;
import com.b3g.cih.cihpay.activities.TermsConditionsActivity;
import com.b3g.cih.cihpay.app.AppConfig;
import com.b3g.cih.cihpay.models.card.Card;
import com.b3g.cih.cihpay.models.global.ParamInGetCardModel;
import com.b3g.cih.cihpay.models.global.RequestModel;
import com.b3g.cih.cihpay.repository.global.GlobalDelegate;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class EnrollmentActivity extends a.a.a.a.a.a implements GlobalDelegate {
    public static Card g;
    public SwipeRefreshLayout h;
    public RecyclerView i;
    public h j;
    public ArrayList k = new ArrayList();
    public a.a.a.a.e.b.a l;
    public Button m;
    public ViewGroup n;

    public class a implements h.b {
        public a() {
        }
    }

    public class b implements SwipeRefreshLayout.OnRefreshListener {
        public b() {
        }

        public void onRefresh() {
            EnrollmentActivity enrollmentActivity = EnrollmentActivity.this;
            Card card = EnrollmentActivity.g;
            enrollmentActivity.o();
        }
    }

    public void a(Card card) {
    }

    public void a(GlobalDelegate.GlobalAction globalAction) {
        n();
    }

    public void a(GlobalDelegate.GlobalAction globalAction, String str) {
        n();
    }

    public void a(ArrayList arrayList) {
        this.k.clear();
        this.k.addAll(arrayList);
        n();
    }

    public final void m() {
        this.n = findViewById(R.id.lay_progress);
        this.h = findViewById(R.id.swipeRefreshLayout);
        this.m = findViewById(R.id.btn_begin);
        RecyclerView findViewById = findViewById(R.id.selection_recyclerView);
        this.i = findViewById;
        findViewById.setLayoutManager(new LinearLayoutManager(((a.a.a.a.a.a) this).a));
        h hVar = new h(((a.a.a.a.a.a) this).a, this.k);
        this.j = hVar;
        this.i.setAdapter(hVar);
        this.j.c = new a();
        this.h.setOnRefreshListener(new b());
        this.n.setVisibility(8);
        findViewById(R.id.btn_begin).setOnClickListener(new EnrollmentActivity$$ExternalSyntheticLambda0(this));
    }

    public final void n() {
        this.j.notifyDataSetChanged();
        this.h.setRefreshing(false);
        this.n.setVisibility(8);
    }

    public final void o() {
        GlobalDelegate globalDelegate;
        GlobalDelegate.GlobalAction globalAction;
        Activity activity;
        int i;
        this.h.setRefreshing(true);
        this.k.clear();
        a.a.a.a.e.b.a aVar = this.l;
        aVar.getClass();
        ArrayList arrayList = new ArrayList();
        if (a.a.a.a.c.b.b(aVar.b)) {
            ParamInGetCardModel paramInGetCardModel = new ParamInGetCardModel(aVar.f, aVar.g);
            RequestModel requestModel = new RequestModel();
            requestModel.setServiceId(AppConfig.SERVICE_ID.GET_CARDS.toString());
            requestModel.setSessionId(aVar.g);
            try {
                requestModel.setParamIn(a.a.a.a.d.a.a(aVar.g, aVar.a(paramInGetCardModel)).replaceAll("\\u003d", "="));
                aVar.d.c(requestModel).enqueue(new c(aVar, arrayList));
                return;
            } catch (Exception unused) {
                globalDelegate = aVar.c;
                globalAction = GlobalDelegate.GlobalAction.GET_CARDS;
                activity = aVar.b;
                i = R.string.system_error;
            }
        } else {
            globalDelegate = aVar.c;
            globalAction = GlobalDelegate.GlobalAction.GET_CARDS;
            activity = aVar.b;
            i = R.string.no_connection_internet;
        }
        globalDelegate.a(globalAction, activity.getString(i));
    }

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onBackPressed() {
        onBackButtonClicked(null);
    }

    public void onBtnDigitaliserCartesClicked(View view) {
        if (g != null) {
            startActivity(new Intent(((a.a.a.a.a.a) this).a, TermsConditionsActivity.class));
            finish();
        } else {
            if (this.k.isEmpty()) {
                return;
            }
            a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, getString(R.string.error_no_selction_card));
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_enrollment);
        g = null;
        ((a.a.a.a.a.a) this).a = this;
        this.l = new a.a.a.a.e.b.a(this, this);
        m();
        o();
    }

    public void onLogoutButtonClicked(View view) {
    }

    public void onResume() {
        super.onResume();
    }
}
