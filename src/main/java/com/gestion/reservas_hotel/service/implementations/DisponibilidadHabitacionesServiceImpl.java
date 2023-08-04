package com.gestion.reservas_hotel.service.implementations;
//
//import com.gestion.reservas_hotel.model.entities.DisponibilidadHabitacionesEntity;
//import com.gestion.reservas_hotel.model.entities.HotelEntity;
//import com.gestion.reservas_hotel.model.repositoy.DisponibilidadHabitacionesRepository;
//import com.gestion.reservas_hotel.model.repositoy.HotelRepository;
//import com.gestion.reservas_hotel.service.interfaces.DisponibilidadHabitacionesService;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.internal.bytebuddy.asm.Advice;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class DisponibilidadHabitacionesServiceImpl implements DisponibilidadHabitacionesService {
//
//    private final HotelRepository hotelRepository;
//    private final DisponibilidadHabitacionesEntity disponibilidadHabitacionesEntity;
//
//    private final DisponibilidadHabitacionesRepository disponibilidadHabitacionesRepository;

//    @Override
//    public List<DisponibilidadHabitacionesEntity> obtenerHabitacionesDisponibles(Date fechaInicio, Date fechaFin) {
//        return disponibilidadHabitacionesRepository.findByReservasFechaInicioGreaterThanEqualAndReservasFechaFinLessThanEqual(fechaInicio, fechaFin);
//    }

//}
