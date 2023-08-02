package com.williamfeliciano.login_registration_jwt_spring_security_6.repositories;

import com.williamfeliciano.login_registration_jwt_spring_security_6.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
