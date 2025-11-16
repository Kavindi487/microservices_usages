package com.example.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.product.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
}
