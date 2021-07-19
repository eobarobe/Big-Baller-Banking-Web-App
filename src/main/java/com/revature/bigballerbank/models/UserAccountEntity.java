package com.revature.bigballerbank.models;

import com.revature.bigballerbank.dtos.BankAccountDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Table(name= "user_accounts")
public class UserAccountEntity {
    //primary key for user accounts
    @Id
    @Column(name = "user_account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //Using OneToOne multiplicity annotation to join users with their ids
    @OneToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    //Grabs all User bankAccounts
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.userAccountEntity",cascade = CascadeType.ALL)
    private Set<UserBankAccountEntity> bankAccountEntities = new HashSet<>();

    //many users can have many roles
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "account_roles",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )

    private Set<UserRoleEntity> roles = new HashSet<>();

    @Column(name = "username",unique = true, nullable = false)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o== null || getClass() != o.getClass()) return false;
        UserAccountEntity that = (UserAccountEntity) o;
        return getId() == that.getId()
                && Objects.equals(getRoles(),that.getRoles())
                && getUsername().equals(that.getUsername())
                && getPassword().equals(that.getPassword());
    }
    @Override
    public int hashCode(){
        return Objects.hash(getId(),getUserBankAccounts(),getRoles(),getUsername());
    }
    public Set<BankAccountDTO> getUserBankAccounts(){
        return this.bankAccountEntities.stream()
            .map(BankAccountDTO::new)
            .collect(Collectors.toSet());
    }
}

