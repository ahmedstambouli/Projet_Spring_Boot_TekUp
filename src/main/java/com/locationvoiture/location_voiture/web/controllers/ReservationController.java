package com.locationvoiture.location_voiture.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.locationvoiture.location_voiture.business.services.IReservation;
import com.locationvoiture.location_voiture.business.services.IVoitureservices;
import com.locationvoiture.location_voiture.dao.entities.Reservation;
import com.locationvoiture.location_voiture.dao.entities.User;
import com.locationvoiture.location_voiture.dao.entities.Voiture;
import com.locationvoiture.location_voiture.web.requests.UserForm;
import com.locationvoiture.location_voiture.web.requests.VoitureForm;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RequestMapping("/Reservation")
@Controller
public class ReservationController {

    @Autowired
    private IReservation iReservation;

    @Autowired
    private IVoitureservices iVoitureservices;

    //  @GetMapping()
    // public String getVoiture(Model model) {
    //     List<Reservation> reservations = iReservation.getallReservation();
    //     model.addAttribute("Reservations", reservations);
    //     return "Reservations";
    // }


     @GetMapping()
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        //List<Voiture> voitures = iVoitureservices.getallvoiture();
        List <Voiture> voitures=iVoitureservices.getvoitureparetat(false); 
        
        model.addAttribute("voitures", voitures);
        return "Reservations"; 
    }

       // Enregistre une nouvelle réservation
    // @PostMapping("/reservation")
    // public String createReservation(@Valid @ModelAttribute("reservation") Reservation reservation,BindingResult bindingResult) {
       
    //    System.out.println("resrvations:"+reservation);
    //     iReservation.addReservation(reservation);
    //     return "redirect:/Reservation";
    // }




    @GetMapping("/reservation/{id}")
    public String showEditVoiture(@PathVariable("id") Long id, Model model) {
        Optional<Voiture> voiture = iVoitureservices.getvoiturebyid(id);
        Reservation reservation = new Reservation();
        reservation.setVoiture(voiture.get());

        // if (voiture != null) {
        //     VoitureForm voitureForm = new VoitureForm(voiture.get().getId(), voiture.get().getImmatriculation(),
        //             voiture.get().getModele(), voiture.get().getMarque(), true);

            //model.addAttribute("voitureForm", voitureForm);
            model.addAttribute("voitureId", id);
            model.addAttribute("reservation", reservation);

            //model.addAttribute("existingImages", voiture.get().getImage());

        //}
        return "listReservation";
    }

       @PostMapping("/reservation/{id}")
    public String createReservation(@Valid @ModelAttribute("reservation") Reservation reservation,BindingResult bindingResult,@PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "listReservation";
        }
        Optional<Voiture> voiture = iVoitureservices.getvoiturebyid(id);
        
        reservation.setVoiture(voiture.get());
       System.out.println("bbbb:"+reservation.getVoiture().getId());
        iReservation.addReservation(reservation,id);
        return "redirect:/Reservation";
    }

    
}
