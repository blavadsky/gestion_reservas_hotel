package com.gestion.reservas_hotel.security.service.interfaces;

import com.gestion.reservas_hotel.model.UsuarioRol;
import jakarta.jws.soap.SOAPBinding;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

}
