package com.solvd.hospital.service;

public interface IJacksonService<T> {
    void serialize(T t, String file);
    T deserialize(String file, String className);
}
