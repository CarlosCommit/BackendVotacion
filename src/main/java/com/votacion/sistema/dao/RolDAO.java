package com.votacion.sistema.dao;

import com.votacion.sistema.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolDAO extends JpaRepository<Role,Long>{
    public Optional<Role> findByNombre(String name);

}
