package com.votacion.sistema.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {
    private String username;
    private String email;
    private boolean voto;
    private String candidado;
    private String token;
}
