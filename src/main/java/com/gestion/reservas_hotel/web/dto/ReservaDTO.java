package com.gestion.reservas_hotel.web.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ReservaDTO {
    private Integer id;
    private Integer capacidadHotel;
    private Integer hotelId;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaInicio;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaFin;
}
