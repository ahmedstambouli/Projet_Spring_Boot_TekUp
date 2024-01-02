package com.locationvoiture.location_voiture.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locationvoiture.location_voiture.dao.entities.Voiture;
import java.util.List;


public interface VoitureRepositories extends JpaRepository<Voiture,Long> {


    List<Voiture> findByImmatriculation(String immatriculation);

    List<Voiture> findByEtat(Boolean etat);

    
    
 
    
}
