package com.rra.vms.services;

import com.rra.vms.entities.User;
import com.rra.vms.request.RegisterUserRequest;

public interface IUserService {

    User createUser(RegisterUserRequest registerUserRequest);
}
