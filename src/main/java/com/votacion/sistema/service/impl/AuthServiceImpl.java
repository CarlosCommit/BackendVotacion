package com.votacion.sistema.service.impl;

import com.votacion.sistema.dto.LoginDTO;
import com.votacion.sistema.dto.UserLoguedDTO;
import com.votacion.sistema.model.User;
import com.votacion.sistema.security.jwt.JwtUtils;
import com.votacion.sistema.security.service.UserDetailsServiceCustom;
import com.votacion.sistema.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceCustom userDetailsServiceCustom;
    @Override
    public UserLoguedDTO login(LoginDTO credential) {
        UserLoguedDTO userLoguedDTO = new UserLoguedDTO();
        String token = "";
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credential.getEmail(),credential.getPassword()));
        if(auth.isAuthenticated())
            token=jwtUtils.generateToken(userDetailsServiceCustom.getUserDetail().getEmail(), userDetailsServiceCustom.getUserDetail().getRol().getNombre());
       log.info("Se logueo el usuario "+ credential.getEmail());
        User userFound = userDetailsServiceCustom.getUserDetail();
        userLoguedDTO.setToken(token);
        userLoguedDTO.setUsername(userFound.getUsername());
        userLoguedDTO.setEmail(userFound.getEmail());
        userLoguedDTO.setRol(userFound.getRol().getNombre());
        if(userFound.getCandidato()!=null)
        {
            userLoguedDTO.setCandidato(userFound.getCandidato().getName());
            userLoguedDTO.setVoto(true);
        }else
        {
            userLoguedDTO.setVoto(false);
        }

        return userLoguedDTO;
    }

}
