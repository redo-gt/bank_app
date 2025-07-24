package com.b3g.cih.cihpay.activities.detail_card;

import a.a.a.a.e.a.d;
import a.a.a.a.e.a.h;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.b3g.cih.cihpay.R;
import com.b3g.cih.cihpay.app.AppController;
import com.b3g.cih.cihpay.models.card.Card;
import com.dejamobile.cbp.application.Callback;
import com.dejamobile.cbp.application.Failure;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class DetailCardActivity extends a.a.a.a.a.a implements a.a.a.a.e.a.b, h {
    public d g;
    public ViewGroup h;
    public ViewGroup i;
    public ImageView j;
    public RecyclerView k;
    public a.a.a.a.b.c n;
    public TextView o;
    public TextView p;
    public ImageView q;
    public ImageView r;
    public Card t;
    public ArrayList l = new ArrayList();
    public ArrayList m = new ArrayList();
    public boolean s = true;

    public class a implements Callback {
        public a() {
        }

        public IBinder asBinder() {
            return null;
        }

        public void onFailure(Failure failure) {
            failure.getMessage();
            Toast.makeText(DetailCardActivity.this, "Échoue", 0).show();
            DetailCardActivity.this.i.setVisibility(8);
            AppController.a(false);
        }

        public void onSuccess() {
            DetailCardActivity.this.i.setVisibility(8);
            AppController.a(false);
            DetailCardActivity.this.g.c();
            DetailCardActivity.this.g.b();
            DetailCardActivity.this.m.clear();
            if (a.a.a.a.c.a.d(((a.a.a.a.a.a) DetailCardActivity.this).a) != null) {
                Iterator it = a.a.a.a.c.a.d(((a.a.a.a.a.a) DetailCardActivity.this).a).iterator();
                while (it.hasNext()) {
                    Card card = (Card) it.next();
                    if (!card.getDcId().equals(DetailCardActivity.this.t.getDcId())) {
                        if (DetailCardActivity.this.t.isDefaultCard()) {
                            card.setDefaultCard(true);
                            DetailCardActivity.this.t.setDefaultCard(false);
                            DetailCardActivity detailCardActivity = DetailCardActivity.this;
                            detailCardActivity.g.a(detailCardActivity.t);
                        }
                        DetailCardActivity.this.m.add(card);
                    }
                }
                a.a.a.a.c.a.b(((a.a.a.a.a.a) DetailCardActivity.this).a);
                DetailCardActivity detailCardActivity2 = DetailCardActivity.this;
                a.a.a.a.c.a.a(((a.a.a.a.a.a) detailCardActivity2).a, detailCardActivity2.m);
                a.a.a.a.c.a.d(((a.a.a.a.a.a) DetailCardActivity.this).a);
            }
            DetailCardActivity.this.onBackPressed();
        }
    }

    public class b implements DialogInterface.OnClickListener {
        public b(DetailCardActivity detailCardActivity) {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    public class c implements DialogInterface.OnClickListener {
        public c() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            DetailCardActivity.this.i.setVisibility(0);
            DetailCardActivity detailCardActivity = DetailCardActivity.this;
            detailCardActivity.g.a(detailCardActivity.t);
        }
    }

    public static /* synthetic */ void $r8$lambda$vll0DRC15Fm6Dgb2rhRaePiuoSM(DetailCardActivity detailCardActivity, DialogInterface dialogInterface, int i) {
        detailCardActivity.a(dialogInterface, i);
    }

    private /* synthetic */ void a(DialogInterface dialogInterface, int i) {
        if (this.t != null) {
            try {
                this.i.setVisibility(0);
                AppController.a(true);
                this.g.c.deleteToken(this.t.getDcId(), new a());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static /* synthetic */ void b(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }

    public void a() {
    }

    public void a(Intent intent) {
    }

    public void a(Failure failure) {
    }

    public void a(Failure failure, Card card) {
        this.i.setVisibility(8);
    }

    public void a(String str) {
    }

    public void a(String str, Intent intent) {
    }

    public void a(List list) {
    }

    public void a(boolean z) {
    }

    public void b() {
    }

    public void b(Card card) {
        ArrayList d = a.a.a.a.c.a.d(((a.a.a.a.a.a) this).a);
        Iterator it = d.iterator();
        while (it.hasNext()) {
            Card card2 = (Card) it.next();
            card2.setDefaultCard(false);
            if (card2.getNumeroCarte().equals(card.getNumeroCarte())) {
                card2.setDefaultCard(true);
                card = card2;
            }
        }
        a.a.a.a.c.a.a(((a.a.a.a.a.a) this).a, d);
        this.q.setImageResource(card.isDefaultCard() ? R.drawable.ic_toggle_on : R.drawable.ic_toggle_off);
        this.i.setVisibility(8);
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

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail_card);
        ((a.a.a.a.a.a) this).a = this;
        this.g = new d(this, false, this, this);
        this.i = findViewById(R.id.lay_progress);
        this.p = findViewById(R.id.txt_card_name);
        this.o = findViewById(R.id.txt_no_data);
        this.j = findViewById(R.id.img_card);
        this.h = findViewById(R.id.lay_delete);
        this.q = findViewById(R.id.img_toggle_1);
        this.r = findViewById(R.id.img_toggle_2);
        RecyclerView findViewById = findViewById(R.id.recyclerView);
        this.k = findViewById;
        findViewById.setLayoutManager(new LinearLayoutManager(((a.a.a.a.a.a) this).a));
        a.a.a.a.b.c cVar = new a.a.a.a.b.c(((a.a.a.a.a.a) this).a, this.l);
        this.n = cVar;
        this.k.setAdapter(cVar);
        this.h.setVisibility(8);
        this.o.setVisibility(8);
        if (getIntent().getExtras() != null) {
            Card card = (Card) getIntent().getSerializableExtra("CARD");
            this.t = card;
            if (card != null) {
                if (card == null) {
                    return;
                }
                new Gson().toJson(this.t);
                Picasso.with(((a.a.a.a.a.a) this).a).load(a.a.a.a.c.c.a(this.t.getStatus().equals("SUSPENDED") ? "/Portals/_default/Skins/CIH/images/newSkinImgs/cardsNonDisponible.png" : this.t.getUrlImage())).placeholder(R.drawable.carte_holder).into(this.j);
                this.q.setImageResource((!this.t.isDefaultCard() || this.t.getStatus().equals("SUSPENDED")) ? R.drawable.ic_toggle_off : R.drawable.ic_toggle_on);
                this.p.setText(this.t.getLibelleProduit());
                this.l.clear();
                this.l.addAll(this.t.getOperations());
                this.n.notifyDataSetChanged();
                this.o.setVisibility(this.l.isEmpty() ? 0 : 8);
                return;
            }
        }
        finish();
    }

    public void onDeleteCardButtonClicked(View view) {
        this.h.setVisibility(8);
    }

    public void onDeleteTokenButtonClicked(View view) {
        if (!a.a.a.a.c.b.b(((a.a.a.a.a.a) this).a)) {
            a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, getString(R.string.no_connection_internet));
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(((a.a.a.a.a.a) this).a);
        builder.setTitle(getString(R.string.reset_this_card_cih_pay)).setMessage(R.string.reset_this_card_cih_pay_description).setPositiveButton(R.string.yes, new DetailCardActivity$$ExternalSyntheticLambda0(this)).setNegativeButton(R.string.cancel, new DetailCardActivity$$ExternalSyntheticLambda1());
        builder.show();
    }

    public void onHideLayDeleteButtonClicked(View view) {
        this.h.setVisibility(8);
    }

    public void onLogoutButtonClicked(View view) {
    }

    public void onOptionsButtonClicked(View view) {
        this.h.setVisibility(0);
    }

    public void onPause() {
        super.onPause();
        this.g.f();
    }

    public void onResume() {
        super.onResume();
        this.g.g();
    }

    public void onToggle1Clicked(View view) {
        ArrayList d = a.a.a.a.c.a.d(((a.a.a.a.a.a) this).a);
        if (this.t.isDefaultCard() || d == null) {
            return;
        }
        new AlertDialog.Builder(((a.a.a.a.a.a) this).a).setCancelable(false).setTitle(R.string.alert_title_change_default_card).setMessage(R.string.alert_message_change_default_card).setPositiveButton(R.string.yes, new c()).setNegativeButton(R.string.cancel, new b(this)).show();
    }

    public void onToggle2Clicked(View view) {
        Card card;
        if (a.a.a.a.c.a.d(((a.a.a.a.a.a) this).a) != null) {
            boolean z = true;
            boolean z2 = !this.s;
            this.s = z2;
            this.r.setImageResource(z2 ? R.drawable.ic_toggle_on : R.drawable.ic_toggle_off);
            if (this.s) {
                card = this.t;
                z = false;
            } else {
                card = this.t;
            }
            card.setActive(z);
            this.g.h();
        }
    }
}
