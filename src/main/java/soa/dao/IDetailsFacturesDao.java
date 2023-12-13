package soa.dao;

import soa.entities.DetailsFactures;

import java.util.List;

public interface IDetailsFacturesDao {
    DetailsFactures save(DetailsFactures detailsFactures);
    List<DetailsFactures> findAll();
    DetailsFactures findOne(Long id);
    DetailsFactures update(DetailsFactures detailsFactures);
    void delete(Long id);

}