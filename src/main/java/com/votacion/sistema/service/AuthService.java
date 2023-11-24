package com.votacion.sistema.service;

import com.votacion.sistema.dto.LoginDTO;
import com.votacion.sistema.dto.SignUpDTO;
import com.votacion.sistema.dto.UserLoguedDTO;

public interface AuthService {
    public UserLoguedDTO login(LoginDTO credential);
}
