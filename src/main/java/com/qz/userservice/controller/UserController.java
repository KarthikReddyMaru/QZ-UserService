package com.qz.userservice.controller;

import com.qz.userservice.dto.UserDto;
import com.qz.userservice.exception.UserNotFoundException;
import com.qz.userservice.model.User;
import com.qz.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) throws UserNotFoundException {
        UserDto userDTO = userService.findByUserName(username);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody User user) {
        return ResponseEntity.created(URI.create("/user")).body(userService.save(user));
    }

}
