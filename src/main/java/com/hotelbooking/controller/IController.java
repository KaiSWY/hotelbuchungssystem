package com.hotelbooking.controller;

public interface IController<T, ID>
{
    T get(ID id);
    void post(T entity);
    void delete(ID entity);
    Object getAnalysis(ID id);
}
