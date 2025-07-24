package com.b3g.cih.cihpay.activities.cards;

import a.a.a.a.a.a;
import a.a.a.a.b.b;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.b3g.cih.cihpay.R;
import com.b3g.cih.cihpay.activities.enrollement.EnrollmentActivity;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CartesActivity extends a {
    public RecyclerView g;
    public b h;
    public ArrayList i = new ArrayList();

    public void onBackButtonClicked(View view) {
        finish();
    }

    public void onBtnAddCardClicked(View view) {
        startActivity(new Intent(((a) this).a, EnrollmentActivity.class));
        finish();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_cartes);
        ((a) this).a = this;
        RecyclerView findViewById = findViewById(R.id.recyclerView);
        this.g = findViewById;
        findViewById.setLayoutManager(new LinearLayoutManager(((a) this).a));
        b bVar = new b(((a) this).a, this.i);
        this.h = bVar;
        this.g.setAdapter(bVar);
    }

    public void onLogoutButtonClicked(View view) {
    }

    public void onResume() {
        super.onResume();
        a.a.a.a.c.a.c(((a) this).a);
        this.i.clear();
        if (a.a.a.a.c.a.d(((a) this).a) == null) {
            onBackPressed();
        } else {
            this.i.addAll(a.a.a.a.c.a.d(((a) this).a));
            this.h.notifyDataSetChanged();
        }
    }
}
