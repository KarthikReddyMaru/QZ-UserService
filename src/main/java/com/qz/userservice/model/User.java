package com.qz.userservice.model;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private Credentials credentials;
    private List<?> groups;

}
