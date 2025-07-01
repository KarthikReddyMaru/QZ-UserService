package com.qz.userservice.mapper;

import com.qz.userservice.dto.UserDto;
import com.qz.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDto implements Mapper<User, UserDto> {
    @Override
    public UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}
