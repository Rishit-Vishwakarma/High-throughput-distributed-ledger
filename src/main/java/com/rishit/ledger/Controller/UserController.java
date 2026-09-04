package com.rishit.ledger.Controller;

import com.rishit.ledger.DTO.Request.CreateUserRequest;
import com.rishit.ledger.DTO.Response.UserResponse;
import com.rishit.ledger.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user/newUser")
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest){
        UserResponse userResponse = userService.createUser(createUserRequest);
        return userResponse;
    }
}
