package com.cal.calbackend.user.service;

import com.cal.calbackend.user.exception.UserAlreadyExistsException;
import com.cal.calbackend.user.model.User;
import com.cal.calbackend.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void whenRegisterUser_thenUserIsSaved() throws UserAlreadyExistsException {
        String testEmail = "test@example.com";
        Long testId = 1L;

        User newUser = new User();
        newUser.setEmail(testEmail);
        newUser.setId(testId);

        User newUserCopy = new User();
        newUserCopy.setId(testId);
        newUserCopy.setEmail(testEmail);

        when(userRepository.save(any(User.class))).thenReturn(newUserCopy);

        User returnedUser = this.userService.registerUser(testEmail);
        assertThat(returnedUser).usingRecursiveComparison().isEqualTo(newUser);


    }

    @Test
    public void whenFindUserByEmail_userIsReturned() {
        String testEmail = "test@example.com";
        User user = new User();
        user.setEmail(testEmail);
        user.setId(1L);

        when(userRepository.findByEmail(testEmail)).thenReturn(user);

        assertThat(this.userService.findUser(testEmail)).usingRecursiveComparison().isEqualTo(user);
    }

}
