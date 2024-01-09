package com.cal.calbackend.user.service;

import com.cal.calbackend.user.exception.UserAlreadyExistsException;
import com.cal.calbackend.user.model.User;
import com.cal.calbackend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository registeredUserRepository;

    public User registerUser(String email) throws UserAlreadyExistsException {
        User newUser = new User();
        newUser.setEmail(email);

        try {
            return this.registeredUserRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException();
        }
    }

    public User findUser(String email){
        return this.registeredUserRepository.findByEmail(email);
    }

}
