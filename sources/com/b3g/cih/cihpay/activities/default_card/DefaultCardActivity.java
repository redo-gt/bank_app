package com.b3g.cih.cihpay.activities.default_card;

import a.a.a.a.b.e;
import a.a.a.a.e.a.b;
import a.a.a.a.e.a.d;
import a.a.a.a.e.a.h;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.b3g.cih.cihpay.R;
import com.b3g.cih.cihpay.activities.enrollement.EnrollmentActivity;
import com.b3g.cih.cihpay.activities.pin.DefinitionPinActivity;
import com.b3g.cih.cihpay.models.card.Card;
import com.dejamobile.cbp.application.Failure;
import com.dejamobile.cbp.application.IServiceEntryPoint;
import com.dejamobile.cbp.wallet.common.model.MpaStatus;
import java.util.ArrayList;
import java.util.List;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DefaultCardActivity extends a.a.a.a.a.a implements b, h {
    public d g;
    public Button h;
    public RecyclerView i;
    public e j;
    public ViewGroup l;
    public ArrayList k = new ArrayList();
    public boolean m = false;

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

    public void a() {
        this.m = true;
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
        if (!z) {
            this.l.setVisibility(8);
        } else if (this.m) {
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
            if (this.g.c.getMpaStatus() == MpaStatus.STOPPED) {
                this.g.i();
            } else if (this.m) {
                m();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void e(Failure failure) {
    }

    public void f() {
        this.l.setVisibility(8);
    }

    public void f(Failure failure) {
        this.l.setVisibility(8);
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
        this.l.setVisibility(8);
    }

    public void j() {
    }

    public void l() {
    }

    public final void m() {
        try {
            this.l.setVisibility(0);
            if (this.g.c.getMpaStatus() == MpaStatus.ELIGIBLE) {
                this.g.d();
            } else {
                this.g.i();
            }
        } catch (RemoteException e) {
            e.getMessage();
        }
    }

    public void onBackButtonClicked(View view) {
        if (this.l.getVisibility() != 0) {
            finish();
        }
    }

    public void onBackPressed() {
        onBackButtonClicked(null);
    }

    public void onChoisirButtonClicked(View view) {
        if (!a.a.a.a.c.b.b(((a.a.a.a.a.a) this).a)) {
            a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, getString(R.string.no_connection_internet));
            return;
        }
        if (this.j.c <= -1) {
            a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, getString(R.string.alert_error_choose_default_card));
            return;
        }
        d dVar = this.g;
        IServiceEntryPoint iServiceEntryPoint = dVar.c;
        if (iServiceEntryPoint == null) {
            this.m = true;
            dVar.g();
            return;
        }
        try {
            if (iServiceEntryPoint.getMpaStatus() == MpaStatus.REGISTERED_WO_CVM) {
                startActivity(new Intent(((a.a.a.a.a.a) this).a, DefinitionPinActivity.class));
                finish();
            } else {
                m();
            }
        } catch (Exception e) {
            a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, getString(R.string.system_error));
            e.getMessage();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_default_card);
        ((a.a.a.a.a.a) this).a = this;
        this.g = new d(this, false, this, this);
        this.k.clear();
        Card card = EnrollmentActivity.g;
        if (card != null) {
            card.setDefaultCard(true);
            this.k.add(card);
        }
        this.l = findViewById(R.id.lay_progress);
        Button findViewById = findViewById(R.id.btn_choisir);
        this.h = findViewById;
        findViewById.setClickable(false);
        this.h.setEnabled(false);
        RecyclerView findViewById2 = findViewById(R.id.recyclerView);
        this.i = findViewById2;
        findViewById2.setLayoutManager(new GridLayoutManager(((a.a.a.a.a.a) this).a, 2));
        e eVar = new e(((a.a.a.a.a.a) this).a, this.k);
        this.j = eVar;
        eVar.d = new a.a.a.a.a.f.a(this);
        this.i.setAdapter(eVar);
        ArrayList arrayList = this.k;
        if (arrayList != null && arrayList.size() == 1) {
            e eVar2 = this.j;
            eVar2.c = 0;
            eVar2.notifyDataSetChanged();
            this.j.d.a();
        }
        this.j.notifyDataSetChanged();
        this.l.setVisibility(8);
        this.i.setVisibility(0);
    }

    public void onLogoutButtonClicked(View view) {
    }

    public void onPause() {
        super.onPause();
        this.g.f();
    }

    public void onResume() {
        super.onResume();
        this.g.g();
    }
}
