package com.gestion.reservas_hotel.model.repositoy;


import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Integer> {

    Optional<UsuarioEntity> findByCorreoElectronico(String email);
    Optional<UsuarioEntity> findByNumeroDocumento(Integer numeroDocumento);


}
