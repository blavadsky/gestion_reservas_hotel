package com.gestion.reservas_hotel.model.repositoy;


import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Integer> {
}
