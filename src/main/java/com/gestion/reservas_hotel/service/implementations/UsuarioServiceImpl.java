package com.gestion.reservas_hotel.service.implementations;


import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import com.gestion.reservas_hotel.model.repositoy.UsuarioRepository;
import com.gestion.reservas_hotel.service.interfaces.UsuarioService;
import com.gestion.reservas_hotel.web.dto.UsuarioDTO;
import com.gestion.reservas_hotel.web.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper;


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

