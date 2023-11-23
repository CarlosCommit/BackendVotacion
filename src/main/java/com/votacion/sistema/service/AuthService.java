package com.votacion.sistema.service;

import com.votacion.sistema.dto.LoginDTO;
import com.votacion.sistema.dto.SignUpDTO;

public interface AuthService {
    public String login(LoginDTO credential);
}
