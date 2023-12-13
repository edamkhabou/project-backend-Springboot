package soa.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soa.entities.Produit;
import soa.metier.ProduitMetierInterface;
import soa.repository.ProduitRepository;
import org.springframework.http.MediaType;


@CrossOrigin(origins = "*")
@RestController // pour déclarer un service web de type REST
@RequestMapping("/produits") // http://localhost:8080/produit
public class ProduitRESTController {
    @Autowired
    private ProduitRepository produitRepos;

    @Autowired
    private ProduitMetierInterface produitInterface ;
    // Message d'accueil
// http://localhost:8080/produits/index (GET)
    @GetMapping(value = "/index")
    public String accueil() {
        return "BienVenue au service Web REST 'produits'.....";
    }

    //Afficher la liste des produits
//http://localhost:8080/produits/ (GET)
    @GetMapping(
            // spécifier le path de la méthode
            value = "/",
            // spécifier le format de retour en XML
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public List<Produit> getAllProduits() {
        return produitRepos.findAll();
    }

    @GetMapping(
            // spécifier le path de la méthode qui englobe un paramètre
            value = "/{id}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public Produit getProduit(@PathVariable Long id) {
        Produit p = produitRepos.findById(id).get();
        return p;
    }

    // Supprimer un produit avec la méthode 'DELETE'
    // http://localhost:8080/produits/ (DELETE)
    @DeleteMapping(
            // spécifier le path de la méthode
            value = "/")
    public void deleteProduit(@RequestBody Produit p)
    {
        produitRepos.delete(p);
    }
    // ajouter un produit avec la méthode "POST"
    // http://localhost:8080/produits/ (POST)
    @PostMapping(
            // spécifier le path de la méthode
    value = "/" ,
//spécifier le format de retour
produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public Produit saveProduit(@RequestBody Produit p) {
        return produitRepos.save(p);
    }
    // modifier un produit avec la méthode "PUT"
    // http://localhost:8080/produits/ (PUT)
    @PutMapping(
            // spécifier le path de la méthode
    value = "/" ,
    // spécifier le format de retour
    produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public Produit updateProduit(@RequestBody Produit p)
    {
        return produitRepos.save(p);
    }
    @GetMapping(value = "/statistique",
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Double> categoriepourcentage() {
return produitInterface.calculerPourcentageParCategorie();
    }

    @GetMapping(value = "/afficherPourcentageQuantiteVenduetous/{date}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Double> afficherPourcentageQuantiteVenduetous(@PathVariable String date) throws ParseException {
        return produitInterface.afficherPourcentageQuantiteVenduetous(date);
    }

    @GetMapping(value = "/listerProduitsParPrixAsc", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Produit> listerProduitsParPrixAsc() {
        return produitInterface.listerProduitsParPrixAsc();
    }
@GetMapping(value = "/listerProduitsParPrixDesc", produces = {MediaType.APPLICATION_JSON_VALUE})
public List<Produit> listerProduitsParPrixDesc() {
    return produitInterface.listerProduitsParPrixDesc();
}

    @GetMapping(value = "/trouverCoutVenteMoinsCher", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Produit trouverCoutVenteMoinsCher() {
        return produitInterface.trouverCoutVenteMoinsCher();
    }

    @GetMapping(value = "/trouverCoutVentePlusCher", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Produit trouverCoutVentePlusCher() {
        return produitInterface.trouverCoutVentePlusCher();
    }

    @GetMapping(value = "/calculerPourcentageParQuantite", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Double> calculerPourcentageParQuantite() {
        return produitInterface.calculerPourcentageParQuantite();
    }

}