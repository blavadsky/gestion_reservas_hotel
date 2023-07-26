package com.gestion.reservas_hotel.model.entities;


import com.gestion.reservas_hotel.model.TipoDocumento;
import com.gestion.reservas_hotel.model.UsuarioRol;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="usuarios")
public class UsuarioEntity implements UserDetails {

    @Id
    @Column(name = "numero_documento_usuario")
    private Integer numeroDocumentoUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "telefono_usuario")
    private String telefonoUsuario;

    @Column(name = "tipo_documento")
    private TipoDocumento tipoDocumento;

    @Column(name = "correo_electronico", nullable = false, unique = true)
    private String correoElectronico;

    @Enumerated(EnumType.STRING)
    private UsuarioRol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }
//    @Column(name="usuario_rol")
//    private UsuarioRol usuarioRol;

//    private String perfil;
//    private boolean enabled = true;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
    private Set<UsuarioRolEntity> usuarioRoles = new HashSet<>();

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//
//    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
//    private AccesoUsuariosEntity accesoUsuario;


}
