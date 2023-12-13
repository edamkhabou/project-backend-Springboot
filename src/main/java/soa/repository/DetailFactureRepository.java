package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soa.entities.DetailsFactures;
import soa.entities.Produit;

public interface DetailFactureRepository  extends JpaRepository<DetailsFactures, Long> {
}
