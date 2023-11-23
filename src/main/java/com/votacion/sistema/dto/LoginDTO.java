package com.votacion.sistema.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class LoginDTO implements Serializable {

    public String email;
    public String password;
}
