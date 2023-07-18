package com.gestion.reservas_hotel.service.interfaces;

import com.gestion.reservas_hotel.model.TipoDocumento;
import com.gestion.reservas_hotel.web.dto.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO obtenerUsuario(String numeroDocumentoUsuario);
    boolean eliminarUsuario(String numeroDocumentoUsuario);
    UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO);
}
