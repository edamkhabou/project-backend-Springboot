package soa.metier;

import soa.entities.DetailsFactures;
import soa.entities.Facture;

import java.util.List;

public interface DetailsFactureInterface {

    void ajouterDetailsFactures(DetailsFactures f);

    List<DetailsFactures> listeFactures();
}