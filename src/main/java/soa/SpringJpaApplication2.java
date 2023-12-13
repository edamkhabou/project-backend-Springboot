package soa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import soa.entities.Categorie;
import soa.entities.DetailsFactures;
import soa.entities.Facture;
import soa.entities.Produit;
import soa.metier.CategorieMetierInterface;
import soa.metier.DetailsFactureInterface;
import soa.metier.FactureMetierInterface;
import soa.metier.ProduitMetierInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringJpaApplication2 {
    //déclaration des objets de type Repository
    //Déclaration d'un objet métier pour gérer les produits
    static ProduitMetierInterface produitMetier;
    static CategorieMetierInterface categorieMetier;

    static FactureMetierInterface factureMetier;

    static DetailsFactureInterface DetailsFacturesMetier;

    public static void main(String[] args) throws ParseException {
        System.out.println("---------Injection de dépendances----------");
        //Commencer par réaliser les injections de dépendances pour les objets de type Repository
        // référencer le contexte
        ApplicationContext contexte = SpringApplication.run(SpringJpaApplication2.class, args);
        // Récupérer une implémentation de l'interface "ProduitRepository" par injection de dépendance
        produitMetier = contexte.getBean(ProduitMetierInterface.class);
        categorieMetier = contexte.getBean(CategorieMetierInterface.class);
        factureMetier = contexte.getBean(FactureMetierInterface.class);
        DetailsFacturesMetier = contexte.getBean(DetailsFactureInterface.class);


        //objet pour formater les dates selon le pattern "yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = null;
        java.util.Date date2 = null;
        java.util.Date date3 = null;
        java.util.Date date4 = null;
        java.util.Date date5 = null;
        java.util.Date date6 = null;
        java.util.Date date7 = null;


        //trois objets de type date
        try
        {
            date1 = sdf.parse("2021-04-15");
            date2 = sdf.parse("2022-02-20");
            date3 = sdf.parse("2021-01-10");
            date4 = sdf.parse("2021-05-15");
            date5 = sdf.parse("2023-04-15");
            date6 = sdf.parse("2023-06-15");
            date7 = sdf.parse("2023-03-15");

        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Categorie catInformatique = new Categorie("INF", "Informatique");
        Categorie catJouet = new Categorie("JOUET", "Jouets");
        Categorie catSport = new Categorie("SPORT", "Sports");

        categorieMetier.ajouterCategorie(catInformatique);
       categorieMetier.ajouterCategorie(catJouet);
       categorieMetier.ajouterCategorie(catSport);


        // Insérer un produit sans catégorie et sans stock
        System.out.println("-2-Insérer des produits  ----------");
        Produit pImprimante =new Produit("Imprimante","I11", 500.0, 30, date2 ,catJouet,0.3f);
        produitMetier.ajouterProduit(pImprimante);

        Produit pOrdinateur =new Produit("Ordinateur", "O12", 2400.0, 50, date1 ,catInformatique,0.6f);
        produitMetier.ajouterProduit(pOrdinateur);

Produit pBascketBall =new Produit("BascketBall", "B17", 2400.0, 60, date1 ,catSport,0.9f);
        produitMetier.ajouterProduit(pBascketBall);



        Produit pTablette =new Produit("Tablette","T13", 300.0, 40, date3,catJouet,0.4f );
        produitMetier.ajouterProduit(pTablette);

        Produit pSmartPhone =new Produit("SmartPhone","S15", 1000.0, 50, date4,catJouet,0.9f );
        produitMetier.ajouterProduit(pSmartPhone);

        Produit Poppy =new Produit("Poppy","P16", 1000.0, 35, date4 ,catJouet,0.1f);
        produitMetier.ajouterProduit(Poppy);

        System.out.println("-3-Insérer une Facture ''  ----------");

        Facture f1 = new Facture("01",date5,1,2);
        Facture f2 = new Facture("02",date6,2,3);
        Facture f3 = new Facture("03",date7,3,4);
factureMetier.ajouterFacture(f1);
factureMetier.ajouterFacture(f2);
factureMetier.ajouterFacture(f3);
        DetailsFactures d1 = new DetailsFactures(f1,pImprimante,14,1200);
        DetailsFacturesMetier.ajouterDetailsFactures(d1);
        DetailsFactures d2 = new DetailsFactures(f2,pImprimante,16,1400);
        DetailsFacturesMetier.ajouterDetailsFactures(d2);
        long st = produitMetier.calculateStockEntreDates(date1,date2);
System.out.println(st);
//        // Changer la catégorie du produit 'Tablette' à 'Informatique'
//        System.out.println("-5-Changer la catégorie du produit 'Tablette' à 'Informatique'");
//        boolean resultat =produitMetier.changerCategorieProduit(3L,1L);
//        if (resultat)
//            System.out.println("----Succès du changement de catégorie----'");
//        else
//            System.out.println("----Echec du changement de catégorie----'");
//        afficherTousLesProduits();
//
//        //Rendre tous les produits achetés avant '2023' en promotion
//        System.out.println("-6-Rendre tous les produits achetés avant '2023' en promotion");
//        produitMetier.rendreProduitsEnPromotionAvant(dateFinPromotion);
//        afficherTousLesProduits();

//        //Calculer le coût de vente de tous les produits en stock en appliquant la remise sur les produits en promotion
//        System.out.println("-7-Calculer le coût de vente de tous les produits en stock en appliquant la remise sur les produits en promotion");
//        System.out.println("Cout de vente du stock:"+produitMetier.calculerCoutVenteStock(50));
//
//        System.out.println("********************Début**********************");
//        System.out.println("***********Produit Moind Cher************");
//Produit pmcher = produitMetier.trouverCoutVenteMoinsCher();
//        System.out.println(pmcher);
//        System.out.println("********************Début**********************");
//        System.out.println("***********Produit Plus Cher************");
//        Produit ppcher = produitMetier.trouverCoutVentePlusCher();
//        System.out.println(ppcher);
afficherTousLesProduits();
        System.out.println("salmennnnnnnnnnnnnnnnnnnnnn");
Map<String, Double> cc= produitMetier.afficherPourcentageQuantiteVenduetous("2023-12-04");
        for (Map.Entry<String, Double> entry : cc.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "%");
        }
        int quantiteVendue = produitMetier.nombreProduitsVendusEntredeuxDates(pImprimante, date1);

        System.out.println(quantiteVendue);

        Map<String, Double> lst = produitMetier.calculerPourcentageParCategorie();

        if (lst != null) {
            System.out.println("Category Percentages:");
            for (Map.Entry<String, Double> entry : lst.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + "%");
            }
        } else {
            System.out.println("No categories or products available.");
        }

    }

    static void afficherTousLesProduits()
    {
        System.out.println("********************Début**********************");
        System.out.println("Afficher tous les produits...");
        System.out.println("***********************************************");

        List<Produit> lp = produitMetier.listeProduits();
        for (Produit p : lp)
        {
            System.out.println(p);
        }
        System.out.println("********************Fin************************");
    }
}
