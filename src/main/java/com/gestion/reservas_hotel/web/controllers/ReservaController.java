package com.gestion.reservas_hotel.web.controllers;


import com.gestion.reservas_hotel.security.dao.request.ReservaRequest;
import com.gestion.reservas_hotel.service.interfaces.ReservaService;
import com.gestion.reservas_hotel.web.dto.ReservaDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("listarReservas")
    public List<ReservaDTO> listarReservas() {
        return reservaService.listarReservas();
    }

    @GetMapping("verificarDisponibilidad")
    public List<ReservaDTO> verificarDisponibilidad(@RequestBody ReservaRequest reservaRequest)
    {
        return reservaService. reservasGuardadasPorFecha(reservaRequest);
    }

    @GetMapping("obtenerReservasPorUsuario/{correoElectronico}")
    public List<ReservaDTO> obtenerReservasPorUsuario(@PathVariable String correoElectronico) {
        return reservaService.obtenerReservasPorUsuario(correoElectronico);
    }

}
