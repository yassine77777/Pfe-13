package com.pfe13.config;

import com.pfe13.dao.UserDao;
import com.pfe13.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class OurUserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao ourUserRepo;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> user = ourUserRepo.findByEmail(name);
        return user.map(OurUserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("User Does Not Exist"));
    }
}