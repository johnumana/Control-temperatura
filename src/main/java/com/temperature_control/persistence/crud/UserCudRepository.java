package com.temperature_control.persistence.crud;

import com.temperature_control.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCudRepository extends JpaRepository<User, Integer> {

    // Buscar un usuario por nombre de usuario
    Optional<User> findByUsername(String username);

    // Buscar un usuario por correo electrónico
    Optional<User> findByEmail(String email);

    // Verificar si existe un usuario por nombre de usuario
    boolean existsByUsername(String username);

    // Verificar si existe un usuario por correo electrónico
    boolean existsByEmail(String email);

    // Buscar todos los usuarios ordenados por nombre de usuario
    List<User> findAllByOrderByUsernameAsc();
}
