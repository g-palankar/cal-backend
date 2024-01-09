package com.cal.calbackend.user.contoller;

import com.cal.calbackend.user.controller.UserController;
import com.cal.calbackend.user.exception.UserAlreadyExistsException;
import com.cal.calbackend.user.model.User;
import com.cal.calbackend.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    final String testEmail = "test@example.com";
     JwtRequestPostProcessor mockJwt = jwt().jwt(jwt -> jwt
        .header("kid", "one").claim("email",testEmail));
    @Test
    public void testRegisterUser_newUser() throws Exception {
        User testUser = new User();
        testUser.setId(1L);
        testUser.setEmail(testEmail);
        when(userService.registerUser(testEmail)).thenReturn(testUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register").with(mockJwt))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(testUser.getId()));
    }

    @Test
    public void testRegisterUser_existingUser() throws Exception {
        User testUser = new User();
        testUser.setId(1L);
        testUser.setEmail(testEmail);

        when(userService.registerUser(testEmail)).thenThrow(UserAlreadyExistsException.class);
        when(userService.findUser(testEmail)).thenReturn(testUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register").with(mockJwt))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(testUser.getId()));

    }
}
