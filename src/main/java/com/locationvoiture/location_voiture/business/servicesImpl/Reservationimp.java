package com.locationvoiture.location_voiture.business.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locationvoiture.location_voiture.business.services.IReservation;
import com.locationvoiture.location_voiture.dao.entities.Reservation;
import com.locationvoiture.location_voiture.dao.entities.Voiture;
import com.locationvoiture.location_voiture.dao.repositories.ReservationRepositories;
import com.locationvoiture.location_voiture.dao.repositories.VoitureRepositories;


@Service
public class Reservationimp implements IReservation {


    @Autowired
    private ReservationRepositories reservationRepositories;
    @Autowired
    private VoitureRepositories voitureRepositories;
    

    @Override
    public Reservation addReservation(Reservation R,Long id) {
        Optional< Voiture >voiture = voitureRepositories.findById(id);
        voiture.get().setEtat(true);
        voitureRepositories.save(voiture.get());
        return reservationRepositories.save(R);
    }

    @Override
    public Reservation updateReservation(Reservation R) {
        return reservationRepositories.save(R);
    }

    @Override
    public void deleteReservation(Long id) {
       reservationRepositories.deleteById(id);
    }

    @Override
    public List<Reservation> getallReservation() {
        return reservationRepositories.findAll();
    }

    @Override
    public Optional<Reservation> getReservationbyid(Long id) {
        return reservationRepositories.findById(id);
    }
     
}
