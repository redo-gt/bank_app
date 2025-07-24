package com.b3g.cih.cihpay.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.b3g.cih.cihpay.R;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CustomViewPager extends ViewPager implements ViewPager.PageTransformer {
    public float a;
    public int b;
    public boolean c;
    public boolean d;
    public float e;

    public CustomViewPager(Context context) {
        this(context, null);
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0.2f;
        this.c = true;
        this.d = false;
        this.e = 0.5f;
        setClipChildren(false);
        setClipToPadding(false);
        setOverScrollMode(2);
        setPageTransformer(false, this);
        setOffscreenPageLimit(3);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen._40sdp);
        this.b = dimensionPixelOffset;
        setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
    }

    public void setAnimationEnabled(boolean z) {
        this.c = z;
    }

    public void setFadeEnabled(boolean z) {
        this.d = z;
    }

    public void setFadeFactor(float f) {
        this.e = f;
    }

    public void setPageMargin(int i) {
        this.b = i;
        setPadding(i, i, i, i);
    }

    public void transformPage(View view, float f) {
        float f2;
        int i = this.b;
        if (i <= 0 || !this.c) {
            return;
        }
        int i2 = i / 3;
        view.setPadding(i2, i2, i2, i2);
        if (this.a == 0.0f && f > 0.0f && f < 1.0f) {
            this.a = f;
        }
        float f3 = f - this.a;
        float abs = Math.abs(f3);
        if (f3 <= -1.0f || f3 >= 1.0f) {
            if (!this.d) {
                return;
            } else {
                f2 = this.e;
            }
        } else if (f3 == 0.0f) {
            view.setScaleX(this.a + 1.0f);
            view.setScaleY(this.a + 1.0f);
            view.setAlpha(1.0f);
            return;
        } else {
            float f4 = 1.0f - abs;
            view.setScaleX((this.a * f4) + 1.0f);
            view.setScaleY((this.a * f4) + 1.0f);
            if (!this.d) {
                return;
            } else {
                f2 = Math.max(this.e, f4);
            }
        }
        view.setAlpha(f2);
    }
}
