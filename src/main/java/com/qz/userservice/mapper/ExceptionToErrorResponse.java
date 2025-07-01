package com.qz.userservice.mapper;

import com.qz.userservice.dto.ErrorResponse;

import java.time.LocalDateTime;

public class ExceptionToErrorResponse<T extends Exception> implements Mapper<Exception, ErrorResponse> {
    @Override
    public ErrorResponse map(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }
}
