package com.testsecurity.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	private String identificacion;

    @Column( name = "fechaNacimiento" )
	private Date  fechaNacimiento;

    @Column(name = "state", length = 1)
	private String state;

    
}