package com.revature.bigballerbank.services;

import com.revature.bigballerbank.dtos.AuthenticatedDTO;
import com.revature.bigballerbank.dtos.CredentialsDTO;
import com.revature.bigballerbank.dtos.UserAccountRegisterDTO;
import com.revature.bigballerbank.models.RoleEntity;
import com.revature.bigballerbank.models.UserAccountEntity;
import com.revature.bigballerbank.models.UserEntity;
import com.revature.bigballerbank.repositories.BankAccountRepository;
import com.revature.bigballerbank.repositories.RoleRepository;
import com.revature.bigballerbank.repositories.UserAccountRepository;
import com.revature.bigballerbank.repositories.UserRepository;
import com.revature.bigballerbank.exceptions.*;
import org.mockito.ArgumentMatchers;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.ActiveProfiles;
import com.revature.bigballerbank.services.UserAccountService;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Optional;



import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;


@ActiveProfiles("test")
public class UserAccountServiceTests {

    //set up the system undertest. in this case it will be our
    //user account service
    @InjectMocks
    private UserAccountService sut;

    /**
     * this section will define and mock out data repositories
     * for accurate unit testing that doesnt actually involve
     * making calls to our backend
     **/
    @Mock
    private UserAccountRepository mockUserAccountRepository;

    @Mock
    private UserRepository mockUserReposity;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private BankAccountRepository mockBankAccountRepository;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    //this before method is called before each test case to properly
    //open the mock repos that we defined earlier
    @Before
    public void setUp(){
        openMocks(this);
    }

    //this @After method is automatically called after each test case
    //to reset our fields for the next test case
    @After
    public void tearDown(){
        mockBankAccountRepository = null;
        mockRoleRepository = null;
        mockUserReposity = null;
        mockUserAccountRepository = null;
        mockPasswordEncoder = null;
    }

    @Test
    public void test_loginWithValidCredentials(){
        //Arrange
        AuthenticatedDTO authenticatedDTO = null;
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        HashSet<RoleEntity> roles = new HashSet<>();
        userAccountEntity.setId(1);
        userAccountEntity.setUsername("valid");
        userAccountEntity.setPassword("password");
        userAccountEntity.setRoles(roles);

        CredentialsDTO credentialsDTO = new CredentialsDTO("valid","password");
        //Act
        when(mockUserAccountRepository.findByUsername(credentialsDTO.getUsername())).thenReturn(userAccountEntity);
        when(mockPasswordEncoder.matches(credentialsDTO.getPassword(),userAccountEntity.getPassword())).thenReturn(true);

        authenticatedDTO=sut.login(credentialsDTO);

        //Assert
        Assert.assertEquals(credentialsDTO.getUsername(),authenticatedDTO.getUsername());
    }
    @Test
    public void test_loginWithBadCredentials(){
        //Arrange
        AuthenticatedDTO authenticatedDTO = null;
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        HashSet<RoleEntity> roles = new HashSet<>();
        userAccountEntity.setId(1);
        userAccountEntity.setUsername("invalid");
        userAccountEntity.setPassword("password");
        userAccountEntity.setRoles(roles);

        CredentialsDTO credentialsDTO = new CredentialsDTO("invalid","pasword");
        //Act
        when(mockUserAccountRepository.findByUsername(credentialsDTO.getUsername())).thenReturn(userAccountEntity);
        when(mockPasswordEncoder.matches(credentialsDTO.getPassword(),userAccountEntity.getPassword())).thenReturn(false);

        authenticatedDTO=sut.login(credentialsDTO);

        //Assert
        Assert.assertNull(authenticatedDTO);

    }

    @Test
    public void test_registerWithGoodCredentials(){
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        UserEntity userEntity = new UserEntity();
        UserAccountRegisterDTO userAccountRegisterDTO = new UserAccountRegisterDTO("uname","pword","email","fname","lname",23);
        userEntity.setFirstName(userAccountRegisterDTO.getFirstName());
        userEntity.setLastName(userAccountRegisterDTO.getLastName());
        userEntity.setEmail(userAccountRegisterDTO.getEmail());
        userEntity.setAge(userAccountRegisterDTO.getAge());
        userAccountEntity.setUser(userEntity);
        AuthenticatedDTO authenticatedDTO;

        RoleEntity roleEntity = new RoleEntity(1, "ROLE_USER");
        when(mockRoleRepository.findById(1)).thenReturn(Optional.of(roleEntity));

        userAccountEntity.setUsername(userAccountRegisterDTO.getUsername());
        userAccountEntity.setPassword(mockPasswordEncoder.encode(userAccountRegisterDTO.getPassword()));

        //Act

        authenticatedDTO = sut.register(userAccountRegisterDTO);

        Assert.assertEquals(new AuthenticatedDTO(userAccountEntity).getUsername(),authenticatedDTO.getUsername());
        verify( mockUserAccountRepository, times(1)).save(userAccountEntity);
        verify( mockUserReposity,times(1)).save(userEntity);
    }
    @Test(expected = DuplicateRegistrationException.class)
    public void test_registerWithTakenUsername(){
        //arrange
        AuthenticatedDTO authenticatedDTO;
        UserAccountRegisterDTO userAccountRegisterDTO = new UserAccountRegisterDTO("takenUname","pword","email","fname","lname",23);
        when(mockUserAccountRepository.save(any())).thenThrow(DuplicateRegistrationException.class);

        RoleEntity roleEntity = new RoleEntity(1, "ROLE_USER");
        when(mockRoleRepository.findById(1)).thenReturn(Optional.of(roleEntity));
        //act
        authenticatedDTO = sut.register(userAccountRegisterDTO);
        //Assert
        Assert.assertNull(authenticatedDTO.getUsername());
        verify( mockUserAccountRepository, times(1)).save(any());
        verify( mockUserReposity,times(0)).save(any());
    }
    @Test(expected = DuplicateRegistrationException.class)
    public void test_registerWithTakeEmail(){
        //arrange
        AuthenticatedDTO authenticatedDTO;
        UserAccountRegisterDTO userAccountRegisterDTO = new UserAccountRegisterDTO("uname","pword","takenEmail","fname","lname",23);
        when(mockUserAccountRepository.save(any())).thenThrow(DuplicateRegistrationException.class);

        RoleEntity roleEntity = new RoleEntity(1, "ROLE_USER");
        when(mockRoleRepository.findById(1)).thenReturn(Optional.of(roleEntity));
        //act
        authenticatedDTO = sut.register(userAccountRegisterDTO);
        //Assert
        Assert.assertNull(authenticatedDTO.getUsername());
        verify( mockUserAccountRepository, times(1)).save(any());
        verify( mockUserReposity,times(0)).save(any());
    }


}

