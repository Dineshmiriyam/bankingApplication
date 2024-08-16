package javaproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import javaproject.entities.User;

public interface UserRepository extends JpaRepository<User,Long>
{
	User findBuUsername(String username);

}
