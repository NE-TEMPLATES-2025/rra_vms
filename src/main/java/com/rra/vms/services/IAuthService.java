package com.rra.vms.services;


import com.rra.vms.request.LoginRequest;
import com.rra.vms.response.AuthResponse;

public interface IAuthService {

    AuthResponse login(LoginRequest loginRequest);
}
