package com.votacion.sistema.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RegisterNotFound extends RuntimeException {
    public String message;
}
