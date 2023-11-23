package com.votacion.sistema.service.impl;

import com.votacion.sistema.dao.RolDAO;
import com.votacion.sistema.dao.UserDAO;
import com.votacion.sistema.dto.SignUpDTO;
import com.votacion.sistema.exception.RegisterDuplicated;
import com.votacion.sistema.model.Role;
import com.votacion.sistema.model.User;
import com.votacion.sistema.service.SignUpService;
import com.votacion.sistema.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final UserDAO userDAO;
    private final RolDAO rolDAO;
    private final PasswordEncoder passwordEncoder;
    @Override
    public SignUpDTO signup(SignUpDTO body) {

        List<User> userByEmail = userDAO.existUserRegistered(body.getEmail(), body.getUsername());
        if (!userByEmail.isEmpty())
            throw new RegisterDuplicated("El username o email ya esta ocupado");
        User userToSave = new User();
        userToSave.setEmail(body.getEmail());
        userToSave.setUsername(body.getUsername());
        userToSave.setPassword( passwordEncoder.encode(body.getPassword()));
        Role roleToSet = new Role();
        roleToSet.setId(1L);
        userToSave.setRol(roleToSet);
        userDAO.save(userToSave);
        return body;
    }
}
