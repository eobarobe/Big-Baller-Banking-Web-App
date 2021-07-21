package com.revature.bigballerbank.repositories;

import com.revature.bigballerbank.models.BankAccountEntity;
import com.revature.bigballerbank.models.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository <BankAccountEntity,Integer> {

    List<BankAccountEntity> findAllByUserAccountEntity(UserAccountEntity userAccountEntity);

}
