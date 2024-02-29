package com.app.test.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table( name = "tb_persona")
public class Persona {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, name = "id_persona")	
	private Integer id_persona;

    @Column(name = "nombres", length=60)
	private String nombres;

    @Column(name = "apellidos", length=60)
	private String apellidos;

    @Column(name = "identificacion", length = 10)
	@Size(min = 10, max = 10, message = "La identificación debe tener exactamente 10 caracteres")
    @Pattern(regexp = "\\d+", message = "La identificación debe contener solo números")
	@Pattern(regexp = "(?!.*(\\d)\\1{3}).*", message = "La identificación no puede tener cuatro números seguidos")
	private String identificacion;

    @Column( name = "fechaNacimiento" )
	private Date  fechaNacimiento;

    @Column(name = "state", length = 1)
	private String state;

    
}
