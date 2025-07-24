package com.b3g.cih.cihpay.models.card;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class CardOperation implements Serializable {

    @SerializedName("autorisationNumber")
    public String autorisationNumber;

    @SerializedName("cardOpActivationStatus")
    public String cardOpActivationStatus;

    @SerializedName("operationType")
    public String operationType;

    @SerializedName("place")
    public String place;

    @SerializedName("transactionAmount")
    public String transactionAmount;

    @SerializedName("transactionCurrency")
    public String transactionCurrency;

    @SerializedName("transactionDate")
    public String transactionDate;

    @SerializedName("transactionHour")
    public String transactionHour;

    public String getAutorisationNumber() {
        return this.autorisationNumber;
    }

    public String getCardOpActivationStatus() {
        return this.cardOpActivationStatus;
    }

    public String getOperationType() {
        return this.operationType;
    }

    public String getPlace() {
        return this.place;
    }

    public String getTransactionAmount() {
        return this.transactionAmount;
    }

    public String getTransactionCurrency() {
        return this.transactionCurrency;
    }

    public String getTransactionDate() {
        return this.transactionDate;
    }

    public String getTransactionHour() {
        return this.transactionHour;
    }

    public void setAutorisationNumber(String str) {
        this.autorisationNumber = str;
    }

    public void setCardOpActivationStatus(String str) {
        this.cardOpActivationStatus = str;
    }

    public void setOperationType(String str) {
        this.operationType = str;
    }

    public void setPlace(String str) {
        this.place = str;
    }

    public void setTransactionAmount(String str) {
        this.transactionAmount = str;
    }

    public void setTransactionCurrency(String str) {
        this.transactionCurrency = str;
    }

    public void setTransactionDate(String str) {
        this.transactionDate = str;
    }

    public void setTransactionHour(String str) {
        this.transactionHour = str;
    }
}
