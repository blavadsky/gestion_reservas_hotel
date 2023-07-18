package com.gestion.reservas_hotel.web.controllers;

import com.gestion.reservas_hotel.service.interfaces.HotelService;
import com.gestion.reservas_hotel.web.dto.HotelDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apiHoteles/v1")
@AllArgsConstructor
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping("crearHotel")
    public ResponseEntity<HotelDTO> crearHotel(@RequestBody HotelDTO hotelDTO){
        return new ResponseEntity<>(hotelService.createHotel(hotelDTO), HttpStatus.CREATED);
    }

    @GetMapping("obtenerHotel")
    public ResponseEntity<HotelDTO> obtenerHotel(@RequestParam Integer id){
        return new ResponseEntity<>(hotelService.getHotel(id), HttpStatus.FOUND);
    }

    @DeleteMapping("eliminarHotel")
    public boolean eliminarHotel(@RequestParam Integer id) {
        return hotelService.deleteHotel(id);
    }

    @PutMapping("actualizarHotel")
    public ResponseEntity<HotelDTO> actualizarHotel(@RequestBody HotelDTO hotelDTO) {
        return new ResponseEntity<>(hotelService.updateHotel(hotelDTO), HttpStatus.OK);
    }
}

/*

    @DeleteMapping("eliminarLibro")
    public boolean eliminarLibro(@RequestParam Integer id) {
        return libroService.deleteBook(id);
    }

    @PutMapping("actualizarLibro")
    public LibroDTO actualizarLibro(@RequestBody LibroDTO libroDTO) {
        return libroService.updateBook(libroDTO);
    }*/
