package com.cal.calbackend.user.config;

import com.cal.calbackend.user.model.CurrentUser;
import com.cal.calbackend.user.model.User;
import com.cal.calbackend.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CurrentUserResolverTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private CurrentUserResolver currentUserResolver;


    @Test
    void supportsParameter_shouldReturnTrueForCurrentUserAnnotation() {
        // Arrange
        MethodParameter parameter = mock(MethodParameter.class);
        when(parameter.getParameterType()).thenReturn((Class) User.class);
        when(parameter.hasParameterAnnotation(CurrentUser.class)).thenReturn(true);

        // Act
        boolean result = currentUserResolver.supportsParameter(parameter);

        // Assert
        assertTrue(result);
    }

    @Test
    void supportsParameter_shouldReturnFalseForNonCurrentUserAnnotation() {
        // Arrange
        MethodParameter parameter = mock(MethodParameter.class);
        when(parameter.getParameterType()).thenReturn((Class) String.class);
        when(parameter.hasParameterAnnotation(CurrentUser.class)).thenReturn(false);

        // Act
        boolean result = currentUserResolver.supportsParameter(parameter);

        // Assert
        assertFalse(result);
    }

    @Test
    void resolveArgument_shouldReturnUserWhenUserExists() throws Exception {
        String testEmail = "test@example.com";
        MethodParameter parameter = mock(MethodParameter.class);
        ModelAndViewContainer mavContainer = mock(ModelAndViewContainer.class);
        NativeWebRequest webRequest = mock(NativeWebRequest.class);
        WebDataBinderFactory binderFactory = mock(WebDataBinderFactory.class);
        Authentication authentication = mock(Authentication.class);
        Object mockUserJwt = mockUserJwt(testEmail);
        when(authentication.getPrincipal()).thenReturn(mockUserJwt);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User mockUser = new User();
        mockUser.setEmail(testEmail);
        when(userService.findUser(testEmail)).thenReturn(mockUser);

        // Act
        User result = (User) currentUserResolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory);

        // Assert
        assertEquals(mockUser.getEmail(), result.getEmail());
    }

    @Test
    void resolveArgument_shouldThrowExceptionWhenUserDoesNotExists(){
        MethodParameter parameter = mock(MethodParameter.class);
        ModelAndViewContainer mavContainer = mock(ModelAndViewContainer.class);
        NativeWebRequest webRequest = mock(NativeWebRequest.class);
        WebDataBinderFactory binderFactory = mock(WebDataBinderFactory.class);
        Authentication authentication = mock(Authentication.class);

        Object mockUserJwt = mockUserJwt("nonexistent@example.com");
        when(authentication.getPrincipal()).thenReturn(mockUserJwt);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(userService.findUser("nonexistent@example.com")).thenReturn(null);
    }

    private Object mockUserJwt(String email) {
        Jwt jwt = mock(Jwt.class);
        when(jwt.getClaim("email")).thenReturn(email);
        return jwt;
    }


}