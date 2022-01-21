package com.revature.bigballerbank.services;

import com.revature.bigballerbank.dtos.BankAccountDTO;
import com.revature.bigballerbank.dtos.UserAccountDTO;
import com.revature.bigballerbank.dtos.requests.DepositRequestDTO;
import com.revature.bigballerbank.models.BankAccountEntity;
import com.revature.bigballerbank.models.UserAccountEntity;
import com.revature.bigballerbank.repositories.BankAccountRepository;
import com.revature.bigballerbank.repositories.UserAccountRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ActiveProfiles("test")
public class BankAccountServiceTests {
    @InjectMocks
    private BankAccountService sut;

    @Mock
    private UserAccountRepository mockUserAccountRepository;

    @Mock
    private BankAccountRepository mockBankAccountRepository;

    @Before
    public void setUp(){
        openMocks(this);
    }

    @After
    public void tearDown(){
        mockBankAccountRepository = null;
        mockUserAccountRepository = null;
    }

    @Test
    public void test_viewUserBankAccountsWithValidCredentials(){
        //Arrange
        List<BankAccountEntity> listOfUserBankAccounts = new ArrayList<>();
        Set<BankAccountDTO> listOfUserBankAccountsDTO ;
        UserAccountDTO userAccountDTO = new UserAccountDTO("valid");
        UserAccountEntity userAccountEntity = new UserAccountEntity();

        when(mockUserAccountRepository.findByUsername(userAccountDTO.getUsername())).thenReturn(userAccountEntity);
        when(mockBankAccountRepository.findAllByUserAccountEntity(userAccountEntity)).thenReturn(listOfUserBankAccounts);

        //Act
        listOfUserBankAccountsDTO = sut.viewUserBankAccounts(userAccountDTO);

        //Assert
        Assert.assertNotNull(userAccountDTO);
        verify(mockUserAccountRepository, times(1)).findByUsername(userAccountDTO.getUsername());
        verify(mockBankAccountRepository, times(1)).findAllByUserAccountEntity(userAccountEntity);

    }

    @Test(expected = NullPointerException.class)
    public void test_viewUserAccountsWithInvalidCredentials(){
        //Arrange
        List<BankAccountEntity> listOfUserBankAccounts = new ArrayList<>();
        Set<BankAccountDTO> listOfUserBankAccountsDTO ;
        UserAccountDTO userAccountDTO = new UserAccountDTO("invalid");
        UserAccountEntity userAccountEntity = new UserAccountEntity();

        when(mockUserAccountRepository.findByUsername(userAccountDTO.getUsername())).thenThrow(NullPointerException.class);
        when(mockBankAccountRepository.findAllByUserAccountEntity(userAccountEntity)).thenThrow(NullPointerException.class);

        //Act
        listOfUserBankAccountsDTO = sut.viewUserBankAccounts(userAccountDTO);

        //Assert
        Assert.assertNull(userAccountDTO);
        verify(mockUserAccountRepository, times(0)).findByUsername(userAccountDTO.getUsername());
        verify(mockBankAccountRepository, times(0)).findAllByUserAccountEntity(userAccountEntity);

    }

    @Test
    public void test_depositWithValidDepositDTO(){
        //arrange
        DepositRequestDTO depositRequestDTO = new DepositRequestDTO("1","250");
        Optional<BankAccountEntity> optionalBankAccountEntity = Optional.empty();
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        Boolean successfulDeposit;
    
        when(mockBankAccountRepository.findById(Integer.parseInt(depositRequestDTO.getBankAccountId()))).thenReturn(optionalBankAccountEntity);
        bankAccountEntity = optionalBankAccountEntity.get();
        when(mockBankAccountRepository.save(bankAccountEntity)).thenReturn(bankAccountEntity);

        //act
        successfulDeposit = sut.deposit(depositRequestDTO);
        //assert
        Assert.assertNotNull(successfulDeposit);
        verify( mockBankAccountRepository, times(1)).findById(Integer.parseInt(depositRequestDTO.getBankAccountId()));
        verify( mockBankAccountRepository, times(1)).save(bankAccountEntity);



    }

}
