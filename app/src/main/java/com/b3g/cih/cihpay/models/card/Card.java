package com.b3g.cih.cihpay.models.card;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class Card implements Serializable {

    @SerializedName("BalanceCard")
    private String BalanceCard;

    @SerializedName("CodeProduit")
    private String CodeProduit;

    @SerializedName("DateExpiration")
    private String DateExpiration;

    @SerializedName("LibelleProduit")
    private String LibelleProduit;

    @SerializedName("NumeroCarte")
    private String NumeroCarte;

    @SerializedName("Operations")
    private ArrayList Operations;

    @SerializedName("PlainNumeroCarte")
    private String PlainNumeroCarte;

    @SerializedName("Psn")
    private String Psn;

    @SerializedName("UrlImage")
    private String UrlImage;

    @SerializedName("dcId")
    private String dcId;

    @SerializedName("radicalClient")
    private String radicalClient;

    @SerializedName("status")
    private String status;

    @SerializedName("token")
    private String token;

    @SerializedName("selected")
    private boolean selected = false;

    @SerializedName("defaultCard")
    private boolean defaultCard = false;

    @SerializedName("active")
    private boolean active = true;

    public String getBalanceCard() {
        return this.BalanceCard;
    }

    public String getCodeProduit() {
        return this.CodeProduit;
    }

    public String getDateExpiration() {
        return this.DateExpiration;
    }

    public String getDcId() {
        return this.dcId;
    }

    public String getLibelleProduit() {
        return this.LibelleProduit;
    }

    public String getNumeroCarte() {
        return this.NumeroCarte;
    }

    public ArrayList getOperations() {
        return this.Operations;
    }

    public String getPlainNumeroCarte() {
        return this.PlainNumeroCarte;
    }

    public String getPsn() {
        return this.Psn;
    }

    public String getRadicalClient() {
        return this.radicalClient;
    }

    public String getStatus() {
        return this.status;
    }

    public String getToken() {
        return this.token;
    }

    public String getUrlImage() {
        return this.UrlImage;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean isDefaultCard() {
        return this.defaultCard;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setActive(boolean z) {
        this.active = z;
    }

    public void setBalanceCard(String str) {
        this.BalanceCard = str;
    }

    public void setCodeProduit(String str) {
        this.CodeProduit = str;
    }

    public void setDateExpiration(String str) {
        this.DateExpiration = str;
    }

    public void setDcId(String str) {
        this.dcId = str;
    }

    public void setDefaultCard(boolean z) {
        this.defaultCard = z;
    }

    public void setLibelleProduit(String str) {
        this.LibelleProduit = str;
    }

    public void setNumeroCarte(String str) {
        this.NumeroCarte = str;
    }

    public void setOperations(ArrayList arrayList) {
        this.Operations = arrayList;
    }

    public void setPlainNumeroCarte(String str) {
        this.PlainNumeroCarte = str;
    }

    public void setPsn(String str) {
        this.Psn = str;
    }

    public void setRadicalClient(String str) {
        this.radicalClient = str;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setUrlImage(String str) {
        this.UrlImage = str;
    }

    public String toString() {
        return "Card{selected=" + this.selected + ", defaultCard=" + this.defaultCard + ", active=" + this.active + ", dcId='" + this.dcId + "', radicalClient='" + this.radicalClient + "', NumeroCarte='" + this.NumeroCarte + "', PlainNumeroCarte='" + this.PlainNumeroCarte + "', CodeProduit='" + this.CodeProduit + "', LibelleProduit='" + this.LibelleProduit + "', DateExpiration='" + this.DateExpiration + "', BalanceCard='" + this.BalanceCard + "', UrlImage='" + this.UrlImage + "', Operations=" + this.Operations + ", Psn='" + this.Psn + "', Token='" + this.token + "', Status='" + this.status + "'}";
    }
}
