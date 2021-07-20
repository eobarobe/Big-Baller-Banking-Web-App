package com.revature.bigballerbank.repositories;

import com.revature.bigballerbank.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <RoleEntity, Integer> {

}
