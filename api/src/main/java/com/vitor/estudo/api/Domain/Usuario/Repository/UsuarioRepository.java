package com.vitor.estudo.api.Domain.Usuario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.vitor.estudo.api.Domain.Usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    UserDetails findByLogin(String login);
        
}
