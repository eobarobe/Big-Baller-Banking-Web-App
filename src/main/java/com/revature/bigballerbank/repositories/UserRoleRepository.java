package com.revature.bigballerbank.repositories;

import com.revature.bigballerbank.models.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository {

}
