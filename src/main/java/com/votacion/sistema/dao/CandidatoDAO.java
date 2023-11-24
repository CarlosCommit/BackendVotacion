package com.votacion.sistema.dao;

import com.votacion.sistema.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoDAO extends JpaRepository<Candidato,Long> {

}
