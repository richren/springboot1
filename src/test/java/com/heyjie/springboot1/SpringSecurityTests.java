package com.heyjie.springboot1;

import com.heyjie.springboot1.model.security.User;
import com.heyjie.springboot1.repository.security.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecurityTests {
    @Autowired
    UserRepository userRepository;
    @Test
    public  void Test1(){
        Optional<User> userOptional = userRepository.findById(2L);
        for(GrantedAuthority ga :userOptional.get().getAuthorities()){
            System.out.println(ga.getAuthority());
        }

    }
}
