package com.locationvoiture.location_voiture.business.services;

import java.util.List;
import java.util.Optional;

import com.locationvoiture.location_voiture.dao.entities.User;

public interface IUserservice {
     public User adduser(User u);
    public User updateusere(User u);
    public void deleteuser (Long id);
    public List<User> getalluser();
    public Optional<User> getuserbyid(Long id);
}
