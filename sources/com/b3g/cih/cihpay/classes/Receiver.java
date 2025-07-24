package com.b3g.cih.cihpay.classes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.b3g.cih.cihpay.R;
import com.b3g.cih.cihpay.activities.paiement.PaiementActivity;
import com.dejamobile.cbp.generic.broadcast.Event;
import com.dejamobile.cbp.generic.broadcast.EventCause;
import com.dejamobile.cbp.generic.broadcast.EventParameter;
import com.dejamobile.cbp.wallet.common.model.CardStatus;
import com.dejamobile.cbp.wallet.common.model.CvmType;
import com.dejamobile.cbp.wallet.common.model.MpaStatus;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Receiver extends BroadcastReceiver {

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[EventCause.values().length];
            a = iArr;
            try {
                iArr[EventCause.NO_CREDENTIAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[EventCause.TRANSACTION_DATA_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[EventCause.NO_ACTIVATED_CARD_FOUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[EventCause.RF_LINK_LOSS_DURING_PAYMENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[EventCause.INTERNAL_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Event event = (Event) intent.getParcelableExtra(EventParameter.TYPE.name());
        Intent intent2 = new Intent("RECEIVER_ACTIONS");
        intent2.putExtra("ACTION_TYPE", event);
        try {
            if (event == Event.MOBILE_COMPROMISED) {
                try {
                    String stringExtra = intent.getStringExtra(EventParameter.CAUSE.name());
                    Intent intent3 = new Intent("MOBILE_COMPROMISED_ACTIONS");
                    intent3.putExtra("CAUSE", stringExtra);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent3);
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (event != Event.FUNCTIONAL_STATUS_UPDATED) {
                if (event == Event.MPA_STATE_CHANGE) {
                    MpaStatus mpaStatus = (MpaStatus) intent.getParcelableExtra(EventParameter.CAUSE.name());
                    String str = "MPA_STATE_CHANGE  status = " + mpaStatus;
                    intent2.putExtra("MPA_STATE_CHANGE", mpaStatus);
                } else if (event != Event.CVM_STATE_CHANGED) {
                    if (event == Event.CVM_SETTING_REQUIRED) {
                        defaultSharedPreferences.edit().putString("key_wallet_id", intent.getStringExtra(EventParameter.DC_ID.name())).commit();
                        if (intent.getParcelableArrayListExtra(EventParameter.CVM_TO_PROVIDE.name()).contains(CvmType.MOBILE_PIN)) {
                            intent2.putExtra("MOBILE_PIN", true);
                        }
                    } else if (event == Event.CARD_STATE_CHANGED) {
                        String stringExtra2 = intent.getStringExtra(EventParameter.DC_ID.name());
                        CardStatus cardStatus = (CardStatus) intent.getParcelableExtra(EventParameter.CAUSE.name());
                        String str2 = "cardStatus : " + cardStatus;
                        intent2.putExtra("CARDSTATUS", cardStatus + "");
                        intent2.putExtra("INITIALIZED", cardStatus.equals(CardStatus.INITIALIZED));
                        intent2.putExtra("dcId", stringExtra2);
                        if (cardStatus.equals(CardStatus.INITIALIZED)) {
                            intent2.putExtra("INITIALIZED", true);
                        }
                    } else if (event == Event.AUTHENTICATION_REQUIRED) {
                        EventCause eventCause = (EventCause) intent.getParcelableExtra(EventParameter.CAUSE.name());
                        if (eventCause != null && eventCause.equals(EventCause.PAYMENT_CONTACTLESS)) {
                            String stringExtra3 = intent.getStringExtra(EventParameter.AMOUNT.name());
                            String str3 = "PAYMENT_CONTACTLESS amount " + stringExtra3;
                            String stringExtra4 = intent.getStringExtra(EventParameter.CURRENCY_CODE.name());
                            String str4 = "PAYMENT_CONTACTLESS currency " + stringExtra4;
                            String stringExtra5 = intent.getStringExtra(EventParameter.DC_ID.name());
                            Intent intent4 = new Intent(context, PaiementActivity.class);
                            intent4.setFlags(335577088);
                            intent4.putExtra("AMOUNT", stringExtra3).putExtra("CURRENCY", stringExtra4).putExtra("DC_ID", stringExtra5);
                            context.startActivity(intent4);
                        }
                    } else if (event == Event.PAY_OK) {
                        String stringExtra6 = intent.getStringExtra(EventParameter.AMOUNT.name());
                        String stringExtra7 = intent.getStringExtra(EventParameter.CURRENCY_CODE.name());
                        String str5 = "PAY_OK amount " + stringExtra6;
                        String str6 = "PAY_OK currencyCode " + stringExtra7;
                        intent2.putExtra("AMOUNT", stringExtra6);
                        intent2.putExtra("CURRENCY", stringExtra7);
                        try {
                            intent2.putExtra("TOKEN_NUMBER", intent.getStringExtra(EventParameter.TOKEN_NUMBER.name()));
                            intent2.putExtra("MERCHANT", intent.getStringExtra(EventParameter.MERCHANT.name()));
                            intent2.putExtra("CAUSE", intent.getStringExtra(EventParameter.CAUSE.name()));
                            intent2.putExtra("DATE", intent.getStringExtra(EventParameter.DATE.name()));
                            intent2.putExtra("ATC", intent.getStringExtra(EventParameter.ATC.name()));
                            intent2.putExtra("DC_ID", intent.getStringExtra(EventParameter.DC_ID.name()));
                        } catch (Exception unused) {
                        }
                    } else if (event == Event.PAY_KO) {
                        EventCause eventCause2 = (EventCause) intent.getParcelableExtra(EventParameter.CAUSE.name());
                        int i = a.a[eventCause2.ordinal()];
                        Toast.makeText(context, i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? null : context.getString(R.string.system_error) : "Il y a un problème de communication NFC, veuillez réessayer!" : "Il n'y a pas de carte active prête à être utilisée. " : "CIH Pay ne reconnaît pas la commande du terminal." : "Veuillez réessayer", 1).show();
                        intent2.putExtra("CAUSE_KO", eventCause2.describeContents());
                    } else if (event == Event.INFORMATION) {
                        String stringExtra8 = intent.getStringExtra(EventParameter.CAUSE.name());
                        intent.getStringExtra(EventParameter.PIN_TRY_COUNTER.name());
                        intent.getStringExtra(EventParameter.FINANCIAL_STATUS.name());
                        intent2.putExtra("INFORMATION_CAUSE", stringExtra8);
                    }
                }
            }
        } catch (Exception e2) {
            e2.getMessage();
            Toast.makeText(context.getApplicationContext(), "Ex : " + e2.getMessage(), 1).show();
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);
    }
}
