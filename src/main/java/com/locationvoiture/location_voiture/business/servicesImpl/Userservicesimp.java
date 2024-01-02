package com.locationvoiture.location_voiture.business.servicesImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.locationvoiture.location_voiture.business.services.IUserservice;
import com.locationvoiture.location_voiture.dao.entities.User;
import com.locationvoiture.location_voiture.dao.repositories.UserRepositories;

@Service
public class Userservicesimp implements IUserservice,UserDetailsService {

    @Autowired
    private  UserRepositories userRepositories;

    

   @Autowired
 	private BCryptPasswordEncoder passwordEncoder;

  


    @Override
    public User adduser(User u) {
        String hashedPassword = passwordEncoder.encode(u.getPassword());
         u.setPassword(hashedPassword);
        return userRepositories.save(u);
    }

    @Override
    public User updateusere(User u) {
        return userRepositories.save(u); 
    }

    @Override
    public void deleteuser(Long id) {
         userRepositories.deleteById(id);
         
    }

    @Override
    public List<User> getalluser() {
        return userRepositories.findAll();
    }

    @Override
    public Optional<User> getuserbyid(Long id) {
        return userRepositories.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }






    // @Override
	// public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	// 	Optional<User> opt = userRepositories.findByUsername(email);
		
	// 	org.springframework.security.core.userdetails.User springUser=null;
		
	// 	if(opt.isEmpty()) {
	// 		throw new UsernameNotFoundException("User with email: " +email +" not found");
	// 	}
	// 		User user =opt.get();	
	// 		List<String> roles = user.getRole();
	// 		Set<GrantedAuthority> ga = new HashSet<>();
	// 		for(String role:roles) {
	// 			ga.add(new SimpleGrantedAuthority(role));
	// 		}
			
	// 		springUser = new org.springframework.security.core.userdetails.User(
	// 						email,
	// 						user.getPassword(),
	// 						ga );	
	// 	return springUser;	
	// }

   
    
}
