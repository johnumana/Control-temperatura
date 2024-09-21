package com.temperature_control.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.temperature_control.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}