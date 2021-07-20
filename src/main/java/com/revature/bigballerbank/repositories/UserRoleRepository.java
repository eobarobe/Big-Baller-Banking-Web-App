package com.revature.bigballerbank.repositories;

import com.revature.bigballerbank.models.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository <UserRoleEntity, Integer> {

}
