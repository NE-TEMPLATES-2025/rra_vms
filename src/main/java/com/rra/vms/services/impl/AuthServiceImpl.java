package com.rra.vms.services.impl;

import com.rra.vms.entities.User;
import com.rra.vms.exceptions.UnauthorizedException;
import com.rra.vms.repository.UserRepository;
import com.rra.vms.request.LoginRequest;
import com.rra.vms.response.AuthResponse;
import com.rra.vms.security.JwtService;
import com.rra.vms.security.UserDetailServiceImpl;
import com.rra.vms.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final JwtService jwtService;
    private final UserDetailServiceImpl userDetailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        Optional<User> _user= userRepository.findByEmail(loginRequest.email());
        if (_user.isEmpty()){
            throw new UnauthorizedException("Invalid Email of Password");
        }
        User user=_user.get();
        if (!passwordEncoder.matches(loginRequest.password(),user.getPassword())){
            throw new UnauthorizedException("Invalid Email or Password");
        }

        UserDetails userDetails = userDetailService.loadUserByUsername(user.getEmail());
        Authentication authToken= new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

//        Claims
        Map<String,Object> claims = new HashMap<>();
        claims.put("email",user.getEmail());
        String token= jwtService.generateToken(claims,userDetails);

        return new AuthResponse(token,user);

    }
}
