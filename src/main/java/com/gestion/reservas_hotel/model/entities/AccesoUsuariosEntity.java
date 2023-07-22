package com.gestion.reservas_hotel.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "AccesoUsuarios")
public class AccesoUsuariosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "nombre_usuario")
    private UsuarioEntity nombreUsuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @OneToOne
    @JoinColumn(name = "numero_documento_usuario")
    private UsuarioEntity usuario;

}
