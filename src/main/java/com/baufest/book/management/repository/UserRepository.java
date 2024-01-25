package com.baufest.book.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baufest.book.management.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByusername(String username);
}
