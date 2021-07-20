package com.revature.bigballerbank.services;

import com.revature.bigballerbank.dtos.AuthenticatedDTO;
import com.revature.bigballerbank.dtos.CredentialsDTO;
import com.revature.bigballerbank.dtos.UserAccountRegisterDTO;
import com.revature.bigballerbank.exceptions.DuplicateRegistrationException;
import com.revature.bigballerbank.exceptions.InvalidCredentialsException;
import com.revature.bigballerbank.exceptions.InvalidRoleException;
import com.revature.bigballerbank.models.UserEntity;
import com.revature.bigballerbank.models.UserAccountEntity;
import com.revature.bigballerbank.models.UserRoleEntity;
import com.revature.bigballerbank.repositories.UserAccountRepository;
import com.revature.bigballerbank.repositories.UserRoleRepository;
import com.revature.bigballerbank.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;

/* This is Service for accounts
**/
@Transactional
@Service
@AllArgsConstructor(onConstructor =  @__(@Autowired))
public class UserAccountService {
    private final UserRepository userRepository;
    private final UserAccountRepository accountRepository;
    private final UserRoleRepository roleRepository;

    /**
     *  This method is responsible for logging into an account that exists in a database
     * @param credentialsDTO the DTO required for authentication
     * @returns accountLoginDTO the DTO required to login to an account
     * @author Eloho Obaro-Best
     * @throws InvalidCredentialsException when the username/password does not exist
     * */
    public AuthenticatedDTO login(CredentialsDTO credentialsDTO) throws InvalidCredentialsException{
        UserAccountEntity userAccountEntity;
        AuthenticatedDTO authenticatedDTO;
        userAccountEntity = accountRepository.findByUsernameAndPassword(credentialsDTO.getUsername(),credentialsDTO.getPassword());

        try{
            authenticatedDTO = new AuthenticatedDTO(userAccountEntity);
        }catch(NullPointerException npe){
            throw new InvalidCredentialsException("Invalid Username and or Passowrd!");
        }
        return authenticatedDTO;
    }
    /**
     * checks if the fields are blank
     * **/
    private boolean isValid(String str){
        if(str.trim().equals("")){
            return false;
        }
        return true;
    }

    /**
     * This method is responsible for the registration(creation) of a new user and their account,
     * persisted into the database.
     * @param userAccountRegisterDTO the DTO required for registering a user.
     * @return accountLoginDTO the DTO required to login to a successful
     * @author Eloho Obaro-Best
     * @throws InvalidCredentialsException when the role does not exist in
     * */
    public AuthenticatedDTO register(UserAccountRegisterDTO userAccountRegisterDTO){
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        UserEntity userEntity = new UserEntity();
        Optional<UserRoleEntity> optionalRoleEntity = roleRepository.findById(1);
        if(!optionalRoleEntity.isPresent()){
            throw new InvalidRoleException("Nonexisting role");
        }
        userEntity.setEmail(userAccountRegisterDTO.getEmail());
        userEntity.setFirstName(userAccountRegisterDTO.getFirstName());
        userEntity.setLastName(userAccountRegisterDTO.getLastName());
        userEntity.setAge(userAccountRegisterDTO.getAge());

        HashSet<UserRoleEntity> roleHashSet = new HashSet<>();
        roleHashSet.add(optionalRoleEntity.get());

        userAccountEntity.setUsername( userAccountRegisterDTO.getUsername() );
        userAccountEntity.setPassword( userAccountRegisterDTO.getPassword() );
        userAccountEntity.setUser(userEntity);




        try{
            userRepository.save(userEntity);
            accountRepository.save(userAccountEntity);

        }catch (Exception e){
            System.out.println(e);

            throw new DuplicateRegistrationException("username and or email already exists");
        }
        AuthenticatedDTO authenticatedDTO = new AuthenticatedDTO(userAccountEntity);
        return authenticatedDTO;
    }


}
