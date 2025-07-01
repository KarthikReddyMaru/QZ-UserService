package com.qz.userservice.mapper;

@FunctionalInterface
public interface Mapper<K, V> {
    V map(K obj);
}
