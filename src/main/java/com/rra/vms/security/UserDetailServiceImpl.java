package com.rra.vms.security;


import com.rra.vms.entities.User;
import com.rra.vms.exceptions.UserNotFoundException;
import com.rra.vms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl  implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user=  userRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User with that email is not found"));
       List<GrantedAuthority> authorities= user.getUserRoles();
        return  new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }
}

