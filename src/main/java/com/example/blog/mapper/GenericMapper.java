package com.example.blog.mapper;

public interface GenericMapper<T,V,U> {
    T toDto(V v);

    V toModel(U u);
}
