 package com.locationvoiture.location_voiture.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


 @Configuration
 @EnableWebSecurity
 public class WebSecurityConfig {
	
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
        @Autowired
        private BCryptPasswordEncoder encoder;
	
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.csrf(c -> c.disable())
		
		.authorizeHttpRequests(request -> 
		request
				.requestMatchers("/register","/images/**","/saveUser","/Reservation/**","/Reservation/reservation").permitAll()
				.requestMatchers("/Voiture/**").hasAuthority("ADMIN")
				.anyRequest().authenticated())

		.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
				.successHandler(customSuccessHandler).permitAll())
		
		.logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").permitAll());
		
 		return http.build();
		
 	}
	
 	@Autowired
 	public void configure (AuthenticationManagerBuilder auth) throws Exception {
 		auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder);
 	}

 }
