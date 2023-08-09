package com.gestion.reservas_hotel.security.service.interfaces;

import com.gestion.reservas_hotel.model.UsuarioRol;
import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import jakarta.jws.soap.SOAPBinding;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);
    String generateToken(UserDetails userDetails, UsuarioRol usuarioRol);

    boolean isTokenValid(String token, UserDetails userDetails);

}
