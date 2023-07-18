package com.gestion.reservas_hotel.model.repositoy;

import com.gestion.reservas_hotel.model.entities.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity,Integer> {

}
