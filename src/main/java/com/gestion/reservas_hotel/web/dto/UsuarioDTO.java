package com.gestion.reservas_hotel.web.dto;


import com.gestion.reservas_hotel.model.TipoDocumento;
import com.gestion.reservas_hotel.model.UsuarioRol;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class UsuarioDTO {
    private String nombreUsuario;
    private String nombre;
    private String apellidos;
    private String telefonoUsuario;
    private String correoElectronico;
    private Integer numeroDocumentoUsuario;
    private TipoDocumento tipoDocumento;

    @Builder.Default
    private UsuarioRol usuarioRol = UsuarioRol.NORMAL;
}
