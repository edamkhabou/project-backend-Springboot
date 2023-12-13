package soa.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.entities.Categorie;
import soa.entities.DetailsFactures;
import soa.entities.Facture;
import soa.entities.Produit;
import soa.repository.CategorieRepository;
import soa.repository.DetailFactureRepository;
import soa.repository.ProduitRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProduitMetierImpl implements ProduitMetierInterface {

    @Autowired  //injection de dépendances
    ProduitRepository produitRepository;
    @Autowired  //injection de dépendances
    CategorieRepository categorieRepository;
    @Autowired
    DetailFactureRepository detailsFacturesRepository;

    @Override
    public boolean changerCategorieProduit(Long idProduit, Long idNouvelleCategorie) {
        //Récupérer le produit
        Produit p = produitRepository.findById(idProduit).get();
        //Récupérer le produit
        Categorie c = categorieRepository.findById(idNouvelleCategorie).get();
        if (p != null && c != null) {
            p.setCategorie(c);
            produitRepository.save(p);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void ajouterProduit(Produit p) {
        produitRepository.save(p);
    }


    @Override
    public List<Produit> listeProduits() {
        return produitRepository.findAll();
    }


    @Override
    public double calculerCoutVenteStock(double coefficientRemise) {
        return 0;
    }


    @Override
    public Produit trouverCoutVenteMoinsCher() {
        List<Produit> produits = produitRepository.findAll();

        if (produits.isEmpty()) {
            return null; // No products available
        }

        Produit produitMoinsCher = null;
        double lowestCost = Double.MAX_VALUE;

        for (Produit produit : produits) {
            double prix = produit.getPrix();
            double currentCost = produit.getQuantite() * prix;

            if (currentCost < lowestCost) {
                lowestCost = currentCost;
                produitMoinsCher = produit;
            }
        }

        return produitMoinsCher;
    }

    @Override
    public Produit trouverCoutVentePlusCher() {
        List<Produit> produits = produitRepository.findAll();

        if (produits.isEmpty()) {
            return null; // No products available
        }

        Produit produitPlusCher = null;
        double highestCost = Double.MIN_VALUE;

        for (Produit produit : produits) {
            double prix = produit.getPrix();
            double currentCost = produit.getQuantite() * prix;

            if (currentCost > highestCost) {
                highestCost = currentCost;
                produitPlusCher = produit;
            }
        }

        return produitPlusCher;
    }

    @Override
    public Map<String, Double> calculerPourcentageParCategorie() {
        List<Categorie> categories = categorieRepository.findAll();
        List<Produit> produits = produitRepository.findAll();

        if (categories.isEmpty() || produits.isEmpty()) {
            return null; // No categories or products available
        }

        // Initialize a map to store category names and their corresponding percentages
        Map<String, Double> pourcentageParCategorie = new HashMap<>();

        // Calculate total cost for each category
        Map<Long, Double> totalCostByCategory = new HashMap<>();
        for (Produit produit : produits) {
            double prix = produit.getPrix();
            double currentCost = produit.getQuantite() * prix;

            Long categoryId = produit.getCategorie().getId();
            totalCostByCategory.put(categoryId, totalCostByCategory.getOrDefault(categoryId, 0.0) + currentCost);
        }

        // Calculate percentage for each category
        double totalCostAllCategories = totalCostByCategory.values().stream().mapToDouble(Double::doubleValue).sum();
        for (Categorie categorie : categories) {
            Long categoryId = categorie.getId();
            double categoryCost = totalCostByCategory.getOrDefault(categoryId, 0.0);
            double percentage = (categoryCost / totalCostAllCategories) * 100;
            pourcentageParCategorie.put(categorie.getLibelle(), percentage);
        }

        return pourcentageParCategorie;
    }

    @Override
    public List<Produit> listerProduitsParPrixDesc() {
        List<Produit> produits = produitRepository.findAll();
        produits.sort(Comparator.comparingDouble(Produit::getPrix).reversed());
        return produits;    }

    // Dans ProduitMetierImpl
    @Override
    public List<Produit> listerProduitsParPrixAsc() {
        List<Produit> produits = produitRepository.findAll();
        produits.sort(Comparator.comparingDouble(Produit::getPrix));
        return produits;
    }

    @Override
    public List<Produit> listerProduitsEnPromotion() {
        return null;
    }
    // Dans ProduitMetierImpl
// Dans ProduitMetierImpl

    @Override
    public long calculateStockEntreDates(Date startDate, Date endDate) {
        // Convert Date objects to LocalDate
        long stock = 0;
        List<Produit> l = produitRepository.findAll();
        for (Produit p : l) {
            if (p.getDateAchat().compareTo(startDate) > 0 && p.getDateAchat().compareTo(endDate) < 0) {
                stock += p.getQuantite();
            }
        }
        return stock;
    }
    public Map<String, Double> calculerPourcentageParQuantite() {
        Map<String, Double> pourcentages = new HashMap<>();
        List<Produit> produits = listeProduits();

        // Étape 1 : Calculer la quantité totale
        int quantiteTotale = produits.stream().mapToInt(Produit::getQuantite).sum();

        // Étape 2 : Calculer le pourcentage de chaque quantité
        for (Produit produit : produits) {
            double pourcentage = (produit.getQuantite() * 100.0) / quantiteTotale;
            pourcentages.put(produit.getDesignation(), pourcentage);
        }

        return pourcentages;
    }

    @Override
    public int nombreProduitsVendusEntreDates(Facture facture, Produit produit) {
        // Récupérer la liste de tous les détails de factures
        List<DetailsFactures> detailsFactures = detailsFacturesRepository.findAll();

        // Initialiser le compteur
        int nombreProduitsVendus = 0;

        // Parcourir les détails de factures et compter les produits vendus
        for (DetailsFactures detailsFacture : detailsFactures) {
            // Vérifier si la date de la facture est entre les deux dates spécifiées
            if (detailsFacture.getFacture().getDateF().after(facture.getDateF()) &&
                    detailsFacture.getFacture().getDateF().before(produit.getDateAchat())) {
                // Ajouter la quantité de produits de chaque détail de facture
                nombreProduitsVendus += detailsFacture.getQte();
            }
        }

        return nombreProduitsVendus;
    }

    @Override
    public int nombreProduitsVendusEntredeuxDates(Produit produit, Date date) {
        // Récupérer la date d'achat du produit
        Date dateAchatProduit = produit.getDateAchat();

        // Récupérer la liste de tous les détails de factures
        List<DetailsFactures> detailsFactures = detailsFacturesRepository.findAll();

        // Initialiser le compteur
        int nombreProduitsVendus = 0;

        // Parcourir les détails de factures et compter les produits vendus
        for (DetailsFactures detailsFacture : detailsFactures) {
            // Vérifier si la date de la facture est entre la date d'achat et la date spécifiée
            if (detailsFacture.getFacture().getDateF().after(dateAchatProduit) &&
                    detailsFacture.getFacture().getDateF().before(date)) {
                // Ajouter la quantité de produits de chaque détail de facture
                nombreProduitsVendus += detailsFacture.getQte();
            }
        }

        return nombreProduitsVendus;
    }

    @Override
    public double afficherPourcentageQuantiteVendue(Produit produit, Date date) {
        int quantiteTotale = produit.getQuantite();
        int quantiteVendue = nombreProduitsVendusEntredeuxDates(produit, date);

        double pourcentageVendu = (quantiteVendue * 100.0) / quantiteTotale;

        return pourcentageVendu;
    }
    //wrong
    @Override
    public Map<String, Double> calculerPourcentageQuantiteVendue(List<Produit> lp, Date date) {
        // Initialiser la Map pour stocker les résultats
        Map<String, Double> pourcentages = new HashMap<>();

        // Récupérer la liste de tous les détails de factures une seule fois
        List<DetailsFactures> detailsFactures = detailsFacturesRepository.findAll();

        // Parcourir la liste des produits
        for (Produit produit : lp) {
            // Récupérer la date d'achat du produit
            Date dateAchatProduit = produit.getDateAchat();

            // Initialiser le compteur pour la quantité vendue
            int quantiteVendue = 0;

            // Parcourir les détails de factures et compter les produits vendus pour le produit actuel
            for (DetailsFactures detailsFacture : detailsFactures) {
                // Vérifier si la date de la facture est entre la date d'achat et la date spécifiée
                if (detailsFacture.getFacture().getDateF().after(dateAchatProduit) &&
                        detailsFacture.getFacture().getDateF().before(date) &&
                        detailsFacture.getProduit().equals(produit)) {
                    // Ajouter la quantité de produits de chaque détail de facture
                    quantiteVendue += detailsFacture.getQte();
                }
            }

            // Calculer le pourcentage de la quantité vendue par rapport à la quantité totale du produit
            double pourcentageVendu = (quantiteVendue * 100.0) / produit.getQuantite();

            // Ajouter le résultat à la Map
            pourcentages.put(produit.getDesignation(), pourcentageVendu);
        }

        return pourcentages;
    }

    @Override
    public Map<String, Double> afficherPourcentageQuantiteVenduetous(String date) throws ParseException {
        List<Produit> lp = listeProduits();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(date);

        Map<String, Double> pourcentages = new HashMap<>();

        for (Produit produit : lp) {
            int quantiteTotale = produit.getQuantite();
            int quantiteVendue = nombreProduitsVendusEntredeuxDates(produit, parsedDate);

            double pourcentageVendu = (quantiteVendue * 100.0) / quantiteTotale;

            pourcentages.put(produit.getDesignation(), pourcentageVendu);
        }

        return pourcentages;
    }

}
