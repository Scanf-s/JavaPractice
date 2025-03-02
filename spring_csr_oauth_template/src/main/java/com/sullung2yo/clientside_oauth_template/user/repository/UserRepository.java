package com.sullung2yo.clientside_oauth_template.user.repository;

import com.sullung2yo.clientside_oauth_template.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
