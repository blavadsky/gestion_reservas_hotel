package com.gestion.reservas_hotel.model.repositoy;


import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import com.gestion.reservas_hotel.web.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Integer> {

    public UsuarioDTO findByNombreUsuario(String nombreUsuario);

    Optional<UsuarioEntity> findByEmail(String email);
}
