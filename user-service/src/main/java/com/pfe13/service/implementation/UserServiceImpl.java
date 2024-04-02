package com.pfe13.service.implementation;
import com.pfe13.dao.UserDao;
import com.pfe13.dto.*;
import com.pfe13.entity.User;
import com.pfe13.exception.EmailAlreadyExistException;
import com.pfe13.exception.UserNotFoundException;
import com.pfe13.mapper.Mapper;
import com.pfe13.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private static final String ALREADY_EXIST = "' already exist.";
    private static final String USER_WITH_EMAIL = "A user with email :'";
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;


    @Autowired
    UserDao userDao;
    Mapper mapper;
    public UserServiceImpl(UserDao userDao, Mapper mapper, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, AuthenticationManager authenticationManager1) {
        this.mapper = mapper;
        this.userDao = userDao;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager1;
    }
    @Override
    public UserDto save(UserDto userDto) throws EmailAlreadyExistException {
        log.info("In save() :");
        checkIfEmailExistBeforeSave(userDto);
        User user = mapper.fromUserDto(userDto);
        user.setId(UUID.randomUUID().toString());
        User userSaved = userDao.save(user);
        log.info("customer saved successfully");
        return mapper.fromUser(userSaved);
    }
    @Override
    public UserDto update(String id, UserDto userDto) throws UserNotFoundException, EmailAlreadyExistException {
        log.info("In update() :");
        User user = userDao.findById(id)
                .orElseThrow( ()-> new UserNotFoundException("user you try to update with id = '"+id+"' not found."));
        checkIfEmailExistBeforeUpdate(user, userDto);
        updateUserFields(user, userDto);
        User userUpdate = userDao.save(user);
        log.info("Customer updated successfully");
        return mapper.fromUser(userUpdate);
    }
    @Override
    public UserDto getById(String id) throws UserNotFoundException {
        log.info("In getById() :");
        User user = userDao.findById(id)
                .orElseThrow( ()-> new UserNotFoundException("user with id = '"+id+"' not found."));
        log.info("user with id = '"+id+"' found successfully.");
        return mapper.fromUser(user);
    }

    @Override
    public void deleteById(String id) {
        log.info("In deleteById() :");
        userDao.deleteById(id);
        log.info("user deleted");
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public LoginResponse loginUser(LoginDto loginDto) {
        String msg = "";
        User user = userDao.searchByEmail(loginDto.getEmail());
        if (user != null) {
            String password = loginDto.getPassword();
            String userPassword = user.getPassword();

            if (password.equals(userPassword)) {
                Optional<User> userLogin = userDao.findOneByEmailAndPassword(loginDto.getEmail(), userPassword);
                if (userLogin.isPresent()) {
                    return new LoginResponse("Login Success", user.getName());
                } else {
                    return new LoginResponse("Login Failed", null);
                }
            } else {
                return new LoginResponse("password Not Match", null);
            }
        }else {
            return new LoginResponse("Email not exits", null);
        }
    }

    private void checkIfEmailExists(String email) throws EmailAlreadyExistException {
        if (Boolean.TRUE.equals(userDao.checkIfEmailExists(email))) {
            throw new EmailAlreadyExistException(USER_WITH_EMAIL + email + ALREADY_EXIST);
        }
    }
    private void checkIfEmailExistBeforeSave(@NotNull UserDto userDto) throws  EmailAlreadyExistException {
        checkIfEmailExists(userDto.email());
    }
    private void checkIfEmailExistBeforeUpdate(@NotNull User user, @NotNull UserDto userDto) throws  EmailAlreadyExistException {
        if(!user.getEmail().equals(userDto.email())){
            checkIfEmailExists(userDto.email());
        }
    }
    private void updateUserFields(@NotNull User user, @NotNull UserDto userDto){
        user.setName(userDto.name());
        user.setRole(userDto.role());
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());
    }

}
