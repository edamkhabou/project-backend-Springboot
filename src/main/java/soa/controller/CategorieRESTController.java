package soa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soa.entities.Categorie;
import soa.entities.Produit;
import soa.metier.CategorieMetierInterface;
import soa.metier.ProduitMetierInterface;
import soa.repository.CategorieRepository;
import soa.repository.ProduitRepository;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController // pour déclarer un service web de type REST
@RequestMapping("/categories")
public class CategorieRESTController {
    @Autowired
    private CategorieRepository categorieRepos;

    @Autowired
    private CategorieMetierInterface categorieInterface ;
    // Message d'accueil
// http://localhost:8080/produits/index (GET)
    @GetMapping(
            // spécifier le path de la méthode
            value = "/",
            // spécifier le format de retour en XML
            produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
    )
    public List<Categorie> getAllCategories() {
        return categorieRepos.findAll();
    }

}
