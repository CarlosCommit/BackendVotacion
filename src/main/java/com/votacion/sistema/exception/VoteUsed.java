package com.votacion.sistema.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class VoteUsed extends RuntimeException {
    public String message;
}
