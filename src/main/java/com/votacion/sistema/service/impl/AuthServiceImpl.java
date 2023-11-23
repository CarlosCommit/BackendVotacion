package com.votacion.sistema.service.impl;

import com.votacion.sistema.dto.LoginDTO;
import com.votacion.sistema.security.jwt.JwtUtils;
import com.votacion.sistema.security.service.UserDetailsServiceCustom;
import com.votacion.sistema.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceCustom userDetailsServiceCustom;
    @Override
    public String login(LoginDTO credential) {
        String token = "";
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credential.getEmail(),credential.getPassword()));
        if(auth.isAuthenticated())
            token=jwtUtils.generateToken(userDetailsServiceCustom.getUserDetail().getEmail(), userDetailsServiceCustom.getUserDetail().getRol().getNombre());
        return token;
    }

}
