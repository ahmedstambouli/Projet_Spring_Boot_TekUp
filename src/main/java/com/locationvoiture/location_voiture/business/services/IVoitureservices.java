package com.locationvoiture.location_voiture.business.services;

import java.util.List;
import java.util.Optional;

import com.locationvoiture.location_voiture.dao.entities.Voiture;

public interface IVoitureservices {
    
    public Voiture addvoiture(Voiture v);
    public Voiture updatevoiture(Voiture v);
    public void deletevoiture (Long id);
    public List<Voiture> getallvoiture();
    public List<Voiture> getvoitureparetat(boolean etat);

    public Optional<Voiture> getvoiturebyid(Long id);


    
}
