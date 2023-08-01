package com.gestion.reservas_hotel.security.service.implementations;

import com.gestion.reservas_hotel.model.UsuarioRol;
import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import com.gestion.reservas_hotel.model.repositoy.UsuarioRepository;
import com.gestion.reservas_hotel.security.dao.request.SignUpRequest;
import com.gestion.reservas_hotel.security.service.interfaces.JwtService;
import com.gestion.reservas_hotel.service.interfaces.UsuarioService;
import com.gestion.reservas_hotel.web.dto.UsuarioDTO;
import com.gestion.reservas_hotel.web.exception.BadRequestException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService, UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper;


    @Value("${token.signing.key}")
    private String jwtSigningKey;

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, getSigningKey()).compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return usuarioRepository.findByCorreoElectronico(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }

        };

    }


    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        boolean existeUsuario = usuarioRepository.findAll().stream()
                .anyMatch(usuario -> usuario.getNumeroDocumento().equals(usuarioDTO.getNumeroDocumento()));
        if (existeUsuario == false) {
            UsuarioEntity usuarioEntity = modelMapper.map(usuarioDTO, UsuarioEntity.class);
            usuarioEntity = usuarioRepository.save(usuarioEntity);
            return modelMapper.map(usuarioEntity, UsuarioDTO.class);
        } { throw new BadRequestException("Ya existe un usuario registrado con el documento: "
                +usuarioDTO.getNumeroDocumento());}
    }

    @Override
    public UsuarioDTO obtenerUsuario(String correoElectronico) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCorreoElectronico(correoElectronico)
                .orElseThrow(() -> new BadRequestException("No se encontró un usuario con correo: "+correoElectronico));
        return modelMapper.map(usuarioEntity, UsuarioDTO.class);
    }
    @Override
    public UsuarioDTO obtenerUsuario(Integer numeroDocumento) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByNumeroDocumento(numeroDocumento)
                .orElseThrow(() -> new BadRequestException("No se encontró un usuario con documento: "+numeroDocumento));
        return modelMapper.map(usuarioEntity, UsuarioDTO.class);
    }


    @Override
    public boolean eliminarUsuario(Integer numeroDocumento) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByNumeroDocumento(numeroDocumento)
                .orElseThrow(()-> new BadRequestException("No se encontró un usuario con documento: "+numeroDocumento));
        usuarioRepository.delete(usuarioEntity);
        return true;
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) {
        return usuarioRepository.findByNumeroDocumento(usuarioDTO.getNumeroDocumento())
                .map(usuarioEntity -> {
                    usuarioEntity.setTelefono(usuarioDTO.getTelefono());
                    usuarioEntity = usuarioRepository.save(usuarioEntity);
                    return modelMapper.map(usuarioEntity, UsuarioDTO.class);
                }).orElseThrow(()->
                        new BadRequestException("No se encontró un usuario con documento: " + usuarioDTO.getNumeroDocumento()));
    }


}
