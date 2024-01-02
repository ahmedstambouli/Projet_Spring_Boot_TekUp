package com.locationvoiture.location_voiture.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.locationvoiture.location_voiture.business.services.IUserservice;
import com.locationvoiture.location_voiture.business.servicesImpl.Userservicesimp;
import com.locationvoiture.location_voiture.dao.entities.User;
import com.locationvoiture.location_voiture.dao.repositories.UserRepositories;
import com.locationvoiture.location_voiture.web.requests.UserForm;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    IUserservice iUserservice;

@GetMapping("/login")
	public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // L'utilisateur est déjà connecté, rediriger vers une autre page
            return "redirect:/access-denied";
        }
        // Sinon, afficher la page de connexion
		return "login";
	}
    // // Go to Registration Page
    // @GetMapping("/register")
    // public String register() {
    //     return "register";
    // }

    // // Read Form data to save into DB
    // @PostMapping("/saveUser")
    // public String saveUser(
    //         @ModelAttribute User user,
    //         Model model) {
    //     User user2 = iUserservice.adduser(user);
    //     String message = "User '" + user2.getUsername() + "' saved successfully !";
    //     model.addAttribute("msg", message);
    //     return "register";
    // }


    
//   @GetMapping("/register")
//     public String addUserForms(Model model) {
//         model.addAttribute("userForm", new User());
//         return "register";
//     }

//     //methode ajouter Utilisateur
//     @PostMapping("/saveUser")
//     public String addUser(@Valid @ModelAttribute("UserForm") UserForm userForm,BindingResult bindingResult,Model model )
//     {
//         if (bindingResult.hasErrors()) {
//             return "register";
            
//         }
//         else{
//             iUserservice.adduser(new User(userForm.getId(), userForm.getUsername(), userForm.getEmail(), userForm.getPassword(), userForm.getAddress(), userForm.getRole()));
//            String message = "User '" + userForm.getUsername() + "' saved successfully !";
//          model.addAttribute("msg", message);
//          return "register";
//         }
//     }

@GetMapping("/register")
	public String getRegistrationPage(@ModelAttribute("user") UserForm userForm) {
		return "register";
	}
	
	@PostMapping("/register")
	public String saveUser(@Valid @ModelAttribute("user") UserForm userForm, Model model,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
            
        }
		iUserservice.adduser(new User(userForm.getId(), userForm.getUsername(), userForm.getEmail(), userForm.getPassword(), userForm.getAddress(), userForm.getRole()));
		model.addAttribute("message", "Registered Successfuly!");
		return "register";
	}

    
}
