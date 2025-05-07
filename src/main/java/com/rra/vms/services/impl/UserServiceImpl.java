package com.rra.vms.services.impl;

import com.rra.vms.entities.Role;
import com.rra.vms.entities.User;
import com.rra.vms.mappers.UserMapper;
import com.rra.vms.repository.RoleRepository;
import com.rra.vms.repository.UserRepository;
import com.rra.vms.request.RegisterUserRequest;
import com.rra.vms.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements IUserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public User createUser(RegisterUserRequest registerUserRequest) {

            List<Role> roles = roleRepository.findAllById(registerUserRequest.roleIds());

            User user = userMapper.toUser(registerUserRequest);
            user.setRoles(roles);

            return userRepository.save(user);


    }
}
