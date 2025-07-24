package com.b3g.ui.menu;

import com.b3g.cih.online.CihMBanking;
import com.b3g.ui.MenuHardData;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.tree.TreeModel;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/* loaded from: /storage/emulated/0/Android/data/com.apktools.app.decompile/files/decompile_out/com.b3g.cih.online/classes2.dex */
public class TreeMenuHardData {
    public ArrayList menuParentList;
    public MenuHardData mnHRD = new MenuHardData();

    /* JADX WARN: Removed duplicated region for block: B:11:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x05f4  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x072a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x075b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0625  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x037d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.codename1.ui.tree.TreeModel GetNewModel() {
        /*
            Method dump skipped, instructions count: 2030
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b3g.ui.menu.TreeMenuHardData.GetNewModel():com.codename1.ui.tree.TreeModel");
    }

    class 1 implements TreeModel {
        NodeB3G[] sillyTreeB3G;
        final /* synthetic */ NodeB3G val$ndAccount;
        final /* synthetic */ NodeB3G val$ndAccountOperation;
        final /* synthetic */ NodeB3G val$ndDotation;
        final /* synthetic */ NodeB3G val$ndEdocuments;
        final /* synthetic */ NodeB3G val$ndHelp;
        final /* synthetic */ NodeB3G val$ndOnlineRequest;
        final /* synthetic */ NodeB3G val$ndProduct;
        final /* synthetic */ NodeB3G val$ndService;

        1(NodeB3G nodeB3G, NodeB3G nodeB3G2, NodeB3G nodeB3G3, NodeB3G nodeB3G4, NodeB3G nodeB3G5, NodeB3G nodeB3G6, NodeB3G nodeB3G7, NodeB3G nodeB3G8) {
            this.val$ndAccount = nodeB3G;
            this.val$ndProduct = nodeB3G2;
            this.val$ndDotation = nodeB3G3;
            this.val$ndAccountOperation = nodeB3G4;
            this.val$ndService = nodeB3G5;
            this.val$ndOnlineRequest = nodeB3G6;
            this.val$ndEdocuments = nodeB3G7;
            this.val$ndHelp = nodeB3G8;
            this.sillyTreeB3G = new NodeB3G[]{nodeB3G, nodeB3G2, nodeB3G3, nodeB3G4, nodeB3G5, nodeB3G6, nodeB3G7, nodeB3G8};
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
            return !TreeMenuHardData.this.menuParentList.contains(((NodeB3G) obj).GetLabel());
        }
    }

    class 2 implements TreeModel {
        Node[] sillyTree = {new Node("Mes Comptes", new Node[]{new Node("Mes Comptes Chèque", new Node[0]), new Node("Mes Comptes d'épargne", new Node[0])}), new Node("Ma Relation Banque", new Node[]{new Node("Mes Cartes", new Node[0]), new Node("Mes Prêts", new Node[0]), new Node("Mes Prélèvements", new Node[0]), new Node("Mon Portefeuille Titre", new Node[0]), new Node("Ma Bancassurance", new Node[0])}), new Node("Mes Opérations", new Node[]{new Node("Virements", new Node[0]), new Node("Recharge Binatna", new Node[0])}), new Node("Mes Demandes En Ligne", new Node[]{new Node("Transfert Etudiant", new Node[0]), new Node("Commande de Fonds Dirhams", new Node[0]), new Node("Commande de Fonds Devise", new Node[0])}), new Node("Mes E-Documents", new Node[]{new Node("E-Documents", new Node[0])}), new Node("A Propos", new Node[]{new Node("Infos Pratiques", new Node[0])})};

        2() {
        }

        public Vector getChildren(Object obj) {
            Object[] objArr;
            Node node = (Node) obj;
            if (obj == null) {
                objArr = this.sillyTree;
            } else {
                objArr = node.children;
            }
            Vector vector = new Vector();
            for (Object obj2 : objArr) {
                vector.addElement(obj2);
            }
            return vector;
        }

        public boolean isLeaf(Object obj) {
            Node node = (Node) obj;
            return node.children == null || node.children.length == 0;
        }
    }

    public TreeModel GetModel() {
        return new 2();
    }

    public TreeModel GetTDMNModel() {
        NodeB3G nodeB3G;
        NodeB3G nodeB3G2;
        NodeB3G nodeB3G3;
        NodeB3G nodeB3G4;
        this.menuParentList = this.mnHRD.GetParentMenu();
        NodeB3G nodeB3G5 = new NodeB3G("Mes Comptes", 1, GetImageFromStringID("/cihv3.res", "Mes_comptesV2.png"), "", 0, "", new NodeB3G[]{new NodeB3G("Mes Comptes Chèque", 2, GetImageFromStringID("/cihv3.res", "Mes comptes cheques.png"), "", 1, "", null, 1, 2, 2)}, 0, 99, 2);
        NodeB3G nodeB3G6 = new NodeB3G("Virements", 9, GetImageFromStringID("/cihv3.res", "Virements.png"), "", 3, "", null, 1, 90, 8);
        NodeB3G nodeB3G7 = new NodeB3G("Paiements de Factures", 12, GetImageFromStringID("/cihv3.res", "mtcPaiement.png"), "", 3, "", null, 1, 90, 25);
        NodeB3G nodeB3G8 = new NodeB3G("Recharges", 13, GetImageFromStringID("/cihv3.res", "mtcTopUp.png"), "", 3, "", null, 1, 90, 33);
        NodeB3G nodeB3G9 = new NodeB3G("Paiement de la Vignette", 12, GetImageFromStringID("/cihv3.res", "mtcDgi.png"), "", 3, "", null, 1, 90, 55);
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            nodeB3G = new NodeB3G("Mes Opérations", 3, GetImageFromStringID("/cihv3.res", "Operations_sur_compte.png"), "", 0, "", new NodeB3G[]{nodeB3G6, nodeB3G7, nodeB3G8, nodeB3G9}, 0, 99, 2);
        } else {
            nodeB3G = new NodeB3G("Mes Opérations", 3, GetImageFromStringID("/cihv3.res", "Operations_sur_compte.png"), "", 0, "", new NodeB3G[]{nodeB3G6, nodeB3G7, nodeB3G8, nodeB3G9}, 0, 99, 2);
        }
        NodeB3G nodeB3G10 = new NodeB3G("Paramètres", 8, GetImageFromStringID("/cihv3.res", "PMTRparametre.png"), "", 2, "", null, 1, 90, 104);
        if (CihMBanking.sesPMTR.getIsDemo() == 0) {
            if (!Display.getInstance().getPlatformName().equals("ios")) {
                nodeB3G2 = new NodeB3G("Mes Services", 4, GetImageFromStringID("/cihv3.res", "Services.png"), "", 0, "", new NodeB3G[]{nodeB3G10}, 0, 99, 2);
            } else {
                nodeB3G2 = new NodeB3G("Mes Services", 4, GetImageFromStringID("/cihv3.res", "Services.png"), "", 0, "", new NodeB3G[]{nodeB3G10}, 0, 99, 2);
            }
        } else {
            nodeB3G2 = new NodeB3G("Mes Services", 4, GetImageFromStringID("/cihv3.res", "Services.png"), "", 0, "", new NodeB3G[]{nodeB3G10}, 0, 99, 2);
        }
        NodeB3G nodeB3G11 = new NodeB3G("Demande RIB", 19, GetImageFromStringID("/cihv3.res", "rib_v2.png"), "", 5, "", null, 1, 90, 16);
        if (Display.getInstance().getPlatformName().equals("win")) {
            nodeB3G3 = new NodeB3G("Mes Demandes En Ligne", 5, GetImageFromStringID("/cihv3.res", "Operations_sur_compte.png"), "", 0, "", new NodeB3G[]{nodeB3G11}, 0, 99, 36);
        } else {
            nodeB3G3 = new NodeB3G("Mes Demandes En Ligne", 5, GetImageFromStringID("/cihv3.res", "Operations_sur_compte.png"), "", 0, "", new NodeB3G[]{nodeB3G11}, 0, 99, 36);
        }
        NodeB3G nodeB3G12 = new NodeB3G("Mes E-Documents", 6, GetImageFromStringID("/cihv3.res", "Operations_sur_compte.png"), "", 0, "", new NodeB3G[]{new NodeB3G("E-Documents", 23, GetImageFromStringID("/cihv3.res", "E-docs.png"), "", 6, "", null, 1, 90, 39)}, 0, 99, 38);
        NodeB3G nodeB3G13 = new NodeB3G("Tarifs des opérations", 24, GetImageFromStringID("/cihv3.res", "tarif.png"), "", 7, "", null, 1, 90, 134);
        NodeB3G nodeB3G14 = new NodeB3G("Contacter Votre Conseiller", 24, GetImageFromStringID("/cihv3.res", "adviser.png"), "", 7, "", null, 1, 90, 18);
        NodeB3G nodeB3G15 = new NodeB3G("Votre avis Nous Intéresse", 25, GetImageFromStringID("/cihv3.res", "comments.png"), "", 7, "", null, 1, 90, 17);
        NodeB3G nodeB3G16 = new NodeB3G("A Propos", 26, GetImageFromStringID("/cihv3.res", "infos_pratiques.png"), "", 7, "", null, 1, 90, 11);
        NodeB3G nodeB3G17 = new NodeB3G("Déposer une Réclamation", 27, GetImageFromStringID("/cihv3.res", "help_gr.png"), "", 7, "", null, 1, 90, 19);
        if (CihMBanking.sesPMTR.getSessionClient().isIsClientAdult()) {
            nodeB3G4 = new NodeB3G("Contacts & Infos", 7, GetImageFromStringID("/cihv3.res", "infos_pratiques.png"), "", 0, "", new NodeB3G[]{nodeB3G13, nodeB3G14, nodeB3G15, nodeB3G17, nodeB3G16}, 0, 99, 2);
        } else {
            nodeB3G4 = new NodeB3G("Contacts & Infos", 7, GetImageFromStringID("/cihv3.res", "infos_pratiques.png"), "", 0, "", new NodeB3G[]{nodeB3G14}, 0, 99, 2);
        }
        return new 3(nodeB3G5, nodeB3G, nodeB3G2, nodeB3G3, nodeB3G12, nodeB3G4);
    }

    class 3 implements TreeModel {
        NodeB3G[] sillyTreeB3G;
        final /* synthetic */ NodeB3G val$ndAccount;
        final /* synthetic */ NodeB3G val$ndAccountOperation;
        final /* synthetic */ NodeB3G val$ndEdocuments;
        final /* synthetic */ NodeB3G val$ndHelp;
        final /* synthetic */ NodeB3G val$ndOnlineRequest;
        final /* synthetic */ NodeB3G val$ndService;

        3(NodeB3G nodeB3G, NodeB3G nodeB3G2, NodeB3G nodeB3G3, NodeB3G nodeB3G4, NodeB3G nodeB3G5, NodeB3G nodeB3G6) {
            this.val$ndAccount = nodeB3G;
            this.val$ndAccountOperation = nodeB3G2;
            this.val$ndService = nodeB3G3;
            this.val$ndOnlineRequest = nodeB3G4;
            this.val$ndEdocuments = nodeB3G5;
            this.val$ndHelp = nodeB3G6;
            this.sillyTreeB3G = new NodeB3G[]{nodeB3G, nodeB3G2, nodeB3G3, nodeB3G4, nodeB3G5, nodeB3G6};
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
            return !TreeMenuHardData.this.menuParentList.contains(((NodeB3G) obj).GetLabel());
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
