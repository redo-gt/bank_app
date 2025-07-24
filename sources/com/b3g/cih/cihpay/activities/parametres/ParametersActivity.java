package com.b3g.cih.cihpay.activities.parametres;

import a.a.a.a.a.a;
import a.a.a.a.a.j.c;
import a.a.a.a.c.d;
import a.a.a.a.e.a.b;
import a.a.a.a.e.a.e;
import a.a.a.a.e.a.h;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcManager;
import android.nfc.cardemulation.CardEmulation;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.b3g.cih.cihpay.R;
import com.b3g.cih.cihpay.activities.TermsConditionsActivity;
import com.b3g.cih.cihpay.app.AppController;
import com.b3g.cih.cihpay.models.card.Card;
import com.dejamobile.cbp.application.ContactlessEntryPoint;
import com.dejamobile.cbp.application.Failure;
import com.dejamobile.cbp.application.IServiceEntryPoint;
import com.dejamobile.cbp.wallet.common.model.MpaStatus;
import java.util.List;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class ParametersActivity extends a implements b, h, c {
    public d g;
    public a.a.a.a.e.a.d h;
    public ImageView i;
    public ImageView j;
    public ImageView k;
    public ViewGroup l;
    public ViewGroup m;
    public boolean n = true;
    public boolean o = true;

    public static /* synthetic */ void $r8$lambda$QXEbTyxhbf1N175D0KrsBtDGqc0(ParametersActivity parametersActivity, DialogInterface dialogInterface, int i) {
        parametersActivity.a(dialogInterface, i);
    }

    public static void a(ParametersActivity parametersActivity) {
        parametersActivity.l.setVisibility(8);
    }

    public static /* synthetic */ void b(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }

    public void a() {
        moveTaskToBack(true);
        System.exit(0);
        finish();
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
    }

    public void b() {
    }

    public void b(Card card) {
    }

    public void b(Failure failure) {
        Toast.makeText(this, "Échoue", 0).show();
        this.l.setVisibility(8);
        AppController.a(false);
    }

    public void c() {
    }

    public void c(Failure failure) {
    }

    public void d() {
        this.l.setVisibility(8);
        AppController.a(false);
        this.g.a(true);
        Activity activity = ((a) this).a;
        a.a.a.a.c.a.f(activity);
        a.a.a.a.c.a.b(activity);
        if (activity != null) {
            a.a.a.a.c.a.f(activity);
            a.a.a.a.c.a.a.edit().remove("RADICAL").commit();
        }
        a.a.a.a.c.a.c(activity);
        moveTaskToBack(true);
        System.exit(0);
        finish();
    }

    public void d(Failure failure) {
    }

    public void e() {
        m();
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

    public void k() {
        this.h.h();
    }

    public void l() {
    }

    public final void m() {
        boolean isLvpModeActivated;
        d dVar = this.g;
        IServiceEntryPoint iServiceEntryPoint = this.h.c;
        if (iServiceEntryPoint != null) {
            try {
                isLvpModeActivated = iServiceEntryPoint.isLvpModeActivated();
            } catch (RemoteException unused) {
            }
        } else {
            isLvpModeActivated = false;
        }
        dVar.a.edit().putBoolean("key_free_payment", isLvpModeActivated).commit();
        boolean z = this.g.a.getBoolean("key_free_payment", true);
        this.o = z;
        this.k.setImageResource(z ? R.drawable.ic_toggle_on : R.drawable.ic_toggle_off);
    }

    public void onBackButtonClicked(View view) {
        if (this.l.getVisibility() != 0) {
            finish();
        }
    }

    public void onBackPressed() {
        onBackButtonClicked(null);
    }

    public void onCGUClicked(View view) {
        startActivity(new Intent(((a) this).a, TermsConditionsActivity.class).putExtra("FROM", "ParametersActivity"));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_parameters);
        ((a) this).a = this;
        this.g = new d(this);
        this.h = new a.a.a.a.e.a.d(((a) this).a, false, this, this);
        ((a) this).c = this;
        this.l = findViewById(R.id.lay_progress);
        this.i = findViewById(R.id.img_toggle_1);
        this.j = findViewById(R.id.img_toggle_2);
        this.m = findViewById(R.id.free_payment_container);
        this.k = findViewById(R.id.toggle_free_payment);
        this.m.setVisibility(0);
        m();
    }

    public void onLogoutButtonClicked(View view) {
    }

    public void onPause() {
        super.onPause();
        this.h.f();
        AppController.c = false;
    }

    public void onResetWalletButtonClicked(View view) {
        if (!a.a.a.a.c.b.b(((a) this).a)) {
            a.a.a.a.c.b.a(((a) this).a, getString(R.string.no_connection_internet));
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(((a) this).a);
        builder.setTitle(getString(R.string.reinitialiser_cih_pay)).setMessage(R.string.reinitialiser_cih_pay_description).setPositiveButton(R.string.yes, new ParametersActivity$$ExternalSyntheticLambda0(this));
        builder.setNegativeButton(R.string.cancel, new ParametersActivity$$ExternalSyntheticLambda1());
        builder.show();
    }

    public void onResume() {
        boolean z;
        super.onResume();
        ImageView imageView = this.i;
        try {
            z = CardEmulation.getInstance(((NfcManager) getSystemService("nfc")).getDefaultAdapter()).isDefaultServiceForCategory(new ComponentName(this, ContactlessEntryPoint.class), "payment");
        } catch (Exception unused) {
            z = false;
        }
        imageView.setImageResource(z ? R.drawable.ic_toggle_on : R.drawable.ic_toggle_off);
        this.h.g();
    }

    public void onToggle1Clicked(View view) {
        startActivity(new Intent("android.settings.NFC_PAYMENT_SETTINGS"));
    }

    public void onToggle2Clicked(View view) {
        boolean z = !this.n;
        this.n = z;
        this.j.setImageResource(z ? R.drawable.ic_toggle_on : R.drawable.ic_toggle_off);
    }

    public void onToggleFreePaymentClicked(View view) {
        boolean z = !this.o;
        this.o = z;
        this.g.a.edit().putBoolean("key_free_payment", z).commit();
        if (this.o) {
            this.l.setVisibility(0);
            this.h.a(new a.a.a.a.a.j.b(this));
            return;
        }
        this.l.setVisibility(0);
        a.a.a.a.e.a.d dVar = this.h;
        a.a.a.a.a.j.a aVar = new a.a.a.a.a.j.a(this);
        IServiceEntryPoint iServiceEntryPoint = dVar.c;
        if (iServiceEntryPoint != null) {
            try {
                if (iServiceEntryPoint.isLvpModeActivated()) {
                    dVar.c.deactivateLvpMode(new e(dVar, aVar));
                }
            } catch (Exception unused) {
            }
        }
    }

    private void a(DialogInterface dialogInterface, int i) {
        this.l.setVisibility(0);
        AppController.a(true);
        a.a.a.a.e.a.d dVar = this.h;
        dVar.getClass();
        try {
            IServiceEntryPoint iServiceEntryPoint = dVar.c;
            if (iServiceEntryPoint != null && iServiceEntryPoint.getMpaStatus() == MpaStatus.REGISTERED) {
                dVar.c.terminateWallet(new a.a.a.a.e.a.c(dVar));
            }
        } catch (Exception unused) {
        }
        a.a.a.a.c.a.b(((a) this).a);
    }
}
