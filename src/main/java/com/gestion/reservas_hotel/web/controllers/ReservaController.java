package com.gestion.reservas_hotel.web.controllers;


import com.gestion.reservas_hotel.service.interfaces.HotelService;
import com.gestion.reservas_hotel.service.interfaces.ReservaService;
import com.gestion.reservas_hotel.web.dto.HotelDTO;
import com.gestion.reservas_hotel.web.dto.ReservaDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;


@RestController
@RequestMapping("apiReservas/v1")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping("crearReserva")
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        return new ResponseEntity<>(reservaService.crearReserva(reservaDTO.getHotelId(),reservaDTO), HttpStatus.CREATED);
    }

    @GetMapping("obtenerReserva")
    public ResponseEntity<ReservaDTO> obtenerReserva(@RequestParam Integer id) {
        return new ResponseEntity<>(reservaService.obtenerReserva(id), HttpStatus.FOUND);
    }

    @DeleteMapping("eliminarReserva")
    public boolean eliminarReserva(@RequestParam Integer id) {
        return reservaService.eliminarReserva(id);
    }

    @PutMapping("actualizarReserva")
    public ResponseEntity<ReservaDTO> actualizarReserva(@RequestBody ReservaDTO reservaDTO) {
        return new ResponseEntity<>(reservaService.actualizarReserva(reservaDTO), HttpStatus.OK);
    }
}
