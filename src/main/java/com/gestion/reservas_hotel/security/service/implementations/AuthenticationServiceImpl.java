package com.gestion.reservas_hotel.security.service.implementations;

import com.gestion.reservas_hotel.model.UsuarioRol;
import com.gestion.reservas_hotel.model.entities.RolEntity;
import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import com.gestion.reservas_hotel.model.repositoy.UsuarioRepository;
import com.gestion.reservas_hotel.security.dao.request.*;
import com.gestion.reservas_hotel.security.dao.response.JwtAuthenticationResponse;
import com.gestion.reservas_hotel.security.service.interfaces.JwtService;
import com.gestion.reservas_hotel.service.interfaces.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = UsuarioEntity.builder().nombre(request.getFirstName()).apellidos(request.getLastName())
                .correoElectronico(request.getEmail()).contrasena(passwordEncoder.encode(request.getPassword()))
                .rol(UsuarioRol.NORMAL).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
