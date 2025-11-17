package com.example.product.service;

import com.example.product.dto.UserDTO;
import com.example.product.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepo.findAll();
        return modelMapper.map(userList,new TypeToken<List<UserDTO><(){}.getType());
    }
}
