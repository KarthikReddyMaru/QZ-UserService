package com.qz.userservice.mapper;

@FunctionalInterface
public interface Mapper<K, V extends Dto> {
    V map(K obj);
}
