package com.locationvoiture.location_voiture.web.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.locationvoiture.location_voiture.business.services.IVoitureservices;
import com.locationvoiture.location_voiture.dao.entities.Voiture;
import com.locationvoiture.location_voiture.web.requests.VoitureForm;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Voiture")

public class VoitureControllers {

    @Autowired
    IVoitureservices iVoitureservices;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";

   
    @GetMapping()
    public String getVoiture(Model model) {
        List<Voiture> voitures = iVoitureservices.getallvoiture();
        
        model.addAttribute("voitures", voitures);

        return "index";
        
    }


   

    @GetMapping("/addvoiture")
    public String addVoiturForm(Model model) {
        model.addAttribute("voitureForm", new Voiture());
        return "create";
    }

    // methode ajouter voiture
    @PostMapping("/addvoiture")
    public String addvoiture(@Valid @ModelAttribute("voitureForm") VoitureForm voitureForm, BindingResult bindingResult,
            @RequestParam("image") MultipartFile file) throws IOException {

        if (bindingResult.hasErrors()) {
            return "create";
        } else {
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            iVoitureservices.addvoiture(new Voiture(voitureForm.getId(), voitureForm.getImmatriculation(),
                    voitureForm.getModele(), voitureForm.getMarque(), voitureForm.getEtat(), fileNames.toString()));
            return "redirect:/Voiture";

        }

    }

    @GetMapping("/{id}/edit")
    public String showEditVoiture(@PathVariable("id") Long id, Model model) {
        Optional<Voiture> voiture = iVoitureservices.getvoiturebyid(id);

        if (voiture != null) {
            VoitureForm voitureForm = new VoitureForm(voiture.get().getId(), voiture.get().getImmatriculation(),
                    voiture.get().getModele(), voiture.get().getMarque(), true);

            model.addAttribute("voitureForm", voitureForm);
            model.addAttribute("voitureId", id);
            model.addAttribute("existingImages", voiture.get().getImage());

        }
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String editProduct(@PathVariable("id") long id,
            @Valid @ModelAttribute("voitureForm") VoitureForm voitureForm, BindingResult bindingResult,
            @RequestParam("image") MultipartFile file) throws IOException {
        // if (bindingResult.hasErrors()) {
        //     System.out.println("oyyy warneg");
        //     return "edit";
        // }
        Optional<Voiture> optionalVoiture = iVoitureservices.getvoiturebyid(id);

        if (optionalVoiture.isPresent()) {
            Voiture voiture = optionalVoiture.get();
            voiture.setId(id);
            voiture.setImmatriculation(voitureForm.getImmatriculation());
            voiture.setMarque(voitureForm.getMarque());
            voiture.setModele(voitureForm.getModele());

            //image
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY,file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            voiture.setImage(fileNames.toString());

            System.out.println(voiture);

            iVoitureservices.updatevoiture(voiture);
        }
        
        return "redirect:/products";
    }


    // methode supprimer voiture
    @PostMapping("/deletevoiture/{id}")
    public String deleteVoiture(@PathVariable("id") Long id) {

        Optional<Voiture> voiture = iVoitureservices.getvoiturebyid(id);

        if (voiture.isPresent()) {
            iVoitureservices.deletevoiture(voiture.get().getId());
            new ResponseEntity<>("voiture is deleted successfully", HttpStatus.OK);
            return "redirect:/Voiture";
        }
        new ResponseEntity<>("Failed: voiture not found", HttpStatus.NOT_FOUND);
        return "redirect:/Voiture";
    }

    // private Voiture findProductById(Long id) {
    //     List<Voiture> voitures = iVoitureservices.getallvoiture();

    //     for (Voiture voiture : voitures) {
    //         if (voiture.getId().equals(id))
    //             return voiture;
    //     }
    //     return null;
    // }
}
