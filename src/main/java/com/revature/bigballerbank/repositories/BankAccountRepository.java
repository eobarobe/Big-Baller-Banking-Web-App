package com.revature.bigballerbank.repositories;

import com.revature.bigballerbank.models.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository <BankAccountEntity,Integer> {
}
