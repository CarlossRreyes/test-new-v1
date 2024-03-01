package com.testsecurity.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@Entity
@Table( name = "tb_usuario")
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, name = "id")	
	private Integer id;

    @ManyToOne
    @Valid
    @JoinColumn(name = "id_persona", nullable = true)
    private Persona person;

    @ManyToOne
    @Valid
    @JoinColumn(name = "id_rol", nullable = true)
    private Rol rol;

    @Column(name = "username", length=50)
	private String username;

    @Column(name = "email", length=120)
	private String email;

    @Column(name = "password", length = 100) 
	private String password;

    @Column(name = "state", length = 1)
	private String state;
    
}
