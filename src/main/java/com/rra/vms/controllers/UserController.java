package com.rra.vms.controllers;


import com.rra.vms.entities.User;
import com.rra.vms.request.RegisterUserRequest;
import com.rra.vms.response.ApiResponse;
import com.rra.vms.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody RegisterUserRequest registerUserRequest) {
        User response = userService.createUser(registerUserRequest);
        return new ApiResponse<>("User Registered Successfully", HttpStatus.CREATED,response).toResponseEntity();
    }
}
