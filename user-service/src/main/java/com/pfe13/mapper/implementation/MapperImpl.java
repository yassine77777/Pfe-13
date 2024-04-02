package com.pfe13.mapper.implementation;

import com.pfe13.dto.UserDto;
import com.pfe13.entity.User;
import com.pfe13.mapper.Mapper;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperImpl implements Mapper {


    @Override
    public UserDto fromUser(@NonNull User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole());
    }

    @Override
    public User fromUserDto(UserDto userDto) {
        return User.builder()
                .id(userDto.id()).name(userDto.name())
                .email(userDto.email()).password(userDto.password())
                .role(userDto.role())
                .build();
    }

    @Override
    public List<UserDto> fromListOfUsers(List<User> users) {
        return users.stream().map(this::fromUser).toList();
    }
    public MapperImpl(){
        super();
    }
}
