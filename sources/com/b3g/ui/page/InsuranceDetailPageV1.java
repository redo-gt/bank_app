package com.b3g.ui.page;

import com.b3g.services.B3gService;
import com.b3g.services.SavingsInsuranceDetails;
import com.b3g.ui.B3GUiManager;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class InsuranceDetailPageV1 extends B3GPage {
    private String codeProduct;
    private String reference;

    public InsuranceDetailPageV1(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setScrollableY(false);
        try {
            SavingsInsuranceDetails savingsInsuranceDetails = new SavingsInsuranceDetails();
            savingsInsuranceDetails.fetchDetails(savingsInsuranceDetails.getDetailsResponse(this.reference, this.codeProduct));
            this.thisContainer.add(savingsInsuranceDetails.DrawDetailsItem(this.codeProduct));
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
        }
        return this.thisContainer;
    }

    public void setCodeProduct(String str) {
        this.codeProduct = str;
    }

    public void setReference(String str) {
        this.reference = str;
    }
}
