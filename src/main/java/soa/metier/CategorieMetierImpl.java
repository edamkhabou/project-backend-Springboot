package soa.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.entities.Categorie;
import soa.repository.CategorieRepository;
@Service
public class CategorieMetierImpl implements CategorieMetierInterface
{
    @Autowired
    private CategorieRepository categorieRepository;
    @Override
    public void ajouterCategorie(Categorie c)
    {
        categorieRepository.save(c);
    }

}
