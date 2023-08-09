package com.gestion.reservas_hotel.web.controllers;



import com.gestion.reservas_hotel.model.UsuarioRol;
import com.gestion.reservas_hotel.model.entities.ReservasEntity;
import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import com.gestion.reservas_hotel.security.dao.request.*;
import com.gestion.reservas_hotel.security.dao.response.JwtAuthenticationResponse;
import com.gestion.reservas_hotel.security.service.interfaces.AuthenticationService;
//import com.gestion.reservas_hotel.web.dto.UsuarioDTO;
import com.gestion.reservas_hotel.service.interfaces.UsuarioService;
import com.gestion.reservas_hotel.web.exception.BadRequestException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UsuarioService usuarioService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/generarTokenAdmin")
    public ResponseEntity<JwtAuthenticationResponse> generarTokenSiEsAdmin(@RequestBody IsAdminRequest request) {
        return ResponseEntity.ok(authenticationService.generarTokenSiEsAdmin(request));
    }

    @GetMapping("/listarReservas")
    public List<ReservasEntity> listarReservas(@RequestParam Integer id) {
        return authenticationService.listarReservas(id );
    }

}

