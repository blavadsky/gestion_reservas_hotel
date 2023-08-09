package com.gestion.reservas_hotel.model.repositoy;


import com.gestion.reservas_hotel.model.UsuarioRol;
import com.gestion.reservas_hotel.model.entities.ReservasEntity;
import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import com.gestion.reservas_hotel.web.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Integer> {

    Optional<UsuarioEntity> findByCorreoElectronico(String email);
    Optional<UsuarioEntity> findByNumeroDocumento(Integer numeroDocumento);

    Optional<UsuarioEntity> findByCorreoElectronicoAndRol(String email, UsuarioRol usuarioRol);

}
