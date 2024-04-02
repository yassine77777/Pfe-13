package com.pfe13.controller;

import com.pfe13.dao.UserDao;
import com.pfe13.dto.*;
import com.pfe13.entity.User;
import com.pfe13.exception.EmailAlreadyExistException;
import com.pfe13.exception.UserNotFoundException;
import com.pfe13.service.UserService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/primatec/users")
public class UserRestController {
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;


    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDto save(@RequestBody UserDto userDto) throws EmailAlreadyExistException {
        return userService.save(userDto);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDto update(@PathVariable String id, @RequestBody  UserDto userDto) throws UserNotFoundException,EmailAlreadyExistException{
        return userService.update(id, userDto);
    }
    @GetMapping("/get/{id}")
    public UserDto getById(@PathVariable String id) throws UserNotFoundException {
        return userService.getById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return users.stream().toList();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable String id){
        userService.deleteById(id);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDto loginDTO)
    {
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(@NotNull Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
