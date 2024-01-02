package com.locationvoiture.location_voiture.web.controllers;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import com.locationvoiture.location_voiture.business.services.IUserservice;
import com.locationvoiture.location_voiture.dao.entities.User;
import com.locationvoiture.location_voiture.web.requests.UserForm;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping("/User")
@Controller
public class UserControllers {

    @Autowired
    IUserservice iUserservice;

   @GetMapping()
    public String getVoiture(Model model) {
        List<User> users = iUserservice.getalluser();
        model.addAttribute("Users", users);
        return "listusers";
    }

    @GetMapping("/addUser")
    public String addUserForms(Model model) {
        model.addAttribute("userForm", new User());
        return "createUser";
    }

    //methode ajouter Utilisateur
    @PostMapping("/addUser")
    public String addUser(@Valid @ModelAttribute("UserForm") UserForm userForm,BindingResult bindingResult )
    {
        if (bindingResult.hasErrors()) {
            return "createUser";
            
        }
        else{
            iUserservice.adduser(new User(userForm.getId(), userForm.getUsername(), userForm.getEmail(), userForm.getPassword(), userForm.getAddress(), userForm.getRole()));
            return "redirect:/User";
        }
    }


    //methode modifier Utilisateur
    @GetMapping("/{id}/edituser")
    public String showEditUser(@PathVariable("id") Long id, Model model) {
        Optional<User> user = iUserservice.getuserbyid(id);

        if (user != null) {
            UserForm userForm   = new UserForm(user.get().getId(), user.get().getUsername(), user.get().getEmail(), user.get().getPassword(), user.get().getAddress(), user.get().getRole());

            model.addAttribute("userForm", userForm);
            model.addAttribute("UserId", id);

        }
        return "editUser";
    }
   @PostMapping("/{id}/edituser")
    public String editUser(@PathVariable("id") long id,
            @Valid @ModelAttribute("UserForm") UserForm userForm, BindingResult bindingResult
           )  {
        if (bindingResult.hasErrors()) {
        
            return "editUser";
        }
        Optional<User> optionalUsers = iUserservice.getuserbyid(id);

        if (optionalUsers.isPresent()) {
            User usere = optionalUsers.get();
            usere.setId(id);
            usere.setUsername(userForm.getUsername());
            usere.setEmail(userForm.getEmail());
            usere.setPassword(userForm.getPassword());
            usere.setAddress(userForm.getAddress());
            usere.setRole(userForm.getRole());

            iUserservice.updateusere(usere);
        }
        
        return "redirect:/User";
    }



    //supprimer Utilisateur
    @GetMapping("/deleteUser/{id}")
    public String deleteVoiture(@PathVariable("id") Long id) {

        Optional<User> user = iUserservice.getuserbyid(id);

        if (user.isPresent()) {
            iUserservice.deleteuser(user.get().getId());
            new ResponseEntity<>("voiture is deleted successfully", HttpStatus.OK);
            return "redirect:/User";
        }
        new ResponseEntity<>("Failed: voiture not found", HttpStatus.NOT_FOUND);
        return "redirect:/User";
    }
    


  
    
}
