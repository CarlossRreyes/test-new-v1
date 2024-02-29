package com.app.test.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table( name = "tb_usuario")
public class Usuario {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, name = "id")	
	private Integer id;

    @ManyToOne
    @Valid
    @JoinColumn(name = "id_persona", nullable = true)
    private Persona person;

    @Column(name = "username", length=50)
    @Pattern(regexp = "^(?=.*[0-9]).*$", message = "El nombre de usuario debe contener al menos un número")
    @Pattern( regexp = ".*[A-Z].*", message = "El nombre de usuario debe tener al menos una letra mayúscula")
    @Size( min = 8, message = "El nombre de usuario debe de tener minimo 8 caracteres")
    @Size( max = 20, message = "El nombre de usuario debe de tener maximo 20 caracteres")
	private String username;

    @Column(name = "email", length=120)
	private String email;

    @Column(name = "password", length = 100) 
    @Size( min = 8, message = "La contraseña debe de tener al menos 8 caracteres")
    @Pattern( regexp = ".*[A-Z].*", message = "La contraseña debe tener al menos una letra mayúscula")
    @Pattern(regexp = "^(?!.*\\s).*$", message = "La contraseña no puede contener espacios")
    @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).*$", message = "La contraseña debe contener al menos un signo")
	private String password;

    @Column(name = "state", length = 1)
	private String state;
    
}
