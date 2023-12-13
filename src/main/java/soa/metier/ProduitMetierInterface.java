package soa.metier;


import soa.entities.Categorie;
import soa.entities.Facture;
import soa.entities.Produit;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProduitMetierInterface {
    boolean changerCategorieProduit(Long idProduit,  Long idNouvelleCategorie );
    void ajouterProduit(Produit p);
    List<Produit> listeProduits();
    double calculerCoutVenteStock(double coefficientRemise);
    Produit trouverCoutVenteMoinsCher();
    Produit trouverCoutVentePlusCher();
    Map<String, Double> calculerPourcentageParCategorie();
    List<Produit> listerProduitsParPrixDesc();
    List<Produit> listerProduitsParPrixAsc();
    // Dans ProduitMetierInterface
// Dans ProduitMetierInterface
    List<Produit> listerProduitsEnPromotion();

    long calculateStockEntreDates(Date startDate, Date endDate);
    Map<String, Double> calculerPourcentageParQuantite();


    int nombreProduitsVendusEntreDates(Facture facture, Produit produit);

    int nombreProduitsVendusEntredeuxDates(Produit produit, Date date);

    double afficherPourcentageQuantiteVendue(Produit produit, Date date);

    Map<String, Double> calculerPourcentageQuantiteVendue(List<Produit> lp, Date date);

    public Map<String, Double> afficherPourcentageQuantiteVenduetous(String date) throws ParseException;
}