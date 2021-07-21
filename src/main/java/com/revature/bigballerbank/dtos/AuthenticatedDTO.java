package com.revature.bigballerbank.dtos;

import com.revature.bigballerbank.models.UserAccountEntity;
import com.revature.bigballerbank.models.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
public @Data class AuthenticatedDTO {
    private int id;
    private String username;
    private Set<RoleEntity> roles;
    public AuthenticatedDTO(UserAccountEntity userAccountEntity) {
        this.id = userAccountEntity.getId();
        this.username = userAccountEntity.getUsername();
        this.roles = userAccountEntity.getRoles();
    }
}
