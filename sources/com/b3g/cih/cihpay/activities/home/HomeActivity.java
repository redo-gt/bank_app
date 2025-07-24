package com.b3g.cih.cihpay.activities.home;

import a.a.a.a.b.f;
import a.a.a.a.c.d;
import a.a.a.a.e.a.h;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.nfc.NfcManager;
import android.nfc.cardemulation.CardEmulation;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.b3g.cih.cihpay.R;
import com.b3g.cih.cihpay.activities.cards.CartesActivity;
import com.b3g.cih.cihpay.activities.paiement.PaiementActivity;
import com.b3g.cih.cihpay.activities.parametres.ParametersActivity;
import com.b3g.cih.cihpay.models.card.Card;
import com.b3g.cih.cihpay.widgets.CustomViewPager;
import com.dejamobile.cbp.application.ContactlessEntryPoint;
import com.dejamobile.cbp.application.Failure;
import com.dejamobile.cbp.wallet.common.model.CardInfo;
import com.dejamobile.cbp.wallet.common.model.MpaStatus;
import com.rd.PageIndicatorView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class HomeActivity extends a.a.a.a.a.a implements a.a.a.a.e.a.b, h {
    public d g;
    public a.a.a.a.e.a.d h;
    public ViewGroup i;
    public ViewGroup j;
    public f k;
    public ArrayList l = new ArrayList();
    public CustomViewPager m;
    public PageIndicatorView n;
    public TextView o;
    public TextView p;
    public ImageView q;

    public class a implements DialogInterface.OnClickListener {
        public a(HomeActivity homeActivity) {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    public class b implements DialogInterface.OnClickListener {
        public b() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            HomeActivity.this.startActivity(new Intent("android.settings.NFC_SETTINGS"));
        }
    }

    public class c implements DialogInterface.OnClickListener {
        public c() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            HomeActivity.this.onBackButtonClicked(null);
        }
    }

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
        CustomViewPager customViewPager;
        int i = 0;
        if (list.isEmpty()) {
            a.a.a.a.c.a.b(((a.a.a.a.a.a) this).a);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.no_digital_card).setPositiveButton(R.string.ok, new c());
            builder.setCancelable(false);
            builder.show();
            return;
        }
        this.h.b();
        this.l.clear();
        if (a.a.a.a.c.a.d(((a.a.a.a.a.a) this).a) != null) {
            Iterator it = a.a.a.a.c.a.d(((a.a.a.a.a.a) this).a).iterator();
            while (it.hasNext()) {
                Card card = (Card) it.next();
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (card.getDcId().equals(((CardInfo) list.get(i2)).getDcId())) {
                        card.setToken(((CardInfo) list.get(i2)).getTokenLast4Digits());
                        card.setStatus(((CardInfo) list.get(i2)).getCardStatus().toString());
                        this.l.add(card);
                    }
                }
                c(card);
            }
            a.a.a.a.c.a.b(((a.a.a.a.a.a) this).a);
            a.a.a.a.c.a.a(((a.a.a.a.a.a) this).a, this.l);
            a.a.a.a.c.a.d(((a.a.a.a.a.a) this).a);
            f fVar = new f(((a.a.a.a.a.a) this).a, this.l);
            this.k = fVar;
            this.m.setAdapter(fVar);
            this.n.setCount(this.l.size());
            if (this.l.size() > 1) {
                this.n.setVisibility(0);
            } else {
                this.n.setVisibility(8);
            }
            this.m.setPadding(((a.a.a.a.a.a) this).a.getResources().getDimensionPixelSize(R.dimen._50sdp), 0, ((a.a.a.a.a.a) this).a.getResources().getDimensionPixelSize(R.dimen._50sdp), 0);
            this.o.setVisibility(this.l.size() > 1 ? 0 : 8);
            this.m.setFadeEnabled(true);
            this.m.setAnimationEnabled(true);
            this.m.addOnPageChangeListener(new a.a.a.a.a.g.a(this));
            while (true) {
                if (i >= this.l.size()) {
                    break;
                }
                Card card2 = (Card) this.l.get(i);
                if (card2.isDefaultCard()) {
                    int i3 = a.a.a.a.c.c.d;
                    if (i3 == -1 || i3 >= this.l.size()) {
                        customViewPager = this.m;
                    } else {
                        customViewPager = this.m;
                        i = a.a.a.a.c.c.d;
                    }
                    customViewPager.setCurrentItem(i);
                    this.h.a(card2);
                } else {
                    c(card2);
                    i++;
                }
            }
        } else {
            try {
                this.h.c.reset();
            } catch (RemoteException unused) {
            }
            finish();
        }
        this.i.setVisibility(8);
    }

    public void a(boolean z) {
    }

    public void b() {
    }

    public void b(Card card) {
    }

    public void b(Failure failure) {
    }

    public void c() {
    }

    public final void c(Card card) {
        TextView textView;
        Resources resources;
        int i;
        if (card.getStatus().equals("SUSPENDED")) {
            this.q.setImageDrawable(getResources().getDrawable(R.drawable.ic_nfc_disabled));
            textView = this.p;
            resources = getResources();
            i = R.color.gray2;
        } else {
            this.q.setImageDrawable(getResources().getDrawable(R.drawable.ic_nfc));
            textView = this.p;
            resources = getResources();
            i = R.color.blue_btn;
        }
        textView.setTextColor(resources.getColor(i));
    }

    public void c(Failure failure) {
    }

    public void d() {
    }

    public void d(Failure failure) {
    }

    public void e() {
        try {
            this.h.h();
            if (this.h.c.getMpaStatus() == MpaStatus.REGISTERED) {
                this.h.c();
            } else {
                finish();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void e(Failure failure) {
    }

    public void f() {
        this.i.setVisibility(8);
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

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onBackPressed() {
        onBackButtonClicked(null);
    }

    public void onBtnCartesClicked(View view) {
        startActivity(new Intent(((a.a.a.a.a.a) this).a, CartesActivity.class));
    }

    public void onBtnPayerClicked(View view) {
        if (this.l.isEmpty()) {
            return;
        }
        Card card = (Card) this.l.get(this.m.getCurrentItem());
        if (card.getStatus().equals("SUSPENDED")) {
            return;
        }
        if (a.a.a.a.c.b.b(((a.a.a.a.a.a) this).a)) {
            startActivity(new Intent(((a.a.a.a.a.a) this).a, PaiementActivity.class).putExtra("CARD", card));
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(((a.a.a.a.a.a) this).a);
        builder.setTitle(R.string.nfc_not_activated);
        builder.setMessage(R.string.go_to_activate_nfc);
        builder.setPositiveButton(R.string.yes, new b()).setNegativeButton(R.string.annuler, new a(this)).setCancelable(false).show();
    }

    public void onBtnSettingsClicked(View view) {
        startActivity(new Intent(((a.a.a.a.a.a) this).a, ParametersActivity.class));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home);
        ((a.a.a.a.a.a) this).a = this;
        this.h = new a.a.a.a.e.a.d(this, false, this, this);
        d dVar = new d(((a.a.a.a.a.a) this).a);
        this.g = dVar;
        dVar.a(false);
        this.j = findViewById(R.id.lay_bottom_tab_buttons);
        this.o = findViewById(R.id.txt_choose_card);
        this.i = findViewById(R.id.lay_progress);
        this.m = findViewById(R.id.viewPager);
        this.q = findViewById(R.id.img_payer);
        this.p = findViewById(R.id.txt_payer);
        PageIndicatorView findViewById = findViewById(R.id.cardsIndicatorView);
        this.n = findViewById;
        findViewById.setVisibility(8);
        this.o.setVisibility(8);
        if (getIntent().getExtras() == null || !getIntent().getBooleanExtra("PAYER", false)) {
            this.j.setVisibility(0);
        } else {
            this.j.setVisibility(8);
        }
        this.i.setVisibility(0);
        Activity activity = ((a.a.a.a.a.a) this).a;
        if (CardEmulation.getInstance(((NfcManager) activity.getSystemService("nfc")).getDefaultAdapter()).isDefaultServiceForCategory(new ComponentName(activity, ContactlessEntryPoint.class), "payment")) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.nfc.cardemulation.action.ACTION_CHANGE_DEFAULT");
        intent.putExtra("component", new ComponentName(this, ContactlessEntryPoint.class));
        intent.putExtra("category", "payment");
        startActivity(intent);
    }

    public void onLogoutButtonClicked(View view) {
    }

    public void onPause() {
        super.onPause();
        this.h.f();
    }

    public void onResume() {
        super.onResume();
        a.a.a.a.c.c.c = a.a.a.a.c.a.e(((a.a.a.a.a.a) this).a);
        this.h.g();
    }
}
