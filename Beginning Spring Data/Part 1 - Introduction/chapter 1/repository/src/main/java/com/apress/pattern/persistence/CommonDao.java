package com.apress.pattern.persistence;

import java.util.List;
import java.util.Optional;

public interface CommonDao<T> {
    
    Optional<T> get(long id);

    List<T> getAll();
    
    void save(T t);
    
    void delete(T t);
}
