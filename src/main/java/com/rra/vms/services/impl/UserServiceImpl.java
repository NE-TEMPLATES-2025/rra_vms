package com.rra.vms.services.impl;

import com.rra.vms.entities.Role;
import com.rra.vms.entities.User;
import com.rra.vms.exceptions.BadRequestException;
import com.rra.vms.mappers.UserMapper;
import com.rra.vms.repository.RoleRepository;
import com.rra.vms.repository.UserRepository;
import com.rra.vms.request.RegisterUserRequest;
import com.rra.vms.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements IUserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public User createUser(RegisterUserRequest registerUserRequest) {

            List<Role> roles = roleRepository.findAllById(registerUserRequest.roleIds());

            Optional<User> existingUser= userRepository.findByEmail(registerUserRequest.email());

            if (existingUser.isPresent()){
                throw new BadRequestException("User with the same email already exists");
            }
            User user = userMapper.toUser(registerUserRequest);
            user.setRoles(roles);
            return userRepository.save(user);
    }
}
