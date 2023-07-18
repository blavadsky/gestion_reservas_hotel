package com.gestion.reservas_hotel.web.dto;


import com.gestion.reservas_hotel.model.TipoDocumento;
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
    private String telefonoUsuario;
    private Integer numeroDocumentoUsuario;
    private TipoDocumento tipoDocumento;
}
