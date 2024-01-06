package com.SpringBootSecurity.Services;


import com.SpringBootSecurity.Models.Users;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<Users> list=new ArrayList<>();

    public UserService(){
        list.add(new Users("satyam","satyam","satyam@gmail.com"));
        list.add(new Users("matyam","matyam","matyam@gmail.com"));
    }

    // Get All Users
    public List<Users> getAllUsers(){
        return this.list;
    }

    // Get Single User
    public Users getSingleUser(String username){
        return this.list.stream().filter((user)->user.getUsername().equals(username)).findAny().orElse(null);
    }

    // Add User
    public Users addUser(Users user){
         this.list.add(user);
         return user;
    }

}
