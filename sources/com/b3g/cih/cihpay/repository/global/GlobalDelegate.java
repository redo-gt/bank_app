package com.b3g.cih.cihpay.repository.global;

import com.b3g.cih.cihpay.models.card.Card;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public interface GlobalDelegate {

    public enum GlobalAction {
        SEND_OTP,
        CHECK_OTP,
        GET_CARDS,
        TOKENIZATION
    }

    void a(Card card);

    void a(GlobalAction globalAction);

    void a(GlobalAction globalAction, String str);

    void a(ArrayList arrayList);
}
