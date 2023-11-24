package com.votacion.sistema.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CandidatoDTO implements Serializable {

    @NotNull
    private Long idCandidato;

}
