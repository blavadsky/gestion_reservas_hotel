package com.gestion.reservas_hotel.model.repositoy;

import com.gestion.reservas_hotel.model.entities.ReservasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<ReservasEntity,Integer> {
}
