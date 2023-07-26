package com.gestion.reservas_hotel.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class RolEntity {

    @Id
    private Long rolId;
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol" )
    private Set<UsuarioRolEntity> usuarioRoles = new HashSet<>();
}
