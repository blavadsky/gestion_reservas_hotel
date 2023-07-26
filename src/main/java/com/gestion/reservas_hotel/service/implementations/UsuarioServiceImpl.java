package com.gestion.reservas_hotel.service.implementations;

import com.gestion.reservas_hotel.model.UsuarioRol;
import com.gestion.reservas_hotel.model.entities.UsuarioEntity;
import com.gestion.reservas_hotel.model.entities.UsuarioRolEntity;
import com.gestion.reservas_hotel.model.repositoy.RolRepository;
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

import java.util.Set;
@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {


    private final UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return usuarioRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

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
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO, Set<UsuarioRolEntity> usuarioRoles) throws Exception {
//        UsuarioDTO usuarioDTO1 = usuarioRepository.findByNombreUsuario(usuarioDTO.getNombreUsuario());
//        if(usuarioDTO1 != null) {
//            System.out.println("Ya existe");
//            throw new BadRequestException("Ya existe el user");
//        }
//        else {
//            for(UsuarioRolEntity usuarioRol: usuarioRoles) {
//                rolRepository.save(usuarioRol.getRol());
//            }
//            usuarioDTO.getUsuarioRoles().addAll(usuarioRoles);

//        }
        return null;
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
