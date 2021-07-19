package com.revature.bigballerbank.repositories;

import com.revature.bigballerbank.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository {
   UserEntity findByEmail(String username);
   boolean existsByEmail(String email);
}
