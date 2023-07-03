package com.example.mpds.api;

import com.example.mpds.entity.UserEntity;
import com.example.mpds.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginAPI {
    @Autowired
    private UserRepository userRepository;


    @GetMapping(value = "/login")
    public String loginPage(){
        return "login";
    }
    @GetMapping(value = "/access")
    public String accessDenied(Model model){
        String mess="Access denied";
        model.addAttribute("mess",mess);
        return "login";
    }
    @PostMapping(value = "/authentication")
    public RedirectView authenUser(@RequestParam(value = "username")String userName,
                              @RequestParam(value = "password")String password,
                              HttpSession session){
        UserEntity entity=userRepository.findOneByUserName(userName);
        if (entity == null||password!=entity.getPassword()) {
            // Xử lý khi người dùng không tồn tại hoặc thông tin đăng nhập không chính xác
            return new RedirectView("/login");
        }


        // Lấy giá trị permission để xác định vai trò
//        int permission = entity.getPermission();
//        String role;
//        if (permission == 0) {
//            role = "ROLE_USER";
//        } else if (permission == 1) {
//            role = "ROLE_ADMIN";
//        } else {
//            // Xử lý khi giá trị permission không hợp lệ
//            return new RedirectView("/login");
//        }
        session.setAttribute("username",entity.getUserName());
        // Tạo đối tượng Authentication và đặt vào SecurityContextHolder
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password, AuthorityUtils.createAuthorityList(role));
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return new RedirectView("/shop");
    }

}
