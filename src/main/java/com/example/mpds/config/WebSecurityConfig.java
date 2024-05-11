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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                //Use token
//                .csrf().ignoringRequestMatchers(
//                        new AntPathRequestMatcher("/admin/**")
//                )
//                .and()
                .authorizeHttpRequests((requests) -> requests
                        //cau hinh folder permission
                        .requestMatchers("/css/**", "/js/**" , "/vendor/**" , "/img/**", "/icons/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/shop").permitAll()
                        .requestMatchers("/cart/**").permitAll()
                        .requestMatchers("/checkout/**").permitAll()

                        .requestMatchers("/authentication").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        //Only admin can access admin page
                        .requestMatchers("/users/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/product/**").hasAuthority("ROLE_ADMIN")
//                        .requestMatchers("/users/**").hasAuthority("ROLE_ADMIN")


                                .anyRequest().authenticated()
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
                .exceptionHandling().accessDeniedPage("/");

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
