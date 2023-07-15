package com.gestion.reservas_hotel.model.repositoy;

import com.gestion.reservas_hotel.model.entities.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<HotelEntity,Integer> {

}
