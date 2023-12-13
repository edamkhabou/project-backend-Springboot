package soa.dao;

import soa.entities.Facture;

import java.util.List;

public interface IFactureDao
{
    Facture save (Facture f);
    List<Facture> findAll();
    Facture findOne(Long id);
    Facture update (Facture f);
    void delete (Long id);
}