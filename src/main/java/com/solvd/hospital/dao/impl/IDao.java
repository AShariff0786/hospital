package com.solvd.hospital.dao.impl;

public interface IDao<T> {
    void insert (T t);
    void update (T t);
    void deleteById(int id);
    T getById(int id);
}
