package com.pfe13.service;

import com.pfe13.dto.*;
import com.pfe13.entity.User;
import com.pfe13.exception.EmailAlreadyExistException;
import com.pfe13.exception.UserNotFoundException;
import org.springframework.security.core.AuthenticationException;

import java.util.List;
import java.util.Locale;

public interface UserService {
    UserDto save(UserDto userDto) throws EmailAlreadyExistException;

    UserDto update(String id, UserDto userDto) throws UserNotFoundException, EmailAlreadyExistException;

    UserDto getById(String id) throws UserNotFoundException;

    void deleteById(String id);
    List<User> getAllUsers();
    LoginResponse loginUser(LoginDto loginDto);

}
