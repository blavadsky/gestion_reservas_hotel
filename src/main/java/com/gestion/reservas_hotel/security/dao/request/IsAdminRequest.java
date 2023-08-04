package com.gestion.reservas_hotel.security.dao.request;

import com.gestion.reservas_hotel.model.UsuarioRol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IsAdminRequest {
    private String correoElectronico;
    private UsuarioRol usuarioRol;
}
