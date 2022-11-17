package com.example.blog.service;

public interface GenericService<T, V> {
    T create(V entity);

    T getById(Long id);

    T update(Long id, V entity);

    void deleteById(Long id);
}
