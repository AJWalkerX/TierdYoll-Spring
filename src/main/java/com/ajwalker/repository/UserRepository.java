package com.ajwalker.repository;

import com.ajwalker.entity.User;
import com.ajwalker.utility.enums.EUserStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserStatus(EUserStatus status);


	Optional<User> findOptionalByUsername(String username);

	Optional<User> findByEmail(String email);
}