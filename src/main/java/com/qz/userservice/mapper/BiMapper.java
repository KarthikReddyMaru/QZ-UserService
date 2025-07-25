package com.qz.userservice.mapper;

public interface BiMapper<T, V extends Dto> {
    T mapToEntity(V dto);
    V mapToDto(T entity);
}
