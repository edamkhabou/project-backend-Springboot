package soa.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.entities.DetailsFactures;
import soa.repository.DetailFactureRepository;

import java.util.List;
@Service
public class DetailsFactureMetierImpl implements DetailsFactureInterface{
    @Autowired
    DetailFactureRepository detailsfactureRepository;

    @Override
    public void ajouterDetailsFactures(DetailsFactures f) {
        detailsfactureRepository.save(f);
    }

    @Override
    public List<DetailsFactures> listeFactures() {
        return detailsfactureRepository.findAll();
    }
}
