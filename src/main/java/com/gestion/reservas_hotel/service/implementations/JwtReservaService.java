package com.gestion.reservas_hotel.service.implementations;

import com.gestion.reservas_hotel.model.entities.HotelEntity;
import com.gestion.reservas_hotel.model.entities.ReservasEntity;
import com.gestion.reservas_hotel.service.interfaces.JwtReserva;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtReservaService implements JwtReserva {

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    @Override
    public String generateToken(ReservasEntity reservasEntity, Integer dias) {
        return generateToken(new HashMap<>(), reservasEntity, dias);
    }

    @Override
    public boolean isTokenValid(String token, ReservasEntity reservasEntity) {
        final String userName = extractUserName(token);
        return (userName.equals(reservasEntity.getHotel()) && !isTokenExpired(token));

    }

    private String generateToken(Map<String, Object> extraClaims, ReservasEntity reservasEntity, Integer dias) {
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(reservasEntity.getHotel().getNombreHotel())
                .claim("dias", dias)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, getSigningKey()).compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
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
}
