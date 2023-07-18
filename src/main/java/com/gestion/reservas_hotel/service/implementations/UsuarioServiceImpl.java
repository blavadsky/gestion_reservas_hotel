package com.gestion.reservas_hotel.service.implementations;

import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import com.gestion.reservas_hotel.model.repositoy.UsuarioRepository;
import com.gestion.reservas_hotel.service.interfaces.UsuarioService;
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
        boolean existeUsuario = usuarioRepository.findAll().stream()
                .anyMatch(usuario -> usuario.getNumeroDocumentoUsuario().equals(usuarioDTO.getNumeroDocumentoUsuario()));
        if (existeUsuario == false) {
            UsuarioEntity usuarioEntity = modelMapper.map(usuarioDTO, UsuarioEntity.class);
            usuarioEntity = usuarioRepository.save(usuarioEntity);
            return modelMapper.map(usuarioEntity, UsuarioDTO.class);
        } { throw new BadRequestException("Ya existe un usuario registrado con el documento: "
                +usuarioDTO.getNumeroDocumentoUsuario());}

    }

    @Override
    public UsuarioDTO obtenerUsuario(Integer numeroDocumentoUsuario) {
            UsuarioEntity usuarioEntity = usuarioRepository.findById(numeroDocumentoUsuario)
                .orElseThrow(() -> new BadRequestException("No se encontró un usuario con documento: "+numeroDocumentoUsuario));
        return modelMapper.map(usuarioEntity, UsuarioDTO.class);
    }

    @Override
    public boolean eliminarUsuario(Integer numeroDocumentoUsuario) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(numeroDocumentoUsuario)
            .orElseThrow(()-> new BadRequestException("No se encontró un usuario con documento: "+numeroDocumentoUsuario));
        usuarioRepository.delete(usuarioEntity);
        return true;
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(usuarioDTO.getNumeroDocumentoUsuario())
                .map(usuarioEntity -> {
                    usuarioEntity.setTelefonoUsuario(usuarioDTO.getTelefonoUsuario());
                    usuarioEntity = usuarioRepository.save(usuarioEntity);
                    return modelMapper.map(usuarioEntity, UsuarioDTO.class);
                }).orElseThrow(()->
                        new BadRequestException("No se encontró un usuario con documento: " + usuarioDTO.getNumeroDocumentoUsuario()));
    }
}
