package com.locationvoiture.location_voiture.web.requests;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class VoitureForm {
    
    private Long id;

    @Column(name = "immatriculation" )
    @NotEmpty(message = "L'immatriculation est requise")
    private String immatriculation;

    @Column(length =20)
    @NotBlank(message = "remplire modele de voitur")
    private String modele;

    @Column(length =20)
    @NotBlank(message = "remplire marque de voitur")
    private String marque;

    private Boolean etat=false;

    private MultipartFile  image;


    public VoitureForm(Long id, String immatriculation, String modele, String marque, boolean etat) {
        this.id = id;
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.marque = marque;
        this.etat = etat;
    }
}
