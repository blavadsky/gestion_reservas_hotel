package com.gestion.reservas_hotel.service.implementations;

import com.gestion.reservas_hotel.model.entities.HotelEntity;
import com.gestion.reservas_hotel.model.entities.ReservasEntity;
import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import com.gestion.reservas_hotel.model.repositoy.HotelRepository;
import com.gestion.reservas_hotel.model.repositoy.ReservaRepository;
//import com.gestion.reservas_hotel.service.interfaces.HabitacionesService;
import com.gestion.reservas_hotel.model.repositoy.UsuarioRepository;
import com.gestion.reservas_hotel.security.dao.request.ReservaRequest;
import com.gestion.reservas_hotel.service.interfaces.ReservaService;
import com.gestion.reservas_hotel.web.dto.HotelDTO;
import com.gestion.reservas_hotel.web.dto.ReservaDTO;
import com.gestion.reservas_hotel.web.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public List<ReservaDTO> reservasGuardadasPorFecha(ReservaRequest reservaRequest) {
        List<ReservasEntity> reservas = reservaRepository.findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual
                (reservaRequest.getFechaInicio(), reservaRequest.getFechaFin());
        return reservas.stream().map(reservasEntity -> modelMapper.map(reservasEntity, ReservaDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<ReservaDTO> listarReservas() {
        List<ReservasEntity> reservas = reservaRepository.findAll();
        return reservas.stream()
                .map(reservasEntity -> modelMapper.map(reservasEntity, ReservaDTO.class))
                .collect(Collectors.toList());
    }


    public List<ReservasEntity> obtenerReservasPorUsuario(String correoElectronico) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findByCorreoElectronico(correoElectronico);
        return usuario.get().getReservas();
    }

    @Override
    public ReservaDTO crearReserva(Integer hotelId, ReservaDTO reservaDTO) {
        HotelEntity hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new BadRequestException("No se encontró un hotel con el ID: " + hotelId));
//        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
//                .orElseThrow(() -> new BadRequestException("No se encontró un usuario con el ID: " + usuarioId));

        List<ReservasEntity> reservasExistenteEnHotel = reservaRepository
                .findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqualAndHotel(
                        reservaDTO.getFechaFin(), reservaDTO.getFechaInicio(), hotel);

        if (((hotel.getNumeroHabitacionesHotel()) - (reservasExistenteEnHotel.stream()
                .mapToInt(ReservasEntity::getCapacidadHotel)
                .sum()) - reservaDTO.getCapacidadHotel()) >= 0) {
            ReservasEntity reserva = modelMapper.map(reservaDTO, ReservasEntity.class);
            reserva.setHotel(hotel);
            reserva.setUsuario(usuarioRepository.findByCorreoElectronico(reservaDTO.getUsuario()).orElseThrow(() ->
                    new BadRequestException("No se encontró usuario con Correo: " + reservaDTO.getUsuario())));
            return modelMapper.map(reservaRepository.save(reserva), ReservaDTO.class);
        }
        throw new BadRequestException("No hay capacidad para la fecha seleccionada.");
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

