package com.votacion.sistema.service.impl;

import com.votacion.sistema.dao.CandidatoDAO;
import com.votacion.sistema.dao.UserDAO;
import com.votacion.sistema.dto.CandidatoDTO;
import com.votacion.sistema.exception.RegisterNotFound;
import com.votacion.sistema.model.Candidato;
import com.votacion.sistema.model.User;
import com.votacion.sistema.service.VotacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.parsers.SAXParser;
import java.util.ArrayList;
import java.util.List;
@Service
public class VotacionServiceImpl implements VotacionService {
    @Autowired
    private CandidatoDAO candidatoDAO;
    @Autowired
    private UserDAO userDAO;
    @Override
    public List<Candidato> getAllCandidatos() {
        List<Candidato> salida = new ArrayList<Candidato>();
        for(Candidato candidato: candidatoDAO.findAll())
        {
            candidato.setVotos(null);
            salida.add(candidato);
        }
        return salida;
    }

    @Override
    public List<Candidato> getAllCandidatosUsuarioLogueado() {
        return candidatoDAO.findAll();
    }

    @Override
    public boolean votarCandidato(CandidatoDTO candidatoDTO,User user) throws RegisterNotFound {
        Candidato candidato = candidatoDAO.findById(candidatoDTO.getIdCandidato()).orElseThrow( () -> new RegisterNotFound("El candidado que intentas votar no existe"));
        candidato.setVotos(candidato.getVotos()+1);
        candidatoDAO.save(candidato);
        user.setCandidato(candidato);
        userDAO.save(user);
        return true;
    }
}
