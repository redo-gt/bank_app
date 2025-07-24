package com.b3g.cih.cihpay.models.profile;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Profile implements Serializable {

    @SerializedName("accountStatus")
    private String accountStatus;

    @SerializedName("adress")
    private String adress;

    @SerializedName("cardProgramId")
    private String cardProgramId;

    @SerializedName("city")
    private String city;

    @SerializedName("clientSex")
    private String clientSex;

    @SerializedName("country")
    private String country;

    @SerializedName("email")
    private String email;

    @SerializedName("feeAmountBinatnaIM")
    private String feeAmountBinatnaIM;

    @SerializedName("feeAmountCardOpperation")
    private String feeAmountCardOpperation;

    @SerializedName("feeAmountDebitCard")
    private String feeAmountDebitCard;

    @SerializedName("feeAmountMessage")
    private String feeAmountMessage;

    @SerializedName("feeAmountTransfertConfIM")
    private String feeAmountTransfertConfIM;

    @SerializedName("feeAmountTransfertIM")
    private String feeAmountTransfertIM;

    @SerializedName("gsm")
    private String gsm;

    @SerializedName("langue")
    private String langue;

    @SerializedName("nomPrenom")
    private String nomPrenom;

    @SerializedName("password")
    private String password;

    @SerializedName("phoneOperator")
    private String phoneOperator;

    @SerializedName("postCode")
    private String postCode;

    @SerializedName("radical")
    private String radical;

    @SerializedName("PathPicProfil")
    private String PathPicProfil = " ";

    @SerializedName("deviceId")
    private String deviceId = "";

    public Profile() {
    }

    public Profile(String str, String str2, String str3, String str4) {
        this.nomPrenom = str;
        this.gsm = str2;
        this.email = str3;
        this.radical = str4;
    }

    public String getAccountStatus() {
        return this.accountStatus;
    }

    public String getAdress() {
        return this.adress;
    }

    public String getCardProgramId() {
        return this.cardProgramId;
    }

    public String getCity() {
        return this.city;
    }

    public String getClientSex() {
        return this.clientSex;
    }

    public String getCountry() {
        return this.country;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFeeAmountBinatnaIM() {
        return this.feeAmountBinatnaIM;
    }

    public String getFeeAmountCardOpperation() {
        return this.feeAmountCardOpperation;
    }

    public String getFeeAmountDebitCard() {
        return this.feeAmountDebitCard;
    }

    public String getFeeAmountMessage() {
        return this.feeAmountMessage;
    }

    public String getFeeAmountTransfertConfIM() {
        return this.feeAmountTransfertConfIM;
    }

    public String getFeeAmountTransfertIM() {
        return this.feeAmountTransfertIM;
    }

    public String getGsm() {
        return this.gsm;
    }

    public String getLangue() {
        return this.langue;
    }

    public String getNomPrenom() {
        return this.nomPrenom;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPathPicProfil() {
        return this.PathPicProfil;
    }

    public String getPhoneOperator() {
        return this.phoneOperator;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public String getRadical() {
        return this.radical;
    }

    public void setAccountStatus(String str) {
        this.accountStatus = str;
    }

    public void setAdress(String str) {
        this.adress = str;
    }

    public void setCardProgramId(String str) {
        this.cardProgramId = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setClientSex(String str) {
        this.clientSex = str;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setFeeAmountBinatnaIM(String str) {
        this.feeAmountBinatnaIM = str;
    }

    public void setFeeAmountCardOpperation(String str) {
        this.feeAmountCardOpperation = str;
    }

    public void setFeeAmountDebitCard(String str) {
        this.feeAmountDebitCard = str;
    }

    public void setFeeAmountMessage(String str) {
        this.feeAmountMessage = str;
    }

    public void setFeeAmountTransfertConfIM(String str) {
        this.feeAmountTransfertConfIM = str;
    }

    public void setFeeAmountTransfertIM(String str) {
        this.feeAmountTransfertIM = str;
    }

    public void setGsm(String str) {
        this.gsm = str;
    }

    public void setLangue(String str) {
        this.langue = str;
    }

    public void setNomPrenom(String str) {
        this.nomPrenom = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setPathPicProfil(String str) {
        this.PathPicProfil = str;
    }

    public void setPhoneOperator(String str) {
        this.phoneOperator = str;
    }

    public void setPostCode(String str) {
        this.postCode = str;
    }

    public void setRadical(String str) {
        this.radical = str;
    }
}
