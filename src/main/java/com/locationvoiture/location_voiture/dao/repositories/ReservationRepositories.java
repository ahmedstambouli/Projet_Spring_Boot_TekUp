package com.locationvoiture.location_voiture.dao.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.locationvoiture.location_voiture.dao.entities.Reservation;
import com.locationvoiture.location_voiture.dao.entities.Voiture;

import java.util.List;


public interface ReservationRepositories extends JpaRepository<Reservation,Long> {

    List<Reservation> findByVoiture(Voiture voiture);
    
}
