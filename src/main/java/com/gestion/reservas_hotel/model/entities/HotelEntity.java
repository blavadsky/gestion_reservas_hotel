package com.gestion.reservas_hotel.model.entities;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="hotel")
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="nombre_hotel")
    private String nombreHotel;

    @Column(name="telefono_hotel")
    private String telefono;

    @Column(name="direccionCorreo_hotel")
    private String direccionCorreo;

    @Column(name="numeroHabitaciones_hotel")
    private Integer numeroHabitaciones;

}
