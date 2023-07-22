package com.gestion.reservas_hotel.model.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="reservas")
public class ReservasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "fecha_inicio_reserva")
    private Date fechaInicio;

    @Column(name = "fecha_fin_reserva")
    private Date fechaFin;

    @Column(name = "reservas_solicitadas")
    private Integer capacidadHotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;

}
