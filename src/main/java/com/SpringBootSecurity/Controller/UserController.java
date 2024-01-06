package com.SpringBootSecurity.Controller;

import com.SpringBootSecurity.Models.UserInfo;
import com.SpringBootSecurity.Models.Users;
import com.SpringBootSecurity.Services.UserInfoService;
import com.SpringBootSecurity.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;


    // All Users
    @GetMapping("/alluser")
    public List<Users> getUsers(){
        return userService.getAllUsers();
    }

    // Single User
    @GetMapping("/{username}")
    public Users getUser(@PathVariable String username){
        return userService.getSingleUser(username);
    }

    // Add User
    @PostMapping("/adduser")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Users addUser(@RequestBody Users user){
        return userService.addUser(user);
    }



}
