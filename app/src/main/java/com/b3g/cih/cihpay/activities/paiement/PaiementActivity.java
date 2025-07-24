package com.b3g.cih.cihpay.activities.paiement;

import a.a.a.a.e.a.g;
import a.a.a.a.e.a.h;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.b3g.cih.cihpay.R;
import com.b3g.cih.cihpay.models.card.Card;
import com.dejamobile.cbp.application.Failure;
import com.dejamobile.cbp.generic.broadcast.EventParameter;
import com.dejamobile.cbp.wallet.common.model.MpaStatus;
import com.mukesh.OtpView;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class PaiementActivity extends a.a.a.a.a.a implements a.a.a.a.e.a.b, h, a.a.a.a.a.j.c {
    public static final /* synthetic */ int g = 0;
    public TextView A;
    public TextView B;
    public TextView C;
    public TextView D;
    public TextView E;
    public int F;
    public CountDownTimer G;
    public byte[] H;
    public a.a.a.a.e.a.d h;
    public ImageView k;
    public ViewGroup l;
    public ViewGroup m;
    public ViewGroup n;
    public ViewGroup o;
    public OtpView p;
    public TextView q;
    public TextView r;
    public ViewGroup s;
    public ViewGroup t;
    public TextView u;
    public ImageView v;
    public Button w;
    public Button x;
    public TextView y;
    public TextView z;
    public ACTION_TYPE i = ACTION_TYPE.NONE;
    public ArrayList j = new ArrayList();
    public Card I = null;

    public enum ACTION_TYPE {
        NONE,
        TAP_PIN_TAP,
        PIN_TAP
    }

    public class a extends CountDownTimer {
        public a(long j, long j2) {
            super(j, j2);
        }

        public void onFinish() {
            PaiementActivity paiementActivity = PaiementActivity.this;
            int i = PaiementActivity.g;
            paiementActivity.p();
        }

        public void onTick(long j) {
            int i = (int) (j / 1000);
            PaiementActivity.this.q.setText("00 : " + (i > 9 ? new StringBuilder("") : new StringBuilder("0")).append(i).toString());
        }
    }

    public class b implements DialogInterface.OnClickListener {
        public b(PaiementActivity paiementActivity) {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    public class c implements DialogInterface.OnClickListener {
        public c() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            PaiementActivity.this.startActivity(new Intent("android.settings.NFC_SETTINGS"));
        }
    }

    public static /* synthetic */ class d {
        public static final /* synthetic */ int[] a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[Failure.Type.values().length];
            b = iArr;
            try {
                iArr[Failure.Type.TIMEOUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[Failure.Type.BAD_MOBILE_PIN_FORMAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[Failure.Type.WRONG_PIN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[Failure.Type.NFC_FEATURE_OFF.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[Failure.Type.TAP_AND_PAY_NEEDED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[Failure.Type.UNAUTHORIZED_OPERATION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[Failure.Type.NO_CARD_AVAILABLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[Failure.Type.CONDITION_NOT_MET_TO_PAY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[Failure.Type.HARDWARE_UNAVAILBLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            ACTION_TYPE.values();
            int[] iArr2 = new int[3];
            a = iArr2;
            try {
                iArr2[ACTION_TYPE.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[ACTION_TYPE.TAP_PIN_TAP.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[ACTION_TYPE.PIN_TAP.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public static /* synthetic */ void $r8$lambda$7FEJLRcHe-1PHUWf1htyIp-GZqg(PaiementActivity paiementActivity, String str) {
        paiementActivity.c(str);
    }

    private /* synthetic */ void c(String str) {
        try {
            this.H = this.h.c.generateKeyboardMapping();
            b(str);
        } catch (Exception unused) {
        }
    }

    public void a() {
    }

    public void a(Intent intent) {
        Date date;
        CountDownTimer countDownTimer = this.G;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.u.setText("Connexion\nréussie");
        this.v.setImageResource(R.drawable.ic_success);
        this.w.setVisibility(0);
        this.x.setVisibility(8);
        String stringExtra = intent.getStringExtra("AMOUNT");
        String stringExtra2 = intent.getStringExtra("CURRENCY");
        if (stringExtra == null || stringExtra2 == null) {
            this.D.setText(stringExtra);
        } else {
            this.D.setText(a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, "PaymentActivity", stringExtra, stringExtra2));
        }
        try {
            String stringExtra3 = intent.getStringExtra(EventParameter.DATE.name());
            new Date();
            String str = null;
            try {
                date = new SimpleDateFormat("yyMMdd").parse(stringExtra3);
            } catch (Exception e) {
                e = e;
                date = null;
            }
            try {
                System.out.println(date);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                this.I.getDcId();
                intent.getStringExtra(EventParameter.DC_ID.name());
                TextView textView = this.y;
                StringBuilder sb = new StringBuilder("");
                str = a.a.a.a.c.b.a(new SimpleDateFormat("dd/MM/yyyy").format(date));
                textView.setText(sb.append(str).toString());
                this.z.setText(intent.getStringExtra(EventParameter.MERCHANT.name()));
                this.A.setText("************" + this.I.getPlainNumeroCarte());
                this.B.setText(this.I.getLibelleProduit());
                this.C.setText(intent.getStringExtra(EventParameter.TOKEN_NUMBER.name()));
                this.F = 3;
                n();
                MediaPlayer create = MediaPlayer.create(((a.a.a.a.a.a) this).a, R.raw.success);
                create.setVolume(1.0f, 1.0f);
                create.start();
            }
            this.I.getDcId();
            intent.getStringExtra(EventParameter.DC_ID.name());
            TextView textView2 = this.y;
            StringBuilder sb2 = new StringBuilder("");
            try {
                str = a.a.a.a.c.b.a(new SimpleDateFormat("dd/MM/yyyy").format(date));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            textView2.setText(sb2.append(str).toString());
            this.z.setText(intent.getStringExtra(EventParameter.MERCHANT.name()));
            this.A.setText("************" + this.I.getPlainNumeroCarte());
            this.B.setText(this.I.getLibelleProduit());
            this.C.setText(intent.getStringExtra(EventParameter.TOKEN_NUMBER.name()));
        } catch (Exception unused) {
        }
        this.F = 3;
        n();
        try {
            MediaPlayer create2 = MediaPlayer.create(((a.a.a.a.a.a) this).a, R.raw.success);
            create2.setVolume(1.0f, 1.0f);
            create2.start();
        } catch (Exception unused2) {
        }
    }

    public void a(Failure failure) {
        finish();
        this.s.setVisibility(8);
    }

    public void a(Failure failure, Card card) {
        this.s.setVisibility(8);
    }

    public void a(String str) {
    }

    public void a(String str, Intent intent) {
        CountDownTimer countDownTimer = this.G;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        p();
    }

    public void a(List list) {
    }

    public void a(boolean z) {
        if (z) {
            this.h.a(this.I);
            o();
        } else {
            this.s.setVisibility(8);
            finish();
        }
    }

    public void b() {
        finish();
        this.s.setVisibility(8);
    }

    public void b(Card card) {
    }

    public void b(Failure failure) {
    }

    public final void b(String str) {
        try {
            if (this.h.c != null) {
                this.s.setVisibility(0);
                this.h.a(this.I, this.H, str);
            }
        } catch (Exception e) {
            String str2 = "ERROR = " + e.getMessage();
            this.s.setVisibility(8);
        }
    }

    public void c() {
    }

    public void c(Failure failure) {
    }

    public void d() {
    }

    public void d(Failure failure) {
        switch (d.b[failure.type.ordinal()]) {
            case 1:
                this.G.cancel();
                p();
                break;
            case 2:
            case 3:
                a.a.a.a.c.b.a(((a.a.a.a.a.a) this).a, getString(R.string.alert_error_pin_incorrect));
                this.p.setText("");
                break;
            case 4:
                AlertDialog.Builder builder = new AlertDialog.Builder(((a.a.a.a.a.a) this).a);
                builder.setTitle(R.string.nfc_not_activated);
                builder.setMessage(R.string.go_to_activate_nfc);
                builder.setPositiveButton(R.string.yes, new a.a.a.a.a.i.b(this)).setNegativeButton(R.string.annuler, new a.a.a.a.a.i.a(this)).setCancelable(false).show();
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                finish();
                break;
        }
        this.s.setVisibility(8);
    }

    public void e() {
        try {
            if (this.h.c.getMpaStatus() == MpaStatus.STOPPED) {
                this.h.i();
            } else {
                this.h.a(this.I);
                o();
            }
        } catch (Exception unused) {
        }
    }

    public void e(Failure failure) {
    }

    public void f() {
        this.s.setVisibility(8);
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
        this.F = 2;
        n();
        this.s.setVisibility(8);
    }

    public void k() {
        this.h.h();
    }

    public void l() {
    }

    public final void m() {
        this.E = findViewById(R.id.txt_card_number);
        this.s = findViewById(R.id.lay_progress);
        this.t = findViewById(R.id.lay_amount);
        this.m = findViewById(R.id.lay_step_1);
        this.n = findViewById(R.id.lay_step_2);
        this.o = findViewById(R.id.lay_step_3);
        this.k = findViewById(R.id.img_card);
        this.r = findViewById(R.id.txt_amount_to_pay);
        this.q = findViewById(R.id.txt_count_down);
        this.p = findViewById(R.id.pin_view);
        this.u = findViewById(R.id.txt_result_paiement);
        this.v = findViewById(R.id.img_result);
        this.w = findViewById(R.id.btn_show_recu);
        this.x = findViewById(R.id.btn_retry);
        this.y = findViewById(R.id.txt_operation_date);
        this.z = findViewById(R.id.txt_operation_merchant);
        this.A = findViewById(R.id.txt_operation_card_number);
        this.B = findViewById(R.id.txt_operation_card_name);
        this.C = findViewById(R.id.txt_operation_number);
        this.D = findViewById(R.id.txt_operation_amount);
        ViewGroup findViewById = findViewById(R.id.lay_recu);
        this.l = findViewById;
        findViewById.setVisibility(8);
        this.p.setFocusable(false);
        this.p.setOtpCompletionListener(new PaiementActivity$$ExternalSyntheticLambda0(this));
    }

    public final void n() {
        this.m.setVisibility(this.F == 1 ? 0 : 8);
        this.n.setVisibility(this.F == 2 ? 0 : 8);
        this.o.setVisibility(this.F == 3 ? 0 : 8);
        int i = this.F;
        if (i == 0) {
            this.s.setVisibility(0);
            return;
        }
        if (i == 1) {
            this.s.setVisibility(8);
        } else {
            if (i != 2) {
                return;
            }
            a aVar = new a(30000L, 1000L);
            this.G = aVar;
            aVar.start();
        }
    }

    public final void o() {
        ViewGroup viewGroup;
        int ordinal = this.i.ordinal();
        int i = 0;
        if (ordinal != 0) {
            if (ordinal == 1) {
                this.F = 1;
                viewGroup = this.t;
            } else if (ordinal == 2) {
                this.F = 1;
                viewGroup = this.t;
                i = 8;
            }
            viewGroup.setVisibility(i);
        } else {
            this.s.setVisibility(0);
            this.F = 0;
        }
        n();
    }

    public void onAnnulerPaiementClicked(View view) {
        CountDownTimer countDownTimer = this.G;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.s.setVisibility(0);
        a.a.a.a.e.a.d dVar = this.h;
        dVar.getClass();
        try {
            dVar.c.cancelContactlessPayment(new g(dVar));
        } catch (Exception unused) {
        }
    }

    public void onBackButtonClicked(View view) {
        int ordinal = this.i.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                moveTaskToBack(true);
                finish();
                System.exit(0);
                return;
            } else if (ordinal != 2) {
                return;
            }
        }
        finish();
    }

    public void onClearAllClicked(View view) {
        this.p.setText("");
    }

    public void onClearOneClicked(View view) {
        if (TextUtils.isEmpty(this.p.getText())) {
            return;
        }
        this.p.setText(this.p.getText().toString().substring(0, r3.length() - 1));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onCreate(android.os.Bundle r5) {
        /*
            r4 = this;
            super.onCreate(r5)
            android.view.Window r5 = r4.getWindow()
            r0 = 4718592(0x480000, float:6.612156E-39)
            r5.addFlags(r0)
            r0 = 2097280(0x200080, float:2.938915E-39)
            r5.addFlags(r0)
            int r5 = com.b3g.cih.cihpay.R.layout.activity_paiement
            r4.setContentView(r5)
            r4.a = r4
            a.a.a.a.e.a.d r5 = new a.a.a.a.e.a.d
            r0 = 0
            r5.<init>(r4, r0, r4, r4)
            r4.h = r5
            r4.c = r4
            r4.F = r0
            r4.m()
            java.util.ArrayList r5 = r4.j
            a.a.a.a.c.b.a(r4, r5)
            android.content.Intent r5 = r4.getIntent()
            android.os.Bundle r5 = r5.getExtras()
            if (r5 == 0) goto L6d
            android.content