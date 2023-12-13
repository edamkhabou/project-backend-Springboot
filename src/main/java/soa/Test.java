package soa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import soa.entities.Produit;
import soa.metier.ProduitMetierInterface;

@SpringBootApplication
public class Test {
    static ProduitMetierInterface produitMetier;

    public static void main(String[] args) {
        System.out.println("---------Injection de dépendances----------");
        //Commencer par réaliser les injections de dépendances pour les objets de type Repository
        // référencer le contexte
        ApplicationContext contexte = SpringApplication.run(SpringJpaApplication2.class, args);
        // Récupérer une implémentation de l'interface "ProduitRepository" par injection de dépendance
        produitMetier = contexte.getBean(ProduitMetierInterface.class);


        //Calculer le coût de vente de tous les produits en stock en appliquant la remise sur les produits en promotion
        System.out.println("-Calculer le coût de vente de tous les produits en stock en appliquant la remise sur les produits en promotion");
        System.out.println("Cout de vente du stock:"+produitMetier.calculerCoutVenteStock(50));

        System.out.println("********************Début**********************");
        System.out.println("***********Produit Moind Cher************");
        Produit pmcher = produitMetier.trouverCoutVenteMoinsCher();
        System.out.println(pmcher);
        System.out.println("********************Début**********************");
        System.out.println("***********Produit Plus Cher************");
        Produit ppcher = produitMetier.trouverCoutVentePlusCher();
        System.out.println(ppcher);

    }
    }
