package com.b3g.ui.menu;

import com.b3g.ui.MenuHardDataOffline;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.tree.TreeModel;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TreeMenuHardDataOffline {
    public ArrayList menuParentList;
    public MenuHardDataOffline mnHRD = new MenuHardDataOffline();

    public TreeModel GetNewModel() {
        NodeB3G nodeB3G;
        this.menuParentList = this.mnHRD.GetParentMenu();
        NodeB3G nodeB3G2 = new NodeB3G("Choisir votre langue", 9, GetImageFromStringID("/cihv3.res", "translationOffline.png"), "", 1, "", null, 1, 90, 99);
        NodeB3G nodeB3G3 = new NodeB3G("Démonstration", 7, GetImageFromStringID("/cihv3.res", "PlayOffline.png"), "", 1, "", null, 1, 90, 82);
        NodeB3G nodeB3G4 = new NodeB3G("Nous Trouver", 4, GetImageFromStringID("/cihv3.res", "geo.png"), "", 1, "", null, 1, 90, 83);
        NodeB3G nodeB3G5 = new NodeB3G("Applis et Sites CIH", 6, GetImageFromStringID("/cihv3.res", "appsOffline.png"), "", 1, "", null, 1, 90, 84);
        NodeB3G nodeB3G6 = new NodeB3G("Cours de Vente", 5, GetImageFromStringID("/cihv3.res", "exchangeOffline.png"), "", 1, "", null, 1, 90, 86);
        new NodeB3G("Réalité Augmentée", 10, GetImageFromStringID("/cihv3.res", "apps.png"), "", 1, "", null, 1, 90, 100);
        NodeB3G nodeB3G7 = new NodeB3G("Nous Joindre", 8, GetImageFromStringID("/cihv3.res", "callOffline.png"), "", 1, "", null, 1, 90, 88);
        NodeB3G nodeB3G8 = new NodeB3G("Règles de confidentialité", 8, GetImageFromStringID("/cihv3.res", "confd.png"), "", 1, "", null, 1, 90, 162);
        if (!Display.getInstance().getPlatformName().equals("ios")) {
            nodeB3G = new NodeB3G("Services CIHBANK Mobile ", 1, GetImageFromStringID("/cihv3.res", "Mes_comptesV2.png"), "", 0, "", new NodeB3G[]{nodeB3G2, nodeB3G6, nodeB3G5, nodeB3G3, nodeB3G4, nodeB3G7, nodeB3G8}, 0, 99, 2);
        } else {
            nodeB3G = new NodeB3G("Services CIHBANK Mobile ", 1, GetImageFromStringID("/cihv3.res", "Mes_comptesV2.png"), "", 0, "", new NodeB3G[]{nodeB3G2, nodeB3G6, nodeB3G5, nodeB3G3, nodeB3G4, nodeB3G7}, 0, 99, 2);
        }
        return new 1(nodeB3G);
    }

    class 1 implements TreeModel {
        NodeB3G[] sillyTreeB3G;
        final /* synthetic */ NodeB3G val$nodeOffligne;

        1(NodeB3G nodeB3G) {
            this.val$nodeOffligne = nodeB3G;
            this.sillyTreeB3G = new NodeB3G[]{nodeB3G};
        }

        public Vector getChildren(Object obj) {
            NodeB3G[] nodeB3GArr;
            NodeB3G nodeB3G = (NodeB3G) obj;
            if (obj == null) {
                nodeB3GArr = this.sillyTreeB3G;
            } else {
                nodeB3GArr = nodeB3G.children;
            }
            Vector vector = new Vector();
            for (NodeB3G nodeB3G2 : nodeB3GArr) {
                vector.addElement(nodeB3G2);
            }
            return vector;
        }

        public boolean isLeaf(Object obj) {
            return !TreeMenuHardDataOffline.this.menuParentList.contains(((NodeB3G) obj).GetLabel());
        }
    }

    public static Image GetImageFromStringID(String str, String str2) {
        try {
            return Resources.open(str).getImage(str2);
        } catch (IOException unused) {
            return null;
        }
    }
}
