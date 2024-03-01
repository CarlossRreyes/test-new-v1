package com.testsecurity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "tb_permiso" )
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id_permiso")
    private Integer id_permiso;

    @ManyToOne
    @JoinColumn(name = "id_opcion", nullable = true)
    private Opcion opcion;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = true)
    private Rol rol;
    
    @Column(name = "state", length = 1)
    private String state;
    
}
