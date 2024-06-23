package com.example.mpds.config;

import com.example.mpds.entity.UserEntity;
import com.example.mpds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user=new UserEntity();
        user=userRepository.findOneByUserName(username).orElseThrow(()->new RuntimeException("Entity Not Found"));
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user.getPermission()==1){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else{
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        if (user != null ) {
//            UserDetails userDetails=new UserDetails(user.getUserName(),user.getPassword(),authorities);
//            tao userDetails
            return User.withUsername(user.getUserName())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .authorities(authorities)
                    .build();
        }
        else{
            throw new UsernameNotFoundException("Login Fail");
        }

    }
}
