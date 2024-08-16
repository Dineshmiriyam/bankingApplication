package javaproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaproject.entities.BankAccount;
import javaproject.entities.User;
import javaproject.repositories.BankAccountRepository;
import javaproject.repositories.UserRepository;

@Service
public class AccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserRepository userRepository;

    public void createAccount(User user, String accountNumber) {
        BankAccount account = new BankAccount();
        account.setAccountNumber(accountNumber);
        account.setBalance(0.0);
        account.setOwner(user);
        bankAccountRepository.save(account);
    }

    public void deposit(Long accountId, Double amount) {
        BankAccount account = bankAccountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountId));
        account.setBalance(account.getBalance() + amount);
        bankAccountRepository.save(account);
    }

    public void withdraw(Long accountId, Double amount) {
        BankAccount account = bankAccountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountId));
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        bankAccountRepository.save(account);
    }

    public Double getBalance(Long accountId) {
        BankAccount account = bankAccountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountId));
        return account.getBalance();
    }
}
