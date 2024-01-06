package com.cal.calbackend.user.controller;

import com.cal.calbackend.common.exception.ApplicationException;
import com.cal.calbackend.common.model.SuccessResponse;
import com.cal.calbackend.user.exception.UserAlreadyExistsException;
import com.cal.calbackend.user.model.User;
import com.cal.calbackend.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("register")
    public SuccessResponse<User> registerUser(@AuthenticationPrincipal Jwt jwt){
        User newUser;
        if(jwt == null) throw new ApplicationException("The user is not authenticated.", HttpStatus.OK, null);

        final String email = "ganeshpalankar01@gmail.com";
        if(email == null) throw new ApplicationException("User's email is not available", HttpStatus.BAD_REQUEST, null);

        User user = new User();
        user.setEmail(email);

        try {
            newUser = userService.registerUser(email);
        }
        catch(UserAlreadyExistsException e){
            return new SuccessResponse<>("User already exists", this.userService.findUser(email));
        }

        return new SuccessResponse<>("Successfully registered", newUser);
    }

    @GetMapping("details")
    public String getDetails(){
        return "Some details to be displayed here";
    }

}
