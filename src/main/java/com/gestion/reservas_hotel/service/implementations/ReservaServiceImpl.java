package com.gestion.reservas_hotel.service.implementations;

import com.gestion.reservas_hotel.model.entities.HotelEntity;
import com.gestion.reservas_hotel.model.entities.ReservasEntity;
import com.gestion.reservas_hotel.model.repositoy.HotelRepository;
import com.gestion.reservas_hotel.model.repositoy.ReservaRepository;
import com.gestion.reservas_hotel.service.interfaces.ReservaService;
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
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ReservaDTO crearReserva(Integer hotelId, ReservaDTO reservaDTO) {
        HotelEntity hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new BadRequestException("No se encontró un hotel con el ID: " + hotelId));
        if (reservaDTO.getCapacidadHotel() <= hotel.getNumeroHabitacionesHotel()) {
            ReservasEntity reserva = modelMapper.map(reservaDTO, ReservasEntity.class);
            reserva = reservaRepository.save(reserva);
            hotel.setNumeroHabitacionesHotel(hotel.getNumeroHabitacionesHotel() - reservaDTO.getCapacidadHotel());
            hotelRepository.save(hotel);
            return modelMapper.map(reserva, ReservaDTO.class);
        } else {
            throw new BadRequestException("No hay suficientes habitaciones disponibles en el hotel.");
        }
    }

    @Override
    public ReservaDTO obtenerReserva(Integer id) {
        ReservasEntity reservasEntity = reservaRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("No se encontró una reserva con ID: "+id));
        return modelMapper.map(reservasEntity, ReservaDTO.class);
    }

    @Override
    public boolean eliminarReserva(Integer id) {
        ReservasEntity reservasEntity = reservaRepository.findById(id)
                        .orElseThrow(()-> new BadRequestException("No se encontró una reserva con ID: "+id));
        reservaRepository.delete(reservasEntity);
        return true;
    }

    @Override
    public ReservaDTO actualizarReserva(ReservaDTO reservaDTO) {
        return reservaRepository.findById(reservaDTO.getId())
                .map(reservasEntity -> {
                    reservasEntity.setFechaInicio(reservaDTO.getFechaInicio());
                    reservasEntity.setFechaFin(reservaDTO.getFechaFin());
                    reservasEntity = reservaRepository.save(reservasEntity);
                    return modelMapper.map(reservasEntity, ReservaDTO.class);
                })
                .orElseThrow(()-> new BadRequestException("No se encontró una reserva con ID:"+reservaDTO.getId()));
    }
}

