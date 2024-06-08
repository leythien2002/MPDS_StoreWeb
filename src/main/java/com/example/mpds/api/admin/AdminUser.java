package com.example.mpds.api.admin;

import com.example.mpds.entity.UserEntity;
import com.example.mpds.services.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class AdminUser {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUser(Model model) throws ExecutionException, InterruptedException {
        List<UserEntity> tmp=userService.getAllUsers();
        model.addAttribute("listUser",tmp);
        System.out.println("CO CHAY VAO GET LIST USER ");

        return "users";
    }

    @DeleteMapping(value = "/{email}")
    @ResponseBody
    public void deleteUser(@PathVariable(value ="email") String email) throws ExecutionException, InterruptedException {
        System.out.println("CO CHAY VAO DELETE "+ email);
        userService.deleteById(email);
    }
    @PutMapping
    public void updateUser(@RequestBody UpdateUserRequest user) throws ExecutionException, InterruptedException {
        System.out.println("CO CHAY VAO update user");

        userService.updateUser(user);
    }

}

