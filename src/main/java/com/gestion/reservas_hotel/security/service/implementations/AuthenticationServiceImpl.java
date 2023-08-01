package com.gestion.reservas_hotel.security.service.implementations;

import com.gestion.reservas_hotel.model.UsuarioRol;
import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import com.gestion.reservas_hotel.model.repositoy.UsuarioRepository;
import com.gestion.reservas_hotel.security.dao.request.IsAdminRequest;
import com.gestion.reservas_hotel.security.dao.request.SignUpRequest;
import com.gestion.reservas_hotel.security.dao.request.SigninRequest;
import com.gestion.reservas_hotel.security.dao.response.JwtAuthenticationResponse;
import com.gestion.reservas_hotel.security.service.interfaces.AuthenticationService;
import com.gestion.reservas_hotel.security.service.interfaces.JwtService;
import com.gestion.reservas_hotel.web.dto.UsuarioDTO;
import com.gestion.reservas_hotel.web.exception.BadRequestException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private Key getSigningKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
                var user = UsuarioEntity.builder().nombre(request.getNombre()).apellidos(request.getApellidos()).telefono(request.getTelefono())
                .numeroDocumento(request.getNumeroDocumento()).correoElectronico(request.getCorreoElectronico()).rol(request.getUsuarioRol())
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .build();
        userRepository.save(user);
            var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreoElectronico(), request.getContrasena()));
        var user = userRepository.findByCorreoElectronico(request.getCorreoElectronico())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    @Override
    public JwtAuthenticationResponse generarTokenSiEsAdmin(IsAdminRequest request) {
        var user = userRepository.findByCorreoElectronicoAndRol(request.getCorreoElectronico(),request.getUsuarioRol())
                .orElseThrow(() -> new BadRequestException("No se encontró un usuario con correo " + request.getCorreoElectronico()));
        if (request.getUsuarioRol() == UsuarioRol.ADMIN) {
            var jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } else {
            throw new BadRequestException("No tienes permiso para acceder a este recurso.");
        }
    }

}