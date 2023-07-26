package com.gestion.reservas_hotel.security.dao.request;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
