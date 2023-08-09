package com.gestion.reservas_hotel.service.interfaces;

import com.gestion.reservas_hotel.web.dto.UsuarioDTO;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;

@Transactional
public interface UsuarioService {

    UserDetailsService userDetailsService();

    UsuarioDTO obtenerUsuario(Integer numeroDocumentoUsuario);
    UsuarioDTO obtenerUsuario(String correoElectronico);
    boolean eliminarUsuario(Integer numeroDocumentoUsuario);
    UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO);
}
