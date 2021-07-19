package com.revature.bigballerbank.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class UserAccountRegisterDTO {
    String username;
    String password;
    String email;
    String firstName;
    String lastName;
}
