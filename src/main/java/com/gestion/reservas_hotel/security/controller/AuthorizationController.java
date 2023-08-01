package com.gestion.reservas_hotel.security.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resource")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthorizationController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Here is your resource");
    }

}
