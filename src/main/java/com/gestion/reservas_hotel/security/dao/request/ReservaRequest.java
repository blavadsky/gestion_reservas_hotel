package com.gestion.reservas_hotel.security.dao.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gestion.reservas_hotel.model.entities.HotelEntity;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequest {

    private long numeroHabitacionesRequeridas;

    //private HotelEntity hotelId;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaInicio;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaFin;
}
