package com.votacion.sistema.service;

import com.votacion.sistema.dto.CandidatoDTO;
import com.votacion.sistema.model.Candidato;
import com.votacion.sistema.model.User;

import java.util.List;

public interface VotacionService {
    public List<Candidato> getAllCandidatos();
    public List<Candidato> getAllCandidatosUsuarioLogueado();
    public boolean votarCandidato(CandidatoDTO candidatoDTO, User user);
}
