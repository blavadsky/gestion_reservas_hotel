package com.gestion.reservas_hotel.model.entities;


import com.gestion.reservas_hotel.model.TipoDocumento;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="usuarios")
public class UsuarioEntity {

    @Id
    private Integer numeroDocumentoUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "telefono_usuario")
    private String telefonoUsuario;

    @Column(name = "tipo_documento")
    private TipoDocumento tipoDocumento;

}
