package com.gestion.reservas_hotel.model.repositoy;

import com.gestion.reservas_hotel.model.entities.AccesoUsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccesoUsuarioRepository extends JpaRepository<AccesoUsuariosEntity,Integer> {
}
