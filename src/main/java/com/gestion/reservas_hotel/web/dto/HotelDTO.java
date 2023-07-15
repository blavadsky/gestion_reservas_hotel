package com.gestion.reservas_hotel.web.dto;

import lombok.*;

import java.util.Collection;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelDTO {
    private String nombreHotel;
    private Date capacidadReservas;
    private String telefono;
    private String direccionCorreo;
    private Integer numeroHabitaciones;

}
