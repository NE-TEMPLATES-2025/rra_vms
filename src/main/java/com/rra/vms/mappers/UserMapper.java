package com.rra.vms.mappers;


import com.rra.vms.entities.Role;
import com.rra.vms.entities.User;
import com.rra.vms.repository.RoleRepository;
import com.rra.vms.repository.UserRepository;
import com.rra.vms.request.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {
private final PasswordEncoder passwordEncoder;


    public User toUser(RegisterUserRequest registerUserRequest) {


        return User
                .builder()
                .firstName(registerUserRequest.firstName())
                .lastName(registerUserRequest.lastName())
                .email(registerUserRequest.email())
                .password(passwordEncoder.encode(registerUserRequest.password()))
                .phoneNumber(registerUserRequest.phoneNumber())
                .nationalId(registerUserRequest.nationalId())
                .build();


    }

}
