package com.gestion.reservas_hotel.web.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class HotelDTO {
    private Integer idHotel;
    private String nombreHotel;
    private String telefonoHotel;
    private String direccionCorreoHotel;
    private Integer numeroHabitacionesHotel;
}
