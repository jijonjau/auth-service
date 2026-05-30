package com.jijonjau.auth_service.domain.repo;

import com.jijonjau.auth_service.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUserUuid(UUID userUuid);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}