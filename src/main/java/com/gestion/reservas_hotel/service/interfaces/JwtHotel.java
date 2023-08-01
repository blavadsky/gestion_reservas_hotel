package com.gestion.reservas_hotel.service.interfaces;

import com.gestion.reservas_hotel.model.UsuarioRol;
import com.gestion.reservas_hotel.model.entities.HotelEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtHotel {

    String extractUserName(String token);


    String generateToken(HotelEntity hotelEntity);

    boolean isTokenValid(String token, HotelEntity hotelEntity);
}
