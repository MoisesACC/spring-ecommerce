package com.curso.ecommerce.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.model.Usuario;

import jakarta.servlet.http.HttpSession;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    HttpSession session;

    private Logger logg = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logg.info("Usuario: " + username);

        Optional<Usuario> optionalUser = usuarioService.findByEmail(username);

        if(optionalUser.isPresent()) {
            logg.info("Esto es el id del usuario:",optionalUser.get().getId());
           session.setAttribute("idusuario", optionalUser.get().getId());
           Usuario usuario = optionalUser.get();
           return User.builder().username(usuario.getNombre()).password(bCrypt.encode(usuario.getPassword())).roles("USER").build();
        } else{
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

}
