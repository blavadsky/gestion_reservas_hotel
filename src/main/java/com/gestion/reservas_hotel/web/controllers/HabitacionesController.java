package com.gestion.reservas_hotel.web.controllers;

//
//import com.gestion.reservas_hotel.model.entities.DisponibilidadHabitacionesEntity;
//import com.gestion.reservas_hotel.service.interfaces.DisponibilidadHabitacionesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//@RestController
//@RequestMapping("/apiHabitaciones/v1")
//public class HabitacionesController {

//    @Autowired
//    private DisponibilidadHabitacionesService habitacionService;

//    @GetMapping("/disponibles")
//    public ResponseEntity<List<DisponibilidadHabitacionesEntity>> obtenerHabitacionesDisponibles
//            (@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
//            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
//
//        List<DisponibilidadHabitacionesEntity> habitacionesDisponibles = habitacionService.obtenerHabitacionesDisponibles(fechaInicio, fechaFin);
//        return ResponseEntity.ok(habitacionesDisponibles);
//    }
//}
