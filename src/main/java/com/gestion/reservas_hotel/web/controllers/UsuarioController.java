package com.gestion.reservas_hotel.web.controllers;

import com.gestion.reservas_hotel.service.interfaces.UsuarioService;
import com.gestion.reservas_hotel.web.dto.HotelDTO;
import com.gestion.reservas_hotel.web.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apiUsuarios/v1")
@AllArgsConstructor
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("crearUsuario")
    public ResponseEntity<UsuarioDTO> crearHotel(@RequestBody UsuarioDTO usuarioDTO){
        return new ResponseEntity<>(usuarioService.crearUsuario(usuarioDTO), HttpStatus.CREATED);
    }
    @GetMapping("obtenerUsuario")
    public ResponseEntity<UsuarioDTO> obtenerHotel(@RequestParam String numeroDocumentoUsuario){
        return new ResponseEntity<>(usuarioService.obtenerUsuario(numeroDocumentoUsuario), HttpStatus.FOUND);
    }

    @DeleteMapping("eliminarUsuario")
    public boolean eliminarUsuario(@RequestParam String numeroDocumentoUsuario) {
        return usuarioService.eliminarUsuario(numeroDocumentoUsuario);
    }
    @PutMapping("actualizarUsuario")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioService.actualizarUsuario(usuarioDTO), HttpStatus.OK);
    }

}
