package com.gestion.reservas_hotel.security.service.interfaces;

import com.gestion.reservas_hotel.model.entities.ReservasEntity;
import com.gestion.reservas_hotel.security.dao.request.SignUpRequest;
import com.gestion.reservas_hotel.security.dao.request.SigninRequest;
import com.gestion.reservas_hotel.security.dao.response.JwtAuthenticationResponse;

import java.util.List;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    List<ReservasEntity> listarReservas(Integer id);

}
