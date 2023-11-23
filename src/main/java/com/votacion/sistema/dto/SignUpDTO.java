package com.votacion.sistema.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SignUpDTO implements Serializable {
    public String email;
    public String password;
    public String username;
}
