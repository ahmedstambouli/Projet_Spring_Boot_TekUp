package com.locationvoiture.location_voiture.business.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locationvoiture.location_voiture.business.services.IVoitureservices;
import com.locationvoiture.location_voiture.dao.entities.Voiture;
import com.locationvoiture.location_voiture.dao.repositories.VoitureRepositories;

@Service
public class VoitureservicesImp implements IVoitureservices {

    @Autowired
    VoitureRepositories voitureRepositories;

    @Override
    public Voiture addvoiture(Voiture v) {
       return voitureRepositories.save(v);
        
    }

    @Override
    public Voiture updatevoiture(Voiture v) {
        return voitureRepositories.save(v);
    }

    @Override
    public void deletevoiture(Long id) {
        voitureRepositories.deleteById(id);
    }

    @Override
    public List<Voiture> getallvoiture() {
        return voitureRepositories.findAll();
    }

    @Override
    public Optional<Voiture> getvoiturebyid(Long id) {
        return voitureRepositories.findById(id);
    }

    @Override
    public List<Voiture> getvoitureparetat(boolean etat) {
        return voitureRepositories.findByEtat(etat);
    }

   
    
}
