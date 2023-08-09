package com.gestion.reservas_hotel.model.repositoy;

import com.gestion.reservas_hotel.model.entities.HotelEntity;
import com.gestion.reservas_hotel.model.entities.ReservasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservaRepository extends JpaRepository<ReservasEntity,Integer> {
    List<ReservasEntity> findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqualAndHotel
            (Date fechaInicio, Date fechaFin, HotelEntity hotelId);

    List<ReservasEntity> findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual
            (Date fechaInicio, Date fechaFin);

}
