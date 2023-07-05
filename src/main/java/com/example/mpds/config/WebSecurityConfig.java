package com.example.mpds.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig   {
    //khi su dung spring security thi session se mac dinh bi vo hieu hoa
    //SC cung su dung CSRF de tao token --> nhung cau query ajax se bi vo hieu
@Autowired
private CustomAuthenticationSuccessHandler successHandler;
@Autowired
private CustomAuthenticationFailHandler failHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .csrf().disable() //vo hieu hoa csrf de su dung ajax (hoac co the su dung the meta va tao token)
                .authorizeHttpRequests((requests) -> requests
                        //cau hinh folder permission
                        .requestMatchers("/css/**", "/js/**" , "/vendor/**" , "/img/**", "/icons/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/shop").hasRole("USER")
                        .requestMatchers("/cart/**").permitAll()
                        .requestMatchers("/checkout/**").permitAll()
                        .requestMatchers("/authentication").permitAll()
                        .anyRequest().authenticated()
//                        hasRole("ADMIN")
                )
                .formLogin((form) -> form
                        .loginPage("/login")
//                        .loginProcessingUrl("/authentication")
                                .successHandler(successHandler)
//                                .failureHandler(failHandler)
//                        .failureUrl("/login?error=Login Fail")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll()
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Kích hoạt lại session
                )
                .exceptionHandling().accessDeniedPage("/access");

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
