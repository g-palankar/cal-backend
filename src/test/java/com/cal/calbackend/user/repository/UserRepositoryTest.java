package com.cal.calbackend.user.repository;

import com.cal.calbackend.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindMyEmail_thenReturnRegisteredUser(){
        User user = new User();
        user.setEmail("ganeshpalankar01@gmail.com");
        this.userRepository.save(user);

        User savedUser = userRepository.findByEmail("ganeshpalankar01@gmail.com");

        assertEquals(savedUser.getEmail(), user.getEmail());
        assertNotNull(savedUser.getId());
    }

    @Test
    public void whenSaveExistingUser_thenThrowException(){
        User user1 = new User();
        user1.setEmail("ganeshpalankar01@gmail.com");
        this.userRepository.save(user1);

        User user2 = new User();
        user2.setEmail("ganeshpalankar01@gmail.com");

        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user2));
    }

}
