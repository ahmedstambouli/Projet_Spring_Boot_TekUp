package com.locationvoiture.location_voiture.dao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.locationvoiture.location_voiture.dao.entities.User;


public interface UserRepositories extends JpaRepository<User,Long> {
    User findByEmail(String email);


}
