package com.gestion.reservas_hotel.service.interfaces;

import com.gestion.reservas_hotel.model.TipoDocumento;
import com.gestion.reservas_hotel.web.dto.UsuarioDTO;
import jakarta.transaction.Transactional;

@Transactional
public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO obtenerUsuario(Integer numeroDocumentoUsuario);
    boolean eliminarUsuario(Integer numeroDocumentoUsuario);
    UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO);
}
