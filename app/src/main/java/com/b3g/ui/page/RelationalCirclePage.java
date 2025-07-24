package com.b3g.ui.page;

import com.b3g.cih.online.CihMBanking;
import com.b3g.services.B3gService;
import com.b3g.services.Relation;
import com.b3g.services.RelationType;
import com.b3g.ui.B3GUiManager;
import com.b3g.ui.page.cih.Module.Settings;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class RelationalCirclePage extends B3GPage {
    public RelationalCirclePage(Object obj, Object obj2, int i) {
        this.uiManager = (B3GUiManager) obj;
        this.service = (B3gService) obj2;
        this.PageId = i;
    }

    public Container GetPageContainer() {
        CihMBanking.exitApplication = true;
        this.thisContainer = new Container(new BoxLayout(2));
        this.thisContainer.setUIID("mn_cntMenuItem");
        try {
            ArrayList relationTypeFromRelations = getRelationTypeFromRelations(new Relation().RelationProcess(97, true));
            if (relationTypeFromRelations.size() == 1) {
                this.thisContainer.addComponent(new RelationListPage(this.uiManager, null, 49).GetPageContainer());
            } else {
                this.thisContainer.addComponent(this.uiManager.DrawListContainer("GloabalContainerV2", "Type de Relation", Boolean.TRUE, relationTypeFromRelations, relationTypeFromRelations.size(), 65, "Vous n'avez aucune relation pour le moment", null, null, null, null));
            }
            Container container = new Container();
            container.setUIID("g_CntTranspMsg");
            this.thisContainer.addComponent(container);
        } catch (Exception unused) {
            this.thisContainer.addComponent(this.uiManager.GetGoBackHome());
            Settings.setNightMode(this.thisContainer, 0);
        }
        Settings.setNightMode(this.thisContainer, 0);
        return this.thisContainer;
    }

    private ArrayList getRelationTypeFromRelations(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String relationType = ((Relation) it.next()).getRelationType();
            if (!isTypeInList(relationType, arrayList2)) {
                arrayList2.add(new RelationType(relationType, getRelationTitleFromType(relationType), ""));
            }
        }
        return arrayList2;
    }

    private String getRelationTitleFromType(String str) {
        return str.equals("001") ? "Tuteur/Mineur" : "Autre relation";
    }

    private boolean isTypeInList(String str, ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (((RelationType) it.next()).getType().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
