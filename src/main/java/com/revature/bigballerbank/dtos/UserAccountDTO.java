package com.revature.bigballerbank.dtos;

import com.revature.bigballerbank.models.UserAccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class UserAccountDTO {
    String username;
    public UserAccountDTO(UserAccountEntity userAccountEntity) {
        this.username = userAccountEntity.getUsername();

    }
}
