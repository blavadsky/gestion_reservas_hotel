package com.gestion.reservas_hotel.security.dao.request;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {
    private String correoElectronico;
    private String contrasena;
}
