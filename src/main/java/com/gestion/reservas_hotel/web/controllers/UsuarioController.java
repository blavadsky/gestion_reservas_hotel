package com.gestion.reservas_hotel.web.controllers;

import com.gestion.reservas_hotel.model.entities.ReservasEntity;
import com.gestion.reservas_hotel.service.interfaces.UsuarioService;
import com.gestion.reservas_hotel.web.dto.HotelDTO;
import com.gestion.reservas_hotel.web.dto.ReservaDTO;
import com.gestion.reservas_hotel.web.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apiUsuarios/v1")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("crearUsuario")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return new ResponseEntity<>(usuarioService.crearUsuario(usuarioDTO), HttpStatus.CREATED);
    }

    @GetMapping("obtenerUsuario")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@RequestParam String correoElectronico){
        return new ResponseEntity<>(usuarioService.obtenerUsuario(correoElectronico), HttpStatus.FOUND);
    }
    @GetMapping("obtenerUsuarioDocumento")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@RequestParam Integer numeroDocumento){
        return new ResponseEntity<>(usuarioService.obtenerUsuario(numeroDocumento), HttpStatus.FOUND);
    }
    @DeleteMapping("eliminarUsuario")
    public boolean eliminarUsuario(@RequestParam Integer numeroDocumento) {
        return usuarioService.eliminarUsuario(numeroDocumento);
    }
    @PutMapping("actualizarUsuario")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioService.actualizarUsuario(usuarioDTO), HttpStatus.OK);
    }



}
