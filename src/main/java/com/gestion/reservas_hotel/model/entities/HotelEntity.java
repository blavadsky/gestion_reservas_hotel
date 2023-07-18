package com.gestion.reservas_hotel.model.entities;


import jakarta.persistence.*;
import lombok.*;
import java.util.*;

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
    private Integer idHotel;

    @Column(name = "nombre_hotel")
    private String nombreHotel;

    @Column(name = "telefono_hotel")
    private String telefonoHotel;

    @Column(name = "direccionCorreo_hotel")
    private String direccionCorreoHotel;

    @Column(name = "numeroHabitaciones_hotel")
    private Integer numeroHabitacionesHotel;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservasEntity> reservas = new ArrayList<>();

}
