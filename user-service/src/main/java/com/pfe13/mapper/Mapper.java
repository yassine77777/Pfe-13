package com.pfe13.mapper;

import com.pfe13.dto.UserDto;
import com.pfe13.entity.User;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public interface Mapper {
    UserDto fromUser(User user);

    User fromUserDto(UserDto userDto);

    List<UserDto> fromListOfUsers(@NotNull List<User> users);


}
