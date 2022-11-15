package com.example.blog.service;

public interface GenericService<T,V,U> {
    T create(V entity);

    U getAll(int pageNo, int pageSize, String sortBy, String sortDir);

    T getById(Long id);

    T update(Long id, V entity);

    void deleteById(Long id);
}
