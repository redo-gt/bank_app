package com.b3g.cih.cihpay.activities;

import a.a.a.a.a.a;
import a.a.a.a.a.d;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.b3g.cih.cihpay.R;
import com.mukesh.OtpView;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OtpTokenisationActivity extends a {
    public OtpView g;

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onBtnAnnulerClicked(View view) {
        finish();
    }

    public void onCliquezIciButtonClicked(View view) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_bg));
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (-8193));
        } catch (Exception unused) {
        }
        setContentView(R.layout.activity_otp_tokenisation);
        ((a) this).a = this;
        OtpView findViewById = findViewById(R.id.otp_view);
        this.g = findViewById;
        findViewById.requestFocus();
        this.g.setOtpCompletionListener(new d(this));
    }

    public void onLogoutButtonClicked(View view) {
    }
}
package com.b3g.cih.cihpay.activities;

import a.a.a.a.a.a;
import a.a.a.a.a.d;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.b3g.cih.cihpay.R;
import com.mukesh.OtpView;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class OtpTokenisationActivity extends a {
    public OtpView g;

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onBtnAnnulerClicked(View view) {
        finish();
    }

    public void onCliquezIciButtonClicked(View view) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_bg));
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (-8193));
        } catch (Exception unused) {
        }
        setContentView(R.layout.activity_otp_tokenisation);
        ((a) this).a = this;
        OtpView findViewById = findViewById(R.id.otp_view);
        this.g = findViewById;
        findViewById.requestFocus();
        this.g.setOtpCompletionListener(new d(this));
    }

    public void onLogoutButtonClicked(View view) {
    }
}
