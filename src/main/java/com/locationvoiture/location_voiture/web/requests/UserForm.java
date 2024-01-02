package com.locationvoiture.location_voiture.web.requests;

import java.util.List;

import com.locationvoiture.location_voiture.dao.entities.RoleUser;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {

    private Long id;

    @Column(length = 20)
    @NotEmpty(message = "User name  est requise")
    private String username;
    @Column(length = 20)
    @NotEmpty(message = "Email  est requise")
    private String email;
    @Column(length =100)
    @NotEmpty(message = "Password est requise")
    private String password;
    @Column(length = 20)
    @NotEmpty(message = "Address  est requise")
    private String address;

    // @ElementCollection(fetch= FetchType.EAGER)
	// @CollectionTable(
	// 		name="role",
	// 		joinColumns = @JoinColumn(name="id")
	// 		)
	// @Column(name="role")
    private RoleUser role;

}
