package com.votacion.sistema.security.service;

import com.votacion.sistema.dao.UserDAO;
import com.votacion.sistema.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    private User userFound;
    @Autowired
    UserDAO userDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        userFound = userDAO.findByEmail(username).orElseThrow( ()-> new UsernameNotFoundException("Email no encontrado") );
        List<GrantedAuthority> roles = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userFound.getRol().getNombre());
        roles.add(grantedAuthority);
        return (UserDetails) new org.springframework.security.core.userdetails.User(username,userFound.getPassword(),roles);

    }

    public User getUserDetail(){return userFound;}
}
