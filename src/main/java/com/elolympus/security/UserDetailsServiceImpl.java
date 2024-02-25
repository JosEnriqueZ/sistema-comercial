package com.elolympus.security;

import com.elolympus.data.Administracion.Usuario;
import com.elolympus.data.User;
import com.elolympus.data.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.elolympus.services.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    private final UserRepository userRepository;
    private  final UsuarioRepository usuarioRepository;

//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsuario(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getPassword(),
                    getAuthorities(user));
        }
    }

    private static List<GrantedAuthority> getAuthorities(Usuario user) {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRol().getCargo().toUpperCase());
        return Collections.singletonList(authority);

    }

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("No user present with username: " + username);
//        } else {
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getHashedPassword(),
//                    getAuthorities(user));
//        }
//    }
//
//    private static List<GrantedAuthority> getAuthorities(User user) {
//        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .collect(Collectors.toList());
//
//    }

}
