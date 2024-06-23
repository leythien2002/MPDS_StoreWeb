package com.example.mpds.config;

import com.example.mpds.entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        boolean hasUserRole = false;
        boolean hasAdminRole = false;
        boolean hasSalerRole =false;
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        HttpSession session=request.getSession();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                hasUserRole = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_SELLER")) {
                hasSalerRole=true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
                hasAdminRole = true;
                break;
            }
        }

        UserEntity principal = (UserEntity) authentication.getPrincipal();
        //nen encode roi hien thi hay la encode trong du lieu luon ?
        String name=principal.getUserName();
        Integer userId=principal.getId();
//Cho nay la name ko phai username
        session.setAttribute("username", name);
        session.setAttribute("userId", userId);

        if (hasUserRole) {
            session.setAttribute("role", "ROLE_USER");
            redirectStrategy.sendRedirect(request, response, "/");
            System.out.println("USER LOGGED IN");
        } else if (hasSalerRole) {
            session.setAttribute("role", "ROLE_SELLER");
            redirectStrategy.sendRedirect(request, response, "/invoice");
            System.out.println("SELLER LOGGED IN");
        } else if (hasAdminRole) {
            session.setAttribute("role", "ROLE_ADMIN");
            redirectStrategy.sendRedirect(request, response, "/admin");
            System.out.println("ADMIN LOGGED IN");

        } else {
            throw new IllegalStateException();
        }
    }
}
