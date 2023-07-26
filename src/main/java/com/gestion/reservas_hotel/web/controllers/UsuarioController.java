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
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("crearUsuario")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return new ResponseEntity<>(usuarioService.crearUsuario(usuarioDTO), HttpStatus.CREATED);
    }
    @GetMapping("obtenerUsuario")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@RequestParam Integer numeroDocumentoUsuario){
        return new ResponseEntity<>(usuarioService.obtenerUsuario(numeroDocumentoUsuario), HttpStatus.FOUND);
    }
    @DeleteMapping("eliminarUsuario")
    public boolean eliminarUsuario(@RequestParam Integer numeroDocumentoUsuario) {
        return usuarioService.eliminarUsuario(numeroDocumentoUsuario);
    }
    @PutMapping("actualizarUsuario")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioService.actualizarUsuario(usuarioDTO), HttpStatus.OK);
    }



}
