package com.qz.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qz.userservice.mapper.Dto;
import lombok.Data;

@Data
public class UserDto implements Dto {
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
