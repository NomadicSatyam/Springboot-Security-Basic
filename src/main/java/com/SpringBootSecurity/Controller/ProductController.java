package com.SpringBootSecurity.Controller;


import com.SpringBootSecurity.Models.UserInfo;
import com.SpringBootSecurity.Services.ProductService;
import com.SpringBootSecurity.Services.UserInfoService;
import com.SpringBootSecurity.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }


    // Add User Info
    @PostMapping("/newinfo")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return userInfoService.addUserInfo(userInfo);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }
}
