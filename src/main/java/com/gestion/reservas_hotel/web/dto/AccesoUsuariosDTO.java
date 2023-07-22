package com.gestion.reservas_hotel.web.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class AccesoUsuariosDTO {
    private Long id;
    private String nombreUsuario;
    private String contrasena;
}
