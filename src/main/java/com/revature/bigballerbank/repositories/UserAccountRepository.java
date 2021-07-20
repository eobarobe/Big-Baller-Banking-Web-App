package com.revature.bigballerbank.repositories;

import com.revature.bigballerbank.dtos.CredentialsDTO;
import com.revature.bigballerbank.models.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository <UserAccountEntity, Integer> {
    UserAccountEntity findByUsernameAndPassword(String username,String password);
    Optional<UserAccountEntity> findById(int id);
    UserAccountEntity findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByUsernameAndPassword(String username, String password);
}
