package soa.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import soa.entities.DetailsFactures;
@Repository
@Transactional
public class DetailsFacturesDaoImpl implements IDetailsFacturesDao {

    @PersistenceContext
    private EntityManager em;

    public DetailsFactures save(DetailsFactures detailsFactures) {
        em.persist(detailsFactures);
        return detailsFactures;
    }

    public List<DetailsFactures> findAll() {
        Query query = em.createQuery("select df from DetailsFactures df order by df.id");
        return query.getResultList();
    }

    public DetailsFactures findOne(Long id) {
        DetailsFactures detailsFactures = em.find(DetailsFactures.class, id);
        return detailsFactures;
    }

    public DetailsFactures update(DetailsFactures detailsFactures) {
        em.merge(detailsFactures);
        return detailsFactures;
    }

    public void delete(Long id) {
        DetailsFactures detailsFactures = em.find(DetailsFactures.class, id);
        em.remove(detailsFactures);
    }
}
