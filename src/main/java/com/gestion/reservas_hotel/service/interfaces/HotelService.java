package com.gestion.reservas_hotel.service.interfaces;

import com.gestion.reservas_hotel.web.dto.HotelDTO;
import jakarta.transaction.Transactional;

public interface HotelService {
    @Transactional
    HotelDTO createHotel(HotelDTO libroDTO);

    HotelDTO getHotel(Integer id);
    boolean deleteHotel(Integer id);
    HotelDTO updateHotel(HotelDTO libroDTO);

}
