package com.SpringBootSecurity.Models;

import lombok.Data;

@Data
public class Users {

    private String username;
    private String password;
    private String email;

    public Users(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
