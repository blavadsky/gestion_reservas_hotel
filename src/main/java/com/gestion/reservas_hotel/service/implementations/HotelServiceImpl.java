package com.gestion.reservas_hotel.service.implementations;


import com.gestion.reservas_hotel.model.entities.HotelEntity;
import com.gestion.reservas_hotel.model.repositoy.HotelRepository;
import com.gestion.reservas_hotel.service.interfaces.HotelService;
import com.gestion.reservas_hotel.web.dto.HotelDTO;
import com.gestion.reservas_hotel.web.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        HotelEntity hotelEntity = modelMapper.map(hotelDTO, HotelEntity.class);
        hotelEntity = hotelRepository.save(hotelEntity);
        return modelMapper.map(hotelEntity, HotelDTO.class);
    }

    @Override
    public HotelDTO getHotel(Integer id) {
        HotelEntity hotelEntity = hotelRepository.findById(id).get();
        return modelMapper.map(hotelEntity, HotelDTO.class);
    }

    @Override
    public boolean deleteHotel(Integer id) {
        HotelEntity hotelEntity = hotelRepository.findById(id).orElse(null);
        hotelRepository.delete(hotelEntity);
        return true;
    }

    @Override
    public HotelDTO updateHotel(HotelDTO hotelDTO) {
        HotelEntity hotelEntity = hotelRepository.findById(hotelDTO.getIdHotel()).orElse(null);
        if (hotelEntity != null) {
            hotelEntity.setNombreHotel(hotelDTO.getNombreHotel());
            hotelEntity.setTelefonoHotel(hotelDTO.getTelefonoHotel());
            hotelEntity.setDireccionCorreoHotel(hotelDTO.getDireccionCorreoHotel());
            hotelEntity.setNumeroHabitacionesHotel(hotelDTO.getNumeroHabitacionesHotel());
            hotelEntity = hotelRepository.save(hotelEntity);
            return modelMapper.map(hotelEntity, HotelDTO.class);
        } else {
            throw new BadRequestException("No se ha encontrado un hotel con id" + hotelDTO.getIdHotel());
        }
    }

}

