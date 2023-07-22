package com.gestion.reservas_hotel.model.entities;


import com.gestion.reservas_hotel.model.TipoDocumento;
import com.gestion.reservas_hotel.model.UsuarioRol;
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
    @Column(name = "numero_documento_usuario")
    private Integer numeroDocumentoUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "telefono_usuario")
    private String telefonoUsuario;

    @Column(name = "tipo_documento")
    private TipoDocumento tipoDocumento;

    @Column(name = "correo_electronico", nullable = false, unique = true)
    private String correoElectronico;

    @Column(name="usuario_rol")
    private UsuarioRol usuarioRol;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private AccesoUsuariosEntity accesoUsuario;

    //public UsuarioEntity() { this.usuarioRol = UsuarioRol.NORMAL; }

}
