package com.gestion.reservas_hotel.model.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="HabitacionesReservadas")
public class HabitacionesReservadasEntity {

    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hotel")
    private HotelEntity idHotel;

    @Column(name = "habitacion_pequena")
    private Integer habitacionPequena;

    @Column(name = "habitacion_grande")
    private Integer habitacionGrande;

//    @OneToMany(mappedBy = "idHotel", cascade = CascadeType.ALL)
//    private List<ReservasEntity> reservas;

}
