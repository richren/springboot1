package com.heyjie.springboot1.repository.security;

import com.heyjie.springboot1.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);
}
