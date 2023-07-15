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
    public HotelDTO createHotel(HotelDTO libroDTO) {
        HotelEntity libroEntity = modelMapper.map(libroDTO, HotelEntity.class);
        libroEntity = hotelRepository.save(libroEntity);
        return modelMapper.map(libroEntity, HotelDTO.class);
    }

    @Override
    public HotelDTO getHotel(Integer id) {
        HotelEntity bookEntity = hotelRepository.findById(id).get();
        return modelMapper.map(bookEntity, HotelDTO.class);
    }

    @Override
    public boolean deleteHotel(Integer id) {
        return false;
    }

    @Override
    public HotelDTO updateHotel(HotelDTO libroDTO) {
        return null;
    }
}
