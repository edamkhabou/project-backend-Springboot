package soa.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import soa.entities.Facture;

public class FactureDaoImpl implements IFactureDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Facture save(Facture f) {
        entityManager.persist(f);
        return f;
    }

    @Override
    public List<Facture> findAll() {
        return entityManager.createQuery("SELECT f FROM Facture f", Facture.class).getResultList();
    }

    @Override
    public Facture findOne(Long id) {
        return entityManager.find(Facture.class, id);
    }

    @Override
    @Transactional
    public Facture update(Facture f) {
        return entityManager.merge(f);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Facture f = findOne(id);
        if (f != null) {
            entityManager.remove(f);
        }
    }
}