package javaproject.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaproject.entities.User;
import javaproject.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        // Perform any necessary checks or preprocessing
        userRepository.save(user);
    }

    // Other user-related methods
}
