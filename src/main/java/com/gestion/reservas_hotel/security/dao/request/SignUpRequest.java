package com.gestion.reservas_hotel.security.dao.request;


import com.gestion.reservas_hotel.model.UsuarioRol;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    private String nombre;
    private String apellidos;
    private String correoElectronico;
    private String contrasena;
    private String telefono;
    private Integer numeroDocumento;
    @Builder.Default
    private UsuarioRol usuarioRol = UsuarioRol.ADMIN;
}
