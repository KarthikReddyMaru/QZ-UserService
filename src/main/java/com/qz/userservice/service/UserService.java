package com.qz.userservice.service;

import com.qz.userservice.dto.UserDto;
import com.qz.userservice.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    public UserDto findByUserName(String username) throws UserNotFoundException {
        return new UserDto();
    }

    public void save(UserDto newUser) {

    }
}
