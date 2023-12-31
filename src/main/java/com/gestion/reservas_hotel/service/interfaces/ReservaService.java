package com.gestion.reservas_hotel.service.interfaces;

import com.gestion.reservas_hotel.security.dao.request.ReservaRequest;
import com.gestion.reservas_hotel.web.dto.ReservaDTO;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface ReservaService {
    List<ReservaDTO> listarReservas();
    List<ReservaDTO> obtenerReservasPorUsuario(String correoElectronico);
    List<ReservaDTO> reservasGuardadasPorFecha(ReservaRequest reservaRequest);
    ReservaDTO crearReserva(Integer hotelId, ReservaDTO reservaDTO);
    ReservaDTO obtenerReserva(Integer id);
    boolean eliminarReserva(Integer id);
    ReservaDTO actualizarReserva(ReservaDTO reservaDTO);

}
