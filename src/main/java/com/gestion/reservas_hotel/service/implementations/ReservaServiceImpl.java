package com.gestion.reservas_hotel.service.implementations;

import com.gestion.reservas_hotel.model.entities.HotelEntity;
import com.gestion.reservas_hotel.model.entities.ReservasEntity;
import com.gestion.reservas_hotel.model.repositoy.ReservaRepository;
import com.gestion.reservas_hotel.service.interfaces.ReservaService;
import com.gestion.reservas_hotel.web.dto.HotelDTO;
import com.gestion.reservas_hotel.web.dto.ReservaDTO;
import com.gestion.reservas_hotel.web.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ReservaDTO crearReserva(Integer hotelId, ReservaDTO reservaDTO) {
        ReservasEntity reservasEntity = modelMapper.map(reservaDTO, ReservasEntity.class);
        reservasEntity = reservaRepository.save(reservasEntity);
        return modelMapper.map(reservasEntity, ReservaDTO.class);
    }

    @Override
    public ReservaDTO obtenerReserva(Integer id) {
        ReservasEntity reservasEntity = reservaRepository.findById(id).get();
        return modelMapper.map(reservasEntity, ReservaDTO.class);
    }

    @Override
    public boolean eliminarReserva(Integer id) {
        ReservasEntity reservasEntity = reservaRepository.findById(id).orElse(null);
        reservaRepository.delete(reservasEntity);
        return true;
    }

    @Override
    public ReservaDTO actualizarReserva(ReservaDTO reservaDTO) {
        ReservasEntity reservasEntity = reservaRepository.findById(reservaDTO.getId()).orElse(null);
        if (reservasEntity != null) {
            reservasEntity.setFechaInicio(reservaDTO.getFechaInicio());
            reservasEntity.setFechaFin(reservaDTO.getFechaFin());
            reservasEntity = reservaRepository.save(reservasEntity);
            return modelMapper.map(reservasEntity, ReservaDTO.class);
        } else {
            throw new BadRequestException("No se ha encontrado una reserva en la fecha" + reservaDTO.getFechaInicio());
        }
    }
}

