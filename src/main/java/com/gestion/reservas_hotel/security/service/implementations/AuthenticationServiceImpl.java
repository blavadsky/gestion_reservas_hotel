package com.gestion.reservas_hotel.security.service.implementations;

import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import com.gestion.reservas_hotel.model.repositoy.UsuarioRepository;
import com.gestion.reservas_hotel.security.dao.request.SignUpRequest;
import com.gestion.reservas_hotel.security.dao.request.SigninRequest;
import com.gestion.reservas_hotel.security.dao.response.JwtAuthenticationResponse;
import com.gestion.reservas_hotel.security.service.interfaces.AuthenticationService;
import com.gestion.reservas_hotel.security.service.interfaces.JwtService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;


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
        var user = userRepository.findByCorreoElectronico(request.getCorreoElectronico())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreoElectronico(), request.getContrasena())).getAuthorities();
        return JwtAuthenticationResponse.builder().token(jwtService.generateToken(user, user.getRol())).build();
    }

}