package com.revature.bigballerbank.repositories;

import com.revature.bigballerbank.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserEntity,Integer> {
}
