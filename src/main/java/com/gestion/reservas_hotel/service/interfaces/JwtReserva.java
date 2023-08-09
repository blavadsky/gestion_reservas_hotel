package com.gestion.reservas_hotel.service.interfaces;

import com.gestion.reservas_hotel.model.entities.ReservasEntity;

public interface JwtReserva {
    String extractUserName(String token);

    String generateToken(ReservasEntity reservasEntity, Integer dias);

    boolean isTokenValid(String token, ReservasEntity reservasEntity);
}
