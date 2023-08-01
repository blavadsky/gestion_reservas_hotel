package com.gestion.reservas_hotel.service.implementations;


import com.gestion.reservas_hotel.model.entities.HotelEntity;
import com.gestion.reservas_hotel.model.repositoy.HotelRepository;
import com.gestion.reservas_hotel.security.dao.response.JwtAuthenticationResponse;
import com.gestion.reservas_hotel.security.service.interfaces.JwtService;
import com.gestion.reservas_hotel.service.interfaces.HotelService;
import com.gestion.reservas_hotel.web.dto.HotelDTO;
import com.gestion.reservas_hotel.web.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final JwtHotelImpl jwtService;


    @Override
    public JwtAuthenticationResponse createHotel(HotelDTO hotelDTO) {
        var hotel = HotelEntity.builder().idHotel(hotelDTO.getIdHotel()).nombreHotel(hotelDTO.getNombreHotel())
                .telefonoHotel(hotelDTO.getTelefonoHotel()).direccionCorreoHotel(hotelDTO.getDireccionCorreoHotel())
                .numeroHabitacionesHotel(hotelDTO.getNumeroHabitacionesHotel()).build();

        hotelRepository.save(hotel);
        var jwt = jwtService.generateToken(hotel);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public HotelDTO getHotel(Integer id) {
        HotelEntity hotelEntity = hotelRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("No se encontró un hotel con el ID: " + id));
        return modelMapper.map(hotelEntity, HotelDTO.class);
    }

//        String nombreHotel = hotelDTO.getNombreHotel();
//        boolean existeHotel = hotelRepository.findAll().stream()
//                .anyMatch(hotel -> hotel.getNombreHotel().equalsIgnoreCase(nombreHotel));
//        if (existeHotel == false) {
//            HotelEntity hotelEntity = modelMapper.map(hotelDTO, HotelEntity.class);
//            hotelEntity = hotelRepository.save(hotelEntity);
//            return modelMapper.map(hotelEntity, HotelDTO.class);
//        } else {
//            throw new BadRequestException("Ya existe un hotel con el nombre "+ hotelDTO.getNombreHotel());}
//




    @Override
    public boolean deleteHotel(Integer id) {
        HotelEntity hotelEntity = hotelRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("No se encontró un hotel con el ID: "+ id));
        hotelRepository.delete(hotelEntity);
        return true;
    }

    @Override
    public HotelDTO updateHotel(HotelDTO hotelDTO) {
        return hotelRepository.findById(hotelDTO.getIdHotel())
                .map(hotelEntity -> {
                    hotelEntity.setNombreHotel(hotelDTO.getNombreHotel());
                    hotelEntity.setTelefonoHotel(hotelDTO.getTelefonoHotel());
                    hotelEntity.setDireccionCorreoHotel(hotelDTO.getDireccionCorreoHotel());
                    hotelEntity.setNumeroHabitacionesHotel(hotelDTO.getNumeroHabitacionesHotel());
                    hotelEntity = hotelRepository.save(hotelEntity);
                    return modelMapper.map(hotelEntity, HotelDTO.class);
                })
                .orElseThrow(() -> new BadRequestException("No se ha encontrado un hotel con id " + hotelDTO.getIdHotel()));
    }

    @Override
    public List<HotelDTO> listarHoteles() {
        List<HotelEntity> hoteles = hotelRepository.findAll();
        return hoteles.stream()
                .map(hotelEntity -> modelMapper.map(hotelEntity, HotelDTO.class))
                .collect(Collectors.toList());
    }

}

