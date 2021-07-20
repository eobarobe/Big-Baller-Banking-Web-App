package com.revature.bigballerbank.repositories;

import com.revature.bigballerbank.models.UserBankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBankAccountRepository extends JpaRepository <UserBankAccountEntity,Integer> {
}
