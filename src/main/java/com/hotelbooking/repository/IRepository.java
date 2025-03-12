package com.hotelbooking.repository;

import java.util.List;

public interface IRepository<T, ID>
{
    void add(T entity);

    void update(T entity);

    void deleteById(ID is);

    T getById(ID id);

    List<T> getAll();
}
