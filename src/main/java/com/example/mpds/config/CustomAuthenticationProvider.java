package com.example.mpds.config;

import com.example.mpds.entity.UserEntity;
import com.example.mpds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserEntity user = userRepository.findOneByUserName(username).orElseThrow(()->new RuntimeException("Entity Not Found"));
        if (user == null || !password.equals(user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // Gán vai trò dựa trên thuộc tính permission của user
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user.getPermission()==1){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else{
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }


        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
    }
}