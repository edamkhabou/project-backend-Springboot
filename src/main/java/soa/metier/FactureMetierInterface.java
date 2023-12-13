package soa.metier;
import soa.entities.Facture;

import java.util.List;

public interface FactureMetierInterface {
    void ajouterFacture(Facture f);
    List<Facture> listeFactures();
}
