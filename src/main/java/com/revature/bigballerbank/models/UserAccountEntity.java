package com.revature.bigballerbank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name= "user_accounts")
public class UserAccountEntity {
    //primary key for user accounts
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_account_id")
    private int id;

    //Using OneToOne multiplicity annotation to join users with their ids
    @OneToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    //many users can have many roles
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "account_roles",
            joinColumns = {@JoinColumn(name = "user_account_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<UserRoleEntity> roles = new HashSet<>();

    @Column(name = "username",unique = true, nullable = false)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

}

