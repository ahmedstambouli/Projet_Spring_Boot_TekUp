package com.locationvoiture.location_voiture.business.services;

import java.util.List;
import java.util.Optional;

import com.locationvoiture.location_voiture.dao.entities.Reservation;

public interface IReservation  {
    public Reservation addReservation(Reservation R,Long id);
    public Reservation updateReservation(Reservation R);
    public void deleteReservation (Long id);
    public List<Reservation> getallReservation();
    public Optional<Reservation> getReservationbyid(Long id);
}
