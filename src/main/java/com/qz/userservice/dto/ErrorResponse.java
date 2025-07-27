package com.qz.userservice.dto;

import com.qz.userservice.mapper.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Dto {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}

