package com.example.product.controller;


import com.example.product.dto.UserDTO;
import com.example.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        return userService.saveUsers(userDTO);
    }

    @PostMapping("/updateUser")
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUsers(userDTO);
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestBody UserDTO userDTO){
        return userService.deleteUser(@RequestBody ;
    }
}
