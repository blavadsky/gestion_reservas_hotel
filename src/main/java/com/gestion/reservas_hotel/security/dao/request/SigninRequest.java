package com.gestion.reservas_hotel.security.dao.request;


import com.gestion.reservas_hotel.model.UsuarioRol;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {
    private String correoElectronico;
    private String contrasena;
    private UsuarioRol usuarioRol;
}
