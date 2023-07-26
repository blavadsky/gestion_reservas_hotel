package com.gestion.reservas_hotel.service.interfaces;

import com.gestion.reservas_hotel.model.TipoDocumento;
import com.gestion.reservas_hotel.model.UsuarioRol;
import com.gestion.reservas_hotel.model.entities.UsuarioRolEntity;
import com.gestion.reservas_hotel.web.dto.UsuarioDTO;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

@Transactional
public interface UsuarioService {

    UserDetailsService userDetailsService();
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO, Set<UsuarioRolEntity> usuarioRoles) throws Exception;
    UsuarioDTO obtenerUsuario(Integer numeroDocumentoUsuario);
    boolean eliminarUsuario(Integer numeroDocumentoUsuario);
    UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO);
}
