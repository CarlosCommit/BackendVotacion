package com.votacion.sistema.dao;

import com.votacion.sistema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM users where email = :email OR username = :username", nativeQuery = true)
    public List<User> existUserRegistered(@Param("email")String email,
                                          @Param("username")String username);
}
