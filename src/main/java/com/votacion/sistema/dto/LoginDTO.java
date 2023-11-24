package com.votacion.sistema.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class LoginDTO implements Serializable {
    @NotBlank
    public String email;
    @NotBlank
    public String password;
}
