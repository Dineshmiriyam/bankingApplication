package javaproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import javaproject.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount , Long>
{
	

}
