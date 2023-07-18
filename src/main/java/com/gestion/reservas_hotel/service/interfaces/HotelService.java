package com.gestion.reservas_hotel.service.interfaces;

import com.gestion.reservas_hotel.web.dto.HotelDTO;
import jakarta.transaction.Transactional;

public interface HotelService {
    @Transactional
    HotelDTO createHotel(HotelDTO hotelDTO);

    HotelDTO getHotel(Integer idHotel);
    boolean deleteHotel(Integer idHotel);
    HotelDTO updateHotel(HotelDTO hotelDTO);

}
