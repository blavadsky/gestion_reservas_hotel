package com.gestion.reservas_hotel.service.implementations;

import com.gestion.reservas_hotel.model.entities.HotelEntity;
import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import com.gestion.reservas_hotel.model.repositoy.HotelRepository;
import com.gestion.reservas_hotel.model.repositoy.UsuarioRepository;
import com.gestion.reservas_hotel.service.interfaces.UsuarioService;
import com.gestion.reservas_hotel.web.dto.HotelDTO;
import com.gestion.reservas_hotel.web.dto.UsuarioDTO;
import com.gestion.reservas_hotel.web.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        UsuarioEntity usuarioEntity = modelMapper.map(usuarioDTO, UsuarioEntity.class);
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        return modelMapper.map(usuarioEntity, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO obtenerUsuario(Integer numeroDocumentoUsuario) {
            UsuarioEntity usuarioEntity = usuarioRepository.findById(numeroDocumentoUsuario).get();
        return modelMapper.map(usuarioEntity, UsuarioDTO.class);
    }

    @Override
    public boolean eliminarUsuario(Integer numeroDocumentoUsuario) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(numeroDocumentoUsuario).orElse(null);
        usuarioRepository.delete(usuarioEntity);
        return true;
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(Integer.valueOf(usuarioDTO.getNumeroDocumentoUsuario())).orElse(null);
        if (usuarioEntity != null) {
            usuarioEntity.setTelefonoUsuario(usuarioDTO.getTelefonoUsuario());
            usuarioEntity = usuarioRepository.save(usuarioEntity);
            return modelMapper.map(usuarioEntity, UsuarioDTO.class);
        } else {
            throw new BadRequestException("No se encuentra un usuario con el documento: " + usuarioDTO.getNumeroDocumentoUsuario());
        }
    }
}
