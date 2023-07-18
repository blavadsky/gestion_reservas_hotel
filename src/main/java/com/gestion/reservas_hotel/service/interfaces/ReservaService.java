package com.gestion.reservas_hotel.service.interfaces;

import com.gestion.reservas_hotel.web.dto.ReservaDTO;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface ReservaService {
    ReservaDTO crearReserva(Integer hotelId, ReservaDTO reservaDTO);
    ReservaDTO obtenerReserva(Integer id);

    boolean eliminarReserva(Integer id);
    ReservaDTO actualizarReserva(ReservaDTO reservaDTO);

}
