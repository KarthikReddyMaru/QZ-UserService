package com.qz.userservice.mapper;

import com.qz.userservice.dto.UserDto;
import com.qz.userservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements BiMapper<User, UserDto> {

    @Override
    public UserDto mapToDto(User user) {
        return null;
    }

    @Override
    public User mapToEntity(UserDto dto) {
        return null;
    }
}
