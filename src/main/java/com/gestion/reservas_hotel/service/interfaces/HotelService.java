package com.gestion.reservas_hotel.service.interfaces;

import com.gestion.reservas_hotel.security.dao.response.JwtAuthenticationResponse;
import com.gestion.reservas_hotel.web.dto.HotelDTO;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface HotelService {

    JwtAuthenticationResponse createHotel(HotelDTO hotelDTO);
    HotelDTO getHotel(Integer idHotel);
    boolean deleteHotel(Integer idHotel);
    HotelDTO updateHotel(HotelDTO hotelDTO);
    List<HotelDTO> listarHoteles();
}
